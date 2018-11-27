/**
 *
 */
package com.etrade.bcts.common.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EricLoh
 */
public class WebDataDict implements Serializable, BctsDataDict{
    /** 
     * 
     */
    private static final long serialVersionUID = -737070488563462007L;

    public static final String PATH_DATA = "/gets/datafiles/bcts/";
    public static final String PATH_CONFIG = "/gets/bcts/config/";
    public static final String PATH_SCRIPT = "/gets/bcts/bin/";
    public static final String PATH_VIDEO = "/gets/bcts/data/video/";

    public static final String DOWNLOAD_DIR = PATH_DATA + "report/output/"; // This is where the PDF Permit will be generated

    public static final String PATH_DATA_REPORT = PATH_DATA + "report/output/"; // WebDataDict.PATH_DATA_REPORT
    public static final String PATH_DATA_REPORT_PR = PATH_DATA + "report/PR/"; // WebDataDict.PATH_DATA_REPORT_PR
    public static final String PATH_DATA_REPORT_REQUEST = PATH_DATA + "report/request/"; // WebDataDict.PATH_DATA_REPORT_REQUEST
    public static final String PATH_DATA_REPORT_PROCESSING = PATH_DATA + "report/processing/"; // WebDataDict.PATH_DATA_REPORT_PROCESSING
    public static final String PATH_DATA_USERCONFIG_GENERICREPORT = PATH_DATA + "report/userconfig/genericreport/";

    public static final String PATH_LOG = "/gets/bcts/logs/";
    public static final String PATH_ZIP_BACKUP = "/gets/datafiles/bcts/zip/";
    public static final String PATH_DB_ARCHIVE = "/gets/datafiles/bcts/archive/db/";

    public static final String PATH_EPA_SERVICE_FILES_ROOT = "/gets/datafiles/bcts/epa/";
    public static final String PATH_TRW_HVU_ROOT = "/gets/datafiles/bcts/comms/hvu/";

    public static final String SOURCE_INTEGRATOR_HOST = "HOST";
    public static final String SOURCE_TRW_LIVE_PORTAL = "LIVE";

    public static final String TRADENET_MAILBOX = "DCS4.DCS4001";

    public static final String ETRADE = "ETRADE";

    public static final String APPID = "TRW4";
    public static final String CONTEXT_ROOT = "epa";

    public static final String APP_PREFIX = "/trw4/";
    public static final String JSP_COMMON_PREFIX = APP_PREFIX + "common/";
    public static final String EJB_PREFIX = "java:app";
    public static final String EJB_LOOKUP_PREFIX = "java:comp/env/ejb";
    public static final String DB_PREFIX = "java:/"; // Changed to match what Infra configured (excludes jboss prefix)
    public static final String DATASOURCE_TRW = "jdbc/trwDSLocal"; // Use trwDSLocal for AWS as old TradeWeb no longer needed

    public static final String DATASOURCE_PRS = "jdbc/prsDSLocal";
    public static final String DATASOURCE_PFK = "jdbc/pfkDSLocal";
    public static final String EJB_MODULE_NAME = "trwEJBrel4_0";

    public static final String TRW_JSP = APP_PREFIX + "trwJSP";

    public static final String ROLE_PR = "PR";
    public static final String ROLE_GEN_USER = "GU";
    public static final String ROLE_GEN_ADMIN = "GA";
    public static final String ROLE_TECH_ADMIN = "TA";
    public static final String ROLE_AS2 = "AS2";  // new Role for ST - Global Air - AS2 message flow
    public static final String ROLE_CC_USER = "CU";

    public static final String ROLE_EPA_SERVICE_USER = "EPU";
    public static final String ROLE_EPA_SERVICE_ADMIN = "EPA";
    public static final String ROLE_EPA_SALES_OPS = "EPO";

    public static final String USER_SESSION = "UserSession"; // User Session object key in HttpSession

    public static final String HIDE_MENU = "hideMenu"; // Hide left side menu

    public static final String MSG_VER = "040";
    public static final String MSG_VER_TN41 = "041";
    public static final String VER_TN41 = "4.1";

    public static final String BILL_POSTED_ON = "BILL POSTED ON ";

    public static final String BIN_EXTENSION = ".bin";
    public static final String CSV_EXTENSION = ".csv";

    public static final String AEB_INCOMING = "SDEPL_INCOMING";

    public static final String UPLOAD_EXCEL_FORMELEMENT = "uploadExcelFormElement"; //Needs to match with (Constants class) in TradeWeb GWT codes
    public static final String CONTAINER_EXCEL_FORMELEMENT = "containerExcelFormElement";
    public static final String ITEMS_EXCEL_FORMELEMENT = "itemsExcelFormElement";
    public static final String ITEMS_COO_EXCEL_FORMELEMENT = "itemsCOOExcelFormElement";

    //added for tabbed hs-smart search and standard search
    public static final String  CLICK_FROM = "CLICK_FROM";
    public static final String  HS_CA_SEARCH = "hs_ca_search";
    public static final String  STANDARD = "standard";

    // TTRW4_CM_PROPERTY Categories
    public static final String CAT_COMMS = "COMMS";
    public static final String CAT_COMMON = "COMMON";
    public static final String CAT_EMAIL = "EMAIL";
    public static final String CAT_MHUB_TO_TRW4_IDS = "MHUB_TO_TRW4_IDS"; // Maps multiple TRW4 IDs to a single Mhub ID for sending back responses
    public static final String CAT_TRW4_TO_MHUB_ID = "TRW4_TO_MHUB_ID"; // Maps TRW4 Account IDs to a single Mhub ID for sending back responses
    public static final String CAT_TXC_TO_TRW4_ID = "TXC_TO_TRW4_ID"; // Mapping from TXC_ID (from Inhouse Xml) to TRW4 ID (for storing records)
    public static final String CAT_RN_GATEWAY = "RN_GATEWAY";
    public static final String CAT_INTEGRATOR = "INTEGRATOR";
    public static final String CAT_BILLING = "BILLING";
    public static final String CAT_PERMISSION = "PERMISSION";
    public static final String CAT_REPORT = "REPORT";
    public static final String CAT_ARCH = "ARCH";
    public static final String CAT_INSURANCE = "INSURANCE";
    public static final String CAT_DECLARATION = "DECLARATION";
    public static final String CAT_HS_SMARTSEARCH = "HS_SMARTSEARCH"; //added for HSSmartSearch
    public static final String CAT_AS2 = "AS2"; // added for STM-GlobalAir AS2 Flow
    public static final String CAT_REMINDER = "REMINDER"; //added for Password REMINDER
    public static final String CAT_FEEDBACK = "FEEDBACK"; // added for Feedback
    public static final String CAT_ADD_RECIPIENT = "ADD_RECIPIENT"; // added for Schenker
    public static final String CAT_ELIXIR_SERVER = "ELIXIR_SERVER"; // added for Exlixir Server


    // TTRW4_CM_PROPERTY ELIXIR_SERVER Keys
    public static final String KEY_ENABLE_ELIXIR_ALL_REPORTS = "ENABLE_ALL_REPORTS";
    public static final String KEY_ENABLE_ELIXIR_CCP_REPORT = "ENABLE_CCP_REPORT";
    public static final String KEY_ENABLE_ELIXIR_REFUND_REPORT = "ENABLE_REFUND_REPORT";
    public static final String KEY_ENABLE_ELIXIR_DRAFT_DEC_REPORT = "ENABLE_DRAFT_DEC_REPORT";
    public static final String KEY_ENABLE_ELIXIR_CO_REPORT = "ENABLE_CO_REPORT";
    public static final String KEY_ENABLE_ELIXIR_FEEMSG_REPORT = "ENABLE_FEEMSG_REPORT";

    public static final String KEY_ENABLE_ELIXIR_SUBMITTED_DEC_REPORT = "ENABLE_SUBMITTED_DEC_REPORT";
    public static final String KEY_ENABLE_ELIXIR_GST_DUTY_REPORT = "ENABLE_GST_DUTY_REPORT";
    public static final String KEY_ENABLE_ELIXIR_PERMIT_COND_REPORT = "ENABLE_PERMIT_COND_REPORT";
    public static final String KEY_ENABLE_ELIXIR_USER_STATIC_REPORT = "ENABLE_USER_STATIC_REPORT";

    // TTRW4_CM_PROPERTY Password Reminder Keys
    public static final String TRW_PASSWORD_EXPIRY_DURATION = "TRW_PASSWORD_EXPIRY_DURATION"; //No. of days before password expires
    public static final String TXC_PASSWORD_EXPIRY_DURATION = "TXC_PASSWORD_EXPIRY_DURATION"; //No. of days before password expires

    // TTRW4_CM_PROPERTY HS_SMARTSEARCH Keys
    public static final String KEY_CLIENT_ID = "CLIENT_ID";
    public static final String KEY_CLIENT_SECRET = "CLIENT_SECRET";

    // TTRW4_CM_PROPERTY COMMS Keys
    public static final String KEY_SENT_HOURS_TO_CHECK = "SENT_HOURS_TO_CHECK"; //Indicate how many hours ago to check for sent messages
    public static final String KEY_VALIDATE_AED = "VALIDATE_AED";
    public static final String KEY_TRADENET_MBX = "TRADENET_MAILBOX";
    public static final String KEY_TRADENET_ID = "TRADENET_ID";
    public static final String KEY_XML_SEND_FOLDER = "XML_SEND_FOLDER";
    public static final String KEY_XML_RECEIVE_FOLDER = "XML_RECEIVE_FOLDER";
    public static final String KEY_XML_RELEASE_FOLDER = "XML_RELEASE_FOLDER";
    public static final String KEY_ATTACH_FOLDER = "ATTACH_FOLDER";
    public static final String KEY_LOG_FOLDER = "LOG_FOLDER";
    public static final String KEY_TRUST_STORE = "TRUST_STORE";
    public static final String KEY_SERVER_URL = "SERVER_URL";
    public static final String KEY_FILE_SERVER = "FILE_SERVER";
    public static final String KEY_PROFILE_PATH = "PROFILE_PATH";
    public static final String KEY_MHX_PATH = "MHX_PATH";
    public static final String KEY_PROXY_HOST = "PROXY_HOST";
    public static final String KEY_PROXY_PORT = "PROXY_PORT";
    public static final String KEY_CRON_PATH = "CRON_PATH";
    public static final String KEY_PROTOCOL = "PROTOCOL";
    public static final String KEY_RETRY_COUNT = "RETRY_COUNT";
    public static final String KEY_NBS_DIR = "NBS_FILE_DIR";
    public static final String KEY_MAX_TO_RECEIVE = "MAX_TO_RECEIVE"; //Maximum messages to receive each time

    public static final String KEY_RECEIVE_LIMIT_SECONDS = "RECEIVE_LIMIT_SECONDS"; //Time Limit before Activate Receive can be called again
    //Noticed that MHX throws errors when receive is triggered too frequently for each User.
    //Some Users like to click Activate Receive every few seconds, not giving time for the receive to complete processing
    //Use Properties in case need to adjust the value

    public static final String KEY_ENABLE_ADD_SEND_RECOVERY = "ENABLE_ADD_SEND_RECOVERY"; //True to enable adding send recovery records

    public static final String KEY_HS_SERVICES_ENDPOINT = "HS_SERVICES_ENDPOINT";
    public static final String KEY_MHB_WS_ENDPOINT = "MHB_WS_ENDPOINT";
    public static final String KEY_MHB_WS_NAMESPACE = "MHB_WS_NAMESPACE";
    public static final String KEY_MHB_WS_KEYSTORE = "MHB_WS_KEYSTORE";
    public static final String KEY_MHB_WS_KEYSTORE_PASS = "MHB_WS_KEYSTORE_PASS";

