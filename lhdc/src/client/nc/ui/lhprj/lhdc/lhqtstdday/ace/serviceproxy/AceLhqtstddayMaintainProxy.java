package nc.ui.lhprj.lhdc.lhqtstdday.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.lhprj.ILhqtstddayMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceLhqtstddayMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhqtstddayMaintain query = NCLocator.getInstance().lookup(
				ILhqtstddayMaintain.class);
		return query.query(queryScheme);
	}

}