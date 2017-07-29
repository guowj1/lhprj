package nc.vo.to.businessinfo.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.to.askprice.AskPriceVO;
import nc.vo.to.businessinfo.entity.BusinessinfoBBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoBVO;
import nc.vo.to.businessinfo.entity.BusinessinfoHVO;
import nc.vo.to.businessinfo.map.BusiinfoBBVODataSetForCal;
import nc.vo.to.businessinfo.map.BusiinfoBBVORelationItemForCal;
import nc.vo.to.businessinfo.map.BusiinfoBVODataSetForCal;
import nc.vo.to.businessinfo.map.BusiinfoBVORelationItemForCal;
import nc.vo.to.pub.para.ParaUtilsForTo;
import nc.vo.to.pub.util.vat.TOVatTriaUtils;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;

public class BusiinfoMoneyPriceUtils {

  public static void calcBBVO(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      BusinessinfoBBVO bbvo, String changedKey, UFBoolean isTaxfirst) {
    TOVatTriaUtils vatutil = new TOVatTriaUtils();
    // 创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new BusiinfoBBVORelationItemForCal();
    // 创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = new BusiinfoBBVODataSetForCal(hvo, bvo, bbvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();
    // 设置是否进行本币换算
    cond.setIsCalLocalCurr(true);
    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(true);
    // 是否固定报价单位换算率
    cond.setIsFixNqtunitrate(true);
    // 设置含税优先还是无税优先
    cond.setIsTaxOrNet(isTaxfirst.booleanValue());
    // 是否跨国(6.1新增)
    cond.setInternational(vatutil.getIsDiffCountry(bbvo.getFbuysellflag())
        .booleanValue());
    // 两个参数 cond 为计算时的参数条件
    // item.getTaxPriceKey()发生变化的key的映射
    cond.setUnitPriorType(Condition.QT_PRIOR);
    //去掉下面的false 使无税单价直接clone，而不是无税单价=无税金额/数量     panfengc
    //cond.setCurCloneAllowed(false);
    tool.calculate(cond, changedKey);
    bbvo.setStatus(VOStatus.UPDATED);
  }

  public static void calcBBVO(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      String changekey, UFBoolean isTaxfirst) {
    for (BusinessinfoBBVO bbvo : bvo.getBBVO()) {
      BusiinfoMoneyPriceUtils.calcBBVO(hvo, bvo, bbvo, changekey, isTaxfirst);
    }
  }

