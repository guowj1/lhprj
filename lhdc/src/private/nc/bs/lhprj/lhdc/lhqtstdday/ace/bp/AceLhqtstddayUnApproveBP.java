package nc.bs.lhprj.lhdc.lhqtstdday.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhqtstddayUnApproveBP {

	public AggLhQtStdDayVO[] unApprove(AggLhQtStdDayVO[] clientBills,
			AggLhQtStdDayVO[] originBills) {
		for (AggLhQtStdDayVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhQtStdDayVO> update = new BillUpdate<AggLhQtStdDayVO>();
		AggLhQtStdDayVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
