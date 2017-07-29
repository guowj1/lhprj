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
    // ��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new BusiinfoBBVORelationItemForCal();
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = new BusiinfoBBVODataSetForCal(hvo, bvo, bbvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();
    // �����Ƿ���б��һ���
    cond.setIsCalLocalCurr(true);
    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    // �����Ƿ�̶���λ������
    cond.setIsFixNchangerate(true);
    // �Ƿ�̶����۵�λ������
    cond.setIsFixNqtunitrate(true);
    // ���ú�˰���Ȼ�����˰����
    cond.setIsTaxOrNet(isTaxfirst.booleanValue());
    // �Ƿ���(6.1����)
    cond.setInternational(vatutil.getIsDiffCountry(bbvo.getFbuysellflag())
        .booleanValue());
    // �������� cond Ϊ����ʱ�Ĳ�������
    // item.getTaxPriceKey()�����仯��key��ӳ��
    cond.setUnitPriorType(Condition.QT_PRIOR);
    //ȥ�������false ʹ��˰����ֱ��clone����������˰����=��˰���/����     panfengc
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

  /** ���ô˷���ǰ��ȷ���߽���·�������ۿ��ʣ�����������bbvo */
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
        // ������ʺ��ۿ���
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
                    .getStrByID("4009010_0", "04009010-0048")/*�ڲ�������Ϣ��·������ǰδȡ�����ʣ�������*/);
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
        // ���õ��۽���㷨
        IRelationForItems item = new BusiinfoBBVORelationItemForCal();
        IDataSetForCal data =
            new BusiinfoBBVODataSetForCal(bizhvo, bizbvo, bbvo, item);
        Calculator tool = new Calculator(data, scale);
        Condition cond = new Condition();
        cond.setIsCalLocalCurr(true);
        cond.setIsChgPriceOrDiscount(false);
        cond.setIsTaxOrNet(taxFirst);
        // �Ƿ���(6.1����)
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

    // ��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new BusiinfoBBVORelationItemForCal();
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = new BusiinfoBBVODataSetForCal(hvo, bvo, bbvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();
    // �����Ƿ���б��һ���
    cond.setIsCalLocalCurr(true);
    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    // �����Ƿ�̶���λ������
    cond.setIsFixNchangerate(true);
    // �Ƿ�̶����۵�λ������
    cond.setIsFixNqtunitrate(true);
    // ���ú�˰���Ȼ�����˰����
    cond.setIsTaxOrNet(isTaxfirst.booleanValue());
    // �Ƿ���(6.1����)
    cond.setInternational(vatutil.getIsDiffCountry(bbvo.getFbuysellflag())
        .booleanValue());
    // �������� cond Ϊ����ʱ�Ĳ�������
    // item.getTaxPriceKey()�����仯��key��ӳ��
    cond.setUnitPriorType(Condition.QT_PRIOR);
    cond.setCurCloneAllowed(false);
    tool.calculateLocalCurrenyMny(cond);
    bbvo.setStatus(VOStatus.UPDATED);
  }

  public static void calcBVO(BusinessinfoHVO hvo, BusinessinfoBVO bvo,
      String changedKey, UFBoolean isTaxfirst) {
    TOVatTriaUtils vatutil = new TOVatTriaUtils();
    // ��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new BusiinfoBVORelationItemForCal();
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = new BusiinfoBVODataSetForCal(hvo, bvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();

    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    // �����Ƿ�̶���λ������
    cond.setIsFixNchangerate(true);
    // �Ƿ�̶����۵�λ������
    cond.setIsFixNqtunitrate(true);
    // ���ú�˰���Ȼ�����˰����
    cond.setIsTaxOrNet(isTaxfirst.booleanValue());
    cond.setIsCalLocalCurr(true);
    cond.setCurCloneAllowed(false);
    // �Ƿ���(6.1����)
    cond.setInternational(vatutil.getIsDiffCountry(hvo.getFbuysellflag())
        .booleanValue());
    // �������� cond Ϊ����ʱ�Ĳ�������
    // item.getTaxPriceKey()�����仯��key��ӳ��
    cond.setUnitPriorType(Condition.QT_PRIOR);
    tool.calculate(cond, changedKey);
    bvo.setStatus(VOStatus.UPDATED);

  }

  public static void calcBVOLocal(BusinessinfoHVO hvo, BusinessinfoBVO bvo) {
    TOVatTriaUtils vatutil = new TOVatTriaUtils();
    UFBoolean ufbTaxFirst =
        ParaUtilsForTo.getInstance().getTO01(hvo.getPk_group());
    boolean taxFirst = ufbTaxFirst == null ? true : ufbTaxFirst.booleanValue();

    // ��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new BusiinfoBVORelationItemForCal();
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IDataSetForCal data = new BusiinfoBVODataSetForCal(hvo, bvo, item);
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    Calculator tool = new Calculator(data, scale);
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = new Condition();
    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    // �����Ƿ�̶���λ������
    cond.setIsFixNchangerate(true);
    // �Ƿ�̶����۵�λ������
    cond.setIsFixNqtunitrate(true);
    // ���ú�˰���Ȼ�����˰����
    cond.setIsTaxOrNet(taxFirst);
    // �Ƿ���(6.1����)
    cond.setInternational(vatutil.getIsDiffCountry(hvo.getFbuysellflag())
        .booleanValue());
    // �������� cond Ϊ����ʱ�Ĳ�������
    // item.getTaxPriceKey()�����仯��key��ӳ��
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
                .getStrByID("4009010_0", "04009010-0038")/*@res "ȡ�����֯����������֯��δȡ��"*/);
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
