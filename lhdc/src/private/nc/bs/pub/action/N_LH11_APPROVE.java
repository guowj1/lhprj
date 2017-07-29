package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdton.plugin.bpplugin.LhqtstdtonPluginPoint;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.itf.lhprj.ILhqtstdtonMaintain;

public class N_LH11_APPROVE extends AbstractPfAction<AggLhQtStdTonVO> {

	public N_LH11_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggLhQtStdTonVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdTonVO> processor = new CompareAroundProcesser<AggLhQtStdTonVO>(
				LhqtstdtonPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggLhQtStdTonVO[] processBP(Object userObj,
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills) {
		AggLhQtStdTonVO[] bills = null;
		ILhqtstdtonMaintain operator = NCLocator.getInstance().lookup(
				ILhqtstdtonMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
