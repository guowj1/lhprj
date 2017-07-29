package nc.vo.lhprj.lhqtstdday;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhqtstdday.LhQtStdDayVO")

public class AggLhQtStdDayVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhQtStdDayVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhQtStdDayVO getParentVO(){
	  	return (LhQtStdDayVO)this.getParent();
	  }
	  
}