    public static final String KEY_EMAIL_RECEIVER = "EMAIL_RECEIVER";
    public static final String KEY_SUPPORT_EMAIL = "SUPPORT_EMAIL";
    public static final String KEY_MHUB_RECEIVER = "MHUB_RECEIVER";
    public static final String KEY_RN_RECEIVER = "RN_RECEIVER"; // RosettaNet Gateway Receiver
    public static final String KEY_INTEGRATOR_USER = "INTEGRATOR_USER";
    public static final String KEY_SEND_BY_EMAIL = "SEND_BY_EMAIL";
    public static final String KEY_TN31_RECEIVER = "TN31_RECEIVER";
    public static final String KEY_FORM_D_RECEIVER = "FORM_D_RECEIVER";
    public static final String KEY_INT4_RECEIVER = "INT4_RECEIVER";
    public static final String KEY_EXEMPT_TN_RECEIVE = "EXEMPT_TN_RECEIVE";
    public static final String KEY_EXEMPT_TN_RECEIVE2 = "EXEMPT_TN_RECEIVE2"; // Row 2 as 1 row cannot fit
    public static final String KEY_EXEMPT_TXC_RECEIVE = "EXEMPT_TXC_RECEIVE"; // Replace EXEMPT_TN_RECEIVE (uses AccountIds instead of UserIds)
    public static final String KEY_BLOCK_TN41_TO_INT31 = "BLOCK_TN41_TO_INT31"; // #2652

    // TTRW4_CM_PROPERTY INTEGRATOR keys
    public static final String KEY_INTEGRATOR_INT_MSG_TN40_ACCOUNT_LIST = "INT_MSG_TN40_ACCOUNT_LIST";

    public static final String KEY_INTEGRATOR_TXC_ID = "INTEGRATOR_TXC_ID";
    public static final String KEY_MAP_PROD_CODE = "MAP_PROD_CODE";

    public static final String KEY_ENABLE_RETRIEVE_FROM_INSURANCE = "ENABLE_RETRIEVE_FROM_INSURANCE";
    public static final String KEY_AXIS2_REPO_PATH = "AXIS2_REPO_PATH";
    public static final String KEY_GTRETRIEVE_WS_ENDPOINT = "GTRETRIEVE_WS_ENDPOINT";
    public static final String KEY_GTRETRIEVE_WS_USERNAME = "GTRETRIEVE_WS_USERNAME";
    public static final String KEY_GTRETRIEVE_WS_PASSWORD = "GTRETRIEVE_WS_PASSWORD";
    public static final String KEY_GTRETRIEVE_WS_KEYSTORE = "GTRETRIEVE_WS_KEYSTORE";
    public static final String KEY_GTRETRIEVE_WS_KEYSTORE_PASS = "GTRETRIEVE_WS_KEYSTORE_PASS";
    public static final String KEY_GTSEND_WS_ENDPOINT = "GTSEND_WS_ENDPOINT";
    public static final String KEY_GTSEND_WS_USERNAME = "GTSEND_WS_USERNAME";
    public static final String KEY_GTSEND_WS_PASSWORD = "GTSEND_WS_PASSWORD";
    public static final String KEY_GTSEND_WS_KEYSTORE = "GTSEND_WS_KEYSTORE";
    public static final String KEY_GTSEND_WS_KEYSTORE_PASS = "GTSEND_WS_KEYSTORE_PASS";

    // TTRW4_CM_PROPERTY COMMON Keys
    public static final String KEY_ENVT = "ENVT";
    public static final String KEY_GST_RATE = "GST_RATE";
    public static final String KEY_ENABLE_REPORT = "ENABLE_REPORT";

    public static final String KEY_ENABLE_SUBMIT_REPORT = "ENABLE_SUBMIT_REPORT";
    public static final String KEY_ENABLE_GST_REPORT = "ENABLE_GST_REPORT";
    public static final String KEY_ENABLE_PMT_COND_REPORT = "ENABLE_PMT_COND_REPORT";
    public static final String KEY_ENABLE_USER_STAT_REPORT = "ENABLE_USER_STAT_REPORT";
    public static final String KEY_ENABLE_GENERIC_REPORT = "ENABLE_GENERIC_REPORT";
    public static final String KEY_ENABLE_SCHEDULE_REPORT = "ENABLE_SCHEDULE_REPORT";

    public static final String KEY_ENABLE_PR_WIZARD = "ENABLE_PR_WIZARD";

    public static final String KEY_ENABLE_CUSCOM_RELEASE_CHECK = "ENABLE_CUSCOM_RELEASE_CHECK";

    public static final String KEY_ENABLE_REF_CODES_UPLOAD = "ENABLE_REF_CODES_UPLOAD";

    public static final String KEY_UEN_CHECKPOINT_FLAG = "UEN_CHECKPOINT_FLAG";
    public static final String CHECKPOINT_CROSSED = "CheckPointCrossed";
    public static final String KEY_COMMON_TN40_ENABLED = "TN40_ENABLED";

    public static final String KEY_ECHOSIGN_URL = "ECHOSIGN_URL";

    // TTRW4_CM_PROPERTY RN_GATEWAY Keys
    public static final String KEY_LDAP_URL = "LDAP_URL";
    public static final String KEY_QCF_NAME = "QCF_NAME";
    public static final String KEY_BPI_ID = "BPI_ID";
    public static final String KEY_BPI_PASSWORD = "BPI_PASSWORD";
    public static final String KEY_BPI_QUEUENAME = "BPI_QUEUENAME";
    public static final String KEY_CRIMSON_DUNS = "CRIMSON_DUNS";
    public static final String KEY_DUNS_SUFFIX = "DUNS";

    // TTRW4_CM_PROPERTY BILLING Keys
    public static final String KEY_CHARGE_INCOMING = "CHARGE_INCOMING";
    public static final String KEY_CHARGE_AEB = "CHARGE_AEB";
    public static final String KEY_ENABLE_CHARGE_GENERIC_REPORT = "ENABLE_CHARGE_GENERIC_REPORT";
    public static final String KEY_ENABLE_CHARGE_ARCHIVAL_ACTIVATION = "ENABLE_CHARGE_ARCHIVAL_ACTIVATION";
    public static final String KEY_ENABLE_CHARGE_ARCHIVAL_RETENTION = "ENABLE_CHARGE_ARCHIVAL_RETENTION";

    // TTRW4_CM_PROPERTY BPO Keys
    public static final String KEY_ENABLE_BPO_FILING_FEE = "ENABLE_BPO_FILING_FEE";
    public static final String KEY_ENABLE_BPO_TOTAL_CHARGES = "ENABLE_BPO_TOTAL_CHARGES";

    public static final String KEY_GSTDUTY_PLUS_ACCESS = "GSTDUTY_PLUS_ACCESS";

    // TTRW4_CM_PROPERTY Archival Keys
    public static final String KEY_DOC_AGE = "DOC_AGE";
    public static final String KEY_AUTO_ARCHIVE_START_DATE = "AUTO_ARCHIVE_START_DATE";
    public static final String KEY_AUTO_ARCHIVE_DAYS = "AUTO_ARCHIVE_DAYS";
    public static final String KEY_AUTO_DELETE_END_DATE_EXCLUSIVE = "AUTO_DELETE_END_DATE_EXCLUSIVE";
    public static final String KEY_AUTO_DELETE_DAYS = "AUTO_DELETE_DAYS";
    public static final String KEY_AUTO_EXPORT_START_DATE = "AUTO_EXPORT_START_DATE";
    public static final String KEY_AUTO_EXPORT_DAYS = "AUTO_EXPORT_DAYS";
    public static final String KEY_AUTO_PWD = "AUTO_PWD";
    public static final String KEY_AUTO_ARCHIVE_TO_FILES_START_DATE = "AUTO_ARCHIVE_TO_FILES_START_DATE";
    public static final String KEY_AUTO_ARCHIVE_TO_FILES_DAYS = "AUTO_ARCHIVE_TO_FILES_DAYS";
    public static final String KEY_ENABLE_AUTO_RESTORE = "ENABLE_AUTO_RESTORE";
    public static final String KEY_DISABLE_PLAN_DOWNGRADE_DURATION = "DISABLE_PLAN_DOWNGRADE_DURATION";
    public static final String KEY_ENABLE_RETENTION_PLAN_UI = "ENABLE_RETENTION_PLAN_UI";
    public static final String KEY_ENABLE_RESTORATION_LOG_UI = "ENABLE_RESTORATION_LOG_UI";
    public static final String KEY_ENABLE_RESTORATION_ADHOC_UI = "ENABLE_RESTORATION_ADHOC_UI";
    public static final String KEY_ENABLE_PLAN_CONTRACT = "ENABLE_PLAN_CONTRACT";
    // TTRW4_CM_PROPERTY Insurance Keys
    public static final String KEY_INSURANCE_USER = "USER";
    public static final String KEY_QBE_URL = "QBE_URL";
    public static final String KEY_MAX_RECORD_SEND = "MAX_RECORD_SEND";
    public static final String KEY_INSURANCE_BYPASS_DRAFT = "BYPASS_DRAFT_USER";

    // TTRW4_JOB_HEADER Status Keys
    public static final String CODE_PERMIT_APPROVE = "PA";
    public static final String CODE_PERMIT_REJECT = "RJ";
    public static final String CODE_DRAFT = "DR";
    public static final String CODE_AWAITING_APPROVAL = "AC";
    public static final String CODE_CANCEL_APPROVE = "CA";
    public static final String CODE_NOT_SENT = "NS";

    // Web Service Option Map Key
    public static final String MAP_USER_ID = "trwUserId";
    public static final String MAP_STATUS = "status";
    public static final String MAP_JOB_NO = "trwJobNo";
    public static final String MAP_PERMIT_NO = "permitNo";

    // TTRW4_CM_PROPERTY REPORT - Generic Report / Report Wizard
    public static String KEY_REPORT_SETTING_GENERIC_REPORT_MAX_THREAD = "GENERIC_REPORT_MAX_THREAD";
    public static String KEY_REPORT_SETTING_GENERIC_REPORT_MAX_REQUEST = "GENERIC_REPORT_MAX_REQUEST";
    public static String KEY_REPORT_SETTING_GENERIC_REPORT_MAX_FILE = "GENERIC_REPORT_MAX_FILE";

    // Report Table Names
    public static final String GST_SEARCH_TABLE = "TTRW4_GST_SEARCH";
    public static final String GST_REPORT_TABLE = "TTRW4_GST_REPORT";
    public static final String SUBMIT_DEC_SEARCH_TABLE = "TTRW4_SUBMIT_DEC_SEARCH";
    public static final String SUBMIT_DEC_REPORT_TABLE = "TTRW4_SUBMIT_DEC_REPORT";

    public static final String USER_STATS_SEARCH_TABLE = "TTRW4_USER_STATS_SEARCH";
    public static final String USER_STATS_REPORT_TABLE = "TTRW4_USER_STATS_REPORT";

    public static final String PERMIT_COND_REPORT_TABLE = "TTRW4_PERMIT_CONDITION_REPORT";
    public static final String SCHEDULE_JOB_REPORT_TABLE = "TTRW4_SCHEDULE_JOB_REPORT";

    // Comms Protocols
    public static final String MHACCESS = "MHACCESS";
    public static final String WEBSERVICE = "WEBSERVICE";
    public static final String SERVLET = "SERVLET";
    public static final String MQ = "MQ"; // Message Queue

    // Comms Transmission Status
    public static final String COMMS_ERROR = "E";
    public static final String COMMS_SUCCESS = "S";
    public static final String COMMS_MHUB_ERROR = "M";

