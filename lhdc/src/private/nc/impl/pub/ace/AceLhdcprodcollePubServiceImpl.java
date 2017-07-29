package nc.impl.pub.ace;

import nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp.AceLhdc_prodcolleInsertBP;
import nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp.AceLhdc_prodcolleUpdateBP;
import nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp.AceLhdc_prodcolleDeleteBP;
import nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp.AceLhdc_prodcolleSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp.AceLhdc_prodcolleUnSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp.AceLhdc_prodcolleApproveBP;
import nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp.AceLhdc_prodcolleUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLhdcprodcollePubServiceImpl {
	// ����
	public AggLhProdColleVO[] pubinsertBills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggLhProdColleVO> transferTool = new BillTransferTool<AggLhProdColleVO>(
					clientFullVOs);
			// ����BP
			AceLhdc_prodcolleInsertBP action = new AceLhdc_prodcolleInsertBP();
			AggLhProdColleVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLhdc_prodcolleDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggLhProdColleVO[] pubupdateBills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggLhProdColleVO> transferTool = new BillTransferTool<AggLhProdColleVO>(
					clientFullVOs);
			AceLhdc_prodcolleUpdateBP bp = new AceLhdc_prodcolleUpdateBP();
			AggLhProdColleVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggLhProdColleVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggLhProdColleVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggLhProdColleVO> query = new BillLazyQuery<AggLhProdColleVO>(
					AggLhProdColleVO.class);
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
	public AggLhProdColleVO[] pubsendapprovebills(
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills)
			throws BusinessException {
		AceLhdc_prodcolleSendApproveBP bp = new AceLhdc_prodcolleSendApproveBP();
		AggLhProdColleVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggLhProdColleVO[] pubunsendapprovebills(
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills)
			throws BusinessException {
		AceLhdc_prodcolleUnSendApproveBP bp = new AceLhdc_prodcolleUnSendApproveBP();
		AggLhProdColleVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggLhProdColleVO[] pubapprovebills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdc_prodcolleApproveBP bp = new AceLhdc_prodcolleApproveBP();
		AggLhProdColleVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggLhProdColleVO[] pubunapprovebills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdc_prodcolleUnApproveBP bp = new AceLhdc_prodcolleUnApproveBP();
		AggLhProdColleVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}