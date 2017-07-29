package nc.vo.to.businessinfo.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.to.askprice.AskPriceBizUtils;
import nc.vo.to.businessinfo.entity.BusinessinfoBBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoHVO;
import nc.vo.to.businessinfo.entity.BusinessinfoVO;
import nc.vo.to.pub.util.vat.QueryTriaResultVO;
import nc.vo.to.pub.util.vat.QueryVatCustResultVO;
import nc.vo.to.pub.util.vat.QueryVatParaVO;
import nc.vo.to.pub.util.vat.TOVatRateUtils;
import nc.vo.to.pub.util.vat.TOVatTriaUtils;
import nc.vo.to.settlepath.entity.SettlePathBVO;
import nc.vo.to.settlepath.entity.SettlePathVO;
import nc.vo.to.settlerule.entity.MatchSettleRuleVO;
import nc.vo.to.settlerule.entity.SettleRuleAggVO;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;

import nc.pubitf.to.settlepath.to.IQuerySettlePathService;
import nc.pubitf.to.settlerule.to.IMatchSettleruleService;

import nc.bs.framework.common.NCLocator;

public class BusinessinfoUtils {

  private final String[] bbvoPriceItemKey = new String[] {
    BusinessinfoBBVO.NASKQTORIGPRICE, BusinessinfoBBVO.NASKQTORIGTAXPRICE,
    BusinessinfoBBVO.NORIGPRICE, BusinessinfoBBVO.NORIGTAXPRICE,
    BusinessinfoBBVO.NQTORIGMNY, BusinessinfoBBVO.NQTORIGPRICE,
    BusinessinfoBBVO.NQTORIGTAXMNY, BusinessinfoBBVO.NQTORIGTAXPRICE,
    BusinessinfoBBVO.NCALTAXMNY, BusinessinfoBBVO.NMNY,
    BusinessinfoBBVO.NPRICE, BusinessinfoBBVO.NQTPRICE,
    BusinessinfoBBVO.NQTTAXPRICE, BusinessinfoBBVO.NTAX,
    BusinessinfoBBVO.NTAXMNY, BusinessinfoBBVO.NTAXPRICE
  };

  private final String[] bvoPriceItemKey = new String[] {
    BusinessinfoBVO.NASKBASEPRICE, BusinessinfoBVO.NASKBASETAXPRICE,
    BusinessinfoBVO.NORIGPRICE, BusinessinfoBVO.NORIGTAXPRICE,
    BusinessinfoBVO.NBASEMNY, BusinessinfoBVO.NBASEPRICE,
    BusinessinfoBVO.NBASETAXMNY, BusinessinfoBVO.NBASETAXPRICE,
    BusinessinfoBVO.NCALTAXMNY, BusinessinfoBVO.NMNY, BusinessinfoBVO.NPRICE,
    BusinessinfoBVO.NQTPRICE, BusinessinfoBVO.NQTTAXPRICE,
    BusinessinfoBVO.NTAX, BusinessinfoBVO.NTAXMNY, BusinessinfoBVO.NTAXPRICE
  };

  private final String[] bvoPriceRelationItemKey = new String[] {
    BusinessinfoBVO.NASKBASEPRICE, BusinessinfoBVO.NASKBASETAXPRICE,
    BusinessinfoBVO.NORIGPRICE, BusinessinfoBVO.NORIGTAXPRICE,
    BusinessinfoBVO.NBASEMNY, BusinessinfoBVO.NBASEPRICE,
    BusinessinfoBVO.NBASETAXMNY, BusinessinfoBVO.NBASETAXPRICE,
    BusinessinfoBVO.FBASETAXTYPEFLAG, BusinessinfoBVO.NBASETAXRATE,
    BusinessinfoBVO.CASTUNITID, BusinessinfoBVO.CQTUNITID,
    BusinessinfoBVO.VCHANGERATE, BusinessinfoBVO.NORIGPRICE,
    BusinessinfoBVO.NORIGTAXPRICE, BusinessinfoBVO.VQTUNITRATE,
    BusinessinfoBVO.NNUMBER, BusinessinfoBVO.NASSISTNUM,
    BusinessinfoBVO.NQTNUM, BusinessinfoBVO.NPRICE, BusinessinfoBVO.NCALTAXMNY,
    BusinessinfoBVO.NMNY, BusinessinfoBVO.NQTPRICE,
    BusinessinfoBVO.NQTTAXPRICE, BusinessinfoBVO.NTAX,
    BusinessinfoBVO.NTAXPRICE, BusinessinfoBVO.NTAXMNY
  };

