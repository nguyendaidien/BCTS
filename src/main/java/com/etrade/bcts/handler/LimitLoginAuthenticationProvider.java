package com.etrade.bcts.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserAttempts;
import com.etrade.bcts.service.UserAttemptService;
import com.etrade.bcts.service.UserService;
import com.etrade.bcts.util.BctsConstants;

@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {
	static final Logger logger = LoggerFactory.getLogger(LimitLoginAuthenticationProvider.class);
	
	@Autowired
	UserAttemptService userAttemptService;
	@Autowired
	UserService userService;
	
	@Autowired
	@Qualifier("userDetailsService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication)throws AuthenticationException {

	  try {
		  super.setPasswordEncoder(passwordEncoder());//added 31102018
		  logger.info("AJAY######### before authenticate : {}",authentication);
		  Authentication auth = super.authenticate(authentication);
		 logger.info("AJAY######### after authenticate : {}",authentication);
		//if reach here, means login success, else an exception will be thrown
		//reset the user_attempts
		 userAttemptService.resetFailAttempts(authentication.getName());
		 User user=new User();
		 user.setSsoId(authentication.getName());
		 user.setAccountLocked(BctsConstants.ACCTNOTLOCKED);
		 userService.updateUserAcct(user);
		return auth;
	  } catch (BadCredentialsException e) {	
		//invalid login, update to user_attempts
		  userAttemptService.updateFailAttempts(authentication.getName());
		throw e;
	  } catch (LockedException e){
			
		//this user is locked!
		String error = "";
		UserAttempts userAttempts = 
				userAttemptService.getUserAttempts(authentication.getName());
		
               if(userAttempts!=null){
			Date lastAttempts = userAttempts.getLastModified();
			error = "User account is locked! <br><br>Username : " 
                           + authentication.getName() + "<br>Last Attempts : " + lastAttempts;
		}else{
			error = e.getMessage();
		}
	  throw new LockedException(error);
	}

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
