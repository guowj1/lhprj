package nc.bs.lhitf.pfxx;

import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.itf.bd.defdoc.IDefdocService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.bd.defdoc.DefdoclistVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhcustomersub.LhCustomerSubVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class LhCustomerSubPlugin extends AbstractPfxxPlugin {

	private IMDPersistenceQueryService mdQueryService;

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {
		if (vo == null) {
			return null;
		}

		if (AppContext.getInstance().getServerTime()
				.after(new UFDate("2018-01-01"))) {
			throw new BusinessException("�ⲿϵͳ�����쳣��");
		}

		String retCustSubPk = "";
		LhCustomerSubVO custSubVO = (LhCustomerSubVO) vo;
		checkData(custSubVO);

		String pk_id = custSubVO.getPk_id();
		String custSubCode = custSubVO.getCustsubcode();
		String custSubName = custSubVO.getCustsubname() + pk_id;
		String custCode = custSubVO.getCustcode();
		// �жϿͻ������Ƿ�Ϸ�
		CustomerVO[] custVO = (CustomerVO[]) getMdQueryService()
				.queryBillOfVOByCond(CustomerVO.class,
						" dr=0 and code='" + custCode + "'", true).toArray(
						new CustomerVO[0]);
		if (custVO == null || custVO.length < 1) {
			throw new BusinessException("�����ͻ�����" + custCode + ",��NCϵͳ�в����ڣ�");
		}
		// ����Զ��嵵����������
		DefdoclistVO[] defdocListVo = (DefdoclistVO[]) getMdQueryService()
				.queryBillOfVOByCond(DefdoclistVO.class,
						" dr=0 and code='zdyx018' ", true).toArray(
						new DefdoclistVO[0]);
		String pk_defdoclist = "";
		if (defdocListVo != null && defdocListVo.length > 0) {
			pk_defdoclist = defdocListVo[0].getPk_defdoclist();
		} else {
			throw new BusinessException("NC����δ�����Զ��嵵������[zdyx018]!");
		}

		// �ж��ӻ������Ƿ����
		DefdocVO[] custSubVoOld = (DefdocVO[]) getMdQueryService()
				.queryBillOfVOByCond(
						DefdocVO.class,
						" dr=0 and def3='" + pk_id + "' and pk_defdoclist='"
								+ pk_defdoclist + "'", true).toArray(
						new DefdocVO[0]);
		if (custSubVoOld == null || custSubVoOld.length < 1) {
			// �����ͻ��ӻ�����
			// �жϿͻ��ӻ�������ӻ�������ϵͳ���Ƿ����
			DefdocVO[] custSubVoExists = (DefdocVO[]) getMdQueryService()
					.queryBillOfVOByCond(
							DefdocVO.class,
							" dr=0 and pk_defdoclist='" + pk_defdoclist
									+ "' and (code='" + custSubCode
									+ "' or name='" + custSubName + "') ", true)
					.toArray(new DefdocVO[0]);
			StringBuilder strErr = new StringBuilder();
			if (custSubVoExists != null && custSubVoExists.length > 0) {
				if (custSubCode.equals(custSubVoExists[0].getCode())) {
					strErr.append("[�ͻ����˻�����:" + custSubCode + "]");
				}
				if (custSubName.equals(custSubVoExists[0].getName())) {
					strErr.append("[�ͻ����˻�����:" + custSubName + "]");
				}
			}
			if (strErr != null && strErr.length() > 0) {
				throw new BusinessException("�����ֶ�ֵ�Ѿ����ڣ��������ظ������飺"
						+ strErr.toString());
			}
			DefdocVO custSubVoNew = new DefdocVO();
			custSubVoNew.setCode(custSubCode);
			custSubVoNew.setName(custSubName);
			custSubVoNew.setMemo(custCode);
			custSubVoNew.setDef3(pk_id);
			custSubVoNew.setPk_defdoclist(pk_defdoclist);// �Զ��嵵����������
			custSubVoNew.setCreator(INCSystemUserConst.NC_USER_PK);
			custSubVoNew.setCreationtime(AppContext.getInstance()
					.getServerTime());
			custSubVoNew.setDataoriginflag(Integer.valueOf(1));
			custSubVoNew.setEnablestate(VOStatus.NEW);
			custSubVoNew.setPk_group(swapContext.getPk_group());
			custSubVoNew.setPk_org(swapContext.getPk_group());
			DefdocVO[] defDocVoNew = NCLocator
					.getInstance()
					.lookup(IDefdocService.class)
					.insertDefdocs(swapContext.getPk_group(),
							new DefdocVO[] { custSubVoNew });
			retCustSubPk = defDocVoNew[0].getPk_defdoc();
		} else {
			// �޸Ŀͻ��ӻ�����
			// �жϿͻ��ӻ�������ӻ�������ϵͳ���Ƿ����
			DefdocVO[] custSubVoExists = (DefdocVO[]) getMdQueryService()
					.queryBillOfVOByCond(
							DefdocVO.class,
							" dr=0 and pk_defdoclist='" + pk_defdoclist
									+ "' and def1<>'" + pk_id + "' and (code='"
									+ custSubCode + "' or name='" + custSubName
									+ "') ", true).toArray(new DefdocVO[0]);
			StringBuilder strErr = new StringBuilder();
			if (custSubVoExists != null && custSubVoExists.length > 0) {
				if (custSubCode.equals(custSubVoExists[0].getCode())) {
					strErr.append("[�ͻ����˻�����:" + custSubCode + "]");
				}
				if (custSubName.equals(custSubVoExists[0].getName())) {
					strErr.append("[�ͻ����˻�����:" + custSubName + "]");
				}
			}
			if (strErr != null && strErr.length() > 0) {
				throw new BusinessException("�����ֶ�ֵ�Ѿ����ڣ��������ظ������飺"
						+ strErr.toString());
			}

			custSubVoOld[0].setCode(custSubCode);
			custSubVoOld[0].setName(custSubName);
			custSubVoOld[0].setMemo(custCode);
			custSubVoOld[0].setModifier(INCSystemUserConst.NC_USER_PK);
			custSubVoOld[0].setModifiedtime(AppContext.getInstance()
					.getServerTime());
			DefdocVO[] custSubVoNew = NCLocator
					.getInstance()
					.lookup(IDefdocService.class)
					.updateDefdocs(swapContext.getPk_group(),
							new DefdocVO[] { custSubVoOld[0] });
			retCustSubPk = custSubVoNew[0].getPk_defdoc();
		}

		return retCustSubPk;

	}

	protected void checkData(LhCustomerSubVO vo) throws BusinessException {
		if (StringUtil.isEmpty(vo.getPk_id())) {
			throw new BusinessException("���̿ͻ��ӻ�����������Ϊ�գ�");
		}
		if (StringUtil.isEmpty(vo.getCustsubcode())) {
			throw new BusinessException("�ͻ��ӻ����벻����Ϊ�գ�");
		}
		if (StringUtil.isEmpty(vo.getCustsubname())) {
			throw new BusinessException("�ͻ��ӻ����Ʋ�����Ϊ�գ�");
		}
		if (StringUtil.isEmpty(vo.getCustcode())) {
			throw new BusinessException("�����ͻ����벻����Ϊ�գ�");
		}
	}

	private IMDPersistenceQueryService getMdQueryService() {
		if (mdQueryService == null)
			mdQueryService = MDPersistenceService
					.lookupPersistenceQueryService();
		return mdQueryService;
	}

}
