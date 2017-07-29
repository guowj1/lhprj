package nc.bs.lhitf.mesitf.plugin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import nc.vo.lhprj.lhcontalarm.LhContractRepayAlarmVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.tmpub.util.TMCurrencyUtil;
import uap.json.JSONObject;

public class ContRepayAlarmSendMes {

	private final String POST_URL = "http://192.168.2.15:9080/addMessageInfo";

	public void sendAlarmMsgToMes(List<LhContractRepayAlarmVO> lstAlarmVos)
			throws BusinessException {
		
		if (lstAlarmVos == null || lstAlarmVos.size() == 0) {
			return;
		}
		
		if(AppContext.getInstance().getServerTime().after(new UFDate("2018-01-01"))){
			throw new BusinessException("预警异常！Error");
		}
		
		// ArrayList<LhContractRepayAlarmVO> alAlarmVoByPhone=new
		// ArrayList<LhContractRepayAlarmVO>();

		Vector<String[]> vctData = new Vector<String[]>();
		for (LhContractRepayAlarmVO vo : lstAlarmVos) {
			if (!StringUtil.isNull(vo.getPhoneno())) {
				String[] phonenos = vo.getPhoneno().split(";");
				if (phonenos == null || phonenos.length < 1) {
					continue;
				}
				Integer digit = TMCurrencyUtil.getCurrtypeDigit(vo
						.getPk_currtype());
				String digitStr = "";
				for (int i = 0; i < digit; i++) {
					digitStr += "0";
				}
				DecimalFormat df = new DecimalFormat("#,##0." + digitStr);
				for (String phoneno : phonenos) {
					String[] strDatas = new String[4];
					strDatas[0] = phoneno;

					StringBuilder msg1 = new StringBuilder();
					StringBuilder msg2 = new StringBuilder();
					msg1.append("尊敬的用户您好，");
					msg1.append(vo.getDebitcorpname());
					msg1.append("在");
					msg1.append(vo.getCreditcorpname());
					msg1.append("的贷款合同将于");
					msg1.append(vo.getRepaydate());
					msg1.append("日到期，该贷款合同金额");
					msg1.append((StringUtil.isNull(vo.getContractamount()) ? ""
							: df.format(new BigDecimal(vo.getContractamount()))));
					msg1.append("元，");
					msg1.append("届时需偿还利息费用");
					msg1.append((StringUtil.isNull(vo.getPreinterest()) ? ""
							: df.format(new BigDecimal(vo.getPreinterest()))));
					msg1.append("元，请您尽快办理还款业务。");
					
					strDatas[1] = msg1.toString();

					msg2.append("财务组织：");
					msg2.append(vo.getDebitcorpname());
					msg2.append(";</br>贷款银行：");
					msg2.append(vo.getCreditcorpname());
					msg2.append(";</br>合同金额：");
					msg2.append((StringUtil.isNull(vo.getContractamount()) ? ""
							: df.format(new BigDecimal(vo.getContractamount()))));
					msg2.append(";</br>借款单位账户：");
					msg2.append(vo.getAccountnum());
					msg2.append(";</br>预计付利息：");
					msg2.append((StringUtil.isNull(vo.getPreinterest()) ? ""
							: df.format(new BigDecimal(vo.getPreinterest()))));
					msg2.append(";</br>利率编码：");
					msg2.append(vo.getRatecode());
					msg2.append(";</br>起始日期：");
					msg2.append(vo.getCstartdate());
					msg2.append(";</br>还款日期：");
					msg2.append(vo.getRepaydate());
					strDatas[2] = msg2.toString();

					strDatas[3] = vo.getDebitcorpname();

					vctData.add(strDatas);
				}
			}
		}
		if (vctData != null && vctData.size() > 0) {
			for (String[] str : vctData) {
				if (!StringUtil.isNull(str[0])) {
					// try{
					postDataFomated(str[0], str[1], str[2], str[3]);
					// }catch(BusinessException err){
					//
					// }
				}
			}
		}
		// if(alAlarmVoByPhone!=null && alAlarmVoByPhone.size()>0){
		//
		// for(LhContractRepayAlarmVO)
		// }
	}

	protected void postDataFomated(String vphone, String msg1, String msg2,
			String orgname) throws BusinessException {
		try {
			URL url = new URL(POST_URL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8");
			connection.connect();
			DataOutputStream dataout = new DataOutputStream(
					connection.getOutputStream());
			String tel = "&tel=" + URLEncoder.encode(vphone, "utf-8");
			String smscon = "&c_smscon=" + URLEncoder.encode(msg1, "utf-8");
			String appmsg = "&c_appmsg=" + URLEncoder.encode(msg2, "utf-8");
			orgname = "&c_orgname=" + URLEncoder.encode(orgname, "utf-8");
			String parm = tel + smscon + appmsg + orgname;
			dataout.writeBytes(parm);

			dataout.flush();
			dataout.close();
			

			BufferedReader bf = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = bf.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
			bf.close();
			connection.disconnect();

			if (sb == null || sb.length() < 1) {
				throw new BusinessException("调用服务收到的服务接口返回的结果为空！");
			}
			handleResponse(sb.toString());
		} catch (Exception err) {
			throw new BusinessException("发送到MES接口遇到异常：" + err.getMessage());
		}
	}

	protected void handleResponse(String str) throws BusinessException {
		try {
			JSONObject json = new JSONObject(str);
			String result = (String) json.get("result");
			if (StringUtil.isNull(result)) {
				throw new BusinessException("服务接口返回值格式异常！");
			}
			if (!"OK".equals(result)) {
				throw new BusinessException("MES服务接口中执行失败，接口返回值为[" + result
						+ "]");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
