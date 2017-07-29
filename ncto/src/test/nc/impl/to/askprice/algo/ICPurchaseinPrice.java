package nc.impl.to.askprice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.to.askprice.AskPriceUtils;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.to.askprice.AskPriceCtrlVO;
import nc.vo.to.askprice.AskPriceResultVO;
import nc.vo.to.askprice.AskPriceVO;
import nc.vo.to.askprice.puinprice.PuinItemPriceVO;
import nc.vo.to.enumeration.PriceRule;
import nc.vo.to.enumeration.SettleSceneType;

public class ICPurchaseinPrice implements ITOAskPriceAlgo {
	
	
	@Override
	public void askPrice(Map<AskPriceVO, AskPriceResultVO> mapResult,
			List<AskPriceVO> vos, AskPriceCtrlVO ctrlVO) {
		List<AskPriceVO> newvos = this.getNeedAskOrderPriceVO(vos);
		if (newvos.size() == 0) {
			return;
		}
		// key:Item的主键(cbill_bid)
		Map<String, PuinItemPriceVO> priceMap = this.queryPurchaseInPrice(vos);
		// 设置单价
		this.setPrice(mapResult, vos, priceMap);
	}

	private List<AskPriceVO> getNeedAskOrderPriceVO(List<AskPriceVO> vos) {
		List<AskPriceVO> lstNeedAskOrderPrice = new ArrayList<AskPriceVO>();
		for (AskPriceVO vo : vos) {
			if (SettleSceneType.STOCK_BETWEEN_ORGANIZATION.equalsValue(vo
					.getFsettletype())
					|| null != vo.getOrdertype()
					&& vo.getOrdertype().equals(POBillType.Order.getCode())) {
				lstNeedAskOrderPrice.add(vo);
			}
		}
		return lstNeedAskOrderPrice;
	}
	
	private Map<String,PuinItemPriceVO> queryPurchaseInPrice(List<AskPriceVO> vos) {
		Set<String> bidSet = new HashSet<String>();
	    for (AskPriceVO vo : vos) {
	      // 取入库单明细主键
	      bidSet.add(vo.getInbid());
	    }
	    // 准备传递参数
	    String[] bids = new String[bidSet.size()];
	    bidSet.toArray(bids);
	    
	    // 转换map key:Item主键 PuinItemPriceVO
	    Map<String,PuinItemPriceVO> priceMap =
	            new HashMap<String,PuinItemPriceVO>();
	    try {
	    	//根据入库单子表主键查询单价信息
	    	StringBuilder sql=new StringBuilder("select cgeneralbid,ccurrencyid,cqtunitid,nqtorignetprice,nqtorigtaxnetprice from ic_purchasein_b where ");
	    	sql.append(SqlUtil.buildSqlForIn("cgeneralbid", bids));
	    	DataAccessUtils tool =new DataAccessUtils();
	    	IRowSet rowset =tool.query(sql.toString());
	    	PuinItemPriceVO itemvo=new PuinItemPriceVO();
	    	if(rowset.size()>0){
	    		while(rowset.next()){
	    			itemvo=new PuinItemPriceVO();
	    			itemvo.setCgeneralbid(rowset.getString(0));
		    		itemvo.setCurrencyid(rowset.getString(1));
		    		itemvo.setCqtunitid(rowset.getString(2));
		    		itemvo.setNqtorignetprice(rowset.getUFDouble(3));
		    		itemvo.setNqtorigtaxnetprc(rowset.getUFDouble(4));
		    		priceMap.put(rowset.getString(0), itemvo);
		    	}
	    	}
	    	
	    }catch(Exception err){
	    	// 屏蔽外部接口异常，询价异常认为没询到价。
	    }
	    
	    return priceMap;
	}

	private void setPrice(Map<AskPriceVO, AskPriceResultVO> mapResult,
			List<AskPriceVO> vos, Map<String, PuinItemPriceVO> priceMap) {
		for (AskPriceVO vo : vos) {
			PuinItemPriceVO puinPrice = priceMap.get(vo.getInbid());
			if (null == puinPrice) {
				continue;
			}

			UFDouble price = AskPriceUtils.calcAddPriceRate(vo,
					puinPrice.getNqtorignetprice(), puinPrice.getCurrencyid());
			UFDouble taxprice = AskPriceUtils.calcAddPriceRate(vo,
					puinPrice.getNqtorigtaxnetprc(), puinPrice.getCurrencyid());
			if (price == null || MathTool.isZero(price)) {
				continue;
			}

			AskPriceResultVO resultvo = new AskPriceResultVO();
			resultvo.setAskpricerule(PriceRule.POPRICE.intValue());
			resultvo.setCaskunit(puinPrice.getCqtunitid());
			resultvo.setCorigcurr(puinPrice.getCurrencyid());
			resultvo.setNaskprice(price);
			resultvo.setNasktaxprice(taxprice);
			mapResult.put(vo, resultvo);
		}
	}


}
