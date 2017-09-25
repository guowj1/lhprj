package nc.bs.ic.lhdayproduct.ace.bp;

import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.vo.sxlhscm.lhdayproduct.DayProductBVO;
import nc.vo.sxlhscm.lhdayproduct.DayProductHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceLhdayproductUpdateBP {

	public AggDayProductHVO[] update(AggDayProductHVO[] bills,
			AggDayProductHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggDayProductHVO> bp = new UpdateBPTemplate<AggDayProductHVO>(
				LhdayproductPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
	
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggDayProductHVO> processer) {
		// TODO �����
		IRule<AggDayProductHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("LHZ2");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<AggDayProductHVO> processer) {
		// TODO ǰ����
		IRule<AggDayProductHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggDayProductHVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("LHZ2");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
