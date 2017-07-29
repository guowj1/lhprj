package nc.impl.lhprj;

import nc.impl.pub.ace.AceLhqtstdtonPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lhprj.lhqtstdton.AggLhQtStdTonVO;
import nc.itf.lhprj.ILhqtstdtonMaintain;
import nc.vo.pub.BusinessException;

public class LhqtstdtonMaintainImpl extends AceLhqtstdtonPubServiceImpl
		implements ILhqtstdtonMaintain {

	@Override
	public void delete(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdTonVO[] insert(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdTonVO[] update(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdTonVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggLhQtStdTonVO[] save(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdTonVO[] unsave(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdTonVO[] approve(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggLhQtStdTonVO[] unapprove(AggLhQtStdTonVO[] clientFullVOs,
			AggLhQtStdTonVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
