package nc.bs.lhprj.lhdc.lhqtstdday.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;

/**
 * ��׼������˵�BP
 */
public class AceLhqtstddayApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggLhQtStdDayVO[] approve(AggLhQtStdDayVO[] clientBills,
			AggLhQtStdDayVO[] originBills) {
		for (AggLhQtStdDayVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhQtStdDayVO> update = new BillUpdate<AggLhQtStdDayVO>();
		AggLhQtStdDayVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
