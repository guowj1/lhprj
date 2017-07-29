package nc.ui.to.businessinfo.view;

import nc.vo.to.businessinfo.entity.BusinessinfoBBVO;
import nc.vo.uif2.LoginContext;

import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.UIState;

/**
 * �ڲ�������Ϣ����·��ҳǩ
 * 
 * @since 6.36
 * @version 2015-5-15 ����8:44:49
 * @author ������
 */
public class PathListView extends ShowUpableBillListView {

  /**
   * 
   */
  private static final long serialVersionUID = -6090030923483231771L;

  private LoginContext context;

  private int editRow;

  /**
   * ���췽��
   */
  public PathListView() {
    super();
  }

  @Override
  public void bodyRowChange(BillEditEvent e) {
    if (this.getModel().getUiState() == UIState.EDIT) {
      this.getModel().setSelectedRow(this.getEditRow());
      return;
    }
    super.bodyRowChange(e);
  }

  @Override
  public boolean canBeHidden() {
    return !super.getBillListPanel().getBillListData().isEnabled();
  }

  /**
   * 
   * @return LoginContext
   */
  public LoginContext getContext() {
    return this.context;
  }

  /**
   * 
   * @return int
   */
  public int getEditRow() {
    return this.editRow;
  }

  @Override
  public void initUI() {
    super.initUI();
    String group = this.context.getPk_group();
    String tabcode =
        this.billListPanel.getBillListData().getBodyTableCodes()[0];
    this.billListPanel.getBodyScrollPane(tabcode).setAutoAddLine(false);
    // �����Ҽ�����
    this.billListPanel.getParentListPanel().setBBodyMenuShow(false);
    this.billListPanel.getBodyScrollPane(tabcode).setBBodyMenuShow(false);

    PrecisionUtil.setPathPrecision(this.billListPanel, group);
    this.getBillListPanel().getHeadTable().setSortEnabled(false);
    this.getBillListPanel().getBodyTable().setSortEnabled(false);
    this.getBillListPanel().getHeadItem(BusinessinfoBBVO.NDISCOUNTRATE)
        .setEnabled(false);
//    this.getBillListPanel().getHeadItem(BusinessinfoBBVO.NDISCOUNTVALUE)
//    	.setEnabled(false);//�ۿ۶�
  }

  /**
   * 
   * @param context
   */
  public void setContext(LoginContext context) {
    this.context = context;
  }

  /**
   * 
   * @param editRow
   */
  public void setEditRow(int editRow) {
    this.editRow = editRow;
  }

  @Override
  protected void synchronizeDataFromModel() {
    super.synchronizeDataFromModel();
    this.getBillListPanel().getHeadBillModel().updateValue();
    this.getBillListPanel().getBodyBillModel().updateValue();
  }
}