    public static final String COMMS_NOTHING_TO_RECEIVE = "NOTHING TO RECEIVE";
    public static final String COMMS_SAAJ0014 = "Mhub Error: SAAJ0014: Content length of reply was zero";
    public static final String COMMS_MESSAGE_ALREADY_SENT = "You have already sent out this Declaration. Please do not Resend.";
    public static final String COMMS_MESSAGE_ALREADY_RECEIVED = "You have already received a Response for this Declaration. Please do not Resend.";
    public static final String COMMS_ACTIVATE_SEND_SUCCESS = "Activate Send Successful";
    public static final String COMMS_ACTIVATE_SEND_FAILED = "Activate Send Failed";
    public static final String COMMS_RE_ATTACH_DOCUMENT = "Please re-attach this document as it cannot be found: ";
    public static final String COMMS_BROKEN_PIPE = "Broken Pipe";
    public static final String COMMS_RECEIVE_FAILURE_MSG = "Receiving was unsuccessful. Please try again later.";
    public static final String COMMS_SENDING_FAILURE_MSG = "Sending was unsuccessful. Please try again later.";
    public static final String MHUB_ERROR_PREFIX = "MHB Error: ";
    public static final String COMMS_TXC_CONNECT_ERROR = "TradeXchange connection maybe temporarily unavailable. Please try again later.";
    public static final String COMMS_FAULTCODE = "FaultCode";
    public static final String COMMS_SUPPORT_DOC_MSSING = "Support Document Missing";

    public static final String SUCCESS = "SUCCESS";

    // Attribute Names (For Session or Request Attributes)
    public static final String MENU_SELECTED = "pMenuSelected"; // New for AddOnService

    public static final String USER_ROLES = "pUserRoles";
    public static final String SELECT = "pSelect";
    public static final String USER_ID = "pUserId";
    public static final String JOB_NO = "pJobNo";
    public static final String CR_NO = "pCrNo";
    public static final String UEI = "pUEI";
    public static final String CAR_TYPE = "pcartype";
    public static final String NO_OF_ITEMS = "pnoOfItems";
    public static final String FOB_AMT = "pfobamount";
    public static final String GROSS_WT = "pgrosswt";
    public static final String GST_AMT = "pgstamt";
    public static final String CUSTS_AMT = "pcustsamt";
    public static final String EXCISE_AMT = "pexciseamt";
    public static final String OTH_TAX_AMT = "othrtaxamt";
    public static final String AMT_PAYABLE = "pamtpayable";
    public static final String USR_EMAIL_ID = "puseremailid";
    public static final String DECLARANT_NAME = "pDeclarantName";
    public static final String FORMACTION = "pAppAction";
    public static final String CURR_PATH = "pCurrPath";
    public static final String DIR_PATH = "pDirPath";
    public static final String JOB_LIST = "pJobList";

    // For Code Maintenance to indicate a the Option (Insert action or Update action)
    public static final String OPTION = "pOption";

    public static final String DECLARING_AGENT_CR_NO = "pDeclaringAgentCrNo";
    public static final String DECLARING_AGENT_NAME1 = "pDeclaringAgentName1";
    public static final String DECLARING_AGENT_NAME2 = "pDeclaringAgentName2";

    public static final String SELECTED_MAX_DISPLAY_ROW = "selMaxDisplayRow";
    public static final String SELECTED_GST_RATE = "selGstRate";
    public static final String SELECTED_GST_RELIEF_AMT = "selGstReliefAmt";

    public static final String SELECTED_USER_ID = "selUserId";
    public static final String SELECTED_CAR_TYPE = "selCarType";
    public static final String SELECTED_UPDATE_TYPE = "selUpdateType";

    public static final String SELECTED_MAP_ID = "selMapId";
    public static final String SELECTED_DATASOURCE = "selDataSource";
    // Parameter name to be used for composing URL parameter, for request
    // parameters and session attributes.

    public static final String PREFIX_PRINT = "print_";
    public static final String PARAMNAME_TN_VER = "pTnVer";
    public static final String PARAMNAME_TARGET_TN_VER = "targetTnVer"; // TN4.1
    public static final String PARAMNAME_TN40_ENABLED = "pIsTn40Enabled";

    public static final String ACTION = "trwAction";
    public static final String ACTION_LOAD = "Load";
    public static final String ACTION_NEW = "New";
    public static final String ACTION_COPY = "Copy";
    public static final String ACTION_EDIT_OWNERSHIP = "Edit_Ownership";
    public static final String ACTION_VIEW = "View";
    public static final String ACTION_VIEW_CANCEL = "CANCEL_View";
    public static final String ACTION_VIEW_REFUND_FRF = "REFUND_FRF_View";
    public static final String ACTION_VIEW_REFUND_PRG = "REFUND_PRG_View";
    public static final String ACTION_VIEW_REFUND_PRS = "REFUND_PRS_View";
    public static final String ACTION_EMAIL = "SENDEMAIL";
    public static final String ACTION_UPDATE = "Update";
    public static final String ACTION_REFUND_FRF = "REFUND_FRF";
    public static final String ACTION_REFUND_PRG = "REFUND_PRG";
    public static final String ACTION_REFUND_PRS = "REFUND_PRS";
    public static final String ACTION_SEND = "Send";
    public static final String ACTION_RECEIVE = "Receive";
    public static final String ACTION_ADVANCED = "Advanced";
    public static final String ACTION_CONTINUE = "Continue";
    public static final String ACTION_DOWNLOAD_REPORT = "DOWNLOAD_REPORT";
    public static final String ACTION_PRINT_DRAFT = "PRINT_DRAFT";

    public static final String HSSMART_HSCODE = "hsCode";

    // For Code Maintenance to indicate an Insert action
    public static final String ACTION_INSERT = "Insert";

    // For Audit Trail action
    public static final String AUDIT_NEW = "New";
    public static final String AUDIT_CANCEL = "Cancel";
    public static final String AUDIT_EDIT = "Edit";
    public static final String AUDIT_COPY = "Copy";
    public static final String AUDIT_EDIT_OWNERSHIP = "Edit_Ownership";
    public static final String AUDIT_VIEW = "View";
    public static final String AUDIT_PRINT = "Print";
    public static final String AUDIT_DELETE = "Delete";
    public static final String AUDIT_SEND = "Send";
    public static final String AUDIT_UPDATE = "Activate Save";
    public static final String AUDIT_RECEIVE = "Receive";
    public static final String AUDIT_AMEND = "Amend";
    public static final String AUDIT_NEW_FRF = "New_FRF";
    public static final String AUDIT_NEW_PRG = "New_PRG";
    public static final String AUDIT_NEW_PRS = "New_PRS";
    public static final String AUDIT_UPLOAD_TRW = "Upload To TRW";

    // for Code Maintenance to indicate CM_TABLE
    public static final String CM_TABLE_BRAND_MODEL = "Brand Model";
    public static final String CM_TABLE_CASC_PRODUCT = "CASC Product";
    public static final String CM_TABLE_CLAIMANT_PARTY = "Claimant Party";
    public static final String CM_TABLE_IN_HOUSE_CODE = "In House Code";
    public static final String CM_TABLE_LOCATION = "Location";
    public static final String CM_TABLE_PARTY = "Party";

    //////////////////////////////////////////////////////////////////////
    // Add-On Service JSP pages
    // Use to identify the source of the form submission
    //////////////////////////////////////////////////////////////////////
    public static final String JSP_CLIENT_ADDONS = "ClientAddOns.jsp";
    public static final String JSP_CLIENT_ADDON_EMAILS_EDIT = "ClientAddOnEmailsEdit.jsp";

    public static final String JSP_ADMIN_ADDONS = "AdminAddOns.jsp";
    public static final String JSP_ADMIN_ADDON_EDIT = "AdminAddOnEdit.jsp"; // TODO create this
    public static final String JSP_ADMIN_ADDON_PACKAGES = "AdminAddOnPackages.jsp"; // TODO create this

    public static final String JSP_ADMIN_ADDON_CLIENTS = "AdminAddOnClients.jsp";
    public static final String JSP_ADMIN_ADDON_CLIENT_EDIT = "AdminAddOnClientEdit.jsp";

    public static final String JSP_SOURCE = "JSPSource"; // use as JSP parameter
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////
    // TX Service JSP pages
    // Use to identify the source of the form submission
    //////////////////////////////////////////////////////////////////////
    public static final String JSP_EPA_SERVICE_SEARCH = "ServiceSearch.jsp"; //Service Search
    public static final String JSP_EPA_SERVICE_EDIT_ADMIN = "ServiceEditAdmin.jsp"; //Service Edit by Admin

    public static final String JSP_EPA_SERVICE_EDIT_USER = "ServiceEditUser.jsp"; //Service Edit by User

    public static final String JSP_ADMIN_EPA_SERVICE_ACCOUNT = "EPAServiceAccounts.jsp";
    public static final String JSP_ADMIN_EPA_SERVICE_ACCOUNT_EDIT = "EPAServiceAccountsEdit.jsp";

    public static final String JSP_EPA_SERVICE_USER_SELF_SETUP = "EPAServiceUserSelfSetup.jsp";
    public static final String JSP_EPA_SERVICE_LIST_FILES = "EPAServiceListFiles.jsp";

    public static final String JSP_EPA_ROUTE_EDIT_ADMIN = "RouteEditAdmin.jsp"; //Route Edit by Admin
    public static final String JSP_EPA_ROUTE_EDIT_USER = "RouteEditUser.jsp"; //Route Edit by User


    public static final String JSP_EPA_SERVICE_PENDING_SETUP = "EPAServicePendingSetup.jsp"; //Pending Setup Page (If User logs in but Tech Admin haven't setup)
    public static final String JSP_EPA_SERVICE_NOT_AUTHORIZED = "EPAServiceNotAuthorized.jsp";

    public static final String JSP_EPA_SERVICE_AUDIT_TRAIL = "EPAServiceAuditTrail.jsp";

    public static final String JSP_EPA_SYSTEM_PROPERTIES = "EPASystemProperties.jsp";
    public static final String JSP_EPA_SYSTEM_PROPERTIES_EDIT = "EPASystemPropertiesEdit.jsp";

    public static final String JSP_EPA_SCHEDULER_CONFIG = "EPASchedulerConfig.jsp";
    public static final String JSP_EPA_SCHEDULER_CONFIG_EDIT = "EPASchedulerConfigEdit.jsp";

    //////////////////////////////////////////////////////////////////////
    public static final String EPA_SERVICE_CODE_SELECTED = "EPAServiceCodeSelected";
    public static final String UEN_SELECTED = "uenSelected";
    public static final String USER_ID_SELECTED = "userIdSelected";

    public static final String COMPANY_NAME = "companyName";

    public static final String COUNTRY_MAP = "countryMap";

    public static final String RECORD_EXISTS = "RecordExists"; //Indicate if Record Exists

    public static final String EPA_SERVICE_CODES = "EPAServiceCodes";

    public static final String[] EPA_SERVICE_ACCOUNTS_HEADER = { "UEN", "Company Name", "Subscribed Services" };

    public static final String[] EPA_CONFIGS_RESULT_HEADER = { "Service Code", "UEN", "Active", "Protocol", "Format", "Activated Date", "Suspend Date"};
    public static final String[] EPA_FILES_RESULT_HEADER = { "Service Code", "FileName", "Status", "Received Date", "Sent Date"};

    public static final String[] EPA_SERVICE_AUDIT_TRAIL_HEADER = { "UEN", "User Id", "Date Time", "Module", "Action" };

    public static final String[] EPA_SYSTEM_PROPERTIES_HEADER = { "Name", "Key", "Value" };
    public static final String[] EPA_SCHEDULER_CONFIG_HEADER = { "Name", "Servers", "Enabled", "Schedule Type", "Interval"};

    public static final String[] EPA_ROUTE_HEADER = { "Route Name", "Protocol", "SFTP Download Folder", "Days before Expiry", "Effective Period"};

