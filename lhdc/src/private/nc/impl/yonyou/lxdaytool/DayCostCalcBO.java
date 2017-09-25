package nc.impl.yonyou.lxdaytool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;
import nc.vo.sxlhscm.lhinvbalance.UseCalcDayRcbSettleVO;

public class DayCostCalcBO {

	/**计算当日的成本，并将计算结果写入 存货结存单
	 * @param dbilldate
	 * @throws BusinessException
	 */
	public void SendInsertBalanceVOS(String dbilldate) throws BusinessException {
		UFDate befbilldate = new UFDate(dbilldate).getDateBefore(1);
		String sbefbilldate = befbilldate.toString().substring(0, 10);
		;
		UseCalcDayRcbSettleVO[] jcdatavos = GetJcCalcDayRcbSettleVOs(sbefbilldate);
		UseCalcDayRcbSettleVO[] drdatavos = GetDrCalcDayRcbSettleVOs(dbilldate);
		UseCalcDayRcbSettleVO[] drcldatavos = GetDrCkCalcDataVOs(dbilldate);
		Map<String, UFDouble> nummaps = new HashMap<String, UFDouble>();//分厂+物料的数量map
		if (jcdatavos != null) {
			for (UseCalcDayRcbSettleVO vo : jcdatavos) {
				String key = vo.getPk_group() + "&" + vo.getPk_org() + "&"
						+ vo.getSfactoryid() + "&" + vo.getPk_material();
				UFDouble num = vo.getJsnum();
				if (nummaps.containsKey(key)) {
					UFDouble newnum = nummaps.get(key).add(num);
					nummaps.put(key, newnum);
				} else {
					nummaps.put(key, num);
				}
			}
		}

		if (drdatavos != null) {
			for (UseCalcDayRcbSettleVO vo : drdatavos) {
				String key = vo.getPk_group() + "&" + vo.getPk_org() + "&"
						+ vo.getSfactoryid() + "&" + vo.getPk_material();
				UFDouble num = vo.getJsnum();
				if (nummaps.containsKey(key)) {
					UFDouble newnum = nummaps.get(key).add(num);
					nummaps.put(key, newnum);
				} else {
					nummaps.put(key, num);
				}
			}
		}

		if (drcldatavos != null) {
			for (UseCalcDayRcbSettleVO vo : drcldatavos) {
				String key = vo.getPk_group() + "&" + vo.getPk_org() + "&"
						+ vo.getSfactoryid() + "&" + vo.getPk_material();
				UFDouble num = vo.getJsnum();
				if (nummaps.containsKey(key)) {
					UFDouble newnum = nummaps.get(key).add(num);
					nummaps.put(key, newnum);
				} else {
					nummaps.put(key, num);
				}
			}
		}

		DayCostCalcServiceImpl impl = new DayCostCalcServiceImpl();
		Map<String, UFDouble> npricemaps = impl.GetCalcDayRcbPricies(dbilldate);//分厂+物料的单价map
		List<InvBalanceVO> invvos = new ArrayList<InvBalanceVO>();
		String newpk_group = "";
		if (nummaps.size() > 0) {
			String[] keys = nummaps.keySet().toArray(new String[0]);
			for (String key : keys) {
				String[] kvalues = key.split("&");
				String pk_group = kvalues[0];
				newpk_group = pk_group;
				String pk_org = kvalues[1];
				String pk_org_v = kvalues[1];
				// String pk_stordoc=kvalues[3];
				String sfactoryid = kvalues[2];
				String pk_material = kvalues[3];
				UFDouble nprice = npricemaps.get(sfactoryid + pk_material);
				if (nprice == null)
					continue;
				UFDouble nmum = nummaps.get(key);
				UFDouble jcmny = nprice.multiply(nmum);
				InvBalanceVO invvo = new InvBalanceVO();
				invvo.setPk_group(pk_group);
				invvo.setPk_org(pk_org);
				invvo.setPk_org_v(pk_org_v);
				invvo.setPk_material(pk_material);
				// invvo.setPk_stordoc(pk_stordoc);
				if (nmum.doubleValue() == 0)
					continue;
				invvo.setBnum(nmum);
				invvo.setBnprice(nprice);
				invvo.setBmny(jcmny);
				invvo.setDr(0);
				invvo.setDbilldate(new UFDate(dbilldate));
				invvo.setDef1(sfactoryid);
				invvo.setDef10("SETTLE");
				invvos.add(invvo);
			}
			ExecSQL("", dbilldate, null);
			dExecCalcSQL("", dbilldate, null);
			if (invvos != null) {
				if (invvos.size() > 0) {
					nc.impl.pubapp.pattern.data.vo.VOInsert service = new nc.impl.pubapp.pattern.data.vo.VOInsert();
					service.insert(invvos.toArray(new InvBalanceVO[0]));
				}
			}
		}
	}

