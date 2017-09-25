package nc.bs.ic.lhingredient.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLhingredientUnApproveBP {

	public AggIngredientHVO[] unApprove(AggIngredientHVO[] clientBills,
			AggIngredientHVO[] originBills) {
		for (AggIngredientHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggIngredientHVO> update = new BillUpdate<AggIngredientHVO>();
		AggIngredientHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
