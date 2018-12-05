package com.etrade.bcts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BCTS_CASE_DEAIL")
public class CaseDetail {
	@Id
	@Column(name="CASE_DEAIL_ID")
	private Integer caseId;
	
	@Column(name="CASE_NAME")
	private String caseName;
	
	
	@Column(name="CASE_TYPE")
	private String caseType;
	
	@Column(name="OFFICER_PHONE")
	private String officerPhone;

	@Column(name="OFFICER_EMAIL")
	private String officerEmail;
	
	@Column(name="DESCRIPTION")
	private String description;


	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getOfficerPhone() {
		return officerPhone;
	}

	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}

	public String getOfficerEmail() {
		return officerEmail;
	}

	public void setOfficerEmail(String officerEmail) {
		this.officerEmail = officerEmail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
