package nc.bs.lhprj.lhdc.lhdcprodcolle.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLhdc_prodcolleSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggLhProdColleVO[] sendApprove(AggLhProdColleVO[] clientBills,
			AggLhProdColleVO[] originBills) {
		for (AggLhProdColleVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggLhProdColleVO[] returnVos = new BillUpdate<AggLhProdColleVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}