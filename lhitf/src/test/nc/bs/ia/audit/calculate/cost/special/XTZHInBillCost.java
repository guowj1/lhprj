package nc.bs.ia.audit.calculate.cost.special;

import nc.vo.ia.audit.AuditVO;
import nc.vo.ia.detailledger.pub.AuditMsgUtil;
import nc.vo.ia.detailledger.view.ia.AuditViewVO;
import nc.vo.ia.pub.util.IAScaleUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import nc.bs.ia.audit.calculate.cost.special.data.XTZHBillData;
import nc.bs.ia.audit.calculate.cost.special.pub.BillDataFactory;
import nc.bs.ia.audit.calculate.cost.special.pub.SpecialCostUtil;
import nc.bs.ia.audit.pub.AuditPageSession;
import nc.bs.ia.audit.pub.IAContext;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * ��̬ת����ⵥ�ɱ������㷨 һ����̬ת�����ⵥ��Ӧ������̬ת����ⵥ
 * 
 * @since 6.0
 * @version 2010-12-8 ����01:59:13
 * @author Ƥ֮��
 */
public class XTZHInBillCost implements IRule<AuditVO> {

  @Override
  public void process(AuditVO[] auditvos) {
    AuditViewVO view = auditvos[0].getAuditViewVO();
    AuditPageSession session = auditvos[0].getAuditPageSession();
    IAContext context = session.getContext();

    BillDataFactory factory = context.getBillDataFactory();
    XTZHBillData billData = factory.getXTZHBillData(view, context);

    UFDouble nmny = null;

    // ת���ɱ� gwj add
    UFDouble cost = billData.getCost();
    
    UFDouble auditedInMny = billData.getAuditedInMny();
    UFDouble unauditedInMny = billData.getUnAuditedInMny();
    UFDouble auditedOutMny = billData.getAuditedOutMny();
    auditedOutMny=MathTool.add(auditedOutMny, cost);//gwj add
    UFDouble allInMny = MathTool.add(auditedInMny, unauditedInMny);
    UFDouble leftMny = MathTool.sub(auditedOutMny, allInMny);
    
//    auditOutMny = MathTool.add(auditOutMny, cost);

    IAScaleUtil scale = IAScaleUtil.getScaleUtils();
    // ���һ���޳ɱ�����ⵥ
    if (billData.getHasNoCostInBillNum() == 1) {
      nmny = leftMny;
    }
    else {
      // �����̯���� = ��ǰ���ݵ���̬ת���ο��ɱ�/�����޳ɱ���ⵥ����̬ת���ο��ɱ�֮��
      UFDouble cuurentVirtulMny = billData.getCurrentVirtualMny(view);
      // �����̯���� = ��ǰ���ݵ���̬ת���ο��ɱ�/�����޳ɱ���ⵥ����̬ת���ο��ɱ�֮��
//      cuurentVirtulMny=MathTool.add(cuurentVirtulMny, cost);//gwj add
      UFDouble rate = cuurentVirtulMny.div(billData.getVirtualMny());
      nmny = auditedOutMny.multiply(rate);
      nmny = scale.adjustMnyScale(nmny, context.getCcurrencyid());

      allInMny = MathTool.add(allInMny, nmny);
      if (MathTool.absCompareTo(allInMny, auditedOutMny) > 0) {
        if (view.getNmny() == null) {
          /*  @res "�Ѿ��ɱ����������ϼƴ��ڳ�����ϼƣ�" */
          String message =
              NCLangRes4VoTransl.getNCLangRes().getStrByID("2014002_0",
                  "02014002-0069")/*@res "�Ѿ��ɱ����������ϼƴ��ڳ�����ϼƣ�"*/;
          boolean isPromptStop = context.isPromptStop();
          AuditMsgUtil.wrappBusinessException(view, message, isPromptStop);
        }
      }
    }

    UFDouble nprice = nmny.div(view.getNnum());
    nprice = scale.adjustPriceScale(nprice);

    SpecialCostUtil.cost(view, nmny, nprice);
  }

}
