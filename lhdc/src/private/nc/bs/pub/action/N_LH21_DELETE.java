package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.lhprj.lhdc.lhdcprodcolle.plugin.bpplugin.Lhdc_prodcollePluginPoint;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.itf.lhprj.ILhdcprodcolleMaintain;

public class N_LH21_DELETE extends AbstractPfAction<AggLhProdColleVO> {

	@Override
	protected CompareAroundProcesser<AggLhProdColleVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggLhProdColleVO> processor = new CompareAroundProcesser<AggLhProdColleVO>(
				Lhdc_prodcollePluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggLhProdColleVO[] processBP(Object userObj,
			AggLhProdColleVO[] clientFullVOs, AggLhProdColleVO[] originBills) {
		ILhdcprodcolleMaintain operator = NCLocator.getInstance().lookup(
				ILhdcprodcolleMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
