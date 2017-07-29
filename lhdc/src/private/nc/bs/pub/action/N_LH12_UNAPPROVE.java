package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcsharerate.plugin.bpplugin.LhdcshareratePluginPoint;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.itf.lhprj.ILhdcsharerateMaintain;

public class N_LH12_UNAPPROVE extends AbstractPfAction<AggLhShareRateVO> {

	@Override
	protected CompareAroundProcesser<AggLhShareRateVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhShareRateVO> processor = new CompareAroundProcesser<AggLhShareRateVO>(
				LhdcshareratePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggLhShareRateVO[] processBP(Object userObj,
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggLhShareRateVO[] bills = null;
		try {
			ILhdcsharerateMaintain operator = NCLocator.getInstance()
					.lookup(ILhdcsharerateMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
