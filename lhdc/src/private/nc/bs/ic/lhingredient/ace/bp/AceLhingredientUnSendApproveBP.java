package nc.bs.ic.lhingredient.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceLhingredientUnSendApproveBP {

	public AggIngredientHVO[] unSend(AggIngredientHVO[] clientBills,
			AggIngredientHVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggIngredientHVO> update = new BillUpdate<AggIngredientHVO>();
		AggIngredientHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggIngredientHVO[] clientBills) {
		for (AggIngredientHVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
