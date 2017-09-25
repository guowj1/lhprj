package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.itf.ic.ILhdayproductMaintain;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;

public class N_LHZ2_DELETE extends AbstractPfAction<AggDayProductHVO> {

	@Override
	protected CompareAroundProcesser<AggDayProductHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggDayProductHVO> processor = new CompareAroundProcesser<AggDayProductHVO>(
				LhdayproductPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggDayProductHVO[] processBP(Object userObj,
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills) {
		ILhdayproductMaintain operator = NCLocator.getInstance().lookup(
				ILhdayproductMaintain.class);
		try {
			for(AggDayProductHVO aggvo:clientFullVOs)
			{
				String begindate=aggvo.getParentVO().getDbilldate().toString().substring(0, 10);
				UFBoolean ischeck=NCLocator.getInstance().lookup(IDayCostCalcService.class).CheckDaySettele(begindate);
				if(!ischeck.booleanValue())
				{
					ExceptionUtils.wrappBusinessException("当日已经成本计算，不允许删除操作！");
				}
			}
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
