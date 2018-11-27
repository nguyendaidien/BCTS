package com.etrade.bcts.model;

import java.io.Serializable;

import javax.persistence.Column;


public class BctsPermitPk implements Serializable {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -132807564315946015L;
	
    @Column(name = "USER_ID")
    private String uenId;
	
    @Column(name="JOB_NO")
    private String urnSeq;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (!(obj instanceof BctsPermitPk)) {
			return false;
		}
		BctsPermitPk other = (BctsPermitPk) obj;
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
