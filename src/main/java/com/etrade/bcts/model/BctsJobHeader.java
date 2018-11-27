/**
 *@author ajayasamanta
 * 
*/
package com.etrade.bcts.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="BCTS_JOB_HEADER")
@IdClass(value=BctsPermitPk.class)
public class BctsJobHeader implements Serializable{
	 
	private static final long serialVersionUID = -257178022837581959L;
	@Id
	@Column(name="USER_ID")
	private String uenId;
	@Id
	@Column(name="JOB_NO")
	private String urnSeq;//JOB
	
	@Column(name="JOB_DATE")
	private BigDecimal jobDate;
	
	@Column(name="JOB_SEQ_NO")
	private String jobSeq;
	@Column(name="CR_UEI_NO")
	private String crUeiNo;
	@Column(name="CAR_TYPE")
	private String carType;
	@Column(name="DEC_TYPE")
	private String decType;
	@Column(name="DEC_STATUS")
	private String decStatus;
	@Column(name="MSG_STATUS")
	private String msgStatus;
	@Column(name="UPDATE_PERMIT_NO")	
	private String updatedPerNo;
	@Column(name="UPDATE_CERT_NO")
	private String updateCertNo;
	@Column(name="CREATE_DATE")
	private Date createdDate;
	@Column(name="LASTMODIFIED_DATE")
	private Date lastModifDate;
	@Column(name="SUBMIT_DATE")
	private Date submitDate;
	@Column(name="RECV_DATE")
	private Date receiveDate;
	@Column(name="APPROVED_DATE")
	private Date approvedDate;
	
	
	@OneToMany(targetEntity=BctsPermitType.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<BctsPermitType> permits;
	
	@OneToMany(targetEntity=CaaApprovalCondition.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<CaaApprovalCondition> permitConditions;
	
	@OneToMany(targetEntity=PermitSummary.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitSummary> summary;

	
	@OneToMany(targetEntity=PermitCertifate.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitCertifate> certificates;
	
	@OneToMany(targetEntity=PermitInvoice.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitInvoice> invoicesLst;
	
	@OneToMany(targetEntity=PermitLicence.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitLicence> licenseLst;
	
	@OneToMany(targetEntity=PermitCargo.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitCargo> cargoList;
	
	@OneToMany(targetEntity=PermitInTransport.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitInTransport> inTransList;
	
	@OneToMany(targetEntity=PermitOutTransport.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitOutTransport> outTransList;
	
	@OneToMany(targetEntity=PermitParty.class,mappedBy="jobHeader",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PermitParty> permitPartyLst;
	
	
	
	/**
	 * @return the uenId
	 */
	public String getUenId() {
		return uenId;
	}


	/**
	 * @param uenId the uenId to set
	 */
	public void setUenId(String uenId) {
		this.uenId = uenId;
	}


	/**
	 * @return the urnSeq
	 */
	public String getUrnSeq() {
		return urnSeq;
	}


	/**
	 * @param urnSeq the urnSeq to set
	 */
	public void setUrnSeq(String urnSeq) {
		this.urnSeq = urnSeq;
	}


	/**
	 * @return the jobDate
	 */
	public BigDecimal getJobDate() {
		return jobDate;
	}


	/**
	 * @param jobDate the jobDate to set
	 */
	public void setJobDate(BigDecimal jobDate) {
		this.jobDate = jobDate;
	}


	/**
	 * @return the jobSeq
	 */
	public String getJobSeq() {
		return jobSeq;
	}


	/**
	 * @param jobSeq the jobSeq to set
	 */
	public void setJobSeq(String jobSeq) {
		this.jobSeq = jobSeq;
	}


	/**
	 * @return the crUeiNo
	 */
	public String getCrUeiNo() {
		return crUeiNo;
	}


	/**
	 * @param crUeiNo the crUeiNo to set
	 */
	public void setCrUeiNo(String crUeiNo) {
		this.crUeiNo = crUeiNo;
	}


	/**
	 * @return the carType
	 */
	public String getCarType() {
		return carType;
	}


	/**
	 * @param carType the carType to set
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}


	/**
	 * @return the decType
	 */
	public String getDecType() {
		return decType;
	}


	/**
	 * @param decType the decType to set
	 */
	public void setDecType(String decType) {
		this.decType = decType;
	}


	/**
	 * @return the decStatus
	 */
	public String getDecStatus() {
		return decStatus;
	}


	/**
	 * @param decStatus the decStatus to set
	 */
	public void setDecStatus(String decStatus) {
		this.decStatus = decStatus;
	}


	/**
	 * @return the msgStatus
	 */
	public String getMsgStatus() {
		return msgStatus;
	}


	/**
	 * @param msgStatus the msgStatus to set
	 */
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}


	/**
	 * @return the updatedPerNo
	 */
	public String getUpdatedPerNo() {
		return updatedPerNo;
	}


	/**
	 * @param updatedPerNo the updatedPerNo to set
	 */
	public void setUpdatedPerNo(String updatedPerNo) {
		this.updatedPerNo = updatedPerNo;
	}


	/**
	 * @return the updateCertNo
	 */
	public String getUpdateCertNo() {
		return updateCertNo;
	}


	/**
	 * @param updateCertNo the updateCertNo to set
	 */
	public void setUpdateCertNo(String updateCertNo) {
		this.updateCertNo = updateCertNo;
	}


	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	/**
	 * @return the lastModifDate
	 */
	public Date getLastModifDate() {
		return lastModifDate;
	}


	/**
	 * @param lastModifDate the lastModifDate to set
	 */
	public void setLastModifDate(Date lastModifDate) {
		this.lastModifDate = lastModifDate;
	}


	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}


	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}


	/**
	 * @return the receiveDate
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}


	/**
	 * @param receiveDate the receiveDate to set
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}


	/**
	 * @return the approvedDate
	 */
	public Date getApprovedDate() {
		return approvedDate;
	}


	/**
	 * @param approvedDate the approvedDate to set
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}


	/**
	 * @return the permits
	 */
	public List<BctsPermitType> getPermits() {
		return permits;
	}


	/**
	 * @param permits the permits to set
	 */
	public void setPermits(List<BctsPermitType> permits) {
		this.permits = permits;
	}


	/**
	 * @return the summary
	 */
	public List<PermitSummary> getSummary() {
		return summary;
	}


	/**
	 * @param summary the summary to set
	 */
	public void setSummary(List<PermitSummary> summary) {
		this.summary = summary;
	}


	/**
	 * @return the permitConditions
	 */
	public List<CaaApprovalCondition> getPermitConditions() {
		return permitConditions;
	}


	/**
	 * @param permitConditions the permitConditions to set
	 */
	public void setPermitConditions(List<CaaApprovalCondition> permitConditions) {
		this.permitConditions = permitConditions;
	}


	/**
	 * @return the certificates
	 */
	public List<PermitCertifate> getCertificates() {
		return certificates;
	}


	/**
	 * @param certificates the certificates to set
	 */
	public void setCertificates(List<PermitCertifate> certificates) {
		this.certificates = certificates;
	}


	/**
	 * @return the invoicesLst
	 */
	public List<PermitInvoice> getInvoicesLst() {
		return invoicesLst;
	}


	/**
	 * @param invoicesLst the invoicesLst to set
	 */
	public void setInvoicesLst(List<PermitInvoice> invoicesLst) {
		this.invoicesLst = invoicesLst;
	}


	/**
	 * @return the licenseLst
	 */
	public List<PermitLicence> getLicenseLst() {
		return licenseLst;
	}


	/**
	 * @param licenseLst the licenseLst to set
	 */
	public void setLicenseLst(List<PermitLicence> licenseLst) {
		this.licenseLst = licenseLst;
	}


	/**
	 * @return the cargoList
	 */
	public List<PermitCargo> getCargoList() {
		return cargoList;
	}


	/**
	 * @param cargoList the cargoList to set
	 */
	public void setCargoList(List<PermitCargo> cargoList) {
		this.cargoList = cargoList;
	}


	/**
	 * @return the inTransList
	 */
	public List<PermitInTransport> getInTransList() {
		return inTransList;
	}


	/**
	 * @param inTransList the inTransList to set
	 */
	public void setInTransList(List<PermitInTransport> inTransList) {
		this.inTransList = inTransList;
	}


	/**
	 * @return the outTransList
	 */
	public List<PermitOutTransport> getOutTransList() {
		return outTransList;
	}


	/**
	 * @param outTransList the outTransList to set
	 */
	public void setOutTransList(List<PermitOutTransport> outTransList) {
		this.outTransList = outTransList;
	}


	/**
	 * @return the permitPartyLst
	 */
	public List<PermitParty> getPermitPartyLst() {
		return permitPartyLst;
	}


	/**
	 * @param permitPartyLst the permitPartyLst to set
	 */
	public void setPermitPartyLst(List<PermitParty> permitPartyLst) {
		this.permitPartyLst = permitPartyLst;
	}
	
	
	
	
	

}
