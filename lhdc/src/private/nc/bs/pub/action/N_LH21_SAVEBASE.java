package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcprodcolle.plugin.bpplugin.Lhdc_prodcollePluginPoint;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.itf.lhprj.ILhdcprodcolleMaintain;

public class N_LH21_SAVEBASE extends AbstractPfAction<AggLhProdColleVO> {

	@Override
	protected CompareAroundProcesser<AggLhProdColleVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhProdColleVO> processor = null;
		AggLhProdColleVO[] clientFullVOs = (AggLhProdColleVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggLhProdColleVO>(
					Lhdc_prodcollePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggLhProdColleVO>(
					Lhdc_prodcollePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggLhProdColleVO> rule = null;

		return processor;
	}

	@Override
	protected AggLhProdColleVO[] processBP(Object userObj,
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills) {

		AggLhProdColleVO[] bills = null;
		try {
			ILhdcprodcolleMaintain operator = NCLocator.getInstance()
					.lookup(ILhdcprodcolleMaintain.class);
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
