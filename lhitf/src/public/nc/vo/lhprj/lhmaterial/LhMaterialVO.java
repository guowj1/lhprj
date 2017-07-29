package nc.vo.lhprj.lhmaterial;

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
 * @author 
 * @version NCPrj ??
 */
 
public class LhMaterialVO extends SuperVO {
	
/**
*����
*/
public java.lang.String pk_material;
/**
*��ϵͳ����
*/
public java.lang.String pk_id;
/**
*��Ʒ����
*/
public java.lang.String matcode;
/**
*��Ʒ����
*/
public java.lang.String matname;
/**
*������λ����
*/
public java.lang.String measname;
/**
*�����������
*/
public java.lang.String matclscode;
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
* ���� pk_material��Getter����.������������
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_material() {
return this.pk_material;
} 

/**
* ����pk_material��Setter����.������������
* ��������:2017-3-12
* @param newPk_material java.lang.String
*/
public void setPk_material ( java.lang.String pk_material) {
this.pk_material=pk_material;
} 
 
/**
* ���� pk_id��Getter����.����������ϵͳ����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* ����pk_id��Setter����.����������ϵͳ����
* ��������:2017-3-12
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* ���� matcode��Getter����.����������Ʒ����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatcode() {
return this.matcode;
} 

/**
* ����matcode��Setter����.����������Ʒ����
* ��������:2017-3-12
* @param newMatcode java.lang.String
*/
public void setMatcode ( java.lang.String matcode) {
this.matcode=matcode;
} 
 
/**
* ���� matname��Getter����.����������Ʒ����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatname() {
return this.matname;
} 

/**
* ����matname��Setter����.����������Ʒ����
* ��������:2017-3-12
* @param newMatname java.lang.String
*/
public void setMatname ( java.lang.String matname) {
this.matname=matname;
} 
 
/**
* ���� measname��Getter����.��������������λ����
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMeasname() {
return this.measname;
} 

/**
* ����measname��Setter����.��������������λ����
* ��������:2017-3-12
* @param newMeasname java.lang.String
*/
public void setMeasname ( java.lang.String measname) {
this.measname=measname;
} 
 
/**
* ���� matclscode��Getter����.�������������������
*  ��������:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatclscode() {
return this.matclscode;
} 

/**
* ����matclscode��Setter����.�������������������
* ��������:2017-3-12
* @param newMatclscode java.lang.String
*/
public void setMatclscode ( java.lang.String matclscode) {
this.matclscode=matclscode;
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
* ���� pk_org��Getter����.����������֯
*  ��������:2017-3-12
* @return nc.vo.org.CorpVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-3-12
* @param newPk_org nc.vo.org.CorpVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhMaterial");
    }
   }
    