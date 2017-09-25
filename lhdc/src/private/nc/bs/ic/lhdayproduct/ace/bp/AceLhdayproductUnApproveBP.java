package nc.bs.ic.lhdayproduct.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhdayproductUnApproveBP {

	public AggDayProductHVO[] unApprove(AggDayProductHVO[] clientBills,
			AggDayProductHVO[] originBills) {
		for (AggDayProductHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggDayProductHVO> update = new BillUpdate<AggDayProductHVO>();
		AggDayProductHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
