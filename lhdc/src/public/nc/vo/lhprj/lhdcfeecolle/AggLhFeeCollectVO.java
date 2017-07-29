package nc.vo.lhprj.lhdcfeecolle;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhdcfeecolle.LhFeeCollectVO")

public class AggLhFeeCollectVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhFeeCollectVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhFeeCollectVO getParentVO(){
	  	return (LhFeeCollectVO)this.getParent();
	  }
	  
}