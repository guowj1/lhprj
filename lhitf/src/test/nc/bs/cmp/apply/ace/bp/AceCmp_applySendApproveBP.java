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
 * ��׼���������BP
 */
public class AceCmp_applySendApproveBP {
  /**
   * ������
   * 
   * @param vos ����VO����
   * @param script ���ݶ����ű�����
   * @return �����ĵ���VO����
 * @throws BusinessException 
   */
  
	public AggApplyVO[] sendApprove(AggApplyVO[] clientBills,
      AggApplyVO[] originBills) throws BusinessException {
     
      
      	// �����޸�ģ��
   		UpdateBPTemplate<AggApplyVO> bp = new UpdateBPTemplate<AggApplyVO>(
   				Cmp_applyPluginPoint.SEND_APPROVE);
   		// ִ��ǰ����
   		this.addBeforeRule(bp.getAroundProcesser());
   		// ִ�к����
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
