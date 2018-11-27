package com.etrade.bcts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BCTS_LICENCE_VALIDITY")
public class LicenceValidity {
	@Id
	@Column(name="CASE_ID")
	private Integer caseId;
	
	@Column(name="LICENCE_NO")
	private String licenceNo;
	
	@Column(name="LICENCE_START_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date licenceStartDate;
	
	@Column(name="LICENCE_END_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date licenceEndDate;
	
	@Column(name="LICENCE_OWNER")
	private String licenceOwner;
	
	@Column(name="CONTROL_AGENCY")
	private String controlAgency;
	
	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public Date getLicenceStartDate() {
		return licenceStartDate;
	}

	public void setLicenceStartDate(Date licenceStartDate) {
		this.licenceStartDate = licenceStartDate;
	}

	public Date getLicenceEndDate() {
		return licenceEndDate;
	}

	public void setLicenceEndDate(Date licenceEndDate) {
		this.licenceEndDate = licenceEndDate;
	}

	public String getLicenceOwner() {
		return licenceOwner;
	}

	public void setLicenceOwner(String licenceOwner) {
		this.licenceOwner = licenceOwner;
	}

	public String getControlAgency() {
		return controlAgency;
	}

	public void setControlAgency(String controlAgency) {
		this.controlAgency = controlAgency;
	}
	
	
}

