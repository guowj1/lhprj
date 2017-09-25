package nc.vo.lhprj.lhdcprodcolle;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class LhProdColleTmpVO extends SuperVO{
	public String pk_subcorp;
	public String pk_marbasclass;
	public UFDouble fqty;
	
	
	public String getPk_subcorp() {
		return pk_subcorp;
	}
	public void setPk_subcorp(String pk_subcorp) {
		this.pk_subcorp = pk_subcorp;
	}
	public String getPk_marbasclass() {
		return pk_marbasclass;
	}
	public void setPk_marbasclass(String pk_marbasclass) {
		this.pk_marbasclass = pk_marbasclass;
	}
	public UFDouble getFqty() {
		return fqty;
	}
	public void setFqty(UFDouble fqty) {
		this.fqty = fqty;
	}
	
	
}
