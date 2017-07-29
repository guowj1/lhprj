package nc.ui.to.m5f.revisepath.util;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.to.m5f.ISettleMaintain;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.to.m5f.entity.STInvDisplayVO;
import nc.vo.to.m5f.entity.STInvPathAggVO;
import nc.vo.to.m5f.entity.STPathDisplayVO;
import nc.vo.to.m5f.entity.STPathInvAggVO;
import nc.vo.to.m5f.entity.SettleListHeaderVO;
import nc.vo.to.m5f.entity.SettleListItemVO;
import nc.vo.to.m5f.entity.SettleListLineVO;
import nc.vo.to.m5f.entity.SettleListVO;

public class SettleListVOConvertUtil {

  public SettleListLineVO[] chgInvPathToList(STInvPathAggVO[] vos) {
    List<SettleListLineVO> lstRet = new ArrayList<SettleListLineVO>();

    for (STInvPathAggVO vo : vos) {
      STPathDisplayVO[] items = vo.getItemVOs();
      for (STPathDisplayVO item : items) {
        SettleListLineVO line = this.chgLineForInvPath(item);
        lstRet.add(line);
      }
    }

    return lstRet.toArray(new SettleListLineVO[0]);

  }

  private SettleListLineVO chgLineForInvPath(STPathDisplayVO item) {
    SettleListLineVO line = new SettleListLineVO();
    line.setFtaxtypeflag(item.getFtaxtypeflag());
    line.setCbill_bbid(item.getCbill_bbid());
    line.setCbill_bid(item.getCbill_bid());
    line.setCbillid(item.getCbillid());
    line.setNdiscountrate(item.getNdiscountrate());
    line.setNdiscountvalue(item.getNdiscountvalue());//gwj
    line.setNexchangerate(item.getNexchangerate());
    line.setNorigmny(item.getNorigmny());
    line.setNorigprice(item.getNorigprice());
    line.setNtaxrate(item.getNtaxrate());
    line.setNorigqtprice(item.getNorigqtprice());
    line.setNorigqttaxprice(item.getNorigqtprice());
    line.setNorigtaxmny(item.getNorigtaxmny());
    line.setNorigtaxprice(item.getNorigtaxprice());
    line.setNcaltaxmny(item.getNcaltaxmny());
    line.setCtaxcodeid(item.getCtaxcodeid());
    line.setCintaxcodeid(item.getCintaxcodeid());
    line.setFintaxtypeflag(item.getFintaxtypeflag());
    line.setFtaxtypeflag(item.getFtaxtypeflag());
    line.setNincaltaxmny(item.getNincaltaxmny());
    line.setNinmny(item.getNinmny());
    line.setNinorigmny(item.getNinorigmny());
    line.setNinorigprice(item.getNinorigprice());
    line.setNinprice(item.getNinprice());
    line.setNinqtorigprice(item.getNinqtorigprice());
    line.setNinqtprice(item.getNinqtprice());
    line.setNinqttaxprice(item.getNinqttaxprice());
    line.setNintax(item.getNintax());
    line.setNintaxmny(item.getNintaxmny());
    line.setNintaxprice(item.getNintaxprice());
    line.setNintaxrate(item.getNintaxrate());
    line.setNinexchangerate(item.getNinexchangerate());
    line.setNmny(item.getNmny());
    line.setNnosubtax(item.getNnosubtax());
    line.setNnosubtaxrate(item.getNnosubtaxrate());
    line.setNprice(item.getNprice());
    line.setNqtprice(item.getNqtprice());
    line.setNqttaxprice(item.getNqttaxprice());
    line.setNtax(item.getNtax());
    line.setNtaxmny(item.getNtaxmny());
    line.setNtaxprice(item.getNtaxprice());

    line.setTs(item.getTs());
    return line;

  }

