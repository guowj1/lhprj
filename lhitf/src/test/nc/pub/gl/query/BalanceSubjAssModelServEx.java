package nc.pub.gl.query;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nc.bs.glcom.ass.assitem.cache.AccAssItemCache;
import nc.bs.logging.Logger;
import nc.gl.account.glconst.CurrTypeConst;
import nc.gl.utils.GLMultiLangUtil;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.fi.pub.Currency;
import nc.itf.fi.pub.MeasdocUtils;
import nc.itf.glcom.para.GLParaAccessor;
import nc.ui.gl.datacache.GLPeriodDataCatch;
import nc.vo.bd.accassitem.AccAssItemVO;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.accessor.bankaccsub.BankaccsubBDDataVO;
import nc.vo.bd.account.AccountVO;
import nc.vo.bd.currtype.CurrtypeVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.pub.BDCacheQueryUtil;
import nc.vo.fipub.freevalue.util.FreeValueDefUtil;
import nc.vo.gateway60.accountbook.AccountBookUtil;
import nc.vo.gateway60.itfs.AccountUtilGL;
import nc.vo.gl.analysis.GlQueryVOEx;
import nc.vo.gl.balancesubjass.BalanceSubjAssBSKey;
import nc.vo.gl.balancesubjass.BalanceSubjAssBSVO;
import nc.vo.gl.detailbooks.CAssTool;
import nc.vo.glcom.account.Balanorient;
import nc.vo.glcom.ass.AssCodeConstructor;
import nc.vo.glcom.ass.AssVO;
import nc.vo.glcom.balance.GLQueryKey;
import nc.vo.glcom.balance.GLQueryObj;
import nc.vo.glcom.balance.GlBalanceKey;
import nc.vo.glcom.balance.GlBalanceVO;
import nc.vo.glcom.balance.GlQueryVO;
import nc.vo.glcom.balance.GlQueryVOBookTool;
import nc.vo.glcom.inteltool.CUFDoubleSumTool;
import nc.vo.glcom.inteltool.UFDCBalanceTool;
import nc.vo.glcom.inteltool.ZeroUFdoubleConstant;
import nc.vo.glcom.sorttool.ISortTool;
import nc.vo.glcom.sorttool.ISortToolProvider;
import nc.vo.glcom.tools.GLPubProxy;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.org.AccountingBookVO;
import nc.vo.pub.BusinessException;
@SuppressWarnings({ "deprecation", "unchecked",	"rawtypes" })
public class BalanceSubjAssModelServEx implements ISortToolProvider {

	GlQueryVO m_qryVO = null;

	GlQueryVO[] queryVOs = null;

	BalanceSubjAssBSVO[] m_dataVOs;

	HashMap<String,AssVO> topLevelAssInfo = new HashMap<String,AssVO>();

	public java.lang.Object dealQuery(nc.vo.glcom.balance.GlQueryVO p_qryVO)
			throws java.lang.Exception {
		AccountBookUtil.getGlOrgBookVOByPrimaryKeys(p_qryVO.getpk_accountingbook());
		String typeName = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031")/*@res "会计科目"*/;
		if("UPP20023030-000031".equals(typeName)){
			throw new BusinessException("后台取多语出错，无法查询！");/* -=notranslate=- */
		}
		query(p_qryVO);
		BalanceSubjAssBSVO[] allAssBalanceVO = m_dataVOs;
		return allAssBalanceVO;
	}

