package nc.bs.lhprj.lhdc.lhqtstdton.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;

/**
 * ��׼������˵�BP
 */
public class AceLhqtstdtonApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggLhQtStdTonVO[] approve(AggLhQtStdTonVO[] clientBills,
			AggLhQtStdTonVO[] originBills) {
		for (AggLhQtStdTonVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggLhQtStdTonVO> update = new BillUpdate<AggLhQtStdTonVO>();
		AggLhQtStdTonVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
