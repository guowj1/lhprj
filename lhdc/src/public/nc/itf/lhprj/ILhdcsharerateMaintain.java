package nc.itf.lhprj;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.vo.pub.BusinessException;

public interface ILhdcsharerateMaintain {

	public void delete(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException;

	public AggLhShareRateVO[] insert(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException;

	public AggLhShareRateVO[] update(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException;

	public AggLhShareRateVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggLhShareRateVO[] save(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException;

	public AggLhShareRateVO[] unsave(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException;

	public AggLhShareRateVO[] approve(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException;

	public AggLhShareRateVO[] unapprove(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException;
}
