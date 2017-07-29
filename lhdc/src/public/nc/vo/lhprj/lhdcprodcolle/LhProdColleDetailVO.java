package nc.vo.lhprj.lhdcprodcolle;

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
 
public class LhProdColleDetailVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String pk_prodcolle_b;
/**
*�к�
*/
public String crowno;
/**
*�ֳ�
*/
public String pk_subcorp;
/**
*��Ʒ��
*/
public String pk_marbasclass;
/**
*����
*/
public UFDouble fqty;
/**
*�����Զ�����1
*/
public String vbdef1;
/**
*�����Զ�����2
*/
public String vbdef2;
/**
*�����Զ�����3
*/
public String vbdef3;
/**
*�����Զ�����4
*/
public String vbdef4;
/**
*�����Զ�����5
*/
public String vbdef5;
/**
*�ϲ㵥������
*/
public String pk_prodcolle;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_prodcolle_b��Getter����.���������ӱ�����
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getPk_prodcolle_b() {
return this.pk_prodcolle_b;
} 

/**
* ����pk_prodcolle_b��Setter����.���������ӱ�����
* ��������:2017-7-4
* @param newPk_prodcolle_b java.lang.String
*/
public void setPk_prodcolle_b ( String pk_prodcolle_b) {
this.pk_prodcolle_b=pk_prodcolle_b;
} 
 
/**
* ���� crowno��Getter����.���������к�
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getCrowno() {
return this.crowno;
} 

/**
* ����crowno��Setter����.���������к�
* ��������:2017-7-4
* @param newCrowno java.lang.String
*/
public void setCrowno ( String crowno) {
this.crowno=crowno;
} 
 
/**
* ���� pk_subcorp��Getter����.���������ֳ�
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getPk_subcorp() {
return this.pk_subcorp;
} 

/**
* ����pk_subcorp��Setter����.���������ֳ�
* ��������:2017-7-4
* @param newPk_subcorp java.lang.String
*/
public void setPk_subcorp ( String pk_subcorp) {
this.pk_subcorp=pk_subcorp;
} 
 
/**
* ���� pk_marbasclass��Getter����.����������Ʒ��
*  ��������:2017-7-4
* @return nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public String getPk_marbasclass() {
return this.pk_marbasclass;
} 

/**
* ����pk_marbasclass��Setter����.����������Ʒ��
* ��������:2017-7-4
* @param newPk_marbasclass nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public void setPk_marbasclass ( String pk_marbasclass) {
this.pk_marbasclass=pk_marbasclass;
} 
 
/**
* ���� fqty��Getter����.������������
*  ��������:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFqty() {
return this.fqty;
} 

/**
* ����fqty��Setter����.������������
* ��������:2017-7-4
* @param newFqty nc.vo.pub.lang.UFDouble
*/
public void setFqty ( UFDouble fqty) {
this.fqty=fqty;
} 
 
/**
* ���� vbdef1��Getter����.�������������Զ�����1
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVbdef1() {
return this.vbdef1;
} 

/**
* ����vbdef1��Setter����.�������������Զ�����1
* ��������:2017-7-4
* @param newVbdef1 java.lang.String
*/
public void setVbdef1 ( String vbdef1) {
this.vbdef1=vbdef1;
} 
 
/**
* ���� vbdef2��Getter����.�������������Զ�����2
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVbdef2() {
return this.vbdef2;
} 

/**
* ����vbdef2��Setter����.�������������Զ�����2
* ��������:2017-7-4
* @param newVbdef2 java.lang.String
*/
public void setVbdef2 ( String vbdef2) {
this.vbdef2=vbdef2;
} 
 
/**
* ���� vbdef3��Getter����.�������������Զ�����3
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVbdef3() {
return this.vbdef3;
} 

/**
* ����vbdef3��Setter����.�������������Զ�����3
* ��������:2017-7-4
* @param newVbdef3 java.lang.String
*/
public void setVbdef3 ( String vbdef3) {
this.vbdef3=vbdef3;
} 
 
/**
* ���� vbdef4��Getter����.�������������Զ�����4
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVbdef4() {
return this.vbdef4;
} 

/**
* ����vbdef4��Setter����.�������������Զ�����4
* ��������:2017-7-4
* @param newVbdef4 java.lang.String
*/
public void setVbdef4 ( String vbdef4) {
this.vbdef4=vbdef4;
} 
 
/**
* ���� vbdef5��Getter����.�������������Զ�����5
*  ��������:2017-7-4
* @return java.lang.String
*/
public String getVbdef5() {
return this.vbdef5;
} 

/**
* ����vbdef5��Setter����.�������������Զ�����5
* ��������:2017-7-4
* @param newVbdef5 java.lang.String
*/
public void setVbdef5 ( String vbdef5) {
this.vbdef5=vbdef5;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2017-7-4
* @return String
*/
public String getPk_prodcolle(){
return this.pk_prodcolle;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2017-7-4
* @param newPk_prodcolle String
*/
public void setPk_prodcolle(String pk_prodcolle){
this.pk_prodcolle=pk_prodcolle;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhProdColleDetail");
    }
   }
    