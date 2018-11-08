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
	
	public UserProfile findById(int transId) {
		return dao.findById(transId);
	}

	public UserProfile findByType(String roleType){
		return dao.findByType(roleType);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
	
	/**
	 * @author ajayasamanta
	 * @param roleType
	 * @return
	 */
	public UserProfile isRoleUnique(String roleType) {
		return dao.findByRole(roleType);
	}
	/**
	 * @author ajayasamanta
	 */
	public boolean isRoleUnique(Integer transId, String roleType) {
		UserProfile userProfile = isRoleUnique(roleType);
		logger.info("UserServiceImpl isRoleUnique: {0} Role::{1} ",userProfile,roleType);
		return ( userProfile == null || ((transId != null) && (userProfile.getTransId() == transId)));
	}
	
	/**
	 * @author ajayasamanta
	 * @param role
	 * @return
	 */
	public UserProfile findByRole(String roleType) {
		return dao.findByRole(roleType);
	}
	/**
	 * @author ajayasamanta
	 * 
	 */
	public void persistRole(UserProfile userprofile) {
		dao.saveRole(userprofile);
	}
	
}
