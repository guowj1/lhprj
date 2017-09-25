package nc.impl.pub.ace;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.lhcalcday.CalcdayVO;

public abstract class AceLhcalcdayPubServiceImpl extends SmartServiceImpl {
	public CalcdayVO[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		String where = querySheme.getWhereSQLOnly();
		CalcdayVO[] orderVOS=null;
		if(where==null)
			where=" 1=1 ";
		String orderbySQL="  order by calcdate ";
		String selectSQL= " select * from uap_lhcalcday where nvl(dr,0)=0 and "+where+orderbySQL;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		Object objvo =iUAPQueryBS.executeQuery(selectSQL, new BeanListProcessor(CalcdayVO.class));
		if(objvo!=null)
		{
			List lhead = (List) objvo;
			orderVOS =(CalcdayVO[])lhead.toArray(new CalcdayVO[0]);
		}
		return orderVOS;
		
	}
}