	public BalanceSubjAssBSVO[] queryOnly(GlQueryVO p_qryVO) throws Exception {
        GlBalanceVO[] initVOs = null;
        GlBalanceVO[] occurVOs = null;
        GlBalanceVO[] accumVOs = null;
        //缓存assvo数据，
        saveAssInfo(p_qryVO);
        try {
        	 //前期处理GlQueryVO
            initQueryVO(p_qryVO);
            GlBalanceVO[][] vos = getVO(queryVOs);
            //查询期初余额
            initVOs = vos[0];
            //查询本期发生
            occurVOs = vos[1];
            //查询累计发生
            accumVOs = vos[2];

        } catch (Exception e) {
            Logger.error("查询错误", e);
        }
        //拼接计算
        BalanceSubjAssBSVO[] bsvos = null;
        try {
        	//zhaoshya  下面3个方法增加了返回币种参数，集团本币和全局本币在计算合计的时候直接去掉， 所以目前组织本币根据返回币种分别赋值
            //拼接期初余额
            bsvos = appendInitVOs(bsvos, initVOs,p_qryVO.getM_Return_CurrType());
            //拼接本期发生
            bsvos = appendOccurVOs(bsvos, occurVOs,p_qryVO.getM_Return_CurrType());

            //拼接累计发生
            bsvos = appendAccumVOs(bsvos, accumVOs,p_qryVO.getM_Return_CurrType());
            //end edit zhaoshya

            //拼接科目信息
            bsvos = appendAccInfo(bsvos,p_qryVO);
            //拼接辅助信息
            bsvos = appendAssInfo(bsvos, p_qryVO);
            //拼接计量单位信息 add by pangjsh
            bsvos = appendMeasUnit(bsvos, p_qryVO);
        } catch (Exception ex) {
        	nc.bs.logging.Logger.error(ex.getMessage(), ex);
        }
        return bsvos;
    }
	public BalanceSubjAssBSVO[] getSettleedDatas(BalanceSubjAssBSVO[] bsvos,GlQueryVO p_qryVO ) throws Exception {
		//  计算期末余额、小计、合计运算
		try {
			if (m_qryVO == null) {
				m_qryVO = (GlQueryVO) p_qryVO.clone();
			}
			boolean blnHasFatherAndSon = m_qryVO.getFormatVO().isIsSubjAccIncldFatherSon();
			boolean blnAssHasFatherAndSon = m_qryVO.getFormatVO().isIsAssIncldFatherSon();
			// 无科目上下级情况替换科目
			if (!blnHasFatherAndSon) {
				// 替换科目
				if(!p_qryVO.getFormatVO().isAssBalIncludeChildSubj()){
					bsvos = changeAccInfo(bsvos);
				}
				// 合并相同科目记录
				String[] strType = null;
				nc.vo.glcom.ass.AssVO[] assvos = p_qryVO.getAssVos();
				if (assvos != null && assvos.length != 0) {
					strType = new String[assvos.length];
					for (int i = 0; i < assvos.length; i++)
						strType[i] = assvos[i].getPk_Checktype();
				}
				bsvos = combineAss(bsvos, strType);
			}
			// 首先计算期末余额
			bsvos = computeBalance(bsvos);
			// 过滤有余额无发生的数据(改为无发生不显示）
			bsvos = filterOfHasBalanceButNoOccur(bsvos, p_qryVO);
			/** 按科目期末余额方向和值过滤数据 */
			bsvos = filterByBalanceOrientAndAmount(bsvos, p_qryVO);
			/**
			 * 计算小计、合计、总计   这边区分 科目末级非末级同页显示情况。
			 * @author zhaoshya
			 */
			if(!p_qryVO.getFormatVO().isAssBalIncludeChildSubj()){
				bsvos = compute(bsvos);	
			}else{
				/*sumList存放只是最上级科目数据汇总的一些小计合计值*/
				List<BalanceSubjAssBSVO> sumList = new ArrayList<BalanceSubjAssBSVO>();
				if(bsvos!=null&&bsvos.length>0){
					List<String> accountpkList = new ArrayList<String>();
					for (BalanceSubjAssBSVO vo : bsvos) {
						if(vo.getValue(BalanceSubjAssBSKey.k_pk_accasoa)!=null
								&&vo.getValue(BalanceSubjAssBSKey.k_pk_accasoa).toString().length()==20)
							accountpkList.add(vo.getValue(BalanceSubjAssBSKey.k_pk_accasoa).toString());
					}
					String[] strPks = accountpkList.toArray(new String[0]);
					AccountVO[] vAccountVO = null;
					vAccountVO = AccountUtilGL.queryByPks(strPks, m_qryVO.getSubjVersion());
					List<BalanceSubjAssBSVO> endAccList = new ArrayList<BalanceSubjAssBSVO>();
					Map<String,AccountVO> accounts = new HashMap<String, AccountVO>();
					for(int i=0;i<vAccountVO.length;i++){
						boolean isToft = true; //是否是最上级科目
						for(int j=0;j<vAccountVO.length;j++){
							if(vAccountVO[i].getCode().startsWith(vAccountVO[j].getCode())&&!vAccountVO[i].getCode().equals(vAccountVO[j].getCode())){
								isToft = false;
								break;
							}
						}
						if(isToft)
							accounts.put(vAccountVO[i].getPk_accasoa(), vAccountVO[i]);
					}
					for(int i=0;i<bsvos.length;i++){
						AccountVO account = accounts.get((String)bsvos[i].getValue(BalanceSubjAssBSKey.k_pk_accasoa));
						//把最上级科目的数据取出来。
						if(account!=null)
							endAccList.add((BalanceSubjAssBSVO)bsvos[i].clone());
					}
					/*只是最上级科目汇总，数据不会重复*/
					BalanceSubjAssBSVO[] topAcc = compute(endAccList.toArray(new BalanceSubjAssBSVO[]{}));
					for(int i=0;topAcc!=null&&i<topAcc.length;i++){
						if (topAcc[i].getValue(BalanceSubjAssBSKey.K_Mark) != null 
								&& (topAcc[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000119")/* @res "币种累计" */)
								|| topAcc[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */)
								|| topAcc[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/)
								|| topAcc[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000115")/* @res "总计" */)
								|| (topAcc[i].getValue(BalanceSubjAssBSKey.K_Mark)
										.toString().indexOf(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000122")/* @res "小计" */)>0
									&&!topAcc[i].getValue(BalanceSubjAssBSKey.K_Mark)
										.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000121")/* @res "科目小计" */))
							)) {
							sumList.add(topAcc[i]);
						}
					}
				}
				//常规合计
				bsvos = compute(bsvos);	
				/*上行汇总的小计合计行，因为末级非末级都汇总了  数据不对，
				 *  下面把不对的数据替换掉，因为批量替换后排序不好搞， 
				 *  这边就一个个搞，如果小计合计行比较多 会慢一些*/
				for(int i=0;i<bsvos.length;i++){
					if (bsvos[i].getValue(BalanceSubjAssBSKey.K_Mark) != null 
							&& (bsvos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000119")/* @res "币种累计" */)
							|| bsvos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */)
							|| bsvos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/)
							|| bsvos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000115")/* @res "总计" */)
							|| (bsvos[i].getValue(BalanceSubjAssBSKey.K_Mark)
										.toString().indexOf(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000122")/* @res "小计" */)>0
								&&!bsvos[i].getValue(BalanceSubjAssBSKey.K_Mark)
										.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000121")/* @res "科目小计" */))
						)) {
						bsvos[i] = appendSumVOs(new BalanceSubjAssBSVO[]{bsvos[i]},sumList.toArray(new BalanceSubjAssBSVO[]{}))[0];
					}
				}
			}

			bsvos = adjustOrgBookInfo(bsvos);
			bsvos = adjustCurrType(bsvos);
			bsvos = adjustContent(bsvos);
			// 有科目上下级情况替换科目
			if (blnHasFatherAndSon) {
				// 替换科目
				if(!p_qryVO.getFormatVO().isAssBalIncludeChildSubj()){
					bsvos = changeAccInfoFaS(bsvos);
				}
			}
			// 有辅助核算上下级情况替换辅助核算
			if (blnAssHasFatherAndSon) {
				// 替换辅助核算
				bsvos = changeAssInfoFaS(bsvos, p_qryVO);
			}
			// 多单位 科目选全部 分单位列示情况 科目信息分单位列示
			if (p_qryVO.getpk_accountingbook().length > 1
					&& !p_qryVO.getFormatVO().isMultiCorpCombine()
					&& !p_qryVO.getFormatVO().isIsCorpSubjDspBase()) {
				bsvos = adjustAccInfo(bsvos, p_qryVO);
			}

		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		}
		// 调整内容
		if (bsvos == null || bsvos.length == 0)
			m_dataVOs = null;
		else
			m_dataVOs = bsvos;
		return m_dataVOs;
	}
	

	private void query(GlQueryVO p_qryVO) throws Exception {
		BalanceSubjAssBSVO[] bsvos = queryOnly(p_qryVO);
		getSettleedDatas(bsvos, p_qryVO);
	}
	
	/**
	 * 此处插入方法说明。 创建日期：(2001-12-11 10:00:28)
	 *
	 * @return nc.vo.gl.balancesubjass.BalanceSubjAssBSVO[]
	 * @param param
	 *            nc.vo.gl.balancesubjass.BalanceSubjAssBSVO[]
	 * @exception java.lang.Exception
	 *                异常说明。
	 */
	private BalanceSubjAssBSVO[] changeAccInfo(BalanceSubjAssBSVO[] param) throws java.lang.Exception {
		// 替换科目信息，补充科目Name和科目Code信息
		if (param == null || param.length == 0)
			return null;

		AccountVO[] AccountVOs = null;
		AccountVOs = AccountUtilGL.queryAccountVosByCodes(m_qryVO.getBaseAccountingbook(), m_qryVO.getAccountCodes(), m_qryVO.getSubjVersion());
		for (int i = 0; i < param.length; i++) {
			String sAcc = param[i].getValue(BalanceSubjAssBSKey.K_accsubjcode).toString();
			AccountVO aAccountVO = null;
			for (int j = 0; AccountVOs!=null &&  j < AccountVOs.length; j++) {
				if (sAcc.startsWith(((AccountVO) AccountVOs[j]).getCode())) {
					aAccountVO = (AccountVO) AccountVOs[j];
					break;
				}
			}
			param[i].setValue(BalanceSubjAssBSKey.k_pk_accasoa, aAccountVO==null?null:aAccountVO.getPk_accasoa());
			param[i].setValue(BalanceSubjAssBSKey.K_accOrient, aAccountVO==null?null:aAccountVO.getBalanorient());
			param[i].setValue(BalanceSubjAssBSKey.K_bothOrient,aAccountVO==null?null: aAccountVO.getBothorient());
			param[i].setValue(BalanceSubjAssBSKey.K_accsubjcode,aAccountVO==null?null: aAccountVO.getCode());
			param[i].setValue(BalanceSubjAssBSKey.K_accsubjname,aAccountVO==null?null:GLMultiLangUtil.getMultiDispName(aAccountVO));
		}
		return param;
	}

	private BalanceSubjAssBSVO[] changeAssInfoFaS(BalanceSubjAssBSVO[] param, GlQueryVO p_qryVO) throws java.lang.Exception {
		// 替换辅助核算信息
		if (param == null || param.length == 0)
			return null;
		Vector<BalanceSubjAssBSVO> vSubjAssBS = new Vector<BalanceSubjAssBSVO>();
		String strAssPks = null;
		String strAssCodes = null;
		String strAssNames = null;
		nc.vo.glcom.balance.GLQueryObj[] glQryObjs = p_qryVO.getFormatVO().getQryObjs();
		nc.vo.glcom.ass.AssVO aVo = null;
		int iAssIn = 0;
		for (int i = 0; i < glQryObjs.length; i++) {
			if (glQryObjs[i].getIncludeSub()) {
				aVo = (nc.vo.glcom.ass.AssVO) glQryObjs[i].getValueRange()[0].clone();
				strAssPks = ((nc.vo.glcom.ass.AssVO) glQryObjs[i].getValueRange()[0]).getPk_Checkvalue();
				strAssCodes = ((nc.vo.glcom.ass.AssVO) glQryObjs[i].getValueRange()[0]).getCheckvaluecode();
				strAssNames = ((nc.vo.glcom.ass.AssVO) glQryObjs[i].getValueRange()[0]).getCheckvaluename();
			}
			if (!glQryObjs[i].getType().equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031")/*@res "会计科目"*/) && !glQryObjs[i].getIncludeSub()) {
				iAssIn++;
			}
		}
		Vector<String> vAssPk = new Vector<String>();
		Vector<String> vAssCode = new Vector<String>();
		Vector<String> vAssName = new Vector<String>();
		java.util.StringTokenizer pkToken = new java.util.StringTokenizer(strAssPks, ",");
		java.util.StringTokenizer codeToken = new java.util.StringTokenizer(strAssCodes, ",");
		java.util.StringTokenizer nameToken = new java.util.StringTokenizer(strAssNames, ",");
		while (pkToken.hasMoreTokens()) {
			vAssPk.add(pkToken.nextToken());
		}
		while (codeToken.hasMoreTokens()) {
			vAssCode.add(codeToken.nextToken());
		}
		while (nameToken.hasMoreTokens()) {
			vAssName.add(nameToken.nextToken());
		}
		nc.vo.glcom.ass.AssVO[] qryAssVos = new nc.vo.glcom.ass.AssVO[vAssCode.size()];
		for (int i = 0; i < vAssCode.size(); i++) {
			qryAssVos[i] = (nc.vo.glcom.ass.AssVO) aVo.clone();
			qryAssVos[i].setPk_Checkvalue(vAssPk.elementAt(i).toString());
			qryAssVos[i].setCheckvaluecode(vAssCode.elementAt(i).toString());
			qryAssVos[i].setCheckvaluename(vAssName.elementAt(i).toString());
		}
		nc.vo.glcom.ass.AssVO[][] subVos = new nc.vo.glcom.ass.AssVO[vAssCode.size()][];
		for (int i = 0; i < vAssCode.size(); i++) {
			subVos[i] = nc.pub.gl.query.CBalanceAssTool.getMeAndSubDocsTackle(qryAssVos[i], getPk_corp(p_qryVO.getBaseAccountingbook()));
		}
		BalanceSubjAssBSVO[] vos = new BalanceSubjAssBSVO[vAssCode.size()];
		for (int i = 0; i < param.length; i++) {
			if (param[i].getValue(BalanceSubjAssBSKey.K_Mark) != null) {
				for (int j = 0; j < vos.length; j++) {
					if (vos[j] != null) {
						vSubjAssBS.addElement(vos[j]);
						vos[j] = null;
					}
				}
				vSubjAssBS.addElement(param[i]);
			} else {
				nc.vo.glcom.ass.AssVO aAssVo = ((nc.vo.glcom.ass.AssVO[]) param[i].getValue(BalanceSubjAssBSKey.K_AssVOs))[iAssIn];
				for (int j = 0; j < vAssCode.size(); j++) {
					if (includeInAssvos(aAssVo, subVos[j])) {
						nc.vo.glcom.ass.AssVO aAssVoToChange = (nc.vo.glcom.ass.AssVO) qryAssVos[j].clone();
						if (vos[j] == null) {
							vos[j] = (BalanceSubjAssBSVO) param[i].clone();
							((nc.vo.glcom.ass.AssVO[]) vos[j].getValue(BalanceSubjAssBSKey.K_AssVOs))[iAssIn] = aAssVoToChange;
						} else {
							BalanceSubjAssBSVO tempVo = (BalanceSubjAssBSVO) param[i].clone();
							((nc.vo.glcom.ass.AssVO[]) tempVo.getValue(BalanceSubjAssBSKey.K_AssVOs))[iAssIn] = aAssVoToChange;
							BalanceSubjAssBSVO[] tempVos = new BalanceSubjAssBSVO[2];
							tempVos[0] = vos[j];
							tempVos[1] = tempVo;
							// 合并相同科目记录
							String[] strType = null;
							nc.vo.glcom.ass.AssVO[] assvos = p_qryVO.getAssVos();
							if (assvos != null && assvos.length != 0) {
								strType = new String[assvos.length];
								for (int k = 0; k < assvos.length; k++)
									strType[k] = assvos[k].getPk_Checktype();
							}
							vos[j] = combineAss1(tempVos, strType)[0];
						}
					}
				}
			}
		}
		BalanceSubjAssBSVO[] newVOs = new BalanceSubjAssBSVO[vSubjAssBS.size()];
		vSubjAssBS.copyInto(newVOs);
		return newVOs;
	}

	private boolean includeInAssvos(nc.vo.glcom.ass.AssVO vo, nc.vo.glcom.ass.AssVO[] vos) throws Exception {
		for (int i = 0; i < vos.length; i++) {
			if (vo.getPk_Checktype().equals(vos[i].getPk_Checktype()) && vo.getCheckvaluecode().equals(vos[i].getCheckvaluecode())) {
				return true;
			}
		}
		return false;
	}

	private BalanceSubjAssBSVO[] adjustAccInfo(BalanceSubjAssBSVO[] param, GlQueryVO queryvo) throws Exception {
		if (param != null && param.length != 0) {
			String[] orgBookPks = m_qryVO.getpk_accountingbook();
			Vector<String>[] accSubjCodesPerCorp = new Vector[orgBookPks.length];
			//下面的操作把 BalanceSubjAssBSVO[] 所有数据的科目放到 对应的核算账簿中
			for (int i = 0; i < accSubjCodesPerCorp.length; i++) {
				Vector<String> vCorpPlusAccsubj = new Vector<String>();
				for (int j = 0; j < param.length; j++) {
					if (param[j].getValue(BalanceSubjAssBSKey.K_PK_AccountingBook).toString().equals(orgBookPks[i])
							&& param[j].getValue(BalanceSubjAssBSKey.K_accsubjcode) != null
								&& !param[j].getValue(BalanceSubjAssBSKey.K_accsubjcode).toString().trim().equals("")) {
						if (!vCorpPlusAccsubj.contains(param[j].getValue(BalanceSubjAssBSKey.K_accsubjcode).toString())) {
							vCorpPlusAccsubj.addElement(param[j].getValue(BalanceSubjAssBSKey.K_accsubjcode).toString());
						}
					}
				}
				accSubjCodesPerCorp[i] = vCorpPlusAccsubj;
			}
			//根据科目编码，账簿 查找pk
			Vector<AccountVO> vAccsubjVO = new Vector<AccountVO>();
			for (int i = 0; i < accSubjCodesPerCorp.length; i++) {
				if (accSubjCodesPerCorp[i].size() != 0) {
					String[] accCodes = new String[accSubjCodesPerCorp[i].size()];
					accSubjCodesPerCorp[i].copyInto(accCodes);
					AccountVO[] AccsubjVOs = AccountUtilGL.queryAccountVosByCodes(orgBookPks[i], accCodes, queryvo.getSubjVersion());
					for (int k = 0; k < AccsubjVOs.length; k++) {
						vAccsubjVO.addElement(AccsubjVOs[k]);
					}
				}
			}
			for (int i = 0; i < param.length; i++) {
				if (param[i].getValue(BalanceSubjAssBSKey.K_accsubjcode) != null 
						&& !param[i].getValue(BalanceSubjAssBSKey.K_accsubjcode).toString().trim().equals("")) {
					String sAcc = param[i].getValue(BalanceSubjAssBSKey.K_accsubjcode).toString();
					String sorgBookPk = param[i].getValue(BalanceSubjAssBSKey.K_PK_AccountingBook).toString();
					AccountingBookVO accountingBook = AccountBookUtil.getAccountingBookVOByPrimaryKey(sorgBookPk);
					AccountVO aAccsubjVO = null;
					for (int j = 0; j < vAccsubjVO.size(); j++) {
						if (accountingBook.getPk_curraccchart().equals(((AccountVO) vAccsubjVO.elementAt(j)).getPk_currentchart()) 
								&& sAcc.equals(((AccountVO) vAccsubjVO.elementAt(j)).getCode())) {
							aAccsubjVO = (AccountVO) vAccsubjVO.elementAt(j);
							break;
						}
					}
					param[i].setValue(BalanceSubjAssBSKey.K_accOrient, aAccsubjVO.getBalanorient());
					param[i].setValue(BalanceSubjAssBSKey.K_bothOrient, aAccsubjVO.getBothorient());
					param[i].setValue(BalanceSubjAssBSKey.K_accsubjcode, aAccsubjVO.getCode());
					param[i].setValue(BalanceSubjAssBSKey.K_accsubjname, GLMultiLangUtil.getMultiDispName(aAccsubjVO));
				}
			}
			return param;
		}
		return null;
	}

	private BalanceSubjAssBSVO[] combineAss1(BalanceSubjAssBSVO[] vos, String[] strType) throws Exception {
		if (vos == null || vos.length == 0)
			return vos;

		/** 先对查询结果辅助项进行处理，格式化成和查询VO一致的辅助项个数 */
		for (int i = 0; i < vos.length; i++) {
			nc.vo.glcom.ass.AssVO[] formAss = new nc.vo.glcom.ass.AssVO[strType.length];
			for (int j = 0; j < strType.length; j++) {
				for (int k = 0; k < vos[i].getAssVOs().length; k++) {
					if (vos[i].getAssVOs()[k].getPk_Checktype().equals(strType[j]))
						formAss[k] = vos[i].getAssVOs()[k];
				}
			}
			vos[i].setValue(BalanceSubjAssBSKey.K_AssVOs, formAss);
		}
		/** End of here ! */
		nc.vo.glcom.intelvo.CIntelVO tt = new nc.vo.glcom.intelvo.CIntelVO();
		// 在此不指定分组信息,则不分组合计
		int[] intSortIndex;
		int intSumLimit = 1;
		// 指定合计的最小列号组合
		if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName())) {
			if (m_qryVO.isMultiCorpCombine()) {
				intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_pk_currtype };
				intSumLimit = 2;
			} else {
				if(isBuSupport()){
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
							, BalanceSubjAssBSKey.K_PK_AccountingBook,BalanceSubjAssBSKey.K_PK_UNIT, BalanceSubjAssBSKey.K_pk_currtype };
					intSumLimit = 4;
				}else{
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
							, BalanceSubjAssBSKey.K_PK_AccountingBook, BalanceSubjAssBSKey.K_pk_currtype };
					intSumLimit = 3;
				}
			}
		} else {
			if (m_qryVO.isMultiCorpCombine()) {
				intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode };
				intSumLimit = 1;
			} else {
				if(isBuSupport()){
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
							, BalanceSubjAssBSKey.K_PK_AccountingBook,BalanceSubjAssBSKey.K_PK_UNIT };
					intSumLimit = 3;
				}else{
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
							, BalanceSubjAssBSKey.K_PK_AccountingBook };
					intSumLimit = 2;
				}
			}
		}
		nc.vo.glcom.inteltool.CGenTool genTool = new nc.vo.glcom.inteltool.CGenTool();
		genTool.setLimitSumGen(intSumLimit);
		genTool.setSortIndex(intSortIndex);
		genTool.setGetSortTool(this);
		nc.vo.glcom.inteltool.CSumTool sumTool = new nc.vo.glcom.inteltool.CSumTool();
		int sumIndex[] = new int[] { BalanceSubjAssBSKey.K_InitCreditAmount, BalanceSubjAssBSKey.K_InitCreditAuxAmount, BalanceSubjAssBSKey.K_InitCreditLocAmount, BalanceSubjAssBSKey.K_InitCreditQuant, BalanceSubjAssBSKey.K_InitDebitAmount, BalanceSubjAssBSKey.K_InitDebitAuxAmount, BalanceSubjAssBSKey.K_InitDebitLocAmount, BalanceSubjAssBSKey.K_InitDebitQuant, BalanceSubjAssBSKey.K_DebitQuant,
				BalanceSubjAssBSKey.K_DebitAmount, BalanceSubjAssBSKey.K_DebitAuxAmount, BalanceSubjAssBSKey.K_DebitLocAmount, BalanceSubjAssBSKey.K_CreditQuant, BalanceSubjAssBSKey.K_CreditAmount, BalanceSubjAssBSKey.K_CreditAuxAmount, BalanceSubjAssBSKey.K_CreditLocAmount, BalanceSubjAssBSKey.K_DebitAccumAmount, BalanceSubjAssBSKey.K_DebitAccumAuxAmount,
				BalanceSubjAssBSKey.K_DebitAccumLocAmount, BalanceSubjAssBSKey.K_DebitAccumQuant, BalanceSubjAssBSKey.K_CreditAccumAmount, BalanceSubjAssBSKey.K_CreditAccumAuxAmount, BalanceSubjAssBSKey.K_CreditAccumLocAmount, BalanceSubjAssBSKey.K_CreditAccumQuant, BalanceSubjAssBSKey.K_EndCreditAmount, BalanceSubjAssBSKey.K_EndCreditAuxAmount, BalanceSubjAssBSKey.K_EndCreditLocAmount,
				BalanceSubjAssBSKey.K_EndCreditQuant, BalanceSubjAssBSKey.K_EndDebitAmount, BalanceSubjAssBSKey.K_EndDebitAuxAmount, BalanceSubjAssBSKey.K_EndDebitLocAmount, BalanceSubjAssBSKey.K_EndDebitQuant, };
		// 要进行合计的列
		sumTool.setSumIndex(sumIndex);

		nc.vo.glcom.inteltool.COutputTool outputTool = new nc.vo.glcom.inteltool.COutputTool();
		outputTool.setRequireOutputDetail(false);
		outputTool.setSummaryCol(-1); // 设置备注信息内容及所对应的列

		nc.vo.glcom.inteltool.CDataSource datasource = new nc.vo.glcom.inteltool.CDataSource();
		Vector vecVos = new Vector();
		for (int i = 0; i < vos.length; i++) {
			vos[i].setUserData(null);
			vecVos.addElement(vos[i]);
		}
		datasource.setSumVector(nc.vo.glcom.inteltool.CDataSource.sortVector(vecVos, genTool, false));
		try {
			tt.setSumTool(sumTool);
			tt.setGenTool(genTool);
			tt.setDatasource(datasource);
			tt.setOutputTool(outputTool);
		} catch (Throwable e) {
			Logger.error(e.getMessage(), e);
		}
		Vector recVector = tt.getResultVector();
		BalanceSubjAssBSVO[] VOs = new BalanceSubjAssBSVO[recVector.size()];
		recVector.copyInto(VOs);
		return VOs;
	}
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	private BalanceSubjAssBSVO[] adjustOrgBookInfo(BalanceSubjAssBSVO[] param) throws Exception {
		if (m_qryVO.isMultiCorpCombine() || m_qryVO.getpk_accountingbook().length == 1) {
			// 余额表联查辅助账时，数量小数位出错。所以要补PK_GLORGBOOK
			if (param != null && param.length != 0) {
				for (int i = 0; i < param.length; i++) {
					param[i].setValue(BalanceSubjAssBSKey.K_PK_AccountingBook, m_qryVO.getBaseAccountingbook());
				}
			}
			return param;
		}
		if (param != null && param.length != 0) {
			AccountingBookVO[] glorgbookvos = new AccountingBookVO[m_qryVO.getpk_accountingbook().length];
			for(int i=0;i<m_qryVO.getpk_accountingbook().length;i++){
				glorgbookvos[i] = AccountBookUtil.getAccountingBookVOByPrimaryKey(m_qryVO.getpk_accountingbook()[i]);
			}
			for (int i = 0; i < param.length; i++) {
				if (param[i].getValue(BalanceSubjAssBSKey.K_PK_AccountingBook) != null && !param[i].getValue(BalanceSubjAssBSKey.K_PK_AccountingBook).toString().trim().equals("")) {
					String sPkGlorgbook = param[i].getValue(BalanceSubjAssBSKey.K_PK_AccountingBook).toString();
					try {
						for (int j = 0; j < glorgbookvos.length; j++) {
							if (sPkGlorgbook.equals(glorgbookvos[j].getPrimaryKey())) {
								param[i].setValue(BalanceSubjAssBSKey.K_GLORGNAME, glorgbookvos[j].getName());
								break;
							}
						}
					} catch (java.lang.NullPointerException e) {
						param[i].setValue(BalanceSubjAssBSKey.K_GLORGNAME, NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000123")/* @res "无此主体账簿" */);
					}
				}
			}
			return param;
		}
		return null;
	}

	private BalanceSubjAssBSVO[] adjustCurrType(BalanceSubjAssBSVO[] vos) throws Exception {
		if (vos == null || vos.length == 0)
			return vos;
		CurrtypeVO[] currVOs = Currency.queryAll(null);
		for (int i = 0; i < vos.length; i++) {
			if (vos[i].getValue(BalanceSubjAssBSKey.K_Mark) == null
					|| (!vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000117")/* @res "年度合计" */) && !vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000115")/* @res "总计" */)
							&& !vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */) && vos[i].getValue(BalanceSubjAssBSKey.K_pk_currtype) != null
							&& !vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/))) {
				for (int j = 0; j < currVOs.length; j++) {
					if (vos[i].getValue(BalanceSubjAssBSKey.K_pk_currtype).equals(currVOs[j].getPk_currtype())) {
						vos[i].setValue(BalanceSubjAssBSKey.K_CurType, currVOs[j].getName());
						break;
					}
				}
			}
		}
		return vos;
	}

	private BalanceSubjAssBSVO[] compute(BalanceSubjAssBSVO[] vos) throws Exception {
		java.util.Vector vecVO = new java.util.Vector();
		if (vos == null || vos.length == 0)
			return null;
		Object sCurrPK = null;
		for (int i = 0; i < vos.length; i++) {
			vos[i].setUserData(null);
			sCurrPK = vos[i].getValue(BalanceSubjAssBSKey.K_pk_currtype);
			if (sCurrPK == null)
				vos[i].setValue(BalanceSubjAssBSKey.K_pk_currtype, "**");
			// 当选择了本币/辅币时，为了汇总总计数据
			vecVO.addElement(vos[i]);
		}
		nc.vo.glcom.inteltool.CGenTool genTool = new nc.vo.glcom.inteltool.CGenTool();
		// 指定用于分组计算的列号
		int[] intSortIndex = getSortIndex(0);
		int intSumLimit = getLimitSumGen(m_qryVO.getCurrTypeName(), m_qryVO.getFormatVO().getQryObjs());
		genTool.setSortIndex(intSortIndex);
		genTool.setLimitSumGen(intSumLimit);
		CUFDoubleSumTool sumTool = new CUFDoubleSumTool();
		int sumIndex[] = { BalanceSubjAssBSKey.K_InitCreditAmount, BalanceSubjAssBSKey.K_InitCreditAuxAmount, BalanceSubjAssBSKey.K_InitCreditLocAmount, BalanceSubjAssBSKey.K_InitCreditQuant, BalanceSubjAssBSKey.K_InitDebitAmount, BalanceSubjAssBSKey.K_InitDebitAuxAmount, BalanceSubjAssBSKey.K_InitDebitLocAmount, BalanceSubjAssBSKey.K_InitDebitQuant, BalanceSubjAssBSKey.K_DebitQuant,
				BalanceSubjAssBSKey.K_DebitAmount, BalanceSubjAssBSKey.K_DebitAuxAmount, BalanceSubjAssBSKey.K_DebitLocAmount, BalanceSubjAssBSKey.K_CreditQuant, BalanceSubjAssBSKey.K_CreditAmount, BalanceSubjAssBSKey.K_CreditAuxAmount, BalanceSubjAssBSKey.K_CreditLocAmount, BalanceSubjAssBSKey.K_DebitAccumAmount, BalanceSubjAssBSKey.K_DebitAccumAuxAmount,
				BalanceSubjAssBSKey.K_DebitAccumLocAmount, BalanceSubjAssBSKey.K_DebitAccumQuant, BalanceSubjAssBSKey.K_CreditAccumAmount, BalanceSubjAssBSKey.K_CreditAccumAuxAmount, BalanceSubjAssBSKey.K_CreditAccumLocAmount, BalanceSubjAssBSKey.K_CreditAccumQuant, BalanceSubjAssBSKey.K_EndCreditAmount, BalanceSubjAssBSKey.K_EndCreditAuxAmount, BalanceSubjAssBSKey.K_EndCreditLocAmount,
				BalanceSubjAssBSKey.K_EndCreditQuant, BalanceSubjAssBSKey.K_EndDebitAmount, BalanceSubjAssBSKey.K_EndDebitAuxAmount, BalanceSubjAssBSKey.K_EndDebitLocAmount, BalanceSubjAssBSKey.K_EndDebitQuant };
		sumTool.setSumIndex(sumIndex);
		UFDCBalanceTool balanceTool = new UFDCBalanceTool();
		int[] balanceIndex;
		String[] relateExpression;
		balanceIndex = new int[] { BalanceSubjAssBSKey.K_EndDebitQuant, BalanceSubjAssBSKey.K_EndDebitAmount, BalanceSubjAssBSKey.K_EndDebitAuxAmount, BalanceSubjAssBSKey.K_EndDebitLocAmount, BalanceSubjAssBSKey.K_EndCreditQuant, BalanceSubjAssBSKey.K_EndCreditAmount, BalanceSubjAssBSKey.K_EndCreditAuxAmount, BalanceSubjAssBSKey.K_EndCreditLocAmount };
		// 确定计算余额的列号
		// 如果>1000 则从相应的上一行取方向标志位,如果在0与1000之间,则取本行的相应方向标志位
		// 如果<0,则不考虑标志位
		relateExpression = new String[] { "[" + BalanceSubjAssBSKey.K_InitDebitQuant + "]+[" + BalanceSubjAssBSKey.K_DebitQuant + "]", "[" + BalanceSubjAssBSKey.K_InitDebitAmount + "]+[" + BalanceSubjAssBSKey.K_DebitAmount + "]", "[" + BalanceSubjAssBSKey.K_InitDebitAuxAmount + "]+[" + BalanceSubjAssBSKey.K_DebitAuxAmount + "]",
				"[" + BalanceSubjAssBSKey.K_InitDebitLocAmount + "]+[" + BalanceSubjAssBSKey.K_DebitLocAmount + "]", "[" + BalanceSubjAssBSKey.K_InitCreditQuant + "]+[" + BalanceSubjAssBSKey.K_CreditQuant + "]", "[" + BalanceSubjAssBSKey.K_InitCreditAmount + "]+[" + BalanceSubjAssBSKey.K_CreditAmount + "]",
				"[" + BalanceSubjAssBSKey.K_InitCreditAuxAmount + "]+[" + BalanceSubjAssBSKey.K_CreditAuxAmount + "]", "[" + BalanceSubjAssBSKey.K_InitCreditLocAmount + "]+[" + BalanceSubjAssBSKey.K_CreditLocAmount + "]" };
		// 计算余额所涉及的列
		balanceTool.setBalanceIndex(balanceIndex);
		balanceTool.setRelateIndex(relateExpression);
		nc.vo.glcom.inteltool.COutputTool outputTool = new nc.vo.glcom.inteltool.COutputTool();
		String[][] strSummarys = getSummary(m_qryVO.getCurrTypeName(), m_qryVO.getFormatVO().getQryObjs());
		String[] strSummary = strSummarys[1]; // null;
		String[] strInitSummary = strSummarys[0]; // null;

		outputTool.setSimpleSummary(true);
		outputTool.setInitSummary(strInitSummary);
		// 设置备注信息内容及所对应的列
		outputTool.setSummary(strSummary);
		// 设置备注信息内容及所对应的列
		outputTool.setSummaryCol(BalanceSubjAssBSKey.K_Mark);
		// 设置备注信息内容及所对应的列
		outputTool.setRequireOutputDetail(true);
		outputTool.setGenTool(genTool);
		nc.vo.glcom.inteltool.CDataSource datasource = new nc.vo.glcom.inteltool.CDataSource();
		datasource.setSumVector(nc.vo.glcom.inteltool.CDataSource.sortVector(vecVO, genTool, false));
		nc.vo.glcom.intelvo.CIntelVO tt = new nc.vo.glcom.intelvo.CIntelVO();
		tt.setSumTool(sumTool);
		try {
			tt.setGenTool(genTool);
			tt.setBalanceTool(balanceTool);
			tt.setDatasource(datasource);
			tt.setOutputTool(outputTool);
			tt.setTotalSumTool(sumTool);
			java.util.Vector recVector = tt.getResultVector();
			vos = new BalanceSubjAssBSVO[recVector.size()];
			recVector.copyInto(vos);
			return addSumAllMark(vos);
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
	}

	private int getLimitSumGen(String strCurrTypeName, GLQueryObj[] glQryObjs) {
		int intSumgenLimit = 0;
		for (int i = 0; i < glQryObjs.length; i++) {
			if (!glQryObjs[i].getType().equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031"/*会计科目*/))) {
				intSumgenLimit++;
			}
		}
		return intSumgenLimit;
	}
	private BalanceSubjAssBSVO[] changeAccInfoFaS(BalanceSubjAssBSVO[] param) throws java.lang.Exception {
		// 替换科目信息
		if (param == null || param.length == 0)
			return null;
		Vector vSubjAssBS = new Vector();
		String[] strPks = m_qryVO.getPk_account();
		AccountVO[] vAccountVO = null;
		vAccountVO = AccountUtilGL.queryByPks(strPks, m_qryVO.getSubjVersion());
		BalanceSubjAssBSVO[] vos = new BalanceSubjAssBSVO[vAccountVO.length];
		for (int i = 0; i < param.length; i++) {
			if (param[i].getValue(BalanceSubjAssBSKey.K_Mark) != null) {
				for (int j = 0; j < vos.length; j++) {
					if (vos[j] != null) {
						vSubjAssBS.addElement(vos[j]);
						vos[j] = null;
					}
				}
				vSubjAssBS.addElement(param[i]);
			} else {
				String sAcc = param[i].getValue(BalanceSubjAssBSKey.K_accsubjcode).toString();
				for (int j = 0; j < vAccountVO.length; j++) {
					if (sAcc.startsWith(((AccountVO) vAccountVO[j]).getCode())) {
						AccountVO aAccountVO = (AccountVO) vAccountVO[j];
						if (vos[j] == null) {
							vos[j] = (BalanceSubjAssBSVO) param[i].clone();
							vos[j].setValue(BalanceSubjAssBSKey.k_pk_accasoa, aAccountVO.getPk_accasoa());
							vos[j].setValue(BalanceSubjAssBSKey.K_accOrient, aAccountVO.getBalanorient());
							vos[j].setValue(BalanceSubjAssBSKey.K_bothOrient, aAccountVO.getBothorient());
							vos[j].setValue(BalanceSubjAssBSKey.K_accsubjcode, aAccountVO.getCode());
							vos[j].setValue(BalanceSubjAssBSKey.K_accsubjname, GLMultiLangUtil.getMultiDispName(aAccountVO));
						} else {
							BalanceSubjAssBSVO tempVo = (BalanceSubjAssBSVO) param[i].clone();
							tempVo.setValue(BalanceSubjAssBSKey.k_pk_accasoa, aAccountVO.getPk_accasoa());
							tempVo.setValue(BalanceSubjAssBSKey.K_accOrient, aAccountVO.getBalanorient());
							tempVo.setValue(BalanceSubjAssBSKey.K_bothOrient, aAccountVO.getBothorient());
							tempVo.setValue(BalanceSubjAssBSKey.K_accsubjcode, aAccountVO.getCode());
							tempVo.setValue(BalanceSubjAssBSKey.K_accsubjname, GLMultiLangUtil.getMultiDispName(aAccountVO));
							BalanceSubjAssBSVO[] tempVos = new BalanceSubjAssBSVO[2];
							tempVos[0] = vos[j];
							tempVos[1] = tempVo;
							vos[j] = combineAcc(tempVos)[0];
						}
					}
				}
			}
		}
		BalanceSubjAssBSVO[] newVOs = new BalanceSubjAssBSVO[vSubjAssBS.size()];
		vSubjAssBS.copyInto(newVOs);
		return newVOs;
	}

	private BalanceSubjAssBSVO[] combineAcc(BalanceSubjAssBSVO[] vos) throws Exception {
		if (vos == null || vos.length == 0)
			return vos;
		nc.vo.glcom.intelvo.CIntelVO tt = new nc.vo.glcom.intelvo.CIntelVO();
		int intSumLimit = 0;
		int[] intSortIndex = new int[] { BalanceSubjAssBSKey.K_accsubjcode, };
		nc.vo.glcom.inteltool.CGenTool genTool = new nc.vo.glcom.inteltool.CGenTool();
		genTool.setLimitSumGen(intSumLimit);
		genTool.setSortIndex(intSortIndex);
		genTool.setGetSortTool(this);
		nc.vo.glcom.inteltool.CSumTool sumTool = new nc.vo.glcom.inteltool.CSumTool();
		int sumIndex[] = new int[] { BalanceSubjAssBSKey.K_InitCreditAmount, BalanceSubjAssBSKey.K_InitCreditAuxAmount, BalanceSubjAssBSKey.K_InitCreditLocAmount, BalanceSubjAssBSKey.K_InitCreditQuant, BalanceSubjAssBSKey.K_InitDebitAmount, BalanceSubjAssBSKey.K_InitDebitAuxAmount, BalanceSubjAssBSKey.K_InitDebitLocAmount, BalanceSubjAssBSKey.K_InitDebitQuant, BalanceSubjAssBSKey.K_DebitQuant,
				BalanceSubjAssBSKey.K_DebitAmount, BalanceSubjAssBSKey.K_DebitAuxAmount, BalanceSubjAssBSKey.K_DebitLocAmount, BalanceSubjAssBSKey.K_CreditQuant, BalanceSubjAssBSKey.K_CreditAmount, BalanceSubjAssBSKey.K_CreditAuxAmount, BalanceSubjAssBSKey.K_CreditLocAmount, BalanceSubjAssBSKey.K_DebitAccumAmount, BalanceSubjAssBSKey.K_DebitAccumAuxAmount,
				BalanceSubjAssBSKey.K_DebitAccumLocAmount, BalanceSubjAssBSKey.K_DebitAccumQuant, BalanceSubjAssBSKey.K_CreditAccumAmount, BalanceSubjAssBSKey.K_CreditAccumAuxAmount, BalanceSubjAssBSKey.K_CreditAccumLocAmount, BalanceSubjAssBSKey.K_CreditAccumQuant, BalanceSubjAssBSKey.K_EndCreditAmount, BalanceSubjAssBSKey.K_EndCreditAuxAmount, BalanceSubjAssBSKey.K_EndCreditLocAmount,
				BalanceSubjAssBSKey.K_EndCreditQuant, BalanceSubjAssBSKey.K_EndDebitAmount, BalanceSubjAssBSKey.K_EndDebitAuxAmount, BalanceSubjAssBSKey.K_EndDebitLocAmount, BalanceSubjAssBSKey.K_EndDebitQuant };
		// 要进行合计的列
		sumTool.setSumIndex(sumIndex);
		nc.vo.glcom.inteltool.COutputTool outputTool = new nc.vo.glcom.inteltool.COutputTool();
		outputTool.setRequireOutputDetail(false);
		outputTool.setSummaryCol(-1); // 设置备注信息内容及所对应的列
		nc.vo.glcom.inteltool.CDataSource datasource = new nc.vo.glcom.inteltool.CDataSource();
		Vector vecVos = new Vector();
		for (int i = 0; i < vos.length; i++) {
			vos[i].setUserData(null);
			vecVos.addElement(vos[i]);
		}
		datasource.setSumVector(nc.vo.glcom.inteltool.CDataSource.sortVector(vecVos, genTool, false));
		try {
			tt.setSumTool(sumTool);
			tt.setGenTool(genTool);
			tt.setDatasource(datasource);
			tt.setOutputTool(outputTool);
		} catch (Throwable e) {
			Logger.error(e.getMessage(), e);
		}
		Vector recVector = tt.getResultVector();
		BalanceSubjAssBSVO[] newVOs = new BalanceSubjAssBSVO[recVector.size()];
		recVector.copyInto(newVOs);
		return newVOs;
	}

	/**
	 * @param p_vos
	 * @return
	 * @throws Exception
	 */
	private BalanceSubjAssBSVO[] adjustContent(BalanceSubjAssBSVO[] p_vos) throws Exception {
		if (p_vos == null || p_vos.length == 0)
			return null;
		// 从查询结果中过滤所要求的记录
		java.util.Vector tmp = new java.util.Vector();
		java.util.Vector vSumEle = getSumEle();
		String sMark = null;
		int[] sortIndexTemp = getSortIndex(1);
		nc.vo.glcom.ass.AssVO[] assVosTemp = m_qryVO.getAssVos();
		// 小计行去除多余信息
		for (int j = 0; j < p_vos.length; j++) {
			sMark = (String) p_vos[j].getValue(BalanceSubjAssBSKey.K_Mark);
			int iIndexTemp = -1;
			if (sMark != null && !sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000115")/* @res "总计" */)) {
				if (sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000117")/* @res "年度合计" */)) {
					iIndexTemp = BalanceSubjAssBSKey.K_year;
					p_vos[j].setValue(BalanceSubjAssBSKey.K_pk_currtype, "99999999999999999998");
				} else if (sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000119")/* @res "币种累计" */)) {
					iIndexTemp = BalanceSubjAssBSKey.K_pk_currtype;
				} else if (sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */) || sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000120")/* @res "公司小计" */)) {
					iIndexTemp = BalanceSubjAssBSKey.K_PK_AccountingBook;
				}else if (sMark.equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/)) {
					iIndexTemp = BalanceSubjAssBSKey.K_PK_UNIT;
				} else if (sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000121")/* @res "科目小计" */)) {
					iIndexTemp = BalanceSubjAssBSKey.K_accsubjname;
				} else {
					for (int i = 0; i < assVosTemp.length; i++) {
						String assSmark = sMark.substring(0, sMark.indexOf(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000122")/* @res "小计" */));
						if (assSmark.equals(assVosTemp[i].getChecktypename())) {
							if (i == 0) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass0InnerCode;
								break;
							} else if (i == 1) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass1InnerCode;
								break;
							} else if (i == 2) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass2InnerCode;
								break;
							} else if (i == 3) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass3InnerCode;
								break;
							} else if (i == 4) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass4InnerCode;
								break;
							} else if (i == 5) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass5InnerCode;
								break;
							} else if (i == 6) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass6InnerCode;
								break;
							} else if (i == 7) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass7InnerCode;
								break;
							} else if (i == 8) {
								iIndexTemp = BalanceSubjAssBSKey.K_Ass8InnerCode;
								break;
							}
						}
					}
				}
			}
			if (iIndexTemp != -1) {
				for (int i = 0; i < sortIndexTemp.length; i++) {
					if (i != sortIndexTemp.length - 1 && sortIndexTemp[i] == iIndexTemp) {
						for (int k = i + 1; k < sortIndexTemp.length; k++) {
							p_vos[j].setValue(sortIndexTemp[k], "");
						}
					}
				}
			}
			// 去除多余小计行
			if (sMark == null || sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000115")/* @res "总计" */) || vSumEle.contains(sMark)) {
				tmp.addElement(p_vos[j]);
			}
		}

		if (tmp.size() == 0)
			p_vos = null;
		else {
			p_vos = new BalanceSubjAssBSVO[tmp.size()];
			tmp.copyInto(p_vos);
		}
		for (int i = 0; i < p_vos.length; i++) {
			if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName()) && p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark) != null && p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */)) {
				p_vos[i].setValue(BalanceSubjAssBSKey.K_CurType, "");
			}
			sMark = (String) p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark);
