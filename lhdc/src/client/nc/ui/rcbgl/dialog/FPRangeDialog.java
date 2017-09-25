package nc.ui.rcbgl.dialog;

import javax.swing.JPanel;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.vo.pub.lang.UFBoolean;

/**
 * 业务组参数对象维护界面的标注对话框 创建日期：(2001-7-6 20:17:44)
 * 
 * @author：<宋学军>
 */
@SuppressWarnings("deprecation")
public class FPRangeDialog extends nc.ui.pub.beans.UIDialog implements
		java.awt.event.ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UIButton ivjBtnCancel = null;

	private UIButton ivjBtnOK = null;

	private UIPanel ivjPnlButton = null;

	private UIScrollPane ivjScp = null;

	private JPanel ivjUIDialogContentPane = null;
	
	private RangeSelectedPanel  dealAllUpdatePanel = null;
	
	
	
	
	private String text = null;
	private String croptext = null;

	public FPRangeDialog(java.awt.Container parent) {
		super(parent);
		dealAllUpdatePanel = (RangeSelectedPanel)parent;
		initialize();
	}

	/**
	 * Invoked when an action occurs.
	 */
	public void actionPerformed(java.awt.event.ActionEvent e) {
		// click Ok Btn,getSysInitVO（），and saveSysInitVO
		if (e.getSource() == getBtnOK()) {
			if(dealAllUpdatePanel.getCbDefitem().isSelected())
			{
				String plantype=(String)dealAllUpdatePanel.getRefBenginOrg().getRefPK();
				setText(plantype);
			}
			else
			{
				setText(null);
			}
			if(dealAllUpdatePanel.getCbDefCGZTitem().isSelected())
			{
				setcroptext(dealAllUpdatePanel.getRefOrg().getRefPK());
			}
			else
			{
				setcroptext(null);
			}
			this.destroy();
		} 
		else if (e.getSource() == getBtnCancel()) {
			setText(null);
			this.destroy();
		}
		else
		{
			setText(null);
			this.destroy();
		}

	}

	/**
	 * 加载业务组Panel 创建日期：(2001-7-9 16:24:09)
	 */
	private void addPanel() {
		if (dealAllUpdatePanel != null) {
			getScp().setViewportView(dealAllUpdatePanel.getPanel());
			// getScp().add(m_SysInitPanel.getPanel());
			getScp().revalidate();
		}
	}

	/**
	 * 返回 BtnCancel 特性值。
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* 警告：此方法将重新生成。 */
	private nc.ui.pub.beans.UIButton getBtnCancel() {
		if (ivjBtnCancel == null) {
			try {
				ivjBtnCancel = new nc.ui.pub.beans.UIButton();
				ivjBtnCancel.setName("BtnCancel");
				ivjBtnCancel.setText(nc.ui.ml.NCLangRes.getInstance()
						.getStrByID("common", "UC001-0000008")/* @res "取消" */);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnCancel;
	}

	/**
	 * 返回 BtnOK 特性值。
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* 警告：此方法将重新生成。 */
	private nc.ui.pub.beans.UIButton getBtnOK() {
		if (ivjBtnOK == null) {
			try {
				ivjBtnOK = new nc.ui.pub.beans.UIButton();
				ivjBtnOK.setName("BtnOK");
				ivjBtnOK.setText("确定");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnOK;
	}

	/**
	 * 返回 PnlButton 特性值。
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	/* 警告：此方法将重新生成。 */
	private nc.ui.pub.beans.UIPanel getPnlButton() {
		if (ivjPnlButton == null) {
			try {
				ivjPnlButton = new nc.ui.pub.beans.UIPanel();
				ivjPnlButton.setName("PnlButton");
				ivjPnlButton.setLayout(new java.awt.FlowLayout());
				getPnlButton().add(getBtnOK(), getBtnOK().getName());
				getPnlButton().add(getBtnCancel(), getBtnCancel().getName());
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjPnlButton;
	}

	/**
	 * 返回 Scp 特性值。
	 * 
	 * @return nc.ui.pub.beans.UIScrollPane
	 */
	/* 警告：此方法将重新生成。 */
	private nc.ui.pub.beans.UIScrollPane getScp() {
		if (ivjScp == null) {
			try {
				ivjScp = new nc.ui.pub.beans.UIScrollPane();
				ivjScp.setName("Scp");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjScp;
	}

	/**
	 * 返回 UIDialogContentPane 特性值。
	 * 
	 * @return javax.swing.JPanel
	 */
	/* 警告：此方法将重新生成。 */
	private javax.swing.JPanel getUIDialogContentPane() {
		if (ivjUIDialogContentPane == null) {
			try {
				ivjUIDialogContentPane = new javax.swing.JPanel();
				ivjUIDialogContentPane.setName("UIDialogContentPane");
				ivjUIDialogContentPane.setLayout(new java.awt.BorderLayout());
				getUIDialogContentPane().add(getPnlButton(), "South");
				getUIDialogContentPane().add(getScp(), "Center");
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjUIDialogContentPane;
	}

	/**
	 * 每当部件抛出异常时被调用
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {

		/* 除去下列各行的注释，以将未捕捉到的异常打印至 stdout。 */
		// System.out.println("--------- 未捕捉到的异常 ---------");
		// exception.printStackTrace(System.out);
	}

	/**
	 * 初始化类。
	 */
	/* 警告：此方法将重新生成。 */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			setName("DealAllUpdatePanel");
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			setSize(628, 334);
			setTitle("对话框");
			setContentPane(getUIDialogContentPane());
		} catch (java.lang.Throwable ivjExc) {
			handleException(ivjExc);
		}
		// user code begin {2}
		addPanel();
		getBtnOK().addActionListener(this);
		getBtnCancel().addActionListener(this);
		getBtnOK().setEnabled(true);
		// user code end
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

	
	public String getcroptext() {
		return croptext;
	}

	public void setcroptext(String croptext) {
		this.croptext = croptext;
	}
	
}