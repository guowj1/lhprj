package nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp;

import nc.bs.lhprj.lhdc.lhdcdccalc.plugin.bpplugin.LhdcdccalcPluginPoint;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLhdcdccalcDeleteBP {

	public void delete(AggLhDayCostCalcVO[] bills) {

		DeleteBPTemplate<AggLhDayCostCalcVO> bp = new DeleteBPTemplate<AggLhDayCostCalcVO>(
				LhdcdccalcPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhDayCostCalcVO> processer) {
		// TODO ǰ����
		IRule<AggLhDayCostCalcVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhDayCostCalcVO> processer) {
		// TODO �����

	}
}
