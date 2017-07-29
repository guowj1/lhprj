INSERT INTO bd_billtype (ts, iseditableproperty, pk_billtypecode, ncbrcode, parentbilltype, canextendtransaction, istransaction, isbizflowbill, datafinderclz, isaccount, referclassname, pk_org, isroot, billtypename, component, billcoderule, emendenumclass, dr, nodecode, isenablebutton, pk_billtypeid, systemcode, classname, checkclassname, accountclass, islock, forwardbilltype, billtypename2, billtypename3, transtype_class, billtypename4, billtypename5, billtypename6, pk_group, webnodecode, billstyle, def3, isapprovebill, isenabletranstypebcr, def2, def1, wherestring ) VALUES ('2017-07-05 13:05:18', null, 'LH22', '~', '~', 'Y', 'N', null, null, null, null, 'GLOBLE00000000000000', null, '费用汇总', 'lhdc_feecolle', '~', null, null, '20H20230', null, '0001ZZ10000000008QH1', '20H2', null, null, null, null, null, null, null, null, null, null, null, '~', '~', null, null, 'Y', null, null, null, null );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:05:36', null, null, '0001ZZ10000000008QH1', 'N', 'N', '0001ZZ10000000008QH2', null, 'SAVE', null, '送审', null, null, null, 10, 'N', '1', null, null, 'LH22' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:05:36', null, null, '0001ZZ10000000008QH1', 'N', 'N', '0001ZZ10000000008QH3', null, 'APPROVE', null, '审核', null, null, null, 11, 'N', '2', null, null, 'LH22' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:05:36', null, null, '0001ZZ10000000008QH1', 'Y', 'Y', '0001ZZ10000000008QH4', null, 'UNSAVEBILL', null, '收回', null, null, null, 13, 'N', '3', null, null, 'LH22' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:05:36', null, null, '0001ZZ10000000008QH1', 'N', 'Y', '0001ZZ10000000008QH5', null, 'UNAPPROVE', null, '弃审', null, null, null, 12, 'N', '3', null, null, 'LH22' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:05:36', null, null, '0001ZZ10000000008QH1', 'N', 'N', '0001ZZ10000000008QH6', null, 'DELETE', null, '删除', null, null, null, 30, 'N', '3', null, null, 'LH22' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:05:36', null, null, '0001ZZ10000000008QH1', 'N', 'N', '0001ZZ10000000008QH7', null, 'SAVEBASE', null, '保存', null, null, null, 31, 'Y', '1', null, null, 'LH22' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:05:36', '0001ZZ10000000008QH1', '~', 'N_LH22_SAVE', 'N', 'SAVE', '~', 0, 'LH22', '0001ZZ10000000008QH8' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:05:36', '0001ZZ10000000008QH1', '~', 'N_LH22_APPROVE', 'N', 'APPROVE', '~', 0, 'LH22', '0001ZZ10000000008QH9' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:05:36', '0001ZZ10000000008QH1', '~', 'N_LH22_UNSAVEBILL', 'N', 'UNSAVEBILL', '~', 0, 'LH22', '0001ZZ10000000008QHA' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:05:36', '0001ZZ10000000008QH1', '~', 'N_LH22_UNAPPROVE', 'N', 'UNAPPROVE', '~', 0, 'LH22', '0001ZZ10000000008QHB' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:05:36', '0001ZZ10000000008QH1', '~', 'N_LH22_DELETE', 'N', 'DELETE', '~', 0, 'LH22', '0001ZZ10000000008QHC' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:05:36', '0001ZZ10000000008QH1', '~', 'N_LH22_SAVEBASE', 'N', 'SAVEBASE', '~', 0, 'LH22', '0001ZZ10000000008QHD' );