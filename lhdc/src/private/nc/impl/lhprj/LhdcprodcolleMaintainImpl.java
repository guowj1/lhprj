package nc.impl.lhprj;

import nc.impl.pub.ace.AceLhdcprodcollePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhdcprodcolle.AggLhProdColleVO;
import nc.itf.lhprj.ILhdcprodcolleMaintain;
import nc.vo.pub.BusinessException;

public class LhdcprodcolleMaintainImpl extends AceLhdcprodcollePubServiceImpl
		implements ILhdcprodcolleMaintain {

	@Override
	public void delete(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhProdColleVO[] insert(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhProdColleVO[] update(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhProdColleVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggLhProdColleVO[] save(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhProdColleVO[] unsave(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhProdColleVO[] approve(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhProdColleVO[] unapprove(AggLhProdColleVO[] clientFullVOs,
			AggLhProdColleVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
