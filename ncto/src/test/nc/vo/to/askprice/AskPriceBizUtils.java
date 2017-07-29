package nc.vo.to.askprice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.to.askprice.bizsettle.BizinfoPriceSetter;
import nc.vo.to.businessinfo.entity.BusinessinfoBBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoHVO;
import nc.vo.to.businessinfo.entity.BusinessinfoVO;
import nc.vo.to.settlerule.entity.SettleRuleAggVO;
import nc.vo.to.settlerule.entity.SettleRuleBVO;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.to.askprice.ITOAskPriceService;

import nc.pubitf.to.settlerule.to.IQuerySettleruleService;

import nc.bs.framework.common.NCLocator;

public class AskPriceBizUtils {

  public static BusinessinfoVO[] askPrice(BusinessinfoVO[] bills) {
    // 过滤出需要询价的数据
    BusinessinfoVO[] vos = AskPriceBizUtils.getNeedAskPriceVO(bills);
    if (vos.length == 0) {
      return bills;
    }
    // 组织询价参数
    AskPriceVO[] askVOs = AskPriceBizUtils.constructAskPriceVO(vos);
    // 设置询价VO itemkey
    int i = 0;
    for (AskPriceVO askvo : askVOs) {
      askvo.setCitemkey(String.valueOf(i++));
    }
    // 调用询价算法
    ITOAskPriceService service =
        NCLocator.getInstance().lookup(ITOAskPriceService.class);
    Map<AskPriceVO, AskPriceResultVO> mapPrice =
        service.askPriceOnTOInfo(askVOs);
    // 设置价格并计算单价金额
    BizinfoPriceSetter pricesetter = new BizinfoPriceSetter();
    pricesetter.setPrices(vos, mapPrice, askVOs);
    return bills;
  }

  private static AskPriceVO constructAskpriceVO(
      Map<String, SettleRuleAggVO> map, Map<String, String> map_custmer,
      BusinessinfoHVO hvo, BusinessinfoBVO bvo) {
    SettleRuleAggVO srvo = map.get(bvo.getCsettlerule_bid());
    SettleRuleBVO srbvo = srvo.getBVO()[0];
    AskPriceVO askPriceVO = new AskPriceVO();
    askPriceVO.setBilldate(hvo.getDsrcbilldate());
    askPriceVO.setCitemkey(bvo.getCbusiness_bid());
    askPriceVO.setCquoteunitid(bvo.getCqtunitid());
    askPriceVO.setCtranstype(hvo.getVsrctrantypecode());
    askPriceVO.setDestOrigCurr(hvo.getCcurrencyid());
    askPriceVO.setFpriceruleflag(srbvo.getVpricerule());
    askPriceVO.setNaddpricerate(srbvo.getNaddpricerate());
    askPriceVO.setNaddpricevalue(srbvo.getNaddpricevalue());//固定加价额 gwj
    askPriceVO.setFsettletype(hvo.getFsettletypeflag());
    askPriceVO.setIncb(hvo.getCinstockorgid());
    askPriceVO.setInfinanceorg(hvo.getCinfinanceorgid());
    askPriceVO.setInventoryid(bvo.getCinventoryid());
    askPriceVO.setInventoryvid(bvo.getCinventoryvid());
    askPriceVO.setNegative(hvo.getBreverseflag());
    askPriceVO.setOutcb(hvo.getCoutstockorgid());
    askPriceVO.setOutfinanceorg(hvo.getPk_org());
    askPriceVO.setPk_batchcode(bvo.getPk_batchcode());
    askPriceVO.setVbatchcode(bvo.getVbatchcode());
    askPriceVO.setCustomerid(map_custmer.get(hvo.getCinfinanceorgid()));
    askPriceVO.setPk_group(bvo.getPk_group());
    askPriceVO.setPk_org(hvo.getPk_org());
    askPriceVO.setPk_org_type(AskPriceVO.PK_ORG_TYPE_FINANCE);
    askPriceVO.setCsendtypeid(bvo.getCsendtypeid());
    askPriceVO.setNqtnum(bvo.getNqtnum());
    askPriceVO.setVfree1(bvo.getVfree1());
    askPriceVO.setVfree2(bvo.getVfree2());
    askPriceVO.setVfree3(bvo.getVfree3());
    askPriceVO.setVfree4(bvo.getVfree4());
    askPriceVO.setVfree5(bvo.getVfree5());
    askPriceVO.setVfree6(bvo.getVfree6());
    askPriceVO.setVfree7(bvo.getVfree7());
    askPriceVO.setVfree8(bvo.getVfree8());
    askPriceVO.setVfree9(bvo.getVfree9());
    askPriceVO.setVfree10(bvo.getVfree10());
    askPriceVO.setOrderbid(bvo.getCsrcbid());
//    askPriceVO.setNaddpricerate(srbvo.getNaddpricerate());
    return askPriceVO;
  }

