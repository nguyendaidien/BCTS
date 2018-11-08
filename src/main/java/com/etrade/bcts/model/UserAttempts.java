/**
 * NAME		:UserAttempts.java
 * DATE		:22/10/2018
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 
 *
 */
@Entity
@Table(name="BCTS_USER_ATTEMPTS")
public class UserAttempts implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*@GenericGenerator(name="ATMPT_SEQ" , strategy="increment")
	@GeneratedValue(generator="ATMPT_SEQ")
	@Column(name="id")
	private int id;*/
	
	@Id
	@NotEmpty
	@Column(name="USER_ID", unique=true, nullable=false)
	private String username;

	@Column(name="NO_OF_ATTEMPTS")
	private int attempts;
	
	@Column(name="LAST_MODIFIED")
	private Date lastModified;
	/**
	 * @return the id
	 *//*
	public int getId() {
		return id;
	}
	*//**
	 * @param id the id to set
	 *//*
	public void setId(int id) {
		this.id = id;
	}*/
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the attempts
	 */
	public int getAttempts() {
		return attempts;
	}
	/**
	 * @param attempts the attempts to set
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	/**
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	

}