    //////////////////////////////////////////////////////////////////////
    public static final List<String> NAVIGATION_BUTTONS = Arrays.asList(WebDataDict.BTN_SEARCH, WebDataDict.BTN_NEXT, WebDataDict.BTN_PREVIOUS, WebDataDict.BTN_GOTO);


    public static final String TERMS_AGREEMENT = "termsAgreement"; //Terms and Conditions Agreement

    public static final String BTN_INSERT_CLIENT = "Insert Client";

    public static final String BTN_SUBMIT = "btnSubmit";
    public static final String BTN_MULTIUPLOAD = "btnMultiUpload";
    public static final String BTN_PERMIT_UPLOAD_SEND = "btnPermitUploadSend";
    public static final String BTN_SEARCH = "Search";
    public static final String BTN_SUBSCRIBE = "Subscribe";
    public static final String BTN_UNSUBSCRIBE = "UnSubscribe";
    public static final String BTN_CONFIGURE_EMAILS = "Configure Emails";
    public static final String BTN_SAVE_EMAILS = "Save Emails";
    public static final String BTN_REFRESH_PROPERTIES = "Refresh Properties";

    public static final String BTN_CHECK = "Check";

    public static final String BTN_AGREE = "AGREE";
    public static final String BTN_DISAGREE = "DISAGREE";

    public static final String BTN_ACTIVATE = "Activate";
    public static final String BTN_SUSPEND = "Suspend";
    public static final String BTN_SHOW_CLIENTS = "Show Clients";

    public static final String BTN_ACTIVATE_CLIENT = "Activate Client";
    public static final String BTN_SUSPEND_CLIENT = "Suspend Client";
    public static final String BTN_REMOVE_CLIENT = "Remove Client"; // When User request to unsubscribe

    public static final String BTN_INSERT = "Insert";
    public static final String BTN_DELETE = "Delete";
    public static final String BTN_SEND = "Send";
    public static final String BTN_RESEND = "Resend";
    public static final String BTN_SEND_BYPASS_AED = "Proceed Sending";
    public static final String BTN_RESEND_BYPASS_AED = "Proceed Resending";
    public static final String BTN_SAVE = "Save";
    public static final String BTN_UPDATE = "Update";
    public static final String BTN_LIST = "List";
    public static final String BTN_CLEAR = "Clear";
    public static final String BTN_CLEAR_SELECTION = "Clear Selection";
    public static final String BTN_CHECKALL = "Check All";
    public static final String BTN_UNCHECKALL = "UnCheck All";
    public static final String BTN_RETURN = "Return";
    public static final String BTN_UPLOAD_PERMIT = "Upload";
    public static final String BTN_SEND_PERMIT = "Send";
    public static final String BTN_EXIT = "Exit";
    public static final String[] BTN_SAVE_EXIT = { "Save and Exit", "SaveExit" };
    public static final String BTN_NEW_CANCEL = "New Cancellation";
    public static final String BTN_NEW_FRF = "New FRF";
    public static final String BTN_NEW_PRG = "New PRG";
    public static final String BTN_NEW_PRS = "New PRS";
    public static final String BTN_PRINT = "Print";
    public static final String BTN_GENERATE_REPORT = "Generate Report";
    public static final String BTN_EXPORT_PR_EXCEL = "Export as Excel";
    public static final String BTN_DOWNLOAD_EXCEL = "DownLoad as Excel";
    public static final String BTN_HTML_REPORT = "HTML Report";
    public static final String BTN_CREATE = "Create";
    public static final String BTN_IMPORT = "Import";
    public static final String BTN_EXPORT = "Export";
    public static final String BTN_UPLOAD_EXCEL = "Upload Excel";

    public static final String BTN_ADD = "Add";
    public static final String BTN_REMOVE = "Remove";
    public static final String BTN_DEACTIVE = "Deactivate";
    public static final String BTN_ACTIVE = "Activate";
    public static final String BTN_SCHEDULE_SAVE = "Save Schedule";
    public static final String BTN_DEL_SCHEDULE = "Delete Schedule";
    public static final String BTN_SCHEDULE_REPORT = "Schedule Report";

    public static final String BTN_CONFIG_SAVE = "ConfigSave";
    public static final String BTN_CONFIG_DELETE = "ConfigDelete";
    public static final String BTN_CONFIG_IMPORT = "ConfigImport";
    public static final String BTN_CONFIG_EXPORT = "ConfigExport";
    public static final String BTN_CONFIG_LOAD = "ConfigLoad";
    public static final String BTN_REFRESH_FILE_LISTING = "RefreshFileListing";
    public static final String BTN_DOWNLOAD_REPORT = "DownloadReport";
    public static final String BTN_DISABLE_MODULE = "DisableModule";
    public static final String BTN_ENABLE_MODULE = "EnableModule";

    public static final String BTN_REQUEST_DELETE = "RequestDelete";
    public static final String BTN_REQUEST_DOWNLOAD = "RequestDownload";

    public static final String ACCESS_RPT_GR = "AccessRightsRptGR";
    public static final String RES_RECORDS = "Records"; //Used by Report Wizard JSP

    public static final String RESULT_RECORDS = "Records";
    public static final String RESULT_RECORD = "ResultRecord";

    public static final String BTN_PREVIOUS = "Previous";
    public static final String BTN_NEXT = "Next";
    public static final String BTN_GOTO = "Goto";
    public static final String HYPERLINK = "hyperlink";

    public static final String BTN_EXPORT_TN4 = "Export to TN4 Xml";
    public static final String BTN_PROCESS_INTEGRATOR = "Process Integrator";
    public static final String BTN_PROCESS_INTEGRATOR_DIRECT_RN = "Process Integrator Direct RN";

    public static final String BTN_SEND_TO_INSURANCE = "Send to MarineOne";
    public static final String BTN_RETRIEVE_FROM_INSURANCE = "Retrieve from MarineOne";
    public static final String CHK_SCHEDULE = "ChkSchedule";
    public static final String CURRENT_PAGE = "pCurrentPage";
    public static final String TOTAL_PAGE = "pTotalPage";
    public static final String NEW_PAGE = "newPage";

    public static final String DEC_SEARCH = "DEC_Search";
    public static final String RESPONSE_SEARCH = "Response_Search";

    public static final String SCHEDULE_JOB = "Schedule_Job";


    // Menu Selections (Code Maintenance)
    public static final String CM_BRAND_MODEL = "CM_BrandModel";
    public static final String CM_CASC_PRODUCT = "CM_CASCProductCode";
    public static final String CM_CLAIMANT = "CM_Claimant";
    public static final String CM_COUNTRY = "CM_Country";
    public static final String CM_CURRENCY = "CM_Currency";
    public static final String CM_HS_CODE = "CM_HsCode";
    public static final String CM_INHOUSE_CODE = "CM_InhouseCode";
    public static final String CM_LOCATION_CODE = "CM_LocationCode";
    public static final String CM_PARTY = "CM_Party";
    public static final String CM_PORT = "CM_Port";
    public static final String CM_TEXTILE_CATEGORY = "CM_TextileCategory";
    public static final String CM_UNIT_CODE = "CM_UnitCode";
    public static final String CM_USER_PROFILE = "CM_UserProfile";
    public static final String CM_COMPANY_PROFILE = "CM_CompanyProfile";
    public static final String CM_AUDIT_TRAIL = "CM_AuditTrail";
    public static final String CM_TXC_PROFILE = "CM_TxcProfile";
    public static final String CM_CHARGE_CODE = "CM_ChargeCode";
    public static final String CM_BILL = "CM_Bill";
    public static final String CM_ADVANCE_HS_CODE = "CM_Advance_HsCode";

    public static final String CM_USER_REF_EXCEL_UPLOAD = "CM_User_Ref_Excel_Upload";
    public static final String CM_USER_REF_EXCEL_INSERT = "CM_User_Ref_Excel_Insert";
    public static final String CM_USER_REF_EXCEL_REJECT = "CM_User_Ref_Excel_Reject";
    public static final String CM_USER_REF_EXCEL_QUERY = "CM_User_Ref_Excel_Query";
    public static final String CM_USER_REF_EXCEL_SKIP = "CM_User_Ref_Excel_Skip";
    public static final String CM_USER_REF_EXCEL_ERROR = "CM_User_Ref_Excel_Error";

    public static final String CM_ADD_ON_SERVICES = "CM_AddOnServices";
    public static final String CM_HS_CLASSIFICATION_SERVICES = "CM_HSClassifcationServices";
    public static final String CM_ADD_ON_SERVICES_ADMIN = "CM_AddOnServicesAdmin";

    public static final String EPA_SERVICE_USER_SETUP_ACCESS = "EPAServiceUserSetupAccess"; //User self-setup Access Return
    public static final String EPA_SERVICE_USER_SETUP_PERMIT = "EPAServiceUserSetupPermit"; //User self-setup Permit Return

    public static final String EPA_SERVICE_USER_SETUP_ECO_PREP = "EPAServiceUserSetupECOPrep"; //User self-setup ECO Prep

    public static final String EPA_SERVICE_USER_SETUP = "EPAServiceUserSetup"; //User self-setup
    public static final String EPA_SERVICE_ADMIN = "EPAServiceAdmin";
    public static final String EPA_SERVICE_LISTFILES = "EPAServiceListFiles";
    public static final String EPA_SERVICE_AUDIT = "EPAServiceAudit";

    public static final String EPA_SERVICE_ACCOUNT_ADMIN = "EPAServiceAccountAdmin"; //Administer Accounts

    public static final String EPA_SYSTEM_PROPERTIES = "EPASystemProperties";
    public static final String EPA_SCHEDULER_CONFIG = "EPASchedulerConfig";

    public static final String INT_RESPONSE = "IntResponse";
    public static final String CM_ADDITIONAL_PROCEDURE_CODE = "CM_ADDITIONAL_PROCEDURE_CODE";

    public static final String RESTORE_JOBS = "Restore_Jobs";

    // Menu Selection (Announcements)
    public static final String CM_ANNOUNCEMENT = "CM_Announcement";

    // Menu Selection (Announcements)
    public static final String CM_VOTE = "CM_Vote";

    // Menu Selection (User Management)
    public static final String CM_USER_MANAGEMENT = "CM_User_Management";


    //Menu Selection (System Properties)
    public static final String CM_SYSTEM_PROPERTIES = "CM_System_Properties";

    //Menu Selection (Map Config)
    public static final String CM_MAP_CONFIG = "CM_MAP_CONFIG";

    // Menu Selection (Response Search)
    public static final String RESPONSE = "All";
    public static final String RESPONSE_TYPE = "responseType";
    public static final String PERMIT = "Permit";
    public static final String PERMIT_OTHER = "Permit_Other"; // Permit received from Other Declarant as Additional Recipient
    public static final String PERMIT_ADMIN = "PermitAdmin";
    public static final String FEE_MESSAGE = "feeMsg";
    public static final String STATUS_APPROVE = "Approve";
    public static final String STATUS_REJECT = "Reject";
    public static final String STATUS_QUERY = "Query";
    public static final String ERROR_MESSAGE = "errorMsg";
    public static final String CANCELLED_RECORD = "cancelledRecord";
    public static final String SUBMITTED_DECLARATION = "submittedDecl";
    public static final String GST_DUTY_PAYMENT = "gstDutyPay";
    public static final String PERMIT_CONDITION = "permitCond";
    public static final String PERMIT_RECONCILIATION = "permitRecon";
    public static final String PERMIT_REPORT_WIZARD = "permitReportWizard";
    public static final String USER_STATISTIC = "userStatistic";
    public static final String SUBMITTED_DECLARATION_ADMIN = "submittedDeclAdmin";
    public static final String GST_DUTY_PAYMENT_ADMIN = "gstDutyPayAdmin";
    public static final String PERMIT_CONDITION_ADMIN = "permitCondAdmin";
    public static final String USER_STATISTIC_ADMIN = "userStatisticAdmin";
    public static final String SCHEDULE_REPORT_ADMIN = "scheduleReportAdmin";
    public static final String AUDIT_TRAIL = "auditTrail";
    public static final String GENERIC_REPORT = "genericReport";
    public static final String DEC_TO_INSURANCE = "DecToInsurance";
    public static final String SCHEDULE_REPORT = "scheduleReport";
    public static final String SUBSCRIBED_ACCOUNT = "subscribedAccount";
    public static final String AS2_STGAF = "as2STGAF"; // for ST - Global Air - AS2 message flow
    public static final String VIEW_DECLARATIONS = "ViewDeclarations";

