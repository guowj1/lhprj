package nc.vo.lhprj.lhcontalarm;

import java.util.List;

import nc.vo.pub.SuperVO;

public class LhContractRepayAlarmVO extends SuperVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ��֯����
	 */
	private String pk_org;
	
	/**
	 * ��֯����
	 */
	private String orgname;
	
	/**
	 * ��ͬ����
	 */
	private String contracttype;
	
	/**
	 * ��ͬ��
	 */
	private String contractcode;
	
	/**
	 * ������������
	 */
	private String pk_creditorg;
	
	/**
	 * ������������
	 */
	private String creditcorpname;
	
	/**
	 * ��λ����
	 */
	private String pk_debitorg;
	
	/**
	 * ��λ����
	 */
	private String debitcorpname;
	
	/**
	 * ���е�λ����
	 */
	private String pk_consignbank;
	
	/**
	 * ��������
	 */
	private String pk_currtype;
	
	/**
	 * ��������
	 */
	private String currtypename;
	
	/**
	 * ��ͬ���
	 */
	private String contractamount;
	
	/**
	 * ��������
	 */
	private String repaydate;
	
	/**
	 * Ԥ�ƻ������
	 */
	private String preamount;
	
	/**
	 * Ԥ�Ƹ�Ϣ���
	 */
	private String preinterest;
	
	/**
	 * ��λ�˻�
	 */
	private String accountnum;
	
	/**
	 * �ֻ���
	 */
	private String phoneno;
	
	/**
	 * ���ʱ���
	 */
	private String ratecode;
	
	/**
	 * ��ʼ����
	 */
	private String cstartdate;
	
	/**
	 * Ԥ��ԭ��
	 */
	private String alarmcause;
	
	/**
	 * ������������
	 */
	private String pk_creditbank;
	
	private List<Object> alarmcauseList;

	public String getCurrtypename() {
		return currtypename;
	}

	public void setCurrtypename(String currtypename) {
		this.currtypename = currtypename;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getContracttype() {
		return contracttype;
	}

	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}

	public String getContractcode() {
		return contractcode;
	}

	public void setContractcode(String contractcode) {
		this.contractcode = contractcode;
	}

	public String getCreditcorpname() {
		return creditcorpname;
	}

	public void setCreditcorpname(String creditcorpname) {
		this.creditcorpname = creditcorpname;
	}

	public String getDebitcorpname() {
		return debitcorpname;
	}

	public void setDebitcorpname(String debitcorpname) {
		this.debitcorpname = debitcorpname;
	}

	public String getPk_consignbank() {
		return pk_consignbank;
	}

	public void setPk_consignbank(String pk_consignbank) {
		this.pk_consignbank = pk_consignbank;
	}

	public String getPk_currtype() {
		return pk_currtype;
	}

	public void setPk_currtype(String pk_currtype) {
		this.pk_currtype = pk_currtype;
	}

	public String getContractamount() {
		return contractamount;
	}

	public void setContractamount(String contractamount) {
		this.contractamount = contractamount;
	}

	public String getRepaydate() {
		return repaydate;
	}

	public void setRepaydate(String repaydate) {
		this.repaydate = repaydate;
	}

	public String getPreamount() {
		return preamount;
	}

	public void setPreamount(String preamount) {
		this.preamount = preamount;
	}

	public String getPreinterest() {
		return preinterest;
	}

	public void setPreinterest(String preinterest) {
		this.preinterest = preinterest;
	}

	public String getAlarmcause() {
		return alarmcause;
	}

	public void setAlarmcause(String alarmcause) {
		this.alarmcause = alarmcause;
	}

	public String getPk_creditorg() {
		return pk_creditorg;
	}

	public void setPk_creditorg(String pk_creditorg) {
		this.pk_creditorg = pk_creditorg;
	}

	public String getPk_debitorg() {
		return pk_debitorg;
	}

	public void setPk_debitorg(String pk_debitorg) {
		this.pk_debitorg = pk_debitorg;
	}

	public String getPk_creditbank() {
		return pk_creditbank;
	}

	public void setPk_creditbank(String pk_creditbank) {
		this.pk_creditbank = pk_creditbank;
	}

	public List<Object> getAlarmcauseList() {
		return alarmcauseList;
	}

	public void setAlarmcauseList(List<Object> alarmcauseList) {
		this.alarmcauseList = alarmcauseList;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getRatecode() {
		return ratecode;
	}

	public void setRatecode(String ratecode) {
		this.ratecode = ratecode;
	}

	public String getCstartdate() {
		return cstartdate;
	}

	public void setCstartdate(String cstartdate) {
		this.cstartdate = cstartdate;
	}
	
	
}
