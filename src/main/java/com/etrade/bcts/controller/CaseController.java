package com.etrade.bcts.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;
import com.etrade.bcts.model.CaseDetail;
import com.etrade.bcts.model.LicenceValidity;
import com.etrade.bcts.model.PermitCondition;
import com.etrade.bcts.model.User;
import com.etrade.bcts.service.CaseService;
import com.etrade.bcts.service.UserService;
import com.etrade.bcts.util.BCTSUtil;
import com.etrade.bcts.util.BctsConstants;
import com.etrade.bcts.util.TrwDate;

@Controller
@RequestMapping(value="cases")
public class CaseController {
	private static final Logger LOG = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	CaseService caseService;
	
	@Autowired 
	UserService userService;
	
	@RequestMapping(value="/{caseId}", method=RequestMethod.GET)
	public String showCaseDetails(@PathVariable int caseId, ModelMap model, Principal principal) {
		User user = userService.findByUserId(principal.getName(), false);
		BCTSAlert caseDetail = caseService.getCaseDetailById(caseId, user.getCompany(), true);
		if(caseDetail == null) {
			LOG.info("Cannot get case detail");
			return "redirect:/accessDenied";
		}
		model.put("caseComment", new CaseComment());
		model.put("caseDetail", caseDetail);
		if(caseDetail.getCategory().equals(BctsConstants.CASE_CATEGORY_PERMIT_CONDITION)) {
			model.put("pc", new PermitCondition(caseDetail));
		}
		List<CaseComment> comments= caseDetail.getComments();
		Collections.sort(comments);
		model.put("comments", comments);
		return "casedetail";
	}
	
	@RequestMapping(value="/lvList", method=RequestMethod.GET)
	public String getLicenceValidityList(ModelMap model, Principal principal) {
		User user = userService.findByUserId(principal.getName(), false);
		List<BCTSAlert> lvList = caseService.getCasesByCategory(BctsConstants.CASE_CATEGORY_LICENCE_VALIDITY, user.getCompany());
		model.put("lvList", lvList);
		return "licenceValidityList";
	}
	
	@RequestMapping(value="/lv/create", method=RequestMethod.GET)
	public String getLicenceValidityNew(ModelMap model) {
		model.put("licenceValidity", new BCTSAlert());
		return "licenceValidityCreate";
	}
	
	@RequestMapping(value="/lv/update/{caseId}", method=RequestMethod.GET)
	public String getLicenceValidityDetail(@PathVariable int caseId,ModelMap model, Principal principal) {
		User user = userService.findByUserId(principal.getName(), false);
		BCTSAlert lv = caseService.getCaseDetailById(caseId, user.getCompany(), false);
		model.put("licenceValidity", lv);
//		model.put("alertEmails", lv.getAlertEmails().split(","));		
//		model.put("licenceStartDate", TrwDate.getDateString(lv.getLicenceStartDate(), TrwDate.UI_DATEFORMAT) );
//		model.put("licenceEndDate", TrwDate.getDateString(lv.getLicenceEndDate(), TrwDate.UI_DATEFORMAT) );
		model.put("licenceStartDate", TrwDate.getDateString(lv.getLicence().getLicenceStartDate(), TrwDate.UI_DATEFORMAT) );
		model.put("licenceEndDate", TrwDate.getDateString(lv.getLicence().getLicenceEndDate(), TrwDate.UI_DATEFORMAT) );
		model.put("reminderDate", TrwDate.getDateString(lv.getReminderDate(), TrwDate.UI_DATEFORMAT) );
		if(StringUtils.isNotEmpty(lv.getAlertEmails())) {
			JSONArray emails = BCTSUtil.convertStringToJsonArr(lv.getAlertEmails(), ",");
			model.put("alertEmails", emails.toString());
		}
		model.put("editMode", true);
		return "licenceValidityCreate";
	}
	
	@RequestMapping(value="/lv/update/{caseId}", method=RequestMethod.POST)
	public String getLicenceValidityUpdate(@ModelAttribute("licenceValidity") BCTSAlert licenceValidity, @PathVariable int caseId,
			BindingResult result, ModelMap model, Principal principal,
			@RequestParam(name="licenceStartDate") String licenceStartDate,
			@RequestParam(name="licenceEndDate") String licenceEndDate,
			@RequestParam(name="reminderDate") String reminderDate,
			@RequestParam(name="alertEmails") JSONArray alertEmails) {
		if(result.hasErrors()) {
			LOG.error("Binding error");
		}
		licenceValidity.setAlertEmails(BCTSUtil.convertJsonArrToString(alertEmails,','));
		try {
			licenceValidity.getLicence().setLicenceStartDate(TrwDate.parse(licenceStartDate));
			licenceValidity.getLicence().setLicenceEndDate(TrwDate.parse(licenceEndDate));
			licenceValidity.setReminderDate(TrwDate.parse(reminderDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		caseService.update(licenceValidity);
		
		return "redirect:/cases/lvList";
	}
	
	@RequestMapping(value="/lv/create", method=RequestMethod.POST)
	public String getLicenceValidityCreate(@ModelAttribute("licenceValidity") BCTSAlert bctsAlert, BindingResult result, ModelMap model, Principal principal,
			@RequestParam(name="licenceStartDate") String licenceStartDate,
			@RequestParam(name="licenceEndDate") String licenceEndDate,
			@RequestParam(name="reminderDate") String reminderDate,
			@RequestParam(name="alertEmails") JSONArray alertEmails) {
		if(result.hasErrors()) {
			LOG.error("Binding error");
		}
		User user = userService.findByUserId(principal.getName(), false);
		bctsAlert.setAlertEmails(BCTSUtil.convertJsonArrToString(alertEmails,','));
		bctsAlert.setCategory(BctsConstants.CASE_CATEGORY_LICENCE_VALIDITY);
		bctsAlert.setOpenBy(user);
		bctsAlert.setOpenDate(new Date());
		bctsAlert.setStatus(BctsConstants.CASE_STATUS_OPEN);
		bctsAlert.setUen(user.getCompany());
		bctsAlert.setToAlertCompany(true);		
		try {
			LicenceValidity lc = bctsAlert.getLicence();
			if(lc == null) {
				lc = new LicenceValidity();
				bctsAlert.setLicence(lc);
			}
			lc.setLicenceStartDate(TrwDate.parse(licenceStartDate));
			lc.setLicenceEndDate(TrwDate.parse(licenceEndDate));
			bctsAlert.setReminderDate(TrwDate.parse(reminderDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		caseService.save(bctsAlert);
		
		return "redirect:/cases/lvList";
	}
	
	@RequestMapping(value="/update/{caseId}", method=RequestMethod.POST, params="addComment")
	public String addComment(CaseComment comment, @PathVariable int caseId, ModelMap model, Principal principal){
		User user = userService.findByUserId(principal.getName(), false);
		BCTSAlert bctsAlert = caseService.getCaseDetailById(caseId, user.getCompany(), false);
		comment.setBctsAlert(bctsAlert);
		comment.setUser(user);
		caseService.update(comment, false);
		return "redirect:/cases/" + caseId;
	}
		
	@RequestMapping(value="/update/{caseId}", method=RequestMethod.POST, params="complete")
	public String complete(CaseComment comment, @PathVariable int caseId, ModelMap model, Principal principal){
		User user = userService.findByUserId(principal.getName(), false);
		BCTSAlert bctsAlert = caseService.getCaseDetailById(caseId, user.getCompany(), false);
		comment.setBctsAlert(bctsAlert);
		comment.setUser(user);
		caseService.update(comment, true);
		return "redirect:/cases/" + caseId;
	}

}
