package nc.ui.ic.lhingredient.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.ic.ILhingredientMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhingredientMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhingredientMaintain query = NCLocator.getInstance().lookup(
				ILhingredientMaintain.class);
		return query.query(queryScheme);
	}

}