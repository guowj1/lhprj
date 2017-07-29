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
	// 新增
	public AggLhProdColleVO[] pubinsertBills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggLhProdColleVO> transferTool = new BillTransferTool<AggLhProdColleVO>(
					clientFullVOs);
			// 调用BP
			AceLhdc_prodcolleInsertBP action = new AceLhdc_prodcolleInsertBP();
			AggLhProdColleVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhdc_prodcolleDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggLhProdColleVO[] pubupdateBills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggLhProdColleVO> transferTool = new BillTransferTool<AggLhProdColleVO>(
					clientFullVOs);
			AceLhdc_prodcolleUpdateBP bp = new AceLhdc_prodcolleUpdateBP();
			AggLhProdColleVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggLhProdColleVO[] pubsendapprovebills(
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills)
			throws BusinessException {
		AceLhdc_prodcolleSendApproveBP bp = new AceLhdc_prodcolleSendApproveBP();
		AggLhProdColleVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggLhProdColleVO[] pubunsendapprovebills(
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills)
			throws BusinessException {
		AceLhdc_prodcolleUnSendApproveBP bp = new AceLhdc_prodcolleUnSendApproveBP();
		AggLhProdColleVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggLhProdColleVO[] pubapprovebills(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdc_prodcolleApproveBP bp = new AceLhdc_prodcolleApproveBP();
		AggLhProdColleVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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