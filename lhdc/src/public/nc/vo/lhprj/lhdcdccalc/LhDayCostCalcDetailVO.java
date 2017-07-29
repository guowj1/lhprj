package nc.vo.lhprj.lhdcdccalc;

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
 *  ��������:2017-7-6
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhDayCostCalcDetailVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String pk_dccalc_b;
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
*��λ���Ϸ�
*/
public UFDouble fmatunitcost;
/**
*���Ϸ�
*/
public UFDouble fmatcost;
/**
*���Ʒ
*/
public UFDouble fsemicost;
/**
*�ֺ��Զ�����1
*/
public UFDouble vbdef1;
/**
*�ֺ��Զ�����2
*/
public UFDouble vbdef2;
/**
*�ֺ��Զ�����3
*/
public UFDouble vbdef3;
/**
*�ֺ��Զ�����4
*/
public UFDouble vbdef4;
/**
*�ֺ��Զ�����5
*/
public UFDouble vbdef5;
/**
*�ֺ��Զ�����6
*/
public UFDouble vbdef6;
/**
*�ֺ��Զ�����7
*/
public UFDouble vbdef7;
/**
*�ֺ��Զ�����8
*/
public UFDouble vbdef8;
/**
*�ֺ��Զ�����9
*/
public UFDouble vbdef9;
/**
*�ֺ��Զ�����10
*/
public UFDouble vbdef10;
/**
*�ֺ��Զ�����11
*/
public UFDouble vbdef11;
/**
*�ֺ��Զ�����12
*/
public UFDouble vbdef12;
/**
*�ֺ��Զ�����13
*/
public UFDouble vbdef13;
/**
*�ֺ��Զ�����14
*/
public UFDouble vbdef14;
/**
*�ֺ��Զ�����15
*/
public UFDouble vbdef15;
/**
*�ֺ��Զ�����16
*/
public UFDouble vbdef16;
/**
*�ֺ��Զ�����17
*/
public UFDouble vbdef17;
/**
*�ֺ��Զ�����18
*/
public UFDouble vbdef18;
/**
*�ֺ��Զ�����19
*/
public UFDouble vbdef19;
/**
*�ֺ��Զ�����20
*/
public UFDouble vbdef20;
/**
*�ܳɱ�
*/
public UFDouble fcostsum;
/**
*��λ�ɱ�
*/
public UFDouble fcostunit;
/**
*�ϲ㵥������
*/
public String pk_dccalc;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_dccalc_b��Getter����.���������ӱ�����
*  ��������:2017-7-6
* @return java.lang.String
*/
public String getPk_dccalc_b() {
return this.pk_dccalc_b;
} 

/**
* ����pk_dccalc_b��Setter����.���������ӱ�����
* ��������:2017-7-6
* @param newPk_dccalc_b java.lang.String
*/
public void setPk_dccalc_b ( String pk_dccalc_b) {
this.pk_dccalc_b=pk_dccalc_b;
} 
 
/**
* ���� crowno��Getter����.���������к�
*  ��������:2017-7-6
* @return java.lang.String
*/
public String getCrowno() {
return this.crowno;
} 

/**
* ����crowno��Setter����.���������к�
* ��������:2017-7-6
* @param newCrowno java.lang.String
*/
public void setCrowno ( String crowno) {
this.crowno=crowno;
} 
 
/**
* ���� pk_subcorp��Getter����.���������ֳ�
*  ��������:2017-7-6
* @return java.lang.String
*/
public String getPk_subcorp() {
return this.pk_subcorp;
} 

/**
* ����pk_subcorp��Setter����.���������ֳ�
* ��������:2017-7-6
* @param newPk_subcorp java.lang.String
*/
public void setPk_subcorp ( String pk_subcorp) {
this.pk_subcorp=pk_subcorp;
} 
 
/**
* ���� pk_marbasclass��Getter����.����������Ʒ��
*  ��������:2017-7-6
* @return nc.vo.bd.material.MaterialVO
*/
public String getPk_marbasclass() {
return this.pk_marbasclass;
} 

