/*
 * 创建日期 2005-12-21
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
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
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class RangeSelectedPanel extends UIPanel{

	private static final long serialVersionUID = 1L;
    private UICheckBox m_cbDefItem = null;// 统自购类型
   
    private  UITextArea m_cboComboBox = null;
    
	private UICheckBox m_cbDefCGZT = null;
	
	private UIRefPane refOrg = null;// 组织录入参照
	private UIRefPane refbenginOrg = null;// 组织录入参照
	
	private boolean icWGZX;// 是否物管中心
	public boolean getisIcWGZX() {
		return icWGZX;
	}

	public void setIcWGZX(boolean icWGZX) {
		this.icWGZX = icWGZX;
	}
	
	private String defaultOrgPk = null;// 默认公司
	/**
	 * 设置默认公司
	 * 
	 * @param pk
	 * @throws BusinessException
	 */
	public void setDefaultRefOrg(String pk) {
		this.defaultOrgPk = pk;
	}

    /**
     * MRCombineBasePanel 构造子注解。
     */
    public RangeSelectedPanel() {
    	super();
    	initialize();
    }
    
    /**
     * MRCombineBasePanel 构造子注解。
     */
    public RangeSelectedPanel(String defaultOrgPk,boolean icWGZX) {
    	super();
    	initialize();
    }

    /* （非 Javadoc）
     * @see nc.ui.pub.para.ISysInitPanel#getPanel()
     */
    public UIPanel getPanel() {
        return this;
    }

    /**
     * 初始化类。
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
     * 自定义项 
     * <p>
     * <b>examples:</b>
     * <p>
     * 使用示例
     * <p>
     * <b>参数说明</b>
     * @return
     * <p>
     * @author donggq
     * @time 2008-7-17 下午07:13:08
     */
    public nc.ui.pub.beans.UICheckBox getCbDefitem() {
    	if (m_cbDefItem == null) {
    		m_cbDefItem = new nc.ui.pub.beans.UICheckBox();
    		m_cbDefItem.setSelected(true);
    		m_cbDefItem.setEnabled(true);
    		m_cbDefItem.setText("计算日期");
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
 			refbenginOrg.setRefNodeName("日历");
 			refbenginOrg.setMultiSelectedEnabled(true);
 			refbenginOrg.setText(ServerTimeProxy.getInstance().getServerTime().getDate().toString());
 			refbenginOrg.setButtonFireEvent(true);
 		}
 		return refbenginOrg;
 	}
    
    /**
     * 
     * 自定义项 
     * <p>
     * <b>examples:</b>
     * <p>
     * 使用示例
     * <p>
     * <b>参数说明</b>
     * @return
     * <p>
     * @author donggq
     * @time 2008-7-17 下午07:13:08
     */
    public UICheckBox getCbDefCGZTitem() {
    	if (m_cbDefCGZT == null) {
    		m_cbDefCGZT = new UICheckBox();
    		m_cbDefCGZT.setEnabled(true);
    		m_cbDefCGZT.setText("结束日期");
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
			getRefOrg().setRefNodeName("日历");
			String wherePart=" ";
			getRefOrg().setWhereString(wherePart);
			refOrg.setMultiSelectedEnabled(true);
			refOrg.setButtonFireEvent(true);
		}
		return refOrg;
	}

}