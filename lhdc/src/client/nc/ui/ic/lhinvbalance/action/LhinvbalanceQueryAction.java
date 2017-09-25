package nc.ui.ic.lhinvbalance.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;

public class LhinvbalanceQueryAction extends DefaultQueryAction {

	private static final long serialVersionUID = 1L;
	private nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable editor;
	private nc.ui.pubapp.uif2app.view.OrgPanel orgPanel;

	public nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable getEditor() {
		return editor;
	}

	public void setEditor(
			nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable editor) {
		this.editor = editor;
	}

	public nc.ui.pubapp.uif2app.view.OrgPanel getOrgPanel() {
		return orgPanel;
	}

	public void setOrgPanel(nc.ui.pubapp.uif2app.view.OrgPanel orgPanel) {
		this.orgPanel = orgPanel;
	}

	@Override
	protected void executeQuery(IQueryScheme queryScheme) {
		// TODO 自动生成的方法存根
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		QueryCondition org = processor.getQueryCondition(InvBalanceVO.PK_ORG);
		if (null != org) {
			String pk_org = org.getValues()[0].toString();
			this.getOrgPanel().setPkOrg(pk_org);
		}

		super.executeQuery(queryScheme);
	}

}
