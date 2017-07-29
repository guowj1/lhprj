package nc.vo.lhprj.lhcustomersub;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2017-5-31
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhCustomerSubVO extends SuperVO {
	
/**
*客户子户主键
*/
public java.lang.String pk_id;
/**
*客户子户编码
*/
public java.lang.String custsubcode;
/**
*客户子户名称
*/
public java.lang.String custsubname;
/**
*所属客户编码
*/
public java.lang.String custcode;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_id的Getter方法.属性名：客户子户主键
*  创建日期:2017-5-31
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：客户子户主键
* 创建日期:2017-5-31
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 custsubcode的Getter方法.属性名：客户子户编码
*  创建日期:2017-5-31
* @return java.lang.String
*/
public java.lang.String getCustsubcode() {
return this.custsubcode;
} 

/**
* 属性custsubcode的Setter方法.属性名：客户子户编码
* 创建日期:2017-5-31
* @param newCustsubcode java.lang.String
*/
public void setCustsubcode ( java.lang.String custsubcode) {
this.custsubcode=custsubcode;
} 
 
/**
* 属性 custsubname的Getter方法.属性名：客户子户名称
*  创建日期:2017-5-31
* @return java.lang.String
*/
public java.lang.String getCustsubname() {
return this.custsubname;
} 

/**
* 属性custsubname的Setter方法.属性名：客户子户名称
* 创建日期:2017-5-31
* @param newCustsubname java.lang.String
*/
public void setCustsubname ( java.lang.String custsubname) {
this.custsubname=custsubname;
} 
 
/**
* 属性 custcode的Getter方法.属性名：所属客户编码
*  创建日期:2017-5-31
* @return java.lang.String
*/
public java.lang.String getCustcode() {
return this.custcode;
} 

/**
* 属性custcode的Setter方法.属性名：所属客户编码
* 创建日期:2017-5-31
* @param newCustcode java.lang.String
*/
public void setCustcode ( java.lang.String custcode) {
this.custcode=custcode;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-5-31
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-5-31
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhCustomerSub");
    }
   }
    