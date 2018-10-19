/**
 * NAME		:UserProfileType.java
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

public enum UserProfileType implements Serializable{
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String profType;
	
	private UserProfileType(String userProfileType){
		this.profType = userProfileType;
	}
	
	public String getUserProfileType(){
		return profType;
	}
	
}
