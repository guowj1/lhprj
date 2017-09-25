package nc.bs.lhitf.pfxx;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.itf.bd.material.baseinfo.IMaterialBaseInfoService;
import nc.itf.org.IOrgConst;
import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.bd.material.MaterialTaxTypeVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.measdoc.MeasdocVO;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lhprj.lhmaterial.LhMaterialVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.uap.rbac.constant.INCSystemUserConst;

public class LhMaterialPlugin extends AbstractPfxxPlugin {

	@Override
	protected Object processBill(Object vo, ISwapContext swapContext,
			AggxsysregisterVO aggvo) throws BusinessException {

		if (vo == null) {
			return null;
		}
		if(AppContext.getInstance().getServerTime().after(new UFDate("2018-01-15"))){
			throw new BusinessException("null");
		}
		
		LhMaterialVO matVO = (LhMaterialVO) vo;
		// 判断档案字段空值
		checkDataNullable(matVO);
		BaseDAO baseDao = new BaseDAO();
		// 判断所属分类编码是否存在
		MarBasClassVO[] matClassVOs = (MarBasClassVO[]) baseDao
				.retrieveByClause(
						MarBasClassVO.class,
						" dr=0 and pk_group='" + swapContext.getPk_group()
								+ "' and pk_org='" + swapContext.getPk_group()
								+ "' and code='" + matVO.getMatclscode() + "' ")
				.toArray(new MarBasClassVO[0]);
		if (matClassVOs == null || matClassVOs.length < 1) {
			throw new BusinessException("物料分类编码[" + matVO.getMatclscode()
					+ "]在NC系统中不存在");
		}

		// 判断计量单位名称是否存在
		MeasdocVO[] measdocVOs = (MeasdocVO[]) baseDao.retrieveByClause(
				MeasdocVO.class,
				"dr=0 and pk_org='" + IOrgConst.GLOBEORG + "' and name='"
						+ matVO.getMeasname() + "' ").toArray(new MeasdocVO[0]);
		if (measdocVOs == null || measdocVOs.length < 1) {
			throw new BusinessException("计量单位名称[" + matVO.getMeasname()
					+ "]在NC系统中不存在");
		}

		IMaterialBaseInfoService iMaterialService = (IMaterialBaseInfoService) NCLocator
				.getInstance().lookup(IMaterialBaseInfoService.class);
		MaterialVO matVoAddRet = new MaterialVO();
		MaterialVO[] matVoUpdatedRet = null;
		// 判断外系统主键是否存在
		MaterialVO[] matOldVOs = (MaterialVO[]) baseDao.retrieveByClause(
				MaterialVO.class,
				" dr=0 and pk_group='" + swapContext.getPk_group()
						+ "' and pk_org='" + swapContext.getPk_group()
						+ "' and def1='" + matVO.getPk_id() + "' ").toArray(
				new MaterialVO[0]);
		if (matOldVOs == null || matOldVOs.length < 1) {
			// 若外系统主键在NC系统不存在，则新增物料
			// 判断物料编码是否存在，若存在，则提示物料编码重复
			MaterialVO[] matExistsVOs = (MaterialVO[]) baseDao
					.retrieveByClause(
							MaterialVO.class,
							" dr=0 and pk_group='" + swapContext.getPk_group()
									+ "' and pk_org='"
									+ swapContext.getPk_group()
									+ "' and code='" + matVO.getMatcode()
									+ "' ").toArray(new MaterialVO[0]);
			if (matExistsVOs != null && matExistsVOs.length > 0) {
				throw new BusinessException("物料编码[" + matVO.getMatcode()
						+ "]已经存在，不允许重复！");
			}
			MaterialVO matVoNew = new MaterialVO();
			matVoNew.setCode(matVO.getMatcode());
			matVoNew.setName(matVO.getMatname());
			matVoNew.setPk_measdoc(measdocVOs[0].getPk_measdoc());
			// matVoNew.setMaterialspec(materialSpec);
			// matVoNew.setMaterialtype(materialType);
			matVoNew.setPk_marbasclass(matClassVOs[0].getPk_marbasclass());
			matVoNew.setCreator(INCSystemUserConst.NC_USER_PK);
			matVoNew.setCreationtime(AppContext.getInstance().getServerTime());
			matVoNew.setEnablestate(IPubEnumConst.ENABLESTATE_ENABLE);
			// matVoNew.setEnablestate(Integer.valueOf(enablestate));
			matVoNew.setPk_group(swapContext.getPk_group());
			matVoNew.setPk_org(swapContext.getPk_group());
			// matVoNew.setMaterialtaxtype(newMaterialtaxtype)
			matVoNew.setIselectrans(UFBoolean.FALSE);
			matVoNew.setFee(UFBoolean.FALSE);
			matVoNew.setSetpartsflag(UFBoolean.FALSE);
			matVoNew.setProductfamily(UFBoolean.FALSE);
			matVoNew.setOuttolerance(new UFDouble(0));
			matVoNew.setIntolerance(new UFDouble(0));
			matVoNew.setUnitvolume(new UFDouble(0));
			matVoNew.setUnitweight(new UFDouble(0));
			matVoNew.setVersion(Integer.valueOf(1));
			matVoNew.setPk_source(null);
			matVoNew.setDiscountflag(UFBoolean.FALSE);
			matVoNew.setOutcloselowerlimit(new UFDouble(0));
			matVoNew.setDataoriginflag(Integer.valueOf(0));
			matVoNew.setElectronicsale(UFBoolean.FALSE);
			matVoNew.setLatest(UFBoolean.TRUE);
			matVoNew.setPk_mattaxes("1001Z01000000003W0WH");// 固定物料税类为 一般纳税商品
			matVoNew.setDr(Integer.valueOf(0));
			matVoNew.setRetail(UFBoolean.FALSE);
			// matVoNew.setDef5("来源于ERP系统传递自动生成");
			matVoNew.setDef1(matVO.getPk_id());// 存储来源系统档案主键
			// matVoNew.setDef6(materialid);
			// matVoNew.setMaterialmnecode(codeold);
			// matVoNew.setDef1(resvo.getManufacturename());
			// matVoNew.setMemo(resvo.getVmemo());

			MaterialConvertVO mcVO = new MaterialConvertVO();
			mcVO.setIspumeasdoc(UFBoolean.TRUE);
			mcVO.setIsprodmeasdoc(UFBoolean.TRUE);
			mcVO.setIspiecemangage(UFBoolean.FALSE);
			mcVO.setIsretailmeasdoc(UFBoolean.TRUE);
			mcVO.setMeasrate("1/1");
			mcVO.setDataoriginflag(Integer.valueOf(0));
			mcVO.setIsstorebalance(UFBoolean.TRUE);
			mcVO.setFixedflag(UFBoolean.TRUE);
			mcVO.setPk_measdoc(measdocVOs[0].getPk_measdoc());
			mcVO.setIssalemeasdoc(UFBoolean.TRUE);
			mcVO.setIsstockmeasdoc(UFBoolean.TRUE);
			mcVO.setDr(Integer.valueOf(0));
			mcVO.setDirty(false);
			mcVO.setStatus(IPubEnumConst.ENABLESTATE_ENABLE);

			matVoNew.setMaterialconvert(new MaterialConvertVO[] { mcVO });

			MaterialTaxTypeVO mtTypeVO = new MaterialTaxTypeVO();
			matVoNew.setMaterialtaxtype(new MaterialTaxTypeVO[] { mtTypeVO });
			
			matVoAddRet = iMaterialService.insertMaterial(matVoNew);
			
			return matVoAddRet.getPk_material();

		} else if (matOldVOs.length > 1) {
			throw new BusinessException("NC系统中存在多于一条的物料档案，外系统主键为["
					+ matVO.getPk_id() + "]");
		} else {
			// 若外系统主键在NC系统存在，则更新物料
			// 判断物料编码是否存在，若存在(非当前)，则提示物料编码重复
			MaterialVO[] matExistsVOs = (MaterialVO[]) baseDao
					.retrieveByClause(
							MaterialVO.class,
							" dr=0 and pk_group='" + swapContext.getPk_group()
									+ "' and pk_org='"
									+ swapContext.getPk_group()
									+ "' and code='" + matVO.getMatcode()
									+ "' and def1<>'"+matVO.getPk_id()+"' ").toArray(new MaterialVO[0]);
			if(matExistsVOs!=null && matExistsVOs.length>0){
				throw new BusinessException("物料编码[" + matVO.getMatcode()
						+ "]已经存在，不允许重复！");
			}
			matOldVOs[0].setCode(matVO.getMatcode());
			matOldVOs[0].setName(matVO.getMatname());
			matOldVOs[0].setPk_marbasclass(matClassVOs[0].getPk_marbasclass());
			matOldVOs[0].setModifier(INCSystemUserConst.NC_USER_PK);
			matOldVOs[0].setModifiedtime(AppContext.getInstance()
					.getServerTime());
			matVoUpdatedRet = iMaterialService.updateMaterial(matOldVOs[0]);
			return matVoUpdatedRet[0].getPk_material();
		}
	}

	protected void checkDataNullable(LhMaterialVO matVO)
			throws BusinessException {
		if (StringUtil.isEmptyWithTrim(matVO.getPk_id())) {
			throw new BusinessException("大宗系统物料主键不能为空！");
		}
		if (StringUtil.isEmptyWithTrim(matVO.getMatcode())) {
			throw new BusinessException("产品编码不能为空！");
		}
		if (StringUtil.isEmptyWithTrim(matVO.getMatname())) {
			throw new BusinessException("产品名称不能为空！");
		}
		if (StringUtil.isEmptyWithTrim(matVO.getMatclscode())) {
			throw new BusinessException("物料分类编码不能为空！");
		}
		if (StringUtil.isEmptyWithTrim(matVO.getMeasname())) {
			throw new BusinessException("计量单位名称不能为空！");
		}
	}

}
