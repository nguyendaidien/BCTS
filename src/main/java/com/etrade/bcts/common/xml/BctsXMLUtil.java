package com.etrade.bcts.common.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.etrade.bcts.common.util.BctsDate;
import com.etrade.bcts.common.util.WebDataDict;
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
import com.etrade.bcts.util.BctsDateFormatter;
import com.etrade.bcts.util.BctsDateUtil;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.AdditionalVesselInformationType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.AddressLineType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.AddressType;
import crimsonlogic.tn41.schema.xsd.commonaggregatecomponents_2.CertificateDetailType;
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
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.HeaderType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.OutwardPermitType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.OutwardType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.PartyType;
import crimsonlogic.tn41.schema.xsd.outwarddeclaration.SummaryType;
import crimsonlogic.tn41.schema.xsd.tradenetdeclaration.InboundMessageType;
import crimsonlogic.tn41.schema.xsd.tradenetdeclaration.TradenetDeclarationType;
import crimsonlogic.tn41.schema.xsd.tradenetresponse.OutboundMessageType;
import crimsonlogic.tn41.schema.xsd.tradenetresponse.TradenetResponseType;

public class BctsXMLUtil {
	static final Logger LOG = LoggerFactory.getLogger(BctsXMLUtil.class);

	public static final String[] JARXSD_TN41_DECLARATION = { "/xsd/tn41/CommonBasicComponents-2.xsd",
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
	
	public static final String[] JARXSD_PIP_SHIPDOC = {
//            //TODO: There are too many XSDs to load for PIP_SHIPDOC. May want to bypass JAXB validation just for this
			"/xsd/pip/Interchange/ShippingDocumentationNotification_02_04.xsd" };

	public static final String[] JARXSD_3B18_SHIPDOC = { "/xsd/shipdoc3b18/3C3_InvoiceNotification.xsd",
			"/xsd/shipdoc3b18/3B18_ShippingDocumentationNotification.xsd" };

	public static final String[] JARXSD_GENERIC_REPORT = { "/xsd/genericReport/ModConfigReportGenericReport.xsd" };
	public static final String[] JARXSD_ACCESS_PERMIT = { "/xsd/accessPermit/accessPermit.xsd" };
	public static final String[] JARXSD_ECO_PREP = { "/xsd/ecoPrep/ecoPrep.xsd" };

	///////////////////////////////////////////////////////////////////////////////////////

	public static final String CONTEXT_TN41_DECLARATION = "crimsonlogic.tn41.schema.xsd.tradenetdeclaration";
	public static final String CONTEXT_TN41_RESPONSE = "crimsonlogic.tn41.schema.xsd.tradenetresponse";

	public static final String CONTEXT_PIP = "pip";
	public static final String CONTEXT_3B18 = "pipship";

	public static final String CONTEXT_INT41_COO = "com.etrade.jaxb.int41.coo";
	public static final String CONTEXT_INT41_CUSTOMS_RESPONSE = "com.etrade.jaxb.int41.res";
	public static final String CONTEXT_INT41_INPDECPMT = "com.etrade.jaxb.int41.inp";
	public static final String CONTEXT_INT41_IPTDECPMT = "com.etrade.jaxb.int41.ipt";
	public static final String CONTEXT_INT41_OUTDECPMT = "com.etrade.jaxb.int41.out";
	public static final String CONTEXT_INT41_TNPDECPMT = "com.etrade.jaxb.int41.tnp";
	public static final String CONTEXT_INT41_REFUND_CANCEL = "com.etrade.jaxb.int41.rfd";
	public static final String CONTEXT_INT41_VAL_RESPONSE = "com.etrade.jaxb.int41.val";

	// Added this to fix Generic Report
	public static final String CONTEXT_GENERIC_REPORT = "com.etrade.trw.common.model.genericreport.jaxb";

	public static final String CONTEXT_ACCESS_PERMIT = "com.etrade.trw.accesspermit.jaxb";
	public static final String CONTEXT_ECO_PREP = "com.etrade.epa.ecoprep.jaxb";

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
				inputStreams[i] = BctsXMLUtil.class.getResourceAsStream(schemaPathsInJar[i]);
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

	/**
	 * Marshals and validates against the XML schema
	 *
	 * @param xmlFilePath     - File to be unmarshalled
	 * @param packageContext  - Example: "com.etrade.integrator.int41.rfd"
	 * @param schemaPathInJar - Example: "/xsd/TRW4 - Refund Only and Cancel.xsd"
	 * @return
	 * @throws JAXBException
	 * @throws BctsXMLProcessException
	 * @throws IOException
	 */
	public static boolean marshalXML(JAXBElement<?> jaxbObj, String xmlFilePath, String packageContext,
			String[] schemaPathsInJar, NamespacePrefixMapper mapper, boolean isFormatted)
			throws BctsXMLProcessException {
		final String methodName = "marshalXML: ";
		boolean success = false;
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(xmlFilePath);

			LOG.debug(methodName + "calling marshalXML for {}", xmlFilePath);

			if (schemaPathsInJar != null) {
				success = marshalXML(jaxbObj, fileWriter, packageContext, schemaPathsInJar, mapper, isFormatted);
			} else {
				success = marshalXML(jaxbObj, fileWriter, packageContext, mapper, isFormatted);
			}

			LOG.info(methodName + "{} successfully", xmlFilePath);
		} catch (IOException e) {
			LOG.error(methodName, e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					LOG.error(methodName, e);
				}
			}
		}
		return success;
	}

	/**
	 * Marshals and validates against the XML schema
	 *
	 * @param writer          - Writer for the output, e.g. FileWriter
	 * @param packageContext  - Example: "com.etrade.integrator.int41.rfd"
	 * @param schemaPathInJar - Example: "/xsd/TRW4 - Refund Only and Cancel.xsd"
	 * @return
	 * @throws JAXBException
	 * @throws BctsXMLProcessException
	 * @throws IOException
	 */
	public static boolean marshalXML(JAXBElement<?> jaxbObj, Writer writer, String packageContext,
			String[] schemaPathsInJar, NamespacePrefixMapper mapper, boolean isFormatted)
			throws BctsXMLProcessException {
		boolean success = false;
		StringBuilder buff = null;

		InputStream[] inputStreams = new InputStream[schemaPathsInJar.length];
		Source[] schemaSources = new Source[schemaPathsInJar.length];

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		ValidationEventCollector collector = new ValidationEventCollector();
		try {
			for (int i = 0; i < schemaPathsInJar.length; i++) {
				inputStreams[i] = BctsXMLUtil.class.getResourceAsStream(schemaPathsInJar[i]);
				schemaSources[i] = new StreamSource(inputStreams[i]);
			}
			Schema schema = schemaFactory.newSchema(schemaSources);

			JAXBContext jaxbContext = JAXBContext.newInstance(packageContext);
			// context is the package name binding specified when building the JAXB jar
			// (e.g. look for the file "binding.rfd.xjb" in this case)

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setSchema(schema); // set the schema to validate against
			marshaller.setEventHandler(collector);

			if (mapper != null) // only set if not null
			{
				marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);
			}
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false); // set as false so that XML Encoding will be
																		// included
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf(isFormatted));

			marshaller.marshal(jaxbObj, writer);

			success = true;
		} catch (MarshalException e) {
//            LOG.error("marshalXML MarshalException!", e); //Comment out as Caller will log the StackTrace

			if (collector.hasEvents()) {
				ValidationEventLocator locator = null;
				buff = new StringBuilder();

				for (ValidationEvent event : collector.getEvents()) {
					if (event.getMessage() != null) // check for null as there is no point logging if there is no
													// message
					{
						locator = event.getLocator();

						if (locator != null) {
							if (locator.getObject() != null) {
								buff.append(" Object: ").append(locator.getObject().toString()).append("\n");
							}

							if (locator.getNode() != null) {
								buff.append(" NodeName: ").append(locator.getNode().getNodeName()).append("\n");
							}
							buff.append(event.getMessage()).append("\n");
						}
					}
				}

				if (buff.length() > 0) {
					throw new BctsXMLProcessException(buff.toString(), e);
				}
			}
		} catch (JAXBException | SAXException e) {
			throw new BctsXMLProcessException("marshalXML", e);
		} finally {
			for (InputStream inputStream : inputStreams) // Close all inputStreams
			{
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						LOG.error("marshalXML > inputStream.close()", e);
					}
				}
			}
		}
		return success;
	}

	/**
	 * Marshals without validation
	 *
	 * @param writer         - Writer for the output, e.g. FileWriter
	 * @param packageContext - Example: "com.etrade.integrator.int41.rfd"
	 * @return
	 * @throws JAXBException
	 * @throws BctsXMLProcessException
	 * @throws IOException
	 */
	public static boolean marshalXML(JAXBElement<?> jaxbObj, Writer writer, String packageContext,
			NamespacePrefixMapper mapper, boolean isFormatted) throws BctsXMLProcessException {
		boolean success = true;

		// context is the package name binding specified when building the JAXB jar
		// (e.g. look for the file "binding.rfd.xjb" in this case)

		Marshaller marshaller = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(packageContext);

			marshaller = jaxbContext.createMarshaller();

			if (mapper != null) // only set if not null
			{
				marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);
			}
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false); // set as false so that XML Encoding will be
																		// included
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf(isFormatted));

			marshaller.marshal(jaxbObj, writer);
		} catch (JAXBException e) {
			throw new BctsXMLProcessException("marshalXML", e);
		}
		return success;
	}

	/**
	 * Only used by Pip3B18ShippingDocumentationNotification currently as its the
	 * only 1 XML Schema with XmlRootElement defined
	 * 
	 * @param jaxbXmlRootObj
	 * @param xmlFilePath
	 * @param packageContext
	 * @param schemaPathsInJar
	 * @param mapper
	 * @param isFormatted
	 * @return
	 * @throws BctsXMLProcessException
	 */
	public static boolean marshalXML(Object jaxbRootObj, Writer writer, String packageContext,
			String[] schemaPathsInJar, NamespacePrefixMapper mapper, boolean isFormatted)
			throws BctsXMLProcessException {
		boolean success = false;
		StringBuilder buff = null;

		InputStream[] inputStreams = new InputStream[schemaPathsInJar.length];
		Source[] schemaSources = new Source[schemaPathsInJar.length];

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		ValidationEventCollector collector = new ValidationEventCollector();
		try {
			for (int i = 0; i < schemaPathsInJar.length; i++) {
				inputStreams[i] = BctsXMLUtil.class.getResourceAsStream(schemaPathsInJar[i]);
				schemaSources[i] = new StreamSource(inputStreams[i]);
			}
			Schema schema = schemaFactory.newSchema(schemaSources);

			JAXBContext jaxbContext = JAXBContext.newInstance(packageContext);
			// context is the package name binding specified when building the JAXB jar
			// (e.g. look for the file "binding.rfd.xjb" in this case)

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setSchema(schema); // set the schema to validate against
			marshaller.setEventHandler(collector);

			if (mapper != null) // only set if not null
			{
				marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);
			}
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false); // set as false so that XML Encoding will be
																		// included
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf(isFormatted));

			marshaller.marshal(jaxbRootObj, writer);

			success = true;
		} catch (MarshalException e) {
			LOG.error("marshalXML MarshalException!", e);

			if (collector.hasEvents()) {
				ValidationEventLocator locator = null;
				buff = new StringBuilder();

				for (ValidationEvent event : collector.getEvents()) {
					if (event.getMessage() != null) // check for null as there is no point logging if there is no
													// message
					{
						locator = event.getLocator();

						buff.append(" Object: ").append(locator.getObject().toString()).append("\n");
						buff.append(" NodeName: ").append(locator.getNode().getNodeName()).append("\n");
						buff.append(event.getMessage()).append("\n");
					}
				}

				if (buff.length() > 0) {
					throw new BctsXMLProcessException(buff.toString(), e);
				}
			}
		} catch (JAXBException | SAXException e) {
			throw new BctsXMLProcessException("marshalXML", e);
		} finally {
			for (InputStream inputStream : inputStreams) {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						LOG.error("marshalXML > inputStream.close()", e);
					}
				}
			}
		}
		return success;
	}

	public static void main(String args[]) {
			try{
				String pmtFilePath="D:/BCTS/IPTPMT_vp4t003_201110065304.xml";
				//String pmtFilePath="D:/BCTS/OUTPMT_vp4t003_201110055504.xml";
				BctsPermitType bctsPermitType=getPermitXmlInfo(pmtFilePath);
				LOG.info("main() bctsPermitType: {}",bctsPermitType.toString());
			}catch(BctsXMLProcessException e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 * This method is used to read approve permit validate and set in Object for further operation
	 * @param pmtFilePath
	 * @return
	 * @throws BctsXMLProcessException
	 */
	public static BctsPermitType getPermitXmlInfo(String pmtFilePath) throws BctsXMLProcessException {
		BctsPermitType bctsPermitType =null;
		try {
			JAXBElement<TradenetResponseType> respObj = BctsXMLUtil
					.unmarshalXML(pmtFilePath, BctsXMLUtil.CONTEXT_TN41_RESPONSE, BctsXMLUtil.JARXSD_TN41_RESPONSE);
			TradenetResponseType resType = null!=respObj?respObj.getValue():null;
			if(null!=resType) {
			List<OutboundMessageType> lst=resType.getOutboundMessage(); 
			HeaderType hType=null;
			crimsonlogic.tn41.schema.xsd.inpayment.HeaderType hInPayType=null;
			bctsPermitType = new BctsPermitType();
			for(OutboundMessageType mstType:lst) {
				OutwardPermitType outWPermit=mstType.getOutwardPermit();
				InPaymentPermitType inPaymtPermit=mstType.getInPaymentPermit();
				
				
				PermitType pType=null;
				if(null!=outWPermit) {
					pType=outWPermit.getPermit();
					bctsPermitType=consructOutPermit(outWPermit,bctsPermitType);
					hType=outWPermit.getDeclaration().getHeader();
					String uid=hType.getUniqueReferenceNumber().getID();
					String urn=hType.getUniqueReferenceNumber().getSequenceNumeric().toString();
					bctsPermitType=consructCaaApproval(pType,bctsPermitType,urn,uid);
					InwardTransportType inwordTrans=outWPermit.getDeclaration().getTransport().getInwardTransport();
					OutwardTransportType ouWordTrans=outWPermit.getDeclaration().getTransport().getOutwardTransport();
					bctsPermitType=consructTrans(inwordTrans,ouWordTrans,bctsPermitType,urn,uid);
					
					bctsPermitType=constructParty(bctsPermitType,outWPermit,urn,uid);
					if(null!=outWPermit.getDeclaration() && null!=outWPermit.getDeclaration().getLicence()) {
					bctsPermitType=costructLicence(bctsPermitType,outWPermit,urn,uid);
					}
					
					bctsPermitType=constructSummary(bctsPermitType,null,outWPermit);
				}
				if(null!=inPaymtPermit) {
					pType=inPaymtPermit.getPermit();
					bctsPermitType=consructInPayPermit(inPaymtPermit,bctsPermitType);
					hInPayType=inPaymtPermit.getDeclaration().getHeader();
					String uid=hInPayType.getUniqueReferenceNumber().getID();
					String urn=hInPayType.getUniqueReferenceNumber().getSequenceNumeric().toString();
					bctsPermitType=consructCaaApproval(pType,bctsPermitType,urn,uid);
					
					InwardTransportType inwordTrans=inPaymtPermit.getDeclaration().getTransport().getInwardTransport();
					bctsPermitType=consructTrans(inwordTrans,null,bctsPermitType,urn,uid);
					bctsPermitType=constructParty(bctsPermitType,inPaymtPermit,urn,uid);
					if(null!=inPaymtPermit.getDeclaration() && null!=inPaymtPermit.getDeclaration().getLicence()) {
					bctsPermitType=costructLicence(bctsPermitType,inPaymtPermit,urn,uid);
					}
					if(null!=inPaymtPermit.getDeclaration().getInvoice()) {
					bctsPermitType=constructinVoice(bctsPermitType,inPaymtPermit);
					}
					
					bctsPermitType=constructSummary(bctsPermitType,inPaymtPermit,null);
					
					/*List<ItemType> itemTypeLst=inPaymtPermit.getDeclaration().getItem();
					for(ItemType itemType:itemTypeLst) {
						itemType.getBrandName();
					List<CASCProductType> ceasProdTypeLst=itemType.getCASCProduct();
					for(CASCProductType cascPr:ceasProdTypeLst) {
						cascPr.get
						
					}
					
					}*/
					
				}
				
				if(null!=pType && null!=pType.getPermitApprovalDatetime()) {
				String perApDate=BctsDateUtil.truncateDate(BctsDateUtil.TRW_DATETIME_NO_TIMEZONE, pType.getPermitApprovalDatetime());
				bctsPermitType.setPermApprDateAndTime(BctsDateFormatter.convertDateTime(perApDate));
				bctsPermitType.setCertNumber(pType.getCertificateNumber());
				}
				if(null!=pType && null!=pType.getCAApprovalDatetime()) {
				String caaApDate=BctsDateUtil.truncateDate(BctsDateUtil.TRW_DATETIME_NO_TIMEZONE, pType.getCAApprovalDatetime());		
				//bctsPermitType.setCaaApproDateAndTime(BctsDateFormatter.convertDateTime(caaApDate));
				}
				PeriodType peroidType=null!=pType?pType.getPermitValidityPeriod():null;
				if(null!=peroidType) {
					//BctsDateFormatter.convertDateTime(date)
				bctsPermitType.setValPeriodStart(BctsDateFormatter.convertDateTime(peroidType.getStartDate()));
				bctsPermitType.setValPeriodEnd(BctsDateFormatter.convertDateTime(peroidType.getEndDate()));
				}
				bctsPermitType.setPermitNumber(null!=pType?pType.getPermitNumber():null);
				
			}
			System.out.println("getPermitXmlInfo() Model value:"+bctsPermitType.toString());
			}
		}catch(BctsXMLProcessException e) {
			LOG.error("BctsXMLProcessException in getPermitXmlInfo():",e);
			throw new BctsXMLProcessException(e.getMessage());
		}
		return bctsPermitType;
	}
	
	public static BctsPermitType constructSummary(BctsPermitType bctsPermitType,InPaymentPermitType inPaymtPermit,OutwardPermitType outWPermit) {
		PermitSummary permSum=new PermitSummary();
		Set<PermitSummary> permitSumLst=new HashSet<>();
		//permSum.setUreId(bctsPermitType.getUenId());
		//permSum.setUreSeq(bctsPermitType.getUrnSeq());
		if(null!=outWPermit) {
				SummaryType summaryType=outWPermit.getDeclaration().getSummary();
				if(null!=summaryType) {
				permSum.setNoOfItems(summaryType.getNumberOfItems());
				permSum.setTotCifFob(summaryType.getTotalCIFFOBValue());
				TotalOuterPack outerPackType=summaryType.getTotalOuterPack();
				if(null!=outerPackType) {
				permSum.setTotOutPckUom(outerPackType.getUnitCode());
				permSum.setTotOutPck(BigDecimal.valueOf(outerPackType.getValue()));
				}
				TotalGrossWeight grossWeiType=summaryType.getTotalGrossWeight();
				if(null!=grossWeiType) {
				permSum.setTotGrossWgtUom(grossWeiType.getUnitCode());
				permSum.setTotGrossWgt(grossWeiType.getValue());
				}
				permitSumLst.add(permSum);
			}
		}
		if(null!=inPaymtPermit) {
			crimsonlogic.tn41.schema.xsd.inpayment.SummaryType summaryType=inPaymtPermit.getDeclaration().getSummary();
			
			if(null!=summaryType) {
			permSum.setNoOfItems(summaryType.getNumberOfItems());
			permSum.setTotCifFob(summaryType.getTotalCIFFOBValue());
			TotalOuterPack outerPackType=summaryType.getTotalOuterPack();
			if(null!=outerPackType) {
			permSum.setTotOutPckUom(outerPackType.getUnitCode());
			permSum.setTotOutPck(BigDecimal.valueOf(outerPackType.getValue()));
			}
			TotalGrossWeight grossWeiType=summaryType.getTotalGrossWeight();
			if(null!=grossWeiType) {
			permSum.setTotGrossWgtUom(grossWeiType.getUnitCode());
			permSum.setTotGrossWgt(grossWeiType.getValue());
			}
			TotalTariffRefundType refundType=summaryType.getTotalTariffRefund();
			if(null!=refundType) {
			permSum.setTotRefCustAmt(refundType.getTotalCustomsDutyRefundAmount());
			permSum.setTotRefExcAmt(refundType.getTotalExciseDutyRefundAmount());
			permSum.setTotRefGstAmt(refundType.getTotalGoodsAndServicesTaxRefundAmount());
			permSum.setTotRefOthTaxAmt(refundType.getTotalOtherTaxRefundAmount());
			}
			permitSumLst.add(permSum);
			}
		}
		//bctsPermitType.setPermitSumTypeLst(permitSumLst);
		return bctsPermitType;
	}
	
	
	public static BctsPermitType constructinVoice(BctsPermitType bctsPermitType,InPaymentPermitType inPaymtPermit) {
		PermitInvoice permitInv=null;
		List<PermitInvoice> pInVoiceLst=new ArrayList<>();
		List<InvoiceType> invTypeLst=inPaymtPermit.getDeclaration().getInvoice();
		int seqNo=1;
		for(InvoiceType invType:invTypeLst) {
			permitInv=new PermitInvoice();
			//permitInv.setUreId(bctsPermitType.getUenId());
			//permitInv.setUreSeq(bctsPermitType.getUrnSeq());
			seqNo+=1;
			permitInv.setInvSeqNo(new BigDecimal(seqNo));
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
		//bctsPermitType.setPermitInvoiceLst(pInVoiceLst);
		
		return bctsPermitType;
	}
	
	
	
	/**
	 * License Construct OutwardPermitType
	 * @param bctsPermitType
	 * @param outWPermit
	 * @param urn
	 * @param uid
	 * @return
	 */
	public static BctsPermitType costructLicence(BctsPermitType bctsPermitType,OutwardPermitType outWPermit,String urn,String uid) {
		PermitLicence permitL=new PermitLicence();
		List<PermitLicence> licPerList=new ArrayList<>();
		permitL.setUreId(uid);
		permitL.setUreSeq(urn);
		List<LicenceType> licencTypeList=outWPermit.getDeclaration().getLicence();
		if(null!=licencTypeList) {
		permitL.setLicence1(!licencTypeList.isEmpty()?licencTypeList.get(0).getReferenceID():null);
		permitL.setLicence2(licencTypeList.size()>1?licencTypeList.get(1).getReferenceID():null);
		permitL.setLicence3(licencTypeList.size()>2?licencTypeList.get(2).getReferenceID():null);
		permitL.setLicence4(licencTypeList.size()>3?licencTypeList.get(3).getReferenceID():null);
		permitL.setLicence5(licencTypeList.size()>4?licencTypeList.get(4).getReferenceID():null);
		}
		
		licPerList.add(permitL);
		//bctsPermitType.setPermitLicenceLst(licPerList);
		return bctsPermitType;
		
	}
	
	
	/**
	 * License Construct InPaymentPermitType
	 * @param bctsPermitType
	 * @param inPaymtPermit
	 * @param urn
	 * @param uid
	 * @return
	 */
	public static BctsPermitType costructLicence(BctsPermitType bctsPermitType,InPaymentPermitType inPaymtPermit,String urn,String uid) {
		PermitLicence permitL=new PermitLicence();
		List<PermitLicence> licPerList=new ArrayList<>();
		permitL.setUreId(uid);
		permitL.setUreSeq(urn);
		List<LicenceType> licencTypeList=inPaymtPermit.getDeclaration().getLicence();
		if(null!=licencTypeList) {
		permitL.setLicence1(!licencTypeList.isEmpty()?licencTypeList.get(0).getReferenceID():null);
		permitL.setLicence2(licencTypeList.size()>1?licencTypeList.get(1).getReferenceID():null);
		permitL.setLicence3(licencTypeList.size()>2?licencTypeList.get(2).getReferenceID():null);
		permitL.setLicence4(licencTypeList.size()>3?licencTypeList.get(3).getReferenceID():null);
		permitL.setLicence5(licencTypeList.size()>4?licencTypeList.get(4).getReferenceID():null);
		}
		licPerList.add(permitL);
		//bctsPermitType.setPermitLicenceLst(licPerList);
		return bctsPermitType;
	}
	
   /**
    * InPaymentPermitType Party Construction
    * @param bctsPermitType
    * @param inPaymtPermit
    * @param urn
    * @param uid
    * @return
    */
   private static BctsPermitType constructParty(BctsPermitType bctsPermitType,InPaymentPermitType inPaymtPermit,String urn,String uid) {
	   crimsonlogic.tn41.schema.xsd.inpayment.PartyType inPayPartyType=inPaymtPermit.getDeclaration().getParty();
	   PermitParty permitPrty=null;
	   List<PermitParty> permitPartyList=new ArrayList<>();
	   /*Declerant party Start*/
		DeclarantPartyType delcPrtType= inPayPartyType.getDeclarantParty();
		if(null!=delcPrtType) {
			permitPrty=new PermitParty();
			permitPrty.setUreId(uid);
			permitPrty.setUreSeq(urn);
			permitPrty.setPartyInd("DP");
			permitPrty.setContactNo(delcPrtType.getTelephone());
			
			PersonInformationType perInfoType=delcPrtType.getPersonInformation();
			permitPrty.setPostalCode(null!=perInfoType?perInfoType.getCodeValue():null);
			List<String> perNameLst=null!=perInfoType?perInfoType.getName():null;
			permitPrty.setPerName1(null!=perNameLst && !perNameLst.isEmpty()?perNameLst.get(0):null);
			permitPrty.setPerName2(null!=perNameLst && perNameLst.size()>1?perNameLst.get(1):null);
			permitPrty.setPerName3(null!=perNameLst && perNameLst.size()>2?perNameLst.get(2):null);
			permitPartyList.add(permitPrty);
		}
		/*Declerant party End*/
		
		/*Declerant Agent Start*/
		PartyDetailType partyDeclAgntTye=inPayPartyType.getDeclaringAgentParty(); 
		if(null!=partyDeclAgntTye) {
		PartyIdentificationType declAgntIdenType=partyDeclAgntTye.getPartyIdentification();
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("DA");
		permitPrty.setPerId(null!=declAgntIdenType?declAgntIdenType.getID():null);
		PartyNameType dclAgtptyNmType=partyDeclAgntTye.getPartyName();
		List<String> dclAntprtNameLst=null!=dclAgtptyNmType?dclAgtptyNmType.getName():null;
		permitPrty.setPerName1(null!=dclAntprtNameLst && !dclAntprtNameLst.isEmpty()?dclAntprtNameLst.get(0):null);
		permitPrty.setPerName2(null!=dclAntprtNameLst && dclAntprtNameLst.size()>1?dclAntprtNameLst.get(1):null);
		permitPrty.setPerName3(null!=dclAntprtNameLst && dclAntprtNameLst.size()>2?dclAntprtNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*Declerant party End*/
		
		/*FreightForwareder party Start*/
		PartyDetailType partyFreForType=inPayPartyType.getFreightForwarderParty();
		if(null!=partyFreForType) {
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("FR");
		PartyIdentificationType freigntIdentType=null!=partyFreForType?partyFreForType.getPartyIdentification():null;
		permitPrty.setPerId(null!=freigntIdentType?freigntIdentType.getID():null);
		PartyNameType fregihtNameType=null!=partyFreForType?partyFreForType.getPartyName():null;
		List<String> freightNameLst=null!=fregihtNameType?fregihtNameType.getName():null;
		permitPrty.setPerName1(null!=freightNameLst && !freightNameLst.isEmpty()?freightNameLst.get(0):null);
		permitPrty.setPerName2(null!=freightNameLst && freightNameLst.size()>1?freightNameLst.get(1):null);
		permitPrty.setPerName3(null!=freightNameLst && freightNameLst.size()>2?freightNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*FreightForwareder party end*/
		
		/*impoPrtyType party Start*/
		PartyDetailType impoPrtyType=inPayPartyType.getImporterParty();
		if(null!=impoPrtyType) {
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("IM");
		PartyIdentificationType importerIdentType=null!=impoPrtyType?impoPrtyType.getPartyIdentification():null;
		permitPrty.setPerId(null!=importerIdentType?importerIdentType.getID():null);
		PartyNameType importerNameType=null!=impoPrtyType?impoPrtyType.getPartyName():null;
		List<String> importerNameLst=null!=importerNameType?importerNameType.getName():null;
		permitPrty.setPerName1(null!=importerNameLst && !importerNameLst.isEmpty()?importerNameLst.get(0):null);
		permitPrty.setPerName2(null!=importerNameLst && importerNameLst.size()>1?importerNameLst.get(1):null);
		permitPrty.setPerName3(null!=importerNameLst && importerNameLst.size()>2?importerNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*impoPrtyType party End*/
		
		/*inWrdCrrAgntPrtType party Start*/
		PartyDetailType inWrdCrrAgntPrtType=inPayPartyType.getInwardCarrierAgentParty();
		if(null!=inWrdCrrAgntPrtType) {
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("IW");
		PartyIdentificationType inWrdIdentType=null!=inWrdCrrAgntPrtType?inWrdCrrAgntPrtType.getPartyIdentification():null;
		permitPrty.setPerId(null!=inWrdIdentType?inWrdIdentType.getID():null);
		PartyNameType inWrdNameType=null!=inWrdCrrAgntPrtType?inWrdCrrAgntPrtType.getPartyName():null;
		List<String> inWrdNameLst=null!=inWrdNameType?inWrdNameType.getName():null;
		permitPrty.setPerName1(null!=inWrdNameLst && !inWrdNameLst.isEmpty()?inWrdNameLst.get(0):null);
		permitPrty.setPerName2(null!=inWrdNameLst && inWrdNameLst.size()>1?inWrdNameLst.get(1):null);
		permitPrty.setPerName3(null!=inWrdNameLst && inWrdNameLst.size()>2?inWrdNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*inWrdCrrAgntPrtType party End*/
		
		/*ClaimantParty party Start*/
		ClaimantPartyType claiMantPrtType=inPayPartyType.getClaimantParty();
		if(null!=claiMantPrtType) {
			
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("CL");
		PersonInformationType perInfoType=null!=claiMantPrtType?claiMantPrtType.getClaimantInformation():null;
		permitPrty.setPostalCode(null!=perInfoType?perInfoType.getCodeValue():null);
		List<String> perNameLst=null!=perInfoType?perInfoType.getName():null;
		permitPrty.setPerName1(null!=perNameLst && !perNameLst.isEmpty()?perNameLst.get(0):null);
		permitPrty.setPerName2(null!=perNameLst && perNameLst.size()>1?perNameLst.get(1):null);
		permitPrty.setPerName3(null!=perNameLst && perNameLst.size()>2?perNameLst.get(2):null);
		PartyDetailType claimPartyDtlsType= claiMantPrtType.getPartyDetail();
		PartyIdentificationType clmPrtIdenType=null!=claimPartyDtlsType?claimPartyDtlsType.getPartyIdentification():null;
		permitPrty.setPerId(null!=clmPrtIdenType?clmPrtIdenType.getID():null);
		PartyNameType clmPrtNameType=null!=claimPartyDtlsType?claimPartyDtlsType.getPartyName():null;
		List<String> clmPrtyNameLst=null!=clmPrtNameType?clmPrtNameType.getName():null;
		permitPrty.setPerName1(null!=clmPrtyNameLst && !clmPrtyNameLst.isEmpty()?clmPrtyNameLst.get(0):null);
		permitPrty.setPerName2(null!=clmPrtyNameLst && clmPrtyNameLst.size()>1?clmPrtyNameLst.get(1):null);
		permitPrty.setPerName3(null!=clmPrtyNameLst && clmPrtyNameLst.size()>2?clmPrtyNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*ClaimantParty party End*/
	   return bctsPermitType;
   }
   
   
   
   /**
    * OutwardPermitType Party construction method
    * @param bctsPermitType
    * @param outWPermit
    * @param urn
    * @param uid
    * @return
    */
   private static BctsPermitType constructParty(BctsPermitType bctsPermitType,OutwardPermitType outWPermit,String urn,String uid) {
	   
	   PermitParty permitPrty=new PermitParty();
	   List<PermitParty> permitPartyList=new ArrayList<>();
	   
	   
		
		PartyType partyType=outWPermit.getDeclaration().getParty();
		/*Consignee party Start*/
		ConsigneePartyType consiPrtType=null!=partyType?partyType.getConsigneeParty():null;
		if(null!=consiPrtType) {
			permitPrty.setUreId(uid);
			permitPrty.setUreSeq(urn);
			permitPrty.setPartyInd("CN");
			AddressType addressType=null!=consiPrtType?consiPrtType.getAddress():null;
				if(null!=addressType) {
				permitPrty.setCityName(addressType.getCityName());
				permitPrty.setCountryCode(addressType.getCountryCode());
				permitPrty.setCitySubName(addressType.getCountrySubentity());
				permitPrty.setCityCode(addressType.getCountrySubentityCode());
				permitPrty.setPostalCode(addressType.getPostalZone());
				}
			AddressLineType consPartAddLineType=null!=consiPrtType?consiPrtType.getAddressLine():null;
			permitPrty.setAddr1(null!=consPartAddLineType && !consPartAddLineType.getLine().isEmpty()?consPartAddLineType.getLine().get(0):null);
			permitPrty.setAddr2(null!=consPartAddLineType && consPartAddLineType.getLine().size()>1?consPartAddLineType.getLine().get(1):null);
			permitPrty.setAddr3(null!=consPartAddLineType && consPartAddLineType.getLine().size()>2?consPartAddLineType.getLine().get(2):null);
			
			PartyNameType partyNameType=null!=consiPrtType?consiPrtType.getPartyName():null;
			List<String> conSPrtNmLst=null!=partyNameType?partyNameType.getName():null;
			permitPrty.setPerName1(null!=conSPrtNmLst && !conSPrtNmLst.isEmpty()?conSPrtNmLst.get(0):null);
			permitPrty.setPerName2(null!=conSPrtNmLst && conSPrtNmLst.size()>1?conSPrtNmLst.get(1):null);
			permitPrty.setPerName3(null!=conSPrtNmLst && conSPrtNmLst.size()>2?conSPrtNmLst.get(2):null);
			permitPartyList.add(permitPrty);
			
		}
		
		
		/*Consignee party End*/
		
		/*Declerant party Start*/
		DeclarantPartyType delcPrtType= partyType.getDeclarantParty();
		if(null!=delcPrtType) {
			permitPrty=new PermitParty();
			permitPrty.setUreId(uid);
			permitPrty.setUreSeq(urn);
			permitPrty.setPartyInd("DP");
			permitPrty.setContactNo(null!=delcPrtType?delcPrtType.getTelephone():null);
			
			PersonInformationType perInfoType=null!=delcPrtType?delcPrtType.getPersonInformation():null;
			permitPrty.setPostalCode(null!=perInfoType?perInfoType.getCodeValue():null);
			List<String> perNameLst=null!=perInfoType?perInfoType.getName():null;
			permitPrty.setPerName1(null!=perNameLst && !perNameLst.isEmpty()?perNameLst.get(0):null);
			permitPrty.setPerName2(null!=perNameLst && perNameLst.size()>1?perNameLst.get(1):null);
			permitPrty.setPerName3(null!=perNameLst && perNameLst.size()>2?perNameLst.get(2):null);
			permitPartyList.add(permitPrty);
		}
		/*Declerant party End*/
		
		/*Declerant Agent Start*/
		PartyDetailType partyDeclAgntTye=partyType.getDeclaringAgentParty(); 
		if(null!=partyDeclAgntTye) {
		PartyIdentificationType declAgntIdenType=null!=partyDeclAgntTye?partyDeclAgntTye.getPartyIdentification():null;
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("DA");
		permitPrty.setPerId(null!=declAgntIdenType?declAgntIdenType.getID():null);
		PartyNameType dclAgtptyNmType=null!=partyDeclAgntTye?partyDeclAgntTye.getPartyName():null;
		List<String> dclAntprtNameLst=null!=dclAgtptyNmType?dclAgtptyNmType.getName():null;
		permitPrty.setPerName1(null!=dclAntprtNameLst && !dclAntprtNameLst.isEmpty()?dclAntprtNameLst.get(0):null);
		permitPrty.setPerName2(null!=dclAntprtNameLst && dclAntprtNameLst.size()>1?dclAntprtNameLst.get(1):null);
		permitPrty.setPerName3(null!=dclAntprtNameLst && dclAntprtNameLst.size()>2?dclAntprtNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*Declerant party End*/
		
		/*Enduser party Start*/
		EndUserPartyType endUsrPrtuType=partyType.getEndUserParty();
		if(null!=endUsrPrtuType) {
		PartyNameType endUrPrtyNameType=null!=endUsrPrtuType?endUsrPrtuType.getPartyName():null;
		AddressType endUsraddressType=null!=endUsrPrtuType?endUsrPrtuType.getAddress():null;
		
		List<String> endUsrPrtNmLst=null!=endUrPrtyNameType?endUrPrtyNameType.getName():null;
		permitPrty.setPerName1(null!=endUsrPrtNmLst && !endUsrPrtNmLst.isEmpty()?endUsrPrtNmLst.get(0):null);
		permitPrty.setPerName2(null!=endUsrPrtNmLst && endUsrPrtNmLst.size()>1?endUsrPrtNmLst.get(1):null);
		permitPrty.setPerName3(null!=endUsrPrtNmLst && endUsrPrtNmLst.size()>2?endUsrPrtNmLst.get(2):null);
		
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("EN");
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
		permitPartyList.add(permitPrty);
		}
		/*Enduser party End*/
		
		
		/*FreightForwareder party Start*/
		PartyDetailType partyFreForType=partyType.getFreightForwarderParty();
		if(null!=partyFreForType) {
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("FR");
		PartyIdentificationType freigntIdentType=null!=partyFreForType?partyFreForType.getPartyIdentification():null;
		permitPrty.setPerId(null!=freigntIdentType?freigntIdentType.getID():null);
		PartyNameType fregihtNameType=null!=partyFreForType?partyFreForType.getPartyName():null;
		List<String> freightNameLst=null!=fregihtNameType?fregihtNameType.getName():null;
		permitPrty.setPerName1(null!=freightNameLst && !freightNameLst.isEmpty()?freightNameLst.get(0):null);
		permitPrty.setPerName2(null!=freightNameLst && freightNameLst.size()>1?freightNameLst.get(1):null);
		permitPrty.setPerName3(null!=freightNameLst && freightNameLst.size()>2?freightNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*FreightForwareder party end*/
		
		/*impoPrtyType party Start*/
		PartyDetailType impoPrtyType=partyType.getImporterParty();
		if(null!=impoPrtyType) {
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("IM");
		PartyIdentificationType importerIdentType=null!=impoPrtyType?impoPrtyType.getPartyIdentification():null;
		permitPrty.setPerId(null!=importerIdentType?importerIdentType.getID():null);
		PartyNameType importerNameType=null!=impoPrtyType?impoPrtyType.getPartyName():null;
		List<String> importerNameLst=null!=importerNameType?importerNameType.getName():null;
		permitPrty.setPerName1(null!=importerNameLst && !importerNameLst.isEmpty()?importerNameLst.get(0):null);
		permitPrty.setPerName2(null!=importerNameLst && importerNameLst.size()>1?importerNameLst.get(1):null);
		permitPrty.setPerName3(null!=importerNameLst && importerNameLst.size()>2?importerNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*impoPrtyType party End*/
		
		/*inWrdCrrAgntPrtType party Start*/
		PartyDetailType inWrdCrrAgntPrtType=partyType.getInwardCarrierAgentParty();
		if(null!=inWrdCrrAgntPrtType) {
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("IW");
		PartyIdentificationType inWrdIdentType=null!=inWrdCrrAgntPrtType?inWrdCrrAgntPrtType.getPartyIdentification():null;
		permitPrty.setPerId(null!=inWrdIdentType?inWrdIdentType.getID():null);
		PartyNameType inWrdNameType=null!=inWrdCrrAgntPrtType?inWrdCrrAgntPrtType.getPartyName():null;
		List<String> inWrdNameLst=null!=inWrdNameType?inWrdNameType.getName():null;
		permitPrty.setPerName1(null!=inWrdNameLst && !inWrdNameLst.isEmpty()?inWrdNameLst.get(0):null);
		permitPrty.setPerName2(null!=inWrdNameLst && inWrdNameLst.size()>1?inWrdNameLst.get(1):null);
		permitPrty.setPerName3(null!=inWrdNameLst && inWrdNameLst.size()>2?inWrdNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*inWrdCrrAgntPrtType party End*/
		
		/*outWrdCrrAgntPrtType party Start*/
		PartyDetailType outWrdCrrAgntPrtType=partyType.getOutwardCarrierAgentParty();
		if(null!=outWrdCrrAgntPrtType) {
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("OW");
		PartyIdentificationType outWrdIdentType=null!=outWrdCrrAgntPrtType?outWrdCrrAgntPrtType.getPartyIdentification():null;
		permitPrty.setPerId(null!=outWrdIdentType?outWrdIdentType.getID():null);
		PartyNameType outWrdNameType=null!=outWrdCrrAgntPrtType?outWrdCrrAgntPrtType.getPartyName():null;
		List<String> outWrdNameLst=null!=outWrdNameType?outWrdNameType.getName():null;
		permitPrty.setPerName1(null!=outWrdNameLst && !outWrdNameLst.isEmpty()?outWrdNameLst.get(0):null);
		permitPrty.setPerName2(null!=outWrdNameLst && outWrdNameLst.size()>1?outWrdNameLst.get(1):null);
		permitPrty.setPerName3(null!=outWrdNameLst && outWrdNameLst.size()>2?outWrdNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*outWrdCrrAgntPrtType party End*/
		
		/*expoPrtyType party Start*/
		ExporterPartyType expoPrtyType=partyType.getExporterParty();
		if(null!=expoPrtyType) {
		AddressType expoAddType=null!=expoPrtyType?expoPrtyType.getAddress():null;
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("EP");
		if(null!=expoAddType) {
			permitPrty.setCityName(expoAddType.getCityName());
			permitPrty.setCountryCode(expoAddType.getCountryCode());
			permitPrty.setCitySubName(expoAddType.getCountrySubentity());
			permitPrty.setCityCode(expoAddType.getCountrySubentityCode());
			permitPrty.setPostalCode(expoAddType.getPostalZone());
			}
		
		AddressLineType expoAddLineType=null!=expoPrtyType?expoPrtyType.getAddressLine():null;
		permitPrty.setAddr1(null!=expoAddLineType && !expoAddLineType.getLine().isEmpty()?expoAddLineType.getLine().get(0):null);
		permitPrty.setAddr2(null!=expoAddLineType && expoAddLineType.getLine().size()>1?expoAddLineType.getLine().get(1):null);
		permitPrty.setAddr3(null!=expoAddLineType && expoAddLineType.getLine().size()>2?expoAddLineType.getLine().get(2):null);
		
		PartyDetailType expoPrtyDtlsType=null!=expoPrtyType?expoPrtyType.getPartyDetail():null;
		PartyIdentificationType expoIdentType=null!=expoPrtyDtlsType?expoPrtyDtlsType.getPartyIdentification():null;
		permitPrty.setPerId(null!=expoIdentType?expoIdentType.getID():null);
		PartyNameType expoNameType=null!=expoPrtyDtlsType?expoPrtyDtlsType.getPartyName():null;
		List<String> expoNameLst=null!=expoNameType?expoNameType.getName():null;
		permitPrty.setPerName1(null!=expoNameLst && !expoNameLst.isEmpty()?expoNameLst.get(0):null);
		permitPrty.setPerName2(null!=expoNameLst && expoNameLst.size()>1?expoNameLst.get(1):null);
		permitPrty.setPerName3(null!=expoNameLst && expoNameLst.size()>2?expoNameLst.get(2):null);
		permitPartyList.add(permitPrty);
		}
		/*expoPrtyType party End*/
		
		
		/*exporManuFType party Start*/
		ExporterPartyType exporManuFType=null!=partyType?partyType.getManufacturerParty():null;
		if(null!=exporManuFType) {
		AddressType expoManuAddType=null!=exporManuFType?exporManuFType.getAddress():null;
		permitPrty=new PermitParty();
		permitPrty.setUreId(uid);
		permitPrty.setUreSeq(urn);
		permitPrty.setPartyInd("EM");
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
		permitPartyList.add(permitPrty);
		}
		/*exporManuFType party End*/
		//bctsPermitType.setPermitPartyLst(permitPartyList);
		return bctsPermitType;
   }
	
	
	private static BctsPermitType consructOutPermit(OutwardPermitType outWPermit,BctsPermitType bctsPermitType) {
		crimsonlogic.tn41.schema.xsd.outwarddeclaration.CertificateType certType=outWPermit.getDeclaration().getCertificate();
		List<PermitCertifate> permitCertList=new ArrayList<>();
		List<PermitCargo> permitCargoList=new ArrayList<>();
		
		HeaderType hType=outWPermit.getDeclaration().getHeader();
		//bctsPermitType.setMsgType(hType.getCommonAccessReference());
		//bctsPermitType.setUenId(hType.getUniqueReferenceNumber().getID());
		//bctsPermitType.setUrnDate(BctsDateFormatter.convertDateTime(hType.getUniqueReferenceNumber().getDate()));
		//bctsPermitType.setUrnSeq(hType.getUniqueReferenceNumber().getSequenceNumeric().toString());
		
		PermitCertifate permitCert=generatePermitCert(certType, hType);
		permitCertList.add(permitCert);
		//bctsPermitType.setPermitCertList(permitCertList);
		
		CargoType cargoType=outWPermit.getDeclaration().getCargo();
		PermitCargo permitCargo=generatePermitCargo(cargoType,hType);
		permitCargoList.add(permitCargo);
		//bctsPermitType.setPermitCargoList(permitCargoList);
		return bctsPermitType;
	}
	
	private static BctsPermitType consructTrans(InwardTransportType inwordTrans,OutwardTransportType ouWordTrans,BctsPermitType bctsPermitType,String urn,String uid) {
		if(null!=inwordTrans) {
		PermitInTransport inTran=new PermitInTransport();
		inTran.setLoadingPortCode(inwordTrans.getLoadingPort());
		//inTran.setArraivalDate(inwordTrans.getArrivalDate());//TODO need to check and change date
		TransportMeansType transPortMeansType=inwordTrans.getTransportMeans();
		inTran.setMastShipDocNo(null!=transPortMeansType?transPortMeansType.getMAWBOUCROBLNumber():null);
		TransportModeType transModeType=null!=transPortMeansType?transPortMeansType.getTransportMode():null;
		inTran.setTransPrtIdentifier(null!=transModeType?transModeType.getTransportIdentifier():null);
		inTran.setTransModeCode(null!=transModeType?transModeType.getModeCode():null);
		inTran.setUreId(uid);
		inTran.setUreSeq(urn);
		List<PermitInTransport> permitInLst=new ArrayList<>();
		permitInLst.add(inTran);
		//bctsPermitType.setPerInTransLst(permitInLst);
		}
		if(null!=ouWordTrans) {
			PermitOutTransport outTrans=new PermitOutTransport();
			//outTrans.setDepartDate(ouWordTrans.getDepartureDate());//TODO Modify date
			outTrans.setDisPortCode(ouWordTrans.getDischargePort());
			outTrans.setFinalCtryCode(ouWordTrans.getFinalDestinationCountry());
			AdditionalVesselInformationType addVesInfoType=ouWordTrans.getAdditionalVesselInformation();
			
			outTrans.setFinalPortCode(null!=addVesInfoType?addVesInfoType.getLoadingFinalPort():null);
			outTrans.setNextPortCode(null!=addVesInfoType?addVesInfoType.getLoadingNextPort():null);
			outTrans.setNrt(null!=addVesInfoType?addVesInfoType.getNetRegisterTonnage():null);
			outTrans.setVesNation(null!=addVesInfoType?addVesInfoType.getVesselNationality():null);
			outTrans.setVesType(null!=addVesInfoType?addVesInfoType.getVesselType():null);
			MaritimeTransportType mariTransType=null!=addVesInfoType?addVesInfoType.getTowingVessel():null;
			outTrans.setVesName(null!=mariTransType?mariTransType.getVesselName():null);
			
			
			//mariTransType.getVesselID();
			TransportMeansType transPortMeansType=ouWordTrans.getTransportMeans();
			outTrans.setMastShipDocNo(null!=transPortMeansType?transPortMeansType.getMAWBOUCROBLNumber():null);
			TransportModeType modeType=null!=transPortMeansType?transPortMeansType.getTransportMode():null;
			outTrans.setTransModeCode(null!=modeType?modeType.getModeCode():null);
			outTrans.setTransPrtIdentifier(null!=modeType?modeType.getTransportIdentifier():null);
			outTrans.setUreId(uid);
			outTrans.setUreSeq(urn);
			List<PermitOutTransport> permOutInLst=new ArrayList<>();
			permOutInLst.add(outTrans);
			//bctsPermitType.setPermitOutTransLst(permOutInLst);
		}
		return bctsPermitType;
	}
	
	
	//inPaymtPermi
	private static BctsPermitType consructCaaApproval(PermitType pType,BctsPermitType bctsPermitType,String urn,String uid) {
		List<CaaApprovalCondition> appCondLst=new ArrayList<>(); 
		List<ConditionType> conTypeLst=null!=pType?pType.getSCApprovalCondition():null;
		Random random=new Random();
		for(ConditionType cType:conTypeLst) {
			BigDecimal seqNo=new BigDecimal(String.format("%04d", random.nextInt(10000)));
			CaaApprovalCondition appCond=new CaaApprovalCondition();
			appCond.setAgencyCode(cType.getAgencyCode());
			appCond.setConditionCode(cType.getConditionCode());
			//appCond.setCondtionDesc(cType.getConditionDescription());
			appCond.setAprovalType("TEST");//NEED to confirm
			appCond.setUenId(uid);
			appCond.setUrnSeq(urn);
			appCond.setSeqNo(seqNo);
			appCondLst.add(appCond);
		}
		//bctsPermitType.setCaaApprovalLst(appCondLst);
		return bctsPermitType;
	}
	
	private static BctsPermitType consructInPayPermit(InPaymentPermitType inPaymtPermit,BctsPermitType bctsPermitType) {
		List<PermitCargo> permitCargoList=new ArrayList<>();
		crimsonlogic.tn41.schema.xsd.inpayment.HeaderType inPhType=inPaymtPermit.getDeclaration().getHeader();
		//bctsPermitType.setMsgType(inPhType.getCommonAccessReference());
		//bctsPermitType.setUenId(inPhType.getUniqueReferenceNumber().getID());
		//bctsPermitType.setUrnDate(BctsDateFormatter.convertDateTime(inPhType.getUniqueReferenceNumber().getDate()));
		//bctsPermitType.setUrnSeq(inPhType.getUniqueReferenceNumber().getSequenceNumeric().toString());
		crimsonlogic.tn41.schema.xsd.inpayment.CargoType cargoType=inPaymtPermit.getDeclaration().getCargo();
		PermitCargo permitCargo=generateInPayPerCargo(cargoType,inPhType);
		permitCargoList.add(permitCargo);
		//bctsPermitType.setPermitCargoList(permitCargoList);
		return bctsPermitType;
	}
	
	private static PermitCargo generatePermitCargo(CargoType cargoType,HeaderType hType) {
		PermitCargo permitCargo=new PermitCargo();
		LocationType recLoType=cargoType.getReceiptLocation();
		LocationType relLoType=cargoType.getReleaseLocation();
		 StorageLocationType storLocType=cargoType.getStorageLocation();
		//permitCargo.setImpBkpStDate();
		//permitCargo.setImpEndDate(null);
		//permitCargo.setMaxAmeCtnSqe(null);
		if(null!=recLoType) {
		permitCargo.setRecLocCode(recLoType.getLocationCode());
		permitCargo.setRecLocName(recLoType.getLocationName());
		}
		if(null!=relLoType) {
		permitCargo.setRelLocCode(relLoType.getLocationCode());
		permitCargo.setRelLocName(relLoType.getLocationName());
		}
		if(null!=storLocType) {
		permitCargo.setStorLocCode(storLocType.getLocationCode());
		//permitCargo.setStorLocName(null);
		}
		//permitCargo.setRemStartDate();
		permitCargo.setUreId(hType.getUniqueReferenceNumber().getID());
		permitCargo.setUreSeq(hType.getUniqueReferenceNumber().getSequenceNumeric().toString());
		return permitCargo;
	}
	
	
	private static PermitCargo generateInPayPerCargo(crimsonlogic.tn41.schema.xsd.inpayment.CargoType cargoType,crimsonlogic.tn41.schema.xsd.inpayment.HeaderType hType) {
		PermitCargo permitCargo=new PermitCargo();
		LocationType recLoType=cargoType.getReceiptLocation();
		LocationType relLoType=cargoType.getReleaseLocation();
		 StorageLocationType storLocType=cargoType.getStorageLocation();
		//permitCargo.setImpBkpStDate();
		//permitCargo.setImpEndDate(null);
		//permitCargo.setMaxAmeCtnSqe(null);
		if(null!=recLoType) {
		permitCargo.setRecLocCode(recLoType.getLocationCode());
		permitCargo.setRecLocName(recLoType.getLocationName());
		}
		if(null!=relLoType) {
		permitCargo.setRelLocCode(relLoType.getLocationCode());
		permitCargo.setRelLocName(relLoType.getLocationName());
		}
		if(null!=storLocType) {
		permitCargo.setStorLocCode(storLocType.getLocationCode());
		//permitCargo.setStorLocName(null);
		}
		//permitCargo.setRemStartDate();
		permitCargo.setUreId(hType.getUniqueReferenceNumber().getID());
		permitCargo.setUreSeq(hType.getUniqueReferenceNumber().getSequenceNumeric().toString());
		
		
		List<PermitCargoCtn> permitCarolst=new ArrayList<>();
		List<TransportEquipmentType> transEqpTypeLst=cargoType.getTransportEquipment();
		for(TransportEquipmentType transEqpType:transEqpTypeLst) {
			PermitCargoCtn permitCaroCnt=new PermitCargoCtn();
			permitCaroCnt.setUreId(hType.getUniqueReferenceNumber().getID());
			permitCaroCnt.setUreSeq(hType.getUniqueReferenceNumber().getSequenceNumeric().toString());
			permitCaroCnt.setCtnSize(transEqpType.getSizeTypeCode());
			permitCaroCnt.setCtnNo(transEqpType.getEquipmentID());
			permitCaroCnt.setCtnSealNo(transEqpType.getTransportEquipmentSeal().getSealID());
			permitCaroCnt.setCtnWgt(transEqpType.getEquipmentWeightMeasureNumeric());
			permitCaroCnt.setSeqNo(transEqpType.getSequenceNumeric());
			permitCarolst.add(permitCaroCnt);
			//transEqpType.getEquipmentID();
			//transEqpType.getEquipmentWeightMeasureNumeric();
			//transEqpType.getSequenceNumeric();
			//transEqpType.getSizeTypeCode();
			//transEqpType.getTransportEquipmentSeal().getSealID();
		}
		permitCargo.setPermitCCtnLst(permitCarolst);
		
		return permitCargo;
	}
	
	
	private static PermitCertifate generatePermitCert(crimsonlogic.tn41.schema.xsd.outwarddeclaration.CertificateType certType,HeaderType hType) {
		PermitCertifate permitCert=new PermitCertifate();
		permitCert.setApprProdType(certType.getApplicationProductType());
		permitCert.setEnteryYear(certType.getEntryYear());
		permitCert.setGspDonCnCode(certType.getGSPDonorCountry());
		permitCert.setPrefPer(certType.getPreferenceContentPercent());
		permitCert.setCurrCode(certType.getCurrencyCode());
		permitCert.setCertiDetails(certType.getAdditionalCertificateDetails().toString());
		permitCert.setTransDetails(certType.getTransportDetails().toString());
		permitCert.setUreId(hType.getUniqueReferenceNumber().getID());
		permitCert.setUreSeq(hType.getUniqueReferenceNumber().getSequenceNumeric().toString());
		List<CertificateDetailType> certDetailsType=certType.getCertificateDetail();
		int flag=1;
		for(CertificateDetailType cer:certDetailsType) {
			if(flag==1) {
				permitCert.setCert1Type(cer.getCertificateType());
				permitCert.setCert1Copies(cer.getCopiesNumeric());
			}else {
				permitCert.setCert2Type(cer.getCertificateType());
				permitCert.setCert1Copies(cer.getCopiesNumeric());
			}
		}
		return permitCert;
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
}
