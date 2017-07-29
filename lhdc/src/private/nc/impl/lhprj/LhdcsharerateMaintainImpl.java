package nc.impl.lhprj;

import nc.impl.pub.ace.AceLhdcshareratePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcsharerate.AggLhShareRateVO;
import nc.itf.lhprj.ILhdcsharerateMaintain;
import nc.vo.pub.BusinessException;

public class LhdcsharerateMaintainImpl extends AceLhdcshareratePubServiceImpl
		implements ILhdcsharerateMaintain {

	@Override
	public void delete(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhShareRateVO[] insert(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhShareRateVO[] update(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhShareRateVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggLhShareRateVO[] save(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhShareRateVO[] unsave(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhShareRateVO[] approve(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhShareRateVO[] unapprove(AggLhShareRateVO[] clientFullVOs,
			AggLhShareRateVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
