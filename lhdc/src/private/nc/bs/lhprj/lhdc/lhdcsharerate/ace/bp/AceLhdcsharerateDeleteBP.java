package nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp;

import nc.bs.lhprj.lhdc.lhdcsharerate.plugin.bpplugin.LhdcshareratePluginPoint;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLhdcsharerateDeleteBP {

	public void delete(AggLhShareRateVO[] bills) {

		DeleteBPTemplate<AggLhShareRateVO> bp = new DeleteBPTemplate<AggLhShareRateVO>(
				LhdcshareratePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhShareRateVO> processer) {
		// TODO ǰ����
		IRule<AggLhShareRateVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhShareRateVO> processer) {
		// TODO �����

	}
}
