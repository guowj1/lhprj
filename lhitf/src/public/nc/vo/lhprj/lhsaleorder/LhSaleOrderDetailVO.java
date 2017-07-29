package nc.vo.lhprj.lhsaleorder;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 * �˴�����۵�������Ϣ
 * </p>
 * ��������:2017-3-17
 * 
 * @author YONYOU NC
 * @version NCPrj ??
 */

public class LhSaleOrderDetailVO extends SuperVO {

	/**
	 * �ӱ�����
	 */
	public java.lang.String pk_saleorder_b;
	/**
	 * ��֯����
	 */
	public java.lang.String pk_storg;
	/**
	 * �ֿ����
	 */
	public java.lang.String whcode;
	/**
	 * ���ϱ���
	 */
	public java.lang.String matcode;
	/**
	 * ������
	 */
	public nc.vo.pub.lang.UFDouble iqty;
	/**
	 * ��˰���
	 */
	public nc.vo.pub.lang.UFDouble ftaxmoney;
	/**
	 * �ϲ㵥������
	 */
	public String pk_saleorder;
	/**
	 * ʱ���
	 */
	public UFDateTime ts;

	/**
	 * ���� pk_saleorder_b��Getter����.���������ӱ����� ��������:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_saleorder_b() {
		return this.pk_saleorder_b;
	}

	/**
	 * ����pk_saleorder_b��Setter����.���������ӱ����� ��������:2017-3-17
	 * 
	 * @param newPk_saleorder_b
	 *            java.lang.String
	 */
	public void setPk_saleorder_b(java.lang.String pk_saleorder_b) {
		this.pk_saleorder_b = pk_saleorder_b;
	}

	/**
	 * ���� pk_storg��Getter����.����������֯���� ��������:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_storg() {
		return this.pk_storg;
	}

	/**
	 * ����pk_storg��Setter����.����������֯���� ��������:2017-3-17
	 * 
	 * @param newPk_storg
	 *            java.lang.String
	 */
	public void setPk_storg(java.lang.String pk_storg) {
		this.pk_storg = pk_storg;
	}

	/**
	 * ���� whcode��Getter����.���������ֿ���� ��������:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getWhcode() {
		return this.whcode;
	}

	/**
	 * ����whcode��Setter����.���������ֿ���� ��������:2017-3-17
	 * 
	 * @param newWhcode
	 *            java.lang.String
	 */
	public void setWhcode(java.lang.String whcode) {
		this.whcode = whcode;
	}

	/**
	 * ���� matcode��Getter����.�����������ϱ��� ��������:2017-3-17
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getMatcode() {
		return this.matcode;
	}

	/**
	 * ����matcode��Setter����.�����������ϱ��� ��������:2017-3-17
	 * 
	 * @param newMatcode
	 *            java.lang.String
	 */
	public void setMatcode(java.lang.String matcode) {
		this.matcode = matcode;
	}

	/**
	 * ���� iqty��Getter����.�������������� ��������:2017-3-17
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getIqty() {
		return this.iqty;
	}

	/**
	 * ����iqty��Setter����.�������������� ��������:2017-3-17
	 * 
	 * @param newIqty
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setIqty(nc.vo.pub.lang.UFDouble iqty) {
		this.iqty = iqty;
	}

	/**
	 * ���� ftaxmoney��Getter����.����������˰��� ��������:2017-3-17
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getFtaxmoney() {
		return this.ftaxmoney;
	}

	/**
	 * ����ftaxmoney��Setter����.����������˰��� ��������:2017-3-17
	 * 
	 * @param newFtaxmoney
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setFtaxmoney(nc.vo.pub.lang.UFDouble ftaxmoney) {
		this.ftaxmoney = ftaxmoney;
	}

	/**
	 * ���� �����ϲ�������Getter����.���������ϲ����� ��������:2017-3-17
	 * 
	 * @return String
	 */
	public String getPk_saleorder() {
		return this.pk_saleorder;
	}

	/**
	 * ���������ϲ�������Setter����.���������ϲ����� ��������:2017-3-17
	 * 
	 * @param newPk_saleorder
	 *            String
	 */
	public void setPk_saleorder(String pk_saleorder) {
		this.pk_saleorder = pk_saleorder;
	}

	/**
	 * ���� ����ʱ�����Getter����.��������ʱ��� ��������:2017-3-17
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * ��������ʱ�����Setter����.��������ʱ��� ��������:2017-3-17
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
