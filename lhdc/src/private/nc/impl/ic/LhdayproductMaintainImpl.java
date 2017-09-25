package nc.impl.ic;

import nc.impl.pub.ace.AceLhdayproductPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.sxlhscm.lhdayproduct.AggDayProductHVO;
import nc.itf.ic.ILhdayproductMaintain;
import nc.vo.pub.BusinessException;

public class LhdayproductMaintainImpl extends AceLhdayproductPubServiceImpl
		implements ILhdayproductMaintain {

	@Override
	public void delete(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggDayProductHVO[] insert(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggDayProductHVO[] update(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggDayProductHVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggDayProductHVO[] save(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggDayProductHVO[] unsave(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggDayProductHVO[] approve(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggDayProductHVO[] unapprove(AggDayProductHVO[] clientFullVOs,
			AggDayProductHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
