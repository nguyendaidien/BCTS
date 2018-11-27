package com.etrade.bcts.scheduler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.etrade.bcts.model.BctsJobHeader;
import com.etrade.bcts.model.BctsPermitType;
import com.etrade.bcts.model.CaaApprovalCondition;
import com.etrade.bcts.model.PermitCargo;
import com.etrade.bcts.model.PermitCargoCtn;
import com.etrade.bcts.model.PermitCertifate;
import com.etrade.bcts.model.PermitInTransport;
import com.etrade.bcts.model.PermitInvoice;
import com.etrade.bcts.model.PermitLicence;
import com.etrade.bcts.model.PermitOutTransport;
import com.etrade.bcts.model.PermitParty;
import com.etrade.bcts.model.PermitSummary;
import com.etrade.bcts.util.BctsConstants;
import com.etrade.bcts.util.BctsDateFormatter;
import com.etrade.bcts.util.BctsDateUtil;

import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.AdditionalVesselInformationType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.AddressLineType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.AddressType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.ChargeValueType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.ClaimantPartyType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.ConditionType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.ConsigneePartyType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.DeclarantPartyType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.EndUserPartyType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.ExporterPartyType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.ForeignAmountType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.InvoiceType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.InwardTransportType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.LicenceType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.LocationType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.MaritimeTransportType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.OutwardTransportType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PartyDetailType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PartyIdentificationType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PartyNameType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PeriodType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PermitType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PersonInformationType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.StorageLocationType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.TotalTariffRefundType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.TransportEquipmentType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.TransportMeansType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.TransportModeType;
import crimsonlogic.tn41.schema.xsd.commonbasiccomponents_2.Amount;
import crimsonlogic.tn41.schema.xsd.commonbasiccomponents_2.TotalGrossWeight;
import crimsonlogic.tn41.schema.xsd.commonbasiccomponents_2.TotalOuterPack;
import crimsonlogic.tn41.schema.xsd.inpayment.InPaymentPermitType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.CargoType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.CertificateType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.OutwardPermitType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.PartyType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.SummaryType;

