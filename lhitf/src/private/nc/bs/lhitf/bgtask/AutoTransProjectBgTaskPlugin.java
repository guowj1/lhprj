package nc.bs.lhitf.bgtask;

import nc.bs.lhitf.bgtask.pub.PostDataImp;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.pub.BusinessException;

public class AutoTransProjectBgTaskPlugin implements IBackgroundWorkPlugin {

	private IMDPersistenceQueryService mdQueryService;

	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		DefdocVO[] vos = null;
		vos = (DefdocVO[]) getMdQueryService()
				.queryBillOfVOByCond(
						DefdocVO.class,
//						" (def2='~' or def2 is null or def2<ts) and pk_defdoclist=(select pk_defdoclist from bd_defdoclist where code='zdyx001') ",
						" (def2='~' or def2 is null) and pk_defdoclist=(select pk_defdoclist from bd_defdoclist where code='zdyx001') ",
						false).toArray(new DefdocVO[0]);

		if (vos == null || vos.length < 1) {
			return null;
		}

		try {
			PostDataImp postServ = new PostDataImp();
			postServ.sendDataByStr(vos, "defdoc");
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
