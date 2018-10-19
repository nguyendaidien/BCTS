/**
 * NAME		:UserProfileDaoImpl.java
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

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{
	static final Logger logger = LoggerFactory.getLogger(UserProfileDaoImpl.class);
	public UserProfile findById(int id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserProfile) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return (List<UserProfile>)crit.list();
	}
	
	
	/**
	 * @author ajayasamanta
	 */
	public UserProfile findByRole(String type) {
		logger.info("role : {}", type);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		UserProfile userProfile = (UserProfile)crit.uniqueResult();
		return userProfile;
	}
	
	
	/**
	 * @author ajayasamanta
	 */
	public void saveRole(UserProfile userProfile) {
		persistRole(userProfile);
	}
	
}
