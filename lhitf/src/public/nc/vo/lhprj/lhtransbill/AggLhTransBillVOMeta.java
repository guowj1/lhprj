package nc.vo.lhprj.lhtransbill;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhTransBillVOMeta extends AbstractBillMeta{
	
	public AggLhTransBillVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhtransbill.LhTransBillVO.class);
		this.addChildren(nc.vo.lhprj.lhtransbill.LhTransBillDetailVO.class);
	}
}