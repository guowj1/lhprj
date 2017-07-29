package nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;

/**
 * 标准单据审核的BP
 */
public class AceLhdc_feecolleApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggLhFeeCollectVO[] approve(AggLhFeeCollectVO[] clientBills,
			AggLhFeeCollectVO[] originBills) {
		for (AggLhFeeCollectVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhFeeCollectVO> update = new BillUpdate<AggLhFeeCollectVO>();
		AggLhFeeCollectVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
