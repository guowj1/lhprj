package nc.vo.to.m5f.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.SuperVO;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class STInvDisplayVO extends SuperVO {

  public static final String BB_CBILL_TS = "bb_cbill_ts";

  private static final long serialVersionUID = -9035142828388916401L;

  private Map<String, Object> valueIndex = new HashMap<String, Object>();

  @Override
  public String[] getAttributeNames() {
    String[] names = null;

    Set<String> set = this.valueIndex.keySet();
    if (set.size() == 0) {
      return names;
    }
    names = set.toArray(new String[set.size()]);
    return names;
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    return this.valueIndex.get(attributeName);
  }

  public UFBoolean getBb_bopptaxflag() {
    return (UFBoolean) this.getAttributeValue(SettleListItemVO.BB_BOPPTAXFLAG);
  }

  public String getBb_cbill_bbid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CBILL_BBID);
  }

  public UFDateTime getBb_cbill_ts() {
    return (UFDateTime) this.getAttributeValue(STInvDisplayVO.BB_CBILL_TS);
  }

  public String getBb_ccurrencyid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CCURRENCYID);
  }

  public String getBb_cincurrencyid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CINCURRENCYID);
  }

  public String getBb_cintaxcodeid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CINTAXCODEID);
  }

  public String getBb_coutcurrencyid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_COUTCURRENCYID);
  }

  public String getBb_ctaxcodeid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CTAXCODEID);
  }

  public Integer getBb_fintaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.BB_FINTAXTYPEFLAG);
  }

  public Integer getBb_ftaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.BB_FTAXTYPEFLAG);
  }

  public UFDouble getBb_ncaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NCALTAXMNY);
  }

  public UFDouble getBb_ndiscountrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NDISCOUNTRATE);
  }
  
  //gwj
  public UFDouble getBb_ndiscountvalue() {
	    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NDISCOUNTVALUE);
  }

  public UFDouble getBb_nexchangerate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NEXCHANGERATE);
  }

  public UFDouble getBb_nincaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINCALTAXMNY);
  }

  public UFDouble getBb_ninexchangerate() {
    return (UFDouble) this
        .getAttributeValue(SettleListItemVO.BB_NINEXCHANGERATE);
  }

  public UFDouble getBb_ninmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINMNY);
  }

  public UFDouble getBb_ninorigmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINORIGMNY);
  }

  public UFDouble getBb_ninorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINORIGPRICE);
  }

  public UFDouble getBb_ninprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINPRICE);
  }

  public UFDouble getBb_ninqtorigpric() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINQTORIGPRIC);
  }

  public UFDouble getBb_ninqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINQTPRICE);
  }

  public UFDouble getBb_ninqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINQTTAXPRICE);
  }

  public UFDouble getBb_nintax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAX);
  }

  public UFDouble getBb_nintaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAXMNY);
  }

  public UFDouble getBb_nintaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAXPRICE);
  }

  public UFDouble getBb_nintaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAXRATE);
  }

  public UFDouble getBb_nmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NMNY);
  }

  public UFDouble getBb_nnosubtax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NNOSUBTAX);
  }

  public UFDouble getBb_nnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NNOSUBTAXRATE);
  }

  public UFDouble getBb_norigmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGMNY);
  }

  public UFDouble getBb_norigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGPRICE);
  }

  public UFDouble getBb_norigqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGQTPRICE);
  }

  public UFDouble getBb_norigqttaxprice() {
    return (UFDouble) this
        .getAttributeValue(SettleListItemVO.BB_NORIGQTTAXPRICE);
  }

  public UFDouble getBb_norigtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGTAXMNY);
  }

  public UFDouble getBb_norigtaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGTAXPRICE);
  }

  public UFDouble getBb_nprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NPRICE);
  }

  public UFDouble getBb_nqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NQTPRICE);
  }

  public UFDouble getBb_nqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NQTTAXPRICE);
  }

  public UFDouble getBb_ntax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAX);
  }

  public UFDouble getBb_ntaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAXMNY);
  }

  public UFDouble getBb_ntaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAXPRICE);
  }

  public UFDouble getBb_ntaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAXRATE);
  }

  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(SettleListItemVO.BOPPTAXFLAG);
  }

  public String getCbill_bid() {
    return (String) this.getAttributeValue(SettleListItemVO.CBILL_BID);
  }

  public String getCbillid() {
    return (String) this.getAttributeValue(SettleListItemVO.CBILLID);
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SettleListHeaderVO.CCURRENCYID);
  }

  public String getCincurrencyid() {
    return (String) this.getAttributeValue(SettleListHeaderVO.CINCURRENCYID);
  }

  public String getCintaxcodeid() {
    return (String) this.getAttributeValue(SettleListItemVO.CINTAXCODEID);
  }

  public String getCinventoryid() {
    return (String) this.getAttributeValue(SettleListItemVO.CINVENTORYID);
  }

  public String getCinventoryvid() {
    return (String) this.getAttributeValue(SettleListItemVO.CINVENTORYVID);
  }

  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SettleListHeaderVO.CORIGCURRENCYID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(SettleListItemVO.CQTUNITID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SettleListItemVO.CROWNO);
  }

  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SettleListItemVO.CTAXCODEID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(SettleListItemVO.CUNITID);
  }

  @Override
  public String getEntityName() {
    return STInvDisplayVO.class.getName();
  }

  public Integer getFintaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.FINTAXTYPEFLAG);
  }

  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.FTAXTYPEFLAG);
  }

  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NCALTAXMNY);
  }

  public UFDouble getNincaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINCALTAXMNY);
  }

  public UFDouble getNinmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINMNY);
  }

  public UFDouble getNinorigmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINORIGMNY);
  }

  public UFDouble getNinorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINORIGPRICE);
  }

  public UFDouble getNinprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINPRICE);
  }

  public UFDouble getNinqtorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINQTORIGPRICE);
  }

  public UFDouble getNinqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINQTPRICE);
  }

  public UFDouble getNinqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINQTTAXPRICE);
  }

  public UFDouble getNintax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINTAX);
  }

  public UFDouble getNintaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINTAXMNY);
  }

  public UFDouble getNintaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINTAXPRICE);
  }

  public UFDouble getNintaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINTAXRATE);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NMNY);
  }

  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NNOSUBTAX);
  }

  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NNOSUBTAXRATE);
  }

  public UFDouble getNnumber() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NNUMBER);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NORIGMNY);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NORIGPRICE);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NORIGTAXPRICE);
  }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NPRICE);
  }

  public UFDouble getNqtnum() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NQTNUM);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NQTORIGPRICE);
  }
  
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NQTORIGTAXPRICE);
  }
  
  public UFDouble getNqtorigtaxpricenew() {
	    return (UFDouble) this.getAttributeValue(SettleListItemVO.NQTORIGTAXPRICENEW);
	  }

  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NQTPRICE);
  }

  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NQTTAXPRICE);
  }

  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NTAX);
  }

  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NTAXMNY);
  }

  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NTAXRATE);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SettleListItemVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SettleListItemVO.PK_ORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleListItemVO.TS);
  }

  public String getVqtunitrate() {
    return (String) this.getAttributeValue(SettleListItemVO.VQTUNITRATE);
  }

  public String getVsrccode() {
    return (String) this.getAttributeValue(SettleListItemVO.VSRCCODE);
  }

  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SettleListItemVO.VSRCROWNO);
  }

  public String getVsrctype() {
    return (String) this.getAttributeValue(SettleListItemVO.VSRCTYPE);
  }

  @Override
  public void setAttributeValue(String attributeName, Object value) {
    this.valueIndex.put(attributeName, value);
  }

  public void setBb_bopptaxflag(UFBoolean bb_bopptaxflag) {
    this.setAttributeValue(SettleListItemVO.BB_BOPPTAXFLAG, bb_bopptaxflag);
  }

  public void setBb_cbill_bbid(String bb_cbill_bbid) {
    this.setAttributeValue(SettleListItemVO.BB_CBILL_BBID, bb_cbill_bbid);
  }

  public void setBb_cbill_ts(UFDateTime bb_cbill_ts) {
    this.setAttributeValue(STInvDisplayVO.BB_CBILL_TS, bb_cbill_ts);
  }

  public void setBb_ccurrencyid(String bb_ccurrencyid) {
    this.setAttributeValue(SettleListItemVO.BB_CCURRENCYID, bb_ccurrencyid);
  }

  public void setBb_cincurrencyid(String bb_cincurrencyid) {
    this.setAttributeValue(SettleListItemVO.BB_CINCURRENCYID, bb_cincurrencyid);
  }

  public void setBb_cintaxcodeid(String bb_cintaxcodeid) {
    this.setAttributeValue(SettleListItemVO.BB_CINTAXCODEID, bb_cintaxcodeid);
  }

  public void setBb_coutcurrencyid(String bb_coutcurrencyid) {
    this.setAttributeValue(SettleListItemVO.BB_COUTCURRENCYID,
        bb_coutcurrencyid);
  }

  public void setBb_ctaxcodeid(String bb_ctaxcodeid) {
    this.setAttributeValue(SettleListItemVO.BB_CTAXCODEID, bb_ctaxcodeid);
  }

  public void setBb_fintaxtypeflag(Integer bb_fintaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.BB_FINTAXTYPEFLAG,
        bb_fintaxtypeflag);
  }

  public void setBb_ftaxtypeflag(Integer bb_ftaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.BB_FTAXTYPEFLAG, bb_ftaxtypeflag);
  }

  public void setBb_ncaltaxmny(UFDouble bb_ncaltaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NCALTAXMNY, bb_ncaltaxmny);
  }

  public void setBb_ndiscountrate(UFDouble bb_ndiscountrate) {
    this.setAttributeValue(SettleListItemVO.BB_NDISCOUNTRATE, bb_ndiscountrate);
  }
  
  //gwj
  public void setBb_ndiscountvalue(UFDouble bb_ndiscountvalue) {
	    this.setAttributeValue(SettleListItemVO.BB_NDISCOUNTVALUE, bb_ndiscountvalue);
  }

  public void setBb_nexchangerate(UFDouble bb_nexchangerate) {
    this.setAttributeValue(SettleListItemVO.BB_NEXCHANGERATE, bb_nexchangerate);
  }

  public void setBb_nincaltaxmny(UFDouble bb_nincaltaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINCALTAXMNY, bb_nincaltaxmny);
  }

  public void setBb_ninexchangerate(UFDouble bb_ninexchangerate) {
    this.setAttributeValue(SettleListItemVO.BB_NINEXCHANGERATE,
        bb_ninexchangerate);
  }

  public void setBb_ninmny(UFDouble bb_ninmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINMNY, bb_ninmny);
  }

  public void setBb_ninorigmny(UFDouble bb_ninorigmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINORIGMNY, bb_ninorigmny);
  }

  public void setBb_ninorigprice(UFDouble bb_ninorigprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINORIGPRICE, bb_ninorigprice);
  }

  public void setBb_ninprice(UFDouble bb_ninprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINPRICE, bb_ninprice);
  }

  public void setBb_ninqtorigpric(UFDouble bb_ninqtorigpric) {
    this.setAttributeValue(SettleListItemVO.BB_NINQTORIGPRIC, bb_ninqtorigpric);
  }

  public void setBb_ninqtprice(UFDouble bb_ninqtprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINQTPRICE, bb_ninqtprice);
  }

  public void setBb_ninqttaxprice(UFDouble bb_ninqttaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINQTTAXPRICE, bb_ninqttaxprice);
  }

  public void setBb_nintax(UFDouble bb_nintax) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAX, bb_nintax);
  }

  public void setBb_nintaxmny(UFDouble bb_nintaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAXMNY, bb_nintaxmny);
  }

  public void setBb_nintaxprice(UFDouble bb_nintaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAXPRICE, bb_nintaxprice);
  }

  public void setBb_nintaxrate(UFDouble bb_nintaxrate) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAXRATE, bb_nintaxrate);
  }

  public void setBb_nmny(UFDouble bb_nmny) {
    this.setAttributeValue(SettleListItemVO.BB_NMNY, bb_nmny);
  }

  public void setBb_nnosubtax(UFDouble bb_nnosubtax) {
    this.setAttributeValue(SettleListItemVO.BB_NNOSUBTAX, bb_nnosubtax);
  }

  public void setBb_nnosubtaxrate(UFDouble bb_nnosubtaxrate) {
    this.setAttributeValue(SettleListItemVO.BB_NNOSUBTAXRATE, bb_nnosubtaxrate);
  }

  public void setBb_norigmny(UFDouble bb_norigmny) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGMNY, bb_norigmny);
  }

  public void setBb_norigprice(UFDouble bb_norigprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGPRICE, bb_norigprice);
  }

  public void setBb_norigqtprice(UFDouble bb_norigqtprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGQTPRICE, bb_norigqtprice);
  }

  public void setBb_norigqttaxprice(UFDouble bb_norigqttaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGQTTAXPRICE,
        bb_norigqttaxprice);
  }

  public void setBb_norigtaxmny(UFDouble bb_norigtaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGTAXMNY, bb_norigtaxmny);
  }

  public void setBb_norigtaxprice(UFDouble bb_norigtaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGTAXPRICE, bb_norigtaxprice);
  }

  public void setBb_nprice(UFDouble bb_nprice) {
    this.setAttributeValue(SettleListItemVO.BB_NPRICE, bb_nprice);
  }

  public void setBb_nqtprice(UFDouble bb_nqtprice) {
    this.setAttributeValue(SettleListItemVO.BB_NQTPRICE, bb_nqtprice);
  }

  public void setBb_nqttaxprice(UFDouble bb_nqttaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NQTTAXPRICE, bb_nqttaxprice);
  }

  public void setBb_ntax(UFDouble bb_ntax) {
    this.setAttributeValue(SettleListItemVO.BB_NTAX, bb_ntax);
  }

  public void setBb_ntaxmny(UFDouble bb_ntaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NTAXMNY, bb_ntaxmny);
  }

  public void setBb_ntaxprice(UFDouble bb_ntaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NTAXPRICE, bb_ntaxprice);
  }

  public void setBb_ntaxrate(UFDouble bb_ntaxrate) {
    this.setAttributeValue(SettleListItemVO.BB_NTAXRATE, bb_ntaxrate);
  }

  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(SettleListItemVO.BOPPTAXFLAG, bopptaxflag);
  }

  public void setCbill_bid(String cbill_bid) {
    this.setAttributeValue(SettleListItemVO.CBILL_BID, cbill_bid);
  }

  public void setCbillid(String cbillid) {
    this.setAttributeValue(SettleListItemVO.CBILLID, cbillid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SettleListHeaderVO.CCURRENCYID, ccurrencyid);
  }

  public void setCincurrencyid(String vsrctype) {
    this.setAttributeValue(SettleListHeaderVO.CINCURRENCYID, vsrctype);
  }

  public void setCintaxcodeid(String cintaxcodeid) {
    this.setAttributeValue(SettleListItemVO.CINTAXCODEID, cintaxcodeid);
  }

  public void setCinventoryid(String cinventoryid) {
    this.setAttributeValue(SettleListItemVO.CINVENTORYID, cinventoryid);
  }

  public void setCinventoryvid(String cinventoryvid) {
    this.setAttributeValue(SettleListItemVO.CINVENTORYVID, cinventoryvid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SettleListHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SettleListItemVO.CQTUNITID, cqtunitid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SettleListItemVO.CROWNO, crowno);
  }

  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SettleListItemVO.CTAXCODEID, ctaxcodeid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(SettleListItemVO.CUNITID, cunitid);
  }

  public void setFintaxtypeflag(Integer fintaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.FINTAXTYPEFLAG, fintaxtypeflag);
  }

  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SettleListItemVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNincaltaxmny(UFDouble nincaltaxmny) {
    this.setAttributeValue(SettleListItemVO.NINCALTAXMNY, nincaltaxmny);
  }

  public void setNinmny(UFDouble ninmny) {
    this.setAttributeValue(SettleListItemVO.NINMNY, ninmny);
  }

  public void setNinorigmny(UFDouble ninorigmny) {
    this.setAttributeValue(SettleListItemVO.NINORIGMNY, ninorigmny);
  }

  public void setNinorigprice(UFDouble ninorigprice) {
    this.setAttributeValue(SettleListItemVO.NINORIGPRICE, ninorigprice);
  }

  public void setNinprice(UFDouble ninprice) {
    this.setAttributeValue(SettleListItemVO.NINPRICE, ninprice);
  }

  public void setNinqtorigprice(UFDouble ninqtorigprice) {
    this.setAttributeValue(SettleListItemVO.NINQTORIGPRICE, ninqtorigprice);
  }

  public void setNinqtprice(UFDouble ninqtprice) {
    this.setAttributeValue(SettleListItemVO.NINQTPRICE, ninqtprice);
  }

  public void setNinqttaxprice(UFDouble vsrctype) {
    this.setAttributeValue(SettleListItemVO.NINQTTAXPRICE, vsrctype);
  }

  public void setNintax(UFDouble nintax) {
    this.setAttributeValue(SettleListItemVO.NINTAX, nintax);
  }

  public void setNintaxmny(UFDouble vsrctype) {
    this.setAttributeValue(SettleListItemVO.NINTAXMNY, vsrctype);
  }

  public void setNintaxprice(UFDouble vsrctype) {
    this.setAttributeValue(SettleListItemVO.NINTAXPRICE, vsrctype);
  }

  public void setNintaxrate(UFDouble nintaxrate) {
    this.setAttributeValue(SettleListItemVO.NINTAXRATE, nintaxrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SettleListItemVO.NMNY, nmny);
  }

  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(SettleListItemVO.NNOSUBTAX, nnosubtax);
  }

  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(SettleListItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  public void setNnumber(UFDouble nnumber) {
    this.setAttributeValue(SettleListItemVO.NNUMBER, nnumber);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SettleListItemVO.NORIGMNY, norigmny);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SettleListItemVO.NORIGPRICE, norigprice);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SettleListItemVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SettleListItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SettleListItemVO.NPRICE, nprice);
  }

  public void setNqtnum(UFDouble nqtnum) {
    this.setAttributeValue(SettleListItemVO.NQTNUM, nqtnum);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(SettleListItemVO.NQTORIGPRICE, nqtorigprice);
  }
  

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(SettleListItemVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }
  
  public void setNqtorigtaxpricenew(UFDouble nqtorigtaxpricenew) {
	    this.setAttributeValue(SettleListItemVO.NQTORIGTAXPRICENEW, nqtorigtaxpricenew);
	  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(SettleListItemVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(SettleListItemVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SettleListItemVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SettleListItemVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SettleListItemVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SettleListItemVO.NTAXRATE, ntaxrate);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SettleListItemVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SettleListItemVO.PK_ORG, pk_org);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleListItemVO.TS, ts);
  }

  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(SettleListItemVO.VQTUNITRATE, vqtunitrate);
  }

  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SettleListItemVO.VSRCCODE, vsrccode);
  }

  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SettleListItemVO.VSRCROWNO, vsrcrowno);
  }

  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SettleListItemVO.VSRCTYPE, vsrctype);
  }

  @Override
  public void validate() throws ValidationException {
    return;
  }

}
