package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcfeecolle.plugin.bpplugin.Lhdc_feecollePluginPoint;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;
import nc.itf.lhprj.ILhdcfeecolleMaintain;

public class N_LH22_SAVEBASE extends AbstractPfAction<AggLhFeeCollectVO> {

	@Override
	protected CompareAroundProcesser<AggLhFeeCollectVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhFeeCollectVO> processor = null;
		AggLhFeeCollectVO[] clientFullVOs = (AggLhFeeCollectVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggLhFeeCollectVO>(
					Lhdc_feecollePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggLhFeeCollectVO>(
					Lhdc_feecollePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggLhFeeCollectVO> rule = null;

		return processor;
	}

	@Override
	protected AggLhFeeCollectVO[] processBP(Object userObj,
			AggLhFeeCollectVO[] clientFullVOs, AggLhFeeCollectVO[] originBills) {

		AggLhFeeCollectVO[] bills = null;
		try {
			ILhdcfeecolleMaintain operator = NCLocator.getInstance()
					.lookup(ILhdcfeecolleMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
