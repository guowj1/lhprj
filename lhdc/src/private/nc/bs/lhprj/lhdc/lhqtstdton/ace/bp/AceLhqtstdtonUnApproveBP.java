package nc.bs.lhprj.lhdc.lhqtstdton.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhqtstdtonUnApproveBP {

	public AggLhQtStdTonVO[] unApprove(AggLhQtStdTonVO[] clientBills,
			AggLhQtStdTonVO[] originBills) {
		for (AggLhQtStdTonVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhQtStdTonVO> update = new BillUpdate<AggLhQtStdTonVO>();
		AggLhQtStdTonVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
