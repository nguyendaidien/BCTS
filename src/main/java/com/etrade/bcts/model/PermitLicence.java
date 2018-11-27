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
@Table(name="BCTS_LICENCE")
public class PermitLicence implements Serializable {
	/**
	 * @author ajayasamanta
	 */
	private static final long serialVersionUID = -6007499640736308657L;
	@Id
	@Column(name="USER_ID")
	private String ureId; 
	@Id
	@Column(name="JOB_NO")
	private String ureSeq;
	@Column(name="LICENCE_NO_1")
	private String licence1;
	@Column(name="LICENCE_NO_2")
	private String licence2;
	@Column(name="LICENCE_NO_3")
	private String licence3;
	@Column(name="LICENCE_NO_4")
	private String licence4;
	@Column(name="LICENCE_NO_5")
	private String licence5;
	
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
	 * @return the licence1
	 */
	public String getLicence1() {
		return licence1;
	}
	/**
	 * @param licence1 the licence1 to set
	 */
	public void setLicence1(String licence1) {
		this.licence1 = licence1;
	}
	/**
	 * @return the licence2
	 */
	public String getLicence2() {
		return licence2;
	}
	/**
	 * @param licence2 the licence2 to set
	 */
	public void setLicence2(String licence2) {
		this.licence2 = licence2;
	}
	/**
	 * @return the licence3
	 */
	public String getLicence3() {
		return licence3;
	}
	/**
	 * @param licence3 the licence3 to set
	 */
	public void setLicence3(String licence3) {
		this.licence3 = licence3;
	}
	/**
	 * @return the licence4
	 */
	public String getLicence4() {
		return licence4;
	}
	/**
	 * @param licence4 the licence4 to set
	 */
	public void setLicence4(String licence4) {
		this.licence4 = licence4;
	}
	/**
	 * @return the licence5
	 */
	public String getLicence5() {
		return licence5;
	}
	/**
	 * @param licence5 the licence5 to set
	 */
	public void setLicence5(String licence5) {
		this.licence5 = licence5;
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
		result = prime * result + ((jobHeader == null) ? 0 : jobHeader.hashCode());
		result = prime * result + ((licence1 == null) ? 0 : licence1.hashCode());
		result = prime * result + ((licence2 == null) ? 0 : licence2.hashCode());
		result = prime * result + ((licence3 == null) ? 0 : licence3.hashCode());
		result = prime * result + ((licence4 == null) ? 0 : licence4.hashCode());
		result = prime * result + ((licence5 == null) ? 0 : licence5.hashCode());
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
		if (!(obj instanceof PermitLicence)) {
			return false;
		}
		PermitLicence other = (PermitLicence) obj;
		if (jobHeader == null) {
			if (other.jobHeader != null) {
				return false;
			}
		} else if (!jobHeader.equals(other.jobHeader)) {
			return false;
		}
		if (licence1 == null) {
			if (other.licence1 != null) {
				return false;
			}
		} else if (!licence1.equals(other.licence1)) {
			return false;
		}
		if (licence2 == null) {
			if (other.licence2 != null) {
				return false;
			}
		} else if (!licence2.equals(other.licence2)) {
			return false;
		}
		if (licence3 == null) {
			if (other.licence3 != null) {
				return false;
			}
		} else if (!licence3.equals(other.licence3)) {
			return false;
		}
		if (licence4 == null) {
			if (other.licence4 != null) {
				return false;
			}
		} else if (!licence4.equals(other.licence4)) {
			return false;
		}
		if (licence5 == null) {
			if (other.licence5 != null) {
				return false;
			}
		} else if (!licence5.equals(other.licence5)) {
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
