package nc.itf.lhprj;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.vo.pub.BusinessException;

public interface ILhdcprodcolleMaintain {

	public void delete(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException;

	public AggLhProdColleVO[] insert(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException;

	public AggLhProdColleVO[] update(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException;

	public AggLhProdColleVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggLhProdColleVO[] save(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException;

	public AggLhProdColleVO[] unsave(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException;

	public AggLhProdColleVO[] approve(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException;

	public AggLhProdColleVO[] unapprove(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException;
}
