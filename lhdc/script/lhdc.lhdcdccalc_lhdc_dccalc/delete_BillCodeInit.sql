DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ10000000008QUM';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'LH23' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'LH23';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ10000000008QUM';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ10000000008QUN';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ10000000008QUN';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008QUO';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008QUP';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008QUQ';
