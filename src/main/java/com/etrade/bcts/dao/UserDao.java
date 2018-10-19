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
import com.etrade.bcts.model.UserAttempts;


public interface UserDao {

	User findById(int id);
	
	User findByUserId(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();
	
	void updateUserPwd(User user,String chgpwd);
	
	//User Attempt
	void updateFailAttempts(String username);
	void resetFailAttempts(String username);
	UserAttempts getUserAttempts(String username);
	
}

