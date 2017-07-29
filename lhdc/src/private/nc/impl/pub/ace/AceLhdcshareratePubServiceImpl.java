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
	// 新增
	public AggLhShareRateVO[] pubinsertBills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggLhShareRateVO> transferTool = new BillTransferTool<AggLhShareRateVO>(
					clientFullVOs);
			// 调用BP
			AceLhdcsharerateInsertBP action = new AceLhdcsharerateInsertBP();
			AggLhShareRateVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhdcsharerateDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggLhShareRateVO[] pubupdateBills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggLhShareRateVO> transferTool = new BillTransferTool<AggLhShareRateVO>(
					clientFullVOs);
			AceLhdcsharerateUpdateBP bp = new AceLhdcsharerateUpdateBP();
			AggLhShareRateVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggLhShareRateVO[] pubsendapprovebills(
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills)
			throws BusinessException {
		AceLhdcsharerateSendApproveBP bp = new AceLhdcsharerateSendApproveBP();
		AggLhShareRateVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggLhShareRateVO[] pubunsendapprovebills(
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills)
			throws BusinessException {
		AceLhdcsharerateUnSendApproveBP bp = new AceLhdcsharerateUnSendApproveBP();
		AggLhShareRateVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggLhShareRateVO[] pubapprovebills(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdcsharerateApproveBP bp = new AceLhdcsharerateApproveBP();
		AggLhShareRateVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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