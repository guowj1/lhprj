package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdton.plugin.bpplugin.LhqtstdtonPluginPoint;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.itf.lhprj.ILhqtstdtonMaintain;

public class N_LH11_UNAPPROVE extends AbstractPfAction<AggLhQtStdTonVO> {

	@Override
	protected CompareAroundProcesser<AggLhQtStdTonVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdTonVO> processor = new CompareAroundProcesser<AggLhQtStdTonVO>(
				LhqtstdtonPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggLhQtStdTonVO[] processBP(Object userObj,
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggLhQtStdTonVO[] bills = null;
		try {
			ILhqtstdtonMaintain operator = NCLocator.getInstance()
					.lookup(ILhqtstdtonMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
