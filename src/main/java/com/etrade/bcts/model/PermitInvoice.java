/**
 * @author ajayasamanta
 */

package com.etrade.bcts.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="BCTS_INVOICE")
public class PermitInvoice implements Serializable{

	private static final long serialVersionUID = 7667381171136049404L;
	@Id
	@Column(name="USER_ID")
	private String ureId;
	@Id
	@Column(name="JOB_NO")
	private String ureSeq; 
	@Id
	@Column(name="INV_SEQ_NO")
	private BigDecimal invSeqNo;
	@Column(name="INV_NO")
	private String invNo;
	@Column(name="UNIT_PRICE_TERM_TYPE")
	private String uPriceTermType;
	@Column(name="INV_DATE")
	private Date invDate;
	@Column(name="TOT_INV_AMT")
	private BigDecimal totInvAmt;
	@Column(name="TOT_INV_CURR_TYPE")
	private String totInvCrcType;
	@Column(name="TOT_INV_EXC_RATE")
	private BigDecimal totInvExRate;
	@Column(name="AD_VALOREM_IND")
	private String adVolmInd;
	@Column(name="SI_RELATION")
	private String siRelation;
	@Column(name="PREF_DUTY_IND")
	private String prefDutyInd;
	@Column(name="PARTY_CODE")
	private String partyCode;
	@Column(name="PARTY_NAME1")
	private String partyName1;
	@Column(name="PARTY_NAME2")
	private String partyName2;
	@Column(name="PARTY_NAME3")
	private String partyName3;
	@Column(name="FRG_CHRG_AMT")
	private BigDecimal frgCrgAmt;
	@Column(name="FRG_CHRG_CURR_CODE")
	private String frgCrgCurCode;
	@Column(name="FRG_CHRG_EXC_RATE")
	private BigDecimal frgCrgExRate;
	@Column(name="FRG_CHRG_PERCENT")
	private BigDecimal frgCrgPercent;
	@Column(name="INS_CHRG_AMT")
	private BigDecimal insCrgAmt;
	@Column(name="INS_CHRG_CURR_CODE")
	private String insCrgCurCode;
	@Column(name="INS_CHRG_EXC_RATE")
	private BigDecimal insCrgExRate;
	@Column(name="INS_CHRG_PERCENT")
	private BigDecimal insCrgPercent;
	@Column(name="OTH_CHRG_AMT")
	private BigDecimal othCrgAmt;
	@Column(name="OTH_CHRG_CURR_CODE")
	private String othCrgCurCode;
	@Column(name="OTH_CHRG_EXC_RATE")
	private BigDecimal othCrgExRate;
	@Column(name="OTH_CHRG_PERCENT")
	private BigDecimal othCrgPercent;
	
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
	 * @return the invSeqNo
	 */
	public BigDecimal getInvSeqNo() {
		return invSeqNo;
	}
	/**
	 * @param invSeqNo the invSeqNo to set
	 */
	public void setInvSeqNo(BigDecimal invSeqNo) {
		this.invSeqNo = invSeqNo;
	}
	/**
	 * @return the invNo
	 */
	public String getInvNo() {
		return invNo;
	}
	/**
	 * @param invNo the invNo to set
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	/**
	 * @return the uPriceTermType
	 */
	public String getuPriceTermType() {
		return uPriceTermType;
	}
	/**
	 * @param uPriceTermType the uPriceTermType to set
	 */
	public void setuPriceTermType(String uPriceTermType) {
		this.uPriceTermType = uPriceTermType;
	}
	/**
	 * @return the invDate
	 */
	public Date getInvDate() {
		return invDate;
	}
	/**
	 * @param invDate the invDate to set
	 */
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
	/**
	 * @return the totInvAmt
	 */
	public BigDecimal getTotInvAmt() {
		return totInvAmt;
	}
	/**
	 * @param totInvAmt the totInvAmt to set
	 */
	public void setTotInvAmt(BigDecimal totInvAmt) {
		this.totInvAmt = totInvAmt;
	}
	/**
	 * @return the totInvCrcType
	 */
	public String getTotInvCrcType() {
		return totInvCrcType;
	}
	/**
	 * @param totInvCrcType the totInvCrcType to set
	 */
	public void setTotInvCrcType(String totInvCrcType) {
		this.totInvCrcType = totInvCrcType;
	}
	/**
	 * @return the totInvExRate
	 */
	public BigDecimal getTotInvExRate() {
		return totInvExRate;
	}
	/**
	 * @param totInvExRate the totInvExRate to set
	 */
	public void setTotInvExRate(BigDecimal totInvExRate) {
		this.totInvExRate = totInvExRate;
	}
	/**
	 * @return the adVolmInd
	 */
	public String getAdVolmInd() {
		return adVolmInd;
	}
	/**
	 * @param adVolmInd the adVolmInd to set
	 */
	public void setAdVolmInd(String adVolmInd) {
		this.adVolmInd = adVolmInd;
	}
	/**
	 * @return the siRelation
	 */
	public String getSiRelation() {
		return siRelation;
	}
	/**
	 * @param siRelation the siRelation to set
	 */
	public void setSiRelation(String siRelation) {
		this.siRelation = siRelation;
	}
	/**
	 * @return the prefDutyInd
	 */
	public String getPrefDutyInd() {
		return prefDutyInd;
	}
	/**
	 * @param prefDutyInd the prefDutyInd to set
	 */
	public void setPrefDutyInd(String prefDutyInd) {
		this.prefDutyInd = prefDutyInd;
	}
	/**
	 * @return the partyCode
	 */
	public String getPartyCode() {
		return partyCode;
	}
	/**
	 * @param partyCode the partyCode to set
	 */
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	/**
	 * @return the partyName1
	 */
	public String getPartyName1() {
		return partyName1;
	}
	/**
	 * @param partyName1 the partyName1 to set
	 */
	public void setPartyName1(String partyName1) {
		this.partyName1 = partyName1;
	}
	/**
	 * @return the partyName2
	 */
	public String getPartyName2() {
		return partyName2;
	}
	/**
	 * @param partyName2 the partyName2 to set
	 */
	public void setPartyName2(String partyName2) {
		this.partyName2 = partyName2;
	}
	/**
	 * @return the partyName3
	 */
	public String getPartyName3() {
		return partyName3;
	}
	/**
	 * @param partyName3 the partyName3 to set
	 */
	public void setPartyName3(String partyName3) {
		this.partyName3 = partyName3;
	}
	/**
	 * @return the frgCrgAmt
	 */
	public BigDecimal getFrgCrgAmt() {
		return frgCrgAmt;
	}
	/**
	 * @param frgCrgAmt the frgCrgAmt to set
	 */
	public void setFrgCrgAmt(BigDecimal frgCrgAmt) {
		this.frgCrgAmt = frgCrgAmt;
	}
	/**
	 * @return the frgCrgCurCode
	 */
	public String getFrgCrgCurCode() {
		return frgCrgCurCode;
	}
	/**
	 * @param frgCrgCurCode the frgCrgCurCode to set
	 */
	public void setFrgCrgCurCode(String frgCrgCurCode) {
		this.frgCrgCurCode = frgCrgCurCode;
	}
	/**
	 * @return the frgCrgExRate
	 */
	public BigDecimal getFrgCrgExRate() {
		return frgCrgExRate;
	}
	/**
	 * @param frgCrgExRate the frgCrgExRate to set
	 */
	public void setFrgCrgExRate(BigDecimal frgCrgExRate) {
		this.frgCrgExRate = frgCrgExRate;
	}
	/**
	 * @return the frgCrgPercent
	 */
	public BigDecimal getFrgCrgPercent() {
		return frgCrgPercent;
	}
	/**
	 * @param frgCrgPercent the frgCrgPercent to set
	 */
	public void setFrgCrgPercent(BigDecimal frgCrgPercent) {
		this.frgCrgPercent = frgCrgPercent;
	}
	/**
	 * @return the insCrgAmt
	 */
	public BigDecimal getInsCrgAmt() {
		return insCrgAmt;
	}
	/**
	 * @param insCrgAmt the insCrgAmt to set
	 */
	public void setInsCrgAmt(BigDecimal insCrgAmt) {
		this.insCrgAmt = insCrgAmt;
	}
	/**
	 * @return the insCrgCurCode
	 */
	public String getInsCrgCurCode() {
		return insCrgCurCode;
	}
	/**
	 * @param insCrgCurCode the insCrgCurCode to set
	 */
	public void setInsCrgCurCode(String insCrgCurCode) {
		this.insCrgCurCode = insCrgCurCode;
	}
	/**
	 * @return the insCrgExRate
	 */
	public BigDecimal getInsCrgExRate() {
		return insCrgExRate;
	}
	/**
	 * @param insCrgExRate the insCrgExRate to set
	 */
	public void setInsCrgExRate(BigDecimal insCrgExRate) {
		this.insCrgExRate = insCrgExRate;
	}
	/**
	 * @return the insCrgPercent
	 */
	public BigDecimal getInsCrgPercent() {
		return insCrgPercent;
	}
	/**
	 * @param insCrgPercent the insCrgPercent to set
	 */
	public void setInsCrgPercent(BigDecimal insCrgPercent) {
		this.insCrgPercent = insCrgPercent;
	}
	/**
	 * @return the othCrgAmt
	 */
	public BigDecimal getOthCrgAmt() {
		return othCrgAmt;
	}
	/**
	 * @param othCrgAmt the othCrgAmt to set
	 */
	public void setOthCrgAmt(BigDecimal othCrgAmt) {
		this.othCrgAmt = othCrgAmt;
	}
	/**
	 * @return the othCrgCurCode
	 */
	public String getOthCrgCurCode() {
		return othCrgCurCode;
	}
	/**
	 * @param othCrgCurCode the othCrgCurCode to set
	 */
	public void setOthCrgCurCode(String othCrgCurCode) {
		this.othCrgCurCode = othCrgCurCode;
	}
	/**
	 * @return the othCrgExRate
	 */
	public BigDecimal getOthCrgExRate() {
		return othCrgExRate;
	}
	/**
	 * @param othCrgExRate the othCrgExRate to set
	 */
	public void setOthCrgExRate(BigDecimal othCrgExRate) {
		this.othCrgExRate = othCrgExRate;
	}
	/**
	 * @return the othCrgPercent
	 */
	public BigDecimal getOthCrgPercent() {
		return othCrgPercent;
	}
	/**
	 * @param othCrgPercent the othCrgPercent to set
	 */
	public void setOthCrgPercent(BigDecimal othCrgPercent) {
		this.othCrgPercent = othCrgPercent;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adVolmInd == null) ? 0 : adVolmInd.hashCode());
		result = prime * result + ((frgCrgAmt == null) ? 0 : frgCrgAmt.hashCode());
		result = prime * result + ((frgCrgCurCode == null) ? 0 : frgCrgCurCode.hashCode());
		result = prime * result + ((frgCrgExRate == null) ? 0 : frgCrgExRate.hashCode());
		result = prime * result + ((frgCrgPercent == null) ? 0 : frgCrgPercent.hashCode());
		result = prime * result + ((insCrgAmt == null) ? 0 : insCrgAmt.hashCode());
		result = prime * result + ((insCrgCurCode == null) ? 0 : insCrgCurCode.hashCode());
		result = prime * result + ((insCrgExRate == null) ? 0 : insCrgExRate.hashCode());
		result = prime * result + ((insCrgPercent == null) ? 0 : insCrgPercent.hashCode());
		result = prime * result + ((invDate == null) ? 0 : invDate.hashCode());
		result = prime * result + ((invNo == null) ? 0 : invNo.hashCode());
		result = prime * result + ((invSeqNo == null) ? 0 : invSeqNo.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((othCrgAmt == null) ? 0 : othCrgAmt.hashCode());
		result = prime * result + ((othCrgCurCode == null) ? 0 : othCrgCurCode.hashCode());
		result = prime * result + ((othCrgExRate == null) ? 0 : othCrgExRate.hashCode());
		result = prime * result + ((othCrgPercent == null) ? 0 : othCrgPercent.hashCode());
		result = prime * result + ((partyCode == null) ? 0 : partyCode.hashCode());
		result = prime * result + ((partyName1 == null) ? 0 : partyName1.hashCode());
		result = prime * result + ((partyName2 == null) ? 0 : partyName2.hashCode());
		result = prime * result + ((partyName3 == null) ? 0 : partyName3.hashCode());
		result = prime * result + ((prefDutyInd == null) ? 0 : prefDutyInd.hashCode());
		result = prime * result + ((siRelation == null) ? 0 : siRelation.hashCode());
		result = prime * result + ((totInvAmt == null) ? 0 : totInvAmt.hashCode());
		result = prime * result + ((totInvCrcType == null) ? 0 : totInvCrcType.hashCode());
		result = prime * result + ((totInvExRate == null) ? 0 : totInvExRate.hashCode());
		result = prime * result + ((uPriceTermType == null) ? 0 : uPriceTermType.hashCode());
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
		if (!(obj instanceof PermitInvoice)) {
			return false;
		}
		PermitInvoice other = (PermitInvoice) obj;
		if (adVolmInd == null) {
			if (other.adVolmInd != null) {
				return false;
			}
		} else if (!adVolmInd.equals(other.adVolmInd)) {
			return false;
		}
		if (frgCrgAmt == null) {
			if (other.frgCrgAmt != null) {
				return false;
			}
		} else if (!frgCrgAmt.equals(other.frgCrgAmt)) {
			return false;
		}
		if (frgCrgCurCode == null) {
			if (other.frgCrgCurCode != null) {
				return false;
			}
		} else if (!frgCrgCurCode.equals(other.frgCrgCurCode)) {
			return false;
		}
		if (frgCrgExRate == null) {
			if (other.frgCrgExRate != null) {
				return false;
			}
		} else if (!frgCrgExRate.equals(other.frgCrgExRate)) {
			return false;
		}
		if (frgCrgPercent == null) {
			if (other.frgCrgPercent != null) {
				return false;
			}
		} else if (!frgCrgPercent.equals(other.frgCrgPercent)) {
			return false;
		}
		if (insCrgAmt == null) {
			if (other.insCrgAmt != null) {
				return false;
			}
		} else if (!insCrgAmt.equals(other.insCrgAmt)) {
			return false;
		}
		if (insCrgCurCode == null) {
			if (other.insCrgCurCode != null) {
				return false;
			}
		} else if (!insCrgCurCode.equals(other.insCrgCurCode)) {
			return false;
		}
		if (insCrgExRate == null) {
			if (other.insCrgExRate != null) {
				return false;
			}
		} else if (!insCrgExRate.equals(other.insCrgExRate)) {
			return false;
		}
		if (insCrgPercent == null) {
			if (other.insCrgPercent != null) {
				return false;
			}
		} else if (!insCrgPercent.equals(other.insCrgPercent)) {
			return false;
		}
		if (invDate == null) {
			if (other.invDate != null) {
				return false;
			}
		} else if (!invDate.equals(other.invDate)) {
			return false;
		}
		if (invNo == null) {
			if (other.invNo != null) {
				return false;
			}
		} else if (!invNo.equals(other.invNo)) {
			return false;
		}
		if (invSeqNo == null) {
			if (other.invSeqNo != null) {
				return false;
			}
		} else if (!invSeqNo.equals(other.invSeqNo)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (othCrgAmt == null) {
			if (other.othCrgAmt != null) {
				return false;
			}
		} else if (!othCrgAmt.equals(other.othCrgAmt)) {
			return false;
		}
		if (othCrgCurCode == null) {
			if (other.othCrgCurCode != null) {
				return false;
			}
		} else if (!othCrgCurCode.equals(other.othCrgCurCode)) {
			return false;
		}
		if (othCrgExRate == null) {
			if (other.othCrgExRate != null) {
				return false;
			}
		} else if (!othCrgExRate.equals(other.othCrgExRate)) {
			return false;
		}
		if (othCrgPercent == null) {
			if (other.othCrgPercent != null) {
				return false;
			}
		} else if (!othCrgPercent.equals(other.othCrgPercent)) {
			return false;
		}
		if (partyCode == null) {
			if (other.partyCode != null) {
				return false;
			}
		} else if (!partyCode.equals(other.partyCode)) {
			return false;
		}
		if (partyName1 == null) {
			if (other.partyName1 != null) {
				return false;
			}
		} else if (!partyName1.equals(other.partyName1)) {
			return false;
		}
		if (partyName2 == null) {
			if (other.partyName2 != null) {
				return false;
			}
		} else if (!partyName2.equals(other.partyName2)) {
			return false;
		}
		if (partyName3 == null) {
			if (other.partyName3 != null) {
				return false;
			}
		} else if (!partyName3.equals(other.partyName3)) {
			return false;
		}
		if (prefDutyInd == null) {
			if (other.prefDutyInd != null) {
				return false;
			}
		} else if (!prefDutyInd.equals(other.prefDutyInd)) {
			return false;
		}
		if (siRelation == null) {
			if (other.siRelation != null) {
				return false;
			}
		} else if (!siRelation.equals(other.siRelation)) {
			return false;
		}
		if (totInvAmt == null) {
			if (other.totInvAmt != null) {
				return false;
			}
		} else if (!totInvAmt.equals(other.totInvAmt)) {
			return false;
		}
		if (totInvCrcType == null) {
			if (other.totInvCrcType != null) {
				return false;
			}
		} else if (!totInvCrcType.equals(other.totInvCrcType)) {
			return false;
		}
		if (totInvExRate == null) {
			if (other.totInvExRate != null) {
				return false;
			}
		} else if (!totInvExRate.equals(other.totInvExRate)) {
			return false;
		}
		if (uPriceTermType == null) {
			if (other.uPriceTermType != null) {
				return false;
			}
		} else if (!uPriceTermType.equals(other.uPriceTermType)) {
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
