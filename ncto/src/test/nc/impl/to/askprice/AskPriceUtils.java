package nc.impl.to.askprice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.to.askprice.AskPriceVO;
import nc.vo.to.enumeration.AskpriceScene;
import nc.vo.to.enumeration.EstimatePriceRule;
import nc.vo.to.enumeration.PriceRule;
import nc.vo.to.pub.util.CostRegionSetUtils;
import nc.vo.to.settlerule.entity.MatchSettleRuleVO;
import nc.vo.to.settlerule.entity.SettleRuleAggVO;
import nc.vo.to.settlerule.entity.SettleRuleBVO;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;

import nc.pubitf.to.settlerule.to.IMatchSettleruleService;

import nc.bs.framework.common.NCLocator;

/** 询价使用的工具方法 */
public class AskPriceUtils {

  private static volatile MapSet<Integer, String> estiPriceRule =
      new MapSet<Integer, String>();

  private static volatile MapSet<Integer, String> priceRule =
      new MapSet<Integer, String>();

  private static List<String[]> buildOrgIds(AskPriceVO[] vos) {
    List<String> stockorgids = new ArrayList<String>();
    List<String> storcs = new ArrayList<String>();
    List<String> fiorgs = new ArrayList<String>();
    for (AskPriceVO vo : vos) {
      String outcr = vo.getOutcostregion();
      String outcb = vo.getOutcb();
      String outwh = vo.getOutwh();
      String outfi = vo.getOutfinanceorg();
      String incr = vo.getIncostregion();
      String incb = vo.getIncb();
      String inwh = vo.getInwh();
      String infi = vo.getInfinanceorg();
      if (null == outcr) {
        if (null == outcb && null == outwh) {
          fiorgs.add(outfi);
        }
        else {
          stockorgids.add(outcb);
          storcs.add(outwh);
        }
      }
      if (null == incr) {
        if (null == incb && null == inwh) {
          fiorgs.add(infi);
        }
        else {
          stockorgids.add(incb);
          storcs.add(inwh);
        }
      }
    }
    String[] arrStockOrgids = new String[stockorgids.size()];
    stockorgids.toArray(arrStockOrgids);
    String[] arrStorcs = new String[storcs.size()];
    storcs.toArray(arrStorcs);
    String[] arrFiOrgs = new String[fiorgs.size()];
    fiorgs.toArray(arrFiOrgs);
    List<String[]> lstReturn = new ArrayList<String[]>();
    lstReturn.add(arrStockOrgids);
    lstReturn.add(arrStorcs);
    lstReturn.add(arrFiOrgs);
    return lstReturn;
  }

  public static UFDouble calcAddPriceRate(AskPriceVO askVO, UFDouble price,String curr) {
    UFDouble retprice = price;
    if (price != null) {
      if (askVO.getNaddpricerate() != null) {
        // 有加价率才需要计算，没有情况单价不用变
        UFDouble t =
            MathTool.add(UFDouble.ONE_DBL,
                askVO.getNaddpricerate().div(new UFDouble(100)));
        retprice = price.multiply(t);
        ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
        retprice =
            scale.adjustSoPuPriceScale(retprice, curr);
      }
      if(askVO.getNaddpricevalue()!=null){
    	  //有固定加价额才需要计算 gwj
    	  UFDouble naddvalue=askVO.getNaddpricevalue();
    	  retprice=price.add(naddvalue);
    	  ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
          retprice =
              scale.adjustSoPuPriceScale(retprice, curr);
      }
    }
    return retprice;
  }

  private static UFDouble calcQtPrice(UFDouble nprice, String rate) {
    return HslParseUtil.hslDivUFDouble(rate, nprice);
  }

  /**
   * 将寻到的主单位价格转换为报价单位价格
   * 
   * @param vos
   */
  public static void convertMainToQTunitPrice(List<AskPriceVO> vos) {
    // 补充主单位
    AskPriceUtils.setInvMainUnit(vos);
    // 将主单位价格转换为与报价单位价格
    for (AskPriceVO vo : vos) {
      String pk_material = vo.getInventoryid();
      String cunit = vo.getCunitid();
      String cqtunit = vo.getCquoteunitid();
      UFDouble nprice = vo.getNprice();
      String rate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(pk_material,
              cqtunit, cunit);
      vo.setVunitchangerate(rate);
      nprice = AskPriceUtils.calcQtPrice(nprice, rate);
      ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
      nprice = scale.adjustSoPuPriceScale(nprice, vo.getCurr());
      vo.setNprice(nprice);
    }
  }

