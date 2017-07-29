package nc.ui.cmp.apply.ext;

import java.awt.event.ActionEvent;
import java.util.HashSet;

import nc.bs.framework.common.NCLocator;
import nc.itf.cmp.apply.ILhQueryGlBalance;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveStatus;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.tmpub.util.SqlUtil;

public class LhApplyFrozenAction extends NCAction {

	protected BillManageModel model;
	private IModelDataManager dataManager;
//	private BillForm billform;
	private BillListView listView;

	public BillManageModel getModel() {
		return this.model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public IModelDataManager getDataManager() {
		return this.dataManager;
	}

	public void setDataManager(IModelDataManager dataManager) {
		this.dataManager = dataManager;
	}

//	public BillForm getBillform() {
//		return this.billform;
//	}
	
//	public void setBillform(BillForm billform) {
//		this.billform = billform;
//	}

	public BillListView getListView() {
		return this.listView;
	}

	public void setListView(BillListView listView) {
		this.listView = listView;
	}

	public LhApplyFrozenAction() {
		super.setCode("FrozenLineAction");
		super.setBtnName("冻结行");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		if (this.getModel().getSelectedData() != null) {
			// 获取选中的表体行
//			int[] iRows = this.billform.getBillCardPanel().getBillTable()
//					.getSelectedRows();
			int[] iRowsList = this.listView.getBillListPanel().getBodyTable()
					.getSelectedRows();
			HashSet<String> hsPkBody = new HashSet<String>();
//			if (iRows != null && iRows.length > 0) {
//				for (int iRow : iRows) {
//					hsPkBody.add(this.billform.getBillCardPanel()
//							.getBodyValueAt(iRow, "pk_apply_b").toString());
//				}
//				if (hsPkBody != null && hsPkBody.size() > 0) {
//					updateRowStatus(hsPkBody.toArray(new String[0]));
//				}
//			}
			if (iRowsList != null && iRowsList.length > 0) {
				for (int iRow : iRowsList) {
					hsPkBody.add(this.listView.getBillListPanel()
							.getBillListData().getBodyBillModel()
							.getValueAt(iRow, "pk_apply_b").toString());
				}
				if (hsPkBody != null && hsPkBody.size() > 0) {
					updateRowStatus(hsPkBody.toArray(new String[0]));
				}
//				return;
			}
			// if (this.getModel().getSelectedOperaDatas().length > 1) {
			// ShowStatusBarMsgUtil.showErrorMsg("立恒更新单据状态遇到异常",
			// "不能批量更新单据状态，只允许选中一张单据！", this.getModel().getContext());
			// return;
			// }
			// AggApplyVO aggVO = (AggApplyVO)
			// this.getModel().getSelectedData();
			// String pk_apply = aggVO.getParentVO().getPk_apply();
			// ILhQueryGlBalance srv = NCLocator.getInstance().lookup(
			// ILhQueryGlBalance.class);
			// try {
			// srv.updateBillStatus(pk_apply);
			// } catch (BusinessException err) {
			// ShowStatusBarMsgUtil.showErrorMsg("立恒更新单据状态遇到异常",
			// err.getMessage(), this.getModel().getContext());
			// return;
			// }
			this.getDataManager().refresh();
			ShowStatusBarMsgUtil.showStatusBarMsg("选中行已冻结！", this.getModel()
					.getContext());
		}

	}

	@Override
	protected boolean isActionEnable() {
		if (this.getModel().getUiState() == UIState.NOT_EDIT) {
			if (this.getModel().getSelectedData() != null) {
				// 只有选中行均为非冻结态才允许点击按钮
				AggApplyVO aggVO = (AggApplyVO) this.getModel()
						.getSelectedData();
				if (!aggVO.getParentVO().getVbillstatus()
						.equals(ApproveStatus.FREE)) {
					// && aggVO.getParentVO().getBusistatus().equals(2)) {
//					int[] iRows = this.billform.getBillCardPanel()
//							.getBillTable().getSelectedRows();
					int[] iRowsList = this.listView.getBillListPanel()
							.getBodyTable().getSelectedRows();
//					if (iRows != null && iRows.length > 0) {
//						for (int iRow : iRows) {
//							if (this.billform.getBillCardPanel()
//									.getBodyValueAt(iRow, "isfrozen")
//									.equals(UFBoolean.TRUE)) {
//								return false;
//							}
//							return true;
//						}
//					}
					if (iRowsList != null && iRowsList.length > 0) {
						for (int iRow : iRowsList) {
							boolean bFrozen = (boolean) this.listView
									.getBillListPanel().getBillListData()
									.getBodyBillModel()
									.getValueAt(iRow, "isfrozen");
							if (bFrozen == true) {
								return false;
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	protected void updateRowStatus(String[] pk_bodys) throws BusinessException {
		NCLocator.getInstance().lookup(ILhQueryGlBalance.class)
				.frozenApplyBill(pk_bodys);
	}
}
