package nc.bs.lhprj.lhdc.lhqtstdton.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceLhqtstdtonUnSendApproveBP {

	public AggLhQtStdTonVO[] unSend(AggLhQtStdTonVO[] clientBills,
			AggLhQtStdTonVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggLhQtStdTonVO> update = new BillUpdate<AggLhQtStdTonVO>();
		AggLhQtStdTonVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggLhQtStdTonVO[] clientBills) {
		for (AggLhQtStdTonVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
