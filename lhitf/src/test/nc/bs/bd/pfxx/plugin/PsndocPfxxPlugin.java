package nc.bs.bd.pfxx.plugin;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.itf.bd.psn.psndoc.IPsndocService;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.bd.psn.PsnjobVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pfxx.util.PfxxPluginUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class PsndocPfxxPlugin extends nc.bs.pfxx.plugin.AbstractPfxxPlugin {

	private IPsndocService service = null;
	private IMDPersistenceQueryService queryService = null;

	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggxsysvo) throws BusinessException {

		// 1.得到转换后的VO数据,取决于向导第一步注册的VO信息
		PsndocVO resvo = (PsndocVO) vo;
		checkBeforeImport(resvo);
		String vopk = PfxxPluginUtils.queryBillPKBeforeSaveOrUpdate(
				swapContext.getBilltype(), swapContext.getDocID(),
				swapContext.getOrgPk());
		resvo = getPsndocVOForSave(resvo, vopk);
		if (StringUtils.isBlank(vopk)) {
			resvo = getPsndocService().insertPsndoc(resvo, false);
			PfxxPluginUtils.addDocIDVsPKContrast(swapContext.getBilltype(),
					swapContext.getDocID(), swapContext.getOrgPk(),
					resvo.getPrimaryKey());
		} else {
			getPsndocService().updatePsndoc(resvo, false);
		}
		return vopk;
	}

	private void checkBeforeImport(PsndocVO resvo) throws BusinessException {
		if (ArrayUtils.isEmpty(resvo.getPsnjobs())) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("10140psn", "010140psn0059"));// 人员必须有一条任职信息
		}
	}

	private PsndocVO getPsndocVOForSave(PsndocVO resvo, String vopk)
			throws MetaDataException, BusinessException {
		List<PsnjobVO> list = new ArrayList<PsnjobVO>();
		addPsnjobVOToList(resvo, list, VOStatus.NEW);
		if (StringUtils.isEmpty(vopk)) {
			resvo.setStatus(VOStatus.NEW);
		} else {
			PsndocVO oldvo = queryOldPSndovCO(vopk);
			resvo.setPrimaryKey(vopk);
			resvo.setStatus(VOStatus.UPDATED);
			resvo.setCreationtime(oldvo.getCreationtime());
			resvo.setCreator(oldvo.getCreator());
			addPsnjobVOToList(oldvo, list, VOStatus.DELETED);
		}
		resvo.setPsnjobs(list.toArray(new PsnjobVO[0]));
		return resvo;
	}

	private void addPsnjobVOToList(PsndocVO resvo, List<PsnjobVO> list,
			int status) {
		PsnjobVO[] jobvos = resvo.getPsnjobs();
		if (jobvos != null) {
			for (PsnjobVO jobvo : jobvos) {
				jobvo.setStatus(status);
				list.add(jobvo);
			}
		}
	}

	private PsndocVO queryOldPSndovCO(String vopk) throws MetaDataException,
			BusinessException {
		NCObject obj = getIPsndocQueryService().queryBillOfNCObjectByPK(
				PsndocVO.class, vopk);
		if (obj == null) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("10140psn", "110140psn0026"));// 该数据已被删除
		}
		PsndocVO oldvo = (PsndocVO) obj.getContainmentObject();
		return oldvo;
	}

	private IPsndocService getPsndocService() {
		if (service == null)
			service = NCLocator.getInstance().lookup(IPsndocService.class);
		return service;

	}

	private IMDPersistenceQueryService getIPsndocQueryService() {
		if (queryService == null)
			queryService = NCLocator.getInstance().lookup(
					IMDPersistenceQueryService.class);
		return queryService;

	}

}
