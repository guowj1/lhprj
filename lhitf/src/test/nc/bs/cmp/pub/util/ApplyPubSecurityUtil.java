package nc.bs.cmp.pub.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.logging.Logger;
import nc.cmp.utils.SerializationUtil;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.apply.ApplyEncryptVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tmpub.proxy.TMPubServiceProxy;
import nc.vo.tmpub.security.EncryptVO;
import nc.vo.tmpub.security.log.CaInfoContext;
import nc.vo.tmpub.security.util.Security;
import nc.vo.tmpub.util.ArrayUtil;

/**
 * 付款申请对外签名工具类
 * 
 * @author wangkangc
 * @version 6.33
 */
public class ApplyPubSecurityUtil {
	public static String CONST_ACTIONCODE_SAVE = "save";
	public static String CONST_ACTIONCODE_SETTLE = "settle";
	public static String CONST_ACTIONCODE_SIGN = "sign";
	public static String CONST_ACTIONCODE_REVERSESETTLE = "reversesettle";
	public static String CONST_ACTIONCODE_REVERSESIGN = "reversesign";
	public static String CONST_ACTIONCODE_NETPAY = "netpay";
	public static String CONST_ACTIONCODE_COMBINPAY = "combinpay";
	public static String CONST_ACTIONCODE_COMMITTOFTS = "committofts";
	public static String CONST_ACTIONCODE_CHANGESAVE = "changesave"; // 支付变更修改操作
	public static String CONST_ACTIONCODE_CHANGEAUDIT = "changeaudit"; // 支付变更审批操作
	public static String CONST_ACTIONCODE_CHANGEUNAUDIT = "changeunaudit"; // 支付变更审批操作

	/**
	 * 添加验签记录
	 * 
	 * @param billvo
	 * @param actionCode
	 * @throws BusinessException
	 */
	private static void addBillCaSignRecord(AggApplyVO billvo, String actionCode)
			throws BusinessException {

		CaInfoContext[] conext = new CaInfoContext[1];
		conext[0] = new CaInfoContext();
		conext[0].setBillvo(billvo.clone());
		conext[0].setSigncontext(billvo.getParentVO().getCode());
		conext[0].setActionCode(actionCode);
		conext[0].setSignInClient(false);
		conext[0].setCheckBeforeSave(true);

		// gwj modified
		String pk_user = getCurrentUser();// original
		// String pk_user = billvo.getParentVO().getBillmaker();//modified
		// gwj modified

		TMPubServiceProxy.getCaLogRecordService().addSignLogsWithCheck(conext,
				pk_user, ApplyEncryptVO.class, null);
	}

	/**
	 * 添加验签记录
	 * 
	 * @param billvo
	 * @param actionCode
	 * @param isclientsign是否前台签名
	 * @throws BusinessException
	 */
	public static void addBillCaSignRecord(AggApplyVO aggvo, String actionCode,
			boolean isclientsign) throws BusinessException {
		ApplyBVO[] children = (ApplyBVO[]) aggvo.getChildren(ApplyBVO.class);
		AggApplyVO newAggvo = CloneObj(aggvo);
		List<ApplyBVO> bodyLst = new ArrayList<ApplyBVO>();
		for (ApplyBVO applyBVO : children) {
			if (VOStatus.DELETED != applyBVO.getStatus()) {
				bodyLst.add(applyBVO);
			}
		}
		newAggvo.setChildren(ApplyBVO.class, bodyLst.toArray(new ApplyBVO[0]));
		CaInfoContext[] conext = new CaInfoContext[1];
		conext[0] = new CaInfoContext();
		conext[0].setBillvo(newAggvo);
		conext[0].setSigncontext(newAggvo.getParentVO().getCode());
		conext[0].setActionCode(actionCode);
		conext[0].setSignInClient(isclientsign);
		conext[0].setCheckBeforeSave(true);

		String pk_user = getCurrentUser();

		TMPubServiceProxy.getCaLogRecordService().addSignLogsWithCheck(conext,
				pk_user, ApplyEncryptVO.class, null);
	}

