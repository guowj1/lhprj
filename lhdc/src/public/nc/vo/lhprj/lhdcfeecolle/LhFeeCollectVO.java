package nc.vo.lhprj.lhdcfeecolle;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2017-7-4
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhFeeCollectVO extends SuperVO {
	
/**
*����
*/
public String pk_feecolle;
/**
*���ݺ�
*/
public String vbillcode;
/**
*��������
*/
public UFDate dbilldate;
/**
*��ע
*/
public String vnote;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*����
*/
public String pk_group;
/**
*������
*/
public String creator;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*�Ƶ���
*/
public String billmaker;
/**
*�޸���
*/
public String modifier;
/**
*����޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*������
*/
public String approver;
/**
*����ʱ��
*/
public UFDateTime approvetime;
/**
*��������
*/
public String approvenote;
/**
*����״̬
*/
public Integer fbillstatus;
/**
*�Զ�����1
*/
public String vdef1;
/**
*�Զ�����2
*/
public String vdef2;
/**
*�Զ�����3
*/
public String vdef3;
/**
*�Զ�����4
*/
public String vdef4;
/**
*�Զ�����5
*/
public String vdef5;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_feecolle��Getter����.������������
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getPk_feecolle() {
return this.pk_feecolle;
} 

/**
* ����pk_feecolle��Setter����.������������
* ��������:2017-7-4
* @param newPk_feecolle java.lang.String
*/
public void setPk_feecolle ( String pk_feecolle) {
this.pk_feecolle=pk_feecolle;
} 
 
/**
* ���� vbillcode��Getter����.�����������ݺ�
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVbillcode() {
return this.vbillcode;
} 

/**
* ����vbillcode��Setter����.�����������ݺ�
* ��������:2017-7-4
* @param newVbillcode java.lang.String
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
} 
 
/**
* ���� dbilldate��Getter����.����������������
*  ��������:2017-7-4
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.����������������
* ��������:2017-7-4
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* ���� vnote��Getter����.����������ע
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVnote() {
return this.vnote;
} 

/**
* ����vnote��Setter����.����������ע
* ��������:2017-7-4
* @param newVnote java.lang.String
*/
public void setVnote ( String vnote) {
this.vnote=vnote;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2017-7-4
* @return nc.vo.org.FinanceOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-7-4
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2017-7-4
* @return nc.vo.vorg.FinanceOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2017-7-4
* @param newPk_org_v nc.vo.vorg.FinanceOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-7-4
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-7-4
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2017-7-4
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2017-7-4
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2017-7-4
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2017-7-4
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� billmaker��Getter����.���������Ƶ���
*  ��������:2017-7-4
* @return nc.vo.sm.UserVO
*/
public String getBillmaker() {
return this.billmaker;
} 

/**
* ����billmaker��Setter����.���������Ƶ���
* ��������:2017-7-4
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( String billmaker) {
this.billmaker=billmaker;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2017-7-4
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2017-7-4
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.������������޸�ʱ��
*  ��������:2017-7-4
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.������������޸�ʱ��
* ��������:2017-7-4
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� approver��Getter����.��������������
*  ��������:2017-7-4
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return this.approver;
} 

/**
* ����approver��Setter����.��������������
* ��������:2017-7-4
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* ���� approvetime��Getter����.������������ʱ��
*  ��������:2017-7-4
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovetime() {
return this.approvetime;
} 

/**
* ����approvetime��Setter����.������������ʱ��
* ��������:2017-7-4
* @param newApprovetime nc.vo.pub.lang.UFDateTime
*/
public void setApprovetime ( UFDateTime approvetime) {
this.approvetime=approvetime;
} 
 
/**
* ���� approvenote��Getter����.����������������
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getApprovenote() {
return this.approvenote;
} 

/**
* ����approvenote��Setter����.����������������
* ��������:2017-7-4
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* ���� fbillstatus��Getter����.������������״̬
*  ��������:2017-7-4
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getFbillstatus() {
return this.fbillstatus;
} 

/**
* ����fbillstatus��Setter����.������������״̬
* ��������:2017-7-4
* @param newFbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setFbillstatus ( Integer fbillstatus) {
this.fbillstatus=fbillstatus;
} 
 
/**
* ���� vdef1��Getter����.���������Զ�����1
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVdef1() {
return this.vdef1;
} 

/**
* ����vdef1��Setter����.���������Զ�����1
* ��������:2017-7-4
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
} 
 
/**
* ���� vdef2��Getter����.���������Զ�����2
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVdef2() {
return this.vdef2;
} 

/**
* ����vdef2��Setter����.���������Զ�����2
* ��������:2017-7-4
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
} 
 
/**
* ���� vdef3��Getter����.���������Զ�����3
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVdef3() {
return this.vdef3;
} 

/**
* ����vdef3��Setter����.���������Զ�����3
* ��������:2017-7-4
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
} 
 
/**
* ���� vdef4��Getter����.���������Զ�����4
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVdef4() {
return this.vdef4;
} 

/**
* ����vdef4��Setter����.���������Զ�����4
* ��������:2017-7-4
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
} 
 
/**
* ���� vdef5��Getter����.���������Զ�����5
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVdef5() {
return this.vdef5;
} 

/**
* ����vdef5��Setter����.���������Զ�����5
* ��������:2017-7-4
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-7-4
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-7-4
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhFeeCollect");
    }
   }
    