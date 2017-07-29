package nc.impl.pub.ace;

import nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp.AceLhdc_feecolleInsertBP;
import nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp.AceLhdc_feecolleUpdateBP;
import nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp.AceLhdc_feecolleDeleteBP;
import nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp.AceLhdc_feecolleSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp.AceLhdc_feecolleUnSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp.AceLhdc_feecolleApproveBP;
import nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp.AceLhdc_feecolleUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLhdcfeecollePubServiceImpl {
	// ����
	public AggLhFeeCollectVO[] pubinsertBills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggLhFeeCollectVO> transferTool = new BillTransferTool<AggLhFeeCollectVO>(
					clientFullVOs);
			// ����BP
			AceLhdc_feecolleInsertBP action = new AceLhdc_feecolleInsertBP();
			AggLhFeeCollectVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLhdc_feecolleDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggLhFeeCollectVO[] pubupdateBills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggLhFeeCollectVO> transferTool = new BillTransferTool<AggLhFeeCollectVO>(
					clientFullVOs);
			AceLhdc_feecolleUpdateBP bp = new AceLhdc_feecolleUpdateBP();
			AggLhFeeCollectVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggLhFeeCollectVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggLhFeeCollectVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggLhFeeCollectVO> query = new BillLazyQuery<AggLhFeeCollectVO>(
					AggLhFeeCollectVO.class);
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
	public AggLhFeeCollectVO[] pubsendapprovebills(
			AggLhFeeCollectVO[] clientFullVOs, AggLhFeeCollectVO[] originBills)
			throws BusinessException {
		AceLhdc_feecolleSendApproveBP bp = new AceLhdc_feecolleSendApproveBP();
		AggLhFeeCollectVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggLhFeeCollectVO[] pubunsendapprovebills(
			AggLhFeeCollectVO[] clientFullVOs, AggLhFeeCollectVO[] originBills)
			throws BusinessException {
		AceLhdc_feecolleUnSendApproveBP bp = new AceLhdc_feecolleUnSendApproveBP();
		AggLhFeeCollectVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggLhFeeCollectVO[] pubapprovebills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdc_feecolleApproveBP bp = new AceLhdc_feecolleApproveBP();
		AggLhFeeCollectVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggLhFeeCollectVO[] pubunapprovebills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdc_feecolleUnApproveBP bp = new AceLhdc_feecolleUnApproveBP();
		AggLhFeeCollectVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}