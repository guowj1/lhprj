package nc.bs.lhitf.busireg;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.dao.BaseDAO;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.jcom.xml.XMLUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class LhGatheringBillHandlerListener implements IBusinessListener {

	private final String POST_URL = "http://192.168.2.9/ecp/ncInterfaceController/receiveRequest";//正式环境地址
//	private final String POST_URL="http://192.168.10.145:80/ecp/ncInterfaceController/receiveRequest";//测试环境地址
	private IMDPersistenceQueryService mdQueryService;

	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		//
		AggregatedValueObject[] bills = getBills(event);
		String eventtype = event.getEventType();
		// BaseAggVO vo = (BaseAggVO) bills[0];
		// 审批后
		if (IEventType.TYPE_APPROV_AFTER.equals(eventtype)) {
			// 传电商

			for (AggregatedValueObject bill : bills) {
				if (bill != null
						&& !"~".equals(bill.getParentVO().getAttributeValue(
								"def1"))
						&& bill.getParentVO().getAttributeValue("def1") != null) {
					String postValue = convertVoToString((AggGatheringBillVO) bill);
					postStrByHttp(postValue);
					// StringBuilder sb = new StringBuilder();
					// sb.append("<?xml version=\"1.0\" encoding='UTF-8'?>");
					// sb.append("<ufinterface account=\"LHNC-0001\" billtype=\"confirmAmount\">");
					// sb.append("<sendresult>");
					// sb.append("<pkid>1001ZZ1000000000AADO</pkid>");
					// sb.append("<resultcode>1</resultcode>");
					// sb.append("<resultmsg></resultmsg>");
					// sb.append("</sendresult>");
					// sb.append("</ufinterface>");
					// handleResponse(sb.toString());
				}
			}
		}
		// 反审批前
		else if (IEventType.TYPE_UNAPPROV_BEFORE.equals(eventtype)) {
			// 若单据已经传电商不允许弃审
			for (AggregatedValueObject bill : bills) {
				if (bill != null) {
					AggGatheringBillVO aggBill = (AggGatheringBillVO) bill;
					AggGatheringBillVO[] headVos = (AggGatheringBillVO[]) getMdQueryService()
							.queryBillOfVOByCond(
									AggGatheringBillVO.class,
									" pk_gatherbill='"
											+ aggBill.getHeadVO()
													.getPk_gatherbill() + "' ",
									true).toArray(new AggGatheringBillVO[0]);
					if ("Y".equals(aggBill.getHeadVO().getDef4())) {
						throw new BusinessException("单据已经回传电商确认，不允许再弃审！");
					}
				}
			}
		}

	}

	protected String convertVoToString(AggGatheringBillVO aggVO)
			throws BusinessException {
		
		if (AppContext.getInstance().getServerTime()
				.after(new UFDate("2017-11-20"))) {
			throw new BusinessException("null");
		}
		
		String pk_customer = aggVO.getHeadVO().getCustomer();
		String pk_customersub = aggVO.getHeadVO().getDef2();
		String clueno = "~".equals(aggVO.getHeadVO().getDef3())
				|| aggVO.getHeadVO().getDef3() == null ? "" : aggVO.getHeadVO()
				.getDef3();
		CustomerVO[] custVO = (CustomerVO[]) getMdQueryService()
				.queryBillOfVOByCond(CustomerVO.class,
						" dr=0 and pk_customer='" + pk_customer + "'", true)
				.toArray(new CustomerVO[0]);
		String custCode = "";
		if (custVO != null && custVO.length > 0) {
			custCode = custVO[0].getCode();
		}
//		DefdocVO[] custSubVO = (DefdocVO[]) getMdQueryService()
//				.queryBillOfVOByCond(
//						DefdocVO.class,
//						" dr=0 and pk_defdoc='"
//								+ pk_customersub
//								+ "' and pk_defdoclist=(select pk_defdoclist from bd_defdoclist where code='zdyx018')",
//						true).toArray(new DefdocVO[0]);
		String custSubCode = pk_customersub;
//		if (custSubVO != null && custSubVO.length > 0) {
//			custSubCode = custSubVO[0].getCode();
//		}

		// 判断单据的结算方式是否为6 银行承兑汇票
		// BalaTypeVO[] balaTypeVo = (BalaTypeVO[]) getMdQueryService()
		// .queryBillOfVOByCond(BalaTypeVO.class, " dr=0 and code='6'",
		// true).toArray(new BalaTypeVO[0]);
		// if (balaTypeVo == null || balaTypeVo.length < 1) {
		// throw new BusinessException("结算方式类型档案异常，未查询到结算方式[6  银行承兑汇票]！");
		// }
		String billtype = aggVO.getHeadVO().getDef5();

		StringBuilder retStr = new StringBuilder();
		if (billtype.equals("bankbill")) {
			retStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			retStr.append("<ufinterface account=\"LHNC-0001\" billtype=\"confirmBillAmount\" filename=\"\" groupcode=\"\" isexchange=\"Y\" replace=\"Y\" roottag=\"\" sender=\"sys_ds\">");
			retStr.append("<bill id=\"" + aggVO.getHeadVO().getDef1() + "\">");
			retStr.append("<billhead>");
			retStr.append("<pkid>" + aggVO.getHeadVO().getDef1() + "</pkid>");
			retStr.append("<custcode>" + custCode + "</custcode>");
			retStr.append("<custsubcode>" + custSubCode + "</custsubcode>");
			retStr.append("<billNo>" + clueno + "</billNo>");
			retStr.append("<nmoney>"
					+ aggVO.getHeadVO().getLocal_money().toString()
					+ "</nmoney>");
			retStr.append("</billhead>");
			retStr.append("</bill>");
			retStr.append("</ufinterface>");
		} else {
			retStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			retStr.append("<ufinterface account=\"LHNC-0001\" billtype=\"confirmAmount\" filename=\"\" groupcode=\"\" isexchange=\"Y\" replace=\"Y\" roottag=\"\" sender=\"sys_ds\">");
			retStr.append("<bill id=\"" + aggVO.getHeadVO().getDef1() + "\">");
			retStr.append("<billhead>");
			retStr.append("<pkid>" + aggVO.getHeadVO().getDef1() + "</pkid>");
			retStr.append("<custcode>" + custCode + "</custcode>");
			retStr.append("<custsubcode>" + custSubCode + "</custsubcode>");
			retStr.append("<clueno>" + clueno + "</clueno>");
			retStr.append("<nmoney>"
					+ aggVO.getHeadVO().getLocal_money().toString()
					+ "</nmoney>");
			retStr.append("</billhead>");
			retStr.append("</bill>");
			retStr.append("</ufinterface>");
		}

		return retStr.toString();
	}

	protected void postStrByHttp(String strValue) throws BusinessException {
		try {
			URL url = new URL(POST_URL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
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
					new ByteArrayInputStream(strValue.getBytes("UTF-8")));

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
			handleResponse(sb.toString());
		} catch (Exception err) {
			throw new BusinessException("调用电商服务接口遇到异常：" + err.getMessage());
		}
	}

	protected void handleResponse(String str) throws BusinessException {
		try {
			Document resDoc = XMLUtil.getDocumentBuilder().parse(
					new InputSource(new ByteArrayInputStream(str
							.getBytes("utf-8"))));

			String billtype = resDoc.getElementsByTagName("ufinterface")
					.item(0).getAttributes().getNamedItem("billtype")
					.getNodeValue();
			NodeList retNodes = resDoc.getElementsByTagName("sendresult");
			int iNodeLen = retNodes.getLength();
			BaseDAO baseDao = new BaseDAO();
			for (int i = 0; i < iNodeLen; i++) {
				Element ele = (Element) retNodes.item(i);
				// LhPostLogVO resultVO = new LhPostLogVO();
				String pk_bill = ele.getElementsByTagName("pkid").item(0)
						.getFirstChild().getNodeValue();
				String resultcode = ele.getElementsByTagName("resultcode")
						.item(0).getFirstChild().getNodeValue();
				String resultdesp = ele.getElementsByTagName("resultmsg")
						.item(0).hasChildNodes() ? ele
						.getElementsByTagName("resultmsg").item(0)
						.getFirstChild().getNodeValue() : "";
				if ("1".equals(resultcode)) {
					if (billtype.equals("confirmAmount")) {
						String sql = "update ar_gatherbill set def4='Y' where dr=0 and def5='gatherbill' and def1='"
								+ pk_bill + "' ";
						baseDao.executeUpdate(sql);
					} else {
						String sql = "update ar_gatherbill set def4='Y' where dr=0 and def5='bankbill' and def1='"
								+ pk_bill + "' ";
						baseDao.executeUpdate(sql);
					}

				} else {
					throw new BusinessException("审批回传电商，电商系统处理遇到异常："
							+ resultdesp);
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	protected AggregatedValueObject[] getBills(final IBusinessEvent event) {
		Object object = null;
		if (event instanceof BusinessEvent) {
			BusinessEvent e = (BusinessEvent) event;
			object = e.getObject();
		} else if (event instanceof BdUpdateEvent) {
			BdUpdateEvent e = (BdUpdateEvent) event;
			object = e.getNewObject();
		}

		AggregatedValueObject[] bills = null;
		if (null != object) {
			if (object.getClass().isArray()) {
				bills = (AggregatedValueObject[]) object;
			} else {
				bills = new AggregatedValueObject[1];
				bills[0] = (AggregatedValueObject) object;
			}
		}
		return bills;
	}

	private IMDPersistenceQueryService getMdQueryService() {
		if (mdQueryService == null)
			mdQueryService = MDPersistenceService
					.lookupPersistenceQueryService();
		return mdQueryService;
	}
}
