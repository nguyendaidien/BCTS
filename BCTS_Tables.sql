--
-- BCTS_JOB_HEADER  (Table) 
--
CREATE TABLE BCTS_JOB_HEADER
(
  USER_ID                VARCHAR2(17 BYTE)      NOT NULL,
  JOB_NO                 VARCHAR2(12 BYTE)      NOT NULL,
  JOB_DATE               NUMBER(12)             NOT NULL,
  JOB_SEQ_NO             NUMBER(4)              NOT NULL,
  CR_UEI_NO              VARCHAR2(20 BYTE)      NOT NULL,
  CAR_TYPE               VARCHAR2(7 BYTE)       NOT NULL,
  DEC_TYPE               VARCHAR2(7 BYTE),
  DEC_STATUS             VARCHAR2(1 BYTE),
  MSG_STATUS             VARCHAR2(1 BYTE),
  UPDATE_PERMIT_NO       VARCHAR2(11 BYTE),
  UPDATE_CERT_NO         VARCHAR2(17 BYTE),
  CREATE_DATE            DATE,
  LASTMODIFIED_DATE      DATE,
  SUBMIT_DATE            DATE,
  RECV_DATE              DATE,
  APPROVED_DATE          DATE,
  MSG_REFERENCE          VARCHAR2(14 BYTE),
  INTERCHANGE            VARCHAR2(14 BYTE),
  UPDATE_IND_CODE        VARCHAR2(3 BYTE),
  UPDATE_COUNT           NUMBER(2),
  REPL_PERMIT_NO         VARCHAR2(11 BYTE),
  CANCEL_REASON_CODE     VARCHAR2(3 BYTE),
  CO_IND                 VARCHAR2(1 BYTE),
  LIC_IND                VARCHAR2(1 BYTE),
  BG_TYPE                VARCHAR2(3 BYTE),
  PREV_PERMIT_NO         VARCHAR2(35 BYTE),
  ADD_RECIPIENT_MBX1     VARCHAR2(17 BYTE),
  ADD_RECIPIENT_MBX2     VARCHAR2(17 BYTE),
  ADD_RECIPIENT_MBX3     VARCHAR2(17 BYTE),
  LAST_USER_ID           VARCHAR2(17 BYTE),
  LAST_JOB_NO            VARCHAR2(12 BYTE),
  CANCELLED_IND          VARCHAR2(1 BYTE),
  RESPONSE_TYPE          VARCHAR2(15 BYTE),
  CARGO_PACK_TYPE        VARCHAR2(3 BYTE),
  IN_TRANS_MODE          VARCHAR2(1 BYTE),
  OUT_TRANS_MODE         VARCHAR2(1 BYTE),
  SUPPLY_IND             VARCHAR2(1 BYTE),
  ISSUING_AUTHORITY_ID   VARCHAR2(17 BYTE),
  FEE_MSG_IND            VARCHAR2(1 BYTE),
  CANCEL_REASON_DESC     VARCHAR2(70 BYTE),
  USER_REF_NO            VARCHAR2(35 BYTE),
  PERMIT_NO              VARCHAR2(11 BYTE),
  ARCHIVE_DATE           DATE,
  INCOMPLETE_HEADER_IND  VARCHAR2(1 BYTE)       DEFAULT '0'                   NOT NULL,
  INS_SEND_IND           VARCHAR2(1 BYTE)       DEFAULT '0'                   NOT NULL,
  SUBMISSION_DATETIME    DATE,
  TN_VERSION             VARCHAR2(3 BYTE)       DEFAULT '4.0'                 NOT NULL,
  SOURCE 				 VARCHAR2(5 BYTE),
  CONSTRAINT PK_BCTS_JOB_HEADER
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE
);

