package nc.bs.lhitf.mesitf.plugin;

import nc.bs.bd.pub.IBDEventType;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.bd.BDCommonEvent;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class MaterialClassHandlerListenser implements IBusinessListener {

	private BaseDAO baseDaoMes;

	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		this.processMaterialClassPostMes(event);
	}

	private void processMaterialClassPostMes(IBusinessEvent event)
			throws BusinessException {
		if (event instanceof BDCommonEvent) {
			if (event.getEventType().equals(IBDEventType.TYPE_INSERT_AFTER)) {
				// 新增时
				// Object[] oldObjects = ((BDCommonEvent) event).getOldObjs();
				Object[] newObjects =  ((BDCommonEvent) event)
						.getNewObjs();
				MarBasClassVO[] vos=convertObjToMatVO(newObjects);
				insertDBValue(vos);
				// if (oldObjects == null || oldObjects.length == 0) {
				// return;
				// }

			} else if (event.getEventType().equals(
					IBDEventType.TYPE_UPDATE_AFTER)) {
				// 修改时
				Object[] newObjects = ((BDCommonEvent) event)
						.getNewObjs();
				MarBasClassVO[] vos=convertObjToMatVO(newObjects);
				updateDBValue(vos);

			} else if (event.getEventType().equals(
					IBDEventType.TYPE_DELETE_AFTER)) {
				// 删除时
				Object[] oldObjects = ((BDCommonEvent) event)
						.getNewObjs();
				MarBasClassVO[] vos=convertObjToMatVO(oldObjects);
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
	
	private MarBasClassVO[] convertObjToMatVO(Object[] obj) {
		MarBasClassVO[] vos = new MarBasClassVO[obj.length];
		for (int i = 0; i < obj.length; i++) {
			MarBasClassVO vo = (MarBasClassVO) obj[i];
			vos[i] = vo;
		}
		return vos;
	}

	private void insertDBValue(MarBasClassVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		for (MarBasClassVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("insert into tif_bd_marbasdoc(pk_marbasdoc,code,name,pk_farther,tlastposttime,enablestate) values(");
			sql.append("'" + vo.getPk_marbasclass() + "',");
			sql.append("'" + vo.getCode() + "',");
			sql.append("'" + vo.getName() + "','");
			sql.append(vo.getPk_parent() == null ? "" : vo.getPk_parent());
			sql.append("','" + dtNow.toStdString() + "',");
			sql.append("" + vo.getEnablestate() + ")");
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void updateDBValue(MarBasClassVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		for (MarBasClassVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_marbasdoc set code='");
			sql.append(vo.getCode());
			sql.append("',name='");
			sql.append(vo.getName());
			sql.append("',pk_farther='");
			sql.append(vo.getPk_parent() == null ? "" : vo.getPk_parent());
			sql.append("',tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append(vo.getEnablestate());
			sql.append("' where ");
			sql.append("pk_marbasdoc", vo.getPk_marbasclass());
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private void deleteDBValue(MarBasClassVO[] vos) throws BusinessException {
		UFDateTime dtNow = AppContext.getInstance().getServerTime();
		for (MarBasClassVO vo : vos) {
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_marbasdoc set tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append("4");
			sql.append("' where ");
			sql.append("pk_marbasdoc", vo.getPk_marbasclass());
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
			MarBasClassVO mbcVO=(MarBasClassVO) vo;
			SqlBuilder sql = new SqlBuilder();
			sql.append("update tif_bd_marbasdoc set tlastposttime='");
			sql.append(dtNow.toStdString());
			sql.append("',enablestate='");
			sql.append(mbcVO.getEnablestate());
			sql.append("' where ");
			sql.append("pk_marbasdoc", mbcVO.getPk_marbasclass());
			try {
				getBaseDaoMes().executeUpdate(sql.toString());
			} catch (DAOException e) {
				throw new BusinessException("同步到MES数据库遇到异常！" + e.getMessage());
			}
		}
	}

	private BaseDAO getBaseDaoMes() {
		if (baseDaoMes == null) {
			baseDaoMes = new BaseDAO("mesdb");
			baseDaoMes.setAddTimeStamp(false);
		}
		return baseDaoMes;
	}
}
