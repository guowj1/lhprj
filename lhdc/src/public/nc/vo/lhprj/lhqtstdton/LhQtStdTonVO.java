package nc.vo.lhprj.lhqtstdton;

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
 *  创建日期:2017-6-16
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class LhQtStdTonVO extends SuperVO {
	
/**
*表头主键
*/
public String pk_id;
/**
*单据号
*/
public String vbillno;
/**
*生效日期
*/
public UFDate dbilldate;
/**
*单据类型
*/
public String pk_billtype;
/**
*交易类型
*/
public String ctranstypeid;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*集团
*/
public String pk_group;
/**
*创建人
*/
public String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*单据状态
*/
public Integer fbillstatus;
/**
*制单人
*/
public String billmaker;
/**
*制单日期
*/
public UFDate dmakedate;
/**
*审批人
*/
public String approver;
/**
*审批时间
*/
public UFDateTime taudittime;
/**
*最后修改人
*/
public String modifier;
/**
*最后修改时间
*/
public UFDateTime modifiedtime;
/**
*审批批语
*/
public String approvenote;
/**
*备注
*/
public String vnote;
/**
*自定义项1
*/
public String vdef1;
/**
*自定义项2
*/
public String vdef2;
/**
*自定义项3
*/
public String vdef3;
/**
*自定义项4
*/
public String vdef4;
/**
*自定义项5
*/
public String vdef5;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_id的Getter方法.属性名：表头主键
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：表头主键
* 创建日期:2017-6-16
* @param newPk_id java.lang.String
*/
public void setPk_id ( String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 vbillno的Getter方法.属性名：单据号
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getVbillno() {
return this.vbillno;
} 

/**
* 属性vbillno的Setter方法.属性名：单据号
* 创建日期:2017-6-16
* @param newVbillno java.lang.String
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：生效日期
*  创建日期:2017-6-16
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：生效日期
* 创建日期:2017-6-16
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 pk_billtype的Getter方法.属性名：单据类型
*  创建日期:2017-6-16
* @return nc.vo.pub.billtype.BilltypeVO
*/
public String getPk_billtype() {
return this.pk_billtype;
} 

/**
* 属性pk_billtype的Setter方法.属性名：单据类型
* 创建日期:2017-6-16
* @param newPk_billtype nc.vo.pub.billtype.BilltypeVO
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
} 
 
/**
* 属性 ctranstypeid的Getter方法.属性名：交易类型
*  创建日期:2017-6-16
* @return nc.vo.pub.billtype.BilltypeVO
*/
public String getCtranstypeid() {
return this.ctranstypeid;
} 

/**
* 属性ctranstypeid的Setter方法.属性名：交易类型
* 创建日期:2017-6-16
* @param newCtranstypeid nc.vo.pub.billtype.BilltypeVO
*/
public void setCtranstypeid ( String ctranstypeid) {
this.ctranstypeid=ctranstypeid;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-6-16
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-6-16
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织版本
*  创建日期:2017-6-16
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织版本
* 创建日期:2017-6-16
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-6-16
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-6-16
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2017-6-16
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2017-6-16
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2017-6-16
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2017-6-16
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 fbillstatus的Getter方法.属性名：单据状态
*  创建日期:2017-6-16
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getFbillstatus() {
return this.fbillstatus;
} 

/**
* 属性fbillstatus的Setter方法.属性名：单据状态
* 创建日期:2017-6-16
* @param newFbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setFbillstatus ( Integer fbillstatus) {
this.fbillstatus=fbillstatus;
} 
 
/**
* 属性 billmaker的Getter方法.属性名：制单人
*  创建日期:2017-6-16
* @return nc.vo.sm.UserVO
*/
public String getBillmaker() {
return this.billmaker;
} 

/**
* 属性billmaker的Setter方法.属性名：制单人
* 创建日期:2017-6-16
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( String billmaker) {
this.billmaker=billmaker;
} 
 
/**
* 属性 dmakedate的Getter方法.属性名：制单日期
*  创建日期:2017-6-16
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDmakedate() {
return this.dmakedate;
} 

/**
* 属性dmakedate的Setter方法.属性名：制单日期
* 创建日期:2017-6-16
* @param newDmakedate nc.vo.pub.lang.UFDate
*/
public void setDmakedate ( UFDate dmakedate) {
this.dmakedate=dmakedate;
} 
 
/**
* 属性 approver的Getter方法.属性名：审批人
*  创建日期:2017-6-16
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return this.approver;
} 

/**
* 属性approver的Setter方法.属性名：审批人
* 创建日期:2017-6-16
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* 属性 taudittime的Getter方法.属性名：审批时间
*  创建日期:2017-6-16
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaudittime() {
return this.taudittime;
} 

/**
* 属性taudittime的Setter方法.属性名：审批时间
* 创建日期:2017-6-16
* @param newTaudittime nc.vo.pub.lang.UFDateTime
*/
public void setTaudittime ( UFDateTime taudittime) {
this.taudittime=taudittime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：最后修改人
*  创建日期:2017-6-16
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：最后修改人
* 创建日期:2017-6-16
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：最后修改时间
*  创建日期:2017-6-16
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：最后修改时间
* 创建日期:2017-6-16
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 approvenote的Getter方法.属性名：审批批语
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getApprovenote() {
return this.approvenote;
} 

/**
* 属性approvenote的Setter方法.属性名：审批批语
* 创建日期:2017-6-16
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* 属性 vnote的Getter方法.属性名：备注
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getVnote() {
return this.vnote;
} 

/**
* 属性vnote的Setter方法.属性名：备注
* 创建日期:2017-6-16
* @param newVnote java.lang.String
*/
public void setVnote ( String vnote) {
this.vnote=vnote;
} 
 
/**
* 属性 vdef1的Getter方法.属性名：自定义项1
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getVdef1() {
return this.vdef1;
} 

/**
* 属性vdef1的Setter方法.属性名：自定义项1
* 创建日期:2017-6-16
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
} 
 
/**
* 属性 vdef2的Getter方法.属性名：自定义项2
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getVdef2() {
return this.vdef2;
} 

/**
* 属性vdef2的Setter方法.属性名：自定义项2
* 创建日期:2017-6-16
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
} 
 
/**
* 属性 vdef3的Getter方法.属性名：自定义项3
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getVdef3() {
return this.vdef3;
} 

/**
* 属性vdef3的Setter方法.属性名：自定义项3
* 创建日期:2017-6-16
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
} 
 
/**
* 属性 vdef4的Getter方法.属性名：自定义项4
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getVdef4() {
return this.vdef4;
} 

/**
* 属性vdef4的Setter方法.属性名：自定义项4
* 创建日期:2017-6-16
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
} 
 
/**
* 属性 vdef5的Getter方法.属性名：自定义项5
*  创建日期:2017-6-16
* @return java.lang.String
*/
public String getVdef5() {
return this.vdef5;
} 

/**
* 属性vdef5的Setter方法.属性名：自定义项5
* 创建日期:2017-6-16
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-6-16
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-6-16
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhQtStdTon");
    }
   }
    