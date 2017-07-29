package nc.vo.lhprj.lhtransbill;

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
 *  ��������:2017-3-12
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhTransBillVO extends SuperVO {
	
/**
*����
*/
public java.lang.String pk_transbill;
/**
*��ϵͳ��������
*/
public java.lang.String pk_id;
/**
*��������
*/
public java.lang.String transtype;
/**
*������֯����
*/
public java.lang.String outorgcode;
/**
*ת���ֿ�����
*/
public java.lang.String pk_warehouse_out;
/**
*ת��ֿ�����
*/
public java.lang.String pk_warehouse_in;
/**
*������֯����
*/
public java.lang.String inorgcode;
/**
*��������
*/
public UFDate ddate;
/**
*ҵ��Ԫ
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
* ���� pk_transbill��Getter����.������������
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_transbill() {
return this.pk_transbill;
} 

/**
* ����pk_transbill��Setter����.������������
* ��������:2017-3-12
* @param newPk_transbill java.lang.String
*/
public void setPk_transbill ( java.lang.String pk_transbill) {
this.pk_transbill=pk_transbill;
} 
 
/**
* ���� pk_id��Getter����.����������ϵͳ��������
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* ����pk_id��Setter����.����������ϵͳ��������
* ��������:2017-3-12
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* ���� transtype��Getter����.����������������
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getTranstype() {
return this.transtype;
} 

/**
* ����transtype��Setter����.����������������
* ��������:2017-3-12
* @param newTranstype java.lang.String
*/
public void setTranstype ( java.lang.String transtype) {
this.transtype=transtype;
} 
 
/**
* ���� outorgcode��Getter����.��������������֯����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getOutorgcode() {
return this.outorgcode;
} 

/**
* ����outorgcode��Setter����.��������������֯����
* ��������:2017-3-12
* @param newOutorgcode java.lang.String
*/
public void setOutorgcode ( java.lang.String outorgcode) {
this.outorgcode=outorgcode;
} 
 
/**
* ���� pk_warehouse_out��Getter����.��������ת���ֿ�����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_warehouse_out() {
return this.pk_warehouse_out;
} 

/**
* ����pk_warehouse_out��Setter����.��������ת���ֿ�����
* ��������:2017-3-12
* @param newPk_warehouse_out java.lang.String
*/
public void setPk_warehouse_out ( java.lang.String pk_warehouse_out) {
this.pk_warehouse_out=pk_warehouse_out;
} 
 
/**
* ���� pk_warehouse_in��Getter����.��������ת��ֿ�����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_warehouse_in() {
return this.pk_warehouse_in;
} 

/**
* ����pk_warehouse_in��Setter����.��������ת��ֿ�����
* ��������:2017-3-12
* @param newPk_warehouse_in java.lang.String
*/
public void setPk_warehouse_in ( java.lang.String pk_warehouse_in) {
this.pk_warehouse_in=pk_warehouse_in;
} 
 
/**
* ���� inorgcode��Getter����.��������������֯����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getInorgcode() {
return this.inorgcode;
} 

/**
* ����inorgcode��Setter����.��������������֯����
* ��������:2017-3-12
* @param newInorgcode java.lang.String
*/
public void setInorgcode ( java.lang.String inorgcode) {
this.inorgcode=inorgcode;
} 
 
/**
* ���� ddate��Getter����.����������������
*  ��������:2017-3-12
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDdate() {
return this.ddate;
} 

/**
* ����ddate��Setter����.����������������
* ��������:2017-3-12
* @param newDdate nc.vo.pub.lang.UFDate
*/
public void setDdate ( UFDate ddate) {
this.ddate=ddate;
} 
 
/**
* ���� pk_org��Getter����.��������ҵ��Ԫ
*  ��������:2017-3-12
* @return nc.vo.org.OrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.��������ҵ��Ԫ
* ��������:2017-3-12
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-3-12
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-3-12
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-3-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-3-12
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhTransBill");
    }
   }
    