--
-- BCTS_LICENCE  (Table) 
--
CREATE TABLE BCTS_LICENCE
(
  USER_ID       VARCHAR2(17 BYTE)               NOT NULL,
  JOB_NO        VARCHAR2(12 BYTE)               NOT NULL,
  LICENCE_NO_1  VARCHAR2(35 BYTE),
  LICENCE_NO_2  VARCHAR2(35 BYTE),
  LICENCE_NO_3  VARCHAR2(35 BYTE),
  LICENCE_NO_4  VARCHAR2(35 BYTE),
  LICENCE_NO_5  VARCHAR2(35 BYTE),
  CONSTRAINT PK_BCTS_LICENCE
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_LICENCE 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_OUT_TRANSPORT  (Table) 
--
CREATE TABLE BCTS_OUT_TRANSPORT
(
  USER_ID              VARCHAR2(17 BYTE)        NOT NULL,
  JOB_NO               VARCHAR2(12 BYTE)        NOT NULL,
  TRANS_MODE_CODE      VARCHAR2(1 BYTE),
  VOYAGE_NO            VARCHAR2(17 BYTE),
  VES_NAME             VARCHAR2(35 BYTE),
  AIRCRAFT_ID          VARCHAR2(17 BYTE),
  MASTER_SHIPDOC_NO    VARCHAR2(35 BYTE),
  VES_TYPE             VARCHAR2(2 BYTE),
  VES_NATION           VARCHAR2(2 BYTE),
  VES_NATION_DESC      VARCHAR2(70 BYTE),
  TOW_VOY_NO           VARCHAR2(17 BYTE),
  TOW_VES_NAME         VARCHAR2(35 BYTE),
  NEXT_PORT_CODE       VARCHAR2(5 BYTE),
  NEXT_PORT_DESC       VARCHAR2(70 BYTE),
  FINAL_PORT_CODE      VARCHAR2(5 BYTE),
  FINAL_PORT_DESC      VARCHAR2(70 BYTE),
  DEPART_DATE          DATE,
  DISCHARGE_PORT_CODE  VARCHAR2(5 BYTE),
  DISCHARGE_PORT_DESC  VARCHAR2(70 BYTE),
  FINAL_CTRY_CODE      VARCHAR2(2 BYTE),
  FINAL_CTRY_DESC      VARCHAR2(70 BYTE),
  VES_LOC_CODE         VARCHAR2(6 BYTE),
  VES_LOC_NAME         VARCHAR2(70 BYTE),
  SEA_STORE_IND        VARCHAR2(1 BYTE),
  TRSPT_IDENTIFIER     VARCHAR2(35 BYTE),
  NRT                  NUMBER(15,3),
  CONSTRAINT PK_BCTS_OUT_TRANSPORT
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_OUT_TRANSPORT 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_PARTY  (Table) 
--
CREATE TABLE BCTS_PARTY
(
  USER_ID           VARCHAR2(17 BYTE)           NOT NULL,
  JOB_NO            VARCHAR2(12 BYTE)           NOT NULL,
  PARTY_IND         VARCHAR2(2 BYTE)            NOT NULL,
  CR_UEI_NO         VARCHAR2(20 BYTE),
  NAME1             VARCHAR2(50 BYTE),
  NAME2             VARCHAR2(50 BYTE),
  NAME3             VARCHAR2(35 BYTE),
  ADDR1             VARCHAR2(35 BYTE),
  ADDR2             VARCHAR2(35 BYTE),
  ADDR3             VARCHAR2(35 BYTE),
  PERSON_NAME1      VARCHAR2(100 BYTE),
  PERSON_NAME2      VARCHAR2(35 BYTE),
  PERSON_NAME3      VARCHAR2(35 BYTE),
  PERSON_ID         VARCHAR2(20 BYTE),
  CONTACT_NO        VARCHAR2(25 BYTE),
  COUNTRYCODE       VARCHAR2(2 BYTE),
  POSTALCODE        VARCHAR2(9 BYTE),
  CTYSUBENTITYNAME  VARCHAR2(35 BYTE),
  CTYSUBENTITYCODE  VARCHAR2(9 BYTE),
  CITYNAME          VARCHAR2(35 BYTE),
  CONSTRAINT PK_BCTS_PARTY
  PRIMARY KEY
  (USER_ID, JOB_NO, PARTY_IND)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_PARTY 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_PERMIT  (Table) 
--
CREATE TABLE BCTS_PERMIT
(
  USER_ID                VARCHAR2(17 BYTE)      NOT NULL,
  JOB_NO                 VARCHAR2(12 BYTE)      NOT NULL,
  PERMIT_NO              VARCHAR2(11 BYTE),
  CERT_NO                VARCHAR2(17 BYTE),
  APPROVE_DATE           DATE,
  ORIG_APPROVE_DATE      DATE,
  VALIDITY_START_DATE    DATE,
  VALIDITY_END_DATE      DATE,
  CA_APPROVE_DATETIME    DATE,
  APPROVE_DATETIME       DATE,
  ORIG_APPROVE_DATETIME  DATE,
  CONSTRAINT PK_BCTS_PERMIT
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_PERMIT 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_SUMMARY  (Table) 
--
CREATE TABLE BCTS_SUMMARY
(
  USER_ID                       VARCHAR2(17 BYTE) NOT NULL,
  JOB_NO                        VARCHAR2(12 BYTE) NOT NULL,
  TOT_CIF_FOB                   NUMBER(15,2),
  TOT_OUT_PACK                  NUMBER(15,4),
  TOT_OUT_PACK_UOM              VARCHAR2(3 BYTE),
  TOT_GROSS_WT                  NUMBER(15,4),
  TOT_GROSS_WT_UOM              VARCHAR2(3 BYTE),
  TOT_GST_AMT                   NUMBER(15,2),
  TOT_EXCISE_AMT                NUMBER(15,2),
  TOT_CUSTOMS_AMT               NUMBER(15,2),
  TOT_AMT_PAYABLE               NUMBER(15,2),
  TOT_REFUND_GST_AMT            NUMBER(15,2),
  TOT_REFUND_EXCISE_AMT         NUMBER(15,2),
  TOT_REFUND_CUSTOMS_AMT        NUMBER(15,2),
  DEC_STMT_IND                  VARCHAR2(1 BYTE),
  CO_STMT_IND                   VARCHAR2(1 BYTE),
  REMARKS                       VARCHAR2(2560 BYTE),
  TOT_OTHER_TAX_DUTY_AMT        NUMBER(15,2),
  TOT_REFUND_OTHERTAX_DUTY_AMT  NUMBER(15,2),
  NO_OF_ITEMS                   NUMBER(5),
  CONSTRAINT PK_BCTS_SUMMARY
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_SUMMARY 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_SUPPORT_DOC  (Table) 
--
CREATE TABLE BCTS_SUPPORT_DOC
(
  USER_ID      VARCHAR2(17 BYTE)                NOT NULL,
  JOB_NO       VARCHAR2(12 BYTE)                NOT NULL,
  SEQ_NO       NUMBER(2)                        NOT NULL,
  DOC_ID_TYPE  VARCHAR2(35 BYTE)                NOT NULL,
  FILENAME     VARCHAR2(140 BYTE)               NOT NULL,
  CONSTRAINT PK_BCTS_SUPPORT_DOC
  PRIMARY KEY
  (USER_ID, JOB_NO, SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_SUPPORT_DOC 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_UPDATE_SUM  (Table) 
--
CREATE TABLE BCTS_UPDATE_SUM
(
  USER_ID      VARCHAR2(17 BYTE)                NOT NULL,
  JOB_NO       VARCHAR2(12 BYTE)                NOT NULL,
  SEQ_NO       NUMBER(4)                        NOT NULL,
  UPDATE_CODE  VARCHAR2(12 BYTE)                NOT NULL,
  UPDATE_TYPE  VARCHAR2(2 BYTE)                 NOT NULL,
  UPDATE_DESC  VARCHAR2(200 BYTE),
  CONSTRAINT PK_BCTS_UPDATE_SUM
  PRIMARY KEY
  (USER_ID, JOB_NO, SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_UPDATE_SUM 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

Commit;

--
-- ID2BCTS_JOB_HEADER  (Index) 
--
--CREATE INDEX ID2BCTS_JOB_HEADER ON BCTS_JOB_HEADER
--(USER_ID, CR_UEI_NO, CREATE_DATE);

--
-- ID3BCTS_JOB_HEADER  (Index) 
--
--CREATE INDEX ID3BCTS_JOB_HEADER ON BCTS_JOB_HEADER
--(CR_UEI_NO, JOB_DATE, USER_ID);

--
-- ID4BCTS_JOB_HEADER  (Index) 
--
--CREATE INDEX ID4BCTS_JOB_HEADER ON BCTS_JOB_HEADER
--(CR_UEI_NO, CREATE_DATE, USER_ID);

--
-- ID5BCTS_JOB_HEADER  (Index) 
--
--CREATE INDEX ID5BCTS_JOB_HEADER ON BCTS_JOB_HEADER
--(CR_UEI_NO, PERMIT_NO);

--
-- IDBCTS_JOB_HEADER  (Index) 
--
--CREATE INDEX IDBCTS_JOB_HEADER ON BCTS_JOB_HEADER
--(CR_UEI_NO, JOB_NO);

--
-- IBCTS_JOB_HEADER  (Index) 
--
--CREATE INDEX IBCTS_JOB_HEADER ON BCTS_JOB_HEADER
--(CR_UEI_NO);

--
-- JH_CREATE_DATE  (Index) 
--
--CREATE INDEX JH_CREATE_DATE ON BCTS_JOB_HEADER
--(CREATE_DATE);

--
-- JH_PERMIT_NO  (Index) 
--
--CREATE INDEX JH_PERMIT_NO ON BCTS_JOB_HEADER
--(PERMIT_NO);

--
-- JH_USER_REF_NO  (Index) 
--
--CREATE INDEX JH_USER_REF_NO ON BCTS_JOB_HEADER
--(USER_REF_NO);

--Commit;

--
-- BCTS_AME_INFO  (Table) 
--
CREATE TABLE BCTS_AME_INFO
(
  USER_ID                  VARCHAR2(17 BYTE)    NOT NULL,
  JOB_NO                   VARCHAR2(12 BYTE)    NOT NULL,
  PERMIT_VALIDITY_EXT_IND  VARCHAR2(1 BYTE),
  AMENDED_FIELDS_COUNT     NUMBER(2),
  AME_REASON               VARCHAR2(280 BYTE),
  EXT_REASON               VARCHAR2(280 BYTE),
  CONSTRAINT PK_BCTS_AME_INFO
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_AME_INFO 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_APPROVAL_COND  (Table) 
--
CREATE TABLE BCTS_APPROVAL_COND
(
  USER_ID          VARCHAR2(17 BYTE)            NOT NULL,
  JOB_NO           VARCHAR2(12 BYTE)            NOT NULL,
  APPROVAL_TYPE    VARCHAR2(10 BYTE)            NOT NULL,
  SEQ_NO           NUMBER(2)                    NOT NULL,
  AGENCY_CODE      VARCHAR2(2 BYTE),
  CONDITION_DESC1  VARCHAR2(512 BYTE)           NOT NULL,
  CONDITION_DESC2  VARCHAR2(60 BYTE),
  CONDITION_DESC3  VARCHAR2(60 BYTE),
  CONDITION_DESC4  VARCHAR2(60 BYTE),
  CONDITION_CODE   VARCHAR2(4 BYTE),
  CONSTRAINT PK_BCTS_APPROVAL_COND
  PRIMARY KEY
  (USER_ID, JOB_NO, APPROVAL_TYPE, SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_APPROVAL_COND 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_PERMIT (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_CARGO_HEADER  (Table) 
--
CREATE TABLE BCTS_CARGO_HEADER
(
  USER_ID                VARCHAR2(17 BYTE)      NOT NULL,
  JOB_NO                 VARCHAR2(12 BYTE)      NOT NULL,
  IMP_END_DATE           DATE,
  REM_START_DATE         DATE,
  RELEASE_LOC_CODE       VARCHAR2(7 BYTE),
  RELEASE_LOC_NAME       VARCHAR2(256 BYTE),
  RECEIPT_LOC_CODE       VARCHAR2(7 BYTE),
  RECEIPT_LOC_NAME       VARCHAR2(256 BYTE),
  STORAGE_LOC_CODE       VARCHAR2(7 BYTE),
  STORAGE_LOC_NAME       VARCHAR2(256 BYTE),
  IMP_BKTREM_START_DATE  DATE,
  MAX_AME_CTN_SEQ        NUMBER(5),
  CONSTRAINT PK_BCTS_CARGO_HEADER
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_CARGO_HEADER 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_CERT_HEADER  (Table) 
--
CREATE TABLE BCTS_CERT_HEADER
(
  USER_ID              VARCHAR2(17 BYTE)        NOT NULL,
  JOB_NO               VARCHAR2(12 BYTE)        NOT NULL,
  APPL_PROD_TYPE       VARCHAR2(2 BYTE),
  CERT1_TYPE           VARCHAR2(2 BYTE),
  CERT1_COPIES         NUMBER(2),
  CERT2_TYPE           VARCHAR2(2 BYTE),
  CERT2_COPIES         NUMBER(2),
  ENTRY_YEAR           NUMBER(4),
  GSP_DONOR_CTRY_CODE  VARCHAR2(2 BYTE),
  PREF_PERCENT         NUMBER(3),
  CURR_CODE            VARCHAR2(3 BYTE),
  CERT_DETAIL          VARCHAR2(175 BYTE),
  TRANS_DETAIL         VARCHAR2(175 BYTE),
  GSP_DONOR_CTRY_DESC  VARCHAR2(70 BYTE),
  CONSTRAINT PK_BCTS_CERT_HEADER
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_CERT_HEADER 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_INVOICE  (Table) 
--
CREATE TABLE BCTS_INVOICE
(
  USER_ID               VARCHAR2(17 BYTE)       NOT NULL,
  JOB_NO                VARCHAR2(12 BYTE)       NOT NULL,
  INV_SEQ_NO            NUMBER(2)               NOT NULL,
  INV_NO                VARCHAR2(35 BYTE),
  UNIT_PRICE_TERM_TYPE  VARCHAR2(3 BYTE),
  INV_DATE              DATE,
  TOT_INV_AMT           NUMBER(15,2),
  TOT_INV_CURR_TYPE     VARCHAR2(3 BYTE),
  TOT_INV_EXC_RATE      NUMBER(10,6),
  AD_VALOREM_IND        VARCHAR2(1 BYTE),
  SI_RELATION           VARCHAR2(2 BYTE),
  PREF_DUTY_IND         VARCHAR2(1 BYTE),
  PARTY_CODE            VARCHAR2(20 BYTE),
  PARTY_NAME1           VARCHAR2(50 BYTE),
  PARTY_NAME2           VARCHAR2(50 BYTE),
  PARTY_NAME3           VARCHAR2(35 BYTE),
  FRG_CHRG_AMT          NUMBER(15,2),
  FRG_CHRG_CURR_CODE    VARCHAR2(3 BYTE),
  FRG_CHRG_EXC_RATE     NUMBER(10,6),
  FRG_CHRG_PERCENT      NUMBER(6,3),
  INS_CHRG_AMT          NUMBER(15,2),
  INS_CHRG_CURR_CODE    VARCHAR2(3 BYTE),
  INS_CHRG_EXC_RATE     NUMBER(10,6),
  INS_CHRG_PERCENT      NUMBER(6,3),
  OTH_CHRG_AMT          NUMBER(15,2),
  OTH_CHRG_CURR_CODE    VARCHAR2(3 BYTE),
  OTH_CHRG_EXC_RATE     NUMBER(10,6),
  OTH_CHRG_PERCENT      NUMBER(6,3),
  CONSTRAINT PK_BCTS_INVOICE
  PRIMARY KEY
  (USER_ID, JOB_NO, INV_SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_INVOICE 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_IN_TRANSPORT  (Table) 
--
CREATE TABLE BCTS_IN_TRANSPORT
(
  USER_ID            VARCHAR2(17 BYTE)          NOT NULL,
  JOB_NO             VARCHAR2(12 BYTE)          NOT NULL,
  TRANS_MODE_CODE    VARCHAR2(1 BYTE),
  VOYAGE_NO          VARCHAR2(17 BYTE),
  VES_NAME           VARCHAR2(35 BYTE),
  AIRCRAFT_ID        VARCHAR2(17 BYTE),
  MASTER_SHIPDOC_NO  VARCHAR2(35 BYTE),
  VES_LOC_CODE       VARCHAR2(6 BYTE),
  VES_LOC_NAME       VARCHAR2(70 BYTE),
  ARRIVAL_DATE       DATE,
  LOADING_PORT_CODE  VARCHAR2(5 BYTE),
  LOADING_PORT_DESC  VARCHAR2(70 BYTE),
  TRSPT_IDENTIFIER   VARCHAR2(35 BYTE),
  CONSTRAINT PK_BCTS_IN_TRANSPORT
  PRIMARY KEY
  (USER_ID, JOB_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_IN_TRANSPORT 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_ITEM  (Table) 
--
CREATE TABLE BCTS_ITEM
(
  USER_ID                        VARCHAR2(17 BYTE) NOT NULL,
  JOB_NO                         VARCHAR2(12 BYTE) NOT NULL,
  ITEM_SEQ_NO                    NUMBER(4)      NOT NULL,
  HS_CODE                        VARCHAR2(10 BYTE),
  ITEM_QTY                       NUMBER(15,4),
  ITEM_UOM_TYPE                  VARCHAR2(3 BYTE),
  TOT_DUT_QTY                    NUMBER(15,4),
  TOT_DUT_UOM_TYPE               VARCHAR2(3 BYTE),
  DUT_QTY                        NUMBER(15,4),
  DUT_UOM_TYPE                   VARCHAR2(3 BYTE),
  ALCOHOL_PERCENT                NUMBER(6,3),
  PERCENT_UOM_TYPE               VARCHAR2(3 BYTE),
  CTRY_ORIGIN_TYPE               VARCHAR2(2 BYTE),
  ITEM_CIFFOB_AMT                NUMBER(15,2),
  ITEM_LSP_AMT                   NUMBER(15,2),
  ITEM_UNIT_PRICE                NUMBER(15,4),
  UNIT_PRICE_CURR                VARCHAR2(3 BYTE),
  UNIT_PRICE_EXCRATE             NUMBER(10,6),
  OPT_CHARGE                     NUMBER(15,2),
  OPT_CHARGE_CURR_TYPE           VARCHAR2(3 BYTE),
  OPT_CHARGE_EXCRATE             NUMBER(10,6),
  BRAND                          VARCHAR2(35 BYTE),
  MODEL_DESC                     VARCHAR2(35 BYTE),
  DG_IND                         VARCHAR2(1 BYTE),
  CURR_LOT_NO                    VARCHAR2(30 BYTE),
  PREV_LOT_NO                    VARCHAR2(30 BYTE),
  LOT_MARKING                    VARCHAR2(2 BYTE),
  IN_HOUSE_SHIPDOC_NO            VARCHAR2(35 BYTE),
  OUT_HOUSE_SHIPDOC_NO           VARCHAR2(35 BYTE),
  IN_MASTER_SHIPDOC_NO           VARCHAR2(35 BYTE),
  OUT_MASTER_SHIPDOC_NO          VARCHAR2(35 BYTE),
  ITEM_INV_NO                    VARCHAR2(35 BYTE),
  PREF_CODE_TYPE                 VARCHAR2(3 BYTE),
  GST_RATE                       NUMBER(1,2),
  GST_AMT                        NUMBER(15,2),
  EXCISE_DUTY_RATE               NUMBER(7,4),
  EXCISE_DUTY_UOM                VARCHAR2(3 BYTE),
  EXCISE_DUTY_AMT                NUMBER(15,2),
  CUSTOMS_DUTY_RATE              NUMBER(7,4),
  CUSTOMS_DUTY_UOM               VARCHAR2(3 BYTE),
  CUSTOMS_DUTY_AMT               NUMBER(15,2),
  HS_TYPE                        VARCHAR2(1 BYTE),
  ITEM_DESC                      VARCHAR2(1024 BYTE),
  SHIPPING_MARKS                 VARCHAR2(170 BYTE),
  CTRY_ORIGIN_DESC               VARCHAR2(70 BYTE),
  DUTY_RATE_TYPE                 VARCHAR2(1 BYTE),
  REFUND_GST_AMT                 NUMBER(15,2),
  REFUND_EXCISE_AMT              NUMBER(15,2),
  REFUND_CUSTOMS_AMT             NUMBER(15,2),
  CONTROL_IND                    VARCHAR2(1 BYTE),
  OTHER_TAX_DUTY_RATE            NUMBER(7,4),
  OTHER_TAX_DUTY_UOM             VARCHAR2(3 BYTE),
  OTHER_TAX_DUTY_AMT             NUMBER(15,2),
  REFUND_OTHER_TAX_DUTY_AMT      NUMBER(15,2),
  SHIPPING_MARKS2                VARCHAR2(170 BYTE),
  SHIPPING_MARKS3                VARCHAR2(136 BYTE),
  SHIPPING_MARKS4                VARCHAR2(36 BYTE),
  ITEM_CIFFOB_FC_AMOUNT          NUMBER(15,2),
  ITEM_CIFFOB_FC_CODE            VARCHAR2(3 BYTE),
  ITEM_CIFFOB_FC_ORIGINAL_XRATE  NUMBER(10,6),
  ITEM_CIFFOB_CURR               VARCHAR2(3 BYTE),
  ITEM_CIFFOB_EXCRATE            NUMBER(10,6),
  ITEM_CIFFOB_UNIT               NUMBER(10,4),
  ITEM_CIFFOB_PRICE              NUMBER(17,5),
  CONSTRAINT PK_BCTS_ITEM
  PRIMARY KEY
  (USER_ID, JOB_NO, ITEM_SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_ITEM 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_JOB_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_ITEM_CASC  (Table) 
--
CREATE TABLE BCTS_ITEM_CASC
(
  USER_ID              VARCHAR2(17 BYTE)        NOT NULL,
  JOB_NO               VARCHAR2(12 BYTE)        NOT NULL,
  ITEM_SEQ_NO          NUMBER(4)                NOT NULL,
  CASC_SEQ_NO          NUMBER(1)                NOT NULL,
  PROD_CODE            VARCHAR2(35 BYTE),
  PROD_QTY             NUMBER(15,4),
  PROD_QTY_UOM_TYPE    VARCHAR2(3 BYTE),
  END_USE_DESCRIPTION  VARCHAR2(256 BYTE),
  CONSTRAINT PK_BCTS_ITEM_CASC
  PRIMARY KEY
  (USER_ID, JOB_NO, ITEM_SEQ_NO, CASC_SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_ITEM_CASC 
  FOREIGN KEY (USER_ID, JOB_NO, ITEM_SEQ_NO) 
  REFERENCES BCTS_ITEM (USER_ID,JOB_NO,ITEM_SEQ_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_ITEM_CASC_CASCID  (Table) 
--
CREATE TABLE BCTS_ITEM_CASC_CASCID
(
  USER_ID        VARCHAR2(17 BYTE)              NOT NULL,
  JOB_NO         VARCHAR2(12 BYTE)              NOT NULL,
  ITEM_SEQ_NO    NUMBER(4)                      NOT NULL,
  CASC_SEQ_NO    NUMBER(1)                      NOT NULL,
  CASC_ID_SEQNO  NUMBER(2)                      NOT NULL,
  CASC_CODE1     VARCHAR2(35 BYTE),
  CASC_CODE2     VARCHAR2(35 BYTE),
  CASC_CODE3     VARCHAR2(35 BYTE),
  END_USE_DESC1  VARCHAR2(35 BYTE),
  END_USE_DESC2  VARCHAR2(35 BYTE),
  END_USE_DESC3  VARCHAR2(35 BYTE),
  CONSTRAINT PK_BCTS_ITEM_CASC_CASCID
  PRIMARY KEY
  (USER_ID, JOB_NO, ITEM_SEQ_NO, CASC_SEQ_NO, CASC_ID_SEQNO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_ITEM_CASC_CASCID 
  FOREIGN KEY (USER_ID, JOB_NO, ITEM_SEQ_NO, CASC_SEQ_NO) 
  REFERENCES BCTS_ITEM_CASC (USER_ID,JOB_NO,ITEM_SEQ_NO,CASC_SEQ_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_ITEM_CERT  (Table) 
--
CREATE TABLE BCTS_ITEM_CERT
(
  USER_ID                VARCHAR2(17 BYTE)      NOT NULL,
  JOB_NO                 VARCHAR2(12 BYTE)      NOT NULL,
  ITEM_SEQ_NO            NUMBER(4)              NOT NULL,
  CERT_ITEM_QTY          NUMBER(15,4),
  CERT_ITEM_UOM_TYPE     VARCHAR2(3 BYTE),
  CERT_ITEM_VALUE        NUMBER(15,2),
  MFR_COST_DATE          DATE,
  TX_CAT_CODE            VARCHAR2(5 BYTE),
  TX_QUOTA_QTY           NUMBER(15,4),
  TX_QUOTA_QTY_UOM_TYPE  VARCHAR2(3 BYTE),
  CERT_INV_NO            VARCHAR2(35 BYTE),
  CERT_INV_DATE          DATE,
  CERT_CRITERION1        VARCHAR2(35 BYTE),
  CERT_CRITERION2        VARCHAR2(35 BYTE),
  CERT_CRITERION3        VARCHAR2(35 BYTE),
  CERT_HS_CODE           VARCHAR2(10 BYTE),
  ORIGIN_PERCENT         NUMBER(3),
  CERT_ITEM_DESC         VARCHAR2(1750 BYTE),
  TX_CAT_DESC            VARCHAR2(70 BYTE),
  CONSTRAINT PK_BCTS_ITEM_CERT
  PRIMARY KEY
  (USER_ID, JOB_NO, ITEM_SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_ITEM_CERT 
  FOREIGN KEY (USER_ID, JOB_NO, ITEM_SEQ_NO) 
  REFERENCES BCTS_ITEM (USER_ID,JOB_NO,ITEM_SEQ_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_ITEM_PACK_DESC  (Table) 
--
CREATE TABLE BCTS_ITEM_PACK_DESC
(
  USER_ID               VARCHAR2(17 BYTE)       NOT NULL,
  JOB_NO                VARCHAR2(12 BYTE)       NOT NULL,
  ITEM_SEQ_NO           NUMBER(4)               NOT NULL,
  OUT_PACK_QTY          NUMBER(8),
  OUT_PACK_UOM_TYPE     VARCHAR2(3 BYTE),
  IN_PACK_QTY           NUMBER(8),
  IN_PACK_UOM_TYPE      VARCHAR2(3 BYTE),
  INNER_PACK_QTY        NUMBER(8),
  INNER_PACK_UOM_TYPE   VARCHAR2(3 BYTE),
  INMOST_PACK_QTY       NUMBER(8),
  INMOST_PACK_UOM_TYPE  VARCHAR2(3 BYTE),
  CONSTRAINT PK_BCTS_ITEM_PACK_DESC
  PRIMARY KEY
  (USER_ID, JOB_NO, ITEM_SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_ITEM_PACK_DESC 
  FOREIGN KEY (USER_ID, JOB_NO, ITEM_SEQ_NO) 
  REFERENCES BCTS_ITEM (USER_ID,JOB_NO,ITEM_SEQ_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

--
-- BCTS_ITEM_VEHICLE  (Table) 
--
CREATE TABLE BCTS_ITEM_VEHICLE
(
  USER_ID          VARCHAR2(17 BYTE)            NOT NULL,
  JOB_NO           VARCHAR2(12 BYTE)            NOT NULL,
  ITEM_SEQ_NO      NUMBER(4)                    NOT NULL,
  ENGINE_CAP       NUMBER(6,2),
  ENGINE_UOM_TYPE  VARCHAR2(2 BYTE),
  VEH_REG_NO       VARCHAR2(17 BYTE),
  ORIG_REG_DATE    DATE,
  VEHICLE_TYPE     VARCHAR2(1 BYTE),
  CONSTRAINT PK_BCTS_ITEM_VEHICLE
  PRIMARY KEY
  (USER_ID, JOB_NO, ITEM_SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_ITEM_VEHICLE 
  FOREIGN KEY (USER_ID, JOB_NO, ITEM_SEQ_NO) 
  REFERENCES BCTS_ITEM (USER_ID,JOB_NO,ITEM_SEQ_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

Commit;

--
-- BCTS_CARGO_CTN  (Table) 
--
CREATE TABLE BCTS_CARGO_CTN
(
  USER_ID        VARCHAR2(17 BYTE)              NOT NULL,
  JOB_NO         VARCHAR2(12 BYTE)              NOT NULL,
  CTN_NO         VARCHAR2(13 BYTE),
  CTN_SIZE_TYPE  VARCHAR2(5 BYTE),
  CTN_WT         NUMBER(3),
  CTN_SEAL_NO    VARCHAR2(35 BYTE),
  SEQ_NO         NUMBER(5),
  CONSTRAINT BCTS_CARGO_CTN_PK
  PRIMARY KEY
  (USER_ID, JOB_NO, SEQ_NO)
  ENABLE VALIDATE,
  CONSTRAINT FK_BCTS_CARGO_CTN 
  FOREIGN KEY (USER_ID, JOB_NO) 
  REFERENCES BCTS_CARGO_HEADER (USER_ID,JOB_NO)
  ON DELETE CASCADE
  ENABLE VALIDATE
);

Commit;

CREATE TABLE BCTS_COMPANY
(
  CR_UEI_NO         VARCHAR2(20 BYTE) NOT NULL,
  ALERT_EMAILS		  VARCHAR2(300 BYTE),
  NAME1             VARCHAR2(50 BYTE),
  NAME2             VARCHAR2(50 BYTE),
  NAME3             VARCHAR2(35 BYTE),
  ADDR1             VARCHAR2(35 BYTE),
  ADDR2             VARCHAR2(35 BYTE),
  ADDR3             VARCHAR2(35 BYTE),
  PERSON_NAME1      VARCHAR2(100 BYTE),
  PERSON_NAME2      VARCHAR2(35 BYTE),
  PERSON_NAME3      VARCHAR2(35 BYTE),
  PERSON_ID         VARCHAR2(20 BYTE),
  CONTACT_NO        VARCHAR2(25 BYTE),
  COUNTRYCODE       VARCHAR2(2 BYTE),
  POSTALCODE        VARCHAR2(9 BYTE),
  CTYSUBENTITYNAME  VARCHAR2(35 BYTE),
  CTYSUBENTITYCODE  VARCHAR2(9 BYTE),
  CITYNAME          VARCHAR2(35 BYTE),
  CONSTRAINT PK_BCTS_COMPANY
  PRIMARY KEY
  (CR_UEI_NO)
  ENABLE VALIDATE
);

DROP TABLE BCTS_ALERT;
CREATE TABLE BCTS_ALERT
(
	CASE_ID 			NUMBER NOT NULL,
	CASE_TYPE 			VARCHAR2(30 BYTE) NOT NULL, -- PERMIT CONDITIONS|LICENCE VALIDITY|CASE MANAGEMENT
	USER_ID				VARCHAR2(17 BYTE), --UEN of declarant
	JOB_NO				VARCHAR2(12 BYTE),
	PERMIT_NO           VARCHAR2(11 BYTE),
	ALERT_CONTENT		VARCHAR2(2560 BYTE), -- JSON format for PC type, Text for others
	STATUS				VARCHAR2(1 BYTE), -- O|C
  ALERT_EMAILS		  VARCHAR2(300 BYTE),
  TO_ALERT_COMPANY  NUMBER(1)       DEFAULT '1',
	REMINDER_DATE		DATE,
	OPEN_DATE			DATE,
	COMPLETED_DATE		DATE,
	OPEN_BY				VARCHAR2(17 BYTE),
	COMPLETED_BY		VARCHAR2(17 BYTE),		
	LICENCE_NO			VARCHAR2(35 BYTE),
	LICENCE_START_DATE  DATE,
  LICENCE_END_DATE    DATE,
  CONSTRAINT BCTS_ALERT_PK
	PRIMARY KEY (CASE_ID)
	ENABLE VALIDATE
);
Commit;

DROP TABLE BCTS_CASE_COMMENT;
CREATE TABLE BCTS_CASE_COMMENT
(
	COMMENT_ID 		NUMBER NOT NULL,
	CASE_ID 		NUMBER NOT NULL,
	USER_ID			VARCHAR2(17 BYTE) NOT NULL,
	UPDATE_TIME		DATE,
	COMMENT_CONTENT	VARCHAR2(2560 BYTE),
	CONSTRAINT BCTS_CASE_COMMENT_PK
	PRIMARY KEY (COMMENT_ID)
	ENABLE VALIDATE,
	CONSTRAINT FK_BCTS_CASE_COMMENT 
	FOREIGN KEY (CASE_ID) 
	REFERENCES BCTS_ALERT (CASE_ID)
	ON DELETE CASCADE
	ENABLE VALIDATE
);
Commit;
--
/*All User's gets stored in APP_USER table*/
create table BCTS_APP_USER (
   TRANSACTION_ID NUMBER NOT NULL,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   CR_UEI_NO VARCHAR2(20 BYTE) NOT NULL,
   ACCT_LOCKED NUMBER(1) DEFAULT 1,
   USER_ENABLED NUMBER(1) DEFAULT 1,
   ACCT_EXPIRED NUMBER(1) DEFAULT 1,
   CREDENTIAL_EXPIRED NUMBER(1) DEFAULT 1,
   CREATED_DATE DATE,
   PWD_UPDATED DATE,
   PWD_EXPIRED_DATE DATE,
   PRIMARY KEY (TRANSACTION_ID),
   UNIQUE (sso_id),
   CONSTRAINT FK_BCTS_COMPANY 
    FOREIGN KEY (CR_UEI_NO) 
    REFERENCES BCTS_COMPANY (CR_UEI_NO)
    ON DELETE CASCADE
    ENABLE VALIDATE
);
   
/* USER_PROFILE table contains all possible roles */ 
create table BCTS_USER_PROFILE(
   TRANSACTION_ID NUMBER NOT NULL,
   ROLE_TYPE VARCHAR(30) NOT NULL,
   PRIMARY KEY (TRANSACTION_ID),
   UNIQUE (ROLE_TYPE)
);
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE BCTS_APP_USER_PROFILE (
    user_id NUMBER NOT NULL,
    user_profile_id NUMBER NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES BCTS_APP_USER (TRANSACTION_ID),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES BCTS_USER_PROFILE (TRANSACTION_ID)
);

CREATE TABLE BCTS_USER_ATTEMPTS (
  USER_ID varchar(45) PRIMARY KEY,
  NO_OF_ATTEMPTS number(10) DEFAULT 0,
  LAST_MODIFIED date NOT NULL
); 
 
CREATE SEQUENCE ATMPT_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

--SYNONYMS for All tables
--
CREATE PUBLIC SYNONYM BCTS_AME_INFO for BCTS_AME_INFO;
CREATE PUBLIC SYNONYM BCTS_APPROVAL_COND for BCTS_APPROVAL_COND;
CREATE PUBLIC SYNONYM BCTS_CARGO_CTN for BCTS_CARGO_CTN;
CREATE PUBLIC SYNONYM BCTS_CARGO_HEADER for BCTS_CARGO_HEADER;
CREATE PUBLIC SYNONYM BCTS_CERT_HEADER for BCTS_CERT_HEADER;
CREATE PUBLIC SYNONYM BCTS_IN_TRANSPORT for BCTS_IN_TRANSPORT;
CREATE PUBLIC SYNONYM BCTS_INVOICE for BCTS_INVOICE;
CREATE PUBLIC SYNONYM BCTS_ITEM for BCTS_ITEM;
CREATE PUBLIC SYNONYM BCTS_ITEM_CASC for BCTS_ITEM_CASC;
CREATE PUBLIC SYNONYM BCTS_ITEM_CASC_CASCID for BCTS_ITEM_CASC_CASCID;
CREATE PUBLIC SYNONYM BCTS_ITEM_CERT for BCTS_ITEM_CERT;
CREATE PUBLIC SYNONYM BCTS_ITEM_PACK_DESC for BCTS_ITEM_PACK_DESC;
CREATE PUBLIC SYNONYM BCTS_ITEM_VEHICLE for BCTS_ITEM_VEHICLE;
CREATE PUBLIC SYNONYM BCTS_JOB_HEADER for BCTS_JOB_HEADER;
CREATE PUBLIC SYNONYM BCTS_LICENCE for BCTS_LICENCE;
CREATE PUBLIC SYNONYM BCTS_OUT_TRANSPORT for BCTS_OUT_TRANSPORT;
CREATE PUBLIC SYNONYM BCTS_PARTY for BCTS_PARTY;
CREATE PUBLIC SYNONYM BCTS_PERMIT for BCTS_PERMIT;
CREATE PUBLIC SYNONYM BCTS_SUMMARY for BCTS_SUMMARY;
CREATE PUBLIC SYNONYM BCTS_SUPPORT_DOC for BCTS_SUPPORT_DOC;
CREATE PUBLIC SYNONYM BCTS_UPDATE_SUM for BCTS_UPDATE_SUM;
CREATE PUBLIC SYNONYM BCTS_ALERT for BCTS_ALERT;
CREATE PUBLIC SYNONYM BCTS_CASE_COMMENT for BCTS_CASE_COMMENT;
CREATE PUBLIC SYNONYM BCTS_APP_USER for BCTS_APP_USER;
CREATE PUBLIC SYNONYM BCTS_USER_PROFILE for BCTS_USER_PROFILE;
CREATE PUBLIC SYNONYM APP_USER_USER_PROFILE for APP_USER_USER_PROFILE;
CREATE PUBLIC SYNONYM BCTS_USER_ATTEMPTS for BCTS_USER_ATTEMPTS;
CREATE PUBLIC SYNONYM persistent_logins for persistent_logins;

Commit;

--
--GRANTS to bctowner
--
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_AME_INFO TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_APPROVAL_COND TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_CARGO_CTN TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_CARGO_HEADER TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_CERT_HEADER TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_IN_TRANSPORT TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_INVOICE TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_ITEM TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_ITEM_CASC TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_ITEM_CASC_CASCID TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_ITEM_CERT TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_ITEM_PACK_DESC TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_ITEM_VEHICLE TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_JOB_HEADER TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_LICENCE TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_OUT_TRANSPORT TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_PARTY TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_PERMIT TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_SUMMARY TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_SUPPORT_DOC TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_UPDATE_SUM TO bctowner;

GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_APP_USER TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_USER_PROFILE TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON APP_USER_USER_PROFILE TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON BCTS_USER_ATTEMPTS TO bctowner;
GRANT DELETE, INSERT, SELECT, UPDATE ON persistent_logins TO bctowner;

INSERT into BCTS_COMPANY VALUES ('UEN1','nguyendaidien@gmail.com;josephngd@gmail.com', 'Test PTE LTD', null, null, null,null, null, null,null, null, null,null, null, null,null, null, null);
COMMIT;
--INSERT 
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (1,'sam','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm','Ajay','Samanta','ajaykumar99009@gmail.com','UEN1',1,1,1,1,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (9,'tstsa05','$2a$10$tg/Un0zvFOOgUY3ykhapy.gEzlTp8JW3wfy2XPNWTOR9b8asgo.Wm','tstsa05','OK','tstsa05@gmail.com','UEN1',1,1,1,1,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO, ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (3,'dbadm01','$2a$10$hIYQJlRx7A/OSGsPlVWy.u0Ft781TEN9I3je4nmJp6e0ZZ.QzSOFy','DB','Admin','dbadm@gmail.com','UEN1',1,1,1,1,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (4,'tstsa01','$2a$10$Nq/OIjk7SdIQeuWnmZP0B.RYNLW4xSXbYst1wwtWl6qzksYfpucXK','Test','User','test@gmail.com','UEN1',1,1,1,1,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (6,'tstsa02','$2a$10$WbQnaEXDpLdaVA1ijE02/uR2aqJukHHs9gQgIriLK9jHrXtYjU6kK','testuser','samuser','testuser2@gm.com','UEN1',1,null,null,null,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (7,'tstsa03','$2a$10$C06DMC12z8yin5qyQb2KdOPyPB54fu4tdTemWSuid0XXKYnjZ.XxG','tstsa03','tstsa03','tstsa03@gm.com','UEN1',null,null,null,null,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (8,'tstsa04','$2a$10$CPyQZXRttX7wMY80PQL8musnKBzXVmmaWbEhzHhCoceSeWFxyQctS','Aj04','Sam04','tstsa04@gmail.com','UEN1',null,null,null,null,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (10,'sdf','$2a$10$liqIjrb3ZNVMAKP2WNmvNeqivRgng.y75TNrXbygbOm6narBYjSBq','yest','sdfds','ajaykumar99009@gmail.com','UEN1',1,1,1,1,null,null,null);
Insert into BCTS_APP_USER (TRANSACTION_ID,SSO_ID,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,CR_UEI_NO,ACCT_LOCKED,USER_ENABLED,ACCT_EXPIRED,CREDENTIAL_EXPIRED,CREATED_DATE,PWD_UPDATED,PWD_EXPIRED_DATE) values (11,'test11','$2a$10$32dCQx5v6bmTyL1H4t7Eq.2rRwb7OluSY1Lu337XbJcqbrhDdbZrq','test11','test11','test11@mail.com','UEN1',1,1,1,1,to_date('07/11/18','DD/MM/RR'),null,null);
COMMIT;

Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (1,2);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (3,3);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (4,1);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (6,1);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (7,3);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (7,4);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (8,3);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (8,4);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (9,1);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (10,3);
Insert into BCTS_APP_USER_PROFILE (USER_ID,USER_PROFILE_ID) values (11,1);
COMMIT;

Insert into BCTS_USER_PROFILE (TRANSACTION_ID,ROLE_TYPE) values (1,'USER');
Insert into BCTS_USER_PROFILE (TRANSACTION_ID,ROLE_TYPE) values (2,'ADMIN');
Insert into BCTS_USER_PROFILE (TRANSACTION_ID,ROLE_TYPE) values (3,'DBA');
Insert into BCTS_USER_PROFILE (TRANSACTION_ID,ROLE_TYPE) values (4,'TEST');
Insert into BCTS_USER_PROFILE (TRANSACTION_ID,ROLE_TYPE) values (5,'qqqq');
Insert into BCTS_USER_PROFILE (TRANSACTION_ID,ROLE_TYPE) values (6,'jjiji');
Insert into BCTS_USER_PROFILE (TRANSACTION_ID,ROLE_TYPE) values (7,'NEWROLE');
COMMIT;

Insert into BCTS_ALERT values (1, 'PC', 'UEN1', '1234', 'P123456', 'Test Alert Content', 'O',null,1, null, null, null, null, null, null, null, null);
Insert into BCTS_ALERT values (2, 'LV', 'UEN1', '1235', 'P123456', 'Test Alert Content - Licence Validity 1', 'O',null,1, to_date('14/11/18','DD/MM/RR'), null, null, null, null, 'LC001', to_date('01/12/17','DD/MM/RR'), to_date('30/11/18','DD/MM/RR'));
Insert into BCTS_ALERT values (3, 'LV', 'UEN1', '1235', 'P123456', 'Test Alert Content - Licence Validity 2', 'O',null,1, to_date('14/11/18','DD/MM/RR'), null, null, null, null, 'LC002', to_date('01/12/17','DD/MM/RR'), to_date('30/11/18','DD/MM/RR'));
Insert into BCTS_ALERT values (4, 'LV', 'UEN1', '1235', 'P123456', 'Test Alert Content - Licence Validity 3', 'O',null,1, to_date('14/11/18','DD/MM/RR'), null, null, null, null, 'LC003', to_date('01/12/17','DD/MM/RR'), to_date('30/11/18','DD/MM/RR'));
Insert into BCTS_ALERT values (5, 'LV', 'UEN1', '1235', 'P123456', 'Test Alert Content - Licence Validity 4', 'O',null,null, to_date('14/11/18','DD/MM/RR'), null, null, null, null, 'LC004', to_date('01/12/17','DD/MM/RR'), to_date('30/11/18','DD/MM/RR'));
Commit;
