package nc.ui.ic.lhcalcday.self.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.trade.business.HYPubBO;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;
import nc.ui.rcbgl.dialog.FPRangeDialog;
import nc.ui.rcbgl.dialog.RangeSelectedPanel;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.ic.lhcalcday.CalcdayVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;

public class SettleDayCalAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -957110124076841402L;

	private nc.ui.pubapp.uif2app.view.ShowUpableBillListView editor;

	private nc.ui.pubapp.uif2app.model.BillManageModel model;
	
	private IModelDataManager dataManager;

	public SettleDayCalAction() {
		super.setCode("calcdaydata");
		super.setBtnName("材料计算");
	}

	@SuppressWarnings("restriction")
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		try {
			RangeSelectedPanel panelCombineBase = new RangeSelectedPanel(null,true);
			FPRangeDialog updateDialog = new FPRangeDialog(panelCombineBase);
			updateDialog.setTitle("日材料成本计算条件");
			updateDialog.setSize(400, 200);
			updateDialog.showModal();
			String begindate = updateDialog.getText();
			if(begindate==null) 
			{
				ShowStatusBarMsgUtil.showStatusBarMsg("计算日期不允许为空！", this.getModel()
						.getContext());
				return;
			}
			if(begindate.length()<1)
			{
				ShowStatusBarMsgUtil.showStatusBarMsg("计算日期不允许为空！", this.getModel()
						.getContext());
				return;
			}
			
			UFBoolean ischeck=NCLocator.getInstance().lookup(IDayCostCalcService.class).CheckDaySettele(begindate);
			if(ischeck.booleanValue())
			{
				 NCLocator.getInstance().lookup(IDayCostCalcService.class).CalcDataDayRCB(begindate);
				 CalcdayVO calvo=new CalcdayVO();
				 calvo.setPk_group(this.getModel().getContext().getPk_group());
				 calvo.setPk_org(this.getModel().getContext().getPk_group());
				 calvo.setPk_org_v("~");
				 calvo.setIscalc(UFBoolean.TRUE);
				 calvo.setCalcdate(new UFDate(begindate));
				 calvo.setCreator(this.getModel().getContext().getPk_loginUser());
				 UFDateTime cretetime=new UFDateTime(System.currentTimeMillis());
				 calvo.setCreationtime(cretetime);
				 calvo.setDr(0);
				 CalcdayVO[] calvvos=new CalcdayVO[1];
				 calvvos[0]=calvo;
				 HYPubBO bo = new HYPubBO();
			     bo.insertAry(calvvos);  
				 ShowStatusBarMsgUtil.showStatusBarMsg("日材料成本计算完成！", this.getModel()	.getContext());
			}
			else
			{
				ShowStatusBarMsgUtil.showStatusBarMsg("当日已经计算，不允许重复计算！", this.getModel()	.getContext());
			}
			
		} 
		catch (Exception ex) {
			//this.showHintMessage("查询出错"+e.getMessage());
			ShowStatusBarMsgUtil.showErrorMsg("失败","日材料成本计算失败，失败原因为："+ex.getMessage(), this.getModel()
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
