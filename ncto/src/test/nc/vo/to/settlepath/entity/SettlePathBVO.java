package nc.vo.to.settlepath.entity;

/**
 * 功能说明: 基于SmartVO的业务VO.结算路径子表,
 * 调试时可以使用nc.vo.scm.bd.SmartVODataUtils.expose()展示其数据。 Generated By CodeSeed 3.1
 * 修改记录： 修改人, 日期, 说明
 */
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SettlePathBVO extends SuperVO {
  // 结算币种
  public static final String CCURRENCYID = "ccurrencyid";

  // 下游成本域
  public static final String CDOWNCOSTREGIONID = "cdowncostregionid";

  // 下游财务组织
  public static final String CDOWNFINANCEORGID = "cdownfinanceorgid";

  // 行号
  public static final String CROWNO = "crowno";

  // 子表主键
  public static final String CSETTLEPATH_BID = "csettlepath_bid";

  // 结算路径主表_主键
  public static final String CSETTLEPATHID = "csettlepathid";

  // 上游成本域
  public static final String CUPCOSTREGIONID = "cupcostregionid";

  // 上游财务组织
  public static final String CUPFINANCEORGID = "cupfinanceorgid";

  // 折扣率
  public static final String NDISCOUNTRATE = "ndiscountrate";
  
  //折扣额
  public static final String NDISCOUNTVALUE = "ndiscountvalue";

  // 集团
  public static final String PK_GROUP = "pk_group";

  private static final long serialVersionUID = 6514100929397830427L;

  // 时间戳
  public static final String TS = "ts";

  private String cdownfinanceorgvid;

  private String cupfinanceorgvid;

  // // 上游财务组织版本
  // public static final String CUPFINANCEORGVID = "cupfinanceorgvid";
  //
  // // 下游财务组织版本
  // public static final String CDOWNFINANCEORGVID = "cdownfinanceorgvid";

  public SettlePathBVO() {
    super();
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SettlePathBVO.CCURRENCYID);
  }

  public String getCdowncostregionid() {
    return (String) this.getAttributeValue(SettlePathBVO.CDOWNCOSTREGIONID);
  }

  public String getCdownfinanceorgid() {
    return (String) this.getAttributeValue(SettlePathBVO.CDOWNFINANCEORGID);
  }

  public String getCdownfinanceorgvid() {
    return this.cdownfinanceorgvid;
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SettlePathBVO.CROWNO);
  }

  public String getCsettlepath_bid() {
    return (String) this.getAttributeValue(SettlePathBVO.CSETTLEPATH_BID);
  }

  public String getCsettlepathid() {
    return (String) this.getAttributeValue(SettlePathBVO.CSETTLEPATHID);
  }

  public String getCupcostregionid() {
    return (String) this.getAttributeValue(SettlePathBVO.CUPCOSTREGIONID);
  }

  public String getCupfinanceorgid() {
    return (String) this.getAttributeValue(SettlePathBVO.CUPFINANCEORGID);
  }

  public String getCupfinanceorgvid() {
    return this.cupfinanceorgvid;
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("to.to_settlepath_b");
    return meta;
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SettlePathBVO.NDISCOUNTRATE);
  }
  
  public UFDouble getNdiscountvalue() {
	    return (UFDouble) this.getAttributeValue(SettlePathBVO.NDISCOUNTVALUE);
	  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SettlePathBVO.PK_GROUP);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettlePathBVO.TS);
  }

  public void setCcurrencyid(String ccurrency) {
    this.setAttributeValue(SettlePathBVO.CCURRENCYID, ccurrency);
  }

  public void setCdowncostregionid(String cdowncostregionid) {
    this.setAttributeValue(SettlePathBVO.CDOWNCOSTREGIONID, cdowncostregionid);
  }

  public void setCdownfinanceorgid(String cdownfinanceorgid) {
    this.setAttributeValue(SettlePathBVO.CDOWNFINANCEORGID, cdownfinanceorgid);
  }

  public void setCdownfinanceorgvid(String cdownfinanceorgvid) {
    this.cdownfinanceorgvid = cdownfinanceorgvid;
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SettlePathBVO.CROWNO, crowno);
  }

  public void setCsettlepath_bid(String csettlepath_bid) {
    this.setAttributeValue(SettlePathBVO.CSETTLEPATH_BID, csettlepath_bid);
  }

  public void setCsettlepathid(String csettlepathid) {
    this.setAttributeValue(SettlePathBVO.CSETTLEPATHID, csettlepathid);
  }

  public void setCupcostregionid(String cupcostregionid) {
    this.setAttributeValue(SettlePathBVO.CUPCOSTREGIONID, cupcostregionid);
  }

  public void setCupfinanceorgid(String cupfinanceorgid) {
    this.setAttributeValue(SettlePathBVO.CUPFINANCEORGID, cupfinanceorgid);
  }

  public void setCupfinanceorgvid(String cupfinanceorgvid) {
    this.cupfinanceorgvid = cupfinanceorgvid;
  }

  public void setNdiscountrate(UFDouble ndiscount) {
    this.setAttributeValue(SettlePathBVO.NDISCOUNTRATE, ndiscount);
  }
  
  public void setNdiscountvalue(UFDouble ndiscountvalue) {
	    this.setAttributeValue(SettlePathBVO.NDISCOUNTVALUE, ndiscountvalue);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SettlePathBVO.PK_GROUP, pk_group);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettlePathBVO.TS, ts);
  }

}
