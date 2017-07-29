package nc.bs.lhitf.mesitf.plugin;

import java.util.HashMap;
import java.util.Map;

import nc.bs.bd.pub.IBDEventType;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.bd.BDCommonEvent;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.tmpub.util.SqlUtil;

public class MaterialHandlerListener implements IBusinessListener {

	private BaseDAO baseDaoMes;
	private BaseDAO baseDao;

	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		this.processMaterialPostMes(event);
	}

	private void processMaterialPostMes(IBusinessEvent event)
			throws BusinessException {
		if (event instanceof BDCommonEvent) {
			if (event.getEventType().equals(IBDEventType.TYPE_INSERT_AFTER)) {
				// 新增时
				// Object[] oldObjects = ((BDCommonEvent) event).getOldObjs();
				Object[] newObjects = ((BDCommonEvent) event).getNewObjs();
				MaterialVO[] vos = convertObjToMatVO(newObjects);
				insertDBValue(vos);
				// if (oldObjects == null || oldObjects.length == 0) {
				// return;
				// }

			} else if (event.getEventType().equals(
					IBDEventType.TYPE_UPDATE_AFTER)) {
				// 修改时
				Object[] newObjects = ((BDCommonEvent) event).getNewObjs();
				MaterialVO[] vos = convertObjToMatVO(newObjects);
				updateDBValue(vos);

			} else if (event.getEventType().equals(
					IBDEventType.TYPE_DELETE_AFTER)) {
				// 删除时
				Object[] oldObjects = ((BDCommonEvent) event).getNewObjs();
				MaterialVO[] vos = convertObjToMatVO(oldObjects);
				deleteDBValue(vos);

			} else if (event.getEventType().equals(
					IBDEventType.TYPE_ENABLE_DISABLE_AFTER)) {
				Object[] newObjects = (Object[]) ((BDCommonEvent) event)
						.getNewObjs();
				enabledisableDBValue(newObjects);
			} else if (event.getEventType().equals(
					IBDEventType.TYPE_DISABLE_ENABLE_AFTER)) {
				Object[] newObjects = (Object[]) ((BDCommonEvent) event)
						.getNewObjs();
				enabledisableDBValue(newObjects);
			}

		}
	}

	private MaterialVO[] convertObjToMatVO(Object[] obj) {
		MaterialVO[] vos = new MaterialVO[obj.length];
		for (int i = 0; i < obj.length; i++) {
			MaterialVO vo = (MaterialVO) obj[i];
			vos[i] = vo;
		}
		return vos;
	}

	private Map<String, MarBasClassVO> getMaterialClass(MaterialVO[] vos)
			throws BusinessException {
		Map<String, MarBasClassVO> mapMarBasClassByPk = new HashMap<String, MarBasClassVO>();
		String[] pks = VOEntityUtil.getVOsNotRepeatValue(vos,
				MaterialVO.PK_MARBASCLASS);
		String conditon = SqlUtil.buildSqlForIn(MarBasClassVO.PK_MARBASCLASS, pks);
		try {
			MarBasClassVO[] marBasClassVos = (MarBasClassVO[]) getBaseDao()
					.retrieveByClause(MarBasClassVO.class, conditon).toArray(
							new MarBasClassVO[0]);
			mapMarBasClassByPk = CollectionUtils.hashVOArray(
					MarBasClassVO.PK_MARBASCLASS, marBasClassVos);
		} catch (Exception err) {
			throw new BusinessException("获取物料分类遇到异常：" + err.getMessage());
		}

		return mapMarBasClassByPk;
	}

	private void insertDBValue(MaterialVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		Map<String, MarBasClassVO> mapMarBasClassByPk=getMaterialClass(vos);
		for (MaterialVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("insert into tif_bd_material(pk_material,code,name,materialspec,materialtype,steelgrd,pk_marbasclass,");
			sql.append("marbasclasscode,marbasclasname,tlastposttime,enablestate) values(");
			sql.append("'" + vo.getPk_material() + "',");
			sql.append("'" + vo.getCode() + "',");
			sql.append("'" + vo.getName() + "','");
			sql.append(vo.getMaterialspec() == null ? "" : vo.getMaterialspec());
			sql.append("','");
			sql.append(vo.getMaterialtype() == null ? "" : vo.getMaterialtype());
			sql.append("','");
			sql.append("','");
			sql.append(vo.getPk_marbasclass());
			sql.append("','");
			sql.append(mapMarBasClassByPk.get(vo.getPk_marbasclass()).getCode());
			sql.append("','");
			sql.append(mapMarBasClassByPk.get(vo.getPk_marbasclass()).getName());
			sql.append("','" + dtNow.toStdString() + "',");
			sql.append("" + vo.getEnablestate() + ")");
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void updateDBValue(MaterialVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		Map<String, MarBasClassVO> mapMarBasClassByPk=getMaterialClass(vos);
		for (MaterialVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_material set code='");
			sql.append(vo.getCode());
			sql.append("',name='");
			sql.append(vo.getName());
			sql.append("',materialspec='");
			sql.append(vo.getMaterialspec() == null ? "" : vo.getMaterialspec());
			sql.append("',materialtype='");
			sql.append(vo.getMaterialtype() == null ? "" : vo.getMaterialtype());
			sql.append("',steelgrd='");
			sql.append("',pk_marbasclass='");
			sql.append(vo.getPk_marbasclass());
			sql.append("',marbasclasscode='");
			sql.append(mapMarBasClassByPk.get(vo.getPk_marbasclass()).getCode());
			sql.append("',marbasclasname='");
			sql.append(mapMarBasClassByPk.get(vo.getPk_marbasclass()).getName());
			sql.append("',tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append(vo.getEnablestate());
			sql.append("' where ");
			sql.append("pk_material", vo.getPk_material());
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void deleteDBValue(MaterialVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		for (MaterialVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_material set tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append("4");
			sql.append("' where ");
			sql.append("pk_material", vo.getPk_material());
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void enabledisableDBValue(Object[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		for (Object vo : vos) {
			MaterialVO mbcVO = (MaterialVO) vo;
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_material set tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append(mbcVO.getEnablestate());
			sql.append("' where ");
			sql.append("pk_material", mbcVO.getPk_material());
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}
	
	private BaseDAO getBaseDao(){
		if(baseDao==null){
			baseDao=new BaseDAO();
		}
		return baseDao;
	}

	private BaseDAO getBaseDaoMes() {
		if (baseDaoMes == null) {
			baseDaoMes = new BaseDAO("mesdb");
			baseDaoMes.setAddTimeStamp(false);
		}
		return baseDaoMes;
	}

}
