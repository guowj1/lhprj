package nc.vo.to.m5f.entity;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.to.m4551.entity.GoldenTaxItemVO;
import nc.vo.to.pub.model.vo.AbstractCuteBBVO;

public class SettleListLineVO extends AbstractCuteBBVO {

  // 是否已传金税
  public static final String BGOLDENTAXFLAG = "bgoldentaxflag";

  // 采购方三角贸易
  public static final String BINTRIATRADEFLAG = "bintriatradeflag";

  // 逆向征税标志
  public static final String BOPPTAXFLAG = "bopptaxflag";

  // 销售方三角贸易
  public static final String BOUTTRIATRADEFLAG = "bouttriatradeflag";

  // 结算清单子表_主键
  public static final String CBILL_BBID = "cbill_bbid";

  // 子表主键
  public static final String CBILL_BID = "cbill_bid";

  // 主表主键
  public static final String CBILLID = "cbillid";

  // 结算币种
  public static final String CCURRENCYID = "ccurrencyid";

  // 客户
  public static final String CCUSTOMERID = "ccustomerid";

  // 下游成本域
  public static final String CDOWNCOSTREGIONID = "cdowncostregionid";

  // 下游财务组织
  public static final String CDOWNFINANCEORGID = "cdownfinanceorgid";

  // 下游财务组织版本
  public static final String CDOWNFINANCEORGVID = "cdownfinanceorgvid";

  // 收货国家/地区
  public static final String CINCOUNTRYID = "cincountryid";

  // 调入方本位币
  public static final String CINCURRENCYID = "cincurrencyid";

  // 调入方税码
  public static final String CINTAXCODEID = "cintaxcodeid";

  // 采购方报税国家/地区
  public static final String CINTAXCOUNTRYID = "cintaxcountryid";

  // 发货国家/地区
  public static final String COUTCOUNTRYID = "coutcountryid";

  // 调出方本位币
  public static final String COUTCURRENCYID = "coutcurrencyid";

  // 销售方报税国家/地区
  public static final String COUTTAXCOUNTRYID = "couttaxcountryid";

  // 行号
  public static final String CROWNO = "crowno";

  // 税码
  public static final String CTAXCODEID = "ctaxcodeid";

  // 上游成本域
  public static final String CUPCOSTREGIONID = "cupcostregionid";

  // 上游财务组织
  public static final String CUPFINANCEORGID = "cupfinanceorgid";

  // 上游财务组织版本
  public static final String CUPFINANCEORGVID = "cupfinanceorgvid";

  // 供应商
  public static final String CVENDORID = "cvendorid";

  // 采购方购销类型
  public static final String FINBUYSELLFLAG = "finbuysellflag";

  // 调入方扣税类别
  public static final String FINTAXTYPEFLAG = "fintaxtypeflag";

  // 销售方购销类型
  public static final String FOUTBUYSELLFLAG = "foutbuysellflag";

  // 结算计税类别
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  // 计税金额
  public static final String NCALTAXMNY = "ncaltaxmny";

  // 结算折扣率
  public static final String NDISCOUNTRATE = "ndiscountrate";
  
  //结算折扣额
  public static final String NDISCOUNTVALUE="ndiscountvalue";

  // 结算币种与原币汇率
  public static final String NEXCHANGERATE = "nexchangerate";

  // 调入方计税金额
  public static final String NINCALTAXMNY = "nincaltaxmny";

  // 调入方折本汇率
  public static final String NINEXCHANGERATE = "ninexchangerate";

  // 调入方主本币无税金额
  public static final String NINMNY = "ninmny";

  // 调入方无税金额
  public static final String NINORIGMNY = "ninorigmny";

  // 调入方主单位原币无税单价
  public static final String NINORIGPRICE = "ninorigprice";

  // 调入方主本币无税单价
  public static final String NINPRICE = "ninprice";

  // 调入方报价单位原币无税单价
  public static final String NINQTORIGPRICE = "ninqtorigprice";

  // 调入方报价本币无税单价
  public static final String NINQTPRICE = "ninqtprice";

  // 调入方本币含税单价
  public static final String NINQTTAXPRICE = "ninqttaxprice";

  // 调入方税额
  public static final String NINTAX = "nintax";

