package nc.vo.lhprj.lhcustomer;

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
 *  创建日期:2017-6-2
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhCustomerVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_customer;
/**
*外系统主键
*/
public java.lang.String pk_id;
/**
*客户代码
*/
public java.lang.String custcode;
/**
*客户名称
*/
public java.lang.String custname;
/**
*客户分类编码
*/
public java.lang.String custclasscode;
/**
*客户属性
*/
public java.lang.String custproperty;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_customer的Getter方法.属性名：主键
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getPk_customer() {
return this.pk_customer;
} 

/**
* 属性pk_customer的Setter方法.属性名：主键
* 创建日期:2017-6-2
* @param newPk_customer java.lang.String
*/
public void setPk_customer ( java.lang.String pk_customer) {
this.pk_customer=pk_customer;
} 
 
/**
* 属性 pk_id的Getter方法.属性名：外系统主键
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：外系统主键
* 创建日期:2017-6-2
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 custcode的Getter方法.属性名：客户代码
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustcode() {
return this.custcode;
} 

/**
* 属性custcode的Setter方法.属性名：客户代码
* 创建日期:2017-6-2
* @param newCustcode java.lang.String
*/
public void setCustcode ( java.lang.String custcode) {
this.custcode=custcode;
} 
 
/**
* 属性 custname的Getter方法.属性名：客户名称
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustname() {
return this.custname;
} 

/**
* 属性custname的Setter方法.属性名：客户名称
* 创建日期:2017-6-2
* @param newCustname java.lang.String
*/
public void setCustname ( java.lang.String custname) {
this.custname=custname;
} 
 
/**
* 属性 custclasscode的Getter方法.属性名：客户分类编码
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustclasscode() {
return this.custclasscode;
} 

/**
* 属性custclasscode的Setter方法.属性名：客户分类编码
* 创建日期:2017-6-2
* @param newCustclasscode java.lang.String
*/
public void setCustclasscode ( java.lang.String custclasscode) {
this.custclasscode=custclasscode;
} 
 
/**
* 属性 custproperty的Getter方法.属性名：客户属性
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustproperty() {
return this.custproperty;
} 

/**
* 属性custproperty的Setter方法.属性名：客户属性
* 创建日期:2017-6-2
* @param newCustproperty java.lang.String
*/
public void setCustproperty ( java.lang.String custproperty) {
this.custproperty=custproperty;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-6-2
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-6-2
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhCustomer");
    }
   }
    