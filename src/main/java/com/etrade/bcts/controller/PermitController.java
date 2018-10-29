package com.etrade.bcts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etrade.bcts.model.Permit;
import com.etrade.bcts.service.PermitService;

@Controller
@RequestMapping("/permit")
public class PermitController {
	@Autowired
	PermitService permitService;
	
	@RequestMapping(value = "search", method = RequestMethod.GET)	
	public @ResponseBody List<Permit> search(HttpServletRequest request) {
		return permitService.search(request.getParameter("term"));
	}
}

