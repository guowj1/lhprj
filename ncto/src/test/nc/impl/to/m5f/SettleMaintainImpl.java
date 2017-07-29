package nc.impl.to.m5f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.to.m5f.entity.SettleListHeaderVO;
import nc.vo.to.m5f.entity.SettleListItemVO;
import nc.vo.to.m5f.entity.SettleListLineVO;
import nc.vo.to.m5f.entity.SettleListVO;
import nc.vo.to.m5f.entity.SettleQueryTransVO;
import nc.vo.to.pub.TOQueryApproveUtil;
import nc.vo.to.pub.TOTempTableNameConst;

import nc.itf.to.m5f.ISettleMaintain;

import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.to.m5f.action.DeleteAction;
import nc.impl.to.m5f.action.DoFinanceAction;
import nc.impl.to.m5f.action.InsertSettleListAction;
import nc.impl.to.m5f.action.MakeInvoiceAction;
import nc.impl.to.m5f.action.SettleQueryAction;
import nc.impl.to.m5f.action.UnDoFinanceAction;
import nc.impl.to.m5f.action.UnMakeInvoiceAction;
import nc.impl.to.m5f.action.UpdateAction;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>结算清单新增保存
 * <li>结算清单修改保存
 * <li>结算清单删除
 * <li>结算清单查询
 * <li>结算查询操作
 * </ul>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author panql
 * @time 2010-1-25 下午07:32:50
 */
public class SettleMaintainImpl implements ISettleMaintain {

