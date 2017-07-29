package nc.bs.lhprj.lhdc.lhdcfeecolle.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLhdc_feecolleSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggLhFeeCollectVO[] sendApprove(AggLhFeeCollectVO[] clientBills,
			AggLhFeeCollectVO[] originBills) {
		for (AggLhFeeCollectVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggLhFeeCollectVO[] returnVos = new BillUpdate<AggLhFeeCollectVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
