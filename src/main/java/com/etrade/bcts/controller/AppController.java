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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.etrade.bcts.model.ChangePwdToken;
import com.etrade.bcts.model.UploadFile;
import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserProfile;
import com.etrade.bcts.service.UserProfileService;
import com.etrade.bcts.service.UserService;
import com.etrade.bcts.util.FileValidator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.etrade.bcts.model.UploadFileBucket;
import com.etrade.bcts.model.UserDocument;
import com.etrade.bcts.service.UserDocumentService;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
@PropertySource(value = { "classpath:messages.properties" })
public class AppController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserDocumentService userDocumentService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	private Environment environment;	
	
	/* @RequestMapping(value = { "/"}, method = RequestMethod.GET)
	    public String homePage(ModelMap model) {
	        return "home";
	    }*/
	 
	    @RequestMapping(value = { "/products"}, method = RequestMethod.GET)
	    public String productsPage(ModelMap model) {
	        return "products";
	    }
	 
	    @RequestMapping(value = { "/contactus"}, method = RequestMethod.GET)
	    public String contactUsPage(ModelMap model) {
	        return "contactus";
	    }
	
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());//we can create any method in side Controlloer and can call using model object
		return "userslist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		Set<UserProfile> userProfileSet = new HashSet<UserProfile>(userProfileService.findAll());
		System.out.println("userProfileSet:"+userProfileSet);
		user.setUserProfiles(userProfileSet);
		System.out.println("user.toString():"+user.toString());
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	
	@RequestMapping(value = { "/changepwd" }, method = RequestMethod.GET)
	public String changePwd(ModelMap model) {
		ChangePwdToken cpw = new ChangePwdToken();
		model.addAttribute("cpw", cpw);
		return "changePassword";
	}
	@RequestMapping(value = { "/changepwd" }, method = RequestMethod.POST)
	public String changePwd(@Valid@ModelAttribute("cpw") ChangePwdToken cpw,BindingResult result,ModelMap model,@RequestParam("userid")String ssoid){
		
		
		if(result.hasErrors()){
			return "changePassword";
		}
		
		User user=userService.findByUserId(ssoid);
		String newPwd=cpw.getPasswordConf();
		userService.updateUserPwd(user,newPwd);
		
		model.addAttribute("success","Your password has been modified successfully");
		
		return "registrationsuccess";
	}
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserIdUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findByUserId(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}
	

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/list";  
	    }
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
	
	
	/**
	 * This method will provide the medium to add a new userprofile.
	 */
	@RequestMapping(value = { "/newrole" }, method = RequestMethod.GET)
	public String newRole(ModelMap model) {
		UserProfile userprofile = new UserProfile();
		userprofile.setType("");
		model.addAttribute("userprofile", userprofile);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "role";
	}
	
	
	/**
	 * @author ajayasamanta
	 * This method will be called on form submission, handling POST request for
	 * saving role in database. It also validates the userprofile input
	 */
	
	@RequestMapping(value = { "/newrole" }, method = RequestMethod.POST)
	public String saveUserProfile(@Valid UserProfile userprofile, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			return "role";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userProfileService.isRoleUnique(userprofile.getId(), userprofile.getType())){
			FieldError ssoError =new FieldError("userprofile","type",messageSource.getMessage("non.unique.profId", new String[]{userprofile.getType()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "role";
		}
		
		userProfileService.persistRole(userprofile);
		model.addAttribute("success", "UserProfile " + userprofile.getId() + " "+ userprofile.getType() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		initializeProfiles();
		return "rolesuccess";
	}
}