  @Override
  public void deleteSettleList(SettleListVO[] bills) throws BusinessException {
    try {
      DeleteAction action = new DeleteAction();
      action.delete(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public SettleListVO[] doFinance(SettleListVO[] bills)
      throws BusinessException {
    SettleListVO[] ret = null;
    try {
      DoFinanceAction action = new DoFinanceAction();
      ret = action.doFinance(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SettleListVO[] insertSettleList(SettleQueryTransVO transvo)
      throws BusinessException {
    SettleListVO[] ret = null;
    try {
      InsertSettleListAction insert = new InsertSettleListAction();
      ret = insert.doInsert(transvo);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SettleListVO[] makeInvoice(SettleListVO[] bills)
      throws BusinessException {
    SettleListVO[] ret = null;
    try {
      MakeInvoiceAction action = new MakeInvoiceAction();
      ret = action.makeInvoice(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SettleQueryTransVO querySettle(IQueryScheme scheme)
      throws BusinessException {
    SettleQueryTransVO model = null;
    try {
      // 调用结算查询动作处理
      SettleQueryAction action = new SettleQueryAction();
      model = action.querySettle(scheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return model;
  }

  @Override
  public SettleListVO[] querySettleList(IQueryScheme queryScheme)
      throws BusinessException {
    SettleListVO[] bills = null;
    try {

      QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
      String headTableName = processor.getMainTableAlias();
      // 添加子表名
      // processor.getTableAliasOfAttribute("bodyfk.cinventoryvid");

      processor.appendCurrentGroup();
      processor.appendFuncPermissionOrgSql();
      SqlBuilder order = new SqlBuilder();
      order.append("order by ");
      order.append(headTableName);
      order.append(".vbillcode");

      BillLazyQuery<SettleListVO> qry =
          new BillLazyQuery<SettleListVO>(SettleListVO.class);
      bills = qry.query(queryScheme, order.toString());

      if (bills != null && bills.length > 0) {
        this.dealDigit(new SettleListVO[] {
          bills[0]
        });
        this.fillGoldaTaxFlag(bills);
        bills =
            TOQueryApproveUtil.filterForApprove(queryScheme, bills,
                TOBillType.SettleList.getCode());
      }

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public SettleListVO querySettleListByHeadID(String headid)
      throws BusinessException {
    SettleListVO[] vos = new SettleListVO[] {};
    try {
      BillQuery<SettleListVO> query =
          new BillQuery<SettleListVO>(SettleListVO.class);
      vos = query.query(new String[] {
        headid
      });
      if (vos == null || vos.length == 0) {
        return null;
      }

      this.dealDigit(vos);

      this.fillGoldaTaxFlag(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
    return vos[0];
  }

  @Override
  public MapList<String, SettleListLineVO> querySettleListLine(
      String[] cbillbids) throws BusinessException {
    MapList<String, SettleListLineVO> maplist =
        new MapList<String, SettleListLineVO>();

    try {
      VOQuery<SettleListLineVO> query =
          new VOQuery<SettleListLineVO>(SettleListLineVO.class);
      SqlBuilder builder = new SqlBuilder();
      IDExQueryBuilder idBuilder =
          new IDExQueryBuilder(TOTempTableNameConst.TMP_TO_FIR_1ID);
      builder.append(" and ");
      builder.append(idBuilder.buildSQL(SettleListLineVO.CBILL_BID, cbillbids));
      SettleListLineVO[] vos = query.query(builder.toString(), null);

      for (SettleListLineVO vo : vos) {
        maplist.put(vo.getCbill_bid(), vo);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return maplist;
  }

  @Override
  public SettleListVO[] unDoFinance(SettleListVO[] bills)
      throws BusinessException {
    SettleListVO[] ret = null;
    try {
      UnDoFinanceAction action = new UnDoFinanceAction();
      ret = action.unDoFinanace(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SettleListVO[] unMakeInvoice(SettleListVO[] bills)
      throws BusinessException {
    SettleListVO[] ret = null;
    try {
      UnMakeInvoiceAction action = new UnMakeInvoiceAction();
      ret = action.unMakeInvoice(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SettleListVO updateSettleList(SettleListVO bill)
      throws BusinessException {
    SettleListVO ret = null;
    try {
      UpdateAction action = new UpdateAction();
      ret = action.update(bill);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SettleListLineVO[] updateSettListLinePrice(SettleListLineVO[] linevos)
      throws BusinessException {
    Set<String> set = new HashSet<String>();
    Set<String> linid = new HashSet<String>();
    for (SettleListLineVO line : linevos) {
      set.add(line.getCbill_bbid());
      set.add(line.getCbill_bid());
      set.add(line.getCbillid());

      linid.add(line.getCbill_bbid());

    }

    SettleListLineVO[] vos = null;
    try {
      this.lockLine(set);
      this.checkLineTs(linid.toArray(new String[linid.size()]), linevos);
      vos = this.updateLinePrice(linevos);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  private void checkLineTs(String[] lineids, SettleListLineVO[] linevos) {
    VOQuery<SettleListLineVO> query =
        new VOQuery<SettleListLineVO>(SettleListLineVO.class);
    SettleListLineVO[] newlinevos = query.query(lineids);
    VOConcurrentTool tool = new VOConcurrentTool();
    tool.checkTS(newlinevos, linevos);

  }

  private void dealDigit(SettleListVO[] vos) {
    ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
    for (SettleListVO vo : vos) {
      String corigcurrencyid = vo.getParentVO().getCorigcurrencyid();
      SettleListItemVO[] items = vo.getItemVOs();
      for (SettleListItemVO item : items) {
        item.setNorigprice(scale.adjustSoPuPriceScale(item.getNorigprice(),
            corigcurrencyid));
        item.setNorigtaxprice(scale.adjustSoPuPriceScale(
            item.getNorigtaxprice(), corigcurrencyid));
        item.setNqtorigprice(scale.adjustSoPuPriceScale(item.getNqtorigprice(),
            corigcurrencyid));
        item.setNqtorigtaxprice(scale.adjustSoPuPriceScale(
            item.getNqtorigtaxprice(), corigcurrencyid));
      }
    }
  }

  private void fillGoldaTaxFlag(SettleListVO[] bills) {
    List<String> list = new ArrayList<String>();
    for (SettleListVO bill : bills) {
      SettleListHeaderVO head = bill.getParentVO();
      if (PubAppTool.isNull(head.getCsettlepathid())) {
        list.add(head.getCbillid());
      }
    }
    if (list.size() == 0) {
      return;
    }

    SqlBuilder builder = new SqlBuilder();
    IDExQueryBuilder idBuild =
        new IDExQueryBuilder(TOTempTableNameConst.TMP_TO_FIR_1ID);
    builder.append(" and ");
    builder.append(idBuild.buildSQL(SettleListLineVO.CBILLID,
        list.toArray(new String[list.size()])));

    VOQuery<SettleListLineVO> query =
        new VOQuery<SettleListLineVO>(SettleListLineVO.class, new String[] {
          SettleListLineVO.CBILLID, SettleListLineVO.BGOLDENTAXFLAG
        });
    SettleListLineVO[] lines = query.query(builder.toString(), null);
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (SettleListLineVO line : lines) {
      map.put(line.getCbillid(), line.getBgoldentaxflag());
    }
    for (SettleListVO bill : bills) {
      SettleListHeaderVO head = bill.getParentVO();
      if (PubAppTool.isNull(head.getCsettlepathid())) {
        head.setBgoldentaxflag(map.get(head.getCbillid()));
      }
    }

  }

  private void lockLine(Set<String> set) {
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4009003_0",
            "04009003-0160")/*@res "出现并发操作，请重新查询"*/;
    LockOperator lock = new LockOperator();
    lock.lock(set.toArray(new String[set.size()]), message);
  }

  private SettleListLineVO[] updateLinePrice(SettleListLineVO[] linevos) {
    String[] names =
        new String[] {
    	  SettleListLineVO.NDISCOUNTVALUE,//gwj
          SettleListLineVO.NDISCOUNTRATE, SettleListLineVO.NEXCHANGERATE,
          SettleListLineVO.NORIGMNY, SettleListLineVO.NORIGPRICE,
          SettleListLineVO.NCALTAXMNY, SettleListLineVO.NINCALTAXMNY,
          SettleListLineVO.NINEXCHANGERATE, SettleListLineVO.NINMNY,
          SettleListLineVO.NINORIGMNY, SettleListLineVO.NINORIGPRICE,
          SettleListLineVO.NINPRICE, SettleListLineVO.NINQTORIGPRICE,
          SettleListLineVO.NINQTPRICE, SettleListLineVO.NINQTTAXPRICE,
          SettleListLineVO.NINTAX, SettleListLineVO.NINTAXMNY,
          SettleListLineVO.NINTAXPRICE, SettleListLineVO.NINTAXRATE,
          SettleListLineVO.NMNY, SettleListLineVO.NNOSUBTAX,
          SettleListLineVO.NNOSUBTAXRATE, SettleListLineVO.NPRICE,
          SettleListLineVO.NQTPRICE, SettleListLineVO.NQTTAXPRICE,
          SettleListLineVO.NTAX, SettleListLineVO.NTAXMNY,
          SettleListLineVO.NTAXPRICE, SettleListLineVO.CTAXCODEID,
          SettleListLineVO.CINTAXCODEID, SettleListLineVO.FINTAXTYPEFLAG,

          SettleListLineVO.NORIGTAXMNY, SettleListLineVO.NORIGTAXPRICE,
          SettleListLineVO.FTAXTYPEFLAG, SettleListLineVO.NTAXRATE
        };
    VOUpdate<SettleListLineVO> update = new VOUpdate<SettleListLineVO>();
    SettleListLineVO[] vos = update.update(linevos, names);
    return vos;
  }
}