  /** 调用此方法前需确定走结算路径，走折扣率，并且已生成bbvo */
  public static void calcBBVOByDisaccount(BusinessinfoHVO bizhvo,
      BusinessinfoBVO bizbvo) {
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    if (scale == null) {
      scale = new ScaleUtils(bizhvo.getPk_group());
    }
    if (!BusiinfoMoneyPriceUtils.hasPrice(bizhvo, bizbvo)) {
      new BusinessinfoUtils().clearBBVOPrice(bizbvo);
      return;
    }
    UFBoolean ufbTaxFirst =
        ParaUtilsForTo.getInstance().getTO01(bizhvo.getPk_group());
    boolean taxFirst = ufbTaxFirst == null ? true : ufbTaxFirst.booleanValue();
    Map<String, UFDouble> maprate = new HashMap<String, UFDouble>();

    try {
      for (BusinessinfoBBVO bbvo : bizbvo.getBBVO()) {
        // 处理汇率和折扣率
        UFDouble changeprice =
            taxFirst ? bizbvo.getNbasetaxprice() : bizbvo.getNbaseprice();
        changeprice = changeprice.multiply(bbvo.getNdiscountrate()).div(100d);
        //gwj
        if(bbvo.getNdiscountvalue()!=null)
        	changeprice=changeprice.add(bbvo.getNdiscountvalue());
        if (!bbvo.getCorigcurrencyid().equals(bizhvo.getCcurrencyid())) {
          UFDouble rate =
              BusiinfoMoneyPriceUtils.getRate(bbvo.getCupfinanceorgid(),
                  bizhvo.getCcurrencyid(), bbvo.getCorigcurrencyid(),
                  bizhvo.getDsrcbilldate(), maprate);

          if (rate == null || MathTool.isZero(rate)) {
            ExceptionUtils
                .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                    .getStrByID("4009010_0", "04009010-0048")/*内部交易信息多路径，当前未取到汇率，请设置*/);
          }

          /*changeprice =
              CurrencyRateUtil.getInstanceByOrg(bbvo.getCupfinanceorgid())
                  .getAmountsByOpp(bizhvo.getCcurrencyid(),
                      bbvo.getCorigcurrencyid(), new UFDouble[] {
                        changeprice
                      }, rate, bizhvo.getDsrcbilldate())[0];*/
        }

        // changeprice = scale.adjustPriceScale(changeprice);
        if (taxFirst) {
          bbvo.setNqtorigtaxprice(changeprice);
        }
        else {
          bbvo.setNqtorigprice(changeprice);
        }
        TOVatTriaUtils vatutil = new TOVatTriaUtils();
        // 调用单价金额算法
        IRelationForItems item = new BusiinfoBBVORelationItemForCal();
        IDataSetForCal data =
            new BusiinfoBBVODataSetForCal(bizhvo, bizbvo, bbvo, item);
        Calculator tool = new Calculator(data, scale);
        Condition cond = new Condition();
        cond.setIsCalLocalCurr(true);
        cond.setIsChgPriceOrDiscount(false);
        cond.setIsTaxOrNet(taxFirst);
        // 是否跨国(6.1新增)
        cond.setInternational(vatutil.getIsDiffCountry(bbvo.getFbuysellflag())
            .booleanValue());
        if (taxFirst) {
          tool.calculate(cond, item.getNqtorigtaxpriceKey());
        }
        else {
          tool.calculate(cond, item.getNqtorigpriceKey());
        }
        bbvo.setStatus(VOStatus.UPDATED);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public static void calcBBVOLocal(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      BusinessinfoBBVO bbvo, String changedKey, UFBoolean isTaxfirst) {
    TOVatTriaUtils vatutil = new TOVatTriaUtils();

    // 创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new BusiinfoBBVORelationItemForCal();
    // 创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = new BusiinfoBBVODataSetForCal(hvo, bvo, bbvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();
    // 设置是否进行本币换算
    cond.setIsCalLocalCurr(true);
    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(true);
    // 是否固定报价单位换算率
    cond.setIsFixNqtunitrate(true);
    // 设置含税优先还是无税优先
    cond.setIsTaxOrNet(isTaxfirst.booleanValue());
    // 是否跨国(6.1新增)
    cond.setInternational(vatutil.getIsDiffCountry(bbvo.getFbuysellflag())
        .booleanValue());
    // 两个参数 cond 为计算时的参数条件
    // item.getTaxPriceKey()发生变化的key的映射
    cond.setUnitPriorType(Condition.QT_PRIOR);
    cond.setCurCloneAllowed(false);
    tool.calculateLocalCurrenyMny(cond);
    bbvo.setStatus(VOStatus.UPDATED);
  }

  public static void calcBVO(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      String changedKey, UFBoolean isTaxfirst) {
    TOVatTriaUtils vatutil = new TOVatTriaUtils();
    // 创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new BusiinfoBVORelationItemForCal();
    // 创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = new BusiinfoBVODataSetForCal(hvo, bvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();

    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(true);
    // 是否固定报价单位换算率
    cond.setIsFixNqtunitrate(true);
    // 设置含税优先还是无税优先
    cond.setIsTaxOrNet(isTaxfirst.booleanValue());
    cond.setIsCalLocalCurr(true);
    cond.setCurCloneAllowed(false);
    // 是否跨国(6.1新增)
    cond.setInternational(vatutil.getIsDiffCountry(hvo.getFbuysellflag())
        .booleanValue());
    // 两个参数 cond 为计算时的参数条件
    // item.getTaxPriceKey()发生变化的key的映射
    cond.setUnitPriorType(Condition.QT_PRIOR);
    tool.calculate(cond, changedKey);
    bvo.setStatus(VOStatus.UPDATED);

  }

  public static void calcBVOLocal(BusinessinfoHVO hvo, BusinessinfoBVO bvo) {
    TOVatTriaUtils vatutil = new TOVatTriaUtils();
    UFBoolean ufbTaxFirst =
        ParaUtilsForTo.getInstance().getTO01(hvo.getPk_group());
    boolean taxFirst = ufbTaxFirst == null ? true : ufbTaxFirst.booleanValue();

    // 创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new BusiinfoBVORelationItemForCal();
    // 创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = new BusiinfoBVODataSetForCal(hvo, bvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();
    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(true);
    // 是否固定报价单位换算率
    cond.setIsFixNqtunitrate(true);
    // 设置含税优先还是无税优先
    cond.setIsTaxOrNet(taxFirst);
    // 是否跨国(6.1新增)
    cond.setInternational(vatutil.getIsDiffCountry(hvo.getFbuysellflag())
        .booleanValue());
    // 两个参数 cond 为计算时的参数条件
    // item.getTaxPriceKey()发生变化的key的映射
    cond.setUnitPriorType(Condition.QT_PRIOR);
    tool.calculateLocalCurrenyMny(cond);
    bvo.setStatus(VOStatus.UPDATED);
  }

  public static UFDouble getCurrencyRate(String pk_org, Integer org_type,
      String srccurr, String descurr, UFDate date) throws BusinessException {
    String fin;
    if (org_type.equals(AskPriceVO.PK_ORG_TYPE_CALBODY)) {
      Map<String, String> map =
          StockOrgPubService.queryFinanceOrgIDByStockOrgID(new String[] {
            pk_org
          });
      fin = map.get(pk_org);
      if (fin == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4009010_0", "04009010-0038")/*@res "取库存组织所属财务组织，未取到"*/);
      }
    }
    else {
      fin = pk_org;
    }
    Map<String, String> map =
        FinanceOrgPubService.queryMainBookID(new String[] {
          fin
        });
    String accountbookid = map.get(fin);
    return CurrencyRate.getCurrencyRateByBook(accountbookid, srccurr, descurr,
        date);
  }

  private static String getKey(String s0, String s1, String s2, UFDate date) {
    return "" + s0 + s1 + s2 + date;
  }

  private static UFDouble getRate(String pk_org, String srccurr,
      String descurr, UFDate date, Map<String, UFDouble> map) {
    UFDouble rate =
        map.get(BusiinfoMoneyPriceUtils.getKey(pk_org, srccurr, descurr, date));

    try {
      rate =
          BusiinfoMoneyPriceUtils.getCurrencyRate(pk_org,
              AskPriceVO.PK_ORG_TYPE_FINANCE, srccurr, descurr, date);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (rate != null) {
      map.put(BusiinfoMoneyPriceUtils.getKey(pk_org, srccurr, descurr, date),
          rate);
    }
    return rate;
  }

  private static boolean hasPrice(BusinessinfoHVO bizhvo, BusinessinfoBVO bizbvo) {
    UFBoolean ufbTaxFirst =
        ParaUtilsForTo.getInstance().getTO01(bizhvo.getPk_group());
    boolean taxFirst = ufbTaxFirst == null ? true : ufbTaxFirst.booleanValue();
    UFDouble changemoney =
        taxFirst ? bizbvo.getNbasetaxmny() : bizbvo.getNbasemny();
    if (changemoney == null || changemoney.equals(UFDouble.ZERO_DBL)) {
      return false;
    }
    return true;
  }
}
