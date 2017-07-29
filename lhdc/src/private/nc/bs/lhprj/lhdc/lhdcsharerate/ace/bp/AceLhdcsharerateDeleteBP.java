package nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp;

import nc.bs.lhprj.lhdc.lhdcsharerate.plugin.bpplugin.LhdcshareratePluginPoint;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLhdcsharerateDeleteBP {

	public void delete(AggLhShareRateVO[] bills) {

		DeleteBPTemplate<AggLhShareRateVO> bp = new DeleteBPTemplate<AggLhShareRateVO>(
				LhdcshareratePluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhShareRateVO> processer) {
		// TODO 前规则
		IRule<AggLhShareRateVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhShareRateVO> processer) {
		// TODO 后规则

	}
}
