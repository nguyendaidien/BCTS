package com.etrade.bcts.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BCTS_ALERT")
public class BCTSAlert {  	
	private static final Logger LOG = LoggerFactory.getLogger(BCTSAlert.class);
	@Id
	@GenericGenerator(name="ALERT_SEQ", strategy="increment")
	@GeneratedValue(generator="ALERT_SEQ")
	@Column(name="CASE_ID")
	private Integer caseId;
	
	@Column(name="CASE_TYPE")
	private String caseType;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="UEN")
	private Company uen;// UEN No
	
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
	private Boolean toAlertCompany = true; // to alert emails in Company setting 
	
	@Column(name="REMINDER_DATE")
	private Date reminderDate;
	
	@Column(name="OPEN_DATE")
	private Date openDate;
	
	@Column(name="COMPLETED_DATE")
	private String completedDate;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="OPEN_BY")
	private User openBy;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="COMPLETED_BY")
	private User completedBy;
	
	@Column(name="LICENCE_NO")
	private String licenceNo;
	
	@Column(name="LICENCE_START_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date licenceStartDate;
	
	@Column(name="LICENCE_END_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date licenceEndDate;
	
	@OneToMany(mappedBy="bctsAlert")	
	private List<CaseComment> comments;	
	
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
	
	public Company getUen() {
		return uen;
	}
	public void setUen(Company uen) {
		this.uen = uen;
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
	
	public Date getReminderDate() {
		return reminderDate;
	}
	public void setReminderDate(Date reminderDate) {
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
		LOG.info("getUen");
		return openBy;
	}
	public void setOpenBy(User openBy) {
		LOG.info("setUen");
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
	public List<CaseComment> getComments() {
		return comments;
	}
	public void setComments(List<CaseComment> comments) {
		this.comments = comments;
	}
	
	public String[] getEmailRecipients() {
		String[] recipients = {};
		if(this.uen!= null && this.uen.getAlertEmails() != null) {
			recipients = this.uen.getAlertEmails().trim().split("\\s*,\\s*");
		}
		if(StringUtils.isNotEmpty(this.alertEmails)) {
			if(this.toAlertCompany && recipients.length > 0) {
				Set<String> emailsSet = new HashSet<String>(Arrays.asList(recipients));
				emailsSet.addAll(Arrays.asList(this.alertEmails.trim().split("\\s*,\\s*")));
				return emailsSet.toArray(new String[emailsSet.size()]);
			} else {
				recipients = this.alertEmails.trim().split("\\s*,\\s*");
			}
		}
		return recipients; 
	}
}
