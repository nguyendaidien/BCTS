package com.etrade.bcts.dao;

import java.util.List;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;

public interface CaseDao {
	BCTSAlert getCaseDetailById(int caseId);
	
	List<BCTSAlert> getCasesByType(String type, String uen);
	
	void updateStatus(int caseId);
	
	void addComment(CaseComment comment);
}
