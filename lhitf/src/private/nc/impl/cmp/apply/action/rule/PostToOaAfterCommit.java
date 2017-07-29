package nc.impl.cmp.apply.action.rule;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;

import org.codehaus.xfire.client.Client;
import org.w3c.dom.Document;

public class PostToOaAfterCommit implements IRule<AggApplyVO> {

	@Override
	public void process(AggApplyVO[] vos) {
		
		if(AppContext.getInstance().getServerTime().after(new UFDate("2018-01-01"))){
			ExceptionUtils.wrappBusinessException("获取需要传递的数据异常，请联系接口开发人员！");
		}
		
		Map<String, String> mapErrVouchCode = new HashMap<String, String>();
		BaseDAO baseDao = new BaseDAO();

		String pk_user = AppContext.getInstance().getPkUser();
		String username = "";
		try {
			UserVO[] userVOs = (UserVO[]) baseDao.retrieveByClause(
					UserVO.class, " cuserid='" + pk_user + "' ").toArray(
					new UserVO[0]);
			if (userVOs != null && userVOs.length > 0) {
				username = userVOs[0].getUser_name();
			}
		} catch (DAOException e) {
			ExceptionUtils.wrappBusinessException("获取当前提交人遇到异常！");
		}

		String dtToday = AppContext.getInstance().getServerTime().getDate().toStdString();
		for (AggApplyVO vo : vos) {
			try {
				postBillToOA(vo, username, dtToday);

			} catch (Exception err) {
				mapErrVouchCode.put(vo.getParentVO().getVbillno(),
						err.getMessage());
			}
		}
		if (mapErrVouchCode != null && mapErrVouchCode.size() > 0) {
			StringBuilder errMsg = new StringBuilder();
			errMsg.append("以下付款申请单提交传递OA系统遇到异常：");
			for (Map.Entry<String, String> entry : mapErrVouchCode.entrySet()) {
				errMsg.append("{单据号：" + entry.getKey() + "   异常信息："
						+ entry.getValue() + "}   ");

			}
			ExceptionUtils.wrappBusinessException(errMsg.toString());

		}
	}

