package nc.ui.lhprj.lhdc.lhdcsharerate.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.lhprj.ILhdcsharerateMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhdcsharerateMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhdcsharerateMaintain query = NCLocator.getInstance().lookup(
				ILhdcsharerateMaintain.class);
		return query.query(queryScheme);
	}

}