  // 调入方本币价税合计
  public static final String NINTAXMNY = "nintaxmny";

  // 调入方主本币含税单价
  public static final String NINTAXPRICE = "nintaxprice";

  // 调入方税率
  public static final String NINTAXRATE = "nintaxrate";

  // 本币无税金额
  public static final String NMNY = "nmny";

  // 不可抵扣税额
  public static final String NNOSUBTAX = "nnosubtax";

  // 不可抵扣税率
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  // 结算无税金额
  public static final String NORIGMNY = "norigmny";

  // 结算币种无税单价
  public static final String NORIGPRICE = "norigprice";

  // 结算币种报价计量单位无税单价
  public static final String NORIGQTPRICE = "norigqtprice";

  // 结算币种报价计量单位含税单价
  public static final String NORIGQTTAXPRICE = "norigqttaxprice";

  // 结算价税合计
  public static final String NORIGTAXMNY = "norigtaxmny";

  // 结算币种含税单价
  public static final String NORIGTAXPRICE = "norigtaxprice";

  // 主本币无税单价
  public static final String NPRICE = "nprice";

  // 报价本币无税单价
  public static final String NQTPRICE = "nqtprice";

  // 报价本币含税单价
  public static final String NQTTAXPRICE = "nqttaxprice";

  // 回冲应付计税金额
  public static final String NREDAPCALTAXMNY = "nredapcaltaxmny";

  // 回冲应付原币价税合计
  public static final String NREDAPORIGTAXMNY = "nredaporigtaxmny";

  // 回冲应收计税金额
  public static final String NREDARCALTAXMNY = "nredarcaltaxmny";

  // 回冲应收原币价税合计
  public static final String NREDARORIGTAXMNY = "nredarorigtaxmny";

  // 回冲应付无税金额
  public static final String NREDBACKAPMNY = "nredbackapmny";

  // 回冲应付原币无税金额
  public static final String NREDBACKAPORIGMNY = "nredbackaporigmny";

  // 回冲应付税额
  public static final String NREDBACKAPTAX = "nredbackaptax";

  // 回冲应付价税合计
  public static final String NREDBACKAPTAXMNY = "nredbackaptaxmny";

  // 回冲应收无税金额
  public static final String NREDBACKARMNY = "nredbackarmny";

  // 回冲应收原币无税金额
  public static final String NREDBACKARORIGMNY = "nredbackarorigmny";

  // 回冲应收税额
  public static final String NREDBACKARTAX = "nredbackartax";

  // 回冲应收价税合计
  public static final String NREDBACKARTAXMNY = "nredbackartaxmny";

  // 回冲入库成本
  public static final String NREDBACKINMNY = "nredbackinmny";

  // 调入方结算金额
  public static final String NSETTLEINMNY = "nsettleinmny";

  // 税额
  public static final String NTAX = "ntax";

  // 本币价税合计
  public static final String NTAXMNY = "ntaxmny";

  // 主本币含税单价
  public static final String NTAXPRICE = "ntaxprice";

  // 结算税率
  public static final String NTAXRATE = "ntaxrate";

  // pk_group
  public static final String PK_GROUP = "pk_group";

  // 传金税时间
  public static final String TGOLDENTAXTIME = "tgoldentaxtime";

  // 时间戳
  public static final String TS = "ts";

  // 金税税票号
  public static final String VGOLDENTAXCODE = "vgoldentaxcode";

  // 调入方注册VAT注册码
  public static final String VINVATCODE = "vinvatcode";

  // 调出方注册VAT注册码
  public static final String VOUTVATCODE = "voutvatcode";

  // 虚拟结算清单编号
  public static final String VVIRTUALCODE = "vvirtualcode";

  private static final long serialVersionUID = -1105309565664072601L;

  public UFBoolean getBgoldentaxflag() {
    return (UFBoolean) this.getAttributeValue(SettleListLineVO.BGOLDENTAXFLAG);
  }

  public UFBoolean getBintriatradeflag() {
    return (UFBoolean) this
        .getAttributeValue(SettleListLineVO.BINTRIATRADEFLAG);
  }

