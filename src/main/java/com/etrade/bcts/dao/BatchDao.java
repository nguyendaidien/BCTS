/**
 * NAME		:BatchDao.java
 * DATE		:15/11/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.dao;

import java.util.List;

import com.etrade.bcts.model.BctsRoute;

 
public interface BatchDao {

	List<BctsRoute> findAllJobs();
	
	List<BctsRoute> findActiveJobs();
	
}

