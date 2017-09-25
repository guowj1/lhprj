package nc.itf.ic;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.vo.pub.BusinessException;

public interface ILhingredientMaintain {

	public void delete(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException;

	public AggIngredientHVO[] insert(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException;

	public AggIngredientHVO[] update(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException;

	public AggIngredientHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggIngredientHVO[] save(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException;

	public AggIngredientHVO[] unsave(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException;

	public AggIngredientHVO[] approve(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException;

	public AggIngredientHVO[] unapprove(AggIngredientHVO[] clientFullVOs,
			AggIngredientHVO[] originBills) throws BusinessException;
}
