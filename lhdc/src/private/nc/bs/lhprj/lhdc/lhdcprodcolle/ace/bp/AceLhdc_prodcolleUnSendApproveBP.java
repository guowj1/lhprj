package nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLhdc_prodcolleUnSendApproveBP {

	public AggLhProdColleVO[] unSend(AggLhProdColleVO[] clientBills,
			AggLhProdColleVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggLhProdColleVO> update = new BillUpdate<AggLhProdColleVO>();
		AggLhProdColleVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggLhProdColleVO[] clientBills) {
		for (AggLhProdColleVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
