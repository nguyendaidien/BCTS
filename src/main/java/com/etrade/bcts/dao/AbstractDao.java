/**
 * NAME		:AbstractDao.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void persistRole(T entity) {
		getSession().persist(entity);
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected CriteriaQuery<T> createEntityCriteria(){
		return getSession().getCriteriaBuilder().createQuery(persistentClass);
	}
	
	protected CriteriaBuilder getCriteriaBuilder(){
		return getSession().getCriteriaBuilder();
	}

	public FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {
        return getFullTextSession().createFullTextQuery(luceneQuery, persistentClass);
    }

	public QueryBuilder getQueryBuilder() { 
    	return getFullTextSession().getSearchFactory()
 	           .buildQueryBuilder().forEntity(persistentClass).get();
    }
    
	public FullTextSession getFullTextSession() {
    	return Search.getFullTextSession(getSession());
    }
    
}