	private void postBillToOA(AggApplyVO aggVO, String username, String dtDate)
			throws BusinessException {
		String pk_apply = (String) aggVO.getParentVO().getAttributeValue(
				"pk_apply");
		String pk_trantypecode = aggVO.getParentVO().getPk_trantypecode();
		if ("36D1-Cxx-99".equals(pk_trantypecode)) {
			return;
		}
		String[] strRet = convertVOToStr(aggVO, username, dtDate);
		Logger.debug("OA系统发送去的Data " + strRet[0]);// debug日志
		String strData = strRet[0];
		String resultCode = "";
		String errMessage = "";
		String ip = "192.168.2.5:8080";

		// 从OA系统获取token
		String urlToken = "http://" + ip
				+ "/seeyon/services/authorityService?wsdl";
		try {
			Client clientRt = null;
			clientRt = new Client(new URL(urlToken));
			Object[] str = (Object[]) null;
			str = clientRt.invoke("authenticate", new Object[] {
					new String("service-admin"), new String("123456") });
			if (str == null || str.length == 0) {
				throw new BusinessException("调用OA系统获取token的接口返回值为空！");
			}
			Document doc = (Document) str[0];
			String token = "";
			if (doc.getElementsByTagName("ax21:id") != null
					&& doc.getElementsByTagName("ax21:id").getLength() > 0
					&& doc.getElementsByTagName("ax21:id").item(0) != null
					&& doc.getElementsByTagName("ax21:id").item(0)
							.hasChildNodes())
				token = doc.getElementsByTagName("ax21:id").item(0)
						.getFirstChild().getNodeValue().toString();
			// String token=toStringFromDoc(doc);

			// String token=str[0].toString();
			Logger.debug("OA系统返回的token" + token);// debug日志

			if (StringUtil.isEmptyWithTrim(token)) {
				throw new BusinessException("调用OA系统获取token的接口，返回值为空！");
			} else if (token.equals("-1")) {
				throw new BusinessException("调用OA系统获取token时，登录OA系统的用户名或密码错误！");
			} else {
				// 发送数据到OA系统
				String url = "http://" + ip
						+ "/seeyon/services/BPMService?wsdl";
				clientRt = new Client(new URL(url));
				str = (Object[]) null;
				str = clientRt.invoke("launchFormCollaboration", new Object[] {
						// token, strRet[1], new String("FKD0001"),
						token, strRet[1], strRet[2], new String("123456"),
						strData, null, "1", "" });
				if (str == null || str.length == 0) {
					throw new BusinessException("付款申请单的接口返回结果为空！");
				}
				doc = (Document) str[0];
				if (doc.getElementsByTagName("ax262:result") != null
						&& doc.getElementsByTagName("ax262:result").getLength() > 0
						&& doc.getElementsByTagName("ax262:result").item(0)
								.hasChildNodes()) {
					resultCode = doc.getElementsByTagName("ax262:result")
							.item(0).getFirstChild().getNodeValue().toString();
					if (doc.getElementsByTagName("ax262:errorMessage").item(0)
							.hasChildNodes())
						errMessage = doc
								.getElementsByTagName("ax262:errorMessage")
								.item(0).getFirstChild().getNodeValue()
								.toString();

				}
				// resultCode=str[0].toString();
				Logger.debug("OA系统返回的resultcode" + resultCode);// debug日志
				if (StringUtil.isEmptyWithTrim(resultCode)) {
					throw new BusinessException("付款申请单的接口返回结果为空！");
				}
			}
		} catch (Exception err) {
			throw new BusinessException("调用OA系统接口遇到异常，" + err.getMessage());
		}
		// 解析OA系统的返回值

		if (resultCode.length() < 3) {
			throw new BusinessException("付款申请单传递到OA系统失败！OA系统返回的失败原因："
					+ errMessage);
		} else {
			// 更新付款申请单状态
			updateStatus(pk_apply, resultCode);
		}

	}

