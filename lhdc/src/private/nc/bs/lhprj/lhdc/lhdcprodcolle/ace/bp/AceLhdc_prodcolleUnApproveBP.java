package nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhdc_prodcolleUnApproveBP {

	public AggLhProdColleVO[] unApprove(AggLhProdColleVO[] clientBills,
			AggLhProdColleVO[] originBills) {
		for (AggLhProdColleVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhProdColleVO> update = new BillUpdate<AggLhProdColleVO>();
		AggLhProdColleVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
