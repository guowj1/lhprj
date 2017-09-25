package nc.impl.pub.ace;

import nc.bs.ic.lhingredient.ace.bp.AceLhingredientInsertBP;
import nc.bs.ic.lhingredient.ace.bp.AceLhingredientUpdateBP;
import nc.bs.ic.lhingredient.ace.bp.AceLhingredientDeleteBP;
import nc.bs.ic.lhingredient.ace.bp.AceLhingredientSendApproveBP;
import nc.bs.ic.lhingredient.ace.bp.AceLhingredientUnSendApproveBP;
import nc.bs.ic.lhingredient.ace.bp.AceLhingredientApproveBP;
import nc.bs.ic.lhingredient.ace.bp.AceLhingredientUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLhingredientPubServiceImpl {
	// ����
	public AggIngredientHVO[] pubinsertBills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggIngredientHVO> transferTool = new BillTransferTool<AggIngredientHVO>(
					clientFullVOs);
			// ����BP
			AceLhingredientInsertBP action = new AceLhingredientInsertBP();
			AggIngredientHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLhingredientDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggIngredientHVO[] pubupdateBills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggIngredientHVO> transferTool = new BillTransferTool<AggIngredientHVO>(
					clientFullVOs);
			AceLhingredientUpdateBP bp = new AceLhingredientUpdateBP();
			AggIngredientHVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggIngredientHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggIngredientHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggIngredientHVO> query = new BillLazyQuery<AggIngredientHVO>(
					AggIngredientHVO.class);
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
	public AggIngredientHVO[] pubsendapprovebills(
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills)
			throws BusinessException {
		AceLhingredientSendApproveBP bp = new AceLhingredientSendApproveBP();
		AggIngredientHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggIngredientHVO[] pubunsendapprovebills(
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills)
			throws BusinessException {
		AceLhingredientUnSendApproveBP bp = new AceLhingredientUnSendApproveBP();
		AggIngredientHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggIngredientHVO[] pubapprovebills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhingredientApproveBP bp = new AceLhingredientApproveBP();
		AggIngredientHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggIngredientHVO[] pubunapprovebills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhingredientUnApproveBP bp = new AceLhingredientUnApproveBP();
		AggIngredientHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}