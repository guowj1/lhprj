package nc.itf.ic;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.vo.pub.BusinessException;

public interface ILhdayproductMaintain {

	public void delete(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException;

	public AggDayProductHVO[] insert(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException;

	public AggDayProductHVO[] update(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException;

	public AggDayProductHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggDayProductHVO[] save(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException;

	public AggDayProductHVO[] unsave(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException;

	public AggDayProductHVO[] approve(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException;

	public AggDayProductHVO[] unapprove(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException;
}