//			if(sMark!=null){
//				p_vos[i].setValue(BalanceSubjAssBSKey.K_BUSIUNITNAME, "");
//			}
			if (sMark != null && !sMark.equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000115")/* @res "总计" */)) {
				if (p_vos[i].getAss0InnerCode() != null && p_vos[i].getAss0InnerCode().equals("")) {
					p_vos[i].getAssVOs()[0].setCheckvaluecode("");
					p_vos[i].getAssVOs()[0].setCheckvaluename("");
				}
				if (p_vos[i].getAss1InnerCode() != null && p_vos[i].getAss1InnerCode().equals("")) {
					p_vos[i].getAssVOs()[1].setCheckvaluecode("");
					p_vos[i].getAssVOs()[1].setCheckvaluename("");
				}
				if (p_vos[i].getAss2InnerCode() != null && p_vos[i].getAss2InnerCode().equals("")) {
					p_vos[i].getAssVOs()[2].setCheckvaluecode("");
					p_vos[i].getAssVOs()[2].setCheckvaluename("");
				}
				if (p_vos[i].getAss3InnerCode() != null && p_vos[i].getAss3InnerCode().equals("")) {
					p_vos[i].getAssVOs()[3].setCheckvaluecode("");
					p_vos[i].getAssVOs()[3].setCheckvaluename("");
				}

				if (p_vos[i].getAss4InnerCode() != null && p_vos[i].getAss4InnerCode().equals("")) {
					p_vos[i].getAssVOs()[4].setCheckvaluecode("");
					p_vos[i].getAssVOs()[4].setCheckvaluename("");
				}
				if (p_vos[i].getAss5InnerCode() != null && p_vos[i].getAss5InnerCode().equals("")) {
					p_vos[i].getAssVOs()[5].setCheckvaluecode("");
					p_vos[i].getAssVOs()[5].setCheckvaluename("");
				}
				if (p_vos[i].getAss6InnerCode() != null && p_vos[i].getAss6InnerCode().equals("")) {
					p_vos[i].getAssVOs()[6].setCheckvaluecode("");
					p_vos[i].getAssVOs()[6].setCheckvaluename("");
				}
				if (p_vos[i].getAss7InnerCode() != null && p_vos[i].getAss7InnerCode().equals("")) {
					p_vos[i].getAssVOs()[7].setCheckvaluecode("");
					p_vos[i].getAssVOs()[7].setCheckvaluename("");
				}
				if (p_vos[i].getAss8InnerCode() != null && p_vos[i].getAss8InnerCode().equals("")) {
					p_vos[i].getAssVOs()[8].setCheckvaluecode("");
					p_vos[i].getAssVOs()[8].setCheckvaluename("");
				}
			}
			//清空小计、合计、累计的计量单位
			if(sMark != null)
				p_vos[i].setValue(BalanceSubjAssBSKey.K_UNIT, "");
		}
		return p_vos;
	}


	private Vector getSumEle() {
		String strCurrTypeName = m_qryVO.getCurrTypeName();
		GLQueryObj[] glQryObjs = m_qryVO.getFormatVO().getQryObjs();
		// 0表示显示在查询对象之后,1为前
		int iCorpDispPos = m_qryVO.getFormatVO().getCorpDispLocation();
		java.util.Vector<String> vTemp = new java.util.Vector<String>();
		// 所有币种
		if (CurrTypeConst.ALL_CURRTYPE().equals(strCurrTypeName)) {
			vTemp.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000119")/* @res "币种累计" */);
		}
		if (iCorpDispPos == 1 && !m_qryVO.isMultiCorpCombine() && m_qryVO.getpk_accountingbook().length > 1) {
			vTemp.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */);
		}
		//多业务单元
		if(isBuSupport()){
			vTemp.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/);
		}
		for (int i = 0; i < glQryObjs.length; i++) {
			if (glQryObjs[i].getAccEle()) {
				if (glQryObjs[i].getType().equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031")/*@res "会计科目"*/))
					vTemp.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000121")/* @res "科目小计" */);
				else {
					vTemp.add(glQryObjs[i].getType() + NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000122")/* @res "小计" */);
				}
			}
		}
		if (iCorpDispPos == 0 && !m_qryVO.isMultiCorpCombine() && m_qryVO.getpk_accountingbook().length > 1) {
			vTemp.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000120")/* @res "公司小计" */);
		}
		// 跨年
		String tempyear = null;
		String tempendyear = null;
		if (m_qryVO.isQueryByPeriod()) {
			tempyear = m_qryVO.getYear();
			tempendyear = m_qryVO.getEndYear();
		} else {
			nc.vo.pub.lang.UFDate date1 = m_qryVO.getDate();
			nc.vo.pub.lang.UFDate date2 = m_qryVO.getEndDate();
			nc.vo.glcom.glperiod.GlPeriodVO period1 = null, period2 = null;
			try {
				period1 = GLPeriodDataCatch.getInstance().getGLperiodVOByDate(m_qryVO.getpk_accountingbook()[0], date1);
				period2 = GLPeriodDataCatch.getInstance().getGLperiodVOByDate(m_qryVO.getpk_accountingbook()[0], date2);

			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}
			tempyear = period1.getYear();
			tempendyear = period2.getYear();

		}
		if (!tempyear.equals(tempendyear)) {
			vTemp.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000117")/* @res "年度合计" */);
		}
		if (vTemp.size() != 0 && (!vTemp.lastElement().equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000119")/* @res "币种累计" */)
				&& !vTemp.lastElement().equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */)
				&& !vTemp.lastElement().equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/))) {
			vTemp.removeElementAt(vTemp.size() - 1);
		}
		return vTemp;
	}

	/**
	 * @param strCurrTypeName
	 * @param glQryObjs
	 * @return
	 */
	private String[][] getSummary(String strCurrTypeName, GLQueryObj[] glQryObjs) {
		int iCorpDispPos = m_qryVO.getFormatVO().getCorpDispLocation();

		java.util.Vector<String> vInitSummary = new java.util.Vector<String>();
		java.util.Vector<String> vSummary = new java.util.Vector<String>();
		int flag = 0;
		if (iCorpDispPos == 1 && !m_qryVO.isMultiCorpCombine() && m_qryVO.getpk_accountingbook().length > 1) {
			vInitSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000459")/* @res "期初余额" */);
			vSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */);
		}

		if (isBuSupport()) {
			vInitSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000459")/* @res "期初余额" */);
			vSummary.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/);
		}
		// 所有币种
		if (CurrTypeConst.ALL_CURRTYPE().equals(strCurrTypeName)) {
			vInitSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000459")/* @res "期初余额" */);
			vSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000119")/* @res "币种累计" */);
		}
		for (int i = 0; i < glQryObjs.length; i++) {
			vInitSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000540")/* @res "期初余额 " */);
			if (glQryObjs[i].getType().equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031")/*@res "会计科目"*/))
				vSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000121")/* @res "科目小计" */);
			else {
				vInitSummary.insertElementAt(null, flag);
				vSummary.insertElementAt(null, flag);
				flag++;
				vSummary.add(glQryObjs[i].getType() + NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000122")/* @res "小计" */);
			}
		}
		if (iCorpDispPos == 0 && !m_qryVO.isMultiCorpCombine() && m_qryVO.getpk_accountingbook().length > 1) {
			vInitSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000459")/* @res "期初余额" */);
			vSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000120")/* @res "公司小计" */);
		}
		// year
		if (!GlQueryVOBookTool.isSameYear(m_qryVO)) {
			vInitSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000459")/* @res "期初余额" */);
			vSummary.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000117")/* @res "年度合计" */);
		}
		String[][] strSummarys = new String[2][vInitSummary.size()];
		for (int i = 0; i < vInitSummary.size(); i++) {
			strSummarys[0][i] = vInitSummary.elementAt(i) == null ? null : vInitSummary.elementAt(i).toString();
			strSummarys[1][i] = vSummary.elementAt(i) == null ? null : vSummary.elementAt(i).toString();
		}
		return strSummarys;
	}

	/**
	 * @param p_vos
	 * @return
	 * @throws Exception
	 */
	private BalanceSubjAssBSVO[] addSumAllMark(BalanceSubjAssBSVO[] p_vos) throws Exception {
		if (p_vos == null || p_vos.length == 0)
			return null;
		int iSumAllIndex = p_vos.length - 1;
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_Mark, NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000115")/* @res "总计" */);
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_accsubjname, "");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.k_pk_accasoa, "");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_GLORGNAME, "");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_PK_AccountingBook, "9999");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_pk_currtype, "99999999999999999999");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_CurType, "");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_AssVOs, "");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_bothOrient, "true");
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_InitOrient, null);
		p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_EndOrient, null);
		if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName())) {
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_InitDebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_InitCreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_DebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_CreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_DebitAccumAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_CreditAccumAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_EndDebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_EndCreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_InitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
			p_vos[iSumAllIndex].setValue(BalanceSubjAssBSKey.K_EndAmount, ZeroUFdoubleConstant.DFDB_ZERO);
		}

		// 如果是跨年查询，清除所有的小计、合计的期初、期末

		String tempyear = null;
		String tempendyear = null;
		if (m_qryVO.isQueryByPeriod()) {
			tempyear = m_qryVO.getYear();
			tempendyear = m_qryVO.getEndYear();
		} else {
			nc.vo.pub.lang.UFDate date1 = m_qryVO.getDate();
			nc.vo.pub.lang.UFDate date2 = m_qryVO.getEndDate();

			nc.vo.glcom.glperiod.GlPeriodVO period1 = null, period2 = null;
			try {

				period1 = GLPeriodDataCatch.getInstance().getGLperiodVOByDate(m_qryVO.getpk_accountingbook()[0], date1);
				period2 = GLPeriodDataCatch.getInstance().getGLperiodVOByDate(m_qryVO.getpk_accountingbook()[0], date2);

			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}

			tempyear = period1.getYear();
			tempendyear = period2.getYear();
		}
		if (!tempyear.equals(tempendyear)) {
			for (int i = 0; i < p_vos.length; i++) {
				if (p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark) != null) {
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitDebitQuant, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitDebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitDebitAuxAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitDebitLocAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitCreditQuant, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitCreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitCreditAuxAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitCreditLocAmount, ZeroUFdoubleConstant.DFDB_ZERO);

					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndDebitQuant, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndDebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndDebitAuxAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndDebitLocAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndCreditQuant, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndCreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndCreditAuxAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndCreditLocAmount, ZeroUFdoubleConstant.DFDB_ZERO);

					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitOrient, null);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndOrient, null);

					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitQuant, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitAuxAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitLocAmount, ZeroUFdoubleConstant.DFDB_ZERO);

					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndQuant, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndAuxAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndLocAmount, ZeroUFdoubleConstant.DFDB_ZERO);
				}
			}
		} else {
			for (int i = 0; i < p_vos.length; i++) {
				if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName()) && p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark) != null && p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000117")/* @res "年度合计" */)) {
					p_vos[i].setValue(BalanceSubjAssBSKey.K_pk_currtype, "99999999999999999998");
					p_vos[i].setValue(BalanceSubjAssBSKey.K_CurType, "");
				}
				if (p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark) != null && p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */))
					p_vos[i].setValue(BalanceSubjAssBSKey.K_CurType, "");
				if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName())
						&& p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark) != null
						&& (p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000471")/* @res "主体帐簿累计" */)
							|| p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark).equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/))) {
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitDebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitCreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_DebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_CreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_DebitAccumAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_CreditAccumAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndDebitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndCreditAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_InitAmount, ZeroUFdoubleConstant.DFDB_ZERO);
					p_vos[i].setValue(BalanceSubjAssBSKey.K_EndAmount, ZeroUFdoubleConstant.DFDB_ZERO);
				}
			}
		}
		for (int i = 0; i < p_vos.length; i++) {
			if (nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("private20111017_0","02002001-0092")/*@res "业务单元累计"*/.equals(p_vos[i].getValue(BalanceSubjAssBSKey.K_Mark))) {
				p_vos[i].setValue(BalanceSubjAssBSKey.K_pk_currtype, null);
				p_vos[i].setValue(BalanceSubjAssBSKey.K_CurType, null);
			}
		}
		return p_vos;
	}

	/**
	 * @param vos
	 * @param p_qryVO
	 * @return
	 */
	private BalanceSubjAssBSVO[] filterByBalanceOrientAndAmount(BalanceSubjAssBSVO[] vos, GlQueryVO p_qryVO) {
		try {
			if (vos == null)
				return null;
			int orient = p_qryVO.getFormatVO().getBalanceOrient();
			if (p_qryVO.getFormatVO().getBalanceRangeFrom() == null)
				return vos;
			double dblRangeFrom = p_qryVO.getFormatVO().getBalanceRangeFrom().doubleValue();
			double dblRangeTo = p_qryVO.getFormatVO().getBalanceRangeTo().doubleValue();
			if (vos == null || vos.length == 0 || (orient == Balanorient.TWOWAY && dblRangeFrom == 0.0 && dblRangeTo == 0.0))
				return vos;
			Vector result = new Vector();
			for (int i = 0; i < vos.length; i++) {
				double locValue = vos[i].getEndLocAmount() == null ? 0.0 : vos[i].getEndLocAmount().doubleValue();
				int endOri = vos[i].getEndOrient().intValue();
				/** 借方过滤 */
				if (orient == Balanorient.DEBIT) {
					if (endOri == Balanorient.DEBIT) {
						if ((dblRangeFrom == 0 && dblRangeTo == 0)) {
							result.addElement(vos[i]);
						} else {
							if (locValue >= dblRangeFrom && locValue <= dblRangeTo) {
								result.addElement(vos[i]);
							}
						}
					}
				} else /** 贷方过滤 */if (orient == Balanorient.CREDIT) {
					if (endOri == Balanorient.CREDIT) {
						if ((dblRangeFrom == 0 && dblRangeTo == 0)) {
							result.addElement(vos[i]);
						} else {
							if (locValue >= dblRangeFrom && locValue <= dblRangeTo) {
								result.addElement(vos[i]);
							}
						}
					}
				} else /** 平过滤 */if (orient ==Balanorient.EQUAL) {
					if (endOri == Balanorient.EQUAL) {
						result.addElement(vos[i]);
					}
				} else {
					/** 双方过滤 */
					if ((dblRangeFrom == 0 && dblRangeTo == 0)) {
						result.addElement(vos[i]);
					} else {
						if (locValue >= dblRangeFrom && locValue <= dblRangeTo) {
							result.addElement(vos[i]);
						}
					}
				}
			}
			vos = new BalanceSubjAssBSVO[result.size()];
			result.copyInto(vos);
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		}
		return vos;
	}

	private int[] getSortIndex(int iType) {
		int intSortIndex[] = null;
		String strCurrTypeName = m_qryVO.getCurrTypeName();
		GLQueryObj[] glQryObjs = m_qryVO.getFormatVO().getQryObjs();
		java.util.Vector<Integer> vTemp = new java.util.Vector<Integer>();
		// 0表示显示在查询对象之后,1为前
		int iCorpDispPos = m_qryVO.getFormatVO().getCorpDispLocation();
		// 跨年
		// 多单位
		if (iCorpDispPos == 1 && !m_qryVO.isMultiCorpCombine() && m_qryVO.getpk_accountingbook().length > 1) {
			vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_PK_AccountingBook));
		}
		//多业务单元
		if(isBuSupport()){
			vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_PK_UNIT));
		}
		// 所有币种
		if (CurrTypeConst.ALL_CURRTYPE().equals(strCurrTypeName)) {
			vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_pk_currtype));
		}

		int flag = 0;
		int adjustValue = 0;
		for (int i = 0; i < glQryObjs.length; i++) {
			if (glQryObjs[i].getType().equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031")/*@res "会计科目"*/))
				adjustValue--;
			if (glQryObjs[i].getType().equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031")/*@res "会计科目"*/)) {
				if (iType == 0) {
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_accsubjcode));
				} else if (iType == 1) {
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_accsubjname));
				}
			} else {
				if ((i + adjustValue) == 0) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass0Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass0InnerCode));
				} else if ((i + adjustValue) == 1) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass1Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass1InnerCode));
				} else if ((i + adjustValue) == 2) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass2Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass2InnerCode));
				} else if ((i + adjustValue) == 3) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass3Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass3InnerCode));
				} else if ((i + adjustValue) == 4) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass4Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass4InnerCode));
				} else if ((i + adjustValue) == 5) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass5Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass5InnerCode));
				} else if ((i + adjustValue) == 6) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass6Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass6InnerCode));
				} else if ((i + adjustValue) == 7) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass7Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass7InnerCode));
				} else if ((i + adjustValue) == 8) {
					vTemp.insertElementAt(Integer.valueOf(BalanceSubjAssBSKey.K_ass8Property), flag);
					flag++;
					vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_Ass8InnerCode));
				}
			}
		}
		if (iCorpDispPos == 0 && !m_qryVO.isMultiCorpCombine() && m_qryVO.getpk_accountingbook().length > 1) {
			vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_PK_AccountingBook));
		}
		// 跨年
		String tempyear = null;
		String tempendyear = null;
		if (m_qryVO.isQueryByPeriod()) {
			tempyear = m_qryVO.getYear();
			tempendyear = m_qryVO.getEndYear();
		} else {
			nc.vo.pub.lang.UFDate date1 = m_qryVO.getDate();
			nc.vo.pub.lang.UFDate date2 = m_qryVO.getEndDate();
			nc.vo.glcom.glperiod.GlPeriodVO period1 = null, period2 = null;
			try {
				period1 = GLPeriodDataCatch.getInstance().getGLperiodVOByDate(m_qryVO.getpk_accountingbook()[0], date1);
				period2 = GLPeriodDataCatch.getInstance().getGLperiodVOByDate(m_qryVO.getpk_accountingbook()[0], date2);
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}
			tempyear = period1.getYear();
			tempendyear = period2.getYear();
		}
		if (!tempyear.equals(tempendyear)) {
			vTemp.add(Integer.valueOf(BalanceSubjAssBSKey.K_year));
		}
		intSortIndex = new int[vTemp.size()];
		for (int i = 0; i < vTemp.size(); i++) {
			intSortIndex[i] = ((Integer) vTemp.elementAt(i)).intValue();
		}
		return intSortIndex;
	}
	
	//判断是否支持业务单元
	private boolean isBuSupport(){
		try {
			if(m_qryVO.getpk_accountingbook().length==1
					&&GLParaAccessor.isSecondBUStart(m_qryVO.getBaseAccountingbook()).booleanValue()
						&&!m_qryVO.isMultiBusi()){
				return true;
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			Logger.error(e.getMessage(),e);
		}
		return false;
	}
	/**
	 * @param vos
	 * @return
	 * @throws Exception
	 */
	private BalanceSubjAssBSVO[] computeBalance(BalanceSubjAssBSVO[] vos) throws Exception {
		Vector vecVO = new Vector();
		if (vos == null || vos.length == 0)
			return null;
		for (int i = 0; i < vos.length; i++) {
			vecVO.addElement(vos[i]);
		}
		nc.vo.glcom.inteltool.CGenTool genTool = new nc.vo.glcom.inteltool.CGenTool();
		int[] intSortIndex; // = getSortIndex();
		int intSumLimit = 0;

		if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName())) {
			if (m_qryVO.isMultiCorpCombine() || m_qryVO.getpk_accountingbook().length == 1) {
				intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_pk_currtype, BalanceSubjAssBSKey.K_AssVOs };
			} else {
				intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_PK_AccountingBook, BalanceSubjAssBSKey.K_pk_currtype, BalanceSubjAssBSKey.K_AssVOs };
			}
		} else {
			if (m_qryVO.isMultiCorpCombine() || m_qryVO.getpk_accountingbook().length == 1) {
				intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_AssVOs };
			} else {
				intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_PK_AccountingBook, BalanceSubjAssBSKey.K_AssVOs };
			}
		}

		genTool.setSortIndex(intSortIndex);
		genTool.setLimitSumGen(intSumLimit);
		genTool.setUpSumGen(0);
		UFDCBalanceTool balanceTool = new UFDCBalanceTool();
		int[] balanceIndex;
		String[] relateExpression;
		balanceIndex = new int[] { BalanceSubjAssBSKey.K_EndDebitQuant, BalanceSubjAssBSKey.K_EndDebitAmount,
				BalanceSubjAssBSKey.K_EndDebitAuxAmount, BalanceSubjAssBSKey.K_EndDebitLocAmount, BalanceSubjAssBSKey.K_EndCreditQuant,
				BalanceSubjAssBSKey.K_EndCreditAmount, BalanceSubjAssBSKey.K_EndCreditAuxAmount, BalanceSubjAssBSKey.K_EndCreditLocAmount };
		// 确定计算余额的列号
		// 如果>1000 则从相应的上一行取方向标志位,如果在0与1000之间,则取本行的相应方向标志位
		// 如果<0,则不考虑标志位
		relateExpression = new String[] { "[" + BalanceSubjAssBSKey.K_InitDebitQuant + "]+[" + BalanceSubjAssBSKey.K_DebitQuant + "]",
				"[" + BalanceSubjAssBSKey.K_InitDebitAmount + "]+[" + BalanceSubjAssBSKey.K_DebitAmount + "]",
				"[" + BalanceSubjAssBSKey.K_InitDebitAuxAmount + "]+[" + BalanceSubjAssBSKey.K_DebitAuxAmount + "]",
				"[" + BalanceSubjAssBSKey.K_InitDebitLocAmount + "]+[" + BalanceSubjAssBSKey.K_DebitLocAmount + "]",
				"[" + BalanceSubjAssBSKey.K_InitCreditQuant + "]+[" + BalanceSubjAssBSKey.K_CreditQuant + "]",
				"[" + BalanceSubjAssBSKey.K_InitCreditAmount + "]+[" + BalanceSubjAssBSKey.K_CreditAmount + "]",
				"[" + BalanceSubjAssBSKey.K_InitCreditAuxAmount + "]+[" + BalanceSubjAssBSKey.K_CreditAuxAmount + "]",
				"[" + BalanceSubjAssBSKey.K_InitCreditLocAmount + "]+[" + BalanceSubjAssBSKey.K_CreditLocAmount + "]" };
		// 计算余额所涉及的列
		balanceTool.setBalanceIndex(balanceIndex);
		balanceTool.setRelateIndex(relateExpression);

		nc.vo.glcom.inteltool.COutputTool outputTool = new nc.vo.glcom.inteltool.COutputTool();
		nc.vo.glcom.inteltool.CDataSource datasource = new nc.vo.glcom.inteltool.CDataSource();
		datasource.setSumVector(nc.vo.glcom.inteltool.CDataSource.sortVector(vecVO, genTool, false));
		nc.vo.glcom.intelvo.CIntelVO tt = new nc.vo.glcom.intelvo.CIntelVO();
		try {
			if (intSortIndex != null)
				tt.setGenTool(genTool);
			tt.setBalanceTool(balanceTool);
			tt.setDatasource(datasource);
			tt.setOutputTool(outputTool);
			Vector recVector = tt.getResultVector();
			vos = new BalanceSubjAssBSVO[recVector.size()];
			recVector.copyInto(vos);
			return vos;
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * @param vos
	 * @param p_qryVO
	 * @return
	 */
	public BalanceSubjAssBSVO[] filterOfHasBalanceButNoOccur(BalanceSubjAssBSVO[] vos, GlQueryVO p_qryVO) {
		try {
			vos=nc.vo.gl.glreport.publictool.FilterBalanceOccurTool.filterOfHasBalanceButNoOccur(vos, p_qryVO);
		} catch (Exception e) {
			Logger.error(e);
		}
		return vos;
	}

	/**
	 * @param param
	 * @param p_qryVO
	 * @return
	 * @throws java.lang.Exception
	 */
	private BalanceSubjAssBSVO[] appendAccInfo(BalanceSubjAssBSVO[] param, GlQueryVO p_qryVO) throws java.lang.Exception {
		// 拼接科目信息，由于数据中只包含科目ID,因此需要补充科目Name和科目Code信息
		if (param != null && param.length != 0) {
			Set<String> pkaccsubjSet = new HashSet<String>();
			for (int i = 0; i < param.length; i++) {
				pkaccsubjSet.add(param[i].getValue(BalanceSubjAssBSKey.k_pk_accasoa).toString());
			}
			AccountVO[] AccountVOs = new AccountVO[pkaccsubjSet.size()];
			String[] subjs = new String[pkaccsubjSet.size()];
			pkaccsubjSet.toArray(subjs);
			AccountVOs = AccountUtilGL.queryByPks(subjs, p_qryVO.getSubjVersion());
			Map<String, AccountVO> subjMap = new HashMap<String, AccountVO>();
			for (int i = 0; i < AccountVOs.length; i ++) {
				subjMap.put(AccountVOs[i].getPk_accasoa(), AccountVOs[i]);
			}
			for (int i = 0; i < param.length; i++) {
				String sAcc = param[i].getValue(BalanceSubjAssBSKey.k_pk_accasoa).toString();
				AccountVO aAccountVO = subjMap.get(sAcc);
				param[i].setValue(BalanceSubjAssBSKey.K_accOrient, aAccountVO.getBalanorient());
				param[i].setValue(BalanceSubjAssBSKey.K_bothOrient, aAccountVO.getBothorient());
				param[i].setValue(BalanceSubjAssBSKey.K_accsubjcode, aAccountVO.getCode());
				if(p_qryVO.getFormatVO().isAssBalIncludeChildSubj()){
					StringBuffer sb = new StringBuffer();
					for(int lvl=1;lvl<aAccountVO.getAcclev();lvl++){
						sb.append("    ");
					}
					param[i].setValue(BalanceSubjAssBSKey.K_accsubjname, sb.toString()+GLMultiLangUtil.getMultiDispName(aAccountVO));
				}else{
					param[i].setValue(BalanceSubjAssBSKey.K_accsubjname, GLMultiLangUtil.getMultiDispName(aAccountVO));
				}
			}
			return param;
		}
		return null;
	}

	private BalanceSubjAssBSVO[] appendAccumVOs(BalanceSubjAssBSVO[] bsvos, GlBalanceVO[] glvos, int locCurrType) throws Exception {
		nc.vo.glcom.wizard.VoWizard wizard = new nc.vo.glcom.wizard.VoWizard();
		nc.vo.glcom.sorttool.CVoSortTool sortTool = new nc.vo.glcom.sorttool.CVoSortTool();
		sortTool.setGetSortTool(this);
		wizard.setSortTool(sortTool);
		if (glvos != null && glvos.length != 0) {
			int[] intIndexBalance = new int[] {
					BalanceSubjAssBSKey.k_pk_accasoa,
					BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_AssID,
					BalanceSubjAssBSKey.K_PK_AccountingBook,
					BalanceSubjAssBSKey.K_PK_UNIT,
					BalanceSubjAssBSKey.K_CurType };
			int[] intIndexInit = new int[] {
					GlBalanceKey.GLBALANCE_PK_ACCASOA,
					GlBalanceKey.GLBALANCE_YEAR,
					GlBalanceKey.GLBALANCE_ASSID,
					GlBalanceKey.GLBALANCE_PK_ACCOUNTINGBOOK,
					GlBalanceKey.GLBALANCE_BUSIUNIT,
					GlBalanceKey.GLBALANCE_PK_CURRTYPE };
			wizard.setMatchingIndex(intIndexBalance, intIndexInit);
			wizard
					.setAppendIndex(
							new int[] {
									BalanceSubjAssBSKey.K_year,
									BalanceSubjAssBSKey.K_AssID,
									BalanceSubjAssBSKey.K_PK_AccountingBook,
									BalanceSubjAssBSKey.K_PK_UNIT,
									BalanceSubjAssBSKey.K_pk_currtype,
									BalanceSubjAssBSKey.k_pk_accasoa,
									BalanceSubjAssBSKey.K_accOrient,
									BalanceSubjAssBSKey.K_bothOrient,
									BalanceSubjAssBSKey.K_AssVOs,
									BalanceSubjAssBSKey.K_GLORGNAME,
									BalanceSubjAssBSKey.K_CurType,
									BalanceSubjAssBSKey.K_DebitAccumQuant,
									BalanceSubjAssBSKey.K_DebitAccumAmount,
									BalanceSubjAssBSKey.K_DebitAccumAuxAmount,
									BalanceSubjAssBSKey.K_DebitAccumLocAmount,
									BalanceSubjAssBSKey.K_DebitAccumGroupAmount,
									BalanceSubjAssBSKey.K_DebitAccumGlobalAmount,
									BalanceSubjAssBSKey.K_CreditAccumQuant,
									BalanceSubjAssBSKey.K_CreditAccumAmount,
									BalanceSubjAssBSKey.K_CreditAccumAuxAmount,
									BalanceSubjAssBSKey.K_CreditAccumLocAmount,
									BalanceSubjAssBSKey.K_CreditAccumGroupAmount,
									BalanceSubjAssBSKey.K_CreditAccumGlobalAmount },
							new int[] {
									GlBalanceKey.GLBALANCE_YEAR,
									GlBalanceKey.GLBALANCE_ASSID,
									GlBalanceKey.GLBALANCE_PK_ACCOUNTINGBOOK/* GLBALANCE_PK_ACCASOA */,
									GlBalanceKey.GLBALANCE_BUSIUNIT,
									GlBalanceKey.GLBALANCE_PK_CURRTYPE,
									GlBalanceKey.GLBALANCE_PK_ACCASOA,
									GlBalanceKey.GLBALANCE_ACCORIENT,
									GlBalanceKey.GLBALANCE_BOTHORITEN,
									GlBalanceKey.GLBALANCE_ASSVOS,
									GlBalanceKey.GLBALANCE_GLORGNAME,
									GlBalanceKey.GLBALANCE_PK_CURRTYPE,
									GlBalanceKey.GLBALANCE_DEBITQUANTITY,
									GlBalanceKey.GLBALANCE_DEBITAMOUNT,
									GlBalanceKey.GLBALANCE_FRACDEBITAMOUNT,
									//GlBalanceKey.GLBALANCE_LOCALDEBITAMOUNT,
									GlBalanceKey.getLocDebitKey(locCurrType),
									GlBalanceKey.GLBALANCE_DEBITGROUPAMOUNT,
									GlBalanceKey.GLBALANCE_DEBITGLOBALAMOUNT,
									GlBalanceKey.GLBALANCE_CREDITQUANTITY,
									GlBalanceKey.GLBALANCE_CREDITAMOUNT,
									GlBalanceKey.GLBALANCE_FRACCREDITAMOUNT,
									//GlBalanceKey.GLBALANCE_LOCALCREDITAMOUNT,
									GlBalanceKey.getLocCreditKey(locCurrType),
									GlBalanceKey.GLBALANCE_CREDITGROUPAMOUNT,
									GlBalanceKey.GLBALANCE_CREDITGLOBALAMOUNT });
			wizard.setLeftClass(BalanceSubjAssBSVO.class);
			// 期初设置
			Object[] obj = wizard.concat(bsvos, glvos, true);
			bsvos = new BalanceSubjAssBSVO[obj.length];
			System.arraycopy(obj, 0, bsvos, 0, obj.length);
		}
		//processBusiUnit(bsvos);
		return bsvos;
	}
	/**
	 * 当科目末级非末级同时出现在一个页面的时候， 币种累计，业务单元累计，核算账簿累计，总计 这些行会出现计算多次，这边来调整
	 * @param bsvos
	 * @param glvos
	 * @param locCurrType
	 * @return
	 * @throws Exception
	 */
	private BalanceSubjAssBSVO[] appendSumVOs(BalanceSubjAssBSVO[] bsvos, BalanceSubjAssBSVO[] glvos) throws Exception {
		nc.vo.glcom.wizard.VoWizard wizard = new nc.vo.glcom.wizard.VoWizard();
		nc.vo.glcom.sorttool.CVoSortTool sortTool = new nc.vo.glcom.sorttool.CVoSortTool();
		sortTool.setGetSortTool(this);
		wizard.setSortTool(sortTool);
		List<Integer> catKey = new ArrayList<Integer>();
		if (glvos != null && glvos.length != 0) {
			GLQueryObj[] glQryObjs = m_qryVO.getFormatVO().getQryObjs();
			int adjustValue = 0;
			catKey.add(BalanceSubjAssBSKey.K_PK_AccountingBook);
			catKey.add(BalanceSubjAssBSKey.K_PK_UNIT);
			catKey.add(BalanceSubjAssBSKey.K_CurType);
			catKey.add(BalanceSubjAssBSKey.K_Mark);
			// add by pangjsh --加入科目维度，否则辅助核算小计有问题(丰盛项目问题)
			catKey.add(BalanceSubjAssBSKey.K_accsubjcode);
			for (int i = 0; i < glQryObjs.length; i++) {
				if (!glQryObjs[i].getType().equals(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20023030", "UPP20023030-000031")/*@res "会计科目"*/)) {
					if ((i + adjustValue) == 0) {
						catKey.add(BalanceSubjAssBSKey.K_ass0Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass0InnerCode);
					} else if ((i + adjustValue) == 1) {
						catKey.add(BalanceSubjAssBSKey.K_ass1Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass1InnerCode);
					} else if ((i + adjustValue) == 2) {
						catKey.add(BalanceSubjAssBSKey.K_ass2Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass2InnerCode);
					} else if ((i + adjustValue) == 3) {
						catKey.add(BalanceSubjAssBSKey.K_ass3Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass3InnerCode);
					} else if ((i + adjustValue) == 4) {
						catKey.add(BalanceSubjAssBSKey.K_ass4Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass4InnerCode);
					} else if ((i + adjustValue) == 5) {
						catKey.add(BalanceSubjAssBSKey.K_ass5Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass5InnerCode);
					} else if ((i + adjustValue) == 6) {
						catKey.add(BalanceSubjAssBSKey.K_ass6Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass6InnerCode);
					} else if ((i + adjustValue) == 7) {
						catKey.add(BalanceSubjAssBSKey.K_ass7Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass7InnerCode);
					} else if ((i + adjustValue) == 8) {
						catKey.add(BalanceSubjAssBSKey.K_ass8Property);
						catKey.add(BalanceSubjAssBSKey.K_Ass8InnerCode);
					}
				}else{
					adjustValue--;
				}
			}
			int [] sortIndex = new int[catKey.size()];
			for(int i=0;i<sortIndex.length;i++){
				sortIndex[i] = catKey.get(i);
			}
			wizard.setMatchingIndex(sortIndex, sortIndex);
			wizard
					.setAppendIndex(
							new int[] {
									
									BalanceSubjAssBSKey.K_DebitQuant,
									BalanceSubjAssBSKey.K_DebitAmount,
									BalanceSubjAssBSKey.K_DebitLocAmount,
									BalanceSubjAssBSKey.K_DebitGroupAmount,
									BalanceSubjAssBSKey.K_DebitGlobalAmount,
									BalanceSubjAssBSKey.K_CreditQuant,
									BalanceSubjAssBSKey.K_CreditAmount,
									BalanceSubjAssBSKey.K_CreditLocAmount,
									BalanceSubjAssBSKey.K_CreditGroupAmount,
									BalanceSubjAssBSKey.K_CreditGlobalAmount,
									
									BalanceSubjAssBSKey.K_DebitAccumQuant,
									BalanceSubjAssBSKey.K_DebitAccumAmount,
									BalanceSubjAssBSKey.K_DebitAccumLocAmount,
									BalanceSubjAssBSKey.K_DebitAccumGroupAmount,
									BalanceSubjAssBSKey.K_DebitAccumGlobalAmount,
									BalanceSubjAssBSKey.K_CreditAccumQuant,
									BalanceSubjAssBSKey.K_CreditAccumAmount,
									BalanceSubjAssBSKey.K_CreditAccumLocAmount,
									BalanceSubjAssBSKey.K_CreditAccumGroupAmount,
									BalanceSubjAssBSKey.K_CreditAccumGlobalAmount,
									
									BalanceSubjAssBSKey.K_InitDebitQuant,
									BalanceSubjAssBSKey.K_InitDebitAmount,
									BalanceSubjAssBSKey.K_InitDebitLocAmount,
									BalanceSubjAssBSKey.K_InitDebitGroupAmount,
									BalanceSubjAssBSKey.K_InitDebitGlobalAmount,
									BalanceSubjAssBSKey.K_InitCreditQuant,
									BalanceSubjAssBSKey.K_InitCreditAmount,
									BalanceSubjAssBSKey.K_InitCreditLocAmount,
									BalanceSubjAssBSKey.K_InitCreditGroupAmount,
									BalanceSubjAssBSKey.K_InitCreditGlobalAmount,
									
									BalanceSubjAssBSKey.K_EndDebitQuant,
									BalanceSubjAssBSKey.K_EndDebitAmount,
									BalanceSubjAssBSKey.K_EndDebitLocAmount,
									BalanceSubjAssBSKey.K_EndDebitGroupAmount,
									BalanceSubjAssBSKey.K_EndDebitGlobalAmount,
									BalanceSubjAssBSKey.K_EndCreditQuant,
									BalanceSubjAssBSKey.K_EndCreditAmount,
									BalanceSubjAssBSKey.K_EndCreditLocAmount,
									BalanceSubjAssBSKey.K_EndCreditGroupAmount,
									BalanceSubjAssBSKey.K_EndCreditGlobalAmount},
							new int[] {
									
									BalanceSubjAssBSKey.K_DebitQuant,
									BalanceSubjAssBSKey.K_DebitAmount,
									BalanceSubjAssBSKey.K_DebitLocAmount,
									BalanceSubjAssBSKey.K_DebitGroupAmount,
									BalanceSubjAssBSKey.K_DebitGlobalAmount,
									BalanceSubjAssBSKey.K_CreditQuant,
									BalanceSubjAssBSKey.K_CreditAmount,
									BalanceSubjAssBSKey.K_CreditLocAmount,
									BalanceSubjAssBSKey.K_CreditGroupAmount,
									BalanceSubjAssBSKey.K_CreditGlobalAmount,
									
									BalanceSubjAssBSKey.K_DebitAccumQuant,
									BalanceSubjAssBSKey.K_DebitAccumAmount,
									BalanceSubjAssBSKey.K_DebitAccumLocAmount,
									BalanceSubjAssBSKey.K_DebitAccumGroupAmount,
									BalanceSubjAssBSKey.K_DebitAccumGlobalAmount,
									BalanceSubjAssBSKey.K_CreditAccumQuant,
									BalanceSubjAssBSKey.K_CreditAccumAmount,
									BalanceSubjAssBSKey.K_CreditAccumLocAmount,
									BalanceSubjAssBSKey.K_CreditAccumGroupAmount,
									BalanceSubjAssBSKey.K_CreditAccumGlobalAmount,
									
									BalanceSubjAssBSKey.K_InitDebitQuant,
									BalanceSubjAssBSKey.K_InitDebitAmount,
									BalanceSubjAssBSKey.K_InitDebitLocAmount,
									BalanceSubjAssBSKey.K_InitDebitGroupAmount,
									BalanceSubjAssBSKey.K_InitDebitGlobalAmount,
									BalanceSubjAssBSKey.K_InitCreditQuant,
									BalanceSubjAssBSKey.K_InitCreditAmount,
									BalanceSubjAssBSKey.K_InitCreditLocAmount,
									BalanceSubjAssBSKey.K_InitCreditGroupAmount,
									BalanceSubjAssBSKey.K_InitCreditGlobalAmount,
									
									BalanceSubjAssBSKey.K_EndDebitQuant,
									BalanceSubjAssBSKey.K_EndDebitAmount,
									BalanceSubjAssBSKey.K_EndDebitLocAmount,
									BalanceSubjAssBSKey.K_EndDebitGroupAmount,
									BalanceSubjAssBSKey.K_EndDebitGlobalAmount,
									BalanceSubjAssBSKey.K_EndCreditQuant,
									BalanceSubjAssBSKey.K_EndCreditAmount,
									BalanceSubjAssBSKey.K_EndCreditLocAmount,
									BalanceSubjAssBSKey.K_EndCreditGroupAmount,
									BalanceSubjAssBSKey.K_EndCreditGlobalAmount});
			wizard.setLeftClass(BalanceSubjAssBSVO.class);
			Object[] obj = wizard.concat(bsvos, glvos, false);
			bsvos = new BalanceSubjAssBSVO[obj.length];
			System.arraycopy(obj, 0, bsvos, 0, obj.length);
		}
		return bsvos;
	}


	/**
	 * @param vos
	 * @param strType
	 * @return
	 * @throws Exception
	 */
	private BalanceSubjAssBSVO[] combineAss(BalanceSubjAssBSVO[] vos, String[] strType) throws Exception {
		if (vos == null || vos.length == 0)
			return vos;

		if (strType == null)
			strType = new String[0];

		/** 先对查询结果辅助项进行处理，格式化成和查询VO一致的辅助项个数 */
		for (int i = 0; i < vos.length; i++) {
			nc.vo.glcom.ass.AssVO[] formAss = new nc.vo.glcom.ass.AssVO[strType.length];
			for (int j = 0; j < strType.length; j++) {
				for (int k = 0; k < vos[i].getAssVOs().length; k++) {
					if (vos[i].getAssVOs()[k].getPk_Checktype().equals(strType[j]))
						formAss[j] = vos[i].getAssVOs()[k];
				}
			}
			vos[i].setValue(BalanceSubjAssBSKey.K_AssVOs, formAss);
		}
		nc.vo.glcom.intelvo.CIntelVO tt = new nc.vo.glcom.intelvo.CIntelVO();
		// 在此不指定分组信息,则不分组合计
		int[] intSortIndex = null;
		int intSumLimit = 1;
		if (m_qryVO instanceof GlQueryVOEx) {
			// 指定合计的最小列号组合
			if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName())) {
				if (m_qryVO.isMultiCorpCombine()) {
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_pk_currtype };
					intSumLimit = 2;
				} else {
					if(!m_qryVO.isMultiBusi()&&GLParaAccessor.isSecondBUStart(m_qryVO.getBaseAccountingbook()).booleanValue()){
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_PK_AccountingBook, BalanceSubjAssBSKey.K_pk_currtype,BalanceSubjAssBSKey.K_PK_UNIT };
						intSumLimit = 4;
					}else{
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_PK_AccountingBook, BalanceSubjAssBSKey.K_pk_currtype };
						intSumLimit = 3;
					}
				}
			} else {
				if (m_qryVO.isMultiCorpCombine()) {
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_accsubjcode };
					intSumLimit = 1;
				} else {
					if(!m_qryVO.isMultiBusi()&&GLParaAccessor.isSecondBUStart(m_qryVO.getBaseAccountingbook()).booleanValue()){
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_PK_AccountingBook,BalanceSubjAssBSKey.K_PK_UNIT };
						intSumLimit = 3;
					}else{
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_PK_AccountingBook };
						intSumLimit = 2;
					}
				}
			}
			intSumLimit = intSumLimit - 1;
		} else {
			// 指定合计的最小列号组合
			if (CurrTypeConst.ALL_CURRTYPE().equals(m_qryVO.getCurrTypeName())) {
				if (m_qryVO.isMultiCorpCombine()) {
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode, BalanceSubjAssBSKey.K_pk_currtype };
					intSumLimit = 2;
				} else {
					if(isBuSupport()){
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
							, BalanceSubjAssBSKey.K_PK_AccountingBook, BalanceSubjAssBSKey.K_pk_currtype,BalanceSubjAssBSKey.K_PK_UNIT };
						intSumLimit = 4;
					}else{
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
								, BalanceSubjAssBSKey.K_PK_AccountingBook, BalanceSubjAssBSKey.K_pk_currtype };
							intSumLimit = 3;
					}
				}
			} else {
				if (m_qryVO.isMultiCorpCombine()) {
					intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode };
					intSumLimit = 1;
				} else {
					if(isBuSupport()){
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
							, BalanceSubjAssBSKey.K_PK_AccountingBook,BalanceSubjAssBSKey.K_PK_UNIT };
						intSumLimit = 3;
					}else{
						intSortIndex = new int[] { BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_accsubjcode
								, BalanceSubjAssBSKey.K_PK_AccountingBook};
							intSumLimit = 2;
					}
				}
			}
		}
		int iAssNum = m_qryVO.getAssVos() == null ? 0 : m_qryVO.getAssVos().length;
		int[] iAssSort = null;
		switch (iAssNum) {
		case 0:
			iAssSort = new int[0];
			break;
		case 1:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode };
			break;
		case 2:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode };
			break;
		case 3:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode, BalanceSubjAssBSKey.K_ass2Property, BalanceSubjAssBSKey.K_Ass2InnerCode };
			break;
		case 4:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode, BalanceSubjAssBSKey.K_ass2Property, BalanceSubjAssBSKey.K_Ass2InnerCode, BalanceSubjAssBSKey.K_ass3Property, BalanceSubjAssBSKey.K_Ass3InnerCode };
			break;
		case 5:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode, BalanceSubjAssBSKey.K_ass2Property, BalanceSubjAssBSKey.K_Ass2InnerCode, BalanceSubjAssBSKey.K_ass3Property, BalanceSubjAssBSKey.K_Ass3InnerCode, BalanceSubjAssBSKey.K_ass4Property, BalanceSubjAssBSKey.K_Ass4InnerCode };
			break;
		case 6:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode, BalanceSubjAssBSKey.K_ass2Property, BalanceSubjAssBSKey.K_Ass2InnerCode, BalanceSubjAssBSKey.K_ass3Property, BalanceSubjAssBSKey.K_Ass3InnerCode, BalanceSubjAssBSKey.K_ass4Property, BalanceSubjAssBSKey.K_Ass4InnerCode,
					BalanceSubjAssBSKey.K_ass5Property, BalanceSubjAssBSKey.K_Ass5InnerCode };
			break;
		case 7:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode, BalanceSubjAssBSKey.K_ass2Property, BalanceSubjAssBSKey.K_Ass2InnerCode, BalanceSubjAssBSKey.K_ass3Property, BalanceSubjAssBSKey.K_Ass3InnerCode, BalanceSubjAssBSKey.K_ass4Property, BalanceSubjAssBSKey.K_Ass4InnerCode,
					BalanceSubjAssBSKey.K_ass5Property, BalanceSubjAssBSKey.K_Ass5InnerCode, BalanceSubjAssBSKey.K_ass6Property, BalanceSubjAssBSKey.K_Ass6InnerCode };
			break;
		case 8:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode, BalanceSubjAssBSKey.K_ass2Property, BalanceSubjAssBSKey.K_Ass2InnerCode, BalanceSubjAssBSKey.K_ass3Property, BalanceSubjAssBSKey.K_Ass3InnerCode, BalanceSubjAssBSKey.K_ass4Property, BalanceSubjAssBSKey.K_Ass4InnerCode,
					BalanceSubjAssBSKey.K_ass5Property, BalanceSubjAssBSKey.K_Ass5InnerCode, BalanceSubjAssBSKey.K_ass6Property, BalanceSubjAssBSKey.K_Ass6InnerCode, BalanceSubjAssBSKey.K_ass7Property, BalanceSubjAssBSKey.K_Ass7InnerCode };
			break;
		case 9:
			iAssSort = new int[] { BalanceSubjAssBSKey.K_ass0Property, BalanceSubjAssBSKey.K_Ass0InnerCode, BalanceSubjAssBSKey.K_ass1Property, BalanceSubjAssBSKey.K_Ass1InnerCode, BalanceSubjAssBSKey.K_ass2Property, BalanceSubjAssBSKey.K_Ass2InnerCode, BalanceSubjAssBSKey.K_ass3Property, BalanceSubjAssBSKey.K_Ass3InnerCode, BalanceSubjAssBSKey.K_ass4Property, BalanceSubjAssBSKey.K_Ass4InnerCode,
					BalanceSubjAssBSKey.K_ass5Property, BalanceSubjAssBSKey.K_Ass5InnerCode, BalanceSubjAssBSKey.K_ass6Property, BalanceSubjAssBSKey.K_Ass6InnerCode, BalanceSubjAssBSKey.K_ass7Property, BalanceSubjAssBSKey.K_Ass7InnerCode, BalanceSubjAssBSKey.K_ass8Property, BalanceSubjAssBSKey.K_Ass8InnerCode };
			break;
		}
		if(iAssSort==null) return null;
		int[] intAll = new int[intSortIndex.length + iAssSort.length];
		System.arraycopy(intSortIndex, 0, intAll, 0, intSortIndex.length);
		System.arraycopy(iAssSort, 0, intAll, intSortIndex.length, iAssSort.length);
		intSumLimit = intSumLimit + iAssNum * 2;
		nc.vo.glcom.inteltool.CGenTool genTool = new nc.vo.glcom.inteltool.CGenTool();
		genTool.setLimitSumGen(intSumLimit);
		genTool.setSortIndex(intAll);
		genTool.setGetSortTool(this);
		nc.vo.glcom.inteltool.CSumTool sumTool = new nc.vo.glcom.inteltool.CSumTool();
		int sumIndex[] = new int[] { BalanceSubjAssBSKey.K_InitCreditAmount, BalanceSubjAssBSKey.K_InitCreditAuxAmount, BalanceSubjAssBSKey.K_InitCreditLocAmount, BalanceSubjAssBSKey.K_InitCreditQuant, BalanceSubjAssBSKey.K_InitDebitAmount, BalanceSubjAssBSKey.K_InitDebitAuxAmount, BalanceSubjAssBSKey.K_InitDebitLocAmount, BalanceSubjAssBSKey.K_InitDebitQuant, BalanceSubjAssBSKey.K_DebitQuant,
				BalanceSubjAssBSKey.K_DebitAmount, BalanceSubjAssBSKey.K_DebitAuxAmount, BalanceSubjAssBSKey.K_DebitLocAmount, BalanceSubjAssBSKey.K_CreditQuant, BalanceSubjAssBSKey.K_CreditAmount, BalanceSubjAssBSKey.K_CreditAuxAmount, BalanceSubjAssBSKey.K_CreditLocAmount, BalanceSubjAssBSKey.K_DebitAccumAmount, BalanceSubjAssBSKey.K_DebitAccumAuxAmount,
				BalanceSubjAssBSKey.K_DebitAccumLocAmount, BalanceSubjAssBSKey.K_DebitAccumQuant, BalanceSubjAssBSKey.K_CreditAccumAmount, BalanceSubjAssBSKey.K_CreditAccumAuxAmount, BalanceSubjAssBSKey.K_CreditAccumLocAmount, BalanceSubjAssBSKey.K_CreditAccumQuant };
		// 要进行合计的列
		sumTool.setSumIndex(sumIndex);
		nc.vo.glcom.inteltool.COutputTool outputTool = new nc.vo.glcom.inteltool.COutputTool();
		outputTool.setRequireOutputDetail(false);
		outputTool.setSummaryCol(-1); // 设置备注信息内容及所对应的列
		nc.vo.glcom.inteltool.CDataSource datasource = new nc.vo.glcom.inteltool.CDataSource();
		Vector vecVos = new Vector();
		for (int i = 0; i < vos.length; i++) {
			vos[i].setUserData(null);
			vecVos.addElement(vos[i]);
		}
		datasource.setSumVector(nc.vo.glcom.inteltool.CDataSource.sortVector(vecVos, genTool, false));
		try {
			tt.setSumTool(sumTool);
			tt.setGenTool(genTool);
			tt.setDatasource(datasource);
			tt.setOutputTool(outputTool);
		} catch (Throwable e) {
			Logger.error(e.getMessage(), e);
		}
		Vector recVector = tt.getResultVector();
		BalanceSubjAssBSVO[] VOs = new BalanceSubjAssBSVO[recVector.size()];
		recVector.copyInto(VOs);
		return VOs;
	}

	/**
	 * @param param
	 * @param p_qryVO
	 * @return
	 * @throws java.lang.Exception
	 */

	private BalanceSubjAssBSVO[] appendAssInfo(BalanceSubjAssBSVO[] param, GlQueryVO p_qryVO) throws java.lang.Exception {
		// 拼接辅助核算信息，由于数据中只包含辅助核算组合ID,因此需要补充具体的辅助核算组合信息
		if (param != null && param.length != 0) {
//			nc.ui.glcom.balance.GlAssDeal objTemp = new nc.ui.glcom.balance.GlAssDeal();
			// 从查询VO中取核算类型主键数组
			String[] strType = null;
			nc.vo.glcom.ass.AssVO[] assvos = p_qryVO.getAssVos();
			if (assvos != null && assvos.length != 0) {
				strType = new String[assvos.length];
				for (int i = 0; i < assvos.length; i++)
					strType[i] = assvos[i].getPk_Checktype();
			}
//			objTemp.setMatchingIndex(BalanceSubjAssBSKey.K_AssID);
//			objTemp.setAppendIndex(BalanceSubjAssBSKey.K_AssVOs);
//			objTemp.dealWith(param, strType); // 处理加工
			appendAssCodeAndName(param);
			adjustAssOrder(param);
			// 计算并生成辅助核算的innerCode
			Vector vMultiCorp = new Vector();
			Vector vTemp1 = new Vector();
			// 多单位合并且俩个单位以上，辅助核算分单位生成innerCode
			if (!p_qryVO.getFormatVO().isMultiCorpCombine() && p_qryVO.getpk_accountingbook().length > 1) {
				for (int i = 0; i < p_qryVO.getpk_accountingbook().length; i++) {
					for (int j = 0; j < param.length; j++) {
						if (param[j].getPk_glorgbook().equals(p_qryVO.getpk_accountingbook()[i])) {
							vTemp1.addElement(param[j]);
						}
					}
					if (vTemp1.size() >= 1) {
						BalanceSubjAssBSVO[] bsVosTemp = new BalanceSubjAssBSVO[vTemp1.size()];
						vTemp1.copyInto(bsVosTemp);
						CBalanceAssTool.buildAssInnerCode(bsVosTemp, getPk_corp(p_qryVO.getpk_accountingbook()[i]), BalanceSubjAssBSKey.K_AssVOs);
						for (int j = 0; j < bsVosTemp.length; j++) {
							vMultiCorp.addElement(bsVosTemp[j]);
						}
						vTemp1.removeAllElements();
					}
				}
				vMultiCorp.copyInto(param);
			} else {
				CBalanceAssTool.buildAssInnerCode(param, getPk_corp(p_qryVO.getBaseAccountingbook()), BalanceSubjAssBSKey.K_AssVOs);
			}
			if (!p_qryVO.getFormatVO().isIsAssIncldFatherSon()) {
				// 替换下级辅助项信息
				if (!topLevelAssInfo.isEmpty()) {
					replaceWhileAssSub(param);

				}
			}
			param = combineAss(param, strType);
			// 补空辅助项信息
			if (param != null)
				for (int i = 0; i < param.length; i++) {
					nc.vo.glcom.ass.AssVO[] assVos = (nc.vo.glcom.ass.AssVO[]) param[i].getValue(BalanceSubjAssBSKey.K_AssVOs);
					CAssTool.convertNull(assVos, new int[] { nc.vo.fipub.freevalue.AssKey.PK_CHECKVALUE, nc.vo.fipub.freevalue.AssKey.ASS_VALUECODE, nc.vo.fipub.freevalue.AssKey.ASS_VALUENAME }, "null");
				}
			return param;
		}
		return null;
	}
	
	private void appendAssCodeAndName(BalanceSubjAssBSVO[] param){
		//add by congjl 2010-12-14 为了得到辅助核算值的名称和编码
		AssCodeConstructor constructor = new AssCodeConstructor();
		//存放按classid分类，list中存value
		HashMap<String,List<String>> map = new HashMap<String,List<String>>();
//		IBDData data = null;
		//add end
		for(BalanceSubjAssBSVO vo : param){
			for(AssVO assvo : vo.getAssVOs()){
				 assvo.setM_classid(AccAssItemCache.getClassIdByAccassItemPK(assvo.getPk_Checktype()));
				 //add by zhaoshya  把辅助核算按classid分类，批量去查询
				 if(!map.containsKey(assvo.getM_classid())){
					 List<String> list = new ArrayList<String>();
					 list.add(assvo.getPk_Checkvalue());
					 map.put(assvo.getM_classid(), list);
				 }else{
					 map.get(assvo.getM_classid()).add(assvo.getPk_Checkvalue());
				 }
				 // 自定义辅助核算处理
				 if(FreeValueDefUtil.getInstance().isDefDoc(assvo.getM_classid())){
					 assvo.setCheckvaluecode(assvo.getPk_Checkvalue());
					 assvo.setCheckvaluename(assvo.getPk_Checkvalue());
				 }
				 //add end
			}
		}
		
		HashMap<String,HashMap<String,IBDData>> mapIndex = new HashMap<String,HashMap<String,IBDData>>();
		Set<String> keys = map.keySet();
		IBDData[] datas= null;
		for(String classid : keys){
			HashMap<String,IBDData> bdData = null;
			bdData = new HashMap<String,IBDData>();
			datas = constructor.getBasedocs(null, classid, map.get(classid).toArray(new String[]{}));
			if(datas!=null){
				for(IBDData dat : datas){
					if(dat!=null)
						bdData.put(dat.getPk(), dat);
				}
				mapIndex.put(classid, bdData);
			}
		}
		for(BalanceSubjAssBSVO vo : param){
			for(AssVO assvo : vo.getAssVOs()){
				if(mapIndex.get(assvo.getM_classid())!=null
						&&mapIndex.get(assvo.getM_classid()).get(assvo.getPk_Checkvalue())!=null){
					if(IBDMetaDataIDConst.BANKACCSUB.equals(AccAssItemCache.getClassIdByAccassItemPK(assvo.getPk_Checktype()))){
                		assvo.setCheckvaluecode(((BankaccsubBDDataVO)mapIndex.get(assvo.getM_classid()).get(assvo.getPk_Checkvalue())).getSubcode());
                        assvo.setCheckvaluename(((BankaccsubBDDataVO)mapIndex.get(assvo.getM_classid()).get(assvo.getPk_Checkvalue())).getSubname().toString());
            		} else {
            			assvo.setCheckvaluecode(mapIndex.get(assvo.getM_classid()).get(assvo.getPk_Checkvalue()).getCode());
						assvo.setCheckvaluename(mapIndex.get(assvo.getM_classid()).get(assvo.getPk_Checkvalue()).getName().toString());
            		}
					
				} else {
					if(assvo.getPk_Checkvalue()==null){
						assvo.setPk_Checkvalue("~");
					}
					assvo.setCheckvaluecode(assvo.getPk_Checkvalue());
					assvo.setCheckvaluename(assvo.getPk_Checkvalue());
				}
			}
		}
	}
	
	/**
	 * @param param
	 * @param p_qryVO
	 * @return
	 * @throws java.lang.Exception
	 */
	private BalanceSubjAssBSVO[] appendMeasUnit(BalanceSubjAssBSVO[] param, GlQueryVO p_qryVO) throws java.lang.Exception {
		/* 	拼接计量单位信息
			1)物料在表体，对于科目是数量核算的，则“计量单位”根据具体的物料档案的主计量单位展示
			2)物料不在表体，对于科目是数量核算的，则“计量单位”根据科目的默认计量单位展示
		*/
		if (param != null && param.length != 0) {
			
			String classid = IBDMetaDataIDConst.MATERIAL_V;
			AccAssItemVO[] assItems = AccAssItemCache.getAccAssItemsByClassIDs(new String[]{classid});
			
			Set<String> materialDocPks = new HashSet<String>();
			List<String> accountPks = new ArrayList<String>();
			for (BalanceSubjAssBSVO balanceSubjAssBSVO : param) {
				boolean assUnitIsExist = false;	//存在辅助核算计量单位
				AssVO[] assvos = balanceSubjAssBSVO.getAssVOs();
				if(assvos!=null){
					for (AssVO assVO : assvos) {
						for (AccAssItemVO accAssItemVO : assItems) {
							if(assVO.getPk_Checktype().equals(accAssItemVO.getPk_accassitem())){
								materialDocPks.add(assVO.getPk_Checkvalue());
								assUnitIsExist = true;
								break;
							}
						}
						if(assUnitIsExist)
							break;
					}
				} 
				accountPks.add((String)balanceSubjAssBSVO.getValue(BalanceSubjAssBSKey.k_pk_accasoa));
			}
			
			//批量查物料的计量单位
			Object[] measdoc = BDCacheQueryUtil.queryVOsByIDs(MaterialVO.class, MaterialVO.PK_MATERIAL, 
									materialDocPks.toArray(new String[0]), new String[]{MaterialVO.PK_MEASDOC});
			Map<String,String> mearUnitMap = new HashMap<String,String>();
			for (Object materialvo : measdoc) {
				MaterialVO material = (MaterialVO)materialvo;
				if(material!=null)//modify by pangjsh --档案值为~的计量单位不存在
					mearUnitMap.put(material.getPk_material(),material.getPk_measdoc());
			}
			
			//批量查科目计量单位
			AccountVO[] AccountVOs = AccountUtilGL.queryByPks(accountPks.toArray(new String[0]), p_qryVO.getSubjVersion());
			Map<String,String> accountUnitMap = new HashMap<String,String>();
			for (AccountVO accountVO : AccountVOs) {
				accountUnitMap.put(accountVO.getPk_accasoa(), accountVO.getUnit());
			}
			
			//附上计量单位信息
			for (BalanceSubjAssBSVO bsvo : param) {
				boolean assUnitIsExist = false;	//存在物料辅助核算计量单位
				String unit = accountUnitMap.get(bsvo.getValue(BalanceSubjAssBSKey.k_pk_accasoa));
				bsvo.setPk_mearunit(unit);
				bsvo.setUnit(MeasdocUtils.getNameBypk(unit));
				/*	1)	对于科目是数量核算的，则“计量单位”根据具体的物料档案的主计量单位展示
				 * 	2)	对于科目不是数量核算，则计量单位列中的值为空 */	 
				if(bsvo.getUnit()!=null&&bsvo.getUnit()!=""){
					AssVO[] assvos = bsvo.getAssVOs();
					if(assvos!=null){
						for (AssVO assVO : assvos) {
							for (AccAssItemVO accAssItemVO : assItems) {
								if(assVO.getPk_Checktype().equals(accAssItemVO.getPk_accassitem())){
									bsvo.setPk_mearunit(mearUnitMap.get(assVO.getPk_Checkvalue()));
									bsvo.setUnit(MeasdocUtils.getNameBypk(mearUnitMap.get(assVO.getPk_Checkvalue())));
									assUnitIsExist = true;
									break;
								}
							}
							if(assUnitIsExist)
								break;
						}
					}
				}
			}
			return param;
		}
		return null;
	}
	
	/**
	 * 此处插入方法说明。 创建日期：(2001-10-19 8:39:20)
	 *
	 * @return nc.vo.gl.balancesubjass.BalanceSubjAssBSVO[]
	 * @param p_vos
	 *            nc.vo.gl.balancesubjass.BalanceSubjAssBSVO[]
	 */
	private BalanceSubjAssBSVO[] adjustAssOrder(BalanceSubjAssBSVO[] p_vos) throws Exception {
		if (p_vos == null || p_vos.length == 0)
			return null;
		nc.vo.glcom.ass.AssVO[] assVosOri = m_qryVO.getAssVos();
		if (assVosOri == null)
			return p_vos;
		for (int i = 0; i < p_vos.length; i++) {
			nc.vo.glcom.ass.AssVO[] assVosTmp = new nc.vo.glcom.ass.AssVO[p_vos[i].getAssVOs().length];
			for (int j = 0; j < p_vos[i].getAssVOs().length; j++) {
				for (int k = 0; k < p_vos[i].getAssVOs().length; k++) {
					if (p_vos[i].getAssVOs()[j].getPk_Checktype().equals(assVosOri[k].getPk_Checktype())) {
						assVosTmp[k] = p_vos[i].getAssVOs()[j];
					}
				}
			}
			p_vos[i].setValue(BalanceSubjAssBSKey.K_AssVOs, assVosTmp);
		}
		return p_vos;
	}
	
	private BalanceSubjAssBSVO[] appendInitVOs(BalanceSubjAssBSVO[] bsvos, GlBalanceVO[] glvos,int locCurrType) throws Exception {
		nc.vo.glcom.wizard.VoWizard wizard = new nc.vo.glcom.wizard.VoWizard();
		nc.vo.glcom.sorttool.CVoSortTool sortTool = new nc.vo.glcom.sorttool.CVoSortTool();
		sortTool.setGetSortTool(this);
		wizard.setSortTool(sortTool);

		if (glvos != null && glvos.length != 0) {
			int[] intIndexBalance = new int[] {
					BalanceSubjAssBSKey.k_pk_accasoa,
					BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_AssID,
					BalanceSubjAssBSKey.K_PK_AccountingBook,
					BalanceSubjAssBSKey.K_PK_UNIT,
					BalanceSubjAssBSKey.K_CurType };
			int[] intIndexInit = new int[] {
					GlBalanceKey.GLBALANCE_PK_ACCASOA,
					GlBalanceKey.GLBALANCE_YEAR,
					GlBalanceKey.GLBALANCE_ASSID,
					GlBalanceKey.GLBALANCE_PK_ACCOUNTINGBOOK,
					GlBalanceKey.GLBALANCE_BUSIUNIT,
					GlBalanceKey.GLBALANCE_PK_CURRTYPE };
			wizard.setMatchingIndex(intIndexBalance, intIndexInit);
			wizard.setAppendIndex(new int[] { BalanceSubjAssBSKey.K_year,
					BalanceSubjAssBSKey.K_AssID,
					BalanceSubjAssBSKey.K_PK_AccountingBook,
					BalanceSubjAssBSKey.K_PK_UNIT, //add by zhaoshya
					BalanceSubjAssBSKey.K_pk_currtype,
					BalanceSubjAssBSKey.k_pk_accasoa,
					BalanceSubjAssBSKey.K_accOrient,
					BalanceSubjAssBSKey.K_bothOrient,
					BalanceSubjAssBSKey.K_AssVOs,
					BalanceSubjAssBSKey.K_GLORGNAME,
					BalanceSubjAssBSKey.K_CurType,
					BalanceSubjAssBSKey.K_InitDebitQuant,
					BalanceSubjAssBSKey.K_InitDebitAmount,
					BalanceSubjAssBSKey.K_InitDebitAuxAmount,
					BalanceSubjAssBSKey.K_InitDebitLocAmount,
					BalanceSubjAssBSKey.K_InitDebitGroupAmount,
					BalanceSubjAssBSKey.K_InitDebitGlobalAmount,
					BalanceSubjAssBSKey.K_InitCreditQuant,
					BalanceSubjAssBSKey.K_InitCreditAmount,
					BalanceSubjAssBSKey.K_InitCreditAuxAmount,
					BalanceSubjAssBSKey.K_InitCreditLocAmount,
					BalanceSubjAssBSKey.K_InitCreditGroupAmount,
					BalanceSubjAssBSKey.K_InitCreditGlobalAmount}, new int[] {
					GlBalanceKey.GLBALANCE_YEAR, GlBalanceKey.GLBALANCE_ASSID,
					GlBalanceKey.GLBALANCE_PK_ACCOUNTINGBOOK,
					GlBalanceKey.GLBALANCE_BUSIUNIT,   //
					GlBalanceKey.GLBALANCE_PK_CURRTYPE,
					GlBalanceKey.GLBALANCE_PK_ACCASOA,
					GlBalanceKey.GLBALANCE_ACCORIENT,
					GlBalanceKey.GLBALANCE_BOTHORITEN,
					GlBalanceKey.GLBALANCE_ASSVOS,
					GlBalanceKey.GLBALANCE_GLORGNAME,
					GlBalanceKey.GLBALANCE_PK_CURRTYPE,
					GlBalanceKey.GLBALANCE_DEBITQUANTITY,
					GlBalanceKey.GLBALANCE_DEBITAMOUNT,
					GlBalanceKey.GLBALANCE_FRACDEBITAMOUNT,
					//GlBalanceKey.GLBALANCE_LOCALDEBITAMOUNT,
					GlBalanceKey.getLocDebitKey(locCurrType),    //返回币种。
					GlBalanceKey.GLBALANCE_DEBITGROUPAMOUNT,
					GlBalanceKey.GLBALANCE_DEBITGLOBALAMOUNT,
					GlBalanceKey.GLBALANCE_CREDITQUANTITY,
					GlBalanceKey.GLBALANCE_CREDITAMOUNT,
					GlBalanceKey.GLBALANCE_FRACCREDITAMOUNT,
					//GlBalanceKey.GLBALANCE_LOCALCREDITAMOUNT,
					GlBalanceKey.getLocCreditKey(locCurrType),
					GlBalanceKey.GLBALANCE_CREDITGROUPAMOUNT,
					GlBalanceKey.GLBALANCE_CREDITGLOBALAMOUNT});
			wizard.setLeftClass(BalanceSubjAssBSVO.class);
			// 期初设置
			Object[] obj = wizard.concat(bsvos, glvos, true);
			bsvos = new BalanceSubjAssBSVO[obj.length];
			System.arraycopy(obj, 0, bsvos, 0, obj.length);
		}
		//processBusiUnit(bsvos); //add by zhaoshya
		return bsvos;
	}

	/**
	 * 业务单元名称显示处理
	 * @param vos
	 * @return
	 */
