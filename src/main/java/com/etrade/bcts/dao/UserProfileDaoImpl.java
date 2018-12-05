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

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.UserProfile;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {
	static final Logger LOG = LoggerFactory.getLogger(UserProfileDaoImpl.class);

	public UserProfile findById(int transId) {
		return getByKey(transId);
	}

	public UserProfile findByType(String roleType) {
		CriteriaQuery<UserProfile> crit = createEntityCriteria();
		Root<UserProfile> root = crit.from(UserProfile.class);
		Predicate condition = getCriteriaBuilder().equal(root.get("roleType"), roleType);
		crit.where(condition);
		Query<UserProfile> query = getSession().createQuery(crit);
		UserProfile userProfile = null;
		try {
			userProfile = query.getSingleResult();
		} catch (Exception e) {
			LOG.error("Error while getting findByType():", e);
		}
		return userProfile;
	}

	public List<UserProfile> findAll() {
		CriteriaQuery<UserProfile> crit = createEntityCriteria();
		Root<UserProfile> root = crit.from(UserProfile.class);
		crit.select(root);
		Query<UserProfile> query = getSession().createQuery(crit);
		List<UserProfile> userProfLst = null;
		try {
			userProfLst = query.getResultList();
		} catch (Exception e) {
			LOG.error("Error while findAll():", e);
		}
		return userProfLst;
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
		UserProfile userProf = null;
		try {
			userProf = query.getSingleResult();
		} catch (Exception e) {
			LOG.error("Error while findByRole():", e);
		}
		return userProf;
	}

	/**
	 * @author ajayasamanta
	 */
	public void saveRole(UserProfile userProfile) {
		persistRole(userProfile);
	}

}
