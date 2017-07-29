package nc.vo.lhprj.lhdcprodcolle;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhProdColleVOMeta extends AbstractBillMeta{
	
	public AggLhProdColleVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhdcprodcolle.LhProdColleVO.class);
		this.addChildren(nc.vo.lhprj.lhdcprodcolle.LhProdColleDetailVO.class);
	}
}