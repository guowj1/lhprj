package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.itf.ic.ILhdayproductMaintain;

public class N_LHZ2_APPROVE extends AbstractPfAction<AggDayProductHVO> {

	public N_LHZ2_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggDayProductHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggDayProductHVO> processor = new CompareAroundProcesser<AggDayProductHVO>(
				LhdayproductPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggDayProductHVO[] processBP(Object userObj,
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills) {
		AggDayProductHVO[] bills = null;
		ILhdayproductMaintain operator = NCLocator.getInstance().lookup(
				ILhdayproductMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
