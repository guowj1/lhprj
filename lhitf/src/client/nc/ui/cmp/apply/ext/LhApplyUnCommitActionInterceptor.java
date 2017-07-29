package nc.ui.cmp.apply.ext;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.Action;
import javax.swing.JComponent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.ui.uif2.editor.IEditor;
import nc.vo.cmp.apply.AggApplyVO;

public class LhApplyUnCommitActionInterceptor implements ActionInterceptor {

	protected BillManageModel model;
	
	protected IEditor editor;

	public BillManageModel getModel() {
		return this.model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
	
	public void setEditor(IEditor editor) {
		this.editor = editor;
	}
	
	public IEditor getEditor(){
		return this.editor;
	}

	@Override
	public boolean afterDoActionFailed(Action arg0, ActionEvent arg1,
			Throwable arg2) {
		return true;
	}

	@Override
	public void afterDoActionSuccessed(Action arg0, ActionEvent arg1) {

	}

	@Override
	public boolean beforeDoAction(Action arg0, ActionEvent arg1) {
		// 判断单据传递标志，若已经传递到OA系统个，则需要
		Object[] objs = this.getModel().getSelectedOperaDatas();
		if (objs == null)
			return true;
		ArrayList<AggApplyVO> alVos=new ArrayList<AggApplyVO>();
		for (Object obj : objs) {
			AggApplyVO vo=(AggApplyVO) obj;
			alVos.add(vo);
		}
		AggApplyVO[] aggVOs = alVos.toArray(new AggApplyVO[0]);
		HashSet<String> hsCode=new HashSet<String>();
		for(AggApplyVO vo:aggVOs){
			if (vo.getParentVO().getVdef1() != null
					&& !"".equals(vo.getParentVO().getVdef1())) {
				hsCode.add(vo.getParentVO().getVbillno());
			}
		}
		if(hsCode!=null && hsCode.size()>0){
//			if(MessageDialog.showYesNoDlg((JComponent)arg1.getSource(),"提示信息","以下付款申请单已经传递到OA系统，请手工在OA系统中删除对应的付款申请单，是否确定收回？(点击[是]按钮将继续执行)")==UIDialog.ID_NO){
//				return false;
//			}

			ShowStatusBarMsgUtil.showErrorMsg("提示信息", "选中的付款申请单已经传递到OA系统，不允许再撤回！", this
					.getModel().getContext());
			return false;
		}

		return true;
	}

}
