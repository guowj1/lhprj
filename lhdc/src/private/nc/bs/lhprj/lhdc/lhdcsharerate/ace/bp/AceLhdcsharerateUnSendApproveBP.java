package nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceLhdcsharerateUnSendApproveBP {

	public AggLhShareRateVO[] unSend(AggLhShareRateVO[] clientBills,
			AggLhShareRateVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggLhShareRateVO> update = new BillUpdate<AggLhShareRateVO>();
		AggLhShareRateVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggLhShareRateVO[] clientBills) {
		for (AggLhShareRateVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
