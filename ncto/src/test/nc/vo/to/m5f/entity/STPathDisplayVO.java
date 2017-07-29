package nc.vo.to.m5f.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.SuperVO;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.to.m4551.entity.GoldenTaxItemVO;

public class STPathDisplayVO extends SuperVO {

  public static final String NNUMBER = "nnumber";

  private static final long serialVersionUID = -5200699616282971016L;

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

  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(SettleListLineVO.BOPPTAXFLAG);
  }

  public UFBoolean getBouttriatradeflag() {
    return (UFBoolean) this
        .getAttributeValue(SettleListLineVO.BOUTTRIATRADEFLAG);
  }

  public String getCbill_bbid() {
    return (String) this.getAttributeValue(SettleListLineVO.CBILL_BBID);
  }

  public String getCbill_bid() {
    return (String) this.getAttributeValue(SettleListLineVO.CBILL_BID);
  }

  public String getCbillid() {
    return (String) this.getAttributeValue(SettleListLineVO.CBILLID);
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SettleListLineVO.CCURRENCYID);
  }

  public String getCdowncostregionid() {
    return (String) this.getAttributeValue(SettleListLineVO.CDOWNCOSTREGIONID);
  }

  public String getCdownfinanceorgid() {
    return (String) this.getAttributeValue(SettleListLineVO.CDOWNFINANCEORGID);
  }

  public String getCdownfinanceorgvid() {
    return (String) this.getAttributeValue(SettleListLineVO.CDOWNFINANCEORGVID);
  }

  public String getCincountryid() {
    return (String) this.getAttributeValue(SettleListLineVO.CINCOUNTRYID);
  }

  public String getCincurrencyid() {
    return (String) this.getAttributeValue(SettleListLineVO.CINCURRENCYID);
  }

  public String getCintaxcodeid() {
    return (String) this.getAttributeValue(SettleListLineVO.CINTAXCODEID);
  }

  public String getCintaxcountryid() {
    return (String) this.getAttributeValue(SettleListLineVO.CINTAXCOUNTRYID);
  }

  public String getCoutcountryid() {
    return (String) this.getAttributeValue(SettleListLineVO.COUTCOUNTRYID);
  }

  public String getCoutcurrencyid() {
    return (String) this.getAttributeValue(SettleListLineVO.COUTCURRENCYID);
  }

  public String getCouttaxcountryid() {
    return (String) this.getAttributeValue(SettleListLineVO.COUTTAXCOUNTRYID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SettleListLineVO.CROWNO);
  }

  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SettleListLineVO.CTAXCODEID);
  }

  public String getCtradewordid() {
    return (String) this.getAttributeValue(SettleListHeaderVO.CTRADEWORDID);
  }

  public String getCupcostregionid() {
    return (String) this.getAttributeValue(SettleListLineVO.CUPCOSTREGIONID);
  }

  public String getCupfinanceorgid() {
    return (String) this.getAttributeValue(SettleListLineVO.CUPFINANCEORGID);
  }

  public String getCupfinanceorgvid() {
    return (String) this.getAttributeValue(SettleListLineVO.CUPFINANCEORGVID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SettleListHeaderVO.DBILLDATE);
  }

  @Override
  public String getEntityName() {
    return STPathDisplayVO.class.getName();
  }

  public Integer getFinbuysellflag() {
    return (Integer) this.getAttributeValue(SettleListLineVO.FINBUYSELLFLAG);
  }

  public Integer getFintaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListLineVO.FINTAXTYPEFLAG);
  }

  public Integer getFoutbuysellflag() {
    return (Integer) this.getAttributeValue(SettleListLineVO.FOUTBUYSELLFLAG);
  }

  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListLineVO.FTAXTYPEFLAG);
  }

  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NCALTAXMNY);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NDISCOUNTRATE);
  }
  
	public UFDouble getNdiscountvalue() {
		return (UFDouble) this.getAttributeValue(SettleListLineVO.NDISCOUNTVALUE);
	}

  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NEXCHANGERATE);
  }

  public UFDouble getNincaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINCALTAXMNY);
  }

  public UFDouble getNinexchangerate() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINEXCHANGERATE);
  }

  public UFDouble getNinmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINMNY);
  }

  public UFDouble getNinorigmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINORIGMNY);
  }

  public UFDouble getNinorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINORIGPRICE);
  }

  public UFDouble getNinprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINPRICE);
  }

  public UFDouble getNinqtorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINQTORIGPRICE);
  }

  public UFDouble getNinqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINQTPRICE);
  }

  public UFDouble getNinqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINQTTAXPRICE);
  }

  public UFDouble getNintax() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINTAX);
  }

  public UFDouble getNintaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINTAXMNY);
  }

  public UFDouble getNintaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NINTAXPRICE);
  }

  public UFDouble getNintaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NINTAXRATE);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NMNY);
  }

  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NNOSUBTAX);
  }

  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NNOSUBTAXRATE);
  }

  public UFDouble getNnumber() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NNUMBER);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NORIGMNY);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NORIGPRICE);
  }

  public UFDouble getNorigqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NORIGQTPRICE);
  }

  public UFDouble getNorigqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NORIGQTTAXPRICE);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NORIGTAXPRICE);
  }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NPRICE);
  }

  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NQTPRICE);
  }

  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NQTTAXPRICE);
  }

  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NTAX);
  }

  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NTAXMNY);
  }

  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NTAXRATE);
  }

  public String getPkGroup() {
    return (String) this.getAttributeValue(GoldenTaxItemVO.PK_GROUP);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleListLineVO.TS);
  }

  public String getVinvatcode() {
    return (String) this.getAttributeValue(SettleListLineVO.VINVATCODE);
  }

  public String getVoutvatcode() {
    return (String) this.getAttributeValue(SettleListLineVO.VOUTVATCODE);
  }

  @Override
  public void setAttributeValue(String attributeName, Object value) {
    this.valueIndex.put(attributeName, value);
  }

  public void setBintriatradeflag(UFBoolean bintriatradeflag) {
    this.setAttributeValue(SettleListLineVO.BINTRIATRADEFLAG, bintriatradeflag);
  }

  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(SettleListLineVO.BOPPTAXFLAG, bopptaxflag);
  }

  public void setBouttriatradeflag(UFBoolean bouttriatradeflag) {
    this.setAttributeValue(SettleListLineVO.BOUTTRIATRADEFLAG,
        bouttriatradeflag);
  }

  public void setCbill_bbid(String cbill_bbid) {
    this.setAttributeValue(SettleListLineVO.CBILL_BBID, cbill_bbid);
  }

  public void setCbill_bid(String cbill_bid) {
    this.setAttributeValue(SettleListLineVO.CBILL_BID, cbill_bid);
  }

  public void setCbillid(String cbillid) {
    this.setAttributeValue(SettleListLineVO.CBILLID, cbillid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SettleListLineVO.CCURRENCYID, ccurrencyid);
  }

  public void setCdowncostregionid(String cdowncostregionid) {
    this.setAttributeValue(SettleListLineVO.CDOWNCOSTREGIONID,
        cdowncostregionid);
  }

  public void setCdownfinanceorgid(String cdownfinanceorgid) {
    this.setAttributeValue(SettleListLineVO.CDOWNFINANCEORGID,
        cdownfinanceorgid);
  }

  public void setCdownfinanceorgvid(String cdownfinanceorgvid) {
    this.setAttributeValue(SettleListLineVO.CDOWNFINANCEORGVID,
        cdownfinanceorgvid);
  }

  public void setCincountryid(String cincountryid) {
    this.setAttributeValue(SettleListLineVO.CINCOUNTRYID, cincountryid);
  }

  public void setCincurrencyid(String vsrctype) {
    this.setAttributeValue(SettleListLineVO.CINCURRENCYID, vsrctype);
  }

  public void setCintaxcodeid(String cintaxcodeid) {
    this.setAttributeValue(SettleListLineVO.CINTAXCODEID, cintaxcodeid);
  }

  public void setCintaxcountryid(String cintaxcountryid) {
    this.setAttributeValue(SettleListLineVO.CINTAXCOUNTRYID, cintaxcountryid);
  }

  public void setCoutcountryid(String coutcountryid) {
    this.setAttributeValue(SettleListLineVO.COUTCOUNTRYID, coutcountryid);
  }

  public void setCoutcurrencyid(String vsrctype) {
    this.setAttributeValue(SettleListLineVO.COUTCURRENCYID, vsrctype);
  }

  public void setCouttaxcountryid(String couttaxcountryid) {
    this.setAttributeValue(SettleListLineVO.COUTTAXCOUNTRYID, couttaxcountryid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SettleListLineVO.CROWNO, crowno);
  }

  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SettleListLineVO.CTAXCODEID, ctaxcodeid);
  }

  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(SettleListHeaderVO.CTRADEWORDID, ctradewordid);
  }

  public void setCupcostregionid(String cupcostregionid) {
    this.setAttributeValue(SettleListLineVO.CUPCOSTREGIONID, cupcostregionid);
  }

  public void setCupfinanceorgid(String cupfinanceorgid) {
    this.setAttributeValue(SettleListLineVO.CUPFINANCEORGID, cupfinanceorgid);
  }

  public void setCupfinanceorgvid(String cupfinanceorgvid) {
    this.setAttributeValue(SettleListLineVO.CUPFINANCEORGVID, cupfinanceorgvid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SettleListHeaderVO.DBILLDATE, dbilldate);
  }

  public void setFinbuysellflag(Integer finbuysellflag) {
    this.setAttributeValue(SettleListLineVO.FINBUYSELLFLAG, finbuysellflag);
  }

  public void setFintaxtypeflag(Integer fintaxtypeflag) {
    this.setAttributeValue(SettleListLineVO.FINTAXTYPEFLAG, fintaxtypeflag);
  }

  public void setFoutbuysellflag(Integer foutbuysellflag) {
    this.setAttributeValue(SettleListLineVO.FOUTBUYSELLFLAG, foutbuysellflag);
  }

  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SettleListLineVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SettleListLineVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SettleListLineVO.NDISCOUNTRATE, ndiscountrate);
  }
  
	public void setNdiscountvalue(UFDouble ndiscountvalue) {
		this.setAttributeValue(SettleListLineVO.NDISCOUNTVALUE, ndiscountvalue);
	}

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SettleListLineVO.NEXCHANGERATE, nexchangerate);
  }

  public void setNincaltaxmny(UFDouble nincaltaxmny) {
    this.setAttributeValue(SettleListLineVO.NINCALTAXMNY, nincaltaxmny);
  }

  public void setNinexchangerate(UFDouble ninexchangerate) {
    this.setAttributeValue(SettleListLineVO.NINEXCHANGERATE, ninexchangerate);
  }

  public void setNinmny(UFDouble ninmny) {
    this.setAttributeValue(SettleListLineVO.NINMNY, ninmny);
  }

  public void setNinorigmny(UFDouble ninorigmny) {
    this.setAttributeValue(SettleListLineVO.NINORIGMNY, ninorigmny);
  }

  public void setNinorigprice(UFDouble ninorigprice) {
    this.setAttributeValue(SettleListLineVO.NINORIGPRICE, ninorigprice);
  }

  public void setNinprice(UFDouble ninprice) {
    this.setAttributeValue(SettleListLineVO.NINPRICE, ninprice);
  }

  public void setNinqtorigprice(UFDouble ninqtorigprice) {
    this.setAttributeValue(SettleListLineVO.NINQTORIGPRICE, ninqtorigprice);
  }

  public void setNinqtprice(UFDouble ninqtprice) {
    this.setAttributeValue(SettleListLineVO.NINQTPRICE, ninqtprice);
  }

  public void setNinqttaxprice(UFDouble vsrctype) {
    this.setAttributeValue(SettleListItemVO.NINQTTAXPRICE, vsrctype);
  }

  public void setNintax(UFDouble nintax) {
    this.setAttributeValue(SettleListLineVO.NINTAX, nintax);
  }

  public void setNintaxmny(UFDouble vsrctype) {
    this.setAttributeValue(SettleListItemVO.NINTAXMNY, vsrctype);
  }

  public void setNintaxprice(UFDouble vsrctype) {
    this.setAttributeValue(SettleListItemVO.NINTAXPRICE, vsrctype);
  }

  public void setNintaxrate(UFDouble nintaxrate) {
    this.setAttributeValue(SettleListLineVO.NINTAXRATE, nintaxrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SettleListLineVO.NMNY, nmny);
  }

  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(SettleListLineVO.NNOSUBTAX, nnosubtax);
  }

  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(SettleListLineVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  public void setNnumber(UFDouble nnumber) {
    this.setAttributeValue(SettleListItemVO.NNUMBER, nnumber);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SettleListLineVO.NORIGMNY, norigmny);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SettleListLineVO.NORIGPRICE, norigprice);
  }

  public void setNorigqtprice(UFDouble norigqtprice) {
    this.setAttributeValue(SettleListLineVO.NORIGQTPRICE, norigqtprice);
  }

  public void setNorigqttaxprice(UFDouble norigqttaxprice) {
    this.setAttributeValue(SettleListLineVO.NORIGQTTAXPRICE, norigqttaxprice);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SettleListLineVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SettleListLineVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SettleListLineVO.NPRICE, nprice);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(SettleListLineVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(SettleListLineVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SettleListLineVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SettleListLineVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SettleListLineVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SettleListLineVO.NTAXRATE, ntaxrate);
  }

  public void setPkGroup(String pk_group) {
    this.setAttributeValue(GoldenTaxItemVO.PK_GROUP, pk_group);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleListLineVO.TS, ts);
  }

  public void setVinvatcode(String vinvatcode) {
    this.setAttributeValue(SettleListLineVO.VINVATCODE, vinvatcode);
  }

  public void setVoutvatcode(String voutvatcode) {
    this.setAttributeValue(SettleListLineVO.VOUTVATCODE, voutvatcode);
  }

  @Override
  public void validate() throws ValidationException {
    return;
  }
}