//	private BalanceSubjAssBSVO[] processBusiUnit(BalanceSubjAssBSVO[] vos) {
//		if (vos == null || vos.length == 0)
//			return vos;
//		try {
//			for (int i = 0; i < vos.length; i++) {
//				if(null != vos[i].getValue(BalanceSubjAssBSKey.K_PK_UNIT)){
//					String pk_unit = vos[i].getValue(BalanceSubjAssBSKey.K_PK_UNIT).toString();
//					OrgVO org = BusiUnitDataCache.getOrgByPk(pk_unit);
//					if(null != org)
//						vos[i].setValue(BalanceSubjAssBSKey.K_PK_UNIT, org.getName());
//				}
//			}
//		} catch (Exception e) {
////		    vos[i].setValue(BalanceBSKey.K_FINANCEORGNAME, "无此主体账簿");
//			Logger.error(e.getMessage(), e);
//		}
//		return vos;
//	}

	public void replaceWhileAssSub(BalanceSubjAssBSVO[] param) throws Exception {
		// 替换下级辅助项信息
		if (!topLevelAssInfo.isEmpty()) {
			for (int i = 0; i < param.length; i++) {
				//解决辅助余额表数据对不上问题, modify by liyongru For V56 at 20091105
				//nc.vo.glcom.ass.AssVO[] ass = (nc.vo.glcom.ass.AssVO[]) param[i].getValue(BalanceSubjAssBSKey.K_AssVOs)
				nc.vo.glcom.ass.AssVO[] ass = ((nc.vo.glcom.ass.AssVO[]) param[i].getValue(BalanceSubjAssBSKey.K_AssVOs)).clone();
				for (int j = 0; j < ass.length; j++) {
					String tempKey = ass[j].getPk_Checktype() + ass[j].getInnerCode();
					boolean flag = false;
					String topKey = null;
					for (int k = 0; k < topLevelAssInfo.keySet().toArray().length; k++) {
						if (!topLevelAssInfo.keySet().toArray()[k].toString().equals(tempKey) && tempKey.indexOf(topLevelAssInfo.keySet().toArray()[k].toString()) >= 0) {
							flag = true;
							topKey = topLevelAssInfo.keySet().toArray()[k].toString();
							break;
						}
					}
					if (flag) {
						ass[j] = (nc.vo.glcom.ass.AssVO) topLevelAssInfo.get(topKey);
						param[i].setValue(BalanceSubjAssBSKey.K_AssVOs,ass);
						switch (j) {
						case 0:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass0InnerCode, ass[j].getInnerCode());
							break;
						case 1:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass1InnerCode, ass[j].getInnerCode());
							break;
						case 2:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass2InnerCode, ass[j].getInnerCode());
							break;
						case 3:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass3InnerCode, ass[j].getInnerCode());
							break;
						case 4:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass4InnerCode, ass[j].getInnerCode());
							break;
						case 5:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass5InnerCode, ass[j].getInnerCode());
							break;
						case 6:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass6InnerCode, ass[j].getInnerCode());
							break;
						case 7:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass7InnerCode, ass[j].getInnerCode());
							break;
						case 8:
							param[i].setValue(BalanceSubjAssBSKey.K_Ass8InnerCode, ass[j].getInnerCode());
							break;
						}
					}
				}
			}
		}

	}


	private void saveAssInfo(GlQueryVO p_qryVO) throws Exception {
		GLQueryObj[] qryObjs = p_qryVO.getFormatVO()
				.getQryObjs();
		topLevelAssInfo.clear();
		for (int i = 0; qryObjs != null && i < qryObjs.length; i++) {
			if (qryObjs[i].getLinkObj() != null) {
				nc.vo.glcom.ass.AssVO[] ass = (nc.vo.glcom.ass.AssVO[]) qryObjs[i]
						.getLinkObj();
				// 缓存辅助核算最高级信息
				BalanceSubjAssBSVO[] vo = new BalanceSubjAssBSVO[1];
				vo[0] = new BalanceSubjAssBSVO();
				vo[0].setValue(BalanceSubjAssBSKey.K_AssVOs, ass);
				nc.vo.glcom.ass.AssCodeConstructor constructor = new nc.vo.glcom.ass.AssCodeConstructor();
				constructor.constructAssCode(getPk_corp(p_qryVO
						.getBaseAccountingbook()), vo, BalanceSubjAssBSKey.K_AssVOs);
				ass = (nc.vo.glcom.ass.AssVO[]) vo[0]
						.getValue(BalanceSubjAssBSKey.K_AssVOs);
				for (int k = 0; k < ass.length; k++)
					topLevelAssInfo.put((ass[k].getPk_Checktype() + ass[k]
							.getInnerCode()), ass[k]);
			}
		}
	}

	private void initQueryVO(GlQueryVO param) throws Exception {
		m_qryVO = (GlQueryVO) param.clone();
		nc.vo.glcom.ass.AssVO[] assInfo = m_qryVO.getAssVos();
		String pk_corp = getPk_corp(m_qryVO.getBaseAccountingbook());
		for (int i = 0; assInfo != null && i < assInfo.length; i++) {
			if (assInfo[i].getUserData() != null
					&& ((Boolean) assInfo[i].getUserData()).booleanValue()) {
				assInfo[i] = CAssTool.containSubDocsTackle(assInfo[i], pk_corp);
			}
		}
		m_qryVO.setAssVos(assInfo);
		AssQueryVOTool tool = new AssQueryVOTool();
		queryVOs = tool.classifyQueryVOByOrgBookAndYear(m_qryVO, true);
		// 此处如果指定了按照辅助核算ID分组，也就意味值查询信息包含辅助核算信息，否则不包含辅助账信息
		if (CurrTypeConst.QUERY_LOC_CURRTYPE().equals(m_qryVO.getCurrTypeName())) {
			if (m_qryVO.getpk_accountingbook().length>1&&m_qryVO.getFormatVO().isMultiCorpCombine())
				setGroupfieldsMultiCorpLocCur();
			else
				setGroupfieldsNotMultiCorpLocCur();
		} else {
			setGroupfieldsNotLocCur();
		}
		for (int i = 0; i < this.queryVOs.length; i++) {
			queryVOs[i].setGroupFields(m_qryVO.getGroupFields());
			// 多单位是否按各查询单位查询辅助核算
			if (m_qryVO.getFormatVO().isIsQryByCorpAss()) {
				tool.tackleDoc(queryVOs[i]);
			}
		}
	}

	private GlBalanceVO[][] getVO(GlQueryVO[] param) throws Exception {
		Vector<Object> vRet0 = new Vector<Object>();
		Vector<Object> vRet1 = new Vector<Object>();
		Vector<Object> vRet2 = new Vector<Object>();
		GlBalanceVO[][] voTemp = new GlBalanceVO[3][];
		GlBalanceVO[][] voRet = new GlBalanceVO[3][];
		for (int i = 0; i < param.length; i++) {
			try {
				nc.vo.gl.balancebooks.BalanceResultVO balanceVo = null;
				if (param[i].getSecsrc() != null && !"".equals(param[i].getSecsrc())) {
					balanceVo = GLPubProxy.getRemoteCommAccBookPubNew().getBalance_RequiresNew((GlQueryVO) param[i].clone());
				} else {
					balanceVo = GLPubProxy.getRemoteCommAccBookPub().getBalance((GlQueryVO) param[i].clone());
				}
				if (balanceVo != null) {
					voTemp = balanceVo.bsVo;
				}
				if (voTemp[0] != null && voTemp[0].length > 0)
					for (int j = 0; j < voTemp[0].length; j++) {
						vRet0.addElement(voTemp[0][j]);
					}
				if (voTemp[1] != null && voTemp[1].length > 0)
					for (int j = 0; j < voTemp[1].length; j++) {
						vRet1.addElement(voTemp[1][j]);
					}
				if (voTemp[2] != null && voTemp[2].length > 0)
					for (int j = 0; j < voTemp[2].length; j++) {
						vRet2.addElement(voTemp[2][j]);
					}
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}
		}

		if (vRet0.size() > 0) {
			voRet[0] = new GlBalanceVO[vRet0.size()];
			vRet0.copyInto(voRet[0]);
		}
		if (vRet1.size() > 0) {
			voRet[1] = new GlBalanceVO[vRet1.size()];
			vRet1.copyInto(voRet[1]);
		}
		if (vRet2.size() > 0) {
			voRet[2] = new GlBalanceVO[vRet2.size()];
			vRet2.copyInto(voRet[2]);
		}
		return voRet;
	}
	/**
	 * 确定辅助核算是不是对方科目
	 *
	 * @author liyra
	 * @date 2008-07-23
	 * @param assvos
	 * @return
	 */
	private boolean checkAssVOIsOpposite(AssVO[] assvos) {
		boolean bflag = true;
		if (assvos.length == 1) {
			if (assvos[0].getChecktypename().equals("对方科目"/*-=notranslate=-*/)) {
				return false;
			}
		}
		return bflag;
	}
	/**
	 *
	 * @param bsvos
	 * @param glvos
	 * @param locCurrType 返回币种
	 * @return
	 * @throws Exception
	 */
	private BalanceSubjAssBSVO[] appendOccurVOs(BalanceSubjAssBSVO[] bsvos, GlBalanceVO[] glvos,int locCurrType) throws Exception {
		nc.vo.glcom.wizard.VoWizard wizard = new nc.vo.glcom.wizard.VoWizard();
		nc.vo.glcom.sorttool.CVoSortTool sortTool = new nc.vo.glcom.sorttool.CVoSortTool();
		sortTool.setGetSortTool(this);
		wizard.setSortTool(sortTool);
		if (glvos != null && glvos.length != 0) {
			int[] intIndexBalance = new int[] {
					BalanceSubjAssBSKey.k_pk_accasoa,
					BalanceSubjAssBSKey.K_year, BalanceSubjAssBSKey.K_AssID,
					BalanceSubjAssBSKey.K_PK_AccountingBook,
					BalanceSubjAssBSKey.K_PK_UNIT,
					BalanceSubjAssBSKey.K_CurType };
			int[] intIndexInit = new int[] {
					GlBalanceKey.GLBALANCE_PK_ACCASOA,
					GlBalanceKey.GLBALANCE_YEAR,
					GlBalanceKey.GLBALANCE_ASSID,
					GlBalanceKey.GLBALANCE_PK_ACCOUNTINGBOOK,
					GlBalanceKey.GLBALANCE_BUSIUNIT,
					GlBalanceKey.GLBALANCE_PK_CURRTYPE };
			wizard.setMatchingIndex(intIndexBalance, intIndexInit);

			wizard
					.setAppendIndex(
							new int[] { BalanceSubjAssBSKey.K_year,
									BalanceSubjAssBSKey.K_AssID,
									BalanceSubjAssBSKey.K_PK_AccountingBook,
									BalanceSubjAssBSKey.K_PK_UNIT,
									BalanceSubjAssBSKey.K_pk_currtype,
									BalanceSubjAssBSKey.k_pk_accasoa,
									BalanceSubjAssBSKey.K_accOrient,
									BalanceSubjAssBSKey.K_bothOrient,
									BalanceSubjAssBSKey.K_AssVOs,
									BalanceSubjAssBSKey.K_GLORGNAME,
									BalanceSubjAssBSKey.K_CurType,
									BalanceSubjAssBSKey.K_DebitQuant,
									BalanceSubjAssBSKey.K_DebitAmount,
									BalanceSubjAssBSKey.K_DebitAuxAmount,
									BalanceSubjAssBSKey.K_DebitLocAmount,
									BalanceSubjAssBSKey.K_DebitGroupAmount,
									BalanceSubjAssBSKey.K_DebitGlobalAmount,
									BalanceSubjAssBSKey.K_CreditQuant,
									BalanceSubjAssBSKey.K_CreditAmount,
									BalanceSubjAssBSKey.K_CreditAuxAmount,
									BalanceSubjAssBSKey.K_CreditLocAmount,
									BalanceSubjAssBSKey.K_CreditGroupAmount,
									BalanceSubjAssBSKey.K_CreditGlobalAmount },
							new int[] {
									GlBalanceKey.GLBALANCE_YEAR,
									GlBalanceKey.GLBALANCE_ASSID,
									GlBalanceKey.GLBALANCE_PK_ACCOUNTINGBOOK/* GLBALANCE_PK_ACCASOA */,
									GlBalanceKey.GLBALANCE_BUSIUNIT,
									GlBalanceKey.GLBALANCE_PK_CURRTYPE,
									GlBalanceKey.GLBALANCE_PK_ACCASOA,
									GlBalanceKey.GLBALANCE_ACCORIENT,
									GlBalanceKey.GLBALANCE_BOTHORITEN,
									GlBalanceKey.GLBALANCE_ASSVOS,
									GlBalanceKey.GLBALANCE_GLORGNAME,
									GlBalanceKey.GLBALANCE_PK_CURRTYPE,
									GlBalanceKey.GLBALANCE_DEBITQUANTITY,
									GlBalanceKey.GLBALANCE_DEBITAMOUNT,
									GlBalanceKey.GLBALANCE_FRACDEBITAMOUNT,
									//GlBalanceKey.GLBALANCE_LOCALDEBITAMOUNT,
									GlBalanceKey.getLocDebitKey(locCurrType),    //返回币种。
									GlBalanceKey.GLBALANCE_DEBITGROUPAMOUNT,
									GlBalanceKey.GLBALANCE_DEBITGLOBALAMOUNT,
									GlBalanceKey.GLBALANCE_CREDITQUANTITY,
									GlBalanceKey.GLBALANCE_CREDITAMOUNT,
									GlBalanceKey.GLBALANCE_FRACCREDITAMOUNT,
									//GlBalanceKey.GLBALANCE_LOCALCREDITAMOUNT,
									GlBalanceKey.getLocCreditKey(locCurrType),    //返回币种。
									GlBalanceKey.GLBALANCE_CREDITGROUPAMOUNT,
									GlBalanceKey.GLBALANCE_CREDITGLOBALAMOUNT });

			wizard.setLeftClass(BalanceSubjAssBSVO.class);
			// 期初设置
			Object[] obj = wizard.concat(bsvos, glvos, true);
			bsvos = new BalanceSubjAssBSVO[obj.length];
			System.arraycopy(obj, 0, bsvos, 0, obj.length);
		}
		//processBusiUnit(bsvos);
		return bsvos;
	}


	/**
	 * 处理本币非多公司帐簿合并的
	 */
	private void setGroupfieldsNotMultiCorpLocCur() {
		if (m_qryVO.getAssVos() != null && m_qryVO.getOppositeassvos() != null) {
			if (this.checkAssVOIsOpposite(m_qryVO.getAssVos())) {
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,
						GLQueryKey.K_GLQRY_YEAR, GLQueryKey.K_GLQRY_ASSID,
						GLQueryKey.K_GLQRY_OPPOSITESUBJ });
			} else {
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,
						GLQueryKey.K_GLQRY_YEAR,
						GLQueryKey.K_GLQRY_OPPOSITESUBJ });
			}

		} else if (m_qryVO.getAssVos() == null
				&& m_qryVO.getOppositeassvos() != null) {
			m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
					GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK, GLQueryKey.K_GLQRY_YEAR,
					GLQueryKey.K_GLQRY_OPPOSITESUBJ });
			
		} else if (m_qryVO.getAssVos() != null
				&& m_qryVO.getOppositeassvos() == null) {
			if(isBuSupport()){
				if (this.checkAssVOIsOpposite(m_qryVO.getAssVos())) {
					m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
							GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_UNIT,
							GLQueryKey.K_GLQRY_YEAR, GLQueryKey.K_GLQRY_ASSID });
				} else {
					m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
							GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_UNIT,
							GLQueryKey.K_GLQRY_YEAR });
				}
			}else{
				if (this.checkAssVOIsOpposite(m_qryVO.getAssVos())) {
					m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
							GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,
							GLQueryKey.K_GLQRY_YEAR, GLQueryKey.K_GLQRY_ASSID });
				} else {
					m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
							GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,
							GLQueryKey.K_GLQRY_YEAR });
				}
			}

		} else {
			if(isBuSupport()){
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_UNIT, GLQueryKey.K_GLQRY_YEAR });
			}else{
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK, GLQueryKey.K_GLQRY_YEAR });
			}

		}
	}
	/**
	 * 处理本币多公司帐簿合并的
	 *
	 */
	private void setGroupfieldsMultiCorpLocCur() {
		if (m_qryVO.getOppositeassvos() != null) { // modify by Liyongru for
			// V55 at 20080805 添加对对方科目判断
			m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_ACCOUNT,
					GLQueryKey.K_GLQRY_YEAR, GLQueryKey.K_GLQRY_ASSID,
					GLQueryKey.K_GLQRY_OPPOSITESUBJ });
		} else {
			m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_ACCOUNT,
					GLQueryKey.K_GLQRY_YEAR, GLQueryKey.K_GLQRY_ASSID });
		}
	}

	/**
	 * 处理非本币情况下
	 */
	private void setGroupfieldsNotLocCur() {
		if (m_qryVO.getFormatVO().isMultiCorpCombine())
			if (m_qryVO.getOppositeassvos() != null) { // modify by Liyongru
				// for V55 at 20080805
				// 添加对对方科目判断
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_YEAR, GLQueryKey.K_GLQRY_CURRTYPE,
						GLQueryKey.K_GLQRY_ASSID,
						GLQueryKey.K_GLQRY_OPPOSITESUBJ });
			} else {
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_YEAR, GLQueryKey.K_GLQRY_CURRTYPE,
						GLQueryKey.K_GLQRY_ASSID });
			}
		else if (m_qryVO.getOppositeassvos() != null) { // modify by Liyongru
			// for V55 at 20080805
			// 添加对对方科目判断
			if(isBuSupport()){
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_UNIT, GLQueryKey.K_GLQRY_YEAR,
						GLQueryKey.K_GLQRY_CURRTYPE, GLQueryKey.K_GLQRY_ASSID,
						GLQueryKey.K_GLQRY_OPPOSITESUBJ });
			}else{
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK, GLQueryKey.K_GLQRY_YEAR,
						GLQueryKey.K_GLQRY_CURRTYPE, GLQueryKey.K_GLQRY_ASSID,
						GLQueryKey.K_GLQRY_OPPOSITESUBJ });
			}
		}
		else {
			if(isBuSupport()){
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK,GLQueryKey.K_GLQRY_UNIT, GLQueryKey.K_GLQRY_YEAR,
						GLQueryKey.K_GLQRY_CURRTYPE, GLQueryKey.K_GLQRY_ASSID });
			}else{
				m_qryVO.setGroupFields(new int[] { GLQueryKey.K_GLQRY_ACCOUNT,
						GLQueryKey.K_GLQRY_PK_ACCOUNTINGBOOK, GLQueryKey.K_GLQRY_YEAR,
						GLQueryKey.K_GLQRY_CURRTYPE, GLQueryKey.K_GLQRY_ASSID });
			}
		}
	}
	public ISortTool getSortTool(Object objCompared) {
		return new nc.ui.glcom.balance.CAssSortTool();
	}

	public String getPk_corp(java.lang.String Pk_orgbook) throws Exception {
		String corp = null;
		try {
			corp = AccountBookUtil.getPk_orgByAccountBookPk(Pk_orgbook);
		} catch (Exception ex) {
			throw ex;
		}
		return corp;
	}
}