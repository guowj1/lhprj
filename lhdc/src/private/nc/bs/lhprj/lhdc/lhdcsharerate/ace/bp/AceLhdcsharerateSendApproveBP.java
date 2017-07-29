package nc.bs.lhprj.lhdc.lhdcsharerate.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLhdcsharerateSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggLhShareRateVO[] sendApprove(AggLhShareRateVO[] clientBills,
			AggLhShareRateVO[] originBills) {
		for (AggLhShareRateVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggLhShareRateVO[] returnVos = new BillUpdate<AggLhShareRateVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
