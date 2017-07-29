package nc.vo.to.businessinfo.entity;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.to.pub.model.vo.AbstractCuteBBVO;

public class BusinessinfoBBVO extends AbstractCuteBBVO {
  // ѯ�ۺ�۸��Ƿ�ɸ�
  public static final String BMODIFYPRICEFLAG = "bmodifypriceflag";

  // �Ƿ���Ҫѯ��
  public static final String BNEEDASKPRICE = "bneedaskprice";

  // ѯ����Ϻ��Ƿ���Ҫ����ת��
  public static final String BNEEDCONVERTCURR = "bneedconvertcurr";

  // ����ó��
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  // �ڲ�������Ϣ���ӱ�����
  public static final String CBUSINESS_BBID = "cbusiness_bbid";

  // �ڲ�������Ϣ�ӱ�����
  public static final String CBUSINESS_BID = "cbusiness_bid";

  // �ڲ�������Ϣ��������
  public static final String CBUSINESSID = "cbusinessid";

  // ��������
  public static final String CCURRENCYID = "ccurrencyid";

  // ���γɱ�������
  public static final String CDOWNCOSTREGIONID = "cdowncostregionid";

  // ���β�����֯����
  public static final String CDOWNFINANCEORGID = "cdownfinanceorgid";

  // ���β�����֯�汾����
  public static final String CDOWNFINANCEORGVID = "cdownfinanceorgvid";

  // �ջ�����/����
  public static final String CINCOUNTRYID = "cincountryid";

  // �ɹ�����˰����/����
  public static final String CINTAXCOUNTRYID = "cintaxcountryid";

  // ���ױ�������
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  // ��������/����
  public static final String COUTCOUNTRYID = "coutcountryid";

  // ����·���к�
  public static final String CPATHROWNO = "cpathrowno";

  // ����·���ӱ�����
  public static final String CSETTLEPATH_BID = "csettlepath_bid";

  // ˰��
  public static final String CTAXCODEID = "ctaxcodeid";

  // ��˰����/����
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  // ���γɱ�������
  public static final String CUPCOSTREGIONID = "cupcostregionid";

  // ���β�����֯����
  public static final String CUPFINANCEORGID = "cupfinanceorgid";

  // ���β�����֯�汾����
  public static final String CUPFINANCEORGVID = "cupfinanceorgvid";

  // ��������
  public static final String FBUYSELLFLAG = "fbuysellflag";

  // ��˰���
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  // �Ӽ���
  public static final String NADDPRICERATE = "naddpricerate";
  
  //�̶��Ӽ۶� gwj
  public static final String NADDPRICEVALUE="naddpricevalue";

  // ���ۼ�����λ���ױ���ѯ����˰��
  public static final String NASKQTORIGPRICE = "naskqtorigprice";

  // ���ۼ�����λ���ױ���ѯ�ۺ�˰��
  public static final String NASKQTORIGTAXPRICE = "naskqtorigtaxprice";

  // ��˰���
  public static final String NCALTAXMNY = "ncaltaxmny";

  // �ۿ���
  public static final String NDISCOUNTRATE = "ndiscountrate";
  
  // �ۿ۶�
  public static final String NDISCOUNTVALUE = "ndiscountvalue";

  // ����
  public static final String NEXCHANGERATE = "nexchangerate";

  // ������˰���
  public static final String NMNY = "nmny";

  // ����λ���ױ�����˰����
  public static final String NORIGPRICE = "norigprice";

  // ����λ���ױ��ֺ�˰����
  public static final String NORIGTAXPRICE = "norigtaxprice";

  // ��������˰����
  public static final String NPRICE = "nprice";

  // ���ۼ�����λ���ױ�����˰���
  public static final String NQTORIGMNY = "nqtorigmny";

  // ���ۼ�����λ���ױ�����˰��
  public static final String NQTORIGPRICE = "nqtorigprice";

  // ���ۼ�����λ���ױ��ּ�˰�ϼ�
  public static final String NQTORIGTAXMNY = "nqtorigtaxmny";

