package nc.vo.lhprj.lhcustomersub;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2017-5-31
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhCustomerSubVO extends SuperVO {
	
/**
*�ͻ��ӻ�����
*/
public java.lang.String pk_id;
/**
*�ͻ��ӻ�����
*/
public java.lang.String custsubcode;
/**
*�ͻ��ӻ�����
*/
public java.lang.String custsubname;
/**
*�����ͻ�����
*/
public java.lang.String custcode;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_id��Getter����.���������ͻ��ӻ�����
*  ��������:2017-5-31
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* ����pk_id��Setter����.���������ͻ��ӻ�����
* ��������:2017-5-31
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* ���� custsubcode��Getter����.���������ͻ��ӻ�����
*  ��������:2017-5-31
* @return java.lang.String
*/
public java.lang.String getCustsubcode() {
return this.custsubcode;
} 

/**
* ����custsubcode��Setter����.���������ͻ��ӻ�����
* ��������:2017-5-31
* @param newCustsubcode java.lang.String
*/
public void setCustsubcode ( java.lang.String custsubcode) {
this.custsubcode=custsubcode;
} 
 
/**
* ���� custsubname��Getter����.���������ͻ��ӻ�����
*  ��������:2017-5-31
* @return java.lang.String
*/
public java.lang.String getCustsubname() {
return this.custsubname;
} 

/**
* ����custsubname��Setter����.���������ͻ��ӻ�����
* ��������:2017-5-31
* @param newCustsubname java.lang.String
*/
public void setCustsubname ( java.lang.String custsubname) {
this.custsubname=custsubname;
} 
 
/**
* ���� custcode��Getter����.�������������ͻ�����
*  ��������:2017-5-31
* @return java.lang.String
*/
public java.lang.String getCustcode() {
return this.custcode;
} 

/**
* ����custcode��Setter����.�������������ͻ�����
* ��������:2017-5-31
* @param newCustcode java.lang.String
*/
public void setCustcode ( java.lang.String custcode) {
this.custcode=custcode;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-5-31
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-5-31
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhCustomerSub");
    }
   }
    