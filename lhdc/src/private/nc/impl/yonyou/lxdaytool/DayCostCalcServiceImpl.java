package nc.impl.yonyou.lxdaytool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.yonyou.lxdaytool.IDayCostCalcService;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcTmpVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sxlhscm.lhinvbalance.UseCalcDayRcbSettleVO;

//nc.impl.yonyou.lxdaytool.DayCostCalcServiceImpl

public class DayCostCalcServiceImpl implements IDayCostCalcService {

	static String qydate = "";

	static String porpfile = RuntimeEnv.getInstance().getNCHome()
			+ File.separator + "ierp" + File.separator + "bin" + File.separator
			+ "rcbrq.properties";

	static {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(porpfile)));
		} catch (FileNotFoundException e) {
			Logger.error("Error", e);
		} catch (IOException e) {
			Logger.error("Error", e);
		}
		qydate = properties.getProperty("qydate");
	}

	@Override
	public String SendCostCalcBill(List<LhDayCostCalcTmpVO> costvos)
			throws BusinessException {
		// TODO �Զ����ɵķ������
		DayCostCalcProBO boserview = new DayCostCalcProBO();
		boserview.WbSendInsertBalanceVOS(costvos);
		return null;
	}

	@Override
	public void UnCalcDataDayRCB(String jsdate) throws BusinessException {
		DayCostCalcBO boservice = new DayCostCalcBO();
		UFBoolean ishas = boservice.CheckNextDaySettele(jsdate);
		if (ishas.booleanValue()) {
			boservice.ExecSQL(null, jsdate, null);
			boservice.ExecCalcSQL(null, jsdate, null);
		} else {
			throw new BusinessException("���������Ѿ����㣬������ȡ�����㣡");
		}

	}

	@Override
	public UFBoolean CheckDaySettele(String jsdate) throws BusinessException {
		DayCostCalcBO boservice = new DayCostCalcBO();
		UFBoolean ishas = boservice.CheckDaySettele(jsdate);
		return ishas;
	}

	@Override
	public void CalcDataDayRCB(String jsdate) throws BusinessException {
		// TODO �Զ����ɵķ������
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(porpfile)));
		} catch (FileNotFoundException e) {
			Logger.error("Error", e);
		} catch (IOException e) {
			Logger.error("Error", e);
		}
		qydate = properties.getProperty("qydate");

		String begindate = qydate;
		UFDate bjbegindate = new UFDate(begindate);
		UFDate bjjsdate = new UFDate(jsdate);
		if (bjjsdate.after(bjbegindate)) {
			CalcRcbClData(jsdate);
		} else {
			throw new BusinessException("�������ڲ���������ϵͳ�������ڣ�");
		}

	}

	/**
	 * ���㵱��ɱ�����д�����ձ����Ĳ��ϳ���ҳǩ��ͬʱ��Ҫд������浥
	 * 
	 * @param dbilldate
	 * @throws BusinessException
	 */
	private void CalcRcbClData(String dbilldate) throws BusinessException {
		UseCalcDayRcbSettleVO[] datavos = GetHxCalcDataVOs(dbilldate);
		Map<String, UFDouble> npricemaps = GetCalcDayRcbPricies(dbilldate);
		if (datavos != null) {
			for (UseCalcDayRcbSettleVO vo : datavos) {
				String key = vo.getSfactoryid() + vo.getPk_material();
				UFDouble nprice = npricemaps.get(key);
				if (nprice != null)
					ExecSQL(vo.getSfactoryid(), vo.getDbilldate(),
							vo.getPk_material(), nprice);
			}
		}
		//gwj ���·����л��������ϱߵķ������¼���һ��ɱ�����Ϊ�˽��ճɱ�����ǹ�д������浥��Ϊʲô��Ҫ���¼����ճɱ���
		DayCostCalcBO boservice = new DayCostCalcBO();
		boservice.SendInsertBalanceVOS(dbilldate);
	}

	/**
	 * ��д�����ձ������ϳ���ҳǩ���ս������
	 * 
	 * @param cfid
	 * @param cdate
	 * @param pk_material
	 * @param nprice
	 * @throws BusinessException
	 */
	private void ExecSQL(String cfid, String cdate, String pk_material,
			UFDouble nprice) throws BusinessException {
		String updateSQL = "update uap_dayproduct_b b " + " set b.outprice="
				+ nprice + ",b.outmny=" + nprice + "*b.outnum  "
				+ "  where b.pk_material='" + pk_material + "' and def19='"
				+ cfid + "' and substr(def20,0,10)='" + cdate + "' ";
		new BaseDAO().executeUpdate(updateSQL);
	}

	/**
	 * ��ȡ������ֳ������ϵĳ���ɱ���Ӧ�ļ۸� key Ϊ �ֳ�+��� value Ϊ�۸�
	 * 
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, UFDouble> GetCalcDayRcbPricies(String dbilldate)
			throws BusinessException {
		UFDate befbilldate = new UFDate(dbilldate).getDateBefore(1);
		String sbefbilldate = befbilldate.toString().substring(0, 10);
		UseCalcDayRcbSettleVO[] jcdatavos = GetJcCalcDayRcbSettleVOs(sbefbilldate);
		UseCalcDayRcbSettleVO[] drdatavos = GetDrCalcDayRcbSettleVOs(dbilldate);
		Map<String, UFDouble> nummaps = new HashMap<String, UFDouble>();//�ֳ�+���ϵ�����map
		Map<String, UFDouble> mnymaps = new HashMap<String, UFDouble>();//�ֳ�+���ϵĽ��map
		Map<String, UFDouble> pricemaps = new HashMap<String, UFDouble>();//�ֳ�+���ϵĵ���map
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
	 * ��ȡĳһ��ĳ���ֳ������ϵĽ�������ͽ����
	 * gwj �÷����������⣬���������Ҫȡ��������֮ǰ�����½�����ݣ������Ǽ������ڵ�ǰһ��Ľ������
	 * TODO
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetJcCalcDayRcbSettleVOs(String dbilldate)
			throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		bufferSQL
				.append(" select uap_invbalance.pk_group,uap_invbalance.pk_org,uap_invbalance.pk_org_v, ");
		bufferSQL
				.append("     uap_invbalance.def1 sfactoryid,substr(uap_invbalance.dbilldate,0,10) dbilldate,uap_invbalance.pk_material,");
		bufferSQL
				.append("   sum(bnum) jsnum,sum(bmny) jsmny  from uap_invbalance ");
		bufferSQL
				.append("   inner join bd_material on bd_material.pk_material=uap_invbalance.pk_material and bd_material.def1='1001A6100000000753H3' ");

		bufferSQL.append("     where substr(uap_invbalance.dbilldate,0,10)='"
				+ dbilldate + "' and nvl(uap_invbalance.dr,0)=0 ");
		bufferSQL.append(" group by ");
		bufferSQL
				.append("     uap_invbalance.pk_group,uap_invbalance.pk_org,uap_invbalance.pk_org_v, ");
		bufferSQL
				.append("     uap_invbalance.def1,substr(uap_invbalance.dbilldate,0,10),uap_invbalance.pk_material ");
		bufferSQL.append("");
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
	 * ��ȡĳһ��ĳ���ֳ������ϵ�������������������
	 * 
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetDrCalcDayRcbSettleVOs(String dbilldate)
			throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		bufferSQL
				.append(" select uap_ingredient_h.pk_group,uap_ingredient_h.pk_org,uap_ingredient_h.pk_org_v,uap_ingredient_h.def1 sfactoryid,");
		bufferSQL
				.append("    substr(uap_ingredient_h.dbilldate,0,10) dbilldate,uap_ingredient_b.pk_material,  ");
		bufferSQL
				.append("    sum(drybase) jsnum,sum(inmny) jsmny from uap_ingredient_h ");
		bufferSQL
				.append("  inner join uap_ingredient_b on uap_ingredient_h.pk_ingredient_h=uap_ingredient_b.pk_ingredient_h ");
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
				.append("    substr(uap_ingredient_h.dbilldate,0,10),uap_ingredient_b.pk_material ");
		bufferSQL.append("");
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
	 * ��ȡĳһ��ĳ���ֳ������ϵĳ��������ͳ����� gwj 170820����Ϊ�ֳ�ȡ��������ֳ�
	 * 
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetDrCkCalcDataVOs(String dbilldate)
			throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		bufferSQL
		// .append("   	select pk_group,pk_org,pk_org_v,pk_stordoc,uap_dayproduct_h.def1 sfactoryid,substr(dbilldate,0,10) dbilldate,  ");
				.append("   	select pk_group,pk_org,pk_org_v,pk_stordoc,uap_dayproduct_b.def19 sfactoryid,substr(dbilldate,0,10) dbilldate,  ");
		bufferSQL
				.append("    pk_material,sum((outnum*-1)) jsnum  from uap_dayproduct_h  ");
		bufferSQL
				.append("  inner join uap_dayproduct_b on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_b.pk_dayproduct_h ");
		bufferSQL
				.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_b.dr=0  ");
		bufferSQL.append("     where substr(dbilldate,0,10)='" + dbilldate
				+ "' ");
		bufferSQL.append(" group by ");
		bufferSQL
				.append("   pk_group,pk_org,pk_org_v,pk_stordoc,uap_dayproduct_b.def19,substr(dbilldate,0,10),pk_material  ");
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
	 * ��ȡ����ֳ����õĲ��ϣ��ֳ������ڡ����� gwj ����170820
	 * 
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UseCalcDayRcbSettleVO[] GetHxCalcDataVOs(String dbilldate)
			throws BusinessException {
		StringBuffer bufferSQL = new StringBuffer();
		// bufferSQL.append("  select distinct uap_dayproduct_h.def1 sfactoryid,substr(uap_dayproduct_h.dbilldate,0,10) dbilldate,  ");
		bufferSQL
				.append("  select distinct uap_dayproduct_b.def19 sfactoryid,substr(uap_dayproduct_h.dbilldate,0,10) dbilldate,  ");
		bufferSQL
				.append("    uap_dayproduct_b.pk_material from uap_dayproduct_h  ");
		bufferSQL
				.append("   inner join uap_dayproduct_b on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_b.pk_dayproduct_h ");
		bufferSQL
				.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_b.dr=0   ");
		bufferSQL
				.append("   inner join bd_material on bd_material.pk_material=uap_dayproduct_b.pk_material and bd_material.def1='1001A6100000000753H3' ");
		bufferSQL.append("     where substr(uap_dayproduct_h.dbilldate,0,10)='"
				+ dbilldate + "' ");
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
