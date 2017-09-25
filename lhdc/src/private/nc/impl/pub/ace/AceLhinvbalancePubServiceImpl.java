package nc.impl.pub.ace;
import nc.bs.ic.lhinvbalance.ace.bp.AceLhinvbalanceBP;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;
import nc.vo.uif2.LoginContext;

public abstract class AceLhinvbalancePubServiceImpl extends SmartServiceImpl {
	public InvBalanceVO[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		return new AceLhinvbalanceBP().queryByQueryScheme(querySheme);
	}
}