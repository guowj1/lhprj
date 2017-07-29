package nc.vo.lhprj.lhdcprodcolle;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhdcprodcolle.LhProdColleVO")

public class AggLhProdColleVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhProdColleVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhProdColleVO getParentVO(){
	  	return (LhProdColleVO)this.getParent();
	  }
	  
}