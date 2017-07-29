package nc.ui.lhprj.lhdc.lhdcfeecolle.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;

public class CalcFeeAction extends NCAction {

	private IModelDataManager dataManager;
	private BillForm editor;
	private AbstractAppModel model;

	public CalcFeeAction() {
		this.setCode("CalcFeeAction");
		this.setBtnName("·ÑÓÃ»ã×Ü");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		
		
		
		
	}

	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
		// if (null == this.orgPanel && editor instanceof PubShowUpableBillForm)
		// {
		// this.orgPanel = ((PubShowUpableBillForm) editor).getBillOrgPanel();
		// }
	}

	public BillForm getEditor() {
		return this.editor;
	}

	public IModelDataManager getDataManager() {
		return this.dataManager;
	}

	public void setDataManager(IModelDataManager dataManager) {
		this.dataManager = dataManager;
	}

}
