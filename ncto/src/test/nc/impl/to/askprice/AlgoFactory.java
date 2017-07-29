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
        // ���������֯���ڲ�ת�Ƽ۸�
        askPriceAlgo = new TranPrice();
      }
      else if (PriceRule.POPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // �ɹ���ⵥ��Ӧ�ɹ������۸�
//        askPriceAlgo = new PUOrderPrice();//gwj ������Ŀ�滻��ȡ�۹���Ϊ�Ӳɹ���ⵥȡ��
    	  askPriceAlgo=new ICPurchaseinPrice();
      }
      else if (PriceRule.SOPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // ���۳��ⵥ��Ӧ���۶����۸�
        askPriceAlgo = new SOOrderPrice();
      }
      else if (PriceRule.LASTPOTRANPRICE
          .equalsValue(Integer.valueOf(pricerule))) {
        // ���¿���֯�ɹ������۸�
        askPriceAlgo = new PULstOrderPrice();
      }
      else if (PriceRule.LASTSETTLEPRICE
          .equalsValue(Integer.valueOf(pricerule))) {
        // �����ڲ�����۸�
        askPriceAlgo = new TOLstSettlePrice();
      }
      else if (PriceRule.SALEPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // ������֯���߼۸�
        askPriceAlgo = new SOOrgSalePrice();
      }
      else if (PriceRule.ABPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // �����ɱ�����ɱ�
        askPriceAlgo = new QryOutCBPrice();
      }
      else if (PriceRule.INPLANPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // ����ɱ���ƻ���
        askPriceAlgo = new QryInPlanPrice();
      }
      else if (PriceRule.TRANOUTPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // �������ⵥ����ɱ�
        askPriceAlgo = new QryTranOutCostPrice();
      }
      else if (PriceRule.SIGNPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // ��������ǩ�ռ�
        askPriceAlgo = new QryICSignPrice();
      }
      else if (PriceRule.BLTRANPRICE.equalsValue(Integer.valueOf(pricerule))) {
        // ���������۸�
        askPriceAlgo = new TOOrderPrice();
      }
      return askPriceAlgo;
    }
    else if (priceType == TOAskPrice.ESTIMATE_PRICE) {
      if (EstimatePriceRule.TOORDERPRICE
          .equalsValue(Integer.valueOf(pricerule))) {
        // �ڲ����������۸�
        askPriceAlgo = new TOOrderPrice();
      }
      else if (EstimatePriceRule.TRANPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // ��������֯���ڲ�ת�Ƽ۸�
        askPriceAlgo = new TranPrice();
      }
      else if (EstimatePriceRule.POORDERPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // �ɹ���ⵥ��Ӧ�ɹ������۸�
        askPriceAlgo = new PUOrderPrice();
      }
      else if (EstimatePriceRule.SOORDERPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // ���۳��ⵥ��Ӧ���۶����۸�
        askPriceAlgo = new SOOrderPrice();
      }
      else if (EstimatePriceRule.OUTPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // �������ⵥ����ɱ�
        askPriceAlgo = new QryTranOutCostPrice();
      }
      else if (EstimatePriceRule.SIGNPRICE.equalsValue(Integer
          .valueOf(pricerule))) {
        // ȡ��������ǩ�ռ�
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
