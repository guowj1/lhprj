package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdday.plugin.bpplugin.LhqtstddayPluginPoint;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.itf.lhprj.ILhqtstddayMaintain;

public class N_LH20_SAVE extends AbstractPfAction<AggLhQtStdDayVO> {

	protected CompareAroundProcesser<AggLhQtStdDayVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdDayVO> processor = new CompareAroundProcesser<AggLhQtStdDayVO>(
				LhqtstddayPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggLhQtStdDayVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggLhQtStdDayVO[] processBP(Object userObj,
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills) {
		ILhqtstddayMaintain operator = NCLocator.getInstance().lookup(
				ILhqtstddayMaintain.class);
		AggLhQtStdDayVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
