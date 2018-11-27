package com.etrade.bcts.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;
import com.etrade.bcts.model.Company;

@Repository("caseDao")
public class CaseDaoImpl extends AbstractDao<Integer, BCTSAlert> implements CaseDao {
	private static final Logger LOG = LoggerFactory.getLogger(CaseDaoImpl.class);
	@Override
	public BCTSAlert getCaseDetailById(int caseId, Object uen, boolean getComments) {		
		BCTSAlert bctsAlert = getByKey(caseId);
		if(bctsAlert!=null){
			if((uen instanceof Company && !bctsAlert.getUen().equals(uen)) || 
					(uen instanceof String && !bctsAlert.getUen().getUeiNo().equals(uen))) {
				LOG.info("User's UEN does NOT match the case's UEN");
				return null;
			}
			if(getComments) {
				Hibernate.initialize(bctsAlert.getComments());
			}
		}
		return bctsAlert;
	}
	
	@Override
	public void addComment(CaseComment comment) {
		getSession().persist(comment);		
	}

	@Override
	public void updateStatus(CaseComment comment) {
		Query q = getSession().createQuery("update BCTSAlert set status=:status, completedDate=:completedDate, completedBy=:completedBy where caseId=:caseId");
		q.setString("status", "C");
		q.setInteger("caseId", comment.getBctsAlert().getCaseId());
		q.setDate("completedDate", comment.getUpdateTime());
		q.setInteger("completedBy", comment.getUser().getId());
		q.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCTSAlert> getCasesByType(String type, Company uen) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("category", type));
		if(uen != null) {
			crit.add(Restrictions.eq("uen", uen));
		}
		return (List<BCTSAlert>)crit.list();
	}

	@Override
	public void save(BCTSAlert bctsAlert) {
		persist(bctsAlert);
	}

	@Override
	public void update(BCTSAlert bctsAlert) {
		super.update(bctsAlert);
	}
}
