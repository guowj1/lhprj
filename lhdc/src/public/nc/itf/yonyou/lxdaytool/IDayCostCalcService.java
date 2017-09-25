package nc.itf.yonyou.lxdaytool;

import java.util.List;

import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcTmpVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public interface IDayCostCalcService {
	

	public String SendCostCalcBill(List<LhDayCostCalcTmpVO> costvos) throws BusinessException;
	
	
	public void CalcDataDayRCB(String jsdate) throws BusinessException;
	
	public void UnCalcDataDayRCB(String jsdate) throws BusinessException;


	public UFBoolean CheckDaySettele(String jsdate) throws BusinessException;
	

}
