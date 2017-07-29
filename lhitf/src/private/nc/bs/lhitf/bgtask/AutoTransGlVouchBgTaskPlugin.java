package nc.bs.lhitf.bgtask;

import nc.bs.framework.common.NCLocator;
import nc.bs.lhitf.bgtask.pub.PostDataImp;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.gl.voucher.IVoucher;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.gl.pubvoucher.VoucherVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

public class AutoTransGlVouchBgTaskPlugin implements IBackgroundWorkPlugin {

//	private IMDPersistenceQueryService mdQueryService;

	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		VoucherVO[] vos = null;
		vos = NCLocator
				.getInstance()
				.lookup(IVoucher.class)
				.queryByCondition(
//						" (free9='~' or free9 is null or free9<ts) and pk_vouchertype in (select pk_vouchertype from bd_vouchertype where code not like 'Y%') and pk_org in (select pk_org from org_orgs where def1 in('Z','G')) ",
						" (free9='~' or free9 is null) and pk_vouchertype in (select pk_vouchertype from bd_vouchertype where code not like 'Y%') and pk_org in (select pk_org from org_orgs where def1 in('Z','G')) ",
//						" pk_voucher in (select pk_voucher from ef_glvoucher_tmp) ",
						true, AppContext.getInstance().getPkGroup());
		// vos = (VoucherVO[]) getMdQueryService()
		// .queryBillOfVOByCond(
		// VoucherVO.class,
		// " (free9='~' or free9 is null or free9<ts) and pk_vouchertype in (select pk_vouchertype from bd_vouchertype where code not like 'Y%') and pk_org in (select pk_org from org_orgs where def1 in('Z','G')) ",
		// false).toArray(new VoucherVO[0]);

		if (vos == null || vos.length < 1) {
			return null;
		}

		try {
//			for(VoucherVO vo:vos){
//				if(vo.getFree9()==null || "".equals(vo.getFree9())|| "~".equals(vo.getFree9())){
//					vo.setPk_voucher("");
//				}
//				else{
//					vo.setPk_voucher(vo.getFree8());
//				}
//			}
			
			PostDataImp postServ = new PostDataImp();
			postServ.sendDataByStr(vos, "vouchergl");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		return null;
	}

//	private IMDPersistenceQueryService getMdQueryService() {
//		if (mdQueryService == null)
//			mdQueryService = MDPersistenceService
//					.lookupPersistenceQueryService();
//		return mdQueryService;
//	}

}
