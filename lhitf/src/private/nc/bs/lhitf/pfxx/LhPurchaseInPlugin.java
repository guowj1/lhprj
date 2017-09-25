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
		// 校验档案数据合法性
		// 判断pk_id是否已经存在对应的单据??

		Map<String, LhPurchaseInDetailVO> mapLhVoDetail = CollectionUtils
				.hashVOArray("pk_saleorder_b", aggLhVo.getChildrenVO());
		if (mapLhVoDetail.size() < aggLhVo.getChildrenVO().length) {
			throw new BusinessException("子表中相同[NC采购订单子表主键]的记录必须合并！");
		}

		// 判断大宗用户编码是否正确
		String usercode = aggLhVo.getParentVO().getUserid();
		String cuserid = INCSystemUserConst.NC_USER_PK;
		if (!StringUtil.isEmptyWithTrim(usercode)) {
			usercode = usercode.toLowerCase();
			UserVO[] uservos = (UserVO[]) baseDao.retrieveByClause(
					UserVO.class,
					" user_code='" + usercode + "' and dr=0 and enablestate=2")
					.toArray(new UserVO[0]);
			if (uservos == null || uservos.length < 1) {
				throw new BusinessException("制单人编码[" + usercode
						+ "]在NC系统中不存在或已停用！");
			}
			cuserid = uservos[0].getCuserid();
		}

		// 判断物料编码是否正确
		HashSet<String> hsMatCodes = new HashSet<String>();// 物料编码
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
				throw new BusinessException("以下物料编码在NC系统中不存在："
						+ hsErrMatCodes.toString());

		}
		HashSet<String> hsMatCodeDef5Null = new HashSet<String>();// 170814
		// 调整物料档案需要映射自定义项5
		for (MaterialVO matvo : matVOs) {
			if (StringUtil.isEmptyWithTrim(matvo.getDef5())
					|| "~".equals(matvo.getDef5())) {
				hsMatCodeDef5Null.add(matvo.getCode());
			}
		}
		if (hsMatCodeDef5Null != null && hsMatCodeDef5Null.size() > 0) {
			throw new BusinessException("以下物料编码在NC中对应的物料档案尚未映射转换后的物料档案(自定义项5):"
					+ hsMatCodeDef5Null.toString());
		}
		// 获取对应采购订单
		OrderHeaderVO[] orderHVos = (OrderHeaderVO[]) baseDao.retrieveByClause(
				OrderHeaderVO.class,
				" dr=0 and pk_order='"
						+ aggLhVo.getParentVO().getPk_saleorder() + "' ")
				.toArray(new OrderHeaderVO[0]);
		if (orderHVos == null || orderHVos.length < 1) {
			throw new BusinessException("NC系统中不存在以下采购订单,订单主键："
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
			throw new BusinessException("NC系统中不存在以下采购订单明细，订单明细主键："
					+ hsOrderPkBErr.toString());
		}

		// 判断物料与采购订单子表主键是否匹配
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
		// throw new BusinessException("传入NC的采购订单子表主键["
		// + lhBVO.getPk_saleorder_b() + "]对应的订单上物料主键是["
		// + pk_material_order + "]，与传入的物料编码["
		// + lhBVO.getMatcode() + "]对应的物料主键[" + pk_material_lh
		// + "]不一致！");
		// }
		// }

		OrderVO orderNewVO = new OrderVO();
		orderNewVO.setParentVO(orderHVos[0]);
		orderNewVO.setBVO(orderBVos);
		OrderVO[] orderVOs = new OrderVO[] { orderNewVO };
		if (!StringUtil.isEmpty(orderNewVO.getHVO().getVdef3())) {// 链式业务，采购订单收货仓库不允许为空！
			for (OrderItemVO bvo : orderNewVO.getBVO()) {
				if (StringUtil.isEmpty(bvo.getPk_recvstordoc())) {
					throw new BusinessException("采购订单为链式业务，收货仓库不允许为空！");
				}
			}
		}

		// 单据转换规则采购订单21转换为采购入库单45
		AggregatedValueObject[] vos = PfUtilTools.runChangeDataAry("21", "45",
				orderVOs);

		// 取得入库单的需求库存组织
		HashSet<String> hspk_reqorg = new HashSet<String>();
		for (AggregatedValueObject aggVO : vos) {
			for (CircularlyAccessibleValueObject bvo : aggVO.getChildrenVO())
				hspk_reqorg.add(bvo.getAttributeValue(
						PurchaseInBodyVO.CREQSTOORGOID).toString());
		}
		// 判断仓库编码是否属于各库存组织
		// 判断仓库编码是否存在
		StordocVO[] whReqVO = NCLocator
				.getInstance()
				.lookup(IStordocQueryService.class)
				.queryStordocByCondition(
						hspk_reqorg.toArray(new String[0]),
						" dr=0 and code='" + aggLhVo.getParentVO().getWhcode()
								+ "' ");// 需求仓库
		// Map<String, StordocVO> mapStordocByOrg = CollectionUtils.hashVOArray(
		// StordocVO.PK_ORG, whVO);
		if (whReqVO == null || whReqVO.length < 1) {
			throw new BusinessException("仓库编码["
					+ aggLhVo.getParentVO().getWhcode() + "]在NC系统中不存在！");
		} else if (whReqVO.length < hspk_reqorg.size()) {
			throw new BusinessException("仓库编码["
					+ aggLhVo.getParentVO().getWhcode() + "]不属于入库单对应的需求组织！");
		}

		// HashSet<String> hsPk_org_in=new HashSet<String>();//用于获取组织的调整系数

		UFDateTime createTime = AppContext.getInstance().getServerTime();
		// UFDate dtToday = createTime.getDate();
		UFDate dtToday = aggLhVo.getParentVO().getDdate();
		for (AggregatedValueObject aggVO : vos) {
			// hsPk_org_in.add(aggVO.getChildrenVO()[0].getAttributeValue("creqstoorgoid").toString());
			// String pk_storg = aggVO.getParentVO().getAttributeValue("pk_org")
			// .toString();// 库存组织
			// String pk_stordoc =
			// mapStordocByOrg.get(pk_storg).getPk_stordoc();
			aggVO.getParentVO().setAttributeValue("dbilldate", dtToday);// 单据日期
			// aggVO.getParentVO().setAttributeValue("cwarehouseid",
			// pk_stordoc);// 入库仓库
			// aggVO.getParentVO().setAttributeValue("vtrantypecode",
			// "45-01");//
			// aggVO.getParentVO().setAttributeValue("ctrantypeid", "");//
			aggVO.getParentVO().setAttributeValue("vdef1",
					aggLhVo.getParentVO().getPk_id());// 单据头自定义项1记录来源单据主键
			aggVO.getParentVO().setAttributeValue("vdef2",
					aggLhVo.getParentVO().getSettleclue());// 单据头自定义项2记录结算依据
			aggVO.getParentVO().setAttributeValue("vdef4", bPreSettle);
			if (StringUtil.isEmpty(orderVOs[0].getHVO().getVdef3())) {// 若采购订单结算路径为空，则仓库取大宗传过来的仓库
				aggVO.getParentVO().setAttributeValue("cwarehouseid",
						whReqVO[0].getPk_stordoc());
			} else {// 若采购订单结算路径不为空，则仓库取采购订单表体收货仓库,单据转换规则会自动转过来

			}
			// 单据头自定义项4记录是否预结算标志
			CircularlyAccessibleValueObject[] bodys = aggVO.getChildrenVO();
			ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
			for (CircularlyAccessibleValueObject body : bodys) {
				String csalebid = (String) body
						.getAttributeValue("csourcebillbid");
				String pk_material_src = (String) body
						.getAttributeValue("cmaterialoid");
				if(!mapMatVOsByPk.containsKey(pk_material_src)){
					throw new BusinessException("大宗传递的物料与NC采购订单上的物料不一致");
				}
				String pk_material_new = mapMatVOsByPk.get(pk_material_src)
						.getDef5();// 170814 生成的入库单物料需要映射为物料档案自定义项5的值
				body.setAttributeValue("cmaterialoid", pk_material_new);
				body.setAttributeValue("cmaterialvid", pk_material_new);
				UFDouble nnum = mapLhVoDetail.get(csalebid).getIqty();
				nnum = scale.adjustNumScale(nnum,
						body.getAttributeValue("cunitid").toString());
				body.setAttributeValue("nnum", nnum);
				body.setAttributeValue("nassistnum", nnum);
				body.setAttributeValue("nqtunitnum", nnum);

				// 重新计算入库单上的金额,解决接口生成的入库单，在生成发票时不能带入金额的问题
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
							.getAttributeValue("norigtaxprice");// 170523若为预结算，则含税价格取订单带过来的单价
					nTaxMny = nTaxPrice.multiply(nnum);
				} else {
					nTaxMny = mapLhVoDetail.get(csalebid).getFmoney();// 含税金额

					nTaxPrice = (nTaxMny.div(nnum)).setScale(5,
							UFDouble.ROUND_UP);// 含税单价
				}
				nMny = nTaxMny.div(nTaxRate.add(new UFDouble(1)));// 无税金额
				nTaxMny = nTaxMny.setScale(2, UFDouble.ROUND_UP);
				nPrice = (nMny.div(nnum)).setScale(5, UFDouble.ROUND_UP);// 无税单价
				nMny = nMny.setScale(2, UFDouble.ROUND_UP);
				// nTaxPrice=mapLhVoDetail.get(csalebid).getFprice();// 含税单价
				// body.setAttributeValue("ntaxprice", nTaxPrice);
				// 税率
				// UFDouble nPrice = nTaxPrice.div(nTaxRate.add(new
				// UFDouble(1)));// 无税单价
				// nPrice=nPrice.setScale(5, UFDouble.ROUND_UP);

				// UFDouble nMny = (nnum.multiply(nPrice)).setScale(2,
				// UFDouble.ROUND_UP);// 无税金额
				// nTaxMny = (nnum.multiply(nTaxPrice)).setScale(2,
				// UFDouble.ROUND_UP);// 含税金额
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
						.getVbillnodz());//170917 大宗结算单号传递

				body.setAttributeValue(PurchaseInBodyVO.PK_CREQWAREID,
						whReqVO[0].getPk_stordoc());// 需求仓库
			}
			aggVO.getParentVO().setAttributeValue(
					ICPubMetaNameConst.CREATIONTIME, createTime);
			aggVO.getParentVO().setAttributeValue(ICPubMetaNameConst.CREATOR,
					cuserid);// 170614 gwj 调整
			aggVO.getParentVO().setAttributeValue("billmaker", cuserid);// 170614
																		// gwj
																		// 调整
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
			throw new BusinessException("生成NC采购入库单遇到异常：" + err.getMessage());
		}

		// 判断若调整数量不为0，则需要再生成其他入库单
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
		// 调整数量不为0，则需要生成其他入库单

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
			throw new BusinessException("未查询到其他入库单的默认出入库类型[4A-01]！");
		}
		ArrayList<GeneralInVO> alGeneralVO = new ArrayList<GeneralInVO>();
		for (AggregatedValueObject puinvo : vos) {
			GeneralInVO generalVO = new GeneralInVO();
			// ICBillHeadVO headVO = (ICBillHeadVO)
			// puinvo.getParentVO().clone();
			GeneralInHeadVO headVO = new GeneralInHeadVO();
			headVO.setDbilldate(dtToday);// 单据日期
			headVO.setVdef1(aggLhVo.getParentVO().getPk_id());//
			// 单据头自定义项1记录来源单据主键
			headVO.setVdef2(aggLhVo.getParentVO().getSettleclue());//
			// 单据头自定义项2记录结算依据
			headVO.setVdef4(aggLhVo.getParentVO().getBpresettle());// 单据头自定义项4记录是否预结算标志
			// 单据头自定义项3记录供应商
			headVO.setVdef3(puinvo.getParentVO().getAttributeValue("cvendorid")
					.toString());// 170624 增加内容
			headVO.setCreator(cuserid);// 170614 gwj 调整
			headVO.setCreationtime(createTime);
			headVO.setBillmaker(cuserid);// 170614 gwj 调整
			headVO.setVtrantypecode("4A-01");
			headVO.setCtrantypeid(billTypeVo[0].getPk_billtypeid());
			headVO.setPk_group(swapContext.getPk_group());
			// headVO.setPk_org(puinvo.getParentVO().getAttributeValue("pk_org")
			// .toString());
			// headVO.setPk_org_v(puinvo.getParentVO()
			// .getAttributeValue("pk_org_v").toString());
			headVO.setPk_org(puinvo.getChildrenVO()[0].getAttributeValue(
					"creqstoorgoid").toString());// 170624 调整
			headVO.setPk_org_v(puinvo.getChildrenVO()[0].getAttributeValue(
					"creqstoorgvid").toString());// 170624 调整
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
					// 调整数量小于等于0大于-10则不生成其他入库单，即调整数量>0或调整数量<=-10的话都需要生成其他入库单
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
					// .getDef5();// 170814 生成的入库单物料需要映射为物料档案自定义项5的值
					// bodyVO.setAttributeValue("cmaterialoid",
					// pk_material_new);
					// bodyVO.setAttributeValue("cmaterialvid",
					// pk_material_new);

					bodyVO.setCastunitid(puinbodyVO.getAttributeValue(
							"castunitid").toString());
					bodyVO.setCunitid(puinbodyVO.getAttributeValue("cunitid")
							.toString());

					// 20170525 1.其他入库单数量=负的接口中的调整数 2.干基数*不同的业务单元的调整系数作为干基数
					UFDouble nnumjs = lhbvo.getIqty();
					UFDouble nnumadj = lhbvo.getIqtyadj();// TO-DO
					UFDouble fadjustrate = new UFDouble(1);
					UFDouble nnumin = new UFDouble(0);
					String crate = mapOrgVOsIn.get(
							puinvo.getChildrenVO()[0].getAttributeValue(
									"creqstoorgoid").toString()).getDef2();// 170624
																			// 调整
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
					bodyVO.setVbdef2(lhbvo.getVbillnodz());//170917 传递大宗结算单号
					alBodyVOs.add(bodyVO);

					// //重新计算入库单上的金额,解决接口生成的入库单，在生成发票时不能带入金额的问题
					// UFDouble nTaxPrice = lhbvo.getFprice();// 含税单价
					// // 税率
					// UFDouble nTaxRate = (UFDouble)
					// puinbodyVO.getAttributeValue("ntaxrate");
					//
					// UFDouble nPrice = nTaxPrice.div(nTaxRate
					// .add(new UFDouble(1)));// 无税单价
					//
					// UFDouble nMny = (nnum.multiply(nPrice)).setScale(2,
					// UFDouble.ROUND_UP);// 无税金额
					// UFDouble nTaxMny = (nnum.multiply(nTaxPrice)).setScale(2,
					// UFDouble.ROUND_UP);// 含税金额
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
			throw new BusinessException("生成其他入库单遇到异常：" + err.getMessage());
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
			throw new BusinessException("获取库存组织编码异常！");
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
			throw new BusinessException("根据库存组织编码无法正确匹配到相应的成本域！");
		}

		BilltypeVO[] billTypeVo56 = (BilltypeVO[]) baseDao.retrieveByClause(
				BilltypeVO.class,
				" pk_billtypecode='45-Cxx-56' and pk_group='"
						+ swapContext.getPk_group() + "'").toArray(
				new BilltypeVO[0]);
		if ((billTypeVo56 == null) || (billTypeVo56.length < 1)) {
			throw new BusinessException("未查询到入库调整单的默认出入库类型[45-Cxx-56]！");
		}

		Map<String, String> mapAccBookByOrgPk = NCLocator
				.getInstance()
				.lookup(IAccountingBookPubService.class)
				.queryAccountingBookIDByFinanceOrgIDWithMainAccountBook(
						hsOrgPks.toArray(new String[0]));
		if ((mapAccBookByOrgPk == null) || (mapAccBookByOrgPk.size() < 1)
				|| (hsOrgPks.size() > mapAccBookByOrgPk.size())) {
			throw new BusinessException("库存组织对应的默认财务核算账簿存在异常，请核对！");
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
			headvo.setCreator(cuserid);// 17081714 gwj 调整
			headvo.setCreationtime(createTime);
			headvo.setBillmaker(cuserid);// 170817 gwj 调整
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
			// 单据头自定义项1记录来源单据主键
			headvo.setVdef4(aggLhVo.getParentVO().getBpresettle());// 单据头自定义项4记录是否预结算标志
			headvo.setVdef11(puinvo.getParentVO().getAttributeValue("vdef11") == null ? "~"
					: puinvo.getParentVO().getAttributeValue("vdef11")
							.toString());
			headvo.setCsrcmodulecode(NCModule.OT.getSystemCode());// 若来源系统非外部系统，则生成的单据制单人会默认取当前登录人BaseInsertDataFillRule.fillMakeInfo

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
								"生成入库调整单异常：公户属性时组织的自定义项3、自定义项4作为除数不能为0");
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
				// .getDef5();// 170814 生成的入库单物料需要映射为物料档案自定义项5的值
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
			throw new BusinessException("外系统单据主键不允许为空！");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_saleorder())) {
			throw new BusinessException("NC采购订单主键不允许为空！");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getWhcode())) {
			throw new BusinessException("仓库编码不允许为空！");
		}
		if (aggVO.getParentVO().getDdate() == null) {
			throw new BusinessException("结算日期不允许为空！");
		}
		// if(!"Y".equals(aggVO.getParentVO().getBpresettle()) &&
		// !"N".equals(aggVO.getParentVO().getBpresettle())){
		// throw new BusinessException("预结算标志只能为Y或N！");//预结算标志必须为Y或N
		// }
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getBpresettle())) {
			bPresettle = false;
			aggVO.getParentVO().setBpresettle("N");// 预结算标志若为空，则默认为N
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
				throw new BusinessException("NC采购订单子表主键不允许为空！");
			}
			if (bvo.getMatcode() == null
					|| StringUtil.isEmptyWithTrim(bvo.getMatcode())) {
				throw new BusinessException("产品编码不允许为空！");
			}
			if (bvo.getIqty() == null
					|| bvo.getIqty().equals(UFDouble.ZERO_DBL)) {
				throw new BusinessException("结算数量不允许为0！");
			}
			if (bvo.getIqtyadj() == null) {
				// throw new BusinessException("原发数量不允许为空！");
				bvo.setIqtyadj(UFDouble.ZERO_DBL);
			}
			if (bvo.getFtransmoney() == null) {
				bvo.setFtransmoney(UFDouble.ZERO_DBL);
			}
			// if (bPresettle == false && bvo.getFprice() == null) {
			// throw new BusinessException("结算价格不允许为空！");
			// }
			// hsMatCodes.add(bvo.getAttributeValue("matcode").toString());
		}
	}
}
