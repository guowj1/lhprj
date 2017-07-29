package nc.vo.lhprj.lhsaleorder;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lhprj.lhsaleorder.LhSaleOrderVO")
public class AggLhSaleOrderVO extends AbstractBill {

	@Override
	public IBillMeta getMetaData() {
		IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
				AggLhSaleOrderVOMeta.class);
		return billMeta;
	}

	@Override
	public LhSaleOrderVO getParentVO() {
		return (LhSaleOrderVO) this.getParent();
	}

	@Override
	public LhSaleOrderDetailVO[] getChildrenVO() {
		return (LhSaleOrderDetailVO[]) super.getChildrenVO();
	}

}