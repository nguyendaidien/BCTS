package com.etrade.bcts.scheduler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.etrade.bcts.common.util.BctsDate;
import com.etrade.bcts.common.util.WebDataDict;
import com.etrade.bcts.common.xml.BctsXMLProcessException;
import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.BctsJobHeader;
import com.etrade.bcts.model.BctsPermitType;
import com.etrade.bcts.model.CaaApprovalCondition;
import com.etrade.bcts.model.Company;
import com.etrade.bcts.model.PermitInTransport;
import com.etrade.bcts.model.PermitOutTransport;
import com.etrade.bcts.model.User;
import com.etrade.bcts.service.CaseService;
import com.etrade.bcts.service.PermitService;
import com.etrade.bcts.util.BctsConstants;

import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.PermitType;
import crimsonlogic.tn41.schema.xsd.inpayment.InPaymentPermitType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.HeaderType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.OutwardPermitType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.OutwardType;
import crimsonlogic.tn41.schema.xsd.tradenetdeclaration.InboundMessageType;
import crimsonlogic.tn41.schema.xsd.tradenetdeclaration.TradenetDeclarationType;
import crimsonlogic.tn41.schema.xsd.tradenetresponse.OutboundMessageType;
import crimsonlogic.tn41.schema.xsd.tradenetresponse.TradenetResponseType;

@Component
public class BctsXMLWriter implements ItemWriter<BctsPermitType>{
	static final Logger LOG = LoggerFactory.getLogger(BctsXMLWriter.class);

	//public static final String ABSPATH="/BCTS/OUTPMT_vp4t003_201110055504.xml";
	public static final String ABSPATH="/BCTS/OUTPMT1.xml";
	public static final String ABSPATH1="/BCTS/IPTPMT_vp4t003_201110065304.xml";
	
	protected static final String[] JARXSD_TN41_DECLARATION = { "/xsd/tn41/CommonBasicComponents-2.xsd",
			"/xsd/tn41/CommonAggregateComponents-2.xsd", "/xsd/tn41/InPayment.xsd", "/xsd/tn41/InNonPayment.xsd",
			"/xsd/tn41/OutwardDeclaration.xsd", "/xsd/tn41/TranshipmentMovement.xsd",
			"/xsd/tn41/CertificateOfOrigin.xsd", "/xsd/tn41/TradenetDeclaration.xsd" };

	/*public static final String[] JARXSD_TN41_RESPONSE = { "/xsd/tn41/CommonBasicComponents-2.xsd",
			"/xsd/tn41/CommonAggregateComponents-2.xsd", "/xsd/tn41/InPayment.xsd", "/xsd/tn41/InNonPayment.xsd",
			"/xsd/tn41/OutwardDeclaration.xsd", "/xsd/tn41/TranshipmentMovement.xsd",
			"/xsd/tn41/CertificateOfOrigin.xsd", "/xsd/tn41/ApprovalMessage.xsd", "/xsd/tn41/RejectionMessage.xsd",
			"/xsd/tn41/ErrorMessage.xsd", "/xsd/tn41/FeeMessage.xsd", "/xsd/tn41/CustomsExchangeRate.xsd",
			"/xsd/tn41/CustomsCommonCode.xsd", "/xsd/tn41/TradenetResponse.xsd" };*/

	public static final String[] JARXSD_TN41_RESPONSE = { "/xsd/tn41/CommonBasicComponents-2.xsd",
			"/xsd/tn41/CommonAggregateComponents-2.xsd", "/xsd/tn41/InPayment.xsd", "/xsd/tn41/InNonPayment.xsd",
			"/xsd/tn41/OutwardDeclaration.xsd", "/xsd/tn41/TranshipmentMovement.xsd",
			"/xsd/tn41/CertificateOfOrigin.xsd", "/xsd/tn41/ApprovalMessage.xsd", "/xsd/tn41/RejectionMessage.xsd",
			"/xsd/tn41/ErrorMessage.xsd", "/xsd/tn41/FeeMessage.xsd", "/xsd/tn41/CustomsExchangeRate.xsd",
			"/xsd/tn41/CustomsCommonCode.xsd", "/xsd/tn41/TradenetResponse.xsd" };
	public static final String CONTEXT_TN41_RESPONSE = "crimsonlogic.tn41.schema.xsd.tradenetresponse";
	

