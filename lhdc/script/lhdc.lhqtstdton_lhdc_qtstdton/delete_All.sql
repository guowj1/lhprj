DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ10000000008OBT';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'LH11' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'LH11';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ10000000008OBT';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ10000000008OBU';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ10000000008OBU';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008OBV';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008OBW';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008OBX';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ10000000008OBG';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ10000000008OBG';
DELETE FROM pub_function WHERE pk_billtype = 'LH11';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ10000000008OBG';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'LH11';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ10000000008OBG';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008OBH';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008OBI';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008OBJ';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008OBK';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008OBL';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ10000000008OBM';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008OBN';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008OBO';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008OBP';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008OBQ';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008OBR';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ10000000008OBS';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008OBF';
delete from pub_print_datasource where ctemplateid = '0001ZZ10000000008O9A';
delete from pub_print_cell where ctemplateid = '0001ZZ10000000008O9A';
delete from pub_print_line where ctemplateid = '0001ZZ10000000008O9A';
delete from pub_print_variable where ctemplateid = '0001ZZ10000000008O9A';
delete from pub_print_template where ctemplateid = '0001ZZ10000000008O9A';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008O99';
delete from pub_query_condition where pk_templet = '0001ZZ10000000008O8J';
delete from pub_query_templet where id = '0001ZZ10000000008O8J';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ10000000008O8I';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ10000000008O79';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ10000000008O79';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ10000000008O79';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ10000000008O78';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ10000000008O76';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ10000000008O77';