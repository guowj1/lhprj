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
 
public class LhTransBillVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_transbill;
/**
*外系统单据主键
*/
public java.lang.String pk_id;
/**
*过磅类型
*/
public java.lang.String transtype;
/**
*调出组织编码
*/
public java.lang.String outorgcode;
/**
*转出仓库主键
*/
public java.lang.String pk_warehouse_out;
/**
*转入仓库主键
*/
public java.lang.String pk_warehouse_in;
/**
*调入组织编码
*/
public java.lang.String inorgcode;
/**
*单据日期
*/
public UFDate ddate;
/**
*业务单元
*/
public java.lang.String pk_org;
/**
*集团
*/
public java.lang.String pk_group;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_transbill的Getter方法.属性名：主键
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_transbill() {
return this.pk_transbill;
} 

/**
* 属性pk_transbill的Setter方法.属性名：主键
* 创建日期:2017-3-12
* @param newPk_transbill java.lang.String
*/
public void setPk_transbill ( java.lang.String pk_transbill) {
this.pk_transbill=pk_transbill;
} 
 
/**
* 属性 pk_id的Getter方法.属性名：外系统单据主键
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：外系统单据主键
* 创建日期:2017-3-12
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 transtype的Getter方法.属性名：过磅类型
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getTranstype() {
return this.transtype;
} 

/**
* 属性transtype的Setter方法.属性名：过磅类型
* 创建日期:2017-3-12
* @param newTranstype java.lang.String
*/
public void setTranstype ( java.lang.String transtype) {
this.transtype=transtype;
} 
 
/**
* 属性 outorgcode的Getter方法.属性名：调出组织编码
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getOutorgcode() {
return this.outorgcode;
} 

/**
* 属性outorgcode的Setter方法.属性名：调出组织编码
* 创建日期:2017-3-12
* @param newOutorgcode java.lang.String
*/
public void setOutorgcode ( java.lang.String outorgcode) {
this.outorgcode=outorgcode;
} 
 
/**
* 属性 pk_warehouse_out的Getter方法.属性名：转出仓库主键
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_warehouse_out() {
return this.pk_warehouse_out;
} 

/**
* 属性pk_warehouse_out的Setter方法.属性名：转出仓库主键
* 创建日期:2017-3-12
* @param newPk_warehouse_out java.lang.String
*/
public void setPk_warehouse_out ( java.lang.String pk_warehouse_out) {
this.pk_warehouse_out=pk_warehouse_out;
} 
 
/**
* 属性 pk_warehouse_in的Getter方法.属性名：转入仓库主键
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_warehouse_in() {
return this.pk_warehouse_in;
} 

/**
* 属性pk_warehouse_in的Setter方法.属性名：转入仓库主键
* 创建日期:2017-3-12
* @param newPk_warehouse_in java.lang.String
*/
public void setPk_warehouse_in ( java.lang.String pk_warehouse_in) {
this.pk_warehouse_in=pk_warehouse_in;
} 
 
/**
* 属性 inorgcode的Getter方法.属性名：调入组织编码
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getInorgcode() {
return this.inorgcode;
} 

/**
* 属性inorgcode的Setter方法.属性名：调入组织编码
* 创建日期:2017-3-12
* @param newInorgcode java.lang.String
*/
public void setInorgcode ( java.lang.String inorgcode) {
this.inorgcode=inorgcode;
} 
 
/**
* 属性 ddate的Getter方法.属性名：单据日期
*  创建日期:2017-3-12
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDdate() {
return this.ddate;
} 

/**
* 属性ddate的Setter方法.属性名：单据日期
* 创建日期:2017-3-12
* @param newDdate nc.vo.pub.lang.UFDate
*/
public void setDdate ( UFDate ddate) {
this.ddate=ddate;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：业务单元
*  创建日期:2017-3-12
* @return nc.vo.org.OrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：业务单元
* 创建日期:2017-3-12
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-3-12
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-3-12
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhTransBill");
    }
   }
    