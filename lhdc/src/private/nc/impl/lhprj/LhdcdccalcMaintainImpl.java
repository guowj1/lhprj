package nc.impl.lhprj;

import java.util.ArrayList;

import nc.impl.pub.ace.AceLhdcdccalcPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcDetailVO;
import nc.itf.lhprj.ILhdcdccalcMaintain;
import nc.vo.pub.BusinessException;

public class LhdcdccalcMaintainImpl extends AceLhdcdccalcPubServiceImpl
		implements ILhdcdccalcMaintain {

	@Override
	public void delete(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhDayCostCalcVO[] insert(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhDayCostCalcVO[] update(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhDayCostCalcVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggLhDayCostCalcVO[] save(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhDayCostCalcVO[] unsave(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhDayCostCalcVO[] approve(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhDayCostCalcVO[] unapprove(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ArrayList<LhDayCostCalcDetailVO> calcCost(String pk_org, String cDate)
			throws BusinessException {
		return super.pubCalcCost(pk_org, cDate);
	}

}
