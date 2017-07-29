package nc.vo.lhprj.lhpurcasein;

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
 *  创建日期:2017-6-14
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhPurchaseInVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_purchasein;
/**
*外系统单据主键
*/
public java.lang.String pk_id;
/**
*对应NC采购订单主键
*/
public java.lang.String pk_saleorder;
/**
*对应NC采购订单号
*/
public java.lang.String sobillno;
/**
*结算依据
*/
public java.lang.String settleclue;
/**
*仓库编码
*/
public java.lang.String whcode;
/**
*结算日期
*/
public UFDate ddate;
/**
*是否预结算单据
*/
public java.lang.String bpresettle;
/**
*制单人编码
*/
public java.lang.String userid;
/**
*组织
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
* 属性 pk_purchasein的Getter方法.属性名：主键
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getPk_purchasein() {
return this.pk_purchasein;
} 

/**
* 属性pk_purchasein的Setter方法.属性名：主键
* 创建日期:2017-6-14
* @param newPk_purchasein java.lang.String
*/
public void setPk_purchasein ( java.lang.String pk_purchasein) {
this.pk_purchasein=pk_purchasein;
} 
 
/**
* 属性 pk_id的Getter方法.属性名：外系统单据主键
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：外系统单据主键
* 创建日期:2017-6-14
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 pk_saleorder的Getter方法.属性名：对应NC采购订单主键
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getPk_saleorder() {
return this.pk_saleorder;
} 

/**
* 属性pk_saleorder的Setter方法.属性名：对应NC采购订单主键
* 创建日期:2017-6-14
* @param newPk_saleorder java.lang.String
*/
public void setPk_saleorder ( java.lang.String pk_saleorder) {
this.pk_saleorder=pk_saleorder;
} 
 
/**
* 属性 sobillno的Getter方法.属性名：对应NC采购订单号
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getSobillno() {
return this.sobillno;
} 

/**
* 属性sobillno的Setter方法.属性名：对应NC采购订单号
* 创建日期:2017-6-14
* @param newSobillno java.lang.String
*/
public void setSobillno ( java.lang.String sobillno) {
this.sobillno=sobillno;
} 
 
/**
* 属性 settleclue的Getter方法.属性名：结算依据
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getSettleclue() {
return this.settleclue;
} 

/**
* 属性settleclue的Setter方法.属性名：结算依据
* 创建日期:2017-6-14
* @param newSettleclue java.lang.String
*/
public void setSettleclue ( java.lang.String settleclue) {
this.settleclue=settleclue;
} 
 
/**
* 属性 whcode的Getter方法.属性名：仓库编码
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getWhcode() {
return this.whcode;
} 

/**
* 属性whcode的Setter方法.属性名：仓库编码
* 创建日期:2017-6-14
* @param newWhcode java.lang.String
*/
public void setWhcode ( java.lang.String whcode) {
this.whcode=whcode;
} 
 
/**
* 属性 ddate的Getter方法.属性名：结算日期
*  创建日期:2017-6-14
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDdate() {
return this.ddate;
} 

/**
* 属性ddate的Setter方法.属性名：结算日期
* 创建日期:2017-6-14
* @param newDdate nc.vo.pub.lang.UFDate
*/
public void setDdate ( UFDate ddate) {
this.ddate=ddate;
} 
 
/**
* 属性 bpresettle的Getter方法.属性名：是否预结算单据
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getBpresettle() {
return this.bpresettle;
} 

/**
* 属性bpresettle的Setter方法.属性名：是否预结算单据
* 创建日期:2017-6-14
* @param newBpresettle java.lang.String
*/
public void setBpresettle ( java.lang.String bpresettle) {
this.bpresettle=bpresettle;
} 
 
/**
* 属性 userid的Getter方法.属性名：制单人编码
*  创建日期:2017-6-14
* @return java.lang.String
*/
public java.lang.String getUserid() {
return this.userid;
} 

/**
* 属性userid的Setter方法.属性名：制单人编码
* 创建日期:2017-6-14
* @param newUserid java.lang.String
*/
public void setUserid ( java.lang.String userid) {
this.userid=userid;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-6-14
* @return nc.vo.org.OrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-6-14
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-6-14
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-6-14
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-6-14
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-6-14
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhPurchaseIn");
    }
   }
    