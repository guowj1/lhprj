package nc.vo.lhprj.lhdcsharerate;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhShareRateVOMeta extends AbstractBillMeta{
	
	public AggLhShareRateVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhdcsharerate.LhShareRateVO.class);
		this.addChildren(nc.vo.lhprj.lhdcsharerate.LhShareRateItemVO.class);
	}
}