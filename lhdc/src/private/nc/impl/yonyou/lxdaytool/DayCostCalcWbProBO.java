package nc.impl.yonyou.lxdaytool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sxlhscm.lhinvbalance.UseCalcDayRcbSettleVO;

//nc.impl.yonyou.lxdaytool.DayCostCalcServiceImpl

public class DayCostCalcWbProBO {

	/**
	 * 计算当天成本，回写生产日报单的材料出库页签
	 * 
	 * @param sfactoryid
	 * @param pk_material
	 * @param dbilldate
	 * @throws BusinessException
	 */
	public void CalcDataDayRCB(String sfactoryid, String pk_material,
			String dbilldate) throws BusinessException {
		// TODO 自动生成的方法存根
		CalcRcbClData(sfactoryid, pk_material, dbilldate);
	}

	private void CalcRcbClData(String sfactoryid, String pk_material,
			String dbilldate) throws BusinessException {
		UseCalcDayRcbSettleVO[] datavos = GetHxCalcDataVOs(sfactoryid,
				pk_material, dbilldate);
		Map<String, UFDouble> npricemaps = GetCalcDayRcbPricies(sfactoryid,
				pk_material, dbilldate);
		if (datavos != null) {
			for (UseCalcDayRcbSettleVO vo : datavos) {
				String key = vo.getSfactoryid() + vo.getPk_material();
				UFDouble nprice = npricemaps.get(key);
				if (nprice != null)
					ExecSQL(vo.getSfactoryid(), vo.getDbilldate(),
							vo.getPk_material(), nprice);
			}
		}
	}

	// 回写材料出库当日结存数据

	private void ExecSQL(String cfid, String cdate, String pk_material,
			UFDouble nprice) throws BusinessException {
		String updateSQL = "update uap_dayproduct_b b " + " set b.outprice="
				+ nprice + ",b.outmny=" + nprice + "*b.outnum  "
				+ "  where b.pk_material='" + pk_material + "' and def19='"
				+ cfid + "' and substr(def20,0,10)='" + cdate + "' ";
		new BaseDAO().executeUpdate(updateSQL);
	}

	// 得到出库成本对应的价格
	// key 为 分厂+存货 value 为价格
	public Map<String, UFDouble> GetCalcDayRcbPricies(String sfactoryid,
			String pk_material, String dbilldate) throws BusinessException {
		UFDate befbilldate = new UFDate(dbilldate).getDateBefore(1);
		String sbefbilldate = befbilldate.toString().substring(0, 10);
		UseCalcDayRcbSettleVO[] jcdatavos = GetJcCalcDayRcbSettleVOs(
				sfactoryid, pk_material, sbefbilldate);
		UseCalcDayRcbSettleVO[] drdatavos = GetDrCalcDayRcbSettleVOs(
				sfactoryid, pk_material, dbilldate);
		Map<String, UFDouble> nummaps = new HashMap<String, UFDouble>();
		Map<String, UFDouble> mnymaps = new HashMap<String, UFDouble>();
		Map<String, UFDouble> pricemaps = new HashMap<String, UFDouble>();
		if (jcdatavos != null) {
			for (UseCalcDayRcbSettleVO vo : jcdatavos) {
				String key = vo.getSfactoryid() + vo.getPk_material();
				UFDouble num = vo.getJsnum();
				UFDouble mny = vo.getJsmny();
				if (nummaps.containsKey(key)) {
					UFDouble newnum = nummaps.get(key).add(num);
					nummaps.put(key, newnum);
				} else {
					nummaps.put(key, num);
				}
				if (mnymaps.containsKey(key)) {
					UFDouble newnum = mnymaps.get(key).add(mny);
					mnymaps.put(key, newnum);
				} else {
					mnymaps.put(key, mny);
				}
			}
		}
		if (drdatavos != null) {
			for (UseCalcDayRcbSettleVO vo : drdatavos) {
				String key = vo.getSfactoryid() + vo.getPk_material();
				UFDouble num = vo.getJsnum();
				UFDouble mny = vo.getJsmny();
				if (nummaps.containsKey(key)) {
					UFDouble newnum = nummaps.get(key).add(num);
					nummaps.put(key, newnum);
				} else {
					nummaps.put(key, num);
				}
				if (mnymaps.containsKey(key)) {
					UFDouble newnum = mnymaps.get(key).add(mny);
					mnymaps.put(key, newnum);
				} else {
					mnymaps.put(key, mny);
				}
			}
		}
		if (mnymaps.size() > 0) {
			String[] keys = mnymaps.keySet().toArray(new String[0]);
			for (String key : keys) {
				UFDouble inum = nummaps.get(key);
				UFDouble imny = mnymaps.get(key);
				UFDouble nprice = imny.div(inum);
				pricemaps.put(key, nprice);
			}
		}

		return pricemaps;

	}

