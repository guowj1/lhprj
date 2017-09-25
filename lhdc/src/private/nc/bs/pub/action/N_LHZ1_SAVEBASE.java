package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.ic.lhingredient.plugin.bpplugin.LhingredientPluginPoint;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.itf.ic.ILhingredientMaintain;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;

public class N_LHZ1_SAVEBASE extends AbstractPfAction<AggIngredientHVO> {

	@Override
	protected CompareAroundProcesser<AggIngredientHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggIngredientHVO> processor = null;
		AggIngredientHVO[] clientFullVOs = (AggIngredientHVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggIngredientHVO>(
					LhingredientPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggIngredientHVO>(
					LhingredientPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggIngredientHVO> rule = null;

		return processor;
	}

	@Override
	protected AggIngredientHVO[] processBP(Object userObj,
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills) {

		AggIngredientHVO[] bills = null;
		try {
			ILhingredientMaintain operator = NCLocator.getInstance()
					.lookup(ILhingredientMaintain.class);
			for(AggIngredientHVO aggvo:clientFullVOs)
			{
				String begindate=aggvo.getParentVO().getDbilldate().toString().substring(0, 10);
				UFBoolean ischeck= NCLocator.getInstance().lookup(IDayCostCalcService.class).CheckDaySettele(begindate);
				if(!ischeck.booleanValue())
				{
					ExceptionUtils.wrappBusinessException("当日已经成本计算，不允许保存操作！");
				}
			}
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