  @Override
  public IAttributeMeta getBodyForeignKeyMeta() {
    // TODO 自动生成方法存根
    return this.getMetaData().getAttribute(SettleListLineVO.CBILL_BID);
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

  public String getCcustomerid() {
    return (String) this.getAttributeValue(SettleListLineVO.CCUSTOMERID);
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

  @Override
  public String getChildPrimaryKey() {
    // TODO 自动生成方法存根
    return this.getCbill_bid();
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

  public String getCupcostregionid() {
    return (String) this.getAttributeValue(SettleListLineVO.CUPCOSTREGIONID);
  }

  public String getCupfinanceorgid() {
    return (String) this.getAttributeValue(SettleListLineVO.CUPFINANCEORGID);
  }

  public String getCupfinanceorgvid() {
    return (String) this.getAttributeValue(SettleListLineVO.CUPFINANCEORGVID);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(SettleListLineVO.CVENDORID);
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

  @Override
  public String getHeaderPrimaryKey() {
    // TODO 自动生成方法存根
    return this.getCbillid();
  }

  @Override
  public IAttributeMeta getHeadForeignKeyMeta() {
    // TODO 自动生成方法存根
    return this.getMetaData().getAttribute(SettleListLineVO.CBILLID);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("to.to_settlelist_bb");
    return meta;
  }

  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NCALTAXMNY);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NDISCOUNTRATE);
  }
  
  public UFDouble getNdiscountvalue(){
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

  public UFDouble getNredapcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDAPCALTAXMNY);
  }

