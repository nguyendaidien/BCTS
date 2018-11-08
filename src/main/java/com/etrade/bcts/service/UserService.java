/**
 * NAME		:UserService.java
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

import com.etrade.bcts.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findByUserId(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	void updateUserPwd(User user,String chgpwd);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserIdUnique(Integer id, String sso);
	
	void updateUserAcct(User user);
	

}