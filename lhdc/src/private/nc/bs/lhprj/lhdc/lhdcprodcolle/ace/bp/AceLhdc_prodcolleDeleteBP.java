package nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp;

import nc.bs.lhprj.lhdc.lhdcprodcolle.plugin.bpplugin.Lhdc_prodcollePluginPoint;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLhdc_prodcolleDeleteBP {

	public void delete(AggLhProdColleVO[] bills) {

		DeleteBPTemplate<AggLhProdColleVO> bp = new DeleteBPTemplate<AggLhProdColleVO>(
				Lhdc_prodcollePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggLhProdColleVO> processer) {
		// TODO ǰ����
		IRule<AggLhProdColleVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggLhProdColleVO> processer) {
		// TODO �����

	}
}
