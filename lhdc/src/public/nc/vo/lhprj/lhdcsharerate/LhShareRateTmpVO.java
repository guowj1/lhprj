package nc.vo.lhprj.lhdcsharerate;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class LhShareRateTmpVO extends SuperVO {
	public String pk_subcorp;
	public String pk_marbasclass;
	public UFDouble fsharerate;
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
	public UFDouble getFsharerate() {
		return fsharerate;
	}
	public void setFsharerate(UFDouble fsharerate) {
		this.fsharerate = fsharerate;
	}
	
	
}
