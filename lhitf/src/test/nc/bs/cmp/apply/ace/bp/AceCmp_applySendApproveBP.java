package nc.bs.cmp.apply.ace.bp;

import nc.bs.cmp.apply.plugin.bpplugin.Cmp_applyPluginPoint;
import nc.bs.cmp.apply.rule.ApplyCommitStatusCheckRule;
import nc.bs.cmp.apply.rule.UpdateSendApproveBusistatusRule;
import nc.impl.cmp.apply.action.rule.PostToOaAfterCommit;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.pub.BusinessException;

/**
 * 标准单据送审的BP
 */
public class AceCmp_applySendApproveBP {
  /**
   * 送审动作
   * 
   * @param vos 单据VO数组
   * @param script 单据动作脚本对象
   * @return 送审后的单据VO数组
 * @throws BusinessException 
   */
  
	public AggApplyVO[] sendApprove(AggApplyVO[] clientBills,
      AggApplyVO[] originBills) throws BusinessException {
     
      
      	// 调用修改模板
   		UpdateBPTemplate<AggApplyVO> bp = new UpdateBPTemplate<AggApplyVO>(
   				Cmp_applyPluginPoint.SEND_APPROVE);
   		// 执行前规则
   		this.addBeforeRule(bp.getAroundProcesser());
   		// 执行后规则
   			this.addAfterRule(bp.getAroundProcesser());
   		AggApplyVO[] returnVos = bp.update(clientBills, originBills);
   		return returnVos;
  }
	private void addAfterRule(CompareAroundProcesser<AggApplyVO> processer) throws BusinessException {
		//gwj add start
//		IRule<AggApplyVO> rule = null;
//		rule=new PostToOaAfterCommit();
//		processer.addAfterRule(rule);
		// gwj add end
		
	}
	private void addBeforeRule(CompareAroundProcesser<AggApplyVO> processer) throws BusinessException {

		IRule<AggApplyVO> rule = null;
		ICompareRule<AggApplyVO> compareRule = new ApplyCommitStatusCheckRule();
		processer.addBeforeRule(compareRule);
		
		rule = new UpdateSendApproveBusistatusRule();
		processer.addBeforeRule(rule);
	}
}
