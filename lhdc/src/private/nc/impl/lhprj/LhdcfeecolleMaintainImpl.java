package nc.impl.lhprj;

import nc.impl.pub.ace.AceLhdcfeecollePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcfeecolle.AggLhFeeCollectVO;
import nc.itf.lhprj.ILhdcfeecolleMaintain;
import nc.vo.pub.BusinessException;

public class LhdcfeecolleMaintainImpl extends AceLhdcfeecollePubServiceImpl
		implements ILhdcfeecolleMaintain {

	@Override
	public void delete(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhFeeCollectVO[] insert(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhFeeCollectVO[] update(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhFeeCollectVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggLhFeeCollectVO[] save(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhFeeCollectVO[] unsave(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhFeeCollectVO[] approve(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhFeeCollectVO[] unapprove(AggLhFeeCollectVO[] clientFullVOs,
			AggLhFeeCollectVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
