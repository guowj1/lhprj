package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.itf.ic.ILhdayproductMaintain;

public class N_LHZ2_UNSAVEBILL extends AbstractPfAction<AggDayProductHVO> {

	@Override
	protected CompareAroundProcesser<AggDayProductHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggDayProductHVO> processor = new CompareAroundProcesser<AggDayProductHVO>(
				LhdayproductPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggDayProductHVO[] processBP(Object userObj,
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills) {
		ILhdayproductMaintain operator = NCLocator.getInstance().lookup(
				ILhdayproductMaintain.class);
		AggDayProductHVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
