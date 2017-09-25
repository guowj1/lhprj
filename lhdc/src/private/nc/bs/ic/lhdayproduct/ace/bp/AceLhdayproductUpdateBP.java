package nc.bs.ic.lhdayproduct.ace.bp;

import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.vo.sxlhscm.lhdayproduct.DayProductBVO;
import nc.vo.sxlhscm.lhdayproduct.DayProductHVO;

/**
 * 修改保存的BP
 * 
 */
public class AceLhdayproductUpdateBP {

	public AggDayProductHVO[] update(AggDayProductHVO[] bills,
			AggDayProductHVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggDayProductHVO> bp = new UpdateBPTemplate<AggDayProductHVO>(
				LhdayproductPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
	
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggDayProductHVO> processer) {
		// TODO 后规则
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
		// TODO 前规则
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
