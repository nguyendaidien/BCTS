package com.etrade.bcts.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.etrade.bcts.model.BctsRoute;
import com.etrade.bcts.service.EmailService;

public class BatchAlertItemWriter implements ItemWriter<BctsRoute> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchAlertItemWriter.class);
	static final String emailFromRecipient = "ajayasamanta@crimsonlogic.com";
	String emailSubject = "Test BatchAlertItemWriter sending Email";
	String emailMessage = "Test BatchAlertItemWriter sending Email";
	String emailToRecipient = "ajaykumar99009@gmail.com";
	String recipientName = "Ajay";
	
	@Autowired
	private EmailService emailService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends BctsRoute> list) throws Exception {
		LOGGER.info("write() start");
			System.out.println("\nReceipient= " + emailToRecipient + ", Subject= " + emailSubject + ", Message= " + emailMessage + "\n");
			emailService.sendBatchAlertMail(recipientName, emailToRecipient, (List<BctsRoute>)list, null);
			System.out.println("\nMessage Send Successfully.... Hurrey!\n");
			LOGGER.debug("write() end");
	}

}
