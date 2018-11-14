package com.etrade.bcts.service;

import java.util.List;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;

public interface CaseService {
	BCTSAlert getCaseDetailById(int caseId);
	List<BCTSAlert> getCasesByType(String type, String uen);
	void update(CaseComment comment, boolean completed);
}
