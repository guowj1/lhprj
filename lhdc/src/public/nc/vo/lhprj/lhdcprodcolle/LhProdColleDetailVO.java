package nc.vo.lhprj.lhdcprodcolle;

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
 
public class LhProdColleDetailVO extends SuperVO {
	
/**
*子表主键
*/
public String pk_prodcolle_b;
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
*上层单据主键
*/
public String pk_prodcolle;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_prodcolle_b的Getter方法.属性名：子表主键
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getPk_prodcolle_b() {
return this.pk_prodcolle_b;
} 

/**
* 属性pk_prodcolle_b的Setter方法.属性名：子表主键
* 创建日期:2017-7-4
* @param newPk_prodcolle_b java.lang.String
*/
public void setPk_prodcolle_b ( String pk_prodcolle_b) {
this.pk_prodcolle_b=pk_prodcolle_b;
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
* 属性 pk_marbasclass的Getter方法.属性名：产品类
*  创建日期:2017-7-4
* @return nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public String getPk_marbasclass() {
return this.pk_marbasclass;
} 

/**
* 属性pk_marbasclass的Setter方法.属性名：产品类
* 创建日期:2017-7-4
* @param newPk_marbasclass nc.vo.bd.material.marbasclass.MarBasClassVO
*/
public void setPk_marbasclass ( String pk_marbasclass) {
this.pk_marbasclass=pk_marbasclass;
} 
 
/**
* 属性 fqty的Getter方法.属性名：产量
*  创建日期:2017-7-4
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getFqty() {
return this.fqty;
} 

/**
* 属性fqty的Setter方法.属性名：产量
* 创建日期:2017-7-4
* @param newFqty nc.vo.pub.lang.UFDouble
*/
public void setFqty ( UFDouble fqty) {
this.fqty=fqty;
} 
 
/**
* 属性 vbdef1的Getter方法.属性名：表体自定义项1
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getVbdef1() {
return this.vbdef1;
} 

/**
* 属性vbdef1的Setter方法.属性名：表体自定义项1
* 创建日期:2017-7-4
* @param newVbdef1 java.lang.String
*/
public void setVbdef1 ( String vbdef1) {
this.vbdef1=vbdef1;
} 
 
/**
* 属性 vbdef2的Getter方法.属性名：表体自定义项2
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getVbdef2() {
return this.vbdef2;
} 

/**
* 属性vbdef2的Setter方法.属性名：表体自定义项2
* 创建日期:2017-7-4
* @param newVbdef2 java.lang.String
*/
public void setVbdef2 ( String vbdef2) {
this.vbdef2=vbdef2;
} 
 
/**
* 属性 vbdef3的Getter方法.属性名：表体自定义项3
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getVbdef3() {
return this.vbdef3;
} 

/**
* 属性vbdef3的Setter方法.属性名：表体自定义项3
* 创建日期:2017-7-4
* @param newVbdef3 java.lang.String
*/
public void setVbdef3 ( String vbdef3) {
this.vbdef3=vbdef3;
} 
 
/**
* 属性 vbdef4的Getter方法.属性名：表体自定义项4
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getVbdef4() {
return this.vbdef4;
} 

/**
* 属性vbdef4的Setter方法.属性名：表体自定义项4
* 创建日期:2017-7-4
* @param newVbdef4 java.lang.String
*/
public void setVbdef4 ( String vbdef4) {
this.vbdef4=vbdef4;
} 
 
/**
* 属性 vbdef5的Getter方法.属性名：表体自定义项5
*  创建日期:2017-7-4
* @return java.lang.String
*/
public String getVbdef5() {
return this.vbdef5;
} 

/**
* 属性vbdef5的Setter方法.属性名：表体自定义项5
* 创建日期:2017-7-4
* @param newVbdef5 java.lang.String
*/
public void setVbdef5 ( String vbdef5) {
this.vbdef5=vbdef5;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2017-7-4
* @return String
*/
public String getPk_prodcolle(){
return this.pk_prodcolle;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2017-7-4
* @param newPk_prodcolle String
*/
public void setPk_prodcolle(String pk_prodcolle){
this.pk_prodcolle=pk_prodcolle;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhProdColleDetail");
    }
   }
    