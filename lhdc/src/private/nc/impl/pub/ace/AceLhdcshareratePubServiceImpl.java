package nc.impl.pub.ace;

import nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp.AceLhdcsharerateInsertBP;
import nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp.AceLhdcsharerateUpdateBP;
import nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp.AceLhdcsharerateDeleteBP;
import nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp.AceLhdcsharerateSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp.AceLhdcsharerateUnSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp.AceLhdcsharerateApproveBP;
import nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp.AceLhdcsharerateUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLhdcshareratePubServiceImpl {
	// ����
	public AggLhShareRateVO[] pubinsertBills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggLhShareRateVO> transferTool = new BillTransferTool<AggLhShareRateVO>(
					clientFullVOs);
			// ����BP
			AceLhdcsharerateInsertBP action = new AceLhdcsharerateInsertBP();
			AggLhShareRateVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLhdcsharerateDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggLhShareRateVO[] pubupdateBills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggLhShareRateVO> transferTool = new BillTransferTool<AggLhShareRateVO>(
					clientFullVOs);
			AceLhdcsharerateUpdateBP bp = new AceLhdcsharerateUpdateBP();
			AggLhShareRateVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggLhShareRateVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggLhShareRateVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggLhShareRateVO> query = new BillLazyQuery<AggLhShareRateVO>(
					AggLhShareRateVO.class);
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
	public AggLhShareRateVO[] pubsendapprovebills(
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills)
			throws BusinessException {
		AceLhdcsharerateSendApproveBP bp = new AceLhdcsharerateSendApproveBP();
		AggLhShareRateVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggLhShareRateVO[] pubunsendapprovebills(
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills)
			throws BusinessException {
		AceLhdcsharerateUnSendApproveBP bp = new AceLhdcsharerateUnSendApproveBP();
		AggLhShareRateVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggLhShareRateVO[] pubapprovebills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdcsharerateApproveBP bp = new AceLhdcsharerateApproveBP();
		AggLhShareRateVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggLhShareRateVO[] pubunapprovebills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdcsharerateUnApproveBP bp = new AceLhdcsharerateUnApproveBP();
		AggLhShareRateVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}