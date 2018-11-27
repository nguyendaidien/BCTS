/**
 * @author ajayasamanta
 */
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
@Table(name="BCTS_SUMMARY")
public class PermitSummary implements Serializable {

	private static final long serialVersionUID = 8151443396233700289L;
	
	@Id
	@Column(name="USER_ID")
	private String ureId;
	@Id
	@Column(name="JOB_NO")
	private String ureSeq;
	
	@Column(name="TOT_CIF_FOB")
	private BigDecimal totCifFob;
	@Column(name="TOT_OUT_PACK")
	private BigDecimal totOutPck;
	@Column(name="TOT_OUT_PACK_UOM")
	private String totOutPckUom;
	@Column(name="TOT_GROSS_WT")
	private BigDecimal totGrossWgt;
	@Column(name="TOT_GROSS_WT_UOM")
	private String totGrossWgtUom;
	@Column(name="TOT_GST_AMT")
	private BigDecimal totGstAmt;
	@Column(name="TOT_EXCISE_AMT")
	private BigDecimal totExciAmt;
	@Column(name="TOT_CUSTOMS_AMT")
	private BigDecimal totCustAmt;
	@Column(name="TOT_AMT_PAYABLE")
	private BigDecimal totAmtPayble;
	@Column(name="TOT_REFUND_GST_AMT")
	private BigDecimal totRefGstAmt;
	@Column(name="TOT_REFUND_EXCISE_AMT")
	private BigDecimal totRefExcAmt;
	@Column(name="TOT_REFUND_CUSTOMS_AMT")
	private BigDecimal totRefCustAmt;
	@Column(name="DEC_STMT_IND")
	private String decStmtInd;
	@Column(name="CO_STMT_IND")
	private String coStmtInd;
	@Column(name="REMARKS")
	private String remarks;
	@Column(name="TOT_OTHER_TAX_DUTY_AMT")
	private BigDecimal totOthTxDutyAmt;
	@Column(name="TOT_REFUND_OTHERTAX_DUTY_AMT")
	private BigDecimal totRefOthTaxAmt;
	@Column(name="NO_OF_ITEMS")
	private BigDecimal noOfItems;
	
	
	
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
	 * @return the totCifFob
	 */
	public BigDecimal getTotCifFob() {
		return totCifFob;
	}
	/**
	 * @param totCifFob the totCifFob to set
	 */
	public void setTotCifFob(BigDecimal totCifFob) {
		this.totCifFob = totCifFob;
	}
	/**
	 * @return the totOutPck
	 */
	public BigDecimal getTotOutPck() {
		return totOutPck;
	}
	/**
	 * @param totOutPck the totOutPck to set
	 */
	public void setTotOutPck(BigDecimal totOutPck) {
		this.totOutPck = totOutPck;
	}
	/**
	 * @return the totOutPckUom
	 */
	public String getTotOutPckUom() {
		return totOutPckUom;
	}
	/**
	 * @param totOutPckUom the totOutPckUom to set
	 */
	public void setTotOutPckUom(String totOutPckUom) {
		this.totOutPckUom = totOutPckUom;
	}
	/**
	 * @return the totGrossWgt
	 */
	public BigDecimal getTotGrossWgt() {
		return totGrossWgt;
	}
	/**
	 * @param totGrossWgt the totGrossWgt to set
	 */
	public void setTotGrossWgt(BigDecimal totGrossWgt) {
		this.totGrossWgt = totGrossWgt;
	}
	/**
	 * @return the totGrossWgtUom
	 */
	public String getTotGrossWgtUom() {
		return totGrossWgtUom;
	}
	/**
	 * @param totGrossWgtUom the totGrossWgtUom to set
	 */
	public void setTotGrossWgtUom(String totGrossWgtUom) {
		this.totGrossWgtUom = totGrossWgtUom;
	}
	/**
	 * @return the totGstAmt
	 */
	public BigDecimal getTotGstAmt() {
		return totGstAmt;
	}
	/**
	 * @param totGstAmt the totGstAmt to set
	 */
	public void setTotGstAmt(BigDecimal totGstAmt) {
		this.totGstAmt = totGstAmt;
	}
	/**
	 * @return the totExciAmt
	 */
	public BigDecimal getTotExciAmt() {
		return totExciAmt;
	}
	/**
	 * @param totExciAmt the totExciAmt to set
	 */
	public void setTotExciAmt(BigDecimal totExciAmt) {
		this.totExciAmt = totExciAmt;
	}
	/**
	 * @return the totCustAmt
	 */
	public BigDecimal getTotCustAmt() {
		return totCustAmt;
	}
	/**
	 * @param totCustAmt the totCustAmt to set
	 */
	public void setTotCustAmt(BigDecimal totCustAmt) {
		this.totCustAmt = totCustAmt;
	}
	/**
	 * @return the totAmtPayble
	 */
	public BigDecimal getTotAmtPayble() {
		return totAmtPayble;
	}
	/**
	 * @param totAmtPayble the totAmtPayble to set
	 */
	public void setTotAmtPayble(BigDecimal totAmtPayble) {
		this.totAmtPayble = totAmtPayble;
	}
	/**
	 * @return the totRefGstAmt
	 */
	public BigDecimal getTotRefGstAmt() {
		return totRefGstAmt;
	}
	/**
	 * @param totRefGstAmt the totRefGstAmt to set
	 */
	public void setTotRefGstAmt(BigDecimal totRefGstAmt) {
		this.totRefGstAmt = totRefGstAmt;
	}
	/**
	 * @return the totRefExcAmt
	 */
	public BigDecimal getTotRefExcAmt() {
		return totRefExcAmt;
	}
	/**
	 * @param totRefExcAmt the totRefExcAmt to set
	 */
	public void setTotRefExcAmt(BigDecimal totRefExcAmt) {
		this.totRefExcAmt = totRefExcAmt;
	}
	/**
	 * @return the totRefCustAmt
	 */
	public BigDecimal getTotRefCustAmt() {
		return totRefCustAmt;
	}
	/**
	 * @param totRefCustAmt the totRefCustAmt to set
	 */
	public void setTotRefCustAmt(BigDecimal totRefCustAmt) {
		this.totRefCustAmt = totRefCustAmt;
	}
	/**
	 * @return the decStmtInd
	 */
	public String getDecStmtInd() {
		return decStmtInd;
	}
	/**
	 * @param decStmtInd the decStmtInd to set
	 */
	public void setDecStmtInd(String decStmtInd) {
		this.decStmtInd = decStmtInd;
	}
	/**
	 * @return the coStmtInd
	 */
	public String getCoStmtInd() {
		return coStmtInd;
	}
	/**
	 * @param coStmtInd the coStmtInd to set
	 */
	public void setCoStmtInd(String coStmtInd) {
		this.coStmtInd = coStmtInd;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the totOthTxDutyAmt
	 */
	public BigDecimal getTotOthTxDutyAmt() {
		return totOthTxDutyAmt;
	}
	/**
	 * @param totOthTxDutyAmt the totOthTxDutyAmt to set
	 */
	public void setTotOthTxDutyAmt(BigDecimal totOthTxDutyAmt) {
		this.totOthTxDutyAmt = totOthTxDutyAmt;
	}
	/**
	 * @return the totRefOthTaxAmt
	 */
	public BigDecimal getTotRefOthTaxAmt() {
		return totRefOthTaxAmt;
	}
	/**
	 * @param totRefOthTaxAmt the totRefOthTaxAmt to set
	 */
	public void setTotRefOthTaxAmt(BigDecimal totRefOthTaxAmt) {
		this.totRefOthTaxAmt = totRefOthTaxAmt;
	}
	/**
	 * @return the noOfItems
	 */
	public BigDecimal getNoOfItems() {
		return noOfItems;
	}
	/**
	 * @param noOfItems the noOfItems to set
	 */
	public void setNoOfItems(BigDecimal noOfItems) {
		this.noOfItems = noOfItems;
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
		result = prime * result + ((coStmtInd == null) ? 0 : coStmtInd.hashCode());
		result = prime * result + ((decStmtInd == null) ? 0 : decStmtInd.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((noOfItems == null) ? 0 : noOfItems.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((totAmtPayble == null) ? 0 : totAmtPayble.hashCode());
		result = prime * result + ((totCifFob == null) ? 0 : totCifFob.hashCode());
		result = prime * result + ((totCustAmt == null) ? 0 : totCustAmt.hashCode());
		result = prime * result + ((totExciAmt == null) ? 0 : totExciAmt.hashCode());
		result = prime * result + ((totGrossWgt == null) ? 0 : totGrossWgt.hashCode());
		result = prime * result + ((totGrossWgtUom == null) ? 0 : totGrossWgtUom.hashCode());
		result = prime * result + ((totGstAmt == null) ? 0 : totGstAmt.hashCode());
		result = prime * result + ((totOthTxDutyAmt == null) ? 0 : totOthTxDutyAmt.hashCode());
		result = prime * result + ((totOutPck == null) ? 0 : totOutPck.hashCode());
		result = prime * result + ((totOutPckUom == null) ? 0 : totOutPckUom.hashCode());
		result = prime * result + ((totRefCustAmt == null) ? 0 : totRefCustAmt.hashCode());
		result = prime * result + ((totRefExcAmt == null) ? 0 : totRefExcAmt.hashCode());
		result = prime * result + ((totRefGstAmt == null) ? 0 : totRefGstAmt.hashCode());
		result = prime * result + ((totRefOthTaxAmt == null) ? 0 : totRefOthTaxAmt.hashCode());
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
		if (!(obj instanceof PermitSummary)) {
			return false;
		}
		PermitSummary other = (PermitSummary) obj;
		if (coStmtInd == null) {
			if (other.coStmtInd != null) {
				return false;
			}
		} else if (!coStmtInd.equals(other.coStmtInd)) {
			return false;
		}
		if (decStmtInd == null) {
			if (other.decStmtInd != null) {
				return false;
			}
		} else if (!decStmtInd.equals(other.decStmtInd)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (noOfItems == null) {
			if (other.noOfItems != null) {
				return false;
			}
		} else if (!noOfItems.equals(other.noOfItems)) {
			return false;
		}
		if (remarks == null) {
			if (other.remarks != null) {
				return false;
			}
		} else if (!remarks.equals(other.remarks)) {
			return false;
		}
		if (totAmtPayble == null) {
			if (other.totAmtPayble != null) {
				return false;
			}
		} else if (!totAmtPayble.equals(other.totAmtPayble)) {
			return false;
		}
		if (totCifFob == null) {
			if (other.totCifFob != null) {
				return false;
			}
		} else if (!totCifFob.equals(other.totCifFob)) {
			return false;
		}
		if (totCustAmt == null) {
			if (other.totCustAmt != null) {
				return false;
			}
		} else if (!totCustAmt.equals(other.totCustAmt)) {
			return false;
		}
		if (totExciAmt == null) {
			if (other.totExciAmt != null) {
				return false;
			}
		} else if (!totExciAmt.equals(other.totExciAmt)) {
			return false;
		}
		if (totGrossWgt == null) {
			if (other.totGrossWgt != null) {
				return false;
			}
		} else if (!totGrossWgt.equals(other.totGrossWgt)) {
			return false;
		}
		if (totGrossWgtUom == null) {
			if (other.totGrossWgtUom != null) {
				return false;
			}
		} else if (!totGrossWgtUom.equals(other.totGrossWgtUom)) {
			return false;
		}
		if (totGstAmt == null) {
			if (other.totGstAmt != null) {
				return false;
			}
		} else if (!totGstAmt.equals(other.totGstAmt)) {
			return false;
		}
		if (totOthTxDutyAmt == null) {
			if (other.totOthTxDutyAmt != null) {
				return false;
			}
		} else if (!totOthTxDutyAmt.equals(other.totOthTxDutyAmt)) {
			return false;
		}
		if (totOutPck == null) {
			if (other.totOutPck != null) {
				return false;
			}
		} else if (!totOutPck.equals(other.totOutPck)) {
			return false;
		}
		if (totOutPckUom == null) {
			if (other.totOutPckUom != null) {
				return false;
			}
		} else if (!totOutPckUom.equals(other.totOutPckUom)) {
			return false;
		}
		if (totRefCustAmt == null) {
			if (other.totRefCustAmt != null) {
				return false;
			}
		} else if (!totRefCustAmt.equals(other.totRefCustAmt)) {
			return false;
		}
		if (totRefExcAmt == null) {
			if (other.totRefExcAmt != null) {
				return false;
			}
		} else if (!totRefExcAmt.equals(other.totRefExcAmt)) {
			return false;
		}
		if (totRefGstAmt == null) {
			if (other.totRefGstAmt != null) {
				return false;
			}
		} else if (!totRefGstAmt.equals(other.totRefGstAmt)) {
			return false;
		}
		if (totRefOthTaxAmt == null) {
			if (other.totRefOthTaxAmt != null) {
				return false;
			}
		} else if (!totRefOthTaxAmt.equals(other.totRefOthTaxAmt)) {
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
