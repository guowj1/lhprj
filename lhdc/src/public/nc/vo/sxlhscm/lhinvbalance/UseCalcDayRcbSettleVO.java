package nc.vo.sxlhscm.lhinvbalance;

import nc.vo.pub.lang.UFDouble;

public class UseCalcDayRcbSettleVO extends nc.vo.pub.SuperVO{
	
	
	private String    crtnbillid;
	private String    pk_group;
	private String    pk_org;
	private String    pk_org_v;
	private String    pk_stordoc;
	private String    sfactoryid;
	private String    dbilldate;
	private String    pk_material;
	private UFDouble  jsnum;
	private UFDouble  jsmny;
	
	
	
	
	public String getPk_stordoc() {
		return pk_stordoc;
	}

	public void setPk_stordoc(String pk_stordoc) {
		this.pk_stordoc = pk_stordoc;
	}

	public String getCrtnbillid() {
		return crtnbillid;
	}

	public void setCrtnbillid(String crtnbillid) {
		this.crtnbillid = crtnbillid;
	}

	public String getPk_group() {
		return pk_group;
	}

	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getPk_org_v() {
		return pk_org_v;
	}

	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	public String getSfactoryid() {
		return sfactoryid;
	}

	public void setSfactoryid(String sfactoryid) {
		this.sfactoryid = sfactoryid;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public String getPk_material() {
		return pk_material;
	}

	public void setPk_material(String pk_material) {
		this.pk_material = pk_material;
	}

	public UFDouble getJsnum() {
		return jsnum;
	}

	public void setJsnum(UFDouble jsnum) {
		this.jsnum = jsnum;
	}

	public UFDouble getJsmny() {
		return jsmny;
	}

	public void setJsmny(UFDouble jsmny) {
		this.jsmny = jsmny;
	}

	/**
	  * <p>取得父VO主键字段.
	  * <p>
	  * 创建日期:2017-5-19
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
   
	/**
	  * <p>取得表主键.
	  * <p>
	  * 创建日期:2017-5-19
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_invbalance";
	}
   
	/**
	 * <p>返回表名称
	 * <p>
	 * 创建日期:2017-5-19
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uap_invbalance";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:2017-5-19
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uap_invbalance";
	}    

}
