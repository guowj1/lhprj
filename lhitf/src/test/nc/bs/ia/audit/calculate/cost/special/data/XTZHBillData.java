package nc.bs.ia.audit.calculate.cost.special.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.bd.accessor.IBDData;
import nc.vo.ia.detailledger.view.ia.AuditViewVO;
import nc.vo.ia.enumeration.FDataGetFlag;
import nc.vo.ia.enumeration.FDispatchFlag;
import nc.vo.ia.enumeration.FXTZHCostFlag;
import nc.vo.ia.pub.data.FieldConst;
import nc.vo.ia.pub.util.IAParameter;
import nc.vo.ia.pub.util.IAScaleUtil;
import nc.vo.ia.pub.util.ToArrayUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;

import nc.bs.ia.audit.calculate.cost.special.pub.AbstractBillData;
import nc.bs.ia.audit.calculate.cost.special.pub.InOutCostData;
import nc.bs.ia.audit.pub.IAContext;
import nc.bs.ia.audit.pub.PriceTool;

/**
 * ��̬ת��������Ϣ һ�ų����Ӧ�������
 * 
 * @since 6.0
 * @version 2010-12-7 ����04:59:10
 * @author Ƥ֮��
 */
public class XTZHBillData extends AbstractBillData {

  // �����޳ɱ����ݵ�����ɱ�֮��
  private UFDouble allVirturalMny = UFDouble.ZERO_DBL;

  private IAContext context;

  // �Ƿ��Ѿ�lazy����������ɱ�����Ҫ�Ǳ�֤������ⵥʱ�ܹ�ͨ������Ϊ���ⵥ����Ҫ��������ɱ�
  private boolean hasLazyComputed;

  // �޳ɱ�����ⵥ����������ȥ�ֹ�¼����ⵥ�ݺ��Ѿ��ɱ�������ĵ��ݣ�
  private int hasNoCostInBillNum;

  private String pk_org;

  // ��̬ת����������۵���������Ϊȫ�ֱ��������ⲿ����
  private Map<String, UFDouble> priceIndex;

  // ��������ɱ���ʱ����Ҫ�Ĵ������
  private List<Object[]> virtualList = new ArrayList<Object[]>();
  
  //ת���ɱ�  gwj add
  private UFDouble cost = UFDouble.ZERO_DBL;

  // ����ɱ�������
  private Map<String, UFDouble> virtualMnyMap = new HashMap<String, UFDouble>();

  public static XTZHBillData loadFromDB(AuditViewVO view, IAContext context,
      Map<String, UFDouble> priceIndex, String key) {
    XTZHBillData data = new XTZHBillData();
    data.key = key;
    data.pk_org = view.getPk_org();
    data.context = context;
    data.priceIndex = priceIndex;
    AuditViewVO[] views = data.queryData();
    data.construct(views);
    return data;
  }
  
  //gwj
  public UFDouble getCost() {
	  return cost;
  }

  public void setCost(UFDouble cost) {
	  this.cost = cost;
  }

  public UFDouble getCurrentVirtualMny(AuditViewVO view) {
    this.lazyComputedVirtualCost();
    String virturalMnykey = view.getCinventoryid() + "|" + view.getNnum();
    return this.virtualMnyMap.get(virturalMnykey);
  }

 

public int getHasNoCostInBillNum() {
    return this.hasNoCostInBillNum;
  }

  /**
   * 
   * 
   * @param context
   */
  public void setContext(IAContext context) {
    this.context = context;
  }

  /**
   * 
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

  /**
   * 
   * 
   * @param priceIndex
   */
  public void setPriceIndex(Map<String, UFDouble> priceIndex) {
    this.priceIndex = priceIndex;
  }

  public UFDouble getVirtualMny() {
    this.lazyComputedVirtualCost();
    return this.allVirturalMny;
  }

  @Override
  protected void registerIn(AuditViewVO view) {
    super.registerIn(view);
    Integer getMode = view.getFdatagetflag();
    if (!FDataGetFlag.SGSR.equalsValue(getMode)) {
      this.hasNoCostInBillNum--;
    }
  }

  /**
   * ��д�Ѿ�ȡ���ɱ��������ⵥ����Ϣ
   * 
   * @param view
   *          AuditViewVO
   */
  @Override
  protected void unRegisterIn(AuditViewVO view) {
    super.unRegisterIn(view);
    Integer getMode = view.getFdatagetflag();
    if (!FDataGetFlag.SGSR.equalsValue(getMode)) {
      this.hasNoCostInBillNum++;
    }
  }

