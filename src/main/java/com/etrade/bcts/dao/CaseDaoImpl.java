package com.etrade.bcts.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;

@Repository("caseDao")
public class CaseDaoImpl extends AbstractDao<Integer, BCTSAlert> implements CaseDao {

	@Override
	public BCTSAlert getCaseDetailById(int caseId) {		
		BCTSAlert bctsAlert = getByKey(caseId);
		if(bctsAlert!=null){
			Hibernate.initialize(bctsAlert.getComments());
		}
		return bctsAlert;
	}

	@Override
	public void addComment(CaseComment comment) {
		getSession().persist(comment);		
	}

	@Override
	public void updateStatus(int caseId) {
		Query q = getSession().createQuery("update BCTSAlert set status=:status where caseId=:caseId");
		q.setString("status", "C");
		q.setInteger("caseId", caseId);
		q.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCTSAlert> getCasesByType(String type, String uen) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("caseType", type));
		crit.add(Restrictions.eq("userId", uen));
		return (List<BCTSAlert>)crit.list();
	}

}
