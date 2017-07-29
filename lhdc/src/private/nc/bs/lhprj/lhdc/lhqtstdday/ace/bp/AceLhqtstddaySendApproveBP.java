package nc.bs.lhprj.lhdc.lhqtstdday.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLhqtstddaySendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggLhQtStdDayVO[] sendApprove(AggLhQtStdDayVO[] clientBills,
			AggLhQtStdDayVO[] originBills) {
		for (AggLhQtStdDayVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggLhQtStdDayVO[] returnVos = new BillUpdate<AggLhQtStdDayVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