	@Autowired
	PermitService permitService;
	@Autowired
	CaseService caseService;
	
	
	/**
	 * Unmarshals and validates against the XML schema
	 *
	 * @param xmlFilePath      - File to be unmarshalled
	 * @param packageContext   - Example: "com.etrade.integrator.int41.rfd"
	 * @param schemaPathsInJar - Example: "/xsd/TRW4 - Refund Only and Cancel.xsd"
	 * @return
	 * @throws JAXBException
	 * @throws BctsXMLProcessException
	 * @throws FileNotFoundException
	 */
	public static JAXBElement<TradenetResponseType> unmarshalXML(String xmlFilePath, String packageContext, String[] schemaPathsInJar)
			throws BctsXMLProcessException {
		JAXBElement<TradenetResponseType> jaxbObj = null;
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(xmlFilePath);

			InputSource source = new InputSource(fileReader);

			if (schemaPathsInJar != null) {
				jaxbObj = unmarshalXML(source, packageContext, schemaPathsInJar);
			} else {
				jaxbObj = unmarshalXML(source, packageContext);
			}

			LOG.info("unmarshalXML {} successfully", xmlFilePath);
		} catch (FileNotFoundException e) {
			throw new BctsXMLProcessException("unmarshalXML", e);
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
					fileReader = null;
				} catch (IOException e) {
					LOG.error("unmarshalXML", e);
					LOG.info("unmarshalXML", e);
				}
			}
		}
		return jaxbObj;
	}

	/**
	 * Unmarshals and validates against the XML schema
	 *
	 * @param xmlFilePath      - File to be unmarshalled
	 * @param packageContext   - Example: "com.etrade.integrator.int41.rfd"
	 * @param schemaPathsInJar - Example: "/xsd/TRW4 - Refund Only and Cancel.xsd"
	 * @return
	 * @throws JAXBException
	 * @throws BctsXMLProcessException
	 * @throws FileNotFoundException
	 */
	public static JAXBElement<TradenetResponseType> unmarshalXML(InputSource source, String packageContext, String[] schemaPathsInJar)
			throws BctsXMLProcessException {
		JAXBElement<TradenetResponseType> jaxbObj = null;
		StringBuilder buff = null;

		InputStream[] inputStreams = new InputStream[schemaPathsInJar.length];
		Source[] schemaSources = new Source[schemaPathsInJar.length];

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		ValidationEventCollector collector = new ValidationEventCollector();

		try {
			for (int i = 0; i < schemaPathsInJar.length; i++) {
				inputStreams[i] = BctsXMLWriter.class.getResourceAsStream(schemaPathsInJar[i]);
				schemaSources[i] = new StreamSource(inputStreams[i]);
			}

			Schema schema = schemaFactory.newSchema(schemaSources);

			JAXBContext jaxbContext = JAXBContext.newInstance(packageContext);
			// context is the package name binding specified when building the JAXB jar
			// (e.g. look for the file "binding.rfd.xjb" in this case)

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			unmarshaller.setSchema(schema); // set the schema to validate against

			unmarshaller.setEventHandler(collector);

			/////////////////////////////////////////////////////////////////
			// Fortify fix - need to test if application still works
			/////////////////////////////////////////////////////////////////
			SAXParserFactory spf = SAXParserFactory.newInstance();

			spf.setNamespaceAware(true); // IMPORTANT

			spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
			spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			Source xmlSource = new SAXSource(spf.newSAXParser().getXMLReader(), source);

			jaxbObj = (JAXBElement<TradenetResponseType>) unmarshaller.unmarshal(xmlSource);
			/////////////////////////////////////////////////////////////////
		} catch (UnmarshalException e) {
			LOG.error("unmarshalXML UnmarshalException!");

			if (collector.hasEvents()) {
				ValidationEventLocator locator = null;
				buff = new StringBuilder();

				for (ValidationEvent event : collector.getEvents()) {
					if (event.getMessage() != null) // check for null as there is no point logging if there is no
													// message
					{
						locator = event.getLocator();

						buff.append("Position:");
						buff.append(" line ").append(locator.getLineNumber());
						buff.append(" column ").append(locator.getColumnNumber()).append("\n");
						buff.append(event.getMessage()).append("\n");
					}
				}

				if (buff.length() > 0) {
					throw new BctsXMLProcessException("UnmarshalException: " + buff.toString(), e);
				}
			} 
		} catch (SAXException | JAXBException | ParserConfigurationException e) {
			throw new BctsXMLProcessException("unmarshalXML", e);
		} finally {
			for (InputStream inputStream : inputStreams) // Close all inputStreams
			{
				if (inputStream != null) {
					try {
						inputStream.close();
						inputStream = null;
					} catch (IOException e) {
						LOG.error("unmarshalXML > inputStream.close()", e);
					}
				}
			}
		}
		return jaxbObj;
	}

	/**
	 * Unmarshal without validating
	 * 
	 * @param source
	 * @param packageContext
	 * @return
	 * @throws JAXBException
	 * @throws BctsXMLProcessException
	 * @throws FileNotFoundException
	 */
	public static JAXBElement<TradenetResponseType> unmarshalXML(InputSource source, String packageContext) throws BctsXMLProcessException {
		JAXBElement<TradenetResponseType> jaxbObj = null;

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(packageContext);

			// context is the package name binding specified when building the JAXB jar
			// (e.g. look for the file "binding.rfd.xjb" in this case)

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			/////////////////////////////////////////////////////////////////
			// Fortify fix - need to test if application still works
			/////////////////////////////////////////////////////////////////
			SAXParserFactory spf = SAXParserFactory.newInstance();

			spf.setNamespaceAware(true); // IMPORTANT

			spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
			spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			Source xmlSource = new SAXSource(spf.newSAXParser().getXMLReader(), source);

			jaxbObj = (JAXBElement<TradenetResponseType>) unmarshaller.unmarshal(xmlSource);
			/////////////////////////////////////////////////////////////////
		} catch (JAXBException | SAXException | ParserConfigurationException e) {
			throw new BctsXMLProcessException("unmarshalXML", e);
		}
		return jaxbObj;
	}

	
	public static void main(String args[]) {
			/*try{
				String pmtFilePath="D:/BCTS/IPTPMT_vp4t003_201110065304.xml";
				//String pmtFilePath="D:/BCTS/OUTPMT_vp4t003_201110055504.xml";
				BctsXMLWriter xmlWriter=new BctsXMLWriter();
				BctsJobHeader bctsPermitType=xmlWriter.getPermitXmlInfo(pmtFilePath);
				LOG.info("main() bctsPermitType: {}",bctsPermitType.toString());
				
				
			}catch(BctsXMLProcessException e) {
				e.printStackTrace();
			}*/
			
			CaaApprovalCondition caaApr=new CaaApprovalCondition();
			caaApr.setAgencyCode("SC");
			caaApr.setConditionCode("A73");
			caaApr.setCondtionDesc1("APPROVED BY SINGAPORE CUSTOMS ON CONDITION THAT (1) YOU COMPLY WITH COMPETENT AUTHORITY REQUIREMENTS  (2) YOU SUBMIT THIS PERMIT AND INVOICE, BILL OF LADING/AIR WAYBILL TO FAX       63371556 OR EMAIL CUSTOMS_TN48HR@CUSTOMS.GOV.SG WITHIN 48HRS");
			
			String s=converntJsonDetais(caaApr);
			System.out.println(s);
	}
	
	
	

	/**
	 * This method is used to read approve permit validate and set in Object for further operation
	 * @param pmtFilePath
	 * @return
	 * @throws BctsXMLProcessException
	 */
	public BctsJobHeader getPermitXmlInfo(String pmtFilePath) throws BctsXMLProcessException {
		BctsPermitType bctsPermitType =null;
		BctsJobHeader bctsJobHeader=new BctsJobHeader();
		try {
			JAXBElement<TradenetResponseType> respObj = BctsXMLWriter
					.unmarshalXML(pmtFilePath, BctsXMLWriter.CONTEXT_TN41_RESPONSE, BctsXMLWriter.JARXSD_TN41_RESPONSE);
			TradenetResponseType resType = null!=respObj?respObj.getValue():null;
			if(null!=resType) {
			List<OutboundMessageType> lst=resType.getOutboundMessage(); 
			HeaderType hType=null;
			crimsonlogic.tn41.schema.xsd.inpayment.HeaderType hInPayType=null;
			bctsPermitType = new BctsPermitType();
			for(OutboundMessageType mstType:lst) {
				OutwardPermitType outWPermit=mstType.getOutwardPermit();
				InPaymentPermitType inPaymtPermit=mstType.getInPaymentPermit();
				
				if(null!=outWPermit) {
					hType=outWPermit.getDeclaration().getHeader();
					String uid=null!=hType && null!=hType.getUniqueReferenceNumber()?hType.getUniqueReferenceNumber().getID():null;
					String urnSeq=null!=hType && null!=hType.getUniqueReferenceNumber()?hType.getUniqueReferenceNumber().getSequenceNumeric().toString():null;
					String urnDate=null!=hType && null!=hType.getUniqueReferenceNumber()?hType.getUniqueReferenceNumber().getDate():null;
					String jobNo=urnSeq+urnDate;
					bctsJobHeader.setUenId(uid);
					bctsJobHeader.setUrnSeq(jobNo);
					bctsJobHeader.setJobDate(new BigDecimal(urnDate));
					bctsJobHeader.setJobSeq(urnSeq);//four digit job number
					bctsJobHeader.setCrUeiNo(uid);
					bctsJobHeader.setCarType(null!=hType?hType.getCommonAccessReference():null);
					bctsJobHeader=consructOutJobHeader(outWPermit,bctsJobHeader);
					bctsJobHeader=PermitXmlConstructor.consructCertificate(outWPermit, bctsJobHeader);
					bctsJobHeader=PermitXmlConstructor.constructSummary(null,outWPermit,bctsJobHeader);
				}else if(null!=inPaymtPermit) {
					hInPayType=inPaymtPermit.getDeclaration().getHeader();
					String uid=null!=hInPayType && null!=hInPayType.getUniqueReferenceNumber()?hInPayType.getUniqueReferenceNumber().getID():null;
					String urnSeq=null!=hInPayType && null!=hInPayType.getUniqueReferenceNumber()?hInPayType.getUniqueReferenceNumber().getSequenceNumeric().toString():null;
					String urnDate=null!=hInPayType && null!=hInPayType.getUniqueReferenceNumber()?hInPayType.getUniqueReferenceNumber().getDate():null;
					String jobNo=urnSeq+urnDate;
					bctsJobHeader.setUenId(uid);
					bctsJobHeader.setUrnSeq(jobNo);
					bctsJobHeader.setJobDate(new BigDecimal(urnDate));
					bctsJobHeader.setJobSeq(urnSeq);//four digit job number
					bctsJobHeader.setCrUeiNo(uid);
					bctsJobHeader.setCarType(null!=hInPayType?hInPayType.getCommonAccessReference():null);
					bctsJobHeader=consructInJobHeader(inPaymtPermit,bctsJobHeader);
					bctsJobHeader=PermitXmlConstructor.constructSummary(inPaymtPermit,null,bctsJobHeader);
					//TODO item type
					/*List<ItemType> itemTypeLst=inPaymtPermit.getDeclaration().getItem();
					for(ItemType itemType:itemTypeLst) {
						itemType.getBrandName();
					List<CASCProductType> ceasProdTypeLst=itemType.getCASCProduct();
					for(CASCProductType cascPr:ceasProdTypeLst) {
						cascPr.get
					}
					
					}*/
					
				}
			}
			System.out.println("getPermitXmlInfo() Model value:"+bctsPermitType.toString());
			LOG.info("getPermitXmlInfo() Model value:{}",bctsPermitType.toString());
			}
			
			/*Insert in all permit related tables*/
			permitService.saveJobHeader(bctsJobHeader);
			/*Insert in Alert table to notify user*/
			 List<BCTSAlert> alrlst=constructAlertInfo(bctsJobHeader);
			 for(BCTSAlert alt:alrlst) {
				 caseService.save(alt);
			 }
		}catch(BctsXMLProcessException e) {
			LOG.error("BctsXMLProcessException in getPermitXmlInfo():",e);
			throw new BctsXMLProcessException(e.getMessage());
		}
		return bctsJobHeader;
	}
	
	
	
	public static List<BCTSAlert> constructAlertInfo(BctsJobHeader bctsJobHeader) {
		String msg=bctsJobHeader.getCarType();
		List<BCTSAlert> alertLst=new ArrayList<>();
		BCTSAlert bctsAlert=null;
		Company comp=new Company();
		String[] values = {"A13","A14","A73","DD","Z02","Z18"};//TODO get details from DB
		Date openDate=new Date();
		if(BctsConstants.OUTPUTPERMIT.equals(msg)) {
			List<CaaApprovalCondition> caaAprCondLst=bctsJobHeader.getPermitConditions();
			for(CaaApprovalCondition caaApr:caaAprCondLst) {
				boolean contains = Arrays.stream(values).anyMatch(caaApr.getConditionCode()::equals);
				if(contains) {
					bctsAlert=new BCTSAlert();
					String altCnt=converntJsonDetais(caaApr);
					LOG.info("constructAlertInfo() altCnt:{}",altCnt);
					bctsAlert.setAlertContent(altCnt);
					List<PermitOutTransport> poutLst=bctsJobHeader.getOutTransList();
					Date depDate=null;
					Date arrvDate=null;
					boolean flag=false;
					if(null!=poutLst && !poutLst.isEmpty()) {
						depDate=poutLst.get(0).getDepartDate();
					}
					List<PermitInTransport> pInLst=bctsJobHeader.getInTransList();
					if(null!=pInLst && !pInLst.isEmpty()) {
						arrvDate=pInLst.get(0).getArraivalDate();
					}
					
					if(null!=depDate && null!=arrvDate && arrvDate.compareTo(depDate) > 0) {
				            System.out.println("Date1 is after Date2");
				            flag=true;
					}
					
					if(flag) {
						bctsAlert.setStatus(BctsConstants.CASE_STATUS_COMPLETED);
					}else {
						bctsAlert.setStatus(BctsConstants.CASE_STATUS_OPEN);
					}
					comp.setUeiNo(bctsJobHeader.getUenId());
					bctsAlert.setJobNo(bctsJobHeader.getUrnSeq());
					bctsAlert.setUen(comp);
					bctsAlert.setCategory(BctsConstants.CASETYPE_PERMIT_CONDITION);
					bctsAlert.setPermitNo(bctsJobHeader.getPermits().get(0).getPermitNumber());
					bctsAlert.setOpenDate(openDate);
					User user=new User();
					user.setId(1);
					bctsAlert.setOpenBy(user);
					alertLst.add(bctsAlert);
				}
			}
			
		}else if(BctsConstants.INPUTPERMIT.equals(msg)) {
			List<CaaApprovalCondition> caaAprCondLst=bctsJobHeader.getPermitConditions();
			for(CaaApprovalCondition caaApr:caaAprCondLst) {
				boolean contains = Arrays.stream(values).anyMatch(caaApr.getConditionCode()::equals);
				
				if(contains) {
					bctsAlert=new BCTSAlert();
					String altCnt=converntJsonDetais(caaApr);
					LOG.info("constructAlertInfo() altCnt:{}",altCnt);
					bctsAlert.setAlertContent(altCnt);
					bctsAlert.setStatus(BctsConstants.CASE_STATUS_OPEN);
					comp.setUeiNo(bctsJobHeader.getUenId());
					bctsAlert.setJobNo(bctsJobHeader.getUrnSeq());
					bctsAlert.setUen(comp);
					bctsAlert.setCategory(BctsConstants.CASETYPE_PERMIT_CONDITION);
					bctsAlert.setPermitNo(bctsJobHeader.getPermits().get(0).getPermitNumber());
					bctsAlert.setOpenDate(openDate);
					User user=new User();
					user.setId(1);
					bctsAlert.setOpenBy(user);
					alertLst.add(bctsAlert);
				}
			}
		}
		return alertLst;
	}
	
	
	public static String converntJsonDetais(CaaApprovalCondition caaApr) {
		StringBuilder curlyS=new StringBuilder();
		curlyS.append("{");
		StringBuilder curlyE=new StringBuilder();
		curlyE.append("}");
		StringBuilder quote=new StringBuilder();
		quote.append("\"");
		StringBuilder colon=new StringBuilder();
		colon.append(":");
		StringBuilder comma=new StringBuilder();
		comma.append(",");
		StringBuilder sb=new StringBuilder();
		sb.append(curlyS);
		sb.append(quote).append("AgencyCode").append(quote).append(colon);
		sb.append(quote).append(caaApr.getAgencyCode()).append(quote).append(comma);
		sb.append(quote).append("ConditionCode").append(quote).append(colon);
		sb.append(quote).append(caaApr.getConditionCode()).append(quote).append(comma);
		sb.append(quote).append("ConditionDescription").append(quote).append(colon);
		sb.append(quote).append(caaApr.getCondtionDesc1()).append(quote);
		sb.append(curlyE);
		return sb.toString();
		
	}
	
	
	/**
	 * This method will club all sub types related to outward permit and return BctsJobHeader
	 * @author ajayasamanta
	 * @param outWPermit
	 * @param bctsJobHeader
	 * @return
	 */
	private static BctsJobHeader consructOutJobHeader(OutwardPermitType outWPermit,BctsJobHeader bctsJobHeader) {
		/*Construct Permit for outward payment*/
		bctsJobHeader=PermitXmlConstructor.consructPermit(outWPermit,null,bctsJobHeader);
		PermitType permitType=outWPermit.getPermit();
		/*Construct Permit Conditions for outward payment*/
		if(null!=permitType) {
			List<CaaApprovalCondition> appCondLst=new ArrayList<>(); 
			if(null!=permitType.getCAApprovalCondition()){
				appCondLst=PermitXmlConstructor.consructPermitApprCond(appCondLst,BctsConstants.CAAAPPROVAL,permitType.getCAApprovalCondition(),bctsJobHeader);
					}
				if(null!=permitType.getRefundApprovalCondition()){
					appCondLst=PermitXmlConstructor.consructPermitApprCond(appCondLst,BctsConstants.REFUNDAPPROVAL,permitType.getRefundApprovalCondition(),bctsJobHeader);
				}
				if(null!=permitType.getSCApprovalCondition()){
					appCondLst=PermitXmlConstructor.consructPermitApprCond(appCondLst,BctsConstants.SCAAPPROVAL,permitType.getSCApprovalCondition(),bctsJobHeader);
				}
				bctsJobHeader.setPermitConditions(appCondLst);
			}
		    /*Construct Permit License for outward payment*/
			bctsJobHeader=PermitXmlConstructor.costructLicence(bctsJobHeader, outWPermit);
			/*Construct Permit Cargo for outward payment*/
			if(null!=outWPermit.getDeclaration()) {
			bctsJobHeader=PermitXmlConstructor.generatePermitCargo(outWPermit.getDeclaration().getCargo(),null,bctsJobHeader);
			/*Construct Transport for permit outward*/
			bctsJobHeader=PermitXmlConstructor.constructTrans(outWPermit.getDeclaration().getTransport().getInwardTransport(), null!=outWPermit.getDeclaration().getTransport()?outWPermit.getDeclaration().getTransport().getOutwardTransport():null, bctsJobHeader);
			}
			/*Construct Permit Party Details for in payment*/
			bctsJobHeader=PermitXmlConstructor.cnstOutPayPrty(outWPermit,bctsJobHeader);
		return bctsJobHeader;
	}
	
	
	/**
	 * This method will club all sub types related to inward permit and return BctsJobHeader
	 * @author ajayasamanta
	 * @param inPaymtPermit
	 * @param bctsJobHeader
	 * @return
	 */
	private static BctsJobHeader consructInJobHeader(InPaymentPermitType inPaymtPermit,BctsJobHeader bctsJobHeader) {
		/*Construct Permit for in payment*/
		bctsJobHeader=PermitXmlConstructor.consructPermit(null,inPaymtPermit,bctsJobHeader);
		PermitType permitType=inPaymtPermit.getPermit();
		/*Construct Permit Conditions for in payment*/
		if(null!=permitType) {
				List<CaaApprovalCondition> appCondLst=new ArrayList<>(); 	
				if(null!=permitType.getCAApprovalCondition() && !permitType.getCAApprovalCondition().isEmpty()){
					appCondLst=PermitXmlConstructor.consructPermitApprCond(appCondLst,"CAA",permitType.getCAApprovalCondition(),bctsJobHeader);
				}
				if(null!=permitType.getRefundApprovalCondition() && !permitType.getRefundApprovalCondition().isEmpty()){
					appCondLst=PermitXmlConstructor.consructPermitApprCond(appCondLst,"REF",permitType.getRefundApprovalCondition(),bctsJobHeader);
				}
				if(null!=permitType.getSCApprovalCondition() && !permitType.getSCApprovalCondition().isEmpty()){
					appCondLst=PermitXmlConstructor.consructPermitApprCond(appCondLst,"SCA",permitType.getSCApprovalCondition(),bctsJobHeader);
				}
				bctsJobHeader.setPermitConditions(appCondLst);
		}
		/*construct Invoice which will always comes under inPayment permit*/
		if(null!=inPaymtPermit.getDeclaration().getInvoice()) {
			bctsJobHeader=PermitXmlConstructor.constructInvoice(bctsJobHeader, inPaymtPermit);
		}
		/*Construct Permit License for in payment*/
		bctsJobHeader=PermitXmlConstructor.costructLicence(bctsJobHeader, inPaymtPermit);
		
		/*Construct Permit Cargo for in payment*/
		if(null!=inPaymtPermit.getDeclaration()) {
		bctsJobHeader=PermitXmlConstructor.generatePermitCargo(null,inPaymtPermit.getDeclaration().getCargo(),bctsJobHeader);
		/*Construct Transport for permit inward*/
		bctsJobHeader=PermitXmlConstructor.constructTrans(null!=inPaymtPermit.getDeclaration().getTransport()?inPaymtPermit.getDeclaration().getTransport().getInwardTransport():null, null, bctsJobHeader);
		}
		
		/*Construct Permit Party Details for in payment*/
		bctsJobHeader=PermitXmlConstructor.cnstInPayPrty(inPaymtPermit,bctsJobHeader);
		return bctsJobHeader;
	}
	
	

	private static JAXBElement<TradenetDeclarationType> getJaxbObject() {
		crimsonlogic.tn41.schema.xsd.tradenetdeclaration.ObjectFactory objFactory = new crimsonlogic.tn41.schema.xsd.tradenetdeclaration.ObjectFactory();

		TradenetDeclarationType result = null;

		result = createSkeleton("interchange");

		JAXBElement<TradenetDeclarationType> jaxbObj = objFactory.createTradenetDeclaration(result);

		InboundMessageType inboundMessage = objFactory.createInboundMessageType();

		crimsonlogic.tn41.schema.xsd.outwarddeclaration.ObjectFactory outFactory = new crimsonlogic.tn41.schema.xsd.outwarddeclaration.ObjectFactory();

		OutwardType outward = outFactory.createOutwardType();

		inboundMessage.setOutward(outward);

		result.getInboundMessage().add(inboundMessage);

		return jaxbObj;
	}

	/**
	 * Create JAXB Object skeleton
	 */
	private static TradenetDeclarationType createSkeleton(String interchange) {
		TradenetDeclarationType result = null;

		crimsonlogic.tn41.schema.xsd.tradenetdeclaration.ObjectFactory objFactory = new crimsonlogic.tn41.schema.xsd.tradenetdeclaration.ObjectFactory();
		result = objFactory.createTradenetDeclarationType(); // - TN41Change

		String dateStr = BctsDate.getDateString(Calendar.getInstance().getTime(), BctsDate.XML_DATETIMEFORMAT);

		result.setDateTime(new BigDecimal(dateStr));
		result.setInstanceIdentifier(interchange);
		result.setMessageVersion(WebDataDict.MSG_VER_TN41); // TN4.1

		result.setSenderID("SENDER123");
		result.setRecipientID("Recipient345");
		result.setTotalNumberOfDeclaration(new BigDecimal(1));

		return result;
	}

	/**
	 * Remove Empty XML tags using regex
	 * 
	 * @param xmlContent
	 * @return
	 */
	public static String removeEmptyTags(String xmlContent) {
		final String[] patterns = new String[] { "\\s*<\\w+/>", // Remove empty elements that look like <ElementName/>

				"\\s*<\\w+></\\w+>", // Remove empty elements that look like <ElementName></ElementName>

				// Remove empty elements that look like <ElementName>
				// </ElementName>
				"\\s*<\\w+>\n*\\s*</\\w+>" };

		if (xmlContent != null) {
			for (String pattern : patterns) {
				Matcher matcher = Pattern.compile(pattern).matcher(xmlContent);
				xmlContent = matcher.replaceAll("");
			}
		}
		return xmlContent;
	}

	@Override
	public void write(List<? extends BctsPermitType> permitList) throws Exception {
		BctsJobHeader bctsPermitType=getPermitXmlInfo(ABSPATH);
	}
}
