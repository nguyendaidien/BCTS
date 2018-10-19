/**
 * NAME		:UserProfileServiceImpl.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.UserProfileDao;
import com.etrade.bcts.model.UserProfile;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);
	@Autowired
	UserProfileDao dao;
	
	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	public UserProfile findByType(String type){
		return dao.findByType(type);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
	
	/**
	 * @author ajayasamanta
	 * @param type
	 * @return
	 */
	public UserProfile isRoleUnique(String type) {
		UserProfile userProfile = dao.findByRole(type);
		return userProfile;
	}
	/**
	 * @author ajayasamanta
	 */
	public boolean isRoleUnique(Integer id, String type) {
		UserProfile userProfile = isRoleUnique(type);
		logger.info("UserServiceImpl isRoleUnique: {0} Role::{1} ",userProfile,type);
		return ( userProfile == null || ((id != null) && (userProfile.getId() == id)));
	}
	
	/**
	 * @author ajayasamanta
	 * @param role
	 * @return
	 */
	public UserProfile findByRole(String role) {
		UserProfile user = dao.findByRole(role);
		return user;
	}
	/**
	 * @author ajayasamanta
	 * 
	 */
	public void persistRole(UserProfile userprofile) {
		dao.saveRole(userprofile);
	}
	
}
