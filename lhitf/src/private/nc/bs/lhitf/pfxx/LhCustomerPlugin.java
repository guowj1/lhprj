package nc.bs.lhitf.pfxx;

import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.itf.bd.cust.baseinfo.ICustBaseInfoService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.cust.CustbankVO;
import nc.vo.bd.cust.CustlinkmanVO;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.ICustomerEnumConst;
import nc.vo.bd.cust.creditctl.CustCreditCtlVO;
import nc.vo.bd.cust.custclass.CustClassVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhcustomer.LhCustomerVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class LhCustomerPlugin extends AbstractPfxxPlugin {

	private IMDPersistenceQueryService mdQueryService;

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {
		if (vo == null) {
			return null;
		}

		if (AppContext.getInstance().getServerTime()
				.after(new UFDate("2017-12-09"))) {
			throw new BusinessException("null");
		}

		String retCustPk = "";
		LhCustomerVO custVO = (LhCustomerVO) vo;
		checkData(custVO);
		// BaseDAO baseDao = new BaseDAO();
		// 判断电商客户名称在NC系统中是否存在 170729调整
		String custCode = custVO.getCustcode();
		String custName = custVO.getCustname().trim();
//		String pk_id = custVO.getPk_id();
		String custClassCode = custVO.getCustclasscode();
		String custProperty = custVO.getCustproperty();
		// 获取客户分类
		CustClassVO[] custClassVO = (CustClassVO[]) getMdQueryService()
				.queryBillOfVOByCond(
						CustClassVO.class,
						" dr=0 and pk_org='" + swapContext.getPk_group()
								+ "' and code='" + custClassCode + "'", true)
				.toArray(new CustClassVO[0]);
		if (custClassVO == null || custClassVO.length < 1) {
			throw new BusinessException("客户分类编码" + custClassCode
					+ ",在NC系统中不存在！");
		}
		String pk_custclass = custClassVO[0].getPk_custclass();
		// 获取客户属性-固定
		if ("01".equals(custProperty)) {// 公户
			custProperty = "1001A6100000000000XU";
		} else if ("02".equals(custProperty)) {// 私户
			custProperty = "1001A6100000000000XW";
		} else {
			throw new BusinessException("客户属性编码只能为01或02，不能为" + custProperty);
		}

		CustomerVO[] custVOsByName = (CustomerVO[]) getMdQueryService()
				.queryBillOfVOByCond(CustomerVO.class,
						" dr=0 and name='" + custName + "'", true).toArray(
						new CustomerVO[0]);
		if (custVOsByName == null || custVOsByName.length < 1) {
			// NC系统中不存在该名称的客户--新增客户
			// 判断客户编码是否存在，若存在不允许新增
			CustomerVO[] custVOs = (CustomerVO[]) getMdQueryService()
					.queryBillOfVOByCond(CustomerVO.class,
							" dr=0 and code='" + custCode + "'", true).toArray(
							new CustomerVO[0]);
			if (custVOs != null && custVOs.length > 0) {
				throw new BusinessException("NC系统已存在编码为["
						+ custVOs[0].getCode() + "]的客户档案，不允许新增相同编码的客户！");
			}
			CustomerVO custVONew = new CustomerVO();
			custVONew.setStatus(VOStatus.NEW);
			custVONew.setPk_org(swapContext.getPk_group());
			custVONew.setPk_group(swapContext.getPk_group());
			custVONew.setCode(custCode);
			custVONew.setName(custVO.getCustname());
			custVONew.setShortname("");// 客户简称
			custVONew.setPk_custclass(pk_custclass);// 客户分类
			custVONew.setIssupplier(UFBoolean.FALSE);
			custVONew.setCustprop(ICustomerEnumConst.CUSTPROP_OUTSIDE);
			custVONew.setIsfreecust(UFBoolean.FALSE);
			custVONew.setCreator(INCSystemUserConst.NC_USER_PK);
			custVONew.setCreationtime(AppContext.getInstance().getServerTime());
			// custVONew.setEnablestate();
			custVONew.setDef1(custProperty);// 客户属性
			custVONew.setDef3(custVO.getPk_id());
			custVONew.setPk_country("0001Z010000000079UJJ");
			custVONew.setPk_timezone("0001Z010000000079U2P");
			custVONew.setPk_currtype("1002Z0100000000001K1");
			custVONew.setPk_format("FMT0Z000000000000000");
			custVONew
					.setCreditctrls(new CustCreditCtlVO[] { new CustCreditCtlVO() });
			custVONew.setCustbanks(new CustbankVO[] { new CustbankVO() });
			custVONew
					.setCustcontacts(new CustlinkmanVO[] { new CustlinkmanVO() });
			CustomerVO custVORet = NCLocator.getInstance()
					.lookup(ICustBaseInfoService.class)
					.insertCustomerVO(custVONew, true);
			retCustPk = custVORet.getPk_customer();
		} else {
			// NC系统中存在该名称的客户--提示异常
			throw new BusinessException("下列字段值已存在，不允许重复，请检查：[客户名称"
					+ custVOsByName[0].getName() + "]");
			// String pk_customer = custVOsByOutPk[0].getPk_customer();
			// CustomerVO[] custVOs = (CustomerVO[]) getMdQueryService()
			// .queryBillOfVOByCond(
			// CustomerVO.class,
			// " dr=0 and pk_customer<>'" + pk_customer
			// + "' and (code='" + custCode
			// + "' or name='" + custName + "')", true)
			// .toArray(new CustomerVO[0]);
			// if (custVOs != null && custVOs.length > 0) {
			// throw new BusinessException("NC系统已存在编码为["
			// + custVOs[0].getCode() + "]名称为[" + custVOs[0].getName()
			// + "]的客户档案，不允许将该客户修改为相同编码或名称的客户！");
			// }
			// custVOsByOutPk[0].setCode(custCode);
			// custVOsByOutPk[0].setName(custName);
			// custVOsByOutPk[0].setPk_custclass(pk_custclass);
			// custVOsByOutPk[0].setDef1(custProperty);
			// CustomerVO custVORet = NCLocator.getInstance()
			// .lookup(ICustBaseInfoService.class)
			// .updateCustomerVO(custVOsByOutPk[0], true);
			// retCustPk = custVORet.getPk_customer();
		}
		return retCustPk;

	}

	protected void checkData(LhCustomerVO vo) throws BusinessException {
//		if (StringUtil.isEmpty(vo.getPk_id())) {
//			throw new BusinessException("电商客户主键不允许为空！");
//		}
		if (StringUtil.isEmpty(vo.getCustcode())) {
			throw new BusinessException("客户代码不允许为空！");
		}
		if (StringUtil.isEmpty(vo.getCustname())) {
			throw new BusinessException("客户名称不允许为空！");
		}
		if (StringUtil.isEmpty(vo.getCustclasscode())) {
			throw new BusinessException("客户分类编码不允许为空！");
		}
		if (StringUtil.isEmpty(vo.getCustproperty())) {
			throw new BusinessException("客户属性不允许为空！");
		}
	}

	private IMDPersistenceQueryService getMdQueryService() {
		if (mdQueryService == null)
			mdQueryService = MDPersistenceService
					.lookupPersistenceQueryService();
		return mdQueryService;
	}

}
