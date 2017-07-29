package nc.vo.to.businessinfo.entity.display;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class BusinessinfoPathViewBVO extends AbstractDataView {

	// 基价询价后价格是否可改
	public static final String BBASEPRICEEDITFLAG = "bbasepriceeditflag";

	// 时间戳
	public static final String BBTS = "bbts";

	// 子子表VO
	public static final String BBVO = "bbvo";

	// 是否赠品
	public static final String BLARGESSFLAG = "blargessflag";

	// 询价后价格是否可改
	public static final String BMODIFYPRICEFLAG = "bmodifypriceflag";

	// 是否需要询价
	public static final String BNEEDASKPRICE = "bneedaskprice";

	// 询价完毕后是否需要币种转换
	public static final String BNEEDCONVERTCURR = "bneedconvertcurr";

	// 时间戳
	public static final String BTS = "bts";

	// 业务计量单位主键
	public static final String CASTUNITID = "castunitid";

	// 内部交易信息子子表主键
	public static final String CBUSINESS_BBID = "cbusiness_bbid";

	// 内部交易信息子表主键
	public static final String CBUSINESS_BID = "cbusiness_bid";

	// 内部交易信息主表主键
	public static final String CBUSINESSID = "cbusinessid";

	// 下游成本域主键
	public static final String CDOWNCOSTREGIONID = "cdowncostregionid";

	// 下游财务组织主键
	public static final String CDOWNFINANCEORGID = "cdownfinanceorgid";

	// 下游财务组织版本主键
	public static final String CDOWNFINANCEORGVID = "cdownfinanceorgvid";

	// 物料主键
	public static final String CINVENTORYID = "cinventoryid";

	// 物料版本主键
	public static final String CINVENTORYVID = "cinventoryvid";

	// 交易币种主键
	public static final String CORIGCURRENCYID = "corigcurrencyid";

	// 结算路径行号
	public static final String CPATHROWNO = "cpathrowno";

	// 生产厂商主键
	public static final String CPRODUCTORID = "cproductorid";

	// 项目主键
	public static final String CPROJECTID = "cprojectid";

	// 报价计量单位
	public static final String CQTUNITID = "cqtunitid";

	// 行号
	public static final String CROWNO = "crowno";

	// 结算路径子表主键
	public static final String CSETTLEPATH_BID = "csettlepath_bid";

	// 结算规则子表主键
	public static final String CSETTLERULE_BID = "csettlerule_bid";

	// 结算规则主表主键
	public static final String CSETTLERULEID = "csettleruleid";

	// 来源单据子表主键
	public static final String CSRCBID = "csrcbid";

	// 来源单据的来源单据子表主键
	public static final String CSRCSRCBID = "csrcsrcbid";

	// 税码
	public static final String CTAXCODEID = "ctaxcodeid";

	// 主单位
	public static final String CUNITID = "cunitid";

	// 上游成本域主键
	public static final String CUPCOSTREGIONID = "cupcostregionid";

	// 上游财务组织主键
	public static final String CUPFINANCEORGID = "cupfinanceorgid";

	// 上游财务组织版本主键
	public static final String CUPFINANCEORGVID = "cupfinanceorgvid";

	// 供应商主键
	public static final String CVENDORID = "cvendorid";

	// 基价计税类别
	public static final String FBASETAXTYPEFLAG = "fbasetaxtypeflag";

	// 计税类别
	public static final String FTAXTYPEFLAG = "ftaxtypeflag";

	// 报价计量单位交易币种询价无税价
	public static final String NASKQTORIGPRICE = "naskqtorigprice";

	// 报价计量单位交易币种询价含税价
	public static final String NASKQTORIGTAXPRICE = "naskqtorigtaxprice";

	// 业务单位数量
	public static final String NASSISTNUM = "nassistnum";

	// 计税金额
	public static final String NCALTAXMNY = "ncaltaxmny";

	// 折扣率
	public static final String NDISCOUNTRATE = "ndiscountrate";

	// 折扣额 gwj
	public static final String NDISCOUNTVALUE = "ndiscountvalue";

	// 本币无税金额
	public static final String NMNY = "nmny";

	// 主数量
	public static final String NNUMBER = "nnumber";

	// 主单位交易币种无税单价
	public static final String NORIGPRICE = "norigprice";

	// 主单位交易币种含税单价
	public static final String NORIGTAXPRICE = "norigtaxprice";

	// 主本币无税单价
	public static final String NPRICE = "nprice";

	// 报价计量单位数量
	public static final String NQTNUM = "nqtnum";

	// 报价计量单位交易币种无税金额
	public static final String NQTORIGMNY = "nqtorigmny";

	// 报价计量单位交易币种无税价
	public static final String NQTORIGPRICE = "nqtorigprice";

	// 报价计量单位交易币种税额
	public static final String NQTORIGTAX = "nqtorigtax";

	// 报价计量单位交易币种价税合计
	public static final String NQTORIGTAXMNY = "nqtorigtaxmny";

	// 报价计量单位交易币种含税价
	public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

	// 报价本币无税单价
	public static final String NQTPRICE = "nqtprice";

	// 报价本币含税单价
	public static final String NQTTAXPRICE = "nqttaxprice";

	// 税额
	public static final String NTAX = "ntax";

	// 本币价税合计
	public static final String NTAXMNY = "ntaxmny";

	// 主本币含税单价
	public static final String NTAXPRICE = "ntaxprice";

	// 税率
	public static final String NTAXRATE = "ntaxrate";

	// 批次档案
	public static final String PK_BATCHCODE = "pk_batchcode";

	// 主组织
	public static final String PK_ORG = "pk_org";

	// 时间戳
	public static final String TS = "ts";

	// 批次号
	public static final String VBATCHCODE = "vbatchcode";

	// 换算率
	public static final String VCHANGERATE = "vchangerate";

	// 自由项1
	public static final String VFREE1 = "vfree1";

	// 自由项10
	public static final String VFREE10 = "vfree10";

	// 自由项2
	public static final String VFREE2 = "vfree2";

	// 自由项3
	public static final String VFREE3 = "vfree3";

	// 自由项4
	public static final String VFREE4 = "vfree4";

	// 自由项5
	public static final String VFREE5 = "vfree5";

	// 自由项6
	public static final String VFREE6 = "vfree6";

	// 自由项7
	public static final String VFREE7 = "vfree7";

	// 自由项8
	public static final String VFREE8 = "vfree8";

	// 自由项9
	public static final String VFREE9 = "vfree9";

	// 报价计量单位换算率
	public static final String VQTUNITRATE = "vqtunitrate";

	private static final long serialVersionUID = -5899314825472851806L;

	public UFBoolean getBbasepriceeditflag() {
		return (UFBoolean) this
				.getAttributeValue(BusinessinfoPathViewBVO.BBASEPRICEEDITFLAG);
	}

	public UFDateTime getBbts() {
		return (UFDateTime) this
				.getAttributeValue(BusinessinfoPathViewBVO.BBTS);
	}

	public UFBoolean getBlargessflag() {
		return (UFBoolean) this
				.getAttributeValue(BusinessinfoPathViewBVO.BLARGESSFLAG);
	}

	public UFBoolean getBmodifypriceflag() {
		return (UFBoolean) this
				.getAttributeValue(BusinessinfoPathViewBVO.BMODIFYPRICEFLAG);
	}

	public UFBoolean getBneedaskprice() {
		return (UFBoolean) this
				.getAttributeValue(BusinessinfoPathViewBVO.BNEEDASKPRICE);
	}

	public UFBoolean getBneedconvertcurr() {
		return (UFBoolean) this
				.getAttributeValue(BusinessinfoPathViewBVO.BNEEDCONVERTCURR);
	}

	public UFDateTime getBts() {
		return (UFDateTime) this.getAttributeValue(BusinessinfoPathViewBVO.BTS);
	}

	public String getCastunitid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CASTUNITID);
	}

	public String getCbusiness_bbid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CBUSINESS_BBID);
	}

	public String getCbusiness_bid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CBUSINESS_BID);
	}

	public String getCbusinessid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CBUSINESSID);
	}

	public String getCdowncostregionid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CDOWNCOSTREGIONID);
	}

	public String getCdownfinanceorgid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CDOWNFINANCEORGID);
	}

	public String getCdownfinanceorgvid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CDOWNFINANCEORGVID);
	}

	public String getCinventoryid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CINVENTORYID);
	}

	public String getCinventoryvid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CINVENTORYVID);
	}

	public String getCorigcurrencyid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CORIGCURRENCYID);
	}

	public String getCpathrowno() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CPATHROWNO);
	}

	public String getCproductorid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CPRODUCTORID);
	}

	public String getCprojectid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CPROJECTID);
	}

	public String getCqtunitid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CQTUNITID);
	}

	public String getCrowno() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.CROWNO);
	}

	public String getCsettlepath_bid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CSETTLEPATH_BID);
	}

	public String getCsettlerule_bid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CSETTLERULE_BID);
	}

	public String getCsettleruleid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CSETTLERULEID);
	}

	public String getCsrcbid() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.CSRCBID);
	}

	public String getCsrcsrcbid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CSRCSRCBID);
	}

	public String getCtaxCodeid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CTAXCODEID);
	}

	public String getCunitid() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.CUNITID);
	}

	public String getCupcostregionid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CUPCOSTREGIONID);
	}

	public String getCupfinanceorgid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CUPFINANCEORGID);
	}

	public String getCupfinanceorgvid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CUPFINANCEORGVID);
	}

	public String getCvendorid() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.CVENDORID);
	}

	public Integer getFbasetaxtypeflag() {
		return (Integer) this
				.getAttributeValue(BusinessinfoPathViewBVO.FBASETAXTYPEFLAG);
	}

	public Integer getFtaxtypeflag() {
		return (Integer) this
				.getAttributeValue(BusinessinfoPathViewBVO.FTAXTYPEFLAG);
	}

	@Override
	public IDataViewMeta getMetaData() {
		IDataViewMeta viewmeta = DataViewMetaFactory.getInstance()
				.getDataViewMeta(BusinessinfoPathViewBVOMeta.class);
		return viewmeta;

	}

	public UFDouble getNaskqtorigprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NASKQTORIGPRICE);
	}

	public UFDouble getNaskqtorigtaxprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NASKQTORIGTAXPRICE);
	}

	public UFDouble getNassistnum() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NASSISTNUM);
	}

	public UFDouble getNcaltaxmny() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NCALTAXMNY);
	}

	public UFDouble getNdiscountrate() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NDISCOUNTRATE);
	}

	public UFDouble getNdiscountvalue() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NDISCOUNTVALUE);
	}

	public UFDouble getNmny() {
		return (UFDouble) this.getAttributeValue(BusinessinfoPathViewBVO.NMNY);
	}

	public UFDouble getNnumber() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NNUMBER);
	}

	public UFDouble getNorigprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NORIGPRICE);
	}

	public UFDouble getNorigtaxprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NORIGTAXPRICE);
	}

	public UFDouble getNprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NPRICE);
	}

	public UFDouble getNqtnum() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTNUM);
	}

	public UFDouble getNqtorigmny() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTORIGMNY);
	}

	public UFDouble getNqtorigprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTORIGPRICE);
	}

	public UFDouble getNqtorigtax() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTORIGTAX);
	}

	public UFDouble getNqtorigtaxmny() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTORIGTAXMNY);
	}

	public UFDouble getNqtorigtaxprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTORIGTAXPRICE);
	}

	public UFDouble getNqtprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTPRICE);
	}

	public UFDouble getNqttaxprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NQTTAXPRICE);
	}

	public UFDouble getNtax() {
		return (UFDouble) this.getAttributeValue(BusinessinfoPathViewBVO.NTAX);
	}

	public UFDouble getNtaxmny() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NTAXMNY);
	}

	public UFDouble getNtaxprice() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NTAXPRICE);
	}

	public UFDouble getNtaxrate() {
		return (UFDouble) this
				.getAttributeValue(BusinessinfoPathViewBVO.NTAXRATE);
	}

	public String getPk_batchcode() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.PK_BATCHCODE);
	}

	public UFBoolean getPk_org() {
		return (UFBoolean) this
				.getAttributeValue(BusinessinfoPathViewBVO.PK_ORG);
	}

	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(BusinessinfoPathViewBVO.TS);
	}

	public String getVbatchcode() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.VBATCHCODE);
	}

	public String getVchangerate() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.VCHANGERATE);
	}

	public String getVfree1() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE1);
	}

	public String getVfree10() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE10);
	}

	public String getVfree2() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE2);
	}

	public String getVfree3() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE3);
	}

	public String getVfree4() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE4);
	}

	public String getVfree5() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE5);
	}

	public String getVfree6() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE6);
	}

	public String getVfree7() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE7);
	}

	public String getVfree8() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE8);
	}

	public String getVfree9() {
		return (String) this.getAttributeValue(BusinessinfoPathViewBVO.VFREE9);
	}

	public String getVqtunitrate() {
		return (String) this
				.getAttributeValue(BusinessinfoPathViewBVO.VQTUNITRATE);
	}

	public void setBbasepriceeditflag(UFBoolean bbasepriceeditflag) {
		this.setAttributeValue(BusinessinfoPathViewBVO.BBASEPRICEEDITFLAG,
				bbasepriceeditflag);
	}

	public void setBbts(UFDateTime bbts) {
		this.setAttributeValue(BusinessinfoPathViewBVO.BBTS, bbts);
	}

	public void setBlargessflag(UFBoolean blargessflag) {
		this.setAttributeValue(BusinessinfoPathViewBVO.BLARGESSFLAG,
				blargessflag);
	}

	public void setBmodifypriceflag(UFBoolean bmodifypriceflag) {
		this.setAttributeValue(BusinessinfoPathViewBVO.BMODIFYPRICEFLAG,
				bmodifypriceflag);
	}

	public void setBneedaskprice(UFBoolean bneedaskprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.BNEEDASKPRICE,
				bneedaskprice);
	}

	public void setBneedconvertcurr(UFBoolean bneedconvertcurr) {
		this.setAttributeValue(BusinessinfoPathViewBVO.BNEEDCONVERTCURR,
				bneedconvertcurr);
	}

	public void setBts(UFDateTime bts) {
		this.setAttributeValue(BusinessinfoPathViewBVO.BTS, bts);
	}

	public void setCastunitid(String castunitid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CASTUNITID, castunitid);
	}

	public void setCbusiness_bbid(String cbusiness_bbid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CBUSINESS_BBID,
				cbusiness_bbid);
	}

	public void setCbusiness_bid(String cbusiness_bid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CBUSINESS_BID,
				cbusiness_bid);
	}

	public void setCbusinessid(String cbusinessid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CBUSINESSID, cbusinessid);
	}

	public void setCdowncostregionid(String cdowncostregionid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CDOWNCOSTREGIONID,
				cdowncostregionid);
	}

	public void setCdownfinanceorgid(String cdownfinanceorgid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CDOWNFINANCEORGID,
				cdownfinanceorgid);
	}

	public void setCdownfinanceorgvid(String cdownfinanceorgvid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CDOWNFINANCEORGVID,
				cdownfinanceorgvid);
	}

	public void setCinventoryid(String cinventoryid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CINVENTORYID,
				cinventoryid);
	}

	public void setCinventoryvid(String cinventoryvid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CINVENTORYVID,
				cinventoryvid);
	}

	public void setCorigcurrencyid(String corigcurrencyid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CORIGCURRENCYID,
				corigcurrencyid);
	}

	public void setCpathrowno(String cpathrowno) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CPATHROWNO, cpathrowno);
	}

	public void setCproductorid(String cproductorid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CPRODUCTORID,
				cproductorid);
	}

	public void setCprojectid(String cprojectid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CPROJECTID, cprojectid);
	}

	public void setCqtunitid(String cqtunitid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CQTUNITID, cqtunitid);
	}

	public void setCrowno(String crowno) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CROWNO, crowno);
	}

	public void setCsettlepath_bid(String csettlepath_bid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CSETTLEPATH_BID,
				csettlepath_bid);
	}

	public void setCsettlerule_bid(String csettlerule_bid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CSETTLERULE_BID,
				csettlerule_bid);
	}

	public void setCsettleruleid(String csettleruleid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CSETTLERULEID,
				csettleruleid);
	}

	public void setCsrcbid(String csrcbid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CSRCBID, csrcbid);
	}

	public void setCsrcsrcbid(String csrcsrcbid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CSRCSRCBID, csrcsrcbid);
	}

	public void setCtaxCodeid(String ctaxcodeid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CTAXCODEID, ctaxcodeid);
	}

	public void setCunitid(String cunitid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CUNITID, cunitid);
	}

	public void setCupcostregionid(String cupcostregionid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CUPCOSTREGIONID,
				cupcostregionid);
	}

	public void setCupfinanceorgid(String cupfinanceorgid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CUPFINANCEORGID,
				cupfinanceorgid);
	}

	public void setCupfinanceorgvid(String cupfinanceorgvid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CUPFINANCEORGVID,
				cupfinanceorgvid);
	}

	public void setCvendorid(String cvendorid) {
		this.setAttributeValue(BusinessinfoPathViewBVO.CVENDORID, cvendorid);
	}

	public void setFbasetaxtypeflag(Integer fbasetaxtypeflag) {
		this.setAttributeValue(BusinessinfoPathViewBVO.FBASETAXTYPEFLAG,
				fbasetaxtypeflag);
	}

	public void setFtaxtypeflag(Integer ftaxtypeflag) {
		this.setAttributeValue(BusinessinfoPathViewBVO.FTAXTYPEFLAG,
				ftaxtypeflag);
	}

	public void setNaskqtorigprice(UFDouble naskqtorigprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NASKQTORIGPRICE,
				naskqtorigprice);
	}

	public void setNaskqtorigtaxprice(UFDouble naskqtorigtaxprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NASKQTORIGTAXPRICE,
				naskqtorigtaxprice);
	}

	public void setNassistnum(UFDouble nassistnum) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NASSISTNUM, nassistnum);
	}

	public void setNcaltaxmny(UFDouble ncaltaxmny) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NCALTAXMNY, ncaltaxmny);
	}

	public void setNdiscountrate(UFDouble ndiscountrate) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NDISCOUNTRATE,
				ndiscountrate);
	}

	public void setNdiscountvalue(UFDouble ndiscountvalue) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NDISCOUNTVALUE,
				ndiscountvalue);
	}

	public void setNmny(UFDouble nmny) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NMNY, nmny);
	}

	public void setNnumber(UFDouble nnumber) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NNUMBER, nnumber);
	}

	public void setNorigprice(UFDouble norigprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NORIGPRICE, norigprice);
	}

	public void setNorigtaxprice(UFDouble norigtaxprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NORIGTAXPRICE,
				norigtaxprice);
	}

	public void setNprice(UFDouble nprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NPRICE, nprice);
	}

	public void setNqtnum(UFDouble nqtnum) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTNUM, nqtnum);
	}

	public void setNqtorigmny(UFDouble nqtorigmny) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTORIGMNY, nqtorigmny);
	}

	public void setNqtorigprice(UFDouble nqtorigprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTORIGPRICE,
				nqtorigprice);
	}

	public void setNqtorigtax(UFDouble nqtorigtax) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTORIGTAX, nqtorigtax);
	}

	public void setNqtorigtaxmny(UFDouble nqtorigtaxmny) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTORIGTAXMNY,
				nqtorigtaxmny);
	}

	public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTORIGTAXPRICE,
				nqtorigtaxprice);
	}

	public void setNqtprice(UFDouble nqtprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTPRICE, nqtprice);
	}

	public void setNqttaxprice(UFDouble nqttaxprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NQTTAXPRICE, nqttaxprice);
	}

	public void setNtax(UFDouble ntax) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NTAX, ntax);
	}

	public void setNtaxmny(UFDouble ntaxmny) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NTAXMNY, ntaxmny);
	}

	public void setNtaxprice(UFDouble ntaxprice) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NTAXPRICE, ntaxprice);
	}

	public void setNtaxrate(UFDouble ntaxrate) {
		this.setAttributeValue(BusinessinfoPathViewBVO.NTAXRATE, ntaxrate);
	}

	public void setPk_batchcode(String pk_batchcode) {
		this.setAttributeValue(BusinessinfoPathViewBVO.PK_BATCHCODE,
				pk_batchcode);
	}

	public void setPk_org(UFBoolean pk_org) {
		this.setAttributeValue(BusinessinfoPathViewBVO.PK_ORG, pk_org);
	}

	public void setTs(UFDateTime ts) {
		this.setAttributeValue(BusinessinfoPathViewBVO.TS, ts);
	}

	public void setVbatchcode(String vbatchcode) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VBATCHCODE, vbatchcode);
	}

	public void setVchangerate(String vchangerate) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VCHANGERATE, vchangerate);
	}

	public void setVfree1(String vfree1) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE1, vfree1);
	}

	public void setVfree10(String vfree10) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE10, vfree10);
	}

	public void setVfree2(String vfree2) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE2, vfree2);
	}

	public void setVfree3(String vfree3) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE3, vfree3);
	}

	public void setVfree4(String vfree4) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE4, vfree4);
	}

	public void setVfree5(String vfree5) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE5, vfree5);
	}

	public void setVfree6(String vfree6) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE6, vfree6);
	}

	public void setVfree7(String vfree7) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE7, vfree7);
	}

	public void setVfree8(String vfree8) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE8, vfree8);
	}

	public void setVfree9(String vfree9) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VFREE9, vfree9);
	}

	public void setVqtunitrate(String nqtunitrate) {
		this.setAttributeValue(BusinessinfoPathViewBVO.VQTUNITRATE, nqtunitrate);
	}

}
