package com.etrade.bcts.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BCTS_ALERT")
public class BCTSAlert {  	
	@Id
	@GenericGenerator(name="ALERT_SEQ", strategy="increment")
	@GeneratedValue(generator="ALERT_SEQ")
	@Column(name="CASE_ID")
	private Integer caseId;
	
	@Column(name="CASE_TYPE")
	private String caseType;
	
	@Column(name="USER_ID")
	private String userId;// UEN No
	
	@Column(name="JOB_NO")
	private String jobNo;
	
	@Column(name="PERMIT_NO")
	private String permitNo;
	
	@Column(name="ALERT_CONTENT")
	private String alertContent;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ALERT_EMAILS")
	private String alertEmails;	// customized email recipients
	
	@Column(name="TO_ALERT_COMPANY")
	private Boolean toAlertCompany; // to alert emails in Company setting 
	
	@Column(name="REMINDER_DATE")
	private String reminderDate;
	
	@Column(name="OPEN_DATE")
	private Date openDate;
	
	@Column(name="COMPLETED_DATE")
	private String completedDate;
	
	@ManyToOne
	@JoinColumn(name="OPEN_BY")
	private User openBy;
	
	@ManyToOne
	@JoinColumn(name="COMPLETED_BY")
	private User completedBy;
	
	@Column(name="LICENCE_NO")
	private String licenceNo;
	
	@Column(name="LICENCE_START_DATE")
	private Date licenceStartDate;
	
	@Column(name="LICENCE_END_DATE")
	private Date licenceEndDate;
	
	@OneToMany(mappedBy="bctsAlert")	
	private Set<CaseComment> comments;	
	
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getPermitNo() {
		return permitNo;
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}
	public String getAlertContent() {
		return alertContent;
	}
	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		
	public String getAlertEmails() {
		return alertEmails;
	}
	public void setAlertEmails(String alertEmails) {
		this.alertEmails = alertEmails;
	}
	public Boolean getToAlertCompany() {
		return toAlertCompany;
	}
	public void setToAlertCompany(Boolean toAlertCompany) {
		this.toAlertCompany = toAlertCompany;
	}
	public String getReminderDate() {
		return reminderDate;
	}
	public void setReminderDate(String reminderDate) {
		this.reminderDate = reminderDate;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	
	public User getOpenBy() {
		return openBy;
	}
	public void setOpenBy(User openBy) {
		this.openBy = openBy;
	}
	public User getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(User completedBy) {
		this.completedBy = completedBy;
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
	public Set<CaseComment> getComments() {
		return comments;
	}
	public void setComments(Set<CaseComment> comments) {
		this.comments = comments;
	}
	
}
