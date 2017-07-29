package nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp;

import nc.bs.lhprj.lhdc.lhdcfeecolle.plugin.bpplugin.Lhdc_feecollePluginPoint;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLhdc_feecolleDeleteBP {

	public void delete(AggLhFeeCollectVO[] bills) {

		DeleteBPTemplate<AggLhFeeCollectVO> bp = new DeleteBPTemplate<AggLhFeeCollectVO>(
				Lhdc_feecollePluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhFeeCollectVO> processer) {
		// TODO 前规则
		IRule<AggLhFeeCollectVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhFeeCollectVO> processer) {
		// TODO 后规则

	}
}
