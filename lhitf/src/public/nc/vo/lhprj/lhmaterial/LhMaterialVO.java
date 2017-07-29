package nc.vo.lhprj.lhmaterial;

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
 * @author 
 * @version NCPrj ??
 */
 
public class LhMaterialVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_material;
/**
*外系统主键
*/
public java.lang.String pk_id;
/**
*产品编码
*/
public java.lang.String matcode;
/**
*产品名称
*/
public java.lang.String matname;
/**
*计量单位名称
*/
public java.lang.String measname;
/**
*所属分类编码
*/
public java.lang.String matclscode;
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
* 属性 pk_material的Getter方法.属性名：主键
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_material() {
return this.pk_material;
} 

/**
* 属性pk_material的Setter方法.属性名：主键
* 创建日期:2017-3-12
* @param newPk_material java.lang.String
*/
public void setPk_material ( java.lang.String pk_material) {
this.pk_material=pk_material;
} 
 
/**
* 属性 pk_id的Getter方法.属性名：外系统主键
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getPk_id() {
return this.pk_id;
} 

/**
* 属性pk_id的Setter方法.属性名：外系统主键
* 创建日期:2017-3-12
* @param newPk_id java.lang.String
*/
public void setPk_id ( java.lang.String pk_id) {
this.pk_id=pk_id;
} 
 
/**
* 属性 matcode的Getter方法.属性名：产品编码
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatcode() {
return this.matcode;
} 

/**
* 属性matcode的Setter方法.属性名：产品编码
* 创建日期:2017-3-12
* @param newMatcode java.lang.String
*/
public void setMatcode ( java.lang.String matcode) {
this.matcode=matcode;
} 
 
/**
* 属性 matname的Getter方法.属性名：产品名称
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatname() {
return this.matname;
} 

/**
* 属性matname的Setter方法.属性名：产品名称
* 创建日期:2017-3-12
* @param newMatname java.lang.String
*/
public void setMatname ( java.lang.String matname) {
this.matname=matname;
} 
 
/**
* 属性 measname的Getter方法.属性名：计量单位名称
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMeasname() {
return this.measname;
} 

/**
* 属性measname的Setter方法.属性名：计量单位名称
* 创建日期:2017-3-12
* @param newMeasname java.lang.String
*/
public void setMeasname ( java.lang.String measname) {
this.measname=measname;
} 
 
/**
* 属性 matclscode的Getter方法.属性名：所属分类编码
*  创建日期:2017-3-12
* @return java.lang.String
*/
public java.lang.String getMatclscode() {
return this.matclscode;
} 

/**
* 属性matclscode的Setter方法.属性名：所属分类编码
* 创建日期:2017-3-12
* @param newMatclscode java.lang.String
*/
public void setMatclscode ( java.lang.String matclscode) {
this.matclscode=matclscode;
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
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-3-12
* @return nc.vo.org.CorpVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-3-12
* @param newPk_org nc.vo.org.CorpVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.LhMaterial");
    }
   }
    