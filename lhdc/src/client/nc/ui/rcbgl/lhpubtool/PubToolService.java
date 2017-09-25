package nc.ui.rcbgl.lhpubtool;

import java.math.BigDecimal;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.lang.UFDouble;

public class PubToolService {
	
//	public static Map<String, String> getMapValue() throws Exception
//	{
//		  StringBuffer bufferSQL=new StringBuffer();
//		  bufferSQL.append(" select ncdata,wadata from uap_waitem ");
//		  IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
//		  Map<String, String> obj=(Map<String, String>)bs.executeQuery(bufferSQL.toString(),new WadaItemConvertProcessor());
//		  return  obj;
//	}
//	  
	
	public static String GetOrgPurid(String pk_source) throws Exception
	{
		String price="";
		StringBuffer querySQL=new StringBuffer();
		querySQL.append(" select pk_material  from  bd_material_v ");
		querySQL.append("   where pk_source ='"+pk_source+"'  ");
		querySQL.append("   order by version  desc ");	
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	
	public static String GetOrgPuridByName(String suppliername) throws Exception
	{
		//select pk_purchaseorg from org_purchaseorg where name='新乡白鹭化纤集团有限责任公司' and nvl(dr,0)=0

		String price="";
		StringBuffer querySQL=new StringBuffer();
		querySQL.append(" select pk_purchaseorg  from  org_purchaseorg ");
		querySQL.append("   where name ='"+suppliername+"'  and nvl(dr,0)=0 ");
		querySQL.append("    ");	
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	public static String GetMaterialID(String materialcode) throws Exception
	{
		String price="";
		StringBuffer querySQL=new StringBuffer();
		querySQL.append(" select pk_material  from  bd_material ");
		querySQL.append("   where code ='"+materialcode+"'  and nvl(dr,0)=0 ");
		querySQL.append("    ");	
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	public static String GetMeasdocID(String measname) throws Exception
	{
		String price="";
		StringBuffer querySQL=new StringBuffer();
		querySQL.append(" select pk_measdoc  from  bd_measdoc ");
		querySQL.append("   where name ='"+measname+"'  and nvl(dr,0)=0 ");
		querySQL.append("    ");	
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	public static String GetSupplierID(String suppliername) throws Exception
	{
		String price="";
		StringBuffer querySQL=new StringBuffer();
		querySQL.append(" select pk_supplier  from  bd_supplier ");
		querySQL.append("   where name ='"+suppliername+"'  and nvl(dr,0)=0 ");
		querySQL.append("    ");	
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	public static String GetInvMerid(String pk_source) throws Exception
	{
		String price="";
		StringBuffer querySQL=new StringBuffer();
		querySQL.append(" select pk_material  from  bd_material_v ");
		querySQL.append("   where pk_source ='"+pk_source+"'  ");
		querySQL.append("   order by version  desc ");	
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	
	public static String GetInvMeasdoc (String pk_source) throws Exception
	{
		String price="";
		StringBuffer querySQL=new StringBuffer();
		querySQL.append(" select pk_measdoc  from bd_material ");
		querySQL.append("   where pk_material ='"+pk_source+"'  ");	
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	


	
	public static String GetStringValue(String querySQL) throws Exception
	{
		String price="";
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getString_TrimZeroLenAsNull(result);
		return price;
	}
	
	
	
	
	
	public static UFDouble GetUFDoubleValue(String querySQL) throws Exception
	{
		UFDouble price=UFDouble.ZERO_DBL;
		IUAPQueryBS bs = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object result = (Object)bs.executeQuery(querySQL.toString(), new ColumnProcessor());
		price=getUFDouble_NullAsZero(result);
		return price;
	}
	
	
	
	public static String getString_TrimZeroLenAsNull(Object value) {
		  if ( value==null || value.toString().trim().length()==0 ) {
		    return  null ;
		  }
		  return  value.toString() ;
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
