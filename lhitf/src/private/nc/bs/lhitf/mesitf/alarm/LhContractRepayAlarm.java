package nc.bs.lhitf.mesitf.alarm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.lhitf.mesitf.plugin.ContRepayAlarmSendMes;
import nc.bs.pub.cdm.alarm.ds.CdmBaseAlarm;
import nc.bs.pub.cdm.alarm.ds.CdmPreAlertDataSource;
import nc.bs.pub.pa.IBusiCalculater;
import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.util.CDMLangResVO;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.org.IOrgMetaDataIDConst;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.model.impl.MDEnum;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.ui.pub.print.IDataSource;
import nc.vo.bd.accessor.IBDData;
import nc.vo.lhprj.lhcontalarm.LhContractRepayAlarmVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.constant.CDMBusConstant;
import nc.vo.pub.contract.ContStatusEnum;
import nc.vo.pub.contract.ContractTypeEnum;
import nc.vo.pub.contract.ContractVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.tmpub.util.TMCurrencyUtil;

public class LhContractRepayAlarm extends CdmBaseAlarm implements IPreAlertPlugin {
	
	private List<Object> getFinishMessageList(UFDate endDate){
		List<Object> list = new ArrayList<Object>();
		list.add(new CDMLangResVO("3615pub_0", "03615pub-0223"));
		list.add(Integer.toString(Math.abs(AppContext.getInstance().getBusiDate().asBegin().getDaysAfter(endDate))));
		list.add(new CDMLangResVO("3615pub_0", "03615pub-0214"));
		return list;
	}
	
