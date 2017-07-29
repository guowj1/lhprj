package nc.bs.lhprj.lhdc.lhqtstdton.ace.bp;

import nc.bs.lhprj.lhdc.lhqtstdton.plugin.bpplugin.LhqtstdtonPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;

/**
 * ��׼��������BP
 */
public class AceLhqtstdtonInsertBP {

	public AggLhQtStdTonVO[] insert(AggLhQtStdTonVO[] bills) {

		InsertBPTemplate<AggLhQtStdTonVO> bp = new InsertBPTemplate<AggLhQtStdTonVO>(
				LhqtstdtonPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggLhQtStdTonVO> processor) {
		// TODO ���������
		IRule<AggLhQtStdTonVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("LH11");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggLhQtStdTonVO> processer) {
		// TODO ����ǰ����
		IRule<AggLhQtStdTonVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("LH11");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
	}
}
