package com.etrade.bcts.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.etrade.trw.common.util.TrwLogger;
//import com.etrade.trw.web.db.cm.CommonDAO;
import com.etrade.bcts.model.BctsPermitType;
import com.etrade.bcts.model.CaaApprovalCondition;

import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.ConditionType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PermitType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.OutwardPermitType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.OutwardUpdatePermitType;
import crimsonlogic.tn41.schema.xsd.tradenetresponse.TradenetResponseType;

public class PRSOutwardPermit {
	private static final Logger LOG = LoggerFactory.getLogger(PRSOutwardPermit.class);
	private OutwardPermitType permitType;
	//private OutwardUpdatePermitType permitUpdateType;
	private TradenetResponseType tradeNetResponse;
	/*private String sqlStatementPermitInfo;
	private String sqlStatementPermitItem;*/
	private Boolean isUpdate = false;

	public PRSOutwardPermit(OutwardPermitType outwardPermit, TradenetResponseType tradeNetResponse,
			String sqlStatementPermitInfo, String sqlStatementPermitItem) {
		this.permitType = outwardPermit;
		this.tradeNetResponse = tradeNetResponse;
		/*this.sqlStatementPermitInfo = sqlStatementPermitInfo;
		this.sqlStatementPermitItem = sqlStatementPermitItem;*/
	}

	public PRSOutwardPermit(OutwardUpdatePermitType permitUpdateType, TradenetResponseType tradeNetResponse,
			String sqlStatementPermitInfo, String sqlStatementPermitItem) {
		//this.permitUpdateType = permitUpdateType;
		this.tradeNetResponse = tradeNetResponse;
		//this.sqlStatementPermitInfo = sqlStatementPermitInfo;
		//this.sqlStatementPermitItem = sqlStatementPermitItem;
		this.isUpdate = true;
	}

	// Process Outward Permit
	public void processOutwardPermit() throws SQLException {
		// validate Inward Permit
		if (isUpdate) {
			// validateOutwardUpdatePermit();
		} else {
			validateOutwardPermit();
		}

	}

	public void validateOutwardPermit() throws SQLException {
		if (null == permitType) {
			throw new SQLException("Error in validateOutwardPermit: OutwardPermitType is null");
		} else if (null == permitType.getDeclaration()) {
			throw new SQLException("Error in validateOutwardPermit: OutwardPermitType.Declaration is null");
		} else if (null == permitType.getDeclaration().getHeader()) {
			throw new SQLException("Error in validateOutwardPermit: OutwardPermitType.Declaration.Header is null");
		} else if (null == permitType.getDeclaration().getHeader().getUniqueReferenceNumber()) {
			throw new SQLException(
					"Error in validateOutwardPermit: OutwardPermitType.Declaration.Header.UniqueReferenceNumber is null");
		} else if (null == permitType.getPermit()) {
			throw new SQLException("Error in validateOutwardPermit: OutwardPermitType.Permit is null");
		} else if (null == tradeNetResponse.getRecipientID()) {
			throw new SQLException("Error in validateOutwardPermit: tradeNetResponse.recipientID is null");
		} else if (tradeNetResponse.getRecipientID().length() < 5) {
			throw new SQLException("Error in validateOutwardPermit: Invalid RecipientID format");
		} else {
			BctsPermitType bctsPermitType = new BctsPermitType();

			PermitType pType = permitType.getPermit();

			bctsPermitType.setPermitNumber(pType.getPermitNumber());
			//bctsPermitType.setPermApprDateAndTime(pType.getPermitApprovalDatetime());
			//bctsPermitType.setCaaApproDateAndTime(pType.getCAApprovalDatetime());
			
			List<ConditionType> conditionListType = pType.getCAApprovalCondition();
			List<CaaApprovalCondition> caaApLst = new ArrayList();

			for (ConditionType cType : conditionListType) {
				CaaApprovalCondition caaAprC = new CaaApprovalCondition();
				System.out.println(cType.getAgencyCode());
				System.out.println(cType.getConditionCode());
				System.out.println(cType.getConditionDescription());
				caaAprC.setAgencyCode(cType.getAgencyCode());
				caaAprC.setConditionCode(cType.getConditionCode());
				//caaAprC.setCondtionDesc(cType.getConditionDescription());
				caaApLst.add(caaAprC);
			}
			//bctsPermitType.setCaaApprovalLst(caaApLst);

			System.out.println("bctsPermitType.toString():" + bctsPermitType.toString());

			saveIntoPermitInfo();
		}

	}

