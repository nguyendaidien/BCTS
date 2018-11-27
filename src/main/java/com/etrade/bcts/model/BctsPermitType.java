/**
	 * @author ajayasamanta
	 * 
*/
package com.etrade.bcts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="BCTS_PERMIT")
public class BctsPermitType implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="USER_ID")
	private String uenId;
	@Id
	@Column(name="JOB_NO")
	private String urnSeq;//JOB
	@Column(name="PERMIT_NO")
	private String permitNumber;
	@Column(name="CERT_NO")
	private String certNumber;
	@Column(name="APPROVE_DATE")
	private Date permApprDateAndTime;
	@Column(name="VALIDITY_START_DATE")
	private Date valPeriodStart;
	@Column(name="VALIDITY_END_DATE")
	private Date valPeriodEnd;
	@Column(name="CA_APPROVE_DATETIME")
	private Date caaApproDateAndTime;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="USER_ID", insertable=false, updatable=false),
		@JoinColumn(name="JOB_NO", insertable=false, updatable=false)
	})
	private BctsJobHeader jobHeader;

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
	 * @return the permitNumber
	 */
	public String getPermitNumber() {
		return permitNumber;
	}

	/**
	 * @param permitNumber the permitNumber to set
	 */
	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}

	/**
	 * @return the certNumber
	 */
	public String getCertNumber() {
		return certNumber;
	}

	/**
	 * @param certNumber the certNumber to set
	 */
	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
	}

	/**
	 * @return the permApprDateAndTime
	 */
	public Date getPermApprDateAndTime() {
		return permApprDateAndTime;
	}

	/**
	 * @param permApprDateAndTime the permApprDateAndTime to set
	 */
	public void setPermApprDateAndTime(Date permApprDateAndTime) {
		this.permApprDateAndTime = permApprDateAndTime;
	}

	/**
	 * @return the valPeriodStart
	 */
	public Date getValPeriodStart() {
		return valPeriodStart;
	}

	/**
	 * @param valPeriodStart the valPeriodStart to set
	 */
	public void setValPeriodStart(Date valPeriodStart) {
		this.valPeriodStart = valPeriodStart;
	}

	/**
	 * @return the valPeriodEnd
	 */
	public Date getValPeriodEnd() {
		return valPeriodEnd;
	}

	/**
	 * @param valPeriodEnd the valPeriodEnd to set
	 */
	public void setValPeriodEnd(Date valPeriodEnd) {
		this.valPeriodEnd = valPeriodEnd;
	}

	/**
	 * @return the caaApproDateAndTime
	 */
	public Date getCaaApproDateAndTime() {
		return caaApproDateAndTime;
	}

	/**
	 * @param caaApproDateAndTime the caaApproDateAndTime to set
	 */
	public void setCaaApproDateAndTime(Date caaApproDateAndTime) {
		this.caaApproDateAndTime = caaApproDateAndTime;
	}

	/**
	 * @return the jobHeader
	 */
	public BctsJobHeader getJobHeader() {
		return jobHeader;
	}

	/**
	 * @param jobHeader the jobHeader to set
	 */
	public void setJobHeader(BctsJobHeader jobHeader) {
		this.jobHeader = jobHeader;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caaApproDateAndTime == null) ? 0 : caaApproDateAndTime.hashCode());
		result = prime * result + ((certNumber == null) ? 0 : certNumber.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((permApprDateAndTime == null) ? 0 : permApprDateAndTime.hashCode());
		result = prime * result + ((permitNumber == null) ? 0 : permitNumber.hashCode());
		result = prime * result + ((uenId == null) ? 0 : uenId.hashCode());
		result = prime * result + ((urnSeq == null) ? 0 : urnSeq.hashCode());
		result = prime * result + ((valPeriodEnd == null) ? 0 : valPeriodEnd.hashCode());
		result = prime * result + ((valPeriodStart == null) ? 0 : valPeriodStart.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BctsPermitType)) {
			return false;
		}
		BctsPermitType other = (BctsPermitType) obj;
		if (caaApproDateAndTime == null) {
			if (other.caaApproDateAndTime != null) {
				return false;
			}
		} else if (!caaApproDateAndTime.equals(other.caaApproDateAndTime)) {
			return false;
		}
		if (certNumber == null) {
			if (other.certNumber != null) {
				return false;
			}
		} else if (!certNumber.equals(other.certNumber)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (permApprDateAndTime == null) {
			if (other.permApprDateAndTime != null) {
				return false;
			}
		} else if (!permApprDateAndTime.equals(other.permApprDateAndTime)) {
			return false;
		}
		if (permitNumber == null) {
			if (other.permitNumber != null) {
				return false;
			}
		} else if (!permitNumber.equals(other.permitNumber)) {
			return false;
		}
		if (uenId == null) {
			if (other.uenId != null) {
				return false;
			}
		} else if (!uenId.equals(other.uenId)) {
			return false;
		}
		if (urnSeq == null) {
			if (other.urnSeq != null) {
				return false;
			}
		} else if (!urnSeq.equals(other.urnSeq)) {
			return false;
		}
		if (valPeriodEnd == null) {
			if (other.valPeriodEnd != null) {
				return false;
			}
		} else if (!valPeriodEnd.equals(other.valPeriodEnd)) {
			return false;
		}
		if (valPeriodStart == null) {
			if (other.valPeriodStart != null) {
				return false;
			}
		} else if (!valPeriodStart.equals(other.valPeriodStart)) {
			return false;
		}
		return true;
	}
	

}
