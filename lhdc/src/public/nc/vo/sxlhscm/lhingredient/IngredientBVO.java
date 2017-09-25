package nc.vo.sxlhscm.lhingredient;

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
 *  ��������:2017-7-7
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class IngredientBVO extends SuperVO {
	
/**
*����
*/
public String pk_ingredient_b;
/**
*��Ӧ��
*/
public String pk_supplier;
/**
*����
*/
public String pk_material;
/**
*������
*/
public UFDouble innum;
/**
*ˮ��
*/
public UFDouble watercontent;
/**
*�ɻ�
*/
public UFDouble drybase;
/**
*���ﵥ��
*/
public UFDouble nprice;
/**
*�˷ѵ���
*/
public UFDouble trainprice;
/**
*��ⵥ��
*/
public UFDouble inprice;
/**
*�����
*/
public UFDouble inmny;
/**
*�Զ�����1
*/
public String def1;
/**
*�Զ�����2
*/
public String def2;
/**
*�Զ�����3
*/
public String def3;
/**
*�Զ�����4
*/
public String def4;
/**
*�Զ�����5
*/
public String def5;
/**
*�Զ�����6
*/
public String def6;
/**
*�Զ�����7
*/
public String def7;
/**
*�Զ�����8
*/
public String def8;
/**
*�Զ�����9
*/
public String def9;
/**
*�Զ�����10
*/
public String def10;
/**
*�Զ�����11
*/
public String def11;
/**
*�Զ�����12
*/
public String def12;
/**
*�Զ�����13
*/
public String def13;
/**
*�Զ�����14
*/
public String def14;
/**
*�Զ�����15
*/
public String def15;
/**
*�Զ�����16
*/
public String def16;
/**
*�Զ�����17
*/
public String def17;
/**
*�Զ�����18
*/
public String def18;
/**
*�Զ�����19
*/
public String def19;
/**
*�Զ�����20
*/
public String def20;
/**
*�ϲ㵥������
*/
public String pk_ingredient_h;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_ingredient_b��Getter����.������������
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getPk_ingredient_b() {
return this.pk_ingredient_b;
} 

/**
* ����pk_ingredient_b��Setter����.������������
* ��������:2017-7-7
* @param newPk_ingredient_b java.lang.String
*/
public void setPk_ingredient_b ( String pk_ingredient_b) {
this.pk_ingredient_b=pk_ingredient_b;
} 
 
/**
* ���� pk_supplier��Getter����.����������Ӧ��
*  ��������:2017-7-7
* @return nc.vo.bd.supplier.SupplierVO
*/
public String getPk_supplier() {
return this.pk_supplier;
} 

/**
* ����pk_supplier��Setter����.����������Ӧ��
* ��������:2017-7-7
* @param newPk_supplier nc.vo.bd.supplier.SupplierVO
*/
public void setPk_supplier ( String pk_supplier) {
this.pk_supplier=pk_supplier;
} 
 
/**
* ���� pk_material��Getter����.������������
*  ��������:2017-7-7
* @return nc.vo.bd.material.MaterialVO
*/
public String getPk_material() {
return this.pk_material;
} 

/**
* ����pk_material��Setter����.������������
* ��������:2017-7-7
* @param newPk_material nc.vo.bd.material.MaterialVO
*/
public void setPk_material ( String pk_material) {
this.pk_material=pk_material;
} 
 
/**
* ���� innum��Getter����.��������������
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInnum() {
return this.innum;
} 

/**
* ����innum��Setter����.��������������
* ��������:2017-7-7
* @param newInnum nc.vo.pub.lang.UFDouble
*/
public void setInnum ( UFDouble innum) {
this.innum=innum;
} 
 
/**
* ���� watercontent��Getter����.��������ˮ��
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getWatercontent() {
return this.watercontent;
} 

/**
* ����watercontent��Setter����.��������ˮ��
* ��������:2017-7-7
* @param newWatercontent nc.vo.pub.lang.UFDouble
*/
public void setWatercontent ( UFDouble watercontent) {
this.watercontent=watercontent;
} 
 
/**
* ���� drybase��Getter����.���������ɻ�
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getDrybase() {
return this.drybase;
} 

/**
* ����drybase��Setter����.���������ɻ�
* ��������:2017-7-7
* @param newDrybase nc.vo.pub.lang.UFDouble
*/
public void setDrybase ( UFDouble drybase) {
this.drybase=drybase;
} 
 
/**
* ���� nprice��Getter����.�����������ﵥ��
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNprice() {
return this.nprice;
} 

/**
* ����nprice��Setter����.�����������ﵥ��
* ��������:2017-7-7
* @param newNprice nc.vo.pub.lang.UFDouble
*/
public void setNprice ( UFDouble nprice) {
this.nprice=nprice;
} 
 
/**
* ���� trainprice��Getter����.���������˷ѵ���
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTrainprice() {
return this.trainprice;
} 

/**
* ����trainprice��Setter����.���������˷ѵ���
* ��������:2017-7-7
* @param newTrainprice nc.vo.pub.lang.UFDouble
*/
public void setTrainprice ( UFDouble trainprice) {
this.trainprice=trainprice;
} 
 
/**
* ���� inprice��Getter����.����������ⵥ��
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInprice() {
return this.inprice;
} 

/**
* ����inprice��Setter����.����������ⵥ��
* ��������:2017-7-7
* @param newInprice nc.vo.pub.lang.UFDouble
*/
public void setInprice ( UFDouble inprice) {
this.inprice=inprice;
} 
 
/**
* ���� inmny��Getter����.�������������
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInmny() {
return this.inmny;
} 

/**
* ����inmny��Setter����.�������������
* ��������:2017-7-7
* @param newInmny nc.vo.pub.lang.UFDouble
*/
public void setInmny ( UFDouble inmny) {
this.inmny=inmny;
} 
 
/**
* ���� def1��Getter����.���������Զ�����1
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef1() {
return this.def1;
} 

/**
* ����def1��Setter����.���������Զ�����1
* ��������:2017-7-7
* @param newDef1 java.lang.String
*/
public void setDef1 ( String def1) {
this.def1=def1;
} 
 
/**
* ���� def2��Getter����.���������Զ�����2
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef2() {
return this.def2;
} 

/**
* ����def2��Setter����.���������Զ�����2
* ��������:2017-7-7
* @param newDef2 java.lang.String
*/
public void setDef2 ( String def2) {
this.def2=def2;
} 
 
/**
* ���� def3��Getter����.���������Զ�����3
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef3() {
return this.def3;
} 

/**
* ����def3��Setter����.���������Զ�����3
* ��������:2017-7-7
* @param newDef3 java.lang.String
*/
public void setDef3 ( String def3) {
this.def3=def3;
} 
 
/**
* ���� def4��Getter����.���������Զ�����4
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef4() {
return this.def4;
} 

/**
* ����def4��Setter����.���������Զ�����4
* ��������:2017-7-7
* @param newDef4 java.lang.String
*/
public void setDef4 ( String def4) {
this.def4=def4;
} 
 
/**
* ���� def5��Getter����.���������Զ�����5
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef5() {
return this.def5;
} 

/**
* ����def5��Setter����.���������Զ�����5
* ��������:2017-7-7
* @param newDef5 java.lang.String
*/
public void setDef5 ( String def5) {
this.def5=def5;
} 
 
/**
* ���� def6��Getter����.���������Զ�����6
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef6() {
return this.def6;
} 

/**
* ����def6��Setter����.���������Զ�����6
* ��������:2017-7-7
* @param newDef6 java.lang.String
*/
public void setDef6 ( String def6) {
this.def6=def6;
} 
 
/**
* ���� def7��Getter����.���������Զ�����7
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef7() {
return this.def7;
} 

/**
* ����def7��Setter����.���������Զ�����7
* ��������:2017-7-7
* @param newDef7 java.lang.String
*/
public void setDef7 ( String def7) {
this.def7=def7;
} 
 
/**
* ���� def8��Getter����.���������Զ�����8
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef8() {
return this.def8;
} 

/**
* ����def8��Setter����.���������Զ�����8
* ��������:2017-7-7
* @param newDef8 java.lang.String
*/
public void setDef8 ( String def8) {
this.def8=def8;
} 
 
/**
* ���� def9��Getter����.���������Զ�����9
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef9() {
return this.def9;
} 

/**
* ����def9��Setter����.���������Զ�����9
* ��������:2017-7-7
* @param newDef9 java.lang.String
*/
public void setDef9 ( String def9) {
this.def9=def9;
} 
 
/**
* ���� def10��Getter����.���������Զ�����10
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef10() {
return this.def10;
} 

/**
* ����def10��Setter����.���������Զ�����10
* ��������:2017-7-7
* @param newDef10 java.lang.String
*/
public void setDef10 ( String def10) {
this.def10=def10;
} 
 
/**
* ���� def11��Getter����.���������Զ�����11
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef11() {
return this.def11;
} 

/**
* ����def11��Setter����.���������Զ�����11
* ��������:2017-7-7
* @param newDef11 java.lang.String
*/
public void setDef11 ( String def11) {
this.def11=def11;
} 
 
/**
* ���� def12��Getter����.���������Զ�����12
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef12() {
return this.def12;
} 

/**
* ����def12��Setter����.���������Զ�����12
* ��������:2017-7-7
* @param newDef12 java.lang.String
*/
public void setDef12 ( String def12) {
this.def12=def12;
} 
 
/**
* ���� def13��Getter����.���������Զ�����13
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef13() {
return this.def13;
} 

/**
* ����def13��Setter����.���������Զ�����13
* ��������:2017-7-7
* @param newDef13 java.lang.String
*/
public void setDef13 ( String def13) {
this.def13=def13;
} 
 
/**
* ���� def14��Getter����.���������Զ�����14
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef14() {
return this.def14;
} 

/**
* ����def14��Setter����.���������Զ�����14
* ��������:2017-7-7
* @param newDef14 java.lang.String
*/
public void setDef14 ( String def14) {
this.def14=def14;
} 
 
/**
* ���� def15��Getter����.���������Զ�����15
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef15() {
return this.def15;
} 

/**
* ����def15��Setter����.���������Զ�����15
* ��������:2017-7-7
* @param newDef15 java.lang.String
*/
public void setDef15 ( String def15) {
this.def15=def15;
} 
 
/**
* ���� def16��Getter����.���������Զ�����16
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef16() {
return this.def16;
} 

/**
* ����def16��Setter����.���������Զ�����16
* ��������:2017-7-7
* @param newDef16 java.lang.String
*/
public void setDef16 ( String def16) {
this.def16=def16;
} 
 
/**
* ���� def17��Getter����.���������Զ�����17
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef17() {
return this.def17;
} 

/**
* ����def17��Setter����.���������Զ�����17
* ��������:2017-7-7
* @param newDef17 java.lang.String
*/
public void setDef17 ( String def17) {
this.def17=def17;
} 
 
/**
* ���� def18��Getter����.���������Զ�����18
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef18() {
return this.def18;
} 

/**
* ����def18��Setter����.���������Զ�����18
* ��������:2017-7-7
* @param newDef18 java.lang.String
*/
public void setDef18 ( String def18) {
this.def18=def18;
} 
 
/**
* ���� def19��Getter����.���������Զ�����19
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef19() {
return this.def19;
} 

/**
* ����def19��Setter����.���������Զ�����19
* ��������:2017-7-7
* @param newDef19 java.lang.String
*/
public void setDef19 ( String def19) {
this.def19=def19;
} 
 
/**
* ���� def20��Getter����.���������Զ�����20
*  ��������:2017-7-7
* @return java.lang.String
*/
public String getDef20() {
return this.def20;
} 

/**
* ����def20��Setter����.���������Զ�����20
* ��������:2017-7-7
* @param newDef20 java.lang.String
*/
public void setDef20 ( String def20) {
this.def20=def20;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2017-7-7
* @return String
*/
public String getPk_ingredient_h(){
return this.pk_ingredient_h;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2017-7-7
* @param newPk_ingredient_h String
*/
public void setPk_ingredient_h(String pk_ingredient_h){
this.pk_ingredient_h=pk_ingredient_h;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-7-7
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-7-7
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.bingredient");
    }
   }
    