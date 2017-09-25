/*
 * �������� 2005-12-21
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package nc.ui.rcbgl.dialog;

import java.awt.Dimension;

import nc.desktop.ui.ServerTimeProxy;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITextArea;
import nc.vo.pub.BusinessException;

/**
 * @author shaobing
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class RangeSelectedPanel extends UIPanel{

	private static final long serialVersionUID = 1L;
    private UICheckBox m_cbDefItem = null;// ͳ�Թ�����
   
    private  UITextArea m_cboComboBox = null;
    
	private UICheckBox m_cbDefCGZT = null;
	
	private UIRefPane refOrg = null;// ��֯¼�����
	private UIRefPane refbenginOrg = null;// ��֯¼�����
	
	private boolean icWGZX;// �Ƿ��������
	public boolean getisIcWGZX() {
		return icWGZX;
	}

	public void setIcWGZX(boolean icWGZX) {
		this.icWGZX = icWGZX;
	}
	
	private String defaultOrgPk = null;// Ĭ�Ϲ�˾
	/**
	 * ����Ĭ�Ϲ�˾
	 * 
	 * @param pk
	 * @throws BusinessException
	 */
	public void setDefaultRefOrg(String pk) {
		this.defaultOrgPk = pk;
	}

    /**
     * MRCombineBasePanel ������ע�⡣
     */
    public RangeSelectedPanel() {
    	super();
    	initialize();
    }
    
    /**
     * MRCombineBasePanel ������ע�⡣
     */
    public RangeSelectedPanel(String defaultOrgPk,boolean icWGZX) {
    	super();
    	initialize();
    }

    /* ���� Javadoc��
     * @see nc.ui.pub.para.ISysInitPanel#getPanel()
     */
    public UIPanel getPanel() {
        return this;
    }

    /**
     * ��ʼ���ࡣ
     */
    private void initialize() {
        setLayout(null);
		setSize(450, 380);
		add(getCbDefitem());
		add(getRefBenginOrg());
//		add(getCbDefCGZTitem());
//		add(getRefOrg());
    }   
   
    /**
     * 
     * �Զ����� 
     * <p>
     * <b>examples:</b>
     * <p>
     * ʹ��ʾ��
     * <p>
     * <b>����˵��</b>
     * @return
     * <p>
     * @author donggq
     * @time 2008-7-17 ����07:13:08
     */
    public nc.ui.pub.beans.UICheckBox getCbDefitem() {
    	if (m_cbDefItem == null) {
    		m_cbDefItem = new nc.ui.pub.beans.UICheckBox();
    		m_cbDefItem.setSelected(true);
    		m_cbDefItem.setEnabled(true);
    		m_cbDefItem.setText("��������");
    		m_cbDefItem.setBounds(10, 25, 100, 22);
    	}
    	return m_cbDefItem;
    }
//    public UITextArea getTFDef() {
//    	if (m_cboComboBox == null) {
//    		m_cboComboBox = new UITextArea();
//    		m_cboComboBox.setEditable(getCbDefitem().isSelected());  
//    		m_cboComboBox.setBounds(110, 25, 240, 22);
//    		m_cboComboBox.setText("15:30");
//    	}
//    	return m_cboComboBox;
//    }
    
    public UIRefPane getRefBenginOrg() {
 		if (refbenginOrg == null) {
 			refbenginOrg = new nc.ui.pub.beans.UIRefPane();
 			refbenginOrg.setName("refbenginOrg");
 			refbenginOrg.setPreferredSize(new Dimension(240, 22));
 			refbenginOrg.setBounds(110, 25, 240, 22);
 			refbenginOrg.setRefNodeName("����");
 			refbenginOrg.setMultiSelectedEnabled(true);
 			refbenginOrg.setText(ServerTimeProxy.getInstance().getServerTime().getDate().toString());
 			refbenginOrg.setButtonFireEvent(true);
 		}
 		return refbenginOrg;
 	}
    
    /**
     * 
     * �Զ����� 
     * <p>
     * <b>examples:</b>
     * <p>
     * ʹ��ʾ��
     * <p>
     * <b>����˵��</b>
     * @return
     * <p>
     * @author donggq
     * @time 2008-7-17 ����07:13:08
     */
    public UICheckBox getCbDefCGZTitem() {
    	if (m_cbDefCGZT == null) {
    		m_cbDefCGZT = new UICheckBox();
    		m_cbDefCGZT.setEnabled(true);
    		m_cbDefCGZT.setText("��������");
    		m_cbDefCGZT.setSelected(true);
    		m_cbDefCGZT.setBounds(10, 55, 100, 22);
    	}
    	return m_cbDefCGZT;
    }
    
    public UIRefPane getRefOrg() {
		if (refOrg == null) {
			refOrg = new nc.ui.pub.beans.UIRefPane();
			refOrg.setName("refOrg");
			refOrg.setPreferredSize(new Dimension(260, 22));
			refOrg.setBounds(110, 55, 240, 22);
			getRefOrg().setRefNodeName("����");
			String wherePart=" ";
			getRefOrg().setWhereString(wherePart);
			refOrg.setMultiSelectedEnabled(true);
			refOrg.setButtonFireEvent(true);
		}
		return refOrg;
	}

}