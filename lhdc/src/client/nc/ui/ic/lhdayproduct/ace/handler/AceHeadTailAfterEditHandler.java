package nc.ui.ic.lhdayproduct.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.rcbgl.lhpubtool.PubToolService;

/**
 * 单据表头表尾字段编辑后事件处理类
 * 
 * @since 6.0
 * @version 2011-7-7 下午02:52:22
 * @author duy
 */
public class AceHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

    @Override
    public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    	if("def1".equals(e.getKey())||"dbilldate".equals(e.getKey()))
    	{
    		SetBodySafeActfids(e);
    	}    	
    }
    
    private void SetBodySafeActfids(CardHeadTailAfterEditEvent e)
    {
    	String btablecode="pk_dayproduct_b";
    	String stablecode="pk_dayproduct_s";
    	BillCardPanel cpanel = e.getBillCardPanel();
        String def1= PubToolService.getString_TrimZeroLenAsNull(
          		cpanel.getHeadItem("def1").getValue());
        String dbilldate= PubToolService.getString_TrimZeroLenAsNull(
          		cpanel.getHeadItem("dbilldate").getValue());   
    	int browcount=e.getBillCardPanel().getBillModel(btablecode).getRowCount();
    	for(int i=0;i<browcount;i++)
    	{
    		//cpanel.setBodyValueAt(def1, i, "def19",btablecode);
    		cpanel.setBodyValueAt(dbilldate, i, "def20",btablecode);
    	}
    	int srowcount=e.getBillCardPanel().getBillModel(stablecode).getRowCount();
    	for(int i=0;i<srowcount;i++)
    	{
    		cpanel.setBodyValueAt(def1, i, "def19",stablecode);
    		cpanel.setBodyValueAt(dbilldate, i, "def20",stablecode);
    	}
    }

}
