package nc.ui.to.m5f.revisepath.handler.head;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.list.ListBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.ui.pubapp.uif2app.event.list.ListHeadBeforeEditEvent;
import nc.ui.to.m5f.revisepath.handler.util.PriceMnyCalc;
import nc.ui.to.m5f.revisepath.model.InvPathManageModel;
import nc.ui.to.m5f.revisepath.model.PathInvManageModel;
import nc.ui.to.m5f.revisepath.util.RevisePathContext;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.to.m5f.entity.STInvDisplayVO;
import nc.vo.to.m5f.entity.STPathInvAggVO;
import nc.vo.to.m5f.entity.SettleListItemVO;
import nc.vo.to.m5f.entity.SettleListLineVO;

public class DiscountValueEditHandler {
	// private static UFDouble ufd100 = new UFDouble(100);

	public void afterEdit(ListHeadAfterEditEvent e, PathInvManageModel model) {
		RevisePathContext context = model.getRevisecontext();

		Object odiscount = e.getValue();
		UFDouble discount = null;
		try {
			discount = new UFDouble(odiscount.toString());
		} catch (NumberFormatException e1) {
			e.getBillListPanel()
					.getHeadBillModel()
					.setValueAt(new UFDouble(0), e.getRow(),
							SettleListLineVO.NDISCOUNTVALUE);
			return;
		}
//		if (discount.getDouble() <= 0) {
//			e.getBillListPanel()
//					.getHeadBillModel()
//					.setValueAt(new UFDouble(0), e.getRow(),
//							SettleListLineVO.NDISCOUNTVALUE);
//			return;
//		}
//		int row = model.getRowCount();
//		if (e.getRow() == row - 1
//				&& !MathTool.equals(discount, new UFDouble(0))) {
//			e.getBillListPanel()
//					.getHeadBillModel()
//					.setValueAt(new UFDouble(0), e.getRow(),
//							SettleListLineVO.NDISCOUNTVALUE);
//
//			MessageDialog
//					.showErrorDlg(e.getBillListPanel(), null,
//							nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
//									.getStrByID("4009008_0", "04009008-0008")/*
//																			 * @res
//																			 * "最后一行折扣率必须为100"
//																			 */);
//			return;
//		}

//		UFDouble ndiscount = new UFDouble(odiscount.toString()).div(100d);
		UFDouble ndiscount = new UFDouble(odiscount.toString());
		ScaleUtils scale = new ScaleUtils(model.getContext().getPk_group());
		boolean taxfirst = context.isTaxFirst();
		STPathInvAggVO aggvo = (STPathInvAggVO) model.getSelectedData();
		STInvDisplayVO[] items = aggvo.getItemVOs();
		PriceMnyCalc util = new PriceMnyCalc(e.getBillListPanel());
		for (int i = 0; i < items.length; i++) {
			e.getBillListPanel()
					.getBodyBillModel()
					.setValueAt(odiscount, i, SettleListItemVO.BB_NDISCOUNTVALUE);
			STInvDisplayVO item = items[i];
			if ((taxfirst ? item.getNorigtaxprice() : item.getNorigprice()) == null) {
				continue;
			}
			UFDouble baseprice = taxfirst ? item.getNorigtaxprice() : item
					.getNorigprice();
			UFDouble pathprice = scale.adjustPriceScale(baseprice
					.add(ndiscount));
			String changeKey = taxfirst ? SettleListItemVO.BB_NORIGTAXPRICE
					: SettleListItemVO.BB_NORIGPRICE;
			e.getBillListPanel().getBodyBillModel()
					.setValueAt(pathprice, i, changeKey);
			e.getBillListPanel().getBodyBillModel().setValueAt(baseprice, i, SettleListItemVO.NQTORIGTAXPRICENEW);
			util.calcInvOutField(changeKey, e.getRow(), i, model);

		}

	}

	public void beforeEdit(ListBodyBeforeEditEvent e, InvPathManageModel model) {
		RevisePathContext context = model.getRevisecontext();
		if (context.getBdiscountflag().booleanValue()) {
			e.setReturnValue(Boolean.TRUE);
		} else {
			e.setReturnValue(Boolean.FALSE);
		}
	}

	public void beforeEdit(ListBodyBeforeEditEvent e, PathInvManageModel model) {
		RevisePathContext context = model.getRevisecontext();
		if (context.getBdiscountflag().booleanValue()) {
			e.setReturnValue(Boolean.TRUE);
		} else {
			e.setReturnValue(Boolean.FALSE);
		}
	}

	public void beforeEdit(ListHeadBeforeEditEvent e, PathInvManageModel model) {
		RevisePathContext context = model.getRevisecontext();
		if (context.getBdiscountflag().booleanValue()) {
			e.setReturnValue(Boolean.TRUE);
		} else {
			e.setReturnValue(Boolean.FALSE);
		}

	}
}
