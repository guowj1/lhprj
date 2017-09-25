package nc.impl.pub.ace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcApproveBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcDeleteBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcInsertBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcUnApproveBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcUnSendApproveBP;
import nc.bs.lhprj.lhdc.lhdcdccalc.ace.bp.AceLhdcdccalcUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.lhprj.lhdcdccalc.AggLhDayCostCalcVO;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcDetailVO;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcTmpVO;
import nc.vo.lhprj.lhdcdccalc.LhDayOutTmpVO;
import nc.vo.lhprj.lhdcprodcolle.LhProdColleTmpVO;
import nc.vo.lhprj.lhdcsharerate.LhShareRateTmpVO;
import nc.vo.lhprj.lhqtstdday.LhQtStdDayTmpVO;
import nc.vo.lhprj.lhqtstdton.LhQtStdTonTmpVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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

	public ArrayList<LhDayCostCalcDetailVO> pubCalcCost(String pk_org,
			String cDate) throws BusinessException {
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

		// 1.获取各分厂各产品线的产量
		sql = new StringBuilder(
				"select b.pk_subcorp,b.pk_marbasclass,sum(nvl(b.fqty,0.0)) fqty ");
		sql.append("from lhprj_lhprodcolle h inner join lhprj_lhprodcolledetail b on h.pk_prodcolle=b.pk_prodcolle ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("' and substr(h.dbilldate,0,10)='");
		sql.append(cDate);
		sql.append("' ");
		sql.append(" group by b.pk_subcorp,b.pk_marbasclass ;");
		ArrayList<LhProdColleTmpVO> alProColle = (ArrayList<LhProdColleTmpVO>) baseDao
				.executeQuery(sql.toString(), new BeanListProcessor(
						LhProdColleTmpVO.class));
		if (alProColle == null || alProColle.size() < 1) {
			throw new BusinessException("未查询到当前组织当天的产量数据，请检查！");
		}
		Map<String, Map<String, LhProdColleTmpVO>> mapProdColleByCorp = new HashMap<String, Map<String, LhProdColleTmpVO>>();
		for (LhProdColleTmpVO vo : alProColle) {
			if (mapProdColleByCorp.containsKey(vo.getPk_subcorp())) {
				mapProdColleByCorp.get(vo.getPk_subcorp()).put(
						vo.getPk_marbasclass(), vo);
			} else {
				Map<String, LhProdColleTmpVO> mapProdColleByMar = new HashMap<String, LhProdColleTmpVO>();
				mapProdColleByMar.put(vo.getPk_marbasclass(), vo);
				mapProdColleByCorp.put(vo.getPk_subcorp(), mapProdColleByMar);
			}
		}

		// 2.获取各分厂各产品线产量费用标准LhQtStdTonTmpVO
		sql = new StringBuilder(
				"select v.pk_subcorp,v.pk_matclass pk_marbasclass,(case when v.vbdef1='~' then 0.0 else nvl(to_number(v.vbdef1),0.0) end) vbdef1,");
		sql.append("(case when v.vbdef2='~' then 0.0 else nvl(to_number(v.vbdef2),0.0) end) vbdef2,");
		sql.append("(case when v.vbdef3='~' then 0.0 else nvl(to_number(v.vbdef3),0.0) end) vbdef3,");
		sql.append("(case when v.vbdef4='~' then 0.0 else nvl(to_number(v.vbdef4),0.0) end) vbdef4,");
		sql.append("(case when v.vbdef5='~' then 0.0 else nvl(to_number(v.vbdef5),0.0) end) vbdef5,");
		sql.append("(case when v.vbdef6='~' then 0.0 else nvl(to_number(v.vbdef6),0.0) end) vbdef6,");
		sql.append("(case when v.vbdef7='~' then 0.0 else nvl(to_number(v.vbdef7),0.0) end) vbdef7,");
		sql.append("(case when v.vbdef8='~' then 0.0 else nvl(to_number(v.vbdef8),0.0) end) vbdef8,");
		sql.append("(case when v.vbdef9='~' then 0.0 else nvl(to_number(v.vbdef9),0.0) end) vbdef9,");
		sql.append("(case when v.vbdef10='~' then 0.0 else nvl(to_number(v.vbdef10),0.0) end) vbdef10,");
		sql.append("(case when v.vbdef11='~' then 0.0 else nvl(to_number(v.vbdef11),0.0) end) vbdef11,");
		sql.append("(case when v.vbdef12='~' then 0.0 else nvl(to_number(v.vbdef12),0.0) end) vbdef12,");
		sql.append("(case when v.vbdef13='~' then 0.0 else nvl(to_number(v.vbdef13),0.0) end) vbdef13,");
		sql.append("(case when v.vbdef14='~' then 0.0 else nvl(to_number(v.vbdef14),0.0) end) vbdef14,");
		sql.append("(case when v.vbdef15='~' then 0.0 else nvl(to_number(v.vbdef15),0.0) end) vbdef15 ");
		sql.append("from( ");
		sql.append("select row_number() over(partition by b.pk_subcorp,b.pk_matclass order by h.dbilldate desc) rn,h.dbilldate,b.pk_subcorp,b.pk_matclass,b.vbdef1,");
		sql.append("b.vbdef2,b.vbdef3,b.vbdef4,b.vbdef5,b.vbdef6,b.vbdef7,b.vbdef8,b.vbdef9,b.vbdef10,");
		sql.append("b.vbdef11,b.vbdef12,b.vbdef13,b.vbdef14,b.vbdef15 ");
		sql.append("from lhprj_lhqtstdton h inner join lhprj_lhqtstdtondetail b on h.pk_id=b.pk_id ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("' ) v where v.rn=1 ;");
		ArrayList<LhQtStdTonTmpVO> alTonStd = (ArrayList<LhQtStdTonTmpVO>) baseDao
				.executeQuery(sql.toString(), new BeanListProcessor(
						LhQtStdTonTmpVO.class));
		Map<String, Map<String, LhQtStdTonTmpVO>> mapTonStdByCorp = new HashMap<String, Map<String, LhQtStdTonTmpVO>>();
		for (LhQtStdTonTmpVO vo : alTonStd) {
			if (mapTonStdByCorp.containsKey(vo.getPk_subcorp())) {
				mapTonStdByCorp.get(vo.getPk_subcorp()).put(
						vo.getPk_marbasclass(), vo);
			} else {
				Map<String, LhQtStdTonTmpVO> mapTonStdByMar = new HashMap<String, LhQtStdTonTmpVO>();
				mapTonStdByMar.put(vo.getPk_marbasclass(), vo);
				mapTonStdByCorp.put(vo.getPk_subcorp(), mapTonStdByMar);
			}
		}

		// 3.获取各分厂固定工费标准
		sql = new StringBuilder(
				"select v.pk_subcorp,(case when v.vbdef1='~' then 0.0 else nvl(to_number(v.vbdef1),0.0) end) vbdef1,");
		sql.append("(case when v.vbdef2='~' then 0.0 else nvl(to_number(v.vbdef2),0.0) end) vbdef2,");
		sql.append("(case when v.vbdef3='~' then 0.0 else nvl(to_number(v.vbdef3),0.0) end) vbdef3,");
		sql.append("(case when v.vbdef4='~' then 0.0 else nvl(to_number(v.vbdef4),0.0) end) vbdef4,");
		sql.append("(case when v.vbdef5='~' then 0.0 else nvl(to_number(v.vbdef5),0.0) end) vbdef5,");
		sql.append("(case when v.vbdef6='~' then 0.0 else nvl(to_number(v.vbdef6),0.0) end) vbdef6,");
		sql.append("(case when v.vbdef7='~' then 0.0 else nvl(to_number(v.vbdef7),0.0) end) vbdef7,");
		sql.append("(case when v.vbdef8='~' then 0.0 else nvl(to_number(v.vbdef8),0.0) end) vbdef8,");
		sql.append("(case when v.vbdef9='~' then 0.0 else nvl(to_number(v.vbdef9),0.0) end) vbdef9,");
		sql.append("(case when v.vbdef10='~' then 0.0 else nvl(to_number(v.vbdef10),0.0) end) vbdef10,");
		sql.append("(case when v.nmny1 is null then 0.0 else v.nmny1 end) nmny1,");
		sql.append("(case when v.nmny2 is null then 0.0 else v.nmny2 end) nmny2,");
		sql.append("(case when v.nmny3 is null then 0.0 else v.nmny3 end) nmny3,");
		sql.append("(case when v.nmny4 is null then 0.0 else v.nmny4 end) nmny4,");
		sql.append("(case when v.nmny5 is null then 0.0 else v.nmny5 end) nmny5,");
		sql.append("(case when v.nmny6 is null then 0.0 else v.nmny6 end) nmny6,");
		sql.append("(case when v.nmny7 is null then 0.0 else v.nmny7 end) nmny7 ");
		sql.append("from( ");
		sql.append("select row_number() over(partition by b.pk_subcorp order by h.dbilldate desc) rn,b.pk_subcorp,b.vbdef1,b.vbdef2,b.vbdef3,b.vbdef4,b.vbdef5,b.vbdef6,b.vbdef7,b.vbdef8,b.vbdef9,b.vbdef10,");
		sql.append("b.nmny1,b.nmny2,b.nmny3,b.nmny4,b.nmny5,b.nmny6,b.nmny7 ");
		sql.append("from lhprj_lhqtstdday h inner join lhprj_lhqtstddaydetail b on h.pk_id=b.pk_id ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("' ) v where v.rn=1 ;");
		ArrayList<LhQtStdDayTmpVO> alDayStd = (ArrayList<LhQtStdDayTmpVO>) baseDao
				.executeQuery(sql.toString(), new BeanListProcessor(
						LhQtStdDayTmpVO.class));
		Map<String, LhQtStdDayTmpVO> mapDayStdByCorp = CollectionUtils
				.hashVOArray("pk_subcorp",
						alDayStd.toArray(new LhQtStdDayTmpVO[0]));

		// 4.获取各分厂各产品线分摊标准
		sql = new StringBuilder(
				"select v.pk_subcorp,v.pk_marbasclass,v.fsharerate ");
		sql.append("from(");
		sql.append("select row_number() over(partition by h.vdef1,b.pk_marbasclass order by h.dbilldate desc) rn,h.vdef1 pk_subcorp,b.pk_marbasclass,nvl(to_number(b.frate),0.0) fsharerate ");
		sql.append("from lhprj_lhsharerate h inner join lhprj_lhsharerateitem b on h.pk_sharerate=b.pk_sharerate ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
		sql.append(pk_org);
		sql.append("' ) v where v.rn=1 ;");
		ArrayList<LhShareRateTmpVO> alShareRate = (ArrayList<LhShareRateTmpVO>) baseDao
				.executeQuery(sql.toString(), new BeanListProcessor(
						LhShareRateTmpVO.class));

		Map<String, Map<String, LhShareRateTmpVO>> mapShareRateByCorp = new HashMap<String, Map<String, LhShareRateTmpVO>>();
		for (LhShareRateTmpVO vo : alShareRate) {
			if (mapShareRateByCorp.containsKey(vo.getPk_subcorp())) {
				mapShareRateByCorp.get(vo.getPk_subcorp()).put(
						vo.getPk_marbasclass(), vo);
			} else {
				Map<String, LhShareRateTmpVO> mapShareRateByMar = new HashMap<String, LhShareRateTmpVO>();
				mapShareRateByMar.put(vo.getPk_marbasclass(), vo);
				mapShareRateByCorp.put(vo.getPk_subcorp(), mapShareRateByMar);
			}
		}

		// 5.以分厂顺序循环各个分厂
		ArrayList<LhDayCostCalcDetailVO> alFinalCalcVO = new ArrayList<LhDayCostCalcDetailVO>();
		Integer iRowNo = Integer.valueOf(10);
		for (DefdocVO vo : alSubCorp) {
			// Map<String, LhDayCostCalcDetailVO> mapThisCorpCalcVOByMar = new
			// HashMap<String, LhDayCostCalcDetailVO>();
			// ArrayList<LhDayCostCalcTmpVO> alWriteBackVO = new
			// ArrayList<LhDayCostCalcTmpVO>();
			String pk_subcorp = vo.getPk_defdoc();
			// 获取当前分厂的各产品线的产量
			if (!mapProdColleByCorp.containsKey(pk_subcorp)) {
				continue;
			}
			Map<String, LhProdColleTmpVO> mapProdColleByMar = mapProdColleByCorp
					.get(pk_subcorp);
			// 获取当前分厂的各产品线的变动工费标准
			Map<String, LhQtStdTonTmpVO> mapTonStdByMar = new HashMap<String, LhQtStdTonTmpVO>();
			if (mapTonStdByCorp.containsKey(pk_subcorp)) {
				mapTonStdByMar = mapTonStdByCorp.get(pk_subcorp);
			}
			// 获取当前分厂的固定工费标准
			LhQtStdDayTmpVO dayStdThisCorp = null;
			if (mapDayStdByCorp.containsKey(pk_subcorp)) {
				dayStdThisCorp = mapDayStdByCorp.get(pk_subcorp);
			}
			// 获取当前分厂的各产品线的分摊标准
			Map<String, LhShareRateTmpVO> mapShareRateByMar = new HashMap<String, LhShareRateTmpVO>();
			if (mapShareRateByCorp.containsKey(pk_subcorp)) {
				mapShareRateByMar = mapShareRateByCorp.get(pk_subcorp);
			}

			// 获取当前分厂的材料出库金额明细
			sql = new StringBuilder(
					"select nvl(b.def1,'~') pk_marbasclass,nvl(sum(b.outmny),0.0) outmny ");
			sql.append("from uap_dayproduct_h h inner join uap_dayproduct_b b on h.pk_dayproduct_h=b.pk_dayproduct_h ");
			sql.append("where h.dr=0 and b.dr=0 and h.pk_org='");
			sql.append(pk_org);
			sql.append("' and h.def1='");
			sql.append(pk_subcorp);
			sql.append("' and substr(h.dbilldate,0,10)='");
			sql.append(cDate);
			sql.append("' group by b.def1 ;");
			ArrayList<LhDayOutTmpVO> alOutmnyAll = (ArrayList<LhDayOutTmpVO>) baseDao
					.executeQuery(sql.toString(), new BeanListProcessor(
							LhDayOutTmpVO.class));
			Map<String, UFDouble> mapOutmnyByMar = new HashMap<String, UFDouble>();
			UFDouble fOutmnyNoObj = UFDouble.ZERO_DBL;
			// 循环出库金额明细分别获得无成本对象需要进行分摊的金额和有成本对象的不需要分摊的金额
			for (LhDayOutTmpVO vomny : alOutmnyAll) {
				if (vomny.getOutmny().equals(UFDouble.ZERO_DBL)) {
					continue;
				}
				if (vomny.getPk_marbasclass() == null
						|| vomny.getPk_marbasclass().equals("~")
						|| vomny.getPk_marbasclass().equals("")) {
					fOutmnyNoObj = fOutmnyNoObj.add(vomny.getOutmny());
				} else {
					if (mapOutmnyByMar.containsKey(vomny.getPk_marbasclass())) {
						mapOutmnyByMar.get(vomny.getPk_marbasclass()).add(
								vomny.getOutmny());
					} else {
						mapOutmnyByMar.put(vomny.getPk_marbasclass(),
								vomny.getOutmny());
					}
				}
			}

			// 获取无产量但是有出库成本对象的记录，抛出异常
			HashSet<String> hsMatErrs = new HashSet<String>();
			for (String pk_mar : mapOutmnyByMar.keySet()) {
				if (!mapProdColleByMar.containsKey(pk_mar)) {
					hsMatErrs.add(pk_mar);
				}
			}
			if (hsMatErrs != null && hsMatErrs.size() > 0) {
				throw new BusinessException("分厂" + pk_subcorp
						+ "的以下产品线无产量，但存在该产品线的生产领用量，请检查：\r"
						+ hsMatErrs.toString());
			}

			// 循环各产品线的产量获取当前分厂的分摊标准合计
			UFDouble fSumDiv = UFDouble.ZERO_DBL;
			for (Map.Entry<String, LhProdColleTmpVO> entry : mapProdColleByMar
					.entrySet()) {
				if (!entry.getValue().getFqty().equals(UFDouble.ZERO_DBL)) {
					String pk_marbasclass = entry.getValue()
							.getPk_marbasclass();
					UFDouble fqty = entry.getValue().getFqty();
					UFDouble frate = UFDouble.ONE_DBL;
					if (mapShareRateByMar.containsKey(pk_marbasclass)) {
						frate = mapShareRateByMar.get(pk_marbasclass)
								.getFsharerate();
					}
					fSumDiv = fSumDiv.add(fqty.multiply(frate));
				}
			}

			// 循环各产品线的产量
			ArrayList<LhDayCostCalcDetailVO> alCalcVO = new ArrayList<LhDayCostCalcDetailVO>();
			ArrayList<LhDayCostCalcTmpVO> alWriteBackVO = new ArrayList<LhDayCostCalcTmpVO>();
			for (Map.Entry<String, LhProdColleTmpVO> entry : mapProdColleByMar
					.entrySet()) {
				if (!entry.getValue().getFqty().equals(UFDouble.ZERO_DBL)) {
					String pk_marbasclass = entry.getValue()
							.getPk_marbasclass();
					UFDouble fqty = entry.getValue().getFqty();
					UFDouble frate = UFDouble.ONE_DBL;
					if (mapShareRateByMar.containsKey(pk_marbasclass)) {
						frate = mapShareRateByMar.get(pk_marbasclass)
								.getFsharerate();
					}
					UFDouble fdiv = fqty.multiply(frate);
					UFDouble fDivRate = fdiv.div(fSumDiv);

					LhQtStdTonTmpVO tonStdCurMarVO = mapTonStdByMar
							.get(pk_marbasclass);
					LhDayCostCalcDetailVO calcVO = new LhDayCostCalcDetailVO();
					calcVO.setPk_subcorp(pk_subcorp);
					calcVO.setPk_marbasclass(pk_marbasclass);
					calcVO.setCrowno(iRowNo.toString());
					calcVO.setFqty(fqty);
					if (tonStdCurMarVO != null) {
						calcVO.setVbdef1(fqty.multiply(tonStdCurMarVO
								.getVbdef1()));
						calcVO.setVbdef2(fqty.multiply(tonStdCurMarVO
								.getVbdef2()));
						calcVO.setVbdef3(fqty.multiply(tonStdCurMarVO
								.getVbdef3()));
						calcVO.setVbdef4(fqty.multiply(tonStdCurMarVO
								.getVbdef4()));
						calcVO.setVbdef5(fqty.multiply(tonStdCurMarVO
								.getVbdef5()));
						calcVO.setVbdef6(fqty.multiply(tonStdCurMarVO
								.getVbdef6()));
						calcVO.setVbdef7(fqty.multiply(tonStdCurMarVO
								.getVbdef7()));
						calcVO.setVbdef8(fqty.multiply(tonStdCurMarVO
								.getVbdef8()));
						calcVO.setVbdef9(fqty.multiply(tonStdCurMarVO
								.getVbdef9()));
						calcVO.setVbdef10(fqty.multiply(tonStdCurMarVO
								.getVbdef10()));
						calcVO.setVbdef21(fqty.multiply(tonStdCurMarVO
								.getVbdef11()));
						calcVO.setVbdef22(fqty.multiply(tonStdCurMarVO
								.getVbdef12()));
						calcVO.setVbdef23(fqty.multiply(tonStdCurMarVO
								.getVbdef13()));
						calcVO.setVbdef24(fqty.multiply(tonStdCurMarVO
								.getVbdef14()));
						calcVO.setVbdef25(fqty.multiply(tonStdCurMarVO
								.getVbdef15()));
					}
					if (dayStdThisCorp != null) {
						calcVO.setVbdef11(dayStdThisCorp.getVbdef1().multiply(
								fDivRate));
						calcVO.setVbdef12(dayStdThisCorp.getVbdef2().multiply(
								fDivRate));
						calcVO.setVbdef13(dayStdThisCorp.getVbdef3().multiply(
								fDivRate));
						calcVO.setVbdef14(dayStdThisCorp.getVbdef4().multiply(
								fDivRate));
						calcVO.setVbdef15(dayStdThisCorp.getVbdef5().multiply(
								fDivRate));
						calcVO.setVbdef16(dayStdThisCorp.getVbdef6().multiply(
								fDivRate));
						calcVO.setVbdef17(dayStdThisCorp.getVbdef7().multiply(
								fDivRate));
						calcVO.setVbdef18(dayStdThisCorp.getVbdef8().multiply(
								fDivRate));
						calcVO.setVbdef19(dayStdThisCorp.getVbdef9().multiply(
								fDivRate));
						calcVO.setVbdef20(dayStdThisCorp.getVbdef10().multiply(
								fDivRate));
						calcVO.setVbdef26(dayStdThisCorp.getVbdef10().multiply(
								fDivRate));

						calcVO.setVbdef27(dayStdThisCorp.getNmny1().multiply(
								fDivRate));
						calcVO.setVbdef28(dayStdThisCorp.getNmny2().multiply(
								fDivRate));
						calcVO.setVbdef29(dayStdThisCorp.getNmny3().multiply(
								fDivRate));
						calcVO.setVbdef30(dayStdThisCorp.getNmny4().multiply(
								fDivRate));
						calcVO.setVbdef31(dayStdThisCorp.getNmny5().multiply(
								fDivRate));
						calcVO.setVbdef32(dayStdThisCorp.getNmny6().multiply(
								fDivRate));
					}
					if (mapOutmnyByMar.containsKey(pk_marbasclass)) {
						calcVO.setFmatcost(mapOutmnyByMar.get(pk_marbasclass));
					} else {
						calcVO.setFmatcost(fOutmnyNoObj.multiply(fDivRate));
					}
					calcVO.setFmatunitcost(calcVO.getFmatcost().div(fqty));
					UFDouble fcostsum = UFDouble.sum(new double[] {
							calcVO.getFmatcost() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getFmatcost()
									.doubleValue(),
							calcVO.getVbdef1() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef1()
									.doubleValue(),
							calcVO.getVbdef2() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef2()
									.doubleValue(),
							calcVO.getVbdef3() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef3()
									.doubleValue(),
							calcVO.getVbdef4() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef4()
									.doubleValue(),
							calcVO.getVbdef5() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef5()
									.doubleValue(),
							calcVO.getVbdef6() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef6()
									.doubleValue(),
							calcVO.getVbdef7() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef7()
									.doubleValue(),
							calcVO.getVbdef8() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef8()
									.doubleValue(),
							calcVO.getVbdef9() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef9()
									.doubleValue(),
							calcVO.getVbdef10() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef10()
									.doubleValue(),
							calcVO.getVbdef11() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef11()
									.doubleValue(),
							calcVO.getVbdef12() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef12()
									.doubleValue(),
							calcVO.getVbdef13() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef13()
									.doubleValue(),
							calcVO.getVbdef14() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef14()
									.doubleValue(),
							calcVO.getVbdef15() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef15()
									.doubleValue(),
							calcVO.getVbdef16() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef16()
									.doubleValue(),
							calcVO.getVbdef17() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef17()
									.doubleValue(),
							calcVO.getVbdef18() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef18()
									.doubleValue(),
							calcVO.getVbdef19() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef19()
									.doubleValue(),
							calcVO.getVbdef20() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef20()
									.doubleValue(),
							calcVO.getVbdef21() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef21()
									.doubleValue(),
							calcVO.getVbdef22() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef22()
									.doubleValue(),
							calcVO.getVbdef23() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef23()
									.doubleValue(),
							calcVO.getVbdef24() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef24()
									.doubleValue(),
							calcVO.getVbdef25() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef25()
									.doubleValue(),
							calcVO.getVbdef26() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef26()
									.doubleValue(),
							calcVO.getVbdef27() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef27()
									.doubleValue(),
							calcVO.getVbdef28() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef28()
									.doubleValue(),
							calcVO.getVbdef29() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef29()
									.doubleValue(),
							calcVO.getVbdef30() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef30()
									.doubleValue(),
							calcVO.getVbdef31() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef31()
									.doubleValue(),
							calcVO.getVbdef32() == null ? UFDouble.ZERO_DBL
									.doubleValue() : calcVO.getVbdef32()
									.doubleValue()
					});
					calcVO.setFcostsum(fcostsum);
					calcVO.setFcostunit(fcostsum.div(fqty));
					alCalcVO.add(calcVO);

					LhDayCostCalcTmpVO writeBackVO = new LhDayCostCalcTmpVO();
					writeBackVO.setcDate(cDate);
					writeBackVO.setPk_subcorp(pk_subcorp);
					writeBackVO.setPk_material(pk_marbasclass);
					writeBackVO.setfMny(fcostsum);
					alWriteBackVO.add(writeBackVO);
					iRowNo += Integer.valueOf(10);
				}
			}
			alFinalCalcVO.addAll(alCalcVO);
			if (alWriteBackVO != null && alWriteBackVO.size() > 0) {
				String retStr = NCLocator.getInstance()
						.lookup(IDayCostCalcService.class)
						.SendCostCalcBill(alWriteBackVO);
			}

		}

		if (alFinalCalcVO != null && alFinalCalcVO.size() > 0) {
			return alFinalCalcVO;
		} else {
			return null;
		}

	}
}