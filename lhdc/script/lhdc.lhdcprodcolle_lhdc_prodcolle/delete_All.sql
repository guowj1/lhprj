DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ10000000008Q5F';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'LH21' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'LH21';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ10000000008Q5F';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ10000000008Q5G';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ10000000008Q5G';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008Q5H';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008Q5I';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008Q5J';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ10000000008Q52';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ10000000008Q52';
DELETE FROM pub_function WHERE pk_billtype = 'LH21';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ10000000008Q52';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'LH21';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ10000000008Q52';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008Q53';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008Q54';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008Q55';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008Q56';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008Q57';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008Q58';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008Q59';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008Q5A';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008Q5B';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008Q5C';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008Q5D';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008Q5E';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008Q51';
delete from pub_print_datasource where ctemplateid = '0001ZZ10000000008Q2G';
delete from pub_print_cell where ctemplateid = '0001ZZ10000000008Q2G';
delete from pub_print_line where ctemplateid = '0001ZZ10000000008Q2G';
delete from pub_print_variable where ctemplateid = '0001ZZ10000000008Q2G';
delete from pub_print_template where ctemplateid = '0001ZZ10000000008Q2G';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008Q2F';
delete from pub_query_condition where pk_templet = '0001ZZ10000000008Q1S';
delete from pub_query_templet where id = '0001ZZ10000000008Q1S';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008Q1R';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ10000000008OH5';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ10000000008OH5';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ10000000008OH5';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ10000000008OH4';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ10000000008OH2';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ10000000008OH3';