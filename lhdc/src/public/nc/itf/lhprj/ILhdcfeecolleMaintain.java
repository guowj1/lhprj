package nc.itf.lhprj;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;
import nc.vo.pub.BusinessException;

public interface ILhdcfeecolleMaintain {

	public void delete(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException;

	public AggLhFeeCollectVO[] insert(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException;

	public AggLhFeeCollectVO[] update(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException;

	public AggLhFeeCollectVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggLhFeeCollectVO[] save(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException;

	public AggLhFeeCollectVO[] unsave(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException;

	public AggLhFeeCollectVO[] approve(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException;

	public AggLhFeeCollectVO[] unapprove(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException;
}
