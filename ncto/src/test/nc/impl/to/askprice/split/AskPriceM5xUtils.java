package nc.impl.to.askprice.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.to.askprice.AskPriceResultVO;
import nc.vo.to.askprice.AskPriceVO;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;
import nc.vo.to.m5x.pub.M5XVOBusiRuleUtil;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.to.askprice.ITOAskPriceService;

import nc.pubitf.to.settlerule.to.IQuerySettleruleService;

import nc.bs.framework.common.NCLocator;

import nc.impl.to.askprice.split.m5xsettle.M5XPriceSetter;
import nc.impl.to.m5x.pub.price.M5XBSSettlePathUtils;

public class AskPriceM5xUtils {

  /**
   * 调拨订单后台询价
   * 
   * @param bill
   */
  public static BillVO askPrice(BillVO bill) {
    return AskPriceM5xUtils.askPrice(new BillVO[] {
      bill
    })[0];
  }

  public static BillVO[] askPrice(BillVO[] bills) {
    // 三方、多路径不询价并清空价格
    // 三方调拨订单没有价格，价格由内部交易信息询和现实，不需要在此处询价
    BillVO[] vos = AskPriceM5xUtils.getNeedAskPriceVO(bills);
    if (vos.length == 0) {
      return bills;
    }
    // 组织询价参数
    AskPriceVO[] askVOs = AskPriceM5xUtils.constructAskPriceVO(vos);
    // 调用询价算法
    ITOAskPriceService service =
        NCLocator.getInstance().lookup(ITOAskPriceService.class);
    Map<AskPriceVO, AskPriceResultVO> mapPrice =
        service.askPriceOnOrder(askVOs);
    // 设置价格并计算单价金额
    Map<String, AskPriceResultVO> resultmap =
        new HashMap<String, AskPriceResultVO>();
    Map<String, AskPriceVO> resultpricemap = new HashMap<String, AskPriceVO>();

    for (AskPriceVO vo : askVOs) {
      if (!mapPrice.containsKey(vo)) {
        continue;
      }
      String key = vo.getCitemkey();
      resultmap.put(key, mapPrice.get(vo));
      resultpricemap.put(key, vo);
    }

    // 设置价格并计算单价金额
    M5XPriceSetter pricesetter = new M5XPriceSetter();
    pricesetter.setPrices(vos, resultmap, resultpricemap);
    return bills;
  }

