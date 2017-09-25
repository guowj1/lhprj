package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.itf.ic.ILhdayproductMaintain;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;

public class N_LHZ2_SAVEBASE extends AbstractPfAction<AggDayProductHVO> {

	@Override
	protected CompareAroundProcesser<AggDayProductHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggDayProductHVO> processor = null;
		AggDayProductHVO[] clientFullVOs = (AggDayProductHVO[]) this.getVos();
		
		
		
		
		
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggDayProductHVO>(
					LhdayproductPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggDayProductHVO>(
					LhdayproductPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggDayProductHVO> rule = null;

		return processor;
	}

	@Override
	protected AggDayProductHVO[] processBP(Object userObj,
			AggDayProductHVO[] clientFullVOs, AggDayProductHVO[] originBills) {

		AggDayProductHVO[] bills = null;
		try {
			ILhdayproductMaintain operator = NCLocator.getInstance()
					.lookup(ILhdayproductMaintain.class);
			
			for(AggDayProductHVO aggvo:clientFullVOs)
			{
				String begindate=aggvo.getParentVO().getDbilldate().toString().substring(0, 10);
				UFBoolean ischeck=NCLocator.getInstance().lookup(IDayCostCalcService.class).CheckDaySettele(begindate);
				if(!ischeck.booleanValue())
				{
					ExceptionUtils.wrappBusinessException("当日已经成本计算，不允许保存操作！");
				}
			}
			
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
