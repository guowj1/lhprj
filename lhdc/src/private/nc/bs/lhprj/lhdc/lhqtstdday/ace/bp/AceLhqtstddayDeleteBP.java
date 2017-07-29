package nc.bs.lhprj.lhdc.lhqtstdday.ace.bp;

import nc.bs.lhprj.lhdc.lhqtstdday.plugin.bpplugin.LhqtstddayPluginPoint;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLhqtstddayDeleteBP {

	public void delete(AggLhQtStdDayVO[] bills) {

		DeleteBPTemplate<AggLhQtStdDayVO> bp = new DeleteBPTemplate<AggLhQtStdDayVO>(
				LhqtstddayPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhQtStdDayVO> processer) {
		// TODO 前规则
		IRule<AggLhQtStdDayVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhQtStdDayVO> processer) {
		// TODO 后规则

	}
}
