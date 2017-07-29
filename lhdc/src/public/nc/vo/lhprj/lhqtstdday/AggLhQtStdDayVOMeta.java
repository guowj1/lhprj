package nc.vo.lhprj.lhqtstdday;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhQtStdDayVOMeta extends AbstractBillMeta{
	
	public AggLhQtStdDayVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhqtstdday.LhQtStdDayVO.class);
		this.addChildren(nc.vo.lhprj.lhqtstdday.LhQtStdDayDetailVO.class);
	}
}