  private final Map<String, SettlePathVO> settlePathIndex =
      new HashMap<String, SettlePathVO>();

  /** ����ӱ�VO��Ӧ���������ӱ�VO�ϵļ۸���Ϣ */
  public void clearBBVOPrice(BusinessinfoBVO bvo) {
    this.clearVOValue(bvo.getBBVO(), this.bbvoPriceItemKey);
  }

  /** ����ӱ�VO��Ӧ���������ӱ�VO�ϵļ۸���Ϣ */
  public void clearBVOPrice(BusinessinfoBVO bvo) {
    this.clearVOValue(new SuperVO[] {
      bvo
    }, this.bvoPriceItemKey);
  }

  public void cpBVOprice(BusinessinfoBVO from, BusinessinfoBVO to) {
    String[] attrNames = this.bvoPriceRelationItemKey;
    for (String attrName : attrNames) {
      to.setAttributeValue(attrName, from.getAttributeValue(attrName));
    }
    to.setStatus(VOStatus.UPDATED);
  }

  public void cpBVOvalue(BusinessinfoBVO from, BusinessinfoBVO to) {
    String[] attrNames = from.getAttributeNames();
    for (String attrName : attrNames) {
      // ����ԭ�к�
      if (attrName.equals(BusinessinfoBVO.BBVO)
          || attrName.equals(BusinessinfoBVO.CBUSINESS_BID)
          || attrName.equals(BusinessinfoBVO.CBUSINESSID)
          || attrName.equals(BusinessinfoBVO.CROWNO)
          || attrName.equals(BusinessinfoBVO.CSRCSRCBID)
          || attrName.equals(BusinessinfoBVO.TS)) {
        continue;
      }
      to.setAttributeValue(attrName, from.getAttributeValue(attrName));
    }
    to.setStatus(VOStatus.UPDATED);
  }

  public void dealSettlePath(BusinessinfoHVO hvo, BusinessinfoBVO bvo) {
    String spid = hvo.getCsettlepathid();
    if (spid == null) {
      bvo.setBBVO(null);
    }
    else {
      SettlePathVO spvo = this.getSettlePath(hvo.getCsettlepathid());

      Map<String, String> corpMap = this.dealCorpByOrg(hvo, spvo);

      Map<String, String> pathcoutryMap = this.getPathCountry(spvo);

      this.generalBBVO(hvo, bvo, spvo, corpMap, pathcoutryMap);
      this.queryTaxCodeRate(new BusinessinfoBVO[] {
        bvo
      });

      BusinessinfoVO bizvo = new BusinessinfoVO();
      bizvo.setParent(hvo);
      bizvo.setChildrenVO(new BusinessinfoBVO[] {
        bvo
      });
      this.updateBizvoSettleRule(bizvo);
      if (bizvo.getParentVO().getBbasediscountflag().booleanValue()) {
        this.calculateDiscount(hvo, bvo);
      }
      else {
        this.clearBVOPrice(bvo);
      }
    }
  }

