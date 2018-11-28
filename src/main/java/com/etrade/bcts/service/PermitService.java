package com.etrade.bcts.service;

import java.util.List;

import com.etrade.bcts.model.BctsJobHeader;
import com.etrade.bcts.model.Permit;

public interface PermitService { 
	List<Permit> search(String term);
	void saveJobHeader(BctsJobHeader jobHeader);
	BctsJobHeader getJobHeaderInfo(String uid,String urn);
}
