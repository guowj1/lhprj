package nc.vo.lhprj.lhdcsharerate;

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
 *  ��������:2017-6-18
 * @author 
 * @version NCPrj ??
 */
 
public class LhShareRateItemVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String pk_sharerateitem;
/**
*�к�
*/
public String crowno;
/**
*��Ʒ��
*/
public String pk_marbasclass;
/**
*ռ��
*/
public UFDouble frate;
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
*�����Զ�����6
*/
public String vbdef6;
/**
*�����Զ�����7
*/
public String vbdef7;
/**
*�����Զ�����8
*/
public String vbdef8;
/**
*�����Զ�����9
*/
public String vbdef9;
/**
*�����Զ�����10
*/
public String vbdef10;
/**
*�ϲ㵥������
*/
public String pk_sharerate;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_sharerateitem��Getter����.���������ӱ�����
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getPk_sharerateitem() {
return this.pk_sharerateitem;
} 

/**
* ����pk_sharerateitem��Setter����.���������ӱ�����
* ��������:2017-6-18
* @param newPk_sharerateitem java.lang.String
*/
public void setPk_sharerateitem ( String pk_sharerateitem) {
this.pk_sharerateitem=pk_sharerateitem;
} 
 
/**
* ���� crowno��Getter����.���������к�
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getCrowno() {
return this.crowno;
} 

/**
* ����crowno��Setter����.���������к�
* ��������:2017-6-18
* @param newCrowno java.lang.String
*/
public void setCrowno ( String crowno) {
this.crowno=crowno;
} 
 
/**
* ���� pk_marbasclass��Getter����.����������Ʒ��
*  ��������:2017-6-18
* @return nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public String getPk_marbasclass() {
return this.pk_marbasclass;
} 

/**
* ����pk_marbasclass��Setter����.����������Ʒ��
* ��������:2017-6-18
* @param newPk_marbasclass nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public void setPk_marbasclass ( String pk_marbasclass) {
this.pk_marbasclass=pk_marbasclass;
} 
 
/**
* ���� frate��Getter����.��������ռ��
*  ��������:2017-6-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFrate() {
return this.frate;
} 

/**
* ����frate��Setter����.��������ռ��
* ��������:2017-6-18
* @param newFrate nc.vo.pub.lang.UFDouble
*/
public void setFrate ( UFDouble frate) {
this.frate=frate;
} 
 
/**
* ���� vbdef1��Getter����.�������������Զ�����1
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef1() {
return this.vbdef1;
} 

/**
* ����vbdef1��Setter����.�������������Զ�����1
* ��������:2017-6-18
* @param newVbdef1 java.lang.String
*/
public void setVbdef1 ( String vbdef1) {
this.vbdef1=vbdef1;
} 
 
/**
* ���� vbdef2��Getter����.�������������Զ�����2
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef2() {
return this.vbdef2;
} 

/**
* ����vbdef2��Setter����.�������������Զ�����2
* ��������:2017-6-18
* @param newVbdef2 java.lang.String
*/
public void setVbdef2 ( String vbdef2) {
this.vbdef2=vbdef2;
} 
 
/**
* ���� vbdef3��Getter����.�������������Զ�����3
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef3() {
return this.vbdef3;
} 

/**
* ����vbdef3��Setter����.�������������Զ�����3
* ��������:2017-6-18
* @param newVbdef3 java.lang.String
*/
public void setVbdef3 ( String vbdef3) {
this.vbdef3=vbdef3;
} 
 
/**
* ���� vbdef4��Getter����.�������������Զ�����4
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef4() {
return this.vbdef4;
} 

/**
* ����vbdef4��Setter����.�������������Զ�����4
* ��������:2017-6-18
* @param newVbdef4 java.lang.String
*/
public void setVbdef4 ( String vbdef4) {
this.vbdef4=vbdef4;
} 
 
/**
* ���� vbdef5��Getter����.�������������Զ�����5
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef5() {
return this.vbdef5;
} 

/**
* ����vbdef5��Setter����.�������������Զ�����5
* ��������:2017-6-18
* @param newVbdef5 java.lang.String
*/
public void setVbdef5 ( String vbdef5) {
this.vbdef5=vbdef5;
} 
 
/**
* ���� vbdef6��Getter����.�������������Զ�����6
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef6() {
return this.vbdef6;
} 

/**
* ����vbdef6��Setter����.�������������Զ�����6
* ��������:2017-6-18
* @param newVbdef6 java.lang.String
*/
public void setVbdef6 ( String vbdef6) {
this.vbdef6=vbdef6;
} 
 
/**
* ���� vbdef7��Getter����.�������������Զ�����7
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef7() {
return this.vbdef7;
} 

/**
* ����vbdef7��Setter����.�������������Զ�����7
* ��������:2017-6-18
* @param newVbdef7 java.lang.String
*/
public void setVbdef7 ( String vbdef7) {
this.vbdef7=vbdef7;
} 
 
/**
* ���� vbdef8��Getter����.�������������Զ�����8
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef8() {
return this.vbdef8;
} 

/**
* ����vbdef8��Setter����.�������������Զ�����8
* ��������:2017-6-18
* @param newVbdef8 java.lang.String
*/
public void setVbdef8 ( String vbdef8) {
this.vbdef8=vbdef8;
} 
 
/**
* ���� vbdef9��Getter����.�������������Զ�����9
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef9() {
return this.vbdef9;
} 

/**
* ����vbdef9��Setter����.�������������Զ�����9
* ��������:2017-6-18
* @param newVbdef9 java.lang.String
*/
public void setVbdef9 ( String vbdef9) {
this.vbdef9=vbdef9;
} 
 
/**
* ���� vbdef10��Getter����.�������������Զ�����10
*  ��������:2017-6-18
* @return java.lang.String
*/
public String getVbdef10() {
return this.vbdef10;
} 

/**
* ����vbdef10��Setter����.�������������Զ�����10
* ��������:2017-6-18
* @param newVbdef10 java.lang.String
*/
public void setVbdef10 ( String vbdef10) {
this.vbdef10=vbdef10;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2017-6-18
* @return String
*/
public String getPk_sharerate(){
return this.pk_sharerate;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2017-6-18
* @param newPk_sharerate String
*/
public void setPk_sharerate(String pk_sharerate){
this.pk_sharerate=pk_sharerate;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-6-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-6-18
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhShareRateItem");
    }
   }
    