	@Override
	public PreAlertObject executeTask(PreAlertContext context)
			throws BusinessException {
		try {
			CdmPreAlertDataSource remsg = getResult(context);
			PreAlertObject pao = new PreAlertObject();
			pao.setReturnObj(remsg);
			if (remsg != null && remsg.getRecordValueArr().size() > 0) {
				pao.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
			} else {
				pao.setReturnType(PreAlertReturnType.RETURNNOTHING);
			}
			pao.setBusiCalculater(new ContractRePayBusiVarCalculate(remsg));
			return pao;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}
	
	protected CdmPreAlertDataSource getResult(PreAlertContext context)  throws BusinessException{
		LinkedHashMap<String, Object> keyMap = new LinkedHashMap<String, Object>();
		keyMap = context.getKeyMap();
		String contracttype = (String) getValueByKey("contracttype", keyMap);
		String[] contracttypeids = contracttype.split(",");
		List<String> contBillTypeList = new ArrayList<String>();
		for (String contracttypeid : contracttypeids) {
			if (contracttypeid != null){
				String contBilltypeCode = null;
				if (contracttypeid.startsWith("36")) {
					contBilltypeCode = contracttypeid;
				} else {
					contBilltypeCode = CDMBusConstant
							.getContBilltypeCodeByContTypeID(contracttypeid);
				}
				contBillTypeList.add(contBilltypeCode);
			}
		}
		String daysStr = (String) getValueByKey("days", keyMap);
		
		String pk_group = context.getGroupId();
		String[] pk_orgs = context.getPk_orgs();
		Integer days = Integer.valueOf(daysStr);
		UFDate currentDate = AppContext.getInstance().getBusiDate().asBegin();
		
		StringBuffer sb = new StringBuffer();
		// V63修改 如果展期按展期结束日期处理
		sb.append("select c.pk_org, c.contracttype, c.contractcode, c.pk_creditorg, c.creditcorpname,");
		sb.append("c.pk_debitorg, c.debitcorpname, c.pk_consignbank, c.pk_currtype, c.contractamount,");
		sb.append("b.repaydate, b.preamount, b.preinterest, c.pk_creditbank, b.extenddate,");
		sb.append("c.begindate,rc.ratename,bankaccsub.accnum,bankaccbas.def3 ");
		sb.append("from cdm_contract c left join (cdm_payrcpt p left join cdm_payrcpt_b b on p.pk_payrcpt=b.pk_payrcpt) on c.pk_contract=p.pk_contract ");
		sb.append("left join bd_bankdoc d on c.pk_consignbank=d.pk_bankdoc ");
		sb.append("left join fi_ratecode rc on c.pk_ratecode=rc.pk_ratecode ");
		sb.append("left join bd_bankaccsub bankaccsub on c.pk_debitunitacct=bankaccsub.pk_bankaccsub ");
		sb.append("left join bd_bankaccbas bankaccbas on bankaccsub.pk_bankaccbas=bankaccbas.pk_bankaccbas ");
		sb.append("where c.dr=0 ");
		sb.append("and p.dr=0 ");
		sb.append("and b.dr=0 ");
		sb.append("and c.contstatus='EXECUTING' ");
		sb.append("and p.vbillstatus=1 ");
		sb.append("and ").append(SqlUtil.buildSqlForIn("c." + ContractVO.PK_BILLTYPECODE, contBillTypeList.toArray(new String[0])));
		sb.append(" and c.").append(ContractVO.CONTSTATUS).append("='").append(ContStatusEnum.EXECUTING.value()).append("' ");
		if(days == null || days.compareTo(0) <= 0){
			return null;
		}
		UFDate endDate = currentDate.getDateAfter(days).asEnd();
		// V63修改 如果展期按展期结束日期处理
		sb.append(" and (( isnull(b.extenddate,'~') = '~'");
		sb.append(" and b.repaydate<='").append(endDate).append("' ");
		sb.append(" and b.repaydate>='").append(currentDate).append("') ");
		sb.append(" or ( isnull(b.extenddate,'~') != '~'");
		sb.append(" and b.extenddate<='").append(endDate).append("' ");
		sb.append(" and b.extenddate>='").append(currentDate).append("')) ");
		
		if(StringUtil.isNotNull(pk_group) && !pk_group.equals("GLOBLE00000000000000")){
			sb.append(" and c.pk_group='").append(pk_group).append("' ");
		}
		if (pk_orgs != null && pk_orgs.length > 0) {
			sb.append(" and ").append(SqlUtil.buildSqlForIn("c.pk_org", pk_orgs));
		}
		
		BaseDAO dao = new BaseDAO();
		List<LhContractRepayAlarmVO> result = (List<LhContractRepayAlarmVO>) dao.executeQuery(sb.toString(), new ResultSetProcessor() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				List<LhContractRepayAlarmVO> resultList = new ArrayList<LhContractRepayAlarmVO>();
				while(rs.next()){
					LhContractRepayAlarmVO vo = new LhContractRepayAlarmVO();
					vo.setPk_org(rs.getString(1));
					vo.setContracttype(rs.getString(2));
					vo.setContractcode(rs.getString(3));
					vo.setPk_creditorg(rs.getString(4));
					vo.setCreditcorpname(rs.getString(5));
					vo.setPk_debitorg(rs.getString(6));
					vo.setDebitcorpname(rs.getString(7));
					vo.setPk_consignbank(rs.getString(8));
					vo.setPk_currtype(rs.getString(9));
					vo.setContractamount(rs.getBigDecimal(10) == null || (new UFDouble(rs.getBigDecimal(10))).equals(new UFDouble(0)) ? "" : rs.getObject(10).toString());
					// V63修改 如果展期按展期结束日期处理
					String repaydate = null;
					if (rs.getString(15) != null) {
						repaydate = new UFDate(rs.getString(15)).toStdString();
					} else if (rs.getString(11) != null) {
						repaydate = new UFDate(rs.getString(11)).toStdString();
					} else {
						repaydate = "";
					}
					vo.setRepaydate(repaydate);
					vo.setPreamount(rs.getBigDecimal(12) == null || (new UFDouble(rs.getBigDecimal(12))).equals(new UFDouble(0)) ? "" : rs.getObject(12).toString());
					vo.setPreinterest(rs.getBigDecimal(13) == null || (new UFDouble(rs.getBigDecimal(13))).equals(new UFDouble(0)) ? "" : rs.getObject(13).toString());
					vo.setPk_creditbank(rs.getString(14));
					vo.setAlarmcauseList(getFinishMessageList(new UFDate(rs.getString(11))));
					vo.setCstartdate(rs.getString(16));
					vo.setRatecode(rs.getString(17));
					vo.setAccountnum(rs.getString(18));
					vo.setPhoneno((rs.getString(19) == null || "~"
							.equals(rs.getString(19)))?"":rs.getString(19));
					resultList.add(vo);

				}
				return resultList;
			}
		});
		
