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
//	static String emailToRecipient, emailSubject, emailMessage;
	static final String emailFromRecipient = "daidiennguyen@crimsonlogic.com";
	// Reading Email Form Input Parameters		
	String emailSubject = "Test BCTSAlertItemWriter sending Email";
	String emailMessage = "Test BCTSAlertItemWriter sending Email";
	String emailToRecipient = "nguyendaidien@gmail.com";
	String recipientName = "Dien";
	
	@Autowired
	private EmailService emailService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends BCTSAlert> list) throws Exception {

			// Logging The Email Form Parameters For Debugging Purpose
			System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
			emailService.sendBCTSAlertMail(recipientName, emailToRecipient, (List<BCTSAlert>)list, null);
			System.out.println("\nMessage Send Successfully.... Hurrey!\n");

	}

}
