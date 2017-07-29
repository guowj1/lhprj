package nc.bs.lhprj.lhdc.lhqtstdday.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLhqtstddayUnSendApproveBP {

	public AggLhQtStdDayVO[] unSend(AggLhQtStdDayVO[] clientBills,
			AggLhQtStdDayVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggLhQtStdDayVO> update = new BillUpdate<AggLhQtStdDayVO>();
		AggLhQtStdDayVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggLhQtStdDayVO[] clientBills) {
		for (AggLhQtStdDayVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