		ContRepayAlarmSendMes postSrv=new ContRepayAlarmSendMes();
		postSrv.sendAlarmMsgToMes(result);
		
		Map<String, List<LhContractRepayAlarmVO>> map = new HashMap<String, List<LhContractRepayAlarmVO>>();
		for(LhContractRepayAlarmVO vo: result){
			if(map.containsKey(vo.getPk_org())){
				map.get(vo.getPk_org()).add(vo);
			}else{
				List<LhContractRepayAlarmVO> list = new ArrayList<LhContractRepayAlarmVO>();
				list.add(vo);
				map.put(vo.getPk_org(), list);
			}
		}
		
		if(map == null || map.isEmpty()){
			return null;
		}
		List<Map<String, Object>> recordValueArr = new ArrayList<Map<String,Object>>();
		Iterator<String> keyIt = map.keySet().iterator();
		List<Object> bodysumaryList = new ArrayList<Object>();
		while(keyIt.hasNext()){
			String key = keyIt.next();
			String[] keyRow = new String[12];
			keyRow[0] = key;
			List<LhContractRepayAlarmVO> valueList = map.get(key);
			//分组织插入（名称）
//			Map<String, Object> recordValueOrg = new HashMap<String, Object>();
//			IBDData orgbd = GeneralAccessorFactory.getAccessor(IOrgMetaDataIDConst.ORG).getDocByPk(key);
//			recordValueOrg.put("pk_org", orgbd.getName());
//			recordValueArr.add(recordValueOrg);
			//分组织插入（内容）
			for(LhContractRepayAlarmVO vo: valueList){
				try {
					Map<String, Object> recordValueHM = new HashMap<String, Object>();
					
					IBDData orgbd = GeneralAccessorFactory.getAccessor(IOrgMetaDataIDConst.ORG).getDocByPk(key);
					recordValueHM.put("pk_org", orgbd.getName());
					
					Integer digit = TMCurrencyUtil.getCurrtypeDigit(vo.getPk_currtype());
					String digitStr = "";
					for(int i=0;i<digit;i++){
						digitStr += "0";
					}
					DecimalFormat df = new DecimalFormat("#,##0." + digitStr);
					recordValueHM.put("contracttype", MDEnum.valueOf(ContractTypeEnum.class, vo.getContracttype()));
					recordValueHM.put("contractcode", vo.getContractcode());
					
					//贷款单位
					IBDData creditorgbd;
					if(vo.getContracttype().equals(ContractTypeEnum.BANKCREDIT.value())){
						creditorgbd = GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.BANKDOC).getDocByPk(vo.getPk_creditbank());
					}else if(vo.getContracttype().equals(ContractTypeEnum.CONSIGNDEBIT.value())){
						creditorgbd = GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.CUSTSUPPLIER).getDocByPk(vo.getPk_creditorg());
					}else{
						creditorgbd = GeneralAccessorFactory.getAccessor(IOrgMetaDataIDConst.ORG).getDocByPk(vo.getPk_creditorg());
					}
					if(creditorgbd != null){
						recordValueHM.put("creditcorpname", creditorgbd.getName());
					}else{
						recordValueHM.put("creditcorpname", vo.getCreditcorpname());
					}
					
					//借款单位
					IBDData debitorgbd;
					if(vo.getContracttype().equals(ContractTypeEnum.CONSIGNCREDIT.value())){
						debitorgbd = GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.CUSTSUPPLIER).getDocByPk(vo.getPk_debitorg());
					}else{
						debitorgbd = GeneralAccessorFactory.getAccessor(IOrgMetaDataIDConst.ORG).getDocByPk(vo.getPk_debitorg());
					}
					if(debitorgbd != null){
						recordValueHM.put("debitcorpname", debitorgbd.getName());
					}else{
						recordValueHM.put("debitcorpname", vo.getDebitcorpname());
					}
					
