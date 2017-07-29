package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdday.plugin.bpplugin.LhqtstddayPluginPoint;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.itf.lhprj.ILhqtstddayMaintain;

public class N_LH20_UNSAVEBILL extends AbstractPfAction<AggLhQtStdDayVO> {

	@Override
	protected CompareAroundProcesser<AggLhQtStdDayVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdDayVO> processor = new CompareAroundProcesser<AggLhQtStdDayVO>(
				LhqtstddayPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggLhQtStdDayVO[] processBP(Object userObj,
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills) {
		ILhqtstddayMaintain operator = NCLocator.getInstance().lookup(
				ILhqtstddayMaintain.class);
		AggLhQtStdDayVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
