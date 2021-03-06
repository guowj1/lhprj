package nc.vo.sxlhscm.lhinvbalance;

import nc.vo.pub.*;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加类的描述信息
 * </p>
 *  创建日期:2017-7-8
 * @author YONYOU NC
 * @version NCPrj ??
 */
public class InvBalanceVO extends nc.vo.pub.SuperVO{
	
    private java.lang.String pk_invbalance;
    private java.lang.String pk_group;
    private java.lang.String pk_org;
    private java.lang.String pk_org_v;
    private java.lang.String pk_stordoc;
    private java.lang.String def1;
    private nc.vo.pub.lang.UFDate dbilldate;
    private java.lang.String pk_material;
    private nc.vo.pub.lang.UFDouble bnum;
    private nc.vo.pub.lang.UFDouble bnprice;
    private nc.vo.pub.lang.UFDouble bmny;
    private nc.vo.pub.lang.UFDouble yldef1;
    private nc.vo.pub.lang.UFDouble yldef2;
    private nc.vo.pub.lang.UFDouble yldef5;
    private nc.vo.pub.lang.UFDouble yldef4;
    private nc.vo.pub.lang.UFDouble yldef3;
    private java.lang.String yldef6;
    private java.lang.String yldef7;
    private java.lang.String yldef8;
    private java.lang.String yldef9;
    private java.lang.String yldef10;
    private java.lang.String creator;
    private nc.vo.pub.lang.UFDateTime creationtime;
    private java.lang.String modifier;
    private nc.vo.pub.lang.UFDateTime modifiedtime;
    private java.lang.String def2;
    private java.lang.String def3;
    private java.lang.String def4;
    private java.lang.String def5;
    private java.lang.String def6;
    private java.lang.String def7;
    private java.lang.String def8;
    private java.lang.String def9;
    private java.lang.String def10;
    private java.lang.Integer dr = 0;
    private nc.vo.pub.lang.UFDateTime ts;    
	
	
    public static final String PK_INVBALANCE = "pk_invbalance";
    public static final String PK_GROUP = "pk_group";
    public static final String PK_ORG = "pk_org";
    public static final String PK_ORG_V = "pk_org_v";
    public static final String PK_STORDOC = "pk_stordoc";
    public static final String DEF1 = "def1";
    public static final String DBILLDATE = "dbilldate";
    public static final String PK_MATERIAL = "pk_material";
    public static final String BNUM = "bnum";
    public static final String BNPRICE = "bnprice";
    public static final String BMNY = "bmny";
    public static final String YLDEF1 = "yldef1";
    public static final String YLDEF2 = "yldef2";
    public static final String YLDEF5 = "yldef5";
    public static final String YLDEF4 = "yldef4";
    public static final String YLDEF3 = "yldef3";
    public static final String YLDEF6 = "yldef6";
    public static final String YLDEF7 = "yldef7";
    public static final String YLDEF8 = "yldef8";
    public static final String YLDEF9 = "yldef9";
    public static final String YLDEF10 = "yldef10";
    public static final String CREATOR = "creator";
    public static final String CREATIONTIME = "creationtime";
    public static final String MODIFIER = "modifier";
    public static final String MODIFIEDTIME = "modifiedtime";
    public static final String DEF2 = "def2";
    public static final String DEF3 = "def3";
    public static final String DEF4 = "def4";
    public static final String DEF5 = "def5";
    public static final String DEF6 = "def6";
    public static final String DEF7 = "def7";
    public static final String DEF8 = "def8";
    public static final String DEF9 = "def9";
    public static final String DEF10 = "def10";

	/**
	 * 属性 pk_invbalance的Getter方法.属性名：主键
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_invbalance () {
		return pk_invbalance;
	}   
	/**
	 * 属性pk_invbalance的Setter方法.属性名：主键
	 * 创建日期:2017-7-8
	 * @param newPk_invbalance java.lang.String
	 */
	public void setPk_invbalance (java.lang.String newPk_invbalance ) {
	 	this.pk_invbalance = newPk_invbalance;
	} 	 
	
