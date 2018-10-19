/**
 * NAME		:RoleToUserProfileConverter.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.etrade.bcts.model.UserProfile;
import com.etrade.bcts.service.UserProfileService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>{

	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);
	
	@Autowired
	UserProfileService userProfileService;

	/**
	 * Gets UserProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public UserProfile convert(Object element) {
		
		boolean flag=UserProfile.class.isInstance(element);
		if(flag) {
			UserProfile profile=(UserProfile)element;
			logger.info("AJAY######### Profile : {}",profile);
			logger.info("Profile : {}",profile);
			return profile;
		}else {
			Integer id = Integer.parseInt((String)element);
			UserProfile profile= userProfileService.findById(id);
			logger.info("AJAY######### Profile : {}",profile);
			logger.info("AJAY######### id : {}",id);
			return profile;
		}
		
	}
	
	
	/*public static void main(String[] args) {
        String pwd = "abc125";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(pwd));
}*/
	
}