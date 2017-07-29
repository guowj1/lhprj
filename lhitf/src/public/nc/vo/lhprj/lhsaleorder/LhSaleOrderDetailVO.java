package nc.vo.lhprj.lhsaleorder;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 * 此处添加累的描述信息
 * </p>
 * 创建日期:2017-3-17
 * 
 * @author YONYOU NC
 * @version NCPrj ??
 */

public class LhSaleOrderDetailVO extends SuperVO {

	/**
	 * 子表主键
	 */
	public java.lang.String pk_saleorder_b;
	/**
	 * 组织编码
	 */
	public java.lang.String pk_storg;
	/**
	 * 仓库编码
	 */
	public java.lang.String whcode;
	/**
	 * 物料编码
	 */
	public java.lang.String matcode;
	/**
	 * 主数量
	 */
	public nc.vo.pub.lang.UFDouble iqty;
	/**
	 * 含税金额
	 */
	public nc.vo.pub.lang.UFDouble ftaxmoney;
	/**
	 * 上层单据主键
	 */
	public String pk_saleorder;
	/**
	 * 时间戳
	 */
	public UFDateTime ts;

	/**
	 * 属性 pk_saleorder_b的Getter方法.属性名：子表主键 创建日期:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_saleorder_b() {
		return this.pk_saleorder_b;
	}

	/**
	 * 属性pk_saleorder_b的Setter方法.属性名：子表主键 创建日期:2017-3-17
	 * 
	 * @param newPk_saleorder_b
	 *            java.lang.String
	 */
	public void setPk_saleorder_b(java.lang.String pk_saleorder_b) {
		this.pk_saleorder_b = pk_saleorder_b;
	}

	/**
	 * 属性 pk_storg的Getter方法.属性名：组织编码 创建日期:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_storg() {
		return this.pk_storg;
	}

	/**
	 * 属性pk_storg的Setter方法.属性名：组织编码 创建日期:2017-3-17
	 * 
	 * @param newPk_storg
	 *            java.lang.String
	 */
	public void setPk_storg(java.lang.String pk_storg) {
		this.pk_storg = pk_storg;
	}

	/**
	 * 属性 whcode的Getter方法.属性名：仓库编码 创建日期:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getWhcode() {
		return this.whcode;
	}

	/**
	 * 属性whcode的Setter方法.属性名：仓库编码 创建日期:2017-3-17
	 * 
	 * @param newWhcode
	 *            java.lang.String
	 */
	public void setWhcode(java.lang.String whcode) {
		this.whcode = whcode;
	}

	/**
	 * 属性 matcode的Getter方法.属性名：物料编码 创建日期:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getMatcode() {
		return this.matcode;
	}

	/**
	 * 属性matcode的Setter方法.属性名：物料编码 创建日期:2017-3-17
	 * 
	 * @param newMatcode
	 *            java.lang.String
	 */
	public void setMatcode(java.lang.String matcode) {
		this.matcode = matcode;
	}

	/**
	 * 属性 iqty的Getter方法.属性名：主数量 创建日期:2017-3-17
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getIqty() {
		return this.iqty;
	}

	/**
	 * 属性iqty的Setter方法.属性名：主数量 创建日期:2017-3-17
	 * 
	 * @param newIqty
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setIqty(nc.vo.pub.lang.UFDouble iqty) {
		this.iqty = iqty;
	}

	/**
	 * 属性 ftaxmoney的Getter方法.属性名：含税金额 创建日期:2017-3-17
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getFtaxmoney() {
		return this.ftaxmoney;
	}

	/**
	 * 属性ftaxmoney的Setter方法.属性名：含税金额 创建日期:2017-3-17
	 * 
	 * @param newFtaxmoney
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setFtaxmoney(nc.vo.pub.lang.UFDouble ftaxmoney) {
		this.ftaxmoney = ftaxmoney;
	}

	/**
	 * 属性 生成上层主键的Getter方法.属性名：上层主键 创建日期:2017-3-17
	 * 
	 * @return String
	 */
	public String getPk_saleorder() {
		return this.pk_saleorder;
	}

	/**
	 * 属性生成上层主键的Setter方法.属性名：上层主键 创建日期:2017-3-17
	 * 
	 * @param newPk_saleorder
	 *            String
	 */
	public void setPk_saleorder(String pk_saleorder) {
		this.pk_saleorder = pk_saleorder;
	}

	/**
	 * 属性 生成时间戳的Getter方法.属性名：时间戳 创建日期:2017-3-17
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * 属性生成时间戳的Setter方法.属性名：时间戳 创建日期:2017-3-17
	 * 
	 * @param newts
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("lhprj.LhSaleOrderDetail");
	}
}
