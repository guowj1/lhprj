package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcprodcolle.plugin.bpplugin.Lhdc_prodcollePluginPoint;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.itf.lhprj.ILhdcprodcolleMaintain;

public class N_LH21_SAVE extends AbstractPfAction<AggLhProdColleVO> {

	protected CompareAroundProcesser<AggLhProdColleVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhProdColleVO> processor = new CompareAroundProcesser<AggLhProdColleVO>(
				Lhdc_prodcollePluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggLhProdColleVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggLhProdColleVO[] processBP(Object userObj,
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills) {
		ILhdcprodcolleMaintain operator = NCLocator.getInstance().lookup(
				ILhdcprodcolleMaintain.class);
		AggLhProdColleVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
