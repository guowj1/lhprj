INSERT INTO bd_billtype (ts, iseditableproperty, pk_billtypecode, ncbrcode, parentbilltype, canextendtransaction, istransaction, isbizflowbill, datafinderclz, isaccount, referclassname, pk_org, isroot, billtypename, component, billcoderule, emendenumclass, dr, nodecode, isenablebutton, pk_billtypeid, systemcode, classname, checkclassname, accountclass, islock, forwardbilltype, billtypename2, billtypename3, transtype_class, billtypename4, billtypename5, billtypename6, pk_group, webnodecode, billstyle, def3, isapprovebill, isenabletranstypebcr, def2, def1, wherestring ) VALUES ('2017-07-05 13:03:00', null, 'LH21', '~', '~', 'Y', 'N', null, null, null, null, 'GLOBLE00000000000000', null, '立恒完工产品汇总', 'lhdc_prodcolle', '~', null, null, '20H20220', null, '0001ZZ10000000008Q52', '20H2', null, null, null, null, null, null, null, null, null, null, null, '~', '~', null, null, 'Y', null, null, null, null );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:03:19', null, null, '0001ZZ10000000008Q52', 'N', 'N', '0001ZZ10000000008Q53', null, 'SAVE', null, '送审', null, null, null, 10, 'N', '1', null, null, 'LH21' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:03:19', null, null, '0001ZZ10000000008Q52', 'N', 'N', '0001ZZ10000000008Q54', null, 'APPROVE', null, '审核', null, null, null, 11, 'N', '2', null, null, 'LH21' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:03:19', null, null, '0001ZZ10000000008Q52', 'Y', 'Y', '0001ZZ10000000008Q55', null, 'UNSAVEBILL', null, '收回', null, null, null, 13, 'N', '3', null, null, 'LH21' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:03:19', null, null, '0001ZZ10000000008Q52', 'N', 'Y', '0001ZZ10000000008Q56', null, 'UNAPPROVE', null, '弃审', null, null, null, 12, 'N', '3', null, null, 'LH21' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:03:19', null, null, '0001ZZ10000000008Q52', 'N', 'N', '0001ZZ10000000008Q57', null, 'DELETE', null, '删除', null, null, null, 30, 'N', '3', null, null, 'LH21' );
INSERT INTO pub_billaction (ts, pushflag, actionstyleremark, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, dr, pk_billtype ) VALUES ('2017-07-05 13:03:19', null, null, '0001ZZ10000000008Q52', 'N', 'N', '0001ZZ10000000008Q58', null, 'SAVEBASE', null, '保存', null, null, null, 31, 'Y', '1', null, null, 'LH21' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:03:19', '0001ZZ10000000008Q52', '~', 'N_LH21_SAVE', 'N', 'SAVE', '~', 0, 'LH21', '0001ZZ10000000008Q59' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:03:19', '0001ZZ10000000008Q52', '~', 'N_LH21_APPROVE', 'N', 'APPROVE', '~', 0, 'LH21', '0001ZZ10000000008Q5A' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:03:19', '0001ZZ10000000008Q52', '~', 'N_LH21_UNSAVEBILL', 'N', 'UNSAVEBILL', '~', 0, 'LH21', '0001ZZ10000000008Q5B' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:03:19', '0001ZZ10000000008Q52', '~', 'N_LH21_UNAPPROVE', 'N', 'UNAPPROVE', '~', 0, 'LH21', '0001ZZ10000000008Q5C' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:03:19', '0001ZZ10000000008Q52', '~', 'N_LH21_DELETE', 'N', 'DELETE', '~', 0, 'LH21', '0001ZZ10000000008Q5D' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2017-07-05 13:03:19', '0001ZZ10000000008Q52', '~', 'N_LH21_SAVEBASE', 'N', 'SAVEBASE', '~', 0, 'LH21', '0001ZZ10000000008Q5E' );