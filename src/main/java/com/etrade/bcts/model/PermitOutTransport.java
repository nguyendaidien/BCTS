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
@Table(name="BCTS_OUT_TRANSPORT")
public class PermitOutTransport implements Serializable {
	
	/**
	 * 
	 */
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
	@Column(name="VES_TYPE")
	private String vesType;
	@Column(name="VES_NATION")
	private String vesNation;
	@Column(name="VES_NATION_DESC")
	private String vesNatDesc;
	@Column(name="TOW_VOY_NO")
	private String towVoyNo;
	@Column(name="TOW_VES_NAME")
	private String towVesName;
	@Column(name="NEXT_PORT_CODE")
	private String nextPortCode;
	@Column(name="NEXT_PORT_DESC")
	private String nextPortDesc;
	@Column(name="FINAL_PORT_CODE")
	private String finalPortCode;
	@Column(name="FINAL_PORT_DESC")
	private String finalPortDesc;
	@Column(name="DEPART_DATE")
	private Date departDate;
	@Column(name="DISCHARGE_PORT_CODE")
	private String disPortCode;
	@Column(name="DISCHARGE_PORT_DESC")
	private String disPortDesc;
	@Column(name="FINAL_CTRY_CODE")
	private String finalCtryCode;
	@Column(name="FINAL_CTRY_DESC")
	private String finalCtryDesc;
	@Column(name="SEA_STORE_IND")
	private String seaStrorInd;
	@Column(name="NRT")
	private BigDecimal nrt;
	@Column(name="VES_LOC_CODE")
	private String vesLocCode;
	@Column(name="VES_LOC_NAME")
	private String vesLocName;
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
	 * @return the vesType
	 */
	public String getVesType() {
		return vesType;
	}
	/**
	 * @param vesType the vesType to set
	 */
	public void setVesType(String vesType) {
		this.vesType = vesType;
	}
	/**
	 * @return the vesNation
	 */
	public String getVesNation() {
		return vesNation;
	}
	/**
	 * @param vesNation the vesNation to set
	 */
	public void setVesNation(String vesNation) {
		this.vesNation = vesNation;
	}
	/**
	 * @return the vesNatDesc
	 */
	public String getVesNatDesc() {
		return vesNatDesc;
	}
	/**
	 * @param vesNatDesc the vesNatDesc to set
	 */
	public void setVesNatDesc(String vesNatDesc) {
		this.vesNatDesc = vesNatDesc;
	}
	/**
	 * @return the towVoyNo
	 */
	public String getTowVoyNo() {
		return towVoyNo;
	}
	/**
	 * @param towVoyNo the towVoyNo to set
	 */
	public void setTowVoyNo(String towVoyNo) {
		this.towVoyNo = towVoyNo;
	}
	/**
	 * @return the towVesName
	 */
	public String getTowVesName() {
		return towVesName;
	}
	/**
	 * @param towVesName the towVesName to set
	 */
	public void setTowVesName(String towVesName) {
		this.towVesName = towVesName;
	}
	/**
	 * @return the nextPortCode
	 */
	public String getNextPortCode() {
		return nextPortCode;
	}
	/**
	 * @param nextPortCode the nextPortCode to set
	 */
	public void setNextPortCode(String nextPortCode) {
		this.nextPortCode = nextPortCode;
	}
	/**
	 * @return the nextPortDesc
	 */
	public String getNextPortDesc() {
		return nextPortDesc;
	}
	/**
	 * @param nextPortDesc the nextPortDesc to set
	 */
	public void setNextPortDesc(String nextPortDesc) {
		this.nextPortDesc = nextPortDesc;
	}
	/**
	 * @return the finalPortCode
	 */
	public String getFinalPortCode() {
		return finalPortCode;
	}
	/**
	 * @param finalPortCode the finalPortCode to set
	 */
	public void setFinalPortCode(String finalPortCode) {
		this.finalPortCode = finalPortCode;
	}
	/**
	 * @return the finalPortDesc
	 */
	public String getFinalPortDesc() {
		return finalPortDesc;
	}
	/**
	 * @param finalPortDesc the finalPortDesc to set
	 */
	public void setFinalPortDesc(String finalPortDesc) {
		this.finalPortDesc = finalPortDesc;
	}
	/**
	 * @return the departDate
	 */
	public Date getDepartDate() {
		return departDate;
	}
	/**
	 * @param departDate the departDate to set
	 */
	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}
	/**
	 * @return the disPortCode
	 */
	public String getDisPortCode() {
		return disPortCode;
	}
	/**
	 * @param disPortCode the disPortCode to set
	 */
	public void setDisPortCode(String disPortCode) {
		this.disPortCode = disPortCode;
	}
	/**
	 * @return the disPortDesc
	 */
	public String getDisPortDesc() {
		return disPortDesc;
	}
	/**
	 * @param disPortDesc the disPortDesc to set
	 */
	public void setDisPortDesc(String disPortDesc) {
		this.disPortDesc = disPortDesc;
	}
	/**
	 * @return the finalCtryCode
	 */
	public String getFinalCtryCode() {
		return finalCtryCode;
	}
	/**
	 * @param finalCtryCode the finalCtryCode to set
	 */
	public void setFinalCtryCode(String finalCtryCode) {
		this.finalCtryCode = finalCtryCode;
	}
	/**
	 * @return the finalCtryDesc
	 */
	public String getFinalCtryDesc() {
		return finalCtryDesc;
	}
	/**
	 * @param finalCtryDesc the finalCtryDesc to set
	 */
	public void setFinalCtryDesc(String finalCtryDesc) {
		this.finalCtryDesc = finalCtryDesc;
	}
	/**
	 * @return the seaStrorInd
	 */
	public String getSeaStrorInd() {
		return seaStrorInd;
	}
	/**
	 * @param seaStrorInd the seaStrorInd to set
	 */
	public void setSeaStrorInd(String seaStrorInd) {
		this.seaStrorInd = seaStrorInd;
	}
	/**
	 * @return the nrt
	 */
	public BigDecimal getNrt() {
		return nrt;
	}
	/**
	 * @param nrt the nrt to set
	 */
	public void setNrt(BigDecimal nrt) {
		this.nrt = nrt;
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
		result = prime * result + ((departDate == null) ? 0 : departDate.hashCode());
		result = prime * result + ((disPortCode == null) ? 0 : disPortCode.hashCode());
		result = prime * result + ((disPortDesc == null) ? 0 : disPortDesc.hashCode());
		result = prime * result + ((finalCtryCode == null) ? 0 : finalCtryCode.hashCode());
		result = prime * result + ((finalCtryDesc == null) ? 0 : finalCtryDesc.hashCode());
		result = prime * result + ((finalPortCode == null) ? 0 : finalPortCode.hashCode());
		result = prime * result + ((finalPortDesc == null) ? 0 : finalPortDesc.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((mastShipDocNo == null) ? 0 : mastShipDocNo.hashCode());
		result = prime * result + ((nextPortCode == null) ? 0 : nextPortCode.hashCode());
		result = prime * result + ((nextPortDesc == null) ? 0 : nextPortDesc.hashCode());
		result = prime * result + ((nrt == null) ? 0 : nrt.hashCode());
		result = prime * result + ((seaStrorInd == null) ? 0 : seaStrorInd.hashCode());
		result = prime * result + ((towVesName == null) ? 0 : towVesName.hashCode());
		result = prime * result + ((towVoyNo == null) ? 0 : towVoyNo.hashCode());
		result = prime * result + ((transModeCode == null) ? 0 : transModeCode.hashCode());
		result = prime * result + ((transPrtIdentifier == null) ? 0 : transPrtIdentifier.hashCode());
		result = prime * result + ((ureId == null) ? 0 : ureId.hashCode());
		result = prime * result + ((ureSeq == null) ? 0 : ureSeq.hashCode());
		result = prime * result + ((vesLocCode == null) ? 0 : vesLocCode.hashCode());
		result = prime * result + ((vesLocName == null) ? 0 : vesLocName.hashCode());
		result = prime * result + ((vesName == null) ? 0 : vesName.hashCode());
		result = prime * result + ((vesNatDesc == null) ? 0 : vesNatDesc.hashCode());
		result = prime * result + ((vesNation == null) ? 0 : vesNation.hashCode());
		result = prime * result + ((vesType == null) ? 0 : vesType.hashCode());
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
		if (!(obj instanceof PermitOutTransport)) {
			return false;
		}
		PermitOutTransport other = (PermitOutTransport) obj;
		if (aircraftId == null) {
			if (other.aircraftId != null) {
				return false;
			}
		} else if (!aircraftId.equals(other.aircraftId)) {
			return false;
		}
		if (departDate == null) {
			if (other.departDate != null) {
				return false;
			}
		} else if (!departDate.equals(other.departDate)) {
			return false;
		}
		if (disPortCode == null) {
			if (other.disPortCode != null) {
				return false;
			}
		} else if (!disPortCode.equals(other.disPortCode)) {
			return false;
		}
		if (disPortDesc == null) {
			if (other.disPortDesc != null) {
				return false;
			}
		} else if (!disPortDesc.equals(other.disPortDesc)) {
			return false;
		}
		if (finalCtryCode == null) {
			if (other.finalCtryCode != null) {
				return false;
			}
		} else if (!finalCtryCode.equals(other.finalCtryCode)) {
			return false;
		}
		if (finalCtryDesc == null) {
			if (other.finalCtryDesc != null) {
				return false;
			}
		} else if (!finalCtryDesc.equals(other.finalCtryDesc)) {
			return false;
		}
		if (finalPortCode == null) {
			if (other.finalPortCode != null) {
				return false;
			}
		} else if (!finalPortCode.equals(other.finalPortCode)) {
			return false;
		}
		if (finalPortDesc == null) {
			if (other.finalPortDesc != null) {
				return false;
			}
		} else if (!finalPortDesc.equals(other.finalPortDesc)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (mastShipDocNo == null) {
			if (other.mastShipDocNo != null) {
				return false;
			}
		} else if (!mastShipDocNo.equals(other.mastShipDocNo)) {
			return false;
		}
		if (nextPortCode == null) {
			if (other.nextPortCode != null) {
				return false;
			}
		} else if (!nextPortCode.equals(other.nextPortCode)) {
			return false;
		}
		if (nextPortDesc == null) {
			if (other.nextPortDesc != null) {
				return false;
			}
		} else if (!nextPortDesc.equals(other.nextPortDesc)) {
			return false;
		}
		if (nrt == null) {
			if (other.nrt != null) {
				return false;
			}
		} else if (!nrt.equals(other.nrt)) {
			return false;
		}
		if (seaStrorInd == null) {
			if (other.seaStrorInd != null) {
				return false;
			}
		} else if (!seaStrorInd.equals(other.seaStrorInd)) {
			return false;
		}
		if (towVesName == null) {
			if (other.towVesName != null) {
				return false;
			}
		} else if (!towVesName.equals(other.towVesName)) {
			return false;
		}
		if (towVoyNo == null) {
			if (other.towVoyNo != null) {
				return false;
			}
		} else if (!towVoyNo.equals(other.towVoyNo)) {
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
		if (vesNatDesc == null) {
			if (other.vesNatDesc != null) {
				return false;
			}
		} else if (!vesNatDesc.equals(other.vesNatDesc)) {
			return false;
		}
		if (vesNation == null) {
			if (other.vesNation != null) {
				return false;
			}
		} else if (!vesNation.equals(other.vesNation)) {
			return false;
		}
		if (vesType == null) {
			if (other.vesType != null) {
				return false;
			}
		} else if (!vesType.equals(other.vesType)) {
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
