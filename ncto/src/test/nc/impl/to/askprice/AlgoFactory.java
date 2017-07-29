package nc.impl.to.askprice;

import nc.vo.to.enumeration.EstimatePriceRule;
import nc.vo.to.enumeration.PriceRule;
import nc.impl.to.askprice.algo.ICPurchaseinPrice;
import nc.impl.to.askprice.algo.ITOAskPriceAlgo;
import nc.impl.to.askprice.algo.PULstOrderPrice;
import nc.impl.to.askprice.algo.PUOrderPrice;
import nc.impl.to.askprice.algo.QryICSignPrice;
import nc.impl.to.askprice.algo.QryInPlanPrice;
import nc.impl.to.askprice.algo.QryOutCBPrice;
import nc.impl.to.askprice.algo.QryTranOutCostPrice;
import nc.impl.to.askprice.algo.SOOrderPrice;
import nc.impl.to.askprice.algo.SOOrgSalePrice;
import nc.impl.to.askprice.algo.TOLstSettlePrice;
import nc.impl.to.askprice.algo.TOOrderPrice;
import nc.impl.to.askprice.algo.TranPrice;

public class AlgoFactory {

  public static ITOAskPriceAlgo create(int pricerule, int priceType) {
    ITOAskPriceAlgo askPriceAlgo = null;
    if (priceType == TOAskPrice.COMMON_PRICE) {
      if (PriceRule.TRANPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 调出库存组织的内部转移价格
        askPriceAlgo = new TranPrice();
      }
      else if (PriceRule.POPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 采购入库单对应采购订单价格
//        askPriceAlgo = new PUOrderPrice();//gwj 立恒项目替换该取价规则为从采购入库单取价
    	  askPriceAlgo=new ICPurchaseinPrice();
      }
      else if (PriceRule.SOPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 销售出库单对应销售订单价格
        askPriceAlgo = new SOOrderPrice();
      }
      else if (PriceRule.LASTPOTRANPRICE
          .equalsValue(Integer.valueOf(pricerule))) {
        // 最新跨组织采购订单价格
        askPriceAlgo = new PULstOrderPrice();
      }
      else if (PriceRule.LASTSETTLEPRICE
          .equalsValue(Integer.valueOf(pricerule))) {
        // 最新内部结算价格
        askPriceAlgo = new TOLstSettlePrice();
      }
      else if (PriceRule.SALEPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 销售组织政策价格
        askPriceAlgo = new SOOrgSalePrice();
      }
      else if (PriceRule.ABPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 调出成本域结存成本
        askPriceAlgo = new QryOutCBPrice();
      }
      else if (PriceRule.INPLANPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 调入成本域计划价
        askPriceAlgo = new QryInPlanPrice();
      }
      else if (PriceRule.TRANOUTPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 调拨出库单出库成本
        askPriceAlgo = new QryTranOutCostPrice();
      }
      else if (PriceRule.SIGNPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 调拨出库签收价
        askPriceAlgo = new QryICSignPrice();
      }
      else if (PriceRule.BLTRANPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // 订单调拨价格
        askPriceAlgo = new TOOrderPrice();
      }
      return askPriceAlgo;
    }
    else if (priceType == TOAskPrice.ESTIMATE_PRICE) {
      if (EstimatePriceRule.TOORDERPRICE
          .equalsValue(Integer.valueOf(pricerule))) {
        // 内部调拨订单价格
        askPriceAlgo = new TOOrderPrice();
      }
      else if (EstimatePriceRule.TRANPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // 按调出组织的内部转移价格
        askPriceAlgo = new TranPrice();
      }
      else if (EstimatePriceRule.POORDERPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // 采购入库单对应采购订单价格
        askPriceAlgo = new PUOrderPrice();
      }
      else if (EstimatePriceRule.SOORDERPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // 销售出库单对应销售订单价格
        askPriceAlgo = new SOOrderPrice();
      }
      else if (EstimatePriceRule.OUTPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // 调拨出库单出库成本
        askPriceAlgo = new QryTranOutCostPrice();
      }
      else if (EstimatePriceRule.SIGNPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // 取调拨出库签收价
        askPriceAlgo = new QryICSignPrice();
      }
      return askPriceAlgo;
    }
    throw new UnsupportedOperationException();
  }

  private AlgoFactory() {
    super();
  }
}