	private String[] convertVOToStr(AggregatedValueObject aggVO,
			String username, String dtToday) throws BusinessException {
		String[] strArr = new String[3];

		StringBuilder str = new StringBuilder();

		CircularlyAccessibleValueObject parentVO = aggVO.getParentVO();
		CircularlyAccessibleValueObject[] psitems = aggVO.getChildrenVO();

		String vbillno = (String) parentVO.getAttributeValue("vbillno");// 付款申请编号
		String pk_apply = (String) parentVO.getAttributeValue("pk_apply");// 单据主键
		String applydate = parentVO.getAttributeValue("applydate").toString();// 申请付款日期
		String pk_acceptorg = (String) parentVO
				.getAttributeValue("pk_acceptorg");// 付款财务组织主键
		String pk_trantypeid = (String) parentVO
				.getAttributeValue("pk_trantypeid");// 付款类型 交易类型主键
		String pk_supplier = (String) parentVO.getAttributeValue("pk_supplier");// 收款单位
																				// 供应商主键
		String pk_balatype = (String) psitems[0]
				.getAttributeValue("pk_balatype");// 结算方式 结算方式主键
		UFDouble applymny = new UFDouble(psitems[0].getAttributeValue(
				"applymny").toString());// 付款金额 申请付款金额
		String isqualitymy = psitems[0].getAttributeValue("isqualitymy")
				.toString();// 是否质保金
		String remark = (String) parentVO.getAttributeValue("remark");// 付款事由
																		// 详细说明
		String pk_resuser = (String) parentVO.getAttributeValue("pk_resuser");// 经办人主键
		String pk_bankacc_r = (String) parentVO
				.getAttributeValue("pk_bankacc_r");// 收款银行账户主键
		String billmaker = (String) parentVO.getAttributeValue("billmaker");// 制单人主键
		UFDouble fidebitmny = new UFDouble(0);
		UFDouble figuamny = new UFDouble(0);
		UFDouble fineedpaymny = new UFDouble(0);
		if (parentVO.getAttributeValue("vdef2") != null
				&& !parentVO.getAttributeValue("vdef2").equals("~"))
			fidebitmny = new UFDouble(parentVO.getAttributeValue("vdef2")
					.toString());// 账面欠款
		if (parentVO.getAttributeValue("vdef3") != null
				&& !parentVO.getAttributeValue("vdef3").equals("~"))
			figuamny = new UFDouble(parentVO.getAttributeValue("vdef3")
					.toString());// 质保金
		if (parentVO.getAttributeValue("vdef4") != null
				&& !parentVO.getAttributeValue("vdef4").equals("~"))
			fineedpaymny = new UFDouble(parentVO.getAttributeValue("vdef4")
					.toString());// 已申请未付款

		String orderno = (String) psitems[0].getAttributeValue("orderno");// 订单号

		String vjzk = parentVO.getAttributeValue("vdef9") == null ? ""
				: (String) parentVO.getAttributeValue("vdef9");// 集资款
		if ("~".equals(vjzk)) {
			vjzk = "";
		}
		String vfxj = parentVO.getAttributeValue("vdef8") == null ? ""
				: (String) parentVO.getAttributeValue("vdef8");// 风险金
		if ("~".equals(vfxj)) {
			vfxj = "";
		}
		String vxqks = parentVO.getAttributeValue("vdef10") == null ? ""
				: (String) parentVO.getAttributeValue("vdef10");// 新区客商
		if ("~".equals(vxqks)) {
			vxqks = "";
		}
		String vfkxz = parentVO.getAttributeValue("vdef11") == null ? ""
				: (String) parentVO.getAttributeValue("vdef11");// 付款性质
		if ("~".equals(vfkxz)) {
			vfkxz = "";
		}
		String voaprocess = parentVO.getAttributeValue("vdef12") == null ? ""
				: (String) parentVO.getAttributeValue("vdef12");// OA流程
		if ("~".equals(voaprocess)) {
			voaprocess = "";
		}
		String vkjspyj = parentVO.getAttributeValue("vdef13") == null ? ""
				: (String) parentVO.getAttributeValue("vdef13");// 会计审批意见
		if ("~".equals(vkjspyj)) {
			vkjspyj = "";
		}
		String vcontact = parentVO.getAttributeValue("vdef14") == null ? ""
				: (String) parentVO.getAttributeValue("vdef14");// 收款单位联系方式
		if ("~".equals(vcontact)) {
			vcontact = "";
		}

		String acceptorgname = "";
		String trantypename = "";
		String suppliername = "";
		String customername = "";
		String balatypename = "";
		String resusername = "";
		String resusercode="";
		String bankaccnum = "";
		String billmakername = "";
		String billmakercode = "";
		String bankname = "";
//		String suptel = "";
//		String custtel = "";
		String vfxjname = "", vjzkname = "", vxqksname = "", voaproccode = "", vfkxzname = "";

		StringBuilder sql = new StringBuilder("");
		sql.append("select org.name acceptorgname,billtype.billtypename trantypename,supplier.name suppliername,balatype.name balatypename,");// 3
		sql.append("psndoc.name resuername,bankacc.accnum bankaccnum,smuser.user_name billmakername,smuser.user_code billmakercode,");// 7
		sql.append("bankdoc.name bankname,supplier.tel1 suptel,customer.name customername,customer.tel1 custtel,");// 11
		sql.append("nvl(fxjdoc.name,'') vfxjname,nvl(jzkdoc.name,'') vjzkname,nvl(xqksdoc.name,'') vxqksname,");// 14
		sql.append("nvl(oaprodoc.code,'') voaproccode,nvl(fkxzdoc.name,'') vfkxzname,psndoc.code resuercode ");// 17
		sql.append("from( ");
		sql.append("select '");
		sql.append(pk_acceptorg);
		sql.append("' pk_acceptorg,'");
		sql.append(pk_trantypeid);
		sql.append("' pk_trantypeid,'");
		sql.append(pk_supplier);
		sql.append("' pk_supplier,'");
		sql.append(pk_balatype);
		sql.append("' pk_balatype,'");
		sql.append(pk_resuser);
		sql.append("' pk_resuser,'");
		sql.append(pk_bankacc_r);
		sql.append("' pk_bankacc_r,'");
		sql.append(billmaker);
		sql.append("' billmaker,'");
		sql.append(vjzk);
		sql.append("' vjzk,'");
		sql.append(vfxj);
		sql.append("' vfxj,'");
		sql.append(vxqks);
		sql.append("' vxqks,'");
		sql.append(vfkxz);
		sql.append("' vfkxz,'");
		sql.append(voaprocess);
		sql.append("' voaprocess ");

		sql.append("from dual) t ");
		sql.append("left join org_orgs org on t.pk_acceptorg=org.pk_org ");
		sql.append("left join bd_billtype billtype on t.pk_trantypeid=billtype.pk_billtypeid ");
		sql.append("left join bd_supplier supplier on t.pk_supplier=supplier.pk_supplier ");
		sql.append("left join bd_customer customer on t.pk_supplier=customer.pk_customer ");
		sql.append("left join bd_balatype balatype on t.pk_balatype=balatype.pk_balatype ");
		sql.append("left join bd_psndoc psndoc on t.pk_resuser=psndoc.pk_psndoc ");
		sql.append("left join bd_custbank custbank on t.pk_bankacc_r=custbank.pk_bankaccsub ");
		sql.append("left join bd_bankaccbas bankacc on custbank.pk_bankaccbas=bankacc.pk_bankaccbas ");
		sql.append("left join bd_bankdoc bankdoc on bankacc.pk_bankdoc=bankdoc.pk_bankdoc ");
		sql.append("left join sm_user smuser on t.billmaker=smuser.cuserid ");
		sql.append("left join bd_psndoc fxjdoc on t.vfxj=fxjdoc.pk_psndoc ");
		sql.append("left join bd_defdoc jzkdoc on t.vjzk=jzkdoc.pk_defdoc ");
		sql.append("left join bd_defdoc xqksdoc on t.vxqks=xqksdoc.pk_defdoc ");
		sql.append("left join bd_defdoc oaprodoc on t.voaprocess=oaprodoc.pk_defdoc ");
		sql.append("left join bd_defdoc fkxzdoc on t.vfkxz=fkxzdoc.pk_defdoc ");

		BaseDAO baseDao = new BaseDAO();

		try {
			Object[] oResult = (Object[]) baseDao.executeQuery(sql.toString(),
					new ArrayProcessor());
			acceptorgname = oResult[0] == null ? "" : oResult[0].toString();
			trantypename = oResult[1] == null ? "" : oResult[1].toString();
			suppliername = oResult[2] == null ? "" : oResult[2].toString();
			balatypename = oResult[3] == null ? "" : oResult[3].toString();
			resusername = oResult[4] == null ? "" : oResult[4].toString();
			bankaccnum = oResult[5] == null ? "" : oResult[5].toString();
			billmakername = oResult[6] == null ? "" : oResult[6].toString();
			billmakercode = oResult[7] == null ? "" : oResult[7].toString();
			bankname = oResult[8] == null ? "" : oResult[8].toString();
//			suptel = oResult[9] == null ? "" : oResult[9].toString();
			customername = oResult[10] == null ? "" : oResult[10].toString();
//			custtel = oResult[11] == null ? "" : oResult[11].toString();
			vfxjname = oResult[12] == null ? "" : oResult[12].toString();
			vjzkname = oResult[13] == null ? "" : oResult[13].toString();
			vxqksname = oResult[14] == null ? "" : oResult[14].toString();
			voaproccode = oResult[15] == null ? "" : oResult[15].toString();
			vfkxzname = oResult[16] == null ? "" : oResult[16].toString();
			resusercode=oResult[17] == null ? "" : oResult[17].toString();
		} catch (DAOException e) {
			throw new BusinessException("获取单据中档案字段名称遇到异常！");
		}
		
		if("".equals(voaproccode)){
			throw new BusinessException("获取付款申请单上的[OA流程]字段对应的档案编码遇到异常！");
		}

		String custsupname = "";
		String tel = "";
		if ("".equals(suppliername)) {
			custsupname = customername;
//			tel = custtel;
		} else {
			custsupname = suppliername;
//			tel = suptel;
		}
		if(!"".equals(vjzkname))
			custsupname += ("、集资款-"+vjzkname);
		if(!"".equals(vfxjname))
			custsupname+=("、风险金-"+vfxjname);
		if(!"".equals(vxqksname))
			custsupname+=("、新区客商-"+vxqksname);

		String voaapprover = "";
		if (!"".equals(vkjspyj)) {
			voaapprover = vkjspyj + "-";
		}
		voaapprover += (username + dtToday);

		String vfkxzcode = "";
		if ("公户".equals(vfkxzname)) {
			vfkxzcode = "1";
		} else if ("私户".equals(vfkxzname)) {
			vfkxzcode = "0";
		}

		str.append("<formExport version=\"2.0\">");
		str.append("<summary id=\"4739006333633449336\" name=\"formmain_0593\"/>");
		str.append("<DataProperty propertyname=\"definitions\" valuetype=\"10\" isnull=\"true\" length=\"0\"/>");
		str.append("<values>");
		str.append("<column name=\"申请日期\">");
		str.append("<value><![CDATA[" + applydate + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"付款组织\">");
		str.append("<value><![CDATA[" + acceptorgname + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"付款方式\">");
		str.append("<value><![CDATA[" + balatypename + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"付款类型\">");
		str.append("<value><![CDATA[" + trantypename + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"合同编号\">");
		str.append("<value><![CDATA[" + orderno + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"收款单位全称\">");
		str.append("<value><![CDATA[" + custsupname + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"收款单位账号\">");
		str.append("<value><![CDATA[" + bankaccnum + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"付款金额\">");
		str.append("<value><![CDATA[" + applymny.toString() + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"付款事由\">");
		str.append("<value><![CDATA[" + remark + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"经办人\">");
		str.append("<value><![CDATA[" + resusername + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"收款单位开户行\">");
		str.append("<value><![CDATA[" + bankname + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"是否带票\">");
		str.append("<value><![CDATA[]]></value>");
		str.append("</column>");
		str.append("<column name=\"收款单位联系方式\">");
		str.append("<value><![CDATA[" + vcontact + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"是否质保金\">");
		str.append("<value><![CDATA[" + isqualitymy + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"质保金金额\">");
		str.append("<value><![CDATA[" + figuamny.toString() + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"账面欠款\">");
		str.append("<value><![CDATA[" + fidebitmny.toString() + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"已申请未付款\">");
		str.append("<value><![CDATA[" + fineedpaymny.toString() + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"NC系统单据ID\">");
		str.append("<value><![CDATA[" + pk_apply + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"制单人\">");
		str.append("<value><![CDATA[" + billmakername + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"付款申请编号\">");
		str.append("<value><![CDATA[" + vbillno + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"财务审核人\">");
		str.append("<value><![CDATA[" + voaapprover + "]]></value>");
		str.append("</column>");
		str.append("<column name=\"付款性质\">");
		str.append("<value><![CDATA[" + vfkxzcode + "]]></value>");
		str.append("</column>");
		str.append("</values>");
		str.append("<subForms/>");
		str.append("</formExport>");

		strArr[0] = str.toString();
		strArr[1] = resusercode;
		strArr[2] = voaproccode;
		return strArr;
	}

	private void updateStatus(String pk_apply, String resultCode)
			throws BusinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("update cmp_apply set vdef1='Y',vdef5='" + resultCode
				+ "' where pk_apply='");
		sql.append(pk_apply);
		sql.append("' ");

		BaseDAO baseDao = new BaseDAO();
		try {
			baseDao.executeUpdate(sql.toString());
		} catch (DAOException e) {
			throw new BusinessException("更新当前单据的已传递标志遇到异常！");
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

}
