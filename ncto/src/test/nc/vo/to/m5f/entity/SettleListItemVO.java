package nc.vo.to.m5f.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SettleListItemVO extends SuperVO {

  /**
   * bb_bgoldentaxflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_BGOLDENTAXFLAG = "bb_bgoldentaxflag";

  /**
   * bb_bopptaxflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_BOPPTAXFLAG = "bb_bopptaxflag";

  /**
   * bb_cbill_bbid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CBILL_BBID = "bb_cbill_bbid";

  /**
   * bb_ccurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CCURRENCYID = "bb_ccurrencyid";

  /**
   * bb_ccustomerid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CCUSTOMERID = "bb_ccustomerid";

  /**
   * bb_cdowncreid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CDOWNCREID = "bb_cdowncreid";

  /**
   * bb_cdownfiorgid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CDOWNFIORGID = "bb_cdownfiorgid";

  /**
   * bb_cdownfiorgvid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CDOWNFIORGVID = "bb_cdownfiorgvid";

  /**
   * bb_cincurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CINCURRENCYID = "bb_cincurrencyid";

  /**
   * bb_cintaxcodeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CINTAXCODEID = "bb_cintaxcodeid";

  /**
   * bb_coutcurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_COUTCURRENCYID = "bb_coutcurrencyid";

  /**
   * bb_crowno（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CROWNO = "bb_crowno";

  /**
   * bb_ctaxcodeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CTAXCODEID = "bb_ctaxcodeid";

  /**
   * bb_cupcreid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CUPCREID = "bb_cupcreid";

  /**
   * bb_cupfiorgid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CUPFIORGID = "bb_cupfiorgid";

  /**
   * bb_cupfiorgvid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CUPFIORGVID = "bb_cupfiorgvid";

  /**
   * bb_cvendorid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_CVENDORID = "bb_cvendorid";

  /**
   * bb_fintaxtypeflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_FINTAXTYPEFLAG = "bb_fintaxtypeflag";

  /**
   * bb_ftaxtypeflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_FTAXTYPEFLAG = "bb_ftaxtypeflag";

  /**
   * bb_ncaltaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NCALTAXMNY = "bb_ncaltaxmny";

  /**
   * bb_ndiscountrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NDISCOUNTRATE = "bb_ndiscountrate";
  
  /**
   * bb_ndiscountvalue（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NDISCOUNTVALUE = "bb_ndiscountvalue";

  /**
   * bb_nexchangerate（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NEXCHANGERATE = "bb_nexchangerate";

  /**
   * bb_nincaltaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINCALTAXMNY = "bb_nincaltaxmny";

  /**
   * bb_ninexchangerate（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINEXCHANGERATE = "bb_ninexchangerate";

  /**
   * bb_ninmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINMNY = "bb_ninmny";

  /**
   * bb_ninorigmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINORIGMNY = "bb_ninorigmny";

  /**
   * bb_ninorigprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINORIGPRICE = "bb_ninorigprice";

  /**
   * bb_ninprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINPRICE = "bb_ninprice";

  /**
   * bb_ninqtorigpric（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINQTORIGPRIC = "bb_ninqtorigpric";

  /**
   * bb_ninqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINQTPRICE = "bb_ninqtprice";

  /**
   * bb_ninqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINQTTAXPRICE = "bb_ninqttaxprice";

  /**
   * bb_nintax（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINTAX = "bb_nintax";

  /**
   * bb_nintaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINTAXMNY = "bb_nintaxmny";

  /**
   * bb_nintaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINTAXPRICE = "bb_nintaxprice";

  /**
   * bb_nintaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NINTAXRATE = "bb_nintaxrate";

  /**
   * bb_nmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NMNY = "bb_nmny";

  /**
   * bb_nnosubtax（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NNOSUBTAX = "bb_nnosubtax";

  /**
   * bb_nnosubtaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NNOSUBTAXRATE = "bb_nnosubtaxrate";

  /**
   * bb_norigmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NORIGMNY = "bb_norigmny";

  /**
   * bb_norigprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NORIGPRICE = "bb_norigprice";

  /**
   * bb_norigqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NORIGQTPRICE = "bb_norigqtprice";

  /**
   * bb_norigqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NORIGQTTAXPRICE = "bb_norigqttaxprice";

  /**
   * bb_norigtaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NORIGTAXMNY = "bb_norigtaxmny";

  /**
   * bb_norigtaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NORIGTAXPRICE = "bb_norigtaxprice";

  /**
   * bb_nprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NPRICE = "bb_nprice";

  /**
   * bb_nqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NQTPRICE = "bb_nqtprice";

  /**
   * bb_nqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NQTTAXPRICE = "bb_nqttaxprice";

  /**
   * bb_nrbapmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NRBAPMNY = "bb_nrbapmny";

  /**
   * bb_nrbaptax（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NRBAPTAX = "bb_nrbaptax";

  /**
   * bb_nrbaptaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NRBAPTAXMNY = "bb_nrbaptaxmny";

  /**
   * bb_nrbarmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NRBARMNY = "bb_nrbarmny";

  /**
   * bb_nrbartax（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NRBARTAX = "bb_nrbartax";

  /**
   * bb_nrbartaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NRBARTAXMNY = "bb_nrbartaxmny";

  /**
   * bb_nrbinmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NRBINMNY = "bb_nrbinmny";

  /**
   * bb_ntax（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NTAX = "bb_ntax";

  /**
   * bb_ntaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NTAXMNY = "bb_ntaxmny";

  /**
   * bb_ntaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NTAXPRICE = "bb_ntaxprice";

  /**
   * bb_ntaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_NTAXRATE = "bb_ntaxrate";

  /**
   * bb_tgoldentaxtime（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_TGOLDENTAXTIME = "bb_tgoldentaxtime";

  /**
   * bb_vgoldentaxcode（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_VGOLDENTAXCODE = "bb_vgoldentaxcode";

  /**
   * bb_vvirtualcode（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BB_VVIRTUALCODE = "bb_vvirtualcode";

  // 逆向征税标志
  public static final String BOPPTAXFLAG = "bopptaxflag";

  // 客户-固定属性
  public static final String CASSCUSTID = "casscustid";

  // 子表主键
  public static final String CBILL_BID = "cbill_bid";

  // 主表主键
  public static final String CBILLID = "cbillid";

  // 源头单据子表主键
  public static final String CFIRSTBID = "cfirstbid";

  // 源头单据主键
  public static final String CFIRSTID = "cfirstid";

  // 调入来源单据子表主键
  public static final String CINSRCBID = "cinsrcbid";

  // 调入来源单据主键
  public static final String CINSRCID = "cinsrcid";

  // 调入方税码
  public static final String CINTAXCODEID = "cintaxcodeid";

  // 物料主键
  public static final String CINVENTORYID = "cinventoryid";

  // 物料版本主键
  public static final String CINVENTORYVID = "cinventoryvid";

  // 待结算调入单表体主键
  public static final String CIPST_BID = "cipst_bid";

  // 待结算调入单主表主键
  public static final String CIPSTID = "cipstid";

  // 待结算调出单表体主键
  public static final String COPST_BID = "copst_bid";

  // 待结算调出单主表主键
  public static final String COPSTID = "copstid";

  // 生产厂家-固定属性
  public static final String CPRODUCTORID = "cproductorid";

  /**
   *特征码
   */
   public static final String CFFILEID="cffileid";

  // 项目-固定属性
  public static final String CPROJECTID = "cprojectid";

  // 报价计量单位
  public static final String CQTUNITID = "cqtunitid";

  // 行号
  public static final String CROWNO = "crowno";

  // 结算规则子表主键
  public static final String CSETTLERULE_BID = "csettlerule_bid";

  // 结算规则主表主键
  public static final String CSETTLERULEID = "csettleruleid";

  // 来源单据子表主键
  public static final String CSRCBID = "csrcbid";

  // 来源单据主键
  public static final String CSRCID = "csrcid";

  // 来源单据交易类型
  public static final String CSRCTRANTYPE = "csrctrantype";

  // 税码
  public static final String CTAXCODEID = "ctaxcodeid";

  // 主计量单位主键
  public static final String CUNITID = "cunitid";

  // 供应商-固定属性
  public static final String CVENDORID = "cvendorid";

  // 单据日期
  public static final String DBILLDATE = "dbilldate";

  // 源头单据日期
  public static final String DFIRSTBILLDATE = "dfirstbilldate";

  // 调入来源单据日期
  public static final String DINSRCBILLDATE = "dinsrcbilldate";

  // 来源单据日期
  public static final String DSRCBILLDATE = "dsrcbilldate";

  // 调入方扣税类别
  public static final String FINTAXTYPEFLAG = "fintaxtypeflag";

  // 内部结算类型
  public static final String FSETTLETYPEFLAG = "fsettletypeflag";

  // 计税类别
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  // 计税金额
  public static final String NCALTAXMNY = "ncaltaxmny";

  // 全局本币无税金额
  public static final String NGLOBALMNY = "nglobalmny";

  // 全局本币价税合计
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  // 集团本币无税金额
  public static final String NGROUPMNY = "ngroupmny";

  // 集团本币价税合计
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  // 调入方计税金额
  public static final String NINCALTAXMNY = "nincaltaxmny";

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

  // 主数量
  public static final String NNUMBER = "nnumber";

  // 原币无税金额
  public static final String NORIGMNY = "norigmny";

  // 主单位原币无税单价
  public static final String NORIGPRICE = "norigprice";

  // 原币价税合计
  public static final String NORIGTAXMNY = "norigtaxmny";

  // 主单位原币含税单价
  public static final String NORIGTAXPRICE = "norigtaxprice";

  // 主本币无税单价
  public static final String NPRICE = "nprice";

  // 报价计量单位数量
  public static final String NQTNUM = "nqtnum";

  // 报价计量单位原币无税价
  public static final String NQTORIGPRICE = "nqtorigprice";

  // 报价计量单位原币含税价
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  //折扣前价格
  public static final String NQTORIGTAXPRICENEW = "nqtorigtaxpricenew";
 
  // 报价本币无税单价
  public static final String NQTPRICE = "nqtprice";

  // 报价本币含税单价
  public static final String NQTTAXPRICE = "nqttaxprice";

  // 本币税额
  public static final String NTAX = "ntax";

  // 本币价税合计
  public static final String NTAXMNY = "ntaxmny";

  // 主本币含税单价
  public static final String NTAXPRICE = "ntaxprice";

  // 税率
  public static final String NTAXRATE = "ntaxrate";

  // pk_group
  public static final String PK_GROUP = "pk_group";

  // 结算主体财务组织
  public static final String PK_ORG = "pk_org";

  private static final long serialVersionUID = 3702824354460241629L;

  // 时间戳
  public static final String TS = "ts";

  // 批次号
  public static final String VBATCHCODE = "vbatchcode";

  // 自定义项1
  public static final String VBDEF1 = "vbdef1";

  // 自定义项10
  public static final String VBDEF10 = "vbdef10";

  // 自定义项11
  public static final String VBDEF11 = "vbdef11";

  // 自定义项12
  public static final String VBDEF12 = "vbdef12";

  // 自定义项13
  public static final String VBDEF13 = "vbdef13";

  // 自定义项14
  public static final String VBDEF14 = "vbdef14";

  // 自定义项15
  public static final String VBDEF15 = "vbdef15";

  // 自定义项16
  public static final String VBDEF16 = "vbdef16";

  // 自定义项17
  public static final String VBDEF17 = "vbdef17";

  // 自定义项18
  public static final String VBDEF18 = "vbdef18";

  // 自定义项19
  public static final String VBDEF19 = "vbdef19";

  // 自定义项2
  public static final String VBDEF2 = "vbdef2";

  // 自定义项20
  public static final String VBDEF20 = "vbdef20";

  // 自定义项3
  public static final String VBDEF3 = "vbdef3";

  // 自定义项4
  public static final String VBDEF4 = "vbdef4";

  // 自定义项5
  public static final String VBDEF5 = "vbdef5";

  // 自定义项6
  public static final String VBDEF6 = "vbdef6";

  // 自定义项7
  public static final String VBDEF7 = "vbdef7";

  // 自定义项8
  public static final String VBDEF8 = "vbdef8";

  // 自定义项9
  public static final String VBDEF9 = "vbdef9";

  // 单据号
  public static final String VBILLCODE = "vbillcode";

  // 源头单据号
  public static final String VFIRSTCODE = "vfirstcode";

  // 源头单据行号
  public static final String VFIRSTROWNO = "vfirstrowno";

  // 源头单据交易类型
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  // 源头单据类型
  public static final String VFIRSTTYPE = "vfirsttype";

  // 自由辅助属性1
  public static final String VFREE1 = "vfree1";

  // 自由辅助属性10
  public static final String VFREE10 = "vfree10";

  // 自由辅助属性2
  public static final String VFREE2 = "vfree2";

  // 自由辅助属性3
  public static final String VFREE3 = "vfree3";

  // 自由辅助属性4
  public static final String VFREE4 = "vfree4";

  // 自由辅助属性5
  public static final String VFREE5 = "vfree5";

  // 自由辅助属性6
  public static final String VFREE6 = "vfree6";

  // 自由辅助属性7
  public static final String VFREE7 = "vfree7";

  // 自由辅助属性8
  public static final String VFREE8 = "vfree8";

  // 自由辅助属性9
  public static final String VFREE9 = "vfree9";

  // 调入来源单据号
  public static final String VINSRCCODE = "vinsrccode";

  // 调入来源单据行号
  public static final String VINSRCROWNO = "vinsrcrowno";

  // 调入来源单据交易类型
  public static final String VINSRCTRANTYPE = "vinsrctrantype";

  // 调入来源单据类型
  public static final String VINSRCTYPE = "vinsrctype";

  // 报价计量单位换算率
  public static final String VQTUNITRATE = "vqtunitrate";

  // 来源单据号
  public static final String VSRCCODE = "vsrccode";

  // 来源单据行号
  public static final String VSRCROWNO = "vsrcrowno";

  // 来源单据类型
  public static final String VSRCTYPE = "vsrctype";

  private SettleListLineVO[] m_BBVOS;

  private UFDateTime outitemts;

  /**
   * 获取bb_bgoldentaxflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_bgoldentaxflag
   */
  public UFBoolean getBb_bgoldentaxflag() {
    return (UFBoolean) this
        .getAttributeValue(SettleListItemVO.BB_BGOLDENTAXFLAG);
  }

  /**
   * 获取bb_bopptaxflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_bopptaxflag
   */
  public UFBoolean getBb_bopptaxflag() {
    return (UFBoolean) this.getAttributeValue(SettleListItemVO.BB_BOPPTAXFLAG);
  }

  /**
   * 获取bb_cbill_bbid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cbill_bbid
   */
  public String getBb_cbill_bbid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CBILL_BBID);
  }

  /**
   * 获取bb_ccurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ccurrencyid
   */
  public String getBb_ccurrencyid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CCURRENCYID);
  }

  /**
   * 获取bb_ccustomerid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ccustomerid
   */
  public String getBb_ccustomerid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CCUSTOMERID);
  }

  /**
   * 获取bb_cdowncreid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cdowncreid
   */
  public String getBb_cdowncreid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CDOWNCREID);
  }

  /**
   * 获取bb_cdownfiorgid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cdownfiorgid
   */
  public String getBb_cdownfiorgid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CDOWNFIORGID);
  }

  /**
   * 获取bb_cdownfiorgvid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cdownfiorgvid
   */
  public String getBb_cdownfiorgvid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CDOWNFIORGVID);
  }

  /**
   * 获取bb_cincurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cincurrencyid
   */
  public String getBb_cincurrencyid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CINCURRENCYID);
  }

  /**
   * 获取bb_cintaxcodeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cintaxcodeid
   */
  public String getBb_cintaxcodeid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CINTAXCODEID);
  }

  /**
   * 获取bb_coutcurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_coutcurrencyid
   */
  public String getBb_coutcurrencyid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_COUTCURRENCYID);
  }

  /**
   * 获取bb_crowno（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_crowno
   */
  public String getBb_crowno() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CROWNO);
  }

  /**
   * 获取bb_ctaxcodeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ctaxcodeid
   */
  public String getBb_ctaxcodeid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CTAXCODEID);
  }

  /**
   * 获取bb_cupcreid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cupcreid
   */
  public String getBb_cupcreid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CUPCREID);
  }

  /**
   * 获取bb_cupfiorgid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cupfiorgid
   */
  public String getBb_cupfiorgid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CUPFIORGID);
  }

  /**
   * 获取bb_cupfiorgvid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cupfiorgvid
   */
  public String getBb_cupfiorgvid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CUPFIORGVID);
  }

  /**
   * 获取bb_cvendorid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_cvendorid
   */
  public String getBb_cvendorid() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_CVENDORID);
  }

  /**
   * 获取bb_fintaxtypeflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_fintaxtypeflag
   * @see TaxType
   */
  public Integer getBb_fintaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.BB_FINTAXTYPEFLAG);
  }

  /**
   * 获取bb_ftaxtypeflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ftaxtypeflag
   * @see EnumDiscounttaxtype
   */
  public Integer getBb_ftaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.BB_FTAXTYPEFLAG);
  }

  /**
   * 获取bb_ncaltaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ncaltaxmny
   */
  public UFDouble getBb_ncaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NCALTAXMNY);
  }

  /**
   * 获取bb_ndiscountrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ndiscountrate
   */
  public UFDouble getBb_ndiscountrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NDISCOUNTRATE);
  }
  
  /**
   * 获取bb_ndiscountvalue（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ndiscountrate
   */
  public UFDouble getBb_ndiscountvalue() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NDISCOUNTVALUE);
  }

  /**
   * 获取bb_nexchangerate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nexchangerate
   */
  public UFDouble getBb_nexchangerate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NEXCHANGERATE);
  }

  /**
   * 获取bb_nincaltaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nincaltaxmny
   */
  public UFDouble getBb_nincaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINCALTAXMNY);
  }

  /**
   * 获取bb_ninexchangerate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninexchangerate
   */
  public UFDouble getBb_ninexchangerate() {
    return (UFDouble) this
        .getAttributeValue(SettleListItemVO.BB_NINEXCHANGERATE);
  }

  /**
   * 获取bb_ninmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninmny
   */
  public UFDouble getBb_ninmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINMNY);
  }

  /**
   * 获取bb_ninorigmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninorigmny
   */
  public UFDouble getBb_ninorigmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINORIGMNY);
  }

  /**
   * 获取bb_ninorigprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninorigprice
   */
  public UFDouble getBb_ninorigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINORIGPRICE);
  }

  /**
   * 获取bb_ninprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninprice
   */
  public UFDouble getBb_ninprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINPRICE);
  }

  /**
   * 获取bb_ninqtorigpric（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninqtorigpric
   */
  public UFDouble getBb_ninqtorigpric() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINQTORIGPRIC);
  }

  /**
   * 获取bb_ninqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninqtprice
   */
  public UFDouble getBb_ninqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINQTPRICE);
  }

  /**
   * 获取bb_ninqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ninqttaxprice
   */
  public UFDouble getBb_ninqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINQTTAXPRICE);
  }

  /**
   * 获取bb_nintax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nintax
   */
  public UFDouble getBb_nintax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAX);
  }

  /**
   * 获取bb_nintaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nintaxmny
   */
  public UFDouble getBb_nintaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAXMNY);
  }

  /**
   * 获取bb_nintaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nintaxprice
   */
  public UFDouble getBb_nintaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAXPRICE);
  }

  /**
   * 获取bb_nintaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nintaxrate
   */
  public UFDouble getBb_nintaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NINTAXRATE);
  }

  /**
   * 获取bb_nmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nmny
   */
  public UFDouble getBb_nmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NMNY);
  }

  /**
   * 获取bb_nnosubtax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nnosubtax
   */
  public UFDouble getBb_nnosubtax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NNOSUBTAX);
  }

  /**
   * 获取bb_nnosubtaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nnosubtaxrate
   */
  public UFDouble getBb_nnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NNOSUBTAXRATE);
  }

  /**
   * 获取bb_norigmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_norigmny
   */
  public UFDouble getBb_norigmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGMNY);
  }

  /**
   * 获取bb_norigprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_norigprice
   */
  public UFDouble getBb_norigprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGPRICE);
  }

  /**
   * 获取bb_norigqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_norigqtprice
   */
  public UFDouble getBb_norigqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGQTPRICE);
  }

  /**
   * 获取bb_norigqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_norigqttaxprice
   */
  public UFDouble getBb_norigqttaxprice() {
    return (UFDouble) this
        .getAttributeValue(SettleListItemVO.BB_NORIGQTTAXPRICE);
  }

  /**
   * 获取bb_norigtaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_norigtaxmny
   */
  public UFDouble getBb_norigtaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGTAXMNY);
  }

  /**
   * 获取bb_norigtaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_norigtaxprice
   */
  public UFDouble getBb_norigtaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NORIGTAXPRICE);
  }

  /**
   * 获取bb_nprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nprice
   */
  public UFDouble getBb_nprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NPRICE);
  }

  /**
   * 获取bb_nqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nqtprice
   */
  public UFDouble getBb_nqtprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NQTPRICE);
  }

  /**
   * 获取bb_nqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nqttaxprice
   */
  public UFDouble getBb_nqttaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NQTTAXPRICE);
  }

  /**
   * 获取bb_nrbapmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nrbapmny
   */
  public UFDouble getBb_nrbapmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NRBAPMNY);
  }

  /**
   * 获取bb_nrbaptax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nrbaptax
   */
  public UFDouble getBb_nrbaptax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NRBAPTAX);
  }

  /**
   * 获取bb_nrbaptaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nrbaptaxmny
   */
  public UFDouble getBb_nrbaptaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NRBAPTAXMNY);
  }

  /**
   * 获取bb_nrbarmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nrbarmny
   */
  public UFDouble getBb_nrbarmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NRBARMNY);
  }

  /**
   * 获取bb_nrbartax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nrbartax
   */
  public UFDouble getBb_nrbartax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NRBARTAX);
  }

  /**
   * 获取bb_nrbartaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nrbartaxmny
   */
  public UFDouble getBb_nrbartaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NRBARTAXMNY);
  }

  /**
   * 获取bb_nrbinmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_nrbinmny
   */
  public UFDouble getBb_nrbinmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NRBINMNY);
  }

  /**
   * 获取bb_ntax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ntax
   */
  public UFDouble getBb_ntax() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAX);
  }

  /**
   * 获取bb_ntaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ntaxmny
   */
  public UFDouble getBb_ntaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAXMNY);
  }

  /**
   * 获取bb_ntaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ntaxprice
   */
  public UFDouble getBb_ntaxprice() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAXPRICE);
  }

  /**
   * 获取bb_ntaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_ntaxrate
   */
  public UFDouble getBb_ntaxrate() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.BB_NTAXRATE);
  }

  /**
   * 获取bb_tgoldentaxtime（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_tgoldentaxtime
   */
  public UFDate getBb_tgoldentaxtime() {
    return (UFDate) this.getAttributeValue(SettleListItemVO.BB_TGOLDENTAXTIME);
  }

  /**
   * 获取bb_vgoldentaxcode（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_vgoldentaxcode
   */
  public String getBb_vgoldentaxcode() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_VGOLDENTAXCODE);
  }

  /**
   * 获取bb_vvirtualcode（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return bb_vvirtualcode
   */
  public String getBb_vvirtualcode() {
    return (String) this.getAttributeValue(SettleListItemVO.BB_VVIRTUALCODE);
  }

  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(SettleListItemVO.BOPPTAXFLAG);
  }

  public String getCasscustid() {
    return (String) this.getAttributeValue(SettleListItemVO.CASSCUSTID);
  }

  public String getCbill_bid() {
    return (String) this.getAttributeValue(SettleListItemVO.CBILL_BID);
  }

  public String getCbillid() {
    return (String) this.getAttributeValue(SettleListItemVO.CBILLID);
  }

  public String getCfirstbid() {
    return (String) this.getAttributeValue(SettleListItemVO.CFIRSTBID);
  }

  public String getCfirstid() {
    return (String) this.getAttributeValue(SettleListItemVO.CFIRSTID);
  }

  public String getCinsrcbid() {
    return (String) this.getAttributeValue(SettleListItemVO.CINSRCBID);
  }

  public String getCinsrcid() {
    return (String) this.getAttributeValue(SettleListItemVO.CINSRCID);
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

  public String getCipst_bid() {
    return (String) this.getAttributeValue(SettleListItemVO.CIPST_BID);
  }

  public String getCipstid() {
    return (String) this.getAttributeValue(SettleListItemVO.CIPSTID);
  }

  public String getCopst_bid() {
    return (String) this.getAttributeValue(SettleListItemVO.COPST_BID);
  }

  public String getCopstid() {
    return (String) this.getAttributeValue(SettleListItemVO.COPSTID);
  }

  public String getCproductorid() {
    return (String) this.getAttributeValue(SettleListItemVO.CPRODUCTORID);
  }

  public String getCprojectid() {
    return (String) this.getAttributeValue(SettleListItemVO.CPROJECTID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(SettleListItemVO.CQTUNITID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SettleListItemVO.CROWNO);
  }

  public String getCsettlerule_bid() {
    return (String) this.getAttributeValue(SettleListItemVO.CSETTLERULE_BID);
  }

  public String getCsettleruleid() {
    return (String) this.getAttributeValue(SettleListItemVO.CSETTLERULEID);
  }

  public String getCsrcbid() {
    return (String) this.getAttributeValue(SettleListItemVO.CSRCBID);
  }

  public String getCsrcid() {
    return (String) this.getAttributeValue(SettleListItemVO.CSRCID);
  }

  public String getCsrctrantype() {
    return (String) this.getAttributeValue(SettleListItemVO.CSRCTRANTYPE);
  }

  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SettleListItemVO.CTAXCODEID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(SettleListItemVO.CUNITID);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(SettleListItemVO.CVENDORID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SettleListItemVO.DBILLDATE);
  }

  public UFDate getDfirstbilldate() {
    return (UFDate) this.getAttributeValue(SettleListItemVO.DFIRSTBILLDATE);
  }

  public UFDate getDinsrcbilldate() {
    return (UFDate) this.getAttributeValue(SettleListItemVO.DINSRCBILLDATE);
  }

  public UFDate getDsrcbilldate() {
    return (UFDate) this.getAttributeValue(SettleListItemVO.DSRCBILLDATE);
  }

  public Integer getFintaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.FINTAXTYPEFLAG);
  }

  public Integer getFsettletypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.FSETTLETYPEFLAG);
  }

  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SettleListItemVO.FTAXTYPEFLAG);
  }
  public void setCffileid(String cffileid) {
	    this.setAttributeValue(SettleListItemVO.CFFILEID, cffileid);
	  }
