package nc.itf.cmp.apply;

import java.util.ArrayList;

import nc.vo.pub.BusinessException;

public interface ILhQueryGlBalance {
	
	/**
	 * ��ѯ�������뵥�����ӵ�����Ƿ��ʱ���������δ����
	 * @param pk_apply
	 * @param pk_org
	 * @param pk_group
	 * @param pk_supplier 
	 * @param String[] accCodes 0 ����Ƿ���Ŀ����  1 �ʱ����Ŀ����
	 * @param String  vPayType ��������
	 * @return ArrayList 0 ����Ƿ��  1 �ʱ���  2 ������δ����
	 * @throws BusinessException
	 */
	public ArrayList queryGlBalance(String pk_apply,String pk_org,String pk_group,String pk_supplier,String[] accCodes,String vPayType) throws BusinessException;
	
	/**
	 * ������ΪOA������ͨ��������NC�е��������ĸ������뵥״̬Ϊ���ύ״̬
	 * @param pk_apply
	 * @throws BusinessException
	 */
	public void updateBillStatus(String pk_apply) throws BusinessException;
	
	
	/**
	 * @param String[] pk_apply_b
	 * @throws BusinessException
	 */
	public void frozenApplyBill(String[] pk_apply_b) throws BusinessException;
}
