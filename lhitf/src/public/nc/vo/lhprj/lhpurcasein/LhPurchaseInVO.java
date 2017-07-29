package nc.vo.lhprj.lhpurcasein;

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
 *  ��������:2017-6-14
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhPurchaseInVO extends SuperVO {
	
/**
*����
*/
public java.lang.String pk_purchasein;
/**
*��ϵͳ��������
*/
public java.lang.String pk_id;
/**
*��ӦNC�ɹ���������
*/
public java.lang.String pk_saleorder;
/**
*��ӦNC�ɹ�������
*/
public java.lang.String sobillno;
/**
*��������
*/
public java.lang.String settleclue;
/**
*�ֿ����
*/
public java.lang.String whcode;
/**
*��������
*/
public UFDate ddate;
/**
*�Ƿ�Ԥ���㵥��
*/
public java.lang.String bpresettle;
/**
*�Ƶ��˱���
*/
public java.lang.String userid;
/**
*��֯
*/
public java.lang.String pk_org;
/**
*����
*/
public java.lang.String pk_group;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_purchasein��Getter����.������������
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getPk_purchasein() {
return this.pk_purchasein;
} 

/**
* ����pk_purchasein��Setter����.������������
* ��������:2017-6-14
* @param newPk_purchasein java.lang.String
*/
public void setPk_purchasein ( java.lang.String pk_purchasein) {
this.pk_purchasein=pk_purchasein;
} 
 
/**
* ���� pk_id��Getter����.����������ϵͳ��������
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* ����pk_id��Setter����.����������ϵͳ��������
* ��������:2017-6-14
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* ���� pk_saleorder��Getter����.����������ӦNC�ɹ���������
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getPk_saleorder() {
return this.pk_saleorder;
} 

/**
* ����pk_saleorder��Setter����.����������ӦNC�ɹ���������
* ��������:2017-6-14
* @param newPk_saleorder java.lang.String
*/
public void setPk_saleorder ( java.lang.String pk_saleorder) {
this.pk_saleorder=pk_saleorder;
} 
 
/**
* ���� sobillno��Getter����.����������ӦNC�ɹ�������
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getSobillno() {
return this.sobillno;
} 

/**
* ����sobillno��Setter����.����������ӦNC�ɹ�������
* ��������:2017-6-14
* @param newSobillno java.lang.String
*/
public void setSobillno ( java.lang.String sobillno) {
this.sobillno=sobillno;
} 
 
/**
* ���� settleclue��Getter����.����������������
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getSettleclue() {
return this.settleclue;
} 

/**
* ����settleclue��Setter����.����������������
* ��������:2017-6-14
* @param newSettleclue java.lang.String
*/
public void setSettleclue ( java.lang.String settleclue) {
this.settleclue=settleclue;
} 
 
/**
* ���� whcode��Getter����.���������ֿ����
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getWhcode() {
return this.whcode;
} 

/**
* ����whcode��Setter����.���������ֿ����
* ��������:2017-6-14
* @param newWhcode java.lang.String
*/
public void setWhcode ( java.lang.String whcode) {
this.whcode=whcode;
} 
 
/**
* ���� ddate��Getter����.����������������
*  ��������:2017-6-14
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDdate() {
return this.ddate;
} 

/**
* ����ddate��Setter����.����������������
* ��������:2017-6-14
* @param newDdate nc.vo.pub.lang.UFDate
*/
public void setDdate ( UFDate ddate) {
this.ddate=ddate;
} 
 
/**
* ���� bpresettle��Getter����.���������Ƿ�Ԥ���㵥��
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getBpresettle() {
return this.bpresettle;
} 

/**
* ����bpresettle��Setter����.���������Ƿ�Ԥ���㵥��
* ��������:2017-6-14
* @param newBpresettle java.lang.String
*/
public void setBpresettle ( java.lang.String bpresettle) {
this.bpresettle=bpresettle;
} 
 
/**
* ���� userid��Getter����.���������Ƶ��˱���
*  ��������:2017-6-14
* @return java.lang.String
*/
public java.lang.String getUserid() {
return this.userid;
} 

/**
* ����userid��Setter����.���������Ƶ��˱���
* ��������:2017-6-14
* @param newUserid java.lang.String
*/
public void setUserid ( java.lang.String userid) {
this.userid=userid;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2017-6-14
* @return nc.vo.org.OrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-6-14
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-6-14
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-6-14
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-6-14
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-6-14
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhPurchaseIn");
    }
   }
    