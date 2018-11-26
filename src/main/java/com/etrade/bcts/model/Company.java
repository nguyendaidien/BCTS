package com.etrade.bcts.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="BCTS_COMPANY")
public class Company {
	private static final Logger LOG = LoggerFactory.getLogger(Company.class);
	@Id
	@Column(name="CR_UEI_NO")
	private String ueiNo;
	
//	@OneToMany(mappedBy="uen")
//	private Set<BCTSAlert> bctsAlerts;
	
	@Column(name="ALERT_EMAILS")
	private String alertEmails;
	
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
	private String personName1;
	
	@Column(name="PERSON_NAME2")
	private String personName2;
	
	@Column(name="PERSON_NAME3")
	private String personName3;
	
	@Column(name="PERSON_ID")
	private String personalId;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Column(name="COUNTRYCODE")
	private String countryCode;
	
	@Column(name="POSTALCODE")
	private String postalCode;
	
	@Column(name="CTYSUBENTITYNAME")
	private String ctySubentityName;
	
	@Column(name="CTYSUBENTITYCODE")
	private String ctySubentityCode;
	
	@Column(name="CITYNAME")
	private String cityName;

	public String getUeiNo() {
		return ueiNo;
	}

	public void setUeiNo(String ueiNo) {
		this.ueiNo = ueiNo;
	}

	public String getAlertEmails() {
		return alertEmails;
	}

	public void setAlertEmails(String alertEmails) {
		this.alertEmails = alertEmails;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getPersonName1() {
		return personName1;
	}

	public void setPersonName1(String personName1) {
		this.personName1 = personName1;
	}

	public String getPersonName2() {
		return personName2;
	}

	public void setPersonName2(String personName2) {
		this.personName2 = personName2;
	}

	public String getPersonName3() {
		return personName3;
	}

	public void setPersonName3(String personName3) {
		this.personName3 = personName3;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCtySubentityName() {
		return ctySubentityName;
	}

	public void setCtySubentityName(String ctySubentityName) {
		this.ctySubentityName = ctySubentityName;
	}

	public String getCtySubentityCode() {
		return ctySubentityCode;
	}

	public void setCtySubentityCode(String ctySubentityCode) {
		this.ctySubentityCode = ctySubentityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public int hashCode() {
		LOG.info("Company- hashCode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ueiNo == null) ? 0 : ueiNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		LOG.info("Company- equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (ueiNo == null) {
			if (other.ueiNo != null)
				return false;
		} else if (!ueiNo.equals(other.ueiNo))
			return false;
		return true;
	}

//	public Set<BCTSAlert> getBctsAlerts() {
//		return bctsAlerts;
//	}
//
//	public void setBctsAlerts(Set<BCTSAlert> bctsAlerts) {
//		this.bctsAlerts = bctsAlerts;
//	}

	
	
}