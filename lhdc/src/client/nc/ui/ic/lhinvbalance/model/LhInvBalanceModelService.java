package nc.ui.ic.lhinvbalance.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.ic.ILhinvbalanceMaintain;
import nc.ui.pubapp.pub.smart.SmartBatchAppModelService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

public class LhInvBalanceModelService extends SmartBatchAppModelService
		implements IQueryService {
	private LoginContext context;

	private Class<? extends ILhinvbalanceMaintain> service = null;

	private String voclassName;

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws Exception {
		ILhinvbalanceMaintain servicesave = NCLocator.getInstance().lookup(
				this.service);
		return servicesave.batchSave(batchVO);
	}

	public LoginContext getContext() {
		return this.context;
	}

	public String getService() {
		return this.service.getName();
	}

	@Override
	public String getVoClass() {
		return this.voclassName;
	}

	@Override
	public Object[] queryByDataVisibilitySetting(final LoginContext context1)
			throws Exception {
		if (context1.getPk_org() == null) {
			return null;
		}
		return this.queryByWhereSql(null);
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILhinvbalanceMaintain spservice = NCLocator.getInstance().lookup(
				this.service);
		return spservice.query(queryScheme);
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	public void setService(String serviceName) {
		try {
			this.service = (Class<? extends ILhinvbalanceMaintain>) Class
					.forName(serviceName);
		} catch (ClassNotFoundException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	@Override
	public void setVoClass(String voClass) {
		this.voclassName = voClass;
	}
}
