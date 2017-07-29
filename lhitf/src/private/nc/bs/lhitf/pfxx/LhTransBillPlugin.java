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
			throw new BusinessException("外部系统配置异常！");
		}
		
		AggLhTransBillVO aggVO = (AggLhTransBillVO) vo;
		String retStr = "";
		checkData(aggVO);
		BaseDAO baseDao = new BaseDAO();
		UFDateTime createTime = AppContext.getInstance().getServerTime();
		UFDate dtToday = createTime.getDate();

		// 循环判断物料编码是否存在
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
				throw new BusinessException("以下物料编码在NC系统中不存在："
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
			throw new BusinessException("获取物料税率遇到异常：" + err.getMessage());
		}
		if (alMatTaxRate == null || alMatTaxRate.size() < 1) {
			throw new BusinessException("获取物料税率遇到异常：未查询到物料的税率信息！");
		}
		Map<String, String[]> mapTaxRateByMatPk = new HashMap<String, String[]>();
		for (int i = 0; i < alMatTaxRate.size(); i++) {
			Object[] obj = (Object[]) alMatTaxRate.get(i);
			mapTaxRateByMatPk.put(obj[0].toString(),
					new String[] { obj[1].toString(), obj[2].toString() });
		}

		if ("调拨过磅".equals(aggVO.getParentVO().getTranstype())
				&& !aggVO.getParentVO().getOutorgcode()
						.equals(aggVO.getParentVO().getInorgcode())) {
			// 生成调拨订单，调拨过磅并且调入组织与调出组织不一致
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
			// 判断调出仓库\调入仓库是否正确
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
				throw new BusinessException("调入仓库编码[" + cinwhcode + "]及调出仓库编码["
						+ coutwhcode + "]在NC系统库存组织下不存在！");
			}
			for (StordocVO storvo : whVOs) {
				mapStorDocVOByOrgAndCode.put(
						storvo.getPk_org() + storvo.getCode(), storvo);
			}
			if (!mapStorDocVOByOrgAndCode.containsKey(pk_org_in + cinwhcode)) {
				throw new BusinessException("调入库存组织不存在编码为[" + cinwhcode
						+ "]的仓库！");
			}
			if (!mapStorDocVOByOrgAndCode.containsKey(pk_org_out + coutwhcode)) {
				throw new BusinessException("调出库存组织不存在编码为[" + coutwhcode
						+ "]的仓库！");
			}

			BilltypeVO[] billTypeVo = (BilltypeVO[]) baseDao.retrieveByClause(
					BilltypeVO.class,
					" pk_billtypecode='5X-01' and pk_group='"
							+ swapContext.getPk_group() + "'").toArray(
					new BilltypeVO[0]);
			if (billTypeVo == null || billTypeVo.length < 1) {
				throw new BusinessException("未查询到调拨订单的默认订单类型[5X-01]！");
			}

			BillVO billVO = new BillVO();
			BillHeaderVO headvo = new BillHeaderVO();
			headvo.setDbilldate(dtToday);
			headvo.setPk_group(swapContext.getPk_group());
			headvo.setPk_org(pk_org_out);
			headvo.setPk_org_v(mapOrgVOs.get(pk_org_out).getPk_vid());
			// headvo.setBioreverseflag(new UFBoolean(false));// 反向结算
			// headvo.setBotreverseflag(new UFBoolean(false));// 调出出货反向结算
			// headvo.setFmodeflag(Integer.valueOf(2));// 调拨方式
			// // 1=三方调拨，2=财务组织间调拨，3=财务组织内库存组织间调拨，4=财务组织内库存组织内调拨
			headvo.setBlatestflag(new UFBoolean(true));// 最新版本
			headvo.setBtriatradeflag(new UFBoolean(false));// 三角贸易
			headvo.setBunilateralflag(new UFBoolean(false));// 单边结算
			headvo.setCbiztypeid("0001A610000000003KIH");// 业务流程bd_busitype
			headvo.setCcurrencyid("1002Z0100000000001K1");// 币种
			headvo.setCincountryid("0001Z010000000079UJJ");// 调入国家/地区
			headvo.setCinfinanceorgid(pk_org_in);// 调入财务组织最新版本
			headvo.setCinfinanceorgvid(mapOrgVOs.get(pk_org_in).getPk_vid());//
			headvo.setCinstockorgid(pk_org_in);// 调入库存组织最新版本
			headvo.setCinstockorgvid(mapOrgVOs.get(pk_org_in).getPk_vid());
			// headvo.setConwayownerorgid(mapOrgVOs.get(pk_org_out).getPk_vid());//
			// 在途归属
			// 组织版本
			headvo.setCorigcurrencyid("1002Z0100000000001K1");// 原币币种
			headvo.setCoutcountryid("0001Z010000000079UJJ");// 调出国家/地区
			headvo.setCoutfinanceorgid(pk_org_out);//
			headvo.setCoutfinanceorgvid(mapOrgVOs.get(pk_org_out).getPk_vid());//
			headvo.setCsettlepathid(null);// 结算路径
			headvo.setCtaxcountryid("0001Z010000000079UJJ");// 报税国家/地区
			headvo.setCtoutcountryid("0001Z010000000079UJJ");// 出货国家/地区
			headvo.setCtoutfinanceorgid(pk_org_out);// 出货财务组织最新版本
			headvo.setCtoutfinanceorgvid(mapOrgVOs.get(pk_org_out).getPk_vid());
			headvo.setCtoutstockorgid(pk_org_out);// 出货库存组织最新版本
			headvo.setCtoutstockorgvid(mapOrgVOs.get(pk_org_out).getPk_vid());
			headvo.setFbuysellflag(Integer.valueOf(1));// 购销类型
														// 1=国内销售，2=国内采购，3=出口，4=进口，5=不区分
			headvo.setFioonwayownerflag(Integer.valueOf(1));// 调入调出在途归属
															// 1=归调出方，2=归调入方
			headvo.setFotonwayownerflag(Integer.valueOf(1));// 调出出货在途归属
															// 1=归调出方，2=归调入方
			headvo.setFstatusflag(Integer.valueOf(1));// 单据状态
														// 1=自由状态，2=审批中，3=审批未通过，4=审批通过，7=关闭，9=冻结
			headvo.setIversion(Integer.valueOf(1));// 版本
			headvo.setNexchangerate(new UFDouble(1));// 折本汇率
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
				bodyVO.setBarrangeendflag(new UFBoolean(false));// 是否补货安排完毕
				bodyVO.setBiolargessflag(new UFBoolean(false));// 调入调出方赠品
				bodyVO.setBiosettleendflag(new UFBoolean(false));// 调入调出是否结算完成
				bodyVO.setBotlargessflag(new UFBoolean(false));// 调出出货方赠品
				bodyVO.setBotsettleendflag(new UFBoolean(false));// 调出出货是否结算完成
				bodyVO.setBoutendflag(new UFBoolean(false));// 是否出库完成
				bodyVO.setBsendendflag(new UFBoolean(false));// 是否已发货结束
				bodyVO.setCastunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());// 辅计量
				bodyVO.setCinstockorgid(pk_org_in);// 调入库存组织
				bodyVO.setCinventoryid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_material());
				bodyVO.setCinventoryvid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_material());
				// bodyVO.setCiosettlerule_bid("0001A6100000000045BE");//
				// 调入调出结算规则明细
				// bodyVO.setCiosettleruleid("0001A6100000000045B8");// 调入调出结算规则
				bodyVO.setCorigcountryid("0001Z010000000079UJJ");// 原产国
				bodyVO.setCqtunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());// 报价单位
				// bodyVO.setCrowno("");//行号
				bodyVO.setCtaxcodeid((mapTaxRateByMatPk.get(mapMatVOs.get(
						lhbvo.getMatcode()).getPk_material()))[0]);// 税码
				bodyVO.setCtoutstockorgid(pk_org_out);// 出货库存组织
				bodyVO.setCunitid(mapMatVOs.get(lhbvo.getMatcode())
						.getPk_measdoc());// 计量单位
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
						pk_org_in + cinwhcode).getPk_stordoc());// 调入仓库
				bodyVO.setCoutstordocid(mapStorDocVOByOrgAndCode.get(
						pk_org_out + coutwhcode).getPk_stordoc());// 调出仓库
				bodyVO.setVchangerate("1.00/1.00");
				bodyVO.setVqtunitrate("1.00/1.00");
				alBodyVO.add(bodyVO);
			}
			billVO.setParentVO(headvo);
			billVO.setChildrenVO(alBodyVO.toArray(new BillItemVO[alBodyVO
					.size()]));
			BillVO[] billVOs = new BillVO[] { billVO };

			// 如果单据的交易类型不为空，根据交易类型获得调拨类型， 补充反向结算
			this.setTrantypeidIfNotNull(billVOs);

			// 匹配结算规则并补充结算规则信息
			this.matchSettleRule(billVOs);

			// 匹配结算路径
			this.matchSettlePath(billVOs);

			// 补充物料组织
			this.setCdelivorgid(billVOs);

			// 补充数量
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
				throw new BusinessException("生成调拨订单遇到异常：" + err.getMessage());
			}

			// M5xPfxxInsertService service = new M5xPfxxInsertService();
			//
			// BillSaveForOuterUtils util = new BillSaveForOuterUtils();
			// util.fillDefaultValue(new BillVO[] { resvo });
			//
			// return service.insert(resvo);

		} else if ("销售过磅".equals(aggVO.getParentVO().getTranstype())
				|| ("调拨过磅".equals(aggVO.getParentVO().getTranstype()) && aggVO
						.getParentVO().getOutorgcode()
						.equals(aggVO.getParentVO().getInorgcode()))) {
			// 生成转库单，销售过磅或者调入组织与调出组织一致
			String pk_org = aggVO.getParentVO().getOutorgcode();

			OrgVO[] orgs = (OrgVO[]) baseDao.retrieveByClause(OrgVO.class,
					" dr=0 and isbusinessunit='Y' and pk_org='" + pk_org + "'")
					.toArray(new OrgVO[0]);
			String pk_org_v = orgs[0].getPk_vid();

			String pk_warehouse_in = aggVO.getParentVO().getPk_warehouse_in();
			String pk_warehouse_out = aggVO.getParentVO().getPk_warehouse_out();
			// 判断调出仓库\调入仓库是否正确
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
				throw new BusinessException("调入仓库编码[" + pk_warehouse_in
						+ "]及调出仓库编码[" + pk_warehouse_out + "]在NC系统库存组织下不存在！");
			} else if (whVOs.length < 2) {
				if (!mapStorDocVO.containsKey(pk_warehouse_in)) {
					throw new BusinessException("调入仓库编码[" + pk_warehouse_in
							+ "]在NC系统库存组织下不存在！");
				}
				if (!mapStorDocVO.containsKey(pk_warehouse_out)) {
					throw new BusinessException("调出仓库编码[" + pk_warehouse_out
							+ "]在NC系统库存组织下不存在！");
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
				throw new BusinessException("未查询到转库单的默认转库类型[4K-01]！");
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
					.getPk_stordoc());// 入库仓库
			headvo.setCwarehouseid(mapStorDocVOByOrgAndCode.get(
					pk_org + aggVO.getParentVO().getPk_warehouse_out())
					.getPk_stordoc());// 出库仓库
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
				throw new BusinessException("生成转库单遇到异常：" + err.getMessage());
			}

		} else {
			throw new BusinessException("转库类型与调入调出组织不匹配！");
		}

		return retStr;
	}

	protected void checkData(AggLhTransBillVO aggVO) throws BusinessException {
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getPk_id())) {
			throw new BusinessException("外系统单据主键不允许为空！");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getTranstype())) {
			throw new BusinessException("过磅类型不允许为空！");
		}
		if (!"销售过磅".equals(aggVO.getParentVO().getTranstype())
				&& !"调拨过磅".equals(aggVO.getParentVO().getTranstype())) {
			throw new BusinessException("过磅类型["
					+ aggVO.getParentVO().getTranstype()
					+ "]不正确，只能为[销售过磅]或[调拨过磅]!");
		}
		if ("调拨过磅".equals(aggVO.getParentVO().getTranstype())) {
			if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getInorgcode())) {
				throw new BusinessException("调入组织主键不允许为空！");
			}
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO().getOutorgcode())) {
			throw new BusinessException("调出组织主键不允许为空！");
		}
		if (StringUtil
				.isEmptyWithTrim(aggVO.getParentVO().getPk_warehouse_in())) {
			throw new BusinessException("调入仓库不允许为空！");
		}
		if (StringUtil.isEmptyWithTrim(aggVO.getParentVO()
				.getPk_warehouse_out())) {
			throw new BusinessException("调出仓库不允许为空！");
		}
		if (aggVO.getParentVO().getDdate() == null) {
			throw new BusinessException("日期不允许为空！");
		}
		LhTransBillDetailVO[] bvos = aggVO.getChildrenVO();
		// HashSet<String> hsMatCodes = new HashSet<String>();
		for (LhTransBillDetailVO bvo : bvos) {
			if (bvo.getMatcode() == null
					|| StringUtil.isEmptyWithTrim(bvo.getMatcode())) {
				throw new BusinessException("产品编码不允许为空！");
			}
			if (bvo.getIqty() == null || bvo.getIqty().equals(new UFDouble(0))) {
				throw new BusinessException("转库数量不允许为零！");
			}
			// hsMatCodes.add(bvo.getAttributeValue("matcode").toString());
		}
	}

	private void matchSettleRule(BillVO[] bills) {
		for (BillVO bill : bills) {
			// 交易类型为空，不匹配结算规则
			if (PubAppTool.isNull(bill.getParentVO().getVtrantypecode())) {
				return;
			}

			// 匹配结算规则并补充结算规则信息
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
			// 交易类型为空时不匹配，避免表头条件不完整时频繁远程调用
			return;
		}
		List<MatchSettleRuleVO> matchVO = M5XVOBusiRuleUtil
				.getIOMatchSettleRuleVO(bill);
		// 调用内部结算规则组件服务询内部结算规则
		Map<Integer, SettleRuleAggVO> map = this.getSettleRule(matchVO);
		// 设置调入调出内部结算规则相关字段
		this.setIOSettleRule(bill, map);
		// 调入调出在途归属改变，影响在途归属
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
		// 调入调出在途归属、调入调出结算规则主表主键、调入调出结算规则子表主键、计税类别、价格规则、税率、加价率。
		// 未询到时清空以上字段setIOSettleRule()，“库存组织间调拨”“库存组织内调拨”调出调入在途归属默认为“调出方”。
		BillItemVO[] itemVOs = bill.getChildrenVO();
		int len = itemVOs.length;
		for (int i = 0; i < len; i++) {
			SettleRuleAggVO settleRuleVO = map.get(Integer.valueOf(i));
			if (null == settleRuleVO) {
				String[] items = new String[] { BillItemVO.CIOSETTLERULEID,
						BillItemVO.CIOSETTLERULE_BID, BillItemVO.FTAXTYPEFLAG,
						BillItemVO.NADDPRICERATE, BillItemVO.NTAXRATE };
				this.clearRowValueByItemKeys(itemVOs[i], items);
				// 不从新设置在途归属，确保用户修改后的数据不被改动
			} else {
				// 匹配到的结算规则，只有一条表体行
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
			// 交易类型为空时不匹配，避免表头条件不完整时频繁远程调用
			return;
		}
		List<MatchSettleRuleVO> matchVO = M5XVOBusiRuleUtil
				.getOTMatchSettleRuleVO(bill);
		// 调用内部结算规则组件服务询内部结算规则
		Map<Integer, SettleRuleAggVO> map = this.getSettleRule(matchVO);
		// 设置调出出货内部结算规则相关字段到界面上
		this.setOTSettleRule(bill, map);
		// 调出出货在途归属改变，影响在途归属
		M5XVOBusiRuleUtil.calculateOnWayOnwer(bill);
	}

	private void setOTSettleRule(BillVO bill, Map<Integer, SettleRuleAggVO> map) {
		BillItemVO[] itemVOs = bill.getChildrenVO();
		int len = itemVOs.length;
		// 包括调出出货在途归属、调出出货结算规则主表主键、调出出货结算规则子表主键。未询到时清空以上字段setOTSettleRule()。
		for (int i = 0; i < len; i++) {
			SettleRuleAggVO settleRuleVO = map.get(Integer.valueOf(i));
			if (null == settleRuleVO) {
				String[] items = new String[] { BillItemVO.COTSETTLERULEID,
						BillItemVO.COTSETTLERULE_BID };
				this.clearRowValueByItemKeys(itemVOs[i], items);
				// 不从新设置在途归属，确保用户修改后的数据不被改动
			} else {
				// 匹配到的结算规则，只有一条表体行
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
		// 取物流组织的版本
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
				// 根据主数量除以换算率得到数量
				ScaleUtils scale = new ScaleUtils(pk_group);
				String changerate = item.getVchangerate();

				changerate = scale.adjustHslScale(changerate);

				String[] changerates = changerate.split("/");

				UFDouble nastnum = item.getNnum().div(
						new UFDouble(changerates[0]).div(new UFDouble(
								changerates[1])));
				nastnum = scale.adjustNumScale(nastnum, item.getCastunitid());

				// 原单据VO上没有设置数量，则数量为主数量除以换算率
				if (item.getNastnum() == null
						|| item.getNastnum().equals(UFDouble.ZERO_DBL)) {
					item.setNastnum(nastnum);
				}
				// 单据VO上的数量和计算结果不相等，取计算结果
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
