package nc.bs.lhitf.bgtask;

import java.util.Map;

import nc.bs.lhitf.bgtask.pub.LhQueryCusBalance;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.vo.lhprj.lhcustomer.LhCustomerVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AutoQueryCusBalanceBgTaskPlugin implements IBackgroundWorkPlugin{

	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		
		LhCustomerVO vo1=new LhCustomerVO();
		vo1.setPk_id("4195");
		vo1.setCustcode("C58672");
		
		LhCustomerVO vo2=new LhCustomerVO();
		vo2.setPk_id("4194");
		vo2.setCustcode("C58673");
		
		
//		LhCustomerVO vo3=new LhCustomerVO();
//		vo3.setPk_id("999000");
//		vo3.setCustcode("C02012-11");
		
		LhCustomerVO[] custVos=new LhCustomerVO[]{vo1,vo2};
		
		LhQueryCusBalance qrySrv=new LhQueryCusBalance();
		Map<String,UFDouble> mapResult= qrySrv.getBalance(custVos);
		
		return null;
	}

}
