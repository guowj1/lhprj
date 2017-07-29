package nc.ui.lhprj.lhdc.lhdcdccalc.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.lhprj.ILhdcdccalcMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhdcdccalcMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhdcdccalcMaintain query = NCLocator.getInstance().lookup(
				ILhdcdccalcMaintain.class);
		return query.query(queryScheme);
	}

}