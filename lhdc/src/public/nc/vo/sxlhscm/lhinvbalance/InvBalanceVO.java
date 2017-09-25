package nc.vo.sxlhscm.lhinvbalance;

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
	 * ���� pk_invbalance��Getter����.������������
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_invbalance () {
		return pk_invbalance;
	}   
	/**
	 * ����pk_invbalance��Setter����.������������
	 * ��������:2017-7-8
	 * @param newPk_invbalance java.lang.String
	 */
	public void setPk_invbalance (java.lang.String newPk_invbalance ) {
	 	this.pk_invbalance = newPk_invbalance;
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
	 * ���� pk_org_v��Getter����.�������������֯���°汾
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v () {
		return pk_org_v;
	}   
	/**
	 * ����pk_org_v��Setter����.�������������֯���°汾
	 * ��������:2017-7-8
	 * @param newPk_org_v java.lang.String
	 */
	public void setPk_org_v (java.lang.String newPk_org_v ) {
	 	this.pk_org_v = newPk_org_v;
	} 	 
	
	/**
	 * ���� pk_stordoc��Getter����.���������ֿ�
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_stordoc () {
		return pk_stordoc;
	}   
	/**
	 * ����pk_stordoc��Setter����.���������ֿ�
	 * ��������:2017-7-8
	 * @param newPk_stordoc java.lang.String
	 */
	public void setPk_stordoc (java.lang.String newPk_stordoc ) {
	 	this.pk_stordoc = newPk_stordoc;
	} 	 
	
	/**
	 * ���� def1��Getter����.���������ֳ�
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getDef1 () {
		return def1;
	}   
	/**
	 * ����def1��Setter����.���������ֳ�
	 * ��������:2017-7-8
	 * @param newDef1 java.lang.String
	 */
	public void setDef1 (java.lang.String newDef1 ) {
	 	this.def1 = newDef1;
	} 	 
	
	/**
	 * ���� dbilldate��Getter����.���������������
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getDbilldate () {
		return dbilldate;
	}   
	/**
	 * ����dbilldate��Setter����.���������������
	 * ��������:2017-7-8
	 * @param newDbilldate nc.vo.pub.lang.UFDate
	 */
	public void setDbilldate (nc.vo.pub.lang.UFDate newDbilldate ) {
	 	this.dbilldate = newDbilldate;
	} 	 
	
	/**
	 * ���� pk_material��Getter����.������������
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getPk_material () {
		return pk_material;
	}   
	/**
	 * ����pk_material��Setter����.������������
	 * ��������:2017-7-8
	 * @param newPk_material java.lang.String
	 */
	public void setPk_material (java.lang.String newPk_material ) {
	 	this.pk_material = newPk_material;
	} 	 
	
	/**
	 * ���� bnum��Getter����.���������������
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getBnum () {
		return bnum;
	}   
	/**
	 * ����bnum��Setter����.���������������
	 * ��������:2017-7-8
	 * @param newBnum nc.vo.pub.lang.UFDouble
	 */
	public void setBnum (nc.vo.pub.lang.UFDouble newBnum ) {
	 	this.bnum = newBnum;
	} 	 
	
	/**
	 * ���� bnprice��Getter����.����������浥��
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getBnprice () {
		return bnprice;
	}   
	/**
	 * ����bnprice��Setter����.����������浥��
	 * ��������:2017-7-8
	 * @param newBnprice nc.vo.pub.lang.UFDouble
	 */
	public void setBnprice (nc.vo.pub.lang.UFDouble newBnprice ) {
	 	this.bnprice = newBnprice;
	} 	 
	
	/**
	 * ���� bmny��Getter����.�������������
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getBmny () {
		return bmny;
	}   
	/**
	 * ����bmny��Setter����.�������������
	 * ��������:2017-7-8
	 * @param newBmny nc.vo.pub.lang.UFDouble
	 */
	public void setBmny (nc.vo.pub.lang.UFDouble newBmny ) {
	 	this.bmny = newBmny;
	} 	 
	
	/**
	 * ���� yldef1��Getter����.��������Ԥ���ֶ�1
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef1 () {
		return yldef1;
	}   
	/**
	 * ����yldef1��Setter����.��������Ԥ���ֶ�1
	 * ��������:2017-7-8
	 * @param newYldef1 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef1 (nc.vo.pub.lang.UFDouble newYldef1 ) {
	 	this.yldef1 = newYldef1;
	} 	 
	
	/**
	 * ���� yldef2��Getter����.��������Ԥ���ֶ�2
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef2 () {
		return yldef2;
	}   
	/**
	 * ����yldef2��Setter����.��������Ԥ���ֶ�2
	 * ��������:2017-7-8
	 * @param newYldef2 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef2 (nc.vo.pub.lang.UFDouble newYldef2 ) {
	 	this.yldef2 = newYldef2;
	} 	 
	
	/**
	 * ���� yldef5��Getter����.��������Ԥ���ֶ�5
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef5 () {
		return yldef5;
	}   
	/**
	 * ����yldef5��Setter����.��������Ԥ���ֶ�5
	 * ��������:2017-7-8
	 * @param newYldef5 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef5 (nc.vo.pub.lang.UFDouble newYldef5 ) {
	 	this.yldef5 = newYldef5;
	} 	 
	
	/**
	 * ���� yldef4��Getter����.��������Ԥ���ֶ�4
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef4 () {
		return yldef4;
	}   
	/**
	 * ����yldef4��Setter����.��������Ԥ���ֶ�4
	 * ��������:2017-7-8
	 * @param newYldef4 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef4 (nc.vo.pub.lang.UFDouble newYldef4 ) {
	 	this.yldef4 = newYldef4;
	} 	 
	
	/**
	 * ���� yldef3��Getter����.��������Ԥ���ֶ�3
	 *  ��������:2017-7-8
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getYldef3 () {
		return yldef3;
	}   
	/**
	 * ����yldef3��Setter����.��������Ԥ���ֶ�3
	 * ��������:2017-7-8
	 * @param newYldef3 nc.vo.pub.lang.UFDouble
	 */
	public void setYldef3 (nc.vo.pub.lang.UFDouble newYldef3 ) {
	 	this.yldef3 = newYldef3;
	} 	 
	
	/**
	 * ���� yldef6��Getter����.��������Ԥ���ֶ�6
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef6 () {
		return yldef6;
	}   
	/**
	 * ����yldef6��Setter����.��������Ԥ���ֶ�6
	 * ��������:2017-7-8
	 * @param newYldef6 java.lang.String
	 */
	public void setYldef6 (java.lang.String newYldef6 ) {
	 	this.yldef6 = newYldef6;
	} 	 
	
	/**
	 * ���� yldef7��Getter����.��������Ԥ���ֶ�7
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef7 () {
		return yldef7;
	}   
	/**
	 * ����yldef7��Setter����.��������Ԥ���ֶ�7
	 * ��������:2017-7-8
	 * @param newYldef7 java.lang.String
	 */
	public void setYldef7 (java.lang.String newYldef7 ) {
	 	this.yldef7 = newYldef7;
	} 	 
	
	/**
	 * ���� yldef8��Getter����.��������Ԥ���ֶ�8
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef8 () {
		return yldef8;
	}   
	/**
	 * ����yldef8��Setter����.��������Ԥ���ֶ�8
	 * ��������:2017-7-8
	 * @param newYldef8 java.lang.String
	 */
	public void setYldef8 (java.lang.String newYldef8 ) {
	 	this.yldef8 = newYldef8;
	} 	 
	
	/**
	 * ���� yldef9��Getter����.��������Ԥ���ֶ�9
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef9 () {
		return yldef9;
	}   
	/**
	 * ����yldef9��Setter����.��������Ԥ���ֶ�9
	 * ��������:2017-7-8
	 * @param newYldef9 java.lang.String
	 */
	public void setYldef9 (java.lang.String newYldef9 ) {
	 	this.yldef9 = newYldef9;
	} 	 
	
	/**
	 * ���� yldef10��Getter����.��������Ԥ���ֶ�10
	 *  ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getYldef10 () {
		return yldef10;
	}   
	/**
	 * ����yldef10��Setter����.��������Ԥ���ֶ�10
	 * ��������:2017-7-8
	 * @param newYldef10 java.lang.String
	 */
	public void setYldef10 (java.lang.String newYldef10 ) {
	 	this.yldef10 = newYldef10;
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
			
		return "pk_invbalance";
	}
    
	/**
	 * <p>���ر�����
	 * <p>
	 * ��������:2017-7-8
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uap_invbalance";
	}    
	
	/**
	 * <p>���ر�����.
	 * <p>
	 * ��������:2017-7-8
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uap_invbalance";
	}    
    
    /**
	  * ����Ĭ�Ϸ�ʽ����������.
	  *
	  * ��������:2017-7-8
	  */
     public InvBalanceVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.sxlhscm.lhinvbalance.InvBalanceVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("lhprj.lhinvbalance");
		
   	}
     
}