package nc.itf.ic;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;

public interface ILhinvbalanceMaintain extends ISmartService {

	public InvBalanceVO[] query(IQueryScheme queryScheme)
			throws BusinessException, Exception;
}