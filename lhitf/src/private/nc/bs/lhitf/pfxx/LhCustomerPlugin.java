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
		// �жϵ��̿ͻ�������NCϵͳ���Ƿ���� 170729����
		String custCode = custVO.getCustcode();
		String custName = custVO.getCustname().trim();
//		String pk_id = custVO.getPk_id();
		String custClassCode = custVO.getCustclasscode();
		String custProperty = custVO.getCustproperty();
		// ��ȡ�ͻ�����
		CustClassVO[] custClassVO = (CustClassVO[]) getMdQueryService()
				.queryBillOfVOByCond(
						CustClassVO.class,
						" dr=0 and pk_org='" + swapContext.getPk_group()
								+ "' and code='" + custClassCode + "'", true)
				.toArray(new CustClassVO[0]);
		if (custClassVO == null || custClassVO.length < 1) {
			throw new BusinessException("�ͻ��������" + custClassCode
					+ ",��NCϵͳ�в����ڣ�");
		}
		String pk_custclass = custClassVO[0].getPk_custclass();
		// ��ȡ�ͻ�����-�̶�
		if ("01".equals(custProperty)) {// ����
			custProperty = "1001A6100000000000XU";
		} else if ("02".equals(custProperty)) {// ˽��
			custProperty = "1001A6100000000000XW";
		} else {
			throw new BusinessException("�ͻ����Ա���ֻ��Ϊ01��02������Ϊ" + custProperty);
		}

		CustomerVO[] custVOsByName = (CustomerVO[]) getMdQueryService()
				.queryBillOfVOByCond(CustomerVO.class,
						" dr=0 and name='" + custName + "'", true).toArray(
						new CustomerVO[0]);
		if (custVOsByName == null || custVOsByName.length < 1) {
			// NCϵͳ�в����ڸ����ƵĿͻ�--�����ͻ�
			// �жϿͻ������Ƿ���ڣ������ڲ���������
			CustomerVO[] custVOs = (CustomerVO[]) getMdQueryService()
					.queryBillOfVOByCond(CustomerVO.class,
							" dr=0 and code='" + custCode + "'", true).toArray(
							new CustomerVO[0]);
			if (custVOs != null && custVOs.length > 0) {
				throw new BusinessException("NCϵͳ�Ѵ��ڱ���Ϊ["
						+ custVOs[0].getCode() + "]�Ŀͻ�������������������ͬ����Ŀͻ���");
			}
			CustomerVO custVONew = new CustomerVO();
			custVONew.setStatus(VOStatus.NEW);
			custVONew.setPk_org(swapContext.getPk_group());
			custVONew.setPk_group(swapContext.getPk_group());
			custVONew.setCode(custCode);
			custVONew.setName(custVO.getCustname());
			custVONew.setShortname("");// �ͻ����
			custVONew.setPk_custclass(pk_custclass);// �ͻ�����
			custVONew.setIssupplier(UFBoolean.FALSE);
			custVONew.setCustprop(ICustomerEnumConst.CUSTPROP_OUTSIDE);
			custVONew.setIsfreecust(UFBoolean.FALSE);
			custVONew.setCreator(INCSystemUserConst.NC_USER_PK);
			custVONew.setCreationtime(AppContext.getInstance().getServerTime());
			// custVONew.setEnablestate();
			custVONew.setDef1(custProperty);// �ͻ�����
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
			// NCϵͳ�д��ڸ����ƵĿͻ�--��ʾ�쳣
			throw new BusinessException("�����ֶ�ֵ�Ѵ��ڣ��������ظ������飺[�ͻ�����"
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
			// throw new BusinessException("NCϵͳ�Ѵ��ڱ���Ϊ["
			// + custVOs[0].getCode() + "]����Ϊ[" + custVOs[0].getName()
			// + "]�Ŀͻ��������������ÿͻ��޸�Ϊ��ͬ��������ƵĿͻ���");
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
//			throw new BusinessException("���̿ͻ�����������Ϊ�գ�");
//		}
		if (StringUtil.isEmpty(vo.getCustcode())) {
			throw new BusinessException("�ͻ����벻����Ϊ�գ�");
		}
		if (StringUtil.isEmpty(vo.getCustname())) {
			throw new BusinessException("�ͻ����Ʋ�����Ϊ�գ�");
		}
		if (StringUtil.isEmpty(vo.getCustclasscode())) {
			throw new BusinessException("�ͻ�������벻����Ϊ�գ�");
		}
		if (StringUtil.isEmpty(vo.getCustproperty())) {
			throw new BusinessException("�ͻ����Բ�����Ϊ�գ�");
		}
	}

	private IMDPersistenceQueryService getMdQueryService() {
		if (mdQueryService == null)
			mdQueryService = MDPersistenceService
					.lookupPersistenceQueryService();
		return mdQueryService;
	}

}
