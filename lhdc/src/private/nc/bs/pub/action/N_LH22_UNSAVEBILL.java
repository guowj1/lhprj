package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcfeecolle.plugin.bpplugin.Lhdc_feecollePluginPoint;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;
import nc.itf.lhprj.ILhdcfeecolleMaintain;

public class N_LH22_UNSAVEBILL extends AbstractPfAction<AggLhFeeCollectVO> {

	@Override
	protected CompareAroundProcesser<AggLhFeeCollectVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhFeeCollectVO> processor = new CompareAroundProcesser<AggLhFeeCollectVO>(
				Lhdc_feecollePluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggLhFeeCollectVO[] processBP(Object userObj,
			AggLhFeeCollectVO[] clientFullVOs, AggLhFeeCollectVO[] originBills) {
		ILhdcfeecolleMaintain operator = NCLocator.getInstance().lookup(
				ILhdcfeecolleMaintain.class);
		AggLhFeeCollectVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
