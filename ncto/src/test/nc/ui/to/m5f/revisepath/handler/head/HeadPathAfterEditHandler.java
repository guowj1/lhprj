package nc.ui.to.m5f.revisepath.handler.head;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.ui.to.m5f.revisepath.model.PathInvManageModel;
import nc.vo.to.m5f.entity.SettleListLineVO;

public class HeadPathAfterEditHandler implements
		IAppEventHandler<ListHeadAfterEditEvent> {
	private PathInvManageModel model;

	public PathInvManageModel getModel() {
		return this.model;
	}

	@Override
	public void handleAppEvent(ListHeadAfterEditEvent e) {
		String itemKey = e.getKey();
		if (itemKey.equals(SettleListLineVO.NDISCOUNTRATE)) {
			DiscountHander hander = new DiscountHander();
			hander.afterEdit(e, this.model);
		}
		if (itemKey.equals(SettleListLineVO.NDISCOUNTVALUE)) {
			DiscountValueEditHandler hander = new DiscountValueEditHandler();
			hander.afterEdit(e, this.model);
		}

	}

	public void setModel(PathInvManageModel model) {
		this.model = model;
	}

}
