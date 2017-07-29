package nc.ui.to.m5f.revisepath.handler.head;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadBeforeEditEvent;
import nc.ui.to.m5f.revisepath.model.PathInvManageModel;
import nc.ui.to.m5f.revisepath.view.PathInvListView;
import nc.vo.to.m5f.entity.SettleListLineVO;

public class HeadPathBeforeEditHandler implements
		IAppEventHandler<ListHeadBeforeEditEvent> {
//	private PathInvListView view;
	private PathInvManageModel model;

	public PathInvManageModel getModel() {
		return this.model;
	}

//	public PathInvListView getPathView() {
//		return this.view;
//	}
//
//	public void setPathView(PathInvListView view) {
//		this.view = view;
//	}

	@Override
	public void handleAppEvent(ListHeadBeforeEditEvent e) {

		String key = e.getKey();
		
		if (e.getRow()==this.model.getSelectedRow()) {
			if (key.equals(SettleListLineVO.NDISCOUNTRATE)) {

				DiscountHander hander = new DiscountHander();
				hander.beforeEdit(e, this.model);
				return;
			}
			if (key.equals(SettleListLineVO.NDISCOUNTVALUE)) {

				DiscountValueEditHandler hander = new DiscountValueEditHandler();
				hander.beforeEdit(e, this.model);
				return;
			}
		}

		e.setReturnValue(Boolean.FALSE);

	}

	public void setModel(PathInvManageModel model) {
		this.model = model;
	}
}
