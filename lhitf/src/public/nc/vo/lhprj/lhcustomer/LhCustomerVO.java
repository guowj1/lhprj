package nc.vo.lhprj.lhcustomer;

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
 *  ��������:2017-6-2
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhCustomerVO extends SuperVO {
	
/**
*����
*/
public java.lang.String pk_customer;
/**
*��ϵͳ����
*/
public java.lang.String pk_id;
/**
*�ͻ�����
*/
public java.lang.String custcode;
/**
*�ͻ�����
*/
public java.lang.String custname;
/**
*�ͻ��������
*/
public java.lang.String custclasscode;
/**
*�ͻ�����
*/
public java.lang.String custproperty;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_customer��Getter����.������������
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getPk_customer() {
return this.pk_customer;
} 

/**
* ����pk_customer��Setter����.������������
* ��������:2017-6-2
* @param newPk_customer java.lang.String
*/
public void setPk_customer ( java.lang.String pk_customer) {
this.pk_customer=pk_customer;
} 
 
/**
* ���� pk_id��Getter����.����������ϵͳ����
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* ����pk_id��Setter����.����������ϵͳ����
* ��������:2017-6-2
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* ���� custcode��Getter����.���������ͻ�����
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustcode() {
return this.custcode;
} 

/**
* ����custcode��Setter����.���������ͻ�����
* ��������:2017-6-2
* @param newCustcode java.lang.String
*/
public void setCustcode ( java.lang.String custcode) {
this.custcode=custcode;
} 
 
/**
* ���� custname��Getter����.���������ͻ�����
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustname() {
return this.custname;
} 

/**
* ����custname��Setter����.���������ͻ�����
* ��������:2017-6-2
* @param newCustname java.lang.String
*/
public void setCustname ( java.lang.String custname) {
this.custname=custname;
} 
 
/**
* ���� custclasscode��Getter����.���������ͻ��������
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustclasscode() {
return this.custclasscode;
} 

/**
* ����custclasscode��Setter����.���������ͻ��������
* ��������:2017-6-2
* @param newCustclasscode java.lang.String
*/
public void setCustclasscode ( java.lang.String custclasscode) {
this.custclasscode=custclasscode;
} 
 
/**
* ���� custproperty��Getter����.���������ͻ�����
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustproperty() {
return this.custproperty;
} 

/**
* ����custproperty��Setter����.���������ͻ�����
* ��������:2017-6-2
* @param newCustproperty java.lang.String
*/
public void setCustproperty ( java.lang.String custproperty) {
this.custproperty=custproperty;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-6-2
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-6-2
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhCustomer");
    }
   }
    