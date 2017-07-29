package nc.bs.lhitf.bgtask;

import nc.bs.lhitf.bgtask.pub.PostDataImp;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.bankaccount.BankAccbasVO;
import nc.vo.pub.BusinessException;

public class AutoTransBankAccBgTaskPlugin implements IBackgroundWorkPlugin {

	private IMDPersistenceQueryService mdQueryService;

	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		BankAccbasVO[] vos = null;
		vos = (BankAccbasVO[]) getMdQueryService().queryBillOfVOByCond(
//				BankAccbasVO.class, " (def2='~' or def2 is null or def2<ts) and def1='1001A6100000000000XU' ",
				BankAccbasVO.class, " (def2='~' or def2 is null) and def1='1001A6100000000000XU' ",
				false).toArray(new BankAccbasVO[0]);

		if (vos == null || vos.length < 1) {
			return null;
		}

		try {
			PostDataImp postServ = new PostDataImp();
			postServ.sendDataByStr(vos, "bankaccbas");
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
