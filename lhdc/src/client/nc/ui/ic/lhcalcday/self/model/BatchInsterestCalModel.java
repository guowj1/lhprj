package nc.ui.ic.lhcalcday.self.model;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class BatchInsterestCalModel extends BillManageModel {
  /**
   * ��ѯ�����������ѯ��������ȷ����ȡ���������ˢ��ʱʹ��
   */
  private IQueryScheme queryScheme = null;

  public IQueryScheme getQueryScheme() {
    return this.queryScheme;
  }

  public void setQueryScheme(IQueryScheme queryScheme) {
    this.queryScheme = queryScheme;
  }

}
