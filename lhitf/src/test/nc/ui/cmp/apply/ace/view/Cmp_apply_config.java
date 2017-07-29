package nc.ui.cmp.apply.ace.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class Cmp_apply_config extends AbstractJavaBeanDefinition {
	private Map<String, Object> context = new HashMap();

	public nc.vo.uif2.LoginContext getContext() {
		if (context.get("context") != null)
			return (nc.vo.uif2.LoginContext) context.get("context");
		nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
		context.put("context", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.tmpub.digit.print.DefaultPrintProcessor getPrintProcessor() {
		if (context.get("printProcessor") != null)
			return (nc.ui.tmpub.digit.print.DefaultPrintProcessor) context
					.get("printProcessor");
		nc.ui.tmpub.digit.print.DefaultPrintProcessor bean = new nc.ui.tmpub.digit.print.DefaultPrintProcessor();
		context.put("printProcessor", bean);
		bean.setSrcDestItemCollection(getCardSrcDestCollection());
		bean.setMutiBodyStructure(false);
		bean.setTabMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("applyBVO", "nc.vo.cmp.apply.ApplyBVO");
		return map;
	}

	public nc.ui.tmpub.digit.vo.SrcDestItemCollection getCardSrcDestCollection() {
		if (context.get("cardSrcDestCollection") != null)
			return (nc.ui.tmpub.digit.vo.SrcDestItemCollection) context
					.get("cardSrcDestCollection");
		nc.ui.tmpub.digit.vo.SrcDestItemCollection bean = new nc.ui.tmpub.digit.vo.SrcDestItemCollection();
		context.put("cardSrcDestCollection", bean);
		bean.setSrcDestOrigMap(getManagedMap1());
		bean.init();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1() {
		Map map = new HashMap();
		map.put(getManagedList0(), getManagedList10());
		map.put(getManagedList18(), getManagedList28());
		map.put(getManagedList36(), getManagedList46());
		map.put(getManagedList54(), getManagedList64());
		map.put(getManagedList72(), getManagedList82());
		map.put(getManagedList90(), getManagedList100());
		map.put(getManagedList108(), getManagedList118());
		map.put(getManagedList126(), getManagedList136());
		map.put(getManagedList144(), getManagedList154());
		map.put(getManagedList162(), getManagedList172());
		map.put(getManagedList180(), getManagedList190());
		map.put(getManagedList198(), getManagedList208());
		map.put(getManagedList216(), getManagedList226());
		map.put(getManagedList234(), getManagedList244());
		return map;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add(getManagedList1());
		list.add(getManagedList2());
		list.add(getManagedList3());
		list.add(getManagedList4());
		list.add(getManagedList5());
		list.add(getManagedList6());
		list.add(getManagedList7());
		list.add(getManagedList8());
		list.add(getManagedList9());
		return list;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("HEAD");
		return list;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("HEAD");
		return list;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("applysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getManagedList11());
		list.add(getManagedList12());
		list.add(getManagedList13());
		list.add(getManagedList14());
		list.add(getManagedList15());
		list.add(getManagedList16());
		list.add(getManagedList17());
		return list;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("applysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList16() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList17() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList18() {
		List list = new ArrayList();
		list.add(getManagedList19());
		list.add(getManagedList20());
		list.add(getManagedList21());
		list.add(getManagedList22());
		list.add(getManagedList23());
		list.add(getManagedList24());
		list.add(getManagedList25());
		list.add(getManagedList26());
		list.add(getManagedList27());
		return list;
	}

	private List getManagedList19() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList20() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList21() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList22() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList23() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("HEAD");
		return list;
	}

	private List getManagedList24() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList25() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("HEAD");
		return list;
	}

	private List getManagedList26() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList27() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("unpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList28() {
		List list = new ArrayList();
		list.add(getManagedList29());
		list.add(getManagedList30());
		list.add(getManagedList31());
		list.add(getManagedList32());
		list.add(getManagedList33());
		list.add(getManagedList34());
		list.add(getManagedList35());
		return list;
	}

	private List getManagedList29() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("unpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList30() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcunpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList31() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcunpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList32() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcunpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList33() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList34() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList35() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList36() {
		List list = new ArrayList();
		list.add(getManagedList37());
		list.add(getManagedList38());
		list.add(getManagedList39());
		list.add(getManagedList40());
		list.add(getManagedList41());
		list.add(getManagedList42());
		list.add(getManagedList43());
		list.add(getManagedList44());
		list.add(getManagedList45());
		return list;
	}

	private List getManagedList37() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList38() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList39() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList40() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList41() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("HEAD");
		return list;
	}

	private List getManagedList42() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList43() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("HEAD");
		return list;
	}

	private List getManagedList44() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList45() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("paysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList46() {
		List list = new ArrayList();
		list.add(getManagedList47());
		list.add(getManagedList48());
		list.add(getManagedList49());
		list.add(getManagedList50());
		list.add(getManagedList51());
		list.add(getManagedList52());
		list.add(getManagedList53());
		return list;
	}

	private List getManagedList47() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("paysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList48() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList49() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList50() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList51() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList52() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList53() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList54() {
		List list = new ArrayList();
		list.add(getManagedList55());
		list.add(getManagedList56());
		list.add(getManagedList57());
		list.add(getManagedList58());
		list.add(getManagedList59());
		list.add(getManagedList60());
		list.add(getManagedList61());
		list.add(getManagedList62());
		list.add(getManagedList63());
		return list;
	}

	private List getManagedList55() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList56() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList57() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList58() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList59() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("HEAD");
		return list;
	}

	private List getManagedList60() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList61() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("HEAD");
		return list;
	}

	private List getManagedList62() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList63() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("unplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList64() {
		List list = new ArrayList();
		list.add(getManagedList65());
		list.add(getManagedList66());
		list.add(getManagedList67());
		list.add(getManagedList68());
		list.add(getManagedList69());
		list.add(getManagedList70());
		list.add(getManagedList71());
		return list;
	}

	private List getManagedList65() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("unplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList66() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcunplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList67() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcunplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList68() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcunplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList69() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList70() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList71() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList72() {
		List list = new ArrayList();
		list.add(getManagedList73());
		list.add(getManagedList74());
		list.add(getManagedList75());
		list.add(getManagedList76());
		list.add(getManagedList77());
		list.add(getManagedList78());
		list.add(getManagedList79());
		list.add(getManagedList80());
		list.add(getManagedList81());
		return list;
	}

	private List getManagedList73() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList74() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList75() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList76() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList77() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("HEAD");
		return list;
	}

	private List getManagedList78() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList79() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("HEAD");
		return list;
	}

	private List getManagedList80() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList81() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("plansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList82() {
		List list = new ArrayList();
		list.add(getManagedList83());
		list.add(getManagedList84());
		list.add(getManagedList85());
		list.add(getManagedList86());
		list.add(getManagedList87());
		list.add(getManagedList88());
		list.add(getManagedList89());
		return list;
	}

	private List getManagedList83() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("plansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList84() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList85() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList86() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcplansum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList87() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList88() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList89() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList90() {
		List list = new ArrayList();
		list.add(getManagedList91());
		list.add(getManagedList92());
		list.add(getManagedList93());
		list.add(getManagedList94());
		list.add(getManagedList95());
		list.add(getManagedList96());
		list.add(getManagedList97());
		list.add(getManagedList98());
		list.add(getManagedList99());
		return list;
	}

	private List getManagedList91() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList92() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList93() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList94() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList95() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("HEAD");
		return list;
	}

	private List getManagedList96() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList97() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("HEAD");
		return list;
	}

	private List getManagedList98() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList99() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("actualpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList100() {
		List list = new ArrayList();
		list.add(getManagedList101());
		list.add(getManagedList102());
		list.add(getManagedList103());
		list.add(getManagedList104());
		list.add(getManagedList105());
		list.add(getManagedList106());
		list.add(getManagedList107());
		return list;
	}

	private List getManagedList101() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("actualpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList102() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcactualpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList103() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcactualpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList104() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcactualpaysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList105() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList106() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList107() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList108() {
		List list = new ArrayList();
		list.add(getManagedList109());
		list.add(getManagedList110());
		list.add(getManagedList111());
		list.add(getManagedList112());
		list.add(getManagedList113());
		list.add(getManagedList114());
		list.add(getManagedList115());
		list.add(getManagedList116());
		list.add(getManagedList117());
		return list;
	}

	private List getManagedList109() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList110() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList111() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList112() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList113() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("HEAD");
		return list;
	}

	private List getManagedList114() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("HEAD");
		return list;
	}

	private List getManagedList115() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("HEAD");
		return list;
	}

	private List getManagedList116() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList117() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("origapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList118() {
		List list = new ArrayList();
		list.add(getManagedList119());
		list.add(getManagedList120());
		list.add(getManagedList121());
		list.add(getManagedList122());
		list.add(getManagedList123());
		list.add(getManagedList124());
		list.add(getManagedList125());
		return list;
	}

	private List getManagedList119() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("origapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList120() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcorigapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList121() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcorigapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList122() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcorigapplysum");
		list.add("HEAD");
		return list;
	}

	private List getManagedList123() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList124() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList125() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("HEAD");
		return list;
	}

	private List getManagedList126() {
		List list = new ArrayList();
		list.add(getManagedList127());
		list.add(getManagedList128());
		list.add(getManagedList129());
		list.add(getManagedList130());
		list.add(getManagedList131());
		list.add(getManagedList132());
		list.add(getManagedList133());
		list.add(getManagedList134());
		list.add(getManagedList135());
		return list;
	}

	private List getManagedList127() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList128() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList129() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList130() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList131() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList132() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList133() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList134() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList135() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("applymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList136() {
		List list = new ArrayList();
		list.add(getManagedList137());
		list.add(getManagedList138());
		list.add(getManagedList139());
		list.add(getManagedList140());
		list.add(getManagedList141());
		list.add(getManagedList142());
		list.add(getManagedList143());
		return list;
	}

	private List getManagedList137() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("applymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList138() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList139() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList140() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList141() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList142() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList143() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList144() {
		List list = new ArrayList();
		list.add(getManagedList145());
		list.add(getManagedList146());
		list.add(getManagedList147());
		list.add(getManagedList148());
		list.add(getManagedList149());
		list.add(getManagedList150());
		list.add(getManagedList151());
		list.add(getManagedList152());
		list.add(getManagedList153());
		return list;
	}

	private List getManagedList145() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList146() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList147() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList148() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList149() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList150() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList151() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList152() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList153() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("unpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList154() {
		List list = new ArrayList();
		list.add(getManagedList155());
		list.add(getManagedList156());
		list.add(getManagedList157());
		list.add(getManagedList158());
		list.add(getManagedList159());
		list.add(getManagedList160());
		list.add(getManagedList161());
		return list;
	}

	private List getManagedList155() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("unpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList156() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcunpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList157() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcunpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList158() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcunpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList159() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList160() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList161() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList162() {
		List list = new ArrayList();
		list.add(getManagedList163());
		list.add(getManagedList164());
		list.add(getManagedList165());
		list.add(getManagedList166());
		list.add(getManagedList167());
		list.add(getManagedList168());
		list.add(getManagedList169());
		list.add(getManagedList170());
		list.add(getManagedList171());
		return list;
	}

	private List getManagedList163() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList164() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList165() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList166() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList167() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList168() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList169() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList170() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList171() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("paymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList172() {
		List list = new ArrayList();
		list.add(getManagedList173());
		list.add(getManagedList174());
		list.add(getManagedList175());
		list.add(getManagedList176());
		list.add(getManagedList177());
		list.add(getManagedList178());
		list.add(getManagedList179());
		return list;
	}

	private List getManagedList173() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("paymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList174() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList175() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList176() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList177() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList178() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList179() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList180() {
		List list = new ArrayList();
		list.add(getManagedList181());
		list.add(getManagedList182());
		list.add(getManagedList183());
		list.add(getManagedList184());
		list.add(getManagedList185());
		list.add(getManagedList186());
		list.add(getManagedList187());
		list.add(getManagedList188());
		list.add(getManagedList189());
		return list;
	}

	private List getManagedList181() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList182() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList183() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList184() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList185() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList186() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList187() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList188() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList189() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("unplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList190() {
		List list = new ArrayList();
		list.add(getManagedList191());
		list.add(getManagedList192());
		list.add(getManagedList193());
		list.add(getManagedList194());
		list.add(getManagedList195());
		list.add(getManagedList196());
		list.add(getManagedList197());
		return list;
	}

	private List getManagedList191() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("unplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList192() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcunplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList193() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcunplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList194() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcunplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList195() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList196() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList197() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList198() {
		List list = new ArrayList();
		list.add(getManagedList199());
		list.add(getManagedList200());
		list.add(getManagedList201());
		list.add(getManagedList202());
		list.add(getManagedList203());
		list.add(getManagedList204());
		list.add(getManagedList205());
		list.add(getManagedList206());
		list.add(getManagedList207());
		return list;
	}

	private List getManagedList199() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList200() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList201() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList202() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList203() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList204() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList205() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList206() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList207() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("planmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList208() {
		List list = new ArrayList();
		list.add(getManagedList209());
		list.add(getManagedList210());
		list.add(getManagedList211());
		list.add(getManagedList212());
		list.add(getManagedList213());
		list.add(getManagedList214());
		list.add(getManagedList215());
		return list;
	}

	private List getManagedList209() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("planmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList210() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList211() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList212() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcplanmny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList213() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList214() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList215() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList216() {
		List list = new ArrayList();
		list.add(getManagedList217());
		list.add(getManagedList218());
		list.add(getManagedList219());
		list.add(getManagedList220());
		list.add(getManagedList221());
		list.add(getManagedList222());
		list.add(getManagedList223());
		list.add(getManagedList224());
		list.add(getManagedList225());
		return list;
	}

	private List getManagedList217() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList218() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList219() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList220() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList221() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList222() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList223() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList224() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList225() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("actualpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList226() {
		List list = new ArrayList();
		list.add(getManagedList227());
		list.add(getManagedList228());
		list.add(getManagedList229());
		list.add(getManagedList230());
		list.add(getManagedList231());
		list.add(getManagedList232());
		list.add(getManagedList233());
		return list;
	}

	private List getManagedList227() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("actualpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList228() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcactualpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList229() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcactualpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList230() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcactualpaymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList231() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList232() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList233() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList234() {
		List list = new ArrayList();
		list.add(getManagedList235());
		list.add(getManagedList236());
		list.add(getManagedList237());
		list.add(getManagedList238());
		list.add(getManagedList239());
		list.add(getManagedList240());
		list.add(getManagedList241());
		list.add(getManagedList242());
		list.add(getManagedList243());
		return list;
	}

	private List getManagedList235() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList236() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList237() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList238() {
		List list = new ArrayList();
		list.add("ORG");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList239() {
		List list = new ArrayList();
		list.add("GROUP");
		list.add("pk_group");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList240() {
		List list = new ArrayList();
		list.add("GLOBAL");
		list.add("pk_org");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList241() {
		List list = new ArrayList();
		list.add("CURR");
		list.add("pk_currtype");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList242() {
		List list = new ArrayList();
		list.add("EXCHANGEDATE");
		list.add("applydate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList243() {
		List list = new ArrayList();
		list.add("MONEY");
		list.add("origapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList244() {
		List list = new ArrayList();
		list.add(getManagedList245());
		list.add(getManagedList246());
		list.add(getManagedList247());
		list.add(getManagedList248());
		list.add(getManagedList249());
		list.add(getManagedList250());
		list.add(getManagedList251());
		return list;
	}

	private List getManagedList245() {
		List list = new ArrayList();
		list.add("CURR_MONEY");
		list.add("origapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList246() {
		List list = new ArrayList();
		list.add("ORG_MONEY");
		list.add("olcorigapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList247() {
		List list = new ArrayList();
		list.add("GROUP_MONEY");
		list.add("glcorigapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList248() {
		List list = new ArrayList();
		list.add("GLOBAL_MONEY");
		list.add("gllcorigapplymny");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList249() {
		List list = new ArrayList();
		list.add("ORG_RATE");
		list.add("olcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList250() {
		List list = new ArrayList();
		list.add("GROUP_RATE");
		list.add("glcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	private List getManagedList251() {
		List list = new ArrayList();
		list.add("GLOBAL_RATE");
		list.add("gllcrate");
		list.add("BODY");
		list.add("pk_apply_b");
		return list;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getDigitMediator() {
		if (context.get("digitMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
					.get("digitMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("digitMediator", bean);
		bean.setHandlerMap(getManagedMap2());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap2() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.event.list.ListPanelLoadEvent",
				getManagedList252());
		map.put("nc.ui.pubapp.uif2app.event.card.CardPanelLoadEvent",
				getManagedList253());
		return map;
	}

	private List getManagedList252() {
		List list = new ArrayList();
		list.add(getListPanelLoadDigitListener_405bec());
		return list;
	}

	private nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener getListPanelLoadDigitListener_405bec() {
		if (context
				.get("nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener#405bec") != null)
			return (nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener) context
					.get("nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener#405bec");
		nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener bean = new nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener();
		context.put(
				"nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener#405bec",
				bean);
		bean.setSrcDestItemCollection(getCardSrcDestCollection());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList253() {
		List list = new ArrayList();
		list.add(getCardPanelLoadDigitListener_1530c1d());
		return list;
	}

	private nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener getCardPanelLoadDigitListener_1530c1d() {
		if (context
				.get("nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener#1530c1d") != null)
			return (nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener) context
					.get("nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener#1530c1d");
		nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener bean = new nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener();
		context.put(
				"nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener#1530c1d",
				bean);
		bean.setSrcDestItemCollection(getCardSrcDestCollection());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.EditHandleMediator getCardEditEventDigitMediator() {
		if (context.get("cardEditEventDigitMediator") != null)
			return (nc.ui.pubapp.uif2app.view.EditHandleMediator) context
					.get("cardEditEventDigitMediator");
		nc.ui.pubapp.uif2app.view.EditHandleMediator bean = new nc.ui.pubapp.uif2app.view.EditHandleMediator(
				getBillFormEditor());
		context.put("cardEditEventDigitMediator", bean);
		bean.setDispatcher(getMany2ManyDispatcher_1148393());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher getMany2ManyDispatcher_1148393() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher#1148393") != null)
			return (nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher) context
					.get("nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher#1148393");
		nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher bean = new nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher();
		context.put(
				"nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher#1148393",
				bean);
		bean.setMany2one(getManagedMap3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap3() {
		Map map = new HashMap();
		map.put(getManagedList254(), getCardEditListener());
		return map;
	}

	private List getManagedList254() {
		List list = new ArrayList();
		list.add("pk_org");
		list.add("applydate");
		list.add("pk_group");
		list.add("pk_currtype");
		list.add("olcrate");
		list.add("glcrate");
		list.add("gllcrate");
		list.add("applymny");
		list.add("paymny");
		list.add("unpaymny");
		list.add("planmny");
		list.add("unplanmny");
		list.add("actualpaymny");
		list.add("origapplymny");
		list.add("applysum");
		list.add("paysum");
		list.add("unpaysum");
		list.add("plansum");
		list.add("unplansum");
		list.add("actualpaysum");
		list.add("origapplysum");
		return list;
	}

	public nc.ui.tmpub.digit.listener.card.CardPanelEditExListener getCardEditListener() {
		if (context.get("cardEditListener") != null)
			return (nc.ui.tmpub.digit.listener.card.CardPanelEditExListener) context
					.get("cardEditListener");
		nc.ui.tmpub.digit.listener.card.CardPanelEditExListener bean = new nc.ui.tmpub.digit.listener.card.CardPanelEditExListener();
		context.put("cardEditListener", bean);
		bean.setSrcDestItemCollection(getCardSrcDestCollection());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getCard_printMenuAction() {
		if (context.get("card_printMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("card_printMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("card_printMenuAction", bean);
		bean.setCode("printgroup");
		bean.setActions(getManagedList255());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList255() {
		List list = new ArrayList();
		list.add(getList_templetprint_action());
		list.add(getList_preview_action());
		list.add(getList_output_action());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getList_templetprint_action() {
		if (context.get("list_templetprint_action") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("list_templetprint_action");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("list_templetprint_action", bean);
		bean.setPreview(false);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("ot");
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getList_preview_action() {
		if (context.get("list_preview_action") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("list_preview_action");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("list_preview_action", bean);
		bean.setPreview(true);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("ot");
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.OutputAction getList_output_action() {
		if (context.get("list_output_action") != null)
			return (nc.ui.pubapp.uif2app.actions.OutputAction) context
					.get("list_output_action");
		nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
		context.put("list_output_action", bean);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getList_printMenuAction() {
		if (context.get("list_printMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("list_printMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("list_printMenuAction", bean);
		bean.setCode("printgroup");
		bean.setActions(getManagedList256());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList256() {
		List list = new ArrayList();
		list.add(getList_templetprint_action());
		list.add(getList_preview_action());
		list.add(getList_output_action());
		list.add(getSeparatorAction());
		list.add(getPrintPreviewBillCardAction());
		return list;
	}

	public nc.ui.tmpub.action.listprint.DefaultTemplatePagePrintFactory getPrintFactory() {
		if (context.get("printFactory") != null)
			return (nc.ui.tmpub.action.listprint.DefaultTemplatePagePrintFactory) context
					.get("printFactory");
		nc.ui.tmpub.action.listprint.DefaultTemplatePagePrintFactory bean = new nc.ui.tmpub.action.listprint.DefaultTemplatePagePrintFactory();
		context.put("printFactory", bean);
		bean.setMdId("5122f559-f635-4628-8654-b2d4c06dd0be");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.tmpub.action.listprint.TemplatePaginationPrintAction getPrintBillCardAction() {
		if (context.get("printBillCardAction") != null)
			return (nc.ui.tmpub.action.listprint.TemplatePaginationPrintAction) context
					.get("printBillCardAction");
		nc.ui.tmpub.action.listprint.TemplatePaginationPrintAction bean = new nc.ui.tmpub.action.listprint.TemplatePaginationPrintAction();
		context.put("printBillCardAction", bean);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("applylist");
		bean.setPrintFactory(getPrintFactory());
		bean.setPaginationModel(getPaginationModel());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		bean.setCode("billprint");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.tmpub.action.listprint.TemplatePaginationPreviewAction getPrintPreviewBillCardAction() {
		if (context.get("printPreviewBillCardAction") != null)
			return (nc.ui.tmpub.action.listprint.TemplatePaginationPreviewAction) context
					.get("printPreviewBillCardAction");
		nc.ui.tmpub.action.listprint.TemplatePaginationPreviewAction bean = new nc.ui.tmpub.action.listprint.TemplatePaginationPreviewAction();
		context.put("printPreviewBillCardAction", bean);
		bean.setPrintAction(getPrintBillCardAction());
		bean.setBtnName(getI18nFB_11b37e4());
		bean.setCode("billprint");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_11b37e4() {
		if (context.get("nc.ui.uif2.I18nFB#11b37e4") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#11b37e4");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#11b37e4", bean);
		bean.setResDir("3607apply_add_0");
		bean.setDefaultValue("billprint");
		bean.setResId("03607apply_add-0160");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#11b37e4", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.cmp.apply.ace.serviceproxy.AceCmp_applyMaintainProxy getMaintainProxy() {
		if (context.get("maintainProxy") != null)
			return (nc.ui.cmp.apply.ace.serviceproxy.AceCmp_applyMaintainProxy) context
					.get("maintainProxy");
		nc.ui.cmp.apply.ace.serviceproxy.AceCmp_applyMaintainProxy bean = new nc.ui.cmp.apply.ace.serviceproxy.AceCmp_applyMaintainProxy();
		context.put("maintainProxy", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory() {
		if (context.get("boadatorfactory") != null)
			return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory) context
					.get("boadatorfactory");
		nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
		context.put("boadatorfactory", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.BillManageModel getManageAppModel() {
		if (context.get("manageAppModel") != null)
			return (nc.ui.pubapp.uif2app.model.BillManageModel) context
					.get("manageAppModel");
		nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
		context.put("manageAppModel", bean);
		bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.pagination.PaginationModelDataManager getModelDataManager() {
		if (context.get("modelDataManager") != null)
			return (nc.ui.pubapp.uif2app.model.pagination.PaginationModelDataManager) context
					.get("modelDataManager");
		nc.ui.pubapp.uif2app.model.pagination.PaginationModelDataManager bean = new nc.ui.pubapp.uif2app.model.pagination.PaginationModelDataManager();
		context.put("modelDataManager", bean);
		bean.setModel(getManageAppModel());
		bean.setPaginationModel(getPaginationModel());
		bean.setPageQueryService(getPageQueryService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.pagination.PubPaginationModel getPaginationModel() {
		if (context.get("paginationModel") != null)
			return (nc.ui.pubapp.uif2app.model.pagination.PubPaginationModel) context
					.get("paginationModel");
		nc.ui.pubapp.uif2app.model.pagination.PubPaginationModel bean = new nc.ui.pubapp.uif2app.model.pagination.PubPaginationModel();
		context.put("paginationModel", bean);
		bean.setPaginationQueryService(getMaintainProxy());
		bean.init();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.pagination.UIPageQueryService getPageQueryService() {
		if (context.get("pageQueryService") != null)
			return (nc.ui.pubapp.uif2app.model.pagination.UIPageQueryService) context
					.get("pageQueryService");
		nc.ui.pubapp.uif2app.model.pagination.UIPageQueryService bean = new nc.ui.pubapp.uif2app.model.pagination.UIPageQueryService();
		context.put("pageQueryService", bean);
		bean.setAllPagePkQueryServiceMethod("nc.itf.cmp.IApplyService.queryPKs");
		bean.setDataOfPksQueryServiceMethod("nc.itf.cmp.IApplyService.queryBillByPK");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.components.pagination.PaginationBar getPaginationBar() {
		if (context.get("paginationBar") != null)
			return (nc.ui.uif2.components.pagination.PaginationBar) context
					.get("paginationBar");
		nc.ui.uif2.components.pagination.PaginationBar bean = new nc.ui.uif2.components.pagination.PaginationBar();
		context.put("paginationBar", bean);
		bean.setPaginationModel(getPaginationModel());
		bean.setContext(getContext());
		bean.registeCallbak();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.view.CMPApplyTemplateContainer getTemplateContainer() {
		if (context.get("templateContainer") != null)
			return (nc.ui.cmp.apply.view.CMPApplyTemplateContainer) context
					.get("templateContainer");
		nc.ui.cmp.apply.view.CMPApplyTemplateContainer bean = new nc.ui.cmp.apply.view.CMPApplyTemplateContainer();
		context.put("templateContainer", bean);
		bean.setContext(getContext());
		bean.setNodeKeies(getManagedList257());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList257() {
		List list = new ArrayList();
		list.add("36D1");
		return list;
	}

	public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer() {
		if (context.get("queryTemplateContainer") != null)
			return (nc.ui.uif2.editor.QueryTemplateContainer) context
					.get("queryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("queryTemplateContainer", bean);
		bean.setContext(getContext());
		bean.setNodeKey("qt");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getListView() {
		if (context.get("listView") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillListView) context
					.get("listView");
		nc.ui.pubapp.uif2app.view.ShowUpableBillListView bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillListView();
		context.put("listView", bean);
		bean.setNodekey("36D1");
		bean.setPaginationBar(getPaginationBar());
		bean.setModel(getManageAppModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setShowTotalLine(true);
		bean.setShowTotalLineTabcodes(getManagedList258());
		bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_1f26fd8());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList258() {
		List list = new ArrayList();
		list.add("pk_apply_b");
		return list;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_1f26fd8() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#1f26fd8") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#1f26fd8");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put(
				"nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#1f26fd8",
				bean);
		bean.setBillListDataPrepares(getManagedList259());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList259() {
		List list = new ArrayList();
		list.add(getUserdefitemlistPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator() {
		if (context.get("userdefitemlistPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerListPreparator) context
					.get("userdefitemlistPreparator");
		nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
		context.put("userdefitemlistPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList260());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList260() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_18cd1fb());
		list.add(getUserdefQueryParam_2dd54e());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_18cd1fb() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#18cd1fb") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#18cd1fb");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#18cd1fb", bean);
		bean.setMdfullname("cmp.cmp_apply");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_2dd54e() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#2dd54e") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#2dd54e");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#2dd54e", bean);
		bean.setMdfullname("cmp.cmp_apply_b");
		bean.setPos(1);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.BillOrgPanel getOrgPanel() {
		if (context.get("orgPanel") != null)
			return (nc.ui.pubapp.uif2app.view.BillOrgPanel) context
					.get("orgPanel");
		nc.ui.pubapp.uif2app.view.BillOrgPanel bean = new nc.ui.pubapp.uif2app.view.BillOrgPanel();
		context.put("orgPanel", bean);
		bean.setModel(getManageAppModel());
		bean.setLabelName(getI18nFB_10f2951());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_10f2951() {
		if (context.get("nc.ui.uif2.I18nFB#10f2951") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#10f2951");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#10f2951", bean);
		bean.setResDir("3607apply_add_0");
		bean.setDefaultValue("pk_org");
		bean.setResId("03607apply_add-0165");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#10f2951", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.cmp.apply.view.ApplyCardView getBillFormEditor() {
		if (context.get("billFormEditor") != null)
			return (nc.ui.cmp.apply.view.ApplyCardView) context
					.get("billFormEditor");
		nc.ui.cmp.apply.view.ApplyCardView bean = new nc.ui.cmp.apply.view.ApplyCardView();
		context.put("billFormEditor", bean);
		bean.setModel(getManageAppModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setBillOrgPanel(getOrgPanel());
		bean.setNodekey("36D1");
		bean.setDefValueItf(getBillDefVauleItf());
		bean.setShowOrgPanel(true);
		bean.setClosingListener(getClosingListener());
		bean.setDefaultRefWherePartHandler(getDefaultRefWherePartHandler());
		bean.setAutoAddLine(false);
		bean.setShowTotalLine(true);
		bean.setBodyLineActions(getManagedList261());
		bean.setUserdefitemPreparator(getCompositeBillDataPrepare_1f6de35());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList261() {
		List list = new ArrayList();
		list.add(getApplyBodyAddLineAction_6191b1());
		list.add(getApplyBodyInsertLineAction_1a4154e());
		list.add(getApplyBodyDelLineAction_172c629());
		list.add(getApplyBodyCopyLineAction_2fe3d4());
		list.add(getApplyBodyPasteLineAction_16abd0());
		list.add(getApplyBodyPasteToTailAction_69e9dc());
		list.add(getBodyLineEditAction_17896d6());
		return list;
	}

	private nc.ui.cmp.apply.action.ApplyBodyAddLineAction getApplyBodyAddLineAction_6191b1() {
		if (context.get("nc.ui.cmp.apply.action.ApplyBodyAddLineAction#6191b1") != null)
			return (nc.ui.cmp.apply.action.ApplyBodyAddLineAction) context
					.get("nc.ui.cmp.apply.action.ApplyBodyAddLineAction#6191b1");
		nc.ui.cmp.apply.action.ApplyBodyAddLineAction bean = new nc.ui.cmp.apply.action.ApplyBodyAddLineAction();
		context.put("nc.ui.cmp.apply.action.ApplyBodyAddLineAction#6191b1",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.action.ApplyBodyInsertLineAction getApplyBodyInsertLineAction_1a4154e() {
		if (context
				.get("nc.ui.cmp.apply.action.ApplyBodyInsertLineAction#1a4154e") != null)
			return (nc.ui.cmp.apply.action.ApplyBodyInsertLineAction) context
					.get("nc.ui.cmp.apply.action.ApplyBodyInsertLineAction#1a4154e");
		nc.ui.cmp.apply.action.ApplyBodyInsertLineAction bean = new nc.ui.cmp.apply.action.ApplyBodyInsertLineAction();
		context.put("nc.ui.cmp.apply.action.ApplyBodyInsertLineAction#1a4154e",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.action.ApplyBodyDelLineAction getApplyBodyDelLineAction_172c629() {
		if (context
				.get("nc.ui.cmp.apply.action.ApplyBodyDelLineAction#172c629") != null)
			return (nc.ui.cmp.apply.action.ApplyBodyDelLineAction) context
					.get("nc.ui.cmp.apply.action.ApplyBodyDelLineAction#172c629");
		nc.ui.cmp.apply.action.ApplyBodyDelLineAction bean = new nc.ui.cmp.apply.action.ApplyBodyDelLineAction();
		context.put("nc.ui.cmp.apply.action.ApplyBodyDelLineAction#172c629",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.action.ApplyBodyCopyLineAction getApplyBodyCopyLineAction_2fe3d4() {
		if (context
				.get("nc.ui.cmp.apply.action.ApplyBodyCopyLineAction#2fe3d4") != null)
			return (nc.ui.cmp.apply.action.ApplyBodyCopyLineAction) context
					.get("nc.ui.cmp.apply.action.ApplyBodyCopyLineAction#2fe3d4");
		nc.ui.cmp.apply.action.ApplyBodyCopyLineAction bean = new nc.ui.cmp.apply.action.ApplyBodyCopyLineAction();
		context.put("nc.ui.cmp.apply.action.ApplyBodyCopyLineAction#2fe3d4",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.action.ApplyBodyPasteLineAction getApplyBodyPasteLineAction_16abd0() {
		if (context
				.get("nc.ui.cmp.apply.action.ApplyBodyPasteLineAction#16abd0") != null)
			return (nc.ui.cmp.apply.action.ApplyBodyPasteLineAction) context
					.get("nc.ui.cmp.apply.action.ApplyBodyPasteLineAction#16abd0");
		nc.ui.cmp.apply.action.ApplyBodyPasteLineAction bean = new nc.ui.cmp.apply.action.ApplyBodyPasteLineAction();
		context.put("nc.ui.cmp.apply.action.ApplyBodyPasteLineAction#16abd0",
				bean);
		bean.setClearItems(getManagedList262());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList262() {
		List list = new ArrayList();
		list.add("pk_apply_b");
		list.add("ts");
		return list;
	}

	private nc.ui.cmp.apply.action.ApplyBodyPasteToTailAction getApplyBodyPasteToTailAction_69e9dc() {
		if (context
				.get("nc.ui.cmp.apply.action.ApplyBodyPasteToTailAction#69e9dc") != null)
			return (nc.ui.cmp.apply.action.ApplyBodyPasteToTailAction) context
					.get("nc.ui.cmp.apply.action.ApplyBodyPasteToTailAction#69e9dc");
		nc.ui.cmp.apply.action.ApplyBodyPasteToTailAction bean = new nc.ui.cmp.apply.action.ApplyBodyPasteToTailAction();
		context.put("nc.ui.cmp.apply.action.ApplyBodyPasteToTailAction#69e9dc",
				bean);
		bean.setClearItems(getManagedList263());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList263() {
		List list = new ArrayList();
		list.add("pk_apply_b");
		list.add("ts");
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_17896d6() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#17896d6") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#17896d6");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#17896d6",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_1f6de35() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#1f6de35") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#1f6de35");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put(
				"nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#1f6de35",
				bean);
		bean.setBillDataPrepares(getManagedList264());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList264() {
		List list = new ArrayList();
		list.add(getUserdefitemPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.cmp.apply.view.ApplyBillDefValue getBillDefVauleItf() {
		if (context.get("billDefVauleItf") != null)
			return (nc.ui.cmp.apply.view.ApplyBillDefValue) context
					.get("billDefVauleItf");
		nc.ui.cmp.apply.view.ApplyBillDefValue bean = new nc.ui.cmp.apply.view.ApplyBillDefValue();
		context.put("billDefVauleItf", bean);
		bean.setEditor(getBillFormEditor());
		bean.setCmpBillNodeKeyChangeListener(getCmpBillNodeKeyChangeListener());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
		if (context.get("userdefitemPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerPreparator) context
					.get("userdefitemPreparator");
		nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
		context.put("userdefitemPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList265());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList265() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_767afe());
		list.add(getUserdefQueryParam_84e60a());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_767afe() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#767afe") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#767afe");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#767afe", bean);
		bean.setMdfullname("cmp.cmp_apply");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_84e60a() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#84e60a") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#84e60a");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#84e60a", bean);
		bean.setMdfullname("cmp.cmp_apply_b");
		bean.setPos(1);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator() {
		if (context.get("marAsstPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator) context
					.get("marAsstPreparator");
		nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
		context.put("marAsstPreparator", bean);
		bean.setModel(getManageAppModel());
		bean.setContainer(getUserdefitemContainer());
		bean.setPrefix("vfree");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
		if (context.get("userdefitemContainer") != null)
			return (nc.ui.uif2.userdefitem.UserDefItemContainer) context
					.get("userdefitemContainer");
		nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
		context.put("userdefitemContainer", bean);
		bean.setContext(getContext());
		bean.setParams(getManagedList266());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList266() {
		List list = new ArrayList();
		list.add(getQueryParam_11ada63());
		list.add(getQueryParam_1f7d7b2());
		list.add(getQueryParam_721128());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_11ada63() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#11ada63") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#11ada63");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#11ada63", bean);
		bean.setMdfullname("cmp.cmp_apply");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1f7d7b2() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1f7d7b2") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#1f7d7b2");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1f7d7b2", bean);
		bean.setMdfullname("cmp.cmp_apply_b");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_721128() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#721128") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#721128");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#721128", bean);
		bean.setRulecode("materialassistant");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
		if (context.get("appEventHandlerMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
					.get("appEventHandlerMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("appEventHandlerMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setHandlerMap(getManagedMap4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap4() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent",
				getManagedList267());
		return map;
	}

	private List getManagedList267() {
		List list = new ArrayList();
		list.add(getCardBodyRowChangedHandler());
		return list;
	}

	private nc.ui.cmp.apply.handler.CardBodyRowChangedHandler getCardBodyRowChangedHandler() {
		if (context.get("CardBodyRowChangedHandler") != null)
			return (nc.ui.cmp.apply.handler.CardBodyRowChangedHandler) context
					.get("CardBodyRowChangedHandler");
		nc.ui.cmp.apply.handler.CardBodyRowChangedHandler bean = new nc.ui.cmp.apply.handler.CardBodyRowChangedHandler();
		context.put("CardBodyRowChangedHandler", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea() {
		if (context.get("queryArea") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell) context
					.get("queryArea");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("queryArea", bean);
		bean.setQueryAreaCreator(getQueryAction());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo() {
		if (context.get("queryInfo") != null)
			return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel) context
					.get("queryInfo");
		nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
		context.put("queryInfo", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl() {
		if (context.get("cardInfoPnl") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) context
					.get("cardInfoPnl");
		nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
		context.put("cardInfoPnl", bean);
		bean.setTitleAction(getReturnaction());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction() {
		if (context.get("returnaction") != null)
			return (nc.ui.pubapp.uif2app.actions.UEReturnAction) context
					.get("returnaction");
		nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
		context.put("returnaction", bean);
		bean.setGoComponent(getListView());
		bean.setSaveAction(getSaveAction());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setModel(getManageAppModel());
		bean.setTangramLayoutRoot(getTBNode_a3b00d());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_a3b00d() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#a3b00d") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context
					.get("nc.ui.uif2.tangramlayout.node.TBNode#a3b00d");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#a3b00d", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList268());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList268() {
		List list = new ArrayList();
		list.add(getHSNode_117e774());
		list.add(getVSNode_1f51a93());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_117e774() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#117e774") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#117e774");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#117e774", bean);
		bean.setLeft(getCNode_c48da3());
		bean.setRight(getVSNode_16cb1f7());
		bean.setDividerLocation(210f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_c48da3() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#c48da3") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#c48da3");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#c48da3", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_16cb1f7() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#16cb1f7") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#16cb1f7");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#16cb1f7", bean);
		bean.setUp(getCNode_2eeeae());
		bean.setDown(getCNode_1c9475f());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_2eeeae() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#2eeeae") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#2eeeae");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#2eeeae", bean);
		bean.setComponent(getQueryInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1c9475f() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1c9475f") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1c9475f");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1c9475f", bean);
		bean.setName(getI18nFB_137f932());
		bean.setComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_137f932() {
		if (context.get("nc.ui.uif2.I18nFB#137f932") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#137f932");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#137f932", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("list");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#137f932", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1f51a93() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#1f51a93") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#1f51a93");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#1f51a93", bean);
		bean.setUp(getCNode_5c7556());
		bean.setDown(getCNode_1901b5f());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_5c7556() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#5c7556") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#5c7556");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#5c7556", bean);
		bean.setComponent(getCardInfoPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1901b5f() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1901b5f") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1901b5f");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1901b5f", bean);
		bean.setName(getI18nFB_1b6149());
		bean.setComponent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1b6149() {
		if (context.get("nc.ui.uif2.I18nFB#1b6149") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1b6149");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1b6149", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("card");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1b6149", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.uif2app.event.ChildrenPicky getChildrenPicky() {
		if (context.get("childrenPicky") != null)
			return (nc.ui.pubapp.uif2app.event.ChildrenPicky) context
					.get("childrenPicky");
		nc.ui.pubapp.uif2app.event.ChildrenPicky bean = new nc.ui.pubapp.uif2app.event.ChildrenPicky();
		context.put("childrenPicky", bean);
		bean.setBillform(getBillFormEditor());
		bean.setBodyVoClasses(getManagedList269());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList269() {
		List list = new ArrayList();
		list.add("nc.vo.cmp.apply.ApplyBVO");
		return list;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getEventMediator() {
		if (context.get("eventMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
					.get("eventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("eventMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setHandlerMap(getManagedMap5());
		bean.setHandlerGroup(getManagedList271());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap5() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.mediator.mutiltrans.NodekeyEvent",
				getManagedList270());
		return map;
	}

	private List getManagedList270() {
		List list = new ArrayList();
		list.add(getCmpBillNodeKeyChangeListener());
		list.add(getAddAction());
		return list;
	}

	private List getManagedList271() {
		List list = new ArrayList();
		list.add(getEventHandlerGroup_3f25ce());
		list.add(getEventHandlerGroup_9359a());
		list.add(getEventHandlerGroup_1130e6d());
		list.add(getEventHandlerGroup_b23cb());
		list.add(getEventHandlerGroup_4b5a38());
		list.add(getEventHandlerGroup_e8fe80());
		list.add(getEventHandlerGroup_9756ec());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_3f25ce() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3f25ce") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3f25ce");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3f25ce", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setPicky(getChildrenPicky());
		bean.setHandler(getAceBodyBeforeEditHandler_7b51eb());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.ace.handler.AceBodyBeforeEditHandler getAceBodyBeforeEditHandler_7b51eb() {
		if (context
				.get("nc.ui.cmp.apply.ace.handler.AceBodyBeforeEditHandler#7b51eb") != null)
			return (nc.ui.cmp.apply.ace.handler.AceBodyBeforeEditHandler) context
					.get("nc.ui.cmp.apply.ace.handler.AceBodyBeforeEditHandler#7b51eb");
		nc.ui.cmp.apply.ace.handler.AceBodyBeforeEditHandler bean = new nc.ui.cmp.apply.ace.handler.AceBodyBeforeEditHandler();
		context.put(
				"nc.ui.cmp.apply.ace.handler.AceBodyBeforeEditHandler#7b51eb",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_9359a() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9359a") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9359a");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9359a", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setPicky(getChildrenPicky());
		bean.setHandler(getAceBodyAfterEditHandler_1cb0e13());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.ace.handler.AceBodyAfterEditHandler getAceBodyAfterEditHandler_1cb0e13() {
		if (context
				.get("nc.ui.cmp.apply.ace.handler.AceBodyAfterEditHandler#1cb0e13") != null)
			return (nc.ui.cmp.apply.ace.handler.AceBodyAfterEditHandler) context
					.get("nc.ui.cmp.apply.ace.handler.AceBodyAfterEditHandler#1cb0e13");
		nc.ui.cmp.apply.ace.handler.AceBodyAfterEditHandler bean = new nc.ui.cmp.apply.ace.handler.AceBodyAfterEditHandler();
		context.put(
				"nc.ui.cmp.apply.ace.handler.AceBodyAfterEditHandler#1cb0e13",
				bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1130e6d() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1130e6d") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1130e6d");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1130e6d",
				bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
		bean.setHandler(getAceHeadTailBeforeEditHandler_9e4e00());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.ace.handler.AceHeadTailBeforeEditHandler getAceHeadTailBeforeEditHandler_9e4e00() {
		if (context
				.get("nc.ui.cmp.apply.ace.handler.AceHeadTailBeforeEditHandler#9e4e00") != null)
			return (nc.ui.cmp.apply.ace.handler.AceHeadTailBeforeEditHandler) context
					.get("nc.ui.cmp.apply.ace.handler.AceHeadTailBeforeEditHandler#9e4e00");
		nc.ui.cmp.apply.ace.handler.AceHeadTailBeforeEditHandler bean = new nc.ui.cmp.apply.ace.handler.AceHeadTailBeforeEditHandler();
		context.put(
				"nc.ui.cmp.apply.ace.handler.AceHeadTailBeforeEditHandler#9e4e00",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_b23cb() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#b23cb") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#b23cb");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#b23cb", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
		bean.setHandler(getAceHeadTailAfterEditHandler_18304d());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.ace.handler.AceHeadTailAfterEditHandler getAceHeadTailAfterEditHandler_18304d() {
		if (context
				.get("nc.ui.cmp.apply.ace.handler.AceHeadTailAfterEditHandler#18304d") != null)
			return (nc.ui.cmp.apply.ace.handler.AceHeadTailAfterEditHandler) context
					.get("nc.ui.cmp.apply.ace.handler.AceHeadTailAfterEditHandler#18304d");
		nc.ui.cmp.apply.ace.handler.AceHeadTailAfterEditHandler bean = new nc.ui.cmp.apply.ace.handler.AceHeadTailAfterEditHandler();
		context.put(
				"nc.ui.cmp.apply.ace.handler.AceHeadTailAfterEditHandler#18304d",
				bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_4b5a38() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#4b5a38") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#4b5a38");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#4b5a38", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
		bean.setHandler(getAceAddHandler_15b0b6d());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.ace.handler.AceAddHandler getAceAddHandler_15b0b6d() {
		if (context.get("nc.ui.cmp.apply.ace.handler.AceAddHandler#15b0b6d") != null)
			return (nc.ui.cmp.apply.ace.handler.AceAddHandler) context
					.get("nc.ui.cmp.apply.ace.handler.AceAddHandler#15b0b6d");
		nc.ui.cmp.apply.ace.handler.AceAddHandler bean = new nc.ui.cmp.apply.ace.handler.AceAddHandler();
		context.put("nc.ui.cmp.apply.ace.handler.AceAddHandler#15b0b6d", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_e8fe80() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#e8fe80") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#e8fe80");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#e8fe80", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
		bean.setHandler(getAceOrgChangedHandler_1103488());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.ace.handler.AceOrgChangedHandler getAceOrgChangedHandler_1103488() {
		if (context
				.get("nc.ui.cmp.apply.ace.handler.AceOrgChangedHandler#1103488") != null)
			return (nc.ui.cmp.apply.ace.handler.AceOrgChangedHandler) context
					.get("nc.ui.cmp.apply.ace.handler.AceOrgChangedHandler#1103488");
		nc.ui.cmp.apply.ace.handler.AceOrgChangedHandler bean = new nc.ui.cmp.apply.ace.handler.AceOrgChangedHandler(
				getBillFormEditor());
		context.put("nc.ui.cmp.apply.ace.handler.AceOrgChangedHandler#1103488",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_9756ec() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9756ec") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context
					.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9756ec");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9756ec", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent");
		bean.setHandler(getTMAppUIStateChangeListener_9cbe63());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.tmpub.event.TMAppUIStateChangeListener getTMAppUIStateChangeListener_9cbe63() {
		if (context.get("nc.ui.tmpub.event.TMAppUIStateChangeListener#9cbe63") != null)
			return (nc.ui.tmpub.event.TMAppUIStateChangeListener) context
					.get("nc.ui.tmpub.event.TMAppUIStateChangeListener#9cbe63");
		nc.ui.tmpub.event.TMAppUIStateChangeListener bean = new nc.ui.tmpub.event.TMAppUIStateChangeListener(
				getBillFormEditor());
		context.put("nc.ui.tmpub.event.TMAppUIStateChangeListener#9cbe63", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context
					.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList272());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList272() {
		List list = new ArrayList();
		list.add(getActionsOfList());
		list.add(getActionsOfCard());
		return list;
	}

	public nc.ui.cmp.bill.impl.CmpBillNodeKeyChangeListener getCmpBillNodeKeyChangeListener() {
		if (context.get("CmpBillNodeKeyChangeListener") != null)
			return (nc.ui.cmp.bill.impl.CmpBillNodeKeyChangeListener) context
					.get("CmpBillNodeKeyChangeListener");
		nc.ui.cmp.bill.impl.CmpBillNodeKeyChangeListener bean = new nc.ui.cmp.bill.impl.CmpBillNodeKeyChangeListener();
		context.put("CmpBillNodeKeyChangeListener", bean);
		bean.setCard(getBillFormEditor());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getListView());
		context.put("actionsOfList", bean);
		bean.setModel(getManageAppModel());
		bean.setActions(getManagedList273());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList273() {
		List list = new ArrayList();
		list.add(getAddMenu());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshAction());
		list.add(getSeparatorAction());
		list.add(getTranstypeAction());
		list.add(getCommitMenuAction());
		list.add(getAuditMenuAction());
		list.add(getSeparatorAction());
		list.add(getGenerateAction());
		list.add(getSeparatorAction());
		list.add(getImageFuncActionGroup());
		list.add(getOtherFuncActionGroup());
		list.add(getSeparatorAction());
		list.add(getBillQueryActionGroup());
		list.add(getSeparatorAction());
		list.add(getList_printMenuAction());
		list.add(getSeparatorAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillFormEditor());
		context.put("actionsOfCard", bean);
		bean.setModel(getManageAppModel());
		bean.setActions(getManagedList274());
		bean.setEditActions(getManagedList275());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList274() {
		List list = new ArrayList();
		list.add(getAddMenu());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getCardRefreshAction());
		list.add(getSeparatorAction());
		list.add(getTranstypeAction());
		list.add(getCommitMenuAction());
		list.add(getAuditMenuAction());
		list.add(getSeparatorAction());
		list.add(getGenerateAction());
		list.add(getSeparatorAction());
		list.add(getOtherFuncActionGroup());
		list.add(getImageFuncActionGroup());
		list.add(getSeparatorAction());
		list.add(getBillQueryActionGroup());
		list.add(getSeparatorAction());
		list.add(getCard_printMenuAction());
		list.add(getSeparatorAction());
		return list;
	}

	private List getManagedList275() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add(getApplySqaddAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		return list;
	}

	public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() {
		if (context.get("separatorAction") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("separatorAction");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("separatorAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ShowUpComponentInterceptor getShowListInterceptor() {
		if (context.get("showListInterceptor") != null)
			return (nc.ui.cmp.apply.action.ShowUpComponentInterceptor) context
					.get("showListInterceptor");
		nc.ui.cmp.apply.action.ShowUpComponentInterceptor bean = new nc.ui.cmp.apply.action.ShowUpComponentInterceptor();
		context.put("showListInterceptor", bean);
		bean.setShowUpComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ShowUpComponentInterceptor getShowCardInterceptor() {
		if (context.get("showCardInterceptor") != null)
			return (nc.ui.cmp.apply.action.ShowUpComponentInterceptor) context
					.get("showCardInterceptor");
		nc.ui.cmp.apply.action.ShowUpComponentInterceptor bean = new nc.ui.cmp.apply.action.ShowUpComponentInterceptor();
		context.put("showCardInterceptor", bean);
		bean.setShowUpComponent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenu() {
		if (context.get("addMenu") != null)
			return (nc.ui.pubapp.uif2app.actions.AddMenuAction) context
					.get("addMenu");
		nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
		context.put("addMenu", bean);
		bean.setBillType("36D1");
		bean.setActions(getManagedList276());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList276() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getAddFromU1Action());
		return list;
	}

	public nc.ui.cmp.apply.action.ApplyAddFrom36OVAction getAddFromU1Action() {
		if (context.get("addFromU1Action") != null)
			return (nc.ui.cmp.apply.action.ApplyAddFrom36OVAction) context
					.get("addFromU1Action");
		nc.ui.cmp.apply.action.ApplyAddFrom36OVAction bean = new nc.ui.cmp.apply.action.ApplyAddFrom36OVAction();
		context.put("addFromU1Action", bean);
		bean.setSourceBillType("36OV");
		bean.setSourceBillName(getI18nFB_1cba041());
		bean.setSelfBillType("36D1");
		bean.setTransferViewProcessor(getTransferViewProcessor());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFlowBillType(false);
		bean.setListDigitListener(getListDigitListener());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1cba041() {
		if (context.get("nc.ui.uif2.I18nFB#1cba041") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1cba041");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1cba041", bean);
		bean.setResDir("3607apply_add_0");
		bean.setResId("03607apply_add-0161");
		bean.setDefaultValue("ReferenceUpBillAction");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1cba041", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.cmp.apply.query.ApplyRefListPanelDigitListener getListDigitListener() {
		if (context.get("listDigitListener") != null)
			return (nc.ui.cmp.apply.query.ApplyRefListPanelDigitListener) context
					.get("listDigitListener");
		nc.ui.cmp.apply.query.ApplyRefListPanelDigitListener bean = new nc.ui.cmp.apply.query.ApplyRefListPanelDigitListener();
		context.put("listDigitListener", bean);
		bean.setSrcDestItemCollection(getCardSrcDestCollection());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyAddAction getAddAction() {
		if (context.get("addAction") != null)
			return (nc.ui.cmp.apply.action.ApplyAddAction) context
					.get("addAction");
		nc.ui.cmp.apply.action.ApplyAddAction bean = new nc.ui.cmp.apply.action.ApplyAddAction();
		context.put("addAction", bean);
		bean.setModel(getManageAppModel());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplySaveAndCommitScriptAction getSaveCommitAction() {
		if (context.get("saveCommitAction") != null)
			return (nc.ui.cmp.apply.action.ApplySaveAndCommitScriptAction) context
					.get("saveCommitAction");
		nc.ui.cmp.apply.action.ApplySaveAndCommitScriptAction bean = new nc.ui.cmp.apply.action.ApplySaveAndCommitScriptAction(
				getSaveAction(), getCommitAction());
		context.put("saveCommitAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyPFNewSaveAction getApplySqaddAction() {
		if (context.get("applySqaddAction") != null)
			return (nc.ui.cmp.apply.action.ApplyPFNewSaveAction) context
					.get("applySqaddAction");
		nc.ui.cmp.apply.action.ApplyPFNewSaveAction bean = new nc.ui.cmp.apply.action.ApplyPFNewSaveAction(
				"SAVENEW");
		context.put("applySqaddAction", bean);
		bean.setAddAction(getAddAction());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.Cmp_applyEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.cmp.apply.action.Cmp_applyEditAction) context
					.get("editAction");
		nc.ui.cmp.apply.action.Cmp_applyEditAction bean = new nc.ui.cmp.apply.action.Cmp_applyEditAction();
		context.put("editAction", bean);
		bean.setModel(getManageAppModel());
		bean.setPowercheck(true);
		bean.setBillType("36D1");
		bean.setBillCodeName("vbillno");
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setFieldNames(getManagedList277());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList277() {
		List list = new ArrayList();
		list.add("pk_group");
		list.add("vbillstatus");
		list.add("pk_currtype");
		list.add("applydate");
		list.add("pk_applyperiod");
		list.add("pk_project");
		return list;
	}

	public nc.ui.cmp.apply.action.Cmp_applyDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.cmp.apply.action.Cmp_applyDeleteAction) context
					.get("deleteAction");
		nc.ui.cmp.apply.action.Cmp_applyDeleteAction bean = new nc.ui.cmp.apply.action.Cmp_applyDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setActionName("DELETE");
		bean.setPowercheck(true);
		bean.setBillType("36D1");
		bean.setBillCodeName("vbillno");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.Cmp_applySaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.cmp.apply.action.Cmp_applySaveAction) context
					.get("saveAction");
		nc.ui.cmp.apply.action.Cmp_applySaveAction bean = new nc.ui.cmp.apply.action.Cmp_applySaveAction();
		context.put("saveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setActionName("SAVEBASE");
		bean.setBillType("36D1");
		bean.setValidationService(getValidateService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.validation.CompositeValidation getValidateService() {
		if (context.get("validateService") != null)
			return (nc.ui.pubapp.uif2app.validation.CompositeValidation) context
					.get("validateService");
		nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
		context.put("validateService", bean);
		bean.setValidators(getManagedList278());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList278() {
		List list = new ArrayList();
		list.add(getTemplateNotNullValidation_1863454());
		return list;
	}

	private nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation getTemplateNotNullValidation_1863454() {
		if (context
				.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#1863454") != null)
			return (nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation) context
					.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#1863454");
		nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation bean = new nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation();
		context.put(
				"nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#1863454",
				bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyCancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.cmp.apply.action.ApplyCancelAction) context
					.get("cancelAction");
		nc.ui.cmp.apply.action.ApplyCancelAction bean = new nc.ui.cmp.apply.action.ApplyCancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyCopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.cmp.apply.action.ApplyCopyAction) context
					.get("copyAction");
		nc.ui.cmp.apply.action.ApplyCopyAction bean = new nc.ui.cmp.apply.action.ApplyCopyAction();
		context.put("copyAction", bean);
		bean.setModel(getManageAppModel());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setEditor(getBillFormEditor());
		bean.setCopyActionProcessor(getCopyActionProcessor_1d59867());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.action.CopyActionProcessor getCopyActionProcessor_1d59867() {
		if (context.get("nc.ui.cmp.apply.action.CopyActionProcessor#1d59867") != null)
			return (nc.ui.cmp.apply.action.CopyActionProcessor) context
					.get("nc.ui.cmp.apply.action.CopyActionProcessor#1d59867");
		nc.ui.cmp.apply.action.CopyActionProcessor bean = new nc.ui.cmp.apply.action.CopyActionProcessor();
		context.put("nc.ui.cmp.apply.action.CopyActionProcessor#1d59867", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.query.Cmp_applyQueryConditionInitializer getQryCondInitializer() {
		if (context.get("qryCondInitializer") != null)
			return (nc.ui.cmp.apply.query.Cmp_applyQueryConditionInitializer) context
					.get("qryCondInitializer");
		nc.ui.cmp.apply.query.Cmp_applyQueryConditionInitializer bean = new nc.ui.cmp.apply.query.Cmp_applyQueryConditionInitializer();
		context.put("qryCondInitializer", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context
					.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setDataManager(getModelDataManager());
		bean.setQryCondDLGInitializer(getQryCondInitializer());
		bean.setShowUpComponent(getListView());
		bean.setTemplateContainer(getQueryTemplateContainer());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.CmpRefreshSchemeAction getRefreshAction() {
		if (context.get("refreshAction") != null)
			return (nc.ui.cmp.apply.action.CmpRefreshSchemeAction) context
					.get("refreshAction");
		nc.ui.cmp.apply.action.CmpRefreshSchemeAction bean = new nc.ui.cmp.apply.action.CmpRefreshSchemeAction();
		context.put("refreshAction", bean);
		bean.setDataManager(getModelDataManager());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction() {
		if (context.get("cardRefreshAction") != null)
			return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction) context
					.get("cardRefreshAction");
		nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
		context.put("cardRefreshAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyPayTransTypeAction getTranstypeAction() {
		if (context.get("transtypeAction") != null)
			return (nc.ui.cmp.apply.action.ApplyPayTransTypeAction) context
					.get("transtypeAction");
		nc.ui.cmp.apply.action.ApplyPayTransTypeAction bean = new nc.ui.cmp.apply.action.ApplyPayTransTypeAction();
		context.put("transtypeAction", bean);
		bean.setModel(getManageAppModel());
		bean.setTransTypeRefModel(getTransTypeRefModel());
		bean.setWherepart(" parentbilltype = '36D1'");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.ref.FiBillTypeRefModel getTransTypeRefModel() {
		if (context.get("transTypeRefModel") != null)
			return (nc.ui.cmp.ref.FiBillTypeRefModel) context
					.get("transTypeRefModel");
		nc.ui.cmp.ref.FiBillTypeRefModel bean = new nc.ui.cmp.ref.FiBillTypeRefModel();
		context.put("transTypeRefModel", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyCommitAction getCommitAction() {
		if (context.get("commitAction") != null)
			return (nc.ui.cmp.apply.action.ApplyCommitAction) context
					.get("commitAction");
		nc.ui.cmp.apply.action.ApplyCommitAction bean = new nc.ui.cmp.apply.action.ApplyCommitAction();
		context.put("commitAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("36D1");
		bean.setActionName("SAVE");
		bean.setFilledUpInFlow(true);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyUnCommitAction getUnCommitAction() {
		if (context.get("unCommitAction") != null)
			return (nc.ui.cmp.apply.action.ApplyUnCommitAction) context
					.get("unCommitAction");
		nc.ui.cmp.apply.action.ApplyUnCommitAction bean = new nc.ui.cmp.apply.action.ApplyUnCommitAction();
		context.put("unCommitAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("36D1");
		bean.setActionName("UNSAVEBILL");
		bean.setFilledUpInFlow(true);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getCommitMenuAction() {
		if (context.get("commitMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("commitMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("commitMenuAction", bean);
		bean.setCode("commitMenuAction");
		bean.setName(getI18nFB_8bc2a());
		bean.setActions(getManagedList279());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_8bc2a() {
		if (context.get("nc.ui.uif2.I18nFB#8bc2a") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#8bc2a");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#8bc2a", bean);
		bean.setResDir("common");
		bean.setResId("2UC000-001107");
		bean.setDefaultValue("commit");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#8bc2a", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList279() {
		List list = new ArrayList();
		list.add(getCommitAction());
		list.add(getUnCommitAction());
		return list;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getApprovepowervalidservice() {
		if (context.get("approvepowervalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("approvepowervalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("approvepowervalidservice", bean);
		bean.setActionCode("approve");
		bean.setBillCodeFiledName("vbillno");
		bean.setPermissionCode("36D1");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getUnapprovepowervalidservice() {
		if (context.get("unapprovepowervalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("unapprovepowervalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("unapprovepowervalidservice", bean);
		bean.setActionCode("unapprove");
		bean.setBillCodeFiledName("vbillno");
		bean.setPermissionCode("36D1");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyApproveAction getApproveAction() {
		if (context.get("approveAction") != null)
			return (nc.ui.cmp.apply.action.ApplyApproveAction) context
					.get("approveAction");
		nc.ui.cmp.apply.action.ApplyApproveAction bean = new nc.ui.cmp.apply.action.ApplyApproveAction();
		context.put("approveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("36D1");
		bean.setActionName("APPROVE");
		bean.setValidationService(getApprovepowervalidservice());
		bean.setFilledUpInFlow(true);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyUnApproveAction getUnApproveAction() {
		if (context.get("unApproveAction") != null)
			return (nc.ui.cmp.apply.action.ApplyUnApproveAction) context
					.get("unApproveAction");
		nc.ui.cmp.apply.action.ApplyUnApproveAction bean = new nc.ui.cmp.apply.action.ApplyUnApproveAction();
		context.put("unApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("36D1");
		bean.setActionName("UNAPPROVE");
		bean.setValidationService(getUnapprovepowervalidservice());
		bean.setFilledUpInFlow(true);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getAuditMenuAction() {
		if (context.get("auditMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("auditMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("auditMenuAction", bean);
		bean.setCode("auditMenuAction");
		bean.setName(getI18nFB_17978cd());
		bean.setActions(getManagedList280());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_17978cd() {
		if (context.get("nc.ui.uif2.I18nFB#17978cd") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#17978cd");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#17978cd", bean);
		bean.setResDir("common");
		bean.setResId("2UC000-001109");
		bean.setDefaultValue("approve");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#17978cd", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList280() {
		List list = new ArrayList();
		list.add(getApproveAction());
		list.add(getUnApproveAction());
		list.add(getQueryAuditFlowAction());
		return list;
	}

	public nc.funcnode.ui.action.GroupAction getBillActionGroup() {
		if (context.get("billActionGroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("billActionGroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("billActionGroup", bean);
		bean.setCode("paybill");
		bean.setName(getI18nFB_1ea037e());
		bean.setActions(getManagedList281());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1ea037e() {
		if (context.get("nc.ui.uif2.I18nFB#1ea037e") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1ea037e");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1ea037e", bean);
		bean.setResDir("3607apply_add_0");
		bean.setDefaultValue("generate");
		bean.setResId("03607apply_add-0164");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1ea037e", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList281() {
		List list = new ArrayList();
		list.add(getGenerateAction());
		list.add(getSeparatorAction());
		return list;
	}

	public nc.ui.cmp.apply.action.ApplyToFiBillAction getGenerateAction() {
		if (context.get("generateAction") != null)
			return (nc.ui.cmp.apply.action.ApplyToFiBillAction) context
					.get("generateAction");
		nc.ui.cmp.apply.action.ApplyToFiBillAction bean = new nc.ui.cmp.apply.action.ApplyToFiBillAction(
				"GENERATE");
		context.put("generateAction", bean);
		bean.setModel(getManageAppModel());
		bean.setCardRefreshAction(getCardRefreshAction());
		bean.setRefreshAction(getRefreshAction());
		bean.setEditor(getBillFormEditor());
		bean.setIsValidateWithRtn(true);
		bean.setIsBatch(true);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyToFiBillCancelAction getUngenerateAction() {
		if (context.get("ungenerateAction") != null)
			return (nc.ui.cmp.apply.action.ApplyToFiBillCancelAction) context
					.get("ungenerateAction");
		nc.ui.cmp.apply.action.ApplyToFiBillCancelAction bean = new nc.ui.cmp.apply.action.ApplyToFiBillCancelAction(
				"UNGENERATE");
		context.put("ungenerateAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setIsValidateWithRtn(true);
		bean.setIsBatch(true);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintAction() {
		if (context.get("printAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("printAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("printAction", bean);
		bean.setPreview(false);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction() {
		if (context.get("previewAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context
					.get("previewAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("previewAction", bean);
		bean.setPreview(true);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction() {
		if (context.get("outputAction") != null)
			return (nc.ui.pubapp.uif2app.actions.OutputAction) context
					.get("outputAction");
		nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
		context.put("outputAction", bean);
		bean.setModel(getManageAppModel());
		bean.setParent(getBillFormEditor());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.FileDocManageAction getFileDocManageAction() {
		if (context.get("fileDocManageAction") != null)
			return (nc.ui.pubapp.uif2app.actions.FileDocManageAction) context
					.get("fileDocManageAction");
		nc.ui.pubapp.uif2app.actions.FileDocManageAction bean = new nc.ui.pubapp.uif2app.actions.FileDocManageAction();
		context.put("fileDocManageAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getImageFuncActionGroup() {
		if (context.get("imageFuncActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("imageFuncActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("imageFuncActionGroup", bean);
		bean.setCode("imageFunc");
		bean.setName(getI18nFB_8b5dc5());
		bean.setActions(getManagedList282());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_8b5dc5() {
		if (context.get("nc.ui.uif2.I18nFB#8b5dc5") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#8b5dc5");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#8b5dc5", bean);
		bean.setResDir("3607mng_0");
		bean.setDefaultValue("影像");
		bean.setResId("03607mng-0446");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#8b5dc5", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList282() {
		List list = new ArrayList();
		list.add(getImageShowAction());
		return list;
	}

	public nc.imag.scan.action.BaseImageShowAction getImageShowAction() {
		if (context.get("imageShowAction") != null)
			return (nc.imag.scan.action.BaseImageShowAction) context
					.get("imageShowAction");
		nc.imag.scan.action.BaseImageShowAction bean = new nc.imag.scan.action.BaseImageShowAction();
		context.put("imageShowAction", bean);
		bean.setModel(getManageAppModel());
		bean.setPk_billtype("0001Z6000000000036D1");
		bean.setBtnName(getI18nFB_1b9475b());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1b9475b() {
		if (context.get("nc.ui.uif2.I18nFB#1b9475b") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1b9475b");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1b9475b", bean);
		bean.setResDir("3607mng_0");
		bean.setDefaultValue("影像查看");
		bean.setResId("03607mng-0447");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1b9475b", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.imag.scan.action.BaseImageScanAction getImageScanAction() {
		if (context.get("imageScanAction") != null)
			return (nc.imag.scan.action.BaseImageScanAction) context
					.get("imageScanAction");
		nc.imag.scan.action.BaseImageScanAction bean = new nc.imag.scan.action.BaseImageScanAction();
		context.put("imageScanAction", bean);
		bean.setModel(getManageAppModel());
		bean.setPk_billtype("0001Z6000000000036D1");
		bean.setCheckscanway("nc.imag.scan.service.CheckScanWay");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getOtherFuncActionGroup() {
		if (context.get("otherFuncActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("otherFuncActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("otherFuncActionGroup", bean);
		bean.setCode("otherFunc");
		bean.setName(getI18nFB_9e49b7());
		bean.setActions(getManagedList283());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_9e49b7() {
		if (context.get("nc.ui.uif2.I18nFB#9e49b7") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#9e49b7");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#9e49b7", bean);
		bean.setResDir("3607apply_add_0");
		bean.setDefaultValue("help");
		bean.setResId("03607apply_add-0163");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#9e49b7", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList283() {
		List list = new ArrayList();
		list.add(getFileDocManageAction());
		return list;
	}

	public nc.funcnode.ui.action.GroupAction getPrintMenuAction() {
		if (context.get("printMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("printMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printMenuAction", bean);
		bean.setCode("printMenuAction");
		bean.setName(getI18nFB_1f9c61d());
		bean.setActions(getManagedList284());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1f9c61d() {
		if (context.get("nc.ui.uif2.I18nFB#1f9c61d") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1f9c61d");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1f9c61d", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("print");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1f9c61d", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList284() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		return list;
	}

	public nc.funcnode.ui.action.MenuAction getBillQueryActionGroup() {
		if (context.get("billQueryActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("billQueryActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("billQueryActionGroup", bean);
		bean.setCode("querybill");
		bean.setName(getI18nFB_13dc811());
		bean.setActions(getManagedList285());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_13dc811() {
		if (context.get("nc.ui.uif2.I18nFB#13dc811") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#13dc811");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#13dc811", bean);
		bean.setResDir("3607apply_add_0");
		bean.setDefaultValue("linkQuery");
		bean.setResId("03607apply_add-0162");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#13dc811", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList285() {
		List list = new ArrayList();
		list.add(getHxBzQueryAction());
		list.add(getSeparatorAction());
		list.add(getLinkQueryAction());
		list.add(getLinkQueryBudgetAction());
		return list;
	}

	public nc.ui.cmp.apply.action.HxBzQueryAction getHxBzQueryAction() {
		if (context.get("hxBzQueryAction") != null)
			return (nc.ui.cmp.apply.action.HxBzQueryAction) context
					.get("hxBzQueryAction");
		nc.ui.cmp.apply.action.HxBzQueryAction bean = new nc.ui.cmp.apply.action.HxBzQueryAction();
		context.put("hxBzQueryAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.LinkQueryAction getLinkQueryAction() {
		if (context.get("linkQueryAction") != null)
			return (nc.ui.pubapp.uif2app.actions.LinkQueryAction) context
					.get("linkQueryAction");
		nc.ui.pubapp.uif2app.actions.LinkQueryAction bean = new nc.ui.pubapp.uif2app.actions.LinkQueryAction();
		context.put("linkQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setBillType("36D1");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyLinkQueryUpBillAction getLinkQueryUpBillAction() {
		if (context.get("linkQueryUpBillAction") != null)
			return (nc.ui.cmp.apply.action.ApplyLinkQueryUpBillAction) context
					.get("linkQueryUpBillAction");
		nc.ui.cmp.apply.action.ApplyLinkQueryUpBillAction bean = new nc.ui.cmp.apply.action.ApplyLinkQueryUpBillAction();
		context.put("linkQueryUpBillAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEdit(getBillFormEditor());
		bean.setListView(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.action.ApplyLinkQueryBudgetAction getLinkQueryBudgetAction() {
		if (context.get("linkQueryBudgetAction") != null)
			return (nc.ui.cmp.apply.action.ApplyLinkQueryBudgetAction) context
					.get("linkQueryBudgetAction");
		nc.ui.cmp.apply.action.ApplyLinkQueryBudgetAction bean = new nc.ui.cmp.apply.action.ApplyLinkQueryBudgetAction();
		context.put("linkQueryBudgetAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getQueryAuditFlowAction() {
		if (context.get("queryAuditFlowAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction) context
					.get("queryAuditFlowAction");
		nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
		context.put("queryAuditFlowAction", bean);
		bean.setBillType("36D1");
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader() {
		if (context.get("billLazilyLoader") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader) context
					.get("billLazilyLoader");
		nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
		context.put("billLazilyLoader", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator() {
		if (context.get("lasilyLodadMediator") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager) context
					.get("lasilyLodadMediator");
		nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
		context.put("lasilyLodadMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setLoader(getBillLazilyLoader());
		bean.setLazilyLoadSupporter(getManagedList286());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList286() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_119e316());
		list.add(getListPanelLazilyLoad_8bdbc4());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_119e316() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#119e316") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#119e316");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#119e316",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_8bdbc4() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#8bdbc4") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#8bdbc4");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#8bdbc4",
				bean);
		bean.setListView(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
		if (context.get("billBodySortMediator") != null)
			return (nc.ui.pubapp.uif2app.model.BillBodySortMediator) context
					.get("billBodySortMediator");
		nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(
				getManageAppModel(), getBillFormEditor(), getListView());
		context.put("billBodySortMediator", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.cmp.apply.listener.CMPApplyFuncNodeInitDataListener getInitDataListener() {
		if (context.get("InitDataListener") != null)
			return (nc.ui.cmp.apply.listener.CMPApplyFuncNodeInitDataListener) context
					.get("InitDataListener");
		nc.ui.cmp.apply.listener.CMPApplyFuncNodeInitDataListener bean = new nc.ui.cmp.apply.listener.CMPApplyFuncNodeInitDataListener();
		context.put("InitDataListener", bean);
		bean.setListView(getListView());
		bean.setEditor(getBillFormEditor());
		bean.setPaginationModel(getPaginationModel());
		bean.setModel(getManageAppModel());
		bean.setContext(getContext());
		bean.setVoClassName("nc.vo.cmp.apply.AggApplyVO");
		bean.setAutoShowUpComponent(getBillFormEditor());
		bean.setProcessorMap(getManagedMap6());
		bean.setQueryAction(getQueryAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap6() {
		Map map = new HashMap();
		map.put("10", getInitDataProcessorFor21_94e46());
		map.put("12", getInitDataProcessorForZ2_f1f996());
		return map;
	}

	private nc.ui.cmp.apply.billref.processor.InitDataProcessorFor21 getInitDataProcessorFor21_94e46() {
		if (context
				.get("nc.ui.cmp.apply.billref.processor.InitDataProcessorFor21#94e46") != null)
			return (nc.ui.cmp.apply.billref.processor.InitDataProcessorFor21) context
					.get("nc.ui.cmp.apply.billref.processor.InitDataProcessorFor21#94e46");
		nc.ui.cmp.apply.billref.processor.InitDataProcessorFor21 bean = new nc.ui.cmp.apply.billref.processor.InitDataProcessorFor21();
		context.put(
				"nc.ui.cmp.apply.billref.processor.InitDataProcessorFor21#94e46",
				bean);
		bean.setTransferProcessor(getTransferViewProcessor());
		bean.setListDigitListener(getListDigitListener());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.billref.processor.InitDataProcessorForZ2 getInitDataProcessorForZ2_f1f996() {
		if (context
				.get("nc.ui.cmp.apply.billref.processor.InitDataProcessorForZ2#f1f996") != null)
			return (nc.ui.cmp.apply.billref.processor.InitDataProcessorForZ2) context
					.get("nc.ui.cmp.apply.billref.processor.InitDataProcessorForZ2#f1f996");
		nc.ui.cmp.apply.billref.processor.InitDataProcessorForZ2 bean = new nc.ui.cmp.apply.billref.processor.InitDataProcessorForZ2();
		context.put(
				"nc.ui.cmp.apply.billref.processor.InitDataProcessorForZ2#f1f996",
				bean);
		bean.setTransferProcessor(getTransferViewProcessor());
		bean.setListDigitListener(getListDigitListener());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor() {
		if (context.get("transferViewProcessor") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferViewProcessor");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferViewProcessor", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCommitAction(getCommitAction());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setSaveAction(getSaveAction());
		bean.setCancelAction(getCancelAction());
		bean.setBillForm(getBillFormEditor());
		bean.setTransferLogic(getApplyTransferBillDataLogic_38ed7d());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.view.ApplyTransferBillDataLogic getApplyTransferBillDataLogic_38ed7d() {
		if (context
				.get("nc.ui.cmp.apply.view.ApplyTransferBillDataLogic#38ed7d") != null)
			return (nc.ui.cmp.apply.view.ApplyTransferBillDataLogic) context
					.get("nc.ui.cmp.apply.view.ApplyTransferBillDataLogic#38ed7d");
		nc.ui.cmp.apply.view.ApplyTransferBillDataLogic bean = new nc.ui.cmp.apply.view.ApplyTransferBillDataLogic();
		context.put("nc.ui.cmp.apply.view.ApplyTransferBillDataLogic#38ed7d",
				bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.pubapp.common.validateservice.ClosingCheck) context
					.get("ClosingListener");
		nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
		context.put("ClosingListener", bean);
		bean.setModel(getManageAppModel());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
		if (context.get("fractionFixMediator") != null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator) context
					.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(
				getBillFormEditor());
		context.put("fractionFixMediator", bean);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
		if (context.get("mouseClickShowPanelMediator") != null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator) context
					.get("mouseClickShowPanelMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("mouseClickShowPanelMediator", bean);
		bean.setListView(getListView());
		bean.setShowUpComponent(getBillFormEditor());
		bean.setHyperLinkColumn("vbillno");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.bill.BillCodeMediator getBillCodeMediator() {
		if (context.get("billCodeMediator") != null)
			return (nc.ui.pubapp.bill.BillCodeMediator) context
					.get("billCodeMediator");
		nc.ui.pubapp.bill.BillCodeMediator bean = new nc.ui.pubapp.bill.BillCodeMediator();
		context.put("billCodeMediator", bean);
		bean.setBillForm(getBillFormEditor());
		bean.setBillCodeKey("vbillno");
		bean.setBillType("36D1");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
		if (context.get("rowNoMediator") != null)
			return (nc.ui.pubapp.uif2app.view.RowNoMediator) context
					.get("rowNoMediator");
		nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
		context.put("rowNoMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller() {
		if (context.get("remoteCallCombinatorCaller") != null)
			return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller) context
					.get("remoteCallCombinatorCaller");
		nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
		context.put("remoteCallCombinatorCaller", bean);
		bean.setRemoteCallers(getManagedList287());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList287() {
		List list = new ArrayList();
		list.add(getQueryTemplateContainer());
		list.add(getTemplateContainer());
		list.add(getUserdefitemContainer());
		return list;
	}

	public nc.ui.tmpub.filter.DefaultRefWherePartHandler getDefaultRefWherePartHandler() {
		if (context.get("defaultRefWherePartHandler") != null)
			return (nc.ui.tmpub.filter.DefaultRefWherePartHandler) context
					.get("defaultRefWherePartHandler");
		nc.ui.tmpub.filter.DefaultRefWherePartHandler bean = new nc.ui.tmpub.filter.DefaultRefWherePartHandler();
		context.put("defaultRefWherePartHandler", bean);
		bean.setUiAccessor(getDefaultUIAccessor_16fdbec());
		bean.setFilterList(getManagedList288());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.tmpub.filter.DefaultUIAccessor getDefaultUIAccessor_16fdbec() {
		if (context.get("nc.ui.tmpub.filter.DefaultUIAccessor#16fdbec") != null)
			return (nc.ui.tmpub.filter.DefaultUIAccessor) context
					.get("nc.ui.tmpub.filter.DefaultUIAccessor#16fdbec");
		nc.ui.tmpub.filter.DefaultUIAccessor bean = new nc.ui.tmpub.filter.DefaultUIAccessor();
		context.put("nc.ui.tmpub.filter.DefaultUIAccessor#16fdbec", bean);
		bean.setBillCardPanelEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList288() {
		List list = new ArrayList();
		list.add(getTranstypeFilter_cf66c4());
		list.add(getPayBankaccFilter_63ef19());
		list.add(getPayBankaccFilter_e68bf4());
		list.add(getBankaccRefbyCurrFilter_1ee5940());
		list.add(getBankaccRefbyCurrFilter_125b229());
		list.add(getBankaccRefbyCurrFilter_1d3a670());
		list.add(getSupplierRefFrozenFilter_54081a());
		list.add(getSelfSupplierRefFilter_dff69e());
		list.add(getBusinesstypeFilter_1b82ba1());
		list.add(getBusinesstypeFilter_385c03());
		list.add(getFundplanFilter_20d667());
		list.add(getCustBankNoInnerAccFilter_1dc3aa0());
		list.add(getBanksubaccNoInnerAccFilter_18ce789());
		list.add(getBanksubaccNoFrozenFilter_1b4dc5f());
		list.add(getBanksubaccNoFrozenFilter_effe92());
		list.add(getBanksubaccNoDestroyFilter_322bed());
		list.add(getBanksubaccNoDestroyFilter_1e2144e());
		return list;
	}

	private nc.ui.cmp.apply.filter.TranstypeFilter getTranstypeFilter_cf66c4() {
		if (context.get("nc.ui.cmp.apply.filter.TranstypeFilter#cf66c4") != null)
			return (nc.ui.cmp.apply.filter.TranstypeFilter) context
					.get("nc.ui.cmp.apply.filter.TranstypeFilter#cf66c4");
		nc.ui.cmp.apply.filter.TranstypeFilter bean = new nc.ui.cmp.apply.filter.TranstypeFilter();
		context.put("nc.ui.cmp.apply.filter.TranstypeFilter#cf66c4", bean);
		bean.setSrcKey("pk_billtypeid");
		bean.setDestKey("pk_billtypeid");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.filter.PayBankaccFilter getPayBankaccFilter_63ef19() {
		if (context.get("nc.ui.cmp.apply.filter.PayBankaccFilter#63ef19") != null)
			return (nc.ui.cmp.apply.filter.PayBankaccFilter) context
					.get("nc.ui.cmp.apply.filter.PayBankaccFilter#63ef19");
		nc.ui.cmp.apply.filter.PayBankaccFilter bean = new nc.ui.cmp.apply.filter.PayBankaccFilter();
		context.put("nc.ui.cmp.apply.filter.PayBankaccFilter#63ef19", bean);
		bean.setSrcKey("paytype");
		bean.setDestKey("pk_bankacc_p");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.filter.PayBankaccFilter getPayBankaccFilter_e68bf4() {
		if (context.get("nc.ui.cmp.apply.filter.PayBankaccFilter#e68bf4") != null)
			return (nc.ui.cmp.apply.filter.PayBankaccFilter) context
					.get("nc.ui.cmp.apply.filter.PayBankaccFilter#e68bf4");
		nc.ui.cmp.apply.filter.PayBankaccFilter bean = new nc.ui.cmp.apply.filter.PayBankaccFilter();
		context.put("nc.ui.cmp.apply.filter.PayBankaccFilter#e68bf4", bean);
		bean.setSrcKey("isputdown");
		bean.setDestKey("pk_bankacc_p");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter getBankaccRefbyCurrFilter_1ee5940() {
		if (context
				.get("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#1ee5940") != null)
			return (nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter) context
					.get("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#1ee5940");
		nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter bean = new nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter();
		context.put("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#1ee5940",
				bean);
		bean.setSrcKey("pk_currtype");
		bean.setDestKey("pk_bankacc_p");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter getBankaccRefbyCurrFilter_125b229() {
		if (context
				.get("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#125b229") != null)
			return (nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter) context
					.get("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#125b229");
		nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter bean = new nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter();
		context.put("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#125b229",
				bean);
		bean.setSrcKey("pk_currtype");
		bean.setDestKey("pk_bankacc_r");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter getBankaccRefbyCurrFilter_1d3a670() {
		if (context
				.get("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#1d3a670") != null)
			return (nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter) context
					.get("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#1d3a670");
		nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter bean = new nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter();
		context.put("nc.ui.cmp.apply.reffilter.BankaccRefbyCurrFilter#1d3a670",
				bean);
		bean.setSrcKey("pk_currtype");
		bean.setDestKey("pk_bankacc_pd");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.reffilter.SupplierRefFrozenFilter getSupplierRefFrozenFilter_54081a() {
		if (context
				.get("nc.ui.cmp.apply.reffilter.SupplierRefFrozenFilter#54081a") != null)
			return (nc.ui.cmp.apply.reffilter.SupplierRefFrozenFilter) context
					.get("nc.ui.cmp.apply.reffilter.SupplierRefFrozenFilter#54081a");
		nc.ui.cmp.apply.reffilter.SupplierRefFrozenFilter bean = new nc.ui.cmp.apply.reffilter.SupplierRefFrozenFilter();
		context.put("nc.ui.cmp.apply.reffilter.SupplierRefFrozenFilter#54081a",
				bean);
		bean.setSrcKey("pk_supplier");
		bean.setDestKey("pk_supplier");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.reffilter.SelfSupplierRefFilter getSelfSupplierRefFilter_dff69e() {
		if (context
				.get("nc.ui.cmp.apply.reffilter.SelfSupplierRefFilter#dff69e") != null)
			return (nc.ui.cmp.apply.reffilter.SelfSupplierRefFilter) context
					.get("nc.ui.cmp.apply.reffilter.SelfSupplierRefFilter#dff69e");
		nc.ui.cmp.apply.reffilter.SelfSupplierRefFilter bean = new nc.ui.cmp.apply.reffilter.SelfSupplierRefFilter();
		context.put("nc.ui.cmp.apply.reffilter.SelfSupplierRefFilter#dff69e",
				bean);
		bean.setSrcKey("pk_org");
		bean.setDestKey("pk_supplier");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.filter.BusinesstypeFilter getBusinesstypeFilter_1b82ba1() {
		if (context.get("nc.ui.cmp.apply.filter.BusinesstypeFilter#1b82ba1") != null)
			return (nc.ui.cmp.apply.filter.BusinesstypeFilter) context
					.get("nc.ui.cmp.apply.filter.BusinesstypeFilter#1b82ba1");
		nc.ui.cmp.apply.filter.BusinesstypeFilter bean = new nc.ui.cmp.apply.filter.BusinesstypeFilter();
		context.put("nc.ui.cmp.apply.filter.BusinesstypeFilter#1b82ba1", bean);
		bean.setSrcKey("pk_busitype");
		bean.setDestKey("pk_busitype");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.filter.BusinesstypeFilter getBusinesstypeFilter_385c03() {
		if (context.get("nc.ui.cmp.apply.filter.BusinesstypeFilter#385c03") != null)
			return (nc.ui.cmp.apply.filter.BusinesstypeFilter) context
					.get("nc.ui.cmp.apply.filter.BusinesstypeFilter#385c03");
		nc.ui.cmp.apply.filter.BusinesstypeFilter bean = new nc.ui.cmp.apply.filter.BusinesstypeFilter();
		context.put("nc.ui.cmp.apply.filter.BusinesstypeFilter#385c03", bean);
		bean.setSrcKey("pk_billtypeid");
		bean.setDestKey("pk_busitype");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.filter.FundplanFilter getFundplanFilter_20d667() {
		if (context.get("nc.ui.cmp.apply.filter.FundplanFilter#20d667") != null)
			return (nc.ui.cmp.apply.filter.FundplanFilter) context
					.get("nc.ui.cmp.apply.filter.FundplanFilter#20d667");
		nc.ui.cmp.apply.filter.FundplanFilter bean = new nc.ui.cmp.apply.filter.FundplanFilter();
		context.put("nc.ui.cmp.apply.filter.FundplanFilter#20d667", bean);
		bean.setSrcKey("pk_planitem");
		bean.setDestKey("pk_planitem");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.cmp.apply.reffilter.CustBankNoInnerAccFilter getCustBankNoInnerAccFilter_1dc3aa0() {
		if (context
				.get("nc.ui.cmp.apply.reffilter.CustBankNoInnerAccFilter#1dc3aa0") != null)
			return (nc.ui.cmp.apply.reffilter.CustBankNoInnerAccFilter) context
					.get("nc.ui.cmp.apply.reffilter.CustBankNoInnerAccFilter#1dc3aa0");
		nc.ui.cmp.apply.reffilter.CustBankNoInnerAccFilter bean = new nc.ui.cmp.apply.reffilter.CustBankNoInnerAccFilter();
		context.put(
				"nc.ui.cmp.apply.reffilter.CustBankNoInnerAccFilter#1dc3aa0",
				bean);
		bean.setSrcKey("pk_supplier");
		bean.setDestKey("pk_bankacc_r");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.tmpub.filter.BanksubaccNoInnerAccFilter getBanksubaccNoInnerAccFilter_18ce789() {
		if (context
				.get("nc.ui.tmpub.filter.BanksubaccNoInnerAccFilter#18ce789") != null)
			return (nc.ui.tmpub.filter.BanksubaccNoInnerAccFilter) context
					.get("nc.ui.tmpub.filter.BanksubaccNoInnerAccFilter#18ce789");
		nc.ui.tmpub.filter.BanksubaccNoInnerAccFilter bean = new nc.ui.tmpub.filter.BanksubaccNoInnerAccFilter();
		context.put("nc.ui.tmpub.filter.BanksubaccNoInnerAccFilter#18ce789",
				bean);
		bean.setSrcKey("pk_bankacc_pd");
		bean.setDestKey("pk_bankacc_pd");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.tmpub.filter.BanksubaccNoFrozenFilter getBanksubaccNoFrozenFilter_1b4dc5f() {
		if (context.get("nc.ui.tmpub.filter.BanksubaccNoFrozenFilter#1b4dc5f") != null)
			return (nc.ui.tmpub.filter.BanksubaccNoFrozenFilter) context
					.get("nc.ui.tmpub.filter.BanksubaccNoFrozenFilter#1b4dc5f");
		nc.ui.tmpub.filter.BanksubaccNoFrozenFilter bean = new nc.ui.tmpub.filter.BanksubaccNoFrozenFilter();
		context.put("nc.ui.tmpub.filter.BanksubaccNoFrozenFilter#1b4dc5f", bean);
		bean.setSrcKey("pk_bankacc_p");
		bean.setDestKey("pk_bankacc_p");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.tmpub.filter.BanksubaccNoFrozenFilter getBanksubaccNoFrozenFilter_effe92() {
		if (context.get("nc.ui.tmpub.filter.BanksubaccNoFrozenFilter#effe92") != null)
			return (nc.ui.tmpub.filter.BanksubaccNoFrozenFilter) context
					.get("nc.ui.tmpub.filter.BanksubaccNoFrozenFilter#effe92");
		nc.ui.tmpub.filter.BanksubaccNoFrozenFilter bean = new nc.ui.tmpub.filter.BanksubaccNoFrozenFilter();
		context.put("nc.ui.tmpub.filter.BanksubaccNoFrozenFilter#effe92", bean);
		bean.setSrcKey("pk_bankacc_pd");
		bean.setDestKey("pk_bankacc_pd");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.tmpub.filter.BanksubaccNoDestroyFilter getBanksubaccNoDestroyFilter_322bed() {
		if (context.get("nc.ui.tmpub.filter.BanksubaccNoDestroyFilter#322bed") != null)
			return (nc.ui.tmpub.filter.BanksubaccNoDestroyFilter) context
					.get("nc.ui.tmpub.filter.BanksubaccNoDestroyFilter#322bed");
		nc.ui.tmpub.filter.BanksubaccNoDestroyFilter bean = new nc.ui.tmpub.filter.BanksubaccNoDestroyFilter();
		context.put("nc.ui.tmpub.filter.BanksubaccNoDestroyFilter#322bed", bean);
		bean.setSrcKey("pk_bankacc_p");
		bean.setDestKey("pk_bankacc_p");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.tmpub.filter.BanksubaccNoDestroyFilter getBanksubaccNoDestroyFilter_1e2144e() {
		if (context.get("nc.ui.tmpub.filter.BanksubaccNoDestroyFilter#1e2144e") != null)
			return (nc.ui.tmpub.filter.BanksubaccNoDestroyFilter) context
					.get("nc.ui.tmpub.filter.BanksubaccNoDestroyFilter#1e2144e");
		nc.ui.tmpub.filter.BanksubaccNoDestroyFilter bean = new nc.ui.tmpub.filter.BanksubaccNoDestroyFilter();
		context.put("nc.ui.tmpub.filter.BanksubaccNoDestroyFilter#1e2144e",
				bean);
		bean.setSrcKey("pk_bankacc_pd");
		bean.setDestKey("pk_bankacc_pd");
		bean.setDestPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}
