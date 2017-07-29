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
	// 新增
	public AggLhQtStdDayVO[] pubinsertBills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggLhQtStdDayVO> transferTool = new BillTransferTool<AggLhQtStdDayVO>(
					clientFullVOs);
			// 调用BP
			AceLhqtstddayInsertBP action = new AceLhqtstddayInsertBP();
			AggLhQtStdDayVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhqtstddayDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggLhQtStdDayVO[] pubupdateBills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggLhQtStdDayVO> transferTool = new BillTransferTool<AggLhQtStdDayVO>(
					clientFullVOs);
			AceLhqtstddayUpdateBP bp = new AceLhqtstddayUpdateBP();
			AggLhQtStdDayVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggLhQtStdDayVO[] pubsendapprovebills(
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills)
			throws BusinessException {
		AceLhqtstddaySendApproveBP bp = new AceLhqtstddaySendApproveBP();
		AggLhQtStdDayVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggLhQtStdDayVO[] pubunsendapprovebills(
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills)
			throws BusinessException {
		AceLhqtstddayUnSendApproveBP bp = new AceLhqtstddayUnSendApproveBP();
		AggLhQtStdDayVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggLhQtStdDayVO[] pubapprovebills(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhqtstddayApproveBP bp = new AceLhqtstddayApproveBP();
		AggLhQtStdDayVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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