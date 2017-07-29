package nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhdc_feecolleUnApproveBP {

	public AggLhFeeCollectVO[] unApprove(AggLhFeeCollectVO[] clientBills,
			AggLhFeeCollectVO[] originBills) {
		for (AggLhFeeCollectVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhFeeCollectVO> update = new BillUpdate<AggLhFeeCollectVO>();
		AggLhFeeCollectVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
