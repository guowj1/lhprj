package nc.ui.rcbgl.dialog;

import javax.swing.JPanel;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.vo.pub.lang.UFBoolean;

/**
 * ҵ�����������ά������ı�ע�Ի��� �������ڣ�(2001-7-6 20:17:44)
 * 
 * @author��<��ѧ��>
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
		// click Ok Btn,getSysInitVO������and saveSysInitVO
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
	 * ����ҵ����Panel �������ڣ�(2001-7-9 16:24:09)
	 */
	private void addPanel() {
		if (dealAllUpdatePanel != null) {
			getScp().setViewportView(dealAllUpdatePanel.getPanel());
			// getScp().add(m_SysInitPanel.getPanel());
			getScp().revalidate();
		}
	}

	/**
	 * ���� BtnCancel ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnCancel() {
		if (ivjBtnCancel == null) {
			try {
				ivjBtnCancel = new nc.ui.pub.beans.UIButton();
				ivjBtnCancel.setName("BtnCancel");
				ivjBtnCancel.setText(nc.ui.ml.NCLangRes.getInstance()
						.getStrByID("common", "UC001-0000008")/* @res "ȡ��" */);
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
	 * ���� BtnOK ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnOK() {
		if (ivjBtnOK == null) {
			try {
				ivjBtnOK = new nc.ui.pub.beans.UIButton();
				ivjBtnOK.setName("BtnOK");
				ivjBtnOK.setText("ȷ��");
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
	 * ���� PnlButton ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	/* ���棺�˷������������ɡ� */
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
	 * ���� Scp ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIScrollPane
	 */
	/* ���棺�˷������������ɡ� */
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
	 * ���� UIDialogContentPane ����ֵ��
	 * 
	 * @return javax.swing.JPanel
	 */
	/* ���棺�˷������������ɡ� */
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
	 * ÿ�������׳��쳣ʱ������
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {

		/* ��ȥ���и��е�ע�ͣ��Խ�δ��׽�����쳣��ӡ�� stdout�� */
		// System.out.println("--------- δ��׽�����쳣 ---------");
		// exception.printStackTrace(System.out);
	}

	/**
	 * ��ʼ���ࡣ
	 */
	/* ���棺�˷������������ɡ� */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			setName("DealAllUpdatePanel");
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			setSize(628, 334);
			setTitle("�Ի���");
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