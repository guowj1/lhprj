package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ic.lhingredient.plugin.bpplugin.LhingredientPluginPoint;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.itf.ic.ILhingredientMaintain;

public class N_LHZ1_APPROVE extends AbstractPfAction<AggIngredientHVO> {

	public N_LHZ1_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggIngredientHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggIngredientHVO> processor = new CompareAroundProcesser<AggIngredientHVO>(
				LhingredientPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggIngredientHVO[] processBP(Object userObj,
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills) {
		AggIngredientHVO[] bills = null;
		ILhingredientMaintain operator = NCLocator.getInstance().lookup(
				ILhingredientMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
