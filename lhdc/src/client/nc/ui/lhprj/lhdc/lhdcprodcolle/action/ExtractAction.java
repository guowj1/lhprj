package nc.ui.lhprj.lhdc.lhdcprodcolle.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.lhprj.lhdcprodcolle.LhProdColleDetailVO;

public class ExtractAction extends NCAction {

	private IModelDataManager dataManager;
	private BillForm editor;
	private AbstractAppModel model;

	public ExtractAction() {
		this.setCode("ExtractAction");
		this.setBtnName("提取");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// this.editor.getBillCardPanel().getHeadItem("dbilldate").getValueObject();
		// if (data instanceof AbstractBill) {
		// AbstractBill aggvo = (AbstractBill) SerializationUtils
		// .clone((Serializable) data);
		// String
		// cDate=aggvo.getParentVO().getAttributeValue("dbilldate")==null?"":aggvo.getParentVO().getAttributeValue("dbilldate").toString();
		// String
		// pk_org=aggvo.getParentVO().getAttributeValue("pk_org")==null?"":aggvo.getParentVO().getAttributeValue("pk_org").toString();
		// if("".equals(cDate)){
		// ShowStatusBarMsgUtil.showErrorMsgWithClear("提示", "日期不得为空！", this
		// .getModel().getContext());
		// return;
		// }
		// if("".equals(pk_org)){
		// ShowStatusBarMsgUtil.showErrorMsgWithClear("提示", "组织不得为空！", this
		// .getModel().getContext());
		// return;
		// }
		//
		//
		//
		// }else {
		// throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
		// .getNCLangRes().getStrByID("pubapp_0", "0pubapp-0126")/*
		// * @res
		// * "数据错误"
		// */);
		// }
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
		// 从生产日报完工入库表中查询入库数据
		StringBuilder sql = new StringBuilder(
				"select h.def1 pk_subcorp,s.pk_material pk_marbasclass,nvl(s.innum,0) fqty from uap_dayproduct_h h inner join uap_dayproduct_s s on h.pk_dayproduct_h=s.pk_dayproduct_h ");
		sql.append("where h.dr=0 and s.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("' and substr(h.dbilldate,0,10)='");
		sql.append(cDate);
		sql.append("'");
		ArrayList<LhProdColleDetailVO> alData = (ArrayList<LhProdColleDetailVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(sql.toString(),
						new BeanListProcessor(LhProdColleDetailVO.class));

		int iRowNo = 10;
		if (alData != null && alData.size() > 0) {
			LhProdColleDetailVO[] bodyVOsNew = alData
					.toArray(new LhProdColleDetailVO[0]);
			for (LhProdColleDetailVO bvo : bodyVOsNew) {
				bvo.setCrowno(String.valueOf(iRowNo));
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
