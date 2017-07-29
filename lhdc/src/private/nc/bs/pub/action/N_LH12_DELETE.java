package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcsharerate.plugin.bpplugin.LhdcshareratePluginPoint;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.itf.lhprj.ILhdcsharerateMaintain;

public class N_LH12_DELETE extends AbstractPfAction<AggLhShareRateVO> {

	@Override
	protected CompareAroundProcesser<AggLhShareRateVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhShareRateVO> processor = new CompareAroundProcesser<AggLhShareRateVO>(
				LhdcshareratePluginPoint.SCRIPT_DELETE);
		// TODO �ڴ˴����ǰ�����
		return processor;
	}

	@Override
	protected AggLhShareRateVO[] processBP(Object userObj,
			AggLhShareRateVO[] clientFullVOs, AggLhShareRateVO[] originBills) {
		ILhdcsharerateMaintain operator = NCLocator.getInstance().lookup(
				ILhdcsharerateMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
