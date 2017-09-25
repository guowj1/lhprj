package nc.vo.ic.lhcalcday;

import nc.vo.pub.*;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�������������Ϣ
 * </p>
 *  ��������:2017-7-8
 * @author YONYOU NC
 * @version NCPrj ??
 */
public class CalcdayVO extends nc.vo.pub.SuperVO{
	
    private java.lang.String pk_lhcalcday;
    private java.lang.String pk_group;
    private java.lang.String pk_org;
    private java.lang.String pk_org_v;
    private nc.vo.pub.lang.UFDate calcdate;
    private nc.vo.pub.lang.UFBoolean iscalc;
    private java.lang.String creator;
    private nc.vo.pub.lang.UFDateTime creationtime;
    private java.lang.String modifier;
    private nc.vo.pub.lang.UFDateTime modifiedtime;
    private java.lang.String def1;
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
	
	
    public static final String PK_LHCALCDAY = "pk_lhcalcday";
    public static final String PK_GROUP = "pk_group";
    public static final String PK_ORG = "pk_org";
    public static final String PK_ORG_V = "pk_org_v";
    public static final String CALCDATE = "calcdate";
    public static final String ISCALC = "iscalc";
    public static final String CREATOR = "creator";
    public static final String CREATIONTIME = "creationtime";
    public static final String MODIFIER = "modifier";
    public static final String MODIFIEDTIME = "modifiedtime";
    public static final String DEF1 = "def1";
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
	 * ���� pk_lhcalcday��Getter����.������������
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_lhcalcday () {
		return pk_lhcalcday;
	}   
	/**
	 * ����pk_lhcalcday��Setter����.������������
	 * ��������:2017-7-8
	 * @param newPk_lhcalcday java.lang.String
	 */
	public void setPk_lhcalcday (java.lang.String newPk_lhcalcday ) {
	 	this.pk_lhcalcday = newPk_lhcalcday;
	} 	 
	
	/**
	 * ���� pk_group��Getter����.������������
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return pk_group;
	}   
	/**
	 * ����pk_group��Setter����.������������
	 * ��������:2017-7-8
	 * @param newPk_group java.lang.String
	 */
	public void setPk_group (java.lang.String newPk_group ) {
	 	this.pk_group = newPk_group;
	} 	 
	
	/**
	 * ���� pk_org��Getter����.�������������֯
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return pk_org;
	}   
	/**
	 * ����pk_org��Setter����.�������������֯
	 * ��������:2017-7-8
	 * @param newPk_org java.lang.String
	 */
	public void setPk_org (java.lang.String newPk_org ) {
	 	this.pk_org = newPk_org;
	} 	 
	
	/**
	 * ���� pk_org_v��Getter����.�������������֯�汾
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v () {
		return pk_org_v;
	}   
	/**
	 * ����pk_org_v��Setter����.�������������֯�汾
	 * ��������:2017-7-8
	 * @param newPk_org_v java.lang.String
	 */
	public void setPk_org_v (java.lang.String newPk_org_v ) {
	 	this.pk_org_v = newPk_org_v;
	} 	 
	
	/**
	 * ���� calcdate��Getter����.����������������
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getCalcdate () {
		return calcdate;
	}   
	/**
	 * ����calcdate��Setter����.����������������
	 * ��������:2017-7-8
	 * @param newCalcdate nc.vo.pub.lang.UFDate
	 */
	public void setCalcdate (nc.vo.pub.lang.UFDate newCalcdate ) {
	 	this.calcdate = newCalcdate;
	} 	 
	
	/**
	 * ���� iscalc��Getter����.���������Ƿ����
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getIscalc () {
		return iscalc;
	}   
	/**
	 * ����iscalc��Setter����.���������Ƿ����
	 * ��������:2017-7-8
	 * @param newIscalc nc.vo.pub.lang.UFBoolean
	 */
	public void setIscalc (nc.vo.pub.lang.UFBoolean newIscalc ) {
	 	this.iscalc = newIscalc;
	} 	 
	
	/**
	 * ���� creator��Getter����.��������������
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getCreator () {
		return creator;
	}   
	/**
	 * ����creator��Setter����.��������������
	 * ��������:2017-7-8
	 * @param newCreator java.lang.String
	 */
	public void setCreator (java.lang.String newCreator ) {
	 	this.creator = newCreator;
	} 	 
	
	/**
	 * ���� creationtime��Getter����.������������ʱ��
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime () {
		return creationtime;
	}   
	/**
	 * ����creationtime��Setter����.������������ʱ��
	 * ��������:2017-7-8
	 * @param newCreationtime nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime (nc.vo.pub.lang.UFDateTime newCreationtime ) {
	 	this.creationtime = newCreationtime;
	} 	 
	
	/**
	 * ���� modifier��Getter����.���������޸���
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getModifier () {
		return modifier;
	}   
	/**
	 * ����modifier��Setter����.���������޸���
	 * ��������:2017-7-8
	 * @param newModifier java.lang.String
	 */
	public void setModifier (java.lang.String newModifier ) {
	 	this.modifier = newModifier;
	} 	 
	
