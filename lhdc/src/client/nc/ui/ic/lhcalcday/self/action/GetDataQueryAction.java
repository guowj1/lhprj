package nc.ui.ic.lhcalcday.self.action;


import nc.bs.framework.common.NCLocator;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.ic.lhcalcday.self.model.BatchInsterestCalModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.pub.BusinessException;
import nc.itf.ic.ILhcalcdayMaintain;
import nc.vo.ic.lhcalcday.CalcdayVO;
/**
 * ��ѯ��
 * 
 * @author zwf
 * 
 */
public class GetDataQueryAction extends DefaultQueryAction {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248698381581186804L;

	private nc.ui.pubapp.uif2app.view.ShowUpableBillListView editor;

	@Override
	protected void executeQuery(IQueryScheme queryScheme) {
		
		try {
			this.showHintMessage("��ʼ��ѯ����");
			CalcdayVO[] vos = NCLocator.getInstance()
					.lookup(ILhcalcdayMaintain.class).query(queryScheme);
			if (vos == null || vos.length < 1) {
				this.getModel().initModel(null);
				this.showHintMessage("δ�鵽��������������,��ȷ�ϲ�ѯ�������ڲ�ѯ");
				return;
			}
			((BatchInsterestCalModel) this.getModel()).initModel(vos);
			int length = vos.length;
			this.showHintMessage("��ѯ��" + length + "�ŵ��� ");
			//((BatchInsterestCalModel) this.getModel()).setQueryScheme(queryScheme);
		} 
		catch (Exception e) {
			this.showHintMessage("��ѯ����"+e.getMessage());
		}
	}
	
	public static String getString_TrimZeroLenAsNull(Object value) {
		  if ( value==null || value.toString().trim().length()==0 ) {
		    return  null ;
		  }
		  return  value.toString() ;
	}
	
	private String GetDataSource() throws BusinessException
	{
		nc.itf.uap.IUAPQueryBS iUAPQueryBS = (nc.itf.uap.IUAPQueryBS) NCLocator.getInstance()
	    	    .lookup(nc.itf.uap.IUAPQueryBS.class.getName());
			String selectSQL="select clnvalue from uap_whbasecon where clnname='accountname' ";
			Object obj=iUAPQueryBS.executeQuery(selectSQL,new ColumnProcessor());
			String objstr=getString_TrimZeroLenAsNull(obj);
			return objstr;
	}
	
	@Override
	protected void showQueryInfo() {
		
	}

	@Override
	protected boolean isActionEnable() {
		return super.isActionEnable();
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getEditor() {
		return editor;
	}

	public void setEditor(
			nc.ui.pubapp.uif2app.view.ShowUpableBillListView editor) {
		this.editor = editor;
	}

	public void showHintMessage(String hint) {
		ShowStatusBarMsgUtil.showStatusBarMsg(hint, this.getModel()
				.getContext());
	}
}
