package nc.ui.ic.lhdayproduct.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.ic.ILhdayproductMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhdayproductMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhdayproductMaintain query = NCLocator.getInstance().lookup(
				ILhdayproductMaintain.class);
		return query.query(queryScheme);
	}

}