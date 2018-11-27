package com.etrade.bcts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BCTS_BATCH_ROUTE")
public class BctsRoute implements Serializable{

	/**
	 *  
	 */ 
	private static final long serialVersionUID = -4508642667583491200L;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROUTE_ID")
	private Integer routeId;	
	
	@Column(name="ROUTE_NAME")
	private String routeName;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORG_ID")
	private Integer orgId;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SRC_ENDPOINT_ID")
	private Integer srcEndPontId;
	
	/*@GeneratedValue(strategy=GenerationType.AUTO)*/
	@Column(name="DST_ENDPOINT_ID")
	private Integer destEndPontId;
	
	@Column(name="MAP_ID")
	private Integer mapId;
	
	
	@Column(name="RULE_ID")
	private Integer ruleId;
	
	@Column(name="CTY_CODE")
	private Integer ctyCode;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name="STATUS")
	private char status;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the routeId
	 */
	public Integer getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}

	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the srcEndPontId
	 */
	public Integer getSrcEndPontId() {
		return srcEndPontId;
	}

	/**
	 * @param srcEndPontId the srcEndPontId to set
	 */
	public void setSrcEndPontId(Integer srcEndPontId) {
		this.srcEndPontId = srcEndPontId;
	}

	/**
	 * @return the destEndPontId
	 */
	public Integer getDestEndPontId() {
		return destEndPontId;
	}

	/**
	 * @param destEndPontId the destEndPontId to set
	 */
	public void setDestEndPontId(Integer destEndPontId) {
		this.destEndPontId = destEndPontId;
	}

	/**
	 * @return the mapId
	 */
	public Integer getMapId() {
		return mapId;
	}

	/**
	 * @param mapId the mapId to set
	 */
	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	/**
	 * @return the ruleId
	 */
	public Integer getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return the ctyCode
	 */
	public Integer getCtyCode() {
		return ctyCode;
	}

	/**
	 * @param ctyCode the ctyCode to set
	 */
	public void setCtyCode(Integer ctyCode) {
		this.ctyCode = ctyCode;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BctsRoute [id=" + id + ", routeId=" + routeId + ", routeName=" + routeName + ", orgId=" + orgId
				+ ", srcEndPontId=" + srcEndPontId + ", destEndPontId=" + destEndPontId + ", mapId=" + mapId
				+ ", ruleId=" + ruleId + ", ctyCode=" + ctyCode + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

	
	
	
	
	
}
