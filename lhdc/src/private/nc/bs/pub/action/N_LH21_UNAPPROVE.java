package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcprodcolle.plugin.bpplugin.Lhdc_prodcollePluginPoint;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.itf.lhprj.ILhdcprodcolleMaintain;

public class N_LH21_UNAPPROVE extends AbstractPfAction<AggLhProdColleVO> {

	@Override
	protected CompareAroundProcesser<AggLhProdColleVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhProdColleVO> processor = new CompareAroundProcesser<AggLhProdColleVO>(
				Lhdc_prodcollePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggLhProdColleVO[] processBP(Object userObj,
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggLhProdColleVO[] bills = null;
		try {
			ILhdcprodcolleMaintain operator = NCLocator.getInstance()
					.lookup(ILhdcprodcolleMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
