package nc.vo.to.askprice.puinprice;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class PuinItemPriceVO extends SuperVO{
	private String currencyid;
	private String cgeneralbid;
	private String cqtunitid;
	private UFDouble nqtorignetprice;
	private UFDouble nqtorigtaxnetprc;
	
	public String getCurrencyid() {
		return currencyid;
	}
	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}
	
	public String getCgeneralbid() {
		return cgeneralbid;
	}
	public void setCgeneralbid(String cgeneralbid) {
		this.cgeneralbid = cgeneralbid;
	}
	public String getCqtunitid() {
		return cqtunitid;
	}
	public void setCqtunitid(String cqtunitid) {
		this.cqtunitid = cqtunitid;
	}
	public UFDouble getNqtorignetprice() {
		return nqtorignetprice;
	}
	public void setNqtorignetprice(UFDouble nqtorignetprice) {
		this.nqtorignetprice = nqtorignetprice;
	}
	public UFDouble getNqtorigtaxnetprc() {
		return nqtorigtaxnetprc;
	}
	public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
		this.nqtorigtaxnetprc = nqtorigtaxnetprc;
	}
	
	
}