	private void saveIntoPermitInfo() throws SQLException {
		/*
		 * final String methodName = "saveIntoPermitInfo"; Connection conn = null;
		 * PreparedStatement pstmt = null; String urn = null; int parameterIndex = 1;
		 * 
		 * try { conn = null;//new CommonDAO().getPRSConnection(); pstmt =
		 * conn.prepareStatement(sqlStatementPermitInfo);
		 * 
		 * // URN UniqueReferenceNumberType urnType =
		 * permitType.getDeclaration().getHeader().getUniqueReferenceNumber();
		 * 
		 * urn = null;//TradeNetResponseToPRSDB.formatURN(urnType);
		 * 
		 * //////////////////////////////////////////////////////////////////// // Add
		 * missing logic to check and remove existing record first
		 * ////////////////////////////////////////////////////////////////////
		 * //TradeNetResponseToPRSDB.checkAndRemoveExistingPermitInfo(urn);
		 * ////////////////////////////////////////////////////////////////////
		 * 
		 * pstmt.setString(parameterIndex++, urn);
		 * 
		 * // PERMIT_NO //pstmt.setString(parameterIndex++,
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getPermit().
		 * getPermitNumber()));
		 * 
		 * // MESSAGE_TYPE //pstmt.setString(parameterIndex++,
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getHeader().getCommonAccessReference()));
		 * 
		 * // DEC_TYPE //pstmt.setString(parameterIndex++,
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getHeader().getDeclarationType()));
		 * 
		 * // PERMIT_TYPE //pstmt.setString(parameterIndex++, "");
		 * 
		 * // CERT_TYPE //pstmt.setString(parameterIndex++, "");
		 * 
		 * // APPROVAL_DATE String approvalDate =
		 * permitType.getPermit().getPermitApprovalDatetime(); approvalDate =
		 * approvalDate.substring(0, 8); // pstmt.setDate(parameterIndex++,
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(approvalDate));
		 * 
		 * String importerParty = (null != permitType.getDeclaration().getParty()) ?
		 * TradeNetResponseToPRSDB.getPartyID(permitType.getDeclaration().getParty().
		 * getImporterParty()) : ""; String exporterParty = (null !=
		 * permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty().getExporterParty()) ?
		 * TradeNetResponseToPRSDB.getPartyID(permitType.getDeclaration().getParty().
		 * getExporterParty().getPartyDetail()) : ""; String inwardCrarierParty = (null
		 * != permitType.getDeclaration().getParty()) ?
		 * TradeNetResponseToPRSDB.getPartyID(permitType.getDeclaration().getParty().
		 * getInwardCarrierAgentParty()) : ""; String delcarationAgentParty = (null !=
		 * permitType.getDeclaration().getParty()) ?
		 * TradeNetResponseToPRSDB.getPartyID(permitType.getDeclaration().getParty().
		 * getDeclaringAgentParty()) : ""; String freightForwarderParty = (null !=
		 * permitType.getDeclaration().getParty()) ?
		 * TradeNetResponseToPRSDB.getPartyID(permitType.getDeclaration().getParty().
		 * getFreightForwarderParty()) : "";
		 * 
		 * // IMPORTER_EI pstmt.setString(parameterIndex++, importerParty); //
		 * EXPORTER_EI pstmt.setString(parameterIndex++, exporterParty);
		 * 
		 * // CARRIER_AGENT_EI pstmt.setString(parameterIndex++, inwardCrarierParty);
		 * 
		 * // DEC_AGENT_EI pstmt.setString(parameterIndex++, delcarationAgentParty);
		 * 
		 * // FREIGHT_FOR_EI pstmt.setString(parameterIndex++, freightForwarderParty);
		 * 
		 * // PLACE_RELEASE String releaseLocation = (null !=
		 * permitType.getDeclaration().getCargo()) ?
		 * TradeNetResponseToPRSDB.getLocationCodeAndName(permitType.getDeclaration().
		 * getCargo().getReleaseLocation()) : ""; pstmt.setString(parameterIndex++,
		 * releaseLocation);
		 * 
		 * // PLACE_RECEIPT String recieptLocation = (null !=
		 * permitType.getDeclaration().getCargo()) ?
		 * TradeNetResponseToPRSDB.getLocationCodeAndName(permitType.getDeclaration().
		 * getCargo().getReceiptLocation()) : ""; pstmt.setString(parameterIndex++,
		 * recieptLocation);
		 * 
		 * // COUNTRY_OF_ORIGIN pstmt.setString(parameterIndex++, "");
		 * 
		 * // COUNTRY_OF_DEST (get the outward transport final destination country
		 * pstmt.setString(parameterIndex++,
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getFinalDestinationCountry());
		 * 
		 * // TRANS_MODE int transMode = 0; if (null !=
		 * permitType.getDeclaration().getTransport()) { if (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport()) { if (null
		 * != permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans()) { if (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans().getTransportMode()) { if (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans().getTransportMode().getModeCode()) { String transModeStr =
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans().getTransportMode().getModeCode(); transMode =
		 * Integer.parseInt(transModeStr); } }
		 * 
		 * } } } pstmt.setInt(parameterIndex++, transMode);
		 * 
		 * // USER_ID String id = tradeNetResponse.getRecipientID(); String[] splitID =
		 * id.split("\\."); String part2 = splitID[1]; pstmt.setString(parameterIndex++,
		 * part2.toLowerCase());
		 * 
		 * // CPC String cpc = "";
		 * 
		 * if (permitType.getDeclaration() != null &&
		 * permitType.getDeclaration().getHeader() != null) {
		 * List<CustomsProcedureCodeInformationType> cpcList =
		 * permitType.getDeclaration().getHeader().getCustomsProcedureCodeInformation();
		 * 
		 * cpc = TradeNetResponseToPRSDB.getCPC_String(cpcList); }
		 * 
		 * pstmt.setString(parameterIndex++, cpc);
		 * 
		 * // VALIDITY_START pstmt.setDate(parameterIndex++, null !=
		 * permitType.getPermit().getPermitValidityPeriod() ?
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(permitType.getPermit().
		 * getPermitValidityPeriod().getStartDate()) : null);
		 * 
		 * // VALIDITY_END pstmt.setDate(parameterIndex++, null !=
		 * permitType.getPermit().getPermitValidityPeriod() ?
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(permitType.getPermit().
		 * getPermitValidityPeriod().getEndDate()) : null);
		 * 
		 * // LAST_APP_DATE pstmt.setNull(parameterIndex++, Types.DATE);
		 * 
		 * // IMPORTER_NAME pstmt.setString(parameterIndex++, null !=
		 * permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty().getImporterParty() && null !=
		 * permitType.getDeclaration().getParty().getImporterParty().getPartyName() ?
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getImporterParty().getPartyName().getName(), ";") : "");
		 * 
		 * // EXPORTER_NAME pstmt.setString(parameterIndex++, null !=
		 * permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty().getExporterParty() && null !=
		 * permitType.getDeclaration().getParty().getExporterParty().getPartyDetail() &&
		 * null !=
		 * permitType.getDeclaration().getParty().getExporterParty().getPartyDetail().
		 * getPartyName() ?
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getExporterParty().getPartyDetail().getPartyName().getName(), ";") : "");
		 * 
		 * // DEC_AGENT_NAME pstmt.setString(parameterIndex++, null !=
		 * permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty().getDeclaringAgentParty() && null !=
		 * permitType.getDeclaration().getParty().getDeclaringAgentParty().getPartyName(
		 * ) ?
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getDeclaringAgentParty().getPartyName().getName(), ";") : "");
		 * 
		 * // HANDLING_AGENT_EI pstmt.setString(parameterIndex++, "");
		 * 
		 * // HANDLING_AGENT_NAME pstmt.setString(parameterIndex++, "");
		 * 
		 * // CLAIMANT_EI String claimantEI = ""; pstmt.setString(parameterIndex++,
		 * claimantEI);
		 * 
		 * // CLAIMANT_NAME String claimantName = ""; pstmt.setString(parameterIndex++,
		 * claimantName);
		 * 
		 * String freightForwarderEI = ""; String freightForwarderName = ""; if (null !=
		 * permitType.getDeclaration().getParty().getFreightForwarderParty()) {
		 * freightForwarderEI =
		 * TradeNetResponseToPRSDB.getPartyID(permitType.getDeclaration().getParty().
		 * getFreightForwarderParty()); freightForwarderName =
		 * TradeNetResponseToPRSDB.getPartyName(permitType.getDeclaration().getParty().
		 * getFreightForwarderParty()); }
		 * 
		 * // FREIGHT_FORWARDER_EI pstmt.setString(parameterIndex++,
		 * freightForwarderEI);
		 * 
		 * // FREIGHT_FORWARDER_NAME pstmt.setString(parameterIndex++,
		 * freightForwarderName);
		 * 
		 * // DEPT_DATE String departureDate =
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getDepartureDate(); pstmt.setDate(parameterIndex++,
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(departureDate));
		 * 
		 * // double totalGrossWeight = 0.0; String totalGrossWeightUOM = ""; int
		 * totalOuterPack = 0; String totalOuterPackUOM = "";
		 * 
		 * // No duty for outward permit double totalCustomerDutyAmount = 0; double
		 * totalExciseDutyAmount = 0; double totalGSTAmount = 0; double totalPaid = 0;
		 * 
		 * if (null != permitType.getDeclaration().getSummary()) { if (null !=
		 * permitType.getDeclaration().getSummary().getTotalGrossWeight()) {
		 * totalGrossWeight =
		 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(permitType.getDeclaration(
		 * ).getSummary().getTotalGrossWeight().getValue()); totalGrossWeightUOM =
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getSummary().getTotalGrossWeight().getUnitCode()); } if (null !=
		 * permitType.getDeclaration().getSummary().getTotalOuterPack()) {
		 * totalOuterPack =
		 * permitType.getDeclaration().getSummary().getTotalOuterPack().getValue();
		 * totalOuterPackUOM =
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getSummary().getTotalOuterPack().getUnitCode()); }
		 * 
		 * }
		 * 
		 * // TOT_GROSS_WT pstmt.setDouble(parameterIndex++, totalGrossWeight);
		 * 
		 * // TOT_GROSS_WT_UOM pstmt.setString(parameterIndex++, totalGrossWeightUOM);
		 * 
		 * // TOT_OUT_PACK pstmt.setInt(parameterIndex++, totalOuterPack);
		 * 
		 * // TOT_OUT_PACK_UOM pstmt.setString(parameterIndex++, totalOuterPackUOM);
		 * 
		 * // TOT_CUSTOMS_DUTY_AMT pstmt.setDouble(parameterIndex++,
		 * totalCustomerDutyAmount);
		 * 
		 * // TOT_EXCISE_DUTY_AMT pstmt.setDouble(parameterIndex++,
		 * totalExciseDutyAmount);
		 * 
		 * // TOT_GST_AMT pstmt.setDouble(parameterIndex++, totalGSTAmount);
		 * 
		 * // TOT_PAID pstmt.setDouble(parameterIndex++, totalPaid);
		 * 
		 * String enduserName = ""; String enduserAddress = "";
		 * 
		 * if (null != permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty().getEndUserParty()) {
		 * 
		 * enduserName = (null !=
		 * permitType.getDeclaration().getParty().getEndUserParty().getPartyName()) ?
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getEndUserParty().getPartyName().getName(), "; ") : "";
		 * 
		 * enduserAddress = (null !=
		 * permitType.getDeclaration().getParty().getEndUserParty().getAddressLine()) ?
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getEndUserParty().getAddressLine().getLine(), "; ") : "";
		 * 
		 * }
		 * 
		 * // END_USER_NAME pstmt.setString(parameterIndex++, enduserName);
		 * 
		 * // END_USER_ADDRESS pstmt.setString(parameterIndex++, enduserAddress);
		 * 
		 * // TOW_VESSEL_NAME pstmt.setString(parameterIndex++, (null !=
		 * permitType.getDeclaration().getTransport() && null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport() && null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getAdditionalVesselInformation() && null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getAdditionalVesselInformation().getTowingVessel()) ? TradeNetResponseToPRSDB
		 * .replaceNullWithEmptyString(permitType.getDeclaration().getTransport().
		 * getOutwardTransport().getAdditionalVesselInformation().getTowingVessel().
		 * getVesselName()) : "");
		 * 
		 * // AWB_BL pstmt.setString(parameterIndex++, "");
		 * 
		 * // REPL_PERMIT_NO pstmt.setString(parameterIndex++, "");
		 * 
		 * // VERSION pstmt.setString(parameterIndex++,
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(tradeNetResponse.
		 * getMessageVersion()));
		 * 
		 * String declarantName = ""; String declarantICNumber = "";
		 * 
		 * if (null != permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty().getDeclarantParty() && null !=
		 * permitType.getDeclaration().getParty().getDeclarantParty().
		 * getPersonInformation()) { declarantName =
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getDeclarantParty().getPersonInformation().getName(), "; ");
		 * declarantICNumber =
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getParty().getDeclarantParty().getPersonInformation().getCodeValue()); }
		 * 
		 * // DECLARANT_NAME pstmt.setString(parameterIndex++, declarantName);
		 * 
		 * // PASSPORT_IC_NUMBER pstmt.setString(parameterIndex++, declarantICNumber);
		 * 
		 * // SUBMISSION_DATE String submissionDate =
		 * permitType.getDeclaration().getHeader().getSubmissionDatetime();
		 * submissionDate = submissionDate.substring(0, 8);
		 * pstmt.setDate(parameterIndex++,
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(submissionDate));
		 * 
		 * // PORT_CODE pstmt.setString(parameterIndex++, null !=
		 * permitType.getDeclaration().getTransport() ? null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport() ?
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getTransport().getInwardTransport().getLoadingPort()) : "" : "");
		 * 
		 * // PORT_NAME pstmt.setString(parameterIndex++, "");
		 * 
		 * // DISCHARGE_PORT_CODE pstmt.setString(parameterIndex++, null !=
		 * permitType.getDeclaration().getTransport() ? null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport() ?
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getTransport().getOutwardTransport().getDischargePort()) : "" : "");
		 * 
		 * // DISCHARGE_PORT_NAME pstmt.setString(parameterIndex++, "");
		 * 
		 * // NEXT_PORT_CODE pstmt.setString(parameterIndex++, (null !=
		 * permitType.getDeclaration().getTransport() && null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport() && null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getAdditionalVesselInformation()) ?
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getTransport().getOutwardTransport().getAdditionalVesselInformation().
		 * getLoadingNextPort()) : "");
		 * 
		 * // NEXT_PORT_NAME pstmt.setString(parameterIndex++, "");
		 * 
		 * // FINAL_PORT_CODE pstmt.setString(parameterIndex++, (null !=
		 * permitType.getDeclaration().getTransport() && null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport() && null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getAdditionalVesselInformation()) ?
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getTransport().getOutwardTransport().getAdditionalVesselInformation().
		 * getLoadingFinalPort()) : "");
		 * 
		 * // FINAL_PORT_NAME pstmt.setString(parameterIndex++, "");
		 * 
		 * // LIC_NO String licences = ""; if (null !=
		 * permitType.getDeclaration().getLicence()) { for (int i = 0; i <
		 * permitType.getDeclaration().getLicence().size(); i++) { licences +=
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getLicence().get(i).getReferenceID()); if (i <
		 * permitType.getDeclaration().getLicence().size() - 1) { licences += "; "; } }
		 * } pstmt.setString(parameterIndex++, licences);
		 * 
		 * // REMARKS String remarks = ""; if (null !=
		 * permitType.getDeclaration().getHeader().getRemarks()) { for (int i = 0; i <
		 * permitType.getDeclaration().getHeader().getRemarks().size(); i++) {
		 * 
		 * remarks +=
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getHeader
		 * ().getRemarks().get(i).getFreeText(), "\r\n");
		 * 
		 * if (i < permitType.getDeclaration().getLicence().size() - 1) { remarks +=
		 * "\r\n"; } } } pstmt.setString(parameterIndex++, remarks);
		 * 
		 * String inConveyanceRefNo = (null !=
		 * permitType.getDeclaration().getTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
		 * .replaceNullWithEmptyString(permitType.getDeclaration().getTransport().
		 * getInwardTransport().getTransportMeans().getTransportMode().
		 * getConveyanceReferenceNumber()) : "" : "" : "" : "";
		 * 
		 * // IN_CONVEYANCE_REFERENCE_NO pstmt.setString(parameterIndex++,
		 * inConveyanceRefNo);
		 * 
		 * // OUT_CONVEYANCE_REFERENCE_NO String outConveyanceRefNo = (null !=
		 * permitType.getDeclaration().getTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
		 * .replaceNullWithEmptyString(permitType.getDeclaration().getTransport().
		 * getOutwardTransport().getTransportMeans().getTransportMode().
		 * getConveyanceReferenceNumber()) : "" : "" : "" : "";
		 * pstmt.setString(parameterIndex++, outConveyanceRefNo);
		 * 
		 * // TOT_CHARGE pstmt.setDouble(parameterIndex++, 0);
		 * 
		 * // ARRIVAL_DATE Date arrivalDate = null;
		 * 
		 * if (null != permitType.getDeclaration().getTransport() && null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport()) {
		 * arrivalDate =
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(permitType.getDeclaration(
		 * ).getTransport().getInwardTransport().getArrivalDate()); }
		 * 
		 * pstmt.setDate(parameterIndex++, arrivalDate);
		 * 
		 * // IN_AWB_BL String inMAWBOUCROBLNumber = (null !=
		 * permitType.getDeclaration().getTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans()) ?
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getTransport().getInwardTransport().getTransportMeans().
		 * getMAWBOUCROBLNumber()) : "" : "" : "";
		 * 
		 * pstmt.setString(parameterIndex++, inMAWBOUCROBLNumber);
		 * 
		 * // OUT_AWB_BL String outMAWBOUCROBLNumber = (null !=
		 * permitType.getDeclaration().getTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans()) ?
		 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitType.getDeclaration(
		 * ).getTransport().getOutwardTransport().getTransportMeans().
		 * getMAWBOUCROBLNumber()) : "" : "" : "";
		 * 
		 * pstmt.setString(parameterIndex++, outMAWBOUCROBLNumber);
		 * 
		 * // IN_TRANSPORT_ID String inTransportID = (null !=
		 * permitType.getDeclaration().getTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans()) ? (null !=
		 * permitType.getDeclaration().getTransport().getInwardTransport().
		 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
		 * .replaceNullWithEmptyString(permitType.getDeclaration().getTransport().
		 * getInwardTransport().getTransportMeans().getTransportMode().
		 * getTransportIdentifier()) : "" : "" : "" : "";
		 * 
		 * pstmt.setString(parameterIndex++, inTransportID);
		 * 
		 * // OUT_TRANSPORT_ID String outTransportID = (null !=
		 * permitType.getDeclaration().getTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans()) ? (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
		 * .replaceNullWithEmptyString(permitType.getDeclaration().getTransport().
		 * getOutwardTransport().getTransportMeans().getTransportMode().
		 * getTransportIdentifier()) : "" : "" : "" : "";
		 * 
		 * pstmt.setString(parameterIndex++, outTransportID);
		 * 
		 * // OUT_TRANS_MODE int outTransMode = 0; if (null !=
		 * permitType.getDeclaration().getTransport()) { if (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport()) { if (null
		 * != permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans()) { if (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans().getTransportMode()) { if (null !=
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getTransportMeans().getTransportMode().getModeCode()) { outTransMode =
		 * Integer.parseInt(permitType.getDeclaration().getTransport().
		 * getOutwardTransport().getTransportMeans().getTransportMode().getModeCode());
		 * } } } } } pstmt.setInt(parameterIndex++, outTransMode);
		 * 
		 * String consigneeName = (null != permitType.getDeclaration().getParty() &&
		 * null != permitType.getDeclaration().getParty().getConsigneeParty() && null !=
		 * permitType.getDeclaration().getParty().getConsigneeParty().getPartyName()) ?
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getConsigneeParty().getPartyName().getName(), "; ") : "";
		 * 
		 * // CONSIGNEE NAME pstmt.setString(parameterIndex++, consigneeName);
		 * 
		 * // CONSIGNEE ADDRESS String consigneeAddress = (null !=
		 * permitType.getDeclaration().getParty() && null !=
		 * permitType.getDeclaration().getParty().getConsigneeParty() && null !=
		 * permitType.getDeclaration().getParty().getConsigneeParty().getAddress().
		 * getAddressLine()) ?
		 * TradeNetResponseToPRSDB.listToSingleStr(permitType.getDeclaration().getParty(
		 * ).getConsigneeParty().getAddress().getAddressLine().getLine(), "; ") : "";
		 * pstmt.setString(parameterIndex++, consigneeAddress);
		 * 
		 * // AED_COMPLIANCE // if the declaration date is later than departure date, it
		 * is not AED compliance String aedCompliance = "Y"; String
		 * declarationDateAsString =
		 * permitType.getDeclaration().getHeader().getUniqueReferenceNumber().getDate();
		 * String departureDateAsString =
		 * permitType.getDeclaration().getTransport().getOutwardTransport().
		 * getDepartureDate(); Date declarationDate =
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(declarationDateAsString);
		 * Date departure_date =
		 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(departureDateAsString);
		 * 
		 * if (declarationDate.after(departure_date)) { aedCompliance = "N"; }
		 * pstmt.setString(parameterIndex++, aedCompliance); // records.add(record);
		 * pstmt.executeUpdate(); } catch (SQLException e) { LOG.error(methodName,
		 * e.getMessage()); throw new SQLException(e); } finally {
		 * CommonDAO.closeResources(conn, pstmt, null); } if (null !=
		 * permitType.getDeclaration().getItem()) { for
		 * (crimsonlogic.tn41.schema.xsd.outwarddeclaration.ItemType item :
		 * permitType.getDeclaration().getItem()) { saveIntoPermitItem(item, urn); } }
		 * 
		 */}

