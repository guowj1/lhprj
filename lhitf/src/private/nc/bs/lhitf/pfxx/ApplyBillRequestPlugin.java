package nc.bs.lhitf.pfxx;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveStatus;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.apply.ApplyVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhoaverify.LhoaVerifyVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.AppContext;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class ApplyBillRequestPlugin extends AbstractPfxxPlugin {

	// private IMDPersistenceQueryService mdQueryService;

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {

		LhoaVerifyVO verifyVO = (LhoaVerifyVO) vo;

		String billid = swapContext.getDocID();
		if (StringUtil.isEmpty(billid)) {
			throw new BusinessException("流程ID不允许为空！");
		}
		String status = verifyVO.getApprovestatus();
		String approvenote = verifyVO.getApprovenote();
		if (StringUtil.isEmpty(status)) {
			throw new BusinessException("审批状态不允许为空！");
		}
		if ("1".equals(status) || "2".equals(status)) {
			updateVouchStatus(billid, status, approvenote);
		} else
			throw new BusinessException("审批状态只能为1(审批通过)或2(审批未通过)！");

		return null;
	}

	private void updateVouchStatus(String billid, String status,
			String approvenote) throws BusinessException {

		BaseDAO baseDao = new BaseDAO();

		ApplyVO[] headVOs = (ApplyVO[]) baseDao.retrieveByClause(ApplyVO.class,
				" vdef5='" + billid + "' and dr=0 ").toArray(new ApplyVO[0]);

		if (headVOs == null || headVOs.length < 1) {
			throw new BusinessException("NC系统中未查询到流程ID[" + billid + "]对应的单据！");
		} else if (headVOs.length > 1) {
			throw new BusinessException("NC系统中查询到流程ID[" + billid
					+ "]对应的单据不止一张，请检查该问题！");
		}

		BillQuery<AggApplyVO> billQrySrv = new BillQuery<AggApplyVO>(
				AggApplyVO.class);
		AggApplyVO[] vos = billQrySrv.query(new String[] { headVOs[0]
				.getPk_apply() });

		// AggApplyVO[] vos=(AggApplyVO[])
		// baseDao.retrieveByClause(AggApplyVO.class,
		// " vdef5='"+billid+"' ").toArray(new AggApplyVO[0]);

		String pk_apply = vos[0].getParentVO().getPk_apply();

		nc.itf.cmp.IApplyService operator = NCLocator.getInstance().lookup(
				nc.itf.cmp.IApplyService.class);
		if (vos[0].getParentVO().getVbillstatus() != ApproveStatus.COMMIT) {
			throw new BusinessException("NC单据状态非提交态，不允许再通过OA系统更改NC系统单据状态");
		}
		if ("1".equals(status)) {
			try {

				AggApplyVO voNew = new AggApplyVO();

				ApplyVO head = new ApplyVO();
				head = (ApplyVO) vos[0].getParentVO().clone();
				head.setApprover(INCSystemUserConst.NC_USER_PK);
				head.setDapprovedate(AppContext.getInstance().getServerTime()
						.getDate());
				head.setVbillstatus(ApproveStatus.APPROVED);
				head.setBusistatus(Integer.valueOf(3));
				head.setApprovenote(approvenote);
				ArrayList<ApplyBVO> alBodys=new ArrayList<ApplyBVO>();
				for(CircularlyAccessibleValueObject bvo:vos[0].getChildrenVO()){
					ApplyBVO bvonew=null;
					bvonew=(ApplyBVO) bvo.clone();
					bvonew.setVbillstatus(ApproveStatus.APPROVED);
					bvonew.setBusistatus(Integer.valueOf(3));
					alBodys.add(bvonew);
				}
				ApplyBVO[] bodys = alBodys.toArray(new ApplyBVO[0]);
				voNew.setParentVO(head);
				voNew.setChildrenVO(bodys);
				
				AggApplyVO[] vosNew = new AggApplyVO[] { voNew };
				operator.approve(vosNew, vos);
			} catch (BusinessException e) {
				throw new BusinessException("NC单据审批失败:" + e.getMessage());
			}
		} else if ("2".equals(status)) {
			try {

				AggApplyVO voNew = new AggApplyVO();

				ApplyVO head = new ApplyVO();
				head = (ApplyVO) vos[0].getParentVO().clone();
				head.setApprover(INCSystemUserConst.NC_USER_PK);
				head.setDapprovedate(AppContext.getInstance().getServerTime()
						.getDate());
				head.setVbillstatus(ApproveStatus.NOPASS);
				head.setBusistatus(Integer.valueOf(1));//待提交
				head.setApprovenote(approvenote);
				ArrayList<ApplyBVO> alBodys=new ArrayList<ApplyBVO>();
				for(CircularlyAccessibleValueObject bvo:vos[0].getChildrenVO()){
					ApplyBVO bvonew=null;
					bvonew=(ApplyBVO) bvo.clone();
					bvonew.setVbillstatus(ApproveStatus.NOPASS);
					bvonew.setBusistatus(Integer.valueOf(1));//待提交
					alBodys.add(bvonew);
				}
				ApplyBVO[] bodys = alBodys.toArray(new ApplyBVO[0]);
				voNew.setParentVO(head);
				voNew.setChildrenVO(bodys);
				
				AggApplyVO[] vosNew = new AggApplyVO[] { voNew };
				operator.approve(vosNew, vos);
			} catch (BusinessException e) {
				throw new BusinessException("NC单据审批失败:" + e.getMessage());
			}

//			StringBuilder sql = new StringBuilder();
//			sql.append("update cmp_apply set busistatus=1 where pk_apply='" + pk_apply + "'");
//			baseDao.executeUpdate(sql.toString());
//			
//			sql=new StringBuilder();
//			sql.append("update cmp_apply_b set busistatus=1 where pk_apply='" + pk_apply + "'");
//			baseDao.executeUpdate(sql.toString());
			
			
//			String msg = "OA审批不通过";
//			if (!StringUtil.isEmptyWithTrim(approvenote)) {
//				msg += "，审批意见：" + approvenote;
//			}
//			 voNew[0].getParentVO().setVdef5(msg);
//			 operator.update(voNew, vos);
			// 判断单据是否已经审批，若已经审批则不允许审批不通过

		}

	}

	// private IMDPersistenceQueryService getMdQueryService() {
	// if (mdQueryService == null)
	// mdQueryService = MDPersistenceService
	// .lookupPersistenceQueryService();
	// return mdQueryService;
	// }

}
