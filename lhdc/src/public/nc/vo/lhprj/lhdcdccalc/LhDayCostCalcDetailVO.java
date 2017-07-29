package nc.vo.lhprj.lhdcdccalc;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2017-7-6
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhDayCostCalcDetailVO extends SuperVO {
	
/**
*子表主键
*/
public String pk_dccalc_b;
/**
*行号
*/
public String crowno;
/**
*分厂
*/
public String pk_subcorp;
/**
*产品类
*/
public String pk_marbasclass;
/**
*产量
*/
public UFDouble fqty;
/**
*单位材料费
*/
public UFDouble fmatunitcost;
/**
*材料费
*/
public UFDouble fmatcost;
/**
*半成品
*/
public UFDouble fsemicost;
/**
*吨耗自定义项1
*/
public UFDouble vbdef1;
/**
*吨耗自定义项2
*/
public UFDouble vbdef2;
/**
*吨耗自定义项3
*/
public UFDouble vbdef3;
/**
*吨耗自定义项4
*/
public UFDouble vbdef4;
/**
*吨耗自定义项5
*/
public UFDouble vbdef5;
/**
*吨耗自定义项6
*/
public UFDouble vbdef6;
/**
*吨耗自定义项7
*/
public UFDouble vbdef7;
/**
*吨耗自定义项8
*/
public UFDouble vbdef8;
/**
*吨耗自定义项9
*/
public UFDouble vbdef9;
/**
*吨耗自定义项10
*/
public UFDouble vbdef10;
/**
*吨耗自定义项11
*/
public UFDouble vbdef11;
/**
*吨耗自定义项12
*/
public UFDouble vbdef12;
/**
*吨耗自定义项13
*/
public UFDouble vbdef13;
/**
*吨耗自定义项14
*/
public UFDouble vbdef14;
/**
*吨耗自定义项15
*/
public UFDouble vbdef15;
/**
*吨耗自定义项16
*/
public UFDouble vbdef16;
/**
*吨耗自定义项17
*/
public UFDouble vbdef17;
/**
*吨耗自定义项18
*/
public UFDouble vbdef18;
/**
*吨耗自定义项19
*/
public UFDouble vbdef19;
/**
*吨耗自定义项20
*/
public UFDouble vbdef20;
/**
*总成本
*/
public UFDouble fcostsum;
/**
*单位成本
*/
public UFDouble fcostunit;
/**
*上层单据主键
*/
public String pk_dccalc;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_dccalc_b的Getter方法.属性名：子表主键
*  创建日期:2017-7-6
* @return java.lang.String
*/
public String getPk_dccalc_b() {
return this.pk_dccalc_b;
} 

/**
* 属性pk_dccalc_b的Setter方法.属性名：子表主键
* 创建日期:2017-7-6
* @param newPk_dccalc_b java.lang.String
*/
public void setPk_dccalc_b ( String pk_dccalc_b) {
this.pk_dccalc_b=pk_dccalc_b;
} 
 
/**
* 属性 crowno的Getter方法.属性名：行号
*  创建日期:2017-7-6
* @return java.lang.String
*/
public String getCrowno() {
return this.crowno;
} 

/**
* 属性crowno的Setter方法.属性名：行号
* 创建日期:2017-7-6
* @param newCrowno java.lang.String
*/
public void setCrowno ( String crowno) {
this.crowno=crowno;
} 
 
/**
* 属性 pk_subcorp的Getter方法.属性名：分厂
*  创建日期:2017-7-6
* @return java.lang.String
*/
public String getPk_subcorp() {
return this.pk_subcorp;
} 

/**
* 属性pk_subcorp的Setter方法.属性名：分厂
* 创建日期:2017-7-6
* @param newPk_subcorp java.lang.String
*/
public void setPk_subcorp ( String pk_subcorp) {
this.pk_subcorp=pk_subcorp;
} 
 
/**
* 属性 pk_marbasclass的Getter方法.属性名：产品类
*  创建日期:2017-7-6
* @return nc.vo.bd.material.MaterialVO
*/
public String getPk_marbasclass() {
return this.pk_marbasclass;
} 

/**
* 属性pk_marbasclass的Setter方法.属性名：产品类
* 创建日期:2017-7-6
* @param newPk_marbasclass nc.vo.bd.material.MaterialVO
*/
public void setPk_marbasclass ( String pk_marbasclass) {
this.pk_marbasclass=pk_marbasclass;
} 
 
/**
* 属性 fqty的Getter方法.属性名：产量
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFqty() {
return this.fqty;
} 

/**
* 属性fqty的Setter方法.属性名：产量
* 创建日期:2017-7-6
* @param newFqty nc.vo.pub.lang.UFDouble
*/
public void setFqty ( UFDouble fqty) {
this.fqty=fqty;
} 
 
/**
* 属性 fmatunitcost的Getter方法.属性名：单位材料费
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFmatunitcost() {
return this.fmatunitcost;
} 

/**
* 属性fmatunitcost的Setter方法.属性名：单位材料费
* 创建日期:2017-7-6
* @param newFmatunitcost nc.vo.pub.lang.UFDouble
*/
public void setFmatunitcost ( UFDouble fmatunitcost) {
this.fmatunitcost=fmatunitcost;
} 
 
/**
* 属性 fmatcost的Getter方法.属性名：材料费
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFmatcost() {
return this.fmatcost;
} 

/**
* 属性fmatcost的Setter方法.属性名：材料费
* 创建日期:2017-7-6
* @param newFmatcost nc.vo.pub.lang.UFDouble
*/
public void setFmatcost ( UFDouble fmatcost) {
this.fmatcost=fmatcost;
} 
 
/**
* 属性 fsemicost的Getter方法.属性名：半成品
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFsemicost() {
return this.fsemicost;
} 

/**
* 属性fsemicost的Setter方法.属性名：半成品
* 创建日期:2017-7-6
* @param newFsemicost nc.vo.pub.lang.UFDouble
*/
public void setFsemicost ( UFDouble fsemicost) {
this.fsemicost=fsemicost;
} 
 
/**
* 属性 vbdef1的Getter方法.属性名：吨耗自定义项1
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef1() {
return this.vbdef1;
} 

/**
* 属性vbdef1的Setter方法.属性名：吨耗自定义项1
* 创建日期:2017-7-6
* @param newVbdef1 nc.vo.pub.lang.UFDouble
*/
public void setVbdef1 ( UFDouble vbdef1) {
this.vbdef1=vbdef1;
} 
 
/**
* 属性 vbdef2的Getter方法.属性名：吨耗自定义项2
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef2() {
return this.vbdef2;
} 

/**
* 属性vbdef2的Setter方法.属性名：吨耗自定义项2
* 创建日期:2017-7-6
* @param newVbdef2 nc.vo.pub.lang.UFDouble
*/
public void setVbdef2 ( UFDouble vbdef2) {
this.vbdef2=vbdef2;
} 
 
/**
* 属性 vbdef3的Getter方法.属性名：吨耗自定义项3
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef3() {
return this.vbdef3;
} 

/**
* 属性vbdef3的Setter方法.属性名：吨耗自定义项3
* 创建日期:2017-7-6
* @param newVbdef3 nc.vo.pub.lang.UFDouble
*/
public void setVbdef3 ( UFDouble vbdef3) {
this.vbdef3=vbdef3;
} 
 
/**
* 属性 vbdef4的Getter方法.属性名：吨耗自定义项4
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef4() {
return this.vbdef4;
} 

/**
* 属性vbdef4的Setter方法.属性名：吨耗自定义项4
* 创建日期:2017-7-6
* @param newVbdef4 nc.vo.pub.lang.UFDouble
*/
public void setVbdef4 ( UFDouble vbdef4) {
this.vbdef4=vbdef4;
} 
 
/**
* 属性 vbdef5的Getter方法.属性名：吨耗自定义项5
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef5() {
return this.vbdef5;
} 

/**
* 属性vbdef5的Setter方法.属性名：吨耗自定义项5
* 创建日期:2017-7-6
* @param newVbdef5 nc.vo.pub.lang.UFDouble
*/
public void setVbdef5 ( UFDouble vbdef5) {
this.vbdef5=vbdef5;
} 
 
/**
* 属性 vbdef6的Getter方法.属性名：吨耗自定义项6
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef6() {
return this.vbdef6;
} 

/**
* 属性vbdef6的Setter方法.属性名：吨耗自定义项6
* 创建日期:2017-7-6
* @param newVbdef6 nc.vo.pub.lang.UFDouble
*/
public void setVbdef6 ( UFDouble vbdef6) {
this.vbdef6=vbdef6;
} 
 
/**
* 属性 vbdef7的Getter方法.属性名：吨耗自定义项7
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef7() {
return this.vbdef7;
} 

/**
* 属性vbdef7的Setter方法.属性名：吨耗自定义项7
* 创建日期:2017-7-6
* @param newVbdef7 nc.vo.pub.lang.UFDouble
*/
public void setVbdef7 ( UFDouble vbdef7) {
this.vbdef7=vbdef7;
} 
 
/**
* 属性 vbdef8的Getter方法.属性名：吨耗自定义项8
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef8() {
return this.vbdef8;
} 

/**
* 属性vbdef8的Setter方法.属性名：吨耗自定义项8
* 创建日期:2017-7-6
* @param newVbdef8 nc.vo.pub.lang.UFDouble
*/
public void setVbdef8 ( UFDouble vbdef8) {
this.vbdef8=vbdef8;
} 
 
/**
* 属性 vbdef9的Getter方法.属性名：吨耗自定义项9
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef9() {
return this.vbdef9;
} 

/**
* 属性vbdef9的Setter方法.属性名：吨耗自定义项9
* 创建日期:2017-7-6
* @param newVbdef9 nc.vo.pub.lang.UFDouble
*/
public void setVbdef9 ( UFDouble vbdef9) {
this.vbdef9=vbdef9;
} 
 
/**
* 属性 vbdef10的Getter方法.属性名：吨耗自定义项10
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef10() {
return this.vbdef10;
} 

/**
* 属性vbdef10的Setter方法.属性名：吨耗自定义项10
* 创建日期:2017-7-6
* @param newVbdef10 nc.vo.pub.lang.UFDouble
*/
public void setVbdef10 ( UFDouble vbdef10) {
this.vbdef10=vbdef10;
} 
 
/**
* 属性 vbdef11的Getter方法.属性名：吨耗自定义项11
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef11() {
return this.vbdef11;
} 

/**
* 属性vbdef11的Setter方法.属性名：吨耗自定义项11
* 创建日期:2017-7-6
* @param newVbdef11 nc.vo.pub.lang.UFDouble
*/
public void setVbdef11 ( UFDouble vbdef11) {
this.vbdef11=vbdef11;
} 
 
/**
* 属性 vbdef12的Getter方法.属性名：吨耗自定义项12
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef12() {
return this.vbdef12;
} 

/**
* 属性vbdef12的Setter方法.属性名：吨耗自定义项12
* 创建日期:2017-7-6
* @param newVbdef12 nc.vo.pub.lang.UFDouble
*/
public void setVbdef12 ( UFDouble vbdef12) {
this.vbdef12=vbdef12;
} 
 
/**
* 属性 vbdef13的Getter方法.属性名：吨耗自定义项13
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef13() {
return this.vbdef13;
} 

/**
* 属性vbdef13的Setter方法.属性名：吨耗自定义项13
* 创建日期:2017-7-6
* @param newVbdef13 nc.vo.pub.lang.UFDouble
*/
public void setVbdef13 ( UFDouble vbdef13) {
this.vbdef13=vbdef13;
} 
 
/**
* 属性 vbdef14的Getter方法.属性名：吨耗自定义项14
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef14() {
return this.vbdef14;
} 

/**
* 属性vbdef14的Setter方法.属性名：吨耗自定义项14
* 创建日期:2017-7-6
* @param newVbdef14 nc.vo.pub.lang.UFDouble
*/
public void setVbdef14 ( UFDouble vbdef14) {
this.vbdef14=vbdef14;
} 
 
/**
* 属性 vbdef15的Getter方法.属性名：吨耗自定义项15
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef15() {
return this.vbdef15;
} 

/**
* 属性vbdef15的Setter方法.属性名：吨耗自定义项15
* 创建日期:2017-7-6
* @param newVbdef15 nc.vo.pub.lang.UFDouble
*/
public void setVbdef15 ( UFDouble vbdef15) {
this.vbdef15=vbdef15;
} 
 
/**
* 属性 vbdef16的Getter方法.属性名：吨耗自定义项16
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef16() {
return this.vbdef16;
} 

/**
* 属性vbdef16的Setter方法.属性名：吨耗自定义项16
* 创建日期:2017-7-6
* @param newVbdef16 nc.vo.pub.lang.UFDouble
*/
public void setVbdef16 ( UFDouble vbdef16) {
this.vbdef16=vbdef16;
} 
 
/**
* 属性 vbdef17的Getter方法.属性名：吨耗自定义项17
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef17() {
return this.vbdef17;
} 

/**
* 属性vbdef17的Setter方法.属性名：吨耗自定义项17
* 创建日期:2017-7-6
* @param newVbdef17 nc.vo.pub.lang.UFDouble
*/
public void setVbdef17 ( UFDouble vbdef17) {
this.vbdef17=vbdef17;
} 
 
/**
* 属性 vbdef18的Getter方法.属性名：吨耗自定义项18
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef18() {
return this.vbdef18;
} 

/**
* 属性vbdef18的Setter方法.属性名：吨耗自定义项18
* 创建日期:2017-7-6
* @param newVbdef18 nc.vo.pub.lang.UFDouble
*/
public void setVbdef18 ( UFDouble vbdef18) {
this.vbdef18=vbdef18;
} 
 
/**
* 属性 vbdef19的Getter方法.属性名：吨耗自定义项19
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef19() {
return this.vbdef19;
} 

/**
* 属性vbdef19的Setter方法.属性名：吨耗自定义项19
* 创建日期:2017-7-6
* @param newVbdef19 nc.vo.pub.lang.UFDouble
*/
public void setVbdef19 ( UFDouble vbdef19) {
this.vbdef19=vbdef19;
} 
 
/**
* 属性 vbdef20的Getter方法.属性名：吨耗自定义项20
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef20() {
return this.vbdef20;
} 

/**
* 属性vbdef20的Setter方法.属性名：吨耗自定义项20
* 创建日期:2017-7-6
* @param newVbdef20 nc.vo.pub.lang.UFDouble
*/
public void setVbdef20 ( UFDouble vbdef20) {
this.vbdef20=vbdef20;
} 
 
/**
* 属性 fcostsum的Getter方法.属性名：总成本
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFcostsum() {
return this.fcostsum;
} 

/**
* 属性fcostsum的Setter方法.属性名：总成本
* 创建日期:2017-7-6
* @param newFcostsum nc.vo.pub.lang.UFDouble
*/
public void setFcostsum ( UFDouble fcostsum) {
this.fcostsum=fcostsum;
} 
 
/**
* 属性 fcostunit的Getter方法.属性名：单位成本
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFcostunit() {
return this.fcostunit;
} 

/**
* 属性fcostunit的Setter方法.属性名：单位成本
* 创建日期:2017-7-6
* @param newFcostunit nc.vo.pub.lang.UFDouble
*/
public void setFcostunit ( UFDouble fcostunit) {
this.fcostunit=fcostunit;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2017-7-6
* @return String
*/
public String getPk_dccalc(){
return this.pk_dccalc;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2017-7-6
* @param newPk_dccalc String
*/
public void setPk_dccalc(String pk_dccalc){
this.pk_dccalc=pk_dccalc;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-7-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-7-6
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
    