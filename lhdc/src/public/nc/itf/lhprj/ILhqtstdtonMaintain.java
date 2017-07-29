package nc.itf.lhprj;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.vo.pub.BusinessException;

public interface ILhqtstdtonMaintain {

	public void delete(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException;

	public AggLhQtStdTonVO[] insert(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException;

	public AggLhQtStdTonVO[] update(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException;

	public AggLhQtStdTonVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggLhQtStdTonVO[] save(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException;

	public AggLhQtStdTonVO[] unsave(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException;

	public AggLhQtStdTonVO[] approve(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException;

	public AggLhQtStdTonVO[] unapprove(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException;
}