	/**
	 * 删除某个分厂的存货结存单（日期、价格无用）
	 * 
	 * @param cfid
	 * @param cdate
	 * @param nprice
	 * @throws BusinessException
	 */
	public void ExecSQL(String cfid, String cdate, UFDouble nprice)
			throws BusinessException {
		String updateSQL = "delete from uap_invbalance b "
				+ "  where substr(dbilldate,0,10)='" + cdate
				+ "' and def10='SETTLE' ";
		new BaseDAO().executeUpdate(updateSQL);
	}

	/**
	 * 删除某一天的日结算单记录（分厂、价格无用）
	 * 
	 * @param cfid
	 * @param cdate
	 * @param nprice
	 * @throws BusinessException
	 */
	public void dExecCalcSQL(String cfid, String cdate, UFDouble nprice)
			throws BusinessException {
		String updateSQL = "delete uap_lhcalcday b "
				+ "  where substr(calcdate,0,10)='" + cdate + "'  ";
		new BaseDAO().executeUpdate(updateSQL);
	}

	/**
	 * 删除某一天的日结算单记录（分厂、价格无用）
	 * 
	 * @param cfid
	 * @param cdate
	 * @param nprice
	 * @throws BusinessException
	 */
	public void ExecCalcSQL(String cfid, String cdate, UFDouble nprice)
			throws BusinessException {
		String updateSQL = "delete from uap_lhcalcday b "
				+ "  where substr(calcdate,0,10)='" + cdate + "'  ";
		new BaseDAO().executeUpdate(updateSQL);
	}

