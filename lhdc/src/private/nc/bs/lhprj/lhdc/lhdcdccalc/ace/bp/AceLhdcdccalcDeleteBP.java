package nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp;

import nc.bs.lhprj.lhdc.lhdcdccalc.plugin.bpplugin.LhdcdccalcPluginPoint;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLhdcdccalcDeleteBP {

	public void delete(AggLhDayCostCalcVO[] bills) {

		DeleteBPTemplate<AggLhDayCostCalcVO> bp = new DeleteBPTemplate<AggLhDayCostCalcVO>(
				LhdcdccalcPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhDayCostCalcVO> processer) {
		// TODO 前规则
		IRule<AggLhDayCostCalcVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhDayCostCalcVO> processer) {
		// TODO 后规则

	}
}
