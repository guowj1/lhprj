package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcsharerate.plugin.bpplugin.LhdcshareratePluginPoint;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.itf.lhprj.ILhdcsharerateMaintain;

public class N_LH12_APPROVE extends AbstractPfAction<AggLhShareRateVO> {

	public N_LH12_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggLhShareRateVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhShareRateVO> processor = new CompareAroundProcesser<AggLhShareRateVO>(
				LhdcshareratePluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggLhShareRateVO[] processBP(Object userObj,
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills) {
		AggLhShareRateVO[] bills = null;
		ILhdcsharerateMaintain operator = NCLocator.getInstance().lookup(
				ILhdcsharerateMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
