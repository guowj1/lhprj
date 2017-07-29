package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcprodcolle.plugin.bpplugin.Lhdc_prodcollePluginPoint;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.itf.lhprj.ILhdcprodcolleMaintain;

public class N_LH21_APPROVE extends AbstractPfAction<AggLhProdColleVO> {

	public N_LH21_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggLhProdColleVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhProdColleVO> processor = new CompareAroundProcesser<AggLhProdColleVO>(
				Lhdc_prodcollePluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggLhProdColleVO[] processBP(Object userObj,
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills) {
		AggLhProdColleVO[] bills = null;
		ILhdcprodcolleMaintain operator = NCLocator.getInstance().lookup(
				ILhdcprodcolleMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
