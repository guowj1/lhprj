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
 * 形态转换单据信息 一张出库对应多张入库
 * 
 * @since 6.0
 * @version 2010-12-7 下午04:59:10
 * @author 皮之兵
 */
public class XTZHBillData extends AbstractBillData {

  // 所有无成本单据的虚拟成本之和
  private UFDouble allVirturalMny = UFDouble.ZERO_DBL;

  private IAContext context;

  // 是否已经lazy计算了虚拟成本，主要是保证计算出库单时能够通过，因为出库单不需要计算虚拟成本
  private boolean hasLazyComputed;

  // 无成本的入库单据数量（除去手工录入入库单据和已经成本计算过的单据）
  private int hasNoCostInBillNum;

  private String pk_org;

  // 形态转换单的虚拟价的索引。作为全局变量从类外部传入
  private Map<String, UFDouble> priceIndex;

  // 计算虚拟成本的时候需要的存货数据
  private List<Object[]> virtualList = new ArrayList<Object[]>();
  
  //转换成本  gwj add
  private UFDouble cost = UFDouble.ZERO_DBL;

  // 虚拟成本的索引
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
   * 回写已经取消成本计算的入库单据信息
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
        // 此时可能构造数据结构的是出库单，它不需要计算虚拟成本。为了防止计算
        // 虚拟成本访问存货档案时没有录入参考成本/计划成本，导致出库单不能计
        // 算通过，此时保存这些存货的数据，在用到虚拟成本时再计算这些存货
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
                  "02014002-0096", null, paras1)/*@res "当前物料在当前成本域下没有设置参考成本"*/;
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
                  "02014002-0097", null, paras1)/*@res "当前物料在当前成本域下没有设置计划成本"*/;
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

      // 物料+数量作为KEY
      String virturalMnykey = inventoryid + "|" + num;
      this.virtualMnyMap.put(virturalMnykey, virtualMny);
    }
    this.hasLazyComputed = true;
  }

  /**
   * 从数据库中查询单据信息
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
