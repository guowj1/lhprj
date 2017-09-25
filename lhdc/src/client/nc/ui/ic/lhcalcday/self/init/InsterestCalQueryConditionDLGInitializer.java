package nc.ui.ic.lhcalcday.self.init;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;

public class InsterestCalQueryConditionDLGInitializer implements
		IQueryConditionDLGInitializer {

	@Override
	public void initQueryConditionDLG(QueryConditionDLGDelegator delegator) {
		delegator.setPowerEnable(true);
//		delegator.registerNeedPermissionOrgFieldCode("pk_accountingbook");
//
//		delegator.setDefaultValue(null, "pk_accountingbook",
//				QueryDlgUtils.getDefaultOrgUnit());

	}

}