  public SettleListLineVO[] chgPathInvToList(STPathInvAggVO[] vos) {
    List<SettleListLineVO> lstRet = new ArrayList<SettleListLineVO>();
    List<STPathDisplayVO> list = new ArrayList<STPathDisplayVO>();
    for (STPathInvAggVO vo : vos) {
      STInvDisplayVO[] items = vo.getItemVOs();
      for (STInvDisplayVO item : items) {
        STPathDisplayVO line = new STPathDisplayVO();
        line.setFtaxtypeflag(item.getBb_ftaxtypeflag());
        line.setCbill_bbid(item.getBb_cbill_bbid());
        line.setCbill_bid(item.getCbill_bid());
        line.setCbillid(item.getCbillid());
        line.setNdiscountrate(item.getBb_ndiscountrate());
        line.setNdiscountvalue(item.getBb_ndiscountvalue());//gwj
        line.setNexchangerate(item.getBb_nexchangerate());
        line.setNorigmny(item.getBb_norigmny());
        line.setNorigprice(item.getBb_norigprice());
        line.setNtaxrate(item.getBb_ntaxrate());
        line.setNorigqtprice(item.getBb_norigqtprice());
        line.setNorigqttaxprice(item.getBb_norigqtprice());

        line.setNorigtaxmny(item.getBb_norigtaxmny());
        line.setNorigtaxprice(item.getBb_norigtaxprice());
        line.setTs(item.getBb_cbill_ts());

        line.setNcaltaxmny(item.getBb_ncaltaxmny());
        line.setCtaxcodeid(item.getBb_ctaxcodeid());
        line.setCintaxcodeid(item.getBb_cintaxcodeid());
        line.setFintaxtypeflag(item.getBb_fintaxtypeflag());
        line.setFtaxtypeflag(item.getBb_ftaxtypeflag());
        line.setNincaltaxmny(item.getBb_nincaltaxmny());
        line.setNinmny(item.getBb_ninmny());
        line.setNinorigmny(item.getBb_ninorigmny());
        line.setNinorigprice(item.getBb_ninorigprice());
        line.setNinprice(item.getBb_ninprice());
        line.setNinqtorigprice(item.getBb_ninqtorigpric());
        line.setNinqtprice(item.getBb_ninqtprice());
        line.setNinqttaxprice(item.getBb_ninqttaxprice());
        line.setNintax(item.getBb_nintax());
        line.setNintaxmny(item.getBb_nintaxmny());
        line.setNintaxprice(item.getBb_nintaxprice());
        line.setNintaxrate(item.getBb_nintaxrate());
        line.setNinexchangerate(item.getBb_ninexchangerate());
        line.setNmny(item.getBb_nmny());
        line.setNnosubtax(item.getBb_nnosubtax());
        line.setNnosubtaxrate(item.getBb_nnosubtaxrate());
        line.setNprice(item.getBb_nprice());
        line.setNqtprice(item.getBb_nqtprice());
        line.setNqttaxprice(item.getBb_nqttaxprice());
        line.setNtax(item.getBb_ntax());
        line.setNtaxmny(item.getBb_ntaxmny());
        line.setNtaxprice(item.getBb_ntaxprice());

        list.add(line);
      }
    }
    for (STPathDisplayVO vo : list) {
      SettleListLineVO line = this.chgLineForInvPath(vo);
      lstRet.add(line);
    }

    return lstRet.toArray(new SettleListLineVO[0]);

  }

  public STInvPathAggVO[] chgToInvPathVO(SettleListVO vo) {
    List<STInvPathAggVO> list = new ArrayList<STInvPathAggVO>();
    SettleListHeaderVO header = vo.getParentVO();
    SettleListItemVO[] items = vo.getItemVOs();
    MapList<String, SettleListLineVO> maplist = this.getSettleLineVO(items);
    for (SettleListItemVO item : items) {
      List<SettleListLineVO> linelist = maplist.get(item.getCbill_bid());
      STInvPathAggVO aggvo = new STInvPathAggVO();
      STInvDisplayVO head = this.getInvPathHead(header, item);
      STPathDisplayVO[] bodyitems = this.getInvPathBody(header, item, linelist);
      aggvo.setHeadVO(head);
      aggvo.setItemVOs(bodyitems);
      list.add(aggvo);
    }

    return list.toArray(new STInvPathAggVO[0]);
  }

