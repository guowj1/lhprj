package nc.bs.lhprj.lhdc.lhqtstdday.ace.bp;

import nc.bs.lhprj.lhdc.lhqtstdday.plugin.bpplugin.LhqtstddayPluginPoint;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLhqtstddayDeleteBP {

	public void delete(AggLhQtStdDayVO[] bills) {

		DeleteBPTemplate<AggLhQtStdDayVO> bp = new DeleteBPTemplate<AggLhQtStdDayVO>(
				LhqtstddayPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhQtStdDayVO> processer) {
		// TODO ǰ����
		IRule<AggLhQtStdDayVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhQtStdDayVO> processer) {
		// TODO �����

	}
}
