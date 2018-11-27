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
@Table(name="BCTS_CARGO_CTN")
public class PermitCargoCtn implements Serializable{

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
	
	@Column(name="CTN_NO")
	private String ctnNo;
	@Column(name="CTN_SIZE_TYPE")
	private String ctnSize;
	@Column(name="CTN_WT")
	private BigDecimal ctnWgt;
	@Column(name="CTN_SEAL_NO")
	private String ctnSealNo;
	@Id
	@Column(name="SEQ_NO")
	private BigDecimal seqNo;
	
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="USER_ID", insertable=false, updatable=false),
		@JoinColumn(name="JOB_NO", insertable=false, updatable=false)
	})
	private PermitCargo permitCargo;
	
	
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
	 * @return the ctnNo
	 */
	public String getCtnNo() {
		return ctnNo;
	}
	/**
	 * @param ctnNo the ctnNo to set
	 */
	public void setCtnNo(String ctnNo) {
		this.ctnNo = ctnNo;
	}
	/**
	 * @return the ctnSize
	 */
	public String getCtnSize() {
		return ctnSize;
	}
	/**
	 * @param ctnSize the ctnSize to set
	 */
	public void setCtnSize(String ctnSize) {
		this.ctnSize = ctnSize;
	}
	/**
	 * @return the ctnWgt
	 */
	public BigDecimal getCtnWgt() {
		return ctnWgt;
	}
	/**
	 * @param ctnWgt the ctnWgt to set
	 */
	public void setCtnWgt(BigDecimal ctnWgt) {
		this.ctnWgt = ctnWgt;
	}
	/**
	 * @return the ctnSealNo
	 */
	public String getCtnSealNo() {
		return ctnSealNo;
	}
	/**
	 * @param ctnSealNo the ctnSealNo to set
	 */
	public void setCtnSealNo(String ctnSealNo) {
		this.ctnSealNo = ctnSealNo;
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
	 * @return the permitCargo
	 */
	public PermitCargo getPermitCargo() {
		return permitCargo;
	}
	/**
	 * @param permitCargo the permitCargo to set
	 */
	public void setPermitCargo(PermitCargo permitCargo) {
		this.permitCargo = permitCargo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctnNo == null) ? 0 : ctnNo.hashCode());
		result = prime * result + ((ctnSealNo == null) ? 0 : ctnSealNo.hashCode());
		result = prime * result + ((ctnSize == null) ? 0 : ctnSize.hashCode());
		result = prime * result + ((ctnWgt == null) ? 0 : ctnWgt.hashCode());
		result = prime * result + ((permitCargo == null) ? 0 : permitCargo.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
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
		if (!(obj instanceof PermitCargoCtn)) {
			return false;
		}
		PermitCargoCtn other = (PermitCargoCtn) obj;
		if (ctnNo == null) {
			if (other.ctnNo != null) {
				return false;
			}
		} else if (!ctnNo.equals(other.ctnNo)) {
			return false;
		}
		if (ctnSealNo == null) {
			if (other.ctnSealNo != null) {
				return false;
			}
		} else if (!ctnSealNo.equals(other.ctnSealNo)) {
			return false;
		}
		if (ctnSize == null) {
			if (other.ctnSize != null) {
				return false;
			}
		} else if (!ctnSize.equals(other.ctnSize)) {
			return false;
		}
		if (ctnWgt == null) {
			if (other.ctnWgt != null) {
				return false;
			}
		} else if (!ctnWgt.equals(other.ctnWgt)) {
			return false;
		}
		if (permitCargo == null) {
			if (other.permitCargo != null) {
				return false;
			}
		} else if (!permitCargo.equals(other.permitCargo)) {
			return false;
		}
		if (seqNo == null) {
			if (other.seqNo != null) {
				return false;
			}
		} else if (!seqNo.equals(other.seqNo)) {
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
