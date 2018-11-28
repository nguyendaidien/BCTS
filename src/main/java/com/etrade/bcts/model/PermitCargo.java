package com.etrade.bcts.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="BCTS_CARGO_HEADER")
public class PermitCargo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3074530696671157548L;
	
	@Id
	@Column(name="USER_ID")
	private String ureId; 
	@Id
	@Column(name="JOB_NO")
	private String ureSeq;
	@Column(name="IMP_END_DATE")
	private Date impEndDate;
	@Column(name="REM_START_DATE")
	private Date remStartDate;
	@Column(name="RELEASE_LOC_CODE")
	private String relLocCode;
	@Column(name="RELEASE_LOC_NAME")
	private String relLocName;
	@Column(name="RECEIPT_LOC_CODE")
	private String recLocCode;
	@Column(name="RECEIPT_LOC_NAME")
	private String recLocName;
	@Column(name="STORAGE_LOC_CODE")
	private String storLocCode;
	@Column(name="STORAGE_LOC_NAME")
	private String storLocName;
	@Column(name="IMP_BKTREM_START_DATE")
	private Date impBkpStDate;
	@Column(name="MAX_AME_CTN_SEQ")
	private BigDecimal maxAmeCtnSqe;
	
	@OneToMany(targetEntity=PermitCargoCtn.class,mappedBy="permitCargo",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<PermitCargoCtn> permitCCtnLst;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="USER_ID", insertable=false, updatable=false),
		@JoinColumn(name="JOB_NO", insertable=false, updatable=false)
	})
	private BctsJobHeader jobHeader;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((impBkpStDate == null) ? 0 : impBkpStDate.hashCode());
		result = prime * result + ((impEndDate == null) ? 0 : impEndDate.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((maxAmeCtnSqe == null) ? 0 : maxAmeCtnSqe.hashCode());
		result = prime * result + ((permitCCtnLst == null) ? 0 : permitCCtnLst.hashCode());
		result = prime * result + ((recLocCode == null) ? 0 : recLocCode.hashCode());
		result = prime * result + ((recLocName == null) ? 0 : recLocName.hashCode());
		result = prime * result + ((relLocCode == null) ? 0 : relLocCode.hashCode());
		result = prime * result + ((relLocName == null) ? 0 : relLocName.hashCode());
		result = prime * result + ((remStartDate == null) ? 0 : remStartDate.hashCode());
		result = prime * result + ((storLocCode == null) ? 0 : storLocCode.hashCode());
		result = prime * result + ((storLocName == null) ? 0 : storLocName.hashCode());
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
		if (!(obj instanceof PermitCargo)) {
			return false;
		}
		PermitCargo other = (PermitCargo) obj;
		if (impBkpStDate == null) {
			if (other.impBkpStDate != null) {
				return false;
			}
		} else if (!impBkpStDate.equals(other.impBkpStDate)) {
			return false;
		}
		if (impEndDate == null) {
			if (other.impEndDate != null) {
				return false;
			}
		} else if (!impEndDate.equals(other.impEndDate)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (maxAmeCtnSqe == null) {
			if (other.maxAmeCtnSqe != null) {
				return false;
			}
		} else if (!maxAmeCtnSqe.equals(other.maxAmeCtnSqe)) {
			return false;
		}
		if (permitCCtnLst == null) {
			if (other.permitCCtnLst != null) {
				return false;
			}
		} else if (!permitCCtnLst.equals(other.permitCCtnLst)) {
			return false;
		}
		if (recLocCode == null) {
			if (other.recLocCode != null) {
				return false;
			}
		} else if (!recLocCode.equals(other.recLocCode)) {
			return false;
		}
		if (recLocName == null) {
			if (other.recLocName != null) {
				return false;
			}
		} else if (!recLocName.equals(other.recLocName)) {
			return false;
		}
		if (relLocCode == null) {
			if (other.relLocCode != null) {
				return false;
			}
		} else if (!relLocCode.equals(other.relLocCode)) {
			return false;
		}
		if (relLocName == null) {
			if (other.relLocName != null) {
				return false;
			}
		} else if (!relLocName.equals(other.relLocName)) {
			return false;
		}
		if (remStartDate == null) {
			if (other.remStartDate != null) {
				return false;
			}
		} else if (!remStartDate.equals(other.remStartDate)) {
			return false;
		}
		if (storLocCode == null) {
			if (other.storLocCode != null) {
				return false;
			}
		} else if (!storLocCode.equals(other.storLocCode)) {
			return false;
		}
		if (storLocName == null) {
			if (other.storLocName != null) {
				return false;
			}
		} else if (!storLocName.equals(other.storLocName)) {
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
	 * @return the impEndDate
	 */
	public Date getImpEndDate() {
		return impEndDate;
	}
	/**
	 * @param impEndDate the impEndDate to set
	 */
	public void setImpEndDate(Date impEndDate) {
		this.impEndDate = impEndDate;
	}
	/**
	 * @return the remStartDate
	 */
	public Date getRemStartDate() {
		return remStartDate;
	}
	/**
	 * @param remStartDate the remStartDate to set
	 */
	public void setRemStartDate(Date remStartDate) {
		this.remStartDate = remStartDate;
	}
	/**
	 * @return the relLocCode
	 */
	public String getRelLocCode() {
		return relLocCode;
	}
	/**
	 * @param relLocCode the relLocCode to set
	 */
	public void setRelLocCode(String relLocCode) {
		this.relLocCode = relLocCode;
	}
	/**
	 * @return the relLocName
	 */
	public String getRelLocName() {
		return relLocName;
	}
	/**
	 * @param relLocName the relLocName to set
	 */
	public void setRelLocName(String relLocName) {
		this.relLocName = relLocName;
	}
	/**
	 * @return the recLocCode
	 */
	public String getRecLocCode() {
		return recLocCode;
	}
	/**
	 * @param recLocCode the recLocCode to set
	 */
	public void setRecLocCode(String recLocCode) {
		this.recLocCode = recLocCode;
	}
	/**
	 * @return the recLocName
	 */
	public String getRecLocName() {
		return recLocName;
	}
	/**
	 * @param recLocName the recLocName to set
	 */
	public void setRecLocName(String recLocName) {
		this.recLocName = recLocName;
	}
	/**
	 * @return the storLocCode
	 */
	public String getStorLocCode() {
		return storLocCode;
	}
	/**
	 * @param storLocCode the storLocCode to set
	 */
	public void setStorLocCode(String storLocCode) {
		this.storLocCode = storLocCode;
	}
	/**
	 * @return the storLocName
	 */
	public String getStorLocName() {
		return storLocName;
	}
	/**
	 * @param storLocName the storLocName to set
	 */
	public void setStorLocName(String storLocName) {
		this.storLocName = storLocName;
	}
	/**
	 * @return the impBkpStDate
	 */
	public Date getImpBkpStDate() {
		return impBkpStDate;
	}
	/**
	 * @param impBkpStDate the impBkpStDate to set
	 */
	public void setImpBkpStDate(Date impBkpStDate) {
		this.impBkpStDate = impBkpStDate;
	}
	/**
	 * @return the maxAmeCtnSqe
	 */
	public BigDecimal getMaxAmeCtnSqe() {
		return maxAmeCtnSqe;
	}
	/**
	 * @param maxAmeCtnSqe the maxAmeCtnSqe to set
	 */
	public void setMaxAmeCtnSqe(BigDecimal maxAmeCtnSqe) {
		this.maxAmeCtnSqe = maxAmeCtnSqe;
	}
	
	/**
	 * @return the permitCCtnLst
	 */
	public List<PermitCargoCtn> getPermitCCtnLst() {
		return permitCCtnLst;
	}
	/**
	 * @param permitCCtnLst the permitCCtnLst to set
	 */
	public void setPermitCCtnLst(List<PermitCargoCtn> permitCCtnLst) {
		this.permitCCtnLst = permitCCtnLst;
	}
	
	
	
	

}
