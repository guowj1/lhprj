package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcdccalc.plugin.bpplugin.LhdcdccalcPluginPoint;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.itf.lhprj.ILhdcdccalcMaintain;

public class N_LH23_DELETE extends AbstractPfAction<AggLhDayCostCalcVO> {

	@Override
	protected CompareAroundProcesser<AggLhDayCostCalcVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhDayCostCalcVO> processor = new CompareAroundProcesser<AggLhDayCostCalcVO>(
				LhdcdccalcPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggLhDayCostCalcVO[] processBP(Object userObj,
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills) {
		ILhdcdccalcMaintain operator = NCLocator.getInstance().lookup(
				ILhdcdccalcMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
