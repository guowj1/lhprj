package nc.impl.pub.ace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcInsertBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcUpdateBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcDeleteBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcUnSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcApproveBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcDetailVO;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcTmpVO;
import nc.vo.lhprj.lhdcsharerate.LhShareRateTmpVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

public abstract class AceLhdcdccalcPubServiceImpl {
	// 新增
	public AggLhDayCostCalcVO[] pubinsertBills(
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggLhDayCostCalcVO> transferTool = new BillTransferTool<AggLhDayCostCalcVO>(
					clientFullVOs);
			// 调用BP
			AceLhdcdccalcInsertBP action = new AceLhdcdccalcInsertBP();
			AggLhDayCostCalcVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggLhDayCostCalcVO[] clientFullVOs,
			AggLhDayCostCalcVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLhdcdccalcDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggLhDayCostCalcVO[] pubupdateBills(
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggLhDayCostCalcVO> transferTool = new BillTransferTool<AggLhDayCostCalcVO>(
					clientFullVOs);
			AceLhdcdccalcUpdateBP bp = new AceLhdcdccalcUpdateBP();
			AggLhDayCostCalcVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggLhDayCostCalcVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggLhDayCostCalcVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggLhDayCostCalcVO> query = new BillLazyQuery<AggLhDayCostCalcVO>(
					AggLhDayCostCalcVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggLhDayCostCalcVO[] pubsendapprovebills(
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills)
			throws BusinessException {
		AceLhdcdccalcSendApproveBP bp = new AceLhdcdccalcSendApproveBP();
		AggLhDayCostCalcVO[] retvos = bp
				.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggLhDayCostCalcVO[] pubunsendapprovebills(
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills)
			throws BusinessException {
		AceLhdcdccalcUnSendApproveBP bp = new AceLhdcdccalcUnSendApproveBP();
		AggLhDayCostCalcVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggLhDayCostCalcVO[] pubapprovebills(
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdcdccalcApproveBP bp = new AceLhdcdccalcApproveBP();
		AggLhDayCostCalcVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggLhDayCostCalcVO[] pubunapprovebills(
			AggLhDayCostCalcVO[] clientFullVOs, AggLhDayCostCalcVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLhdcdccalcUnApproveBP bp = new AceLhdcdccalcUnApproveBP();
		AggLhDayCostCalcVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

	public LhDayCostCalcDetailVO[] pubCalcCost(String pk_org, String cDate)
			throws BusinessException {
		StringBuilder sql = new StringBuilder();
		// 获取组织对应的分厂顺序
		sql.append("select pk_defdoc,code,name from bd_defdoc ");
		sql.append("where dr=0 and pk_defdoclist=(select pk_defdoclist from bd_defdoclist where code='zdyx020') ");
		sql.append("and pk_org='");
		sql.append(pk_org);
		sql.append("' order by code");
		BaseDAO baseDao = new BaseDAO();
		ArrayList<DefdocVO> alSubCorp = (ArrayList<DefdocVO>) baseDao
				.executeQuery(sql.toString(), new BeanListProcessor(
						DefdocVO.class));
		if (alSubCorp == null || alSubCorp.size() < 1) {
			throw new BusinessException("未查询到当前组织对应的分厂自定义档案！");
		}
		Map<String, DefdocVO> mapSubCorp = CollectionUtils.hashVOArray(
				DefdocVO.PK_DEFDOC, alSubCorp.toArray(new DefdocVO[0]));

		// 获取各分厂各产品类的产量数据及变动工费

		sql = new StringBuilder(
				"select b.pk_subcorp,b.pk_marbasclass,b.fqty,b.fqty*vrate.vbdef1 vbdef1,b.fqty*vrate.vbdef2 vbdef2,");
		sql.append("b.fqty*vrate.vbdef3 vbdef3,b.fqty*vrate.vbdef4 vbdef4,b.fqty*vrate.vbdef5 vbdef5,");
		sql.append("b.fqty*vrate.vbdef6 vbdef6,b.fqty*vrate.vbdef7 vbdef7,b.fqty*vrate.vbdef8 vbdef8,");
		sql.append("b.fqty*vrate.vbdef9 vbdef9,b.fqty*vrate.vbdef10 vbdef10 ");
		sql.append("from lhprj_lhprodcolle h inner join lhprj_lhprodcolledetail b on h.pk_prodcolle=b.pk_prodcolle ");
		sql.append("left join(");
		sql.append("select v.pk_subcorp,v.pk_matclass,(case when v.vbdef1='~' then '0' else v.vbdef1 end) vbdef1,(case when v.vbdef2='~' then '0' else v.vbdef2 end) vbdef2,(case when v.vbdef3='~' then '0' else v.vbdef3 end) vbdef3,(case when v.vbdef4='~' then '0' else v.vbdef4 end) vbdef4,");
		sql.append("(case when v.vbdef5='~' then '0' else v.vbdef5 end) vbdef5,(case when v.vbdef6='~' then '0' else v.vbdef6 end) vbdef6,(case when v.vbdef7='~' then '0' else v.vbdef7 end) vbdef7,");
		sql.append("(case when v.vbdef8='~' then '0' else v.vbdef8 end) vbdef8,(case when v.vbdef9='~' then '0' else v.vbdef9 end) vbdef9,(case when v.vbdef10='~' then '0' else v.vbdef10 end) vbdef10 ");
		sql.append("from( ");
		sql.append("select row_number() over(partition by b.pk_subcorp,b.pk_matclass order by h.dbilldate desc) rn,h.dbilldate,b.pk_subcorp,b.pk_matclass,b.vbdef1,");
		sql.append("b.vbdef2,b.vbdef3,b.vbdef4,b.vbdef5,b.vbdef6,b.vbdef7,b.vbdef8,b.vbdef9,b.vbdef10 ");
		sql.append("from lhprj_lhqtstdton h inner join lhprj_lhqtstdtondetail b on h.pk_id=b.pk_id ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("') v where v.rn=1");
		sql.append(") vrate on b.pk_subcorp=vrate.pk_subcorp and b.pk_marbasclass=vrate.pk_matclass ");
		sql.append("where h.dr=0 and b.dr=0 and substr(h.dbilldate,0,10)='");
		sql.append(cDate);
		sql.append("' and h.pk_org='");
		sql.append(pk_org);
		sql.append("'");
		ArrayList<LhDayCostCalcDetailVO> alTonFeeVOs =  (ArrayList<LhDayCostCalcDetailVO>) baseDao
				.executeQuery(sql.toString(), new BeanListProcessor(
						LhDayCostCalcDetailVO.class));
		Map<String, LhDayCostCalcDetailVO> mapTonFee = CollectionUtils
				.hashVOArrayByMultiKeys(new String[] { "pk_subcorp",
						"pk_marbasclass" }, alTonFeeVOs.toArray(new LhDayCostCalcDetailVO[0]), "|");

		// 获取各分厂产品类的固定费用分摊比例
		sql = new StringBuilder(
				"select v.pk_subcorp,v.pk_marbasclass,v.fsharerate ");
		sql.append("from(");
		sql.append("select row_number() over(partition by h.vdef1,b.pk_marbasclass order by h.dbilldate desc) rn,h.vdef1 pk_subcorp,b.pk_marbasclass,nvl(b.frate,0) fsharerate ");
		sql.append("from lhprj_lhsharerate h inner join lhprj_lhsharerateitem b on h.pk_sharerate=b.pk_sharerate ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("') v where v.rn=1");
		ArrayList alShareRateData=(ArrayList) baseDao
				.executeQuery(sql.toString(),new ArrayListProcessor());
		
		ArrayList<LhShareRateTmpVO> alShareRate = new ArrayList<LhShareRateTmpVO>();
		if (alShareRateData == null || alShareRateData.size() < 1) {
			throw new BusinessException("分厂分摊标准尚未建立，请先建立分厂产品类分摊标准！");
		}
		for(int i=0;i<alShareRateData.size();i++){
			LhShareRateTmpVO tmpvo=new LhShareRateTmpVO();
			tmpvo.setPk_subcorp(((Object[])alShareRateData.get(i))[0].toString());
			tmpvo.setPk_marbasclass(((Object[])alShareRateData.get(i))[1].toString());
			tmpvo.setfRate(new UFDouble(((Object[])alShareRateData.get(i))[2].toString()));
			alShareRate.add(tmpvo);
		}
		Map<String, List<LhShareRateTmpVO>> mapShareRateBySubCorp = CollectionUtils
				.groupVOs(new String[] { "pk_subcorp" },
						alShareRate.toArray(new LhShareRateTmpVO[0]));

		// 获取各分厂的固定工费，并按照分摊比例分摊到各产品类
		sql = new StringBuilder(
				"select vstd.pk_subcorp,vrate.pk_marbasclass,vrate.frate*vstd.vbdef1 vbdef11,vrate.frate*vstd.vbdef2 vbdef12,");
		sql.append("vrate.frate*vstd.vbdef3 vbdef13,vrate.frate*vstd.vbdef4 vbdef14,vrate.frate*vstd.vbdef5 vbdef15,");
		sql.append("vrate.frate*vstd.vbdef6 vbdef16,vrate.frate*vstd.vbdef7 vbdef17,vrate.frate*vstd.vbdef8 vbdef18,");
		sql.append("vrate.frate*vstd.vbdef9 vbdef19,vrate.frate*vstd.vbdef10 vbdef20 ");
		sql.append("from(");
		sql.append("select v.pk_subcorp,(case when v.vbdef1='~' then '0' else v.vbdef1 end) vbdef1,(case when v.vbdef2='~' then '0' else v.vbdef2 end) vbdef2,(case when v.vbdef3='~' then '0' else v.vbdef3 end) vbdef3,(case when v.vbdef4='~' then '0' else v.vbdef4 end) vbdef4,");
		sql.append("(case when v.vbdef5='~' then '0' else v.vbdef5 end) vbdef5,(case when v.vbdef6='~' then '0' else v.vbdef6 end) vbdef6,(case when v.vbdef7='~' then '0' else v.vbdef7 end) vbdef7,");
		sql.append("(case when v.vbdef8='~' then '0' else v.vbdef8 end) vbdef8,(case when v.vbdef9='~' then '0' else v.vbdef9 end) vbdef9,(case when v.vbdef10='~' then '0' else v.vbdef10 end) vbdef10 ");
		sql.append("from(");
		sql.append("select row_number() over(partition by b.pk_subcorp order by h.dbilldate desc) rn,b.pk_subcorp,b.vbdef1,b.vbdef2,b.vbdef3,b.vbdef4,b.vbdef5,b.vbdef6,b.vbdef7,b.vbdef8,b.vbdef9,b.vbdef10 ");
		sql.append("from lhprj_lhqtstdday h inner join lhprj_lhqtstddaydetail b on h.pk_id=b.pk_id ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("') v where v.rn=1");
		sql.append(") vstd left join (");
		sql.append("select v.pk_subcorp,v.pk_marbasclass,nvl(v.frate,0) frate ");
		sql.append("from(");
		sql.append("select row_number() over(partition by h.vdef1,b.pk_marbasclass order by h.dbilldate desc) rn,h.vdef1 pk_subcorp,b.pk_marbasclass,b.frate ");
		sql.append("from lhprj_lhsharerate h inner join lhprj_lhsharerateitem b on h.pk_sharerate=b.pk_sharerate ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("') v where v.rn=1");
		sql.append(") vrate on vstd.pk_subcorp=vrate.pk_subcorp");
		ArrayList<LhDayCostCalcDetailVO> dayFeeVOs = (ArrayList<LhDayCostCalcDetailVO>) baseDao
				.executeQuery(sql.toString(), new BeanListProcessor(
						LhDayCostCalcDetailVO.class));
		Map<String, LhDayCostCalcDetailVO> mapDayFee = CollectionUtils
				.hashVOArrayByMultiKeys(new String[] { "pk_subcorp",
						"pk_marbasclass" }, dayFeeVOs.toArray(new LhDayCostCalcDetailVO[0]), "|");

		Map<String, LhDayCostCalcDetailVO> mapFinalVO = new HashMap<String, LhDayCostCalcDetailVO>();
		if (mapTonFee != null && mapTonFee.size() > 0) {
			mapFinalVO.putAll(mapTonFee);
		}
		if (mapDayFee != null && mapDayFee.size() > 0) {
			for (Map.Entry<String, LhDayCostCalcDetailVO> entry : mapDayFee
					.entrySet()) {
				if (mapFinalVO.containsKey(entry.getKey())) {
					LhDayCostCalcDetailVO tmpvo = mapFinalVO
							.get(entry.getKey());
					tmpvo.setVbdef11(entry.getValue().getVbdef11());
					tmpvo.setVbdef12(entry.getValue().getVbdef12());
					tmpvo.setVbdef13(entry.getValue().getVbdef13());
					tmpvo.setVbdef14(entry.getValue().getVbdef14());
					tmpvo.setVbdef15(entry.getValue().getVbdef15());
					tmpvo.setVbdef16(entry.getValue().getVbdef16());
					tmpvo.setVbdef17(entry.getValue().getVbdef17());
					tmpvo.setVbdef18(entry.getValue().getVbdef18());
					tmpvo.setVbdef19(entry.getValue().getVbdef19());
					tmpvo.setVbdef20(entry.getValue().getVbdef20());
				} else {
					LhDayCostCalcDetailVO tmpvo = new LhDayCostCalcDetailVO();
					tmpvo.setPk_subcorp(entry.getValue().getPk_subcorp());
					tmpvo.setPk_marbasclass(entry.getValue()
							.getPk_marbasclass());
					tmpvo.setVbdef11(entry.getValue().getVbdef11());
					tmpvo.setVbdef12(entry.getValue().getVbdef12());
					tmpvo.setVbdef13(entry.getValue().getVbdef13());
					tmpvo.setVbdef14(entry.getValue().getVbdef14());
					tmpvo.setVbdef15(entry.getValue().getVbdef15());
					tmpvo.setVbdef16(entry.getValue().getVbdef16());
					tmpvo.setVbdef17(entry.getValue().getVbdef17());
					tmpvo.setVbdef18(entry.getValue().getVbdef18());
					tmpvo.setVbdef19(entry.getValue().getVbdef19());
					tmpvo.setVbdef20(entry.getValue().getVbdef20());
					mapFinalVO.put(entry.getKey(), tmpvo);
				}
			}
		}
		// 依次获取各分厂各出库总金额，按照分摊标准分摊到各产品类上，并回写成本金额到生产日报
		for (int i = 0; i < alSubCorp.size(); i++) {
			if (alSubCorp.get(i) != null) {
				ArrayList<LhDayCostCalcDetailVO> alWriteBackData = new ArrayList<LhDayCostCalcDetailVO>();
				
				String pk_subcorp = alSubCorp.get(i).getPk_defdoc();
				sql = new StringBuilder(
						"select nvl(sum(b.outmny),0) outmny ");
				sql.append("from uap_dayproduct_h h inner join uap_dayproduct_b b on h.pk_dayproduct_h=b.pk_dayproduct_h ");
				sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
				sql.append(pk_org);
				sql.append("' and h.def1='");
				sql.append(pk_subcorp);
				sql.append("'");
				ArrayList alMatCost = (ArrayList) baseDao.executeQuery(
						sql.toString(), new ArrayListProcessor());
				UFDouble fCorpCost = new UFDouble(0);// 分厂材料出库总金额
				if (alMatCost != null && alMatCost.size() > 0) {
					Object[] restCost = (Object[]) alMatCost.get(0);
					if (restCost != null && restCost[0] != null) {
						fCorpCost = new UFDouble(restCost[0].toString());
						if (!fCorpCost.equals(UFDouble.ZERO_DBL)) {
							if (mapShareRateBySubCorp.containsKey(pk_subcorp)) {
								List<LhShareRateTmpVO> lsMatShareRate = mapShareRateBySubCorp
										.get(pk_subcorp);
								if (lsMatShareRate != null
										&& lsMatShareRate.size() > 0) {
									for (LhShareRateTmpVO srvo : lsMatShareRate) {
										String keyCorpAndMat = pk_subcorp + "|"
												+ srvo.getPk_marbasclass();
										if(srvo.getfRate()==null){
											srvo.setfRate(new UFDouble(1));
										}
										UFDouble fMatCost = fCorpCost
												.multiply(srvo.getfRate());
										if (!fMatCost.equals(UFDouble.ZERO_DBL)) {
											if (mapFinalVO
													.containsKey(keyCorpAndMat)) {
												mapFinalVO.get(keyCorpAndMat)
														.setFmatcost(fMatCost);
											} else {
												LhDayCostCalcDetailVO tmpvo = new LhDayCostCalcDetailVO();
												tmpvo.setPk_subcorp(pk_subcorp);
												tmpvo.setPk_marbasclass(srvo
														.getPk_marbasclass());
												tmpvo.setFmatcost(fMatCost);
												mapFinalVO.put(keyCorpAndMat,
														tmpvo);
											}
										}
									}
								}
							} else {
								throw new BusinessException("分厂["
										+ mapSubCorp.get(pk_subcorp).getName()
										+ "]尚未建立产品类分摊标准！");
							}
						}
					}
				}

				// 取得mapFinalVO中当前分厂的数据,并分别计算产品类的总成本

				for (Map.Entry<String, LhDayCostCalcDetailVO> entry : mapFinalVO
						.entrySet()) {
					if (entry.getKey().startsWith(pk_subcorp)) {
						LhDayCostCalcDetailVO rowVO = entry.getValue();
						UFDouble fMatCost = rowVO.getFmatcost() == null ? UFDouble.ZERO_DBL
								: rowVO.getFmatcost();
						UFDouble fBDef1 = rowVO.getVbdef1() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef1();
						UFDouble fBDef2 = rowVO.getVbdef2() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef2();
						UFDouble fBDef3 = rowVO.getVbdef3() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef3();
						UFDouble fBDef4 = rowVO.getVbdef4() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef4();
						UFDouble fBDef5 = rowVO.getVbdef5() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef5();
						UFDouble fBDef6 = rowVO.getVbdef6() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef6();
						UFDouble fBDef7 = rowVO.getVbdef7() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef7();
						UFDouble fBDef8 = rowVO.getVbdef8() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef8();
						UFDouble fBDef9 = rowVO.getVbdef9() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef9();
						UFDouble fBDef10 = rowVO.getVbdef10() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef10();
						UFDouble fBDef11 = rowVO.getVbdef11() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef11();
						UFDouble fBDef12 = rowVO.getVbdef12() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef12();
						UFDouble fBDef13 = rowVO.getVbdef13() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef13();
						UFDouble fBDef14 = rowVO.getVbdef14() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef14();
						UFDouble fBDef15 = rowVO.getVbdef15() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef15();
						UFDouble fBDef16 = rowVO.getVbdef16() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef16();
						UFDouble fBDef17 = rowVO.getVbdef17() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef17();
						UFDouble fBDef18 = rowVO.getVbdef18() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef18();
						UFDouble fBDef19 = rowVO.getVbdef19() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef19();
						UFDouble fBDef20 = rowVO.getVbdef20() == null ? UFDouble.ZERO_DBL
								: rowVO.getVbdef20();
						UFDouble fCostSum = UFDouble.sum(new double[] {
								fMatCost.doubleValue(), fBDef1.doubleValue(), fBDef2.doubleValue(), fBDef3.doubleValue(),
								fBDef4.doubleValue(), fBDef5.doubleValue(), fBDef6.doubleValue(), fBDef7.doubleValue(), fBDef8.doubleValue(), fBDef9.doubleValue(),
								fBDef10.doubleValue(), fBDef11.doubleValue(), fBDef12.doubleValue(), fBDef13.doubleValue(), fBDef14.doubleValue(),
								fBDef15.doubleValue(), fBDef16.doubleValue(), fBDef17.doubleValue(), fBDef18.doubleValue(), fBDef19.doubleValue(),
								fBDef20.doubleValue() });
						rowVO.setFcostsum(fCostSum);
						alWriteBackData.add(rowVO);
					}
				}

				if (alWriteBackData != null && alWriteBackData.size() > 0) {
					// 回写该分厂的成本 需要传递日期、分厂主键、该分厂成品入库的物料主键、成本金额
					// 但成品物料的金额如何分摊，以哪个分厂的分摊标准进行分摊
					ArrayList<LhDayCostCalcTmpVO> alBackData=new ArrayList<LhDayCostCalcTmpVO>();
					for(LhDayCostCalcDetailVO vo:alWriteBackData){
						LhDayCostCalcTmpVO backVO=new LhDayCostCalcTmpVO();
						backVO.setcDate(cDate);
						backVO.setPk_subcorp(vo.getPk_subcorp());
						backVO.setPk_material(vo.getPk_marbasclass());
						backVO.setfMny(vo.getFcostsum());
						alBackData.add(backVO);
					}
					//将alBackData通过接口返回给另一部分开发
					String retStr=NCLocator.getInstance().lookup(IDayCostCalcService.class).SendCostCalcBill(alBackData);
				}
				
			}
		}

		if (mapFinalVO != null && mapFinalVO.size() > 0) {
			return mapFinalVO.values().toArray(new LhDayCostCalcDetailVO[0]);
		} else {
			return null;
		}

	}

}