public class PermitXmlConstructor {
	
	
	
	
	/**
	 * Construct Permit Vo for storing in permit table
	 * @author ajayasamanta
	 * @param outWPermit
	 * @param inPaymtPermit
	 * @param bctsJobHeader
	 * @return
	 */
	public static BctsJobHeader consructPermit(OutwardPermitType outWPermit,InPaymentPermitType inPaymtPermit,BctsJobHeader bctsJobHeader) {
		BctsPermitType bctsPermitType=new BctsPermitType();
		List<BctsPermitType> permitTypeLst=new ArrayList<>();
		bctsPermitType.setUenId(bctsJobHeader.getUenId());
		bctsPermitType.setUrnSeq(bctsJobHeader.getUrnSeq());
		PermitType pType=null;
		if(null!=outWPermit) {
			pType=outWPermit.getPermit();
		}else if(null!=inPaymtPermit) {
			pType=inPaymtPermit.getPermit();
		}
		if(null!=pType && null!=pType.getPermitApprovalDatetime()) {
			String perApDate=BctsDateUtil.truncateDate(BctsDateUtil.TRW_DATETIME_NO_TIMEZONE, pType.getPermitApprovalDatetime());
			bctsPermitType.setPermApprDateAndTime(BctsDateFormatter.convertDateTime(perApDate));
			bctsPermitType.setCertNumber(pType.getCertificateNumber());
			}
			if(null!=pType && null!=pType.getCAApprovalDatetime()) {
			String caaApDate=BctsDateUtil.truncateDate(BctsDateUtil.TRW_DATETIME_NO_TIMEZONE, pType.getCAApprovalDatetime());		
			bctsPermitType.setCaaApproDateAndTime(BctsDateFormatter.convertDateTime(caaApDate));
			}
			PeriodType peroidType=null!=pType?pType.getPermitValidityPeriod():null;
			if(null!=peroidType) {
			bctsPermitType.setValPeriodStart(BctsDateFormatter.convertDateTime(peroidType.getStartDate()));
			bctsPermitType.setValPeriodEnd(BctsDateFormatter.convertDateTime(peroidType.getEndDate()));
			}
			bctsPermitType.setPermitNumber(null!=pType?pType.getPermitNumber():null);
			bctsPermitType.setJobHeader(bctsJobHeader);
			permitTypeLst.add(bctsPermitType);
			bctsJobHeader.setPermits(permitTypeLst);
		return bctsJobHeader;
	}
	
	
	/**
	 * This method will costruct Summary information to store in permit Summary table
	 * @author ajayasamanta
	 * @param inPaymtPermit
	 * @param outWPermit
	 * @param bctsJobHeader
	 * @return
	 */
	public static BctsJobHeader constructSummary(InPaymentPermitType inPaymtPermit, OutwardPermitType outWPermit,
			BctsJobHeader bctsJobHeader) {
		PermitSummary permSum = new PermitSummary();
		List<PermitSummary> permitSumLst = new ArrayList<>();
		permSum.setUreId(bctsJobHeader.getUenId());
		permSum.setUreSeq(bctsJobHeader.getUrnSeq());
		if (null != outWPermit) {
			SummaryType summaryType = outWPermit.getDeclaration().getSummary();
			if (null != summaryType) {
				permSum.setNoOfItems(summaryType.getNumberOfItems());
				permSum.setTotCifFob(summaryType.getTotalCIFFOBValue());
				TotalOuterPack outerPackType = summaryType.getTotalOuterPack();
				if (null != outerPackType) {
					permSum.setTotOutPckUom(outerPackType.getUnitCode());
					permSum.setTotOutPck(BigDecimal.valueOf(outerPackType.getValue()));
				}
				TotalGrossWeight grossWeiType = summaryType.getTotalGrossWeight();
				if (null != grossWeiType) {
					permSum.setTotGrossWgtUom(grossWeiType.getUnitCode());
					permSum.setTotGrossWgt(grossWeiType.getValue());
				}
				permitSumLst.add(permSum);
			}
		}
		if (null != inPaymtPermit) {
			crimsonlogic.tn41.schema.xsd.inpayment.SummaryType summaryType = inPaymtPermit.getDeclaration()
					.getSummary();

			if (null != summaryType) {
				permSum.setNoOfItems(summaryType.getNumberOfItems());
				permSum.setTotCifFob(summaryType.getTotalCIFFOBValue());
				TotalOuterPack outerPackType = summaryType.getTotalOuterPack();
				if (null != outerPackType) {
					permSum.setTotOutPckUom(outerPackType.getUnitCode());
					permSum.setTotOutPck(BigDecimal.valueOf(outerPackType.getValue()));
				}
				TotalGrossWeight grossWeiType = summaryType.getTotalGrossWeight();
				if (null != grossWeiType) {
					permSum.setTotGrossWgtUom(grossWeiType.getUnitCode());
					permSum.setTotGrossWgt(grossWeiType.getValue());
				}
				TotalTariffRefundType refundType = summaryType.getTotalTariffRefund();
				if (null != refundType) {
					permSum.setTotRefCustAmt(refundType.getTotalCustomsDutyRefundAmount());
					permSum.setTotRefExcAmt(refundType.getTotalExciseDutyRefundAmount());
					permSum.setTotRefGstAmt(refundType.getTotalGoodsAndServicesTaxRefundAmount());
					permSum.setTotRefOthTaxAmt(refundType.getTotalOtherTaxRefundAmount());
				}
				permitSumLst.add(permSum);
			}
		}
		bctsJobHeader.setSummary(permitSumLst);
		return bctsJobHeader;
	}
	
	
	
	
	/**
	 * This method will construct permit condition to store in approval condition table
	 * @author ajayasamanta
	 * @param appCondLst
	 * @param aprType
	 * @param conTypeLst
	 * @param bctsJobHeader
	 * @return
	 */
	public static List<CaaApprovalCondition> consructPermitApprCond(List<CaaApprovalCondition> appCondLst,String aprType,List<ConditionType> conTypeLst,BctsJobHeader bctsJobHeader) {
		int counter=1;
		for(ConditionType cType:conTypeLst) {
			CaaApprovalCondition appCond=new CaaApprovalCondition();
			appCond.setAgencyCode(cType.getAgencyCode());
			appCond.setConditionCode(cType.getConditionCode());
			appCond.setCondtionDesc1(cType.getConditionDescription());
			appCond.setAprovalType(aprType);
			appCond.setUenId(bctsJobHeader.getUenId());
			appCond.setUrnSeq(bctsJobHeader.getUrnSeq());
			appCond.setSeqNo(new BigDecimal(counter));
			counter+=1;
			appCondLst.add(appCond);
		}
		return appCondLst;
	}
	
	
	/**
	 * Construct PermitCertificate Vo for storing in permit certificate header table
	 * @author ajayasamanta
	 * @param outWPermit
	 * @param bctsJobHeader
	 * @return
	 */
	public static BctsJobHeader consructCertificate(OutwardPermitType outWPermit, BctsJobHeader bctsJobHeader) {
		CertificateType certType = null != outWPermit.getDeclaration() ? outWPermit.getDeclaration().getCertificate()
				: null;
		List<PermitCertifate> permitCertList = new ArrayList<>();
		PermitCertifate permitCert = new PermitCertifate();
		permitCert.setUreId(bctsJobHeader.getUenId());
		permitCert.setUreSeq(bctsJobHeader.getUrnSeq());
		if (null != certType) {
			permitCert.setApprProdType(certType.getApplicationProductType());
			permitCert.setEnteryYear(certType.getEntryYear());
			permitCert.setGspDonCnCode(certType.getGSPDonorCountry());
			permitCert.setPrefPer(certType.getPreferenceContentPercent());
			permitCert.setCurrCode(certType.getCurrencyCode());
			permitCert.setCertiDetails(null != certType.getAdditionalCertificateDetails()
					? certType.getAdditionalCertificateDetails().toString()
					: null);
			permitCert.setTransDetails(
					null != certType.getTransportDetails() ? certType.getTransportDetails().toString() : null);
			permitCert
					.setCert1Type(null != certType.getCertificateDetail() && !certType.getCertificateDetail().isEmpty()
							? certType.getCertificateDetail().get(0).getCertificateType()
							: null);
			permitCert.setCert1Copies(
					null != certType.getCertificateDetail() && !certType.getCertificateDetail().isEmpty()
							? certType.getCertificateDetail().get(0).getCopiesNumeric()
							: null);
			permitCert
					.setCert2Type(null != certType.getCertificateDetail() && certType.getCertificateDetail().size() > 1
							? certType.getCertificateDetail().get(1).getCertificateType()
							: null);
			permitCert.setCert2Copies(
					null != certType.getCertificateDetail() && certType.getCertificateDetail().size() > 1
							? certType.getCertificateDetail().get(1).getCopiesNumeric()
							: null);
			permitCertList.add(permitCert);
		}
		bctsJobHeader.setCertificates(permitCertList);
		return bctsJobHeader;
	}
	