    // Update Type
    public static final String SEARCH_AMEND = "SearchAmend";
    public static final String SEARCH_CANCEL = "SearchCancel";
    public static final String SEARCH_REFUND = "SearchRefund";

    // Dec Type
    public static final String SEARCH_DRAFT = "Draft";
    public static final String SEARCH_OUTBOX = "Outbox";
    public static final String SEARCH_SENT = "Sent";

    //Declaration File Upload
    public static final String DEC_UPLOAD = "DecUpload";

    // CMD
    public static final String SEARCH_CMD = "CMD";
    public static final String BTN_CMD_EXPORT = "CMD Export";

    // Dec Status Type
    public static final String INCOMPLETE = "I";
    public static final String COMPLETE = "C";
    public static final String SENT = "S";
    public static final String RECEIVED = "R";
    public static final String UNRECEIVED = "U";
    public static final String SENDFAIL = "SF";
    public static final String RECEIVEFAIL = "RF";
    public static final String PENDING = "PG";
    public static final String PROCESSING = "P";

    public static final String INTEGRATOR_RECEIVE = "INT_R";
    public static final String INTEGRATOR_SEND = "INT_S";
    public static final String INTEGRATOR_AUTO_RECOVER = "INT_A_R";
    public static final String RETRIEVE_FROM_INSURANCE = "RETRIEVE_FROM_INSURANCE";
    public static final String SEND_TO_INSURANCE = "SEND_TO_INSURANCE";

    //TODO: Customize for AS2 (To be removed when SBS takes over)
    public static final String AS2_RECEIVE = "AS2_R";

    // Transport Modes
    public static final String SEA_MODE = "1";
    public static final String RAIL_MODE = "2";
    public static final String ROAD_MODE = "3";
    public static final String AIR_MODE = "4";
    public static final String MAIL_MODE = "5";
    public static final String MULTI_MODE = "6";
    public static final String PIPELINE_MODE = "7";

    // Scheme Indicator Codes
    public static final String PARTY_SCHEME_MES = "MES";
    public static final String PARTY_SCHEME_AISS = "AISS";
    public static final String PARTY_SCHEME_IGDS = "IGDS";

    // Update Indicator Codes
    public static final String UPDATE_AME = "AME";
    public static final String UPDATE_CNL = "CNL";
    public static final String UPDATE_FULL_REFUND = "FRF";
    public static final String UPDATE_PART_REFUND_GEN = "PRG";
    public static final String UPDATE_PART_REFUND_SPECIFIC = "PRS";
    public static final String UPDATE_AMR = "AMR";

    // Condition Codes
    public static final String CONDITION_CODE_C6 = "C6";
    public static final String CONDITION_CODE_Y95 = "Y95";
    public static final String CONDITION_CODE_Z02 = "Z02";
    public static final String CONDITION_CODE_G1 = "G1";
    public static final String CONDITION_CODE_G7 = "G7";
    public static final String CONDITION_CODE_GF = "GF";
    public static final String CONDITION_CODE_C6_DESC = "C6";
    public static final String CONDITION_CODE_Y95_DESC = "Y95";
    public static final String CONDITION_CODE_Z02_DESC = "Z02";
    public static final String CONDITION_CODE_G1_DESC = "G1: Subject to Payment";
    public static final String CONDITION_CODE_G7_DESC = "G7: Declarant's Agent Account";
    public static final String CONDITION_CODE_GF_DESC = "GF: Importer's Account";

    //Email Permit related
    public static final String PERMIT_NO = "PERMIT_NO";
    public static final String NAME1 = "NAME1";
    public static final String NAME2 = "NAME2";
    public static final String NAME3 = "NAME3";
    public static final String IN_MASTER_SHIP_NO = "IN_MASTER_SHIP_NO";
    public static final String OUT_MASTER_SHIP_NO = "OUT_MASTER_SHIP_NO";

    public static final String CERT_NO = "CERT_NO";
    public static final String APPROVE_DATE = "APPROVE_DATE";

    public static final String IN_HOUSE_SHIPDOC_NO = "IN_HOUSE_SHIPDOC_NO";
    public static final String OUT_HOUSE_SHIPDOC_NO = "OUT_HOUSE_SHIPDOC_NO";

    // Table headings
    public static final String[] CHARGE_CODE_HEADER = { "ChargeCode", "ChargeKey", "Description", "AppId", "PartnerId" }; //add chargeKey
    public static final String[] BILL_HEADER = { "ID", "TransId", "ChargeCode", "ChargeKey", "DateTime", "Quantity" }; //add chargeKey

    public static final String[] BRAND_MODEL_HEADER = { "Code", "Brand", "Model" };
    public static final String[] CASC_PRODUCT_HEADER = { "Code", "HS Code", "Unit", "Description" };
    public static final String[] CLAIMANT_HEADER = { "Code", "Party CR No.", "Party UEI", "Name1", "Claimant ID", "Claimant Name1" };
    public static final String[] COUNTRY_HEADER = { "Code", "Description" };
    public static final String[] CURRENCY_HEADER = { "Code", "Description", "ExcRate", "Rate Unit", "Country" };
    public static final String[] HSCODE_HEADER = { "HS Code", "Item Description", "Unit"};
    public static final String[] INHOUSE_HEADER = { "Item Code", "HS Code", "Description", "Unit" };
    public static final String[] LOCATION_HEADER = { "Code", "Description" };
    public static final String[] PARTY_HEADER = { "Code", "Party CR No.", "Party UEI", "Name1", "Address1", "MES", "AISS", "IGDS" };
    public static final String[] PORT_HEADER = { "Code", "Description" };
    public static final String[] TEXTILE_CODE_HEADER = { "Cat. Code", "Cty Code", "QuotaInd.", "Description", "Unit" };
    public static final String[] UNIT_CODE_HEADER = { "Code", "Description" };
    public static final String[] AUDITTRAIL_HEADER = { "User Id", "Module", "Date Time", "Action" };
    public static final String[] INT_RESPONSE_HEADER = { "User Id", "Job No", "Permit No", "Create Date" };
    public static final String[] ANNOUNCEMENT_HEADER = {"Title", "Description", "Show Indicator", "Posting Date", "Expiry Date","Priority"};
    public static final String[] SYSTEM_PROPERTIES_HEADER = {"Category", "Key", "Value"};
    public static final String[] USER_ADMIN_CONFIG_HEADER = {"User Id", "CR UEI No", "Name1", "Host To Host", "SRC Protocol", "Host Sender ID", "MHX Profile", "Suspend Indicator"};
    public static final String[] MAP_CONFIG_HEADER = {"Map ID", "User ID", "UEI No", "Map Description", "Map Config Path", "SRC Format", "SRC Protocol"};
    public static final String[] RESTORE_JOBS_HEADER = {"User ID", "Job No", "CR UEI No", "Car Type", "Dec Status", "Msg Status", "Create Date", "Submit Date"};

//    public static final String[] DEC_SEARCH_HEADER = { "Job No", "Departure Date<br><font color='red'>Date already past</font><br><font color='#ff6600'>Date same as Today</font>",
//        "Option", "User ID", "Msg Type", "Dec Type", "User Ref. No.", "BL/AWB" };
    public static final String[] DEC_SEARCH_HEADER = { "Job No", "Departure Date<br><font color='red'>Date already past</font><br><font color='#ff6600'>Date same as Today</font>",
        "Option", "User ID", "Msg Type", "Dec Type", "User Ref. No.", "BL/AWB", "Party Name" };
    // For Excel Upload Table Results
    public static final String[] EXCEL_CASC_CODE_HEADERS = {"Item Code", "HS Code", "Unit Code", "Description 1", "Description 2", "Description 3", "Description 4", "Description 5"};
    public static final String[] EXCEL_LOCATION_HEADERS = {"Location Code", "Description"};

    // Table and Excel header different. Table header needs to shorten
    public static final String[] EXCEL_PARTY_CODE_HEADERS = {"Party CR No", "Party UEN No", "Code", "Name 1", "Name 2", "Name 3", "Address 1", "Address 2", "Address 3", "City Name", "Country Code", "Postal Code", "Country Subdivison Code", "Country Subdivison Name", "Major Exporter"};
    public static final String[] EXCEL_PARTY_CODE_TABLE_HEADERS = {"CR No", "UEN No", "Code", "Name1", "Name2", "Name3", "Addr1", "Addr2", "Addr3", "City", "Country Code", "Postal Code", "Country Sub Code", "Country Sub Name", "ME Ind"};
    public static final String[] EXCEL_INHOUSE_ITEM_HEADERS = {"Item Code", "HS Code", "Item Description", "CASC Product Code", "Unit of Measurement", "Brand", "Model", "Country of Origin Code", "HS UOM", "CASC Code 1", "CASC Code 2", "CASC Code 3"};
    public static final String[] EXCEL_INHOUSE_ITEM_TABLE_HEADERS = {"Item Code", "HS Code", "Item Desc", "CASC Prod Code", "UOM", "Brand", "Model", "Cty of Origin Code", "HS UOM", "CASC Code 1", "CASC Code 2", "CASC Code 3"};

    // For ST - Global Air - AS2 Message flow
    public static final String[] AS2_STGAF_SEARCH_HEADER = { "File Name", "Message Type","Date Time"};

    // For Vote
    public static final String[] VOTE_HEADER = {"ID", "Title", "Description", "Posting Date", "Expiry Date","Priority"};

    public static final String[] SCHEDULE_JOB_HEADER = { "Select", "Report Type", "Template", "Next Run Time", "Status", "Email Recipient" };

    //
    public static final String[] PRR_SCHEDULE_REPORTS_HEADER = { "Select", "Schedule Type", "Config Name", "Next Run Time", "Status", "Email Recipient(s)" };

    public static final String[] RESPONSE_SEARCH_HEADER = { "Job No", "Option", "User ID", "Msg Type", "Dec Type", "User Ref. No.", "Response Type", "BL/AWB", "Party Name" };

    public static final String[] JOB_HEADER_COLUMNS = { "USER_ID", "JOB_NO", "JOB_DATE", "JOB_SEQ_NO", "CR_UEI_NO", "CAR_TYPE", "DEC_TYPE", "DEC_STATUS", "MSG_STATUS", "UPDATE_PERMIT_NO",
        "UPDATE_CERT_NO", "CREATE_DATE", "LASTMODIFIED_DATE", "SUBMIT_DATE", "RECV_DATE", "APPROVED_DATE", "MSG_REFERENCE", "INTERCHANGE", "UPDATE_IND_CODE", "UPDATE_COUNT", "REPL_PERMIT_NO",
        "PERMIT_VALIDITY_EXT_IND", "CANCEL_REASON_CODE", "CO_IND", "LIC_IND", "BG_TYPE", "PREV_PERMIT_NO", "ADD_RECIPIENT_MBX1", "ADD_RECIPIENT_MBX2", "ADD_RECIPIENT_MBX3", "AMENDED_FIELDS_COUNT",
        "LAST_USER_ID", "LAST_JOB_NO", "CANCELLED_IND", "RESPONSE_TYPE", "CARGO_PACK_TYPE", "IN_TRANS_MODE", "OUT_TRANS_MODE" };

