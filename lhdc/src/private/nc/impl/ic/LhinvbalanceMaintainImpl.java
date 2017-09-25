package nc.impl.ic;

import nc.impl.pub.ace.AceLhinvbalancePubServiceImpl;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;
import nc.itf.ic.ILhinvbalanceMaintain;

public class LhinvbalanceMaintainImpl extends AceLhinvbalancePubServiceImpl
		implements ILhinvbalanceMaintain {

	@Override
	public InvBalanceVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybasedoc(queryScheme);
	}

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
		BatchSaveAction<InvBalanceVO> saveAction = new BatchSaveAction<InvBalanceVO>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}
}
