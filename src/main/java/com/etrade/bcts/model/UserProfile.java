/**
 * NAME		:UserProfile.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BCTS_USER_PROFILE")
public class UserProfile implements Serializable{
	
	private static final long serialVersionUID = -3465813074586302847L;

	@Id 
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name="PROFILE_SEQ" , strategy="increment")
	@GeneratedValue(generator="PROFILE_SEQ")
	@Column(name="TRANSACTION_ID")
	private Integer transId;	

	@Column(name="ROLE_TYPE", length=15, unique=true, nullable=false)
	private String roleType = UserProfileType.USER.getUserProfileType();
	
	

	/**
	 * @return the transId
	 */
	public Integer getTransId() {
		return transId;
	}

	/**
	 * @param transId the transId to set
	 */
	public void setTransId(Integer transId) {
		this.transId = transId;
	}

	/**
	 * @return the roleType
	 */
	public String getRoleType() {
		return roleType;
	}

	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transId == null) ? 0 : transId.hashCode());
		result = prime * result + ((roleType == null) ? 0 : roleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserProfile))
			return false;
		UserProfile other = (UserProfile) obj;
		if (transId == null) {
			if (other.transId != null)
				return false;
		} else if (!transId.equals(other.transId)) {
			return false;
		}
		if (roleType == null) {
			if (other.roleType != null)
				return false;
		} else if (!roleType.equals(other.roleType)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [transId=" + transId + ", roleType=" + roleType + "]";
	}




}