  private static AskPriceVO constructAskpriceVO(
      Map<String, String> map_custmer, BusinessinfoHVO hvo,
      BusinessinfoBVO bvo, BusinessinfoBBVO bbvo) {
    AskPriceVO askPriceVO = new AskPriceVO();
    askPriceVO.setBilldate(hvo.getDsrcbilldate());
    askPriceVO.setCitemkey(bbvo.getCbusiness_bbid());
    // askPriceVO.setCsettleruleid(bvo.getCsettleruleid());
    // askPriceVO.setCsettlerulebid(bvo.getCsettlerule_bid());
    askPriceVO.setCquoteunitid(bvo.getCqtunitid());
    askPriceVO.setCtranstype(hvo.getVsrctrantypecode());
    askPriceVO.setDestOrigCurr(bbvo.getCorigcurrencyid());
    askPriceVO.setFpriceruleflag(bbvo.getVpricerule());
    askPriceVO.setNaddpricerate(bbvo.getNaddpricerate());
    askPriceVO.setFsettletype(hvo.getFsettletypeflag());
    if (hvo.getCinfinanceorgid().equals(bbvo.getCdownfinanceorgid())) {
      askPriceVO.setIncb(hvo.getCinstockorgid());
    }
    /*  // 为支持结算路径大于2时询价
      else if (!hvo.getCinfinanceorgid().equals(bbvo.getCdownfinanceorgid())
          && hvo.getPk_org().equals(bbvo.getCupfinanceorgid())) {
        askPriceVO.setOutcb(bbvo.getCupfinanceorgid());
      }*/
    if (hvo.getPk_org().equals(bbvo.getCupfinanceorgid())) {
      askPriceVO.setOutcb(hvo.getCoutstockorgid());
    }
    else {
      askPriceVO.setOutcb(bbvo.getCupfinanceorgid());
    }

    askPriceVO.setInfinanceorg(bbvo.getCdownfinanceorgid());
    askPriceVO.setInventoryid(bvo.getCinventoryid());
    askPriceVO.setInventoryvid(bvo.getCinventoryvid());
    askPriceVO.setNegative(hvo.getBreverseflag());
    askPriceVO.setOutfinanceorg(bbvo.getCupfinanceorgid());
    askPriceVO.setPk_batchcode(bvo.getPk_batchcode());
    askPriceVO.setVbatchcode(bvo.getVbatchcode());
    askPriceVO.setCustomerid(map_custmer.get(hvo.getCinfinanceorgid()));
    askPriceVO.setPk_group(bvo.getPk_group());
    askPriceVO.setPk_org(bbvo.getCupfinanceorgid());
    askPriceVO.setPk_org_type(AskPriceVO.PK_ORG_TYPE_FINANCE);
    askPriceVO.setCsendtypeid(bvo.getCsendtypeid());
    askPriceVO.setNqtnum(bvo.getNqtnum());
    askPriceVO.setVfree1(bvo.getVfree1());
    askPriceVO.setVfree2(bvo.getVfree2());
    askPriceVO.setVfree3(bvo.getVfree3());
    askPriceVO.setVfree4(bvo.getVfree4());
    askPriceVO.setVfree5(bvo.getVfree5());
    askPriceVO.setVfree6(bvo.getVfree6());
    askPriceVO.setVfree7(bvo.getVfree7());
    askPriceVO.setVfree8(bvo.getVfree8());
    askPriceVO.setVfree9(bvo.getVfree9());
    askPriceVO.setVfree10(bvo.getVfree10());
    askPriceVO.setOrderbid(bvo.getCsrcbid());

    return askPriceVO;
  }

  private static AskPriceVO[] constructAskPriceVO(BusinessinfoVO[] bills) {
    Set<String> setrule = new HashSet<String>();
    for (BusinessinfoVO bill : bills) {
      BusinessinfoBVO[] items = bill.getChildrenVO();
      for (BusinessinfoBVO item : items) {
        String csettlerulebid = item.getCsettlerule_bid();
        if (null != csettlerulebid) {
          setrule.add(item.getCsettlerule_bid());
        }
      }
    }
    if (setrule.size() == 0) {
      return new AskPriceVO[0];
    }

    Map<String, SettleRuleAggVO> map = new HashMap<String, SettleRuleAggVO>();
    IQuerySettleruleService service =
        NCLocator.getInstance().lookup(IQuerySettleruleService.class);
    try {
      map = service.querySettleRule(setrule.toArray(new String[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    Set<String> set = new HashSet<String>();
    for (BusinessinfoVO bill : bills) {
      if (!PubAppTool.isNull(bill.getParentVO().getCinfinanceorgid())) {
        set.add(bill.getParentVO().getCinfinanceorgid());
      }
    }
    Map<String, String> map_custmer = new HashMap<String, String>();
    if (set.size() > 0) {
      map_custmer =
          CustomerPubService.queryCusPkByOrgPk(set.toArray(new String[set
              .size()]));
    }
    List<AskPriceVO> l = new ArrayList<AskPriceVO>();
    for (BusinessinfoVO bill : bills) {
      BusinessinfoBVO[] bvos = bill.getChildrenVO();
      for (BusinessinfoBVO bvo : bvos) {
        if (bvo.getBneedaskprice().booleanValue()) {
          l.add(AskPriceBizUtils.constructAskpriceVO(map, map_custmer,
              bill.getParentVO(), bvo));
        }
        else {
          for (BusinessinfoBBVO bbvo : bvo.getBBVO()) {
            l.add(AskPriceBizUtils.constructAskpriceVO(map_custmer,
                bill.getParentVO(), bvo, bbvo));
          }
        }
      }
    }
    return l.toArray(new AskPriceVO[0]);
  }

  private static BusinessinfoVO[] getNeedAskPriceVO(BusinessinfoVO[] bills) {
    return bills;
  }
}