  // ���ۼ�����λ���ױ��ֺ�˰��
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";
  
  //���ۼ�����λ���ױ��ֺ�˰��
  public static final String NQTORIGTAXPRICENEW = "nqtorigtaxpricenew";

  // ���۱�����˰����
  public static final String NQTPRICE = "nqtprice";

  // ���۱��Һ�˰����
  public static final String NQTTAXPRICE = "nqttaxprice";

  // ˰��
  public static final String NTAX = "ntax";

  // ���Ҽ�˰�ϼ�
  public static final String NTAXMNY = "ntaxmny";

  // �����Һ�˰����
  public static final String NTAXPRICE = "ntaxprice";

  // ˰��
  public static final String NTAXRATE = "ntaxrate";

  // ����
  public static final String PK_GROUP = "pk_group";

  private static final long serialVersionUID = 7206275845086321294L;

  // ʱ���
  public static final String TS = "ts";

  // ѯ�۹���
  public static final String VPRICERULE = "vpricerule";

  private UFBoolean bneedaskprice;

  private String csettlerule_bid;

  private UFDouble num;

  private String vpricerule;

  public UFBoolean getBmodifypriceflag() {
    return (UFBoolean) this
        .getAttributeValue(BusinessinfoBBVO.BMODIFYPRICEFLAG);
  }

  public UFBoolean getBneedaskprice() {
    return this.bneedaskprice;
  }

  public UFBoolean getBneedconvertcurr() {
    return (UFBoolean) this
        .getAttributeValue(BusinessinfoBBVO.BNEEDCONVERTCURR);
  }

