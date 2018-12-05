/**
 * NAME		:UserDaoImpl.java
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

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByUserId(String sso, boolean withProfiles) {
		LOG.info("findByUserId SSO : {}", sso);
		CriteriaQuery<User> crit = createEntityCriteria();
		Root<User> root = crit.from(User.class);
		Predicate condition = getCriteriaBuilder().equal(root.get("ssoId"), sso);
		crit.where(condition);
		Query<User> query = getSession().createQuery(crit);
		User user =null;
		try {
				user=query.getSingleResult();
		}catch(Exception e) {
			LOG.error("error while geting single result findByUserId(): ",e);
		}
		if(user!=null && withProfiles){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}
	public List<User> findAllUsers() {
		CriteriaQuery<User> crit = createEntityCriteria();
		Root<User> root = crit.from(User.class);
		crit.select(root);
		Query<User> query = getSession().createQuery(crit);
		List<User> users = query.getResultList();
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}
		return users;
	}

	public void save(User user) {
		LOG.info("UserDaoImpl: user:{}",user);
		persist(user);
	}
	
	

	public void deleteBySSO(String sso) {
		LOG.info("SSO : {}", sso);
		User user=findByUserId(sso, true);
		if(null!=user) {
			delete(user);
		}
	}

	@Override
	public void updateUserPwd(User user, String chgpwd) {
		Query<?> q = getSession().createQuery("update User set password=:p where ssoId=:ssoId");
		q.setParameter("p", user.getPassword());
		q.setParameter("ssoId", user.getSsoId());
		LOG.info("before update in updateUserPwd :{}",q);
		try {
		q.executeUpdate();
		}catch(Exception e) {
			LOG.error("not able to update password",e);
		}
	}
	
	
	@Override
	public void updateUserAcct(User user) {
		Query<?> q = getSession().createQuery("update User set accountLocked=:lockedStatus where ssoId=:ssoId");
		q.setParameter("lockedStatus", user.getAccountLocked());
		q.setParameter("ssoId", user.getSsoId());
		LOG.info("before update in updateUserAcct :{}",q);
		try {
		q.executeUpdate();
		}catch(Exception e) {
			LOG.error("not able to updateUserAccount",e);
		}
	}
}
