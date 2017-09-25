package nc.itf.ic;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.ic.lhcalcday.CalcdayVO;

public interface ILhcalcdayMaintain extends ISmartService{

	 public CalcdayVO[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
}