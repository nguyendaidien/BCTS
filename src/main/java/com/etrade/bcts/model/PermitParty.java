/**
 * @author ajayasamanta
 */
package com.etrade.bcts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="BCTS_PARTY")
public class PermitParty implements Serializable{

	private static final long serialVersionUID = -4061144365838373518L;
	@Id
	@Column(name="USER_ID")
	private String ureId;
	@Id
	@Column(name="JOB_NO")
	private String ureSeq;
	@Id
	@Column(name="PARTY_IND")
	private String partyInd;
	@Column(name="CR_UEI_NO")
	private String crUeiNo;
	@Column(name="ALERT_EMAILS")
	private String alertEmil;
	@Column(name="NAME1")
	private String name1;
	@Column(name="NAME2")
	private String name2;
	@Column(name="NAME3")
	private String name3;
	@Column(name="ADDR1")
	private String addr1;
	@Column(name="ADDR2")
	private String addr2;
	@Column(name="ADDR3")
	private String addr3;
	@Column(name="PERSON_NAME1")
	private String perName1;
	@Column(name="PERSON_NAME2")
	private String perName2;
	@Column(name="PERSON_NAME3")
	private String perName3;
	@Column(name="PERSON_ID")
	private String perId;
	@Column(name="CONTACT_NO")
	private String contactNo;
	@Column(name="COUNTRYCODE")
	private String countryCode;
	@Column(name="POSTALCODE")
	private String postalCode;
	@Column(name="CITYNAME")
	private String cityName;
	@Column(name="CTYSUBENTITYCODE")
	private String cityCode;
	@Column(name="CTYSUBENTITYNAME")
	private String citySubName;
	
	
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
	 * @return the partyInd
	 */
	public String getPartyInd() {
		return partyInd;
	}
	/**
	 * @param partyInd the partyInd to set
	 */
	public void setPartyInd(String partyInd) {
		this.partyInd = partyInd;
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
	 * @return the alertEmil
	 */
	public String getAlertEmil() {
		return alertEmil;
	}
	/**
	 * @param alertEmil the alertEmil to set
	 */
	public void setAlertEmil(String alertEmil) {
		this.alertEmil = alertEmil;
	}
	/**
	 * @return the name1
	 */
	public String getName1() {
		return name1;
	}
	/**
	 * @param name1 the name1 to set
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}
	/**
	 * @return the name2
	 */
	public String getName2() {
		return name2;
	}
	/**
	 * @param name2 the name2 to set
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}
	/**
	 * @return the name3
	 */
	public String getName3() {
		return name3;
	}
	/**
	 * @param name3 the name3 to set
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}
	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}
	/**
	 * @param addr1 the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}
	/**
	 * @param addr2 the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	/**
	 * @return the addr3
	 */
	public String getAddr3() {
		return addr3;
	}
	/**
	 * @param addr3 the addr3 to set
	 */
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	/**
	 * @return the perName1
	 */
	public String getPerName1() {
		return perName1;
	}
	/**
	 * @param perName1 the perName1 to set
	 */
	public void setPerName1(String perName1) {
		this.perName1 = perName1;
	}
	/**
	 * @return the perName2
	 */
	public String getPerName2() {
		return perName2;
	}
	/**
	 * @param perName2 the perName2 to set
	 */
	public void setPerName2(String perName2) {
		this.perName2 = perName2;
	}
	/**
	 * @return the perName3
	 */
	public String getPerName3() {
		return perName3;
	}
	/**
	 * @param perName3 the perName3 to set
	 */
	public void setPerName3(String perName3) {
		this.perName3 = perName3;
	}
	/**
	 * @return the perId
	 */
	public String getPerId() {
		return perId;
	}
	/**
	 * @param perId the perId to set
	 */
	public void setPerId(String perId) {
		this.perId = perId;
	}
	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the citySubName
	 */
	public String getCitySubName() {
		return citySubName;
	}
	/**
	 * @param citySubName the citySubName to set
	 */
	public void setCitySubName(String citySubName) {
		this.citySubName = citySubName;
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
		result = prime * result + ((addr1 == null) ? 0 : addr1.hashCode());
		result = prime * result + ((addr2 == null) ? 0 : addr2.hashCode());
		result = prime * result + ((addr3 == null) ? 0 : addr3.hashCode());
		result = prime * result + ((alertEmil == null) ? 0 : alertEmil.hashCode());
		result = prime * result + ((cityCode == null) ? 0 : cityCode.hashCode());
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((citySubName == null) ? 0 : citySubName.hashCode());
		result = prime * result + ((contactNo == null) ? 0 : contactNo.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((crUeiNo == null) ? 0 : crUeiNo.hashCode());
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((name1 == null) ? 0 : name1.hashCode());
		result = prime * result + ((name2 == null) ? 0 : name2.hashCode());
		result = prime * result + ((name3 == null) ? 0 : name3.hashCode());
		result = prime * result + ((partyInd == null) ? 0 : partyInd.hashCode());
		result = prime * result + ((perId == null) ? 0 : perId.hashCode());
		result = prime * result + ((perName1 == null) ? 0 : perName1.hashCode());
		result = prime * result + ((perName2 == null) ? 0 : perName2.hashCode());
		result = prime * result + ((perName3 == null) ? 0 : perName3.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
		if (!(obj instanceof PermitParty)) {
			return false;
		}
		PermitParty other = (PermitParty) obj;
		if (addr1 == null) {
			if (other.addr1 != null) {
				return false;
			}
		} else if (!addr1.equals(other.addr1)) {
			return false;
		}
		if (addr2 == null) {
			if (other.addr2 != null) {
				return false;
			}
		} else if (!addr2.equals(other.addr2)) {
			return false;
		}
		if (addr3 == null) {
			if (other.addr3 != null) {
				return false;
			}
		} else if (!addr3.equals(other.addr3)) {
			return false;
		}
		if (alertEmil == null) {
			if (other.alertEmil != null) {
				return false;
			}
		} else if (!alertEmil.equals(other.alertEmil)) {
			return false;
		}
		if (cityCode == null) {
			if (other.cityCode != null) {
				return false;
			}
		} else if (!cityCode.equals(other.cityCode)) {
			return false;
		}
		if (cityName == null) {
			if (other.cityName != null) {
				return false;
			}
		} else if (!cityName.equals(other.cityName)) {
			return false;
		}
		if (citySubName == null) {
			if (other.citySubName != null) {
				return false;
			}
		} else if (!citySubName.equals(other.citySubName)) {
			return false;
		}
		if (contactNo == null) {
			if (other.contactNo != null) {
				return false;
			}
		} else if (!contactNo.equals(other.contactNo)) {
			return false;
		}
		if (countryCode == null) {
			if (other.countryCode != null) {
				return false;
			}
		} else if (!countryCode.equals(other.countryCode)) {
			return false;
		}
		if (crUeiNo == null) {
			if (other.crUeiNo != null) {
				return false;
			}
		} else if (!crUeiNo.equals(other.crUeiNo)) {
			return false;
		}
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (name1 == null) {
			if (other.name1 != null) {
				return false;
			}
		} else if (!name1.equals(other.name1)) {
			return false;
		}
		if (name2 == null) {
			if (other.name2 != null) {
				return false;
			}
		} else if (!name2.equals(other.name2)) {
			return false;
		}
		if (name3 == null) {
			if (other.name3 != null) {
				return false;
			}
		} else if (!name3.equals(other.name3)) {
			return false;
		}
		if (partyInd == null) {
			if (other.partyInd != null) {
				return false;
			}
		} else if (!partyInd.equals(other.partyInd)) {
			return false;
		}
		if (perId == null) {
			if (other.perId != null) {
				return false;
			}
		} else if (!perId.equals(other.perId)) {
			return false;
		}
		if (perName1 == null) {
			if (other.perName1 != null) {
				return false;
			}
		} else if (!perName1.equals(other.perName1)) {
			return false;
		}
		if (perName2 == null) {
			if (other.perName2 != null) {
				return false;
			}
		} else if (!perName2.equals(other.perName2)) {
			return false;
		}
		if (perName3 == null) {
			if (other.perName3 != null) {
				return false;
			}
		} else if (!perName3.equals(other.perName3)) {
			return false;
		}
		if (postalCode == null) {
			if (other.postalCode != null) {
				return false;
			}
		} else if (!postalCode.equals(other.postalCode)) {
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
