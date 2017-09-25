package nc.impl.ic;

import nc.impl.pub.ace.AceLhcalcdayPubServiceImpl;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.ic.lhcalcday.CalcdayVO;
import nc.itf.ic.ILhcalcdayMaintain;

public class LhcalcdayMaintainImpl extends AceLhcalcdayPubServiceImpl
		implements ILhcalcdayMaintain {

	@Override
	public CalcdayVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybasedoc(queryScheme);
	}

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
		BatchSaveAction<CalcdayVO> saveAction = new BatchSaveAction<CalcdayVO>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}
}
