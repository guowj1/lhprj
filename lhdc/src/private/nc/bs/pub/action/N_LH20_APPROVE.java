package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdday.plugin.bpplugin.LhqtstddayPluginPoint;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.itf.lhprj.ILhqtstddayMaintain;

public class N_LH20_APPROVE extends AbstractPfAction<AggLhQtStdDayVO> {

	public N_LH20_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggLhQtStdDayVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdDayVO> processor = new CompareAroundProcesser<AggLhQtStdDayVO>(
				LhqtstddayPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggLhQtStdDayVO[] processBP(Object userObj,
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills) {
		AggLhQtStdDayVO[] bills = null;
		ILhqtstddayMaintain operator = NCLocator.getInstance().lookup(
				ILhqtstddayMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