  private STPathDisplayVO[] getInvPathBody(SettleListHeaderVO header,
      SettleListItemVO item, List<SettleListLineVO> linelist) {
    List<STPathDisplayVO> list = new ArrayList<STPathDisplayVO>();
    for (SettleListLineVO line : linelist) {
      STPathDisplayVO vo = new STPathDisplayVO();
      vo.setCbill_bbid(line.getCbill_bbid());
      vo.setCbill_bid(line.getCbill_bid());
      vo.setCbillid(line.getCbillid());
      vo.setCcurrencyid(line.getCcurrencyid());
      vo.setCincurrencyid(line.getCincurrencyid());
      vo.setCoutcurrencyid(line.getCoutcurrencyid());
      vo.setCdowncostregionid(line.getCdowncostregionid());
      vo.setCdownfinanceorgid(line.getCdownfinanceorgid());
      vo.setCdownfinanceorgvid(line.getCdownfinanceorgvid());
      vo.setCrowno(line.getCrowno());
      vo.setCupcostregionid(line.getCupcostregionid());
      vo.setCupfinanceorgid(line.getCupfinanceorgid());
      vo.setCupfinanceorgvid(line.getCupfinanceorgvid());
      vo.setFtaxtypeflag(line.getFtaxtypeflag());
      vo.setNdiscountrate(line.getNdiscountrate());
      vo.setNdiscountvalue(line.getNdiscountvalue());//gwj
      vo.setNexchangerate(line.getNexchangerate());
      vo.setNnumber(item.getNnumber());
      vo.setNorigmny(line.getNorigmny());
      vo.setNorigprice(line.getNorigprice());

      vo.setNorigtaxmny(line.getNorigtaxmny());
      vo.setNorigtaxprice(line.getNorigtaxprice());

      vo.setNtaxrate(line.getNtaxrate());
      vo.setTs(line.getTs());
      vo.setNorigqtprice(line.getNorigqtprice());
      vo.setNorigqttaxprice(line.getNorigqttaxprice());
      vo.setCincountryid(line.getCincountryid());
      vo.setCoutcountryid(line.getCoutcountryid());
      vo.setCouttaxcountryid(line.getCouttaxcountryid());
      vo.setFoutbuysellflag(line.getFoutbuysellflag());
      vo.setBouttriatradeflag(line.getBouttriatradeflag());
      vo.setCintaxcountryid(line.getCintaxcountryid());
      vo.setFinbuysellflag(line.getFinbuysellflag());
      vo.setBintriatradeflag(line.getBintriatradeflag());
      vo.setVinvatcode(line.getVinvatcode());
      vo.setVoutvatcode(line.getVoutvatcode());
      vo.setCtradewordid(header.getCtradewordid());
      vo.setDbilldate(header.getDbilldate());
      vo.setCincurrencyid(line.getCincurrencyid());
      vo.setCoutcurrencyid(line.getCoutcurrencyid());
      vo.setCtaxcodeid(line.getCtaxcodeid());
      vo.setNprice(line.getNprice());
      vo.setNqtprice(line.getNqtprice());
      vo.setNqttaxprice(line.getNqttaxprice());

      vo.setNtax(line.getNtax());
      vo.setNtaxmny(line.getNtaxmny());
      vo.setNmny(line.getNmny());
      vo.setNtaxprice(line.getNtaxprice());
      vo.setNcaltaxmny(line.getNcaltaxmny());
      vo.setCintaxcodeid(line.getCintaxcodeid());
      vo.setNintaxrate(line.getNintaxrate());
      vo.setFintaxtypeflag(line.getFintaxtypeflag());
      vo.setNincaltaxmny(line.getNincaltaxmny());
      vo.setNintax(line.getNintax());
      vo.setNintaxmny(line.getNintaxmny());
      vo.setNintaxprice(line.getNintaxprice());
      vo.setNinprice(line.getNinprice());
      vo.setNinmny(line.getNinmny());
      vo.setNinqtprice(line.getNinqtprice());
      vo.setNinqttaxprice(line.getNinqttaxprice());
      vo.setNinexchangerate(line.getNinexchangerate());
      vo.setNinorigmny(line.getNinorigmny());
      vo.setNinorigprice(line.getNinorigprice());
      vo.setNinqtorigprice(line.getNinqtorigprice());
      vo.setNnosubtax(line.getNnosubtax());
      vo.setNnosubtaxrate(line.getNnosubtaxrate());
      vo.setBopptaxflag(line.getBopptaxflag());

      list.add(vo);
    }

    return list.toArray(new STPathDisplayVO[list.size()]);
  }

