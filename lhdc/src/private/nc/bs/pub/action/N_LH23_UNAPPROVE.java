package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcdccalc.plugin.bpplugin.LhdcdccalcPluginPoint;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.itf.lhprj.ILhdcdccalcMaintain;

public class N_LH23_UNAPPROVE extends AbstractPfAction<AggLhDayCostCalcVO> {

	@Override
	protected CompareAroundProcesser<AggLhDayCostCalcVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhDayCostCalcVO> processor = new CompareAroundProcesser<AggLhDayCostCalcVO>(
				LhdcdccalcPluginPoint.UNAPPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggLhDayCostCalcVO[] processBP(Object userObj,
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggLhDayCostCalcVO[] bills = null;
		try {
			ILhdcdccalcMaintain operator = NCLocator.getInstance()
					.lookup(ILhdcdccalcMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
