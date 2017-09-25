package nc.bs.ic.lhdayproduct.ace.bp;

import nc.bs.ic.lhdayproduct.plugin.bpplugin.LhdayproductPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.vo.sxlhscm.lhdayproduct.DayProductBVO;
import nc.vo.sxlhscm.lhdayproduct.DayProductHVO;

/**
 * 标准单据新增BP
 */
public class AceLhdayproductInsertBP {

	public AggDayProductHVO[] insert(AggDayProductHVO[] bills) {

		InsertBPTemplate<AggDayProductHVO> bp = new InsertBPTemplate<AggDayProductHVO>(
				LhdayproductPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		//保存前需要把表头日期和分厂写到表体def20和def19字段中
//		for(AggDayProductHVO aggvo:bills)
//		{
//			DayProductHVO hvo=aggvo.getParentVO();
//			DayProductBVO[] bvos=(DayProductBVO[])aggvo.getTableVO("uap_dayproduct_b");
//			for(DayProductBVO bvo:bvos)
//			{
//				bvo.setDef20(hvo.getDbilldate().toString());
//				bvo.setDef19(hvo.getDef1());
//			}
//		}
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggDayProductHVO> processor) {
		// TODO 新增后规则
		IRule<AggDayProductHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("LHZ2");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggDayProductHVO> processer) {
		// TODO 新增前规则
		IRule<AggDayProductHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("LHZ2");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
	}
}
