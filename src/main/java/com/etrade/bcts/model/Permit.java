package com.etrade.bcts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="PERMIT")
public class Permit implements Serializable {
	@Id 
	//@GenericGenerator(name="PERMIT_SEQ" , strategy="increment")
	//@GeneratedValue(generator="PERMIT_SEQ")
	private Integer id;	
	
	@Column(name="permitNo", length=30, nullable=true)
	private String permitNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((permitNo == null) ? 0 : permitNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permit other = (Permit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (permitNo == null) {
			if (other.permitNo != null)
				return false;
		} else if (!permitNo.equals(other.permitNo))
			return false;
		return true;
	}		
}