  private static AskPriceVO[] constructAskPriceVO(BillVO[] bills) {
    int idx = 0;
    Set<String> setrule = new HashSet<String>();
    List<AskPriceVO> lstItems = new ArrayList<AskPriceVO>();
    for (BillVO bill : bills) {
      BillItemVO[] items = bill.getChildrenVO();
      for (BillItemVO item : items) {
        String iosettlerulebid = item.getCiosettlerule_bid();
        if (null != iosettlerulebid) {
          setrule.add(item.getCiosettlerule_bid());
        }
      }
    }
    if (setrule.size() == 0) {
      return new AskPriceVO[0];
    }
    Map<String, String> mapPriceRule = new HashMap<String, String>();
    IQuerySettleruleService service =
        NCLocator.getInstance().lookup(IQuerySettleruleService.class);
    try {
      mapPriceRule = service.queryPricerule(setrule.toArray(new String[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    Set<String> set = new HashSet<String>();
    for (BillVO bill : bills) {
      if (!PubAppTool.isNull(bill.getParentVO().getCinfinanceorgid())) {
        set.add(bill.getParentVO().getCinfinanceorgid());
      }
    }
    Map<String, String> map = new HashMap<String, String>();
    if (set.size() > 0) {
      map = AskPriceM5xUtils.querCustomer(set.toArray(new String[set.size()]));
    }

    for (BillVO bill : bills) {
      BillItemVO[] items = bill.getChildrenVO();
      BillHeaderVO header = bill.getParentVO();
      UFBoolean flag =
          header.getBioreverseflag() == null ? UFBoolean.FALSE : header
              .getBioreverseflag();
      for (BillItemVO item : items) {
        if (item.getCinventoryvid() == null) {
          continue;
        }
        AskPriceVO askVO = new AskPriceVO();
        lstItems.add(askVO);
        askVO.setBilldate(header.getDbilldate());
        askVO.setCitemkey(String.valueOf(idx++));
        askVO.setCsettleruleid(item.getCiosettleruleid());
        askVO.setCsettlerulebid(item.getCiosettlerule_bid());
        askVO.setCtranstype(header.getCtrantypeid());
        askVO.setCquoteunitid(item.getCqtunitid());
        String askPriceRule = mapPriceRule.get(item.getCiosettlerule_bid());
        askVO.setFpriceruleflag(askPriceRule);
        askVO.setDestOrigCurr(header.getCorigcurrencyid());
        askVO.setCreceiveareaid(item.getCreceiveareaid());
        // 如果是反向的，对于调拨订单的询价时候也需要反向进行询价
        if (!flag.booleanValue()) {
          askVO.setIncb(header.getCinstockorgid());
          askVO.setInfinanceorg(header.getCinfinanceorgid());

          askVO.setInwh(item.getCinstordocid());
          askVO.setOutcb(header.getPk_org());
          askVO.setOutfinanceorg(header.getCoutfinanceorgid());
          askVO.setOutwh(item.getCoutstordocid());
        }
        else {
          askVO.setIncb(header.getPk_org());
          askVO.setInfinanceorg(header.getCoutfinanceorgid());
          askVO.setInwh(item.getCoutstordocid());
          askVO.setOutcb(header.getCinstockorgid());
          askVO.setOutfinanceorg(header.getCinfinanceorgid());
          askVO.setOutwh(item.getCinstordocid());
        }

        askVO.setInventoryid(item.getCinventoryid());
        askVO.setInventoryvid(item.getCinventoryvid());
        askVO.setNaddpricerate(item.getNaddpricerate());

        askVO.setOrdertype(item.getVfirsttype());
        askVO.setOrderbid(item.getCfirstbid());

        askVO.setPk_batchcode(item.getPk_batchcode());
        askVO.setPk_group(header.getPk_group());
        askVO.setPk_org(header.getPk_org());
        askVO.setPk_org_type(AskPriceVO.PK_ORG_TYPE_CALBODY);
        askVO.setVbatchcode(item.getVbatchcode());
        askVO.setCsendtypeid(item.getCsendtypeid());
        askVO.setNqtnum(item.getNqtunitnum());
        askVO.setCustomerid(map.get(header.getCinfinanceorgid()));
        askVO.setVfree1(item.getVfree1());
        askVO.setVfree2(item.getVfree2());
        askVO.setVfree3(item.getVfree3());
        askVO.setVfree4(item.getVfree4());
        askVO.setVfree5(item.getVfree5());
        askVO.setVfree6(item.getVfree6());
        askVO.setVfree7(item.getVfree7());
        askVO.setVfree8(item.getVfree8());
        askVO.setVfree9(item.getVfree9());
        askVO.setVfree10(item.getVfree10());
        // 固定辅助属性
        askVO.setCprojectid(item.getCprojectid());
        askVO.setCasscustid(item.getCasscustid());
        askVO.setCvendorid(item.getCvendorid());
        askVO.setCproductorid(item.getCproductorid());
        askVO.setCffileid(item.getCffileid());
        
        //gwj 增加入库单主键和子表主键
//        askVO.setInid(item.getCbillid());
        askVO.setInbid(item.getCsrcbid());
      }
    }
    return lstItems.toArray(new AskPriceVO[0]);
  }

  private static BillVO[] getNeedAskPriceVO(BillVO[] bills) {
    List<BillVO> lst = new ArrayList<BillVO>();
    Map<String, UFBoolean> mapDiscount =
        M5XBSSettlePathUtils.getBisDiscount(bills);
    for (BillVO bill : bills) {
      if (!M5XVOBusiRuleUtil.is5C(bill)
          && bill.getParentVO().getCsettlepathid() == null) {
        lst.add(bill);
      }
      else {
        // 多路径走折扣的需要询价
        if (bill.getParentVO().getCsettlepathid() != null) {
          // 查询结算路径上是否折扣
          UFBoolean bisDiscount =
              mapDiscount.get(bill.getParentVO().getCsettlepathid());
          if (bisDiscount != null && bisDiscount.booleanValue()) {
            lst.add(bill);
            continue;
          }
        }

      }
    }
    return lst.toArray(new BillVO[0]);
  }

  private static Map<String, String> querCustomer(String[] orgs) {
    Map<String, String> mapReturn = CustomerPubService.queryCusPkByOrgPk(orgs);
    return mapReturn;
  }

}
