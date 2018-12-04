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

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserProfile;



@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	

	
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByUserId(String sso, boolean withProfiles) {
		logger.info("SSO : {}", sso);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("ssoId", sso));
//		User user = (User)crit.uniqueResult();
//		if(user!=null && withProfiles){
//			Hibernate.initialize(user.getUserProfiles());
//		}
		CriteriaQuery<User> crit = createEntityCriteria();
		Root<User> root = crit.from(User.class);
//		crit.select(root).where(getQueryBuilder().keyword().);
		Predicate condition = getCriteriaBuilder().equal(root.get("ssoId"), sso);
		crit.where(condition);
		Query<User> query = getSession().createQuery(crit);
		User user = (User) query.getSingleResult();
		if(user!=null && withProfiles){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}
	public List<User> findAllUsers() {
//		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
//		List<User> users = (List<User>) criteria.list();
//		
//		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
//		// Uncomment below lines for eagerly fetching of userProfiles if you want.
//		/*
//		for(User user : users){
//			Hibernate.initialize(user.getUserProfiles());
//		}*/
//		return users;
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
		logger.info("UserDaoImpl: user:{0}",user.toString());
		persist(user);
	}
	
	

	public void deleteBySSO(String sso) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("ssoId", sso));
//		User user = (User)crit.uniqueResult();
//		delete(user);
	}

	@Override
	public void updateUserPwd(User user, String chgpwd) {
//		Query q=getSession().createQuery("update User set password=:p where ssoId=:ssoId");
//		q.setString("p",user.getPassword());
//		q.setString("ssoId",user.getSsoId());
//		q.executeUpdate();
	}
	
	
	@Override
	public void updateUserAcct(User user) {
//		Query q=getSession().createQuery("update User set accountLocked=:lockedStatus where ssoId=:ssoId");
//		q.setString("lockedStatus",user.getAccountLocked());
//		q.setString("ssoId",user.getSsoId());
//		q.executeUpdate();
	}
	 

}
