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
@Table(name="BCTS_IN_TRANSPORT")
public class PermitInTransport implements Serializable {

	private static final long serialVersionUID = 7222299725833510862L;
	@Id
	@Column(name="USER_ID")
	private String ureId; 
	@Id
	@Column(name="JOB_NO")
	private String ureSeq;
	@Column(name="TRANS_MODE_CODE")
	private String transModeCode;
	@Column(name="VOYAGE_NO")
	private String voyageNo;
	@Column(name="VES_NAME")
	private String vesName;
	@Column(name="AIRCRAFT_ID")
	private String aircraftId;
	@Column(name="MASTER_SHIPDOC_NO")
	private String mastShipDocNo;
	@Column(name="VES_LOC_CODE")
	private String vesLocCode;
	@Column(name="VES_LOC_NAME")
	private String vesLocName;
	@Column(name="ARRIVAL_DATE")
	private Date arraivalDate;
	@Column(name="LOADING_PORT_CODE")
	private String loadingPortCode;
	@Column(name="LOADING_PORT_DESC")
	private String loadingPortDesc;
	@Column(name="TRSPT_IDENTIFIER")
	private String transPrtIdentifier;
	
	
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
	 * @return the transModeCode
	 */
	public String getTransModeCode() {
		return transModeCode;
	}
	/**
	 * @param transModeCode the transModeCode to set
	 */
	public void setTransModeCode(String transModeCode) {
		this.transModeCode = transModeCode;
	}
	/**
	 * @return the voyageNo
	 */
	public String getVoyageNo() {
		return voyageNo;
	}
	/**
	 * @param voyageNo the voyageNo to set
	 */
	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
	}
	/**
	 * @return the vesName
	 */
	public String getVesName() {
		return vesName;
	}
	/**
	 * @param vesName the vesName to set
	 */
	public void setVesName(String vesName) {
		this.vesName = vesName;
	}
	/**
	 * @return the aircraftId
	 */
	public String getAircraftId() {
		return aircraftId;
	}
	/**
	 * @param aircraftId the aircraftId to set
	 */
	public void setAircraftId(String aircraftId) {
		this.aircraftId = aircraftId;
	}
	/**
	 * @return the mastShipDocNo
	 */
	public String getMastShipDocNo() {
		return mastShipDocNo;
	}
	/**
	 * @param mastShipDocNo the mastShipDocNo to set
	 */
	public void setMastShipDocNo(String mastShipDocNo) {
		this.mastShipDocNo = mastShipDocNo;
	}
	/**
	 * @return the vesLocCode
	 */
	public String getVesLocCode() {
		return vesLocCode;
	}
	/**
	 * @param vesLocCode the vesLocCode to set
	 */
	public void setVesLocCode(String vesLocCode) {
		this.vesLocCode = vesLocCode;
	}
	/**
	 * @return the vesLocName
	 */
	public String getVesLocName() {
		return vesLocName;
	}
	/**
	 * @param vesLocName the vesLocName to set
	 */
	public void setVesLocName(String vesLocName) {
		this.vesLocName = vesLocName;
	}
	/**
	 * @return the arraivalDate
	 */
	public Date getArraivalDate() {
		return arraivalDate;
	}
	/**
	 * @param arraivalDate the arraivalDate to set
	 */
	public void setArraivalDate(Date arraivalDate) {
		this.arraivalDate = arraivalDate;
	}
	/**
	 * @return the loadingPortCode
	 */
	public String getLoadingPortCode() {
		return loadingPortCode;
	}
	/**
	 * @param loadingPortCode the loadingPortCode to set
	 */
	public void setLoadingPortCode(String loadingPortCode) {
		this.loadingPortCode = loadingPortCode;
	}
	/**
	 * @return the loadingPortDesc
	 */
	public String getLoadingPortDesc() {
		return loadingPortDesc;
	}
	/**
	 * @param loadingPortDesc the loadingPortDesc to set
	 */
	public void setLoadingPortDesc(String loadingPortDesc) {
		this.loadingPortDesc = loadingPortDesc;
	}
	/**
	 * @return the transPrtIdentifier
	 */
	public String getTransPrtIdentifier() {
		return transPrtIdentifier;
	}
	/**
	 * @param transPrtIdentifier the transPrtIdentifier to set
	 */
	public void setTransPrtIdentifier(String transPrtIdentifier) {
		this.transPrtIdentifier = transPrtIdentifier;
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
		result = prime * result + ((aircraftId == null) ? 0 : aircraftId.hashCode());
		result = prime * result + ((arraivalDate == null) ? 0 : arraivalDate.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((loadingPortCode == null) ? 0 : loadingPortCode.hashCode());
		result = prime * result + ((loadingPortDesc == null) ? 0 : loadingPortDesc.hashCode());
		result = prime * result + ((mastShipDocNo == null) ? 0 : mastShipDocNo.hashCode());
		result = prime * result + ((transModeCode == null) ? 0 : transModeCode.hashCode());
		result = prime * result + ((transPrtIdentifier == null) ? 0 : transPrtIdentifier.hashCode());
		result = prime * result + ((ureId == null) ? 0 : ureId.hashCode());
		result = prime * result + ((ureSeq == null) ? 0 : ureSeq.hashCode());
		result = prime * result + ((vesLocCode == null) ? 0 : vesLocCode.hashCode());
		result = prime * result + ((vesLocName == null) ? 0 : vesLocName.hashCode());
		result = prime * result + ((vesName == null) ? 0 : vesName.hashCode());
		result = prime * result + ((voyageNo == null) ? 0 : voyageNo.hashCode());
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
		if (!(obj instanceof PermitInTransport)) {
			return false;
		}
		PermitInTransport other = (PermitInTransport) obj;
		if (aircraftId == null) {
			if (other.aircraftId != null) {
				return false;
			}
		} else if (!aircraftId.equals(other.aircraftId)) {
			return false;
		}
		if (arraivalDate == null) {
			if (other.arraivalDate != null) {
				return false;
			}
		} else if (!arraivalDate.equals(other.arraivalDate)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (loadingPortCode == null) {
			if (other.loadingPortCode != null) {
				return false;
			}
		} else if (!loadingPortCode.equals(other.loadingPortCode)) {
			return false;
		}
		if (loadingPortDesc == null) {
			if (other.loadingPortDesc != null) {
				return false;
			}
		} else if (!loadingPortDesc.equals(other.loadingPortDesc)) {
			return false;
		}
		if (mastShipDocNo == null) {
			if (other.mastShipDocNo != null) {
				return false;
			}
		} else if (!mastShipDocNo.equals(other.mastShipDocNo)) {
			return false;
		}
		if (transModeCode == null) {
			if (other.transModeCode != null) {
				return false;
			}
		} else if (!transModeCode.equals(other.transModeCode)) {
			return false;
		}
		if (transPrtIdentifier == null) {
			if (other.transPrtIdentifier != null) {
				return false;
			}
		} else if (!transPrtIdentifier.equals(other.transPrtIdentifier)) {
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
		if (vesLocCode == null) {
			if (other.vesLocCode != null) {
				return false;
			}
		} else if (!vesLocCode.equals(other.vesLocCode)) {
			return false;
		}
		if (vesLocName == null) {
			if (other.vesLocName != null) {
				return false;
			}
		} else if (!vesLocName.equals(other.vesLocName)) {
			return false;
		}
		if (vesName == null) {
			if (other.vesName != null) {
				return false;
			}
		} else if (!vesName.equals(other.vesName)) {
			return false;
		}
		if (voyageNo == null) {
			if (other.voyageNo != null) {
				return false;
			}
		} else if (!voyageNo.equals(other.voyageNo)) {
			return false;
		}
		return true;
	}
	
	
	

}
