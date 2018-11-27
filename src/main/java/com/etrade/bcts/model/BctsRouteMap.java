package com.etrade.bcts.model;

import java.io.Serializable;

/*@Entity
@Table(name="BCTS_BATCH_ROUTE_MAP")*/
public class BctsRouteMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555069670002291053L;
	
	/*private Long id;
	
	@Id
	@Column(name="MAP_ID")
	private Long mapId;

	@Column(name="MAP_BATCH_PROC_CLS")
	private String mapBatchProcCls;

	@Column(name="MAP_POST_PROC_CLS")
	private String mapPostProcCls;
	
	@Column(name="MAP_MAPPING_CLS")
	private String mapMappingCls;
	
	@Column(name="MAP_PROPERTIES_JSON")
	private String mapPropertiesJson;
	
	@Column(name="IN_FILENAME_PATTERN")
	private String inFilenamePattern;
	
	@Column(name="RESP_FILENAME_PATTERN")
	private String respFilenamePattern;
	
	@Column(name="OUT_FILENAME_PATTERN")
	private String outFilenamePattern;
	
	@Column(name="RUN_INTERVAL")
	private Integer runInterval;
	
	@Column(name="LAST_RUN_STATUS")
	private String lastRunStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="NEXT_TRIG")
	private Date nextTrig;
	
	@Transient
	private transient Map<String, Object> data = new HashMap<>();

	*//**
	 * @return the id
	 *//*
	public Long getId() {
		return id;
	}

	*//**
	 * @param id the id to set
	 *//*
	public void setId(Long id) {
		this.id = id;
	}

	*//**
	 * @return the mapId
	 *//*
	public Long getMapId() {
		return mapId;
	}

	*//**
	 * @param mapId the mapId to set
	 *//*
	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}

	*//**
	 * @return the mapBatchProcCls
	 *//*
	public String getMapBatchProcCls() {
		return mapBatchProcCls;
	}

	*//**
	 * @param mapBatchProcCls the mapBatchProcCls to set
	 *//*
	public void setMapBatchProcCls(String mapBatchProcCls) {
		this.mapBatchProcCls = mapBatchProcCls;
	}

	*//**
	 * @return the mapPostProcCls
	 *//*
	public String getMapPostProcCls() {
		return mapPostProcCls;
	}

	*//**
	 * @param mapPostProcCls the mapPostProcCls to set
	 *//*
	public void setMapPostProcCls(String mapPostProcCls) {
		this.mapPostProcCls = mapPostProcCls;
	}

	*//**
	 * @return the mapMappingCls
	 *//*
	public String getMapMappingCls() {
		return mapMappingCls;
	}

	*//**
	 * @param mapMappingCls the mapMappingCls to set
	 *//*
	public void setMapMappingCls(String mapMappingCls) {
		this.mapMappingCls = mapMappingCls;
	}

	*//**
	 * @return the mapPropertiesJson
	 *//*
	public String getMapPropertiesJson() {
		return mapPropertiesJson;
	}

	*//**
	 * @param mapPropertiesJson the mapPropertiesJson to set
	 *//*
	public void setMapPropertiesJson(String mapPropertiesJson) {
		this.mapPropertiesJson = mapPropertiesJson;
	}

	*//**
	 * @return the inFilenamePattern
	 *//*
	public String getInFilenamePattern() {
		return inFilenamePattern;
	}

	*//**
	 * @param inFilenamePattern the inFilenamePattern to set
	 *//*
	public void setInFilenamePattern(String inFilenamePattern) {
		this.inFilenamePattern = inFilenamePattern;
	}

	*//**
	 * @return the respFilenamePattern
	 *//*
	public String getRespFilenamePattern() {
		return respFilenamePattern;
	}

	*//**
	 * @param respFilenamePattern the respFilenamePattern to set
	 *//*
	public void setRespFilenamePattern(String respFilenamePattern) {
		this.respFilenamePattern = respFilenamePattern;
	}

	*//**
	 * @return the outFilenamePattern
	 *//*
	public String getOutFilenamePattern() {
		return outFilenamePattern;
	}

	*//**
	 * @param outFilenamePattern the outFilenamePattern to set
	 *//*
	public void setOutFilenamePattern(String outFilenamePattern) {
		this.outFilenamePattern = outFilenamePattern;
	}

	*//**
	 * @return the runInterval
	 *//*
	public Integer getRunInterval() {
		return runInterval;
	}

	*//**
	 * @param runInterval the runInterval to set
	 *//*
	public void setRunInterval(Integer runInterval) {
		this.runInterval = runInterval;
	}

	*//**
	 * @return the lastRunStatus
	 *//*
	public String getLastRunStatus() {
		return lastRunStatus;
	}

	*//**
	 * @param lastRunStatus the lastRunStatus to set
	 *//*
	public void setLastRunStatus(String lastRunStatus) {
		this.lastRunStatus = lastRunStatus;
	}

	*//**
	 * @return the nextTrig
	 *//*
	public Date getNextTrig() {
		return nextTrig;
	}

	*//**
	 * @param nextTrig the nextTrig to set
	 *//*
	public void setNextTrig(Date nextTrig) {
		this.nextTrig = nextTrig;
	}

	*//**
	 * @return the data
	 *//*
	public Map<String, Object> getData() {
		return data;
	}

	*//**
	 * @param data the data to set
	 *//*
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	 (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return "BctsRouteMap [id=" + id + ", mapId=" + mapId + ", mapBatchProcCls=" + mapBatchProcCls
				+ ", mapPostProcCls=" + mapPostProcCls + ", mapMappingCls=" + mapMappingCls + ", mapPropertiesJson="
				+ mapPropertiesJson + ", inFilenamePattern=" + inFilenamePattern + ", respFilenamePattern="
				+ respFilenamePattern + ", outFilenamePattern=" + outFilenamePattern + ", runInterval=" + runInterval
				+ ", lastRunStatus=" + lastRunStatus + ", nextTrig=" + nextTrig + "]";
	}
	
*/
}
