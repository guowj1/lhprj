package nc.vo.lhprj.lhrecbill;

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
 * @author 
 * @version NCPrj ??
 */
 
public class LhRecBillVO extends SuperVO {
	
/**
*源系统主键
*/
public java.lang.String pkid;
/**
*财务组织编码
*/
public java.lang.String orgcode;
/**
*客户编码
*/
public java.lang.String custcode;
/**
*客户子户编码
*/
public java.lang.String custsubcode;
/**
*收款业务类型
*/
public java.lang.String busitype;
/**
*业务属性
*/
public java.lang.String busiproperty;
/**
*收款类型编码
*/
public java.lang.String subjcode;
/**
*结算方式编码
*/
public java.lang.String balatype;
/**
*收款银行帐号
*/
public java.lang.String recaccount;
/**
*银行交易线索号
*/
public java.lang.String clueno;
/**
*收款金额
*/
public nc.vo.pub.lang.UFDouble nmoney;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pkid的Getter方法.属性名：源系统主键
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getPkid() {
return this.pkid;
} 

/**
* 属性pkid的Setter方法.属性名：源系统主键
* 创建日期:2017-6-2
* @param newPkid java.lang.String
*/
public void setPkid ( java.lang.String pkid) {
this.pkid=pkid;
} 
 
/**
* 属性 orgcode的Getter方法.属性名：财务组织编码
*  创建日期:2017-6-2
* @return nc.vo.org.FinanceOrgVO
*/
public java.lang.String getOrgcode() {
return this.orgcode;
} 

/**
* 属性orgcode的Setter方法.属性名：财务组织编码
* 创建日期:2017-6-2
* @param newOrgcode nc.vo.org.FinanceOrgVO
*/
public void setOrgcode ( java.lang.String orgcode) {
this.orgcode=orgcode;
} 
 
/**
* 属性 custcode的Getter方法.属性名：客户编码
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustcode() {
return this.custcode;
} 

/**
* 属性custcode的Setter方法.属性名：客户编码
* 创建日期:2017-6-2
* @param newCustcode java.lang.String
*/
public void setCustcode ( java.lang.String custcode) {
this.custcode=custcode;
} 
 
/**
* 属性 custsubcode的Getter方法.属性名：客户子户编码
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getCustsubcode() {
return this.custsubcode;
} 

/**
* 属性custsubcode的Setter方法.属性名：客户子户编码
* 创建日期:2017-6-2
* @param newCustsubcode java.lang.String
*/
public void setCustsubcode ( java.lang.String custsubcode) {
this.custsubcode=custsubcode;
} 
 
/**
* 属性 busitype的Getter方法.属性名：收款业务类型
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getBusitype() {
return this.busitype;
} 

/**
* 属性busitype的Setter方法.属性名：收款业务类型
* 创建日期:2017-6-2
* @param newBusitype java.lang.String
*/
public void setBusitype ( java.lang.String busitype) {
this.busitype=busitype;
} 
 
/**
* 属性 busiproperty的Getter方法.属性名：业务属性
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getBusiproperty() {
return this.busiproperty;
} 

/**
* 属性busiproperty的Setter方法.属性名：业务属性
* 创建日期:2017-6-2
* @param newBusiproperty java.lang.String
*/
public void setBusiproperty ( java.lang.String busiproperty) {
this.busiproperty=busiproperty;
} 
 
/**
* 属性 subjcode的Getter方法.属性名：收款类型编码
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getSubjcode() {
return this.subjcode;
} 

/**
* 属性subjcode的Setter方法.属性名：收款类型编码
* 创建日期:2017-6-2
* @param newSubjcode java.lang.String
*/
public void setSubjcode ( java.lang.String subjcode) {
this.subjcode=subjcode;
} 
 
/**
* 属性 balatype的Getter方法.属性名：结算方式编码
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getBalatype() {
return this.balatype;
} 

/**
* 属性balatype的Setter方法.属性名：结算方式编码
* 创建日期:2017-6-2
* @param newBalatype java.lang.String
*/
public void setBalatype ( java.lang.String balatype) {
this.balatype=balatype;
} 
 
/**
* 属性 recaccount的Getter方法.属性名：收款银行帐号
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getRecaccount() {
return this.recaccount;
} 

/**
* 属性recaccount的Setter方法.属性名：收款银行帐号
* 创建日期:2017-6-2
* @param newRecaccount java.lang.String
*/
public void setRecaccount ( java.lang.String recaccount) {
this.recaccount=recaccount;
} 
 
/**
* 属性 clueno的Getter方法.属性名：银行交易线索号
*  创建日期:2017-6-2
* @return java.lang.String
*/
public java.lang.String getClueno() {
return this.clueno;
} 

/**
* 属性clueno的Setter方法.属性名：银行交易线索号
* 创建日期:2017-6-2
* @param newClueno java.lang.String
*/
public void setClueno ( java.lang.String clueno) {
this.clueno=clueno;
} 
 
/**
* 属性 nmoney的Getter方法.属性名：收款金额
*  创建日期:2017-6-2
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getNmoney() {
return this.nmoney;
} 

/**
* 属性nmoney的Setter方法.属性名：收款金额
* 创建日期:2017-6-2
* @param newNmoney nc.vo.pub.lang.UFDouble
*/
public void setNmoney ( nc.vo.pub.lang.UFDouble nmoney) {
this.nmoney=nmoney;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhRecBill");
    }
   }
    