	/*
	 * private void
	 * saveIntoPermitItem(crimsonlogic.tn41.schema.xsd.outwarddeclaration.ItemType
	 * item, String urn) throws SQLException { Connection conn = null;
	 * PreparedStatement pstmt = null; int parameterIndex = 0; String methodName =
	 * "saveIntoPermitItem"; parameterIndex = 1; try { conn = new
	 * CommonDAO().getPRSConnection(); pstmt =
	 * conn.prepareStatement(sqlStatementPermitItem);
	 * 
	 * // URN pstmt.setString(parameterIndex++, urn);
	 * 
	 * // ITEM_SEQ_NO pstmt.setInt(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToInt(item.getItemSequenceNumeric()
	 * ));
	 * 
	 * // HS_CODE pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(item.
	 * getItemHarmonizedSystemCode()));
	 * 
	 * // HS_QTY pstmt.setDouble(parameterIndex++, null !=
	 * item.getItemQuantity().getHarmonizedSystemQuantity() ?
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(item.getItemQuantity().
	 * getHarmonizedSystemQuantity().getValue()) : 0);
	 * 
	 * // HS_UOM pstmt.setString(parameterIndex++, null !=
	 * item.getItemQuantity().getHarmonizedSystemQuantity() ?
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(item.getItemQuantity().
	 * getHarmonizedSystemQuantity().getUnitCode()) : "");
	 * 
	 * // ITEM_DESC pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.listToSingleStr(item.getGoodsDescription(), " "));
	 * 
	 * // MARKING pstmt.setString(parameterIndex++, "");
	 * 
	 * // CTY_ORIGIN pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(item.getOriginCountry()));
	 * 
	 * // IN_MAWB_OBL_AWB_BL pstmt.setString(parameterIndex++, ""); //
	 * OUT_MAWB_OBL_AWB_BL pstmt.setString(parameterIndex++, ""); // IN_HAWB_HBL
	 * pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(item.
	 * getInHAWBHUCRHBLNumber())); // OUT_HAWB_HBL pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(item.
	 * getOutHAWBHUCRHBLNumber())); // CURR_LOT_NO String lotNumber = ""; if (null
	 * != item.getLotIdentification()) { if (null !=
	 * item.getLotIdentification().getCurrentLotNumber()) { lotNumber =
	 * item.getLotIdentification().getCurrentLotNumber(); } }
	 * pstmt.setString(parameterIndex++, lotNumber); // PREV_LOT_NO String
	 * previousLotNumber = ""; if (null != item.getLotIdentification()) { if (null
	 * != item.getLotIdentification().getPreviousLotNumber()) { previousLotNumber =
	 * item.getLotIdentification().getPreviousLotNumber(); } }
	 * pstmt.setString(parameterIndex++, previousLotNumber); // BRAND_NAME String
	 * brandName = ""; if (null != item.getBrandName()) { brandName =
	 * item.getBrandName(); } pstmt.setString(parameterIndex++, brandName); //
	 * MODEL_NAME String modelName = ""; if (null != item.getModelDescription()) {
	 * modelName = item.getModelDescription(); } pstmt.setString(parameterIndex++,
	 * modelName); // DUT_QTY_WT_VOL BigDecimal dutiableQuantity = null; if (null !=
	 * item.getItemQuantity()) { if (null !=
	 * item.getItemQuantity().getDutiableQuantity()) { if (null !=
	 * item.getItemQuantity().getDutiableQuantity().getValue()) { dutiableQuantity =
	 * item.getItemQuantity().getDutiableQuantity().getValue(); } } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(dutiableQuantity)); //
	 * DUT_QTY_WT_UOM String dutiableQuantityUOM = ""; if (null !=
	 * item.getItemQuantity()) { if (null !=
	 * item.getItemQuantity().getDutiableQuantity()) { if (null !=
	 * item.getItemQuantity().getDutiableQuantity().getUnitCode()) {
	 * dutiableQuantityUOM =
	 * item.getItemQuantity().getDutiableQuantity().getUnitCode(); } } }
	 * pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(dutiableQuantityUOM)); //
	 * UNIT_PRICE BigDecimal unitPrice = null; if (null !=
	 * item.getTransactionValue()) { if (null !=
	 * item.getTransactionValue().getUnitPriceValue()) { if (null !=
	 * item.getTransactionValue().getUnitPriceValue().getAmount()) { if (null !=
	 * item.getTransactionValue().getUnitPriceValue().getAmount().getValue()) {
	 * unitPrice =
	 * item.getTransactionValue().getUnitPriceValue().getAmount().getValue(); } } }
	 * } pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(unitPrice)); //
	 * CUSTOMS_DUTY_AMT BigDecimal customsDutyAmt = null; if (null !=
	 * item.getTariff()) { if (null != item.getTariff().getCustomsDuty()) { if (null
	 * != item.getTariff().getCustomsDuty().getDutyAmount()) { customsDutyAmt =
	 * item.getTariff().getCustomsDuty().getDutyAmount(); } } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(customsDutyAmt)); //
	 * EXCISE_DUTY_AMT BigDecimal exciseDutyAmt = null; if (null !=
	 * item.getTariff()) { if (null != item.getTariff().getExciseDuty()) { if (null
	 * != item.getTariff().getExciseDuty().getDutyAmount()) { exciseDutyAmt =
	 * item.getTariff().getExciseDuty().getDutyAmount(); } } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(exciseDutyAmt)); //
	 * CIF_FOB_VALUE BigDecimal cifFobValue = null; if (null !=
	 * item.getTransactionValue()) { if (null !=
	 * item.getTransactionValue().getItemCIFFOBValue()) { cifFobValue =
	 * item.getTransactionValue().getItemCIFFOBValue(); } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(cifFobValue)); // GST_AMT
	 * BigDecimal gstAmount = null; if (null != item.getTariff()) { if (null !=
	 * item.getTariff().getGoodsAndServicesTax()) { if (null !=
	 * item.getTariff().getGoodsAndServicesTax().getGoodsAndServicesTaxAmount()) {
	 * gstAmount =
	 * item.getTariff().getGoodsAndServicesTax().getGoodsAndServicesTaxAmount(); } }
	 * } pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(gstAmount)); // LSP_VALUE
	 * BigDecimal lspValue = null; if (null != item.getTransactionValue()) { if
	 * (null != item.getTransactionValue().getLastSellingPriceValue()) { lspValue =
	 * item.getTariff().getGoodsAndServicesTax().getGoodsAndServicesTaxAmount(); } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(lspValue)); //
	 * REFUND_CUSTOMS_AMOUNT => Nil pstmt.setDouble(parameterIndex++, 0); //
	 * REFUND_EXCISE_AMOUNT => Nil pstmt.setDouble(parameterIndex++, 0); //
	 * REFUND_GST_AMOUNT => Nil pstmt.setDouble(parameterIndex++, 0); // INMOST_PACK
	 * BigDecimal inmostPack = null; if (null != item.getPackingDescription()) { if
	 * (null != item.getPackingDescription().getInmostPackQuantity()) { if (null !=
	 * item.getPackingDescription().getInmostPackQuantity().getValue()) { inmostPack
	 * = item.getPackingDescription().getInmostPackQuantity().getValue(); } } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(inmostPack)); //
	 * INMOST_PACK_UOM String inmostPackUOM = ""; if (null !=
	 * item.getPackingDescription()) { if (null !=
	 * item.getPackingDescription().getInmostPackQuantity()) { if (null !=
	 * item.getPackingDescription().getInmostPackQuantity().getUnitCode()) {
	 * inmostPackUOM =
	 * item.getPackingDescription().getInmostPackQuantity().getUnitCode(); } } }
	 * pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(inmostPackUOM)); //
	 * IN_PACK BigDecimal inPack = null; if (null != item.getPackingDescription()) {
	 * if (null != item.getPackingDescription().getInPackQuantity()) { if (null !=
	 * item.getPackingDescription().getInPackQuantity().getValue()) { inPack =
	 * item.getPackingDescription().getInPackQuantity().getValue(); } } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(inPack)); // IN_PACK_UOM
	 * String inPackUOM = ""; if (null != item.getPackingDescription()) { if (null
	 * != item.getPackingDescription().getInPackQuantity()) { if (null !=
	 * item.getPackingDescription().getInPackQuantity().getUnitCode()) { inPackUOM =
	 * item.getPackingDescription().getInPackQuantity().getUnitCode(); } } }
	 * pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(inPackUOM)); // OUT_PACK
	 * BigDecimal outPack = null; if (null != item.getPackingDescription()) { if
	 * (null != item.getPackingDescription().getOuterPackQuantity()) { if (null !=
	 * item.getPackingDescription().getOuterPackQuantity().getValue()) { outPack =
	 * item.getPackingDescription().getOuterPackQuantity().getValue(); } } }
	 * pstmt.setDouble(parameterIndex++,
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(outPack)); // OUT_PACK_UOM
	 * String outPackUOM = ""; if (null != item.getPackingDescription()) { if (null
	 * != item.getPackingDescription().getOuterPackQuantity()) { if (null !=
	 * item.getPackingDescription().getOuterPackQuantity().getUnitCode()) {
	 * outPackUOM =
	 * item.getPackingDescription().getOuterPackQuantity().getUnitCode(); } } }
	 * pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(outPackUOM)); // PREF_IND
	 * String prefInd = ""; if (null != item.getTariff()) { if (null !=
	 * item.getTariff().getPreferentialCode()) { prefInd =
	 * item.getTariff().getPreferentialCode(); } } pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(prefInd)); // TOT_DUT_QTY
	 * pstmt.setInt(parameterIndex++, 0); // TOT_DUT_UOM
	 * pstmt.setString(parameterIndex++, ""); // UNIT_PRICE_CURR String
	 * unitPriceCurrency = ""; if (null != item.getTransactionValue()) { if (null !=
	 * item.getTransactionValue().getUnitPriceValue()) { if (null !=
	 * item.getTransactionValue().getUnitPriceValue().getAmount()) { if (null !=
	 * item.getTransactionValue().getUnitPriceValue().getAmount().getCurrencyID()) {
	 * unitPriceCurrency =
	 * item.getTransactionValue().getUnitPriceValue().getAmount().getCurrencyID(); }
	 * } } } pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(unitPriceCurrency)); //
	 * INV_NO no item Invoice Numer pstmt.setString(parameterIndex++, ""); // MARKS
	 * 
	 * @SuppressWarnings("rawtypes") List shippingMarksInfo = new ArrayList(); if
	 * (null != item.getShippingMarksInformation()) { shippingMarksInfo =
	 * item.getShippingMarksInformation(); } pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.getShippingMark(shippingMarksInfo));
	 * 
	 * pstmt.executeUpdate(); } catch (SQLException e) { LOG.error(methodName, e);
	 * throw new SQLException(e); } finally { CommonDAO.closeResources(conn, pstmt,
	 * null); } }
	 * 
	 * process for outward update permit
	 * 
	 * public void validateOutwardUpdatePermit() throws SQLException { if (null ==
	 * permitUpdateType) { throw new
	 * SQLException("Error in validateOutwardPermit: OutwardUpdatePermitType is null"
	 * ); } else if (null == permitUpdateType.getDeclaration()) { throw new
	 * SQLException("Error in validateOutwardPermit: OutwardUpdatePermitType.Declaration is null"
	 * ); } else if (null == permitUpdateType.getDeclaration().getHeader()) { throw
	 * new
	 * SQLException("Error in validateOutwardPermit: OutwardUpdatePermitType.Declaration.Header is null"
	 * ); } else if (null ==
	 * permitUpdateType.getDeclaration().getHeader().getUniqueReferenceNumber()) {
	 * throw new
	 * SQLException("Error in validateOutwardPermit: OutwardUpdatePermitType.Declaration.Header.UniqueReferenceNumber is null"
	 * ); } else if (null == permitUpdateType.getPermit()) { throw new
	 * SQLException("Error in validateOutwardPermit: OutwardUpdatePermitType.Permit is null"
	 * ); } else if (null == tradeNetResponse.getRecipientID()) { throw new
	 * SQLException("Error in validateOutwardPermit: tradeNetResponse.recipientID is null"
	 * ); } else if (tradeNetResponse.getRecipientID().length() < 5) { throw new
	 * SQLException("Error in validateOutwardPermit: Invalid RecipientID format"); }
	 * else { saveUpdatePermitInfo(); }
	 * 
	 * }
	 * 
	 * private void saveUpdatePermitInfo() throws SQLException {
	 * 
	 * final String methodName = "saveUpdatePermitInfo"; Connection conn = null;
	 * PreparedStatement pstmt = null; String urn = null; int parameterIndex = 1;
	 * 
	 * try { conn = new CommonDAO().getPRSConnection(); pstmt =
	 * conn.prepareStatement(sqlStatementPermitInfo);
	 * 
	 * // URN UniqueReferenceNumberType urnType =
	 * permitUpdateType.getDeclaration().getHeader().getUniqueReferenceNumber();
	 * 
	 * urn = TradeNetResponseToPRSDB.formatURN(urnType);
	 * 
	 * //////////////////////////////////////////////////////////////////// // Add
	 * missing logic to check and remove existing record first
	 * ////////////////////////////////////////////////////////////////////
	 * TradeNetResponseToPRSDB.checkAndRemoveExistingPermitInfo(urn);
	 * ////////////////////////////////////////////////////////////////////
	 * 
	 * pstmt.setString(parameterIndex++, urn);
	 * 
	 * // PERMIT_NO pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.getPermit
	 * ().getPermitNumber()));
	 * 
	 * // MESSAGE_TYPE pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getHeader().getCommonAccessReference()));
	 * 
	 * // DEC_TYPE pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getHeader().getDeclarationType()));
	 * 
	 * // PERMIT_TYPE String permit_type = ""; if (null !=
	 * permitUpdateType.getUpdate()) { if (null !=
	 * permitUpdateType.getUpdate().getUpdateIndicatorCode()) { permit_type =
	 * permitUpdateType.getUpdate().getUpdateIndicatorCode(); } }
	 * pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permit_type));
	 * 
	 * // CERT_TYPE pstmt.setString(parameterIndex++, "");
	 * 
	 * // APPROVAL_DATE String approvalDate =
	 * permitUpdateType.getPermit().getPermitApprovalDatetime(); approvalDate =
	 * approvalDate.substring(0, 8); pstmt.setDate(parameterIndex++,
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(approvalDate));
	 * 
	 * String importerParty = (null != permitUpdateType.getDeclaration().getParty())
	 * ?
	 * TradeNetResponseToPRSDB.getPartyID(permitUpdateType.getDeclaration().getParty
	 * ().getImporterParty()) : ""; String exporterParty = (null !=
	 * permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getExporterParty()) ?
	 * TradeNetResponseToPRSDB.getPartyID(permitUpdateType.getDeclaration().getParty
	 * ().getExporterParty().getPartyDetail()) : ""; String inwardCrarierParty =
	 * (null != permitUpdateType.getDeclaration().getParty()) ?
	 * TradeNetResponseToPRSDB.getPartyID(permitUpdateType.getDeclaration().getParty
	 * ().getInwardCarrierAgentParty()) : ""; String delcarationAgentParty = (null
	 * != permitUpdateType.getDeclaration().getParty()) ?
	 * TradeNetResponseToPRSDB.getPartyID(permitUpdateType.getDeclaration().getParty
	 * ().getDeclaringAgentParty()) : ""; String freightForwarderParty = (null !=
	 * permitUpdateType.getDeclaration().getParty()) ?
	 * TradeNetResponseToPRSDB.getPartyID(permitUpdateType.getDeclaration().getParty
	 * ().getFreightForwarderParty()) : "";
	 * 
	 * // IMPORTER_EI pstmt.setString(parameterIndex++, importerParty); //
	 * EXPORTER_EI pstmt.setString(parameterIndex++, exporterParty);
	 * 
	 * // CARRIER_AGENT_EI pstmt.setString(parameterIndex++, inwardCrarierParty);
	 * 
	 * // DEC_AGENT_EI pstmt.setString(parameterIndex++, delcarationAgentParty);
	 * 
	 * // FREIGHT_FOR_EI pstmt.setString(parameterIndex++, freightForwarderParty);
	 * 
	 * // PLACE_RELEASE String releaseLocation = (null !=
	 * permitUpdateType.getDeclaration().getCargo()) ?
	 * TradeNetResponseToPRSDB.getLocationCodeAndName(permitUpdateType.
	 * getDeclaration().getCargo().getReleaseLocation()) : "";
	 * pstmt.setString(parameterIndex++, releaseLocation);
	 * 
	 * // PLACE_RECEIPT String recieptLocation = (null !=
	 * permitUpdateType.getDeclaration().getCargo()) ?
	 * TradeNetResponseToPRSDB.getLocationCodeAndName(permitUpdateType.
	 * getDeclaration().getCargo().getReceiptLocation()) : "";
	 * pstmt.setString(parameterIndex++, recieptLocation);
	 * 
	 * // COUNTRY_OF_ORIGIN pstmt.setString(parameterIndex++, "");
	 * 
	 * // COUNTRY_OF_DEST (get the outward transport final destination country
	 * pstmt.setString(parameterIndex++,
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getFinalDestinationCountry());
	 * 
	 * // TRANS_MODE int transMode = 0; if (null !=
	 * permitUpdateType.getDeclaration().getTransport()) { if (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport()) { if
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans()) { if (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans().getTransportMode()) { if (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans().getTransportMode().getModeCode()) { String transModeStr =
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans().getTransportMode().getModeCode(); transMode =
	 * Integer.parseInt(transModeStr); } }
	 * 
	 * } } } pstmt.setInt(parameterIndex++, transMode);
	 * 
	 * // USER_ID String id = tradeNetResponse.getRecipientID(); String[] splitID =
	 * id.split("\\."); String part2 = splitID[1]; pstmt.setString(parameterIndex++,
	 * part2.toLowerCase());
	 * 
	 * // CPC String cpc = ""; for (int i = 0; i <
	 * permitUpdateType.getDeclaration().getHeader().
	 * getCustomsProcedureCodeInformation().size(); i++) {
	 * 
	 * cpc += permitUpdateType.getDeclaration().getHeader().
	 * getCustomsProcedureCodeInformation().get(i);
	 * 
	 * if (i < permitUpdateType.getDeclaration().getHeader().
	 * getCustomsProcedureCodeInformation().size() - 1) { cpc += "/"; } }
	 * pstmt.setString(parameterIndex++, cpc);
	 * 
	 * // VALIDITY_START pstmt.setDate(parameterIndex++, null !=
	 * permitUpdateType.getPermit().getPermitValidityPeriod() ?
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(permitUpdateType.getPermit
	 * ().getPermitValidityPeriod().getStartDate()) : null);
	 * 
	 * // VALIDITY_END pstmt.setDate(parameterIndex++, null !=
	 * permitUpdateType.getPermit().getPermitValidityPeriod() ?
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(permitUpdateType.getPermit
	 * ().getPermitValidityPeriod().getEndDate()) : null);
	 * 
	 * // LAST_APP_DATE pstmt.setNull(parameterIndex++, Types.DATE);
	 * 
	 * // IMPORTER_NAME pstmt.setString(parameterIndex++, null !=
	 * permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getImporterParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getImporterParty().getPartyName(
	 * ) ?
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getImporterParty().getPartyName().getName(), ";") : "");
	 * 
	 * // EXPORTER_NAME pstmt.setString(parameterIndex++, null !=
	 * permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getExporterParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getExporterParty().
	 * getPartyDetail() && null !=
	 * permitUpdateType.getDeclaration().getParty().getExporterParty().
	 * getPartyDetail().getPartyName() ?
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getExporterParty().getPartyDetail().getPartyName().getName(), ";")
	 * : "");
	 * 
	 * // DEC_AGENT_NAME pstmt.setString(parameterIndex++, null !=
	 * permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getDeclaringAgentParty() && null
	 * != permitUpdateType.getDeclaration().getParty().getDeclaringAgentParty().
	 * getPartyName() ?
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getDeclaringAgentParty().getPartyName().getName(), ";") : "");
	 * 
	 * // HANDLING_AGENT_EI pstmt.setString(parameterIndex++, "");
	 * 
	 * // HANDLING_AGENT_NAME pstmt.setString(parameterIndex++, "");
	 * 
	 * // CLAIMANT_EI String claimantEI = ""; pstmt.setString(parameterIndex++,
	 * claimantEI);
	 * 
	 * // CLAIMANT_NAME String claimantName = ""; pstmt.setString(parameterIndex++,
	 * claimantName);
	 * 
	 * String freightForwarderEI = ""; String freightForwarderName = ""; if (null !=
	 * permitUpdateType.getDeclaration().getParty().getFreightForwarderParty()) {
	 * freightForwarderEI =
	 * TradeNetResponseToPRSDB.getPartyID(permitUpdateType.getDeclaration().getParty
	 * ().getFreightForwarderParty()); freightForwarderName =
	 * TradeNetResponseToPRSDB.getPartyName(permitUpdateType.getDeclaration().
	 * getParty().getFreightForwarderParty()); }
	 * 
	 * // FREIGHT_FORWARDER_EI pstmt.setString(parameterIndex++,
	 * freightForwarderEI);
	 * 
	 * // FREIGHT_FORWARDER_NAME pstmt.setString(parameterIndex++,
	 * freightForwarderName);
	 * 
	 * // DEPT_DATE String departureDate =
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getDepartureDate(); pstmt.setDate(parameterIndex++,
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(departureDate));
	 * 
	 * // double totalGrossWeight = 0.0; String totalGrossWeightUOM = ""; int
	 * totalOuterPack = 0; String totalOuterPackUOM = "";
	 * 
	 * // No duty for outward permit double totalCustomerDutyAmount = 0; double
	 * totalExciseDutyAmount = 0; double totalGSTAmount = 0; double totalPaid = 0;
	 * 
	 * if (null != permitUpdateType.getDeclaration().getSummary()) { if (null !=
	 * permitUpdateType.getDeclaration().getSummary().getTotalGrossWeight()) {
	 * totalGrossWeight =
	 * TradeNetResponseToPRSDB.nullSafeBigDecimalToDouble(permitUpdateType.
	 * getDeclaration().getSummary().getTotalGrossWeight().getValue());
	 * totalGrossWeightUOM =
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getSummary().getTotalGrossWeight().getUnitCode()); } if
	 * (null != permitUpdateType.getDeclaration().getSummary().getTotalOuterPack())
	 * { totalOuterPack =
	 * permitUpdateType.getDeclaration().getSummary().getTotalOuterPack().getValue()
	 * ; totalOuterPackUOM =
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getSummary().getTotalOuterPack().getUnitCode()); }
	 * 
	 * }
	 * 
	 * // TOT_GROSS_WT pstmt.setDouble(parameterIndex++, totalGrossWeight);
	 * 
	 * // TOT_GROSS_WT_UOM pstmt.setString(parameterIndex++, totalGrossWeightUOM);
	 * 
	 * // TOT_OUT_PACK pstmt.setInt(parameterIndex++, totalOuterPack);
	 * 
	 * // TOT_OUT_PACK_UOM pstmt.setString(parameterIndex++, totalOuterPackUOM);
	 * 
	 * // TOT_CUSTOMS_DUTY_AMT pstmt.setDouble(parameterIndex++,
	 * totalCustomerDutyAmount);
	 * 
	 * // TOT_EXCISE_DUTY_AMT pstmt.setDouble(parameterIndex++,
	 * totalExciseDutyAmount);
	 * 
	 * // TOT_GST_AMT pstmt.setDouble(parameterIndex++, totalGSTAmount);
	 * 
	 * // TOT_PAID pstmt.setDouble(parameterIndex++, totalPaid);
	 * 
	 * String enduserName = ""; String enduserAddress = "";
	 * 
	 * if (null != permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getEndUserParty()) {
	 * 
	 * enduserName = (null !=
	 * permitUpdateType.getDeclaration().getParty().getEndUserParty().getPartyName()
	 * ) ?
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getEndUserParty().getPartyName().getName(), "; ") : "";
	 * 
	 * enduserAddress = (null !=
	 * permitUpdateType.getDeclaration().getParty().getEndUserParty().getAddressLine
	 * ()) ?
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getEndUserParty().getAddressLine().getLine(), "; ") : "";
	 * 
	 * }
	 * 
	 * // END_USER_NAME pstmt.setString(parameterIndex++, enduserName);
	 * 
	 * // END_USER_ADDRESS pstmt.setString(parameterIndex++, enduserAddress);
	 * 
	 * // TOW_VESSEL_NAME pstmt.setString(parameterIndex++, (null !=
	 * permitUpdateType.getDeclaration().getTransport() && null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport() &&
	 * null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getAdditionalVesselInformation() && null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getAdditionalVesselInformation().getTowingVessel()) ?
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getAdditionalVesselInformation().getTowingVessel().getVesselName()) : "");
	 * 
	 * // AWB_BL pstmt.setString(parameterIndex++, "");
	 * 
	 * // REPL_PERMIT_NO String replacementPermitNumber = ""; if (null !=
	 * permitUpdateType.getUpdate()) { if (null !=
	 * permitUpdateType.getUpdate().getReplacementPermitNumber()) {
	 * replacementPermitNumber =
	 * permitUpdateType.getUpdate().getReplacementPermitNumber(); } }
	 * pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(replacementPermitNumber));
	 * 
	 * // VERSION pstmt.setString(parameterIndex++,
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(tradeNetResponse.
	 * getMessageVersion()));
	 * 
	 * String declarantName = ""; String declarantICNumber = "";
	 * 
	 * if (null != permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getDeclarantParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getDeclarantParty().
	 * getPersonInformation()) { declarantName =
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getDeclarantParty().getPersonInformation().getName(), "; ");
	 * declarantICNumber =
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getParty().getDeclarantParty().getPersonInformation().
	 * getCodeValue()); }
	 * 
	 * // DECLARANT_NAME pstmt.setString(parameterIndex++, declarantName);
	 * 
	 * // PASSPORT_IC_NUMBER pstmt.setString(parameterIndex++, declarantICNumber);
	 * 
	 * // SUBMISSION_DATE String submissionDate =
	 * permitUpdateType.getDeclaration().getHeader().getSubmissionDatetime();
	 * submissionDate = submissionDate.substring(0, 8);
	 * pstmt.setDate(parameterIndex++,
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(submissionDate));
	 * 
	 * // PORT_CODE pstmt.setString(parameterIndex++, null !=
	 * permitUpdateType.getDeclaration().getTransport() ? null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport() ?
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getTransport().getInwardTransport().getLoadingPort()) : "" :
	 * "");
	 * 
	 * // PORT_NAME pstmt.setString(parameterIndex++, "");
	 * 
	 * // DISCHARGE_PORT_CODE pstmt.setString(parameterIndex++, null !=
	 * permitUpdateType.getDeclaration().getTransport() ? null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport() ?
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getTransport().getOutwardTransport().getDischargePort()) :
	 * "" : "");
	 * 
	 * // DISCHARGE_PORT_NAME pstmt.setString(parameterIndex++, "");
	 * 
	 * // NEXT_PORT_CODE pstmt.setString(parameterIndex++, (null !=
	 * permitUpdateType.getDeclaration().getTransport() && null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport() &&
	 * null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getAdditionalVesselInformation()) ? TradeNetResponseToPRSDB
	 * .replaceNullWithEmptyString(permitUpdateType.getDeclaration().getTransport().
	 * getOutwardTransport().getAdditionalVesselInformation().getLoadingNextPort())
	 * : "");
	 * 
	 * // NEXT_PORT_NAME pstmt.setString(parameterIndex++, "");
	 * 
	 * // FINAL_PORT_CODE pstmt.setString(parameterIndex++, (null !=
	 * permitUpdateType.getDeclaration().getTransport() && null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport() &&
	 * null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getAdditionalVesselInformation()) ? TradeNetResponseToPRSDB
	 * .replaceNullWithEmptyString(permitUpdateType.getDeclaration().getTransport().
	 * getOutwardTransport().getAdditionalVesselInformation().getLoadingFinalPort())
	 * : "");
	 * 
	 * // FINAL_PORT_NAME pstmt.setString(parameterIndex++, "");
	 * 
	 * // LIC_NO String licences = ""; if (null !=
	 * permitUpdateType.getDeclaration().getLicence()) { for (int i = 0; i <
	 * permitUpdateType.getDeclaration().getLicence().size(); i++) { licences +=
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getLicence().get(i).getReferenceID()); if (i <
	 * permitUpdateType.getDeclaration().getLicence().size() - 1) { licences +=
	 * "; "; } } } pstmt.setString(parameterIndex++, licences);
	 * 
	 * // REMARKS String remarks = ""; if (null !=
	 * permitUpdateType.getDeclaration().getHeader().getRemarks()) { for (int i = 0;
	 * i < permitUpdateType.getDeclaration().getHeader().getRemarks().size(); i++) {
	 * 
	 * remarks +=
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getHeader().getRemarks().get(i).getFreeText(), "\r\n");
	 * 
	 * if (i < permitUpdateType.getDeclaration().getLicence().size() - 1) { remarks
	 * += "\r\n"; } } } pstmt.setString(parameterIndex++, remarks);
	 * 
	 * String inConveyanceRefNo = (null !=
	 * permitUpdateType.getDeclaration().getTransport()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport()) ?
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
	 * .replaceNullWithEmptyString(permitUpdateType.getDeclaration().getTransport().
	 * getInwardTransport().getTransportMeans().getTransportMode().
	 * getConveyanceReferenceNumber()) : "" : "" : "" : "";
	 * 
	 * // IN_CONVEYANCE_REFERENCE_NO pstmt.setString(parameterIndex++,
	 * inConveyanceRefNo);
	 * 
	 * // OUT_CONVEYANCE_REFERENCE_NO String outConveyanceRefNo = (null !=
	 * permitUpdateType.getDeclaration().getTransport()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport()) ?
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
	 * .replaceNullWithEmptyString(permitUpdateType.getDeclaration().getTransport().
	 * getOutwardTransport().getTransportMeans().getTransportMode().
	 * getConveyanceReferenceNumber()) : "" : "" : "" : "";
	 * pstmt.setString(parameterIndex++, outConveyanceRefNo);
	 * 
	 * // TOT_CHARGE pstmt.setDouble(parameterIndex++, 0);
	 * 
	 * // ARRIVAL_DATE Date arrivalDate = null;
	 * 
	 * if (null != permitUpdateType.getDeclaration().getTransport() && null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport()) {
	 * arrivalDate =
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(permitUpdateType.
	 * getDeclaration().getTransport().getInwardTransport().getArrivalDate()); }
	 * 
	 * pstmt.setDate(parameterIndex++, arrivalDate);
	 * 
	 * // IN_AWB_BL String inMAWBOUCROBLNumber = (null !=
	 * permitUpdateType.getDeclaration().getTransport()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport()) ?
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans()) ?
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getTransport().getInwardTransport().getTransportMeans().
	 * getMAWBOUCROBLNumber()) : "" : "" : "";
	 * 
	 * pstmt.setString(parameterIndex++, inMAWBOUCROBLNumber);
	 * 
	 * // OUT_AWB_BL String outMAWBOUCROBLNumber = (null !=
	 * permitUpdateType.getDeclaration().getTransport()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport()) ?
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans()) ?
	 * TradeNetResponseToPRSDB.replaceNullWithEmptyString(permitUpdateType.
	 * getDeclaration().getTransport().getOutwardTransport().getTransportMeans().
	 * getMAWBOUCROBLNumber()) : "" : "" : "";
	 * 
	 * pstmt.setString(parameterIndex++, outMAWBOUCROBLNumber);
	 * 
	 * // IN_TRANSPORT_ID String inTransportID = (null !=
	 * permitUpdateType.getDeclaration().getTransport()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport()) ?
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getInwardTransport().
	 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
	 * .replaceNullWithEmptyString(permitUpdateType.getDeclaration().getTransport().
	 * getInwardTransport().getTransportMeans().getTransportMode().
	 * getTransportIdentifier()) : "" : "" : "" : "";
	 * 
	 * pstmt.setString(parameterIndex++, inTransportID);
	 * 
	 * // OUT_TRANSPORT_ID String outTransportID = (null !=
	 * permitUpdateType.getDeclaration().getTransport()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport()) ?
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans()) ? (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans().getTransportMode()) ? TradeNetResponseToPRSDB
	 * .replaceNullWithEmptyString(permitUpdateType.getDeclaration().getTransport().
	 * getOutwardTransport().getTransportMeans().getTransportMode().
	 * getTransportIdentifier()) : "" : "" : "" : "";
	 * 
	 * pstmt.setString(parameterIndex++, outTransportID);
	 * 
	 * // OUT_TRANS_MODE int outTransMode = 0; if (null !=
	 * permitUpdateType.getDeclaration().getTransport()) { if (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport()) { if
	 * (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans()) { if (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans().getTransportMode()) { if (null !=
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getTransportMeans().getTransportMode().getModeCode()) { outTransMode =
	 * Integer.parseInt(permitUpdateType.getDeclaration().getTransport().
	 * getOutwardTransport().getTransportMeans().getTransportMode().getModeCode());
	 * } } } } } pstmt.setInt(parameterIndex++, outTransMode);
	 * 
	 * String consigneeName = (null != permitUpdateType.getDeclaration().getParty()
	 * && null != permitUpdateType.getDeclaration().getParty().getConsigneeParty()
	 * && null !=
	 * permitUpdateType.getDeclaration().getParty().getConsigneeParty().getPartyName
	 * ()) ?
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getConsigneeParty().getPartyName().getName(), "; ") : "";
	 * 
	 * // CONSIGNEE NAME pstmt.setString(parameterIndex++, consigneeName);
	 * 
	 * // CONSIGNEE ADDRESS String consigneeAddress = (null !=
	 * permitUpdateType.getDeclaration().getParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getConsigneeParty() && null !=
	 * permitUpdateType.getDeclaration().getParty().getConsigneeParty().getAddress()
	 * .getAddressLine()) ?
	 * TradeNetResponseToPRSDB.listToSingleStr(permitUpdateType.getDeclaration().
	 * getParty().getConsigneeParty().getAddress().getAddressLine().getLine(), "; ")
	 * : ""; pstmt.setString(parameterIndex++, consigneeAddress);
	 * 
	 * // AED_COMPLIANCE // if the declaration date is later than departure date, it
	 * is not AED compliance String aedCompliance = "Y"; String
	 * declarationDateAsString =
	 * permitUpdateType.getDeclaration().getHeader().getUniqueReferenceNumber().
	 * getDate(); String departureDateAsString =
	 * permitUpdateType.getDeclaration().getTransport().getOutwardTransport().
	 * getDepartureDate(); Date declarationDate =
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(declarationDateAsString);
	 * Date departure_date =
	 * TradeNetResponseToPRSDB.getSqlDateFromTN4_1DateStr(departureDateAsString);
	 * 
	 * if (declarationDate.after(departure_date)) { aedCompliance = "N"; }
	 * pstmt.setString(parameterIndex++, aedCompliance); // records.add(record);
	 * pstmt.executeUpdate(); } catch (SQLException e) { LOG.error(methodName,
	 * e.getMessage()); throw new SQLException(e); } finally {
	 * CommonDAO.closeResources(conn, pstmt, null); } if (null !=
	 * permitUpdateType.getDeclaration().getItem()) { for
	 * (crimsonlogic.tn41.schema.xsd.outwarddeclaration.ItemType item :
	 * permitUpdateType.getDeclaration().getItem()) { saveIntoPermitItem(item, urn);
	 * } }
	 * 
	 * }
	 */

}
