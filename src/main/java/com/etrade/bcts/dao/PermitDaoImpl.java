package com.etrade.bcts.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.Permit;

@Repository("permitDao")
public class PermitDaoImpl extends AbstractDao<Integer, Permit> implements PermitDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Permit> search(String term) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.like("permitNo", term, MatchMode.START));
		List<Permit> permits = (List<Permit>) criteria.list();
		return permits;
	}

}
