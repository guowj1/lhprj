package nc.vo.lhprj.lhtransbill;

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
 *  创建日期:2017-3-12
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhTransBillDetailVO extends SuperVO {
	
/**
*子表主键
*/
public java.lang.String pk_transbill_b;
/**
*物料编码
*/
public java.lang.String matcode;
/**
*转库数量
*/
public nc.vo.pub.lang.UFDouble iqty;
/**
*上层单据主键
*/
public String pk_transbill;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_transbill_b的Getter方法.属性名：子表主键
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_transbill_b() {
return this.pk_transbill_b;
} 

/**
* 属性pk_transbill_b的Setter方法.属性名：子表主键
* 创建日期:2017-3-12
* @param newPk_transbill_b java.lang.String
*/
public void setPk_transbill_b ( java.lang.String pk_transbill_b) {
this.pk_transbill_b=pk_transbill_b;
} 
 
/**
* 属性 matcode的Getter方法.属性名：物料编码
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatcode() {
return this.matcode;
} 

/**
* 属性matcode的Setter方法.属性名：物料编码
* 创建日期:2017-3-12
* @param newMatcode java.lang.String
*/
public void setMatcode ( java.lang.String matcode) {
this.matcode=matcode;
} 
 
/**
* 属性 iqty的Getter方法.属性名：转库数量
*  创建日期:2017-3-12
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getIqty() {
return this.iqty;
} 

/**
* 属性iqty的Setter方法.属性名：转库数量
* 创建日期:2017-3-12
* @param newIqty nc.vo.pub.lang.UFDouble
*/
public void setIqty ( nc.vo.pub.lang.UFDouble iqty) {
this.iqty=iqty;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2017-3-12
* @return String
*/
public String getPk_transbill(){
return this.pk_transbill;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2017-3-12
* @param newPk_transbill String
*/
public void setPk_transbill(String pk_transbill){
this.pk_transbill=pk_transbill;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-3-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-3-12
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
    