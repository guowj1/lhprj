package nc.ui.lhprj.lhdc.lhdcprodcolle.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.lhprj.ILhdcprodcolleMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhdc_prodcolleMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhdcprodcolleMaintain query = NCLocator.getInstance().lookup(
				ILhdcprodcolleMaintain.class);
		return query.query(queryScheme);
	}

}