/**
* ����pk_marbasclass��Setter����.����������Ʒ��
* ��������:2017-7-6
* @param newPk_marbasclass nc.vo.bd.material.MaterialVO
*/
public void setPk_marbasclass ( String pk_marbasclass) {
this.pk_marbasclass=pk_marbasclass;
} 
 
/**
* ���� fqty��Getter����.������������
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFqty() {
return this.fqty;
} 

/**
* ����fqty��Setter����.������������
* ��������:2017-7-6
* @param newFqty nc.vo.pub.lang.UFDouble
*/
public void setFqty ( UFDouble fqty) {
this.fqty=fqty;
} 
 
/**
* ���� fmatunitcost��Getter����.����������λ���Ϸ�
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFmatunitcost() {
return this.fmatunitcost;
} 

/**
* ����fmatunitcost��Setter����.����������λ���Ϸ�
* ��������:2017-7-6
* @param newFmatunitcost nc.vo.pub.lang.UFDouble
*/
public void setFmatunitcost ( UFDouble fmatunitcost) {
this.fmatunitcost=fmatunitcost;
} 
 
/**
* ���� fmatcost��Getter����.�����������Ϸ�
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFmatcost() {
return this.fmatcost;
} 

/**
* ����fmatcost��Setter����.�����������Ϸ�
* ��������:2017-7-6
* @param newFmatcost nc.vo.pub.lang.UFDouble
*/
public void setFmatcost ( UFDouble fmatcost) {
this.fmatcost=fmatcost;
} 
 
/**
* ���� fsemicost��Getter����.�����������Ʒ
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFsemicost() {
return this.fsemicost;
} 

/**
* ����fsemicost��Setter����.�����������Ʒ
* ��������:2017-7-6
* @param newFsemicost nc.vo.pub.lang.UFDouble
*/
public void setFsemicost ( UFDouble fsemicost) {
this.fsemicost=fsemicost;
} 
 
/**
* ���� vbdef1��Getter����.���������ֺ��Զ�����1
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef1() {
return this.vbdef1;
} 

/**
* ����vbdef1��Setter����.���������ֺ��Զ�����1
* ��������:2017-7-6
* @param newVbdef1 nc.vo.pub.lang.UFDouble
*/
public void setVbdef1 ( UFDouble vbdef1) {
this.vbdef1=vbdef1;
} 
 
/**
* ���� vbdef2��Getter����.���������ֺ��Զ�����2
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef2() {
return this.vbdef2;
} 

/**
* ����vbdef2��Setter����.���������ֺ��Զ�����2
* ��������:2017-7-6
* @param newVbdef2 nc.vo.pub.lang.UFDouble
*/
public void setVbdef2 ( UFDouble vbdef2) {
this.vbdef2=vbdef2;
} 
 
/**
* ���� vbdef3��Getter����.���������ֺ��Զ�����3
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef3() {
return this.vbdef3;
} 

/**
* ����vbdef3��Setter����.���������ֺ��Զ�����3
* ��������:2017-7-6
* @param newVbdef3 nc.vo.pub.lang.UFDouble
*/
public void setVbdef3 ( UFDouble vbdef3) {
this.vbdef3=vbdef3;
} 
 
/**
* ���� vbdef4��Getter����.���������ֺ��Զ�����4
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef4() {
return this.vbdef4;
} 

/**
* ����vbdef4��Setter����.���������ֺ��Զ�����4
* ��������:2017-7-6
* @param newVbdef4 nc.vo.pub.lang.UFDouble
*/
public void setVbdef4 ( UFDouble vbdef4) {
this.vbdef4=vbdef4;
} 
 
