package nc.bs.bd.pfxx.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.bs.uap.bd.supplier.ISupplierConst;
import nc.itf.bd.supplier.baseinfo.ISupplierBaseInfoService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.IMDPersistenceService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.supplier.SupLinkmanVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pfxx.util.ArrayUtils;
import nc.vo.pfxx.util.PfxxPluginUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;

public class SupplierPfxxPlugin extends AbstractPfxxPlugin {

  private IMDPersistenceQueryService mdQryService;

  private IMDPersistenceService mdService;

  private ISupplierBaseInfoService basesService;

  @Override
  protected Object processBill(Object vo, ISwapContext swapContext, AggxsysregisterVO aggvo)
      throws BusinessException {
    SupplierVO supplierVO = (SupplierVO) vo;
    if (!hasUniqueDefaultLinkMan(supplierVO))
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("bdpub",
          "0bdpub0060")/*@res "供应商默认联系人必须唯一。"*/);
    String voPk =
        PfxxPluginUtils.queryBillPKBeforeSaveOrUpdate(swapContext.getBilltype(),
            swapContext.getDocID());
    setVOStatus(supplierVO.getSuplinkman(), VOStatus.NEW);
    
    String aa="";
    if (voPk == null) {
      supplierVO.setStatus(VOStatus.NEW);
      supplierVO = getBasesService().pfxxInsertSupplierVO(supplierVO, false);
      voPk = supplierVO.getPk_supplier();
      PfxxPluginUtils.addDocIDVsPKContrast(swapContext.getBilltype(), swapContext.getDocID(), voPk);
    }
    else {
      supplierVO.setStatus(VOStatus.UPDATED);
      setUpdateValues(supplierVO, voPk);
      getBasesService().pfxxUpdateSupplierVO(supplierVO, false);
    }
    return voPk;
  }

  private boolean hasUniqueDefaultLinkMan(SupplierVO supplier) {
    if (supplier.getSuplinkman() == null || supplier.getSuplinkman().length == 0)
      return true;
    int i = 0;
    for (SupLinkmanVO linkMan : supplier.getSuplinkman()) {
      if (linkMan.getIsdefault() != null && linkMan.getIsdefault().booleanValue() == true) {
        i++;
      }
    }
    return i > 1 ? false : true;
  }

  private void setVOStatus(SuperVO[] vos, int status) {
    if (ArrayUtils.isEmpty(vos))
      return;
    for (SuperVO vo : vos)
      vo.setStatus(status);
  }

  private void setUpdateValues(SupplierVO updateDocVO, String pk) throws BusinessException {
    Object[] objs = getMdQryService().queryBillOfVOByPKsWithOrder(SupplierVO.class, new String[] {
      pk
    }, new String[] {
      ISupplierConst.ATTR_SUPPLIER_CONTACTS
    });
    if (ArrayUtils.isEmpty(objs))
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("bdpub",
          "0bdpub0057")/* @res "单据已被删除" */);
    SupplierVO oldDocVO = (SupplierVO) objs[0];
    updateDocVO.setCreator(oldDocVO.getCreator());
    updateDocVO.setCreationtime(oldDocVO.getCreationtime());
    updateDocVO.setModifier(oldDocVO.getModifier());
    updateDocVO.setModifiedtime(oldDocVO.getModifiedtime());
    updateDocVO.setDataoriginflag(oldDocVO.getDataoriginflag());
    updateDocVO.setPrimaryKey(pk);
    updateDocVO.setPk_oldsupplier(oldDocVO.getPk_oldsupplier());
    List<SupLinkmanVO> linkManVOList = new ArrayList<SupLinkmanVO>();
    if (oldDocVO.getSuplinkman() != null) {
      for (SupLinkmanVO linkManVO : oldDocVO.getSuplinkman()) {
        linkManVO.setStatus(VOStatus.DELETED);
        linkManVOList.add(linkManVO);
      }
    }
    if (updateDocVO.getSuplinkman() != null) {
      linkManVOList.addAll(Arrays.asList(updateDocVO.getSuplinkman()));
    }
    updateDocVO.setSuplinkman(linkManVOList.toArray(new SupLinkmanVO[0]));
  }

  private IMDPersistenceQueryService getMdQryService() {
    if (mdQryService == null)
      mdQryService = MDPersistenceService.lookupPersistenceQueryService();
    return mdQryService;
  }

  private ISupplierBaseInfoService getBasesService() {
    if (basesService == null)
      basesService = NCLocator.getInstance().lookup(ISupplierBaseInfoService.class);
    return basesService;
  }

  private IMDPersistenceService getMdService() {
    if (mdService == null)
      mdService = MDPersistenceService.lookupPersistenceService();
    return mdService;
  }
}
