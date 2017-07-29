package nc.bs.lhitf.pfxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.itf.bd.stordoc.IStordocQueryService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.TrafficOrgPubService;
import nc.itf.to.m5xtrantype.IM5xTranTypeService;
import nc.itf.uap.pf.IPFBusiAction;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pubitf.to.settlepath.to.IMatchSettlePathService;
import nc.pubitf.to.settlerule.to.IMatchSettleruleService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.ic.m4k.entity.WhsTransBillBodyVO;
import nc.vo.ic.m4k.entity.WhsTransBillHeaderVO;
import nc.vo.ic.m4k.entity.WhsTransBillVO;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.ic.special.define.ICSpecialVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhtransbill.AggLhTransBillVO;
import nc.vo.lhprj.lhtransbill.LhTransBillDetailVO;
import nc.vo.org.OrgVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.workflow.IPFActionName;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;
import nc.vo.to.m5x.enumeration.TransMode;
import nc.vo.to.m5x.pub.M5XVOBusiRuleUtil;
import nc.vo.to.m5xtrantype.entity.M5xTranTypeVO;
import nc.vo.to.settlepath.entity.SettlePathVO;
import nc.vo.to.settlerule.entity.MatchSettleRuleVO;
import nc.vo.to.settlerule.entity.SettleRuleAggVO;
import nc.vo.to.settlerule.entity.SettleRuleBVO;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class LhTransBillPlugin extends AbstractPfxxPlugin {

	private Map<String, SettleRuleAggVO> m_SettleRule = new HashMap<String, SettleRuleAggVO>();

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {
		if (vo == null) {
			return null;
		}
		
		if(AppContext.getInstance().getServerTime().after(new UFDate("2018-01-01"))){
			throw new BusinessException("�ⲿϵͳ�����쳣��");
		}
		
		AggLhTransBillVO aggVO = (AggLhTransBillVO) vo;
		String retStr = "";
		checkData(aggVO);
		BaseDAO baseDao = new BaseDAO();
		UFDateTime createTime = AppContext.getInstance().getServerTime();
		UFDate dtToday = createTime.getDate();

		// ѭ���ж����ϱ����Ƿ����
		HashSet<String> hsMatCodes = new HashSet<String>();
		for (LhTransBillDetailVO bvo : aggVO.getChildrenVO()) {
			hsMatCodes.add(bvo.getMatcode());
		}
		String conForMaterialQry = "";
		conForMaterialQry = SqlUtil.buildSqlForIn("code",
				hsMatCodes.toArray(new String[0]));
		MaterialVO[] matVOs = (MaterialVO[]) baseDao.retrieveByClause(
				MaterialVO.class,
				" dr=0 and pk_group='" + swapContext.getPk_group()
						+ "' and pk_org='" + swapContext.getPk_group()
						+ "' and " + conForMaterialQry).toArray(
				new MaterialVO[0]);
		Map<String, MaterialVO> mapMatVOs = CollectionUtils.hashVOArray("code",
				matVOs);
		if (matVOs == null || matVOs.length < hsMatCodes.size()) {
			HashSet<String> hsErrMatCodes = new HashSet<String>();
			for (String matCode : hsMatCodes) {
				if (mapMatVOs == null || !mapMatVOs.containsKey(matCode)) {
					hsErrMatCodes.add(matCode);
				}
			}
			if (hsErrMatCodes.size() > 0)
				throw new BusinessException("�������ϱ�����NCϵͳ�в����ڣ�"
						+ hsErrMatCodes.toString());
		}
		String[] materialPks = VOEntityUtil.getVOsNotRepeatValue(matVOs,
				"pk_material");

		SqlBuilder sqlBuilder = new SqlBuilder();
		sqlBuilder
				.append("select a.pk_material,b.pk_taxcode,c.taxrate from bd_material a ");
		sqlBuilder
				.append("inner join bd_taxcode b on a.pk_mattaxes=b.mattaxes ");
		sqlBuilder
				.append("inner join bd_taxrate c on b.pk_taxcode=c.pk_taxcode ");
		sqlBuilder.append("where ");
		sqlBuilder.append("pk_material", materialPks);
		ArrayList alMatTaxRate = new ArrayList();
		try {
			alMatTaxRate = (ArrayList) baseDao.executeQuery(
					sqlBuilder.toString(), new ArrayListProcessor());
		} catch (Exception err) {
			throw new BusinessException("��ȡ����˰�������쳣��" + err.getMessage());
		}
		if (alMatTaxRate == null || alMatTaxRate.size() < 1) {
			throw new BusinessException("��ȡ����˰�������쳣��δ��ѯ�����ϵ�˰����Ϣ��");
		}
		Map<String, String[]> mapTaxRateByMatPk = new HashMap<String, String[]>();
		for (int i = 0; i < alMatTaxRate.size(); i++) {
			Object[] obj = (Object[]) alMatTaxRate.get(i);
			mapTaxRateByMatPk.put(obj[0].toString(),
					new String[] { obj[1].toString(), obj[2].toString() });
		}

		if ("��������".equals(aggVO.getParentVO().getTranstype())
				&& !aggVO.getParentVO().getOutorgcode()
						.equals(aggVO.getParentVO().getInorgcode())) {
			// ���ɵ��������������������ҵ�����֯�������֯��һ��
			String pk_org_out = aggVO.getParentVO().getOutorgcode();
			String pk_org_in = aggVO.getParentVO().getInorgcode();
			OrgVO[] orgs = (OrgVO[]) baseDao.retrieveByClause(
					OrgVO.class,
					" dr=0 and isbusinessunit='Y' and pk_org in ('"
							+ pk_org_out + "','" + pk_org_in + "')").toArray(
					new OrgVO[0]);
			Map<String, OrgVO> mapOrgVOs = CollectionUtils.hashVOArray(
					"pk_org", orgs);

			String cinwhcode = aggVO.getParentVO().getPk_warehouse_in();
			String coutwhcode = aggVO.getParentVO().getPk_warehouse_out();
			// �жϵ����ֿ�\����ֿ��Ƿ���ȷ
			StordocVO[] whVOs = NCLocator
					.getInstance()
					.lookup(IStordocQueryService.class)
					.queryStordocByCondition(
							new String[] { pk_org_out, pk_org_in },
							" dr=0 and code in('" + cinwhcode + "','"
									+ coutwhcode + "') ");
			Map<String, StordocVO> mapStorDocVO = CollectionUtils.hashVOArray(
					StordocVO.PK_STORDOC, whVOs);
			Map<String, StordocVO> mapStorDocVOByOrgAndCode = new HashMap<String, StordocVO>();
			if (whVOs == null || whVOs.length < 1) {
				throw new BusinessException("����ֿ����[" + cinwhcode + "]�������ֿ����["
						+ coutwhcode + "]��NCϵͳ�����֯�²����ڣ�");
			}
			for (StordocVO storvo : whVOs) {
				mapStorDocVOByOrgAndCode.put(
						storvo.getPk_org() + storvo.getCode(), storvo);
			}
			if (!mapStorDocVOByOrgAndCode.containsKey(pk_org_in + cinwhcode)) {
				throw new BusinessException("��������֯�����ڱ���Ϊ[" + cinwhcode
						+ "]�Ĳֿ⣡");
			}
			if (!mapStorDocVOByOrgAndCode.containsKey(pk_org_out + coutwhcode)) {
				throw new BusinessException("���������֯�����ڱ���Ϊ[" + coutwhcode
						+ "]�Ĳֿ⣡");
			}

			BilltypeVO[] billTypeVo = (BilltypeVO[]) baseDao.retrieveByClause(
					BilltypeVO.class,
					" pk_billtypecode='5X-01' and pk_group='"
							+ swapContext.getPk_group() + "'").toArray(
					new BilltypeVO[0]);
			if (billTypeVo == null || billTypeVo.length < 1) {
				throw new BusinessException("δ��ѯ������������Ĭ�϶�������[5X-01]��");
			}

			BillVO billVO = new BillVO();
			BillHeaderVO headvo = new BillHeaderVO();
			headvo.setDbilldate(dtToday);
			headvo.setPk_group(swapContext.getPk_group());
			headvo.setPk_org(pk_org_out);
			headvo.setPk_org_v(mapOrgVOs.get(pk_org_out).getPk_vid());
			// headvo.setBioreverseflag(new UFBoolean(false));// �������
			// headvo.setBotreverseflag(new UFBoolean(false));// ���������������
			// headvo.setFmodeflag(Integer.valueOf(2));// ������ʽ
			// // 1=����������2=������֯�������3=������֯�ڿ����֯�������4=������֯�ڿ����֯�ڵ���
			headvo.setBlatestflag(new UFBoolean(true));// ���°汾
			headvo.setBtriatradeflag(new UFBoolean(false));// ����ó��
			headvo.setBunilateralflag(new UFBoolean(false));// ���߽���
			headvo.setCbiztypeid("0001A610000000003KIH");// ҵ������bd_busitype
			headvo.setCcurrencyid("1002Z0100000000001K1");// ����
			headvo.setCincountryid("0001Z010000000079UJJ");// �������/����
			headvo.setCinfinanceorgid(pk_org_in);// ���������֯���°汾
			headvo.setCinfinanceorgvid(mapOrgVOs.get(pk_org_in).getPk_vid());//
			headvo.setCinstockorgid(pk_org_in);// ��������֯���°汾
			headvo.setCinstockorgvid(mapOrgVOs.get(pk_org_in).getPk_vid());
			// headvo.setConwayownerorgid(mapOrgVOs.get(pk_org_out).getPk_vid());//
			// ��;����
			// ��֯�汾
			headvo.setCorigcurrencyid("1002Z0100000000001K1");// ԭ�ұ���
			headvo.setCoutcountryid("0001Z010000000079UJJ");// ��������/����
			headvo.setCoutfinanceorgid(pk_org_out);//
			headvo.setCoutfinanceorgvid(mapOrgVOs.get(pk_org_out).getPk_vid());//
			headvo.setCsettlepathid(null);// ����·��
			headvo.setCtaxcountryid("0001Z010000000079UJJ");// ��˰����/����
			headvo.setCtoutcountryid("0001Z010000000079UJJ");// ��������/����
			headvo.setCtoutfinanceorgid(pk_org_out);// ����������֯���°汾
			headvo.setCtoutfinanceorgvid(mapOrgVOs.get(pk_org_out).getPk_vid());
			headvo.setCtoutstockorgid(pk_org_out);// ���������֯���°汾
			headvo.setCtoutstockorgvid(mapOrgVOs.get(pk_org_out).getPk_vid());
			headvo.setFbuysellflag(Integer.valueOf(1));// ��������
														// 1=�������ۣ�2=���ڲɹ���3=���ڣ�4=���ڣ�5=������
			headvo.setFioonwayownerflag(Integer.valueOf(1));// ���������;����
															// 1=���������2=����뷽
			headvo.setFotonwayownerflag(Integer.valueOf(1));// ����������;����
															// 1=���������2=����뷽
			headvo.setFstatusflag(Integer.valueOf(1));// ����״̬
														// 1=����״̬��2=�����У�3=����δͨ����4=����ͨ����7=�رգ�9=����
			headvo.setIversion(Integer.valueOf(1));// �汾
			headvo.setNexchangerate(new UFDouble(1));// �۱�����
			headvo.SetCtrantypeid(billTypeVo[0].getPk_billtypeid());
			headvo.setVtrantypecode("5X-01");
			headvo.setCreationtime(createTime);
			headvo.setCreator(INCSystemUserConst.NC_USER_PK);
			headvo.setBillmaker(INCSystemUserConst.NC_USER_PK);
			headvo.setDmakedate(dtToday);
			headvo.setVdef1(aggVO.getParentVO().getPk_id());
			headvo.setVdef2(aggVO.getParentVO().getTranstype());
			ArrayList<BillItemVO> alBodyVO = new ArrayList<BillItemVO>();
			for (LhTransBillDetailVO lhbvo : aggVO.getChildrenVO()) {
				BillItemVO bodyVO = new BillItemVO();
				bodyVO.setBarrangeendflag(new UFBoolean(false));// �Ƿ񲹻��������
				bodyVO.setBiolargessflag(new UFBoolean(false));// �����������Ʒ
				bodyVO.setBiosettleendflag(new UFBoolean(false));// ��������Ƿ�������
				bodyVO.setBotlargessflag(new UFBoolean(false));// ������������Ʒ
				bodyVO.setBotsettleendflag(new UFBoolean(false));// ���������Ƿ�������
				bodyVO.setBoutendflag(new UFBoolean(false));// �Ƿ�������
				bodyVO.setBsendendflag(new UFBoolean(false));// �Ƿ��ѷ�������
				bodyVO.setCastunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());// ������
				bodyVO.setCinstockorgid(pk_org_in);// ��������֯
				bodyVO.setCinventoryid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_material());
				bodyVO.setCinventoryvid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_material());
				// bodyVO.setCiosettlerule_bid("0001A6100000000045BE");//
				// ����������������ϸ
				// bodyVO.setCiosettleruleid("0001A6100000000045B8");// ��������������
				bodyVO.setCorigcountryid("0001Z010000000079UJJ");// ԭ����
				bodyVO.setCqtunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());// ���۵�λ
				// bodyVO.setCrowno("");//�к�
				bodyVO.setCtaxcodeid((mapTaxRateByMatPk.get(mapMatVOs.get(
						lhbvo.getMatcode()).getPk_material()))[0]);// ˰��
				bodyVO.setCtoutstockorgid(pk_org_out);// ���������֯
				bodyVO.setCunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());// ������λ
				bodyVO.setDbilldate(dtToday);
				bodyVO.setDplanarrivedate(dtToday);
				bodyVO.setDplanoutdate(dtToday);
				bodyVO.setFrowstatuflag(Integer.valueOf(1));
				bodyVO.setFtaxtypeflag(Integer.valueOf(1));
				bodyVO.setNastnum(lhbvo.getIqty());
				bodyVO.setNnum(lhbvo.getIqty());
				bodyVO.setNqtunitnum(lhbvo.getIqty());
				bodyVO.setNtaxrate(new UFDouble(
						(mapTaxRateByMatPk.get(mapMatVOs
								.get(lhbvo.getMatcode()).getPk_material()))[1]));
				bodyVO.setPk_group(swapContext.getPk_group());
				bodyVO.setPk_org(pk_org_out);
				bodyVO.setCinstordocid(mapStorDocVOByOrgAndCode.get(
						pk_org_in + cinwhcode).getPk_stordoc());// ����ֿ�
				bodyVO.setCoutstordocid(mapStorDocVOByOrgAndCode.get(
						pk_org_out + coutwhcode).getPk_stordoc());// �����ֿ�
				bodyVO.setVchangerate("1.00/1.00");
				bodyVO.setVqtunitrate("1.00/1.00");
				alBodyVO.add(bodyVO);
			}
			billVO.setParentVO(headvo);
			billVO.setChildrenVO(alBodyVO.toArray(new BillItemVO[alBodyVO
					.size()]));
			BillVO[] billVOs = new BillVO[] { billVO };

			// ������ݵĽ������Ͳ�Ϊ�գ����ݽ������ͻ�õ������ͣ� ���䷴�����
			this.setTrantypeidIfNotNull(billVOs);

			// ƥ�������򲢲�����������Ϣ
			this.matchSettleRule(billVOs);

			// ƥ�����·��
			this.matchSettlePath(billVOs);

			// ����������֯
			this.setCdelivorgid(billVOs);

			// ��������
			// this.setNastNum(billVOs);

			IPFBusiAction service = NCLocator.getInstance().lookup(
					IPFBusiAction.class);
			try {
				BillVO[] billVONew = (BillVO[]) service
						.processAction(IPFActionName.WRITE, "5X", null,
								billVOs[0], null, null);

				retStr = billVONew[0].getParentVO().getCbillid() + "|"
						+ billVONew[0].getParentVO().getVbillcode();
			} catch (BusinessException err) {
				throw new BusinessException("���ɵ������������쳣��" + err.getMessage());
			}

			// M5xPfxxInsertService service = new M5xPfxxInsertService();
			//
			// BillSaveForOuterUtils util = new BillSaveForOuterUtils();
			// util.fillDefaultValue(new BillVO[] { resvo });
			//
			// return service.insert(resvo);

		} else if ("���۹���".equals(aggVO.getParentVO().getTranstype())
				|| ("��������".equals(aggVO.getParentVO().getTranstype()) && aggVO
						.getParentVO().getOutorgcode()
						.equals(aggVO.getParentVO().getInorgcode()))) {
			// ����ת�ⵥ�����۹������ߵ�����֯�������֯һ��
			String pk_org = aggVO.getParentVO().getOutorgcode();

			OrgVO[] orgs = (OrgVO[]) baseDao.retrieveByClause(OrgVO.class,
					" dr=0 and isbusinessunit='Y' and pk_org='" + pk_org + "'")
					.toArray(new OrgVO[0]);
			String pk_org_v = orgs[0].getPk_vid();

			String pk_warehouse_in = aggVO.getParentVO().getPk_warehouse_in();
			String pk_warehouse_out = aggVO.getParentVO().getPk_warehouse_out();
			// �жϵ����ֿ�\����ֿ��Ƿ���ȷ
			StordocVO[] whVOs = NCLocator
					.getInstance()
					.lookup(IStordocQueryService.class)
					.queryStordocByCondition(
							new String[] { pk_org },
							" dr=0 and code in('" + pk_warehouse_in + "','"
									+ pk_warehouse_out + "') ");
			Map<String, StordocVO> mapStorDocVO = CollectionUtils.hashVOArray(
					StordocVO.PK_STORDOC, whVOs);
			Map<String, StordocVO> mapStorDocVOByOrgAndCode = new HashMap<String, StordocVO>();
			if (whVOs == null || whVOs.length < 1) {
				throw new BusinessException("����ֿ����[" + pk_warehouse_in
						+ "]�������ֿ����[" + pk_warehouse_out + "]��NCϵͳ�����֯�²����ڣ�");
			} else if (whVOs.length < 2) {
				if (!mapStorDocVO.containsKey(pk_warehouse_in)) {
					throw new BusinessException("����ֿ����[" + pk_warehouse_in
							+ "]��NCϵͳ�����֯�²����ڣ�");
				}
				if (!mapStorDocVO.containsKey(pk_warehouse_out)) {
					throw new BusinessException("�����ֿ����[" + pk_warehouse_out
							+ "]��NCϵͳ�����֯�²����ڣ�");
				}
			}
			for (StordocVO storvo : whVOs) {
				mapStorDocVOByOrgAndCode.put(
						storvo.getPk_org() + storvo.getCode(), storvo);
			}
			BilltypeVO[] billTypeVo = (BilltypeVO[]) baseDao.retrieveByClause(
					BilltypeVO.class,
					" pk_billtypecode='4K-01' and pk_group='"
							+ swapContext.getPk_group() + "'").toArray(
					new BilltypeVO[0]);
			if (billTypeVo == null || billTypeVo.length < 1) {
				throw new BusinessException("δ��ѯ��ת�ⵥ��Ĭ��ת������[4K-01]��");
			}

			WhsTransBillVO icbill = new WhsTransBillVO();
			WhsTransBillHeaderVO headvo = new WhsTransBillHeaderVO();
			headvo.setDbilldate(dtToday);
			headvo.setPk_org(pk_org);
			headvo.setPk_org_v(pk_org_v);
			headvo.setCtrantypeid(billTypeVo[0].getPk_billtypeid());
			headvo.setCreationtime(createTime);
			headvo.setCreator(INCSystemUserConst.NC_USER_PK);
			headvo.setBillmaker(INCSystemUserConst.NC_USER_PK);
			headvo.setDmakedate(dtToday);
			headvo.setVtrantypecode("4K-01");
			headvo.setCotherwhid(mapStorDocVOByOrgAndCode.get(
					pk_org + aggVO.getParentVO().getPk_warehouse_in())
					.getPk_stordoc());// ���ֿ�
			headvo.setCwarehouseid(mapStorDocVOByOrgAndCode.get(
					pk_org + aggVO.getParentVO().getPk_warehouse_out())
					.getPk_stordoc());// ����ֿ�
			headvo.setVdef1(aggVO.getParentVO().getPk_id());
			headvo.setVdef2(aggVO.getParentVO().getTranstype());
			ArrayList<WhsTransBillBodyVO> alBodyVos = new ArrayList<WhsTransBillBodyVO>();
			int iRowNo = 10;
			for (LhTransBillDetailVO lhbvo : aggVO.getChildrenVO()) {
				WhsTransBillBodyVO bodyvo = new WhsTransBillBodyVO();
				bodyvo.setCrowno(String.valueOf(iRowNo));
				bodyvo.setCmaterialoid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_material());
				bodyvo.setCmaterialvid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_material());
				bodyvo.setCastunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());
				bodyvo.setCunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());
				bodyvo.setNassistnum(lhbvo.getIqty());
				bodyvo.setNnum(lhbvo.getIqty());
				bodyvo.setVchangerate("1");

				alBodyVos.add(bodyvo);
				iRowNo += 10;
			}
			icbill.setParentVO(headvo);
			icbill.setChildrenVO(alBodyVos
					.toArray(new WhsTransBillBodyVO[alBodyVos.size()]));

			IPFBusiAction service = NCLocator.getInstance().lookup(
					IPFBusiAction.class);
			try {
				ICSpecialVO[] icbills = (ICSpecialVO[]) service.processAction(
						IPFActionName.WRITE, "4K", null, icbill, null, null);
				retStr = icbills[0].getParentVO().getCspecialhid() + "|"
						+ icbills[0].getParentVO().getVbillcode();
			} catch (BusinessException err) {
				throw new BusinessException("����ת�ⵥ�����쳣��" + err.getMessage());
			}

		} else {
			throw new BusinessException("ת����������������֯��ƥ�䣡");
		}

		return retStr;
	}

	protected void checkData(AggLhTransBillVO aggVO) throws BusinessException {
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_id())) {
			throw new BusinessException("��ϵͳ��������������Ϊ�գ�");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getTranstype())) {
			throw new BusinessException("�������Ͳ�����Ϊ�գ�");
		}
		if (!"���۹���".equals(aggVO.getParentVO().getTranstype())
				&& !"��������".equals(aggVO.getParentVO().getTranstype())) {
			throw new BusinessException("��������["
					+ aggVO.getParentVO().getTranstype()
					+ "]����ȷ��ֻ��Ϊ[���۹���]��[��������]!");
		}
		if ("��������".equals(aggVO.getParentVO().getTranstype())) {
			if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getInorgcode())) {
				throw new BusinessException("������֯����������Ϊ�գ�");
			}
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getOutorgcode())) {
			throw new BusinessException("������֯����������Ϊ�գ�");
		}
		if (StringUtil
				.isEmptyWithTrim(aggVO.getParentVO().getPk_warehouse_in())) {
			throw new BusinessException("����ֿⲻ����Ϊ�գ�");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO()
				.getPk_warehouse_out())) {
			throw new BusinessException("�����ֿⲻ����Ϊ�գ�");
		}
		if (aggVO.getParentVO().getDdate() == null) {
			throw new BusinessException("���ڲ�����Ϊ�գ�");
		}
		LhTransBillDetailVO[] bvos = aggVO.getChildrenVO();
		// HashSet<String> hsMatCodes = new HashSet<String>();
		for (LhTransBillDetailVO bvo : bvos) {
			if (bvo.getMatcode() == null
					|| StringUtil.isEmptyWithTrim(bvo.getMatcode())) {
				throw new BusinessException("��Ʒ���벻����Ϊ�գ�");
			}
			if (bvo.getIqty() == null || bvo.getIqty().equals(new UFDouble(0))) {
				throw new BusinessException("ת������������Ϊ�㣡");
			}
			// hsMatCodes.add(bvo.getAttributeValue("matcode").toString());
		}
	}

	private void matchSettleRule(BillVO[] bills) {
		for (BillVO bill : bills) {
			// ��������Ϊ�գ���ƥ��������
			if (PubAppTool.isNull(bill.getParentVO().getVtrantypecode())) {
				return;
			}

			// ƥ�������򲢲�����������Ϣ
			Integer fmodeflag = M5XVOBusiRuleUtil.getTransMode(bill);
			bill.getParentVO().setFmodeflag(fmodeflag);
			if (TransMode.TRANSMODE_5C.value().equals(fmodeflag)) {
				impactIOSettleRule(bill);
				impactOTSettleRule(bill);
			} else {
				impactIOSettleRule(bill);
			}

			M5XVOBusiRuleUtil.calculateOnWayOnwer(bill);
		}
	}

	private void impactIOSettleRule(BillVO bill) {
		Integer transmode = M5XVOBusiRuleUtil.getTransMode(bill);
		if (transmode == null) {
			return;
		}
		String vtrantypecode = bill.getParentVO().getVtrantypecode();
		if (null == vtrantypecode) {
			// ��������Ϊ��ʱ��ƥ�䣬�����ͷ����������ʱƵ��Զ�̵���
			return;
		}
		List<MatchSettleRuleVO> matchVO = M5XVOBusiRuleUtil
				.getIOMatchSettleRuleVO(bill);
		// �����ڲ���������������ѯ�ڲ��������
		Map<Integer, SettleRuleAggVO> map = this.getSettleRule(matchVO);
		// ���õ�������ڲ������������ֶ�
		this.setIOSettleRule(bill, map);
		// ���������;�����ı䣬Ӱ����;����
		M5XVOBusiRuleUtil.calculateOnWayOnwer(bill);
	}

	private Map<Integer, SettleRuleAggVO> getSettleRule(
			List<MatchSettleRuleVO> matchVO) {
		Map<Integer, SettleRuleAggVO> map = null;
		IMatchSettleruleService service = NCLocator.getInstance().lookup(
				IMatchSettleruleService.class);
		try {
			map = service.matchSettlerule(matchVO);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return map;
	}

	private void setIOSettleRule(BillVO bill, Map<Integer, SettleRuleAggVO> map) {
		// ���������;���������������������������������������������ӱ���������˰��𡢼۸����˰�ʡ��Ӽ��ʡ�
		// δѯ��ʱ��������ֶ�setIOSettleRule()���������֯��������������֯�ڵ���������������;����Ĭ��Ϊ������������
		BillItemVO[] itemVOs = bill.getChildrenVO();
		int len = itemVOs.length;
		for (int i = 0; i < len; i++) {
			SettleRuleAggVO settleRuleVO = map.get(Integer.valueOf(i));
			if (null == settleRuleVO) {
				String[] items = new String[] { BillItemVO.CIOSETTLERULEID,
						BillItemVO.CIOSETTLERULE_BID, BillItemVO.FTAXTYPEFLAG,
						BillItemVO.NADDPRICERATE, BillItemVO.NTAXRATE };
				this.clearRowValueByItemKeys(itemVOs[i], items);
				// ������������;������ȷ���û��޸ĺ�����ݲ����Ķ�
			} else {
				// ƥ�䵽�Ľ������ֻ��һ��������
				SettleRuleBVO settleRuleBVO = settleRuleVO.getBVO()[0];
				this.m_SettleRule.put(settleRuleBVO.getCsettlerule_bid(),
						settleRuleVO);
				itemVOs[i].setCiosettleruleid(settleRuleBVO.getCsettleruleid());
				itemVOs[i].setCiosettlerule_bid(settleRuleBVO
						.getCsettlerule_bid());

				itemVOs[i].setNaddpricerate(settleRuleBVO.getNaddpricerate());

				bill.getParentVO().setFioonwayownerflag(
						settleRuleVO.getHVO().getFonwayowner());
			}
		}
	}

	private void clearRowValueByItemKeys(BillItemVO item, String[] itemKeys) {
		for (String itemKey : itemKeys) {
			item.setAttributeValue(itemKey, null);
		}
	}

	private void impactOTSettleRule(BillVO bill) {
		Integer transmode = M5XVOBusiRuleUtil.getTransMode(bill);
		if (!TransMode.TRANSMODE_5C.equalsValue(transmode)) {
			return;
		}
		String vtrantypecode = bill.getParentVO().getVtrantypecode();
		if (null == vtrantypecode) {
			// ��������Ϊ��ʱ��ƥ�䣬�����ͷ����������ʱƵ��Զ�̵���
			return;
		}
		List<MatchSettleRuleVO> matchVO = M5XVOBusiRuleUtil
				.getOTMatchSettleRuleVO(bill);
		// �����ڲ���������������ѯ�ڲ��������
		Map<Integer, SettleRuleAggVO> map = this.getSettleRule(matchVO);
		// ���õ��������ڲ������������ֶε�������
		this.setOTSettleRule(bill, map);
		// ����������;�����ı䣬Ӱ����;����
		M5XVOBusiRuleUtil.calculateOnWayOnwer(bill);
	}

	private void setOTSettleRule(BillVO bill, Map<Integer, SettleRuleAggVO> map) {
		BillItemVO[] itemVOs = bill.getChildrenVO();
		int len = itemVOs.length;
		// ��������������;�����������������������������������������������ӱ�������δѯ��ʱ��������ֶ�setOTSettleRule()��
		for (int i = 0; i < len; i++) {
			SettleRuleAggVO settleRuleVO = map.get(Integer.valueOf(i));
			if (null == settleRuleVO) {
				String[] items = new String[] { BillItemVO.COTSETTLERULEID,
						BillItemVO.COTSETTLERULE_BID };
				this.clearRowValueByItemKeys(itemVOs[i], items);
				// ������������;������ȷ���û��޸ĺ�����ݲ����Ķ�
			} else {
				// ƥ�䵽�Ľ������ֻ��һ��������
				SettleRuleBVO settleRuleBVO = settleRuleVO.getBVO()[0];
				this.m_SettleRule.put(settleRuleBVO.getCsettlerule_bid(),
						settleRuleVO);
				itemVOs[i].setCotsettleruleid(settleRuleBVO.getCsettleruleid());
				itemVOs[i].setCotsettlerule_bid(settleRuleBVO
						.getCsettlerule_bid());
				bill.getParentVO().setFotonwayownerflag(
						settleRuleVO.getHVO().getFonwayowner());
			}
		}
	}

	private void matchSettlePath(BillVO[] bills) {
		try {
			String[] cinfinanceorgids = new String[bills.length];
			String[] coutfinanceorgids = new String[bills.length];
			for (int i = 0; i < bills.length; i++) {
				BillHeaderVO hvo = bills[i].getParentVO();
				cinfinanceorgids[i] = (String) hvo
						.getAttributeValue(BillHeaderVO.CINFINANCEORGID);
				coutfinanceorgids[i] = (String) hvo
						.getAttributeValue(BillHeaderVO.COUTFINANCEORGID);
			}
			IMatchSettlePathService service = NCLocator.getInstance().lookup(
					IMatchSettlePathService.class);
			UFBoolean bioreverseflag = bills[0].getParentVO()
					.getBioreverseflag();
			Map<String, SettlePathVO> mapSettlePath = new HashMap<String, SettlePathVO>();
			if (UFBoolean.TRUE.equals(bioreverseflag)) {
				mapSettlePath = service.matchDefaultSettlePath(
						cinfinanceorgids, coutfinanceorgids);
			} else {
				mapSettlePath = service.matchDefaultSettlePath(
						coutfinanceorgids, cinfinanceorgids);
			}
			String key = null;
			String settlepathid = null;
			for (int i = 0; i < bills.length; i++) {
				if (UFBoolean.TRUE.equals(bioreverseflag)) {
					key = cinfinanceorgids[i] + coutfinanceorgids[i];
				} else {
					key = coutfinanceorgids[i] + cinfinanceorgids[i];
				}
				if (mapSettlePath.get(key) == null) {
					continue;
				}
				settlepathid = mapSettlePath.get(key).getHead()
						.getCsettlepathid();
				bills[i].getParentVO().setAttributeValue(
						BillHeaderVO.CSETTLEPATHID, settlepathid);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
	}

	private void setCdelivorgid(BillVO[] bills) {
		Set<String> set = new HashSet<String>();
		for (BillVO bill : bills) {
			set.add(bill.getParentVO().getCtoutstockorgid());
		}

		Map<String, String> mapTrafficOrgs = TrafficOrgPubService
				.getTrafficOrgIDSByStockOrgIDS(set.toArray(new String[0]));
		if (null == mapTrafficOrgs || mapTrafficOrgs.size() == 0) {
			return;
		}
		// ȡ������֯�İ汾
		Map<String, String> trafficOrgVids = OrgUnitPubService
				.getNewVIDSByOrgIDS(mapTrafficOrgs.values().toArray(
						new String[0]));

		for (BillVO bill : bills) {
			BillHeaderVO header = bill.getParentVO();
			BillItemVO[] items = bill.getChildrenVO();
			String ctoustockorgid = header.getCtoutstockorgid();
			if (mapTrafficOrgs.containsKey(ctoustockorgid)) {
				String cdelivorgid = mapTrafficOrgs.get(ctoustockorgid);
				String cdelivorgvid = trafficOrgVids.get(cdelivorgid);
				for (BillItemVO item : items) {
					if (PubAppTool.isNull(item.getCdelivorgvid())) {
						item.setCdelivorgid(cdelivorgid);
						item.setCdelivorgvid(cdelivorgvid);
					}
				}
			}
		}
	}

	private void setNastNum(BillVO[] bills) {
		String pk_group = AppContext.getInstance().getPkGroup();
		for (BillVO bill : bills) {

			BillItemVO[] items = bill.getChildrenVO();
			for (BillItemVO item : items) {
				// �������������Ի����ʵõ�����
				ScaleUtils scale = new ScaleUtils(pk_group);
				String changerate = item.getVchangerate();

				changerate = scale.adjustHslScale(changerate);

				String[] changerates = changerate.split("/");

				UFDouble nastnum = item.getNnum().div(
						new UFDouble(changerates[0]).div(new UFDouble(
								changerates[1])));
				nastnum = scale.adjustNumScale(nastnum, item.getCastunitid());

				// ԭ����VO��û������������������Ϊ���������Ի�����
				if (item.getNastnum() == null
						|| item.getNastnum().equals(UFDouble.ZERO_DBL)) {
					item.setNastnum(nastnum);
				}
				// ����VO�ϵ������ͼ���������ȣ�ȡ������
				if (!item.getNastnum().equals(nastnum)) {
					item.setNastnum(nastnum);
				}
			}
		}
	}

	private void setTrantypeidIfNotNull(BillVO[] bills) {

		IM5xTranTypeService impl = NCLocator.getInstance().lookup(
				IM5xTranTypeService.class);
		Set<String> ids = new HashSet<String>();
		Map<String, M5xTranTypeVO> map = null;
		for (BillVO bill : bills) {
			BillHeaderVO header = bill.getParentVO();
			String trantypeId = header.getCtrantypeid();
			if (!PubAppTool.isNull(trantypeId)) {
				ids.add(trantypeId);
			}
		}
		if (ids.size() < 1) {
			return;
		}
		try {
			map = impl.queryTranTypeBatch(ids.toArray(new String[0]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		for (BillVO bill : bills) {
			BillHeaderVO header = bill.getParentVO();
			String trantypeId = header.getCtrantypeid();
			if (!PubAppTool.isNull(trantypeId) && map.containsKey(trantypeId)) {
				M5xTranTypeVO tranTypeVO = map.get(trantypeId);
				header.setBioreverseflag(tranTypeVO.getBreverseflag());
				header.setBotreverseflag(tranTypeVO.getBotreverseflag());
				if (PubAppTool.isNull(header.getVtrantypecode())) {
					header.setVtrantypecode(tranTypeVO.getVtrantypecode());
				}
			}
		}
	}
}
