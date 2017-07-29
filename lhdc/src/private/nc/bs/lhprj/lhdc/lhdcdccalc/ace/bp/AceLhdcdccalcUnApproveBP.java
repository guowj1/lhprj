package nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhdcdccalcUnApproveBP {

	public AggLhDayCostCalcVO[] unApprove(AggLhDayCostCalcVO[] clientBills,
			AggLhDayCostCalcVO[] originBills) {
		for (AggLhDayCostCalcVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhDayCostCalcVO> update = new BillUpdate<AggLhDayCostCalcVO>();
		AggLhDayCostCalcVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
