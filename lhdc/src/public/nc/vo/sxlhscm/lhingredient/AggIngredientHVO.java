package nc.vo.sxlhscm.lhingredient;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.sxlhscm.lhingredient.IngredientHVO")

public class AggIngredientHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggIngredientHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public IngredientHVO getParentVO(){
	  	return (IngredientHVO)this.getParent();
	  }
	  
}