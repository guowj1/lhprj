package nc.vo.to.businessinfo.entity.display;

import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.to.businessinfo.entity.BusinessinfoBBVO;

public class BusinessinfoPathViewHVOMeta extends DataViewMeta {

  public BusinessinfoPathViewHVOMeta() {
    super();
    this.add(BusinessinfoBBVO.class, new String[] {
      BusinessinfoBBVO.CUPFINANCEORGID, BusinessinfoBBVO.CUPFINANCEORGVID,
      BusinessinfoBBVO.CUPCOSTREGIONID, BusinessinfoBBVO.CDOWNFINANCEORGID,
      BusinessinfoBBVO.CDOWNFINANCEORGVID, BusinessinfoBBVO.CDOWNCOSTREGIONID,
      BusinessinfoBBVO.CPATHROWNO, BusinessinfoBBVO.NDISCOUNTRATE,BusinessinfoBBVO.NDISCOUNTVALUE,//gwj
      BusinessinfoBBVO.CORIGCURRENCYID, BusinessinfoBBVO.NEXCHANGERATE,
      BusinessinfoBBVO.NTAXRATE, BusinessinfoBBVO.FTAXTYPEFLAG,
      BusinessinfoBBVO.CINCOUNTRYID, BusinessinfoBBVO.COUTCOUNTRYID,
      BusinessinfoBBVO.CTAXCOUNTRYID, BusinessinfoBBVO.CINTAXCOUNTRYID,
      BusinessinfoBBVO.FBUYSELLFLAG, BusinessinfoBBVO.BTRIATRADEFLAG,
      BusinessinfoBBVO.CCURRENCYID
    });
  }

}
