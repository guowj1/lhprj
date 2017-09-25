package nc.bs.ic.lhcalcday.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.ic.lhcalcday.CalcdayVO;

public class AceLhcalcdayBP {

	public CalcdayVO[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<CalcdayVO>(CalcdayVO.class).query(querySheme,
				null);
	}
}
