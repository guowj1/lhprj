package nc.impl.pub.ace;

import nc.bs.ic.lhdayproduct.ace.bp.AceLhdayproductInsertBP;
import nc.bs.ic.lhdayproduct.ace.bp.AceLhdayproductUpdateBP;
import nc.bs.ic.lhdayproduct.ace.bp.AceLhdayproductDeleteBP;
import nc.bs.ic.lhdayproduct.ace.bp.AceLhdayproductSendApproveBP;
import nc.bs.ic.lhdayproduct.ace.bp.AceLhdayproductUnSendApproveBP;
import nc.bs.ic.lhdayproduct.ace.bp.AceLhdayproductApproveBP;
import nc.bs.ic.lhdayproduct.ace.bp.AceLhdayproductUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLhdayproductPubServiceImpl {
	// ����
	public AggDayProductHVO[] pubinsertBills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggDayProductHVO> transferTool = new BillTransferTool<AggDayProductHVO>(
					clientFullVOs);
			// ����BP
			AceLhdayproductInsertBP action = new AceLhdayproductInsertBP();
			AggDayProductHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLhdayproductDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggDayProductHVO[] pubupdateBills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggDayProductHVO> transferTool = new BillTransferTool<AggDayProductHVO>(
					clientFullVOs);
			AceLhdayproductUpdateBP bp = new AceLhdayproductUpdateBP();
			AggDayProductHVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggDayProductHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggDayProductHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggDayProductHVO> query = new BillLazyQuery<AggDayProductHVO>(
					AggDayProductHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public AggDayProductHVO[] pubsendapprovebills(
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills)
			throws BusinessException {
		AceLhdayproductSendApproveBP bp = new AceLhdayproductSendApproveBP();
		AggDayProductHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggDayProductHVO[] pubunsendapprovebills(
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills)
			throws BusinessException {
		AceLhdayproductUnSendApproveBP bp = new AceLhdayproductUnSendApproveBP();
		AggDayProductHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggDayProductHVO[] pubapprovebills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdayproductApproveBP bp = new AceLhdayproductApproveBP();
		AggDayProductHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggDayProductHVO[] pubunapprovebills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdayproductUnApproveBP bp = new AceLhdayproductUnApproveBP();
		AggDayProductHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}