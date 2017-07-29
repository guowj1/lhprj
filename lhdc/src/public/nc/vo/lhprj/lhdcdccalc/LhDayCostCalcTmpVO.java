package nc.vo.lhprj.lhdcdccalc;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class LhDayCostCalcTmpVO extends SuperVO {
	public String cDate;
	public String pk_subcorp;
	public String pk_material;
	public UFDouble fMny;

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getPk_subcorp() {
		return pk_subcorp;
	}

	public void setPk_subcorp(String pk_subcorp) {
		this.pk_subcorp = pk_subcorp;
	}

	public String getPk_material() {
		return pk_material;
	}

	public void setPk_material(String pk_material) {
		this.pk_material = pk_material;
	}

	public UFDouble getfMny() {
		return fMny;
	}

	public void setfMny(UFDouble fMny) {
		this.fMny = fMny;
	}

}
