package nc.ui.lhprj.lhdc.lhqtstdday.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;

public class AceBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{
	@Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        String key = e.getKey();
        //String stablecode="pk_dayproduct_s";
        UFDouble nnum=UFDouble.ZERO_DBL;
        UFDouble nprice=UFDouble.ZERO_DBL;
        UFDouble nmny=UFDouble.ZERO_DBL;
        if("nnum1".equals(key) || "nprice1".equals(key)){
        	nnum=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum1")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum1"));
        	nprice=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice1")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice1"));
        	nmny=nnum.multiply(nprice);
        	e.getBillCardPanel().setBodyValueAt(nmny, e.getRow(), "nmny1");
        }else if("nnum2".equals(key) || "nprice2".equals(key)){
        	nnum=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum2")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum2"));
        	nprice=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice2")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice2"));
        	nmny=nnum.multiply(nprice);
        	e.getBillCardPanel().setBodyValueAt(nmny, e.getRow(), "nmny2");
        }else if("nnum3".equals(key) || "nprice3".equals(key)){
        	nnum=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum3")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum3"));
        	nprice=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice3")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice3"));
        	nmny=nnum.multiply(nprice);
        	e.getBillCardPanel().setBodyValueAt(nmny, e.getRow(), "nmny3");
        }else if("nnum4".equals(key) || "nprice4".equals(key)){
        	nnum=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum4")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum4"));
        	nprice=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice4")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice4"));
        	nmny=nnum.multiply(nprice);
        	e.getBillCardPanel().setBodyValueAt(nmny, e.getRow(), "nmny4");
        }else if("nnum5".equals(key) || "nprice5".equals(key)){
        	nnum=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum5")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum5"));
        	nprice=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice5")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice5"));
        	nmny=nnum.multiply(nprice);
        	e.getBillCardPanel().setBodyValueAt(nmny, e.getRow(), "nmny5");
        }else if("nnum6".equals(key) || "nprice6".equals(key)){
        	nnum=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum6")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum6"));
        	nprice=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice6")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice6"));
        	nmny=nnum.multiply(nprice);
        	e.getBillCardPanel().setBodyValueAt(nmny, e.getRow(), "nmny6");
        }else if("nnum7".equals(key) || "nprice7".equals(key)){
        	nnum=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum7")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nnum7"));
        	nprice=(UFDouble) (e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice7")==null?UFDouble.ZERO_DBL:e.getBillCardPanel().getBodyValueAt(e.getRow(), "nprice7"));
        	nmny=nnum.multiply(nprice);
        	e.getBillCardPanel().setBodyValueAt(nmny, e.getRow(), "nmny7");
        }
        
      
    }
}
