package nc.bs.lhprj.lhdc.lhqtstdton.ace.bp;

import nc.bs.lhprj.lhdc.lhqtstdton.plugin.bpplugin.LhqtstdtonPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;

/**
 * �޸ı����BP
 * 
 */
public class AceLhqtstdtonUpdateBP {

	public AggLhQtStdTonVO[] update(AggLhQtStdTonVO[] bills,
			AggLhQtStdTonVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggLhQtStdTonVO> bp = new UpdateBPTemplate<AggLhQtStdTonVO>(
				LhqtstdtonPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggLhQtStdTonVO> processer) {
		// TODO �����
		IRule<AggLhQtStdTonVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("LH11");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<AggLhQtStdTonVO> processer) {
		// TODO ǰ����
		IRule<AggLhQtStdTonVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggLhQtStdTonVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("LH11");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
