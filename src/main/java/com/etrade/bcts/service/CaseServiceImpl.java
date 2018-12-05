package com.etrade.bcts.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.CaseDao;
import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.CaseComment;
import com.etrade.bcts.model.CaseDetail;
import com.etrade.bcts.model.Company;
import com.etrade.bcts.model.LicenceValidity;
import com.etrade.bcts.util.BctsConstants;

@Service(value="caseService")
@Transactional
public class CaseServiceImpl implements CaseService {

	@Autowired
	CaseDao caseDao;
	
	@Override
	public BCTSAlert getCaseDetailById(int caseId, Object uen, boolean getComments) {
		return caseDao.getCaseDetailById(caseId, uen, getComments);
	}

	@Override
	public void update(CaseComment comment, boolean completed) {
		Date time = new Date();
		comment.setUpdateTime(time);
		caseDao.addComment(comment);
		if(completed) {
			caseDao.updateStatus(comment);
		}
	}

	@Override
	public List<BCTSAlert> getCasesByCategory(String category, Company uen) {
		return caseDao.getCasesByCategory(category, uen);
	}

	@Override
	public void save(BCTSAlert b) {
		BCTSAlert bctsAlert = new BCTSAlert(b);
		caseDao.save(bctsAlert);
		
		if(b.getCategory().equals(BctsConstants.CASE_CATEGORY_LICENCE_VALIDITY)) {
			LicenceValidity lv = b.getLicence();
			lv.setCaseId(bctsAlert.getCaseId());
			bctsAlert.setLicence(lv);
		} else { 
			CaseDetail caseDetail = b.getCaseDetail();
			/**In case of job permit reader case details will always be null
			 * TODO need to check with Dien
			 * */
			if(null!=caseDetail) {
			caseDetail.setCaseId(bctsAlert.getCaseId());
			bctsAlert.setCaseDetail(caseDetail);
			}
		}
	}

	@Override
	public void update(BCTSAlert bctsAlert) {
		BCTSAlert b = caseDao.getCaseDetailById(bctsAlert.getCaseId() , null , false);
		b.setAlertContent(bctsAlert.getAlertContent());
		b.setAlertEmails(bctsAlert.getAlertEmails());
		b.setReminderDate(bctsAlert.getReminderDate());
		b.setJobNo(bctsAlert.getJobNo());
		b.setPermitNo(bctsAlert.getPermitNo());
		b.setToAlertCompany(bctsAlert.getToAlertCompany());
		if(b.getCategory().equals(BctsConstants.CASE_CATEGORY_LICENCE_VALIDITY)) {
			LicenceValidity lv = b.getLicence();
			lv.setLicenceStartDate(bctsAlert.getLicence().getLicenceStartDate());
			lv.setLicenceEndDate(bctsAlert.getLicence().getLicenceEndDate());
			lv.setLicenceOwner(bctsAlert.getLicence().getLicenceOwner());
			lv.setControlAgency(bctsAlert.getLicence().getControlAgency());
		}
	}
}
