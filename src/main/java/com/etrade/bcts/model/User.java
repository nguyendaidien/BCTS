/**
 * NAME		:User.java
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="BCTS_APP_USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = -3465813074586302847L;

	/*@Id @GeneratedValue(strategy=GenerationType.AUTO)*/
	@Id
	@GenericGenerator(name="USER_SEQ" , strategy="increment")
	@GeneratedValue(generator="USER_SEQ")
	@Column(name="TRANSACTION_ID")
	private Integer id;

	@NotEmpty
	@Column(name="SSO_ID", unique=true, nullable=false)
	private String ssoId;
	
	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
	private String password;
		
	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	@Column(name="ACCT_LOCKED",nullable=false)
	private String accountLocked;
	
	@Column(name="USER_ENABLED",nullable=false)
	private String userEnabled;
	
	@Column(name="ACCT_EXPIRED",nullable=false)
	private String acctExpired;
	
	@Column(name="CREDENTIAL_EXPIRED",nullable=false)
	private String crdExpired;
	
	@Column(name="CREATED_DATE")
	/*@Temporal(TemporalType.DATE)*/
	 /*@Temporal(TemporalType.TIMESTAMP)*/
	private Date   createdDate;
	
	@Column(name="PWD_UPDATED")
	//@Temporal(TemporalType.DATE)
	/* @Temporal(TemporalType.TIMESTAMP)*/
	private Date pwdUpdatedDate;
	
	
	@Column(name="PWD_EXPIRED_DATE")
	/*@Temporal(TemporalType.DATE)*/
	private Date pwdExpiredDate;
	


	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId)) {
			return false;
		}
		return true;
	}
	
	

	/**
	 * @return the userEnabled
	 */
	public String getUserEnabled() {
		return userEnabled;
	}

	/**
	 * @param userEnabled the userEnabled to set
	 */
	public void setUserEnabled(String userEnabled) {
		this.userEnabled = userEnabled;
	}

	/**
	 * @return the acctExpired
	 */
	public String getAcctExpired() {
		return acctExpired;
	}

	/**
	 * @param acctExpired the acctExpired to set
	 */
	public void setAcctExpired(String acctExpired) {
		this.acctExpired = acctExpired;
	}

	/**
	 * @return the crdExpired
	 */
	public String getCrdExpired() {
		return crdExpired;
	}

	/**
	 * @param crdExpired the crdExpired to set
	 */
	public void setCrdExpired(String crdExpired) {
		this.crdExpired = crdExpired;
	}

	

	

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the pwdUpdatedDate
	 */
	public Date getPwdUpdatedDate() {
		return pwdUpdatedDate;
	}

	/**
	 * @param pwdUpdatedDate the pwdUpdatedDate to set
	 */
	public void setPwdUpdatedDate(Date pwdUpdatedDate) {
		this.pwdUpdatedDate = pwdUpdatedDate;
	}

	/**
	 * @return the pwdExpiredDate
	 */
	public Date getPwdExpiredDate() {
		return pwdExpiredDate;
	}

	/**
	 * @param pwdExpiredDate the pwdExpiredDate to set
	 */
	public void setPwdExpiredDate(Date pwdExpiredDate) {
		this.pwdExpiredDate = pwdExpiredDate;
	}

	/**
	 * @return the accountLocked
	 */
	public String getAccountLocked() {
		return accountLocked;
	}

	/**
	 * @param accountLocked the accountLocked to set
	 */
	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", accountLocked=" + accountLocked +"]";
	}


	
}
