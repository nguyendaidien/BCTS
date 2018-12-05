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

import com.etrade.bcts.model.Permit;

@Repository("permitDao")
public class PermitDaoImpl extends AbstractDao<Integer, Permit> implements PermitDao {
	static final Logger LOG = LoggerFactory.getLogger(PermitDaoImpl.class);
	@Override
	public List<Permit> search(String term) {
		CriteriaQuery<Permit> crit = createEntityCriteria();
		Root<Permit> root = crit.from(Permit.class);
		CriteriaBuilder cbuilder=getCriteriaBuilder();
		Predicate condition = cbuilder.like(root.get("permitNo"), term.toUpperCase());
		crit.where(condition).select(root).distinct(true).orderBy(cbuilder.asc(root.get("permitNo")));
		Query<Permit> query = getSession().createQuery(crit);
		List<Permit> permitList=null;
		try {
			permitList=query.getResultList();
		}catch(Exception e) {
			LOG.error("Error while getting permit list search():",e);
		}
		return permitList;
	}
	 
}
