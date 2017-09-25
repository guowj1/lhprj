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
import nc.vo.ic.lhcalcday.CalcdayVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sxlhscm.lhinvbalance.InvBalanceVO;
import nc.vo.sxlhscm.lhinvbalance.UseCalcDayRcbSettleVO;

public class DayCostCalcWbBO {
	
	 public void SendInsertBalanceVOS(String sfactoryid,String pk_material,String dbilldate) throws BusinessException
	 {
		   UFDate befbilldate=new UFDate(dbilldate).getDateBefore(1);
		   String sbefbilldate=befbilldate.toString().substring(0, 10);;
		   UseCalcDayRcbSettleVO[] jcdatavos=GetJcCalcDayRcbSettleVOs(sfactoryid,pk_material,sbefbilldate);
		   UseCalcDayRcbSettleVO[] drdatavos=GetDrCalcDayRcbSettleVOs(sfactoryid,pk_material,dbilldate);
		   UseCalcDayRcbSettleVO[] drcldatavos=GetDrCkCalcDataVOs(sfactoryid,pk_material,dbilldate);
		   Map<String,UFDouble> nummaps=new HashMap<String,UFDouble>();
		   if(jcdatavos!=null)
		   {
			   for(UseCalcDayRcbSettleVO vo:jcdatavos)
			   {
				   String key=vo.getPk_group()+"&"+vo.getPk_org()+"&"+vo.getSfactoryid()+"&"+vo.getPk_material();
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
		   }
		  
		   if(drdatavos!=null)
		   {
			   for(UseCalcDayRcbSettleVO vo:drdatavos)
			   {
				   String key=vo.getPk_group()+"&"+vo.getPk_org()+"&"+vo.getSfactoryid()+"&"+vo.getPk_material();
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
		   }
		  
		   if(drcldatavos!=null)
		   {
			   for(UseCalcDayRcbSettleVO vo:drcldatavos)
			   {
				   String key=vo.getPk_group()+"&"+vo.getPk_org()+"&"+vo.getSfactoryid()+"&"+vo.getPk_material();
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
		   }
		  
		   DayCostCalcWbProBO impl=new DayCostCalcWbProBO();
		Map<String, UFDouble> npricemaps = impl.GetCalcDayRcbPricies(sfactoryid,pk_material,dbilldate);
		   List<InvBalanceVO> invvos=new ArrayList<InvBalanceVO>();
		   String newpk_group="";
		   if(nummaps.size()>0)
		   {
			   String[] keys=nummaps.keySet().toArray(new String[0]);
			   for(String key:keys)
			   {
				   String[] kvalues=key.split("&");
				   String  pk_group=kvalues[0];
				   newpk_group=pk_group;
				   String  pk_org=kvalues[1];
				   String  pk_org_v=kvalues[1];
				   //String  pk_stordoc=kvalues[3];
				   String  sfactoryid1=kvalues[2];
				   String pk_material1=kvalues[3];
				   UFDouble nprice=npricemaps.get(sfactoryid1+pk_material1);
				   if(nprice==null)
					   continue;
				   UFDouble nmum=nummaps.get(key);
				   UFDouble jcmny=nprice.multiply(nmum);
				   InvBalanceVO invvo=new InvBalanceVO();
				   invvo.setPk_group(pk_group);
				   invvo.setPk_org(pk_org);
				   invvo.setPk_org_v(pk_org_v);
				   invvo.setPk_material(pk_material1);
				   //invvo.setPk_stordoc(pk_stordoc);
				   if(nmum.doubleValue()==0)
					   continue;
				   invvo.setBnum(nmum);
				   invvo.setBnprice(nprice);
				   invvo.setBmny(jcmny);
				   invvo.setDr(0);
				   invvo.setDbilldate(new UFDate(dbilldate));
				   invvo.setDef1(sfactoryid1);
				   invvo.setDef10("wbxtjs");
				   invvos.add(invvo);
			   }
			   //sfactoryid,pk_material
			   ExecSQL(sfactoryid,dbilldate,pk_material);
			   if(invvos!=null)
			   {
				   if(invvos.size()>0)
				   {
					   nc.impl.pubapp.pattern.data.vo.VOInsert service=new nc.impl.pubapp.pattern.data.vo.VOInsert();
					   service.insert(invvos.toArray(new InvBalanceVO[0]));
				   }
			   }
			
			   
			  
		   }
	   }
		
	 
	 public void ExecSQL(String sfactoryid,String cdate,String pk_material)  throws BusinessException
		{
			String updateSQL=" delete from uap_invbalance b "
					+ "  where substr(dbilldate,0,10)='"+cdate+"' and def10='wbxtjs' "
							+ "  and def1='"+sfactoryid+"' and pk_material='"+pk_material+"' " ;
			new BaseDAO().executeUpdate(updateSQL);
		}
	 
	 
	 


	
	 /**获取某一天某个分厂的物料的结存数量和结存金额 gwj 该方法存在问题，需调整：需要取计算日期之前的最新结存数据，而不是计算日期的前一天的结存数据
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
	    	bufferSQL.append(" select pk_group,pk_org,pk_org_v, ");   //pk_stordoc，
	    	bufferSQL.append("     def1 sfactoryid,substr(dbilldate,0,10) dbilldate,uap_invbalance.pk_material, ");
	    	bufferSQL.append("     sum(bnum) jsnum,sum(bmny) jsmny  from uap_invbalance");
	    	bufferSQL.append("     where substr(dbilldate,0,10)='"+dbilldate+"' and dr=0 ");
	    	bufferSQL.append("     and def1='"+sfactoryid+"' ");
	    	bufferSQL.append("     and pk_material='"+pk_material+"' ");
	    	bufferSQL.append("  group by ");
	    	bufferSQL.append("     pk_group,pk_org,pk_org_v, ");   //pk_stordoc，
	    	bufferSQL.append("     def1,substr(dbilldate,0,10),uap_invbalance.pk_material ");
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
	    
	    /**获取某一天某个分厂的物料的主料入库数量和入库金额
	     * @param sfactoryid
	     * @param pk_material
	     * @param dbilldate
	     * @return
	     * @throws BusinessException
	     */
	    private  UseCalcDayRcbSettleVO[] GetDrCalcDayRcbSettleVOs(String sfactoryid,String pk_material,String dbilldate) throws BusinessException
	 	{
	    	StringBuffer bufferSQL=new StringBuffer();  //pk_stordoc,
	    	bufferSQL.append(" select uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_h.def1 sfactoryid, ");
	    	bufferSQL.append("    substr(dbilldate,0,10) dbilldate,uap_dayproduct_s.pk_material, ");
	    	bufferSQL.append("    sum(innum)   jsnum,sum(inmny) jsmny from uap_dayproduct_h ");
	    	bufferSQL.append("  inner join uap_dayproduct_s on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_s.pk_dayproduct_h ");
	    	bufferSQL.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_s.dr=0 ");
	     	bufferSQL.append("     where substr(uap_dayproduct_h.dbilldate,0,10)='"+dbilldate+"' ");
	     	bufferSQL.append("     and uap_dayproduct_h.def1='"+sfactoryid+"' ");
	     	bufferSQL.append("     and uap_dayproduct_s.pk_material='"+pk_material+"' ");
	     	bufferSQL.append("  group by ");
	     	bufferSQL.append("    uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_h.def1, ");
	    	bufferSQL.append("    substr(dbilldate,0,10),uap_dayproduct_s.pk_material ");
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
	    
	    
	    /**获取某一天某个分厂的物料的出库数量和出库金额 
	     * @param sfactoryid
	     * @param pk_material
	     * @param dbilldate
	     * @return
	     * @throws BusinessException
	     */
	    private  UseCalcDayRcbSettleVO[] GetDrCkCalcDataVOs(String sfactoryid,String pk_material,String dbilldate) throws BusinessException
	   	{
	      	StringBuffer bufferSQL=new StringBuffer();  //pk_stordoc,
	      	bufferSQL.append("  select uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_b.def19 sfactoryid, ");
	      	bufferSQL.append("    substr(uap_dayproduct_h.dbilldate,0,10) dbilldate,  uap_dayproduct_b.pk_material, ");
	      	bufferSQL.append("   sum((outnum*-1)) jsnum  from uap_dayproduct_h  ");
	      	bufferSQL.append("  inner join uap_dayproduct_b on uap_dayproduct_h.pk_dayproduct_h=uap_dayproduct_b.pk_dayproduct_h ");
	      	bufferSQL.append("          and uap_dayproduct_h.dr=0 and uap_dayproduct_b.dr=0  ");
	       	bufferSQL.append("     where substr(uap_dayproduct_h.dbilldate,0,10)='"+dbilldate+"' ");
	    	bufferSQL.append("     and uap_dayproduct_b.def19='"+sfactoryid+"' ");
	     	bufferSQL.append("     and uap_dayproduct_b.pk_material='"+pk_material+"' ");
	    	bufferSQL.append("  group by ");
	     	bufferSQL.append("    uap_dayproduct_h.pk_group,uap_dayproduct_h.pk_org,uap_dayproduct_h.pk_org_v,uap_dayproduct_b.def19, ");
	      	bufferSQL.append("    substr(uap_dayproduct_h.dbilldate,0,10),  uap_dayproduct_b.pk_material ");
	    	
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
	    
	    
		private static BaseDAO dao = null;

		private static BaseDAO getDao() {
			if (dao == null) {
				dao = new BaseDAO();
			}
			return dao;
		}
		
		
		
		public UFBoolean CheckNextDaySettele(String begindate) throws BusinessException
		{
			StringBuffer checkSQL=new StringBuffer();
			checkSQL.append(" select count(*) from uap_lhcalcday where nvl(dr,0)=0 and substr(calcdate,0,10)>'"+begindate+"' and nvl(iscalc,'N')='Y' ");
			Object result = (Object)getDao().executeQuery(checkSQL.toString(), new ColumnProcessor());
		    UFDouble dl=getUFDouble_NullAsZero(result);
			if(dl.doubleValue()>0)
			{
				return UFBoolean.FALSE; 
			}
			else
			{
				return UFBoolean.TRUE;
			}
		}
		
		public UFBoolean CheckDaySettele(String begindate) throws BusinessException
		{
			StringBuffer checkSQL=new StringBuffer();
			checkSQL.append(" select count(*) from uap_lhcalcday where nvl(dr,0)=0 and substr(calcdate,0,10)='"+begindate+"' and nvl(iscalc,'N')='Y' ");
			Object result = (Object)getDao().executeQuery(checkSQL.toString(), new ColumnProcessor());
		    UFDouble dl=getUFDouble_NullAsZero(result);
			if(dl.doubleValue()>0)
			{
				return UFBoolean.FALSE; 
			}
			else
			{
				return UFBoolean.TRUE;
			}
		}
	     
		
		public static UFDouble getUFDouble_NullAsZero(Object  value) {
			  if ( value == null || value.toString().trim().equals("") ){
			    return   new UFDouble(0.0) ;
			  }else if(value  instanceof  UFDouble){
			    return  (UFDouble)value ;
			  }else if(value  instanceof  BigDecimal){
			    return  new UFDouble((BigDecimal)value) ;
			  }else{
			    return  new UFDouble(value.toString().trim());
			  }
			}
}
