package nc.vo.lhprj.lhpurcasein;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhpurcasein.LhPurchaseInVO")

public class AggLhPurchaseInVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhPurchaseInVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhPurchaseInVO getParentVO(){
	  	return (LhPurchaseInVO)this.getParent();
	  }
	  
	  @Override
	  public LhPurchaseInDetailVO[] getChildrenVO(){
		  return (LhPurchaseInDetailVO[]) super.getChildrenVO();
	  }
}