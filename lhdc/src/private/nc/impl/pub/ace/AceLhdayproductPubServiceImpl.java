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
	// 新增
	public AggDayProductHVO[] pubinsertBills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggDayProductHVO> transferTool = new BillTransferTool<AggDayProductHVO>(
					clientFullVOs);
			// 调用BP
			AceLhdayproductInsertBP action = new AceLhdayproductInsertBP();
			AggDayProductHVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhdayproductDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggDayProductHVO[] pubupdateBills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggDayProductHVO> transferTool = new BillTransferTool<AggDayProductHVO>(
					clientFullVOs);
			AceLhdayproductUpdateBP bp = new AceLhdayproductUpdateBP();
			AggDayProductHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggDayProductHVO[] pubsendapprovebills(
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills)
			throws BusinessException {
		AceLhdayproductSendApproveBP bp = new AceLhdayproductSendApproveBP();
		AggDayProductHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggDayProductHVO[] pubunsendapprovebills(
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills)
			throws BusinessException {
		AceLhdayproductUnSendApproveBP bp = new AceLhdayproductUnSendApproveBP();
		AggDayProductHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggDayProductHVO[] pubapprovebills(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdayproductApproveBP bp = new AceLhdayproductApproveBP();
		AggDayProductHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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