package nc.itf.cmp.apply;

import java.util.ArrayList;

import nc.vo.pub.BusinessException;

public interface ILhQueryGlBalance {
	
	/**
	 * 查询付款申请单上增加的账面欠款、质保金、已申请未付款
	 * @param pk_apply
	 * @param pk_org
	 * @param pk_group
	 * @param pk_supplier 
	 * @param String[] accCodes 0 账面欠款科目编码  1 质保金科目编码
	 * @param String  vPayType 付款性质
	 * @return ArrayList 0 账面欠款  1 质保金  2 已申请未付款
	 * @throws BusinessException
	 */
	public ArrayList queryGlBalance(String pk_apply,String pk_org,String pk_group,String pk_supplier,String[] accCodes,String vPayType) throws BusinessException;
	
	/**
	 * 更新因为OA审批不通过并且在NC中点击过弃审的付款申请单状态为待提交状态
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
