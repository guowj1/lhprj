package nc.vo.lhprj.lhtransbill;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhtransbill.LhTransBillVO")

public class AggLhTransBillVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggLhTransBillVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public LhTransBillVO getParentVO(){
	  	return (LhTransBillVO)this.getParent();
	  }

	@Override
	public LhTransBillDetailVO[] getChildrenVO() {
		return (LhTransBillDetailVO[]) super.getChildrenVO();
	}
	  
	  
}