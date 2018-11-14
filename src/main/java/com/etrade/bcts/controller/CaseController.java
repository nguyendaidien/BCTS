package com.etrade.bcts.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;
import com.etrade.bcts.model.User;
import com.etrade.bcts.service.CaseService;
import com.etrade.bcts.service.UserService;
import com.etrade.bcts.util.BctsConstants;

@Controller
@RequestMapping(value="cases")
public class CaseController {
	private static final Logger LOG = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	CaseService caseService;
	
	@Autowired 
	UserService userService;
	
	@RequestMapping(value="/{caseId}", method=RequestMethod.GET)
	public String showCaseDetails(@PathVariable int caseId, ModelMap model) {
		BCTSAlert caseDetail = caseService.getCaseDetailById(caseId);
		model.put("caseComment", new CaseComment());
		model.put("caseDetail", caseDetail);
		model.put("comments", caseDetail.getComments());
		return "casedetail";
	}
	
	@RequestMapping(value="/lvList", method=RequestMethod.GET)
	public String getLicenceValidityList(ModelMap model) {
		List<BCTSAlert> lvList = caseService.getCasesByType(BctsConstants.CASETYPE_LV, "UEN1");
		model.put("lvList", lvList);
		return "licenceValidityList";
	}
	
	@RequestMapping(value="/update/{caseId}", method=RequestMethod.POST, params="addComment")
	public String addComment(CaseComment comment, @PathVariable int caseId, ModelMap model, Principal principal){
		User user = userService.findByUserId(principal.getName(), false);
		BCTSAlert bctsAlert = caseService.getCaseDetailById(caseId);
		comment.setBctsAlert(bctsAlert);
		comment.setUser(user);
		caseService.update(comment, false);
		return "redirect:/cases/" + caseId;
	}
		
	@RequestMapping(value="/update/{caseId}", method=RequestMethod.POST, params="complete")
	public String complete(CaseComment comment, @PathVariable int caseId, ModelMap model, Principal principal){
		User user = userService.findByUserId(principal.getName(), false);
		BCTSAlert bctsAlert = caseService.getCaseDetailById(caseId);
		comment.setBctsAlert(bctsAlert);
		comment.setUser(user);
		caseService.update(comment, true);
		return "redirect:/cases/" + caseId;
	}
}
