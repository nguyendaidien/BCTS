package com.etrade.bcts.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.service.EmailService;

public class BCTSAlertItemWriter implements ItemWriter<BCTSAlert> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BCTSAlertItemWriter.class);
		
	@Autowired
	private EmailService emailService;
	
	@Override
	public void write(List<? extends BCTSAlert> list) throws Exception {
			LOGGER.info("chunk size: " + list.size());
			
			for (BCTSAlert bctsAlert : list) {		
				emailService.sendBCTSAlertMail(bctsAlert, null);
				LOGGER.info("Alert Message Sent Successfully....caseId {0} ; type {1}", bctsAlert.getCaseId(), bctsAlert.getCategory());
			}
	}
}
