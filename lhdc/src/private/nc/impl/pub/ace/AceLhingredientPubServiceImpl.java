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
	// 新增
	public AggIngredientHVO[] pubinsertBills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggIngredientHVO> transferTool = new BillTransferTool<AggIngredientHVO>(
					clientFullVOs);
			// 调用BP
			AceLhingredientInsertBP action = new AceLhingredientInsertBP();
			AggIngredientHVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhingredientDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggIngredientHVO[] pubupdateBills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggIngredientHVO> transferTool = new BillTransferTool<AggIngredientHVO>(
					clientFullVOs);
			AceLhingredientUpdateBP bp = new AceLhingredientUpdateBP();
			AggIngredientHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggIngredientHVO[] pubsendapprovebills(
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills)
			throws BusinessException {
		AceLhingredientSendApproveBP bp = new AceLhingredientSendApproveBP();
		AggIngredientHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggIngredientHVO[] pubunsendapprovebills(
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills)
			throws BusinessException {
		AceLhingredientUnSendApproveBP bp = new AceLhingredientUnSendApproveBP();
		AggIngredientHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggIngredientHVO[] pubapprovebills(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhingredientApproveBP bp = new AceLhingredientApproveBP();
		AggIngredientHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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