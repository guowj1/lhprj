package nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLhdcdccalcSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggLhDayCostCalcVO[] sendApprove(AggLhDayCostCalcVO[] clientBills,
			AggLhDayCostCalcVO[] originBills) {
		for (AggLhDayCostCalcVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggLhDayCostCalcVO[] returnVos = new BillUpdate<AggLhDayCostCalcVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
