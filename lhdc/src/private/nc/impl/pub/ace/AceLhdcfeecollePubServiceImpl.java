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
	// 新增
	public AggLhFeeCollectVO[] pubinsertBills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggLhFeeCollectVO> transferTool = new BillTransferTool<AggLhFeeCollectVO>(
					clientFullVOs);
			// 调用BP
			AceLhdc_feecolleInsertBP action = new AceLhdc_feecolleInsertBP();
			AggLhFeeCollectVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhdc_feecolleDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggLhFeeCollectVO[] pubupdateBills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggLhFeeCollectVO> transferTool = new BillTransferTool<AggLhFeeCollectVO>(
					clientFullVOs);
			AceLhdc_feecolleUpdateBP bp = new AceLhdc_feecolleUpdateBP();
			AggLhFeeCollectVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggLhFeeCollectVO[] pubsendapprovebills(
			AggLhFeeCollectVO[] clientFullVOs, AggLhFeeCollectVO[] originBills)
			throws BusinessException {
		AceLhdc_feecolleSendApproveBP bp = new AceLhdc_feecolleSendApproveBP();
		AggLhFeeCollectVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggLhFeeCollectVO[] pubunsendapprovebills(
			AggLhFeeCollectVO[] clientFullVOs, AggLhFeeCollectVO[] originBills)
			throws BusinessException {
		AceLhdc_feecolleUnSendApproveBP bp = new AceLhdc_feecolleUnSendApproveBP();
		AggLhFeeCollectVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggLhFeeCollectVO[] pubapprovebills(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdc_feecolleApproveBP bp = new AceLhdc_feecolleApproveBP();
		AggLhFeeCollectVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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