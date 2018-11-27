package com.etrade.bcts.model;

/**
 * 
 * @author ajayasamanta
 *
 */
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="BCTS_CERT_HEADER")
public class PermitCertifate implements Serializable{
	
	private static final long serialVersionUID = -2021574230664555648L;
	@Id
	@Column(name="USER_ID")
	private String ureId;
	@Id
	@Column(name="JOB_NO")
	private String ureSeq;
	@Column(name="APPL_PROD_TYPE")
	private String apprProdType;
	@Column(name="CERT1_TYPE")
	private String cert1Type;
	@Column(name="CERT1_COPIES")
	private BigDecimal cert1Copies;
	@Column(name="CERT2_TYPE")
	private String cert2Type;
	@Column(name="CERT2_COPIES")
	private BigDecimal cert2Copies;
	@Column(name="ENTRY_YEAR")
	private BigDecimal enteryYear;
	@Column(name="GSP_DONOR_CTRY_CODE")
	private String gspDonCnCode;
	@Column(name="PREF_PERCENT")
	private BigDecimal prefPer;
	@Column(name="CURR_CODE")
	private String currCode;
	@Column(name="CERT_DETAIL")
	private String certiDetails;
	@Column(name="TRANS_DETAIL")
	private String transDetails;
	@Column(name="GSP_DONOR_CTRY_DESC")
	private String gspDonCnDes;
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="USER_ID", insertable=false, updatable=false),
		@JoinColumn(name="JOB_NO", insertable=false, updatable=false)
	})
	private BctsJobHeader jobHeader;
	/**
	 * @return the ureId
	 */
	public String getUreId() {
		return ureId;
	}
	/**
	 * @param ureId the ureId to set
	 */
	public void setUreId(String ureId) {
		this.ureId = ureId;
	}
	/**
	 * @return the ureSeq
	 */
	public String getUreSeq() {
		return ureSeq;
	}
	/**
	 * @param ureSeq the ureSeq to set
	 */
	public void setUreSeq(String ureSeq) {
		this.ureSeq = ureSeq;
	}
	/**
	 * @return the apprProdType
	 */
	public String getApprProdType() {
		return apprProdType;
	}
	/**
	 * @param apprProdType the apprProdType to set
	 */
	public void setApprProdType(String apprProdType) {
		this.apprProdType = apprProdType;
	}
	/**
	 * @return the cert1Type
	 */
	public String getCert1Type() {
		return cert1Type;
	}
	/**
	 * @param cert1Type the cert1Type to set
	 */
	public void setCert1Type(String cert1Type) {
		this.cert1Type = cert1Type;
	}
	/**
	 * @return the cert1Copies
	 */
	public BigDecimal getCert1Copies() {
		return cert1Copies;
	}
	/**
	 * @param cert1Copies the cert1Copies to set
	 */
	public void setCert1Copies(BigDecimal cert1Copies) {
		this.cert1Copies = cert1Copies;
	}
	/**
	 * @return the cert2Type
	 */
	public String getCert2Type() {
		return cert2Type;
	}
	/**
	 * @param cert2Type the cert2Type to set
	 */
	public void setCert2Type(String cert2Type) {
		this.cert2Type = cert2Type;
	}
	/**
	 * @return the cert2Copies
	 */
	public BigDecimal getCert2Copies() {
		return cert2Copies;
	}
	/**
	 * @param cert2Copies the cert2Copies to set
	 */
	public void setCert2Copies(BigDecimal cert2Copies) {
		this.cert2Copies = cert2Copies;
	}
	/**
	 * @return the enteryYear
	 */
	public BigDecimal getEnteryYear() {
		return enteryYear;
	}
	/**
	 * @param enteryYear the enteryYear to set
	 */
	public void setEnteryYear(BigDecimal enteryYear) {
		this.enteryYear = enteryYear;
	}
	/**
	 * @return the gspDonCnCode
	 */
	public String getGspDonCnCode() {
		return gspDonCnCode;
	}
	/**
	 * @param gspDonCnCode the gspDonCnCode to set
	 */
	public void setGspDonCnCode(String gspDonCnCode) {
		this.gspDonCnCode = gspDonCnCode;
	}
	/**
	 * @return the prefPer
	 */
	public BigDecimal getPrefPer() {
		return prefPer;
	}
	/**
	 * @param prefPer the prefPer to set
	 */
	public void setPrefPer(BigDecimal prefPer) {
		this.prefPer = prefPer;
	}
	/**
	 * @return the currCode
	 */
	public String getCurrCode() {
		return currCode;
	}
	/**
	 * @param currCode the currCode to set
	 */
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	/**
	 * @return the certiDetails
	 */
	public String getCertiDetails() {
		return certiDetails;
	}
	/**
	 * @param certiDetails the certiDetails to set
	 */
	public void setCertiDetails(String certiDetails) {
		this.certiDetails = certiDetails;
	}
	/**
	 * @return the transDetails
	 */
	public String getTransDetails() {
		return transDetails;
	}
	/**
	 * @param transDetails the transDetails to set
	 */
	public void setTransDetails(String transDetails) {
		this.transDetails = transDetails;
	}
	/**
	 * @return the gspDonCnDes
	 */
	public String getGspDonCnDes() {
		return gspDonCnDes;
	}
	/**
	 * @param gspDonCnDes the gspDonCnDes to set
	 */
	public void setGspDonCnDes(String gspDonCnDes) {
		this.gspDonCnDes = gspDonCnDes;
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
		result = prime * result + ((apprProdType == null) ? 0 : apprProdType.hashCode());
		result = prime * result + ((cert1Copies == null) ? 0 : cert1Copies.hashCode());
		result = prime * result + ((cert1Type == null) ? 0 : cert1Type.hashCode());
		result = prime * result + ((cert2Copies == null) ? 0 : cert2Copies.hashCode());
		result = prime * result + ((cert2Type == null) ? 0 : cert2Type.hashCode());
		result = prime * result + ((certiDetails == null) ? 0 : certiDetails.hashCode());
		result = prime * result + ((currCode == null) ? 0 : currCode.hashCode());
		result = prime * result + ((enteryYear == null) ? 0 : enteryYear.hashCode());
		result = prime * result + ((gspDonCnCode == null) ? 0 : gspDonCnCode.hashCode());
		result = prime * result + ((gspDonCnDes == null) ? 0 : gspDonCnDes.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((prefPer == null) ? 0 : prefPer.hashCode());
		result = prime * result + ((transDetails == null) ? 0 : transDetails.hashCode());
		result = prime * result + ((ureId == null) ? 0 : ureId.hashCode());
		result = prime * result + ((ureSeq == null) ? 0 : ureSeq.hashCode());
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
		if (!(obj instanceof PermitCertifate)) {
			return false;
		}
		PermitCertifate other = (PermitCertifate) obj;
		if (apprProdType == null) {
			if (other.apprProdType != null) {
				return false;
			}
		} else if (!apprProdType.equals(other.apprProdType)) {
			return false;
		}
		if (cert1Copies == null) {
			if (other.cert1Copies != null) {
				return false;
			}
		} else if (!cert1Copies.equals(other.cert1Copies)) {
			return false;
		}
		if (cert1Type == null) {
			if (other.cert1Type != null) {
				return false;
			}
		} else if (!cert1Type.equals(other.cert1Type)) {
			return false;
		}
		if (cert2Copies == null) {
			if (other.cert2Copies != null) {
				return false;
			}
		} else if (!cert2Copies.equals(other.cert2Copies)) {
			return false;
		}
		if (cert2Type == null) {
			if (other.cert2Type != null) {
				return false;
			}
		} else if (!cert2Type.equals(other.cert2Type)) {
			return false;
		}
		if (certiDetails == null) {
			if (other.certiDetails != null) {
				return false;
			}
		} else if (!certiDetails.equals(other.certiDetails)) {
			return false;
		}
		if (currCode == null) {
			if (other.currCode != null) {
				return false;
			}
		} else if (!currCode.equals(other.currCode)) {
			return false;
		}
		if (enteryYear == null) {
			if (other.enteryYear != null) {
				return false;
			}
		} else if (!enteryYear.equals(other.enteryYear)) {
			return false;
		}
		if (gspDonCnCode == null) {
			if (other.gspDonCnCode != null) {
				return false;
			}
		} else if (!gspDonCnCode.equals(other.gspDonCnCode)) {
			return false;
		}
		if (gspDonCnDes == null) {
			if (other.gspDonCnDes != null) {
				return false;
			}
		} else if (!gspDonCnDes.equals(other.gspDonCnDes)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (prefPer == null) {
			if (other.prefPer != null) {
				return false;
			}
		} else if (!prefPer.equals(other.prefPer)) {
			return false;
		}
		if (transDetails == null) {
			if (other.transDetails != null) {
				return false;
			}
		} else if (!transDetails.equals(other.transDetails)) {
			return false;
		}
		if (ureId == null) {
			if (other.ureId != null) {
				return false;
			}
		} else if (!ureId.equals(other.ureId)) {
			return false;
		}
		if (ureSeq == null) {
			if (other.ureSeq != null) {
				return false;
			}
		} else if (!ureSeq.equals(other.ureSeq)) {
			return false;
		}
		return true;
	}
	
	

}
