/**
 * NAME		:BatchDaoImpl.java
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BctsRoute;



@Repository("batchDao")
public class BatchDaoImpl extends AbstractDao<Integer, BctsRoute> implements BatchDao{
	
	static final Logger LOG = LoggerFactory.getLogger(BatchDaoImpl.class);
	
	/**
	 * @author ajayasamanta
	 * Used javax.persistence.criteria.CriteriaQuery to get all record whose status is A
	 */
	public List<BctsRoute> findActiveJobs() {
		CriteriaQuery<BctsRoute> crit = createEntityCriteria();
		Root<BctsRoute> root = crit.from(BctsRoute.class);
		CriteriaBuilder cbuilder=getCriteriaBuilder();
		Predicate condition = cbuilder.equal(root.get("status"), 'A');
		crit.where(condition).select(root).distinct(true).orderBy(cbuilder.asc(root.get("routeId")));
		Query<BctsRoute> query = getSession().createQuery(crit);
		List<BctsRoute> routeList=null;
		try {
			routeList=query.getResultList();
		}catch(Exception e) {
			LOG.error("Error while findActiveJobs():",e);
		}
		return routeList;
	}
	
	/**
	 * @author ajayasamanta
	 * Used javax.persistence.criteria.CriteriaQuery to get all records
	 */
	public List<BctsRoute> findAllJobs() {
		CriteriaQuery<BctsRoute> crit = createEntityCriteria();
		Root<BctsRoute> root = crit.from(BctsRoute.class);
		CriteriaBuilder cbuilder=getCriteriaBuilder();
		crit.select(root).distinct(true).orderBy(cbuilder.asc(root.get("routeId")));
		Query<BctsRoute> query = getSession().createQuery(crit);
		List<BctsRoute> routeList=null;
		try {
			routeList=query.getResultList();
		}catch(Exception e) {
			LOG.error("Error while findAllJobs():",e);
		}
		return routeList;
	}
	
}