	/**
	 * This method will construct Invoice details for permit and store in bcts invoice table
	 * @author ajayasamanta
	 * @param bctsJobHeader
	 * @param inPaymtPermit
	 * @return
	 */
	public static BctsJobHeader constructInvoice(BctsJobHeader bctsJobHeader,InPaymentPermitType inPaymtPermit) {
		PermitInvoice permitInv=null;
		List<PermitInvoice> pInVoiceLst=new ArrayList<>();
		List<InvoiceType> invTypeLst=inPaymtPermit.getDeclaration().getInvoice();
		int seqNo=1;
		for(InvoiceType invType:invTypeLst) {
			permitInv=new PermitInvoice();
			permitInv.setUreId(bctsJobHeader.getUenId());
			permitInv.setUreSeq(bctsJobHeader.getUrnSeq());
			permitInv.setInvSeqNo(new BigDecimal(seqNo));
			seqNo+=1;
			ChargeValueType crgValType=invType.getFreightCharge();
			if(null!=crgValType) {
			Amount crgValAmtTyp=crgValType.getAmount();
			permitInv.setFrgCrgExRate(crgValType.getExchangeRate());
			permitInv.setFrgCrgPercent(crgValType.getChargePercent());
			permitInv.setFrgCrgAmt(crgValAmtTyp.getValue());
			permitInv.setFrgCrgCurCode(crgValAmtTyp.getCurrencyID());
			}
			
			ChargeValueType insuValType=invType.getInsuranceCharge();
			if(null!=insuValType) {
			Amount insuValAmtTyp=insuValType.getAmount();
			permitInv.setInsCrgPercent(insuValType.getChargePercent());
			permitInv.setInsCrgExRate(insuValType.getExchangeRate());
			permitInv.setInsCrgCurCode(insuValAmtTyp.getCurrencyID());
			permitInv.setInsCrgAmt(insuValAmtTyp.getValue());
			}
			
			String inVdate=invType.getInvoiceDate();
			permitInv.setInvDate(BctsDateFormatter.convertDateTime(inVdate));
			permitInv.setInvNo(invType.getInvoiceNumber());
			
			ChargeValueType othTaxValType=invType.getOtherTaxableCharge();
			if(null!=othTaxValType) {
			Amount othTaxAmtTyp=othTaxValType.getAmount();
			permitInv.setOthCrgAmt(othTaxAmtTyp.getValue());
			permitInv.setOthCrgCurCode(othTaxAmtTyp.getCurrencyID());
			permitInv.setOthCrgExRate(othTaxValType.getExchangeRate());
			permitInv.setOthCrgPercent(othTaxValType.getChargePercent());
			}
			
			PersonInformationType suplManPartyType=invType.getSupplierManufacturerParty();
			permitInv.setPartyCode(null!=suplManPartyType?suplManPartyType.getCodeValue():null);
			List<String> prtNamLst=null!=suplManPartyType?suplManPartyType.getName():null;
			permitInv.setPartyName1(null!=prtNamLst && !prtNamLst.isEmpty()?prtNamLst.get(0):null);
			permitInv.setPartyName2(null!=prtNamLst && prtNamLst.size()>1?prtNamLst.get(1):null);
			permitInv.setPartyName3(null!=prtNamLst && prtNamLst.size()>2?prtNamLst.get(2):null);
			
			ForeignAmountType forAmtType=invType.getTotalInvoiceValue();
			if(null!=forAmtType) {
			Amount amountTyp=forAmtType.getAmount();
			permitInv.setFrgCrgExRate(forAmtType.getExchangeRate());
			permitInv.setFrgCrgAmt(amountTyp.getValue());
			permitInv.setFrgCrgCurCode(amountTyp.getCurrencyID());
			permitInv.setuPriceTermType(invType.getUnitPriceTermType());
			}
			pInVoiceLst.add(permitInv);
		}
		bctsJobHeader.setInvoicesLst(pInVoiceLst);
		return bctsJobHeader;
	}
	
	
	