/**
* ���� vbdef5��Getter����.���������ֺ��Զ�����5
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef5() {
return this.vbdef5;
} 

/**
* ����vbdef5��Setter����.���������ֺ��Զ�����5
* ��������:2017-7-6
* @param newVbdef5 nc.vo.pub.lang.UFDouble
*/
public void setVbdef5 ( UFDouble vbdef5) {
this.vbdef5=vbdef5;
} 
 
/**
* ���� vbdef6��Getter����.���������ֺ��Զ�����6
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef6() {
return this.vbdef6;
} 

/**
* ����vbdef6��Setter����.���������ֺ��Զ�����6
* ��������:2017-7-6
* @param newVbdef6 nc.vo.pub.lang.UFDouble
*/
public void setVbdef6 ( UFDouble vbdef6) {
this.vbdef6=vbdef6;
} 
 
/**
* ���� vbdef7��Getter����.���������ֺ��Զ�����7
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef7() {
return this.vbdef7;
} 

/**
* ����vbdef7��Setter����.���������ֺ��Զ�����7
* ��������:2017-7-6
* @param newVbdef7 nc.vo.pub.lang.UFDouble
*/
public void setVbdef7 ( UFDouble vbdef7) {
this.vbdef7=vbdef7;
} 
 
/**
* ���� vbdef8��Getter����.���������ֺ��Զ�����8
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef8() {
return this.vbdef8;
} 

/**
* ����vbdef8��Setter����.���������ֺ��Զ�����8
* ��������:2017-7-6
* @param newVbdef8 nc.vo.pub.lang.UFDouble
*/
public void setVbdef8 ( UFDouble vbdef8) {
this.vbdef8=vbdef8;
} 
 
/**
* ���� vbdef9��Getter����.���������ֺ��Զ�����9
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef9() {
return this.vbdef9;
} 

/**
* ����vbdef9��Setter����.���������ֺ��Զ�����9
* ��������:2017-7-6
* @param newVbdef9 nc.vo.pub.lang.UFDouble
*/
public void setVbdef9 ( UFDouble vbdef9) {
this.vbdef9=vbdef9;
} 
 
/**
* ���� vbdef10��Getter����.���������ֺ��Զ�����10
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef10() {
return this.vbdef10;
} 

/**
* ����vbdef10��Setter����.���������ֺ��Զ�����10
* ��������:2017-7-6
* @param newVbdef10 nc.vo.pub.lang.UFDouble
*/
public void setVbdef10 ( UFDouble vbdef10) {
this.vbdef10=vbdef10;
} 
 
/**
* ���� vbdef11��Getter����.���������ֺ��Զ�����11
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef11() {
return this.vbdef11;
} 

/**
* ����vbdef11��Setter����.���������ֺ��Զ�����11
* ��������:2017-7-6
* @param newVbdef11 nc.vo.pub.lang.UFDouble
*/
public void setVbdef11 ( UFDouble vbdef11) {
this.vbdef11=vbdef11;
} 
 
/**
* ���� vbdef12��Getter����.���������ֺ��Զ�����12
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef12() {
return this.vbdef12;
} 

/**
* ����vbdef12��Setter����.���������ֺ��Զ�����12
* ��������:2017-7-6
* @param newVbdef12 nc.vo.pub.lang.UFDouble
*/
public void setVbdef12 ( UFDouble vbdef12) {
this.vbdef12=vbdef12;
} 
 
/**
* ���� vbdef13��Getter����.���������ֺ��Զ�����13
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef13() {
return this.vbdef13;
} 

/**
* ����vbdef13��Setter����.���������ֺ��Զ�����13
* ��������:2017-7-6
* @param newVbdef13 nc.vo.pub.lang.UFDouble
*/
public void setVbdef13 ( UFDouble vbdef13) {
this.vbdef13=vbdef13;
} 
 
/**
* ���� vbdef14��Getter����.���������ֺ��Զ�����14
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef14() {
return this.vbdef14;
} 

/**
* ����vbdef14��Setter����.���������ֺ��Զ�����14
* ��������:2017-7-6
* @param newVbdef14 nc.vo.pub.lang.UFDouble
*/
public void setVbdef14 ( UFDouble vbdef14) {
this.vbdef14=vbdef14;
} 
 
