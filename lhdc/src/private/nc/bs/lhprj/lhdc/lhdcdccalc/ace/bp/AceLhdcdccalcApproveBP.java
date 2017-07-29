package nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;

/**
 * 标准单据审核的BP
 */
public class AceLhdcdccalcApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggLhDayCostCalcVO[] approve(AggLhDayCostCalcVO[] clientBills,
			AggLhDayCostCalcVO[] originBills) {
		for (AggLhDayCostCalcVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhDayCostCalcVO> update = new BillUpdate<AggLhDayCostCalcVO>();
		AggLhDayCostCalcVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
