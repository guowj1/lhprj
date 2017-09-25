package nc.ui.ic.lhcalcday.self.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;
import nc.ui.rcbgl.dialog.FPRangeDialog;
import nc.ui.rcbgl.dialog.RangeSelectedPanel;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class UnSettleDayCalAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -957110124076841402L;

	private nc.ui.pubapp.uif2app.view.ShowUpableBillListView editor;

	private nc.ui.pubapp.uif2app.model.BillManageModel model;
	
	private IModelDataManager dataManager;

	public UnSettleDayCalAction() {
		super.setCode("calcdaydata");
		super.setBtnName("ȡ���ղ��ϳɱ�����");
	}

	@SuppressWarnings("restriction")
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		try {
			RangeSelectedPanel panelCombineBase = new RangeSelectedPanel(null,true);
			FPRangeDialog updateDialog = new FPRangeDialog(panelCombineBase);
			updateDialog.setTitle("ȡ���ղ��ϳɱ���������");
			updateDialog.setSize(400, 200);
			updateDialog.showModal();
			String begindate = updateDialog.getText();
			if(begindate==null) 
			{
				ShowStatusBarMsgUtil.showStatusBarMsg("ȡ���������ڲ�����Ϊ�գ�", this.getModel()
						.getContext());
				return;
			}
			if(begindate.length()<1)
			{
				ShowStatusBarMsgUtil.showStatusBarMsg("ȡ���������ڲ�����Ϊ�գ�", this.getModel()
						.getContext());
				return;
			}
			NCLocator.getInstance().lookup(IDayCostCalcService.class).UnCalcDataDayRCB(begindate);
			ShowStatusBarMsgUtil.showStatusBarMsg("ȡ���ղ��ϳɱ�������ɣ�", this.getModel()	.getContext());
		} 
		catch (Exception ex) {
			//this.showHintMessage("��ѯ����"+e.getMessage());
			ShowStatusBarMsgUtil.showErrorMsg("ʧ��","ȡ���ղ��ϳɱ�����ʧ�ܣ�ʧ��ԭ��Ϊ��"+ex.getMessage(), this.getModel()
					.getContext());
		}
		
	
	}
	
	

	public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getEditor() {
		return editor;
	}

	public void setEditor(
			nc.ui.pubapp.uif2app.view.ShowUpableBillListView editor) {
		this.editor = editor;
	}

	public nc.ui.pubapp.uif2app.model.BillManageModel getModel() {
		return model;
	}

	public void setModel(nc.ui.pubapp.uif2app.model.BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public IModelDataManager getDataManager() {
		return this.dataManager;
	}
	
	public void setDataManager(IModelDataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	public void showHintMessage(String hint) {
		ShowStatusBarMsgUtil.showStatusBarMsg(hint, this.getModel()
				.getContext());
	}
	
	public static String getString_TrimZeroLenAsNull(Object value) {
		  if ( value==null || value.toString().trim().length()==0 ) {
		    return  null ;
		  }
		  return  value.toString() ;
	}

}
