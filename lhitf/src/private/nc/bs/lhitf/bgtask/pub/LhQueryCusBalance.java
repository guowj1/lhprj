package nc.bs.lhitf.bgtask.pub;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.jcom.xml.XMLUtil;
import nc.vo.lhprj.lhcustomer.LhCustomerVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class LhQueryCusBalance {
	private final String POST_URL = "http://192.168.2.9/ecp/ncInterfaceController/receiveRequest";
//	private final String POST_URL="http://192.168.10.209:8080/ecp/ncInterfaceController/receiveRequest";

	public Map<String, UFDouble> getBalance(LhCustomerVO[] custVos)
			throws BusinessException {
		Map<String, UFDouble> mapBanlanceByCust = new HashMap<String, UFDouble>();
		try {
			if (custVos != null && custVos.length > 0) {
				String dataToPost = getDataToPost(custVos);
				Document resDoc = sentUrlByStr(dataToPost);

				// URL url = new URL(POST_URL);
				// HttpURLConnection connection = (HttpURLConnection) url
				// .openConnection();
				// connection.setDoOutput(true);
				// connection.setDoInput(true);
				// connection.setRequestMethod("POST");
				// connection.setUseCaches(false);
				// connection.setInstanceFollowRedirects(true);
				// connection.setRequestProperty("Content-Type",
				// "application/x-www-form-urlencoded;charset=utf-8");
				// connection.connect();
				// DataOutputStream dataout = new DataOutputStream(
				// connection.getOutputStream());
				// String paraCustSubCode = "&custsubcode="
				// + URLEncoder.encode(custsubcode, "utf-8");
				// String paraCustCode = "&custcode="
				// + URLEncoder.encode(custcode, "utf-8");
				// String parm = paraCustSubCode + paraCustCode;
				// dataout.writeBytes(parm);
				//
				// dataout.flush();
				// dataout.close();
				//
				// BufferedReader bf = new BufferedReader(new InputStreamReader(
				// connection.getInputStream(), "UTF-8"));
				// String line;
				// StringBuilder sb = new StringBuilder();
				// while ((line = bf.readLine()) != null) {
				// sb.append(line)
				// .append(System.getProperty("line.separator"));
				// }
				// bf.close();
				// connection.disconnect();
				//
				// if (sb == null || sb.length() < 1) {
				// throw new BusinessException("调用电商余额查询服务收到的返回的结果为空！");
				// }
				//
				// Document resDoc = XMLUtil.getDocumentBuilder().parse(
				// sb.toString());
				NodeList retNodes = resDoc.getElementsByTagName("bill");
				int iNodeLen = retNodes.getLength();
				for (int i = 0; i < iNodeLen; i++) {
					Element ele = (Element) retNodes.item(i);
					// LhPostLogVO resultVO = new LhPostLogVO();
					String pk_id = ele.getElementsByTagName("pk_id").item(0)
							.getFirstChild().getNodeValue();
//					String retCustSubCode = ele
//							.getElementsByTagName("custsubcode").item(0)
//							.getFirstChild().getNodeValue();
					String retCustCode = ele.getElementsByTagName("custcode")
							.item(0).getFirstChild().getNodeValue();
					String retMoney = ele.getElementsByTagName("nmoney")
							.item(0).hasChildNodes() ? ele
							.getElementsByTagName("nmoney").item(0)
							.getFirstChild().getNodeValue() : "";
					if (!StringUtil.isEmptyWithTrim(retMoney)) {
						mapBanlanceByCust.put(retCustCode, new UFDouble(
								retMoney));
					}
				}
			}
			// handleResponse(sb.toString());
		} catch (Exception err) {
			throw new BusinessException("调用电商余额查询服务接口遇到异常：" + err.getMessage());
		}
		return mapBanlanceByCust;

	}

	protected String getDataToPost(LhCustomerVO[] custVos) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		strBuild.append("<ufinterface account=\"01\" billtype=\"contractBalance\" filename=\"\" groupcode=\"\" isexchange=\"Y\" replace=\"Y\" roottag=\"\" sender=\"sys_ds\">");
		for (LhCustomerVO vo : custVos) {
			strBuild.append("<bill id=\"" + vo.getPk_id() + "\">");
			strBuild.append("<billhead>");
			strBuild.append("<pk_id>" + vo.getPk_id() + "</pk_id>");
			strBuild.append("<custsubcode></custsubcode>");
			strBuild.append("<custcode>" + vo.getCustcode()
					+ "</custcode>");
			strBuild.append("</billhead>");
			strBuild.append("</bill>");
		}
		strBuild.append("</ufinterface>");

		return strBuild.toString();
	}

	public Document sentUrlByStr(String str) throws Exception {
		URL url = new URL(POST_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/xml");
		connection.connect();
		BufferedOutputStream out = new BufferedOutputStream(
				connection.getOutputStream());
		BufferedInputStream input = new BufferedInputStream(
				new ByteArrayInputStream(str.getBytes("UTF-8")));

		int length;
		byte[] buffer = new byte[1000];
		while ((length = input.read(buffer, 0, 1000)) != -1) {
			out.write(buffer, 0, length);
		}
		input.close();
		out.flush();
		out.close();
		InputStream inputStream = connection.getInputStream();
		BufferedReader bf = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = bf.readLine()) != null) {
			sb.append(line).append(System.getProperty("line.separator"));
		}
		bf.close();
		connection.disconnect();

		if (sb == null || sb.length() < 1) {
			throw new BusinessException("调用电商服务收到的服务接口返回的结果为空！");
		}

		Document resDoc = XMLUtil.getDocumentBuilder().parse(
				new InputSource(new ByteArrayInputStream(sb.toString()
						.getBytes("utf-8"))));
		return resDoc;
	}
}
