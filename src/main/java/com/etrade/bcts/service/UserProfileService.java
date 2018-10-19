/**
 * NAME		:UserProfileService.java
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

import com.etrade.bcts.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	/*Ajay*/
	boolean isRoleUnique(Integer id, String role);
	void persistRole(UserProfile userprofile);
	UserProfile findByRole(String role);
}
