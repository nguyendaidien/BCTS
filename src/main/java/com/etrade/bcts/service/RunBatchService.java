package com.etrade.bcts.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RunBatchService {

	
	private static final Logger logger = LoggerFactory.getLogger(RunBatchService.class);
	@Value("${calista.talend.file.path:}")
	private String batchFilePath;
	
	@Async
	public void rubBatchFile() throws IOException, InterruptedException{
		try {
		    Runtime.getRuntime().exec("cmd /c start "+batchFilePath);
		} catch(IOException e) {
			logger.info("IOException",e);
			logger.debug("IOException {}",e.getMessage());
		}
		 catch(Exception e) {
				logger.info("context",e);
				logger.debug("Exception {}",e.getMessage());
			   
			}
	}
}