  /** 设置结算规则 */
  public static void fetchSettleRule(AskPriceVO[] vos) {
    List<MatchSettleRuleVO> matchSettleRuleVOs =
        AskPriceUtils.getMatchSettleRuleVOs(vos);
    IMatchSettleruleService bo =
        NCLocator.getInstance().lookup(IMatchSettleruleService.class);
    Map<Integer, SettleRuleAggVO> map = null;
    try {
      map = bo.matchSettlerule(matchSettleRuleVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    AskPriceUtils.setSettleRule(vos, map);
  }

  public static String filterEstipricerule(Integer scene, String estipricerule) {
    if (estipricerule == null) {
      return null;
    }
    Set<String> avoidrule = AskPriceUtils.getEstipricerule().get(scene);
    if (avoidrule == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    String[] rules = estipricerule.split(",");
    for (String rule : rules) {
      if (avoidrule.contains(rule)) {
        if (sb.length() > 0) {
          sb.append(",");
        }
        sb.append(rule);
      }
    }
    return sb.toString();
  }

  public static String filterPricerule(Integer scene, String pricerule) {
    if (pricerule == null) {
      return null;
    }
    Set<String> avoidrule = AskPriceUtils.getPricerule().get(scene);
    StringBuffer sb = new StringBuffer();
    String[] rules = pricerule.split(",");
    for (String rule : rules) {
      if (avoidrule.contains(rule)) {
        if (sb.length() > 0) {
          sb.append(",");
        }
        sb.append(rule);
      }
    }
    return sb.toString();
  }

  public static UFDouble getCurrencyRate(String paraPk_Org, Integer org_type,
      String srccurr, String descurr, UFDate date) {
    String pk_org = paraPk_Org;
    if (org_type.equals(AskPriceVO.PK_ORG_TYPE_CALBODY)) {
      Map<String, String> map =
          StockOrgPubService.queryFinanceOrgIDByStockOrgID(new String[] {
            pk_org
          });
      pk_org = map.get(pk_org);
      if (pk_org == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4009005_0", "04009005-0008")/*
                                                                     * @res
                                                                     * "取库存组织所属财务组织，未取到"
                                                                     */);
      }
    }
    Map<String, String> map =
        FinanceOrgPubService.queryMainBookID(new String[] {
          pk_org
        });
    if (map.size() == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4009005_0", "04009005-0009")/*
                                                                   * @res
                                                                   * "财务组织没有找到对应的财务核算主账簿"
                                                                   */);
    }
    String accountbookid = map.get(pk_org);
    return CurrencyRate.getCurrencyRateByBook(accountbookid, srccurr, descurr,
        date);
  }

  private static MapSet<Integer, String> getEstipricerule() {
    synchronized (AskPriceUtils.estiPriceRule) {
      if (AskPriceUtils.estiPriceRule.size() == 0) {
        MapSet<Integer, String> tmp = new MapSet<Integer, String>();
        // 出库暂估
        tmp.put(AskpriceScene.OUTESTI,
            EstimatePriceRule.POORDERPRICE.strValue());
        tmp.put(AskpriceScene.OUTESTI, EstimatePriceRule.RELAPRICE.strValue());
        tmp.put(AskpriceScene.OUTESTI,
            EstimatePriceRule.SOORDERPRICE.strValue());
        tmp.put(AskpriceScene.OUTESTI,
            EstimatePriceRule.TOORDERPRICE.strValue());
        tmp.put(AskpriceScene.OUTESTI, EstimatePriceRule.TRANPRICE.strValue());
        tmp.put(AskpriceScene.OUTESTI, EstimatePriceRule.OUTPRICE.strValue());
        tmp.put(AskpriceScene.OUTESTI, EstimatePriceRule.SIGNPRICE.strValue());
        // 入库暂估
        tmp.put(AskpriceScene.INESTI, EstimatePriceRule.OUTPRICE.strValue());
        tmp.put(AskpriceScene.INESTI, EstimatePriceRule.POORDERPRICE.strValue());
        tmp.put(AskpriceScene.INESTI, EstimatePriceRule.RELAPRICE.strValue());
        tmp.put(AskpriceScene.INESTI, EstimatePriceRule.SOORDERPRICE.strValue());
        tmp.put(AskpriceScene.INESTI, EstimatePriceRule.TOORDERPRICE.strValue());
        tmp.put(AskpriceScene.INESTI, EstimatePriceRule.TRANPRICE.strValue());
        tmp.put(AskpriceScene.INESTI, EstimatePriceRule.SIGNPRICE.strValue());
        AskPriceUtils.estiPriceRule = tmp;
      }
    }
    return AskPriceUtils.estiPriceRule;
  }

  private static List<MatchSettleRuleVO> getMatchSettleRuleVOs(AskPriceVO[] vos) {
    List<MatchSettleRuleVO> alMatchSettleRuleVOs =
        new ArrayList<MatchSettleRuleVO>();
    int i = 0;
    for (AskPriceVO vo : vos) {
      MatchSettleRuleVO matchvo = new MatchSettleRuleVO();
      matchvo.setCinfinanceorgid(vo.getInfinanceorg());
      matchvo.setCinstockorgid(vo.getIncb());
      matchvo.setCoutfinanceorgid(vo.getOutfinanceorg());
      matchvo.setCoutstockorgid(vo.getOutcb());
      matchvo.setCinventoryid(vo.getInventoryid());
      matchvo.setCtranstype(vo.getCtranstype());
      matchvo.setFsettletype(vo.getFsettletype());
      matchvo.setNegative(vo.getNegative());
      matchvo.setPk_group(vo.getPk_group());
      matchvo.setId(Integer.valueOf(i++));
      alMatchSettleRuleVOs.add(matchvo);
    }
    return alMatchSettleRuleVOs;
  }

  private static MapSet<Integer, String> getPricerule() {
    synchronized (AskPriceUtils.priceRule) {
      if (AskPriceUtils.priceRule.size() == 0) {
        MapSet<Integer, String> tmpPricerule = new MapSet<Integer, String>();
        // 调入申请
        tmpPricerule.put(AskpriceScene.APPLY, PriceRule.ABPRICE.strValue());
        tmpPricerule.put(AskpriceScene.APPLY, PriceRule.INPLANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.APPLY,
            PriceRule.LASTPOTRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.APPLY,
            PriceRule.LASTSETTLEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.APPLY, PriceRule.SALEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.APPLY, PriceRule.SOPRICE.strValue());
        tmpPricerule.put(AskpriceScene.APPLY, PriceRule.TRANPRICE.strValue());
        // 调拨订单
        tmpPricerule.put(AskpriceScene.ORDER, PriceRule.ABPRICE.strValue());
        tmpPricerule.put(AskpriceScene.ORDER, PriceRule.INPLANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.ORDER,
            PriceRule.LASTPOTRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.ORDER,
            PriceRule.LASTSETTLEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.ORDER, PriceRule.POPRICE.strValue());
        tmpPricerule.put(AskpriceScene.ORDER, PriceRule.SALEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.ORDER, PriceRule.SOPRICE.strValue());
        tmpPricerule.put(AskpriceScene.ORDER, PriceRule.TRANPRICE.strValue());
        // 内部交易信息
        tmpPricerule.put(AskpriceScene.TOINFO, PriceRule.ABPRICE.strValue());
        tmpPricerule
            .put(AskpriceScene.TOINFO, PriceRule.INPLANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO,
            PriceRule.LASTPOTRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO,
            PriceRule.LASTSETTLEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO, PriceRule.POPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO, PriceRule.SALEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO, PriceRule.SOPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO, PriceRule.TRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO, PriceRule.SIGNPRICE.strValue());
        tmpPricerule.put(AskpriceScene.TOINFO,
            PriceRule.TRANOUTPRICE.strValue());
        // 结算
        tmpPricerule.put(AskpriceScene.SETTLE, PriceRule.ABPRICE.strValue());
        tmpPricerule
            .put(AskpriceScene.SETTLE, PriceRule.INPLANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE,
            PriceRule.LASTPOTRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE,
            PriceRule.LASTSETTLEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE, PriceRule.POPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE, PriceRule.SALEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE, PriceRule.SIGNPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE, PriceRule.SOPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE,
            PriceRule.TRANOUTPRICE.strValue());
        tmpPricerule.put(AskpriceScene.SETTLE, PriceRule.TRANPRICE.strValue());
        tmpPricerule
            .put(AskpriceScene.SETTLE, PriceRule.BLTRANPRICE.strValue());
        // 出库暂估
        tmpPricerule.put(AskpriceScene.OUTESTI, PriceRule.ABPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI,
            PriceRule.INPLANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI,
            PriceRule.LASTPOTRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI,
            PriceRule.LASTSETTLEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI, PriceRule.POPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI, PriceRule.SALEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI, PriceRule.SOPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI, PriceRule.TRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI, PriceRule.SIGNPRICE.strValue());
        tmpPricerule.put(AskpriceScene.OUTESTI,
            PriceRule.TRANOUTPRICE.strValue());
        // 入库暂估
        tmpPricerule.put(AskpriceScene.INESTI, PriceRule.ABPRICE.strValue());
        tmpPricerule
            .put(AskpriceScene.INESTI, PriceRule.INPLANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI,
            PriceRule.LASTPOTRANPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI,
            PriceRule.LASTSETTLEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI, PriceRule.POPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI, PriceRule.SALEPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI, PriceRule.SIGNPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI, PriceRule.SOPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI,
            PriceRule.TRANOUTPRICE.strValue());
        tmpPricerule.put(AskpriceScene.INESTI, PriceRule.TRANPRICE.strValue());
        AskPriceUtils.priceRule = tmpPricerule;
      }
    }
    return AskPriceUtils.priceRule;
  }

  /** 查询组织本币 */
  public static Map<String, String> queryCurr(String[] orgs) {
    Map<String, String> map = new HashMap<String, String>();
    for (String org : orgs) {
      String curr = OrgUnitPubService.queryOrgCurrByPk(org);
      if (curr != null) {
        map.put(org, curr);
      }
    }
    return map;
  }

  public static void setCostRegion(AskPriceVO[] vos) {
    // 组织需要查成本域的ID
    List<String[]> lstIds = AskPriceUtils.buildOrgIds(vos);
    // 查询成本域
    Map<String, String> mapByStorDoc = new HashMap<String, String>();
    Map<String, String> mapByFiOrgs = new HashMap<String, String>();
    if (lstIds.get(0).length != 0 && lstIds.get(1).length != 0) {
      mapByStorDoc =
          CostRegionSetUtils.queryCostRegionIDByStockOrgsAndStordocs(
              lstIds.get(0), lstIds.get(1));
    }
    if (lstIds.get(2).length != 0) {
      mapByFiOrgs = CostRegionSetUtils.queryCostRegionIDByFiOrgs(lstIds.get(2));
    }
    if (mapByStorDoc.size() == 0 && mapByFiOrgs.size() == 0) {
      return;
    }
    AskPriceUtils.setCostRegion(vos, mapByStorDoc, mapByFiOrgs);

  }

  private static void setCostRegion(AskPriceVO[] vos,
      Map<String, String> mapByStorDoc, Map<String, String> mapByFiOrgs) {
    for (AskPriceVO vo : vos) {
      String outcr = vo.getOutcostregion();
      String outcb = vo.getOutcb();
      String outwh = vo.getOutwh();
      String outfi = vo.getOutfinanceorg();
      String incr = vo.getIncostregion();
      String incb = vo.getIncb();
      String inwh = vo.getInwh();
      String infi = vo.getInfinanceorg();
      if (null == outcr) {
        if (null == outcb && null == outwh) {
          String coutcostregionid = mapByFiOrgs.get(outfi);
          vo.setOutcostregion(coutcostregionid);
        }
        else {
          String key = outcb + outwh;
          String coutcostregionid = mapByStorDoc.get(key);
          vo.setOutcostregion(coutcostregionid);
        }
      }
      if (null == incr) {
        if (null == incb && null == inwh) {
          String cincostregionid = mapByFiOrgs.get(infi);
          vo.setIncostregion(cincostregionid);
        }
        else {
          String key = incb + inwh;
          String cincostregionid = mapByStorDoc.get(key);
          vo.setIncostregion(cincostregionid);
        }
      }
    }
  }

  // 设置主单位
  private static void setInvMainUnit(List<AskPriceVO> vos) {
    Set<String> set = new HashSet<String>();
    for (AskPriceVO vo : vos) {
      if (null == vo.getCunitid()) {
        set.add(vo.getInventoryid());
      }
    }
    Map<String, String> mapUnit = new HashMap<String, String>();
    if (set.size() > 0) {
      // 查询主单位
      mapUnit =
          MaterialPubService.queryMaterialMeasdoc(set.toArray(new String[0]));
    }
    for (AskPriceVO vo : vos) {
      if (null == vo.getCunitid()) {
        vo.setCunitid(mapUnit.get(vo.getInventoryid()));
      }
    }
  }

  private static void setSettleRule(AskPriceVO[] vos,
      Map<Integer, SettleRuleAggVO> map) {
    int i = 0;
    for (AskPriceVO vo : vos) {
      SettleRuleAggVO settlerule = map.get(Integer.valueOf(i));
      if (settlerule == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4009005_0", "04009005-0010")/*
                                                                     * @res
                                                                     * "询价时没有匹配到结算规则。"
                                                                     */);
      }
      else {
        SettleRuleBVO settlerulebody = settlerule.getBVO()[0];
        vo.setFestimatepriceruleflag(settlerulebody.getVestimatepricerule());
        vo.setFpriceruleflag(settlerulebody.getVpricerule());
        vo.setNaddpricerate(settlerulebody.getNaddpricerate());

      }
    }
  }

  private AskPriceUtils() {
    super();
  }
}
