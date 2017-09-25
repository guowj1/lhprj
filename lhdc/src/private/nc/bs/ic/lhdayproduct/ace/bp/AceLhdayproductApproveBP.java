package nc.bs.ic.lhdayproduct.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;

/**
 * ��׼������˵�BP
 */
public class AceLhdayproductApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggDayProductHVO[] approve(AggDayProductHVO[] clientBills,
			AggDayProductHVO[] originBills) {
		for (AggDayProductHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggDayProductHVO> update = new BillUpdate<AggDayProductHVO>();
		AggDayProductHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
