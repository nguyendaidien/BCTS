package com.etrade.bcts.common.util;

public interface BctsDataDict
{  
    public static final String CHARSET_NAME = "UTF-8";

    public static final String BCTS_HOME = "/gets/bcts/"; // Add a home folder (no longer using /sns/datafiles/trade/trw)
    public static final String BCTS_CONFIG_PATH = "/gets/bcts/config/"; // add default TradeWeb config path for GETS
    public static final String BCTS_CONFIG_FILE = "bcts.config"; // Add a default config file for TradeWeb

    // public static final String TRW_LOG4J_CONF_FILE = "trwlog4j.properties"; // add default TradeWeb config path for GETS
    public static final String TRW_LOG4J_CONF_XML = "trwlog4j.xml"; // add default TradeWeb config path for GETS

    public static final String OUTDEC = "OUTDEC";
    public static final String INPDEC = "INPDEC";
    public static final String IPTDEC = "IPTDEC";
    public static final String COODEC = "COODEC";
    public static final String TNPDEC = "TNPDEC";

    public static final String SUPPORT_EMAIL = "support@crimsonlogic-etrade.com";

    public static final int MANDATORY = 0;
    // public static final int CONDITIONAL = 1 ;
    public static final int OPTIONAL = 2;
    public static final int DISABLE = 3;

    public static final String APPID = "TRW4";
    public static final String TRADENET_MAILBOX = "DCS4.DCS4001";
    public static final String ETRADE = "ETRADE";
    public static final String MSG_VER = "040";

    // TTRW4_CM_PROPERTY Categories
    public static final String CAT_COMMS = "COMMS";
    public static final String CAT_COMMON = "COMMON";
    public static final String CAT_EMAIL = "EMAIL";

    // TTRW4_CM_PROPERTY COMMS Keys
    public static final String KEY_TRADENET_MBX = "TRADENET_MAILBOX";
    public static final String KEY_TRADENET_ID = "TRADENET_ID";
    public static final String KEY_SEND_FOLDER = "XML_SEND_FOLDER";
    public static final String KEY_RECEIVE_FOLDER = "XML_RECEIVE_FOLDER";
    public static final String KEY_RELEASE_FOLDER = "XML_RELEASE_FOLDER";
    public static final String KEY_ERROR_FOLDER = "XML_ERROR_FOLDER";
    public static final String KEY_ATTACH_FOLDER = "ATTACH_FOLDER";
    public static final String KEY_TRUST_STORE = "TRUST_STORE";
    public static final String KEY_SERVER_URL = "SERVER_URL";
    public static final String KEY_FILE_SERVER = "FILE_SERVER";
    public static final String KEY_PROFILE_PATH = "PROFILE_PATH";
    public static final String KEY_MHX_PATH = "MHX_PATH";
    public static final String KEY_PROXY_HOST = "PROXY_HOST";
    public static final String KEY_PROXY_PORT = "PROXY_PORT";
    public static final String KEY_CRON_PATH = "CRON_PATH";

    public static final String KEY_EMAIL_RECEIVER = "EMAIL_RECEIVER";

    // TTRW4_CM_PROPERTY COMMON Keys
    public static final String KEY_ENVT = "ENVT";

    // Comms Transmission Status
    public static final String COMMS_ERROR = "E";
    public static final String COMMS_SUCCESS = "S";
    public static final String COMMS_MHUB_ERROR = "M";
    public static final String COMMS_NOTHING_TO_RECEIVE = "NOTHING TO RECEIVE";

    // Dec Type
    public static final String SEARCH_DRAFT = "Draft";
    public static final String SEARCH_OUTBOX = "Outbox";
    public static final String SEARCH_SENT = "sent";

    // Dec Status Type
    public static final String INCOMPLETE = "I";
    public static final String COMPLETE = "C";
    public static final String SENT = "S";
    public static final String RECEIVED = "R";
    public static final String UNRECEIVED = "U";
    public static final String SENDFAIL = "SF";
    public static final String RECEIVEFAIL = "RF";
    public static final String PENDING = "PG";

    // Transport Modes
    public static final String SEA_MODE = "1";
    public static final String RAIL_MODE = "2";
    public static final String ROAD_MODE = "3";
    public static final String AIR_MODE = "4";
    public static final String MAIL_MODE = "5";
    public static final String MULTI_MODE = "6";
    public static final String PIPELINE_MODE = "7";

    // Update Indicator Codes
    public static final String UPDATE_AME = "AME";
    public static final String UPDATE_CNL = "CNL";
    public static final String UPDATE_FULL_REFUND = "FRF";
    public static final String UPDATE_PART_REFUND_GEN = "PRG";
    public static final String UPDATE_PART_REFUND_SPECIFIC = "PRS";
    public static final String UPDATE_AMR = "AMR";

    public static final String[][] DIRECTION_LIST = { { "IN", "Inward" }, { "OUT", "Outward" } };

    public static final String[][] SI_RELATION_LIST = { { "", "" }, { "1", "ORDINARY IMPORTER" }, { "2", "AGENCY" } };

    public String getDownloadDir();

    public static final String MHUB_ENDPOINT_URL_TRIAL = "trial.tradexchange.gov.sg";
    public static final String MHUB_ENDPOINT_URL_PROD = "www.tradexchange.gov.sg";

    // ////////////////////////////////////////////////////
    // Constants for PartyType
    // ////////////////////////////////////////////////////
    public static final String PARTY_TYPE_DECLARANT = "DT";
    public static final String PARTY_TYPE_DECLARING_AGENT = "AE";

    public static final String PARTY_TYPE_FREIGHT_FORWARDER = "FW";
    public static final String PARTY_TYPE_IN_CARRIER_AGENT = "IG";
    public static final String PARTY_TYPE_IMPORTER = "IM";

    public static final String PARTY_TYPE_OUT_CARRIER_AGENT = "OG";
    public static final String PARTY_TYPE_EXPORTER = "EX";
    public static final String PARTY_TYPE_CONSIGNEE = "CN";

    public static final String PARTY_TYPE_CLAIMANT = "CC";
    public static final String PARTY_TYPE_END_USER = "UC";
    public static final String PARTY_TYPE_HANDLING_AGENT = "AH";

    public static final String PARTY_TYPE_MANUFACTURER = "MF";
    public static final String PARTY_TYPE_SUPPLIER = "SU";

    public static final String PARTY_TYPE_APPLICANT = "AP";
    public static final String PARTY_TYPE_BUYER = "BY";
    public static final String PARTY_TYPE_NOTIFY_PARTY = "NI";
    public static final String PARTY_TYPE_RECIPIENT = "RC";
    public static final String PARTY_TYPE_CLAIMANT_DETAILS = "ZZ";
}
