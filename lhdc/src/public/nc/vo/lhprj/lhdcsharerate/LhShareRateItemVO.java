package nc.vo.lhprj.lhdcsharerate;

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
 *  创建日期:2017-6-18
 * @author 
 * @version NCPrj ??
 */
 
public class LhShareRateItemVO extends SuperVO {
	
/**
*子表主键
*/
public String pk_sharerateitem;
/**
*行号
*/
public String crowno;
/**
*产品类
*/
public String pk_marbasclass;
/**
*占比
*/
public UFDouble frate;
/**
*表体自定义项1
*/
public String vbdef1;
/**
*表体自定义项2
*/
public String vbdef2;
/**
*表体自定义项3
*/
public String vbdef3;
/**
*表体自定义项4
*/
public String vbdef4;
/**
*表体自定义项5
*/
public String vbdef5;
/**
*表体自定义项6
*/
public String vbdef6;
/**
*表体自定义项7
*/
public String vbdef7;
/**
*表体自定义项8
*/
public String vbdef8;
/**
*表体自定义项9
*/
public String vbdef9;
/**
*表体自定义项10
*/
public String vbdef10;
/**
*上层单据主键
*/
public String pk_sharerate;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_sharerateitem的Getter方法.属性名：子表主键
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getPk_sharerateitem() {
return this.pk_sharerateitem;
} 

/**
* 属性pk_sharerateitem的Setter方法.属性名：子表主键
* 创建日期:2017-6-18
* @param newPk_sharerateitem java.lang.String
*/
public void setPk_sharerateitem ( String pk_sharerateitem) {
this.pk_sharerateitem=pk_sharerateitem;
} 
 
/**
* 属性 crowno的Getter方法.属性名：行号
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getCrowno() {
return this.crowno;
} 

/**
* 属性crowno的Setter方法.属性名：行号
* 创建日期:2017-6-18
* @param newCrowno java.lang.String
*/
public void setCrowno ( String crowno) {
this.crowno=crowno;
} 
 
/**
* 属性 pk_marbasclass的Getter方法.属性名：产品类
*  创建日期:2017-6-18
* @return nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public String getPk_marbasclass() {
return this.pk_marbasclass;
} 

/**
* 属性pk_marbasclass的Setter方法.属性名：产品类
* 创建日期:2017-6-18
* @param newPk_marbasclass nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public void setPk_marbasclass ( String pk_marbasclass) {
this.pk_marbasclass=pk_marbasclass;
} 
 
/**
* 属性 frate的Getter方法.属性名：占比
*  创建日期:2017-6-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFrate() {
return this.frate;
} 

/**
* 属性frate的Setter方法.属性名：占比
* 创建日期:2017-6-18
* @param newFrate nc.vo.pub.lang.UFDouble
*/
public void setFrate ( UFDouble frate) {
this.frate=frate;
} 
 
/**
* 属性 vbdef1的Getter方法.属性名：表体自定义项1
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef1() {
return this.vbdef1;
} 

/**
* 属性vbdef1的Setter方法.属性名：表体自定义项1
* 创建日期:2017-6-18
* @param newVbdef1 java.lang.String
*/
public void setVbdef1 ( String vbdef1) {
this.vbdef1=vbdef1;
} 
 
/**
* 属性 vbdef2的Getter方法.属性名：表体自定义项2
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef2() {
return this.vbdef2;
} 

/**
* 属性vbdef2的Setter方法.属性名：表体自定义项2
* 创建日期:2017-6-18
* @param newVbdef2 java.lang.String
*/
public void setVbdef2 ( String vbdef2) {
this.vbdef2=vbdef2;
} 
 
/**
* 属性 vbdef3的Getter方法.属性名：表体自定义项3
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef3() {
return this.vbdef3;
} 

/**
* 属性vbdef3的Setter方法.属性名：表体自定义项3
* 创建日期:2017-6-18
* @param newVbdef3 java.lang.String
*/
public void setVbdef3 ( String vbdef3) {
this.vbdef3=vbdef3;
} 
 
/**
* 属性 vbdef4的Getter方法.属性名：表体自定义项4
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef4() {
return this.vbdef4;
} 

/**
* 属性vbdef4的Setter方法.属性名：表体自定义项4
* 创建日期:2017-6-18
* @param newVbdef4 java.lang.String
*/
public void setVbdef4 ( String vbdef4) {
this.vbdef4=vbdef4;
} 
 
/**
* 属性 vbdef5的Getter方法.属性名：表体自定义项5
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef5() {
return this.vbdef5;
} 

/**
* 属性vbdef5的Setter方法.属性名：表体自定义项5
* 创建日期:2017-6-18
* @param newVbdef5 java.lang.String
*/
public void setVbdef5 ( String vbdef5) {
this.vbdef5=vbdef5;
} 
 
/**
* 属性 vbdef6的Getter方法.属性名：表体自定义项6
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef6() {
return this.vbdef6;
} 

/**
* 属性vbdef6的Setter方法.属性名：表体自定义项6
* 创建日期:2017-6-18
* @param newVbdef6 java.lang.String
*/
public void setVbdef6 ( String vbdef6) {
this.vbdef6=vbdef6;
} 
 
/**
* 属性 vbdef7的Getter方法.属性名：表体自定义项7
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef7() {
return this.vbdef7;
} 

/**
* 属性vbdef7的Setter方法.属性名：表体自定义项7
* 创建日期:2017-6-18
* @param newVbdef7 java.lang.String
*/
public void setVbdef7 ( String vbdef7) {
this.vbdef7=vbdef7;
} 
 
/**
* 属性 vbdef8的Getter方法.属性名：表体自定义项8
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef8() {
return this.vbdef8;
} 

/**
* 属性vbdef8的Setter方法.属性名：表体自定义项8
* 创建日期:2017-6-18
* @param newVbdef8 java.lang.String
*/
public void setVbdef8 ( String vbdef8) {
this.vbdef8=vbdef8;
} 
 
/**
* 属性 vbdef9的Getter方法.属性名：表体自定义项9
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef9() {
return this.vbdef9;
} 

/**
* 属性vbdef9的Setter方法.属性名：表体自定义项9
* 创建日期:2017-6-18
* @param newVbdef9 java.lang.String
*/
public void setVbdef9 ( String vbdef9) {
this.vbdef9=vbdef9;
} 
 
/**
* 属性 vbdef10的Getter方法.属性名：表体自定义项10
*  创建日期:2017-6-18
* @return java.lang.String
*/
public String getVbdef10() {
return this.vbdef10;
} 

/**
* 属性vbdef10的Setter方法.属性名：表体自定义项10
* 创建日期:2017-6-18
* @param newVbdef10 java.lang.String
*/
public void setVbdef10 ( String vbdef10) {
this.vbdef10=vbdef10;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2017-6-18
* @return String
*/
public String getPk_sharerate(){
return this.pk_sharerate;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2017-6-18
* @param newPk_sharerate String
*/
public void setPk_sharerate(String pk_sharerate){
this.pk_sharerate=pk_sharerate;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-6-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-6-18
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
    