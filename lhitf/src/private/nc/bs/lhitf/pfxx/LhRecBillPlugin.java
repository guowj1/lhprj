package nc.bs.lhitf.pfxx;

import nc.bs.arap.util.ArapFlowUtil;
import nc.bs.arap.util.IArapBillTypeCons;
import nc.bs.framework.common.NCLocator;
import nc.bs.pf.pub.PfDataCache;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.fi.pub.Currency;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.accperiod.AccountCalendar;
import nc.pubitf.arap.bill.IArapBillPubService;
import nc.vo.arap.basebill.BaseItemVO;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.arap.pub.ArapConstant;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.arap.utils.ArrayUtil;
import nc.vo.bd.balatype.BalaTypeVO;
import nc.vo.bd.bankaccount.BankAccSubVO;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.bd.inoutbusiclass.InoutBusiClassVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhrecbill.LhRecBillVO;
import nc.vo.org.OrgVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.pubapp.util.NCPfServiceUtils;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class LhRecBillPlugin extends AbstractPfxxPlugin {

	private IMDPersistenceQueryService mdQueryService;
	private PfUserObject[] userObjs;

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {
		if (vo == null) {
			return null;
		}

		if (AppContext.getInstance().getServerTime()
				.after(new UFDate("2018-01-01"))) {
			throw new BusinessException("外部系统配置异常！");
		}
		String retPk = "";
		LhRecBillVO lhRecVo = (LhRecBillVO) vo;

		String pk_org = lhRecVo.getOrgcode();
		String pk_id = lhRecVo.getPkid();
		String custCode = lhRecVo.getCustcode();
		String custSubCode = lhRecVo.getCustsubcode();
		String busiType = lhRecVo.getBusitype();
		String busiProperty = lhRecVo.getBusiproperty();
		String subjCode = lhRecVo.getSubjcode();
		String balaType = lhRecVo.getBalatype();
		String recAccount = lhRecVo.getRecaccount();
		String clueNo = lhRecVo.getClueno();
		UFDouble nmoney = lhRecVo.getNmoney();

		// 判断客户编码是否合法
		CustomerVO[] custVO = (CustomerVO[]) getMdQueryService()
				.queryBillOfVOByCond(CustomerVO.class,
						" dr=0 and code='" + custCode + "'", true).toArray(
						new CustomerVO[0]);
		if (custVO == null || custVO.length < 1) {
			throw new BusinessException("客户编码" + custCode + "在NC系统中不存在！");
		}
		// String pk_customer = custVO[0].getPk_customer();
		lhRecVo.setCustcode(custVO[0].getPk_customer());
		// 判断客户子户编码是否合法
		// String pk_customersub="";
		//170629 调整为此字段存储客户名称
//		if (!StringUtil.isEmptyWithTrim(custSubCode)) {
//			DefdocVO[] custSubVO = (DefdocVO[]) getMdQueryService()
//					.queryBillOfVOByCond(
//							DefdocVO.class,
//							" dr=0 and code='"
//									+ custSubCode
//									+ "' and pk_defdoclist=(select pk_defdoclist from bd_defdoclist where code='zdyx018')",
//							true).toArray(new DefdocVO[0]);
//			if (custSubVO == null || custSubVO.length < 1) {
//				throw new BusinessException("客户子账户编码" + custSubCode
//						+ "在NC系统中不存在！");
//			}
//			// pk_customersub=custSubVO[0].getPk_defdoc();
//			lhRecVo.setCustsubcode(custSubVO[0].getPk_defdoc());
//		}
		// String pk_busyitype="GLOBZ300000000000001";//收款业务类型 默认为001 货款 全局档案
		lhRecVo.setBusitype("GLOBZ300000000000001");
		// 判断业务属性是否合法
		// String pk_busiproperty="";
		if ("01".equals(busiProperty)) {
			// pk_busiproperty="1001A6100000000000XU";//公户
			lhRecVo.setBusiproperty("1001A6100000000000XU");
		} else if ("02".equals(busiProperty)) {
			// pk_busiproperty="1001A6100000000000XW";//私户
			lhRecVo.setBusiproperty("1001A6100000000000XW");
		} else {
			throw new BusinessException("业务属性只能为01或02，不能为" + busiProperty);
		}
		// 判断收支项目是否合法
		// String pk_subjcode="";
		if (!StringUtil.isEmptyWithTrim(subjCode)) {
			InoutBusiClassVO[] subjVo = (InoutBusiClassVO[]) getMdQueryService()
					.queryBillOfVOByCond(
							InoutBusiClassVO.class,
							" dr=0 and enablestate=2 and code='"+subjCode+"' and pk_org='" + pk_org
									+ "'", true).toArray(
							new InoutBusiClassVO[0]);
			if (subjVo == null || subjVo.length < 1) {
				throw new BusinessException("收支项目编码" + subjCode + ",在NC系统中不存在！");
			}
			// pk_subjcode=subjVo[0].getPk_inoutbusiclass();
			lhRecVo.setSubjcode(subjVo[0].getPk_inoutbusiclass());
		}
		// 判断结算方式是否合法
		// String pk_balatype="";
		UFBoolean bIsGatheringBill = new UFBoolean(true);
		if (!StringUtil.isEmptyWithTrim(balaType)) {
			BalaTypeVO[] balaVO = (BalaTypeVO[]) getMdQueryService()
					.queryBillOfVOByCond(
							BalaTypeVO.class,
							" dr=0 and pk_org='GLOBLE00000000000000' and enablestate=2 and code='"
									+ balaType + "'", true).toArray(
							new BalaTypeVO[0]);
			if (balaVO == null || balaVO.length < 1) {
				throw new BusinessException("结算方式编码" + balaType + ",在NC系统中不存在！");
			}
			// pk_balatype=balaVO[0].getPk_balatype();
			lhRecVo.setBalatype(balaVO[0].getPk_balatype());
			if (balaType.equals("6")) {
				bIsGatheringBill = UFBoolean.FALSE;
			}
		}
		// 判断收款银行帐号是否合法
		// String pk_bankaccsub="";
		if (!StringUtil.isEmptyWithTrim(recAccount)) {
			BankAccSubVO[] bankVO = (BankAccSubVO[]) getMdQueryService()
					.queryBillOfVOByCond(BankAccSubVO.class,
							" dr=0 and accnum='" + recAccount + "'", true)
					.toArray(new BankAccSubVO[0]);
			if (bankVO == null || bankVO.length < 1) {
				throw new BusinessException("收款银行帐号" + recAccount
						+ ",在NC系统中不存在！");
			}
			// pk_bankaccsub=bankVO[0].getPk_bankaccsub();
			lhRecVo.setRecaccount(bankVO[0].getPk_bankaccsub());
		}

		// 判断电商单据主键是否已经存在
		// BillQuery<AggGatheringBillVO> billQry=new
		// BillQuery<AggGatheringBillVO>(AggGatheringBillVO.class);
		// billQry.query(keys)
		AggGatheringBillVO[] aggGatherBillVoOld = null;
		if (bIsGatheringBill.equals(UFBoolean.TRUE))
			aggGatherBillVoOld = (AggGatheringBillVO[]) getMdQueryService()
					.queryBillOfVOByCond(
							AggGatheringBillVO.class,
							" dr=0 and def1='" + pk_id
									+ "' and def5='gatherbill' ", true)
					.toArray(new AggGatheringBillVO[0]);
		else
			aggGatherBillVoOld = (AggGatheringBillVO[]) getMdQueryService()
					.queryBillOfVOByCond(
							AggGatheringBillVO.class,
							" dr=0 and def1='" + pk_id
									+ "' and def5='bankbill' ", true).toArray(
							new AggGatheringBillVO[0]);
		if (aggGatherBillVoOld == null || aggGatherBillVoOld.length < 1) {
			// 新增一张收款单
			retPk = createNewVoucher(lhRecVo, swapContext.getPk_group(),
					bIsGatheringBill);

		} else {
			// 判断单据状态是否允许删除
			if (!aggGatherBillVoOld[0].getHeadVO().getApprovestatus()
					.equals(Integer.valueOf(3))
					|| !aggGatherBillVoOld[0].getHeadVO().getBillstatus()
							.equals(Integer.valueOf(-1))) {
				throw new BusinessException("收款单" + pk_id + "在NC系统已经生效，不允许再修改！");
			}
			// 删除对应收款单
			NCLocator.getInstance().lookup(IArapBillPubService.class)
					.delete(aggGatherBillVoOld);
			// 新增一张收款单
			retPk = createNewVoucher(lhRecVo, swapContext.getPk_group(),
					bIsGatheringBill);
		}
		return retPk;
	}

	protected void checkData(LhRecBillVO vo) throws BusinessException {
		if (StringUtil.isEmpty(vo.getPkid())) {
			throw new BusinessException("电商单据主键不允许为空！");
		}
		if (StringUtil.isEmpty(vo.getOrgcode())) {
			throw new BusinessException("财务组织编码不允许为空！");
		}

		if (StringUtil.isEmpty(vo.getCustcode())) {
			throw new BusinessException("客户编码不允许为空！");
		}
		if (StringUtil.isEmpty(vo.getBusiproperty())) {
			throw new BusinessException("业务属性不允许为空！");
		}
		// if (StringUtil.isEmpty(vo.getCustclasscode())) {
		// throw new BusinessException("客户分类编码不允许为空！");
		// }
		// if (StringUtil.isEmpty(vo.getCustproperty())) {
		// throw new BusinessException("客户属性不允许为空！");
		// }
	}

	private String createNewVoucher(LhRecBillVO lhRecVo, String pk_group,
			UFBoolean bIsGatherBill) throws BusinessException {
		UFDate dtDate = AppContext.getInstance().getServerTime().getDate();

		OrgVO[] orgVO = (OrgVO[]) getMdQueryService().queryBillOfVOByCond(
				OrgVO.class, " pk_org='" + lhRecVo.getOrgcode() + "' ", true)
				.toArray(new OrgVO[0]);
		String pk_org_v = orgVO[0].getPk_vid();

		AggGatheringBillVO aggVO = new AggGatheringBillVO();
		GatheringBillVO head = new GatheringBillVO();
		head.setPk_org(lhRecVo.getOrgcode());
		head.setPk_org_v(pk_org_v);
		head.setPk_group(pk_group);
		head.setBilldate(dtDate);
		head.setPk_tradetype("D2");
		head.setPk_billtype("F2");
		head.setBillclass("sk");
		head.setBillmaker(INCSystemUserConst.NC_USER_PK);
		head.setDef1(lhRecVo.getPkid());
		head.setDef2(lhRecVo.getCustsubcode());
		head.setDef3(lhRecVo.getClueno());
		if (bIsGatherBill.equals(UFBoolean.TRUE))
			head.setDef5("gatherbill");
		else
			head.setDef5("bankbill");

			head.setDef11(lhRecVo.getBusiproperty());
		head.setIsinit(UFBoolean.FALSE);
		head.setPk_currtype("1002Z0100000000001K1");
		head.setPk_fiorg(lhRecVo.getOrgcode());
		head.setPk_fiorg_v(pk_org_v);
		head.setSett_org(lhRecVo.getOrgcode());
		head.setSett_org_v(pk_org_v);
		head.setSyscode(Integer.valueOf(0));
		setHeaderDefault(head);

		// 设置业务流程
		head.setCreator(INCSystemUserConst.NC_USER_PK);
		head.setPk_busitype("0001A6100000000008VZ");// 业务流程
		// try {
		// IPFConfig ipf = NCLocator.getInstance().lookup(IPFConfig.class);
		// if (!StringUtils.isEmpty(head.getPk_billtype())
		// && !StringUtils.isEmpty(head.getPk_tradetype())) {
		// if (head.getCreator() == null) {
		// head.setCreator(InvocationInfoProxy.getInstance()
		// .getUserId());
		// }
		// String pk_busitype = ipf.retBusitypeCanStart(
		// head.getPk_billtype(), head.getPk_tradetype(),
		// head.getPk_org(), head.getCreator());
		// if (pk_busitype == null) {
		// throw new BusinessException("busitype is null");
		// }
		// head.setPk_busitype(pk_busitype);
		// }
		// } catch (Exception e) {
		// String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
		// "2006pub_0", "02006pub-0127")/* @res "交易类型" */
		// + head.getPk_tradetype()
		// + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
		// "2006pub_0", "02006pub-0239")/*
		// * @res
		// * "没有找到相应的流程,请在[业务流定义]配置"
		// */
		// + head.getPk_tradetype()
		// + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
		// "2006pub_0", "02006pub-0240")/* @res "自制流程" */;
		// throw new BusinessRuntimeException(msg);
		// }

		GatheringBillItemVO body = new GatheringBillItemVO();
		body.setPk_org(head.getPk_org());
		body.setDr(Integer.valueOf(0));
		body.setPk_tradetypeid(head.getPk_tradetypeid());
		body.setCoordflag(null);
		body.setBankrelated_code("");// 对账标识码 TO-DO
		body.setBillclass("sk");
		body.setBilldate(dtDate);
		body.setBusidate(dtDate);
		body.setCustomer(lhRecVo.getCustcode());
		body.setDirection(Integer.valueOf(-1));
		body.setLocal_money_bal(lhRecVo.getNmoney());
		body.setLocal_money_cr(lhRecVo.getNmoney());
		body.setLocal_notax_cr(lhRecVo.getNmoney());
		body.setMoney_bal(lhRecVo.getNmoney());
		body.setMoney_cr(lhRecVo.getNmoney());
		body.setNotax_cr(lhRecVo.getNmoney());
		body.setObjtype(Integer.valueOf(0));
		body.setOccupationmny(lhRecVo.getNmoney());
		body.setOrdercubasdoc(lhRecVo.getCustcode());
		body.setPk_balatype(lhRecVo.getBalatype());
		body.setPk_billtype("F2");
		body.setPk_currtype("1002Z0100000000001K1");
		body.setPk_fiorg(lhRecVo.getOrgcode());
		body.setPk_fiorg_v(pk_org_v);
		body.setPk_group(pk_group);
		body.setPk_org(lhRecVo.getOrgcode());
		body.setPk_org_v(pk_org_v);
		body.setPk_recpaytype(lhRecVo.getBusitype());
		body.setPk_subjcode(lhRecVo.getSubjcode());
		body.setPk_tradetype("D2");
		body.setPrepay(Integer.valueOf(0));
		body.setRate(new UFDouble(1));
		body.setRecaccount(lhRecVo.getRecaccount());
		body.setSett_org(lhRecVo.getOrgcode());
		body.setSett_org_v(pk_org_v);

		sumBodyToHead(head, new GatheringBillItemVO[] { body });

		aggVO.setParentVO(head);
		aggVO.setChildrenVO(new GatheringBillItemVO[] { body });

		AggGatheringBillVO retVO = (AggGatheringBillVO) ArrayUtil
				.getFirstInArrays((Object[]) NCPfServiceUtils.processBatch(
						ArapFlowUtil.getCommitActionCode(aggVO.getHeadVO()
								.getPk_org(), IArapBillTypeCons.F2), aggVO
								.getHeadVO().getPk_billtype(),
						new AggGatheringBillVO[] { aggVO }, getUserObj(),
						new WorkflownoteVO()));

		return retVO.getPrimaryKey();
	}

	/**
	 * 设置表头默认信息
	 * 
	 * @param headerVo
	 * @return
	 * @throws BusinessException
	 */
	private GatheringBillVO setHeaderDefault(GatheringBillVO header)
			throws BusinessException {
		Integer ZERO = Integer.valueOf(0);
		/* 单据状态为未审核 */
		header.setBillstatus(BillEnumCollection.BillSatus.Save.VALUE);
		header.setEffectstatus(BillEnumCollection.InureSign.NOINURE.VALUE);
		header.setDr(ZERO);
		// 来源系统是外部交换平台
		header.setSrc_syscode(BillEnumCollection.FromSystem.WBJHPT.VALUE);
		header.setCreationtime(new UFDateTime());
		header.setCoordflag(null);

		// 设置会计年和会计期间。若根据日期查不到会计期间，则捕获异常，不作处理
		try {
			AccountCalendar ac = AccountCalendar.getInstanceByPk_org(header
					.getPk_org());
			ac.setDate(header.getBilldate());
			header.setBillyear(ac.getYearVO().getPeriodyear());
			header.setBillperiod(ac.getMonthVO().getAccperiodmth());
		} catch (BusinessException ex) {
		}

		// 设置交易类型pk
		header.setPk_tradetypeid(PfDataCache.getBillTypeInfo(
				header.getPk_group(), header.getPk_tradetype())
				.getPk_billtypeid());
		return header;
	}

	private void sumBodyToHead(GatheringBillVO head, GatheringBillItemVO[] items) {
		UFDouble global_money = ArapConstant.DOUBLE_ZERO;
		UFDouble group_money = ArapConstant.DOUBLE_ZERO;
		UFDouble local_money = ArapConstant.DOUBLE_ZERO;
		UFDouble money = ArapConstant.DOUBLE_ZERO;
		UFDouble globalnotax = UFDouble.ZERO_DBL;
		UFDouble grouptax = UFDouble.ZERO_DBL;
		UFDouble globaltax = UFDouble.ZERO_DBL;
		UFDouble groupnotax = UFDouble.ZERO_DBL;

		for (BaseItemVO item : items) {
			global_money = global_money
					.add(item.getGlobalcrebit() == null ? ArapConstant.DOUBLE_ZERO
							: item.getGlobalcrebit());
			group_money = group_money
					.add(item.getGroupcrebit() == null ? ArapConstant.DOUBLE_ZERO
							: item.getGroupcrebit());
			local_money = local_money
					.add(item.getLocal_money_cr() == null ? ArapConstant.DOUBLE_ZERO
							: item.getLocal_money_cr());
			money = money
					.add(item.getMoney_cr() == null ? ArapConstant.DOUBLE_ZERO
							: item.getMoney_cr());

			globaltax = local_money
					.add(item.getGlobaltax_cre() == null ? ArapConstant.DOUBLE_ZERO
							: item.getGlobaltax_cre());
			globalnotax = global_money
					.add(item.getGlobalnotax_cre() == null ? ArapConstant.DOUBLE_ZERO
							: item.getGlobalnotax_cre());
			grouptax = group_money
					.add(item.getGrouptax_cre() == null ? ArapConstant.DOUBLE_ZERO
							: item.getGrouptax_cre());
			groupnotax = money
					.add(item.getGroupnotax_cre() == null ? ArapConstant.DOUBLE_ZERO
							: item.getGroupnotax_cre());

			// 设置单据的方向
			item.setDirection(BillEnumCollection.Direction.CREDIT.VALUE);
		}

		money = Currency.getFormaUfValue(
				(String) head.getAttributeValue(IBillFieldGet.PK_CURRTYPE),
				money);
		try {
			local_money = Currency.getFormaUfValueByOrg(head.getPk_org(),
					local_money);
		} catch (BusinessException e) {
		}
		head.setGloballocal(global_money);
		head.setGrouplocal(group_money);
		head.setLocal_money(local_money);
		head.setMoney(money);
		head.setGlobaltax(globaltax);
		head.setGrouptax(grouptax);
		head.setGlobalnotax(globalnotax);
		head.setGroupnotax(groupnotax);
	}

	private PfUserObject[] getUserObj() {
		if (userObjs == null) {
			userObjs = new PfUserObject[] { new PfUserObject() };
		}
		return userObjs;
	}

	private IMDPersistenceQueryService getMdQueryService() {
		if (mdQueryService == null)
			mdQueryService = MDPersistenceService
					.lookupPersistenceQueryService();
		return mdQueryService;
	}

}
