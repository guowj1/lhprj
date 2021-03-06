package nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;

/**
 * 标准单据审核的BP
 */
public class AceLhdc_prodcolleApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggLhProdColleVO[] approve(AggLhProdColleVO[] clientBills,
			AggLhProdColleVO[] originBills) {
		for (AggLhProdColleVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhProdColleVO> update = new BillUpdate<AggLhProdColleVO>();
		AggLhProdColleVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
