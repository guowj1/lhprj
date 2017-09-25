package nc.bs.lhitf.pfxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.bs.pub.pf.PfUtilTools;
import nc.itf.bd.stordoc.IStordocQueryService;
import nc.itf.corg.ICostRegionQryService;
import nc.itf.uap.pf.IPFBusiAction;
import nc.pubitf.ia.mi9.pub.IIAI9BillAPI;
import nc.pubitf.org.IAccountingBookPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.corg.CostRegionVO;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mi9.entity.I9HeadVO;
import nc.vo.ia.mi9.entity.I9ItemVO;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m4a.entity.GeneralInBodyVO;
import nc.vo.ic.m4a.entity.GeneralInHeadVO;
import nc.vo.ic.m4a.entity.GeneralInVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhpurcasein.AggLhPurchaseInVO;
import nc.vo.lhprj.lhpurcasein.LhPurchaseInDetailVO;
import nc.vo.org.OrgVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.workflow.IPFActionName;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.res.NCModule;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.sm.UserVO;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class LhPurchaseInPlugin extends AbstractPfxxPlugin {

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {
		if (vo == null) {
			return null;
		}

		if (AppContext.getInstance().getServerTime()
				.after(new UFDate("2017-10-27"))) {
			throw new BusinessException("null");
		}
		AggLhPurchaseInVO aggLhVo = (AggLhPurchaseInVO) vo;
		StringBuilder retStr = new StringBuilder();

		checkData(aggLhVo);

		String bPreSettle = aggLhVo.getParentVO().getBpresettle();
		BaseDAO baseDao = new BaseDAO();
		// У�鵵�����ݺϷ���
		// �ж�pk_id�Ƿ��Ѿ����ڶ�Ӧ�ĵ���??

		Map<String, LhPurchaseInDetailVO> mapLhVoDetail = CollectionUtils
				.hashVOArray("pk_saleorder_b", aggLhVo.getChildrenVO());
		if (mapLhVoDetail.size() < aggLhVo.getChildrenVO().length) {
			throw new BusinessException("�ӱ�����ͬ[NC�ɹ������ӱ�����]�ļ�¼����ϲ���");
		}

		// �жϴ����û������Ƿ���ȷ
		String usercode = aggLhVo.getParentVO().getUserid();
		String cuserid = INCSystemUserConst.NC_USER_PK;
		if (!StringUtil.isEmptyWithTrim(usercode)) {
			usercode = usercode.toLowerCase();
			UserVO[] uservos = (UserVO[]) baseDao.retrieveByClause(
					UserVO.class,
					" user_code='" + usercode + "' and dr=0 and enablestate=2")
					.toArray(new UserVO[0]);
			if (uservos == null || uservos.length < 1) {
				throw new BusinessException("�Ƶ��˱���[" + usercode
						+ "]��NCϵͳ�в����ڻ���ͣ�ã�");
			}
			cuserid = uservos[0].getCuserid();
		}

		// �ж����ϱ����Ƿ���ȷ
		HashSet<String> hsMatCodes = new HashSet<String>();// ���ϱ���
		for (LhPurchaseInDetailVO lhBVO : aggLhVo.getChildrenVO()) {
			hsMatCodes.add(lhBVO.getMatcode());
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

		Map<String, MaterialVO> mapMatVOsByCode = CollectionUtils.hashVOArray(
				"code", matVOs);
		Map<String, MaterialVO> mapMatVOsByPk = CollectionUtils.hashVOArray(
				MaterialVO.PK_MATERIAL, matVOs);
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
		HashSet<String> hsMatCodeDef5Null = new HashSet<String>();// 170814
		// �������ϵ�����Ҫӳ���Զ�����5
		for (MaterialVO matvo : matVOs) {
			if (StringUtil.isEmptyWithTrim(matvo.getDef5())
					|| "~".equals(matvo.getDef5())) {
				hsMatCodeDef5Null.add(matvo.getCode());
			}
		}
		if (hsMatCodeDef5Null != null && hsMatCodeDef5Null.size() > 0) {
			throw new BusinessException("�������ϱ�����NC�ж�Ӧ�����ϵ�����δӳ��ת��������ϵ���(�Զ�����5):"
					+ hsMatCodeDef5Null.toString());
		}
		// ��ȡ��Ӧ�ɹ�����
		OrderHeaderVO[] orderHVos = (OrderHeaderVO[]) baseDao.retrieveByClause(
				OrderHeaderVO.class,
				" dr=0 and pk_order='"
						+ aggLhVo.getParentVO().getPk_saleorder() + "' ")
				.toArray(new OrderHeaderVO[0]);
		if (orderHVos == null || orderHVos.length < 1) {
			throw new BusinessException("NCϵͳ�в��������²ɹ�����,����������"
					+ aggLhVo.getParentVO().getPk_saleorder());
		}

		String[] arrOrderPkB = mapLhVoDetail.keySet().toArray(new String[0]);
		OrderItemVO[] orderBVos = (OrderItemVO[]) baseDao
				.retrieveByClause(
						OrderItemVO.class,
						" dr=0 and "
								+ SqlUtil.buildSqlForIn("pk_order_b",
										arrOrderPkB)).toArray(
						new OrderItemVO[0]);
		Map<String, OrderItemVO> mapOrderItemByPk = CollectionUtils
				.hashVOArray(OrderItemVO.PK_ORDER_B, orderBVos);
		if (orderBVos.length < mapLhVoDetail.size()) {
			HashSet<String> hsOrderPkBErr = new HashSet<String>();
			for (String pkb : arrOrderPkB) {
				if (mapOrderItemByPk == null
						|| !mapOrderItemByPk.containsKey(pkb)) {
					hsOrderPkBErr.add(pkb);
				}
			}
			throw new BusinessException("NCϵͳ�в��������²ɹ�������ϸ��������ϸ������"
					+ hsOrderPkBErr.toString());
		}

		// �ж�������ɹ������ӱ������Ƿ�ƥ��
		// Map<String, String> mapMaterialPkByOderPkb = new HashMap<String,
		// String>();
		// for (OrderItemVO bvo : orderBVos) {
		// mapMaterialPkByOderPkb.put(bvo.getPk_order_b(),
		// bvo.getPk_material());
		// }
		// for (LhPurchaseInDetailVO lhBVO : aggLhVo.getChildrenVO()) {
		// String pk_material_order = mapMaterialPkByOderPkb.get(lhBVO
		// .getPk_saleorder_b());
		// String pk_material_lh = mapMatVOsByCode.get(lhBVO.getMatcode())
		// .getPk_material();
		// if (!pk_material_order.equals(pk_material_lh)) {
		// throw new BusinessException("����NC�Ĳɹ������ӱ�����["
		// + lhBVO.getPk_saleorder_b() + "]��Ӧ�Ķ���������������["
		// + pk_material_order + "]���봫������ϱ���["
		// + lhBVO.getMatcode() + "]��Ӧ����������[" + pk_material_lh
		// + "]��һ�£�");
		// }
		// }

		OrderVO orderNewVO = new OrderVO();
		orderNewVO.setParentVO(orderHVos[0]);
		orderNewVO.setBVO(orderBVos);
		OrderVO[] orderVOs = new OrderVO[] { orderNewVO };
		if (!StringUtil.isEmpty(orderNewVO.getHVO().getVdef3())) {// ��ʽҵ�񣬲ɹ������ջ��ֿⲻ����Ϊ�գ�
			for (OrderItemVO bvo : orderNewVO.getBVO()) {
				if (StringUtil.isEmpty(bvo.getPk_recvstordoc())) {
					throw new BusinessException("�ɹ�����Ϊ��ʽҵ���ջ��ֿⲻ����Ϊ�գ�");
				}
			}
		}

		// ����ת������ɹ�����21ת��Ϊ�ɹ���ⵥ45
		AggregatedValueObject[] vos = PfUtilTools.runChangeDataAry("21", "45",
				orderVOs);

		// ȡ����ⵥ����������֯
		HashSet<String> hspk_reqorg = new HashSet<String>();
		for (AggregatedValueObject aggVO : vos) {
			for (CircularlyAccessibleValueObject bvo : aggVO.getChildrenVO())
				hspk_reqorg.add(bvo.getAttributeValue(
						PurchaseInBodyVO.CREQSTOORGOID).toString());
		}
		// �жϲֿ�����Ƿ����ڸ������֯
		// �жϲֿ�����Ƿ����
		StordocVO[] whReqVO = NCLocator
				.getInstance()
				.lookup(IStordocQueryService.class)
				.queryStordocByCondition(
						hspk_reqorg.toArray(new String[0]),
						" dr=0 and code='" + aggLhVo.getParentVO().getWhcode()
								+ "' ");// ����ֿ�
		// Map<String, StordocVO> mapStordocByOrg = CollectionUtils.hashVOArray(
		// StordocVO.PK_ORG, whVO);
		if (whReqVO == null || whReqVO.length < 1) {
			throw new BusinessException("�ֿ����["
					+ aggLhVo.getParentVO().getWhcode() + "]��NCϵͳ�в����ڣ�");
		} else if (whReqVO.length < hspk_reqorg.size()) {
			throw new BusinessException("�ֿ����["
					+ aggLhVo.getParentVO().getWhcode() + "]��������ⵥ��Ӧ��������֯��");
		}

		// HashSet<String> hsPk_org_in=new HashSet<String>();//���ڻ�ȡ��֯�ĵ���ϵ��

		UFDateTime createTime = AppContext.getInstance().getServerTime();
		// UFDate dtToday = createTime.getDate();
		UFDate dtToday = aggLhVo.getParentVO().getDdate();
		for (AggregatedValueObject aggVO : vos) {
			// hsPk_org_in.add(aggVO.getChildrenVO()[0].getAttributeValue("creqstoorgoid").toString());
			// String pk_storg = aggVO.getParentVO().getAttributeValue("pk_org")
			// .toString();// �����֯
			// String pk_stordoc =
			// mapStordocByOrg.get(pk_storg).getPk_stordoc();
			aggVO.getParentVO().setAttributeValue("dbilldate", dtToday);// ��������
			// aggVO.getParentVO().setAttributeValue("cwarehouseid",
			// pk_stordoc);// ���ֿ�
			// aggVO.getParentVO().setAttributeValue("vtrantypecode",
			// "45-01");//
			// aggVO.getParentVO().setAttributeValue("ctrantypeid", "");//
			aggVO.getParentVO().setAttributeValue("vdef1",
					aggLhVo.getParentVO().getPk_id());// ����ͷ�Զ�����1��¼��Դ��������
			aggVO.getParentVO().setAttributeValue("vdef2",
					aggLhVo.getParentVO().getSettleclue());// ����ͷ�Զ�����2��¼��������
			aggVO.getParentVO().setAttributeValue("vdef4", bPreSettle);
			if (StringUtil.isEmpty(orderVOs[0].getHVO().getVdef3())) {// ���ɹ���������·��Ϊ�գ���ֿ�ȡ���ڴ������Ĳֿ�
				aggVO.getParentVO().setAttributeValue("cwarehouseid",
						whReqVO[0].getPk_stordoc());
			} else {// ���ɹ���������·����Ϊ�գ���ֿ�ȡ�ɹ����������ջ��ֿ�,����ת��������Զ�ת����

			}
			// ����ͷ�Զ�����4��¼�Ƿ�Ԥ�����־
			CircularlyAccessibleValueObject[] bodys = aggVO.getChildrenVO();
			ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
			for (CircularlyAccessibleValueObject body : bodys) {
				String csalebid = (String) body
						.getAttributeValue("csourcebillbid");
				String pk_material_src = (String) body
						.getAttributeValue("cmaterialoid");
				if(!mapMatVOsByPk.containsKey(pk_material_src)){
					throw new BusinessException("���ڴ��ݵ�������NC�ɹ������ϵ����ϲ�һ��");
				}
				String pk_material_new = mapMatVOsByPk.get(pk_material_src)
						.getDef5();// 170814 ���ɵ���ⵥ������Ҫӳ��Ϊ���ϵ����Զ�����5��ֵ
				body.setAttributeValue("cmaterialoid", pk_material_new);
				body.setAttributeValue("cmaterialvid", pk_material_new);
				UFDouble nnum = mapLhVoDetail.get(csalebid).getIqty();
				nnum = scale.adjustNumScale(nnum,
						body.getAttributeValue("cunitid").toString());
				body.setAttributeValue("nnum", nnum);
				body.setAttributeValue("nassistnum", nnum);
				body.setAttributeValue("nqtunitnum", nnum);

				// ���¼�����ⵥ�ϵĽ��,����ӿ����ɵ���ⵥ�������ɷ�Ʊʱ���ܴ����������
				UFDouble nTaxPrice = new UFDouble(0);
				UFDouble nTaxMny = new UFDouble(0);
				UFDouble nTaxRate = new UFDouble(0);
				UFDouble nMny = new UFDouble(0);
				UFDouble nPrice = new UFDouble(0);
				nTaxRate = (UFDouble) (body.getAttributeValue("ntaxrate") == null ? new UFDouble(
						0) : (new UFDouble(body.getAttributeValue("ntaxrate")
						.toString())).div(new UFDouble(100)));
				if ("Y".equals(bPreSettle)) {
					nTaxPrice = (UFDouble) body
							.getAttributeValue("norigtaxprice");// 170523��ΪԤ���㣬��˰�۸�ȡ�����������ĵ���
					nTaxMny = nTaxPrice.multiply(nnum);
				} else {
					nTaxMny = mapLhVoDetail.get(csalebid).getFmoney();// ��˰���

					nTaxPrice = (nTaxMny.div(nnum)).setScale(5,
							UFDouble.ROUND_UP);// ��˰����
				}
				nMny = nTaxMny.div(nTaxRate.add(new UFDouble(1)));// ��˰���
				nTaxMny = nTaxMny.setScale(2, UFDouble.ROUND_UP);
				nPrice = (nMny.div(nnum)).setScale(5, UFDouble.ROUND_UP);// ��˰����
				nMny = nMny.setScale(2, UFDouble.ROUND_UP);
				// nTaxPrice=mapLhVoDetail.get(csalebid).getFprice();// ��˰����
				// body.setAttributeValue("ntaxprice", nTaxPrice);
				// ˰��
				// UFDouble nPrice = nTaxPrice.div(nTaxRate.add(new
				// UFDouble(1)));// ��˰����
				// nPrice=nPrice.setScale(5, UFDouble.ROUND_UP);

				// UFDouble nMny = (nnum.multiply(nPrice)).setScale(2,
				// UFDouble.ROUND_UP);// ��˰���
				// nTaxMny = (nnum.multiply(nTaxPrice)).setScale(2,
				// UFDouble.ROUND_UP);// ��˰���
				UFDouble nTax = nTaxMny.sub(nMny);

				body.setAttributeValue("nnetprice", nPrice);
				body.setAttributeValue("norignetprice", nPrice);
				body.setAttributeValue("norigprice", nPrice);
				body.setAttributeValue("nprice", nPrice);
				body.setAttributeValue("nqtnetprice", nPrice);
				body.setAttributeValue("nqtorignetprice", nPrice);
				body.setAttributeValue("nqtorigprice", nPrice);
				body.setAttributeValue("nqtprice", nPrice);

				body.setAttributeValue("norigtaxnetprice", nTaxPrice);
				body.setAttributeValue("norigtaxprice", nTaxPrice);
				body.setAttributeValue("nqtorigtaxnetprice", nTaxPrice);
				body.setAttributeValue("nqtorigtaxprice", nTaxPrice);
				body.setAttributeValue("nqttaxnetprice", nTaxPrice);
				body.setAttributeValue("nqttaxprice", nTaxPrice);
				body.setAttributeValue("ntaxnetprice", nTaxPrice);
				body.setAttributeValue("ntaxprice", nTaxPrice);

				body.setAttributeValue("ncalcostmny", nMny);
				body.setAttributeValue("ncaltaxmny", nMny);
				body.setAttributeValue("nmny", nMny);
				body.setAttributeValue("norigmny", nMny);

				body.setAttributeValue("norigtaxmny", nTaxMny);
				body.setAttributeValue("ntaxmny", nTaxMny);

				body.setAttributeValue("ntax", nTax);
				body.setAttributeValue("nshouldassistnum", new UFDouble(body
						.getAttributeValue("nshouldassistnum").toString())
						.multiply(new UFDouble(-1)));
				body.setAttributeValue("vbdef2", mapLhVoDetail.get(csalebid)
						.getVbillnodz());//170917 ���ڽ��㵥�Ŵ���

				body.setAttributeValue(PurchaseInBodyVO.PK_CREQWAREID,
						whReqVO[0].getPk_stordoc());// ����ֿ�
			}
			aggVO.getParentVO().setAttributeValue(
					ICPubMetaNameConst.CREATIONTIME, createTime);
			aggVO.getParentVO().setAttributeValue(ICPubMetaNameConst.CREATOR,
					cuserid);// 170614 gwj ����
			aggVO.getParentVO().setAttributeValue("billmaker", cuserid);// 170614
																		// gwj
																		// ����
		}

		// ScaleUtils scale = new ScaleUtils(swapContext.getPk_group());

		IPFBusiAction busiAction = NCLocator.getInstance().lookup(
				IPFBusiAction.class);
		try {
			AggregatedValueObject[] purchaseInVOs = (AggregatedValueObject[]) busiAction
					.processBatch("PUSHSAVE", "45", vos, null, null, null);
			for (AggregatedValueObject tmpAggVO : purchaseInVOs) {
				retStr.append("{"
						+ tmpAggVO.getParentVO()
								.getAttributeValue("cgeneralhid").toString()
						+ "|"
						+ tmpAggVO.getParentVO().getAttributeValue("vbillcode")
								.toString() + "}");
			}
		} catch (BusinessException err) {
			throw new BusinessException("����NC�ɹ���ⵥ�����쳣��" + err.getMessage());
		}

		// �ж�������������Ϊ0������Ҫ������������ⵥ
		// HashSet<String> hsorderpk_b=new HashSet<String>();

		// ArrayList<GeneralInBodyVO> algeneralBVO = new
		// ArrayList<GeneralInBodyVO>();
		// for (Map.Entry<String, LhPurchaseInDetailVO> entry : mapLhVoDetail
		// .entrySet()) {
		// GeneralInBodyVO generalBVO = new GeneralInBodyVO();
		// LhPurchaseInDetailVO lhPuBVO = entry.getValue();
		// if (!lhPuBVO.getIqtyadj().equals(new UFDouble(0))) {
		// String pk_material = mapMatVOsByCode.get(lhPuBVO.getMatcode())
		// .getPk_material();
		// UFDouble nnum = lhPuBVO.getIqtyadj();
		// // UFDouble nTaxPrice=lhPuBVO.getFprice();
		//
		// generalBVO.setCmaterialoid(pk_material);
		// generalBVO.setCmaterialvid(pk_material);
		// generalBVO.setNnum(nnum);
		// generalBVO.setNassistnum(nnum);
		// algeneralBVO.add(generalBVO);
		// }
		// }
		// if (algeneralBVO != null && algeneralBVO.size() > 0) {
		// ����������Ϊ0������Ҫ����������ⵥ

		OrgVO[] orgVOsIn = (OrgVO[]) baseDao.retrieveByClause(
				OrgVO.class,
				" dr=0 and isbusinessunit='Y' and "
						+ SqlUtil.buildSqlForIn("pk_org",
								hspk_reqorg.toArray(new String[0]))).toArray(
				new OrgVO[0]);
		Map<String, OrgVO> mapOrgVOsIn = CollectionUtils.hashVOArray(
				OrgVO.PK_ORG, orgVOsIn);

		BilltypeVO[] billTypeVo = (BilltypeVO[]) baseDao.retrieveByClause(
				BilltypeVO.class,
				" pk_billtypecode='4A-01' and pk_group='"
						+ swapContext.getPk_group() + "'").toArray(
				new BilltypeVO[0]);
		if (billTypeVo == null || billTypeVo.length < 1) {
			throw new BusinessException("δ��ѯ��������ⵥ��Ĭ�ϳ��������[4A-01]��");
		}
		ArrayList<GeneralInVO> alGeneralVO = new ArrayList<GeneralInVO>();
		for (AggregatedValueObject puinvo : vos) {
			GeneralInVO generalVO = new GeneralInVO();
			// ICBillHeadVO headVO = (ICBillHeadVO)
			// puinvo.getParentVO().clone();
			GeneralInHeadVO headVO = new GeneralInHeadVO();
			headVO.setDbilldate(dtToday);// ��������
			headVO.setVdef1(aggLhVo.getParentVO().getPk_id());//
			// ����ͷ�Զ�����1��¼��Դ��������
			headVO.setVdef2(aggLhVo.getParentVO().getSettleclue());//
			// ����ͷ�Զ�����2��¼��������
			headVO.setVdef4(aggLhVo.getParentVO().getBpresettle());// ����ͷ�Զ�����4��¼�Ƿ�Ԥ�����־
			// ����ͷ�Զ�����3��¼��Ӧ��
			headVO.setVdef3(puinvo.getParentVO().getAttributeValue("cvendorid")
					.toString());// 170624 ��������
			headVO.setCreator(cuserid);// 170614 gwj ����
			headVO.setCreationtime(createTime);
			headVO.setBillmaker(cuserid);// 170614 gwj ����
			headVO.setVtrantypecode("4A-01");
			headVO.setCtrantypeid(billTypeVo[0].getPk_billtypeid());
			headVO.setPk_group(swapContext.getPk_group());
			// headVO.setPk_org(puinvo.getParentVO().getAttributeValue("pk_org")
			// .toString());
			// headVO.setPk_org_v(puinvo.getParentVO()
			// .getAttributeValue("pk_org_v").toString());
			headVO.setPk_org(puinvo.getChildrenVO()[0].getAttributeValue(
					"creqstoorgoid").toString());// 170624 ����
			headVO.setPk_org_v(puinvo.getChildrenVO()[0].getAttributeValue(
					"creqstoorgvid").toString());// 170624 ����
			headVO.setCwarehouseid(puinvo.getParentVO()
					.getAttributeValue("cwarehouseid").toString());
			// headVO.setPk_org(puinvo.getParentVO().getAttributeValue("pk_org").toString());
			CircularlyAccessibleValueObject[] puinbodyVOs = puinvo
					.getChildrenVO();
			ArrayList<GeneralInBodyVO> alBodyVOs = new ArrayList<GeneralInBodyVO>();
			for (CircularlyAccessibleValueObject puinbodyVO : puinbodyVOs) {
				LhPurchaseInDetailVO lhbvo = mapLhVoDetail.get(puinbodyVO
						.getAttributeValue("csourcebillbid"));
				if (lhbvo.getIqtyadj().compareTo(new UFDouble(-10)) <= 0
						|| lhbvo.getIqtyadj().compareTo(UFDouble.ZERO_DBL) > 0) {// 170724
					// ��������С�ڵ���0����-10������������ⵥ������������>0���������<=-10�Ļ�����Ҫ����������ⵥ
					// ICBillBodyVO bodyVO = (ICBillBodyVO) puinbodyVO.clone();
					GeneralInBodyVO bodyVO = new GeneralInBodyVO();
					bodyVO.setCrowno(puinbodyVO.getAttributeValue("crowno")
							.toString());
					bodyVO.setCmaterialoid(puinbodyVO.getAttributeValue(
							"cmaterialoid").toString());
					bodyVO.setCmaterialvid(puinbodyVO.getAttributeValue(
							"cmaterialvid").toString());

					// String pk_material_src = (String) puinbodyVO
					// .getAttributeValue("cmaterialoid");
					// String pk_material_new =
					// mapMatVOsByPk.get(pk_material_src)
					// .getDef5();// 170814 ���ɵ���ⵥ������Ҫӳ��Ϊ���ϵ����Զ�����5��ֵ
					// bodyVO.setAttributeValue("cmaterialoid",
					// pk_material_new);
					// bodyVO.setAttributeValue("cmaterialvid",
					// pk_material_new);

					bodyVO.setCastunitid(puinbodyVO.getAttributeValue(
							"castunitid").toString());
					bodyVO.setCunitid(puinbodyVO.getAttributeValue("cunitid")
							.toString());

					// 20170525 1.������ⵥ����=���Ľӿ��еĵ����� 2.�ɻ���*��ͬ��ҵ��Ԫ�ĵ���ϵ����Ϊ�ɻ���
					UFDouble nnumjs = lhbvo.getIqty();
					UFDouble nnumadj = lhbvo.getIqtyadj();// TO-DO
					UFDouble fadjustrate = new UFDouble(1);
					UFDouble nnumin = new UFDouble(0);
					String crate = mapOrgVOsIn.get(
							puinvo.getChildrenVO()[0].getAttributeValue(
									"creqstoorgoid").toString()).getDef2();// 170624
																			// ����
					if (!StringUtil.isEmpty(crate) && !"~".equals(crate)
							&& !new UFDouble(1).equals(new UFDouble(crate))) {
						fadjustrate = new UFDouble(crate);
						nnumin = nnumjs.sub((nnumjs.sub(nnumadj))
								.multiply(fadjustrate));
					} else {
						nnumin = (nnumadj.multiply(fadjustrate))
								.multiply(new UFDouble(-1));
					}

					bodyVO.setNnum(nnumin);
					bodyVO.setNassistnum(nnumin);
					bodyVO.setVchangerate(puinbodyVO.getAttributeValue(
							"vchangerate").toString());
					bodyVO.setNshouldassistnum(nnumin);
					bodyVO.setNshouldnum(nnumin);

					bodyVO.setNcostmny(new UFDouble(0));
					bodyVO.setNcostprice(new UFDouble(0));
					bodyVO.setCsourcebillbid(null);
					bodyVO.setCsourcebillhid(null);
					bodyVO.setCsourcetranstype(null);
					bodyVO.setCsourcetype(null);
					bodyVO.setCfirstbillbid(null);
					bodyVO.setCfirstbillhid(null);
					bodyVO.setCfirsttranstype(null);
					bodyVO.setCfirsttype(null);
					bodyVO.setVsourcebillcode(null);
					bodyVO.setCbodytranstypecode("");
					bodyVO.setBbarcodeclose(new UFBoolean(false));
					bodyVO.setBhasiabill(new UFBoolean(true));
					bodyVO.setBonroadflag(new UFBoolean(false));
					bodyVO.setVbdef2(lhbvo.getVbillnodz());//170917 ���ݴ��ڽ��㵥��
					alBodyVOs.add(bodyVO);

					// //���¼�����ⵥ�ϵĽ��,����ӿ����ɵ���ⵥ�������ɷ�Ʊʱ���ܴ����������
					// UFDouble nTaxPrice = lhbvo.getFprice();// ��˰����
					// // ˰��
					// UFDouble nTaxRate = (UFDouble)
					// puinbodyVO.getAttributeValue("ntaxrate");
					//
					// UFDouble nPrice = nTaxPrice.div(nTaxRate
					// .add(new UFDouble(1)));// ��˰����
					//
					// UFDouble nMny = (nnum.multiply(nPrice)).setScale(2,
					// UFDouble.ROUND_UP);// ��˰���
					// UFDouble nTaxMny = (nnum.multiply(nTaxPrice)).setScale(2,
					// UFDouble.ROUND_UP);// ��˰���
					// UFDouble nTax = nTaxMny.sub(nMny);

					// body.setAttributeValue("ncalcostmny", nMny);
					// body.setAttributeValue("ncaltaxmny", nMny);
					// body.setAttributeValue("nmny", nMny);
					// body.setAttributeValue("norigmny", nMny);
					//
					// body.setAttributeValue("norigtaxmny", nTaxMny);
					// body.setAttributeValue("ntaxmny", nTaxMny);
					//
					// body.setAttributeValue("ntax", nTax);
				}
			}
			if (alBodyVOs != null && alBodyVOs.size() > 0) {
				generalVO.setParentVO(headVO);
				generalVO.setChildrenVO(alBodyVOs
						.toArray(new GeneralInBodyVO[alBodyVOs.size()]));
				alGeneralVO.add(generalVO);
			}
		}

		try {
			if (alGeneralVO != null && alGeneralVO.size() > 0) {
				GeneralInVO[] icbills = (GeneralInVO[]) busiAction
						.processBatch(IPFActionName.WRITE, "4A", alGeneralVO
								.toArray(new GeneralInVO[alGeneralVO.size()]),
								null, null, null);

			}
		} catch (BusinessException err) {
			throw new BusinessException("����������ⵥ�����쳣��" + err.getMessage());
		}

		Map<String, String> mapOrgCodeByPk = new HashMap();
		HashSet<String> hsOrgCodes = new HashSet<String>();
		HashSet<String> hsOrgPks = new HashSet<String>();
		for (OrgVO orgvo : orgVOsIn) {
			mapOrgCodeByPk.put(orgvo.getPk_org(), orgvo.getCode());
			hsOrgCodes.add(orgvo.getCode());
			hsOrgPks.add(orgvo.getPk_org());
		}
		if ((hsOrgCodes == null) || (hsOrgCodes.size() < 1)) {
			throw new BusinessException("��ȡ�����֯�����쳣��");
		}
		String costregConditon = SqlUtil.buildSqlForIn("code",
				(String[]) hsOrgCodes.toArray(new String[0]));
		CostRegionVO[] costregionVOs = NCLocator.getInstance()
				.lookup(ICostRegionQryService.class)
				.queryCostRegionVOsByClause(" dr=0 and " + costregConditon);
		Map<String, CostRegionVO> mapCostRegionByCode = CollectionUtils
				.hashVOArray("code", costregionVOs);
		if ((mapCostRegionByCode == null)
				|| (mapOrgCodeByPk.size() != mapCostRegionByCode.size())) {
			throw new BusinessException("���ݿ����֯�����޷���ȷƥ�䵽��Ӧ�ĳɱ���");
		}

		BilltypeVO[] billTypeVo56 = (BilltypeVO[]) baseDao.retrieveByClause(
				BilltypeVO.class,
				" pk_billtypecode='45-Cxx-56' and pk_group='"
						+ swapContext.getPk_group() + "'").toArray(
				new BilltypeVO[0]);
		if ((billTypeVo56 == null) || (billTypeVo56.length < 1)) {
			throw new BusinessException("δ��ѯ������������Ĭ�ϳ��������[45-Cxx-56]��");
		}

		Map<String, String> mapAccBookByOrgPk = NCLocator
				.getInstance()
				.lookup(IAccountingBookPubService.class)
				.queryAccountingBookIDByFinanceOrgIDWithMainAccountBook(
						hsOrgPks.toArray(new String[0]));
		if ((mapAccBookByOrgPk == null) || (mapAccBookByOrgPk.size() < 1)
				|| (hsOrgPks.size() > mapAccBookByOrgPk.size())) {
			throw new BusinessException("�����֯��Ӧ��Ĭ�ϲ�������˲������쳣����˶ԣ�");
		}

		ArrayList<I9BillVO> alAdjInBillVO = new ArrayList<I9BillVO>();
		for (AggregatedValueObject puinvo : vos) {
			I9BillVO adjInVo = new I9BillVO();
			I9HeadVO headvo = new I9HeadVO();
			UFDate dtbilldate = new UFDate(puinvo.getParentVO()
					.getAttributeValue("dbilldate").toString());
			String pk_costregion = mapCostRegionByCode.get(
					mapOrgCodeByPk.get(puinvo.getParentVO()
							.getAttributeValue("pk_org").toString()))
					.getPk_costregion();
			String pk_book = mapAccBookByOrgPk.get(puinvo.getParentVO()
					.getAttributeValue("pk_org").toString());
			OrgVO orgvo = mapOrgVOsIn.get(puinvo.getParentVO()
					.getAttributeValue("pk_org").toString());
			UFDouble fdef3value = ("~".equals(orgvo.getDef3()))
					|| ("".equals(orgvo.getDef3())) ? UFDouble.ONE_DBL
					: new UFDouble(orgvo.getDef3());
			UFDouble fdef4value = ("~".equals(orgvo.getDef4()))
					|| ("".equals(orgvo.getDef4())) ? UFDouble.ONE_DBL
					: new UFDouble(orgvo.getDef4());
			headvo.setBconvertflag(UFBoolean.FALSE);
			headvo.setBestimateflag(UFBoolean.FALSE);
			// headvo.setBillmaker(puinvo.getParentVO()
			// .getAttributeValue("billmaker").toString());
			headvo.setBsystemflag(UFBoolean.FALSE);
			headvo.setCaccountperiod(String.valueOf(dtbilldate.getYear()) + "-"
					+ dtbilldate.getStrMonth());
			headvo.setCreator(cuserid);// 17081714 gwj ����
			headvo.setCreationtime(createTime);
			headvo.setBillmaker(cuserid);// 170817 gwj ����
			// headvo.setCreationtime(new UFDateTime(puinvo.getParentVO()
			// .getAttributeValue("creationtime").toString()));
			// headvo.setCreator(puinvo.getParentVO().getAttributeValue("creator")
			// .toString());
			headvo.setCsrcmodulecode("IA");
			headvo.setCstockorgid(puinvo.getParentVO()
					.getAttributeValue("pk_org").toString());
			headvo.setCstockorgvid(puinvo.getParentVO()
					.getAttributeValue("pk_org_v").toString());
			headvo.setCstordocid(puinvo.getParentVO()
					.getAttributeValue("cwarehouseid").toString());
			headvo.setCtrantypeid(billTypeVo56[0].getPk_billtypeid());
			headvo.setCvendorid(puinvo.getParentVO()
					.getAttributeValue("cvendorid").toString());
			headvo.setDbilldate(new UFDate(puinvo.getParentVO()
					.getAttributeValue("dbilldate").toString()));
			headvo.setDmakedate(createTime.getDate());
			headvo.setDr(Integer.valueOf(0));
			headvo.setPk_book(pk_book);
			headvo.setPk_group(puinvo.getParentVO()
					.getAttributeValue("pk_group").toString());
			headvo.setPk_org(pk_costregion);
			headvo.setVdef1(aggLhVo.getParentVO().getPk_id());//
			// ����ͷ�Զ�����1��¼��Դ��������
			headvo.setVdef4(aggLhVo.getParentVO().getBpresettle());// ����ͷ�Զ�����4��¼�Ƿ�Ԥ�����־
			headvo.setVdef11(puinvo.getParentVO().getAttributeValue("vdef11") == null ? "~"
					: puinvo.getParentVO().getAttributeValue("vdef11")
							.toString());
			headvo.setCsrcmodulecode(NCModule.OT.getSystemCode());// ����Դϵͳ���ⲿϵͳ�������ɵĵ����Ƶ��˻�Ĭ��ȡ��ǰ��¼��BaseInsertDataFillRule.fillMakeInfo

			ArrayList<I9ItemVO> alBodyvo = new ArrayList<I9ItemVO>();
			for (CircularlyAccessibleValueObject puinbvo : puinvo
					.getChildrenVO()) {
				I9ItemVO bodyvo = new I9ItemVO();
				UFDouble fTransMny = mapLhVoDetail.get(
						puinbvo.getAttributeValue("csourcebillbid").toString())
						.getFtransmoney();
				if (fTransMny.equals(UFDouble.ZERO_DBL)) {
					continue;
				}
				if ("1001A6100000000000XU".equals(puinvo.getParentVO()
						.getAttributeValue("vdef11").toString())) {
					if ((fdef3value.equals(UFDouble.ZERO_DBL))
							|| (fdef4value.equals(UFDouble.ZERO_DBL))) {
						throw new BusinessException(
								"�������������쳣����������ʱ��֯���Զ�����3���Զ�����4��Ϊ��������Ϊ0");
					}
					fTransMny = fTransMny.div(fdef3value).div(fdef4value);
				}
				bodyvo.setCaccountperiod(String.valueOf(dtbilldate.getYear())
						+ "-" + dtbilldate.getStrMonth());
				bodyvo.setCcurrencyid("1002Z0100000000001K1");

				bodyvo.setCinventoryid(puinbvo
						.getAttributeValue("cmaterialoid").toString());
				bodyvo.setCinventoryvid(puinbvo.getAttributeValue(
						"cmaterialvid").toString());

				// String pk_material_src = (String) puinbvo
				// .getAttributeValue("cmaterialoid");
				// String pk_material_new = mapMatVOsByPk.get(pk_material_src)
				// .getDef5();// 170814 ���ɵ���ⵥ������Ҫӳ��Ϊ���ϵ����Զ�����5��ֵ
				// bodyvo.setAttributeValue("cmaterialoid", pk_material_new);
				// bodyvo.setAttributeValue("cmaterialvid", pk_material_new);

				bodyvo.setCrowno(puinbvo.getAttributeValue("crowno").toString());
				bodyvo.setCunitid(puinbvo.getAttributeValue("cunitid")
						.toString());
				bodyvo.setDaccountdate(dtbilldate);
				bodyvo.setDbilldate(dtbilldate);
				bodyvo.setDbizdate(dtbilldate);
				bodyvo.setDr(Integer.valueOf(0));
				bodyvo.setFcalcbizflag(Integer.valueOf(1));
				bodyvo.setFcalcthreadbizflag(Integer.valueOf(1));
				bodyvo.setNmny(fTransMny);
				bodyvo.setPk_book(pk_book);
				bodyvo.setPk_group(puinbvo.getAttributeValue("pk_group")
						.toString());
				bodyvo.setPk_org(pk_costregion);
				bodyvo.setVbdef2(((LhPurchaseInDetailVO) mapLhVoDetail
						.get(puinbvo.getAttributeValue("csourcebillbid")
								.toString())).getVbillnodz());

				alBodyvo.add(bodyvo);
			}
			if ((alBodyvo != null) && (alBodyvo.size() > 0)) {
				adjInVo.setParentVO(headvo);
				adjInVo.setChildrenVO((CircularlyAccessibleValueObject[]) alBodyvo
						.toArray(new I9ItemVO[0]));
				alAdjInBillVO.add(adjInVo);
			}
		}
		if ((alAdjInBillVO != null) && (alAdjInBillVO.size() > 0)) {
			I9BillVO[] retAdjInBillVO = NCLocator
					.getInstance()
					.lookup(IIAI9BillAPI.class)
					.insertI9ForPUB(
							(I9BillVO[]) alAdjInBillVO.toArray(new I9BillVO[0]));
		}

		return retStr.toString();
	}

	protected void checkData(AggLhPurchaseInVO aggVO) throws BusinessException {

		boolean bPresettle = false;
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_id())) {
			throw new BusinessException("��ϵͳ��������������Ϊ�գ�");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_saleorder())) {
			throw new BusinessException("NC�ɹ���������������Ϊ�գ�");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getWhcode())) {
			throw new BusinessException("�ֿ���벻����Ϊ�գ�");
		}
		if (aggVO.getParentVO().getDdate() == null) {
			throw new BusinessException("�������ڲ�����Ϊ�գ�");
		}
		// if(!"Y".equals(aggVO.getParentVO().getBpresettle()) &&
		// !"N".equals(aggVO.getParentVO().getBpresettle())){
		// throw new BusinessException("Ԥ�����־ֻ��ΪY��N��");//Ԥ�����־����ΪY��N
		// }
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getBpresettle())) {
			bPresettle = false;
			aggVO.getParentVO().setBpresettle("N");// Ԥ�����־��Ϊ�գ���Ĭ��ΪN
		}
		if (aggVO.getParentVO().getBpresettle().equals("Y")) {
			bPresettle = true;
		} else {
			bPresettle = false;
		}

		LhPurchaseInDetailVO[] bvos = aggVO.getChildrenVO();
		// HashSet<String> hsMatCodes = new HashSet<String>();
		for (LhPurchaseInDetailVO bvo : bvos) {
			if (bvo.getPk_saleorder_b() == null
					|| StringUtil.isEmptyWithTrim(bvo.getPk_saleorder_b())) {
				throw new BusinessException("NC�ɹ������ӱ�����������Ϊ�գ�");
			}
			if (bvo.getMatcode() == null
					|| StringUtil.isEmptyWithTrim(bvo.getMatcode())) {
				throw new BusinessException("��Ʒ���벻����Ϊ�գ�");
			}
			if (bvo.getIqty() == null
					|| bvo.getIqty().equals(UFDouble.ZERO_DBL)) {
				throw new BusinessException("��������������Ϊ0��");
			}
			if (bvo.getIqtyadj() == null) {
				// throw new BusinessException("ԭ������������Ϊ�գ�");
				bvo.setIqtyadj(UFDouble.ZERO_DBL);
			}
			if (bvo.getFtransmoney() == null) {
				bvo.setFtransmoney(UFDouble.ZERO_DBL);
			}
			// if (bPresettle == false && bvo.getFprice() == null) {
			// throw new BusinessException("����۸�����Ϊ�գ�");
			// }
			// hsMatCodes.add(bvo.getAttributeValue("matcode").toString());
		}
	}
}
