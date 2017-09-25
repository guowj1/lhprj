package nc.ui.ic.lhinvbalance.action;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.ui.uif2.editor.BatchBillTable;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;
/**
  batch addLine or insLine action autogen
*/
public class LhinvbalanceAddLineActiona extends BatchAddLineAction {

	private static final long serialVersionUID = 1L;

	
private BatchBillTable editor = null;
	
	public BatchBillTable getEditor() {
		return editor;
	}

	public void setEditor(BatchBillTable editor) {
		this.editor = editor;
	}
	
	@Override
	protected void setDefaultData(Object obj) {
		super.setDefaultData(obj);
		InvBalanceVO singleDocVO = (InvBalanceVO) obj;
		singleDocVO.setPk_group(this.getModel().getContext().getPk_group());
		singleDocVO.setPk_org(this.getModel().getContext().getPk_org());
		
//		UIRefPane refPanel = (UIRefPane) 
//				 editor.getBillCardPanel().getBillModel().getItemByKey("pk_stordoc").getComponent();
//		refPanel.getRefModel().setPk_org(this.getModel().getContext().getPk_org());
		
		UIRefPane drefPanel = (UIRefPane) 
				 editor.getBillCardPanel().getBillModel().getItemByKey("def1").getComponent();
		drefPanel.getRefModel().setPk_group(this.getModel().getContext().getPk_group());
		drefPanel.getRefModel().setPk_org(this.getModel().getContext().getPk_org());
	}

}