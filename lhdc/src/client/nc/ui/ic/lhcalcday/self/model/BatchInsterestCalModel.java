package nc.ui.ic.lhcalcday.self.model;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class BatchInsterestCalModel extends BillManageModel {
  /**
   * 查询方案，缓存查询方案，在确定或取消方便调用刷新时使用
   */
  private IQueryScheme queryScheme = null;

  public IQueryScheme getQueryScheme() {
    return this.queryScheme;
  }

  public void setQueryScheme(IQueryScheme queryScheme) {
    this.queryScheme = queryScheme;
  }

}