	/**
	 * 获取某一天某个分厂的物料的结存数量和结存金额 gwj 该方法存在问题，需调整：需要取计算日期之前的最新结存数据，而不是计算日期的前一天的结存数据
	 * TODO
	 * 
	 * @param sfactoryid
	 * @param pk_material
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetJcCalcDayRcbSettleVOs(String sfactoryid,
			String pk_material, String dbilldate) throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		bufferSQL
				.append(" select uap_invbalance.pk_group,uap_invbalance.pk_org,uap_invbalance.pk_org_v, ");
		bufferSQL
				.append("     uap_invbalance.def1 sfactoryid,substr(uap_invbalance.dbilldate,0,10) dbilldate,uap_invbalance.pk_material,");
		bufferSQL
				.append("     sum(bnum) jsnum,sum(bmny) jsmny  from uap_invbalance  ");
		bufferSQL.append("     where substr(uap_invbalance.dbilldate,0,10)='"
				+ dbilldate + "' ");
		bufferSQL.append("     and uap_invbalance.def1='" + sfactoryid + "' ");
		bufferSQL.append("     and uap_invbalance.pk_material='" + pk_material
				+ "' and nvl(uap_invbalance.dr,0)=0  ");

		bufferSQL.append(" group by ");
		bufferSQL
				.append("     uap_invbalance.pk_group,uap_invbalance.pk_org,uap_invbalance.pk_org_v, ");
		bufferSQL
				.append("     uap_invbalance.def1,substr(uap_invbalance.dbilldate,0,10),uap_invbalance.pk_material ");
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

	/**
	 * 获取分厂+物料+日期的入库数量、入库金额
	 * 
	 * @param sfactoryid
	 * @param pk_material
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetDrCalcDayRcbSettleVOs(String sfactoryid,
			String pk_material, String dbilldate) throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		bufferSQL
				.append(" select uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_h.def1 sfactoryid,substr(dbilldate,0,10) dbilldate,uap_dayproduct_s.pk_material, ");
		bufferSQL
				.append("    sum(innum)   jsnum,sum(inmny) jsmny from uap_dayproduct_h ");
		bufferSQL
				.append("  inner join uap_dayproduct_s on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_s.pk_dayproduct_h ");
		bufferSQL
				.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_s.dr=0 ");
		bufferSQL.append("     where substr(uap_dayproduct_h.dbilldate,0,10)='"
				+ dbilldate + "' ");
		bufferSQL
				.append("     and uap_dayproduct_h.def1='" + sfactoryid + "' ");
		bufferSQL.append("     and uap_dayproduct_s.pk_material='"
				+ pk_material + "' ");
		bufferSQL.append(" group by ");
		bufferSQL
				.append(" uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_h.def1,substr(dbilldate,0,10),uap_dayproduct_s.pk_material ");

		// b.outprice="+nprice+",b.outmny="+nprice+"*b.outnum

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

//	private UseCalcDayRcbSettleVO[] GetDrCkCalcDataVOs(String dbilldate)
//			throws BusinessException {
//		StringBuffer bufferSQL = new StringBuffer();
//		bufferSQL
//				.append("   	select pk_group,pk_org,pk_org_v,pk_stordoc,uap_dayproduct_h.def1 sfactoryid,substr(dbilldate,0,10) dbilldate,pk_material, ");
//		bufferSQL.append("    sum((outnum*-1)) jsnum  from uap_dayproduct_h  ");
//		bufferSQL
//				.append("  inner join uap_dayproduct_b on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_b.pk_dayproduct_h ");
//		bufferSQL
//				.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_b.dr=0  ");
//		bufferSQL.append("     where substr(dbilldate,0,10)='" + dbilldate
//				+ "' ");
//		bufferSQL.append(" group by ");
//		bufferSQL
//				.append("   	pk_group,pk_org,pk_org_v,pk_stordoc,uap_dayproduct_h.def1,substr(dbilldate,0,10),pk_material ");
//
//		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
//				IUAPQueryBS.class.getName());
//		Object o = iUAPQueryBS.executeQuery(bufferSQL.toString(),
//				new BeanListProcessor(UseCalcDayRcbSettleVO.class));
//		if (o == null)
//			return null;
//		List lhead = (List) o;
//		if (lhead.size() < 1)
//			return null;
//		UseCalcDayRcbSettleVO[] orderVOS = (UseCalcDayRcbSettleVO[]) lhead
//				.toArray(new UseCalcDayRcbSettleVO[0]);
//		return orderVOS;
//	}

	/**
	 * 获取材料出库页签数据分厂+物料+日期
	 * 
	 * @param sfactoryid
	 * @param pk_material
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetHxCalcDataVOs(String sfactoryid,
			String pk_material, String dbilldate) throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		bufferSQL
				.append("  select distinct uap_dayproduct_b.def19 sfactoryid,substr(dbilldate,0,10) dbilldate,  ");
		bufferSQL
				.append("    uap_dayproduct_b.pk_material from uap_dayproduct_h  ");
		bufferSQL
				.append("   inner join uap_dayproduct_b on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_b.pk_dayproduct_h ");
		bufferSQL
				.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_b.dr=0   ");
		bufferSQL.append("     where substr(dbilldate,0,10)='" + dbilldate
				+ "' ");
		bufferSQL.append("     and uap_dayproduct_b.def19='" + sfactoryid
				+ "' ");
		bufferSQL.append("     and uap_dayproduct_b.pk_material='"
				+ pk_material + "' ");
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

}