	/**
	 * 属性 pk_group的Getter方法.属性名：集团
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return pk_group;
	}   
	/**
	 * 属性pk_group的Setter方法.属性名：集团
	 * 创建日期:2017-7-8
	 * @param newPk_group java.lang.String
	 */
	public void setPk_group (java.lang.String newPk_group ) {
	 	this.pk_group = newPk_group;
	} 	 
	
	/**
	 * 属性 pk_org的Getter方法.属性名：库存组织
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return pk_org;
	}   
	/**
	 * 属性pk_org的Setter方法.属性名：库存组织
	 * 创建日期:2017-7-8
	 * @param newPk_org java.lang.String
	 */
	public void setPk_org (java.lang.String newPk_org ) {
	 	this.pk_org = newPk_org;
	} 	 
	
	/**
	 * 属性 pk_org_v的Getter方法.属性名：库存组织最新版本
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v () {
		return pk_org_v;
	}   
	/**
	 * 属性pk_org_v的Setter方法.属性名：库存组织最新版本
	 * 创建日期:2017-7-8
	 * @param newPk_org_v java.lang.String
	 */
	public void setPk_org_v (java.lang.String newPk_org_v ) {
	 	this.pk_org_v = newPk_org_v;
	} 	 
	
	/**
	 * 属性 pk_stordoc的Getter方法.属性名：仓库
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_stordoc () {
		return pk_stordoc;
	}   
	/**
	 * 属性pk_stordoc的Setter方法.属性名：仓库
	 * 创建日期:2017-7-8
	 * @param newPk_stordoc java.lang.String
	 */
	public void setPk_stordoc (java.lang.String newPk_stordoc ) {
	 	this.pk_stordoc = newPk_stordoc;
	} 	 
	
	/**
	 * 属性 def1的Getter方法.属性名：分厂
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef1 () {
		return def1;
	}   
	/**
	 * 属性def1的Setter方法.属性名：分厂
	 * 创建日期:2017-7-8
	 * @param newDef1 java.lang.String
	 */
	public void setDef1 (java.lang.String newDef1 ) {
	 	this.def1 = newDef1;
	} 	 
	
	/**
	 * 属性 dbilldate的Getter方法.属性名：结存日期
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getDbilldate () {
		return dbilldate;
	}   
	/**
	 * 属性dbilldate的Setter方法.属性名：结存日期
	 * 创建日期:2017-7-8
	 * @param newDbilldate nc.vo.pub.lang.UFDate
	 */
	public void setDbilldate (nc.vo.pub.lang.UFDate newDbilldate ) {
	 	this.dbilldate = newDbilldate;
	} 	 
	
	/**
	 * 属性 pk_material的Getter方法.属性名：物料
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_material () {
		return pk_material;
	}   
	/**
	 * 属性pk_material的Setter方法.属性名：物料
	 * 创建日期:2017-7-8
	 * @param newPk_material java.lang.String
	 */
	public void setPk_material (java.lang.String newPk_material ) {
	 	this.pk_material = newPk_material;
	} 	 
	
	/**
	 * 属性 bnum的Getter方法.属性名：结存数量
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getBnum () {
		return bnum;
	}   
	/**
	 * 属性bnum的Setter方法.属性名：结存数量
	 * 创建日期:2017-7-8
	 * @param newBnum nc.vo.pub.lang.UFDouble
	 */
	public void setBnum (nc.vo.pub.lang.UFDouble newBnum ) {
	 	this.bnum = newBnum;
	} 	 
	
	/**
	 * 属性 bnprice的Getter方法.属性名：结存单价
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getBnprice () {
		return bnprice;
	}   
	/**
	 * 属性bnprice的Setter方法.属性名：结存单价
	 * 创建日期:2017-7-8
	 * @param newBnprice nc.vo.pub.lang.UFDouble
	 */
	public void setBnprice (nc.vo.pub.lang.UFDouble newBnprice ) {
	 	this.bnprice = newBnprice;
	} 	 
	
