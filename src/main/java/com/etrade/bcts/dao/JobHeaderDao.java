package com.etrade.bcts.dao;

import java.util.List;

import com.etrade.bcts.model.BctsJobHeader;

public interface JobHeaderDao {
	void saveJobHeader(BctsJobHeader jobHeader);
	BctsJobHeader getJobHeaderInfo(String uid,String urn);
	
	List<BctsJobHeader> searchPermitByKeywordQuery(String text);
	void indexing();
}
