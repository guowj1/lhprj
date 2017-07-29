package nc.vo.to.businessinfo.entity.display;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.to.businessinfo.entity.BusinessinfoBBVO;

public class BusinessinfoPathViewHVO extends AbstractDataView {
  // ����ó��
  public static final String BTRIATRADEFLAG = "btriatradeflag";

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

  // �ۿ���
  public static final String NDISCOUNTRATE = "ndiscountrate";
  
  //�ۿ۶� gwj
  public static final String NDISCOUNTVALUE = "ndiscountvalue";

  // ����
  public static final String NEXCHANGERATE = "nexchangerate";

  /**
   * 
   */
  private static final long serialVersionUID = -8182675374366622801L;

  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this
        .getAttributeValue(BusinessinfoPathViewHVO.BTRIATRADEFLAG);
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(BusinessinfoBBVO.CCURRENCYID);
  }

  public String getCdowncostregionid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CDOWNCOSTREGIONID);
  }

  public String getCdownfinanceorgid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CDOWNFINANCEORGID);
  }

  public String getCdownfinanceorgvid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CDOWNFINANCEORGVID);
  }

  public String getCincountryid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CINCOUNTRYID);
  }

  public String getCintaxcountryid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CINTAXCOUNTRYID);
  }

  public String getCorigcurrencyid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CORIGCURRENCYID);
  }

  public String getCoutcountryid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.COUTCOUNTRYID);
  }

  public String getCpathrowno() {
    return (String) this.getAttributeValue(BusinessinfoPathViewHVO.CPATHROWNO);
  }

  public String getCtaxcountryid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CTAXCOUNTRYID);
  }

  public String getCupcostregionid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CUPCOSTREGIONID);
  }

  public String getCupfinanceorgid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CUPFINANCEORGID);
  }

  public String getCupfinanceorgvid() {
    return (String) this
        .getAttributeValue(BusinessinfoPathViewHVO.CUPFINANCEORGVID);
  }

  public Integer getFbuysellflag() {
    return (Integer) this
        .getAttributeValue(BusinessinfoPathViewHVO.FBUYSELLFLAG);
  }

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewmeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            BusinessinfoPathViewHVOMeta.class);
    return viewmeta;
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(BusinessinfoPathViewHVO.NDISCOUNTRATE);
  }
  
  public UFDouble getNdiscountvalue() {
	    return (UFDouble) this
	        .getAttributeValue(BusinessinfoPathViewHVO.NDISCOUNTVALUE);
  }

  public UFDouble getNexchangerate() {
    return (UFDouble) this
        .getAttributeValue(BusinessinfoPathViewHVO.NEXCHANGERATE);
  }

  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(BusinessinfoPathViewHVO.BTRIATRADEFLAG,
        btriatradeflag);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(BusinessinfoBBVO.CCURRENCYID, ccurrencyid);
  }

  public void setCdowncostregionid(String cdowncostregionid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CDOWNCOSTREGIONID,
        cdowncostregionid);
  }

  public void setCdownfinanceorgid(String cdownfinanceorgid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CDOWNFINANCEORGID,
        cdownfinanceorgid);
  }

  public void setCdownfinanceorgvid(String cdownfinanceorgvid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CDOWNFINANCEORGVID,
        cdownfinanceorgvid);
  }

  public void setCincountryid(String cincountryid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CINCOUNTRYID, cincountryid);
  }

  public void setCintaxcountryid(String cintaxcountryid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CINTAXCOUNTRYID,
        cintaxcountryid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CORIGCURRENCYID,
        corigcurrencyid);
  }

  public void setCoutcountryid(String coutcountryid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.COUTCOUNTRYID, coutcountryid);
  }

  public void setCpathrowno(String cpathrowno) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CPATHROWNO, cpathrowno);
  }

  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  public void setCupcostregionid(String cupcostregionid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CUPCOSTREGIONID,
        cupcostregionid);
  }

  public void setCupfinanceorgid(String cupfinanceorgid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CUPFINANCEORGID,
        cupfinanceorgid);
  }

  public void setCupfinanceorgvid(String cupfinanceorgvid) {
    this.setAttributeValue(BusinessinfoPathViewHVO.CUPFINANCEORGVID,
        cupfinanceorgvid);
  }

  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(BusinessinfoPathViewHVO.FBUYSELLFLAG, fbuysellflag);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(BusinessinfoPathViewHVO.NDISCOUNTRATE, ndiscountrate);
  }
  
  public void setNdiscountvalue(UFDouble ndiscountvalue) {
	   this.setAttributeValue(BusinessinfoPathViewHVO.NDISCOUNTVALUE, ndiscountvalue);
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(BusinessinfoPathViewHVO.NEXCHANGERATE, nexchangerate);
  }

}
