package nc.bs.lhitf.mesitf.plugin;

import java.util.HashMap;
import java.util.Map;

import nc.bs.bd.pub.IBDEventType;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.bd.BDCommonEvent;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.tmpub.util.SqlUtil;

public class StordocHandlerListener implements IBusinessListener {

	private BaseDAO baseDaoMes;
	private BaseDAO baseDao;
	
	
	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		processStorDocPostMes(event);
	}
	
	private void processStorDocPostMes(IBusinessEvent event)
			throws BusinessException {
		if (event instanceof BDCommonEvent) {
			if (event.getEventType().equals(IBDEventType.TYPE_INSERT_AFTER)) {
				// 新增时
				// Object[] oldObjects = ((BDCommonEvent) event).getOldObjs();
				Object[] newObjects = (Object[]) ((BDCommonEvent) event)
						.getNewObjs();
				StordocVO[] vos=convertObjToStorVO(newObjects);
				insertDBValue(vos);
				// if (oldObjects == null || oldObjects.length == 0) {
				// return;
				// }

			} else if (event.getEventType().equals(
					IBDEventType.TYPE_UPDATE_AFTER)) {
				// 修改时
				Object[] newObjects = (Object[]) ((BDCommonEvent) event)
						.getNewObjs();
				StordocVO[] vos=convertObjToStorVO(newObjects);
				updateDBValue(vos);

			} else if (event.getEventType().equals(
					IBDEventType.TYPE_DELETE_AFTER)) {
				// 删除时
				Object[] oldObjects = (Object[]) ((BDCommonEvent) event)
						.getNewObjs();
				StordocVO[] vos=convertObjToStorVO(oldObjects);
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
	
	private StordocVO[] convertObjToStorVO(Object[] obj) {
		StordocVO[] vos = new StordocVO[obj.length];
		for (int i = 0; i < obj.length; i++) {
			StordocVO vo = (StordocVO) obj[i];
			vos[i] = vo;
		}
		return vos;
	}
	
	private Map<String, OrgVO> getOrgVOs(StordocVO[] vos)
			throws BusinessException {
		Map<String, OrgVO> mapOrgVOByPk = new HashMap<String, OrgVO>();
		String[] pks = VOEntityUtil.getVOsNotRepeatValue(vos,
				StordocVO.PK_ORG);
		String conditon = SqlUtil.buildSqlForIn(StordocVO.PK_ORG, pks);
		try {
			OrgVO[] orgVos = (OrgVO[]) getBaseDao()
					.retrieveByClause(OrgVO.class, conditon).toArray(
							new OrgVO[0]);
			mapOrgVOByPk = CollectionUtils.hashVOArray(
					OrgVO.PK_ORG, orgVos);
		} catch (Exception err) {
			throw new BusinessException("获取组织遇到异常：" + err.getMessage());
		}

		return mapOrgVOByPk;
	}


	private void insertDBValue(StordocVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		Map<String, OrgVO> mapOrgVOByPk=getOrgVOs(vos);
		for (StordocVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("insert into tif_bd_stordoc(pk_stordoc,code,name,orgcode,orgname,tlastposttime,enablestate) values(");
			sql.append("'" + vo.getPk_stordoc() + "',");
			sql.append("'" + vo.getCode() + "',");
			sql.append("'" + vo.getName() + "','");
			sql.append(mapOrgVOByPk.get(vo.getPk_org()).getCode());
			sql.append("','");
			sql.append(mapOrgVOByPk.get(vo.getPk_org()).getName());
			sql.append("','" + dtNow.toStdString() + "',");
			sql.append("" + vo.getEnablestate() + ")");
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void updateDBValue(StordocVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		Map<String, OrgVO> mapOrgVOByPk=getOrgVOs(vos);
		for (StordocVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_stordoc set code='");
			sql.append(vo.getCode());
			sql.append("',name='");
			sql.append(vo.getName());
			sql.append("',orgcode='");
			sql.append(mapOrgVOByPk.get(vo.getPk_org()).getCode());
			sql.append("',orgname='");
			sql.append(mapOrgVOByPk.get(vo.getPk_org()).getName());
			sql.append("',tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append(vo.getEnablestate());
			sql.append("' where ");
			sql.append("pk_stordoc", vo.getPk_stordoc());
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void deleteDBValue(StordocVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		for (StordocVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_stordoc set tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append("4");
			sql.append("' where ");
			sql.append("pk_stordoc", vo.getPk_stordoc());
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void enabledisableDBValue(Object[] vos)
			throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		for (Object vo : vos) {
			StordocVO mbcVO=(StordocVO) vo;
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_stordoc set tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append(mbcVO.getEnablestate());
			sql.append("' where ");
			sql.append("pk_stordoc", mbcVO.getPk_stordoc());
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
