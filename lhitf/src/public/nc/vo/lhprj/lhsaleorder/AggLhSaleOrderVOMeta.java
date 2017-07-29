package nc.vo.lhprj.lhsaleorder;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhSaleOrderVOMeta extends AbstractBillMeta{
	
	public AggLhSaleOrderVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhsaleorder.LhSaleOrderVO.class);
		this.addChildren(nc.vo.lhprj.lhsaleorder.LhSaleOrderDetailVO.class);
	}
}