	/**
	 * License Construct OutwardPermitType
	 * @param bctsPermitType
	 * @param outWPermit
	 * @param urn
	 * @param uid
	 * @return
	 */
	public static BctsJobHeader costructLicence(BctsJobHeader bctsJobHeader,OutwardPermitType outWPermit) {
		PermitLicence permitL=new PermitLicence();
		List<PermitLicence> licPerList=new ArrayList<>();
		permitL.setUreId(bctsJobHeader.getUenId());
		permitL.setUreSeq(bctsJobHeader.getUrnSeq());
		List<LicenceType> licencTypeList=null!=outWPermit.getDeclaration()?outWPermit.getDeclaration().getLicence():null;
		if(null!=licencTypeList) {
		permitL.setLicence1(!licencTypeList.isEmpty()?licencTypeList.get(0).getReferenceID():null);
		permitL.setLicence2(licencTypeList.size()>1?licencTypeList.get(1).getReferenceID():null);
		permitL.setLicence3(licencTypeList.size()>2?licencTypeList.get(2).getReferenceID():null);
		permitL.setLicence4(licencTypeList.size()>3?licencTypeList.get(3).getReferenceID():null);
		permitL.setLicence5(licencTypeList.size()>4?licencTypeList.get(4).getReferenceID():null);
		}
		
		licPerList.add(permitL);
		bctsJobHeader.setLicenseLst(licPerList);
		return bctsJobHeader;
		
	}
	
	
	/**
	 * License Construct InPaymentPermitType
	 * @param bctsPermitType
	 * @param inPaymtPermit
	 * @param urn
	 * @param uid
	 * @return
	 */
	public static BctsJobHeader costructLicence(BctsJobHeader bctsJobHeader,InPaymentPermitType inPaymtPermit) {
		PermitLicence permitL=new PermitLicence();
		List<PermitLicence> licPerList=new ArrayList<>();
		permitL.setUreId(bctsJobHeader.getUenId());
		permitL.setUreSeq(bctsJobHeader.getUrnSeq());
		List<LicenceType> licencTypeList=null!=inPaymtPermit.getDeclaration()?inPaymtPermit.getDeclaration().getLicence():null;
		if(null!=licencTypeList) {
		permitL.setLicence1(!licencTypeList.isEmpty()?licencTypeList.get(0).getReferenceID():null);
		permitL.setLicence2(licencTypeList.size()>1?licencTypeList.get(1).getReferenceID():null);
		permitL.setLicence3(licencTypeList.size()>2?licencTypeList.get(2).getReferenceID():null);
		permitL.setLicence4(licencTypeList.size()>3?licencTypeList.get(3).getReferenceID():null);
		permitL.setLicence5(licencTypeList.size()>4?licencTypeList.get(4).getReferenceID():null);
		}
		licPerList.add(permitL);
		bctsJobHeader.setLicenseLst(licPerList);
		return bctsJobHeader;
	}
	
	/**
	 * This method will construct Cargo details for both in permit and out permit
	 * @author ajayasamanta
	 * @param outpCargoType
	 * @param inpCargoType
	 * @param bctsJobHeader
	 * @return
	 */
	public static BctsJobHeader generatePermitCargo(CargoType outpCargoType,crimsonlogic.tn41.schema.xsd.inpayment.CargoType inpCargoType,BctsJobHeader bctsJobHeader) {
		List<PermitCargo> cargoList=new ArrayList<>();
		PermitCargo permitCargo=new PermitCargo();
		List<PermitCargoCtn> permitCarolst=new ArrayList<>();
		LocationType recLoType=null;
		LocationType relLoType=null;
		StorageLocationType storLocType=null;
		List<TransportEquipmentType> transEqpTypeLst=null;
		if(null!=outpCargoType) {
			recLoType=outpCargoType.getReceiptLocation();
			relLoType=outpCargoType.getReleaseLocation();
			storLocType=outpCargoType.getStorageLocation();
			transEqpTypeLst=outpCargoType.getTransportEquipment();
			permitCargo.setImpBkpStDate(BctsDateFormatter.convertDateTime(outpCargoType.getBlanketStartDate()));
		}
		
		if(null!=inpCargoType) {
			recLoType=inpCargoType.getReceiptLocation();
			relLoType=inpCargoType.getReleaseLocation();
			storLocType=inpCargoType.getStorageLocation();
			transEqpTypeLst=inpCargoType.getTransportEquipment();
			permitCargo.setImpBkpStDate(BctsDateFormatter.convertDateTime(inpCargoType.getBlanketStartDate()));
		}
		
			permitCargo.setRecLocCode(null!=recLoType?recLoType.getLocationCode():null);
			permitCargo.setRecLocName(null!=recLoType?recLoType.getLocationName():null);
			permitCargo.setRelLocCode(null!=relLoType?relLoType.getLocationCode():null);
			permitCargo.setRelLocName(null!=relLoType?relLoType.getLocationName():null);
			permitCargo.setStorLocCode(null!=storLocType?storLocType.getLocationCode():null);
			
			for(TransportEquipmentType transEqpType:transEqpTypeLst) {
				PermitCargoCtn permitCaroCnt=new PermitCargoCtn();
				permitCaroCnt.setUreId(bctsJobHeader.getUenId());
				permitCaroCnt.setUreSeq(bctsJobHeader.getUrnSeq());
				permitCaroCnt.setCtnSize(transEqpType.getSizeTypeCode());
				permitCaroCnt.setCtnNo(transEqpType.getEquipmentID());
				permitCaroCnt.setCtnSealNo(transEqpType.getTransportEquipmentSeal().getSealID());
				permitCaroCnt.setCtnWgt(transEqpType.getEquipmentWeightMeasureNumeric());
				permitCaroCnt.setSeqNo(transEqpType.getSequenceNumeric());
				permitCarolst.add(permitCaroCnt);
			}
		permitCargo.setPermitCCtnLst(permitCarolst);
		permitCargo.setUreId(bctsJobHeader.getUenId());
		permitCargo.setUreSeq(bctsJobHeader.getUrnSeq());
		cargoList.add(permitCargo);
		bctsJobHeader.setCargoList(cargoList);
		return bctsJobHeader;
	}
	
