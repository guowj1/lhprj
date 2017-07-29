package nc.impl.lhprj;

import nc.impl.pub.ace.AceLhqtstddayPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhqtstdday.AggLhQtStdDayVO;
import nc.itf.lhprj.ILhqtstddayMaintain;
import nc.vo.pub.BusinessException;

public class LhqtstddayMaintainImpl extends AceLhqtstddayPubServiceImpl
		implements ILhqtstddayMaintain {

	@Override
	public void delete(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdDayVO[] insert(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdDayVO[] update(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdDayVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggLhQtStdDayVO[] save(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdDayVO[] unsave(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdDayVO[] approve(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdDayVO[] unapprove(AggLhQtStdDayVO[] clientFullVOs,
			AggLhQtStdDayVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
