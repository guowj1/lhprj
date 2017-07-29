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
 
public class LhTransBillDetailVO extends SuperVO {
	
/**
*�ӱ�����
*/
public java.lang.String pk_transbill_b;
/**
*���ϱ���
*/
public java.lang.String matcode;
/**
*ת������
*/
public nc.vo.pub.lang.UFDouble iqty;
/**
*�ϲ㵥������
*/
public String pk_transbill;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_transbill_b��Getter����.���������ӱ�����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_transbill_b() {
return this.pk_transbill_b;
} 

/**
* ����pk_transbill_b��Setter����.���������ӱ�����
* ��������:2017-3-12
* @param newPk_transbill_b java.lang.String
*/
public void setPk_transbill_b ( java.lang.String pk_transbill_b) {
this.pk_transbill_b=pk_transbill_b;
} 
 
/**
* ���� matcode��Getter����.�����������ϱ���
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatcode() {
return this.matcode;
} 

/**
* ����matcode��Setter����.�����������ϱ���
* ��������:2017-3-12
* @param newMatcode java.lang.String
*/
public void setMatcode ( java.lang.String matcode) {
this.matcode=matcode;
} 
 
/**
* ���� iqty��Getter����.��������ת������
*  ��������:2017-3-12
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getIqty() {
return this.iqty;
} 

/**
* ����iqty��Setter����.��������ת������
* ��������:2017-3-12
* @param newIqty nc.vo.pub.lang.UFDouble
*/
public void setIqty ( nc.vo.pub.lang.UFDouble iqty) {
this.iqty=iqty;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2017-3-12
* @return String
*/
public String getPk_transbill(){
return this.pk_transbill;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2017-3-12
* @param newPk_transbill String
*/
public void setPk_transbill(String pk_transbill){
this.pk_transbill=pk_transbill;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhTransBillDetail");
    }
   }
    