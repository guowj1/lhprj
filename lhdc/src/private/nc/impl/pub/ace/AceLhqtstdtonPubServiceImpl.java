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
	// 新增
	public AggLhQtStdTonVO[] pubinsertBills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggLhQtStdTonVO> transferTool = new BillTransferTool<AggLhQtStdTonVO>(
					clientFullVOs);
			// 调用BP
			AceLhqtstdtonInsertBP action = new AceLhqtstdtonInsertBP();
			AggLhQtStdTonVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhqtstdtonDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggLhQtStdTonVO[] pubupdateBills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggLhQtStdTonVO> transferTool = new BillTransferTool<AggLhQtStdTonVO>(
					clientFullVOs);
			AceLhqtstdtonUpdateBP bp = new AceLhqtstdtonUpdateBP();
			AggLhQtStdTonVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggLhQtStdTonVO[] pubsendapprovebills(
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills)
			throws BusinessException {
		AceLhqtstdtonSendApproveBP bp = new AceLhqtstdtonSendApproveBP();
		AggLhQtStdTonVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggLhQtStdTonVO[] pubunsendapprovebills(
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills)
			throws BusinessException {
		AceLhqtstdtonUnSendApproveBP bp = new AceLhqtstdtonUnSendApproveBP();
		AggLhQtStdTonVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggLhQtStdTonVO[] pubapprovebills(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhqtstdtonApproveBP bp = new AceLhqtstdtonApproveBP();
		AggLhQtStdTonVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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