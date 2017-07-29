package nc.bs.lhprj.lhdc.lhqtstdton.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLhqtstdtonSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggLhQtStdTonVO[] sendApprove(AggLhQtStdTonVO[] clientBills,
			AggLhQtStdTonVO[] originBills) {
		for (AggLhQtStdTonVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggLhQtStdTonVO[] returnVos = new BillUpdate<AggLhQtStdTonVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
