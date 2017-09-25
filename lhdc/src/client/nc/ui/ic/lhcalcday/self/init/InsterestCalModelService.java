package nc.ui.ic.lhcalcday.self.init;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.uif2.LoginContext;
import nc.itf.ic.ILhcalcdayMaintain;
import nc.vo.ic.lhcalcday.CalcdayVO;

public class InsterestCalModelService implements IAppModelService, IQueryService {

	@Override
	public void delete(Object object) throws Exception {
		// do nothing
	}

	@Override
	public Object insert(Object object) throws Exception {
		return null;
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		//Ë¢ÐÂÊ¹ÓÃ
		CalcdayVO[] vos = NCLocator.getInstance()
				.lookup(ILhcalcdayMaintain.class).query(queryScheme);
		return vos;
	}

	@Override
	public Object update(Object object) throws Exception {
		return null;
	}

}
