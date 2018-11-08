/**
 * NAME		:UserAttemptDao.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.dao;

import com.etrade.bcts.model.UserAttempts;


public interface UserAttemptDao {
		void updateFailAttempts(String username);
		void resetFailAttempts(String username);
		UserAttempts getUserAttempts(String username);
}
