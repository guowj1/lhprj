package nc.vo.lhprj.lhrecbill;

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
 * @author 
 * @version NCPrj ??
 */
 
public class LhRecBillVO extends SuperVO {
	
/**
*Դϵͳ����
*/
public java.lang.String pkid;
/**
*������֯����
*/
public java.lang.String orgcode;
/**
*�ͻ�����
*/
public java.lang.String custcode;
/**
*�ͻ��ӻ�����
*/
public java.lang.String custsubcode;
/**
*�տ�ҵ������
*/
public java.lang.String busitype;
/**
*ҵ������
*/
public java.lang.String busiproperty;
/**
*�տ����ͱ���
*/
public java.lang.String subjcode;
/**
*���㷽ʽ����
*/
public java.lang.String balatype;
/**
*�տ������ʺ�
*/
public java.lang.String recaccount;
/**
*���н���������
*/
public java.lang.String clueno;
/**
*�տ���
*/
public nc.vo.pub.lang.UFDouble nmoney;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pkid��Getter����.��������Դϵͳ����
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getPkid() {
return this.pkid;
} 

/**
* ����pkid��Setter����.��������Դϵͳ����
* ��������:2017-6-2
* @param newPkid java.lang.String
*/
public void setPkid ( java.lang.String pkid) {
this.pkid=pkid;
} 
 
/**
* ���� orgcode��Getter����.��������������֯����
*  ��������:2017-6-2
* @return nc.vo.org.FinanceOrgVO
*/
public java.lang.String getOrgcode() {
return this.orgcode;
} 

/**
* ����orgcode��Setter����.��������������֯����
* ��������:2017-6-2
* @param newOrgcode nc.vo.org.FinanceOrgVO
*/
public void setOrgcode ( java.lang.String orgcode) {
this.orgcode=orgcode;
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
* ���� custsubcode��Getter����.���������ͻ��ӻ�����
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustsubcode() {
return this.custsubcode;
} 

/**
* ����custsubcode��Setter����.���������ͻ��ӻ�����
* ��������:2017-6-2
* @param newCustsubcode java.lang.String
*/
public void setCustsubcode ( java.lang.String custsubcode) {
this.custsubcode=custsubcode;
} 
 
/**
* ���� busitype��Getter����.���������տ�ҵ������
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getBusitype() {
return this.busitype;
} 

/**
* ����busitype��Setter����.���������տ�ҵ������
* ��������:2017-6-2
* @param newBusitype java.lang.String
*/
public void setBusitype ( java.lang.String busitype) {
this.busitype=busitype;
} 
 
/**
* ���� busiproperty��Getter����.��������ҵ������
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getBusiproperty() {
return this.busiproperty;
} 

/**
* ����busiproperty��Setter����.��������ҵ������
* ��������:2017-6-2
* @param newBusiproperty java.lang.String
*/
public void setBusiproperty ( java.lang.String busiproperty) {
this.busiproperty=busiproperty;
} 
 
/**
* ���� subjcode��Getter����.���������տ����ͱ���
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getSubjcode() {
return this.subjcode;
} 

/**
* ����subjcode��Setter����.���������տ����ͱ���
* ��������:2017-6-2
* @param newSubjcode java.lang.String
*/
public void setSubjcode ( java.lang.String subjcode) {
this.subjcode=subjcode;
} 
 
/**
* ���� balatype��Getter����.�����������㷽ʽ����
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getBalatype() {
return this.balatype;
} 

/**
* ����balatype��Setter����.�����������㷽ʽ����
* ��������:2017-6-2
* @param newBalatype java.lang.String
*/
public void setBalatype ( java.lang.String balatype) {
this.balatype=balatype;
} 
 
/**
* ���� recaccount��Getter����.���������տ������ʺ�
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getRecaccount() {
return this.recaccount;
} 

/**
* ����recaccount��Setter����.���������տ������ʺ�
* ��������:2017-6-2
* @param newRecaccount java.lang.String
*/
public void setRecaccount ( java.lang.String recaccount) {
this.recaccount=recaccount;
} 
 
/**
* ���� clueno��Getter����.�����������н���������
*  ��������:2017-6-2
* @return java.lang.String
*/
public java.lang.String getClueno() {
return this.clueno;
} 

/**
* ����clueno��Setter����.�����������н���������
* ��������:2017-6-2
* @param newClueno java.lang.String
*/
public void setClueno ( java.lang.String clueno) {
this.clueno=clueno;
} 
 
/**
* ���� nmoney��Getter����.���������տ���
*  ��������:2017-6-2
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getNmoney() {
return this.nmoney;
} 

/**
* ����nmoney��Setter����.���������տ���
* ��������:2017-6-2
* @param newNmoney nc.vo.pub.lang.UFDouble
*/
public void setNmoney ( nc.vo.pub.lang.UFDouble nmoney) {
this.nmoney=nmoney;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhRecBill");
    }
   }
    