/**
 * NAME		:UserDao.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.dao;

import java.util.List;

import com.etrade.bcts.model.User;


public interface UserDao {

	User findById(int id);
	
	User findByUserId(String sso, boolean withProfiles);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();
	
	void updateUserPwd(User user,String chgpwd);
	
	void updateUserAcct(User user);
	
}