  private STInvDisplayVO getInvPathHead(SettleListHeaderVO header,
      SettleListItemVO vo) {
    STInvDisplayVO disvo = new STInvDisplayVO();
    disvo.setCbill_bid(vo.getCbill_bid());
    disvo.setCbillid(vo.getCbillid());
    disvo.setCinventoryid(vo.getCinventoryid());
    disvo.setCinventoryvid(vo.getCinventoryvid());
    disvo.setCrowno(vo.getCrowno());
    disvo.setPk_group(vo.getPk_group());
    disvo.setPk_org(vo.getPk_org());
    disvo.setTs(vo.getTs());
    disvo.setVsrccode(vo.getVsrccode());
    disvo.setVsrctype(vo.getVsrctype());
    disvo.setVsrcrowno(vo.getVsrcrowno());
    disvo.setNnumber(vo.getNnumber());
    disvo.setNorigmny(vo.getNorigmny());
    disvo.setNorigprice(vo.getNorigprice());
    disvo.setNqtnum(vo.getNqtnum());
    disvo.setCqtunitid(vo.getCqtunitid());
    disvo.setVqtunitrate(vo.getVqtunitrate());

    disvo.setNorigtaxmny(vo.getNorigtaxmny());
    disvo.setNorigtaxprice(vo.getNorigtaxprice());
    disvo.setNqtorigtaxprice(vo.getNqtorigtaxprice());
    disvo.setNqtorigprice(vo.getNqtorigprice());
    disvo.setNtaxrate(vo.getNtaxrate());
    disvo.setCcurrencyid(header.getCcurrencyid());
    disvo.setCorigcurrencyid(header.getCorigcurrencyid());
    disvo.setCunitid(vo.getCunitid());
    disvo.setFtaxtypeflag(vo.getFtaxtypeflag());
    disvo.setCtaxcodeid(vo.getCtaxcodeid());
    disvo.setNtax(vo.getNtax());
    disvo.setNqtprice(vo.getNqtprice());
    disvo.setNqttaxprice(vo.getNqttaxprice());
    disvo.setNprice(vo.getNprice());
    disvo.setNtaxprice(vo.getNtaxprice());
    disvo.setNmny(vo.getNmny());
    disvo.setNtaxmny(vo.getNtaxmny());
    disvo.setNcaltaxmny(vo.getNcaltaxmny());
    disvo.setCincurrencyid(header.getCincurrencyid());

    disvo.setCintaxcodeid(vo.getCintaxcodeid());
    disvo.setFintaxtypeflag(vo.getFintaxtypeflag());
    disvo.setNintaxrate(vo.getNintaxrate());
    disvo.setNincaltaxmny(vo.getNincaltaxmny());
    disvo.setNinmny(vo.getNinmny());
    disvo.setNinorigmny(vo.getNinorigmny());
    disvo.setNinorigprice(vo.getNinorigprice());
    disvo.setNinprice(vo.getNinprice());
    disvo.setNinqtorigprice(vo.getNinqtorigprice());
    disvo.setNinqtprice(vo.getNinqtprice());
    disvo.setNinqttaxprice(vo.getNinqttaxprice());
    disvo.setNintax(vo.getNintax());
    disvo.setNintaxmny(vo.getNintaxmny());
    disvo.setNintaxprice(vo.getNintaxprice());
    disvo.setNintaxrate(vo.getNintaxrate());
    disvo.setNnosubtax(vo.getNnosubtax());
    disvo.setNnosubtaxrate(vo.getNnosubtaxrate());
    disvo.setBopptaxflag(vo.getBopptaxflag());

    disvo.setBb_ccurrencyid(header.getCorigcurrencyid());

    return disvo;
  }

  private MapList<String, SettleListLineVO> getSettleLineVO(
      SettleListItemVO[] items) {
    List<String> list = new ArrayList<String>();
    for (SettleListItemVO item : items) {
      list.add(item.getCbill_bid());
    }
    MapList<String, SettleListLineVO> maplist =
        new MapList<String, SettleListLineVO>();
    ISettleMaintain service =
        NCLocator.getInstance().lookup(ISettleMaintain.class);
    try {
      maplist =
          service.querySettleListLine(list.toArray(new String[list.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return maplist;
  }

}
