package com.etrade.bcts.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class PermitCondition {    
    private String jobNo;
    private String permitNo;
    private String agencyCode;
    private String conditionCode;
    private String conditionDescription;
    
    public PermitCondition(){};
    
    public PermitCondition(BCTSAlert b){
    	this.jobNo = b.getJobNo();
    	this.permitNo = b.getPermitNo();
    	JSONObject pcJson;
		try {
			pcJson = new JSONObject(b.getAlertContent());
			this.agencyCode =  pcJson.getString("AgencyCode");
			this.conditionCode =  pcJson.getString("ConditionCode");
			this.conditionDescription = pcJson.getString("ConditionDescription");
		} catch (JSONException e) {
			e.printStackTrace();
		}
    };
    
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getPermitNo() {
		return permitNo;
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getConditionCode() {
		return conditionCode;
	}
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}
	public String getConditionDescription() {
		return conditionDescription;
	}
	public void setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
	}        
}
