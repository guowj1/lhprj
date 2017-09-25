package nc.vo.sxlhscm.lhdayproduct;

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
 *  创建日期:2017-7-7
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class DayProductHVO extends SuperVO {
	
/**
*主键
*/
public String pk_dayproduct_h;
/**
*集团
*/
public String pk_group;
/**
*库存组织
*/
public String pk_org;
/**
*库存组织版本
*/
public String pk_org_v;
/**
*领料仓库
*/
public String pk_stordoc;
/**
*产品仓库
*/
public String cpstordoc;
/**
*单据号
*/
public String vbillcode;
/**
*单据日期
*/
public UFDate dbilldate;
/**
*创建人
*/
public String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*修改人
*/
public String modifier;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*审批人
*/
public String approver;
/**
*审批时间
*/
public UFDateTime taudittime;
/**
*单据状态
*/
public Integer fbillflag;
/**
*领料分厂
*/
public String def1;
/**
*产品分厂
*/
public String def2;
/**
*自定义项3
*/
public String def3;
/**
*自定义项4
*/
public String def4;
/**
*自定义项5
*/
public String def5;
/**
*自定义项6
*/
public String def6;
/**
*自定义项7
*/
public String def7;
/**
*自定义项8
*/
public String def8;
/**
*自定义项9
*/
public String def9;
/**
*自定义项10
*/
public String def10;
/**
*自定义项11
*/
public String def11;
/**
*自定义项12
*/
public String def12;
/**
*自定义项13
*/
public String def13;
/**
*自定义项14
*/
public String def14;
/**
*自定义项15
*/
public String def15;
/**
*自定义项16
*/
public String def16;
/**
*自定义项17
*/
public String def17;
/**
*自定义项18
*/
public String def18;
/**
*自定义项19
*/
public String def19;
/**
*自定义项20
*/
public String def20;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_dayproduct_h的Getter方法.属性名：主键
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getPk_dayproduct_h() {
return this.pk_dayproduct_h;
} 

/**
* 属性pk_dayproduct_h的Setter方法.属性名：主键
* 创建日期:2017-7-7
* @param newPk_dayproduct_h java.lang.String
*/
public void setPk_dayproduct_h ( String pk_dayproduct_h) {
this.pk_dayproduct_h=pk_dayproduct_h;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-7-7
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-7-7
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：库存组织
*  创建日期:2017-7-7
* @return nc.vo.org.StockOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：库存组织
* 创建日期:2017-7-7
* @param newPk_org nc.vo.org.StockOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：库存组织版本
*  创建日期:2017-7-7
* @return nc.vo.vorg.StockOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：库存组织版本
* 创建日期:2017-7-7
* @param newPk_org_v nc.vo.vorg.StockOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 pk_stordoc的Getter方法.属性名：领料仓库
*  创建日期:2017-7-7
* @return nc.vo.bd.stordoc.StordocVO
*/
public String getPk_stordoc() {
return this.pk_stordoc;
} 

/**
* 属性pk_stordoc的Setter方法.属性名：领料仓库
* 创建日期:2017-7-7
* @param newPk_stordoc nc.vo.bd.stordoc.StordocVO
*/
public void setPk_stordoc ( String pk_stordoc) {
this.pk_stordoc=pk_stordoc;
} 
 
/**
* 属性 cpstordoc的Getter方法.属性名：产品仓库
*  创建日期:2017-7-7
* @return nc.vo.bd.stordoc.StordocVO
*/
public String getCpstordoc() {
return this.cpstordoc;
} 

/**
* 属性cpstordoc的Setter方法.属性名：产品仓库
* 创建日期:2017-7-7
* @param newCpstordoc nc.vo.bd.stordoc.StordocVO
*/
public void setCpstordoc ( String cpstordoc) {
this.cpstordoc=cpstordoc;
} 
 
/**
* 属性 vbillcode的Getter方法.属性名：单据号
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getVbillcode() {
return this.vbillcode;
} 

/**
* 属性vbillcode的Setter方法.属性名：单据号
* 创建日期:2017-7-7
* @param newVbillcode java.lang.String
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：单据日期
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：单据日期
* 创建日期:2017-7-7
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2017-7-7
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2017-7-7
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2017-7-7
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2017-7-7
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2017-7-7
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2017-7-7
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 approver的Getter方法.属性名：审批人
*  创建日期:2017-7-7
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return this.approver;
} 

/**
* 属性approver的Setter方法.属性名：审批人
* 创建日期:2017-7-7
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* 属性 taudittime的Getter方法.属性名：审批时间
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaudittime() {
return this.taudittime;
} 

/**
* 属性taudittime的Setter方法.属性名：审批时间
* 创建日期:2017-7-7
* @param newTaudittime nc.vo.pub.lang.UFDateTime
*/
public void setTaudittime ( UFDateTime taudittime) {
this.taudittime=taudittime;
} 
 
/**
* 属性 fbillflag的Getter方法.属性名：单据状态
*  创建日期:2017-7-7
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getFbillflag() {
return this.fbillflag;
} 

/**
* 属性fbillflag的Setter方法.属性名：单据状态
* 创建日期:2017-7-7
* @param newFbillflag nc.vo.pub.pf.BillStatusEnum
*/
public void setFbillflag ( Integer fbillflag) {
this.fbillflag=fbillflag;
} 
 
/**
* 属性 def1的Getter方法.属性名：领料分厂
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef1() {
return this.def1;
} 

/**
* 属性def1的Setter方法.属性名：领料分厂
* 创建日期:2017-7-7
* @param newDef1 java.lang.String
*/
public void setDef1 ( String def1) {
this.def1=def1;
} 
 
/**
* 属性 def2的Getter方法.属性名：产品分厂
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef2() {
return this.def2;
} 

/**
* 属性def2的Setter方法.属性名：产品分厂
* 创建日期:2017-7-7
* @param newDef2 java.lang.String
*/
public void setDef2 ( String def2) {
this.def2=def2;
} 
 
/**
* 属性 def3的Getter方法.属性名：自定义项3
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef3() {
return this.def3;
} 

/**
* 属性def3的Setter方法.属性名：自定义项3
* 创建日期:2017-7-7
* @param newDef3 java.lang.String
*/
public void setDef3 ( String def3) {
this.def3=def3;
} 
 
/**
* 属性 def4的Getter方法.属性名：自定义项4
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef4() {
return this.def4;
} 

/**
* 属性def4的Setter方法.属性名：自定义项4
* 创建日期:2017-7-7
* @param newDef4 java.lang.String
*/
public void setDef4 ( String def4) {
this.def4=def4;
} 
 
/**
* 属性 def5的Getter方法.属性名：自定义项5
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef5() {
return this.def5;
} 

/**
* 属性def5的Setter方法.属性名：自定义项5
* 创建日期:2017-7-7
* @param newDef5 java.lang.String
*/
public void setDef5 ( String def5) {
this.def5=def5;
} 
 
/**
* 属性 def6的Getter方法.属性名：自定义项6
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef6() {
return this.def6;
} 

/**
* 属性def6的Setter方法.属性名：自定义项6
* 创建日期:2017-7-7
* @param newDef6 java.lang.String
*/
public void setDef6 ( String def6) {
this.def6=def6;
} 
 
/**
* 属性 def7的Getter方法.属性名：自定义项7
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef7() {
return this.def7;
} 

/**
* 属性def7的Setter方法.属性名：自定义项7
* 创建日期:2017-7-7
* @param newDef7 java.lang.String
*/
public void setDef7 ( String def7) {
this.def7=def7;
} 
 
/**
* 属性 def8的Getter方法.属性名：自定义项8
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef8() {
return this.def8;
} 

/**
* 属性def8的Setter方法.属性名：自定义项8
* 创建日期:2017-7-7
* @param newDef8 java.lang.String
*/
public void setDef8 ( String def8) {
this.def8=def8;
} 
 
/**
* 属性 def9的Getter方法.属性名：自定义项9
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef9() {
return this.def9;
} 

/**
* 属性def9的Setter方法.属性名：自定义项9
* 创建日期:2017-7-7
* @param newDef9 java.lang.String
*/
public void setDef9 ( String def9) {
this.def9=def9;
} 
 
/**
* 属性 def10的Getter方法.属性名：自定义项10
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef10() {
return this.def10;
} 

/**
* 属性def10的Setter方法.属性名：自定义项10
* 创建日期:2017-7-7
* @param newDef10 java.lang.String
*/
public void setDef10 ( String def10) {
this.def10=def10;
} 
 
/**
* 属性 def11的Getter方法.属性名：自定义项11
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef11() {
return this.def11;
} 

/**
* 属性def11的Setter方法.属性名：自定义项11
* 创建日期:2017-7-7
* @param newDef11 java.lang.String
*/
public void setDef11 ( String def11) {
this.def11=def11;
} 
 
/**
* 属性 def12的Getter方法.属性名：自定义项12
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef12() {
return this.def12;
} 

/**
* 属性def12的Setter方法.属性名：自定义项12
* 创建日期:2017-7-7
* @param newDef12 java.lang.String
*/
public void setDef12 ( String def12) {
this.def12=def12;
} 
 
/**
* 属性 def13的Getter方法.属性名：自定义项13
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef13() {
return this.def13;
} 

/**
* 属性def13的Setter方法.属性名：自定义项13
* 创建日期:2017-7-7
* @param newDef13 java.lang.String
*/
public void setDef13 ( String def13) {
this.def13=def13;
} 
 
/**
* 属性 def14的Getter方法.属性名：自定义项14
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef14() {
return this.def14;
} 

/**
* 属性def14的Setter方法.属性名：自定义项14
* 创建日期:2017-7-7
* @param newDef14 java.lang.String
*/
public void setDef14 ( String def14) {
this.def14=def14;
} 
 
/**
* 属性 def15的Getter方法.属性名：自定义项15
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef15() {
return this.def15;
} 

/**
* 属性def15的Setter方法.属性名：自定义项15
* 创建日期:2017-7-7
* @param newDef15 java.lang.String
*/
public void setDef15 ( String def15) {
this.def15=def15;
} 
 
/**
* 属性 def16的Getter方法.属性名：自定义项16
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef16() {
return this.def16;
} 

/**
* 属性def16的Setter方法.属性名：自定义项16
* 创建日期:2017-7-7
* @param newDef16 java.lang.String
*/
public void setDef16 ( String def16) {
this.def16=def16;
} 
 
/**
* 属性 def17的Getter方法.属性名：自定义项17
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef17() {
return this.def17;
} 

/**
* 属性def17的Setter方法.属性名：自定义项17
* 创建日期:2017-7-7
* @param newDef17 java.lang.String
*/
public void setDef17 ( String def17) {
this.def17=def17;
} 
 
/**
* 属性 def18的Getter方法.属性名：自定义项18
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef18() {
return this.def18;
} 

/**
* 属性def18的Setter方法.属性名：自定义项18
* 创建日期:2017-7-7
* @param newDef18 java.lang.String
*/
public void setDef18 ( String def18) {
this.def18=def18;
} 
 
/**
* 属性 def19的Getter方法.属性名：自定义项19
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef19() {
return this.def19;
} 

/**
* 属性def19的Setter方法.属性名：自定义项19
* 创建日期:2017-7-7
* @param newDef19 java.lang.String
*/
public void setDef19 ( String def19) {
this.def19=def19;
} 
 
/**
* 属性 def20的Getter方法.属性名：自定义项20
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef20() {
return this.def20;
} 

/**
* 属性def20的Setter方法.属性名：自定义项20
* 创建日期:2017-7-7
* @param newDef20 java.lang.String
*/
public void setDef20 ( String def20) {
this.def20=def20;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-7-7
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("lhprj.hdayproduct");
    }
   }
    