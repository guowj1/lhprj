package nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp;

import nc.bs.lhprj.lhdc.lhdcprodcolle.plugin.bpplugin.Lhdc_prodcollePluginPoint;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLhdc_prodcolleDeleteBP {

	public void delete(AggLhProdColleVO[] bills) {

		DeleteBPTemplate<AggLhProdColleVO> bp = new DeleteBPTemplate<AggLhProdColleVO>(
				Lhdc_prodcollePluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhProdColleVO> processer) {
		// TODO 前规则
		IRule<AggLhProdColleVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhProdColleVO> processer) {
		// TODO 后规则

	}
}