					//银行
					IBDData bankbd = GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.BANKDOC).getDocByPk(vo.getPk_consignbank());
					if(bankbd != null){
						recordValueHM.put("pk_consignbank", bankbd.getName());
					}else{
						recordValueHM.put("pk_consignbank", "");
					}
					
					//币种
					IBDData currtypebd = GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.CURRTYPE).getDocByPk(vo.getPk_currtype());
					recordValueHM.put("pk_currtype", currtypebd.getName());
					
					recordValueHM.put("contractamount", (StringUtil.isNull(vo.getContractamount()) ? "" : df.format(new BigDecimal(vo.getContractamount()))));
					recordValueHM.put("repaydate", vo.getRepaydate());
					recordValueHM.put("preamount", (StringUtil.isNull(vo.getPreamount()) ? "" : df.format(new BigDecimal(vo.getPreamount()))));
					recordValueHM.put("preinterest", (StringUtil.isNull(vo.getPreinterest()) ? "" : df.format(new BigDecimal(vo.getPreinterest()))));
					recordValueHM.put("alarmcause", vo.getAlarmcauseList());
					List<Object> h_list = new ArrayList<Object>();
					h_list.add(new CDMLangResVO("3615pub_0", "03615pub-0225"));
					h_list.add(Integer.toString(days));
					h_list.add(new CDMLangResVO("3615pub_0", "03615pub-0214"));
					recordValueHM.put("h_alarmcause", h_list);
					
					List<Object> t_list = new ArrayList<Object>();
					t_list.add(new CDMLangResVO("3615pub_0", "03615pub-0219"));/*@res "业务日期："*/
					t_list.add(AppContext.getInstance().getBusiDate().toStdString());
					recordValueHM.put("t_busidate", t_list);

					recordValueArr.add(recordValueHM);
					
					//移动预警处理
					List<Object> summaryValue = new ArrayList<Object>();
					summaryValue.add(new CDMLangResVO("common","UC000-0001133"));
					summaryValue.add(":");
					summaryValue.add(vo.getContractcode());
					summaryValue.add(" ");
					summaryValue.add(vo.getAlarmcauseList());
					bodysumaryList.add(summaryValue);
				} catch (BusinessException e) {
					throw new BusinessRuntimeException(e.getMessage(), e);
				}
			}
		}
		CdmPreAlertDataSource ds = new CdmPreAlertDataSource();
		ds.setRecordValueArr(recordValueArr);
		
		/*
		 * 移动预警处理
		 */
		
		//表头摘要
		List<Object> headerSummary = new ArrayList<Object>();
		headerSummary.add(new CDMLangResVO("3615pub_0", "03615pub-0224"));/*@res "合同还本、付息到期预警"*/
		headerSummary.add(new CDMLangResVO("3615pub_0", "03615pub-0225"));/*@res "报警原因（1）距还本付息天数："*/
		headerSummary.add(Integer.toString(days));
		headerSummary.add(new CDMLangResVO("3615pub_0", "03615pub-0214"));/*@res "天"*/
		ds.setHeaderSummary(headerSummary);
		
		//表头描述
		Map<Object, Object> headerDescription = new HashMap<Object, Object>();
		headerDescription.put("h_alarmcause", new CDMLangResVO("3615pub_0","03615pub-0212")/*@res "预警原因"*/);
		ds.setHeaderDescription(headerDescription);
		
		//表体摘要
		ds.setBodySummaries(bodysumaryList.toArray(new Object[0]));
		
		//表体描述
		Map<String, Object> bodyDescription = new HashMap<String, Object>();
		bodyDescription.put("pk_org", new CDMLangResVO("3615pub_0","03615pub-0210")/*@res "财务组织"*/);
		bodyDescription.put("contracttype", new CDMLangResVO("common","UC000-0001128")/*@res "合同类型"*/);
		bodyDescription.put("contractcode", new CDMLangResVO("common","UC000-0001133")/*@res "合同编号"*/);
		bodyDescription.put("creditcorpname", new CDMLangResVO("3615pub_0","03615pub-0009")/*@res "贷款单位"*/);
		bodyDescription.put("debitcorpname", new CDMLangResVO("3615pub_0","03615pub-0002")/*@res "借款单位"*/);
		bodyDescription.put("pk_consignbank", new CDMLangResVO("3615pub_0","03615pub-0211")/*@res "受托单位"*/);
		bodyDescription.put("pk_currtype", new CDMLangResVO("common","UC000-0001110")/*@res "合同币种"*/);
		bodyDescription.put("contractamount", new CDMLangResVO("3615pub_0","03615pub-0020")/*@res "合同金额"*/);
		bodyDescription.put("repaydate", new CDMLangResVO("3615pub_0","03615pub-0187")/*@res "还款日期"*/);
		bodyDescription.put("preamount", new CDMLangResVO("3615pub_0","03615pub-0221")/*@res "预计还本金额"*/);
		bodyDescription.put("preinterest", new CDMLangResVO("3615pub_0","03615pub-0222")/*@res "预计付息金额"*/);
		bodyDescription.put("alarmcause", new CDMLangResVO("3615pub_0","03615pub-0212")/*@res "预警原因"*/);
		ds.setBodyDescription(bodyDescription);
		return ds;
	}
	
	class ContractRePayBusiVarCalculate implements IBusiCalculater{

		IDataSource dataSource = null;
		private ContractRePayBusiVarCalculate(IDataSource dataSource){
			super();
			this.dataSource = dataSource;
		}
		@Override
		public String calculateValue(String express) {
			if (this.dataSource == null){
				return null;
			}
			String[] valuesByExpress = dataSource.getItemValuesByExpress(express);
			StringBuffer stringBuffer = new StringBuffer();
			for (int i=0; i<valuesByExpress.length; i++){
				if (valuesByExpress[i] != null){
					stringBuffer.append(valuesByExpress[i]);	
					stringBuffer.append("/");
				}
			}
			return stringBuffer.toString();
		}
		
	}
	
