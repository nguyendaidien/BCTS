/**
 * NAME		:UserAttemptServiceImpl.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.UserAttemptDao;
import com.etrade.bcts.model.UserAttempts;


@Service("userAttemptService")
@Transactional
public class UserAttemptServiceImpl implements UserAttemptService{
	static final Logger logger = LoggerFactory.getLogger(UserAttemptServiceImpl.class);
	@Autowired
	UserAttemptDao dao;
	/* (non-Javadoc)
	 * @see com.etrade.bcts.service.UserAttemptService#updateFailAttempts(java.lang.String)
	 */
	@Override
	public void updateFailAttempts(String username) {
		dao.updateFailAttempts(username);
	}
	/* (non-Javadoc)
	 * @see com.etrade.bcts.service.UserAttemptService#resetFailAttempts(java.lang.String)
	 */
	@Override
	public void resetFailAttempts(String username) {
		dao.resetFailAttempts(username);
	}
	/* (non-Javadoc)
	 * @see com.etrade.bcts.service.UserAttemptService#getUserAttempts(java.lang.String)
	 */
	@Override
	public UserAttempts getUserAttempts(String username) {
		return dao.getUserAttempts(username);
	}
	
	
	
}
