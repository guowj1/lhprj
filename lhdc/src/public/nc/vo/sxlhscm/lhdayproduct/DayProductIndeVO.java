package nc.vo.sxlhscm.lhdayproduct;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class DayProductIndeVO extends SuperVO {
	public String pk_dayproduct_inde;
	public String pk_material;
	public String vindecode;
	public String vindename;
	public UFDouble findevalue;
	public String vunitname;
	public String vbdef1;
	public String vbdef2;
	public String vbdef3;
	public String vbdef4;
	public String vbdef5;
	public String vbdef6;
	public String vbdef7;
	public String vbdef8;
	public String vbdef9;
	public String vbdef10;
	public UFDateTime ts;
	/**
	*上层单据主键
	*/
	public String pk_dayproduct_h;

	public String getPk_dayproduct_inde() {
		return pk_dayproduct_inde;
	}

	public void setPk_dayproduct_inde(String pk_dayproduct_inde) {
		this.pk_dayproduct_inde = pk_dayproduct_inde;
	}

	public String getPk_material() {
		return pk_material;
	}

	public void setPk_material(String pk_material) {
		this.pk_material = pk_material;
	}

	public String getVindecode() {
		return vindecode;
	}

	public void setVindecode(String vindecode) {
		this.vindecode = vindecode;
	}

	public String getVindename() {
		return vindename;
	}

	public void setVindename(String vindename) {
		this.vindename = vindename;
	}

	public UFDouble getFindevalue() {
		return findevalue;
	}

	public void setFindevalue(UFDouble findevalue) {
		this.findevalue = findevalue;
	}

	public String getVunitname() {
		return vunitname;
	}

	public void setVunitname(String vunitname) {
		this.vunitname = vunitname;
	}

	public String getVbdef1() {
		return vbdef1;
	}

	public void setVbdef1(String vbdef1) {
		this.vbdef1 = vbdef1;
	}

	public String getVbdef2() {
		return vbdef2;
	}

	public void setVbdef2(String vbdef2) {
		this.vbdef2 = vbdef2;
	}

	public String getVbdef3() {
		return vbdef3;
	}

	public void setVbdef3(String vbdef3) {
		this.vbdef3 = vbdef3;
	}

	public String getVbdef4() {
		return vbdef4;
	}

	public void setVbdef4(String vbdef4) {
		this.vbdef4 = vbdef4;
	}

	public String getVbdef5() {
		return vbdef5;
	}

	public void setVbdef5(String vbdef5) {
		this.vbdef5 = vbdef5;
	}

	public String getVbdef6() {
		return vbdef6;
	}

	public void setVbdef6(String vbdef6) {
		this.vbdef6 = vbdef6;
	}

	public String getVbdef7() {
		return vbdef7;
	}

	public void setVbdef7(String vbdef7) {
		this.vbdef7 = vbdef7;
	}

	public String getVbdef8() {
		return vbdef8;
	}

	public void setVbdef8(String vbdef8) {
		this.vbdef8 = vbdef8;
	}

	public String getVbdef9() {
		return vbdef9;
	}

	public void setVbdef9(String vbdef9) {
		this.vbdef9 = vbdef9;
	}

	public String getVbdef10() {
		return vbdef10;
	}

	public void setVbdef10(String vbdef10) {
		this.vbdef10 = vbdef10;
	}

	public UFDateTime getTs() {
		return ts;
	}

	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	
	

	public String getPk_dayproduct_h() {
		return pk_dayproduct_h;
	}

	public void setPk_dayproduct_h(String pk_dayproduct_h) {
		this.pk_dayproduct_h = pk_dayproduct_h;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("lhprj.dayproductinde");
	}

}
