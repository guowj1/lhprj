package nc.vo.lhprj.lhdcdccalc;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhdcdccalc.LhDayCostCalcVO")

public class AggLhDayCostCalcVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhDayCostCalcVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhDayCostCalcVO getParentVO(){
	  	return (LhDayCostCalcVO)this.getParent();
	  }
	  
}