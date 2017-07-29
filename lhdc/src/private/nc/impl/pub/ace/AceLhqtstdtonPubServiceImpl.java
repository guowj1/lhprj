package nc.impl.pub.ace;

import nc.bs.lhprj.lhdc.lhqtstdton.ace.bp.AceLhqtstdtonInsertBP;
import nc.bs.lhprj.lhdc.lhqtstdton.ace.bp.AceLhqtstdtonUpdateBP;
import nc.bs.lhprj.lhdc.lhqtstdton.ace.bp.AceLhqtstdtonDeleteBP;
import nc.bs.lhprj.lhdc.lhqtstdton.ace.bp.AceLhqtstdtonSendApproveBP;
import nc.bs.lhprj.lhdc.lhqtstdton.ace.bp.AceLhqtstdtonUnSendApproveBP;
import nc.bs.lhprj.lhdc.lhqtstdton.ace.bp.AceLhqtstdtonApproveBP;
import nc.bs.lhprj.lhdc.lhqtstdton.ace.bp.AceLhqtstdtonUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLhqtstdtonPubServiceImpl {
	// ����
	public AggLhQtStdTonVO[] pubinsertBills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggLhQtStdTonVO> transferTool = new BillTransferTool<AggLhQtStdTonVO>(
					clientFullVOs);
			// ����BP
			AceLhqtstdtonInsertBP action = new AceLhqtstdtonInsertBP();
			AggLhQtStdTonVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLhqtstdtonDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggLhQtStdTonVO[] pubupdateBills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggLhQtStdTonVO> transferTool = new BillTransferTool<AggLhQtStdTonVO>(
					clientFullVOs);
			AceLhqtstdtonUpdateBP bp = new AceLhqtstdtonUpdateBP();
			AggLhQtStdTonVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggLhQtStdTonVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggLhQtStdTonVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggLhQtStdTonVO> query = new BillLazyQuery<AggLhQtStdTonVO>(
					AggLhQtStdTonVO.class);
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
	public AggLhQtStdTonVO[] pubsendapprovebills(
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills)
			throws BusinessException {
		AceLhqtstdtonSendApproveBP bp = new AceLhqtstdtonSendApproveBP();
		AggLhQtStdTonVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggLhQtStdTonVO[] pubunsendapprovebills(
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills)
			throws BusinessException {
		AceLhqtstdtonUnSendApproveBP bp = new AceLhqtstdtonUnSendApproveBP();
		AggLhQtStdTonVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggLhQtStdTonVO[] pubapprovebills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhqtstdtonApproveBP bp = new AceLhqtstdtonApproveBP();
		AggLhQtStdTonVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggLhQtStdTonVO[] pubunapprovebills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhqtstdtonUnApproveBP bp = new AceLhqtstdtonUnApproveBP();
		AggLhQtStdTonVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}