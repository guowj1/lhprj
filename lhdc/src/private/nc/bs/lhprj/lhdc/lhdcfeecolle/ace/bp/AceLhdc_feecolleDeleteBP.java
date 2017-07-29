package nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp;

import nc.bs.lhprj.lhdc.lhdcfeecolle.plugin.bpplugin.Lhdc_feecollePluginPoint;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLhdc_feecolleDeleteBP {

	public void delete(AggLhFeeCollectVO[] bills) {

		DeleteBPTemplate<AggLhFeeCollectVO> bp = new DeleteBPTemplate<AggLhFeeCollectVO>(
				Lhdc_feecollePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhFeeCollectVO> processer) {
		// TODO ǰ����
		IRule<AggLhFeeCollectVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhFeeCollectVO> processer) {
		// TODO �����

	}
}