	/**
	 * 获取某一天某个分厂的物料的结存数量和结存金额 gwj 该方法存在问题，需调整：需要取计算日期之前的最新结存数据，而不是计算日期的前一天的结存数据
	 * TODO
	 * 
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetJcCalcDayRcbSettleVOs(String dbilldate)
			throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		bufferSQL
				.append(" select uap_invbalance.pk_group,uap_invbalance.pk_org,uap_invbalance.pk_org_v, "); // pk_stordoc，
		bufferSQL
				.append("     uap_invbalance.def1 sfactoryid,substr(uap_invbalance.dbilldate,0,10) dbilldate,uap_invbalance.pk_material, ");
		bufferSQL
				.append("     sum(bnum) jsnum,sum(bmny) jsmny  from uap_invbalance ");
		bufferSQL
				.append("     inner join bd_material on bd_material.pk_material=uap_invbalance.pk_material and bd_material.def1='1001A6100000000753H3' ");
		bufferSQL.append("     where substr(uap_invbalance.dbilldate,0,10)='"
				+ dbilldate + "' and uap_invbalance.dr=0 ");
		bufferSQL
				.append(" group by  uap_invbalance.pk_group,uap_invbalance.pk_org,uap_invbalance.pk_org_v, ");
		bufferSQL
				.append("           uap_invbalance.def1,substr(uap_invbalance.dbilldate,0,10),uap_invbalance.pk_material ");
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
		Object o = iUAPQueryBS.executeQuery(bufferSQL.toString(),
				new BeanListProcessor(UseCalcDayRcbSettleVO.class));
		if (o == null)
			return null;
		List lhead = (List) o;
		if (lhead.size() < 1)
			return null;
		UseCalcDayRcbSettleVO[] orderVOS = (UseCalcDayRcbSettleVO[]) lhead
				.toArray(new UseCalcDayRcbSettleVO[0]);
		return orderVOS;
	}

	/**获取某一天某个分厂的物料的主料入库数量和入库金额
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetDrCalcDayRcbSettleVOs(String dbilldate)
			throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer(); // pk_stordoc,
		bufferSQL
				.append(" select uap_ingredient_h.pk_group,uap_ingredient_h.pk_org,uap_ingredient_h.pk_org_v,uap_ingredient_h.def1 sfactoryid,");
		bufferSQL
				.append("    substr(uap_ingredient_h.dbilldate,0,10) dbilldate,uap_ingredient_b.pk_material,  ");
		bufferSQL
				.append("    sum(drybase) jsnum,sum(inmny) jsmny from uap_ingredient_h ");
		bufferSQL
				.append("    inner join uap_ingredient_b on uap_ingredient_h.pk_ingredient_h=uap_ingredient_b.pk_ingredient_h ");
		bufferSQL
				.append("          and uap_ingredient_h.dr=0 and uap_ingredient_b.dr=0 ");
		bufferSQL
				.append("   inner join bd_material on bd_material.pk_material=uap_ingredient_b.pk_material and bd_material.def1='1001A6100000000753H3' ");
		bufferSQL.append("     where substr(uap_ingredient_h.dbilldate,0,10)='"
				+ dbilldate + "' ");
		bufferSQL.append(" group by ");
		bufferSQL
				.append("    uap_ingredient_h.pk_group,uap_ingredient_h.pk_org,uap_ingredient_h.pk_org_v,uap_ingredient_h.def1,");
		bufferSQL
				.append("    substr(uap_ingredient_h.dbilldate,0,10),uap_ingredient_b.pk_material  ");
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
		Object o = iUAPQueryBS.executeQuery(bufferSQL.toString(),
				new BeanListProcessor(UseCalcDayRcbSettleVO.class));
		if (o == null)
			return null;
		List lhead = (List) o;
		if (lhead.size() < 1)
			return null;
		UseCalcDayRcbSettleVO[] orderVOS = (UseCalcDayRcbSettleVO[]) lhead
				.toArray(new UseCalcDayRcbSettleVO[0]);
		return orderVOS;
	}

	/**获取某一天某个分厂的物料的出库数量和出库金额 gwj 170820调整为分厂取表体出货分厂
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetDrCkCalcDataVOs(String dbilldate)
			throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer(); // pk_stordoc,
		bufferSQL
//				.append("  select uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_h.def1 sfactoryid, ");
		.append("  select uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_b.def19 sfactoryid, ");
		bufferSQL
				.append("  substr(uap_dayproduct_h.dbilldate,0,10) dbilldate, uap_dayproduct_b.pk_material, ");
		bufferSQL.append("   sum((outnum*-1)) jsnum  from uap_dayproduct_h  ");
		bufferSQL
				.append("  inner join uap_dayproduct_b on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_b.pk_dayproduct_h ");
		bufferSQL
				.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_b.dr=0  ");

		bufferSQL
				.append("   inner join bd_material on bd_material.pk_material=uap_dayproduct_b.pk_material and bd_material.def1='1001A6100000000753H3' ");

		bufferSQL.append("     where substr(uap_dayproduct_h.dbilldate,0,10)='"
				+ dbilldate + "' ");
		bufferSQL.append(" group by ");
		bufferSQL
				.append("  uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_b.def19, ");
		bufferSQL
				.append("  substr(uap_dayproduct_h.dbilldate,0,10), uap_dayproduct_b.pk_material ");
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
		Object o = iUAPQueryBS.executeQuery(bufferSQL.toString(),
				new BeanListProcessor(UseCalcDayRcbSettleVO.class));
		if (o == null)
			return null;
		List lhead = (List) o;
		if (lhead.size() < 1)
			return null;
		UseCalcDayRcbSettleVO[] orderVOS = (UseCalcDayRcbSettleVO[]) lhead
				.toArray(new UseCalcDayRcbSettleVO[0]);
		return orderVOS;
	}

	private static BaseDAO dao = null;

	private static BaseDAO getDao() {
		if (dao == null) {
			dao = new BaseDAO();
		}
		return dao;
	}

	public UFBoolean CheckNextDaySettele(String begindate)
			throws BusinessException {
		StringBuffer checkSQL = new StringBuffer();
		checkSQL.append(" select count(*) from uap_lhcalcday where nvl(dr,0)=0 and substr(calcdate,0,10)>'"
				+ begindate + "' and nvl(iscalc,'N')='Y' ");
		Object result = (Object) getDao().executeQuery(checkSQL.toString(),
				new ColumnProcessor());
		UFDouble dl = getUFDouble_NullAsZero(result);
		if (dl.doubleValue() > 0) {
			return UFBoolean.FALSE;
		} else {
			return UFBoolean.TRUE;
		}
	}

	public UFBoolean CheckDaySettele(String begindate) throws BusinessException {
		StringBuffer checkSQL = new StringBuffer();
		checkSQL.append(" select count(*) from uap_lhcalcday where nvl(dr,0)=0 and substr(calcdate,0,10)='"
				+ begindate + "' and nvl(iscalc,'N')='Y' ");
		Object result = (Object) getDao().executeQuery(checkSQL.toString(),
				new ColumnProcessor());
		UFDouble dl = getUFDouble_NullAsZero(result);
		if (dl.doubleValue() > 0) {
			return UFBoolean.FALSE;
		} else {
			return UFBoolean.TRUE;
		}
	}

	public static UFDouble getUFDouble_NullAsZero(Object value) {
		if (value == null || value.toString().trim().equals("")) {
			return new UFDouble(0.0);
		} else if (value instanceof UFDouble) {
			return (UFDouble) value;
		} else if (value instanceof BigDecimal) {
			return new UFDouble((BigDecimal) value);
		} else {
			return new UFDouble(value.toString().trim());
		}
	}
}
