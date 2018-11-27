package com.etrade.bcts.common.util;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import com.crimsonlogic.calista.exception.CalistaBatchException;
//import com.crimsonlogic.calista.repository.RouteMapRepository;
//import com.crimsonlogic.calista.service.AuditLogService;
//import com.crimsonlogic.calista.service.TaskLogService;

import com.etrade.bcts.model.BctsRouteMap;
import com.etrade.bcts.service.RunBatchService;

/**
 * Abstract class for business SI batch processing job.
 * Various useful objects are passed to task in map with following keys:
 * 'appContext' - Spring ApplicationContext
 * 'mapId' - current route map id
 * 'mapRepo' - TXbsRouteMap repository
 * that beans and services can be retrieved from it
 * 
 * @author jeanhaur
 *
 */
public abstract class XTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(XTask.class);
	
	/*@Autowired
	private AuditLogService auditLog;
	
	@Autowired
	private RouteMapRepository mapRepo;
	*/
	@Autowired
	private RunBatchService runBatchService;
	
	/*@Autowired
	private TaskLogService taskLogService;*/
	
	protected Map<String, Object> parameters;
	
	protected String runStatus;
	
	protected Long mapId;
	
	protected Long logId;
	
	protected StringBuilder sb = new StringBuilder();

	protected abstract void runInternal() throws Exception;
	
	@Override
	public void run()
	{
		String className = this.getClass().getSimpleName();
		
		logger.debug("Task started - {}", className);
		
		runStatus = "R";
		
		beforeTask();
		
		try {
			
			runInternal();
			
			runStatus = "S";
			
		}catch(Exception e) {
			
			logger.error("Error occurred in executing task", e);
			
			runStatus = "F";
			
		}
		
		logger.debug("Task ended - {}", className);
		logger.debug("Summary : {}", sb.toString());
		
		afterTask();
//		executeBatchTask();
	}
	
	private void beforeTask()
	{
		parameters.put("sb", sb);
		
		logId = null;//taskLogService.startTask(null, (String) parameters.get("routeName"));
		
		mapId = (Long) parameters.get("mapId");
		
		BctsRouteMap map = null;//mapRepo.findOne(mapId);
		
		//map.setLastRunStatus(runStatus);
		
		//mapRepo.save(map);
	}
	
	private void afterTask()
	{		
		BctsRouteMap map = null;//mapRepo.findOne(mapId);
		
		Date nextTrig = new Date();
		//nextTrig.setTime(new Date().getTime() + map.getRunInterval() * 60000);//interval is in minute
		
		//map.setNextTrig(nextTrig);
		//map.setLastRunStatus(runStatus);
		
		//mapRepo.save(map);
		
		//taskLogService.endTask(null, logId, sb.toString(), runStatus);
	}
	
	
	private void executeBatchTask()	{	
		
		try {
			runBatchService.rubBatchFile();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error("Interrupted!", e);
		    Thread.currentThread().interrupt();
		}
		/*TXbsRouteMap map = mapRepo.findOne(mapId);
		
		Date nextTrig = new Date();
		nextTrig.setTime(new Date().getTime() + map.getRunInterval() * 60000);//interval is in minute
		
		map.setNextTrig(nextTrig);
		map.setLastRunStatus(runStatus);
		
		mapRepo.save(map);
		
		taskLogService.endTask(null, logId, sb.toString(), runStatus);*/
	}
	

	/*public AuditLogService getAuditLog() {
		return auditLog;
	}

	public void setAuditLog(AuditLogService auditLog) {
		this.auditLog = auditLog;
	}
*/
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}
	
}