	/**
	 * 属性 bmny的Getter方法.属性名：结存金额
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getBmny () {
		return bmny;
	}   
	/**
	 * 属性bmny的Setter方法.属性名：结存金额
	 * 创建日期:2017-7-8
	 * @param newBmny nc.vo.pub.lang.UFDouble
	 */
	public void setBmny (nc.vo.pub.lang.UFDouble newBmny ) {
	 	this.bmny = newBmny;
	} 	 
	
	/**
	 * 属性 yldef1的Getter方法.属性名：预留字段1
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef1 () {
		return yldef1;
	}   
	/**
	 * 属性yldef1的Setter方法.属性名：预留字段1
	 * 创建日期:2017-7-8
	 * @param newYldef1 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef1 (nc.vo.pub.lang.UFDouble newYldef1 ) {
	 	this.yldef1 = newYldef1;
	} 	 
	
	/**
	 * 属性 yldef2的Getter方法.属性名：预留字段2
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef2 () {
		return yldef2;
	}   
	/**
	 * 属性yldef2的Setter方法.属性名：预留字段2
	 * 创建日期:2017-7-8
	 * @param newYldef2 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef2 (nc.vo.pub.lang.UFDouble newYldef2 ) {
	 	this.yldef2 = newYldef2;
	} 	 
	
	/**
	 * 属性 yldef5的Getter方法.属性名：预留字段5
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef5 () {
		return yldef5;
	}   
	/**
	 * 属性yldef5的Setter方法.属性名：预留字段5
	 * 创建日期:2017-7-8
	 * @param newYldef5 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef5 (nc.vo.pub.lang.UFDouble newYldef5 ) {
	 	this.yldef5 = newYldef5;
	} 	 
	
	/**
	 * 属性 yldef4的Getter方法.属性名：预留字段4
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef4 () {
		return yldef4;
	}   
	/**
	 * 属性yldef4的Setter方法.属性名：预留字段4
	 * 创建日期:2017-7-8
	 * @param newYldef4 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef4 (nc.vo.pub.lang.UFDouble newYldef4 ) {
	 	this.yldef4 = newYldef4;
	} 	 
	
	/**
	 * 属性 yldef3的Getter方法.属性名：预留字段3
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef3 () {
		return yldef3;
	}   
	/**
	 * 属性yldef3的Setter方法.属性名：预留字段3
	 * 创建日期:2017-7-8
	 * @param newYldef3 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef3 (nc.vo.pub.lang.UFDouble newYldef3 ) {
	 	this.yldef3 = newYldef3;
	} 	 
	
	/**
	 * 属性 yldef6的Getter方法.属性名：预留字段6
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef6 () {
		return yldef6;
	}   
	/**
	 * 属性yldef6的Setter方法.属性名：预留字段6
	 * 创建日期:2017-7-8
	 * @param newYldef6 java.lang.String
	 */
	public void setYldef6 (java.lang.String newYldef6 ) {
	 	this.yldef6 = newYldef6;
	} 	 
	
	/**
	 * 属性 yldef7的Getter方法.属性名：预留字段7
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef7 () {
		return yldef7;
	}   
	/**
	 * 属性yldef7的Setter方法.属性名：预留字段7
	 * 创建日期:2017-7-8
	 * @param newYldef7 java.lang.String
	 */
	public void setYldef7 (java.lang.String newYldef7 ) {
	 	this.yldef7 = newYldef7;
	} 	 
	
	/**
	 * 属性 yldef8的Getter方法.属性名：预留字段8
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef8 () {
		return yldef8;
	}   
	/**
	 * 属性yldef8的Setter方法.属性名：预留字段8
	 * 创建日期:2017-7-8
	 * @param newYldef8 java.lang.String
	 */
	public void setYldef8 (java.lang.String newYldef8 ) {
	 	this.yldef8 = newYldef8;
	} 	 
	
