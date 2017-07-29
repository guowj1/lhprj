package nc.ui.cmp.apply.ext;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.cmp.apply.ILhQueryGlBalance;
import nc.ui.cmp.apply.view.ApplyCardView;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.ui.uif2.editor.IEditor;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyVO;
import nc.vo.jcom.lang.StringUtil;

public class LhApplySaveActionInterceptor implements ActionInterceptor {

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

	public IEditor getEditor() {
		return this.editor;
	}

	@Override
	public boolean afterDoActionFailed(Action arg0, ActionEvent arg1,
			Throwable arg2) {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public void afterDoActionSuccessed(Action arg0, ActionEvent arg1) {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean beforeDoAction(Action arg0, ActionEvent arg1) {
		AggApplyVO selectedData = (AggApplyVO) this.editor.getValue();
		if (selectedData == null)
			return true;

		// ArrayList<AggApplyVO> alVos = new ArrayList<AggApplyVO>();
		// for (Object obj : objs) {
		// AggApplyVO vo = (AggApplyVO) obj;
		// alVos.add(vo);
		// }

		((ApplyCardView) this.editor).getBillCardPanel().setHeadItem(
				ApplyVO.VDEF2, "");// 账面欠款
		((ApplyCardView) this.editor).getBillCardPanel().setHeadItem(
				ApplyVO.VDEF3, "");// 质保金
		((ApplyCardView) this.editor).getBillCardPanel().setHeadItem(
				ApplyVO.VDEF4, "");// 已申请未付款

		// AggApplyVO[] aggVOs = alVos.toArray(new AggApplyVO[0]);
		String[] strCodes;
		ILhQueryGlBalance serv = NCLocator.getInstance().lookup(
				ILhQueryGlBalance.class);
		// for (AggApplyVO vo : aggVOs) {
		String pk_trantypecode = selectedData.getParentVO()
				.getPk_trantypecode();// 交易类型编码
		String vPayType="";//付款性质
//		UIRefPane payTypeRefPanel=(UIRefPane) ((ApplyCardView) this.editor).getBillCardPanel().getHeadItem("vdef11").getComponent();
//		vPayType=payTypeRefPanel.getRefModel().getRefNameValue();
		strCodes = getAccCodeByTranType(pk_trantypecode);
		vPayType=selectedData.getParentVO().getVdef11();
		if (strCodes != null && strCodes.length > 0
				&& !StringUtil.isEmptyWithTrim(strCodes[0])) {
			// String pk_acceptorg = vo.getParentVO().getPk_acceptorg();//
			// 付款财务组织
			String pk_org = selectedData.getParentVO().getPk_org();// 申请付款财务组织
			String pk_group = selectedData.getParentVO().getPk_group();
			String pk_supplier = selectedData.getParentVO().getPk_supplier();
			String pk_apply = selectedData.getParentVO().getPk_apply();
			if (StringUtil.isEmptyWithTrim(pk_org)) {
				ShowStatusBarMsgUtil.showErrorMsg("立恒保存插件执行失败",
						"申请付款财务组织不允许为空！", this.getModel().getContext());
				return false;
			}
			if (StringUtil.isEmptyWithTrim(pk_supplier)) {
				ShowStatusBarMsgUtil.showErrorMsg("立恒保存插件执行失败", "供应商不允许为空！",
						this.getModel().getContext());
				return false;
			}
			try {
				ArrayList alResult = null;

				alResult = (ArrayList) serv.queryGlBalance(pk_apply, pk_org,
						pk_group, pk_supplier, strCodes,vPayType);
				if (alResult != null && alResult.size() > 0) {
					((ApplyCardView) this.editor).getBillCardPanel()
							.setHeadItem(ApplyVO.VDEF2, alResult.get(0));// 账面欠款
					((ApplyCardView) this.editor).getBillCardPanel()
							.setHeadItem(ApplyVO.VDEF3, alResult.get(1));// 质保金
					((ApplyCardView) this.editor).getBillCardPanel()
							.setHeadItem(ApplyVO.VDEF4, alResult.get(2));// 已申请未付款
				}
			} catch (Exception err) {
				ShowStatusBarMsgUtil.showErrorMsg("立恒保存插件执行失败", "取账面欠款遇到异常！"
						+ err.getMessage(), this.getModel().getContext());
			}
		}

		// }

		return true;

	}

	protected String[] getAccCodeByTranType(String pk_trantypecode) {
		String[] strCodes = new String[2];
		if ("36D1-Cxx-01".equals(pk_trantypecode)) {
			strCodes[0] = "220201";
			strCodes[1] = "220205";
		} else if ("36D1-Cxx-02".equals(pk_trantypecode)) {
			strCodes[0] = "220202";
			strCodes[1] = "220205";
		} else if ("36D1-Cxx-03".equals(pk_trantypecode)) {
			strCodes[0] = "220203";
			strCodes[1] = "220205";
		} else if ("36D1-Cxx-04".equals(pk_trantypecode)) {
			strCodes[0] = "220204";
			strCodes[1] = "224103";
		}
		return strCodes;
	}

}
