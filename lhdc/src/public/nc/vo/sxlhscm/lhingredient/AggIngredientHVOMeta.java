package nc.vo.sxlhscm.lhingredient;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggIngredientHVOMeta extends AbstractBillMeta{
	
	public AggIngredientHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.sxlhscm.lhingredient.IngredientHVO.class);
		this.addChildren(nc.vo.sxlhscm.lhingredient.IngredientBVO.class);
	}
}