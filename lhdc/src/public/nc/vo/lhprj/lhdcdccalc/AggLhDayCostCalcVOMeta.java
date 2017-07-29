package nc.vo.lhprj.lhdcdccalc;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhDayCostCalcVOMeta extends AbstractBillMeta{
	
	public AggLhDayCostCalcVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhdcdccalc.LhDayCostCalcVO.class);
		this.addChildren(nc.vo.lhprj.lhdcdccalc.LhDayCostCalcDetailVO.class);
	}
}