//	@Override
//	public PreAlertObject executeTask(PreAlertContext context)
//			throws BusinessException {
//
//		PreAlertObject pao = new PreAlertObject();
//		LinkedHashMap<String, Object> keyMap = new LinkedHashMap<String, Object>();
//		keyMap = context.getKeyMap();
//		String daysStr = (String) getValueByKey("days", keyMap);
//		Integer days = Integer.valueOf(daysStr);
//		String[][] datas = getData(context);
//		if(datas == null){
//			return null;
//		}
//		LhCDMAlertMessageFormat remsg = new LhCDMAlertMessageFormat();
//		remsg.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
//				"3615pub_0", "03615pub-0224")/* @res "合同还本、付息到期预警" */);
//		remsg.setTop(new String[] { nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
//				.getStrByID("3615pub_0", "03615pub-0225")/*
//														 * @res
//														 * "报警原因（1）距还本付息天数："
//														 */
//				+ days
//				+ nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
//						"3615pub_0", "03615pub-0214")/* @res "天" */});
//		remsg.setBodyFields(getBodyFields());
//		remsg.setBottom(new String[] { nc.vo.ml.NCLangRes4VoTransl
//				.getNCLangRes().getStrByID("3615pub_0", "03615pub-0219")/*
//																		 * @res
//																		 * "业务日期："
//																		 */
//				+ AppContext.getInstance().getBusiDate().toStdString() });
//		remsg.setBodyValue(datas);
//		pao.setMsgTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
//				"3615pub_0", "03615pub-0224")/* @res "合同还本、付息到期预警" */);
//		pao.setReturnObj(remsg);
//		pao.setReturnType(PreAlertReturnType.RETURNFORMATMSG);
//
//		return pao;
//	}

//	private Object getValueByKey(String key, HashMap<String, Object> map)
//			throws BusinessException {
//		Object objdata = map.get(key);
//		if (objdata != null) {
//			Object temp = objdata.toString().trim();
//			if (StringUtil.isNull(temp) || temp.equals("null"))
//				objdata = null;
//		}
//		return objdata;
//	}
//
//	public String[] getBodyFields() {
//		return bodyFields;
//	}
//
//	public void setBodyFields(String[] bodyFields) {
//		this.bodyFields = bodyFields;
//	}
//
//	private String[] bodyFields = new String[] {
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0210")/* @res "财务组织" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
//					"UC000-0001128")/* @res "合同类型" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
//					"UC000-0001133")/* @res "合同编号" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0009")/* @res "贷款单位" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0002")/* @res "借款单位" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0211")/* @res "受托单位" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
//					"UC000-0001110")/* @res "合同币种" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0020")/* @res "合同金额" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0187")/* @res "还款日期" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0221")/* @res "预计还本金额" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0222")/* @res "预计付息金额" */,
//			nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0",
//					"03615pub-0212") /* @res "预警原因" */};

	
	

}
