package nc.vo.lhprj.lhpurcasein;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class LhPurchaseInDetailVO extends SuperVO {
	public String pk_purchasein_b;
	public String pk_saleorder_b;
	public String matcode;
	public String vbillnodz;
	public UFDouble iqty;
	public UFDouble iqtyadj;
	public UFDouble fprice;
	public UFDouble fmoney;
	public UFDouble ftransmoney;
	public String pk_purchasein;
	public UFDateTime ts;

	public String getPk_purchasein_b() {
		return this.pk_purchasein_b;
	}

	public void setPk_purchasein_b(String pk_purchasein_b) {
		this.pk_purchasein_b = pk_purchasein_b;
	}

	public String getPk_saleorder_b() {
		return this.pk_saleorder_b;
	}

	public void setPk_saleorder_b(String pk_saleorder_b) {
		this.pk_saleorder_b = pk_saleorder_b;
	}

	public String getMatcode() {
		return this.matcode;
	}

	public void setMatcode(String matcode) {
		this.matcode = matcode;
	}

	public UFDouble getIqty() {
		return this.iqty;
	}

	public void setIqty(UFDouble iqty) {
		this.iqty = iqty;
	}

	public UFDouble getIqtyadj() {
		return this.iqtyadj;
	}

	public void setIqtyadj(UFDouble iqtyadj) {
		this.iqtyadj = iqtyadj;
	}

	public UFDouble getFprice() {
		return this.fprice;
	}

	public void setFprice(UFDouble fprice) {
		this.fprice = fprice;
	}

	public UFDouble getFmoney() {
		return this.fmoney;
	}

	public void setFmoney(UFDouble fmoney) {
		this.fmoney = fmoney;
	}

	public String getPk_purchasein() {
		return this.pk_purchasein;
	}

	public void setPk_purchasein(String pk_purchasein) {
		this.pk_purchasein = pk_purchasein;
	}

	public UFDateTime getTs() {
		return this.ts;
	}

	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	public String getVbillnodz() {
		return this.vbillnodz;
	}

	public void setVbillnodz(String vbillnodz) {
		this.vbillnodz = vbillnodz;
	}

	public UFDouble getFtransmoney() {
		return this.ftransmoney;
	}

	public void setFtransmoney(UFDouble ftransmoney) {
		this.ftransmoney = ftransmoney;
	}

	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance()
				.getVOMeta("lhprj.LhPurchaseInDetail");
	}
}