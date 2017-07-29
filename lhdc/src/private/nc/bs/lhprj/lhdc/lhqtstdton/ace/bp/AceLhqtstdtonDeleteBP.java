package nc.bs.lhprj.lhdc.lhqtstdton.ace.bp;

import nc.bs.lhprj.lhdc.lhqtstdton.plugin.bpplugin.LhqtstdtonPluginPoint;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLhqtstdtonDeleteBP {

	public void delete(AggLhQtStdTonVO[] bills) {

		DeleteBPTemplate<AggLhQtStdTonVO> bp = new DeleteBPTemplate<AggLhQtStdTonVO>(
				LhqtstdtonPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhQtStdTonVO> processer) {
		// TODO ǰ����
		IRule<AggLhQtStdTonVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhQtStdTonVO> processer) {
		// TODO �����

	}
}
