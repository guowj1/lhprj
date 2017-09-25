package nc.ui.ic.lhdayproduct.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.rcbgl.lhpubtool.PubToolService;
import nc.vo.pub.lang.UFDouble;
/**
 *单据表体字段编辑后事件
 * 
 * @since 6.0
 * @version 2011-7-12 下午08:17:33
 * @author duy
 */
public class AceBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

    @Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        String key = e.getKey();
        //String stablecode="pk_dayproduct_s";
        BillCardPanel cpanel = e.getBillCardPanel();
        String def1= PubToolService.getString_TrimZeroLenAsNull(
        		cpanel.getHeadItem("def1").getValue());
        String dbilldate= PubToolService.getString_TrimZeroLenAsNull(
        		cpanel.getHeadItem("dbilldate").getValue());
       	//cpanel.setBodyValueAt(def1, e.getRow(), "def19");
    	cpanel.setBodyValueAt(dbilldate, e.getRow(), "def20");
        if("pk_dayproduct_s".equals(e.getTableCode()))
        {
        	cpanel.setBodyValueAt(def1, e.getRow(), "def19");
        }
      
    }
    
    
    
 
    
    private UFDouble GetWaterData(String pk_supplier,String pk_material)
    {
    	UFDouble rwaterdata=UFDouble.ZERO_DBL;
    	if(pk_supplier==null)
    		return rwaterdata;
    	if(pk_material==null)
    		return rwaterdata;
    	String selectSQL=" select watercontent from uap_ingredient_b where "
    			+ "	pk_supplier='"+pk_supplier+"' and pk_material='"+pk_material+"' order by ts desc";
    	try {
    		rwaterdata=PubToolService.GetUFDoubleValue(selectSQL);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    	return rwaterdata;
    }

}
