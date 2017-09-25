package nc.ui.ic.lhinvbalance.init;

import org.apache.commons.lang.StringUtils;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.util.QueryDlgUtils;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;

public class InvbalanceQueryConditionDLGInitializer implements
		IQueryConditionDLGInitializer {
	@Override
	public void initQueryConditionDLG(
			QueryConditionDLGDelegator condDLGDelegator) {
		this.setDefaultOrgValue(condDLGDelegator);
		// 按用户已分配组织进行过滤的组织条件
		condDLGDelegator
				.registerNeedPermissionOrgFieldCode(InvBalanceVO.PK_ORG);
		condDLGDelegator.setDefaultValue(InvBalanceVO.PK_ORG,
				QueryDlgUtils.getDefaultOrgUnit());
		condDLGDelegator.setPowerEnable(false);
	}

	private void setDefaultOrgValue(QueryConditionDLGDelegator condDLGDelegator) {
		String pk_org = QueryDlgUtils.getDefaultOrgUnit();
		if (StringUtils.isEmpty(pk_org)) {
			return;
		}
		if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
			condDLGDelegator.setDefaultValue(InvBalanceVO.PK_ORG, pk_org);
		}
	}

}
