package nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;

/**
 * ��׼������˵�BP
 */
public class AceLhdcsharerateApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggLhShareRateVO[] approve(AggLhShareRateVO[] clientBills,
			AggLhShareRateVO[] originBills) {
		for (AggLhShareRateVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhShareRateVO> update = new BillUpdate<AggLhShareRateVO>();
		AggLhShareRateVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
