package nc.ui.lhprj.lhdc.lhqtstdton.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.lhprj.ILhqtstdtonMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhqtstdtonMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhqtstdtonMaintain query = NCLocator.getInstance().lookup(
				ILhqtstdtonMaintain.class);
		return query.query(queryScheme);
	}

}