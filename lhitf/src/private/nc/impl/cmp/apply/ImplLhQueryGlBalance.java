package nc.impl.cmp.apply;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.cmp.apply.ILhQueryGlBalance;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveStatus;
import nc.vo.cmp.apply.ApplyVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.tmpub.util.SqlUtil;

public class ImplLhQueryGlBalance implements ILhQueryGlBalance {

	@Override
	public void frozenApplyBill(String[] pk_apply_b) throws BusinessException {
		String condition = SqlUtil.buildSqlForIn("pk_apply_b", pk_apply_b);
		SqlBuilder sql = new SqlBuilder();
		sql.append(" update cmp_apply_b set isfrozen='Y' where ");
		sql.append(condition);
		DataAccessUtils tool = new DataAccessUtils();
		try {
			tool.update(sql.toString());
		} catch (Exception err) {
			throw new BusinessException(err.getMessage());
		}
	}

	@Override
	public ArrayList queryGlBalance(String pk_apply, String pk_org,
			String pk_group, String pk_supplier, String[] accCodes,
			String vPayType) throws BusinessException {
		// TODO 自动生成的方法存根

		// UFDate dtToday = AppContext.getInstance().getServerTime().getDate();
		//
		// String vyear = String.valueOf(dtToday.getYear());
		// String vmonth = dtToday.getStrMonth();
		// String vmonthstartdate = vyear + "-" + vmonth + "-01 00:00:00";
		// String vtoday = vyear + "-" + vmonth + "-" + dtToday.getStrDay()
		// + " 23:59:59";
		ArrayList alValues = new ArrayList();
		alValues.add("0");
		alValues.add("0");
		alValues.add("0");

		StringBuilder sql = new StringBuilder();
		BaseDAO baseDao = new BaseDAO();
		if (!StringUtil.isEmptyWithTrim(accCodes[0])) {
			sql = buildQueryBalanceSql(pk_org, pk_group, pk_supplier,
					accCodes[0], vPayType);
			Object[] objRes = (Object[]) baseDao.executeQuery(sql.toString(),
					new ArrayProcessor());
			if (objRes != null && objRes.length > 0)
				alValues.set(0, objRes[3] == null ? "0" : objRes[3]);
		}

		if (!StringUtil.isEmptyWithTrim(accCodes[1])) {
			sql = buildQueryBalanceSql(pk_org, pk_group, pk_supplier,
					accCodes[1], vPayType);
			Object[] objRes = (Object[]) baseDao.executeQuery(sql.toString(),
					new ArrayProcessor());
			if (objRes != null && objRes.length > 0)
				alValues.set(1, objRes[3] == null ? "0" : objRes[3]);
		}

		sql = buildQueryNeedPaySql(pk_apply, pk_org, pk_supplier);
		Object[] objRes = (Object[]) baseDao.executeQuery(sql.toString(),
				new ArrayProcessor());
		if (objRes != null && objRes.length > 0) {
			UFDouble dblApplyMny = new UFDouble(objRes[2] == null ? "0"
					: objRes[2].toString());
			UFDouble dblSettleMny = new UFDouble(objRes[4] == null ? "0"
					: objRes[4].toString());
			UFDouble dblNotSettleMny = dblApplyMny.sub(dblSettleMny);
			alValues.set(2, dblNotSettleMny == null ? "0" : dblNotSettleMny);
		}

		return alValues;
	}

