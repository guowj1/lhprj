package nc.ui.lhprj.lhdc.lhdcfeecolle.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.lhprj.ILhdcfeecolleMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhdc_feecolleMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhdcfeecolleMaintain query = NCLocator.getInstance().lookup(
				ILhdcfeecolleMaintain.class);
		return query.query(queryScheme);
	}

}