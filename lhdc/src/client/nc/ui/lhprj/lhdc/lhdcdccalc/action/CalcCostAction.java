package nc.ui.lhprj.lhdc.lhdcdccalc.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.lhprj.ILhdcdccalcMaintain;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcDetailVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class CalcCostAction extends NCAction {

	private IModelDataManager dataManager;
	private BillForm editor;
	private AbstractAppModel model;

	public CalcCostAction() {
		this.setCode("CalcFeeAction");
		this.setBtnName("成本计算");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		String cDate = this.editor.getBillCardPanel().getHeadItem("dbilldate")
				.getValueObject() == null ? "" : this.editor.getBillCardPanel()
				.getHeadItem("dbilldate").getValueObject().toString();
		String pk_org = this.editor.getBillCardPanel().getHeadItem("pk_org")
				.getValueObject() == null ? "" : this.editor.getBillCardPanel()
				.getHeadItem("pk_org").getValueObject().toString();
		if ("".equals(cDate)) {
			ShowStatusBarMsgUtil.showErrorMsgWithClear("提示", "日期不得为空！", this
					.getModel().getContext());
			return;
		}
		if ("".equals(pk_org)) {
			ShowStatusBarMsgUtil.showErrorMsgWithClear("提示", "组织不得为空！", this
					.getModel().getContext());
			return;
		}
		this.editor.getBillCardPanel().getBillData().setBodyValueVO(null);
		cDate=cDate.substring(0,10);
		LhDayCostCalcDetailVO[] bodyVOsNew = NCLocator.getInstance()
				.lookup(ILhdcdccalcMaintain.class).calcCost(pk_org, cDate);
		if (bodyVOsNew.length > 0) {
			int iRowNo = 10;
			for (LhDayCostCalcDetailVO bvo : bodyVOsNew) {
				bvo.setCrowno(String.valueOf(iRowNo));
				//计算单位材料费及单位成本
				UFDouble fQty=bvo.getFqty()==null?new UFDouble(0):bvo.getFqty();
				UFDouble fMatCost=bvo.getFmatcost()==null?new UFDouble(0):bvo.getFmatcost();
				UFDouble fCostSum=bvo.getFcostsum()==null?new UFDouble(0):bvo.getFcostsum();
				if(!fQty.equals(new UFDouble(0))){
					bvo.setFmatunitcost((fMatCost.div(fQty)).setScale(2, UFDouble.ROUND_UP));
					bvo.setFcostunit((fCostSum.div(fQty)).setScale(2, UFDouble.ROUND_UP));
				}
				iRowNo += 10;
			}
			this.editor.getBillCardPanel().getBillData()
					.setBodyValueVO(bodyVOsNew);
			this.editor.getBillCardPanel().stopEditing();
			this.editor.getBillCardPanel().getBillModel()
					.loadLoadRelationItemValue();
		}

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
