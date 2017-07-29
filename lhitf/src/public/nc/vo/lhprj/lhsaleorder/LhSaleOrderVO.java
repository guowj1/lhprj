package nc.vo.lhprj.lhsaleorder;

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
 *  创建日期:2017-3-17
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhSaleOrderVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_saleorder;
/**
*外系统主键
*/
public java.lang.String pk_id;
/**
*销售组织编码
*/
public java.lang.String pk_saleorg;
/**
*客户编码
*/
public java.lang.String custcode;
/**
*结算路径
*/
public java.lang.String settleroute;
/**
*合同类型名称
*/
public java.lang.String cttype;
/**
*合同号
*/
public java.lang.String ctcode;
/**
*合同日期
*/
public UFDate ctdate;

public String cbusiproperty;
/**
*集团
*/
public java.lang.String pk_group;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_saleorder的Getter方法.属性名：主键
*  创建日期:2017-3-17
* @return java.lang.String
*/
public java.lang.String getPk_saleorder() {
return this.pk_saleorder;
} 

/**
* 属性pk_saleorder的Setter方法.属性名：主键
* 创建日期:2017-3-17
* @param newPk_saleorder java.lang.String
*/
public void setPk_saleorder ( java.lang.String pk_saleorder) {
this.pk_saleorder=pk_saleorder;
} 
 
/**
* 属性 pk_id的Getter方法.属性名：外系统主键
*  创建日期:2017-3-17
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：外系统主键
* 创建日期:2017-3-17
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 pk_saleorg的Getter方法.属性名：销售组织编码
*  创建日期:2017-3-17
* @return java.lang.String
*/
public java.lang.String getPk_saleorg() {
return this.pk_saleorg;
} 

/**
* 属性pk_saleorg的Setter方法.属性名：销售组织编码
* 创建日期:2017-3-17
* @param newPk_saleorg java.lang.String
*/
public void setPk_saleorg ( java.lang.String pk_saleorg) {
this.pk_saleorg=pk_saleorg;
} 
 
/**
* 属性 custcode的Getter方法.属性名：客户编码
*  创建日期:2017-3-17
* @return java.lang.String
*/
public java.lang.String getCustcode() {
return this.custcode;
} 

/**
* 属性custcode的Setter方法.属性名：客户编码
* 创建日期:2017-3-17
* @param newCustcode java.lang.String
*/
public void setCustcode ( java.lang.String custcode) {
this.custcode=custcode;
} 
 
/**
* 属性 settleroute的Getter方法.属性名：结算路径
*  创建日期:2017-3-17
* @return java.lang.String
*/
public java.lang.String getSettleroute() {
return this.settleroute;
} 

/**
* 属性settleroute的Setter方法.属性名：结算路径
* 创建日期:2017-3-17
* @param newSettleroute java.lang.String
*/
public void setSettleroute ( java.lang.String settleroute) {
this.settleroute=settleroute;
} 
 
/**
* 属性 cttype的Getter方法.属性名：合同类型名称
*  创建日期:2017-3-17
* @return java.lang.String
*/
public java.lang.String getCttype() {
return this.cttype;
} 

/**
* 属性cttype的Setter方法.属性名：合同类型名称
* 创建日期:2017-3-17
* @param newCttype java.lang.String
*/
public void setCttype ( java.lang.String cttype) {
this.cttype=cttype;
} 
 
/**
* 属性 ctcode的Getter方法.属性名：合同号
*  创建日期:2017-3-17
* @return java.lang.String
*/
public java.lang.String getCtcode() {
return this.ctcode;
} 

/**
* 属性ctcode的Setter方法.属性名：合同号
* 创建日期:2017-3-17
* @param newCtcode java.lang.String
*/
public void setCtcode ( java.lang.String ctcode) {
this.ctcode=ctcode;
} 
 
/**
* 属性 ctdate的Getter方法.属性名：合同日期
*  创建日期:2017-3-17
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getCtdate() {
return this.ctdate;
} 

/**
* 属性ctdate的Setter方法.属性名：合同日期
* 创建日期:2017-3-17
* @param newCtdate nc.vo.pub.lang.UFDate
*/
public void setCtdate ( UFDate ctdate) {
this.ctdate=ctdate;
} 


 
public String getCbusiproperty() {
	return cbusiproperty;
}

public void setCbusiproperty(String cbusiproperty) {
	this.cbusiproperty = cbusiproperty;
}

/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-3-17
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-3-17
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-3-17
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-3-17
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhSaleOrder");
    }
   }
    