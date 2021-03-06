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

import java.util.Date;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserAttempts;
import com.etrade.bcts.util.BctsConstants;



@Repository("userAttemptDao")
public class UserAttemptDaoImpl extends AbstractDao<Integer, UserAttempts>implements UserAttemptDao{
	static final Logger LOG = LoggerFactory.getLogger(UserAttemptDaoImpl.class);
	private static final int MAX_ATTEMPTS = 3;
	@Autowired
	UserDao userDao;

	@Override
	public UserAttempts getUserAttempts(String userName) {
		CriteriaQuery<UserAttempts> crit = createEntityCriteria();
		Root<UserAttempts> root = crit.from(UserAttempts.class);
		Predicate condition = getCriteriaBuilder().equal(root.get("username"), userName);
		crit.where(condition);
		Query<UserAttempts> query = getSession().createQuery(crit);
		UserAttempts attmpts=null;
		try {
			attmpts=query.getSingleResult();
		}catch(Exception e) {
			LOG.error("error while getting user attempts()",e);
		}
		return attmpts;
	}
	

	public void resetFailAttempts(String userName) {
		UserAttempts userAttempts = getUserAttempts(userName);
		if(null!=userAttempts) {
			delete(userAttempts);
		}
	}
	
	@Override
	public void updateFailAttempts(String userName) {
		UserAttempts userAttempts = getUserAttempts(userName);
		User user = userDao.findByUserId(userName, false);
		if (null == userAttempts) {
			if (null != user) {
				userAttempts = new UserAttempts();
				userAttempts.setAttempts(1);
				Date dt=new Date();
				userAttempts.setLastModified(dt);
				userAttempts.setUsername(userName);
				LOG.info("before persisting in user attempt :{}",userAttempts);
				persist(userAttempts);
				LOG.info("after persisting in user attempt :{}",userAttempts);
			}
		} else {
			if (null != user) {
				Query<?> q = getSession().createQuery(
						"update UserAttempts SET attempts =:attmpt,lastModified=:modif WHERE username = :usr");
				q.setParameter("attmpt", userAttempts.getAttempts() + 1);
				Date dt=new Date();
				q.setParameter("modif", dt);
				q.setParameter("usr", userName);
				LOG.info("before update in user attempt :{}",q);
				try {
					q.executeUpdate();
					LOG.info("after update in user attempt :{}",q);
					if (userAttempts.getAttempts() + 1 >= MAX_ATTEMPTS) {
						user.setAccountLocked(BctsConstants.ACCTLOCKED);
						userDao.updateUserAcct(user);
						throw new LockedException("User Account is locked!");
					}
				}catch(Exception e) {
					LOG.error("error while updating failed attempts updateFailAttempts():",e);
				}
			}
		}
	}
	
}
