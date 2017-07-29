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
			throw new BusinessException("外部系统配置异常！");
		}
		
		AggLhSaleOrderVO aggVO = (AggLhSaleOrderVO) vo;
		checkData(aggVO);

		String csettlepathname = aggVO.getParentVO().getSettleroute();
		String csettlepathid = "";
		if (!StringUtil.isEmptyWithTrim(csettlepathname)) {
			DataAccessUtils tool = new DataAccessUtils();
			// 判断结算路径名称是否包含"-"
			if (!StringUtil.hasText("-")) {
				// 查询该名称对应的组织作为销售组织
				SqlBuilder sqlWhere = new SqlBuilder();
				sqlWhere.append(
						"select pk_org from org_salesorg where islastversion='Y' and dr=0 and shortname",
						csettlepathname);
				IRowSet rowset = tool.query(sqlWhere.toString());
				String[] pk_saleorgs = rowset.toOneDimensionStringArray();
				if ((pk_saleorgs == null) || (pk_saleorgs.length == 0)) {
					throw new BusinessException("传入NC的结算路径名称["
							+ csettlepathname + "]作为销售组织简称时，不存在该销售组织！");
				} else if (pk_saleorgs.length > 1) {
					throw new BusinessException("传入NC的结算路径名称["
							+ csettlepathname
							+ "]作为销售组织简称时，在NC系统中存在多个该简称的销售组织！");
				}
				aggVO.getParentVO().setPk_saleorg(pk_saleorgs[0]);
				aggVO.getParentVO().setSettleroute("");

			} else {
				// 查询结算路径

				SqlBuilder sqlWhere = new SqlBuilder();
				sqlWhere.append(
						"select csettlepathid from to_settlepath where dr=0 and blatestflag='Y' and vPathName",
						csettlepathname);
				IRowSet rowset = tool.query(sqlWhere.toString());

				String[] csettlepathids = rowset.toOneDimensionStringArray();
				if ((csettlepathids == null) || (csettlepathids.length == 0)) {
					throw new BusinessException("传入NC的结算路径名称["
							+ csettlepathname + "]不存在！");
				} else if (csettlepathids.length > 1) {
					throw new BusinessException("传入NC的结算路径名称["
							+ csettlepathname + "]在NC系统中不唯一！");
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
		// 结算路径
		// 若销售组织与发货库存组织不一致，则需要
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
			throw new BusinessException("保存销售订单结算路径遇到异常！" + err.getMessage());
		}

		return SaleOrderVOs[0].getParentVO().getCsaleorderid() + "|"
				+ SaleOrderVOs[0].getParentVO().getVbillcode();
	}

	protected void checkData(AggLhSaleOrderVO aggVO) throws BusinessException {
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_id())) {
			throw new BusinessException("外系统单据主键不允许为空！");
		}
		// if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_saleorg()))
		// {
		// throw new BusinessException("销售组织编码不允许为空！");
		// }
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getCustcode())) {
			throw new BusinessException("客户编码不允许为空！");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getCttype())) {
			throw new BusinessException("合同类型不允许为空！");
		}
		if (aggVO.getParentVO().getCtdate() == null) {
			throw new BusinessException("合同日期不允许为空！");
		}
		LhSaleOrderDetailVO[] bvos = aggVO.getChildrenVO();
		// HashSet<String> hsMatCodes = new HashSet<String>();
		for (LhSaleOrderDetailVO bvo : bvos) {
			if (StringUtil.isEmptyWithTrim(bvo.getPk_storg())) {
				throw new BusinessException("表体组织编码不允许为空！");
			}
			if (StringUtil.isEmptyWithTrim(bvo.getWhcode())) {
				throw new BusinessException("表体仓库编码不允许为空！");
			}
			if (StringUtil.isEmptyWithTrim(bvo.getMatcode())) {
				throw new BusinessException("产品编码不允许为空！");
			}
			if (bvo.getIqty() == null || bvo.getIqty().equals(new UFDouble(0))) {
				throw new BusinessException("数量不允许为0！");
			}
			if (bvo.getFtaxmoney() == null
					|| bvo.getFtaxmoney().equals(new UFDouble(0))) {
				throw new BusinessException("金额不允许为0！");
			}
			// hsMatCodes.add(bvo.getAttributeValue("matcode").toString());
		}

	}

	private SaleOrderExternalVO turnToSaleOrderExternalVO(
			AggLhSaleOrderVO resvo, ISwapContext swapContext)
			throws BusinessException {

		BaseDAO baseDao = new BaseDAO();
		// 判断pk_id是否已经存在对应的单据??

		// 判断销售组织编码是否存在
		// 判断组织编码是否存在

		UFDate dtToday = AppContext.getInstance().getServerTime().getDate();

		HashSet<String> hsOrgPk = new HashSet<String>();
		HashSet<String> hsDeliverOrgPk = new HashSet<String>();// 发货库存组织
		HashSet<String> hsDeliverWhCode = new HashSet<String>();// 发货仓库
		HashSet<String> hsMatCodes = new HashSet<String>();// 物料编码
		// hsOrgCode.add(resvo.getParentVO().getPk_saleorg());
		for (LhSaleOrderDetailVO bvo : resvo.getChildrenVO()) {
			hsOrgPk.add(bvo.getPk_storg());
			hsDeliverOrgPk.add(bvo.getPk_storg());
			hsDeliverWhCode.add(bvo.getWhcode());
			hsMatCodes.add(bvo.getMatcode());
		}
		if (hsDeliverOrgPk.size() > 1) {
			throw new BusinessException("传入NC的销售订单表体必须是同一个发货库存组织！");
		}

		String condition = "";
		condition = SqlUtil.buildSqlForIn("pk_org",
				hsOrgPk.toArray(new String[0]));
		OrgVO[] orgs = (OrgVO[]) baseDao.retrieveByClause(OrgVO.class,
				" dr=0 and isbusinessunit='Y' and " + condition).toArray(
				new OrgVO[0]);

		if (orgs == null || orgs.length < 1) {
			throw new BusinessException("传入的组织主键在NC中不存在！"
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
			throw new BusinessException("传入的组织主键在NC中不存在！"
					+ hsOrgPkErr.toString());
		}

		String pk_org_v = "";
		String pk_org = "";
		SettlePathVO settlepathVO = null;
		if (StringUtil.isEmptyWithTrim(resvo.getParentVO().getSettleroute())) {// 结算路径为空的话
			if (StringUtil.isEmptyWithTrim(resvo.getParentVO().getPk_saleorg())) {// 表头销售组织为空的话,取表体发货组织作为销售组织
				pk_org_v = mapOrgsByPk.get(
						resvo.getChildrenVO()[0].getPk_storg()).getPk_vid();
				pk_org = mapOrgsByPk.get(
						resvo.getChildrenVO()[0].getPk_storg()).getPk_org();
			} else {//销售组织不为空的话，取表头销售组织
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

		// 判断客户编码是否存在
		CustomerVO[] custVO = null;
		custVO = (CustomerVO[]) baseDao.retrieveByClause(
				CustomerVO.class,
				" code='" + resvo.getParentVO().getCustcode()
						+ "' and enablestate=2 ").toArray(new CustomerVO[0]);
		if (custVO == null || custVO.length == 0) {
			throw new BusinessException("客户编码"
					+ resvo.getParentVO().getCustcode() + "在NC系统中不存在！");
		}
		String pk_customer = custVO[0].getPk_customer();
		// 取出客户档案中对应销售组织的部门
		CustsaleVO[] custSaleVOs = (CustsaleVO[]) baseDao.retrieveByClause(
				CustsaleVO.class,
				" dr=0 and pk_customer='" + pk_customer + "' and pk_org='"
						+ pk_org + "' ").toArray(new CustsaleVO[0]);
		if (custSaleVOs == null || custSaleVOs.length < 1) {
			throw new BusinessException("客户["
					+ resvo.getParentVO().getCustcode() + "]未设置默认的销售组织专管部门！");
		}
		String pk_dept = "";
		if (StringUtil.isEmptyWithTrim(custSaleVOs[0].getRespdept())) {
			throw new BusinessException("客户["
					+ resvo.getParentVO().getCustcode() + "]未设置默认的销售组织专管部门！");
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
		// throw new BusinessException("客户["
		// + resvo.getParentVO().getCustcode() + "]未设置默认的销售组织部门！");
		// }

		// String pk_dept_v="";

		// 判断仓库编码是否存在
		condition = SqlUtil.buildSqlForIn("code",
				hsDeliverWhCode.toArray(new String[hsDeliverWhCode.size()]));
		StordocVO[] whVOs = NCLocator.getInstance()
				.lookup(IStordocQueryService.class)
				.queryStordocByCondition(pk_orgs, " dr=0 and " + condition);
		if (whVOs == null || whVOs.length < 1) {
			throw new BusinessException("仓库编码在NC系统中不存在！"
					+ hsDeliverWhCode.toString());
		}
		Map<String, StordocVO> mapStorDocByOrgStor = new HashMap<String, StordocVO>();
		for (StordocVO whvo : whVOs) {
			mapStorDocByOrgStor.put(whvo.getPk_org()
					+ whvo.getCode(), whvo);// 组织主键+仓库编码作为线索
		}

		// 判断合同类型名称是否合法
		// if (resvo.getParentVO().getCttype().equals("现货")) {
		//
		// } else if (resvo.getParentVO().getCttype().equals("期货")) {
		//
		// }
		// 循环判断物料编码是否存在

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
				throw new BusinessException("以下物料编码在NC系统中不存在："
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
			throw new BusinessException("获取物料税率遇到异常：" + err.getMessage());
		}
		if (alMatTaxRate == null || alMatTaxRate.size() < 1) {
			throw new BusinessException("获取物料税率遇到异常：未查询到物料的税率信息！");
		}
		Map<String, UFDouble> mapTaxRateByMatPk = new HashMap<String, UFDouble>();
		for (int i = 0; i < alMatTaxRate.size(); i++) {
			Object[] obj = (Object[]) alMatTaxRate.get(i);
			mapTaxRateByMatPk.put(obj[0].toString(),
					new UFDouble(obj[1].toString()));
		}

		// // 取物料税率信息
		// ArrayList<VATInfoQueryVO> alQueryVOs = new
		// ArrayList<VATInfoQueryVO>();
		// String sendcnty = null;// 发货国
		// String rececnty = "0001Z010000000079UJJ";// 收货国
		// String taxcnty = "0001Z010000000079UJJ";// 报税国
		// String pk_supplier = null;// 供应商
		// UFDate bizdate = dtToday;
		// // Integer bsflag = 1;// 购销类型
		// // BuySellFlagEnum bsflagenum = null == bsflag ? null :
		// BuySellFlagEnum
		// // .valueOf(bsflag);
		// BuySellFlagEnum bsflagenum = BuySellFlagEnum.NATIONAL_BUY;
		// UFBoolean btriatrade = new UFBoolean(false);// 三角贸易
		// for (Map.Entry<String, MaterialVO> entry : mapMatVOs.entrySet()) {
		//
		// String pk_material = entry.getValue().getPk_material();// 物料
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
		// throw new BusinessException("取物料税率遇到异常：" + e.getMessage());
		// }
		// if (vatinfos == null || vatinfos.length < 1) {
		// throw new BusinessException("取物料税率遇到异常：未查询到物料的税率信息！");
		// }
		// for (VATInfoVO vatinfo : vatinfos) {
		// mapTaxTateByTaxCodePK.put(vatinfo.getCtaxcodeid(),
		// vatinfo.getNtaxrate());
		// }
		
		//获取业务属性值
		
		
		String cbusiproperty=resvo.getParentVO().getCbusiproperty();
		String cbusipropertypk="";
		if(cbusiproperty.equals("1")){
			DefdocVO[] defdocVOs=(DefdocVO[]) baseDao.retrieveByClause(DefdocVO.class, " (name ='公户' ) and dr=0 and enablestate=2 ").toArray(new DefdocVO[0]);
			if(defdocVOs!=null && defdocVOs.length>0)
				cbusipropertypk=defdocVOs[0].getPk_defdoc();
		}else if (cbusiproperty.equals("2")){
			DefdocVO[] defdocVOs=(DefdocVO[]) baseDao.retrieveByClause(DefdocVO.class, " (name ='私户' ) and dr=0 and enablestate=2 ").toArray(new DefdocVO[0]);
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
			throw new BusinessException("未查询到销售订单的默认订单类型[30-Cxx-01]！");
		}

		headVO.setPk_org(pk_org);
		headVO.setCtrantypeid(billTypeVo[0].getPk_billtypeid());// 订单类型
		headVO.setDbilldate(dtToday);
		headVO.setCcustomerid(pk_customer);
		headVO.setCdeptvid(pk_dept_v);
		headVO.setCorigcurrencyid("1002Z0100000000001K1");// 币种
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
		// // 设置是否进行本币换算
		// cond.setIsCalLocalCurr(false);
		// // 设置调单价方式调折扣
		// cond.setIsChgPriceOrDiscount(false);
		// // 设置是否固定单位换算率
		// cond.setIsFixNchangerate(false);
		// // 是否固定报价单位换算率
		// cond.setIsFixNqtunitrate(false);
		// // 设置含税优先还是无税优先
		// cond.setIsTaxOrNet(true);
		// // 是否报价单位优先
		// cond.setUnitPriorType(0);
		// // 是否计算集团本位币金额
		// cond.setGroupLocalCurrencyEnable(true);
		// // 是否计算全局本位币金额
		// cond.setGlobalLocalCurrencyEnable(true);
		// // 是否折算本位币
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
			bodyVO.setNatxrate(nTaxRate);// 取税率
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
