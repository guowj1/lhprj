package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhqtstdton.plugin.bpplugin.LhqtstdtonPluginPoint;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.itf.lhprj.ILhqtstdtonMaintain;

public class N_LH11_SAVEBASE extends AbstractPfAction<AggLhQtStdTonVO> {

	@Override
	protected CompareAroundProcesser<AggLhQtStdTonVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhQtStdTonVO> processor = null;
		AggLhQtStdTonVO[] clientFullVOs = (AggLhQtStdTonVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggLhQtStdTonVO>(
					LhqtstdtonPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggLhQtStdTonVO>(
					LhqtstdtonPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggLhQtStdTonVO> rule = null;

		return processor;
	}

	@Override
	protected AggLhQtStdTonVO[] processBP(Object userObj,
			AggLhQtStdTonVO[] clientFullVOs, AggLhQtStdTonVO[] originBills) {

		AggLhQtStdTonVO[] bills = null;
		try {
			ILhqtstdtonMaintain operator = NCLocator.getInstance()
					.lookup(ILhqtstdtonMaintain.class);
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
