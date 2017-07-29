package nc.vo.to.askprice;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * ѯ��VO��ʹ��ѯ��ʱ���ܴ�ʲô�ֶξʹ�ʲô�ֶΣ����ܾ����ܵ�ѯ����ͬ�ļ۸� ��û�е��ֶο���
 */
public class AskPriceVO extends SuperVO {

  public static final String BILLDATE = "billdate";

  public static final String CGATHERBID = "cgatherbid";

  public static final String CITEMKEY = "citemkey";

  public static final String CQUOTEUNITID = "cquoteunitid";

  public static final String CRECEIVEAREAID = "creceiveareaid";

  public static final String CTRANSTYPE = "ctranstype";

  public static final String CUNITID = "cunitid";

  public static final String CURR = "curr";

  public static final String CUSTOMERID = "customerid";

  public static final String DESTORIGCURR = "destOrigCurr";

  public static final String FESTIMATEPRICERULEFLAG = "festimatepriceruleflag";

  public static final String FPRICERULEFLAG = "fpriceruleflag";

  public static final String FSETTLETYPE = "fsettletype";

  public static final String HASPRICE = "hasPrice";

  public static final String INCB = "incb";

  public static final String INCOSTREGION = "incostregion";

  public static final String INFINANCEORG = "infinanceorg";

  public static final String INVENTORYID = "inventoryid";

  public static final String INVENTORYVID = "inventoryvid";

  public static final String INWH = "inwh";

  public static final String NADDPRICERATE = "naddpricerate";
  //gwj �̶��Ӽ۶�
  public static final String NADDPRICEVALUE = "naddpricevalue";

  public static final String NEGATIVE = "negative";

  public static final String NEXCHANGERATE = "nexchangerate";

  public static final String NQTNUM = "nqtnum";

  public static final String NTAXRATE = "ntaxrate";

  public static final String ORDERBID = "orderbid";

  public static final String ORDERTYPE = "ordertype";

  public static final String OUTBID = "outbid";

  public static final String INBID = "inbid";

  public static final String INID = "inid";

  public static final String OUTCB = "outcb";

  // ����ѯ����ɱ���˵�����ڶ��ڿ���֯�ĵ������ⵥ�䵥���ϴ洢���Ǵ�������������ӱ�ID
  // �����������outbid��˵����洢���ǿ�浥���ӱ�ID�������Ļ� ���ݸ�ֵ����ѯ��������ɱ�������Զ�鲻����Ӧ�ļ۸�
  // ��˶���ѯ��������ɱ���˵����ȡ��һ��ID
  public static final String OUTCOSTBID = "outcostbid";

  public static final String OUTCOSTID = "outcostid";

  public static final String OUTCOSTREGION = "outcostregion";

  public static final String OUTFINANCEORG = "outfinanceorg";

  public static final String OUTID = "outid";

  public static final String OUTWH = "outwh";

  public static final String PK_BATCHCODE = "pk_batchcode";

  public static final String PK_GROUP = "pk_group";

  public static final String PK_ORG = "pk_org";

  public static final String PK_ORG_TYPE = "pk_org_type";

  // ѯ������֯�ǿ����֯
  public static final Integer PK_ORG_TYPE_CALBODY = Integer.valueOf(1);

  // ѯ������֯�ǲ�����֯
  public static final Integer PK_ORG_TYPE_FINANCE = Integer.valueOf(0);

  // ѯ���۵ļ۸����
  public static final String PRICEGETRULE = "pricegetrule";

  // ���������ID
  public static final String SETTLERULEBID = "settlerulebid";

  // �������ID
  public static final String SETTLERULEID = "settleruleid";

  public static final String TAXFIRST = "taxfirst";

  public static final String VBATCHCODE = "vbatchcode";

  public static final String VFREE1 = "vfree1";

  public static final String VFREE10 = "vfree10";

  public static final String VFREE2 = "vfree2";

  public static final String VFREE3 = "vfree3";