	/**
	 * 属性 yldef9的Getter方法.属性名：预留字段9
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef9 () {
		return yldef9;
	}   
	/**
	 * 属性yldef9的Setter方法.属性名：预留字段9
	 * 创建日期:2017-7-8
	 * @param newYldef9 java.lang.String
	 */
	public void setYldef9 (java.lang.String newYldef9 ) {
	 	this.yldef9 = newYldef9;
	} 	 
	
	/**
	 * 属性 yldef10的Getter方法.属性名：预留字段10
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef10 () {
		return yldef10;
	}   
	/**
	 * 属性yldef10的Setter方法.属性名：预留字段10
	 * 创建日期:2017-7-8
	 * @param newYldef10 java.lang.String
	 */
	public void setYldef10 (java.lang.String newYldef10 ) {
	 	this.yldef10 = newYldef10;
	} 	 
	
	/**
	 * 属性 creator的Getter方法.属性名：创建人
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getCreator () {
		return creator;
	}   
	/**
	 * 属性creator的Setter方法.属性名：创建人
	 * 创建日期:2017-7-8
	 * @param newCreator java.lang.String
	 */
	public void setCreator (java.lang.String newCreator ) {
	 	this.creator = newCreator;
	} 	 
	
	/**
	 * 属性 creationtime的Getter方法.属性名：创建时间
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime () {
		return creationtime;
	}   
	/**
	 * 属性creationtime的Setter方法.属性名：创建时间
	 * 创建日期:2017-7-8
	 * @param newCreationtime nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime (nc.vo.pub.lang.UFDateTime newCreationtime ) {
	 	this.creationtime = newCreationtime;
	} 	 
	
	/**
	 * 属性 modifier的Getter方法.属性名：修改人
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getModifier () {
		return modifier;
	}   
	/**
	 * 属性modifier的Setter方法.属性名：修改人
	 * 创建日期:2017-7-8
	 * @param newModifier java.lang.String
	 */
	public void setModifier (java.lang.String newModifier ) {
	 	this.modifier = newModifier;
	} 	 
	
	/**
	 * 属性 modifiedtime的Getter方法.属性名：修改时间
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime () {
		return modifiedtime;
	}   
	/**
	 * 属性modifiedtime的Setter方法.属性名：修改时间
	 * 创建日期:2017-7-8
	 * @param newModifiedtime nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime (nc.vo.pub.lang.UFDateTime newModifiedtime ) {
	 	this.modifiedtime = newModifiedtime;
	} 	 
	
	/**
	 * 属性 def2的Getter方法.属性名：自定义项2
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef2 () {
		return def2;
	}   
	/**
	 * 属性def2的Setter方法.属性名：自定义项2
	 * 创建日期:2017-7-8
	 * @param newDef2 java.lang.String
	 */
	public void setDef2 (java.lang.String newDef2 ) {
	 	this.def2 = newDef2;
	} 	 
	
	/**
	 * 属性 def3的Getter方法.属性名：自定义项3
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef3 () {
		return def3;
	}   
	/**
	 * 属性def3的Setter方法.属性名：自定义项3
	 * 创建日期:2017-7-8
	 * @param newDef3 java.lang.String
	 */
	public void setDef3 (java.lang.String newDef3 ) {
	 	this.def3 = newDef3;
	} 	 
	
	/**
	 * 属性 def4的Getter方法.属性名：自定义项4
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef4 () {
		return def4;
	}   
	/**
	 * 属性def4的Setter方法.属性名：自定义项4
	 * 创建日期:2017-7-8
	 * @param newDef4 java.lang.String
	 */
	public void setDef4 (java.lang.String newDef4 ) {
	 	this.def4 = newDef4;
	} 	 
	