  public void dealSettlePath(BusinessinfoVO bizvo, boolean needCalculateDiscount) {

    String csettlepathid = bizvo.getParentVO().getCsettlepathid();
    if (csettlepathid != null) {

      SettlePathVO vo = this.getSettlePath(csettlepathid);
      boolean discountflag =
          vo.getHead().getBdiscountflag() == null ? false : vo.getHead()
              .getBdiscountflag().booleanValue();
      bizvo.getParentVO().setBbasediscountflag(UFBoolean.valueOf(discountflag));
      if (!discountflag) {
        BusinessinfoBVO[] bvos = bizvo.getChildrenVO();
        for (BusinessinfoBVO bvo : bvos) {
          this.clearBVOPrice(bvo);
          this.clearBBVOPrice(bvo);
        }
      }
      this.generalBBVO(bizvo, vo);
      if (bizvo.getParentVO().getBbasediscountflag().booleanValue()
          && needCalculateDiscount) {
        for (BusinessinfoBVO bvo : bizvo.getChildrenVO()) {
          this.calculateDiscount(bizvo.getParentVO(), bvo);
        }
      }
      else if (needCalculateDiscount) {
        BusinessinfoBVO[] bvos = bizvo.getChildrenVO();
        for (BusinessinfoBVO bvo : bvos) {
          bvo.setBneedaskprice(UFBoolean.FALSE);
        }
        // ѯ��
        AskPriceBizUtils.askPrice(new BusinessinfoVO[] {
          bizvo
        });
      }
    }
    else {
      bizvo.getParentVO().setBbasediscountflag(null);
      this.clearBbvo(bizvo);
    }
  }

  /** ����ָ���Ľ���·����ͷid�����ڲ�����VO�������ӱ�VO������۸���Ϣ,�����������α��֡����� */
  public BusinessinfoVO[] dealSettlePath(BusinessinfoVO[] bizvos,
      boolean needCalculateDiscount) {
    for (BusinessinfoVO vo : bizvos) {
      this.dealSettlePath(vo, needCalculateDiscount);
    }
    return bizvos;
  }

