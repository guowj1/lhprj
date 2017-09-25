package nc.vo.lhprj.lhdcdccalc;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class LhDayOutTmpVO extends SuperVO {
	public String pk_marbasclass;
	public UFDouble outmny;

	public String getPk_marbasclass() {
		return pk_marbasclass;
	}

	public void setPk_marbasclass(String pk_marbasclass) {
		this.pk_marbasclass = pk_marbasclass;
	}

	public UFDouble getOutmny() {
		return outmny;
	}

	public void setOutmny(UFDouble outmny) {
		this.outmny = outmny;
	}

}
