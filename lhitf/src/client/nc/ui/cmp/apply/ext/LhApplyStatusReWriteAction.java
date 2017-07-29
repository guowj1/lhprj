package nc.ui.cmp.apply.ext;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.cmp.apply.ILhQueryGlBalance;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveStatus;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class LhApplyStatusReWriteAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3191281742255756362L;

	protected BillManageModel model;
	private IModelDataManager dataManager;

	public BillManageModel getModel() {
		return this.model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public LhApplyStatusReWriteAction() {
		super.setCode("rwStatusAction");
		super.setBtnName("恢复状态");
	}
	
	public IModelDataManager getDataManager() {
		return this.dataManager;
	}
	
	public void setDataManager(IModelDataManager dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		if (this.getModel().getSelectedData() != null) {
			if(this.getModel().getSelectedOperaDatas().length>1){
				ShowStatusBarMsgUtil.showErrorMsg("立恒更新单据状态遇到异常",
						"不能批量更新单据状态，只允许选中一张单据！", this.getModel().getContext());
				return;
			}
			AggApplyVO aggVO = (AggApplyVO) this.getModel().getSelectedData();
			String pk_apply = aggVO.getParentVO().getPk_apply();
			ILhQueryGlBalance srv = NCLocator.getInstance().lookup(
					ILhQueryGlBalance.class);
			try {
				srv.updateBillStatus(pk_apply);
			} catch (BusinessException err) {
				ShowStatusBarMsgUtil.showErrorMsg("立恒更新单据状态遇到异常",
						err.getMessage(), this.getModel().getContext());
				return;
			}
			this.getDataManager().refresh();
			ShowStatusBarMsgUtil.showStatusBarMsg("单据异常状态更新成功！", this.getModel().getContext());
		}

	}

	@Override
	protected boolean isActionEnable() {
		if (this.getModel().getUiState() == UIState.NOT_EDIT) {
			if (this.getModel().getSelectedData() != null) {
				// 只有vbusistatus
				AggApplyVO aggVO = (AggApplyVO) this.getModel()
						.getSelectedData();
				// AggApplyVO[] aggVOs=(AggApplyVO[])
				// this.getModel().getSelectedOperaDatas();
				// for(AggApplyVO aggVO:aggVOs){
				if (aggVO.getParentVO().getVbillstatus()
						.equals(ApproveStatus.FREE)
						&& aggVO.getParentVO().getBusistatus().equals(2)) {
					return true;
				}
				// }
			}
		}
		// return false;
		return false;
	}

}
