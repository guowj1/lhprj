package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcdccalc.plugin.bpplugin.LhdcdccalcPluginPoint;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.itf.lhprj.ILhdcdccalcMaintain;

public class N_LH23_SAVE extends AbstractPfAction<AggLhDayCostCalcVO> {

	protected CompareAroundProcesser<AggLhDayCostCalcVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhDayCostCalcVO> processor = new CompareAroundProcesser<AggLhDayCostCalcVO>(
				LhdcdccalcPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggLhDayCostCalcVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggLhDayCostCalcVO[] processBP(Object userObj,
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills) {
		ILhdcdccalcMaintain operator = NCLocator.getInstance().lookup(
				ILhdcdccalcMaintain.class);
		AggLhDayCostCalcVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
