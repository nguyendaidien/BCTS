package com.etrade.bcts.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etrade.bcts.exception.BCTSException;
import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.BctsJobHeader;
import com.etrade.bcts.model.CaaApprovalCondition;
import com.etrade.bcts.model.Company;
import com.etrade.bcts.model.PermitCondition;
import com.etrade.bcts.model.PermitInTransport;
import com.etrade.bcts.model.PermitOutTransport;
import com.etrade.bcts.model.User;
import com.etrade.bcts.service.CaseService;
import com.etrade.bcts.service.PermitService;
import com.etrade.bcts.util.BctsConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BctsXMLWriter implements ItemWriter<BctsJobHeader> {
	static final Logger LOG = LoggerFactory.getLogger(BctsXMLWriter.class);

	@Autowired
	PermitService permitService;
	@Autowired
	CaseService caseService;

	/**
	 * This method will construct Alert object list to store in Alert table
	 * 
	 * @author ajayasamanta
	 * @param bctsJobHeader
	 * @return
	 */
	public static List<BCTSAlert> constructAlertInfo(BctsJobHeader bctsJobHeader) {
		String msg = bctsJobHeader.getCarType();
		List<BCTSAlert> alertLst = new ArrayList<>();
		BCTSAlert bctsAlert = null;
		Company comp = new Company();
		String[] values = { "A13", "A14", "A73", "DD", "Z02", "Z18" };// TODO get details from DB
		Date openDate = new Date();
		if (BctsConstants.OUTPUTPERMIT.equals(msg)) {
			List<CaaApprovalCondition> caaAprCondLst = bctsJobHeader.getPermitConditions();
			for (CaaApprovalCondition caaApr : caaAprCondLst) {
				boolean contains = Arrays.stream(values).anyMatch(caaApr.getConditionCode()::equals);
				if (contains) {
					bctsAlert = new BCTSAlert();
					String altCnt = converntJsonDetais(caaApr);
					LOG.info("constructAlertInfo() altCnt:{}", altCnt);
					bctsAlert.setAlertContent(altCnt);
					List<PermitOutTransport> poutLst = bctsJobHeader.getOutTransList();
					Date depDate = null;
					Date arrvDate = null;
					boolean flag = false;
					if (null != poutLst && !poutLst.isEmpty()) {
						depDate = poutLst.get(0).getDepartDate();
					}
					List<PermitInTransport> pInLst = bctsJobHeader.getInTransList();
					if (null != pInLst && !pInLst.isEmpty()) {
						arrvDate = pInLst.get(0).getArraivalDate();
					}

					if (null != depDate && null != arrvDate && arrvDate.compareTo(depDate) > 0) {
						LOG.info("Date1 is after Date2");
						flag = true;
					}

					if (flag) {
						bctsAlert.setStatus(BctsConstants.CASE_STATUS_COMPLETED);
					} else {
						bctsAlert.setStatus(BctsConstants.CASE_STATUS_OPEN);
					}
					comp.setUeiNo(bctsJobHeader.getUenId());
					bctsAlert.setJobNo(bctsJobHeader.getUrnSeq());
					bctsAlert.setUen(comp);
					bctsAlert.setCategory(BctsConstants.CASE_CATEGORY_PERMIT_CONDITION);
					bctsAlert.setPermitNo(bctsJobHeader.getPermits().get(0).getPermitNumber());
					bctsAlert.setOpenDate(openDate);
					User user = new User();
					user.setId(1);
					bctsAlert.setOpenBy(user);
					alertLst.add(bctsAlert);
				}
			}
		} else if (BctsConstants.INPUTPERMIT.equals(msg)) {
			List<CaaApprovalCondition> caaAprCondLst = bctsJobHeader.getPermitConditions();
			for (CaaApprovalCondition caaApr : caaAprCondLst) {
				boolean contains = Arrays.stream(values).anyMatch(caaApr.getConditionCode()::equals);
				if (contains) {
					bctsAlert = new BCTSAlert();
					String altCnt = converntJsonDetais(caaApr);
					LOG.info("constructAlertInfo() altCnt:{}", altCnt);
					bctsAlert.setAlertContent(altCnt);
					bctsAlert.setStatus(BctsConstants.CASE_STATUS_OPEN);
					comp.setUeiNo(bctsJobHeader.getUenId());
					bctsAlert.setJobNo(bctsJobHeader.getUrnSeq());
					bctsAlert.setUen(comp);
					bctsAlert.setCategory(BctsConstants.CASE_CATEGORY_PERMIT_CONDITION);
					bctsAlert.setPermitNo(bctsJobHeader.getPermits().get(0).getPermitNumber());
					bctsAlert.setOpenDate(openDate);
					User user = new User();
					user.setId(1);
					bctsAlert.setOpenBy(user);
					alertLst.add(bctsAlert);
				}
			}
		}
		return alertLst;
	}

	/**
	 * It will convert to JSon format for Alert purpose
	 * 
	 * @author ajayasamanta
	 * @param caaApr
	 * @return
	 * @throws JsonProcessingException 
	 */
//	public static String converntJsonDetais(CaaApprovalCondition caaApr) {
//		StringBuilder curlyS = new StringBuilder();
//		curlyS.append("{");
//		StringBuilder curlyE = new StringBuilder();
//		curlyE.append("}");
//		StringBuilder quote = new StringBuilder();
//		quote.append("\"");
//		StringBuilder colon = new StringBuilder();
//		colon.append(":");
//		StringBuilder comma = new StringBuilder();
//		comma.append(",");
//		StringBuilder sb = new StringBuilder();
//		sb.append(curlyS);
//		sb.append(quote).append("AgencyCode").append(quote).append(colon);
//		sb.append(quote).append(caaApr.getAgencyCode()).append(quote).append(comma);
//		sb.append(quote).append("ConditionCode").append(quote).append(colon);
//		sb.append(quote).append(caaApr.getConditionCode()).append(quote).append(comma);
//		sb.append(quote).append("ConditionDescription").append(quote).append(colon);
//		sb.append(quote).append(caaApr.getCondtionDesc1()).append(quote);
//		sb.append(curlyE);
//		return sb.toString();
//
//	}

	public static String converntJsonDetais(CaaApprovalCondition caaApr) {
		ObjectMapper mapper = new ObjectMapper();
		String condition = "";
		try {
			condition = mapper.writeValueAsString(new PermitCondition(caaApr));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return condition;
	}
	/**
	 * This method will write into all permit related tables
	 * 
	 * @author ajayasamanta
	 */
	@Override
	public void write(List<? extends BctsJobHeader> permitList)  {
		LOG.info("writepermitList size:{}", permitList.size());
		BctsJobHeader jobH=null;
		for (BctsJobHeader jobHeader : permitList) {
			//LOG.info("jobHeader:{}", jobHeader.toString());
			/* Insert in all permit related tables */
				jobH=permitService.getJobHeaderInfo(jobHeader.getUenId(), jobHeader.getUrnSeq());
				if(null==jobH) {
					LOG.info("inside BctsJobObject:{}",jobHeader.toString());
					permitService.saveJobHeader(jobHeader);
					/* Insert in Alert table to notify user */
					List<BCTSAlert> alrlst = constructAlertInfo(jobHeader);
					for (BCTSAlert alt : alrlst) {
						caseService.save(alt);
					}
				}else {
					LOG.info("ByPass BctsJobObject:{}",jobHeader.toString());
				}
		}
	}
}
