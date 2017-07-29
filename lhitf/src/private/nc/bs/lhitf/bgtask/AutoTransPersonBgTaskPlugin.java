package nc.bs.lhitf.bgtask;

import nc.bs.lhitf.bgtask.pub.PostDataImp;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.pub.BusinessException;

public class AutoTransPersonBgTaskPlugin implements IBackgroundWorkPlugin {

	private IMDPersistenceQueryService mdQueryService;

	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		PsndocVO[] vos = null;
		vos = (PsndocVO[]) getMdQueryService().queryBillOfVOByCond(
//				PsndocVO.class, " (def2='~' or def2 is null or def2<ts) and pk_psndoc in (select distinct pk_psndoc from bd_psnjob where pk_psncl=(select pk_psncl from bd_psncl where name='在职人员')) ",
				PsndocVO.class, " (def2='~' or def2 is null ) and pk_psndoc in (select distinct pk_psndoc from bd_psnjob where pk_psncl=(select pk_psncl from bd_psncl where name='在职人员')) ",
				false).toArray(new PsndocVO[0]);
		if (vos == null || vos.length < 1) {
			return null;
		}

		try {
			PostDataImp postServ = new PostDataImp();
			postServ.sendDataByStr(vos, "psndoc");
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
