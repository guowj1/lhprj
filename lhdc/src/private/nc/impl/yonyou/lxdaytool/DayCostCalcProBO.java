package nc.impl.yonyou.lxdaytool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcTmpVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sxlhscm.lhinvbalance.UseCalcDayRcbSettleVO;

public class DayCostCalcProBO {
	
	 /**���ݽӿڴ��������+�ֳ�+����+�������ճɱ�
	 * @param costvos
	 * @throws BusinessException
	 */
	public void WbSendInsertBalanceVOS(List<LhDayCostCalcTmpVO> costvos) throws BusinessException
	 {
		 for(LhDayCostCalcTmpVO ntmpvo:costvos.toArray(new LhDayCostCalcTmpVO[0]))
		 {
			 String dbilldate=ntmpvo.getcDate();
			 String pk_subcorp=ntmpvo.getPk_subcorp();
			 String pk_material=ntmpvo.getPk_material();
			 String sbefbilldate=dbilldate.toString().substring(0, 10);
			 UseCalcDayRcbSettleVO[] jcdatavos=GetJcCalcDayRcbSettleVOs(pk_subcorp,pk_material,sbefbilldate);
			 Map<String,UFDouble> pricemaps=new HashMap<String,UFDouble>();//�ֳ�+���ϵĵ���map
			 Map<String,UFDouble> nummaps=new HashMap<String,UFDouble>();//�ֳ�+���ϵ�����map
			 if(jcdatavos!=null)
			 {
				 for(UseCalcDayRcbSettleVO vo:jcdatavos)
				 {
					   String key=vo.getSfactoryid()+vo.getPk_material();
					   UFDouble num=vo.getJsnum();
					   if(nummaps.containsKey(key))
					   {
						   UFDouble newnum=nummaps.get(key).add(num);
						   nummaps.put(key, newnum);
					   }
					   else
					   {
						   nummaps.put(key, num);
					   }
				 }
				 for(LhDayCostCalcTmpVO tmpvo:costvos.toArray(new LhDayCostCalcTmpVO[0]))
				 {
					 String key=tmpvo.getPk_subcorp()+tmpvo.getPk_material();
					 UFDouble mny=tmpvo.getfMny();
					 UFDouble num=nummaps.get(key);
					 if(mny==null||num==null)
						 continue;
					 UFDouble nprice=mny.div(num);
					 pricemaps.put(key, nprice);
				 }
				 for(UseCalcDayRcbSettleVO vo:jcdatavos)
				 {
					 String key=vo.getSfactoryid()+vo.getPk_material();
					 UFDouble nprice=pricemaps.get(key);
					 ExecSQL(vo.getSfactoryid(),sbefbilldate,vo.getPk_material(),nprice);
				 }
				 DayCostCalcWbProBO boservce=new DayCostCalcWbProBO();
				 DayCostCalcWbBO    wbservice=new DayCostCalcWbBO();
				 boservce.CalcDataDayRCB(pk_subcorp,pk_material, dbilldate);
				 wbservice.SendInsertBalanceVOS(pk_subcorp,pk_material, dbilldate);
			 }
		 }
		
	 }
	
	 
	 /**��д�����ձ����깤���ҳǩ��ⵥ��+�����
	 * @param cfid
	 * @param cdate
	 * @param pk_material
	 * @param nprice
	 * @throws BusinessException
	 */
	private void ExecSQL(String cfid,String cdate,String pk_material,UFDouble nprice)  throws BusinessException
	 {
			String updateSQL="update uap_dayproduct_s b "
					+ " set b.inprice="+nprice+",b.inmny="+nprice+"*b.innum  "
					+ "  where b.pk_material='"+pk_material+"' and def19='"+cfid+"' and substr(def20,0,10)='"+cdate+"' " ;
			new BaseDAO().executeUpdate(updateSQL);
	}
	

	
	 /**��ȡĳһ��ĳ���ֳ������ϵĽ�������ͽ���� gwj �÷����������⣬���������Ҫȡ��������֮ǰ�����½�����ݣ������Ǽ������ڵ�ǰһ��Ľ������
	  * TODO
	 * @param sfactoryid
	 * @param pk_material
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private  UseCalcDayRcbSettleVO[] GetJcCalcDayRcbSettleVOs(String sfactoryid,String pk_material,String dbilldate) throws BusinessException
	 {
	    	StringBuffer bufferSQL=new StringBuffer();
	    	bufferSQL.append(" select uap_dayproduct_h.def1 sfactoryid, pk_material,sum(innum) jsnum  from uap_dayproduct_h ");
	    	bufferSQL.append(" inner join uap_dayproduct_s on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_s.pk_dayproduct_h ");
	    	bufferSQL.append(" and uap_dayproduct_h.dr=0 and uap_dayproduct_s.dr=0 ");
	    	bufferSQL.append("     where substr(dbilldate,0,10)='"+dbilldate+"'  ");
	    	
	      	bufferSQL.append("     and uap_dayproduct_h.def1='"+sfactoryid+"' ");
	     	bufferSQL.append("     and uap_dayproduct_s.pk_material='"+pk_material+"' ");
	     	
	    	bufferSQL.append("  group by uap_dayproduct_h.def1, pk_material  ");
	 		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
	 		Object o =iUAPQueryBS.executeQuery(bufferSQL.toString(), new BeanListProcessor(UseCalcDayRcbSettleVO.class));
	 		if (o == null)
	 			return null;
	 		List lhead = (List) o;
	 		if(lhead.size()<1)
	 			return null;
	 		UseCalcDayRcbSettleVO[] orderVOS =(UseCalcDayRcbSettleVO[])lhead.toArray(new UseCalcDayRcbSettleVO[0]);
	 		return orderVOS;
	 }
	    
	
}