    public static final String[] SEND_TRANS_LOG_HEADER = { "Date Sent", "User Id", "JobNo. List", "Msg Type", "Transmission Status", "Recipient", "Interchange", "Error Description" };
    public static final String[] RECEIVE_TRANS_LOG_HEADER = { "Date Received", "User Id", "JobNo. List", "Msg Type", "Transmission Status", "Interchange", "Error Description" };
    public static final String[] REPORT_RESULT_HEADER = { "Job No", "User ID", "Msg Type", "Dec Type", "Update Indi. Code", "Party Name" };

    public static final String[] PRS_RESULT_HEADER = { "URN", "Permit No", "Message Type", "Declaration Type",
        "Permit Type", "Certificate Type", "Approval Date", "Importer EI", "Exporter EI", "Carrier Agent EI",
        "Declaration Agent EI", "Freight Forwarder EI", "Place of Release", "Place of Receipt", "Country of Origin",
        "Country of Destination", "Transport Mode", "User ID", "CPC", "Validity Start", "Validity End",
        "Last Application Date", "Importer Name", "Exporter Name", "Declaration Agent Name", "Handling Agent EI",
        "Handling Agent Name ", "Claimant EI ", "Claimant Name", "Freight Forwarder EI", "Freight Forwarder Name",
        "Depart Date", "Total Gross Weight", "Total Gross Weight UOM", "Total Out Pack", "Total Out Pack UOM",
        "Total Customs Duty Amount", "Total Excise Duty Amount", "Total GST Amount", "Total Paid", "End User Name",
        "End User Address", "Tow Vessel Name", "AWB BL", "Replacement Permit No", "Version", "Declarant Name",
        "PassPort / IC No", "Submission Date", "Port Code", "Port Name", "Discharge Port Code", "Discharge Port Name",
        "Next Port Code", "Next Port Name", "Final Port Code", "Final Port Name", "Licence No", "Remarks",
        "IN Conveyance Reference No", "OUT Conveyance Reference No", "Total Charge ", "Arrival Date ", "IN AWB BL",
        "OUT AWB BL ", "IN Transport ID", "OUT Transport ID", "OUT Transport Mode", "Consignee Name",
        "Consignee Address", "AED Compliance", "Item Seq. No", "HS Code", "Quantity", "UOM",
        "Item Description", "Marking", "Country of Origin", "MAWB", "HAWB", "Current Lot No", "Previous Lot No",
        "Brand Name", "Model Name", "Duty Quantity/Weight Vol", "Duty Quantity/Weight UOM", "Unit Price",
        "Customs Duty Amount", "Excise Duty Amount", "CIF/FOB Value", "GST Amt", "LSP Value",
        "Refund Customs Amount", "Refund Excise Amount", "Refund GST Amount", "In Most Pack", "In Most Pack UOM",
        "In Pack", "In Pack UOM", "Out Pack", "Out Pack UOM", "Pref Ind", "Total Duty Quantity", "Total Duty UOM",
        "Unit Price Currency", "Invoice No", "Marks", "AR Msg Id", "AR Message Type", "ACCESS Permit No",
        "Amend Request No", "Whole CNL", "MAWB/HWAB Seq No", "Shipper Name", "House AWB CIF/FOB",
        "MAWB/HWAB Sequence No", "No Show","Purpose Indicator"};

    // Select Box Options
    public static final String[][] OFFICIAL_DOC_LIST = { { "S", "S-Singaporean" }, { "T", "T-Singaporean" }, { "M", "M-Malaysian" }, { "P", "P-Passport" } };

    public static final String[][] COMPANY_USER_LIST = { { "Y", "Company" }, { "N", "User" } };

    public static final String[][] YES_NO_LIST = { { "Y", "Yes" }, { "N", "No" } };
    public static final String[][] EMAIL_LIST = {};

    // Used for converting to Boolean (1 - True, 0 - False)
    public static final String[][] YES_NO_LIST2 = { { "1", "Yes" }, { "0", "No" } };

    // Used for Boolean.parseBoolean() which returns boolean primitive
    public static final String[][] YES_NO_BOOLEAN_LIST = { { "True", "Yes" }, { "False", "No" } };

    //Used for Boolean.parseBoolean() which returns boolean primitive
    public static final String[][] YES_NO_BOOLEAN_LIST_LOWCASE = { { "true", "Yes" }, { "false", "No" } };

    // Used for SonyInterface (where PackType is not mapped)
    public static final String[][] PACK_TYPE_LIST = { { "5", "OTHER NON-CONTAINERIZED" }, { "9", "CONTAINERIZED" }, };

    public static final String[][] DATASOURCE_LIST = { { DATASOURCE_TRW, "TradeWeb DataSource" }, { DATASOURCE_PRS, "PRS DataSource" }, { DATASOURCE_PFK, "PFK DataSource" } };

    //////////////////////////////////////////////////
    // Used for Billing
    //////////////////////////////////////////////////
    public static final String[][] ID_TYPE_LIST = { { "A", "Account Id" }, { "U", "User Id" } };

    public static final String[][] BILL_TRANS_TYPE_LIST = { { "D", "Debit" }, { "C", "Credit" } };

    public static final String[][] BILL_PAY_STATUS_LIST = { { "PAID", "PAID" }, { "UNPAID", "UNPAID" } };

    public static final String[][] BILL_PAY_MODE_LIST = { { "CC", "Credit Card" }, { "MAP", "Cash / Cheque" }, { "GIR", "Interbank Giro" } };
    //////////////////////////////////////////////////

    public static final String[][] GST_RATE_LIST = { { "2", "2" }, { "5", "5" }, { "7", "7" } };

    public static final String[][] DIRECTION_LIST = { { "IN", "Inward" }, { "OUT", "Outward" } };

    public static final String[][] PARTY_TYPE_LIST = { { "FW", "Freight Forwarder" }, { "IG", "Inward Carrier Agent" }, { "OG", "Outward Carrier Agent" }, { "IM", "Importer" }, { "EX", "Exporter" },
        { "CN", "Consignee" }, { "CC", "Claimant" }, { "UC", "End User" }, { "MF", "Manufacturer" }, { "AH", "Handling Agent" } };

    // For Use with Amend, Cancel and Refund
    public static final String[][] PMT_CAR_TYPE_LIST = { { "COODCI", "COODCI: Certificate of Origin" }, { "INPPMT", "INPPMT: In-non-payment" }, { "INPUPT", "INPUPT: In-non-payment Update" },
        { "IPTPMT", "IPTPMT: In-payment" }, { "IPTUPT", "IPTUPT: In-payment Update" }, { "OUTPMT", "OUTPMT: Out (with/without Certificate of Origin)" },
        { "OUTUPT", "OUTUPT: Out (with/without Certificate of Origin) Update" }, { "TNPPMT", "TNPPMT: Transhipment / Movement" }, { "TNPUPT", "TNPUPT: Transhipment / Movement Update" } };

    public static final String[][] SRC_DOCUMENT_LIST = {
        {"PR", "Permit Return"},
        {"AR", "ACCESS Return"}
    };
    public static final String CAR_IPTDEC = "IPTDEC";
    public static final String CAR_INPDEC = "INPDEC";
    public static final String CAR_OUTDEC = "OUTDEC";
    public static final String CAR_TNPDEC = "TNPDEC";
    public static final String CAR_COODEC = "COODEC";
    public static final String CAR_IPTUPD = "IPTUPD";
    public static final String CAR_INPUPD = "INPUPD";
    public static final String CAR_OUTUPD = "OUTUPD";
    public static final String CAR_TNPUPD = "TNPUPD";

    public static final String CAR_DEC = "DEC"; // TODO For Sony Upload, CarType is set as DEC

    public static final String[][] DEC_CAR_TYPE_LIST = { { "COODEC", "COODEC: Certificate of Origin" }, { "INPDEC", "INPDEC: In-non-payment" }, { "INPUPD", "INPUPD: In-non-payment Update" },
        { "IPTDEC", "IPTDEC: In-payment" }, { "IPTUPD", "IPTUPD: In-payment Update" }, { "OUTDEC", "OUTDEC: Out (with/without Certificate of Origin)" },
        { "OUTUPD", "OUTUPD: Out (with/without Certificate of Origin) Update" }, { "TNPDEC", "TNPDEC: Transhipment / Movement" }, { "TNPUPD", "TNPUPD: Transhipment / Movement Update" } };

    public static final String[][] CAR_TYPE_LIST = { { "COODCI", "COODCI: Certificate of Origin" }, { "COODEC", "COODEC: Certificate of Origin" }, { "INPDEC", "INPDEC: In-non-payment" },
        { "INPPMT", "INPPMT: In-non-payment" }, { "INPUPD", "INPUPD: In-non-payment Update" }, { "INPUPT", "INPUPT: In-non-payment Update" }, { "IPTDEC", "IPTDEC: In-payment" },
        { "IPTPMT", "IPTPMT: In-payment" }, { "IPTUPD", "IPTUPD: In-payment Update" }, { "IPTUPT", "IPTUPT: In-payment Update" }, { "OUTDEC", "OUTDEC: Out (with/without Certificate of Origin)" },
        { "OUTPMT", "OUTPMT: Out (with/without Certificate of Origin)" }, { "OUTUPD", "OUTUPD: Out (with/without Certificate of Origin) Update" },
        { "OUTUPT", "OUTUPT: Out (with/without Certificate of Origin) Update" }, { "TNPDEC", "TNPDEC: Transhipment / Movement" }, { "TNPPMT", "TNPPMT: Transhipment / Movement" },
        { "TNPUPD", "TNPUPD: Transhipment / Movement Update" }, { "TNPUPT", "TNPUPT: Transhipment / Movement Update" } };
    public static final String[][] CAR_TYPE_LIST_FOR_INSURANCE = { { "INPDEC", "INPDEC: In-non-payment" }, { "INPPMT", "INPPMT: In-non-payment" }, { "INPUPD", "INPUPD: In-non-payment Update" },
        { "INPUPT", "INPUPT: In-non-payment Update" }, { "IPTDEC", "IPTDEC: In-payment" }, { "IPTPMT", "IPTPMT: In-payment" }, { "IPTUPD", "IPTUPD: In-payment Update" },
        { "IPTUPT", "IPTUPT: In-payment Update" }, { "OUTDEC", "OUTDEC: Out (with/without Certificate of Origin)" }, { "OUTPMT", "OUTPMT: Out (with/without Certificate of Origin)" },
        { "OUTUPD", "OUTUPD: Out (with/without Certificate of Origin) Update" }, { "OUTUPT", "OUTUPT: Out (with/without Certificate of Origin) Update" }, };
    public static final String[][] DEC_TYPE_LIST = { { "DUT", "IPT-DUT: Duty" }, { "GST", "IPT-GST: GST (including Duty Exemption)" }, { "DNG", "IPT-DNG: Duty and GST" },
        { "BKT-IPT", "IPT-BKT: Blanket (including blanket GST payment and duty exemption)" },

        { "SFZ", "INP-SFZ: Storage in FTZ" }, { "APS-INP", "INP-APS: Approved Premises / Schemes" }, { "BKT-INP", "INP-BKT: Blanket (including blanket GST relief and duty exemption)" },
        { "GTR", "INP-GTR: GST Relief and duty exemption" }, { "SHO", "INP-SHO: Shut-out" }, { "DES", "INP-DES: Destruction" }, { "REX", "INP-REX: Re-export" },
        { "TCS-INP", "INP-TCS: Temporary Import for exhibition / auctions WITH sales" }, { "TCR-INP", "INP-TCR: Temporary Import for repairs" },
        { "TCE-INP", "INP-TCE: Temporary Import for exhibition / auctions WITHOUT sales" }, { "TCO-INP", "INP-TCO: Temporary Import for other purposes" },
        { "TCI-INP", "INP-TCI: Temporary Export / Re-imported goods" },

        { "DRT", "OUT-DRT: Direct (including storage in FTZ)" }, { "BKT-OUT", "OUT-BKT: Blanket" }, { "APS-OUT", "OUT-APS: Approved Premises / Schemes" },
        { "TCS-OUT", "OUT-TCS: Temporary Import for exhibition / auctions WITH sales" }, { "TCR-OUT", "OUT-TCR: Temporary Import for repairs" },
        { "TCE-OUT", "OUT-TCE: Temporary Import for exhibition / auctions WITHOUT sales" }, { "TCO-OUT", "OUT-TCO: Temporary Import for other purposes" },
        { "TCI-OUT", "OUT-TCI: Temporary Export / Re-imported goods" },

        { "TTI", "TNP-TTI: Thru Transhipment with Inter-gateway movement" }, { "TTF", "TNP-TTF: Thru Transhipment within same FTZ" }, { "IGM", "TNP-IGM: Inter-gateway movement" },
        { "REM", "TNP-REM: Removal" }, { "BRE", "TNP-BRE: Blanket Removal" } };

