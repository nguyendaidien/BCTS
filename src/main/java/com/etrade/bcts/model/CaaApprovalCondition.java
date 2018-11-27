package com.etrade.bcts.model;

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
@Table(name="BCTS_APPROVAL_COND")
public class CaaApprovalCondition implements Serializable{

	/**
	 * @author ajayasamanta
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_ID")
	private String uenId;
	@Id
	@Column(name="JOB_NO")
	private String urnSeq;
	@Id
	@Column(name="SEQ_NO")
	private BigDecimal seqNo;
	@Id
	@Column(name="APPROVAL_TYPE")
	private String aprovalType;
	@Column(name="AGENCY_CODE")
	private String agencyCode;
	@Column(name="CONDITION_CODE")
	private String conditionCode;
	@Column(name="CONDITION_DESC1")
	private String condtionDesc1;
	@Column(name="CONDITION_DESC2")
	private String condtionDesc2;
	@Column(name="CONDITION_DESC3")
	private String condtionDesc3;
	@Column(name="CONDITION_DESC4")
	private String condtionDesc4;
	
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
	 * @return the seqNo
	 */
	public BigDecimal getSeqNo() {
		return seqNo;
	}
	/**
	 * @param seqNo the seqNo to set
	 */
	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}
	/**
	 * @return the aprovalType
	 */
	public String getAprovalType() {
		return aprovalType;
	}
	/**
	 * @param aprovalType the aprovalType to set
	 */
	public void setAprovalType(String aprovalType) {
		this.aprovalType = aprovalType;
	}
	/**
	 * @return the agencyCode
	 */
	public String getAgencyCode() {
		return agencyCode;
	}
	/**
	 * @param agencyCode the agencyCode to set
	 */
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	/**
	 * @return the conditionCode
	 */
	public String getConditionCode() {
		return conditionCode;
	}
	/**
	 * @param conditionCode the conditionCode to set
	 */
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}
	/**
	 * @return the condtionDesc1
	 */
	public String getCondtionDesc1() {
		return condtionDesc1;
	}
	/**
	 * @param condtionDesc1 the condtionDesc1 to set
	 */
	public void setCondtionDesc1(String condtionDesc1) {
		this.condtionDesc1 = condtionDesc1;
	}
	/**
	 * @return the condtionDesc2
	 */
	public String getCondtionDesc2() {
		return condtionDesc2;
	}
	/**
	 * @param condtionDesc2 the condtionDesc2 to set
	 */
	public void setCondtionDesc2(String condtionDesc2) {
		this.condtionDesc2 = condtionDesc2;
	}
	/**
	 * @return the condtionDesc3
	 */
	public String getCondtionDesc3() {
		return condtionDesc3;
	}
	/**
	 * @param condtionDesc3 the condtionDesc3 to set
	 */
	public void setCondtionDesc3(String condtionDesc3) {
		this.condtionDesc3 = condtionDesc3;
	}
	/**
	 * @return the condtionDesc4
	 */
	public String getCondtionDesc4() {
		return condtionDesc4;
	}
	/**
	 * @param condtionDesc4 the condtionDesc4 to set
	 */
	public void setCondtionDesc4(String condtionDesc4) {
		this.condtionDesc4 = condtionDesc4;
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
		result = prime * result + ((agencyCode == null) ? 0 : agencyCode.hashCode());
		result = prime * result + ((aprovalType == null) ? 0 : aprovalType.hashCode());
		result = prime * result + ((conditionCode == null) ? 0 : conditionCode.hashCode());
		result = prime * result + ((condtionDesc1 == null) ? 0 : condtionDesc1.hashCode());
		result = prime * result + ((condtionDesc2 == null) ? 0 : condtionDesc2.hashCode());
		result = prime * result + ((condtionDesc3 == null) ? 0 : condtionDesc3.hashCode());
		result = prime * result + ((condtionDesc4 == null) ? 0 : condtionDesc4.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
		result = prime * result + ((uenId == null) ? 0 : uenId.hashCode());
		result = prime * result + ((urnSeq == null) ? 0 : urnSeq.hashCode());
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
		if (!(obj instanceof CaaApprovalCondition)) {
			return false;
		}
		CaaApprovalCondition other = (CaaApprovalCondition) obj;
		if (agencyCode == null) {
			if (other.agencyCode != null) {
				return false;
			}
		} else if (!agencyCode.equals(other.agencyCode)) {
			return false;
		}
		if (aprovalType == null) {
			if (other.aprovalType != null) {
				return false;
			}
		} else if (!aprovalType.equals(other.aprovalType)) {
			return false;
		}
		if (conditionCode == null) {
			if (other.conditionCode != null) {
				return false;
			}
		} else if (!conditionCode.equals(other.conditionCode)) {
			return false;
		}
		if (condtionDesc1 == null) {
			if (other.condtionDesc1 != null) {
				return false;
			}
		} else if (!condtionDesc1.equals(other.condtionDesc1)) {
			return false;
		}
		if (condtionDesc2 == null) {
			if (other.condtionDesc2 != null) {
				return false;
			}
		} else if (!condtionDesc2.equals(other.condtionDesc2)) {
			return false;
		}
		if (condtionDesc3 == null) {
			if (other.condtionDesc3 != null) {
				return false;
			}
		} else if (!condtionDesc3.equals(other.condtionDesc3)) {
			return false;
		}
		if (condtionDesc4 == null) {
			if (other.condtionDesc4 != null) {
				return false;
			}
		} else if (!condtionDesc4.equals(other.condtionDesc4)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (seqNo == null) {
			if (other.seqNo != null) {
				return false;
			}
		} else if (!seqNo.equals(other.seqNo)) {
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
		return true;
	}
	
	

	
	
	
	
	
	
}
