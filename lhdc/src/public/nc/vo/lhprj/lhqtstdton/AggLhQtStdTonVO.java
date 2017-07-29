package nc.vo.lhprj.lhqtstdton;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhqtstdton.LhQtStdTonVO")

public class AggLhQtStdTonVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhQtStdTonVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhQtStdTonVO getParentVO(){
	  	return (LhQtStdTonVO)this.getParent();
	  }
	  
}