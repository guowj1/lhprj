package nc.bs.lhitf.bgtask;

import nc.bs.lhitf.bgtask.pub.PostDataImp;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.pub.BusinessException;

public class AutoTransCustomerBgTaskPlugin implements IBackgroundWorkPlugin {

	private IMDPersistenceQueryService mdQueryService;

	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {

		// 获取需要发送的vo数组
		CustomerVO[] vos = null;

		vos = (CustomerVO[]) getMdQueryService().queryBillOfVOByCond(
//				CustomerVO.class, " (def2='~' or def2 is null or def2<ts) and def1='1001A6100000000000XU' ",
				CustomerVO.class, " (def2='~' or def2 is null) and def1='1001A6100000000000XU' ",
				false).toArray(new CustomerVO[0]);

		if (vos == null || vos.length < 1) {
			return null;
		}

		try {
			PostDataImp postServ=new PostDataImp();
			postServ.sendDataByStr(vos, "customer");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		return null;
	}

	private IMDPersistenceQueryService getMdQueryService() {
		if (mdQueryService == null)
			mdQueryService = MDPersistenceService
					.lookupPersistenceQueryService();
		return mdQueryService;
	}

}
