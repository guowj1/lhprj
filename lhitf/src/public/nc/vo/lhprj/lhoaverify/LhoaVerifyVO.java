package nc.vo.lhprj.lhoaverify;

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
 *  ��������:2017-1-18
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhoaVerifyVO extends SuperVO {
	
/**
*����
*/
public java.lang.String pk_id;
/**
*����ID
*/
public java.lang.String pk_bill;
/**
*����״̬
*/
public java.lang.String approvestatus;
/**
*��������
*/
public java.lang.String approvenote;
/**
*����
*/
public java.lang.String pk_group;
/**
*��֯
*/
public java.lang.String pk_org;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_id��Getter����.������������
*  ��������:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* ����pk_id��Setter����.������������
* ��������:2017-1-18
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* ���� pk_bill��Getter����.������������ID
*  ��������:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_bill() {
return this.pk_bill;
} 

/**
* ����pk_bill��Setter����.������������ID
* ��������:2017-1-18
* @param newPk_bill java.lang.String
*/
public void setPk_bill ( java.lang.String pk_bill) {
this.pk_bill=pk_bill;
} 
 
/**
* ���� approvestatus��Getter����.������������״̬
*  ��������:2017-1-18
* @return java.lang.String
*/
public java.lang.String getApprovestatus() {
return this.approvestatus;
} 

/**
* ����approvestatus��Setter����.������������״̬
* ��������:2017-1-18
* @param newApprovestatus java.lang.String
*/
public void setApprovestatus ( java.lang.String approvestatus) {
this.approvestatus=approvestatus;
} 
 
/**
* ���� approvenote��Getter����.����������������
*  ��������:2017-1-18
* @return java.lang.String
*/
public java.lang.String getApprovenote() {
return this.approvenote;
} 

/**
* ����approvenote��Setter����.����������������
* ��������:2017-1-18
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( java.lang.String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-1-18
* @param newPk_group java.lang.String
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-1-18
* @param newPk_org java.lang.String
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-1-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-1-18
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhoaVerify");
    }
   }
    