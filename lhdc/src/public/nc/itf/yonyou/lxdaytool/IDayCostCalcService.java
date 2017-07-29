package nc.itf.yonyou.lxdaytool;

import java.util.List;

import nc.vo.lhprj.lhdcdccalc.LhDayCostCalcTmpVO;

public interface IDayCostCalcService {
	

	public String SendCostCalcBill(List<LhDayCostCalcTmpVO> costvos);

}
