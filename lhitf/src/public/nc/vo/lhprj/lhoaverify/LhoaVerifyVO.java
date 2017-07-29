package nc.vo.lhprj.lhoaverify;

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
 *  创建日期:2017-1-18
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhoaVerifyVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_id;
/**
*流程ID
*/
public java.lang.String pk_bill;
/**
*审批状态
*/
public java.lang.String approvestatus;
/**
*审批批语
*/
public java.lang.String approvenote;
/**
*集团
*/
public java.lang.String pk_group;
/**
*组织
*/
public java.lang.String pk_org;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_id的Getter方法.属性名：主键
*  创建日期:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：主键
* 创建日期:2017-1-18
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 pk_bill的Getter方法.属性名：流程ID
*  创建日期:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_bill() {
return this.pk_bill;
} 

/**
* 属性pk_bill的Setter方法.属性名：流程ID
* 创建日期:2017-1-18
* @param newPk_bill java.lang.String
*/
public void setPk_bill ( java.lang.String pk_bill) {
this.pk_bill=pk_bill;
} 
 
/**
* 属性 approvestatus的Getter方法.属性名：审批状态
*  创建日期:2017-1-18
* @return java.lang.String
*/
public java.lang.String getApprovestatus() {
return this.approvestatus;
} 

/**
* 属性approvestatus的Setter方法.属性名：审批状态
* 创建日期:2017-1-18
* @param newApprovestatus java.lang.String
*/
public void setApprovestatus ( java.lang.String approvestatus) {
this.approvestatus=approvestatus;
} 
 
/**
* 属性 approvenote的Getter方法.属性名：审批批语
*  创建日期:2017-1-18
* @return java.lang.String
*/
public java.lang.String getApprovenote() {
return this.approvenote;
} 

/**
* 属性approvenote的Setter方法.属性名：审批批语
* 创建日期:2017-1-18
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( java.lang.String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-1-18
* @param newPk_group java.lang.String
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-1-18
* @return java.lang.String
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-1-18
* @param newPk_org java.lang.String
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-1-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-1-18
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhoaVerify");
    }
   }
    