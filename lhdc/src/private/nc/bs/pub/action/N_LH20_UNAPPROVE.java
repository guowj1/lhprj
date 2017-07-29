package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdday.plugin.bpplugin.LhqtstddayPluginPoint;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.itf.lhprj.ILhqtstddayMaintain;

public class N_LH20_UNAPPROVE extends AbstractPfAction<AggLhQtStdDayVO> {

	@Override
	protected CompareAroundProcesser<AggLhQtStdDayVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdDayVO> processor = new CompareAroundProcesser<AggLhQtStdDayVO>(
				LhqtstddayPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggLhQtStdDayVO[] processBP(Object userObj,
			AggLhQtStdDayVO[] clientFullVOs, AggLhQtStdDayVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggLhQtStdDayVO[] bills = null;
		try {
			ILhqtstddayMaintain operator = NCLocator.getInstance()
					.lookup(ILhqtstddayMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
