package nc.vo.lhprj.lhdcfeecolle;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhFeeCollectVOMeta extends AbstractBillMeta{
	
	public AggLhFeeCollectVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhdcfeecolle.LhFeeCollectVO.class);
		this.addChildren(nc.vo.lhprj.lhdcfeecolle.LhFeeCollectDetailVO.class);
	}
}