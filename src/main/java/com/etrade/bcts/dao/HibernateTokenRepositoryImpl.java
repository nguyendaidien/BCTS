/**
 * NAME		:HibernateTokenRepositoryImpl.java
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
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.model.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
		implements PersistentTokenRepository {

	static final Logger LOG = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		LOG.info("Creating Token for user : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		persist(persistentLogin);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		LOG.info("Fetch Token if any for seriesId : {}", seriesId);
		try {
			CriteriaQuery<PersistentLogin> crit = createEntityCriteria();
			Root<PersistentLogin> root = crit.from(PersistentLogin.class);
			Predicate condition = getCriteriaBuilder().equal(root.get("series"), seriesId);
			crit.where(condition);
			Query<PersistentLogin> query = getSession().createQuery(crit);
			PersistentLogin persistentLogin = (PersistentLogin) query.getSingleResult();
			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());
		} catch (Exception e) {
			LOG.info("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		LOG.info("Removing Token if any for user : {}", username);
		CriteriaQuery<PersistentLogin> crit = createEntityCriteria();
		Root<PersistentLogin> root = crit.from(PersistentLogin.class);
		Predicate condition = getCriteriaBuilder().equal(root.get("username"), username);
		crit.where(condition);
		Query<PersistentLogin> query = getSession().createQuery(crit);
		PersistentLogin persistentLogin = null;
		try {
			persistentLogin = query.getSingleResult();
			delete(persistentLogin);
		} catch (Exception e) {
			LOG.error("error while geting single result findByUserId(): ", e);
		}
	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		LOG.info("Updating Token for seriesId : {}", seriesId);
		PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);
	}

}
