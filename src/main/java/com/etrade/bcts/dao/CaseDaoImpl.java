package com.etrade.bcts.dao;

import java.util.List;

//import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public void updateStatus(CaseComment comment) {
		Query<CaseComment> q = getSession().createQuery("update BCTSAlert set status=:status, completedDate=:completedDate, completedBy=:completedBy where caseId=:caseId");
		q.setParameter("status", "C");
		q.setParameter("caseId", comment.getBctsAlert().getCaseId());
		q.setParameter("completedDate", comment.getUpdateTime());
		q.setParameter("completedBy", comment.getUser().getId());
		try {
		q.executeUpdate();
		}catch(Exception e) {
			LOG.error("Error while updating in updateStatus():",e);
		}
	}

	@Override
	public List<BCTSAlert> getCasesByCategory(String category, Company uen) {
		CriteriaQuery<BCTSAlert> crit = createEntityCriteria();
		Root<BCTSAlert> root = crit.from(BCTSAlert.class);
		crit.select(root);
		 
		Query<BCTSAlert> query = getSession().createQuery(crit);
//		crit.select(root).where(getQueryBuilder().keyword().);
//		crit.add(Restrictions.eq("category", category));
//		if(uen != null) {
//			crit.add(Restrictions.eq("uen", uen));
//		}
		return query.getResultList();
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
