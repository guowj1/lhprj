package nc.vo.lhprj.lhpostlog;

import nc.vo.pub.*;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加类的描述信息
 * </p>
 *  创建日期:2017-1-8
 * @author 
 * @version NCPrj ??
 */
public class LhPostLogVO extends nc.vo.pub.SuperVO{
	
    private java.lang.String pk_id;
    private java.lang.String pk_voucher;
    private java.lang.String vouchcode;
    private java.lang.String vouchtype;
    private java.lang.String sendresult;
    private java.lang.String resultdescription;
    private nc.vo.pub.lang.UFDateTime postdate;
    private java.lang.String pk_org;
    private java.lang.String pk_group;
    private java.lang.Integer dr = 0;
    private nc.vo.pub.lang.UFDateTime ts;    
	
	
    public static final String PK_ID = "pk_id";
    public static final String PK_VOUCHER = "pk_voucher";
    public static final String VOUCHCODE = "vouchcode";
    public static final String VOUCHTYPE = "vouchtype";
    public static final String SENDRESULT = "sendresult";
    public static final String RESULTDESCRIPTION = "resultdescription";
    public static final String POSTDATE = "postdate";
    public static final String PK_ORG = "pk_org";
    public static final String PK_GROUP = "pk_group";

	/**
	 * 属性 pk_id的Getter方法.属性名：主键
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_id () {
		return pk_id;
	}   
	/**
	 * 属性pk_id的Setter方法.属性名：主键
	 * 创建日期:2017-1-8
	 * @param newPk_id java.lang.String
	 */
	public void setPk_id (java.lang.String newPk_id ) {
	 	this.pk_id = newPk_id;
	} 	 
	
	/**
	 * 属性 pk_voucher的Getter方法.属性名：单据ID
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_voucher () {
		return pk_voucher;
	}   
	/**
	 * 属性pk_voucher的Setter方法.属性名：单据ID
	 * 创建日期:2017-1-8
	 * @param newPk_voucher java.lang.String
	 */
	public void setPk_voucher (java.lang.String newPk_voucher ) {
	 	this.pk_voucher = newPk_voucher;
	} 	 
	
	/**
	 * 属性 vouchcode的Getter方法.属性名：单据编号
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getVouchcode () {
		return vouchcode;
	}   
	/**
	 * 属性vouchcode的Setter方法.属性名：单据编号
	 * 创建日期:2017-1-8
	 * @param newVouchcode java.lang.String
	 */
	public void setVouchcode (java.lang.String newVouchcode ) {
	 	this.vouchcode = newVouchcode;
	} 	 
	
	/**
	 * 属性 vouchtype的Getter方法.属性名：单据类型
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getVouchtype () {
		return vouchtype;
	}   
	/**
	 * 属性vouchtype的Setter方法.属性名：单据类型
	 * 创建日期:2017-1-8
	 * @param newVouchtype java.lang.String
	 */
	public void setVouchtype (java.lang.String newVouchtype ) {
	 	this.vouchtype = newVouchtype;
	} 	 
	
	/**
	 * 属性 sendresult的Getter方法.属性名：发送结果
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getSendresult () {
		return sendresult;
	}   
	/**
	 * 属性sendresult的Setter方法.属性名：发送结果
	 * 创建日期:2017-1-8
	 * @param newSendresult java.lang.String
	 */
	public void setSendresult (java.lang.String newSendresult ) {
	 	this.sendresult = newSendresult;
	} 	 
	
	/**
	 * 属性 resultdescription的Getter方法.属性名：异常信息
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getResultdescription () {
		return resultdescription;
	}   
	/**
	 * 属性resultdescription的Setter方法.属性名：异常信息
	 * 创建日期:2017-1-8
	 * @param newResultdescription java.lang.String
	 */
	public void setResultdescription (java.lang.String newResultdescription ) {
	 	this.resultdescription = newResultdescription;
	} 	 
	
	/**
	 * 属性 postdate的Getter方法.属性名：发送时间
	 *  创建日期:2017-1-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getPostdate () {
		return postdate;
	}   
	/**
	 * 属性postdate的Setter方法.属性名：发送时间
	 * 创建日期:2017-1-8
	 * @param newPostdate nc.vo.pub.lang.UFDateTime
	 */
	public void setPostdate (nc.vo.pub.lang.UFDateTime newPostdate ) {
	 	this.postdate = newPostdate;
	} 	 
	
	/**
	 * 属性 pk_org的Getter方法.属性名：组织
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return pk_org;
	}   
	/**
	 * 属性pk_org的Setter方法.属性名：组织
	 * 创建日期:2017-1-8
	 * @param newPk_org java.lang.String
	 */
	public void setPk_org (java.lang.String newPk_org ) {
	 	this.pk_org = newPk_org;
	} 	 
	
	/**
	 * 属性 pk_group的Getter方法.属性名：集团
	 *  创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return pk_group;
	}   
	/**
	 * 属性pk_group的Setter方法.属性名：集团
	 * 创建日期:2017-1-8
	 * @param newPk_group java.lang.String
	 */
	public void setPk_group (java.lang.String newPk_group ) {
	 	this.pk_group = newPk_group;
	} 	 
	
	/**
	 * 属性 dr的Getter方法.属性名：dr
	 *  创建日期:2017-1-8
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return dr;
	}   
	/**
	 * 属性dr的Setter方法.属性名：dr
	 * 创建日期:2017-1-8
	 * @param newDr java.lang.Integer
	 */
	public void setDr (java.lang.Integer newDr ) {
	 	this.dr = newDr;
	} 	 
	
	/**
	 * 属性 ts的Getter方法.属性名：ts
	 *  创建日期:2017-1-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return ts;
	}   
	/**
	 * 属性ts的Setter方法.属性名：ts
	 * 创建日期:2017-1-8
	 * @param newTs nc.vo.pub.lang.UFDateTime
	 */
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
	 	this.ts = newTs;
	} 	 
	
	
	/**
	  * <p>取得父VO主键字段.
	  * <p>
	  * 创建日期:2017-1-8
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>取得表主键.
	  * <p>
	  * 创建日期:2017-1-8
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_id";
	}
    
	/**
	 * <p>返回表名称
	 * <p>
	 * 创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "lh_lhpostlog";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:2017-1-8
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "lh_lhpostlog";
	}    
    
    /**
	  * 按照默认方式创建构造子.
	  *
	  * 创建日期:2017-1-8
	  */
     public LhPostLogVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.lhprj.lhpostlog.LhPostLogVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("lhprj.lhpostlog");
		
   	}
     
}