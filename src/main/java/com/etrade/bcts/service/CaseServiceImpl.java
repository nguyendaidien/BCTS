package com.etrade.bcts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.CaseDao;
import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;

@Service(value="caseService")
@Transactional
public class CaseServiceImpl implements CaseService {

	@Autowired
	CaseDao caseDao;
	
	@Override
	public BCTSAlert getCaseDetailById(int caseId) {
		return caseDao.getCaseDetailById(caseId);
	}

	@Override
	public void update(CaseComment comment, boolean completed) {
		caseDao.addComment(comment);
		if(completed) {
			caseDao.updateStatus(comment.getBctsAlert().getCaseId());
		}
	}

	@Override
	public List<BCTSAlert> getCasesByType(String type, String uen) {
		return caseDao.getCasesByType(type, uen);
	}

}
