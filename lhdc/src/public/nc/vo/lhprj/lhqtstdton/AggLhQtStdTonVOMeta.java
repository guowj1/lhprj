package nc.vo.lhprj.lhqtstdton;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggLhQtStdTonVOMeta extends AbstractBillMeta{
	
	public AggLhQtStdTonVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.lhprj.lhqtstdton.LhQtStdTonVO.class);
		this.addChildren(nc.vo.lhprj.lhqtstdton.LhQtStdTonDetailVO.class);
	}
}