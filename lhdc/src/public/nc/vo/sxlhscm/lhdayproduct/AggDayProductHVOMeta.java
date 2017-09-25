package nc.vo.sxlhscm.lhdayproduct;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggDayProductHVOMeta extends AbstractBillMeta{
	
	public AggDayProductHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.sxlhscm.lhdayproduct.DayProductHVO.class);
		this.addChildren(nc.vo.sxlhscm.lhdayproduct.DayProductBVO.class);
		this.addChildren(nc.vo.sxlhscm.lhdayproduct.DayProductSVO.class);
		this.addChildren(nc.vo.sxlhscm.lhdayproduct.DayProductIndeVO.class);
	}
}