  public void construct(AuditViewVO[] views) {
    List<InOutCostData> list = new ArrayList<InOutCostData>();
    for (AuditViewVO relateview : views) {
      InOutCostData costData = super.create(relateview);
      list.add(costData);

      Integer fdispatchflag = costData.fdispatchflag;
      Integer getMode = costData.fdatagetflag;
      if (FDispatchFlag.IN.equalsValue(fdispatchflag)
          && !FDataGetFlag.SGSR.equalsValue(getMode)) {
        // ��ʱ���ܹ������ݽṹ���ǳ��ⵥ��������Ҫ��������ɱ���Ϊ�˷�ֹ����
        // ����ɱ����ʴ������ʱû��¼��ο��ɱ�/�ƻ��ɱ������³��ⵥ���ܼ�
        // ��ͨ������ʱ������Щ��������ݣ����õ�����ɱ�ʱ�ټ�����Щ���
        Object[] virtualItemIndex = new Object[2];
        virtualItemIndex[0] = relateview.getCinventoryid();
        virtualItemIndex[1] = relateview.getNnum();

        this.virtualList.add(virtualItemIndex);
        if (costData.nmny == null) {
          this.hasNoCostInBillNum++;
        }
      }
      
      UFDouble ncost = relateview.getNcost();
      if (ncost != null) {
        this.cost = ncost;
      }
    }
    InOutCostData[] costDatas = ToArrayUtil.convert(list);
    this.construct(costDatas);
  }

  private UFDouble getXTZHFTYJ(String cinventoryid,
      Map<String, IBDData> inventoryDataMap) {
    UFDouble price = null;
    String pricekey = cinventoryid;
    price = this.priceIndex.get(pricekey);
    if (price != null) {
      return price;
    }
    IBDData cinventoryData = inventoryDataMap.get(pricekey);
    String pk_book = this.context.getPk_book();
    FXTZHCostFlag[] paras = IAParameter.getIA0203(this.pk_org);

    for (int i = 0; i < paras.length; i++) {
      if (FXTZHCostFlag.CKCB_NUM.equals(paras[i])) {
        price = PriceTool.getReferencePrice(this.pk_org, cinventoryid);
        if (price == null) {
          String[] paras1 = new String[] {
            cinventoryData.getCode()
          };
          String msg =
              NCLangRes4VoTransl.getNCLangRes().getStrByID("2014002_0",
                  "02014002-0096", null, paras1)/*@res "��ǰ�����ڵ�ǰ�ɱ�����û�����òο��ɱ�"*/;
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
      else if (FXTZHCostFlag.JHCB_NUM.equals(paras[i])) {
        price = PriceTool.getPlanedPrice(this.pk_org, pk_book, cinventoryid);
        if (price == null) {
          String[] paras1 = new String[] {
            cinventoryData.getCode()
          };
          String msg =
              NCLangRes4VoTransl.getNCLangRes().getStrByID("2014002_0",
                  "02014002-0097", null, paras1)/*@res "��ǰ�����ڵ�ǰ�ɱ�����û�����üƻ��ɱ�"*/;
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
      else if (FXTZHCostFlag.NUM.equals(paras[i])) {
        price = UFDouble.ONE_DBL;
      }
      if (price != null) {
        break;
      }
    }
    this.priceIndex.put(pricekey, price);
    return price;
  }

  private void lazyComputedVirtualCost() {
    if (this.hasLazyComputed) {
      return;
    }

    IAScaleUtil scale = IAScaleUtil.getScaleUtils();
    Set<String> cinventoryidlist = new HashSet<String>();
    for (Object[] obj : this.virtualList) {
      String inventoryid = (String) obj[0];
      cinventoryidlist.add(inventoryid);
    }
    String[] cinventoryids = ToArrayUtil.convert(cinventoryidlist);
    IBDData[] bddatas = MaterialAccessor.getDocbyPks(cinventoryids);
    Map<String, IBDData> inventoryDataMap = new HashMap<String, IBDData>();
    for (IBDData data : bddatas) {
      inventoryDataMap.put(data.getPk(), data);
    }

    for (Object[] obj : this.virtualList) {
      String inventoryid = (String) obj[0];
      UFDouble num = (UFDouble) obj[1];
      UFDouble virtualPrice = this.getXTZHFTYJ(inventoryid, inventoryDataMap);
      UFDouble virtualMny = virtualPrice.multiply(num);
      virtualMny =
          scale.adjustMnyScale(virtualMny, this.context.getCcurrencyid());
      this.allVirturalMny = MathTool.add(this.allVirturalMny, virtualMny);

      // ����+������ΪKEY
      String virturalMnykey = inventoryid + "|" + num;
      this.virtualMnyMap.put(virturalMnykey, virtualMny);
    }
    this.hasLazyComputed = true;
  }

  /**
   * �����ݿ��в�ѯ������Ϣ
   * 
   * @param view
   *          AuditViewVO
   * @return IRowSet
   */
  private AuditViewVO[] queryData() {
    String[] qryFields =
        new String[] {
          FieldConst.NNUM, FieldConst.NPRICE, FieldConst.NMNY,
          FieldConst.APPROVER, FieldConst.FDATAGETFLAG,
          FieldConst.FDISPATCHFLAG, FieldConst.FCALCBIZFLAG,
          FieldConst.CINVENTORYID,
          FieldConst.NCOST// gwj add
        };
    return super.queryRelatedData(qryFields);
  }
}
