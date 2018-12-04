package com.etrade.bcts.dao;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.Permit;
import com.etrade.bcts.model.Product;

@Repository("permitDao")
public class PermitDaoImpl extends AbstractDao<Integer, Permit> implements PermitDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Permit> search(String term) {
//		Criteria criteria = createEntityCriteria();
//		criteria.add(Restrictions.like("permitNo", term.toUpperCase(), MatchMode.START));
//		List<Permit> permits = (List<Permit>) criteria.list();
//		return permits;
		return null;
	}
	 
}