	/**
	 * 验签
	 * 
	 * @param aggvo
	 * @throws BusinessException
	 */
	public static void verifySign(AggApplyVO aggvo, String actionCode,
			boolean isclientsign) throws BusinessException {
		// gwj modified
		// String pkUser=aggvo.getParentVO().getBillmaker();//modified
		String pkUser = getCurrentUser();// original
		// gwj modified
		Security signMark = new Security(pkUser);
		String code = aggvo.getParentVO().getCode();
		try {
			if (isclientsign) {
				if (!signMark.verifySignatureForClient(
						new ApplyEncryptVO(aggvo), code)) {
					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("3607set_0",
									"03607set-0706")/* @res "验证签名身份合法性失败！" */);
				}
			} else {
				if (!signMark.verifySignatureForServer(
						new ApplyEncryptVO(aggvo), code)) {
					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("3607set_0",
									"03607set-0706")/* @res "验证签名身份合法性失败！" */);
				}
			}
			TMPubServiceProxy.getCaLogRecordService()
					.checkPerviousOperationCASignature(aggvo,
							ApplyEncryptVO.class);
			addBillCaSignRecord(aggvo, actionCode);
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
	}

	/**
	 * 数字签名
	 * 
	 * @param aggvo
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static void signdata(AggApplyVO... applyVOS)
			throws BusinessException {
		List<AggApplyVO> listVOS = Arrays.asList(applyVOS);
		if (ArrayUtil.isNull(listVOS)) {
			return;
		}
		List<AggApplyVO> newapplyVOS = CloneObj(listVOS);
		for (AggApplyVO aggApplyVO : newapplyVOS) {
			ApplyBVO[] children = (ApplyBVO[]) aggApplyVO
					.getChildren(ApplyBVO.class);
			List<ApplyBVO> bodyLst = new ArrayList<ApplyBVO>();
			for (ApplyBVO applyBVO : children) {
				if (VOStatus.DELETED != applyBVO.getStatus()) {
					bodyLst.add(applyBVO);
				}
			}
			aggApplyVO.setChildren(ApplyBVO.class,
					bodyLst.toArray(new ApplyBVO[0]));
		}
		String pkUser = getCurrentUser();
		String[] signDataForSettlement = signDataForSettlement(pkUser,
				newapplyVOS.toArray(new AggApplyVO[] {}));
		if (!ArrayUtil.isNull(signDataForSettlement)) {
			for (AggApplyVO aggApplyVO : applyVOS) {
				aggApplyVO.getParentVO().setCode(signDataForSettlement[0]);
				ApplyBVO[] children = (ApplyBVO[]) aggApplyVO
						.getChildren(ApplyBVO.class);
				for (ApplyBVO applyBVO : children) {
					applyBVO.setCode(signDataForSettlement[0]);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T CloneObj(T t) throws BusinessException {
		Serializable serializable = (Serializable) t;
		return (T) SerializationUtil.clone(serializable);
	}

	/**
	 * 结算单签名方法
	 * 
	 * @param billvo
	 *            需要签名的VO
	 * @param pk_user
	 *            当前登录用户PK
	 * @throws Exception
	 */
	private static String[] signDataForSettlement(String pk_user,
			AggApplyVO... aggApplyVOs) throws BusinessException {
		if (ArrayUtil.isNull(aggApplyVOs)) {
			throw new BusinessException("传入单据不能为空！");
		}

		return signData(pk_user, new ApplyEncryptVO(aggApplyVOs));
	}

	/**
	 * 签名方法
	 * 
	 * @param pk_user
	 * @param encryptVO
	 * @return
	 * @throws BusinessException
	 */
	private static String[] signData(String pk_user, EncryptVO encryptVO)
			throws BusinessException {

		Security casinger = new Security(pk_user);
		String[] encrpytkey = null;
		try {
			encrpytkey = casinger.signData(encryptVO);
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw (BusinessException) e;
			}
			Logger.error(e);
			ExceptionUtils.wrappException(e);
		}
		return encrpytkey;
	}

	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public static String getCurrentUser() {
		if (AppContext.getInstance().getPkUser().equals("superadminpk00000000")
				|| AppContext.getInstance().getPkUser().startsWith("pfxx-user")) {
			return "NC_USER0000000000000";
		}
		return AppContext.getInstance().getPkUser();
	}
}