/**
* ���� vbdef15��Getter����.���������ֺ��Զ�����15
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef15() {
return this.vbdef15;
} 

/**
* ����vbdef15��Setter����.���������ֺ��Զ�����15
* ��������:2017-7-6
* @param newVbdef15 nc.vo.pub.lang.UFDouble
*/
public void setVbdef15 ( UFDouble vbdef15) {
this.vbdef15=vbdef15;
} 
 
/**
* ���� vbdef16��Getter����.���������ֺ��Զ�����16
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef16() {
return this.vbdef16;
} 

/**
* ����vbdef16��Setter����.���������ֺ��Զ�����16
* ��������:2017-7-6
* @param newVbdef16 nc.vo.pub.lang.UFDouble
*/
public void setVbdef16 ( UFDouble vbdef16) {
this.vbdef16=vbdef16;
} 
 
/**
* ���� vbdef17��Getter����.���������ֺ��Զ�����17
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef17() {
return this.vbdef17;
} 

/**
* ����vbdef17��Setter����.���������ֺ��Զ�����17
* ��������:2017-7-6
* @param newVbdef17 nc.vo.pub.lang.UFDouble
*/
public void setVbdef17 ( UFDouble vbdef17) {
this.vbdef17=vbdef17;
} 
 
/**
* ���� vbdef18��Getter����.���������ֺ��Զ�����18
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef18() {
return this.vbdef18;
} 

/**
* ����vbdef18��Setter����.���������ֺ��Զ�����18
* ��������:2017-7-6
* @param newVbdef18 nc.vo.pub.lang.UFDouble
*/
public void setVbdef18 ( UFDouble vbdef18) {
this.vbdef18=vbdef18;
} 
 
/**
* ���� vbdef19��Getter����.���������ֺ��Զ�����19
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef19() {
return this.vbdef19;
} 

/**
* ����vbdef19��Setter����.���������ֺ��Զ�����19
* ��������:2017-7-6
* @param newVbdef19 nc.vo.pub.lang.UFDouble
*/
public void setVbdef19 ( UFDouble vbdef19) {
this.vbdef19=vbdef19;
} 
 
/**
* ���� vbdef20��Getter����.���������ֺ��Զ�����20
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef20() {
return this.vbdef20;
} 

/**
* ����vbdef20��Setter����.���������ֺ��Զ�����20
* ��������:2017-7-6
* @param newVbdef20 nc.vo.pub.lang.UFDouble
*/
public void setVbdef20 ( UFDouble vbdef20) {
this.vbdef20=vbdef20;
} 
 
/**
* ���� fcostsum��Getter����.���������ܳɱ�
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFcostsum() {
return this.fcostsum;
} 

/**
* ����fcostsum��Setter����.���������ܳɱ�
* ��������:2017-7-6
* @param newFcostsum nc.vo.pub.lang.UFDouble
*/
public void setFcostsum ( UFDouble fcostsum) {
this.fcostsum=fcostsum;
} 
 
/**
* ���� fcostunit��Getter����.����������λ�ɱ�
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFcostunit() {
return this.fcostunit;
} 

/**
* ����fcostunit��Setter����.����������λ�ɱ�
* ��������:2017-7-6
* @param newFcostunit nc.vo.pub.lang.UFDouble
*/
public void setFcostunit ( UFDouble fcostunit) {
this.fcostunit=fcostunit;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2017-7-6
* @return String
*/
public String getPk_dccalc(){
return this.pk_dccalc;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2017-7-6
* @param newPk_dccalc String
*/
public void setPk_dccalc(String pk_dccalc){
this.pk_dccalc=pk_dccalc;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-7-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-7-6
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhDayCostCalcDetail");
    }
   }
    