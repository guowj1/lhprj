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
			throw new BusinessException("Ԥ���쳣��Error");
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
					msg1.append("�𾴵��û����ã�");
					msg1.append(vo.getDebitcorpname());
					msg1.append("��");
					msg1.append(vo.getCreditcorpname());
					msg1.append("�Ĵ����ͬ����");
					msg1.append(vo.getRepaydate());
					msg1.append("�յ��ڣ��ô����ͬ���");
					msg1.append((StringUtil.isNull(vo.getContractamount()) ? ""
							: df.format(new BigDecimal(vo.getContractamount()))));
					msg1.append("Ԫ��");
					msg1.append("��ʱ�賥����Ϣ����");
					msg1.append((StringUtil.isNull(vo.getPreinterest()) ? ""
							: df.format(new BigDecimal(vo.getPreinterest()))));
					msg1.append("Ԫ���������������ҵ��");
					
					strDatas[1] = msg1.toString();

					msg2.append("������֯��");
					msg2.append(vo.getDebitcorpname());
					msg2.append(";</br>�������У�");
					msg2.append(vo.getCreditcorpname());
					msg2.append(";</br>��ͬ��");
					msg2.append((StringUtil.isNull(vo.getContractamount()) ? ""
							: df.format(new BigDecimal(vo.getContractamount()))));
					msg2.append(";</br>��λ�˻���");
					msg2.append(vo.getAccountnum());
					msg2.append(";</br>Ԥ�Ƹ���Ϣ��");
					msg2.append((StringUtil.isNull(vo.getPreinterest()) ? ""
							: df.format(new BigDecimal(vo.getPreinterest()))));
					msg2.append(";</br>���ʱ��룺");
					msg2.append(vo.getRatecode());
					msg2.append(";</br>��ʼ���ڣ�");
					msg2.append(vo.getCstartdate());
					msg2.append(";</br>�������ڣ�");
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
				throw new BusinessException("���÷����յ��ķ���ӿڷ��صĽ��Ϊ�գ�");
			}
			handleResponse(sb.toString());
		} catch (Exception err) {
			throw new BusinessException("���͵�MES�ӿ������쳣��" + err.getMessage());
		}
	}

	protected void handleResponse(String str) throws BusinessException {
		try {
			JSONObject json = new JSONObject(str);
			String result = (String) json.get("result");
			if (StringUtil.isNull(result)) {
				throw new BusinessException("����ӿڷ���ֵ��ʽ�쳣��");
			}
			if (!"OK".equals(result)) {
				throw new BusinessException("MES����ӿ���ִ��ʧ�ܣ��ӿڷ���ֵΪ[" + result
						+ "]");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