  @Override
  public IAttributeMeta getBodyForeignKeyMeta() {
    return this.getMetaData().getAttribute(BusinessinfoBBVO.CBUSINESS_BID);
  }

  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(BusinessinfoBBVO.BTRIATRADEFLAG);
  }

  public String getCbusiness_bbid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CBUSINESS_BBID);
  }

  public String getCbusiness_bid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CBUSINESS_BID);
  }

  public String getCbusinessid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CBUSINESSID);
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CCURRENCYID);
  }

  public String getCdowncostregionid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CDOWNCOSTREGIONID);
  }

  public String getCdownfinanceorgid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CDOWNFINANCEORGID);
  }

  public String getCdownfinanceorgvid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CDOWNFINANCEORGVID);
  }

  @Override
  public String getChildPrimaryKey() {
    return this.getCbusiness_bid();
  }

  public String getCincountryid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CINCOUNTRYID);
  }

  public String getCintaxcountryid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CINTAXCOUNTRYID);
  }

  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CORIGCURRENCYID);
  }

  public String getCoutcountryid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.COUTCOUNTRYID);
  }

  public String getCpathrowno() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CPATHROWNO);
  }

  public String getCsettlepath_bid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CSETTLEPATH_BID);
  }

  public String getCsettlerule_bid() {
    return this.csettlerule_bid;
  }

  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CTAXCODEID);
  }

  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CTAXCOUNTRYID);
  }

  public String getCupcostregionid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CUPCOSTREGIONID);
  }

  public String getCupfinanceorgid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CUPFINANCEORGID);
  }

  public String getCupfinanceorgvid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CUPFINANCEORGVID);
  }

  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(BusinessinfoBBVO.FBUYSELLFLAG);
  }

  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(BusinessinfoBBVO.FTAXTYPEFLAG);
  }

  @Override
  public String getHeaderPrimaryKey() {
    return this.getCbusinessid();
  }

  @Override
  public IAttributeMeta getHeadForeignKeyMeta() {
    return this.getMetaData().getAttribute(BusinessinfoBBVO.CBUSINESSID);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta("to.to_businessinfo_bb");
    return meta;
  }

  public UFDouble getNaddpricerate() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NADDPRICERATE);
  }
  
  public UFDouble getNaddpricevalue(){
	  return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NADDPRICEVALUE);
  }

  public UFDouble getNaskqtorigprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NASKQTORIGPRICE);
  }

  public UFDouble getNaskqtorigtaxprice() {
    return (UFDouble) this
        .getAttributeValue(BusinessinfoBBVO.NASKQTORIGTAXPRICE);
  }

  public UFDouble getNassistnum() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBVO.NASSISTNUM);
  }

  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NCALTAXMNY);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NDISCOUNTRATE);
  }
  
  public UFDouble getNdiscountvalue() {
	    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NDISCOUNTVALUE);
  }

  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NEXCHANGERATE);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NMNY);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBVO.NORIGPRICE);
  }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBVO.NORIGTAXPRICE);
  }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NPRICE);
  }

  public UFDouble getNqtorigmny() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NQTORIGMNY);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NQTORIGPRICE);
  }

  public UFDouble getNqtorigtaxmny() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NQTORIGTAXMNY);
  }

  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NQTORIGTAXPRICE);
  }
  
  public UFDouble getNqtorigtaxpricenew() {
	    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NQTORIGTAXPRICENEW);
  }

  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NQTPRICE);
  }

  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NQTTAXPRICE);
  }

  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NTAX);
  }

  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NTAXMNY);
  }

  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(BusinessinfoBBVO.NTAXRATE);
  }

  public UFDouble getNum() {
    return this.num;
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.PK_GROUP);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(BusinessinfoBBVO.TS);
  }

  public String getVpricerule() {
    return this.vpricerule;
  }

  public void setBmodifypriceflag(UFBoolean bmodifypriceflag) {
    this.setAttributeValue(BusinessinfoBBVO.BMODIFYPRICEFLAG, bmodifypriceflag);
  }

  public void setBneedaskprice(UFBoolean bneedaskprice) {
    this.bneedaskprice = bneedaskprice;
  }

  public void setBneedconvertcurr(UFBoolean bneedconvertcurr) {
    this.setAttributeValue(BusinessinfoBBVO.BNEEDCONVERTCURR, bneedconvertcurr);
  }

  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(BusinessinfoBBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setCbusiness_bbid(String cbusiness_bbid) {
    this.setAttributeValue(BusinessinfoBBVO.CBUSINESS_BBID, cbusiness_bbid);
  }

  public void setCbusiness_bid(String cbusiness_bid) {
    this.setAttributeValue(BusinessinfoBBVO.CBUSINESS_BID, cbusiness_bid);
  }

  public void setCbusinessid(String cbusinessid) {
    this.setAttributeValue(BusinessinfoBBVO.CBUSINESSID, cbusinessid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(BusinessinfoBBVO.CCURRENCYID, ccurrencyid);
  }

  public void setCdowncostregionid(String cdowncostregionid) {
    this.setAttributeValue(BusinessinfoBBVO.CDOWNCOSTREGIONID,
        cdowncostregionid);
  }

  public void setCdownfinanceorgid(String cdownfinanceorgid) {
    this.setAttributeValue(BusinessinfoBBVO.CDOWNFINANCEORGID,
        cdownfinanceorgid);
  }

  public void setCdownfinanceorgvid(String cdownfinanceorgvid) {
    this.setAttributeValue(BusinessinfoBBVO.CDOWNFINANCEORGVID,
        cdownfinanceorgvid);
  }

  @Override
  public void setChildPrimaryKey(String childPrimaryKey) {
    this.setCbusiness_bid(childPrimaryKey);

  }

  public void setCincountryid(String cincountryid) {
    this.setAttributeValue(BusinessinfoBBVO.CINCOUNTRYID, cincountryid);
  }

  public void setCintaxcountryid(String cintaxcountryid) {
    this.setAttributeValue(BusinessinfoBBVO.CINTAXCOUNTRYID, cintaxcountryid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(BusinessinfoBBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCoutcountryid(String coutcountryid) {
    this.setAttributeValue(BusinessinfoBBVO.COUTCOUNTRYID, coutcountryid);
  }

  public void setCpathrowno(String cpathrowno) {
    this.setAttributeValue(BusinessinfoBBVO.CPATHROWNO, cpathrowno);
  }

  public void setCsettlepath_bid(String csettlepath_bid) {
    this.setAttributeValue(BusinessinfoBBVO.CSETTLEPATH_BID, csettlepath_bid);
  }

  public void setCsettlerule_bid(String csettlerule_bid) {
    this.csettlerule_bid = csettlerule_bid;
  }

  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(BusinessinfoBBVO.CTAXCODEID, ctaxcodeid);
  }

  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(BusinessinfoBBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  public void setCupcostregionid(String cupcostregionid) {
    this.setAttributeValue(BusinessinfoBBVO.CUPCOSTREGIONID, cupcostregionid);
  }

  public void setCupfinanceorgid(String cupfinanceorgid) {
    this.setAttributeValue(BusinessinfoBBVO.CUPFINANCEORGID, cupfinanceorgid);
  }

  public void setCupfinanceorgvid(String cupfinanceorgvid) {
    this.setAttributeValue(BusinessinfoBBVO.CUPFINANCEORGVID, cupfinanceorgvid);
  }

  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(BusinessinfoBBVO.FBUYSELLFLAG, fbuysellflag);
  }

  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(BusinessinfoBBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  @Override
  public void setHeaderPrimaryKey(String headerPrimaryKey) {
    this.setCbusinessid(headerPrimaryKey);
  }

  public void setNaddpricerate(UFDouble naddpricerate) {
    this.setAttributeValue(BusinessinfoBBVO.NADDPRICERATE, naddpricerate);
  }
  
  public void setNaddpricevalue(UFDouble naddpricevalue){
	  this.setAttributeValue(BusinessinfoBBVO.NADDPRICEVALUE, naddpricevalue);
  }

  public void setNaskqtorigprice(UFDouble naskqtorigprice) {
    this.setAttributeValue(BusinessinfoBBVO.NASKQTORIGPRICE, naskqtorigprice);
  }

  public void setNaskqtorigtaxprice(UFDouble naskqtorigtaxprice) {
    this.setAttributeValue(BusinessinfoBBVO.NASKQTORIGTAXPRICE,
        naskqtorigtaxprice);
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(BusinessinfoBBVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(BusinessinfoBBVO.NDISCOUNTRATE, ndiscountrate);
  }
  
  public void setNdiscountvalue(UFDouble ndiscountvalue) {
	    this.setAttributeValue(BusinessinfoBBVO.NDISCOUNTVALUE, ndiscountvalue);
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(BusinessinfoBBVO.NEXCHANGERATE, nexchangerate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(BusinessinfoBBVO.NMNY, nmny);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(BusinessinfoBVO.NORIGPRICE, norigprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(BusinessinfoBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(BusinessinfoBBVO.NPRICE, nprice);
  }

  public void setNqtorigmny(UFDouble nqtorigmny) {
    this.setAttributeValue(BusinessinfoBBVO.NQTORIGMNY, nqtorigmny);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(BusinessinfoBBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxmny(UFDouble nqtorigtaxmny) {
    this.setAttributeValue(BusinessinfoBBVO.NQTORIGTAXMNY, nqtorigtaxmny);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(BusinessinfoBBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }
  
  public void setNqtorigtaxpricenew(UFDouble nqtorigtaxpricenew) {
	    this.setAttributeValue(BusinessinfoBBVO.NQTORIGTAXPRICENEW, nqtorigtaxpricenew);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(BusinessinfoBBVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(BusinessinfoBBVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(BusinessinfoBBVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(BusinessinfoBBVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(BusinessinfoBBVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(BusinessinfoBBVO.NTAXRATE, ntaxrate);
  }

  public void setNum(UFDouble num) {
    this.num = num;
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(BusinessinfoBBVO.PK_GROUP, pk_group);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(BusinessinfoBBVO.TS, ts);
  }

  public void setVpricerule(String vpricerule) {
    this.vpricerule = vpricerule;
  }

}
