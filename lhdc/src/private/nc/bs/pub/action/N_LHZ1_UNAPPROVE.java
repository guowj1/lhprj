package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.ic.lhingredient.plugin.bpplugin.LhingredientPluginPoint;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.itf.ic.ILhingredientMaintain;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;

public class N_LHZ1_UNAPPROVE extends AbstractPfAction<AggIngredientHVO> {

	@Override
	protected CompareAroundProcesser<AggIngredientHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggIngredientHVO> processor = new CompareAroundProcesser<AggIngredientHVO>(
				LhingredientPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggIngredientHVO[] processBP(Object userObj,
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		
		AggIngredientHVO[] bills = null;
		try {
			for(AggIngredientHVO aggvo:clientFullVOs)
			{
				String begindate=aggvo.getParentVO().getDbilldate().toString().substring(0, 10);
				UFBoolean ischeck= NCLocator.getInstance().lookup(IDayCostCalcService.class).CheckDaySettele(begindate);
				if(!ischeck.booleanValue())
				{
					ExceptionUtils.wrappBusinessException("当日已经成本计算，不允许取消审批操作！");
				}
			}
			ILhingredientMaintain operator = NCLocator.getInstance()
					.lookup(ILhingredientMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
