package nc.vo.lhprj.lhpostlog;

import nc.vo.pub.*;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�������������Ϣ
 * </p>
 *  ��������:2017-1-8
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
	 * ���� pk_id��Getter����.������������
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_id () {
		return pk_id;
	}   
	/**
	 * ����pk_id��Setter����.������������
	 * ��������:2017-1-8
	 * @param newPk_id java.lang.String
	 */
	public void setPk_id (java.lang.String newPk_id ) {
	 	this.pk_id = newPk_id;
	} 	 
	
	/**
	 * ���� pk_voucher��Getter����.������������ID
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_voucher () {
		return pk_voucher;
	}   
	/**
	 * ����pk_voucher��Setter����.������������ID
	 * ��������:2017-1-8
	 * @param newPk_voucher java.lang.String
	 */
	public void setPk_voucher (java.lang.String newPk_voucher ) {
	 	this.pk_voucher = newPk_voucher;
	} 	 
	
	/**
	 * ���� vouchcode��Getter����.�����������ݱ��
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getVouchcode () {
		return vouchcode;
	}   
	/**
	 * ����vouchcode��Setter����.�����������ݱ��
	 * ��������:2017-1-8
	 * @param newVouchcode java.lang.String
	 */
	public void setVouchcode (java.lang.String newVouchcode ) {
	 	this.vouchcode = newVouchcode;
	} 	 
	
	/**
	 * ���� vouchtype��Getter����.����������������
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getVouchtype () {
		return vouchtype;
	}   
	/**
	 * ����vouchtype��Setter����.����������������
	 * ��������:2017-1-8
	 * @param newVouchtype java.lang.String
	 */
	public void setVouchtype (java.lang.String newVouchtype ) {
	 	this.vouchtype = newVouchtype;
	} 	 
	
	/**
	 * ���� sendresult��Getter����.�����������ͽ��
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getSendresult () {
		return sendresult;
	}   
	/**
	 * ����sendresult��Setter����.�����������ͽ��
	 * ��������:2017-1-8
	 * @param newSendresult java.lang.String
	 */
	public void setSendresult (java.lang.String newSendresult ) {
	 	this.sendresult = newSendresult;
	} 	 
	
	/**
	 * ���� resultdescription��Getter����.���������쳣��Ϣ
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getResultdescription () {
		return resultdescription;
	}   
	/**
	 * ����resultdescription��Setter����.���������쳣��Ϣ
	 * ��������:2017-1-8
	 * @param newResultdescription java.lang.String
	 */
	public void setResultdescription (java.lang.String newResultdescription ) {
	 	this.resultdescription = newResultdescription;
	} 	 
	
	/**
	 * ���� postdate��Getter����.������������ʱ��
	 *  ��������:2017-1-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getPostdate () {
		return postdate;
	}   
	/**
	 * ����postdate��Setter����.������������ʱ��
	 * ��������:2017-1-8
	 * @param newPostdate nc.vo.pub.lang.UFDateTime
	 */
	public void setPostdate (nc.vo.pub.lang.UFDateTime newPostdate ) {
	 	this.postdate = newPostdate;
	} 	 
	
	/**
	 * ���� pk_org��Getter����.����������֯
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return pk_org;
	}   
	/**
	 * ����pk_org��Setter����.����������֯
	 * ��������:2017-1-8
	 * @param newPk_org java.lang.String
	 */
	public void setPk_org (java.lang.String newPk_org ) {
	 	this.pk_org = newPk_org;
	} 	 
	
	/**
	 * ���� pk_group��Getter����.������������
	 *  ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return pk_group;
	}   
	/**
	 * ����pk_group��Setter����.������������
	 * ��������:2017-1-8
	 * @param newPk_group java.lang.String
	 */
	public void setPk_group (java.lang.String newPk_group ) {
	 	this.pk_group = newPk_group;
	} 	 
	
	/**
	 * ���� dr��Getter����.��������dr
	 *  ��������:2017-1-8
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return dr;
	}   
	/**
	 * ����dr��Setter����.��������dr
	 * ��������:2017-1-8
	 * @param newDr java.lang.Integer
	 */
	public void setDr (java.lang.Integer newDr ) {
	 	this.dr = newDr;
	} 	 
	
	/**
	 * ���� ts��Getter����.��������ts
	 *  ��������:2017-1-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return ts;
	}   
	/**
	 * ����ts��Setter����.��������ts
	 * ��������:2017-1-8
	 * @param newTs nc.vo.pub.lang.UFDateTime
	 */
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
	 	this.ts = newTs;
	} 	 
	
	
	/**
	  * <p>ȡ�ø�VO�����ֶ�.
	  * <p>
	  * ��������:2017-1-8
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>ȡ�ñ�����.
	  * <p>
	  * ��������:2017-1-8
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_id";
	}
    
	/**
	 * <p>���ر�����
	 * <p>
	 * ��������:2017-1-8
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "lh_lhpostlog";
	}    
	
	/**
	 * <p>���ر�����.
	 * <p>
	 * ��������:2017-1-8
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "lh_lhpostlog";
	}    
    
    /**
	  * ����Ĭ�Ϸ�ʽ����������.
	  *
	  * ��������:2017-1-8
	  */
     public LhPostLogVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.lhprj.lhpostlog.LhPostLogVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("lhprj.lhpostlog");
		
   	}
     
}