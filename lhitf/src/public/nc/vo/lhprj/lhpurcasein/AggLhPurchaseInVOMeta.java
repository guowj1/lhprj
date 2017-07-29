package nc.vo.lhprj.lhpurcasein;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhPurchaseInVOMeta extends AbstractBillMeta{
	
	public AggLhPurchaseInVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhpurcasein.LhPurchaseInVO.class);
		this.addChildren(nc.vo.lhprj.lhpurcasein.LhPurchaseInDetailVO.class);
	}
}