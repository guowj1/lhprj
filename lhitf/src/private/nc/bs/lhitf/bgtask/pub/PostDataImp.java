package nc.bs.lhitf.bgtask.pub;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.agent.XMLPostServiceImpl;
import nc.cmp.tools.StringUtil;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.bd.account.IAccountQryService;
import nc.itf.org.IAccountingBookQryService;
import nc.itf.org.IOrgConst;
import nc.uap.ws.log.NCLogger;
import nc.vo.bd.account.AccountVO;
import nc.vo.gl.pubvoucher.VoucherVO;
import nc.vo.gl.vouchertools.XML_VoucherTranslator;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.jcom.xml.XMLUtil;
import nc.vo.lhprj.lhpostlog.LhPostLogVO;
import nc.vo.org.AccountingBookVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.tmpub.util.SqlUtil;

import org.apache.xerces.dom.DocumentImpl;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uap.iweb.log.Logger;

public class PostDataImp {
	public void sendDataByStr(Object[] vos, String billtype)
			throws BusinessException {

		// 清理日志
		delPostLog(AppContext.getInstance().getServerTime().getDate());

		XMLPostServiceImpl srv = new XMLPostServiceImpl();
		String ip = "192.168.2.11:6565";// 正式服务器
		// String ip = "192.168.10.92:651";//测试服务器
		// String ip = "127.0.0.1:651";
		String srcaccountcode = "0001";// "01";
		// String srcgroupcode = "0";
		String aimaccountcode = "0002";// "02";
		String aimgroupcode = "0";
		String pk_org = "";
		String srcexsystemCode = "sys01";
		String aimexsystemCode = "sys02";

		String url = "http://" + ip + "/service/XChangeServlet?account="
				+ aimaccountcode + "&groupcode=" + aimgroupcode;

		// UFDateTime postTime = AppContext.getInstance().getServerTime();

		try {
			int iGroupSize = 20;// 每20条数据作为一组
			int iGroupNum = ((new UFDouble(vos.length)).div(new UFDouble(
					iGroupSize))).setScale(0, UFDouble.ROUND_CEILING)
					.intValue();
			for (int i = 0; i < iGroupNum; i++) {
				int iEndIndex = i * iGroupSize + iGroupSize;
				if (i == iGroupNum - 1)
					iEndIndex = vos.length;

				Object[] vosToSend = Arrays.copyOfRange(vos, i * iGroupSize,
						iEndIndex);

				org.w3c.dom.Document doc = new DocumentImpl();
				if (billtype.equals("vouchergl")) {
					org.w3c.dom.Element root = doc.createElement("ufinterface");
					root.setAttribute("account", aimaccountcode);
					root.setAttribute("billtype", billtype);
					root.setAttribute("businessunitcode", "");
					root.setAttribute("filename", null);
					root.setAttribute("groupcode", aimgroupcode);
					root.setAttribute("isexchange", null);
					// root.setAttribute("orgcode",
					// orgVO==null?null:orgVO.getCode());
					root.setAttribute("receiver", "");
					root.setAttribute("replace", null);
					root.setAttribute("roottag", null);
					root.setAttribute("sender", aimexsystemCode);

					for (int j = 0; j < vosToSend.length; j++) {
						org.w3c.dom.Node voucherNode = XML_VoucherTranslator
								.getVoucherNode(doc, (VoucherVO) vosToSend[j]);
						root.appendChild(voucherNode);
					}
					doc.appendChild(root);
					// StringBuffer fileBuffer = new StringBuffer();
					// XML_VoucherTranslator.writeXMLFormatString(fileBuffer ,
					// doc, -2);
					// XML_VoucherTranslator.saveToFile(filepath, fileBuffer);
					handleGlVouchDocWithSpecial(doc);
				} else {
					doc = srv.postBill(vosToSend, srcaccountcode, billtype,
							pk_org, srcexsystemCode);
					Node itfNode = doc.getElementsByTagName("ufinterface")
							.item(0);
					Element element = (Element) itfNode;
					element.setAttribute("sender", aimexsystemCode);
					element.setAttribute("account", aimaccountcode);
					element.setAttribute("groupcode", aimgroupcode);

				}

				// doc.getElementsByTagName("primaryKey")[0].parentNode.removeChild(doc.getElementsByTagName("primaryKey")[0]);

				// NodeList nl2=doc.getElementsByTagName("pk_detail");
				//
				// //------------------删除节点------------------
				// for(int k=0;k<nl2.getLength();k++){
				// Node nodeDel=nl2.item(k);
				// nodeDel.getParentNode().removeChild(nodeDel);
				// }

				String strContent = toStringFromDoc(doc);
//				saveToFile("d:\\voucher"+String.valueOf(i)+".xml", new StringBuffer(strContent));
				Document resDoc = sentUrlByStr(strContent, url);

				LhPostLogVO[] resultVOs = handleResult(resDoc, billtype);

			}

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public String toStringFromDoc(Document document) {
		String result = null;

		if (document != null) {
			StringWriter strWtr = new StringWriter();
			StreamResult strResult = new StreamResult(strWtr);
			TransformerFactory tfac = TransformerFactory.newInstance();
			try {
				javax.xml.transform.Transformer t = tfac.newTransformer();
				t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
				// text
				t.setOutputProperty(
						"{http://xml.apache.org/xslt}indent-amount", "4");
				t.transform(new DOMSource(document.getDocumentElement()),
						strResult);
			} catch (Exception e) {
				System.err.println("XML.toString(Document): " + e);
			}
			result = strResult.getWriter().toString();
			try {
				strWtr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Document sentUrlByStr(String str, String url) throws Exception {
		URL realURL = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) realURL
				.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-type", "text/xml");
		connection.setRequestMethod("POST");

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
		out.close();
		InputStream inputStream = connection.getInputStream();
		Document resDoc = XMLUtil.getDocumentBuilder().parse(inputStream);
		return resDoc;
	}

	private LhPostLogVO[] handleResult(Document retDoc, String billtype)
			throws Exception {
		UFDateTime dtDate = AppContext.getInstance().getServerTime();
		String pk_group = AppContext.getInstance().getPkGroup();
		String pk_org = IOrgConst.GLOBEORG;

		ArrayList<LhPostLogVO> alResultVO = new ArrayList<LhPostLogVO>();
		NodeList retNodes = retDoc.getElementsByTagName("sendresult");
		Set<String> pk_bill_succ = new HashSet<String>();
		int iNodeLen = retNodes.getLength();

		for (int i = 0; i < iNodeLen; i++) {
			Element ele = (Element) retNodes.item(i);
			LhPostLogVO resultVO = new LhPostLogVO();
			String pk_bill = ele.getElementsByTagName("bdocid").item(0)
					.getFirstChild().getNodeValue();
			String resultcode = ele.getElementsByTagName("resultcode").item(0)
					.getFirstChild().getNodeValue();
			String resultdesp = ele.getElementsByTagName("resultdescription")
					.item(0).hasChildNodes() ? ele
					.getElementsByTagName("resultdescription").item(0)
					.getFirstChild().getNodeValue() : "";
			resultVO.setPk_voucher(pk_bill);
			resultVO.setPostdate(dtDate);
			resultVO.setSendresult(resultcode);
			resultVO.setResultdescription(resultdesp.length() > 2000 ? resultdesp
					.substring(0, 2000) : resultdesp);
			resultVO.setPk_group(pk_group);
			resultVO.setPk_org(pk_org);
			resultVO.setVouchtype(billtype);
			alResultVO.add(resultVO);

			if ("1".equals(resultcode)) {
				pk_bill_succ.add(pk_bill);
			}

		}

		BaseDAO baseDao = new BaseDAO("nc65");// 正式服务器数据源名称
		// BaseDAO baseDao = new BaseDAO("LHCSY1");//测试服务器数据源名称
		if (pk_bill_succ != null && pk_bill_succ.size() > 0) {
			// 更新订单表头自定义项13
			// StringBuilder updateStr = new StringBuilder();
			String[] pkbills = pk_bill_succ.toArray(new String[0]);

			String sqlUpdate = "";
			if ("customer".equals(billtype))
				sqlUpdate = "Update bd_customer set def2='"
						+ dtDate.toStdString() + "' where "
						+ SqlUtil.buildSqlForIn("pk_customer", pkbills);
			if ("supplier".equals(billtype))
				sqlUpdate = "Update bd_supplier set def2='"
						+ dtDate.toStdString() + "' where "
						+ SqlUtil.buildSqlForIn("pk_supplier", pkbills);
			if ("psndoc".equals(billtype))
				sqlUpdate = "Update bd_psndoc set def2='"
						+ dtDate.toStdString() + "' where "
						+ SqlUtil.buildSqlForIn("pk_psndoc", pkbills);
			if ("defdoc".equals(billtype))
				sqlUpdate = "Update bd_defdoc set def2='"
						+ dtDate.toStdString() + "' where "
						+ SqlUtil.buildSqlForIn("pk_defdoc", pkbills);
			if ("bankaccbas".equals(billtype))
				sqlUpdate = "Update bd_bankaccbas set def2='"
						+ dtDate.toStdString() + "' where "
						+ SqlUtil.buildSqlForIn("pk_bankaccbas", pkbills);
			if ("vouchergl".equals(billtype))
				sqlUpdate = "Update gl_voucher set free9='"
						+ dtDate.toStdString() + "' where "
						+ SqlUtil.buildSqlForIn("pk_voucher", pkbills);

			baseDao.executeUpdate(sqlUpdate);
		}
		if (alResultVO != null && alResultVO.size() > 0) {

			for (LhPostLogVO result : alResultVO) {
				baseDao.insertVO(result);
			}
			// baseDao.insertVOList(alResultVO);
			// baseDao.insertVOArray(alResultVO.toArray(new SyPostLogVO[0]));
		}

		return alResultVO.toArray(new LhPostLogVO[0]);

	}

	// 清理120天前的日志
	private void delPostLog(UFDate dtToday) {
		UFDate dtDelFinalDate = dtToday.getDateBefore(120);
		DataAccessUtils tool = new DataAccessUtils();
		SqlBuilder sqlBuilder = new SqlBuilder();
		sqlBuilder.append("delete from lh_lhpostlog where postdate ", "<",
				dtDelFinalDate.toStdString());
		try {
			tool.update(sqlBuilder.toString());
		} catch (Exception err) {
			Logger.error(err.getMessage());
		}

	}

	// 处理源系统导出的凭证xml
	private void handleGlVouchDocWithSpecial(Document doc)
			throws BusinessException {

		NodeList nlVoucherHead = doc.getElementsByTagName("voucher_head");
		for (int i = 0; i < nlVoucherHead.getLength(); i++) {
//			String pk_voucher=eleHead.getElementsByTagName("pk_voucher")
//					.item(0).getFirstChild().getNodeValue();
//			Element eleHead = (Element) nlVoucherHead.item(i);
//			eleHead.removeChild(eleHead.getElementsByTagName("pk_voucher")
//					.item(0));// 移去pk_voucher节点
			
			Element eleHead = (Element) nlVoucherHead.item(i);
			NodeList nlAccountingbook = eleHead
					.getElementsByTagName("pk_accountingbook");
			String accountingbookCode = "";
			if (nlAccountingbook.item(0).hasChildNodes()) {
				accountingbookCode = nlAccountingbook.item(0).getFirstChild()
						.getNodeValue();
			}
			String pk_voucher=eleHead.getElementsByTagName("pk_voucher")
					.item(0).getFirstChild().getNodeValue();
			Element elVoucher=(Element) nlVoucherHead.item(i).getParentNode();
			elVoucher.setAttribute("id", pk_voucher);
			eleHead.removeChild(eleHead.getElementsByTagName("pk_voucher")
					.item(0));// 移去pk_voucher节点
			NodeList nlaccasoaCode = eleHead.getElementsByTagName("pk_accasoa");
			if ("1001-0001".equals(accountingbookCode)) {
				for (int j = 0; j < nlaccasoaCode.getLength(); j++) {
					String accasoaCode = "";
					if (nlaccasoaCode.item(j).hasChildNodes()) {
						accasoaCode = nlaccasoaCode.item(j).getFirstChild()
								.getNodeValue();
						if (accasoaCode.startsWith("1403")) {
							Element itemEle = (Element) nlaccasoaCode.item(j)
									.getParentNode();// 获得父节点
							NodeList nlCheckType = itemEle
									.getElementsByTagName("pk_Checktype");// 获得pk_Checktype节点
							if (nlCheckType.getLength() > 0
									&& nlCheckType.item(0).hasChildNodes()) {
								if ("0001".equals(nlCheckType.item(0)
										.getFirstChild().getNodeValue())) {
									NodeList nlCheckValue = itemEle
											.getElementsByTagName("pk_Checkvalue");// 获得CheckValue节点
									if (nlCheckValue.getLength() > 0
											&& nlCheckValue.item(0)
													.hasChildNodes()) {
										nlCheckValue.item(0).getFirstChild()
												.setNodeValue("0399");
									}
								}
							}
						}
					}
				}
			}

			HashSet<String> hsaccasoaCode = new HashSet<String>();
			for (int j = 0; j < nlaccasoaCode.getLength(); j++) {
				String accasoaCode = "";
				if (nlaccasoaCode.item(j).hasChildNodes()) {
					accasoaCode = nlaccasoaCode.item(j).getFirstChild()
							.getNodeValue();
					hsaccasoaCode.add(accasoaCode);
				}
			}
			if (hsaccasoaCode != null && hsaccasoaCode.size() > 0) {

				// 获取科目表
				// AccountingBookVO[] accountingbookVO = NCLocator.getInstance()
				// .lookup(IAccountingBookQryService.class)
				// .queryVOsByPks(new String[] { accountingbookCode });
				BaseDAO baseDao = new BaseDAO();
				AccountingBookVO[] accountingbookVO = (AccountingBookVO[]) baseDao
						.retrieveByClause(
								AccountingBookVO.class,
								"code='" + accountingbookCode
										+ "' and dr=0 and enablestate=2")
						.toArray(new AccountingBookVO[0]);
				String pk_accchart = accountingbookVO[0].getPk_curraccchart();

				AccountVO[] accountVOs = NCLocator
						.getInstance()
						.lookup(IAccountQryService.class)
						.getAccountVOByCodes(
								hsaccasoaCode.toArray(new String[0]),
								pk_accchart);

				Map<String, AccountVO> mapAccountVO = CollectionUtils
						.hashVOArray(AccountVO.CODE, accountVOs);
				for (int k = 0; k < nlaccasoaCode.getLength(); k++) {
					Node nodeAccaso = nlaccasoaCode.item(k);
					if (nodeAccaso.hasChildNodes()) {
						String oppAccountCode = mapAccountVO.get(
								nodeAccaso.getFirstChild().getNodeValue())
								.getRemcode();
						if (!StringUtil.isEmptyWithTrim(oppAccountCode))
							nodeAccaso.getFirstChild().setNodeValue(
									oppAccountCode);
					}
				}
			}

			NodeList nlCheckType = eleHead.getElementsByTagName("pk_Checktype");
			for (int j = 0; j < nlCheckType.getLength(); j++) {
				if (nlCheckType.getLength() > 0
						&& nlCheckType.item(j).hasChildNodes()) {
					String checkTypeCode = nlCheckType.item(j).getFirstChild()
							.getNodeValue();
					if (checkCheckTypeNeedDel(checkTypeCode)) {
						nlCheckType
								.item(j)
								.getParentNode()
								.getParentNode()
								.removeChild(
										nlCheckType.item(j).getParentNode());
						j--;
					}
				}
			}

			// 解决删除辅助核算后只留下一个ass节点的问题

		}


	}

	private boolean checkCheckTypeNeedDel(String checkTypeCode) {
		boolean bResult = false;
		switch (checkTypeCode) {
		case "zdyx004":
			bResult = true;
			break;
		case "zdyx005":
			bResult = true;
			break;
		case "zdyx007":
			bResult = true;
			break;
		case "zdyx008":
			bResult = true;
			break;
		}
		return bResult;
	}

	private void saveToFile(String fileName, StringBuffer fileContent)
			throws Exception {
		OutputStreamWriter out = null;
		if (fileName != null && fileContent != null) {
			try {
				// nc.vo.pub.util.EnvironmentUtil.createDirectoryAsNeeded(fileName,
				// null);
				nc.vo.jcom.io.FileUtil.createDirectoryAsNeeded(fileName, null);
				out = new OutputStreamWriter(new FileOutputStream(fileName,
						false), "UTF-8");
				out.write(fileContent.toString());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null)
					out.close();
			}
		}
	}

}