    public static final String[][] AR_MSG_TYPE_LIST = { { "NEW", "New ACCESS Permit" },
        { "AME", "Amendment or Partial Cancellation" }, { "CNL", "Full Cancellation" } };
    public static final String[][] AR_PURPOSE_IND_LIST = { { "E", "Export" },
        { "I", "Import" } };
    public static final String[][] IP_DEC_TYPE_LIST = { { "DUT", "IPT-DUT: Duty" }, { "GST", "IPT-GST: GST (including Duty Exemption)" }, { "DNG", "IPT-DNG: Duty and GST" },
        { "BKT", "IPT-BKT: Blanket (including blanket GST payment and duty exemption)" } };

    public static final String[][] IPT_DEC_TYPE_LIST = { { "DUT", "DUT: Duty" }, { "GST", "GST: GST (including Duty Exemption)" }, { "DNG", "DNG: Duty and GST" },
        { "BKT", "BKT: Blanket (including blanket GST payment and duty exemption)" } };

    public static final String[][] INP_DEC_TYPE_LIST = { { "SFZ", "SFZ: Storage in FTZ" }, { "APS", "APS: Approved Premises / Schemes" },
        { "BKT", "BKT: Blanket (including blanket GST relief and duty exemption)" }, { "GTR", "GTR: GST Relief and duty exemption" }, { "SHO", "SHO: Shut-out" }, { "DES", "DES: Destruction" },
        { "REX", "REX: Re-export" }, { "TCS", "TCS: Temporary Import for exhibition / auctions WITH sales" }, { "TCR", "TCR: Temporary Import for repairs" },
        { "TCE", "TCR: Temporary Import for exhibition / auctions WITHOUT sales" }, { "TCO", "TCO: Temporary Import for other purposes" }, { "TCI", "TCI: Temporary Export / Re-imported goods" } };

    public static final String[][] OUT_DEC_TYPE_LIST = { { "DRT", "DRT: Direct (including storage in FTZ)" }, { "BKT", "BKT: Blanket" }, { "APS", "APS: Approved Premises / Schemes" },
        { "TCS", "TCS: Temporary Import for exhibition / auctions WITH sales" }, { "TCR", "TCR: Temporary Import for repairs" },
        { "TCE", "TCR: Temporary Import for exhibition / auctions WITHOUT sales" }, { "TCO", "TCO: Temporary Import for other purposes" }, { "TCI", "TCI: Temporary Export / Re-imported goods" } };

    public static final String[][] TNP_DEC_TYPE_LIST = { { "TTI", "TTI: Thru Transhipment with Inter-gateway movement" }, { "TTF", "TTF: Thru Transhipment within same FTZ" },
        { "IGM", "IGM: Inter-gateway movement" }, { "REM", "REM: Removal" }, { "BRE", "BRE: Blanket Removal" } };

    public static final String[][] DOCUMENT_TYPE_LIST = { { "PMT", "Permit" }, { "AME", "Amendment" } };

    public static final String[][] CONDITION_CODE_LIST = { { CONDITION_CODE_C6, CONDITION_CODE_C6_DESC }, { CONDITION_CODE_Y95, CONDITION_CODE_Y95_DESC },
        { CONDITION_CODE_Z02, CONDITION_CODE_Z02_DESC } };

    public static final String[][] CONDITION_CODE_FOR_GST = { { CONDITION_CODE_G1, CONDITION_CODE_G1_DESC }, { CONDITION_CODE_G7, CONDITION_CODE_G7_DESC },
        { CONDITION_CODE_GF, CONDITION_CODE_GF_DESC } };

    // Reports Constants TODO
    // GST Report
    public static final String[][] GST_RPT_CAR_TYPE_LIST = { { "INPPMT", "INPPMT: In-non-payment" }, { "INPUPT", "INPUPT: In-non-payment Update" }, { "IPTPMT", "IPTPMT: In-payment" },
        { "IPTUPT", "IPTUPT: In-payment Update" } };

    public static final String[][] GST_RPT_DEC_TYPE_LIST = { { "DUT", "IPT-DUT: Duty" }, { "GST", "IPT-GST: GST (including Duty Exemption)" }, { "DNG", "IPT-DNG: Duty and GST" },
        { "BKT-IPT", "IPT-BKT: Blanket (including blanket GST payment and duty exemption)" },

        { "SFZ", "INP-SFZ: Storage in FTZ" }, { "APS-INP", "INP-APS: Approved Premises / Schemes" }, { "BKT-INP", "INP-BKT: Blanket (including blanket GST relief and duty exemption)" },
        { "GTR", "INP-GTR: GST Relief and duty exemption" }, { "SHO", "INP-SHO: Shut-out" }, { "DES", "INP-DES: Destruction" }, { "REX", "INP-REX: Re-export" },
        { "TCS-INP", "INP-TCS: Temporary Import for exhibition / auctions WITH sales" }, { "TCR-INP", "INP-TCR: Temporary Import for repairs" },
        { "TCE-INP", "INP-TCE: Temporary Import for exhibition / auctions WITHOUT sales" }, { "TCO-INP", "INP-TCO: Temporary Import for other purposes" },
        { "TCI-INP", "INP-TCI: Temporary Export / Re-imported goods" } };

    public static final String[][] CR_UEN_LIST = { { "pCrNo", "CR No: Company Registration Number" }, { "pUEI", "UEI: Unique Entity Identifier" } };

    // Integrator Features list for each User
    public static final String[][] INT_USER_FEATURE_LIST = { { "INTEGRATOR", "Identify this UserId as an Integrator User" }, { "DUNS_NO", "DUNS number for this Integrator User (Rosettanet Gateway)" },
        { "TXC_TO_TRW4_ID", "TradeXchange ID to be mapped to this TradeWeb Live ID" }, { "PERMIT_EMAIL", "Email Address for Receiving Permits" },
        { "EXCH_RATE_EMAIL", "Email Address for Receiving Exchange Rate files" } };

    // Integrator Features list for each Account (Company)
    public static final String[][] INT_ACCOUNT_FEATURE_LIST = { { "MHUB_PARENT_ID", "Mhub ID that will receive all Responses from this Account" },
        { "BILL_INCOMING", "Bill all incoming transactions from this account (partial / fully completed Declarations)" }, { "EMAIL", "Email Address for this Account" },
        { "MAP_PROD_CODE", "Auto-default CASC Product Code if HS Code is a Controlled Item" }, { "TN31_RECEIVER", "Send Generated TN31 format Response Xml to this Account" } };

    // Announcement Show Indicator list
    public static final String[][] ANNOUNCEMENT_SHOW_INDICATOR = {{"-1","Any"}, {"1","1: Show User"},{"2","2: Show Public"},{"3","3: Show All"},{"4","4: What's New"},
    	{"5","5: Suspend Message"},{"6","6: Priority Message"}};
    public static final String[][] ANNOUNCEMENT_SHOW_INDICATOR_SELECTION = {{"1","1: Show User"},{"2","2: Show Public"},{"3","3: Show All"},{"4","4: What's New"},
        	{"5","5: Suspend Message"},{"6","6: Priority Message"}};

    // User Reference Codes Type
    //public static final String[][] USER_REFERENCE_CODES_TYPE = { { CM_BRAND_MODEL, "BRAND MODEL CODES" }, { CM_CASC_PRODUCT, "CASC PRODUCT CODES" }, { CM_CLAIMANT, "CLAIMANT PARTY CODES"}, { CM_INHOUSE_CODE, "INHOUSE ITEM CODES" }, { CM_LOCATION_CODE,"LOCATION CODES"}, { CM_PARTY, "PARTY CODES" } };
    public static final String[][] USER_REFERENCE_CODES_TYPE = { { CM_CASC_PRODUCT, "CASC PRODUCT CODES" }, { CM_INHOUSE_CODE, "INHOUSE ITEM CODES" }, { CM_LOCATION_CODE,"LOCATION CODES"}, { CM_PARTY, "PARTY CODES" } };

    public static final String[][] ADMIN_TABLES = {{"TTRW4_JOB_HEADER", "Header"},
        {"TTRW4_PARTY", "Party"},
        {"TTRW4_CERT_HEADER", "Certificate"},
        {"TTRW4_CARGO_HEADER", "Cargo Header"},
        {"TTRW4_CARGO_CTN", "Container"},
        {"TTRW4_IN_TRANSPORT", "In Transport"},
        {"TTRW4_OUT_TRANSPORT", "Out Transport"},
        {"TTRW4_INVOICE", "Invoice"},
        {"TTRW4_ITEM", "Item"},
        {"TTRW4_ITEM_CASC", "Item CASC"},
        {"TTRW4_ITEM_CASC_CASCID", "Item CASC ID"},
        {"TTRW4_ITEM_CERT", "Item Cert"},
        {"TTRW4_ITEM_PACK_DESC", "Item Pack"},
        {"TTRW4_ITEM_VEHICLE", "Item Vehicle"},
        {"TTRW4_LICENCE", "Licence"},
        {"TTRW4_SUPPORT_DOC", "Support Doc"},
        {"TTRW4_CUSPRO_CODES", "CPC"},
        {"TTRW4_CUSPRO_ADD_INFO", "CPC Add. Info"},
        {"TTRW4_SUMMARY", "Summary"},
        {"TTRW4_PERMIT", "Permit"},
        {"TTRW4_STATUS", "Status"},
        {"TTRW4_STATUS_APP_COND", "Status Approval"},
        {"TTRW4_STATUS_REJ_DTL", "Status Reject"},
        {"TTRW4_ERRORM", "ErrorM"},
        {"TTRW4_ERRORM_DTL", "ErrorM Detail"},
        {"TTRW4_APPROVAL_COND", "Approval Cond."},
        {"TTRW4_AME_INFO", "Amend Info"},
        {"TTRW4_UPDATE_SUM", "Update Summary"},
        {"TTRW4_REFUND", "Refund"},
        {"TTRW4_REFUND_ITEM", "Refund Item"},
        {"UserProfile", "User Profile"},
        {"AuditTrail", "Audit Trail"},
        {"TransmissionLog", "Transmission Log"},
        {"IntegratorRecover", "Integrator Recover"},
        {"Interface", "Interface"},
        {"DecUploadStatus","Dec Upload Status"},
        {"AddOnUsage", "AddOn Usage"},
        {"RetentionPlan", "Retention Plan"},
        {"QuerySendCustoms", "Query Send Customs"},
        {"QueryTxnCount", "Query Txn Count"}};