  public void resetVostatus(BusinessinfoVO[] vos) {
    for (BusinessinfoVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UNCHANGED);
      for (BusinessinfoBVO bvo : vo.getChildrenVO()) {
        bvo.setStatus(VOStatus.UNCHANGED);
        BusinessinfoBBVO[] bbvos = bvo.getBBVO();
        if (bbvos != null) {
          for (BusinessinfoBBVO bbvo : bbvos) {
            bbvo.setStatus(VOStatus.UNCHANGED);
          }
        }
      }
    }
  }

  /**
   * ����ƥ�������򣬲����ݽ���·�������ϵ���������vo�����ֶ�ֵ�� <br>
   * ��˰���˰�ʡ�ѯ�ۺ�۸��Ƿ�ɸġ��Ӽ��ʡ�ѯ�۹���<br>
   * ʹ�ó�����1��·����ѯ��ǰ�ȸ���bizvo��Ȼ������ѯ��vo<br>
   * 2������hvo��bvo��settlepath����bbvo�󣬸����ۿ��ʼ���ǰ������ĳЩ�ֶ�ֵ
   */
  public void updateBizvoSettleRule(BusinessinfoVO vo) {
    BusinessinfoHVO hvo = vo.getParentVO();
    if (hvo.getCsettlepathid() == null) {
      return;
    }
    SettlePathVO sp = this.getSettlePath(hvo.getCsettlepathid());
    Map<String, SettlePathBVO> spIndex = this.getSpIndex(sp);
    List<MatchSettleRuleVO> msr = new ArrayList<MatchSettleRuleVO>();
    for (BusinessinfoBVO bvo : vo.getChildrenVO()) {
      if (bvo.getBBVO() == null) {
        ExceptionUtils.unSupported();
      }
      for (BusinessinfoBBVO bbvo : bvo.getBBVO()) {
        MatchSettleRuleVO matchVO = this.constructMSR(hvo, bvo, bbvo);
        msr.add(matchVO);
      }
    }
    if (!UFBoolean.TRUE.equals(hvo.getBbasediscountflag())) {
      IMatchSettleruleService service =
          NCLocator.getInstance().lookup(IMatchSettleruleService.class);
      Map<String, SettleRuleAggVO> srIndex = null;
      try {
        srIndex = service.matchSettlerule2(msr);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
        srIndex = new HashMap<String, SettleRuleAggVO>();
      }
      for (BusinessinfoBVO bvo : vo.getChildrenVO()) {
        for (BusinessinfoBBVO bbvo : bvo.getBBVO()) {
          SettlePathBVO spb = spIndex.get(bbvo.getCsettlepath_bid());
          SettleRuleAggVO sr = srIndex.get(this.getMatchSRKey(hvo, bvo, bbvo));
          if (sr == null) {
            ExceptionUtils
                .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                    .getNCLangRes().getStrByID("4009010_0", "04009010-0042")/*@res "����·���ϵ���֯δƥ�䵽�ڲ�������������ú��ʵ��ڲ��������"*/);
            return;
          }

          // ����ѯ�ۺ�۸��Ƿ�ɸģ�ȡ�۹��򣬼Ӽ���
          bbvo.setBmodifypriceflag(sr.getBVO()[0].getBmodifyprice());
          bbvo.setVpricerule(sr.getBVO()[0].getVpricerule());
          // bbvo.setAttributeValue(BusinessinfoBBVO.VPRICERULE,
          // sr.getBVO()[0].getVpricerule());
          bbvo.setCsettlepath_bid(spb.getCsettlepath_bid());
          bbvo.setCsettlerule_bid(sr.getBVO()[0].getCsettlerule_bid());
          bbvo.setNaddpricerate(sr.getBVO()[0].getNaddpricerate());
        }
      }
    }
    else {
      for (BusinessinfoBVO bvo : vo.getChildrenVO()) {
        for (BusinessinfoBBVO bbvo : bvo.getBBVO()) {
          bbvo.setNaddpricerate(UFDouble.ZERO_DBL);
        }
      }

    }
  }

  private void calculateDiscount(BusinessinfoHVO bizhvo, BusinessinfoBVO bizbvo) {
    BusiinfoMoneyPriceUtils.calcBBVOByDisaccount(bizhvo, bizbvo);
  }

  private void clearBbvo(BusinessinfoVO bizvo) {
    for (BusinessinfoBVO bvo : bizvo.getChildrenVO()) {
      bvo.setBBVO(null);
    }
  }

  private void clearVOValue(SuperVO[] vos, String[] itemkey) {

    if (vos == null) {
      return;
    }
    for (SuperVO vo : vos) {
      for (String attributeName : itemkey) {
        vo.setAttributeValue(attributeName, null);
      }
      vo.setStatus(VOStatus.UPDATED);
    }
  }

  private MatchSettleRuleVO constructMSR(BusinessinfoHVO hvo,
      BusinessinfoBVO bvo, BusinessinfoBBVO bbvo) {
    MatchSettleRuleVO matchvo = new MatchSettleRuleVO();
    matchvo.setCinfinanceorgid(bbvo.getCdownfinanceorgid());
    matchvo.setCinventoryid(bvo.getCinventoryid());
    matchvo.setCoutfinanceorgid(bbvo.getCupfinanceorgid());
    matchvo.setCtranstype(hvo.getVsrctrantypecode());
    matchvo.setFsettletype(hvo.getFsettletypeflag());
    matchvo.setNegative(hvo.getBreverseflag());
    matchvo.setPk_group(hvo.getPk_group());
    matchvo.setSid(this.getMatchSRKey(hvo, bvo, bbvo));
    return matchvo;
  }

  /**
   * 
   * ����˵���� <li>���ݽ���·���ϡ����β�����֯����ȡ��Ӧ��������˾���� <li>
   * ����ʹ�ó�����Ŀ�ģ���������ӱ���Ϣʱ��������ϡ����β�����֯��Ӧ��˾����������ӱ�ı�˰����Ϣ
   * 
   * @since 6.1
   * @version 2012-1-4����10:47:03
   * @author ����
   * 
   * @param vo
   * @return
   */
  private Map<String, String> dealCorpByOrg(BusinessinfoHVO hvo, SettlePathVO vo) {

    Map<String, String> corpMap = new HashMap<String, String>();
    Set<String> financeorgs = new HashSet<String>();
    SettlePathBVO[] pathbvos = vo.getItem();
    financeorgs.add(hvo.getCinfinanceorgid());
    financeorgs.add(hvo.getPk_org());
    for (SettlePathBVO pathbvo : pathbvos) {
      financeorgs.add(pathbvo.getCupfinanceorgid());
      financeorgs.add(pathbvo.getCdownfinanceorgid());
    }

    corpMap =
        FinanceOrgPubService.queryPKCorp(financeorgs.toArray(new String[0]));

    return corpMap;
  }

  private void fillFinVid(BusinessinfoBBVO[] bbvos) {
    Set<String> orgset = new HashSet<String>();
    for (BusinessinfoBBVO bbvo : bbvos) {
      String upfiorgvid = bbvo.getCupfinanceorgvid();
      String downfiorgvid = bbvo.getCdownfinanceorgvid();
      if (PubAppTool.isNull(upfiorgvid)) {
        orgset.add(bbvo.getCupfinanceorgid());
      }
      if (PubAppTool.isNull(downfiorgvid)) {
        orgset.add(bbvo.getCdownfinanceorgid());
      }

    }
    if (orgset.size() == 0) {
      return;
    }
    Map<String, String> orgmapvids =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgset.toArray(new String[orgset
            .size()]));
    for (BusinessinfoBBVO bbvo : bbvos) {

      String upfiorgvid = bbvo.getCupfinanceorgvid();
      String downfiorgvid = bbvo.getCdownfinanceorgvid();
      if (PubAppTool.isNull(upfiorgvid)) {
        bbvo.setCupfinanceorgvid(orgmapvids.get(bbvo.getCupfinanceorgid()));

      }
      if (PubAppTool.isNull(downfiorgvid)) {
        bbvo.setCdownfinanceorgvid(orgmapvids.get(bbvo.getCdownfinanceorgid()));
      }

    }

  }

  private void generalBBVO(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      SettlePathVO vo, Map<String, String> corpMap,
      Map<String, String> pathcoutryMap) {
    SettlePathBVO[] spbodys = vo.getItem();
    int splength = spbodys.length;
    BusinessinfoBBVO[] bbvos = new BusinessinfoBBVO[splength];
    bvo.setBBVO(bbvos);
    for (int i = 0; i < splength; i++) {
      SettlePathBVO spbody = spbodys[i];
      BusinessinfoBBVO bbvo = new BusinessinfoBBVO();
      bbvos[i] = bbvo;
      bbvo.setPk_group(hvo.getPk_group());
      bbvo.setBneedaskprice(UFBoolean.FALSE);
      bbvo.setBneedconvertcurr(UFBoolean.FALSE);
      bbvo.setCbusiness_bid(bvo.getCbusiness_bid());
      bbvo.setCbusinessid(bvo.getCbusinessid());
      bbvo.setCdowncostregionid(spbody.getCdowncostregionid());
      /* if (spbody.getCdownfinanceorgid().equals(hvo.getCinfinanceorgid())) {
         bbvo.setCdownfinanceorgid(hvo.getCinfinanceorgid());
         bbvo.setCdownfinanceorgvid(hvo.getCinfinanceorgvid());
       }
       else {*/
      bbvo.setCdownfinanceorgid(spbody.getCdownfinanceorgid());
      bbvo.setCdownfinanceorgvid(spbody.getCdownfinanceorgvid());
      // }

      if (spbody.getCcurrencyid() != null) {
        bbvo.setCorigcurrencyid(spbody.getCcurrencyid());
      }
      else if (spbody.getCupfinanceorgid() != null) {
        bbvo.setCorigcurrencyid(hvo.getCcurrencyid());
      }
      else {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4009010_0", "04009010-0039")/*@res "�������ҵ�񵥾�û�б���"*/);
      }
      bbvo.setCpathrowno(spbody.getCrowno());
      bbvo.setCsettlepath_bid(spbody.getCsettlepath_bid());
      bbvo.setCupcostregionid(spbody.getCupcostregionid());
      /* if (spbody.getCupfinanceorgid().equals(hvo.getPk_org())) {
         bbvo.setCupfinanceorgid(hvo.getPk_org());
         bbvo.setCupfinanceorgvid(hvo.getPk_org_v());
       }
       else {*/
      bbvo.setCupfinanceorgid(spbody.getCupfinanceorgid());
      bbvo.setCupfinanceorgvid(spbody.getCupfinanceorgvid());
      // }

      // δѯ��
      bbvo.setNaskqtorigprice(null);
      bbvo.setNaskqtorigtaxprice(null);
      bbvo.setNdiscountrate(spbody.getNdiscountrate());
      //�ۿ۶� gwj
      bbvo.setNdiscountvalue(spbody.getNdiscountvalue());
      String ccur =
          OrgUnitPubService.queryOrgCurrByPk(spbody.getCupfinanceorgid());
      bbvo.setCcurrencyid(ccur);

      UFDouble rate =
          CurrencyRate.getCurrencyRateByOrg(bbvo.getCupfinanceorgid(), bbvo
              .getCorigcurrencyid(), ccur, AppContext.getInstance()
              .getBusiDate());
      if (rate == null || rate.equals(UFDouble.ZERO_DBL)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4009003_0", "04009003-0186")/*@res "û��ȡ�����ʣ������á�"*/);
      }
      bbvo.setNexchangerate(rate);
      // �������ӱ�vat����ֶ�ֵ
      this.setVatValue(hvo, bvo, bbvo, corpMap, pathcoutryMap);
    }

    this.fillFinVid(bbvos);

  }

  private void generalBBVO(BusinessinfoVO bizvo, SettlePathVO vo) {
    if (vo == null) {
      return;
    }
    BusinessinfoBVO[] bvos = bizvo.getChildrenVO();
    BusinessinfoHVO hvo = bizvo.getParentVO();

    hvo.setBbasediscountflag(vo.getHead().getBdiscountflag());

    Map<String, String> corpMap = this.dealCorpByOrg(hvo, vo);

    Map<String, String> pathcoutryMap = this.getPathCountry(vo);
    for (BusinessinfoBVO bvo : bvos) {
      this.generalBBVO(hvo, bvo, vo, corpMap, pathcoutryMap);
    }
    // ȡ˰�롢˰�ʡ���˰��𣬲���ֵ
    this.queryTaxCodeRate(bvos);

    // ����settlepathvo
    this.updateBizvoSettleRule(bizvo);
  }

  private Map<String, String> getCustMap(BusinessinfoBVO[] bvos) {

    Set<String> set = new HashSet<String>();
    for (BusinessinfoBVO bvo : bvos) {
      BusinessinfoBBVO[] bbvos = bvo.getBBVO();
      for (BusinessinfoBBVO bbvo : bbvos) {
        set.add(bbvo.getCdownfinanceorgid());
      }
    }
    Map<String, String> map =
        CustomerPubService.queryCusPkByOrgPk(set.toArray(new String[0]));
    return map;
  }

  private String getMatchSRKey(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      BusinessinfoBBVO bbvo) {
    return hvo.getCinfinanceorgvid() + hvo.getPk_org_v()
        + bbvo.getCsettlepath_bid() + bvo.getCrowno() + bvo.getCinventoryid();
  }

  private Map<String, String> getPathCountry(SettlePathVO spvo) {
    SettlePathBVO[] items = spvo.getItem();
    Set<String> set = new HashSet<String>();
    for (SettlePathBVO item : items) {
      set.add(item.getCupfinanceorgid());
      set.add(item.getCdownfinanceorgid());
    }
    Map<String, String> map =
        FinanceOrgPubService.queryCountryByFinanceOrg(set
            .toArray(new String[set.size()]));
    return map;

  }

  private SettlePathVO getSettlePath(String id) {
    SettlePathVO vo = this.settlePathIndex.get(id);
    if (vo == null) {
      vo = this.getSettlePathFromDB(id);
      if (vo == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4009010_0", "04009010-0040")/*@res "�Ҳ���ָ������·��"*/);
      }
      this.settlePathIndex.put(id, vo);
    }
    return vo;
  }

  private SettlePathVO getSettlePathFromDB(String id) {
    IQuerySettlePathService bo =
        NCLocator.getInstance().lookup(IQuerySettlePathService.class);
    return bo.query(id, true);
  }

  private Map<String, SettlePathBVO> getSpIndex(SettlePathVO sp) {
    Map<String, SettlePathBVO> ret = new HashMap<String, SettlePathBVO>();
    for (SettlePathBVO spb : sp.getItem()) {
      ret.put(spb.getPrimaryKey(), spb);
    }
    return ret;
  }

  /**
   * 
   * ����˵���� <li>ȡ˰�롢˰�ʡ���˰��𣬲���ֵ
   * 
   * @since 6.1
   * @version 2012-1-4����03:21:43
   * @author ����
   * 
   * @param hvo ����vo
   * @param bvos �ӱ�vo
   */
  private void queryTaxCodeRate(BusinessinfoBVO[] bvos) {
    TOVatRateUtils vatRateUtil = new TOVatRateUtils();
    List<QueryVatParaVO> paralist = new ArrayList<QueryVatParaVO>();

    Map<BusinessinfoBBVO, QueryVatParaVO> bbMaps =
        new HashMap<BusinessinfoBBVO, QueryVatParaVO>();
    Map<String, String> custmap = this.getCustMap(bvos);
    for (BusinessinfoBVO bvo : bvos) {
      BusinessinfoBBVO[] bbvos = bvo.getBBVO();
      for (BusinessinfoBBVO bbvo : bbvos) {
        QueryVatParaVO vatParaVo = new QueryVatParaVO();
        paralist.add(vatParaVo);
        bbMaps.put(bbvo, vatParaVo);
        vatParaVo.setCinventoryid(bvo.getCinventoryid());
        vatParaVo.setCtaxcountryid(bbvo.getCtaxcountryid());
        vatParaVo.setCoutcountryid(bbvo.getCoutcountryid());
        vatParaVo.setCincountryid(bbvo.getCincountryid());
        vatParaVo.setFbuysellflag(bbvo.getFbuysellflag());
        vatParaVo.setBtriatradeflag(bbvo.getBtriatradeflag());
        vatParaVo.setDbilldate(bvo.getDbilldate());
        vatParaVo.setCcustomerid(custmap.get(bbvo.getCdownfinanceorgid()));
        vatParaVo.setCinfinanceorgid(bbvo.getCdownfinanceorgid());
        vatParaVo.setCoutfinanceorgid(bbvo.getCupfinanceorgid());

      }
    }
    Map<QueryVatParaVO, QueryVatCustResultVO> resultMapVOs =
        vatRateUtil.queryCustTaxCodeRate(paralist
            .toArray(new QueryVatParaVO[0]));
    if (resultMapVOs.size() == 0) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4009010_0",
              "04009010-0051")/*@res "δ�������·����˰�룬�����ã�"*/;
      ExceptionUtils.wrappBusinessException(message);
    }

    this.setVATRate(bvos, bbMaps, resultMapVOs);
  }

  private void setVATRate(BusinessinfoBVO[] bvos,
      Map<BusinessinfoBBVO, QueryVatParaVO> bbMaps,
      Map<QueryVatParaVO, QueryVatCustResultVO> resultMapVOs) {
    for (BusinessinfoBVO bvo : bvos) {
      BusinessinfoBBVO[] bbvos = bvo.getBBVO();
      for (BusinessinfoBBVO bbvo : bbvos) {
        QueryVatParaVO vatParaVo = bbMaps.get(bbvo);
        if (!resultMapVOs.containsKey(vatParaVo)) {
          String message =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4009010_0", "04009010-0051")/*@res "δ�������·����˰�룬�����ã�"*/;
          ExceptionUtils.wrappBusinessException(message);
        }
        QueryVatCustResultVO vatResultVo = resultMapVOs.get(vatParaVo);

        bbvo.setCtaxcodeid(vatResultVo.getCtaxcodeid());
        bbvo.setNtaxrate(vatResultVo.getNtaxrate());
        bbvo.setFtaxtypeflag(vatResultVo.getFtaxtypeflag());
      }
    }
  }

  /**
   * 
   * ����˵���� <li>v61����������vat˰��ģ�͹��� <li>��֯���ӱ�vat����ֶε�ֵ
   * 
   * @since 6.1
   * @version 2012-1-4����10:16:35
   * @author ����
   * 
   * @param hvo �ڲ�������Ϣ����
   * @param bvo �ڲ�������Ϣ�ӱ�
   * @param bbvo �ڲ�������Ϣ���ӱ�
   * @param spbody ����·���ӱ�
   * @param corpMap ������֯������˾map<pk_org,corp>
   */
  private void setVatValue(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      BusinessinfoBBVO bbvo, Map<String, String> corpMap,
      Map<String, String> pathcoutryMap) {
    TOVatTriaUtils vatTriaUtil = new TOVatTriaUtils();
    bbvo.setCoutcountryid(hvo.getCoutcountryid());
    bbvo.setCincountryid(hvo.getCincountryid());
    // ��˰����/����
    if (PubAppTool.isEqual(corpMap.get(bbvo.getCupfinanceorgid()),
        corpMap.get(hvo.getPk_org()))) {
      bbvo.setCtaxcountryid(hvo.getCtaxcountryid());
    }
    else if (PubAppTool.isEqual(corpMap.get(bbvo.getCupfinanceorgid()),
        corpMap.get(hvo.getCinfinanceorgid()))) {
      bbvo.setCtaxcountryid(hvo.getCintaxcountryid());
    }
    else {
      bbvo.setCtaxcountryid(pathcoutryMap.get(bbvo.getCupfinanceorgid()));
    }
    // �ɹ�����˰����/����
    if (PubAppTool.isEqual(corpMap.get(bbvo.getCdownfinanceorgid()),
        corpMap.get(hvo.getPk_org()))) {
      bbvo.setCintaxcountryid(hvo.getCtaxcountryid());
    }
    else if (PubAppTool.isEqual(corpMap.get(bbvo.getCdownfinanceorgid()),
        corpMap.get(hvo.getCinfinanceorgid()))) {
      bbvo.setCintaxcountryid(hvo.getCintaxcountryid());
    }
    else {
      bbvo.setCintaxcountryid(pathcoutryMap.get(bbvo.getCdownfinanceorgid()));
    }

    QueryTriaResultVO result =
        vatTriaUtil.getAllSellFlagForPreSettle(bbvo.getCintaxcountryid(),
            bbvo.getCtaxcountryid(), bbvo.getCoutcountryid());
    // ��������
    bbvo.setFbuysellflag(result.getBuysellflag());
    // ����ó��
    bbvo.setBtriatradeflag(result.getIstria());
  }
}
