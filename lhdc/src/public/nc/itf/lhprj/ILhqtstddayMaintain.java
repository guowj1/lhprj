package nc.itf.lhprj;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.vo.pub.BusinessException;

public interface ILhqtstddayMaintain {

	public void delete(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException;

	public AggLhQtStdDayVO[] insert(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException;

	public AggLhQtStdDayVO[] update(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException;

	public AggLhQtStdDayVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggLhQtStdDayVO[] save(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException;

	public AggLhQtStdDayVO[] unsave(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException;

	public AggLhQtStdDayVO[] approve(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException;

	public AggLhQtStdDayVO[] unapprove(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException;
}