  public UFDouble getNredaporigtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDAPORIGTAXMNY);
  }

  public UFDouble getNredarcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDARCALTAXMNY);
  }

  public UFDouble getNredarorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDARORIGTAXMNY);
  }

  public UFDouble getNredbackapmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDBACKAPMNY);
  }

  public UFDouble getNredbackaporigmny() {
    return (UFDouble) this
        .getAttributeValue(SettleListLineVO.NREDBACKAPORIGMNY);
  }

  public UFDouble getNredbackaptax() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDBACKAPTAX);
  }

  public UFDouble getNredbackaptaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDBACKAPTAXMNY);
  }

  public UFDouble getNredbackarmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDBACKARMNY);
  }

  public UFDouble getNredbackarorigmny() {
    return (UFDouble) this
        .getAttributeValue(SettleListLineVO.NREDBACKARORIGMNY);
  }

  public UFDouble getNredbackartax() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDBACKARTAX);
  }

  public UFDouble getNredbackartaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDBACKARTAXMNY);
  }

  public UFDouble getNredbackinmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NREDBACKINMNY);
  }

  public UFDouble getNsettleinmny() {
    return (UFDouble) this.getAttributeValue(SettleListLineVO.NSETTLEINMNY);
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

  public UFDate getTgoldentaxtime() {
    return (UFDate) this.getAttributeValue(SettleListLineVO.TGOLDENTAXTIME);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleListLineVO.TS);
  }

  public String getVgoldentaxcode() {
    return (String) this.getAttributeValue(SettleListLineVO.VGOLDENTAXCODE);
  }

  public String getVinvatcode() {
    return (String) this.getAttributeValue(SettleListLineVO.VINVATCODE);
  }

  public String getVoutvatcode() {
    return (String) this.getAttributeValue(SettleListLineVO.VOUTVATCODE);
  }

  public String getVvirtualcode() {
    return (String) this.getAttributeValue(SettleListLineVO.VVIRTUALCODE);
  }

  public void setBgoldentaxflag(UFBoolean bgoldentaxflag) {
    this.setAttributeValue(SettleListLineVO.BGOLDENTAXFLAG, bgoldentaxflag);
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

  public void setCcustomerid(String ccustomerid) {
    this.setAttributeValue(SettleListLineVO.CCUSTOMERID, ccustomerid);
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

  @Override
  public void setChildPrimaryKey(String childPrimaryKey) {
    // TODO 自动生成方法存根
    this.setCbill_bid(childPrimaryKey);
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

  public void setCupcostregionid(String cupcostregionid) {
    this.setAttributeValue(SettleListLineVO.CUPCOSTREGIONID, cupcostregionid);
  }

  public void setCupfinanceorgid(String cupfinanceorgid) {
    this.setAttributeValue(SettleListLineVO.CUPFINANCEORGID, cupfinanceorgid);
  }

  public void setCupfinanceorgvid(String cupfinanceorgvid) {
    this.setAttributeValue(SettleListLineVO.CUPFINANCEORGVID, cupfinanceorgvid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SettleListLineVO.CVENDORID, cvendorid);
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

  @Override
  public void setHeaderPrimaryKey(String headerPrimaryKey) {
    // TODO 自动生成方法存根
    this.setCbillid(headerPrimaryKey);
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SettleListLineVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SettleListLineVO.NDISCOUNTRATE, ndiscountrate);
  }
  
  public void setNdiscountvalue(UFDouble ndiscountvalue){
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

  public void setNredapcaltaxmny(UFDouble nredapcaltaxmny) {
    this.setAttributeValue(SettleListLineVO.NREDAPCALTAXMNY, nredapcaltaxmny);
  }

  public void setNredaporigtaxmny(UFDouble nredaporigtaxmny) {
    this.setAttributeValue(SettleListLineVO.NREDAPORIGTAXMNY, nredaporigtaxmny);
  }

  public void setNredarcaltaxmny(UFDouble nredarcaltaxmny) {
    this.setAttributeValue(SettleListLineVO.NREDARCALTAXMNY, nredarcaltaxmny);
  }

  public void setNredarorigtaxmny(UFDouble nredarorigtaxmny) {
    this.setAttributeValue(SettleListLineVO.NREDARORIGTAXMNY, nredarorigtaxmny);
  }

  public void setNredbackapmny(UFDouble nredbackapmny) {
    this.setAttributeValue(SettleListLineVO.NREDBACKAPMNY, nredbackapmny);
  }

  public void setNredbackaporigmny(UFDouble nredbackaporigmny) {
    this.setAttributeValue(SettleListLineVO.NREDBACKAPORIGMNY,
        nredbackaporigmny);
  }

  public void setNredbackaptax(UFDouble nredbackaptax) {
    this.setAttributeValue(SettleListLineVO.NREDBACKAPTAX, nredbackaptax);
  }

  public void setNredbackaptaxmny(UFDouble nredbackaptaxmny) {
    this.setAttributeValue(SettleListLineVO.NREDBACKAPTAXMNY, nredbackaptaxmny);
  }

  public void setNredbackarmny(UFDouble nredbackarmny) {
    this.setAttributeValue(SettleListLineVO.NREDBACKARMNY, nredbackarmny);
  }

  public void setNredbackarorigmny(UFDouble nredbackarorigmny) {
    this.setAttributeValue(SettleListLineVO.NREDBACKARORIGMNY,
        nredbackarorigmny);
  }

  public void setNredbackartax(UFDouble nredbackartax) {
    this.setAttributeValue(SettleListLineVO.NREDBACKARTAX, nredbackartax);
  }

  public void setNredbackartaxmny(UFDouble nredbackartaxmny) {
    this.setAttributeValue(SettleListLineVO.NREDBACKARTAXMNY, nredbackartaxmny);
  }

  public void setNredbackinmny(UFDouble nredbackinmny) {
    this.setAttributeValue(SettleListLineVO.NREDBACKINMNY, nredbackinmny);
  }

  public void setNsettleinmny(UFDouble nsettleinmny) {
    this.setAttributeValue(SettleListLineVO.NSETTLEINMNY, nsettleinmny);
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

  public void setTgoldentaxtime(UFDate tgoldentaxtime) {
    this.setAttributeValue(SettleListLineVO.TGOLDENTAXTIME, tgoldentaxtime);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleListLineVO.TS, ts);
  }

  public void setVgoldentaxcode(String vgoldentaxcode) {
    this.setAttributeValue(SettleListLineVO.VGOLDENTAXCODE, vgoldentaxcode);
  }

  public void setVinvatcode(String vinvatcode) {
    this.setAttributeValue(SettleListLineVO.VINVATCODE, vinvatcode);
  }

  public void setVoutvatcode(String voutvatcode) {
    this.setAttributeValue(SettleListLineVO.VOUTVATCODE, voutvatcode);
  }

  public void setVvirtualcode(String vvirtualcode) {
		this.setAttributeValue(SettleListLineVO.VVIRTUALCODE, vvirtualcode);
	}
}