	/**
	 * ���� modifiedtime��Getter����.���������޸�ʱ��
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime () {
		return modifiedtime;
	}   
	/**
	 * ����modifiedtime��Setter����.���������޸�ʱ��
	 * ��������:2017-7-8
	 * @param newModifiedtime nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime (nc.vo.pub.lang.UFDateTime newModifiedtime ) {
	 	this.modifiedtime = newModifiedtime;
	} 	 
	
	/**
	 * ���� def1��Getter����.���������Զ�����1
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef1 () {
		return def1;
	}   
	/**
	 * ����def1��Setter����.���������Զ�����1
	 * ��������:2017-7-8
	 * @param newDef1 java.lang.String
	 */
	public void setDef1 (java.lang.String newDef1 ) {
	 	this.def1 = newDef1;
	} 	 
	
	/**
	 * ���� def2��Getter����.���������Զ�����2
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef2 () {
		return def2;
	}   
	/**
	 * ����def2��Setter����.���������Զ�����2
	 * ��������:2017-7-8
	 * @param newDef2 java.lang.String
	 */
	public void setDef2 (java.lang.String newDef2 ) {
	 	this.def2 = newDef2;
	} 	 
	
	/**
	 * ���� def3��Getter����.���������Զ�����3
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef3 () {
		return def3;
	}   
	/**
	 * ����def3��Setter����.���������Զ�����3
	 * ��������:2017-7-8
	 * @param newDef3 java.lang.String
	 */
	public void setDef3 (java.lang.String newDef3 ) {
	 	this.def3 = newDef3;
	} 	 
	
	/**
	 * ���� def4��Getter����.���������Զ�����4
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef4 () {
		return def4;
	}   
	/**
	 * ����def4��Setter����.���������Զ�����4
	 * ��������:2017-7-8
	 * @param newDef4 java.lang.String
	 */
	public void setDef4 (java.lang.String newDef4 ) {
	 	this.def4 = newDef4;
	} 	 
	
	/**
	 * ���� def5��Getter����.���������Զ�����5
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef5 () {
		return def5;
	}   
	/**
	 * ����def5��Setter����.���������Զ�����5
	 * ��������:2017-7-8
	 * @param newDef5 java.lang.String
	 */
	public void setDef5 (java.lang.String newDef5 ) {
	 	this.def5 = newDef5;
	} 	 
	
	/**
	 * ���� def6��Getter����.���������Զ�����6
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef6 () {
		return def6;
	}   
	/**
	 * ����def6��Setter����.���������Զ�����6
	 * ��������:2017-7-8
	 * @param newDef6 java.lang.String
	 */
	public void setDef6 (java.lang.String newDef6 ) {
	 	this.def6 = newDef6;
	} 	 
	
	/**
	 * ���� def7��Getter����.���������Զ�����7
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef7 () {
		return def7;
	}   
	/**
	 * ����def7��Setter����.���������Զ�����7
	 * ��������:2017-7-8
	 * @param newDef7 java.lang.String
	 */
	public void setDef7 (java.lang.String newDef7 ) {
	 	this.def7 = newDef7;
	} 	 
	
	/**
	 * ���� def8��Getter����.���������Զ�����8
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef8 () {
		return def8;
	}   
	/**
	 * ����def8��Setter����.���������Զ�����8
	 * ��������:2017-7-8
	 * @param newDef8 java.lang.String
	 */
	public void setDef8 (java.lang.String newDef8 ) {
	 	this.def8 = newDef8;
	} 	 
	
	/**
	 * ���� def9��Getter����.���������Զ�����9
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef9 () {
		return def9;
	}   
	/**
	 * ����def9��Setter����.���������Զ�����9
	 * ��������:2017-7-8
	 * @param newDef9 java.lang.String
	 */
	public void setDef9 (java.lang.String newDef9 ) {
	 	this.def9 = newDef9;
	} 	 
	
	/**
	 * ���� def10��Getter����.���������Զ�����10
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef10 () {
		return def10;
	}   
	/**
	 * ����def10��Setter����.���������Զ�����10
	 * ��������:2017-7-8
	 * @param newDef10 java.lang.String
	 */
	public void setDef10 (java.lang.String newDef10 ) {
	 	this.def10 = newDef10;
	} 	 
	
	/**
	 * ���� dr��Getter����.��������dr
	 *  ��������:2017-7-8
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return dr;
	}   
	/**
	 * ����dr��Setter����.��������dr
	 * ��������:2017-7-8
	 * @param newDr java.lang.Integer
	 */
	public void setDr (java.lang.Integer newDr ) {
	 	this.dr = newDr;
	} 	 
	
	/**
	 * ���� ts��Getter����.��������ts
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return ts;
	}   
	/**
	 * ����ts��Setter����.��������ts
	 * ��������:2017-7-8
	 * @param newTs nc.vo.pub.lang.UFDateTime
	 */
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
	 	this.ts = newTs;
	} 	 
	
	
	/**
	  * <p>ȡ�ø�VO�����ֶ�.
	  * <p>
	  * ��������:2017-7-8
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>ȡ�ñ�����.
	  * <p>
	  * ��������:2017-7-8
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_lhcalcday";
	}
    
	/**
	 * <p>���ر�����
	 * <p>
	 * ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uap_lhcalcday";
	}    
	
	/**
	 * <p>���ر�����.
	 * <p>
	 * ��������:2017-7-8
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uap_lhcalcday";
	}    
    
    /**
	  * ����Ĭ�Ϸ�ʽ����������.
	  *
	  * ��������:2017-7-8
	  */
     public CalcdayVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.ic.lhcalcday.CalcdayVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("lhprj.lhcalcday");
		
   	}
     
}