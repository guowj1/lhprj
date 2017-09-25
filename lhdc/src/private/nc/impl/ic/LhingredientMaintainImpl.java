package nc.impl.ic;

import nc.impl.pub.ace.AceLhingredientPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.itf.ic.ILhingredientMaintain;
import nc.vo.pub.BusinessException;

public class LhingredientMaintainImpl extends AceLhingredientPubServiceImpl
		implements ILhingredientMaintain {

	@Override
	public void delete(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggIngredientHVO[] insert(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggIngredientHVO[] update(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggIngredientHVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggIngredientHVO[] save(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggIngredientHVO[] unsave(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggIngredientHVO[] approve(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggIngredientHVO[] unapprove(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
