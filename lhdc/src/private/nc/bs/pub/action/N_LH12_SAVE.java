package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcsharerate.plugin.bpplugin.LhdcshareratePluginPoint;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.itf.lhprj.ILhdcsharerateMaintain;

public class N_LH12_SAVE extends AbstractPfAction<AggLhShareRateVO> {

	protected CompareAroundProcesser<AggLhShareRateVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhShareRateVO> processor = new CompareAroundProcesser<AggLhShareRateVO>(
				LhdcshareratePluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggLhShareRateVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggLhShareRateVO[] processBP(Object userObj,
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills) {
		ILhdcsharerateMaintain operator = NCLocator.getInstance().lookup(
				ILhdcsharerateMaintain.class);
		AggLhShareRateVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