  public static final String VFREE4 = "vfree4";

  public static final String VFREE5 = "vfree5";

  public static final String VFREE6 = "vfree6";

  public static final String VFREE7 = "vfree7";

  public static final String VFREE8 = "vfree8";

  public static final String VFREE9 = "vfree9";

  /**
   * ��Ŀ
   */
  public static final String CPROJECTID = "cprojectid";

  /**
   * �ͻ�
   */
  public static final String CASSCUSTID = "casscustid";

  /**
   * ��Ӧ��
   */
  public static final String CVENDORID = "cvendorid";

  /**
   * ��������
   */
  public static final String CPRODUCTORID = "cproductorid";

  /**
   * ������
   */
  public static final String CFFILEID = "cffileid";

  public static final String VUNITCHANGERATE = "vunitchangerate";

  private static final long serialVersionUID = -4578804574060437570L;

  // ���ڣ�ȡ������
  private UFDate billdate;

  // ֻ�н����嵥����
  private String cgatherbid;

  // ѯ����������֤Ψһ����
  private String citemkey;

  // ���ۼ�����λ
  private String cquoteunitid;

  // �ջ�����
  private String creceiveareaid;

  // ���䷽ʽ
  private String csendtypeid;

  // ���������ID
  private String csettlerulebid;

  // �������ID
  private String csettleruleid;

  // �������ͣ�ƥ��������ʹ��
  private String ctranstype;

  // ��������λ û�еĻ����Լ���
  private String cunitid;

  // ԭ�ҵ�ǰ����
  private String curr;

  // customerid�ͻ�id�����ջ����Ϊ��ʱ���������ҿͻ����������ã�
  private String customerid;

  // ԭ��Ŀ�ı���
  private String destOrigCurr;

  // �ݹ�ȡ�۹���
  private String festimatepriceruleflag;

  // ȡ�۹���
  private String fpriceruleflag;

  // �ڲ��������ͣ�ƥ��������ʹ��
  private Integer fsettletype;

  // ����
  private UFBoolean hasPrice;

  // �۸��Ƿ�˰
  private boolean hastax;

  // �������֯
  private String incb;

  // ���뷽�ɱ���
  private String incostregion;

  // ��������֯��ƥ��������ʹ��
  private String infinanceorg;

  // ���
  private String inventoryid;

  // ���
  private String inventoryvid;

  // ���ֿ�
  private String inwh;

  // �Ӽ���
  private UFDouble naddpricerate;
  
  //�̶��Ӽ۶�
  private UFDouble naddpricevalue;

  // �Ƿ��������ƥ��������ʹ��
  private UFBoolean negative = UFBoolean.FALSE;

  // �۱����ʣ������һ�ȡ
  private UFDouble nexchangerate;

  // ԭ�Ҽ۸񣨣�
  private UFDouble nprice;

  // ��������:ȡ�������߼�ʱ����
  private UFDouble nqtnum;

  // ˰��
  private UFDouble ntaxrate;

  // ����id
  private String orderbid;

  // ��������
  private String ordertype;

  // ���ⵥ�ӱ�id
  private String outbid;

  // ��������֯
  private String outcb;

  // ��ⵥid
  private String inid;

  // ��ⵥbid
  private String inbid;

  private String outcostbid;

  private String outcostid;

  // �������ɱ���
  private String outcostregion;

  // ���������֯��ƥ��������ʹ��
  private String outfinanceorg;

  // ���ⵥ����id
  private String outid;

  // ����ֿ�
  private String outwh;

  // ����
  private String pk_batchcode;

  // ���ţ�ƥ��������ʹ��
  private String pk_group;

  // ����֯
  private String pk_org;

  // ����֯����
  private Integer pk_org_type;

  // ʵ��ȡ���۵Ĺ���
  private Integer pricegetrule;

  // ��˰����,���ô������ռ���ȡ�ڲ����ײ���
  private boolean taxfirst = true;

  // ����
  private String vbatchcode;

