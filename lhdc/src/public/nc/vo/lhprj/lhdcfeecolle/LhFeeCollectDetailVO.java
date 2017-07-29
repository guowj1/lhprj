package nc.vo.lhprj.lhdcfeecolle;

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
 *  创建日期:2017-7-4
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhFeeCollectDetailVO extends SuperVO {
	
/**
*子表主键
*/
public String pk_feecolle_b;
/**
*行号
*/
public String crowno;
/**
*分厂
*/
public String pk_subcorp;
/**
*主材费
*/
public UFDouble fmainmny;
/**
*半成品
*/
public UFDouble fsemimny;
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
*总额自定义项1
*/
public UFDouble vbdef11;
/**
*总额自定义项2
*/
public UFDouble vbdef12;
/**
*总额自定义项3
*/
public UFDouble vbdef13;
/**
*总额自定义项4
*/
public UFDouble vbdef14;
/**
*总额自定义项5
*/
public UFDouble vbdef15;
/**
*总额自定义项6
*/
public UFDouble vbdef16;
/**
*总额自定义项7
*/
public UFDouble vbdef17;
/**
*总额自定义项8
*/
public UFDouble vbdef18;
/**
*总额自定义项9
*/
public UFDouble vbdef19;
/**
*总额自定义项10
*/
public UFDouble vbdef20;
/**
*上层单据主键
*/
public String pk_feecolle;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_feecolle_b的Getter方法.属性名：子表主键
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getPk_feecolle_b() {
return this.pk_feecolle_b;
} 

/**
* 属性pk_feecolle_b的Setter方法.属性名：子表主键
* 创建日期:2017-7-4
* @param newPk_feecolle_b java.lang.String
*/
public void setPk_feecolle_b ( String pk_feecolle_b) {
this.pk_feecolle_b=pk_feecolle_b;
} 
 
/**
* 属性 crowno的Getter方法.属性名：行号
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getCrowno() {
return this.crowno;
} 

/**
* 属性crowno的Setter方法.属性名：行号
* 创建日期:2017-7-4
* @param newCrowno java.lang.String
*/
public void setCrowno ( String crowno) {
this.crowno=crowno;
} 
 
/**
* 属性 pk_subcorp的Getter方法.属性名：分厂
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getPk_subcorp() {
return this.pk_subcorp;
} 

/**
* 属性pk_subcorp的Setter方法.属性名：分厂
* 创建日期:2017-7-4
* @param newPk_subcorp java.lang.String
*/
public void setPk_subcorp ( String pk_subcorp) {
this.pk_subcorp=pk_subcorp;
} 
 
/**
* 属性 fmainmny的Getter方法.属性名：主材费
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFmainmny() {
return this.fmainmny;
} 

/**
* 属性fmainmny的Setter方法.属性名：主材费
* 创建日期:2017-7-4
* @param newFmainmny nc.vo.pub.lang.UFDouble
*/
public void setFmainmny ( UFDouble fmainmny) {
this.fmainmny=fmainmny;
} 
 
/**
* 属性 fsemimny的Getter方法.属性名：半成品
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFsemimny() {
return this.fsemimny;
} 

/**
* 属性fsemimny的Setter方法.属性名：半成品
* 创建日期:2017-7-4
* @param newFsemimny nc.vo.pub.lang.UFDouble
*/
public void setFsemimny ( UFDouble fsemimny) {
this.fsemimny=fsemimny;
} 
 
/**
* 属性 vbdef1的Getter方法.属性名：吨耗自定义项1
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef1() {
return this.vbdef1;
} 

/**
* 属性vbdef1的Setter方法.属性名：吨耗自定义项1
* 创建日期:2017-7-4
* @param newVbdef1 nc.vo.pub.lang.UFDouble
*/
public void setVbdef1 ( UFDouble vbdef1) {
this.vbdef1=vbdef1;
} 
 
/**
* 属性 vbdef2的Getter方法.属性名：吨耗自定义项2
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef2() {
return this.vbdef2;
} 

/**
* 属性vbdef2的Setter方法.属性名：吨耗自定义项2
* 创建日期:2017-7-4
* @param newVbdef2 nc.vo.pub.lang.UFDouble
*/
public void setVbdef2 ( UFDouble vbdef2) {
this.vbdef2=vbdef2;
} 
 
/**
* 属性 vbdef3的Getter方法.属性名：吨耗自定义项3
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef3() {
return this.vbdef3;
} 

/**
* 属性vbdef3的Setter方法.属性名：吨耗自定义项3
* 创建日期:2017-7-4
* @param newVbdef3 nc.vo.pub.lang.UFDouble
*/
public void setVbdef3 ( UFDouble vbdef3) {
this.vbdef3=vbdef3;
} 
 
/**
* 属性 vbdef4的Getter方法.属性名：吨耗自定义项4
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef4() {
return this.vbdef4;
} 

/**
* 属性vbdef4的Setter方法.属性名：吨耗自定义项4
* 创建日期:2017-7-4
* @param newVbdef4 nc.vo.pub.lang.UFDouble
*/
public void setVbdef4 ( UFDouble vbdef4) {
this.vbdef4=vbdef4;
} 
 
/**
* 属性 vbdef5的Getter方法.属性名：吨耗自定义项5
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef5() {
return this.vbdef5;
} 

/**
* 属性vbdef5的Setter方法.属性名：吨耗自定义项5
* 创建日期:2017-7-4
* @param newVbdef5 nc.vo.pub.lang.UFDouble
*/
public void setVbdef5 ( UFDouble vbdef5) {
this.vbdef5=vbdef5;
} 
 
/**
* 属性 vbdef6的Getter方法.属性名：吨耗自定义项6
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef6() {
return this.vbdef6;
} 

/**
* 属性vbdef6的Setter方法.属性名：吨耗自定义项6
* 创建日期:2017-7-4
* @param newVbdef6 nc.vo.pub.lang.UFDouble
*/
public void setVbdef6 ( UFDouble vbdef6) {
this.vbdef6=vbdef6;
} 
 
/**
* 属性 vbdef7的Getter方法.属性名：吨耗自定义项7
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef7() {
return this.vbdef7;
} 

/**
* 属性vbdef7的Setter方法.属性名：吨耗自定义项7
* 创建日期:2017-7-4
* @param newVbdef7 nc.vo.pub.lang.UFDouble
*/
public void setVbdef7 ( UFDouble vbdef7) {
this.vbdef7=vbdef7;
} 
 
/**
* 属性 vbdef8的Getter方法.属性名：吨耗自定义项8
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef8() {
return this.vbdef8;
} 

/**
* 属性vbdef8的Setter方法.属性名：吨耗自定义项8
* 创建日期:2017-7-4
* @param newVbdef8 nc.vo.pub.lang.UFDouble
*/
public void setVbdef8 ( UFDouble vbdef8) {
this.vbdef8=vbdef8;
} 
 
/**
* 属性 vbdef9的Getter方法.属性名：吨耗自定义项9
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef9() {
return this.vbdef9;
} 

/**
* 属性vbdef9的Setter方法.属性名：吨耗自定义项9
* 创建日期:2017-7-4
* @param newVbdef9 nc.vo.pub.lang.UFDouble
*/
public void setVbdef9 ( UFDouble vbdef9) {
this.vbdef9=vbdef9;
} 
 
/**
* 属性 vbdef10的Getter方法.属性名：吨耗自定义项10
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef10() {
return this.vbdef10;
} 

/**
* 属性vbdef10的Setter方法.属性名：吨耗自定义项10
* 创建日期:2017-7-4
* @param newVbdef10 nc.vo.pub.lang.UFDouble
*/
public void setVbdef10 ( UFDouble vbdef10) {
this.vbdef10=vbdef10;
} 
 
/**
* 属性 vbdef11的Getter方法.属性名：总额自定义项1
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef11() {
return this.vbdef11;
} 

/**
* 属性vbdef11的Setter方法.属性名：总额自定义项1
* 创建日期:2017-7-4
* @param newVbdef11 nc.vo.pub.lang.UFDouble
*/
public void setVbdef11 ( UFDouble vbdef11) {
this.vbdef11=vbdef11;
} 
 
/**
* 属性 vbdef12的Getter方法.属性名：总额自定义项2
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef12() {
return this.vbdef12;
} 

/**
* 属性vbdef12的Setter方法.属性名：总额自定义项2
* 创建日期:2017-7-4
* @param newVbdef12 nc.vo.pub.lang.UFDouble
*/
public void setVbdef12 ( UFDouble vbdef12) {
this.vbdef12=vbdef12;
} 
 
/**
* 属性 vbdef13的Getter方法.属性名：总额自定义项3
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef13() {
return this.vbdef13;
} 

/**
* 属性vbdef13的Setter方法.属性名：总额自定义项3
* 创建日期:2017-7-4
* @param newVbdef13 nc.vo.pub.lang.UFDouble
*/
public void setVbdef13 ( UFDouble vbdef13) {
this.vbdef13=vbdef13;
} 
 
/**
* 属性 vbdef14的Getter方法.属性名：总额自定义项4
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef14() {
return this.vbdef14;
} 

/**
* 属性vbdef14的Setter方法.属性名：总额自定义项4
* 创建日期:2017-7-4
* @param newVbdef14 nc.vo.pub.lang.UFDouble
*/
public void setVbdef14 ( UFDouble vbdef14) {
this.vbdef14=vbdef14;
} 
 
/**
* 属性 vbdef15的Getter方法.属性名：总额自定义项5
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef15() {
return this.vbdef15;
} 

/**
* 属性vbdef15的Setter方法.属性名：总额自定义项5
* 创建日期:2017-7-4
* @param newVbdef15 nc.vo.pub.lang.UFDouble
*/
public void setVbdef15 ( UFDouble vbdef15) {
this.vbdef15=vbdef15;
} 
 
/**
* 属性 vbdef16的Getter方法.属性名：总额自定义项6
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef16() {
return this.vbdef16;
} 

/**
* 属性vbdef16的Setter方法.属性名：总额自定义项6
* 创建日期:2017-7-4
* @param newVbdef16 nc.vo.pub.lang.UFDouble
*/
public void setVbdef16 ( UFDouble vbdef16) {
this.vbdef16=vbdef16;
} 
 
/**
* 属性 vbdef17的Getter方法.属性名：总额自定义项7
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef17() {
return this.vbdef17;
} 

/**
* 属性vbdef17的Setter方法.属性名：总额自定义项7
* 创建日期:2017-7-4
* @param newVbdef17 nc.vo.pub.lang.UFDouble
*/
public void setVbdef17 ( UFDouble vbdef17) {
this.vbdef17=vbdef17;
} 
 
/**
* 属性 vbdef18的Getter方法.属性名：总额自定义项8
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef18() {
return this.vbdef18;
} 

/**
* 属性vbdef18的Setter方法.属性名：总额自定义项8
* 创建日期:2017-7-4
* @param newVbdef18 nc.vo.pub.lang.UFDouble
*/
public void setVbdef18 ( UFDouble vbdef18) {
this.vbdef18=vbdef18;
} 
 
/**
* 属性 vbdef19的Getter方法.属性名：总额自定义项9
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef19() {
return this.vbdef19;
} 

/**
* 属性vbdef19的Setter方法.属性名：总额自定义项9
* 创建日期:2017-7-4
* @param newVbdef19 nc.vo.pub.lang.UFDouble
*/
public void setVbdef19 ( UFDouble vbdef19) {
this.vbdef19=vbdef19;
} 
 
/**
* 属性 vbdef20的Getter方法.属性名：总额自定义项10
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVbdef20() {
return this.vbdef20;
} 

/**
* 属性vbdef20的Setter方法.属性名：总额自定义项10
* 创建日期:2017-7-4
* @param newVbdef20 nc.vo.pub.lang.UFDouble
*/
public void setVbdef20 ( UFDouble vbdef20) {
this.vbdef20=vbdef20;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2017-7-4
* @return String
*/
public String getPk_feecolle(){
return this.pk_feecolle;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2017-7-4
* @param newPk_feecolle String
*/
public void setPk_feecolle(String pk_feecolle){
this.pk_feecolle=pk_feecolle;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-7-4
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhFeeCollectDetail");
    }
   }
    