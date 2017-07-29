package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdton.plugin.bpplugin.LhqtstdtonPluginPoint;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.itf.lhprj.ILhqtstdtonMaintain;

public class N_LH11_SAVE extends AbstractPfAction<AggLhQtStdTonVO> {

	protected CompareAroundProcesser<AggLhQtStdTonVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdTonVO> processor = new CompareAroundProcesser<AggLhQtStdTonVO>(
				LhqtstdtonPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggLhQtStdTonVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggLhQtStdTonVO[] processBP(Object userObj,
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills) {
		ILhqtstdtonMaintain operator = NCLocator.getInstance().lookup(
				ILhqtstdtonMaintain.class);
		AggLhQtStdTonVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
