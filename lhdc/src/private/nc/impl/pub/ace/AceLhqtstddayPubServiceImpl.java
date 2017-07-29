package nc.impl.pub.ace;

import nc.bs.lhprj.lhdc.lhqtstdday.ace.bp.AceLhqtstddayInsertBP;
import nc.bs.lhprj.lhdc.lhqtstdday.ace.bp.AceLhqtstddayUpdateBP;
import nc.bs.lhprj.lhdc.lhqtstdday.ace.bp.AceLhqtstddayDeleteBP;
import nc.bs.lhprj.lhdc.lhqtstdday.ace.bp.AceLhqtstddaySendApproveBP;
import nc.bs.lhprj.lhdc.lhqtstdday.ace.bp.AceLhqtstddayUnSendApproveBP;
import nc.bs.lhprj.lhdc.lhqtstdday.ace.bp.AceLhqtstddayApproveBP;
import nc.bs.lhprj.lhdc.lhqtstdday.ace.bp.AceLhqtstddayUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLhqtstddayPubServiceImpl {
	// ����
	public AggLhQtStdDayVO[] pubinsertBills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggLhQtStdDayVO> transferTool = new BillTransferTool<AggLhQtStdDayVO>(
					clientFullVOs);
			// ����BP
			AceLhqtstddayInsertBP action = new AceLhqtstddayInsertBP();
			AggLhQtStdDayVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLhqtstddayDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggLhQtStdDayVO[] pubupdateBills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggLhQtStdDayVO> transferTool = new BillTransferTool<AggLhQtStdDayVO>(
					clientFullVOs);
			AceLhqtstddayUpdateBP bp = new AceLhqtstddayUpdateBP();
			AggLhQtStdDayVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggLhQtStdDayVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggLhQtStdDayVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggLhQtStdDayVO> query = new BillLazyQuery<AggLhQtStdDayVO>(
					AggLhQtStdDayVO.class);
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
	public AggLhQtStdDayVO[] pubsendapprovebills(
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills)
			throws BusinessException {
		AceLhqtstddaySendApproveBP bp = new AceLhqtstddaySendApproveBP();
		AggLhQtStdDayVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggLhQtStdDayVO[] pubunsendapprovebills(
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills)
			throws BusinessException {
		AceLhqtstddayUnSendApproveBP bp = new AceLhqtstddayUnSendApproveBP();
		AggLhQtStdDayVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggLhQtStdDayVO[] pubapprovebills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhqtstddayApproveBP bp = new AceLhqtstddayApproveBP();
		AggLhQtStdDayVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggLhQtStdDayVO[] pubunapprovebills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhqtstddayUnApproveBP bp = new AceLhqtstddayUnApproveBP();
		AggLhQtStdDayVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}