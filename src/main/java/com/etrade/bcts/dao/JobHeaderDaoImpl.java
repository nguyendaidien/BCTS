package com.etrade.bcts.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BctsJobHeader;

@Repository("jobHeaderDao")
public class JobHeaderDaoImpl extends AbstractDao<Integer, BctsJobHeader> implements JobHeaderDao {
	static final Logger LOG = LoggerFactory.getLogger(JobHeaderDaoImpl.class);
	
	public void saveJobHeader(BctsJobHeader jobHeader) {
		LOG.info("JobHeaderDaoImpl: permit:{}",jobHeader);
		persist(jobHeader);
	}
	
	@Override
	public BctsJobHeader getJobHeaderInfo(String uid, String urn) {
		LOG.info("getJobHeaderInfo : {}", uid);
		CriteriaQuery<BctsJobHeader> crit = createEntityCriteria();
		Root<BctsJobHeader> root = crit.from(BctsJobHeader.class);
		CriteriaBuilder cBuilder= getCriteriaBuilder();
		List <Predicate> prdCndLst = new ArrayList<>();
		if(uid != null){
			prdCndLst.add(cBuilder.equal(root.get("uenId"), uid));
	    }
		if(urn != null){
	         prdCndLst.add(cBuilder.equal(root.get("urnSeq"), urn));
	    }
		if(!prdCndLst.isEmpty()){
	        Predicate[] prdAry = new Predicate[prdCndLst.size()];
	        prdCndLst.toArray(prdAry);
	        crit.where(prdAry);    
	    }
		crit.distinct(true);
		org.hibernate.query.Query<BctsJobHeader> query = getSession().createQuery(crit);
		BctsJobHeader jobHeader=null;
		try {
			jobHeader=query.getSingleResult();
		}catch(Exception e) {
			LOG.error("Error while get single result in getJobHeaderInfo():",e);
		}
		return jobHeader;
	}

	public List<BctsJobHeader> searchPermitByKeywordQuery(String text) {
        Query keywordQuery = getQueryBuilder()
            .keyword()
            .onFields("crUeiNo", "permits.permitNumber")
            .matching(text)
            .createQuery();
        @SuppressWarnings("unchecked")
		List<BctsJobHeader> results = getJpaQuery(keywordQuery).getResultList();
        return results;
	}
	
	  public void indexing() {
	       try{
	          FullTextSession fullTextSession = Search.getFullTextSession(getFullTextSession());
	          fullTextSession.createIndexer().startAndWait();
	       }catch(Exception e){
	          LOG.error(e.getMessage());
	       }
	   }
}
