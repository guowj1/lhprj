package nc.vo.sxlhscm.lhingredient;

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
 
public class IngredientBVO extends SuperVO {
	
/**
*主键
*/
public String pk_ingredient_b;
/**
*供应商
*/
public String pk_supplier;
/**
*物料
*/
public String pk_material;
/**
*过磅量
*/
public UFDouble innum;
/**
*水份
*/
public UFDouble watercontent;
/**
*干基
*/
public UFDouble drybase;
/**
*货物单价
*/
public UFDouble nprice;
/**
*运费单价
*/
public UFDouble trainprice;
/**
*入库单价
*/
public UFDouble inprice;
/**
*入库金额
*/
public UFDouble inmny;
/**
*自定义项1
*/
public String def1;
/**
*自定义项2
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
*上层单据主键
*/
public String pk_ingredient_h;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_ingredient_b的Getter方法.属性名：主键
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getPk_ingredient_b() {
return this.pk_ingredient_b;
} 

/**
* 属性pk_ingredient_b的Setter方法.属性名：主键
* 创建日期:2017-7-7
* @param newPk_ingredient_b java.lang.String
*/
public void setPk_ingredient_b ( String pk_ingredient_b) {
this.pk_ingredient_b=pk_ingredient_b;
} 
 
/**
* 属性 pk_supplier的Getter方法.属性名：供应商
*  创建日期:2017-7-7
* @return nc.vo.bd.supplier.SupplierVO
*/
public String getPk_supplier() {
return this.pk_supplier;
} 

/**
* 属性pk_supplier的Setter方法.属性名：供应商
* 创建日期:2017-7-7
* @param newPk_supplier nc.vo.bd.supplier.SupplierVO
*/
public void setPk_supplier ( String pk_supplier) {
this.pk_supplier=pk_supplier;
} 
 
/**
* 属性 pk_material的Getter方法.属性名：物料
*  创建日期:2017-7-7
* @return nc.vo.bd.material.MaterialVO
*/
public String getPk_material() {
return this.pk_material;
} 

/**
* 属性pk_material的Setter方法.属性名：物料
* 创建日期:2017-7-7
* @param newPk_material nc.vo.bd.material.MaterialVO
*/
public void setPk_material ( String pk_material) {
this.pk_material=pk_material;
} 
 
/**
* 属性 innum的Getter方法.属性名：过磅量
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInnum() {
return this.innum;
} 

/**
* 属性innum的Setter方法.属性名：过磅量
* 创建日期:2017-7-7
* @param newInnum nc.vo.pub.lang.UFDouble
*/
public void setInnum ( UFDouble innum) {
this.innum=innum;
} 
 
/**
* 属性 watercontent的Getter方法.属性名：水份
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getWatercontent() {
return this.watercontent;
} 

/**
* 属性watercontent的Setter方法.属性名：水份
* 创建日期:2017-7-7
* @param newWatercontent nc.vo.pub.lang.UFDouble
*/
public void setWatercontent ( UFDouble watercontent) {
this.watercontent=watercontent;
} 
 
/**
* 属性 drybase的Getter方法.属性名：干基
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getDrybase() {
return this.drybase;
} 

/**
* 属性drybase的Setter方法.属性名：干基
* 创建日期:2017-7-7
* @param newDrybase nc.vo.pub.lang.UFDouble
*/
public void setDrybase ( UFDouble drybase) {
this.drybase=drybase;
} 
 
/**
* 属性 nprice的Getter方法.属性名：货物单价
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNprice() {
return this.nprice;
} 

/**
* 属性nprice的Setter方法.属性名：货物单价
* 创建日期:2017-7-7
* @param newNprice nc.vo.pub.lang.UFDouble
*/
public void setNprice ( UFDouble nprice) {
this.nprice=nprice;
} 
 
/**
* 属性 trainprice的Getter方法.属性名：运费单价
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTrainprice() {
return this.trainprice;
} 

/**
* 属性trainprice的Setter方法.属性名：运费单价
* 创建日期:2017-7-7
* @param newTrainprice nc.vo.pub.lang.UFDouble
*/
public void setTrainprice ( UFDouble trainprice) {
this.trainprice=trainprice;
} 
 
/**
* 属性 inprice的Getter方法.属性名：入库单价
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInprice() {
return this.inprice;
} 

/**
* 属性inprice的Setter方法.属性名：入库单价
* 创建日期:2017-7-7
* @param newInprice nc.vo.pub.lang.UFDouble
*/
public void setInprice ( UFDouble inprice) {
this.inprice=inprice;
} 
 
/**
* 属性 inmny的Getter方法.属性名：入库金额
*  创建日期:2017-7-7
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInmny() {
return this.inmny;
} 

/**
* 属性inmny的Setter方法.属性名：入库金额
* 创建日期:2017-7-7
* @param newInmny nc.vo.pub.lang.UFDouble
*/
public void setInmny ( UFDouble inmny) {
this.inmny=inmny;
} 
 
/**
* 属性 def1的Getter方法.属性名：自定义项1
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef1() {
return this.def1;
} 

/**
* 属性def1的Setter方法.属性名：自定义项1
* 创建日期:2017-7-7
* @param newDef1 java.lang.String
*/
public void setDef1 ( String def1) {
this.def1=def1;
} 
 
/**
* 属性 def2的Getter方法.属性名：自定义项2
*  创建日期:2017-7-7
* @return java.lang.String
*/
public String getDef2() {
return this.def2;
} 

/**
* 属性def2的Setter方法.属性名：自定义项2
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
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2017-7-7
* @return String
*/
public String getPk_ingredient_h(){
return this.pk_ingredient_h;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2017-7-7
* @param newPk_ingredient_h String
*/
public void setPk_ingredient_h(String pk_ingredient_h){
this.pk_ingredient_h=pk_ingredient_h;
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
    return VOMetaFactory.getInstance().getVOMeta("lhprj.bingredient");
    }
   }
    