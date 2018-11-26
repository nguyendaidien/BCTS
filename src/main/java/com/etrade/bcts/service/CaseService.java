package com.etrade.bcts.service;

import java.util.List;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;
import com.etrade.bcts.model.Company;
import com.etrade.bcts.model.User;

public interface CaseService {
	BCTSAlert getCaseDetailById(int caseId, Object uen, boolean getComments);
	List<BCTSAlert> getCasesByType(String type, Company uen);
	void update(CaseComment comment, boolean completed);
	void save(BCTSAlert bctsAlert);
	void update(BCTSAlert bctsAlert);
}
