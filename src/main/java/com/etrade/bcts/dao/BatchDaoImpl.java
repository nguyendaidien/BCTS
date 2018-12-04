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

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BctsRoute;



@Repository("batchDao")
public class BatchDaoImpl extends AbstractDao<Integer, BctsRoute> implements BatchDao{
	
	static final Logger LOG = LoggerFactory.getLogger(BatchDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	public List<BctsRoute> findActiveJobs() {
//		Criteria criteria = createEntityCriteria().addOrder(Order.asc("routeId"));
//		criteria.add(Restrictions.eq("status", 'A'));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
//		return (List<BctsRoute>) criteria.list();
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BctsRoute> findAllJobs() {
//		Criteria criteria = createEntityCriteria().addOrder(Order.asc("routeId"));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
//		return (List<BctsRoute>) criteria.list();
		return null;
	}
	
	
	

}
