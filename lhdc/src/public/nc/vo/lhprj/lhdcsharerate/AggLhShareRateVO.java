package nc.vo.lhprj.lhdcsharerate;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhdcsharerate.LhShareRateVO")

public class AggLhShareRateVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhShareRateVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhShareRateVO getParentVO(){
	  	return (LhShareRateVO)this.getParent();
	  }
	  
}