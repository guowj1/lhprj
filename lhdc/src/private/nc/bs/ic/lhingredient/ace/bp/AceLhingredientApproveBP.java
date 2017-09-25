package nc.bs.ic.lhingredient.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;

/**
 * ��׼������˵�BP
 */
public class AceLhingredientApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggIngredientHVO[] approve(AggIngredientHVO[] clientBills,
			AggIngredientHVO[] originBills) {
		for (AggIngredientHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggIngredientHVO> update = new BillUpdate<AggIngredientHVO>();
		AggIngredientHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
