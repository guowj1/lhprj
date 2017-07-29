package nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhdcsharerateUnApproveBP {

	public AggLhShareRateVO[] unApprove(AggLhShareRateVO[] clientBills,
			AggLhShareRateVO[] originBills) {
		for (AggLhShareRateVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhShareRateVO> update = new BillUpdate<AggLhShareRateVO>();
		AggLhShareRateVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
