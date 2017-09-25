package nc.bs.ic.lhdayproduct.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLhdayproductUnSendApproveBP {

	public AggDayProductHVO[] unSend(AggDayProductHVO[] clientBills,
			AggDayProductHVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggDayProductHVO> update = new BillUpdate<AggDayProductHVO>();
		AggDayProductHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggDayProductHVO[] clientBills) {
		for (AggDayProductHVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