	/**
	 * 属性 def5的Getter方法.属性名：自定义项5
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef5 () {
		return def5;
	}   
	/**
	 * 属性def5的Setter方法.属性名：自定义项5
	 * 创建日期:2017-7-8
	 * @param newDef5 java.lang.String
	 */
	public void setDef5 (java.lang.String newDef5 ) {
	 	this.def5 = newDef5;
	} 	 
	
	/**
	 * 属性 def6的Getter方法.属性名：自定义项6
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef6 () {
		return def6;
	}   
	/**
	 * 属性def6的Setter方法.属性名：自定义项6
	 * 创建日期:2017-7-8
	 * @param newDef6 java.lang.String
	 */
	public void setDef6 (java.lang.String newDef6 ) {
	 	this.def6 = newDef6;
	} 	 
	
	/**
	 * 属性 def7的Getter方法.属性名：自定义项7
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef7 () {
		return def7;
	}   
	/**
	 * 属性def7的Setter方法.属性名：自定义项7
	 * 创建日期:2017-7-8
	 * @param newDef7 java.lang.String
	 */
	public void setDef7 (java.lang.String newDef7 ) {
	 	this.def7 = newDef7;
	} 	 
	
	/**
	 * 属性 def8的Getter方法.属性名：自定义项8
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef8 () {
		return def8;
	}   
	/**
	 * 属性def8的Setter方法.属性名：自定义项8
	 * 创建日期:2017-7-8
	 * @param newDef8 java.lang.String
	 */
	public void setDef8 (java.lang.String newDef8 ) {
	 	this.def8 = newDef8;
	} 	 
	
	/**
	 * 属性 def9的Getter方法.属性名：自定义项9
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef9 () {
		return def9;
	}   
	/**
	 * 属性def9的Setter方法.属性名：自定义项9
	 * 创建日期:2017-7-8
	 * @param newDef9 java.lang.String
	 */
	public void setDef9 (java.lang.String newDef9 ) {
	 	this.def9 = newDef9;
	} 	 
	
	/**
	 * 属性 def10的Getter方法.属性名：自定义项10
	 *  创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef10 () {
		return def10;
	}   
	/**
	 * 属性def10的Setter方法.属性名：自定义项10
	 * 创建日期:2017-7-8
	 * @param newDef10 java.lang.String
	 */
	public void setDef10 (java.lang.String newDef10 ) {
	 	this.def10 = newDef10;
	} 	 
	
	/**
	 * 属性 dr的Getter方法.属性名：dr
	 *  创建日期:2017-7-8
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return dr;
	}   
	/**
	 * 属性dr的Setter方法.属性名：dr
	 * 创建日期:2017-7-8
	 * @param newDr java.lang.Integer
	 */
	public void setDr (java.lang.Integer newDr ) {
	 	this.dr = newDr;
	} 	 
	
	/**
	 * 属性 ts的Getter方法.属性名：ts
	 *  创建日期:2017-7-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return ts;
	}   
	/**
	 * 属性ts的Setter方法.属性名：ts
	 * 创建日期:2017-7-8
	 * @param newTs nc.vo.pub.lang.UFDateTime
	 */
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
	 	this.ts = newTs;
	} 	 
	
	
	/**
	  * <p>取得父VO主键字段.
	  * <p>
	  * 创建日期:2017-7-8
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>取得表主键.
	  * <p>
	  * 创建日期:2017-7-8
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_invbalance";
	}
    
	/**
	 * <p>返回表名称
	 * <p>
	 * 创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uap_invbalance";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:2017-7-8
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uap_invbalance";
	}    
    
    /**
	  * 按照默认方式创建构造子.
	  *
	  * 创建日期:2017-7-8
	  */
     public InvBalanceVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.sxlhscm.lhinvbalance.InvBalanceVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("lhprj.lhinvbalance");
		
   	}
     
}