    public static final List<String> TXN_TABLES = Arrays.asList("TTRW4_PARTY", "TTRW4_CERT_HEADER", "TTRW4_CARGO_HEADER", "TTRW4_CARGO_CTN",
        "TTRW4_IN_TRANSPORT", "TTRW4_OUT_TRANSPORT", "TTRW4_INVOICE", "TTRW4_ITEM", "TTRW4_ITEM_CASC", "TTRW4_ITEM_CASC_CASCID", "TTRW4_ITEM_CERT",
        "TTRW4_ITEM_PACK_DESC", "TTRW4_ITEM_VEHICLE", "TTRW4_LICENCE", "TTRW4_SUPPORT_DOC", "TTRW4_CUSPRO_CODES", "TTRW4_CUSPRO_ADD_INFO",
        "TTRW4_SUMMARY", "TTRW4_PERMIT", "TTRW4_STATUS", "TTRW4_STATUS_APP_COND", "TTRW4_STATUS_REJ_DTL", "TTRW4_ERRORM", "TTRW4_ERRORM_DTL",
        "TTRW4_APPROVAL_COND", "TTRW4_AME_INFO", "TTRW4_UPDATE_SUM", "TTRW4_REFUND", "TTRW4_REFUND_ITEM");

    public static final List<String> SCHEDULE_TYPES = Arrays.asList("DAILY", "WEEKLY", "MONTHLY");

    public static final String[][] JOB_HEADER_DATES = {{"CREATE_DATE", "Create Date"}, {"SUBMIT_DATE", "Submit Date"}, {"RECV_DATE", "Receive Date"}};

    // Checkbox parameter
    public static final String CHK_DELETE = "pChkDelete";
    public static final String CKL_SELECT = "pChkSelect";

    // Selected record
    public static final String SELECTED_RECORD = "pSelectedRec";
    public static final String RECORD = "Record";

    // Delimiter
    public static final String DELIMITER = "|";

    // Message
    public static final String MESSAGE = "pMessage";
    //for standard and smart search
    public static final String MESSAGE_1 = "pMessage1";
    public static final String MESSAGE_2 = "pMessage2";

    // TXC Password Expiry Reminder Message
    public static final String MESSAGE_TX_REMINDER = "reminderTXPwdMessage";
    public static final String MESSAGE_TRW_REMINDER = "reminderTRWPwdMessage";

    public static final int PWD_REMINDER_DAYS = 10; //TODO: Number of days to start daily Password reminder

    // Confirmation Box
    public static final String CONFIRM_MESSAGE = "pConfirm";

    // AED Unsent Job No
    public static final String AED_PENDING_SEND = "aedJobNoList";

    // Unread Only
    public static final String UNREAD_ONLY = "pUnreadOnly";

    // Display row
    public static final int MAX_CODE_ROW = 20;

    // GST Relief Amount
    public static final double GST_RELIEF_AMOUNT = 400;

    // Maximum number of CCP allowed to be printed in 1 selection
    public static final int MAX_CCP_PRINT = 20;

    public static final String GSTDUTY_PLUS_ACCESS = "gstDutyPlusAccess";
    public static final String BTN_GSTDUTY_PLUS_PDF = "Generate Report with HAWB";
    public static final String BTN_GSTDUTY_PLUS_EXCEL = "Download as Excel with HAWB";
    public static final String ARCHIVAL_SUBMENU = "ArchivalSubmenu";
    public static final String REPORT_ADMIN_SUBMENU = "ReportAdminSubmenu";
    public static final String EXPORT = "Export";
    public static final String IMPORT = "Import";
    public static final String RESTORE = "Restore";
    public static final String RESTORE_RE_TRIGGER = "RestoreReRrigger";
    public static final String AUTO_ARCHIVE_TO_FILES = "AutoArchiveToFiles";
    public static final String AUTO_DELETE = "AutoDelete";
    public static final String MANUAL_DELETE = "ManualDelete";
    public static final String RETENTION_PLAN = "RetentionPlan";
    public static final String BTN_UPDATE_RETENTION_PLAN = "Update Plan";
    public static final String RESTORATION_LOG = "RestorationLog";
    public static final String RESTORATION_ADHOC = "AdhocRestoration";
    public static final String BILL_RETENTION = "BillRetention";
    public static final String MANAGE_ARCHIVE_FILES = "ManageArchiveFiles";
    public static final String DOWNLOAD_ALL_ARCHIVE_FILES = "Download All Archive Files";
    public static final String FILENAME = "fileName";
    public static final String FILETYPE = "fileType";
    public static final String DELETE_ALL_ARCHIVE_FILES = "Delete All Archive Files";
    public static final String UPLOAD_ARCHIVE_FILES = "Upload Archive Files";

    public static final String ACCTID = "acctId";
    public static final String USERID = "userId";
    public static final String UEN = "uen";
    public static final String FROMJOBNO = "fromJobNo";
    public static final String TOJOBNO = "toJobNo";
    public static final String FROMDATE = "FromDate";
    public static final String TODATE = "ToDate";
    public static final String PERMITNO = "permitNo";
    public static final String DECDATATYPE = "responseDecs";
    public static final String TABLETYPE = "liveData";
    public static final String RESTORE_BY_SELECTION = "Restore by selection";
    public static final String RESTORE_BY_SELECTION_OVERWRITE = "Restore by selection with overwrite";
    public static final String RESTORE_BY_CRITERIA = "Restore by criteria";
    public static final String[][] DECDATATYPES = { { "false", "All Declarations" }, { "true", "Response Declarations only" } };
    public static final String[][] TABLETYPES = { { "false", "Archive tables" }, { "true", "Live tables" } };
    public static final String FILES = "files";
    public static final String FILE = "file";
    public static final String DIVERT_TO_ARCH = "divertToArch";
    public static final String RECORDS = "Records";
    public static final String RETAINDAYS = "retainDays";
    public static final String PWD = "pwd";
    public static final String RETENTION_PLAN_STATUS = "retentionPlanStatus";
    public static final String RESTORE_LOG_ID = "restoreLogId";

    public static String MSG_MANDATORY_RETAIN_DAYS = "Data Retention Period must be specified.";

    public static final String INSURANCE_URL = "INSURANCE_URL";

    /**************************** TN4.1 *************************************/
    // CAR types for Declaration and Updates
    public static final String IPTDEC_CAR_TYPE = "IPTDEC"; // In-payment
    public static final String INPDEC_CAR_TYPE = "INPDEC"; // In-nonpayment
    public static final String OUTDEC_CAR_TYPE = "OUTDEC"; // Outward
    public static final String TNPDEC_CAR_TYPE = "TNPDEC"; // Transhipment
    public static final String COODEC_CAR_TYPE = "COODEC"; // Certificate of Origin
    public static final String IPTUPD_CAR_TYPE = "IPTUPD"; // In-payment Update
    public static final String INPUPD_CAR_TYPE = "INPUPD"; // In-nonpayment Update
    public static final String OUTUPD_CAR_TYPE = "OUTUPD"; // Outward Update
    public static final String TNPUPD_CAR_TYPE = "TNPUPD"; // Transhipment Update

    // CAR types for Permit and Updates
    public static final String IPTPMT_CAR_TYPE = "IPTPMT"; // In-payment
    public static final String INPTPMT_CAR_TYPE = "INPPMT"; // In-non-payment
    public static final String OUTPMT_CAR_TYPE = "OUTPMT"; // Out permit
    public static final String TNPPMT_CAR_TYPE = "TNPPMT"; // Transhipment permit
    public static final String COODCI_CAR_TYPE = "COODCI"; // Certificate of Origin approval
    public static final String IPTUPT_CAR_TYPE = "IPTUPT"; // In-payment Update
    public static final String INPUPT_CAR_TYPE = "INPUPT"; // In-non-payment Update
    public static final String OUTUPT_CAR_TYPE = "OUTUPT"; // Out Update
    public static final String TNPUPT_CAR_TYPE = "TNPUPT"; // Transhipment / Movement Update

    // CAR Ref for Customs Procedure Code(CPC)
    public static final int IPT_DEC_PMT_UPD_UPT = 1;
    public static final int INP_DEC_PMT_UPD_UPT = 2;
    public static final int OUT_DEC_PMT_UPD_UPT = 4;
    public static final int OUTWITHCO_DEC_PMT_UPD_UPT = 5;
    public static final int TNP_DEC_PMT_UPD_UPT = 7;

    // Inbox Selection Menu Mapping
    public static final int INBOX_MENU_ALL = 0;
    public static final int INBOX_MENU_PERMIT = 1;
    public static final int INBOX_MENU_FEE = 2;
    public static final int INBOX_MENU_REJECT = 3;
    public static final int INBOX_MENU_APPROVE = 4;
    public static final int INBOX_MENU_QUERY = 5;
    public static final int INBOX_MENU_ERROR = 6;

    // Excel Upload Type
    public static final String EXCEL_UPLOAD_OVERRIDE = "OVERRIDE";
    public static final String EXCEL_UPLOAD_SKIP = "SKIP";
    public static final String EXCEL_UPLOAD_USER_SELECTION = "USER_SELECTION";
    public static final int EXCEL_UPLOAD_LIMIT = 1000;


    // Dec Type Ref for Customs Procedure Code(CPC)
    public static final Map<String, Integer> CPC_DEC_TYPEMAP()
    {
        HashMap<String, Integer> CPC_DEC_TYPE = new HashMap<String, Integer>();

        CPC_DEC_TYPE.put("GST", new Integer(10));
        CPC_DEC_TYPE.put("DUT", new Integer(11));
        CPC_DEC_TYPE.put("DNG", new Integer(12));

        CPC_DEC_TYPE.put("BKT", new Integer(90));

        CPC_DEC_TYPE.put("APS", new Integer(20));
        CPC_DEC_TYPE.put("GTR", new Integer(21));
        CPC_DEC_TYPE.put("SHO", new Integer(22));
        CPC_DEC_TYPE.put("DES", new Integer(23));
        CPC_DEC_TYPE.put("REX", new Integer(24));
        CPC_DEC_TYPE.put("SFZ", new Integer(25));

        CPC_DEC_TYPE.put("TCS", new Integer(91));
        CPC_DEC_TYPE.put("TCR", new Integer(92));
        CPC_DEC_TYPE.put("TCE", new Integer(93));
        CPC_DEC_TYPE.put("TCO", new Integer(94));
        CPC_DEC_TYPE.put("TCI", new Integer(95));

        CPC_DEC_TYPE.put("DRT", new Integer(40));

        CPC_DEC_TYPE.put("TTI", new Integer(70));
        CPC_DEC_TYPE.put("TTF", new Integer(71));
        CPC_DEC_TYPE.put("IGM", new Integer(72));
        CPC_DEC_TYPE.put("REM", new Integer(80));
        CPC_DEC_TYPE.put("BRE", new Integer(90));

        return CPC_DEC_TYPE;
    }

    /* @see com.etrade.trw.common.util.Trw4DataDict#getDownloadDir() */
    @Override
    public String getDownloadDir()
    {
        return DOWNLOAD_DIR;
    }

    public static final String[][] INSURANCE_SORTING_FIELDS = { { "JOB_NO", "Job No." }, { "CAR_TYPE", "Message Type" }, { "DEC_TYPE", "Declaration Type" }, { "MSG_STATUS", "Message Status" },
        { "IN_MASTER_SHIPDOC_NO", "BL/AWB Inward" }, { "OUT_MASTER_SHIPDOC_NO", "BL/AWB Outward" }, { "USER_REF_NO", "User Ref. No." } };

    /**************************** Email *************************************/
    public static final String EMAIL_DEFAULT_IP = "mail2.crimsonlogic.com"; // Server_IP , can test in dev
    public static final String EMAIL_DEFAULT_SENDER = "trwadmin@crimsonlogic.com";
    public static final String EMAIL_DEFAULT_MAILBOX = "master@crimsonlogic-etrade.com";
    public static final String EMAIL_DEFAULT_SALES = "sales@crimsonlogic-etrade.com";

    /**************************** Retention Plan *************************************/
    public static final int MAX_RETENTION_PLAN_DAYS = 1826;
}
