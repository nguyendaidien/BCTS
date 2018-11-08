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

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
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
	static final Logger logger = LoggerFactory.getLogger(UserAttemptDaoImpl.class);
	private static final int MAX_ATTEMPTS = 3;
	@Autowired
	UserDao userDao;

	@Override
	public UserAttempts getUserAttempts(String userName) {
		logger.info("getUserAttempts() username : {}", userName);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", userName));
		UserAttempts userAttempts = (UserAttempts)crit.uniqueResult();
		return userAttempts;
	}
	
	
	/*@Override
	public void resetFailAttempts(String userName) {
		Query q=getSession().createQuery("update UserAttempts SET attempts =:attmpt, lastmodified =:modif WHERE username = :usr");
		q.setString("attmpt","0");
		Date dt=new Date();
		q.setDate("modif",dt);
		q.setString("usr",userName);
		q.executeUpdate();
		
	}*/
	
	
	public void resetFailAttempts(String userName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", userName));
		UserAttempts userAttempts = (UserAttempts)crit.uniqueResult();
		if(null!=userAttempts) {
		delete(userAttempts);
		}
	}
	
	@Override
	public void updateFailAttempts(String userName) throws LockedException{
		UserAttempts userAttempts = getUserAttempts(userName);
		User user = userDao.findByUserId(userName);
		if (null == userAttempts) {
			if (null != user) {
				userAttempts = new UserAttempts();
				userAttempts.setAttempts(1);
				Date dt=new Date();
				userAttempts.setLastModified(dt);
				userAttempts.setUsername(userName);
				logger.info("before persisting in user attempt :{}",userAttempts);
				persist(userAttempts);
				logger.info("after persisting in user attempt :{}",userAttempts);
			}
		} else {
			if (null != user) {
				Query q = getSession().createQuery(
						"update UserAttempts SET attempts =:attmpt,lastModified=:modif WHERE username = :usr");
				q.setInteger("attmpt", userAttempts.getAttempts() + 1);
				Date dt=new Date();
				q.setDate("modif", dt);
				q.setString("usr", userName);
				logger.info("before update in user attempt :{}",q);
				q.executeUpdate();
				
				logger.info("after update in user attempt :{}",q);
				if (userAttempts.getAttempts() + 1 >= MAX_ATTEMPTS) {
					user.setAccountLocked(BctsConstants.ACCTLOCKED);
					userDao.updateUserAcct(user);
					throw new LockedException("User Account is locked!");
				}
			}
		}
	}
	
}
