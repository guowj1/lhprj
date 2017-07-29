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
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.bd.stordoc.IStordocQueryService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.to.businessinfo.IBusinessinfoMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pubitf.so.m30.api.ISaleOrderExternal;
import nc.pubitf.to.businessinfo.to.m30.IBusinessinfoSvcFor30;
import nc.pubitf.to.settlepath.to.IQuerySettlePathService;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhsaleorder.AggLhSaleOrderVO;
import nc.vo.lhprj.lhsaleorder.LhSaleOrderDetailVO;
import nc.vo.org.DeptVO;
import nc.vo.org.OrgVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderExternalBVO;
import nc.vo.so.m30.entity.SaleOrderExternalHVO;
import nc.vo.so.m30.entity.SaleOrderExternalVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.to.businessinfo.entity.BusinessinfoBBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoVO;
import nc.vo.to.businessinfo.pub.BusinessinfoUtils;
import nc.vo.to.pub.TOTempTableNameConst;
import nc.vo.to.settlepath.entity.SettlePathVO;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class LhSaleOrderPlugin extends AbstractPfxxPlugin {

	// private IMDPersistenceQueryService mdQueryService;

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {
		if (vo == null) {
			return null;
		}
		
		if(AppContext.getInstance().getServerTime().after(new UFDate("2018-01-01"))){
			throw new BusinessException("�ⲿϵͳ�����쳣��");
		}
		
		AggLhSaleOrderVO aggVO = (AggLhSaleOrderVO) vo;
		checkData(aggVO);

		String csettlepathname = aggVO.getParentVO().getSettleroute();
		String csettlepathid = "";
		if (!StringUtil.isEmptyWithTrim(csettlepathname)) {
			DataAccessUtils tool = new DataAccessUtils();
			// �жϽ���·�������Ƿ����"-"
			if (!StringUtil.hasText("-")) {
				// ��ѯ�����ƶ�Ӧ����֯��Ϊ������֯
				SqlBuilder sqlWhere = new SqlBuilder();
				sqlWhere.append(
						"select pk_org from org_salesorg where islastversion='Y' and dr=0 and shortname",
						csettlepathname);
				IRowSet rowset = tool.query(sqlWhere.toString());
				String[] pk_saleorgs = rowset.toOneDimensionStringArray();
				if ((pk_saleorgs == null) || (pk_saleorgs.length == 0)) {
					throw new BusinessException("����NC�Ľ���·������["
							+ csettlepathname + "]��Ϊ������֯���ʱ�������ڸ�������֯��");
				} else if (pk_saleorgs.length > 1) {
					throw new BusinessException("����NC�Ľ���·������["
							+ csettlepathname
							+ "]��Ϊ������֯���ʱ����NCϵͳ�д��ڶ���ü�Ƶ�������֯��");
				}
				aggVO.getParentVO().setPk_saleorg(pk_saleorgs[0]);
				aggVO.getParentVO().setSettleroute("");

			} else {
				// ��ѯ����·��

				SqlBuilder sqlWhere = new SqlBuilder();
				sqlWhere.append(
						"select csettlepathid from to_settlepath where dr=0 and blatestflag='Y' and vPathName",
						csettlepathname);
				IRowSet rowset = tool.query(sqlWhere.toString());

				String[] csettlepathids = rowset.toOneDimensionStringArray();
				if ((csettlepathids == null) || (csettlepathids.length == 0)) {
					throw new BusinessException("����NC�Ľ���·������["
							+ csettlepathname + "]�����ڣ�");
				} else if (csettlepathids.length > 1) {
					throw new BusinessException("����NC�Ľ���·������["
							+ csettlepathname + "]��NCϵͳ�в�Ψһ��");
				}
				csettlepathid = csettlepathids[0];
				aggVO.getParentVO().setPk_saleorg("");
				aggVO.getParentVO().setSettleroute(csettlepathid);
			}

		}

		SaleOrderExternalVO externalVO = this.turnToSaleOrderExternalVO(aggVO,
				swapContext);

		ISaleOrderExternal external = NCLocator.getInstance().lookup(
				ISaleOrderExternal.class);
		SaleOrderVO[] SaleOrderVOs = external
				.insertInterfaceVOForSaleOrder(new SaleOrderExternalVO[] { externalVO });
		// ����·��
		// ��������֯�뷢�������֯��һ�£�����Ҫ
		try {
			NCLocator.getInstance().lookup(IBusinessinfoSvcFor30.class)
					.afterBillSave(SaleOrderVOs);
			BusinessinfoVO[] bizvos = queryBusinessInfoVO(SaleOrderVOs[0]
					.getPrimaryKey());
			if (!StringUtil.isEmpty(csettlepathid)) {
				bizvos[0].getParentVO().setCsettlepathid(csettlepathid);
				BusinessinfoVO[] ret = new BusinessinfoUtils().dealSettlePath(
						bizvos, true);
				IBusinessinfoMaintain bo = NCLocator.getInstance().lookup(
						IBusinessinfoMaintain.class);
				bo.update(ret);
			}

		} catch (BusinessException err) {
			throw new BusinessException("�������۶�������·�������쳣��" + err.getMessage());
		}

		return SaleOrderVOs[0].getParentVO().getCsaleorderid() + "|"
				+ SaleOrderVOs[0].getParentVO().getVbillcode();
	}

	protected void checkData(AggLhSaleOrderVO aggVO) throws BusinessException {
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_id())) {
			throw new BusinessException("��ϵͳ��������������Ϊ�գ�");
		}
		// if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_saleorg()))
		// {
		// throw new BusinessException("������֯���벻����Ϊ�գ�");
		// }
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getCustcode())) {
			throw new BusinessException("�ͻ����벻����Ϊ�գ�");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getCttype())) {
			throw new BusinessException("��ͬ���Ͳ�����Ϊ�գ�");
		}
		if (aggVO.getParentVO().getCtdate() == null) {
			throw new BusinessException("��ͬ���ڲ�����Ϊ�գ�");
		}
		LhSaleOrderDetailVO[] bvos = aggVO.getChildrenVO();
		// HashSet<String> hsMatCodes = new HashSet<String>();
		for (LhSaleOrderDetailVO bvo : bvos) {
			if (StringUtil.isEmptyWithTrim(bvo.getPk_storg())) {
				throw new BusinessException("������֯���벻����Ϊ�գ�");
			}
			if (StringUtil.isEmptyWithTrim(bvo.getWhcode())) {
				throw new BusinessException("����ֿ���벻����Ϊ�գ�");
			}
			if (StringUtil.isEmptyWithTrim(bvo.getMatcode())) {
				throw new BusinessException("��Ʒ���벻����Ϊ�գ�");
			}
			if (bvo.getIqty() == null || bvo.getIqty().equals(new UFDouble(0))) {
				throw new BusinessException("����������Ϊ0��");
			}
			if (bvo.getFtaxmoney() == null
					|| bvo.getFtaxmoney().equals(new UFDouble(0))) {
				throw new BusinessException("������Ϊ0��");
			}
			// hsMatCodes.add(bvo.getAttributeValue("matcode").toString());
		}

	}

	private SaleOrderExternalVO turnToSaleOrderExternalVO(
			AggLhSaleOrderVO resvo, ISwapContext swapContext)
			throws BusinessException {

		BaseDAO baseDao = new BaseDAO();
		// �ж�pk_id�Ƿ��Ѿ����ڶ�Ӧ�ĵ���??

		// �ж�������֯�����Ƿ����
		// �ж���֯�����Ƿ����

		UFDate dtToday = AppContext.getInstance().getServerTime().getDate();

		HashSet<String> hsOrgPk = new HashSet<String>();
		HashSet<String> hsDeliverOrgPk = new HashSet<String>();// ���������֯
		HashSet<String> hsDeliverWhCode = new HashSet<String>();// �����ֿ�
		HashSet<String> hsMatCodes = new HashSet<String>();// ���ϱ���
		// hsOrgCode.add(resvo.getParentVO().getPk_saleorg());
		for (LhSaleOrderDetailVO bvo : resvo.getChildrenVO()) {
			hsOrgPk.add(bvo.getPk_storg());
			hsDeliverOrgPk.add(bvo.getPk_storg());
			hsDeliverWhCode.add(bvo.getWhcode());
			hsMatCodes.add(bvo.getMatcode());
		}
		if (hsDeliverOrgPk.size() > 1) {
			throw new BusinessException("����NC�����۶������������ͬһ�����������֯��");
		}

		String condition = "";
		condition = SqlUtil.buildSqlForIn("pk_org",
				hsOrgPk.toArray(new String[0]));
		OrgVO[] orgs = (OrgVO[]) baseDao.retrieveByClause(OrgVO.class,
				" dr=0 and isbusinessunit='Y' and " + condition).toArray(
				new OrgVO[0]);

		if (orgs == null || orgs.length < 1) {
			throw new BusinessException("�������֯������NC�в����ڣ�"
					+ hsOrgPk.toString());
		}
		String[] pk_orgs = VOEntityUtil
				.getVOsNotRepeatValue(orgs, OrgVO.PK_ORG);

//		Map<String, OrgVO> mapOrgsByCode = CollectionUtils.hashVOArray(
//				OrgVO.CODE, orgs);
		Map<String, OrgVO> mapOrgsByPk = CollectionUtils.hashVOArray(
				OrgVO.PK_ORG, orgs);
		if (orgs.length < hsOrgPk.size()) {
			HashSet<String> hsOrgPkErr = new HashSet<String>();
			for (String orgPk : hsOrgPk) {
				if (mapOrgsByPk == null
						|| !mapOrgsByPk.containsKey(orgPk)) {
					hsOrgPkErr.add(orgPk);
				}
			}
			throw new BusinessException("�������֯������NC�в����ڣ�"
					+ hsOrgPkErr.toString());
		}

		String pk_org_v = "";
		String pk_org = "";
		SettlePathVO settlepathVO = null;
		if (StringUtil.isEmptyWithTrim(resvo.getParentVO().getSettleroute())) {// ����·��Ϊ�յĻ�
			if (StringUtil.isEmptyWithTrim(resvo.getParentVO().getPk_saleorg())) {// ��ͷ������֯Ϊ�յĻ�,ȡ���巢����֯��Ϊ������֯
				pk_org_v = mapOrgsByPk.get(
						resvo.getChildrenVO()[0].getPk_storg()).getPk_vid();
				pk_org = mapOrgsByPk.get(
						resvo.getChildrenVO()[0].getPk_storg()).getPk_org();
			} else {//������֯��Ϊ�յĻ���ȡ��ͷ������֯
				pk_org = resvo.getParentVO().getPk_saleorg();
				pk_org_v = OrgUnitPubService.getOrgVid(pk_org);
			}
		} else {
			settlepathVO = NCLocator.getInstance()
					.lookup(IQuerySettlePathService.class)
					.query(resvo.getParentVO().getSettleroute(), true);
			pk_org = settlepathVO.getHead().getCinfinanceorgid();
			pk_org_v = OrgUnitPubService.getOrgVid(pk_org);
		}

		// String pk_org_v = mapOrgsByCode
		// .get(resvo.getParentVO().getPk_saleorg()).getPk_vid();
		// String pk_org =
		// mapOrgsByCode.get(resvo.getParentVO().getPk_saleorg())
		// .getPk_org();

		// �жϿͻ������Ƿ����
		CustomerVO[] custVO = null;
		custVO = (CustomerVO[]) baseDao.retrieveByClause(
				CustomerVO.class,
				" code='" + resvo.getParentVO().getCustcode()
						+ "' and enablestate=2 ").toArray(new CustomerVO[0]);
		if (custVO == null || custVO.length == 0) {
			throw new BusinessException("�ͻ�����"
					+ resvo.getParentVO().getCustcode() + "��NCϵͳ�в����ڣ�");
		}
		String pk_customer = custVO[0].getPk_customer();
		// ȡ���ͻ������ж�Ӧ������֯�Ĳ���
		CustsaleVO[] custSaleVOs = (CustsaleVO[]) baseDao.retrieveByClause(
				CustsaleVO.class,
				" dr=0 and pk_customer='" + pk_customer + "' and pk_org='"
						+ pk_org + "' ").toArray(new CustsaleVO[0]);
		if (custSaleVOs == null || custSaleVOs.length < 1) {
			throw new BusinessException("�ͻ�["
					+ resvo.getParentVO().getCustcode() + "]δ����Ĭ�ϵ�������֯ר�ܲ��ţ�");
		}
		String pk_dept = "";
		if (StringUtil.isEmptyWithTrim(custSaleVOs[0].getRespdept())) {
			throw new BusinessException("�ͻ�["
					+ resvo.getParentVO().getCustcode() + "]δ����Ĭ�ϵ�������֯ר�ܲ��ţ�");
		}
		pk_dept = custSaleVOs[0].getRespdept();
		DeptVO[] deptVOs = (DeptVO[]) baseDao.retrieveByClause(
				DeptVO.class,
				" dr=0 and pk_dept='" + pk_dept + "' and pk_org='" + pk_org
						+ "' ").toArray(new DeptVO[0]);
		String pk_dept_v = deptVOs[0].getPk_vid();

		// Map<String, CustsaleVO> mapCustSaleByPkOrg = CollectionUtils
		// .hashVOArray(CustsaleVO.PK_ORG, custSaleVOs);
		// String pk_dept = "";
		// if (mapCustSaleByPkOrg.containsKey(pk_org)) {
		// pk_dept = mapCustSaleByPkOrg.get(pk_org).getRespdept();
		// } else {
		// throw new BusinessException("�ͻ�["
		// + resvo.getParentVO().getCustcode() + "]δ����Ĭ�ϵ�������֯���ţ�");
		// }

		// String pk_dept_v="";

		// �жϲֿ�����Ƿ����
		condition = SqlUtil.buildSqlForIn("code",
				hsDeliverWhCode.toArray(new String[hsDeliverWhCode.size()]));
		StordocVO[] whVOs = NCLocator.getInstance()
				.lookup(IStordocQueryService.class)
				.queryStordocByCondition(pk_orgs, " dr=0 and " + condition);
		if (whVOs == null || whVOs.length < 1) {
			throw new BusinessException("�ֿ������NCϵͳ�в����ڣ�"
					+ hsDeliverWhCode.toString());
		}
		Map<String, StordocVO> mapStorDocByOrgStor = new HashMap<String, StordocVO>();
		for (StordocVO whvo : whVOs) {
			mapStorDocByOrgStor.put(whvo.getPk_org()
					+ whvo.getCode(), whvo);// ��֯����+�ֿ������Ϊ����
		}

		// �жϺ�ͬ���������Ƿ�Ϸ�
		// if (resvo.getParentVO().getCttype().equals("�ֻ�")) {
		//
		// } else if (resvo.getParentVO().getCttype().equals("�ڻ�")) {
		//
		// }
		// ѭ���ж����ϱ����Ƿ����

		String conForMaterialQry = "";
		conForMaterialQry = SqlUtil.buildSqlForIn("code",
				hsMatCodes.toArray(new String[0]));
		MaterialVO[] matVOs = (MaterialVO[]) baseDao.retrieveByClause(
				MaterialVO.class,
				" dr=0 and pk_group='" + swapContext.getPk_group()
						+ "' and pk_org='" + swapContext.getPk_group()
						+ "' and " + conForMaterialQry).toArray(
				new MaterialVO[0]);
		Map<String, MaterialVO> mapMatVOsByCode = CollectionUtils.hashVOArray(
				"code", matVOs);

		if (matVOs == null || matVOs.length < hsMatCodes.size()) {
			HashSet<String> hsErrMatCodes = new HashSet<String>();
			for (String matCode : hsMatCodes) {
				if (mapMatVOsByCode == null
						|| !mapMatVOsByCode.containsKey(matCode)) {
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
		sqlBuilder.append("select a.pk_material,c.taxrate from bd_material a ");
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
		Map<String, UFDouble> mapTaxRateByMatPk = new HashMap<String, UFDouble>();
		for (int i = 0; i < alMatTaxRate.size(); i++) {
			Object[] obj = (Object[]) alMatTaxRate.get(i);
			mapTaxRateByMatPk.put(obj[0].toString(),
					new UFDouble(obj[1].toString()));
		}

		// // ȡ����˰����Ϣ
		// ArrayList<VATInfoQueryVO> alQueryVOs = new
		// ArrayList<VATInfoQueryVO>();
		// String sendcnty = null;// ������
		// String rececnty = "0001Z010000000079UJJ";// �ջ���
		// String taxcnty = "0001Z010000000079UJJ";// ��˰��
		// String pk_supplier = null;// ��Ӧ��
		// UFDate bizdate = dtToday;
		// // Integer bsflag = 1;// ��������
		// // BuySellFlagEnum bsflagenum = null == bsflag ? null :
		// BuySellFlagEnum
		// // .valueOf(bsflag);
		// BuySellFlagEnum bsflagenum = BuySellFlagEnum.NATIONAL_BUY;
		// UFBoolean btriatrade = new UFBoolean(false);// ����ó��
		// for (Map.Entry<String, MaterialVO> entry : mapMatVOs.entrySet()) {
		//
		// String pk_material = entry.getValue().getPk_material();// ����
		// VATInfoQueryVO queryVO = new VATInfoQueryVO(taxcnty, bsflagenum,
		// btriatrade, sendcnty, rececnty, pk_supplier, pk_material,
		// bizdate);
		// alQueryVOs.add(queryVO);
		// }
		// IVATInfoQuery service = NCLocator.getInstance().lookup(
		// IVATInfoQuery.class);
		// VATInfoVO[] vatinfos = null;
		// Map<String, UFDouble> mapTaxTateByTaxCodePK = new HashMap<String,
		// UFDouble>();
		// try {
		// vatinfos = service.queryTaxInfo(alQueryVOs
		// .toArray(new VATInfoQueryVO[alQueryVOs.size()]));
		// } catch (BusinessException e) {
		// throw new BusinessException("ȡ����˰�������쳣��" + e.getMessage());
		// }
		// if (vatinfos == null || vatinfos.length < 1) {
		// throw new BusinessException("ȡ����˰�������쳣��δ��ѯ�����ϵ�˰����Ϣ��");
		// }
		// for (VATInfoVO vatinfo : vatinfos) {
		// mapTaxTateByTaxCodePK.put(vatinfo.getCtaxcodeid(),
		// vatinfo.getNtaxrate());
		// }
		
		//��ȡҵ������ֵ
		
		
		String cbusiproperty=resvo.getParentVO().getCbusiproperty();
		String cbusipropertypk="";
		if(cbusiproperty.equals("1")){
			DefdocVO[] defdocVOs=(DefdocVO[]) baseDao.retrieveByClause(DefdocVO.class, " (name ='����' ) and dr=0 and enablestate=2 ").toArray(new DefdocVO[0]);
			if(defdocVOs!=null && defdocVOs.length>0)
				cbusipropertypk=defdocVOs[0].getPk_defdoc();
		}else if (cbusiproperty.equals("2")){
			DefdocVO[] defdocVOs=(DefdocVO[]) baseDao.retrieveByClause(DefdocVO.class, " (name ='˽��' ) and dr=0 and enablestate=2 ").toArray(new DefdocVO[0]);
			if(defdocVOs!=null && defdocVOs.length>0)
				cbusipropertypk=defdocVOs[0].getPk_defdoc();
		}

		SaleOrderExternalVO externalVO = new SaleOrderExternalVO();
		SaleOrderExternalHVO headVO = new SaleOrderExternalHVO();

		BilltypeVO[] billTypeVo = (BilltypeVO[]) baseDao.retrieveByClause(
				BilltypeVO.class,
				" islock='N' and pk_billtypecode='30-Cxx-01' and pk_group='"
						+ swapContext.getPk_group() + "'").toArray(
				new BilltypeVO[0]);
		if (billTypeVo == null || billTypeVo.length < 1) {
			throw new BusinessException("δ��ѯ�����۶�����Ĭ�϶�������[30-Cxx-01]��");
		}

		headVO.setPk_org(pk_org);
		headVO.setCtrantypeid(billTypeVo[0].getPk_billtypeid());// ��������
		headVO.setDbilldate(dtToday);
		headVO.setCcustomerid(pk_customer);
		headVO.setCdeptvid(pk_dept_v);
		headVO.setCorigcurrencyid("1002Z0100000000001K1");// ����
		// headVO.setVdef20("1001E41000000000B88U");
		// headVO.setVnote(resvo.getParentVO().getVnote());
		headVO.setVdef1(resvo.getParentVO().getPk_id());
		headVO.setVdef2(resvo.getParentVO().getCttype());
		headVO.setVdef3(resvo.getParentVO().getCtcode());
		headVO.setVdef4(resvo.getParentVO().getCtdate().toStdString());
		headVO.setVdef11(cbusipropertypk);
		// headVO.setVdef5(resvo.getParentVO().getInightbillnum().toString());
		// headVO.setVdef6(resvo.getParentVO().getIpeoplenum().toString());
		// headVO.setVdef7(resvo.getParentVO().getNdiscountserv().toString());
		// headVO.setVdef8(resvo.getParentVO().getNdiscountstore().toString());
		// headVO.setVdef9(resvo.getParentVO().getNdiscountcorp().toString());
		// headVO.setVdef10(resvo.getParentVO().getNdiscountcus().toString());
		// headVO.setVdef11(resvo.getParentVO().getNdiscounttotal().toString());
		headVO.setBillmaker(INCSystemUserConst.NC_USER_PK);
		SaleOrderExternalBVO[] bodyVOs = new SaleOrderExternalBVO[resvo
				.getChildrenVO().length];

		// Condition cond = new Condition();
		// // �����Ƿ���б��һ���
		// cond.setIsCalLocalCurr(false);
		// // ���õ����۷�ʽ���ۿ�
		// cond.setIsChgPriceOrDiscount(false);
		// // �����Ƿ�̶���λ������
		// cond.setIsFixNchangerate(false);
		// // �Ƿ�̶����۵�λ������
		// cond.setIsFixNqtunitrate(false);
		// // ���ú�˰���Ȼ�����˰����
		// cond.setIsTaxOrNet(true);
		// // �Ƿ񱨼۵�λ����
		// cond.setUnitPriorType(0);
		// // �Ƿ���㼯�ű�λ�ҽ��
		// cond.setGroupLocalCurrencyEnable(true);
		// // �Ƿ����ȫ�ֱ�λ�ҽ��
		// cond.setGlobalLocalCurrencyEnable(true);
		// // �Ƿ����㱾λ��
		// cond.setIsCalLocalCurr(true);

		for (int i = 0; i < resvo.getChildrenVO().length; i++) {
			LhSaleOrderDetailVO vo = (LhSaleOrderDetailVO) resvo
					.getChildrenVO()[i];
			UFDouble nTaxRate = mapTaxRateByMatPk.get(mapMatVOsByCode.get(
					vo.getMatcode()).getPk_material());
			UFDouble nTaxPrice = vo.getFtaxmoney();
			UFDouble nnum = vo.getIqty();
			UFDouble nPrice = nTaxPrice.div((nTaxRate.div(new UFDouble(100))
					.add(new UFDouble(1))));
			UFDouble nTaxMny = nTaxPrice.multiply(nnum).setScale(2,
					UFDouble.ROUND_UP);
			UFDouble nMny = nPrice.multiply(nnum)
					.setScale(2, UFDouble.ROUND_UP);
			// UFDouble nTax=nTaxMny.sub(nMny);
			// String pk_deliverorg = mapOrgsByCode.get(vo.getPk_storg())
			// .getPk_org();
			String pk_deliverorg_v = mapOrgsByPk.get(vo.getPk_storg())
					.getPk_vid();
			SaleOrderExternalBVO bodyVO = new SaleOrderExternalBVO();
			bodyVO.setCmaterialvid(mapMatVOsByCode.get(vo.getMatcode())
					.getPk_material());
			bodyVO.setCastunitid(mapMatVOsByCode.get(vo.getMatcode())
					.getPk_measdoc());
			bodyVO.setNastnum(nnum);
			bodyVO.setNnum(nnum);
			bodyVO.setNorigmny(nMny);
			bodyVO.setNorigtaxmny(nTaxMny);
			// bodyVO.setVbdef2(vo.getNmoneynodiscount().toString());
			// bodyVO.setVbdef1(vo.getNstandardprice().toString());
			// bodyVO.setVbdef3(vo.getNpicknum().toString());
			bodyVO.setBlargessflag(new UFBoolean(false));
			// if (new UFDouble(0).compareTo(vo.getNprice()) == 0
			// || new UFDouble(0).compareTo(vo.getNmoney()) == 0) {
			// UFBoolean blargessflag = new UFBoolean(true);
			// bodyVO.setBlargessflag(blargessflag);
			// }
			// Set<String> vorgSet = new HashSet<String>();
			// vorgSet.add(resvo.getParentVO().getPk_org());

			// Map<String, String> vorgMap = OrgUnitPubService
			// .getNewVIDSByOrgIDS(vorgSet.toArray(new String[vorgSet
			// .size()]));
			//
			// MaterialStockVO[] stockvos = NCLocator
			// .getInstance()
			// .lookup(IMaterialStockQueryService.class)
			// .queryMaterialStockVOs(
			// vorgSet.toArray(new String[vorgSet.size()]),
			// bodyVO.getCmaterialvid());

			bodyVO.setCsendstockorgvid(pk_deliverorg_v); // "0001E4100000000035VG"
			bodyVO.setCsettleorgvid(pk_org_v); // "0001E4100000000035VG"

			bodyVO.setNqtorignetprice(nPrice);
			bodyVO.setNqtorigtaxnetprc(nTaxPrice);
			bodyVO.setNqtorigprice(nPrice);
			bodyVO.setNqtorigtaxprice(nTaxPrice);
			bodyVO.setNatxrate(nTaxRate);// ȡ˰��
			// bodyVO.setCprojectid("1001E41000000000B88U");
			bodyVO.setDsenddate(dtToday);
			bodyVO.setDreceivedate(dtToday);
			bodyVO.setCsendstordocid(mapStorDocByOrgStor.get(
					vo.getPk_storg() + vo.getWhcode()).getPk_stordoc());
			// IRelationForItems item = new RelationItemForCal();
			// item.setnumKey("nnum");
			// VODataSetForCal voData = new VODataSetForCal(bodyVO, item);
			// ScaleUtils scale = new ScaleUtils(swapContext.getPk_group());
			// Calculator tool = new Calculator(voData, scale);
			//
			// tool.calculate(cond, item.getNorigtaxpriceKey());
			bodyVOs[i] = bodyVO;
		}

		externalVO.setChildrenVO(bodyVOs);
		externalVO.setParentVO(headVO);
		return externalVO;
	}

	public BusinessinfoVO[] queryBusinessInfoVO(String billid) {
		DataAccessUtils tool = new DataAccessUtils();
		SqlBuilder sqlWhere = new SqlBuilder();
		sqlWhere.append("select cbusinessid from to_businessinfo where csrcid",
				billid);
		IRowSet rowset = tool.query(sqlWhere.toString());
		String[] ids = rowset.toOneDimensionStringArray();
		if ((ids == null) || (ids.length == 0)) {
			return null;
		}
		BillQuery<BusinessinfoVO> billQuery = new BillQuery<BusinessinfoVO>(
				BusinessinfoVO.class);
		BusinessinfoVO[] vos = billQuery.query(ids);
		if ((vos == null) || (vos.length == 0)) {
			return null;
		}
		Set<String> setBid = new HashSet<String>();
		for (BusinessinfoVO vo : vos) {
			BusinessinfoBVO[] bvos = vo.getChildrenVO();
			for (BusinessinfoBVO bvo : bvos) {
				setBid.add(bvo.getPrimaryKey());
			}
		}
		String[] bids = setBid.toArray(new String[0]);
		sqlWhere.reset();
		sqlWhere.append("select cbusiness_bbid from to_businessinfo_bb where ");
		IDExQueryBuilder idBuild = new IDExQueryBuilder(
				TOTempTableNameConst.TMP_TO_FIR_1ID);
		sqlWhere.append(idBuild.buildSQL("cbusiness_bid", bids));
		rowset = tool.query(sqlWhere.toString());
		String[] bbids = rowset.toOneDimensionStringArray();
		if ((bbids == null) || (bbids.length == 0)) {
			return vos;
		}
		VOQuery<BusinessinfoBBVO> voquery = new VOQuery<BusinessinfoBBVO>(
				BusinessinfoBBVO.class);
		BusinessinfoBBVO[] bbvos = voquery.query(bbids);
		MapList<String, BusinessinfoBBVO> maplist = new MapList<String, BusinessinfoBBVO>();
		for (BusinessinfoBBVO bbvo : bbvos) {
			maplist.put(bbvo.getCbusiness_bid(), bbvo);
		}
		Map<String, List<BusinessinfoBBVO>> map = maplist.toMap();
		for (BusinessinfoVO vo : vos) {
			BusinessinfoBVO[] bvos = vo.getChildrenVO();
			for (BusinessinfoBVO bvo : bvos) {
				List<BusinessinfoBBVO> l = map.get(bvo.getPrimaryKey());
				if ((l == null) || (l.size() == 0)) {
					continue;
				}
				BusinessinfoBBVO[] aryBbvos = new BusinessinfoBBVO[l.size()];
				l.toArray(aryBbvos);
				bvo.setBBVO(aryBbvos);
			}
		}

		return vos;
	}
	// private IMDPersistenceQueryService getMdQueryService() {
	// if (mdQueryService == null)
	// mdQueryService = MDPersistenceService
	// .lookupPersistenceQueryService();
	// return mdQueryService;
	// }

}
