DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ10000000008HSX';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'LH20' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'LH20';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ10000000008HSX';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ10000000008HSY';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ10000000008HSY';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008HSZ';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008HT0';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008HT1';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ10000000008HSK';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ10000000008HSK';
DELETE FROM pub_function WHERE pk_billtype = 'LH20';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ10000000008HSK';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'LH20';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ10000000008HSK';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008HSL';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008HSM';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008HSN';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008HSO';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008HSP';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008HSQ';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008HSR';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008HSS';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008HST';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008HSU';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008HSV';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008HSW';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008HSJ';
delete from pub_print_datasource where ctemplateid = '0001ZZ10000000008HQQ';
delete from pub_print_cell where ctemplateid = '0001ZZ10000000008HQQ';
delete from pub_print_line where ctemplateid = '0001ZZ10000000008HQQ';
delete from pub_print_variable where ctemplateid = '0001ZZ10000000008HQQ';
delete from pub_print_template where ctemplateid = '0001ZZ10000000008HQQ';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008HQP';
delete from pub_query_condition where pk_templet = '0001ZZ10000000008HQ2';
delete from pub_query_templet where id = '0001ZZ10000000008HQ2';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008HQ1';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ10000000008HOW';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ10000000008HOW';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ10000000008HOW';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ10000000008HOV';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ10000000008HOT';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ10000000008HOU';
