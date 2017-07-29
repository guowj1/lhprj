package nc.bs.ia.audit.preprocess.special;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.ia.audit.PreprocessVO;
import nc.vo.ia.detailledger.view.ia.AuditViewVO;
import nc.vo.ia.pub.data.FieldConst;

import nc.bs.ia.audit.calculate.cost.special.data.XTZHBillData;
import nc.bs.ia.audit.calculate.cost.special.pub.BillDataFactory;
import nc.bs.ia.audit.preprocess.pub.PreProcessUtil;
import nc.bs.ia.audit.pub.BizJudgeUtil;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 形态转换业务数据预处理
 * 
 * @since 6.3
 * @version 2013-07-11 09:26:35
 * @author 于浩洋
 */
public class XTZHDataPreprocess implements IRule<PreprocessVO> {

  @Override
  public void process(PreprocessVO[] vos) {
    AuditViewVO[] views = vos[0].getAuditViewVOS();
    Set<String> keys = new HashSet<String>();
    BillDataFactory factory = vos[0].getIAContext().getBillDataFactory();
    for (AuditViewVO view : views) {
      if (BizJudgeUtil.isXTZH(view)) {
        String key = factory.getMapKey(view);
        if (!factory.getMap().containsKey(key)) {
          keys.add(key);
        }
      }
    }
    if (keys.size() == 0) {
      return;
    }
    PreProcessUtil preprocessutil = new PreProcessUtil();
    String[] qryFields = this.getQryFields();
    boolean isPromptStop = vos[0].getIAContext().isPromptStop();
    Map<String, List<AuditViewVO>> viewMap =
        preprocessutil.bqueryRelatedData(qryFields, keys, isPromptStop);
    for (Entry<String, List<AuditViewVO>> entry : viewMap.entrySet()) {
      XTZHBillData billdata = new XTZHBillData();
      AuditViewVO[] viewvos = entry.getValue().toArray(new AuditViewVO[0]);
      billdata.setContext(vos[0].getIAContext());
      billdata.setPk_org(viewvos[0].getPk_org());
      billdata.setPriceIndex(factory.getXtzhPriceIndex());
      billdata.construct(viewvos);
      factory.putBillData(entry.getKey(), billdata);
    }
  }

  private String[] getQryFields() {
    PreProcessUtil util = new PreProcessUtil();
    List<String> qryFields = util.getCommonQryFields();
    qryFields.add(FieldConst.FCALCBIZFLAG);
    qryFields.add(FieldConst.CINVENTORYID);
    qryFields.add(FieldConst.PK_ORG);
    qryFields.add(FieldConst.NCOST);//gwj add
    return qryFields.toArray(new String[0]);
  }

}
