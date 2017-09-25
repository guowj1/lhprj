package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.itf.ic.ILhdayproductMaintain;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;

public class N_LHZ2_UNAPPROVE extends AbstractPfAction<AggDayProductHVO> {

	@Override
	protected CompareAroundProcesser<AggDayProductHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggDayProductHVO> processor = new CompareAroundProcesser<AggDayProductHVO>(
				LhdayproductPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggDayProductHVO[] processBP(Object userObj,
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggDayProductHVO[] bills = null;
		try {
			ILhdayproductMaintain operator = NCLocator.getInstance()
					.lookup(ILhdayproductMaintain.class);
			
			for(AggDayProductHVO aggvo:clientFullVOs)
			{
				String begindate=aggvo.getParentVO().getDbilldate().toString().substring(0, 10);
				UFBoolean ischeck= NCLocator.getInstance().lookup(IDayCostCalcService.class).CheckDaySettele(begindate);
				if(!ischeck.booleanValue())
				{
					ExceptionUtils.wrappBusinessException("当日已经成本计算，不允许取消审批操作！");
				}
			}
			
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
