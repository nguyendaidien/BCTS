/**
 * NAME		:AppController.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.etrade.bcts.model.UploadFileBucket;
import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserDocument;
import com.etrade.bcts.service.UserDocumentService;
import com.etrade.bcts.service.UserService;
import com.etrade.bcts.util.FileValidator;



@Controller
@RequestMapping("docs")
@PropertySource(value = { "classpath:messages.properties" })
public class DocumentController {

	@Autowired
	UserService userService;	
	
	@Autowired
	UserDocumentService userDocumentService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	FileValidator fileValidator;
	
	@InitBinder("uploadFileBucket")
	protected void initBinder(WebDataBinder binder) {
	   binder.setValidator(fileValidator);
	}
	
	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.GET)
	public String addDocuments(@PathVariable String userId, ModelMap model) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);

		UploadFileBucket fileModel = new UploadFileBucket();
		model.addAttribute("uploadFileBucket", fileModel);

		List<UserDocument> documents = userDocumentService.findAllByUserId(userId);
		model.addAttribute("documents", documents);
		
		return "managedocuments";
	}

	@RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int userId, @PathVariable int docId, HttpServletResponse response) throws IOException {
		UserDocument document = userDocumentService.findById(docId);
		File file = new File(document.getUrl());
//		response.setContentType(document.getType());
//        response.setContentLength(file.get);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
 
 		return "redirect:/add-document-"+userId;
	}

	@RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
		userDocumentService.deleteById(docId);
		return "redirect:/add-document-"+userId;
	}

	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid UploadFileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable String userId) throws IOException{
		
		if (result.hasErrors()) {
			System.out.println("validation errors");
			User user = userService.findByUserId(userId);
			model.addAttribute("user", user);

			List<UserDocument> documents = userDocumentService.findAllByUserId(userId);
			model.addAttribute("documents", documents);
			
			return "managedocuments";
		} else {
			
			System.out.println("Fetching file");
			User user = userService.findByUserId(userId);
			model.addAttribute("user", user);
			userDocumentService.uploadFile(fileBucket, user);
			return "redirect:/docs/add-document-"+userId;
		}
	}
	
	@RequestMapping(value = { "/add-document-err-{errCode}" }, method = RequestMethod.GET)
	public String addDocumentsError(@PathVariable String errCode, ModelMap model) {
		User user = userService.findByUserId("sam");
		model.addAttribute("user", user);

		UploadFileBucket fileModel = new UploadFileBucket();
		model.addAttribute("uploadFileBucket", fileModel);
		model.addAttribute("error", environment.getRequiredProperty("error." + errCode));
		List<UserDocument> documents = userDocumentService.findAllByUserId("sam");
		model.addAttribute("documents", documents);
		
		return "managedocuments";
	}
}