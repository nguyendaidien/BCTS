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

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{
	static final Logger logger = LoggerFactory.getLogger(UserProfileDaoImpl.class);
	public UserProfile findById(int transId) {
		return getByKey(transId);
	}

	public UserProfile findByType(String roleType) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("roleType", roleType));
//		return (UserProfile) crit.uniqueResult();
		CriteriaQuery<UserProfile> crit = createEntityCriteria();
		Root<UserProfile> root = crit.from(UserProfile.class);
		Predicate condition = getCriteriaBuilder().equal(root.get("roleType"), roleType);
		crit.where(condition);
		Query<UserProfile> query = getSession().createQuery(crit);
		return (UserProfile) query.getSingleResult();		
	}
	
	public List<UserProfile> findAll(){
//		Criteria crit = createEntityCriteria();
//		crit.addOrder(Order.asc("roleType"));
//		return (List<UserProfile>)crit.list();
		CriteriaQuery<UserProfile> crit = createEntityCriteria();
		Root<UserProfile> root = crit.from(UserProfile.class);
		crit.select(root);
		Query<UserProfile> query = getSession().createQuery(crit);
		return (List<UserProfile>) query.getResultList();		
	}
	
	
	/**
	 * @author ajayasamanta
	 */
	public UserProfile findByRole(String roleType) {
		CriteriaQuery<UserProfile> crit = createEntityCriteria();
		Root<UserProfile> root = crit.from(UserProfile.class);
		Predicate condition = getCriteriaBuilder().equal(root.get("roleType"), roleType);
		crit.where(condition);
		Query<UserProfile> query = getSession().createQuery(crit);
		return (UserProfile) query.getSingleResult();		
	}
	
	
	/**
	 * @author ajayasamanta
	 */
	public void saveRole(UserProfile userProfile) {
		persistRole(userProfile);
	}
	
}
