package com.etrade.bcts.dao;

import java.util.List;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;
import com.etrade.bcts.model.Company;

public interface CaseDao {
	
//	BCTSAlert getCaseDetailById(int caseId, Company uen, boolean getComments);
	
	BCTSAlert getCaseDetailById(int caseId, Object uen, boolean getComments);
	
	List<BCTSAlert> getCasesByCategory(String category, Company uen);
	
	void updateStatus(CaseComment comment);
	
	void addComment(CaseComment comment);
	
	void save(BCTSAlert bctsAlert);
	
	void update(BCTSAlert bctsAlert);
}
