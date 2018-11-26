package com.etrade.bcts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="BCTS_PERMIT")
//@IdClass(PermitPK.class)
public class Permit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id 
//	@Column(name="USER_ID")
//	private Integer userId;	
//	
//	@Id 
//	@Column(name="JOB_NO")
//	private Integer jobNo;
	@EmbeddedId
	private PermitPK pk;
	
	@Column(name="PERMIT_NO")
	private String permitNo;

	@Column(name="CERT_NO")
	private String certNo;
	
	@Column(name="APPROVE_DATE")
	private Date approveDate;
	
	@Column(name="ORIG_APPROVE_DATE")
	private Date origApproveDate;
	
	@Column(name="VALIDITY_START_DATE")
	private Date validityStartDate;
	
	@Column(name="VALIDITY_END_DATE")
	private Date validityEndDate;
	
	@Column(name="CA_APPROVE_DATETIME")
	private Date caApproveDate;
	
	@Column(name="APPROVE_DATETIME")
	private Date approveDateTime;
	
	@Column(name="ORIG_APPROVE_DATETIME")
	private Date origApproveDateTime;

	public PermitPK getPk() {
		return pk;
	}

	public void setPk(PermitPK pk) {
		this.pk = pk;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public Date getOrigApproveDate() {
		return origApproveDate;
	}

	public void setOrigApproveDate(Date origApproveDate) {
		this.origApproveDate = origApproveDate;
	}

	public Date getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	public Date getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public Date getCaApproveDate() {
		return caApproveDate;
	}

	public void setCaApproveDate(Date caApproveDate) {
		this.caApproveDate = caApproveDate;
	}

	public Date getApproveDateTime() {
		return approveDateTime;
	}

	public void setApproveDateTime(Date approveDateTime) {
		this.approveDateTime = approveDateTime;
	}

	public Date getOrigApproveDateTime() {
		return origApproveDateTime;
	}

	public void setOrigApproveDateTime(Date origApproveDateTime) {
		this.origApproveDateTime = origApproveDateTime;
	}
}
