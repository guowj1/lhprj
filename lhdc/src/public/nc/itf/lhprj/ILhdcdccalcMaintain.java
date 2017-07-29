package nc.itf.lhprj;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcDetailVO;
import nc.vo.pub.BusinessException;

public interface ILhdcdccalcMaintain {

	public void delete(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException;

	public AggLhDayCostCalcVO[] insert(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException;

	public AggLhDayCostCalcVO[] update(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException;

	public AggLhDayCostCalcVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggLhDayCostCalcVO[] save(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException;

	public AggLhDayCostCalcVO[] unsave(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException;

	public AggLhDayCostCalcVO[] approve(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException;

	public AggLhDayCostCalcVO[] unapprove(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException;

	public LhDayCostCalcDetailVO[] calcCost(String pk_org, String dDate)
			throws BusinessException;
}