	/**
	 * This method will constructPermitTraport for both inward or outward
	 * @author ajayasamanta
	 * @param inwordTrans
	 * @param ouWordTrans
	 * @param bctsJobHeader
	 * @return
	 */
	public static BctsJobHeader constructTrans(InwardTransportType inwordTrans, OutwardTransportType ouWordTrans,
			BctsJobHeader bctsJobHeader) {
		if (null != inwordTrans) {
			PermitInTransport inTran = new PermitInTransport();
			List<PermitInTransport> permitInLst = new ArrayList<>();
			inTran.setLoadingPortCode(inwordTrans.getLoadingPort());
			inTran.setArraivalDate(BctsDateFormatter.convertDateTime(inwordTrans.getArrivalDate()));
			TransportMeansType transPortMeansType = inwordTrans.getTransportMeans();
			inTran.setMastShipDocNo(null != transPortMeansType ? transPortMeansType.getMAWBOUCROBLNumber() : null);
			TransportModeType transModeType = null != transPortMeansType ? transPortMeansType.getTransportMode() : null;
			inTran.setTransPrtIdentifier(null != transModeType ? transModeType.getTransportIdentifier() : null);
			inTran.setTransModeCode(null != transModeType ? transModeType.getModeCode() : null);
			inTran.setUreId(bctsJobHeader.getUenId());
			inTran.setUreSeq(bctsJobHeader.getUrnSeq());
			permitInLst.add(inTran);
			bctsJobHeader.setInTransList(permitInLst);
		}
		if (null != ouWordTrans) {
			List<PermitOutTransport> permOutInLst = new ArrayList<>();
			PermitOutTransport outTrans = new PermitOutTransport();
			outTrans.setDepartDate(BctsDateFormatter.convertDateTime(ouWordTrans.getDepartureDate()));
			outTrans.setDisPortCode(ouWordTrans.getDischargePort());
			outTrans.setFinalCtryCode(ouWordTrans.getFinalDestinationCountry());
			AdditionalVesselInformationType addVesInfoType = ouWordTrans.getAdditionalVesselInformation();

			outTrans.setFinalPortCode(null != addVesInfoType ? addVesInfoType.getLoadingFinalPort() : null);
			outTrans.setNextPortCode(null != addVesInfoType ? addVesInfoType.getLoadingNextPort() : null);
			outTrans.setNrt(null != addVesInfoType ? addVesInfoType.getNetRegisterTonnage() : null);
			outTrans.setVesNation(null != addVesInfoType ? addVesInfoType.getVesselNationality() : null);
			outTrans.setVesType(null != addVesInfoType ? addVesInfoType.getVesselType() : null);
			MaritimeTransportType mariTransType = null != addVesInfoType ? addVesInfoType.getTowingVessel() : null;
			outTrans.setVesName(null != mariTransType ? mariTransType.getVesselName() : null);

			TransportMeansType transPortMeansType = ouWordTrans.getTransportMeans();
			outTrans.setMastShipDocNo(null != transPortMeansType ? transPortMeansType.getMAWBOUCROBLNumber() : null);
			TransportModeType modeType = null != transPortMeansType ? transPortMeansType.getTransportMode() : null;
			outTrans.setTransModeCode(null != modeType ? modeType.getModeCode() : null);
			outTrans.setTransPrtIdentifier(null != modeType ? modeType.getTransportIdentifier() : null);
			outTrans.setUreId(bctsJobHeader.getUenId());
			outTrans.setUreSeq(bctsJobHeader.getUrnSeq());
			permOutInLst.add(outTrans);
			bctsJobHeader.setOutTransList(permOutInLst);
		}
		return bctsJobHeader;
	}
	
	
	/**
	    * InPaymentPermitType Party Construction
	    * @param bctsPermitType
	    * @param inPaymtPermit
	    * @param urn
	    * @param uid
	    * @return
	    */
	   public static BctsJobHeader cnstInPayPrty(InPaymentPermitType inPaymtPermit,BctsJobHeader bctsJobHeader) {
		   crimsonlogic.tn41.schema.xsd.inpayment.PartyType inPayPartyType=inPaymtPermit.getDeclaration().getParty();
		   PermitParty permitPrty=null;
		   List<PermitParty> permitPartyList=new ArrayList<>();
		   /*Declerant party Start*/
			if(null!=inPayPartyType.getDeclarantParty()) {
				permitPrty=cnstDeclParty("DP",inPayPartyType.getDeclarantParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*Declerant party End*/
			
			/*Declerant Agent Start*/
			if(null!=inPayPartyType.getDeclaringAgentParty()) {
			permitPrty=cnstPermitParty("DA",inPayPartyType.getDeclaringAgentParty(),bctsJobHeader);
			permitPartyList.add(permitPrty);
			}
			/*Declerant party End*/
			
			/*FreightForwareder party Start*/
			if(null!=inPayPartyType.getFreightForwarderParty()) {
				permitPrty=cnstPermitParty("FR",inPayPartyType.getFreightForwarderParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*FreightForwareder party end*/
			
			/*impoPrtyType party Start*/
			if(null!=inPayPartyType.getImporterParty()) {
			permitPrty=cnstPermitParty("IM",inPayPartyType.getImporterParty(),bctsJobHeader);
			permitPartyList.add(permitPrty);
			}
			/*impoPrtyType party End*/
			
			/*inWrdCrrAgntPrtType party Start*/
			if(null!=inPayPartyType.getInwardCarrierAgentParty()) {
			permitPrty=cnstPermitParty("IW",inPayPartyType.getInwardCarrierAgentParty(),bctsJobHeader);
			permitPartyList.add(permitPrty);
			}
			/*inWrdCrrAgntPrtType party End*/
			
			/*ClaimantParty party Start*/
			if(null!=inPayPartyType.getClaimantParty()) {
				permitPrty=cnstClaimnParty("CL",inPayPartyType.getClaimantParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*ClaimantParty party End*/
			bctsJobHeader.setPermitPartyLst(permitPartyList);
		   return bctsJobHeader;
	   }
	   
	   
	   /**
	    * OutwardPermitType Party construction method
	    * @param bctsPermitType
	    * @param outWPermit
	    * @param urn
	    * @param uid
	    * @return
	    */
	   public static BctsJobHeader cnstOutPayPrty(OutwardPermitType outWPermit,BctsJobHeader bctsJobHeader) {
		   PermitParty permitPrty=null;
		   List<PermitParty> permitPartyList=new ArrayList<>();
			PartyType partyType=outWPermit.getDeclaration().getParty();
			/*Consignee party Start*/
			if(null!=partyType && null!=partyType.getConsigneeParty()) {
				permitPrty=PermitXmlConstructor.cnstConsigneeParty(BctsConstants.CONSPRTYCODE, partyType.getConsigneeParty(), bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*Consignee party End*/
			
			/*Declerant party Start*/
			if(null!=partyType.getDeclarantParty()) {
				permitPrty=PermitXmlConstructor.cnstDeclParty(BctsConstants.DCLRNTPRTYCODE, partyType.getDeclarantParty(), bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*Declerant party End*/
			
			/*Declerant Agent Start*/
			if(null!=partyType.getDeclaringAgentParty()) {
			permitPrty=PermitXmlConstructor.cnstPermitParty(BctsConstants.DCLRAGENTCODE, partyType.getDeclaringAgentParty(), bctsJobHeader);
			permitPartyList.add(permitPrty);
			}
			/*Declerant party End*/
			
			/*Enduser party Start*/
			if(null!=partyType.getEndUserParty()) {
				permitPrty=PermitXmlConstructor.cnstEndUserParty(BctsConstants.ENDUSERPRTYCODE,partyType.getEndUserParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*Enduser party End*/
			
			/*FreightForwareder party Start*/
			if(null!=partyType.getFreightForwarderParty()) {
				permitPrty=PermitXmlConstructor.cnstPermitParty(BctsConstants.FREGHFORWPRTYCODE,partyType.getFreightForwarderParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*FreightForwareder party end*/
			
			/*impoPrtyType party Start*/
			if(null!=partyType.getImporterParty()) {
				permitPrty=PermitXmlConstructor.cnstPermitParty("IM",partyType.getImporterParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*impoPrtyType party End*/
			
			/*inWrdCrrAgntPrtType party Start*/
			if(null!=partyType.getInwardCarrierAgentParty()) {
				permitPrty=PermitXmlConstructor.cnstPermitParty("IW",partyType.getInwardCarrierAgentParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*inWrdCrrAgntPrtType party End*/
			
			/*outWrdCrrAgntPrtType party Start*/
			if(null!=partyType.getOutwardCarrierAgentParty()) {
				permitPrty=PermitXmlConstructor.cnstPermitParty("OW",partyType.getOutwardCarrierAgentParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*outWrdCrrAgntPrtType party End*/
			
			/*expoPrtyType party Start*/
			ExporterPartyType expoPrtyType=partyType.getExporterParty();
			if(null!=expoPrtyType) {
				permitPrty=PermitXmlConstructor.cnstExporterParty("EP",partyType.getExporterParty(),bctsJobHeader);
				permitPartyList.add(permitPrty);
			}
			/*expoPrtyType party End*/
			
			
			/*exporManuFType party Start*/
			if(null!=partyType.getManufacturerParty()) {
				permitPrty=PermitXmlConstructor.cnstExporterParty("EM",partyType.getManufacturerParty(),bctsJobHeader);
			permitPartyList.add(permitPrty);
			}
			/*exporManuFType party End*/
			bctsJobHeader.setPermitPartyLst(permitPartyList);
			return bctsJobHeader;
	   }
	   
	   
	   public static PermitParty cnstDeclParty(String indType,DeclarantPartyType delcPrtType,BctsJobHeader bctsJobHeader) {
		   	PermitParty permitPrty=new PermitParty();
		   	permitPrty.setUreId(bctsJobHeader.getUenId());
			permitPrty.setUreSeq(bctsJobHeader.getUrnSeq());
			permitPrty.setPartyInd(indType);
			permitPrty.setContactNo(delcPrtType.getTelephone());
			
			PersonInformationType perInfoType=delcPrtType.getPersonInformation();
			permitPrty.setPostalCode(null!=perInfoType?perInfoType.getCodeValue():null);
			List<String> perNameLst=null!=perInfoType?perInfoType.getName():null;
			permitPrty.setPerName1(null!=perNameLst && !perNameLst.isEmpty()?perNameLst.get(0):null);
			permitPrty.setPerName2(null!=perNameLst && perNameLst.size()>1?perNameLst.get(1):null);
			permitPrty.setPerName3(null!=perNameLst && perNameLst.size()>2?perNameLst.get(2):null);
		   return permitPrty;
	  }
	   
	   
	   
	   public static PermitParty cnstExporterParty(String indType,ExporterPartyType exporManuFType,BctsJobHeader bctsJobHeader) {
		   	PermitParty permitPrty=new PermitParty();
		   	AddressType expoManuAddType=null!=exporManuFType?exporManuFType.getAddress():null;
			permitPrty.setUreId(bctsJobHeader.getUenId());
			permitPrty.setUreSeq(bctsJobHeader.getUrnSeq());
			permitPrty.setPartyInd(indType);
			if(null!=expoManuAddType) {
				permitPrty.setCityName(expoManuAddType.getCityName());
				permitPrty.setCountryCode(expoManuAddType.getCountryCode());
				permitPrty.setCitySubName(expoManuAddType.getCountrySubentity());
				permitPrty.setCityCode(expoManuAddType.getCountrySubentityCode());
				permitPrty.setPostalCode(expoManuAddType.getPostalZone());
				}
			
			AddressLineType expoManuAddLineType=null!=exporManuFType?exporManuFType.getAddressLine():null;
			permitPrty.setAddr1(null!=expoManuAddLineType && !expoManuAddLineType.getLine().isEmpty()?expoManuAddLineType.getLine().get(0):null);
			permitPrty.setAddr2(null!=expoManuAddLineType && expoManuAddLineType.getLine().size()>1?expoManuAddLineType.getLine().get(1):null);
			permitPrty.setAddr3(null!=expoManuAddLineType && expoManuAddLineType.getLine().size()>2?expoManuAddLineType.getLine().get(2):null);
			
			PartyDetailType expoManuPrtyDtlsType=null!=exporManuFType?exporManuFType.getPartyDetail():null;
			PartyIdentificationType expoManuIdentType=null!=expoManuPrtyDtlsType?expoManuPrtyDtlsType.getPartyIdentification():null;
			permitPrty.setPerId(null!=expoManuIdentType?expoManuIdentType.getID():null);
			PartyNameType expoManuNameType=null!=expoManuPrtyDtlsType?expoManuPrtyDtlsType.getPartyName():null;
			List<String> expoManuNameLst=null!=expoManuNameType?expoManuNameType.getName():null;
			permitPrty.setPerName1(null!=expoManuNameLst && !expoManuNameLst.isEmpty()?expoManuNameLst.get(0):null);
			permitPrty.setPerName2(null!=expoManuNameLst && expoManuNameLst.size()>1?expoManuNameLst.get(1):null);
			permitPrty.setPerName3(null!=expoManuNameLst && expoManuNameLst.size()>2?expoManuNameLst.get(2):null);
		   return permitPrty;
	  }
	   
	   public static PermitParty cnstEndUserParty(String indType,EndUserPartyType endUsrPrtuType,BctsJobHeader bctsJobHeader) {
		   	PermitParty permitPrty=new PermitParty();
		   	PartyNameType endUrPrtyNameType=null!=endUsrPrtuType?endUsrPrtuType.getPartyName():null;
			AddressType endUsraddressType=null!=endUsrPrtuType?endUsrPrtuType.getAddress():null;
			
			List<String> endUsrPrtNmLst=null!=endUrPrtyNameType?endUrPrtyNameType.getName():null;
			permitPrty.setPerName1(null!=endUsrPrtNmLst && !endUsrPrtNmLst.isEmpty()?endUsrPrtNmLst.get(0):null);
			permitPrty.setPerName2(null!=endUsrPrtNmLst && endUsrPrtNmLst.size()>1?endUsrPrtNmLst.get(1):null);
			permitPrty.setPerName3(null!=endUsrPrtNmLst && endUsrPrtNmLst.size()>2?endUsrPrtNmLst.get(2):null);
			
			permitPrty.setUreId(bctsJobHeader.getUenId());
			permitPrty.setUreSeq(bctsJobHeader.getUrnSeq());
			permitPrty.setPartyInd(indType);
			if(null!=endUsraddressType) {
				permitPrty.setCityName(endUsraddressType.getCityName());
				permitPrty.setCountryCode(endUsraddressType.getCountryCode());
				permitPrty.setCitySubName(endUsraddressType.getCountrySubentity());
				permitPrty.setCityCode(endUsraddressType.getCountrySubentityCode());
				permitPrty.setPostalCode(endUsraddressType.getPostalZone());
				}
			
			AddressLineType endUsrAddLineType=null!=endUsrPrtuType?endUsrPrtuType.getAddressLine():null;
			permitPrty.setAddr1(null!=endUsrAddLineType && !endUsrAddLineType.getLine().isEmpty()?endUsrAddLineType.getLine().get(0):null);
			permitPrty.setAddr2(null!=endUsrAddLineType && endUsrAddLineType.getLine().size()>1?endUsrAddLineType.getLine().get(1):null);
			permitPrty.setAddr3(null!=endUsrAddLineType && endUsrAddLineType.getLine().size()>2?endUsrAddLineType.getLine().get(2):null);
		   	return permitPrty;
	  }
	   
	   
	   
	   
	   public static PermitParty cnstClaimnParty(String indType,ClaimantPartyType claiMantPrtType,BctsJobHeader bctsJobHeader) {
		   	PermitParty permitPrty=new PermitParty();
			permitPrty.setUreId(bctsJobHeader.getUenId());
			permitPrty.setUreSeq(bctsJobHeader.getUrnSeq());
			permitPrty.setPartyInd(indType);
			PersonInformationType perInfoType=null!=claiMantPrtType?claiMantPrtType.getClaimantInformation():null;
			permitPrty.setPostalCode(null!=perInfoType?perInfoType.getCodeValue():null);
			List<String> perNameLst=null!=perInfoType?perInfoType.getName():null;
			permitPrty.setPerName1(null!=perNameLst && !perNameLst.isEmpty()?perNameLst.get(0):null);
			permitPrty.setPerName2(null!=perNameLst && perNameLst.size()>1?perNameLst.get(1):null);
			permitPrty.setPerName3(null!=perNameLst && perNameLst.size()>2?perNameLst.get(2):null);
			PartyDetailType claimPartyDtlsType= null!=claiMantPrtType?claiMantPrtType.getPartyDetail():null;
			PartyIdentificationType clmPrtIdenType=null!=claimPartyDtlsType?claimPartyDtlsType.getPartyIdentification():null;
			permitPrty.setPerId(null!=clmPrtIdenType?clmPrtIdenType.getID():null);
			PartyNameType clmPrtNameType=null!=claimPartyDtlsType?claimPartyDtlsType.getPartyName():null;
			List<String> clmPrtyNameLst=null!=clmPrtNameType?clmPrtNameType.getName():null;
			permitPrty.setPerName1(null!=clmPrtyNameLst && !clmPrtyNameLst.isEmpty()?clmPrtyNameLst.get(0):null);
			permitPrty.setPerName2(null!=clmPrtyNameLst && clmPrtyNameLst.size()>1?clmPrtyNameLst.get(1):null);
			permitPrty.setPerName3(null!=clmPrtyNameLst && clmPrtyNameLst.size()>2?clmPrtyNameLst.get(2):null);
		   return permitPrty;
	 }
	   
	   public static PermitParty cnstPermitParty(String indType,PartyDetailType partyDetailsType,BctsJobHeader bctsJobHeader) {
		   	PermitParty permitPrty=new PermitParty();
				PartyIdentificationType declAgntIdenType=partyDetailsType.getPartyIdentification();
				permitPrty.setUreId(bctsJobHeader.getUenId());
				permitPrty.setUreSeq(bctsJobHeader.getUrnSeq());
				permitPrty.setPartyInd(indType);
				permitPrty.setPerId(null!=declAgntIdenType?declAgntIdenType.getID():null);
				PartyNameType dclAgtptyNmType=partyDetailsType.getPartyName();
				List<String> dclAntprtNameLst=null!=dclAgtptyNmType?dclAgtptyNmType.getName():null;
				permitPrty.setPerName1(null!=dclAntprtNameLst && !dclAntprtNameLst.isEmpty()?dclAntprtNameLst.get(0):null);
				permitPrty.setPerName2(null!=dclAntprtNameLst && dclAntprtNameLst.size()>1?dclAntprtNameLst.get(1):null);
				permitPrty.setPerName3(null!=dclAntprtNameLst && dclAntprtNameLst.size()>2?dclAntprtNameLst.get(2):null);
		   return permitPrty;
	   }
	   
	   public static PermitParty cnstConsigneeParty(String indType,ConsigneePartyType consiPrtType,BctsJobHeader bctsJobHeader) {
		   	PermitParty permitPrty=null;
		   	permitPrty=new PermitParty();
		   	permitPrty.setUreId(bctsJobHeader.getUenId());
			permitPrty.setUreSeq(bctsJobHeader.getUrnSeq());
			permitPrty.setPartyInd(indType);
				if(null!=consiPrtType && null!=consiPrtType.getAddress()) {
				permitPrty.setCityName(consiPrtType.getAddress().getCityName());
				permitPrty.setCountryCode(consiPrtType.getAddress().getCountryCode());
				permitPrty.setCitySubName(consiPrtType.getAddress().getCountrySubentity());
				permitPrty.setCityCode(consiPrtType.getAddress().getCountrySubentityCode());
				permitPrty.setPostalCode(consiPrtType.getAddress().getPostalZone());
				}
			if(null!=consiPrtType && null!=consiPrtType.getAddressLine()) {
			permitPrty.setAddr1(!consiPrtType.getAddressLine().getLine().isEmpty()?consiPrtType.getAddressLine().getLine().get(0):null);
			permitPrty.setAddr2(consiPrtType.getAddressLine().getLine().size()>1?consiPrtType.getAddressLine().getLine().get(1):null);
			permitPrty.setAddr3(consiPrtType.getAddressLine().getLine().size()>2?consiPrtType.getAddressLine().getLine().get(2):null);
			}
			PartyNameType partyNameType=null!=consiPrtType?consiPrtType.getPartyName():null;
			List<String> conSPrtNmLst=null!=partyNameType?partyNameType.getName():null;
			permitPrty.setPerName1(null!=conSPrtNmLst && !conSPrtNmLst.isEmpty()?conSPrtNmLst.get(0):null);
			permitPrty.setPerName2(null!=conSPrtNmLst && conSPrtNmLst.size()>1?conSPrtNmLst.get(1):null);
			permitPrty.setPerName3(null!=conSPrtNmLst && conSPrtNmLst.size()>2?conSPrtNmLst.get(2):null);
		   return permitPrty;
	   }
	   
	   
	   

}