	protected StringBuilder buildQueryBalanceSql(String pk_org,
			String pk_group, String pk_cust_sup, String accCode, String vPayType) {

		UFDate dtToday = AppContext.getInstance().getServerTime().getDate();

		String vyear = String.valueOf(dtToday.getYear());
		String vmonth = dtToday.getStrMonth();
		String vmonthstartdate = vyear + "-" + vmonth + "-01 00:00:00";
		String vtoday = vyear + "-" + vmonth + "-" + dtToday.getStrDay()
				+ " 23:59:59";

		StringBuilder sql = new StringBuilder();
		sql.append("select v.pk_accountingbook,v.yearv,v.docf14,sum(v.qclocalcreditamountsum-v.qclocaldebitamountsum+v.localcreditamountsum-v.localdebitamountsum) qmbalance ");
		sql.append(" from ( ");
		sql.append("select distinct gl_balan.pk_accasoa,gl_balan.pk_accountingbook,gl_balan.year yearv,gl_docfree1.F14 docF14,sum(gl_balan.localdebitamount) qclocaldebitamountsum,sum(gl_balan.localcreditamount) qclocalcreditamountsum,0.0 localdebitamountsum,0.0 localcreditamountsum ");
		sql.append("from gl_balance gl_balan, gl_docfree1 ");
		sql.append("where gl_balan.assid = gl_docfree1.assid ");
		sql.append("and gl_balan.pk_accountingbook = (select pk_accountingbook from org_accountingbook where pk_relorg='"
				+ pk_org + "' and enablestate=2 and dr=0) ");
		sql.append("and gl_balan.year = '" + vyear + "' ");
		sql.append("and gl_balan.adjustperiod >= '00' ");
		sql.append("and gl_balan.adjustperiod < '" + vmonth + "' ");
		sql.append("and gl_balan.voucherkind <> 5 ");
		sql.append("and gl_balan.pk_accasoa in (select pk_accasoa from bd_accasoa where pk_account in (select pk_account from bd_account where code like '"
				+ accCode + "%' ");
		sql.append("and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org
				+ "' or pk_org='"
				+ pk_group
				+ "') and dr=0)) and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org + "' or pk_org='" + pk_group + "') and dr=0)) ");
		// sql.append("and (gl_docfree1.F14 = '" + pk_cust_sup
		// + "' or gl_docfree1.F13='" + pk_cust_sup + "') ");
		sql.append("and gl_docfree1.F14 = '" + pk_cust_sup + "' ");
		sql.append("and gl_docfree1.F22='" + vPayType + "' ");
		sql.append("group by gl_balan.pk_accasoa,gl_balan.pk_accountingbook,gl_balan.year,gl_docfree1.F14 ");
		sql.append("union all ");
		sql.append("select gl_detail.pk_accasoa,gl_detail.pk_accountingbook,gl_detail.yearv yearv,gl_docfree1.F14 docF14,sum(gl_detail.localdebitamount) qclocaldebitamountsum,sum(gl_detail.localcreditamount) qclocalcreditamountsum,0.0 localdebitamountsum,0.0 localcreditamountsum ");
		sql.append("from gl_detail gl_detail, gl_docfree1 ");
		sql.append("where gl_detail.assid = gl_docfree1.assid ");
		sql.append("and gl_detail.pk_accasoa in (select pk_accasoa from bd_accasoa where pk_account in (select pk_account from bd_account where code like '"
				+ accCode + "%' ");
		sql.append("and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org
				+ "' or pk_org='"
				+ pk_group
				+ "') and dr=0)) and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org + "' or pk_org='" + pk_group + "') and dr=0)) ");
		sql.append("and gl_detail.yearv = '" + vyear + "' ");
		sql.append("and gl_detail.adjustperiod >= '00' ");
		sql.append("and gl_detail.adjustperiod < '" + vmonth + "' ");
		sql.append("and gl_detail.pk_accountingbook = (select pk_accountingbook from org_accountingbook where pk_relorg='"
				+ pk_org + "' and enablestate=2 and dr=0) ");
		sql.append("and gl_detail.discardflagv <> 'Y' and gl_detail.dr = 0 and gl_detail.voucherkindv <> 255 and gl_detail.tempsaveflag <> 'Y' ");
		sql.append("and gl_detail.pk_managerv = 'N/A' and gl_detail.voucherkindv <> 5 ");
		// sql.append("and (gl_docfree1.F14 = '" + pk_cust_sup
		// + "' or gl_docfree1.F13='" + pk_cust_sup + "') ");
		sql.append("and gl_docfree1.F14 = '" + pk_cust_sup + "' ");
		sql.append("and gl_docfree1.F22='" + vPayType + "' ");
		sql.append("group by gl_detail.pk_accasoa,gl_detail.pk_accountingbook,gl_detail.yearv,gl_docfree1.F14 ");
		sql.append("union all ");
		sql.append("select gl_detail.pk_accasoa,gl_detail.pk_accountingbook,gl_detail.yearv yearv,gl_docfree1.F14 docF14,sum(gl_detail.localdebitamount) qclocaldebitamountsum,sum(gl_detail.localcreditamount) qclocalcreditamountsum,0.0 localdebitamountsum,0.0 localcreditamountsum ");
		sql.append("from gl_detail gl_detail, gl_docfree1 ");
		sql.append("where gl_detail.assid = gl_docfree1.assid ");
		sql.append("and gl_detail.pk_accasoa in (select pk_accasoa from bd_accasoa where pk_account in (select pk_account from bd_account where code like '"
				+ accCode + "%' ");
		sql.append("and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org
				+ "' or pk_org='"
				+ pk_group
				+ "') and dr=0)) and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org + "' or pk_org='" + pk_group + "') and dr=0)) ");
		sql.append("and gl_detail.prepareddatev >= '" + vmonthstartdate + "' ");
		sql.append("and gl_detail.prepareddatev < '" + vmonthstartdate + "' ");
		sql.append("and gl_detail.yearv >= '" + vyear + "' ");
		sql.append("and gl_detail.yearv <= '" + vyear + "' ");
		sql.append("and gl_detail.pk_accountingbook = (select pk_accountingbook from org_accountingbook where pk_relorg='"
				+ pk_org + "' and enablestate=2 and dr=0) ");
		sql.append("and gl_detail.discardflagv <> 'Y' and gl_detail.dr = 0 and gl_detail.voucherkindv <> 255 and gl_detail.tempsaveflag <> 'Y' and gl_detail.voucherkindv <> 5 ");
		// sql.append("and (gl_docfree1.F14 = '" + pk_cust_sup
		// + "' or gl_docfree1.F13='" + pk_cust_sup + "') ");
		sql.append("and gl_docfree1.F14 = '" + pk_cust_sup + "' ");
		sql.append("and gl_docfree1.F22='" + vPayType + "' ");
		sql.append("group by gl_detail.pk_accasoa,gl_detail.pk_accountingbook,gl_detail.yearv,gl_docfree1.F14 ");
		sql.append("union all ");
		sql.append("select gl_detail.pk_accasoa,gl_detail.pk_accountingbook,gl_detail.yearv yearv,gl_docfree1.F14 docF14,0.0 qclocaldebitamountsum,0.0 qclocalcreditamountsum,sum(gl_detail.localdebitamount) localdebitamountsum,sum(gl_detail.localcreditamount) localcreditamountsum ");
		sql.append("from gl_detail gl_detail, gl_docfree1 ");
		sql.append("where gl_detail.assid = gl_docfree1.assid ");
		sql.append("and gl_detail.pk_accasoa in (select pk_accasoa from bd_accasoa where pk_account in (select pk_account from bd_account where code like '"
				+ accCode + "%' ");
		sql.append("and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org
				+ "' or pk_org='"
				+ pk_group
				+ "') and dr=0)) and pk_accchart in (select pk_accchart from bd_accchart where (pk_org='"
				+ pk_org + "' or pk_org='" + pk_group + "') and dr=0)) ");
		sql.append("and gl_detail.yearv = '" + vyear + "' ");
		sql.append("and gl_detail.prepareddatev >= '" + vmonthstartdate + "' ");
		sql.append("and gl_detail.prepareddatev <= '" + vtoday + "' ");
		sql.append("and gl_detail.yearv >= '" + vyear + "' ");
		sql.append("and gl_detail.yearv <= '" + vyear + "' ");
		sql.append("and gl_detail.pk_accountingbook = (select pk_accountingbook from org_accountingbook where pk_relorg='"
				+ pk_org + "' and enablestate=2 and dr=0) ");
		sql.append("and gl_detail.discardflagv <> 'Y' and gl_detail.dr = 0 and gl_detail.voucherkindv <> 255 and gl_detail.tempsaveflag <> 'Y' and gl_detail.pk_managerv = 'N/A' and gl_detail.voucherkindv <> 5 ");
		// sql.append("and (gl_docfree1.F14 = '" + pk_cust_sup
		// + "' or gl_docfree1.F13='" + pk_cust_sup + "') ");
		sql.append("and gl_docfree1.F14 = '" + pk_cust_sup + "' ");
		sql.append("and gl_docfree1.F22='" + vPayType + "' ");
		sql.append("group by gl_detail.pk_accasoa,gl_detail.pk_accountingbook,gl_detail.yearv,gl_docfree1.F14 ");
		sql.append(") v inner join bd_supplier supplier on v.docf14=supplier.pk_supplier ");
		sql.append("group by v.pk_accountingbook,v.yearv,v.docf14 ");

		return sql;
	}

	protected StringBuilder buildQueryNeedPaySql(String pk_apply,
			String pk_org, String pk_supplier) {
		StringBuilder sql = new StringBuilder();

		sql.append("select h.pk_org,h.pk_supplier,sum(b.olcapplymny) olcapplymny,sum(b.olcunpaymny) olcunpaymny,sum(b.olcactualpaymny) olcactualpaymny ");
		sql.append("from cmp_apply h inner join cmp_apply_b b on h.pk_apply=b.pk_apply ");
		sql.append("where h.dr=0 and b.dr=0 and h.pk_org='" + pk_org
				+ "' and h.pk_supplier='" + pk_supplier
				+ "' and h.vbillstatus<>0 ");
		sql.append("and b.olcapplymny>nvl(b.olcactualpaymny,0) ");
		sql.append(" and b.isfrozen='N' ");
		if (pk_apply != null && !"".equals(pk_apply))
			sql.append(" and h.pk_apply<>'" + pk_apply + "' ");
		sql.append("group by h.pk_org,h.pk_supplier ");

		return sql;
	}

	@Override
	public void updateBillStatus(String pk_apply) throws BusinessException {
		SqlBuilder sql = new SqlBuilder();
		BaseDAO baseDao = new BaseDAO();
		sql.append("update cmp_apply set busistatus=1 where ");
		sql.append(ApplyVO.PK_APPLY, pk_apply);
		sql.append("and ");
		sql.append(ApplyVO.BUSISTATUS, Integer.valueOf(2));
		sql.append("and ");
		sql.append(ApplyVO.VBILLSTATUS, ApproveStatus.FREE);
		baseDao.executeUpdate(sql.toString());
		sql = new SqlBuilder();
		sql.append("update cmp_apply_b set busistatus=1 where ");
		sql.append(ApplyVO.PK_APPLY, pk_apply);
		sql.append("and ");
		sql.append(ApplyVO.BUSISTATUS, Integer.valueOf(2));
		sql.append("and ");
		sql.append(ApplyVO.VBILLSTATUS, ApproveStatus.FREE);
		baseDao.executeUpdate(sql.toString());
	}

}
