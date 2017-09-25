package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ic.lhingredient.plugin.bpplugin.LhingredientPluginPoint;
import nc.vo.sxlhscm.lhingredient.AggIngredientHVO;
import nc.itf.ic.ILhingredientMaintain;

public class N_LHZ1_UNSAVEBILL extends AbstractPfAction<AggIngredientHVO> {

	@Override
	protected CompareAroundProcesser<AggIngredientHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggIngredientHVO> processor = new CompareAroundProcesser<AggIngredientHVO>(
				LhingredientPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggIngredientHVO[] processBP(Object userObj,
			AggIngredientHVO[] clientFullVOs, AggIngredientHVO[] originBills) {
		ILhingredientMaintain operator = NCLocator.getInstance().lookup(
				ILhingredientMaintain.class);
		AggIngredientHVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