public String getCffileid() {
	    return (String) this.getAttributeValue(SettleListItemVO.CFFILEID);
}


  public SettleListLineVO[] getLineVOs() {
    return this.m_BBVOS;
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("to.to_settlelist_b");
    return meta;
  }

  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NCALTAXMNY);
  }

  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NGLOBALMNY);
  }

  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NGLOBALTAXMNY);
  }

  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NGROUPMNY);
  }

  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SettleListItemVO.NGROUPTAXMNY);
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

  public UFDateTime getOutItemTs() {
    return this.outitemts;
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

  public String getVbatchcode() {
    return (String) this.getAttributeValue(SettleListItemVO.VBATCHCODE);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(SettleListItemVO.VBDEF9);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(SettleListItemVO.VBILLCODE);
  }

  public String getVfirstcode() {
    return (String) this.getAttributeValue(SettleListItemVO.VFIRSTCODE);
  }

  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SettleListItemVO.VFIRSTROWNO);
  }

  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SettleListItemVO.VFIRSTTRANTYPE);
  }

  public String getVfirsttype() {
    return (String) this.getAttributeValue(SettleListItemVO.VFIRSTTYPE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(SettleListItemVO.VFREE9);
  }

  public String getVinsrccode() {
    return (String) this.getAttributeValue(SettleListItemVO.VINSRCCODE);
  }

  public String getVinsrcrowno() {
    return (String) this.getAttributeValue(SettleListItemVO.VINSRCROWNO);
  }

  public String getVinsrctrantype() {
    return (String) this.getAttributeValue(SettleListItemVO.VINSRCTRANTYPE);
  }

  public String getVinsrctype() {
    return (String) this.getAttributeValue(SettleListItemVO.VINSRCTYPE);
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

  /**
   * 设置bb_bgoldentaxflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_bgoldentaxflag bb_bgoldentaxflag
   */
  public void setBb_bgoldentaxflag(UFBoolean bb_bgoldentaxflag) {
    this.setAttributeValue(SettleListItemVO.BB_BGOLDENTAXFLAG,
        bb_bgoldentaxflag);
  }

  /**
   * 设置bb_bopptaxflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_bopptaxflag bb_bopptaxflag
   */
  public void setBb_bopptaxflag(UFBoolean bb_bopptaxflag) {
    this.setAttributeValue(SettleListItemVO.BB_BOPPTAXFLAG, bb_bopptaxflag);
  }

  /**
   * 设置bb_cbill_bbid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cbill_bbid bb_cbill_bbid
   */
  public void setBb_cbill_bbid(String bb_cbill_bbid) {
    this.setAttributeValue(SettleListItemVO.BB_CBILL_BBID, bb_cbill_bbid);
  }

  /**
   * 设置bb_ccurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ccurrencyid bb_ccurrencyid
   */
  public void setBb_ccurrencyid(String bb_ccurrencyid) {
    this.setAttributeValue(SettleListItemVO.BB_CCURRENCYID, bb_ccurrencyid);
  }

  /**
   * 设置bb_ccustomerid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ccustomerid bb_ccustomerid
   */
  public void setBb_ccustomerid(String bb_ccustomerid) {
    this.setAttributeValue(SettleListItemVO.BB_CCUSTOMERID, bb_ccustomerid);
  }

  /**
   * 设置bb_cdowncreid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cdowncreid bb_cdowncreid
   */
  public void setBb_cdowncreid(String bb_cdowncreid) {
    this.setAttributeValue(SettleListItemVO.BB_CDOWNCREID, bb_cdowncreid);
  }

  /**
   * 设置bb_cdownfiorgid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cdownfiorgid bb_cdownfiorgid
   */
  public void setBb_cdownfiorgid(String bb_cdownfiorgid) {
    this.setAttributeValue(SettleListItemVO.BB_CDOWNFIORGID, bb_cdownfiorgid);
  }

  /**
   * 设置bb_cdownfiorgvid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cdownfiorgvid bb_cdownfiorgvid
   */
  public void setBb_cdownfiorgvid(String bb_cdownfiorgvid) {
    this.setAttributeValue(SettleListItemVO.BB_CDOWNFIORGVID, bb_cdownfiorgvid);
  }

  /**
   * 设置bb_cincurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cincurrencyid bb_cincurrencyid
   */
  public void setBb_cincurrencyid(String bb_cincurrencyid) {
    this.setAttributeValue(SettleListItemVO.BB_CINCURRENCYID, bb_cincurrencyid);
  }

  /**
   * 设置bb_cintaxcodeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cintaxcodeid bb_cintaxcodeid
   */
  public void setBb_cintaxcodeid(String bb_cintaxcodeid) {
    this.setAttributeValue(SettleListItemVO.BB_CINTAXCODEID, bb_cintaxcodeid);
  }

  /**
   * 设置bb_coutcurrencyid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_coutcurrencyid bb_coutcurrencyid
   */
  public void setBb_coutcurrencyid(String bb_coutcurrencyid) {
    this.setAttributeValue(SettleListItemVO.BB_COUTCURRENCYID,
        bb_coutcurrencyid);
  }

  /**
   * 设置bb_crowno（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_crowno bb_crowno
   */
  public void setBb_crowno(String bb_crowno) {
    this.setAttributeValue(SettleListItemVO.BB_CROWNO, bb_crowno);
  }

  /**
   * 设置bb_ctaxcodeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ctaxcodeid bb_ctaxcodeid
   */
  public void setBb_ctaxcodeid(String bb_ctaxcodeid) {
    this.setAttributeValue(SettleListItemVO.BB_CTAXCODEID, bb_ctaxcodeid);
  }

  /**
   * 设置bb_cupcreid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cupcreid bb_cupcreid
   */
  public void setBb_cupcreid(String bb_cupcreid) {
    this.setAttributeValue(SettleListItemVO.BB_CUPCREID, bb_cupcreid);
  }

  /**
   * 设置bb_cupfiorgid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cupfiorgid bb_cupfiorgid
   */
  public void setBb_cupfiorgid(String bb_cupfiorgid) {
    this.setAttributeValue(SettleListItemVO.BB_CUPFIORGID, bb_cupfiorgid);
  }

  /**
   * 设置bb_cupfiorgvid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cupfiorgvid bb_cupfiorgvid
   */
  public void setBb_cupfiorgvid(String bb_cupfiorgvid) {
    this.setAttributeValue(SettleListItemVO.BB_CUPFIORGVID, bb_cupfiorgvid);
  }

  /**
   * 设置bb_cvendorid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_cvendorid bb_cvendorid
   */
  public void setBb_cvendorid(String bb_cvendorid) {
    this.setAttributeValue(SettleListItemVO.BB_CVENDORID, bb_cvendorid);
  }

  /**
   * 设置bb_fintaxtypeflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_fintaxtypeflag bb_fintaxtypeflag
   * @see TaxType
   */
  public void setBb_fintaxtypeflag(Integer bb_fintaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.BB_FINTAXTYPEFLAG,
        bb_fintaxtypeflag);
  }

  /**
   * 设置bb_ftaxtypeflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ftaxtypeflag bb_ftaxtypeflag
   * @see EnumDiscounttaxtype
   */
  public void setBb_ftaxtypeflag(Integer bb_ftaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.BB_FTAXTYPEFLAG, bb_ftaxtypeflag);
  }

  /**
   * 设置bb_ncaltaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ncaltaxmny bb_ncaltaxmny
   */
  public void setBb_ncaltaxmny(UFDouble bb_ncaltaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NCALTAXMNY, bb_ncaltaxmny);
  }

  /**
   * 设置bb_ndiscountrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ndiscountrate bb_ndiscountrate
   */
  public void setBb_ndiscountrate(UFDouble bb_ndiscountrate) {
    this.setAttributeValue(SettleListItemVO.BB_NDISCOUNTRATE, bb_ndiscountrate);
  }
  
  /**
   * 设置bb_ndiscountvalue（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ndiscountrate bb_ndiscountrate
   */
  public void setBb_ndiscountvalue(UFDouble bb_ndiscountvalue) {
    this.setAttributeValue(SettleListItemVO.BB_NDISCOUNTVALUE, bb_ndiscountvalue);
  }

  /**
   * 设置bb_nexchangerate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nexchangerate bb_nexchangerate
   */
  public void setBb_nexchangerate(UFDouble bb_nexchangerate) {
    this.setAttributeValue(SettleListItemVO.BB_NEXCHANGERATE, bb_nexchangerate);
  }

  /**
   * 设置bb_nincaltaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nincaltaxmny bb_nincaltaxmny
   */
  public void setBb_nincaltaxmny(UFDouble bb_nincaltaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINCALTAXMNY, bb_nincaltaxmny);
  }

  /**
   * 设置bb_ninexchangerate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninexchangerate bb_ninexchangerate
   */
  public void setBb_ninexchangerate(UFDouble bb_ninexchangerate) {
    this.setAttributeValue(SettleListItemVO.BB_NINEXCHANGERATE,
        bb_ninexchangerate);
  }

  /**
   * 设置bb_ninmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninmny bb_ninmny
   */
  public void setBb_ninmny(UFDouble bb_ninmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINMNY, bb_ninmny);
  }

  /**
   * 设置bb_ninorigmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninorigmny bb_ninorigmny
   */
  public void setBb_ninorigmny(UFDouble bb_ninorigmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINORIGMNY, bb_ninorigmny);
  }

  /**
   * 设置bb_ninorigprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninorigprice bb_ninorigprice
   */
  public void setBb_ninorigprice(UFDouble bb_ninorigprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINORIGPRICE, bb_ninorigprice);
  }

  /**
   * 设置bb_ninprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninprice bb_ninprice
   */
  public void setBb_ninprice(UFDouble bb_ninprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINPRICE, bb_ninprice);
  }

  /**
   * 设置bb_ninqtorigpric（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninqtorigpric bb_ninqtorigpric
   */
  public void setBb_ninqtorigpric(UFDouble bb_ninqtorigpric) {
    this.setAttributeValue(SettleListItemVO.BB_NINQTORIGPRIC, bb_ninqtorigpric);
  }

  /**
   * 设置bb_ninqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninqtprice bb_ninqtprice
   */
  public void setBb_ninqtprice(UFDouble bb_ninqtprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINQTPRICE, bb_ninqtprice);
  }

  /**
   * 设置bb_ninqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ninqttaxprice bb_ninqttaxprice
   */
  public void setBb_ninqttaxprice(UFDouble bb_ninqttaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINQTTAXPRICE, bb_ninqttaxprice);
  }

  /**
   * 设置bb_nintax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nintax bb_nintax
   */
  public void setBb_nintax(UFDouble bb_nintax) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAX, bb_nintax);
  }

  /**
   * 设置bb_nintaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nintaxmny bb_nintaxmny
   */
  public void setBb_nintaxmny(UFDouble bb_nintaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAXMNY, bb_nintaxmny);
  }

  /**
   * 设置bb_nintaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nintaxprice bb_nintaxprice
   */
  public void setBb_nintaxprice(UFDouble bb_nintaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAXPRICE, bb_nintaxprice);
  }

  /**
   * 设置bb_nintaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nintaxrate bb_nintaxrate
   */
  public void setBb_nintaxrate(UFDouble bb_nintaxrate) {
    this.setAttributeValue(SettleListItemVO.BB_NINTAXRATE, bb_nintaxrate);
  }

  /**
   * 设置bb_nmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nmny bb_nmny
   */
  public void setBb_nmny(UFDouble bb_nmny) {
    this.setAttributeValue(SettleListItemVO.BB_NMNY, bb_nmny);
  }

  /**
   * 设置bb_nnosubtax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nnosubtax bb_nnosubtax
   */
  public void setBb_nnosubtax(UFDouble bb_nnosubtax) {
    this.setAttributeValue(SettleListItemVO.BB_NNOSUBTAX, bb_nnosubtax);
  }

  /**
   * 设置bb_nnosubtaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nnosubtaxrate bb_nnosubtaxrate
   */
  public void setBb_nnosubtaxrate(UFDouble bb_nnosubtaxrate) {
    this.setAttributeValue(SettleListItemVO.BB_NNOSUBTAXRATE, bb_nnosubtaxrate);
  }

  /**
   * 设置bb_norigmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_norigmny bb_norigmny
   */
  public void setBb_norigmny(UFDouble bb_norigmny) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGMNY, bb_norigmny);
  }

  /**
   * 设置bb_norigprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_norigprice bb_norigprice
   */
  public void setBb_norigprice(UFDouble bb_norigprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGPRICE, bb_norigprice);
  }

  /**
   * 设置bb_norigqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_norigqtprice bb_norigqtprice
   */
  public void setBb_norigqtprice(UFDouble bb_norigqtprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGQTPRICE, bb_norigqtprice);
  }

  /**
   * 设置bb_norigqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_norigqttaxprice bb_norigqttaxprice
   */
  public void setBb_norigqttaxprice(UFDouble bb_norigqttaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGQTTAXPRICE,
        bb_norigqttaxprice);
  }

  /**
   * 设置bb_norigtaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_norigtaxmny bb_norigtaxmny
   */
  public void setBb_norigtaxmny(UFDouble bb_norigtaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGTAXMNY, bb_norigtaxmny);
  }

  /**
   * 设置bb_norigtaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_norigtaxprice bb_norigtaxprice
   */
  public void setBb_norigtaxprice(UFDouble bb_norigtaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NORIGTAXPRICE, bb_norigtaxprice);
  }

  /**
   * 设置bb_nprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nprice bb_nprice
   */
  public void setBb_nprice(UFDouble bb_nprice) {
    this.setAttributeValue(SettleListItemVO.BB_NPRICE, bb_nprice);
  }

  /**
   * 设置bb_nqtprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nqtprice bb_nqtprice
   */
  public void setBb_nqtprice(UFDouble bb_nqtprice) {
    this.setAttributeValue(SettleListItemVO.BB_NQTPRICE, bb_nqtprice);
  }

  /**
   * 设置bb_nqttaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nqttaxprice bb_nqttaxprice
   */
  public void setBb_nqttaxprice(UFDouble bb_nqttaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NQTTAXPRICE, bb_nqttaxprice);
  }

  /**
   * 设置bb_nrbapmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nrbapmny bb_nrbapmny
   */
  public void setBb_nrbapmny(UFDouble bb_nrbapmny) {
    this.setAttributeValue(SettleListItemVO.BB_NRBAPMNY, bb_nrbapmny);
  }

  /**
   * 设置bb_nrbaptax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nrbaptax bb_nrbaptax
   */
  public void setBb_nrbaptax(UFDouble bb_nrbaptax) {
    this.setAttributeValue(SettleListItemVO.BB_NRBAPTAX, bb_nrbaptax);
  }

  /**
   * 设置bb_nrbaptaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nrbaptaxmny bb_nrbaptaxmny
   */
  public void setBb_nrbaptaxmny(UFDouble bb_nrbaptaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NRBAPTAXMNY, bb_nrbaptaxmny);
  }

  /**
   * 设置bb_nrbarmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nrbarmny bb_nrbarmny
   */
  public void setBb_nrbarmny(UFDouble bb_nrbarmny) {
    this.setAttributeValue(SettleListItemVO.BB_NRBARMNY, bb_nrbarmny);
  }

  /**
   * 设置bb_nrbartax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nrbartax bb_nrbartax
   */
  public void setBb_nrbartax(UFDouble bb_nrbartax) {
    this.setAttributeValue(SettleListItemVO.BB_NRBARTAX, bb_nrbartax);
  }

  /**
   * 设置bb_nrbartaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nrbartaxmny bb_nrbartaxmny
   */
  public void setBb_nrbartaxmny(UFDouble bb_nrbartaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NRBARTAXMNY, bb_nrbartaxmny);
  }

  /**
   * 设置bb_nrbinmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_nrbinmny bb_nrbinmny
   */
  public void setBb_nrbinmny(UFDouble bb_nrbinmny) {
    this.setAttributeValue(SettleListItemVO.BB_NRBINMNY, bb_nrbinmny);
  }

  /**
   * 设置bb_ntax（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ntax bb_ntax
   */
  public void setBb_ntax(UFDouble bb_ntax) {
    this.setAttributeValue(SettleListItemVO.BB_NTAX, bb_ntax);
  }

  /**
   * 设置bb_ntaxmny（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ntaxmny bb_ntaxmny
   */
  public void setBb_ntaxmny(UFDouble bb_ntaxmny) {
    this.setAttributeValue(SettleListItemVO.BB_NTAXMNY, bb_ntaxmny);
  }

  /**
   * 设置bb_ntaxprice（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ntaxprice bb_ntaxprice
   */
  public void setBb_ntaxprice(UFDouble bb_ntaxprice) {
    this.setAttributeValue(SettleListItemVO.BB_NTAXPRICE, bb_ntaxprice);
  }

  /**
   * 设置bb_ntaxrate（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_ntaxrate bb_ntaxrate
   */
  public void setBb_ntaxrate(UFDouble bb_ntaxrate) {
    this.setAttributeValue(SettleListItemVO.BB_NTAXRATE, bb_ntaxrate);
  }

  /**
   * 设置bb_tgoldentaxtime（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_tgoldentaxtime bb_tgoldentaxtime
   */
  public void setBb_tgoldentaxtime(UFDate bb_tgoldentaxtime) {
    this.setAttributeValue(SettleListItemVO.BB_TGOLDENTAXTIME,
        bb_tgoldentaxtime);
  }

  /**
   * 设置bb_vgoldentaxcode（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_vgoldentaxcode bb_vgoldentaxcode
   */
  public void setBb_vgoldentaxcode(String bb_vgoldentaxcode) {
    this.setAttributeValue(SettleListItemVO.BB_VGOLDENTAXCODE,
        bb_vgoldentaxcode);
  }

  /**
   * 设置bb_vvirtualcode（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param bb_vvirtualcode bb_vvirtualcode
   */
  public void setBb_vvirtualcode(String bb_vvirtualcode) {
    this.setAttributeValue(SettleListItemVO.BB_VVIRTUALCODE, bb_vvirtualcode);
  }

  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(SettleListItemVO.BOPPTAXFLAG, bopptaxflag);
  }

  public void setCasscustid(String casscustid) {
    this.setAttributeValue(SettleListItemVO.CASSCUSTID, casscustid);
  }

  public void setCbill_bid(String cbill_bid) {
    this.setAttributeValue(SettleListItemVO.CBILL_BID, cbill_bid);
  }

  public void setCbillid(String cbillid) {
    this.setAttributeValue(SettleListItemVO.CBILLID, cbillid);
  }

  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SettleListItemVO.CFIRSTBID, cfirstbid);
  }

  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SettleListItemVO.CFIRSTID, cfirstid);
  }

  public void setCinsrcbid(String cinsrcbid) {
    this.setAttributeValue(SettleListItemVO.CINSRCBID, cinsrcbid);
  }

  public void setCinsrcid(String cinsrcid) {
    this.setAttributeValue(SettleListItemVO.CINSRCID, cinsrcid);
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

  public void setCipst_bid(String cipst_bid) {
    this.setAttributeValue(SettleListItemVO.CIPST_BID, cipst_bid);
  }

  public void setCipstid(String cipstid) {
    this.setAttributeValue(SettleListItemVO.CIPSTID, cipstid);
  }

  public void setCopst_bid(String copst_bid) {
    this.setAttributeValue(SettleListItemVO.COPST_BID, copst_bid);
  }

  public void setCopstid(String copstid) {
    this.setAttributeValue(SettleListItemVO.COPSTID, copstid);
  }

  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SettleListItemVO.CPRODUCTORID, cproductorid);
  }

  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SettleListItemVO.CPROJECTID, cprojectid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SettleListItemVO.CQTUNITID, cqtunitid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SettleListItemVO.CROWNO, crowno);
  }

  public void setCsettlerule_bid(String csettlerule_bid) {
    this.setAttributeValue(SettleListItemVO.CSETTLERULE_BID, csettlerule_bid);
  }

  public void setCsettleruleid(String csettleruleid) {
    this.setAttributeValue(SettleListItemVO.CSETTLERULEID, csettleruleid);
  }

  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SettleListItemVO.CSRCBID, csrcbid);
  }

  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SettleListItemVO.CSRCID, csrcid);
  }

  public void setCsrctrantype(String csrctrantype) {
    this.setAttributeValue(SettleListItemVO.CSRCTRANTYPE, csrctrantype);
  }

  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SettleListItemVO.CTAXCODEID, ctaxcodeid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(SettleListItemVO.CUNITID, cunitid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SettleListItemVO.CVENDORID, cvendorid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SettleListItemVO.DBILLDATE, dbilldate);
  }

  public void setDfirstbilldate(UFDate dfirstbilldate) {
    this.setAttributeValue(SettleListItemVO.DFIRSTBILLDATE, dfirstbilldate);
  }

  public void setDinsrcbilldate(UFDate dinsrcbilldate) {
    this.setAttributeValue(SettleListItemVO.DINSRCBILLDATE, dinsrcbilldate);
  }

  public void setDsrcbilldate(UFDate dsrcbilldate) {
    this.setAttributeValue(SettleListItemVO.DSRCBILLDATE, dsrcbilldate);
  }

  public void setFintaxtypeflag(Integer fintaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.FINTAXTYPEFLAG, fintaxtypeflag);
  }

  public void setFsettletypeflag(Integer fsettletypeflag) {
    this.setAttributeValue(SettleListItemVO.FSETTLETYPEFLAG, fsettletypeflag);
  }

  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SettleListItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setLineVOs(SettleListLineVO[] bbchildren) {
    this.m_BBVOS = bbchildren;
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SettleListItemVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SettleListItemVO.NGLOBALMNY, nglobalmny);
  }

  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SettleListItemVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SettleListItemVO.NGROUPMNY, ngroupmny);
  }

  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SettleListItemVO.NGROUPTAXMNY, ngrouptaxmny);
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

  public void setOutItemTs(UFDateTime ts) {
    this.outitemts = ts;
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

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SettleListItemVO.VBATCHCODE, vbatchcode);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SettleListItemVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SettleListItemVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SettleListItemVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SettleListItemVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SettleListItemVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SettleListItemVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SettleListItemVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SettleListItemVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SettleListItemVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SettleListItemVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SettleListItemVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SettleListItemVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SettleListItemVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SettleListItemVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SettleListItemVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SettleListItemVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SettleListItemVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SettleListItemVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SettleListItemVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SettleListItemVO.VBDEF9, vbdef9);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SettleListItemVO.VBILLCODE, vbillcode);
  }

  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SettleListItemVO.VFIRSTCODE, vfirstcode);
  }

  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SettleListItemVO.VFIRSTROWNO, vfirstrowno);
  }

  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SettleListItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SettleListItemVO.VFIRSTTYPE, vfirsttype);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(SettleListItemVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(SettleListItemVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(SettleListItemVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(SettleListItemVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(SettleListItemVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(SettleListItemVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(SettleListItemVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(SettleListItemVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(SettleListItemVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(SettleListItemVO.VFREE9, vfree9);
  }

  public void setVinsrccode(String vinsrccode) {
    this.setAttributeValue(SettleListItemVO.VINSRCCODE, vinsrccode);
  }

  public void setVinsrcrowno(String vinsrcrowno) {
    this.setAttributeValue(SettleListItemVO.VINSRCROWNO, vinsrcrowno);
  }

  public void setVinsrctrantype(String vinsrctrantype) {
    this.setAttributeValue(SettleListItemVO.VINSRCTRANTYPE, vinsrctrantype);
  }

  public void setVinsrctype(String vinsrctype) {
    this.setAttributeValue(SettleListItemVO.VINSRCTYPE, vinsrctype);
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

}
