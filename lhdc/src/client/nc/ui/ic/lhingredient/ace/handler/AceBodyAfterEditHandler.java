package nc.ui.ic.lhingredient.ace.handler;

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
        BillCardPanel cpanel = e.getBillCardPanel();
        if (key.equals("pk_material")) {
            String pk_material= PubToolService.getString_TrimZeroLenAsNull(
       			 cpanel.getBodyValueAt(e.getRow(), "pk_material"));
            String pk_supplier= PubToolService.getString_TrimZeroLenAsNull(
          			 cpanel.getBodyValueAt(e.getRow(), "pk_supplier"));
            UFDouble watersl=GetWaterData(pk_supplier,pk_material);
            cpanel.setBodyValueAt(watersl, e.getRow(), "watercontent");
            
            UFDouble innum=PubToolService.getUFDouble_NullAsZero(
            		cpanel.getBodyValueAt(e.getRow(), "innum"));
        	UFDouble watercontent=PubToolService.getUFDouble_NullAsZero(
        			cpanel.getBodyValueAt(e.getRow(), "watercontent"));
        	UFDouble waterdatachange=new UFDouble(1.00).sub(watercontent.div(100));
        	UFDouble drybase=innum.multiply(waterdatachange);
        	cpanel.setBodyValueAt(drybase, e.getRow(), "drybase");
        	   UFDouble trainprice=PubToolService.getUFDouble_NullAsZero(
        			   cpanel.getBodyValueAt(e.getRow(), "trainprice"));
           	  UFDouble nprice=PubToolService.getUFDouble_NullAsZero(
           			cpanel.getBodyValueAt(e.getRow(), "nprice"));
           	UFDouble inprice=nprice.add(trainprice);
           	cpanel.setBodyValueAt(inprice, e.getRow(), "inprice");
           	UFDouble inmny=inprice.multiply(drybase);
           	cpanel.setBodyValueAt(inmny, e.getRow(), "inmny");
        }
        else if (key.equals("pk_material")) {
        	  String pk_material= PubToolService.getString_TrimZeroLenAsNull(
            			 cpanel.getBodyValueAt(e.getRow(), "pk_material"));
              String pk_supplier= PubToolService.getString_TrimZeroLenAsNull(
               			 cpanel.getBodyValueAt(e.getRow(), "pk_supplier"));
              UFDouble watersl=GetWaterData(pk_supplier,pk_material);
              cpanel.setBodyValueAt(watersl, e.getRow(), "watercontent");
              
              
              UFDouble innum=PubToolService.getUFDouble_NullAsZero(
              		cpanel.getBodyValueAt(e.getRow(), "innum"));
          	UFDouble watercontent=PubToolService.getUFDouble_NullAsZero(
          			cpanel.getBodyValueAt(e.getRow(), "watercontent"));
          	UFDouble waterdatachange=new UFDouble(1.00).sub(watercontent.div(100));
          	UFDouble drybase=innum.multiply(waterdatachange);
          	cpanel.setBodyValueAt(drybase, e.getRow(), "drybase");
            UFDouble trainprice=PubToolService.getUFDouble_NullAsZero(
     			   cpanel.getBodyValueAt(e.getRow(), "trainprice"));
             	  UFDouble nprice=PubToolService.getUFDouble_NullAsZero(
             			cpanel.getBodyValueAt(e.getRow(), "nprice"));
             	UFDouble inprice=nprice.add(trainprice);
             	cpanel.setBodyValueAt(inprice, e.getRow(), "inprice");
             	UFDouble inmny=inprice.multiply(drybase);
             	cpanel.setBodyValueAt(inmny, e.getRow(), "inmny");
        }
        else if(key.equals("watercontent"))
        {
        	 UFDouble innum=PubToolService.getUFDouble_NullAsZero(
             		cpanel.getBodyValueAt(e.getRow(), "innum"));
        	UFDouble watercontent=PubToolService.getUFDouble_NullAsZero(
        			cpanel.getBodyValueAt(e.getRow(), "watercontent"));
        	UFDouble waterdatachange=new UFDouble(1.00).sub(watercontent.div(100));
        	UFDouble drybase=innum.multiply(waterdatachange);
        	cpanel.setBodyValueAt(drybase, e.getRow(), "drybase");
        	  UFDouble trainprice=PubToolService.getUFDouble_NullAsZero(
       			   cpanel.getBodyValueAt(e.getRow(), "trainprice"));
           	  UFDouble nprice=PubToolService.getUFDouble_NullAsZero(
           			cpanel.getBodyValueAt(e.getRow(), "nprice"));
           	UFDouble inprice=nprice.add(trainprice);
           	cpanel.setBodyValueAt(inprice, e.getRow(), "inprice");
           	UFDouble inmny=inprice.multiply(drybase);
           	cpanel.setBodyValueAt(inmny, e.getRow(), "inmny");
        }
        else if(key.equals("innum"))
        {
 
        	UFDouble innum=PubToolService.getUFDouble_NullAsZero(e.getValue());
        	UFDouble watercontent=PubToolService.getUFDouble_NullAsZero(
        			cpanel.getBodyValueAt(e.getRow(), "watercontent"));
        	UFDouble waterdatachange=new UFDouble(1.00).sub(watercontent.div(100));
        	UFDouble drybase=innum.multiply(waterdatachange);
        	cpanel.setBodyValueAt(drybase, e.getRow(), "drybase");
        	
        	
        
        	  UFDouble trainprice=PubToolService.getUFDouble_NullAsZero(
       			   cpanel.getBodyValueAt(e.getRow(), "trainprice"));
           	 UFDouble nprice=PubToolService.getUFDouble_NullAsZero(
           			cpanel.getBodyValueAt(e.getRow(), "nprice"));
           	 UFDouble inprice=nprice.add(trainprice);
           	 cpanel.setBodyValueAt(inprice, e.getRow(), "inprice");
           	 UFDouble inmny=inprice.multiply(drybase);
           	 cpanel.setBodyValueAt(inmny, e.getRow(), "inmny");
        }
        else if(key.equals("nprice"))
        {
 
        	UFDouble nprice=PubToolService.getUFDouble_NullAsZero(e.getValue());
        	UFDouble trainprice=PubToolService.getUFDouble_NullAsZero(
        			cpanel.getBodyValueAt(e.getRow(), "trainprice"));
        	UFDouble inprice=nprice.add(trainprice);
        	cpanel.setBodyValueAt(inprice, e.getRow(), "inprice");
        	UFDouble drybase=PubToolService.getUFDouble_NullAsZero(
        			cpanel.getBodyValueAt(e.getRow(), "drybase"));
        	UFDouble inmny=inprice.multiply(drybase);
        	cpanel.setBodyValueAt(inmny, e.getRow(), "inmny");
        }
        else if(key.equals("trainprice"))
        {
 
        	UFDouble trainprice=PubToolService.getUFDouble_NullAsZero(e.getValue());
        	UFDouble nprice=PubToolService.getUFDouble_NullAsZero(
        			cpanel.getBodyValueAt(e.getRow(), "nprice"));
        	UFDouble inprice=nprice.add(trainprice);
        	cpanel.setBodyValueAt(inprice, e.getRow(), "inprice");
        	UFDouble drybase=PubToolService.getUFDouble_NullAsZero(
        			cpanel.getBodyValueAt(e.getRow(), "drybase"));
        	UFDouble inmny=inprice.multiply(drybase);
        	cpanel.setBodyValueAt(inmny, e.getRow(), "inmny");
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
