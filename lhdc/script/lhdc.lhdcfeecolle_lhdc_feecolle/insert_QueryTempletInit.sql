INSERT INTO pub_query_templet (ts, ID, MODEL_CODE, MODEL_NAME, NODE_CODE, PK_CORP, METACLASS, LAYER ) VALUES ('2017-07-05 13:05:13', '0001ZZ10000000008Q75', '20H20230', '成本中心费用汇总', '20H20230', '@@@@', 'cebef4b2-68d2-4ff7-96cf-0d4402e832c9', 0 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 0, 1, 'pk_feecolle', '0001ZZ10000000008Q76', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 1, 1, 'vbillcode', '0001ZZ10000000008Q77', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, VALUE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 3, 2, 0, 'dbilldate', '0001ZZ10000000008Q78', 'Y', 'N', 'Y', 'N', 'N', 'Y', 'N', 'N', 'Y', 'N', 'N', 'between@=@>@>=@<@<=@', '介于@等于@大于@大于等于@小于@小于等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, '#day(0)#,#day(0)#', 'Y', 'Y', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 3, 1, 'vnote', '0001ZZ10000000008Q79', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, VALUE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '财务组织', 5, 4, 1, 'pk_org', '0001ZZ10000000008Q7A', 'Y', 'N', 'Y', 'N', 'N', 'Y', 'N', 'N', 'Y', 'N', 'N', '=@', '等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, '#mainorg#', 'Y', 'Y', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '财务组织版本', 5, 5, 1, 'pk_org_v', '0001ZZ10000000008Q7B', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=', '等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '集团', 5, 6, 1, 'pk_group', '0001ZZ10000000008Q7C', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=', '等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '用户', 5, 7, 1, 'creator', '0001ZZ10000000008Q7D', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=', '等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 8, 8, 1, 'creationtime', '0001ZZ10000000008Q7E', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'between@=@>@>=@<@<=@', '介于@等于@大于@大于等于@小于@小于等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'Y', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '用户', 5, 9, 1, 'billmaker', '0001ZZ10000000008Q7F', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=', '等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '用户', 5, 10, 1, 'modifier', '0001ZZ10000000008Q7G', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=', '等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 8, 11, 1, 'modifiedtime', '0001ZZ10000000008Q7H', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'between@=@>@>=@<@<=@', '介于@等于@大于@大于等于@小于@小于等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'Y', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '用户', 5, 12, 1, 'approver', '0001ZZ10000000008Q7I', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=', '等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 8, 13, 1, 'approvetime', '0001ZZ10000000008Q7J', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'between@=@>@>=@<@<=@', '介于@等于@大于@大于等于@小于@小于等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'Y', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 14, 1, 'approvenote', '0001ZZ10000000008Q7K', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', 'IM,2ed33012-890c-4e5f-82a0-40ef0eeb4b45', 6, 15, 1, 'fbillstatus', '0001ZZ10000000008Q7L', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@<>@', '等于@不等于@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 16, 1, 'vdef1', '0001ZZ10000000008Q7M', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 17, 1, 'vdef2', '0001ZZ10000000008Q7N', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 18, 1, 'vdef3', '0001ZZ10000000008Q7O', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 19, 1, 'vdef4', '0001ZZ10000000008Q7P', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 20, 1, 'vdef5', '0001ZZ10000000008Q7Q', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_query_condition (ts, CONSULT_CODE, DATA_TYPE, DISP_SEQUENCE, DISP_TYPE, FIELD_CODE, ID, IF_AUTOCHECK, IF_DATAPOWER, IF_DEFAULT, IF_GROUP, IF_IMMOBILITY, IF_MUST, IF_ORDER, IF_SUM, IF_USED, IF_SUBINCLUDED, IF_MULTICORPREF, OPERA_CODE, OPERA_NAME, ORDER_SEQUENCE, PK_CORP, PK_TEMPLET, RETURN_TYPE, ISCONDITION, IF_SYSFUNCREFUSED, IF_ATTRREFUSED, LIMITS ) VALUES ('2017-07-05 13:05:14', '-99', 0, 21, 1, 'id_lhfeecollectdetail', '0001ZZ10000000008Q7R', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', '=@like@left like@right like@', '等于@包含@左包含@右包含@', 0, '@@@@', '0001ZZ10000000008Q75', 2, 'Y', 'N', 'N', 9999 );
INSERT INTO pub_systemplate_base (ts, nodekey, funnode, layer, moduleid, templateid, pk_systemplate, devorg, pk_country, tempstyle, pk_industry, dr ) VALUES ('2017-07-05 13:05:14', 'qt', '20H20230', 0, '20H2', '0001ZZ10000000008Q75', '0001ZZ10000000008Q7S', '00001', '~', 1, '~', 0 );
