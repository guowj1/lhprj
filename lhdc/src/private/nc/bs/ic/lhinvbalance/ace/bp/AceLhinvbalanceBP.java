package nc.bs.ic.lhinvbalance.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;

public class AceLhinvbalanceBP {

	public InvBalanceVO[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<InvBalanceVO>(InvBalanceVO.class).query(querySheme,
				null);
	}
}
