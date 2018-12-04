package com.etrade.bcts.dao;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BctsJobHeader;
import com.etrade.bcts.model.Product;

@Repository("jobHeaderDao")
public class JobHeaderDaoImpl extends AbstractDao<Integer, BctsJobHeader> implements JobHeaderDao {
	static final Logger LOG = LoggerFactory.getLogger(JobHeaderDaoImpl.class);
	
	public void saveJobHeader(BctsJobHeader jobHeader) {
		LOG.info("JobHeaderDaoImpl: permit:{}",jobHeader.toString());
		persist(jobHeader);
	}
	
	@Override
	public BctsJobHeader getJobHeaderInfo(String uid, String urn) {
//		LOG.info("getJobHeaderInfo : {}", uid);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("uenId", uid));
//		crit.add(Restrictions.eq("urnSeq", urn));
//		return (BctsJobHeader)crit.uniqueResult();
		return null;
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
	
	  public void indexing()
	    {
	       try
	       {

	          FullTextSession fullTextSession = Search.getFullTextSession(getFullTextSession());
	          fullTextSession.createIndexer().startAndWait();
	       }
	       catch(Exception e)
	       {
	          LOG.error(e.getMessage());
	       }
	   }
}