  // ������1
  private String vfree1;

  // ������10
  private String vfree10;

  // ������2
  private String vfree2;

  // ������3
  private String vfree3;

  // ������4
  private String vfree4;

  // ������5
  private String vfree5;

  // ������6
  private String vfree6;

  // ������7
  private String vfree7;

  // ������8
  private String vfree8;

  // ������9
  private String vfree9;

  /**
   * ��Ŀ
   */
  private String cprojectid;

  /**
   * �ͻ�
   */
  private String casscustid;

  private String cvendorid;

  /**
   * ��������
   */
  private String cproductorid;

  /**
   * ������
   */
  private String cffileid;

  // ����λ�����۵�λ������ û�еĻ��һ�ȡ
  private String vunitchangerate;

  @Override
  public boolean equals(Object vo) {
    AssertUtils.assertValue(vo != null, nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4009005_0", "04009005-0011")/*
                                                                 * @res
                                                                 * "ѯ��VO�Ƚϲ�������!"
                                                                 */);
    AssertUtils.assertValue(
        vo instanceof AskPriceVO,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4009005_0",
            "04009005-0011")/* @res "ѯ��VO�Ƚϲ�������!" */);
    AskPriceVO askvo = (AskPriceVO) vo;
    return PubAppTool.isEqual(askvo == null ? null : askvo.getCitemkey(),
        this.getCitemkey());
  }

  public String getCprojectid() {
    return this.cprojectid;
  }

  public String getCasscustid() {
    return this.casscustid;
  }

  public String getCvendorid() {
    return this.cvendorid;
  }

  public String getCproductorid() {
    return this.cproductorid;
  }

  public String getCffileid() {
    return this.cffileid;
  }

  public UFDate getBilldate() {
    return this.billdate;
  }

  public String getCgatherbid() {
    return this.cgatherbid;
  }

  public String getCitemkey() {
    return this.citemkey;
  }

  public String getCquoteunitid() {
    return this.cquoteunitid;
  }

  public String getCreceiveareaid() {
    return this.creceiveareaid;
  }

  public String getCsendtypeid() {
    return this.csendtypeid;
  }

  public String getCsettlerulebid() {
    return this.csettlerulebid;
  }

  public String getCsettleruleid() {
    return this.csettleruleid;
  }

  public String getCtranstype() {
    return this.ctranstype;
  }

  public String getCunitid() {
    return this.cunitid;
  }

  public String getCurr() {
    return this.curr;
  }

  public String getCustomerid() {
    return this.customerid;
  }

  public String getDestOrigCurr() {
    return this.destOrigCurr;
  }

  public String getFestimatepriceruleflag() {
    return this.festimatepriceruleflag;
  }

  public String getFpriceruleflag() {
    return this.fpriceruleflag;
  }

  public Integer getFsettletype() {
    return this.fsettletype;
  }

  public UFBoolean getHasPrice() {
    return this.hasPrice;
  }

  public boolean getHastax() {
    return this.hastax;
  }

  public String getIncb() {
    return this.incb;
  }

  public String getIncostregion() {
    return this.incostregion;
  }

  public String getInfinanceorg() {
    return this.infinanceorg;
  }

  public String getInventoryid() {
    return this.inventoryid;
  }

  public String getInventoryvid() {
    return this.inventoryvid;
  }

  public String getInwh() {
    return this.inwh;
  }

  public UFDouble getNaddpricerate() {
    return this.naddpricerate;
  }
  
  public UFDouble getNaddpricevalue(){
	  return this.naddpricevalue;
  }

  public UFBoolean getNegative() {
    return this.negative;
  }

  public UFDouble getNexchangerate() {
    return this.nexchangerate;
  }

  public UFDouble getNprice() {
    return this.nprice;
  }

  public UFDouble getNqtnum() {
    return this.nqtnum;
  }

  public UFDouble getNtaxrate() {
    return this.ntaxrate;
  }

  public String getOrderbid() {
    return this.orderbid;
  }

  public String getOrdertype() {
    return this.ordertype;
  }

  public String getOutbid() {
    return this.outbid;
  }

  public String getInid() {
    return this.inid;
  }

  public String getInbid() {
    return this.inbid;
  }

  public String getOutcb() {
    return this.outcb;
  }

  public String getOutcostbid() {
    return this.outcostbid;
  }

  public String getOutcostid() {
    return this.outcostid;
  }

  public String getOutcostregion() {
    return this.outcostregion;
  }

  public String getOutfinanceorg() {
    return this.outfinanceorg;
  }

  public String getOutid() {
    return this.outid;
  }

  public String getOutwh() {
    return this.outwh;
  }

  public String getPk_batchcode() {
    return this.pk_batchcode;
  }

  public String getPk_group() {
    return this.pk_group;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  public Integer getPk_org_type() {
    return this.pk_org_type;
  }

  public Integer getPricegetrule() {
    return this.pricegetrule;
  }

  public String getVbatchcode() {
    return this.vbatchcode;
  }

  public String getVfree1() {
    return this.vfree1;
  }

  public String getVfree10() {
    return this.vfree10;
  }

  public String getVfree2() {
    return this.vfree2;
  }

  public String getVfree3() {
    return this.vfree3;
  }

  public String getVfree4() {
    return this.vfree4;
  }

  public String getVfree5() {
    return this.vfree5;
  }

  public String getVfree6() {
    return this.vfree6;
  }

  public String getVfree7() {
    return this.vfree7;
  }

  public String getVfree8() {
    return this.vfree8;
  }

  public String getVfree9() {
    return this.vfree9;
  }

  public String getVunitchangerate() {
    return this.vunitchangerate;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public boolean isTaxfirst() {
    return this.taxfirst;
  }

  public void setCprojectid(String cprojectid) {
    this.cprojectid = cprojectid;
  }

  public void setCasscustid(String casscustid) {
    this.casscustid = casscustid;
  }

  public void setCvendorid(String cvendorid) {
    this.cvendorid = cvendorid;
  }

  public void setCproductorid(String cproductorid) {
    this.cproductorid = cproductorid;
  }

  public void setCffileid(String cffileid) {
    this.cffileid = cffileid;
  }

  public void setBilldate(UFDate billdate) {
    this.billdate = billdate;
  }

  public void setCgatherbid(String cgatherbid) {
    this.cgatherbid = cgatherbid;
  }

  public void setCitemkey(String citemkey) {
    this.citemkey = citemkey;
  }

  public void setCquoteunitid(String cquoteunitid) {
    this.cquoteunitid = cquoteunitid;
  }

  public void setCreceiveareaid(String creceiveareaid) {
    this.creceiveareaid = creceiveareaid;
  }

  public void setCsendtypeid(String csendtypeid) {
    this.csendtypeid = csendtypeid;
  }

  public void setCsettlerulebid(String csettlerulebid) {
    this.csettlerulebid = csettlerulebid;
  }

  public void setCsettleruleid(String settleruleid) {
    this.csettleruleid = settleruleid;
  }

  public void setCtranstype(String ctranstype) {
    this.ctranstype = ctranstype;
  }

  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }

  public void setCurr(String curr) {
    this.curr = curr;
  }

  public void setCustomerid(String customerid) {
    this.customerid = customerid;
  }

  public void setDestOrigCurr(String destorigCurr) {
    this.destOrigCurr = destorigCurr;
  }

  public void setFestimatepriceruleflag(String festimatepriceruleflag) {
    this.festimatepriceruleflag = festimatepriceruleflag;
  }

  public void setFpriceruleflag(String fpriceruleflag) {
    this.fpriceruleflag = fpriceruleflag;
  }

  public void setFsettletype(Integer fsettletype) {
    this.fsettletype = fsettletype;
  }

  public void setHasPrice(UFBoolean hasPrice) {
    this.hasPrice = hasPrice;
  }

  public void setHastax(boolean hastax) {
    this.hastax = hastax;
  }

  public void setIncb(String incb) {
    this.incb = incb;
  }

  public void setIncostregion(String costregion) {
    this.incostregion = costregion;
  }

  public void setInfinanceorg(String infinanceorg) {
    this.infinanceorg = infinanceorg;
  }

  public void setInventoryid(String inventoryid) {
    this.inventoryid = inventoryid;
  }

  public void setInventoryvid(String inventoryvid) {
    this.inventoryvid = inventoryvid;
  }

  public void setInwh(String inwh) {
    this.inwh = inwh;
  }

  public void setNaddpricerate(UFDouble naddpricerate) {
    this.naddpricerate = naddpricerate;
  }
  
  public void setNaddpricevalue(UFDouble naddpricevalue){
	  this.naddpricevalue=naddpricevalue;
  }

  public void setNegative(UFBoolean negative) {
    this.negative = negative;
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.nexchangerate = nexchangerate;
  }

  public void setNprice(UFDouble nprice) {
    this.nprice = nprice;
  }

  public void setNqtnum(UFDouble nqtnum) {
    this.nqtnum = nqtnum;
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.ntaxrate = ntaxrate;
  }

  public void setOrderbid(String orderbid) {
    this.orderbid = orderbid;
  }

  public void setOrdertype(String ordertype) {
    this.ordertype = ordertype;
  }

  public void setOutbid(String outbid) {
    this.outbid = outbid;
  }

  public void setInid(String inid) {
    this.inid = inid;
  }

  public void setInbid(String inbid) {
    this.inbid = inbid;
  }

  public void setOutcb(String outcb) {
    this.outcb = outcb;
  }

  public void setOutcostbid(String value) {
    this.outcostbid = value;
  }

  public void setOutcostid(String value) {
    this.outcostid = value;
  }

  public void setOutcostregion(String costregion) {
    this.outcostregion = costregion;
  }

  public void setOutfinanceorg(String outfinanceorg) {
    this.outfinanceorg = outfinanceorg;
  }

  public void setOutid(String outid) {
    this.outid = outid;
  }

  public void setOutwh(String outwh) {
    this.outwh = outwh;
  }

  public void setPk_batchcode(String pk_batchcode) {
    this.pk_batchcode = pk_batchcode;
  }

  public void setPk_group(String pkGroup) {
    this.pk_group = pkGroup;
  }

  public void setPk_org(String pkOrg) {
    this.pk_org = pkOrg;
  }

  public void setPk_org_type(Integer pkOrgType) {
    this.pk_org_type = pkOrgType;
  }

  public void setPricegetrule(Integer pricegetrule) {
    this.pricegetrule = pricegetrule;
  }

  public void setTaxfirst(boolean taxfirst) {
    this.taxfirst = taxfirst;
  }

  public void setVbatchcode(String vbatchcode) {
    this.vbatchcode = vbatchcode;
  }

  public void setVfree1(String vfree1) {
    this.vfree1 = vfree1;
  }

  public void setVfree10(String vfree10) {
    this.vfree10 = vfree10;
  }

  public void setVfree2(String vfree2) {
    this.vfree2 = vfree2;
  }

  public void setVfree3(String vfree3) {
    this.vfree3 = vfree3;
  }

  public void setVfree4(String vfree4) {
    this.vfree4 = vfree4;
  }

  public void setVfree5(String vfree5) {
    this.vfree5 = vfree5;
  }

  public void setVfree6(String vfree6) {
    this.vfree6 = vfree6;
  }

  public void setVfree7(String vfree7) {
    this.vfree7 = vfree7;
  }

  public void setVfree8(String vfree8) {
    this.vfree8 = vfree8;
  }

  public void setVfree9(String vfree9) {
    this.vfree9 = vfree9;
  }

  public void setVunitchangerate(String vunitchangerate) {
		this.vunitchangerate = vunitchangerate;
	}
}
