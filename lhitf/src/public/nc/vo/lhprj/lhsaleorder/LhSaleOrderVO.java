package nc.vo.lhprj.lhsaleorder;

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
 *  ��������:2017-3-17
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhSaleOrderVO extends SuperVO {
	
/**
*����
*/
public java.lang.String pk_saleorder;
/**
*��ϵͳ����
*/
public java.lang.String pk_id;
/**
*������֯����
*/
public java.lang.String pk_saleorg;
/**
*�ͻ�����
*/
public java.lang.String custcode;
/**
*����·��
*/
public java.lang.String settleroute;
/**
*��ͬ��������
*/
public java.lang.String cttype;
/**
*��ͬ��
*/
public java.lang.String ctcode;
/**
*��ͬ����
*/
public UFDate ctdate;

public String cbusiproperty;
/**
*����
*/
public java.lang.String pk_group;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_saleorder��Getter����.������������
*  ��������:2017-3-17
* @return java.lang.String
*/
public java.lang.String getPk_saleorder() {
return this.pk_saleorder;
} 

/**
* ����pk_saleorder��Setter����.������������
* ��������:2017-3-17
* @param newPk_saleorder java.lang.String
*/
public void setPk_saleorder ( java.lang.String pk_saleorder) {
this.pk_saleorder=pk_saleorder;
} 
 
/**
* ���� pk_id��Getter����.����������ϵͳ����
*  ��������:2017-3-17
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* ����pk_id��Setter����.����������ϵͳ����
* ��������:2017-3-17
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* ���� pk_saleorg��Getter����.��������������֯����
*  ��������:2017-3-17
* @return java.lang.String
*/
public java.lang.String getPk_saleorg() {
return this.pk_saleorg;
} 

/**
* ����pk_saleorg��Setter����.��������������֯����
* ��������:2017-3-17
* @param newPk_saleorg java.lang.String
*/
public void setPk_saleorg ( java.lang.String pk_saleorg) {
this.pk_saleorg=pk_saleorg;
} 
 
/**
* ���� custcode��Getter����.���������ͻ�����
*  ��������:2017-3-17
* @return java.lang.String
*/
public java.lang.String getCustcode() {
return this.custcode;
} 

/**
* ����custcode��Setter����.���������ͻ�����
* ��������:2017-3-17
* @param newCustcode java.lang.String
*/
public void setCustcode ( java.lang.String custcode) {
this.custcode=custcode;
} 
 
/**
* ���� settleroute��Getter����.������������·��
*  ��������:2017-3-17
* @return java.lang.String
*/
public java.lang.String getSettleroute() {
return this.settleroute;
} 

/**
* ����settleroute��Setter����.������������·��
* ��������:2017-3-17
* @param newSettleroute java.lang.String
*/
public void setSettleroute ( java.lang.String settleroute) {
this.settleroute=settleroute;
} 
 
/**
* ���� cttype��Getter����.����������ͬ��������
*  ��������:2017-3-17
* @return java.lang.String
*/
public java.lang.String getCttype() {
return this.cttype;
} 

/**
* ����cttype��Setter����.����������ͬ��������
* ��������:2017-3-17
* @param newCttype java.lang.String
*/
public void setCttype ( java.lang.String cttype) {
this.cttype=cttype;
} 
 
/**
* ���� ctcode��Getter����.����������ͬ��
*  ��������:2017-3-17
* @return java.lang.String
*/
public java.lang.String getCtcode() {
return this.ctcode;
} 

/**
* ����ctcode��Setter����.����������ͬ��
* ��������:2017-3-17
* @param newCtcode java.lang.String
*/
public void setCtcode ( java.lang.String ctcode) {
this.ctcode=ctcode;
} 
 
/**
* ���� ctdate��Getter����.����������ͬ����
*  ��������:2017-3-17
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getCtdate() {
return this.ctdate;
} 

/**
* ����ctdate��Setter����.����������ͬ����
* ��������:2017-3-17
* @param newCtdate nc.vo.pub.lang.UFDate
*/
public void setCtdate ( UFDate ctdate) {
this.ctdate=ctdate;
} 


 
public String getCbusiproperty() {
	return cbusiproperty;
}

public void setCbusiproperty(String cbusiproperty) {
	this.cbusiproperty = cbusiproperty;
}

/**
* ���� pk_group��Getter����.������������
*  ��������:2017-3-17
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-3-17
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-3-17
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-3-17
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhSaleOrder");
    }
   }
    