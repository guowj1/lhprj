package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcdccalc.plugin.bpplugin.LhdcdccalcPluginPoint;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.itf.lhprj.ILhdcdccalcMaintain;

public class N_LH23_APPROVE extends AbstractPfAction<AggLhDayCostCalcVO> {

	public N_LH23_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggLhDayCostCalcVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhDayCostCalcVO> processor = new CompareAroundProcesser<AggLhDayCostCalcVO>(
				LhdcdccalcPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggLhDayCostCalcVO[] processBP(Object userObj,
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills) {
		AggLhDayCostCalcVO[] bills = null;
		ILhdcdccalcMaintain operator = NCLocator.getInstance().lookup(
				ILhdcdccalcMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
