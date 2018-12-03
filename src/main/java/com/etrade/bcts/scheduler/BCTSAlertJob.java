package com.etrade.bcts.scheduler;

import java.util.Date;
import java.util.Map;

import java.util.HashMap;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.util.BctsConstants;
import com.etrade.bcts.util.TrwDate;


@Configuration
@EnableBatchProcessing
public class BCTSAlertJob {
	private static final Logger LOGGER = LoggerFactory.getLogger(BCTSAlertJob.class);
	/*@Autowired
	HibernateConfiguration configuration;*/
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	@Autowired
	StepBuilderFactory steps;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String SQL_QUERY= "Select * from BCTS_ALERT";
	
//	@Bean
//	ItemReader<BCTSAlert> itemReader() {
//		JdbcCursorItemReader<BCTSAlert> itemReader = new JdbcCursorItemReader<>();
//		itemReader.setDataSource(configuration.dataSource());
//		itemReader.setSql(SQL_QUERY);
////		itemReader.setRowMapper(new BeanPropertyRowMapper<>(BCTSAlert.class));
//		itemReader.setRowMapper(new BCTSAlertRowMapper());
//		return itemReader;
//	}
	
	@Bean
	ItemReader<BCTSAlert> itemReader() {
		LOGGER.info("start BCTSAlertJob itemReader....");
		HibernateCursorItemReader<BCTSAlert> itemReader = new HibernateCursorItemReader<>();
		String query = "FROM BCTSAlert b WHERE b.reminderDate <= :today "
										+ "AND b.status=:status "
										+ "AND (b.category=:category1 OR b.category=:category2)";
//		String query = "FROM BCTSAlert b WHERE b.status=:status";
		Map<String, Object> parameterValues = new HashMap();		
		parameterValues.put("today", new Date());
		parameterValues.put("status", BctsConstants.CASE_STATUS_OPEN);
		parameterValues.put("category1", BctsConstants.CASE_CATEGORY_LICENCE_VALIDITY);
		parameterValues.put("category2", BctsConstants.CASE_CATEGORY_CASE_MANAGEMENT);
		
		itemReader.setSessionFactory(sessionFactory);
		itemReader.setQueryString(query);		
		itemReader.setParameterValues(parameterValues);
		return itemReader;
	}
	
	@Bean
	ItemReader<BCTSAlert> pcItemReader() {
		LOGGER.info("start BCTSAlertJob pcItemReader....");
		HibernateCursorItemReader<BCTSAlert> itemReader = new HibernateCursorItemReader<>();
		String query = "FROM BCTSAlert b WHERE b.status=:status "
										+ "AND category=:category";
		Map<String, Object> parameterValues = new HashMap();		
		parameterValues.put("status", BctsConstants.CASE_STATUS_OPEN);
		parameterValues.put("category", BctsConstants.CASE_CATEGORY_PERMIT_CONDITION);
		
		itemReader.setSessionFactory(sessionFactory);
		itemReader.setQueryString(query);		
		itemReader.setParameterValues(parameterValues);
		return itemReader;
	}
	/*//Reusing Existing Services
	@Bean
	ItemReader<BCTSAlert> itemReader() {
		ItemReaderAdapter<BCTSAlert> itemReader = new ItemReaderAdapter<>();
		itemReader.setTargetObject(targetObject);
		itemReader.setTargetMethod(targetMethod);		
		return itemReader;
	}*/
	
	@Bean
	ItemProcessor<BCTSAlert, BCTSAlert> itemProcessor() {
		LOGGER.info("start BCTSAlertJob itemProcessor....");
		return new ItemProcessor<BCTSAlert, BCTSAlert>() {
			
			@Override
			public BCTSAlert process(BCTSAlert arg0) throws Exception {				
				LOGGER.info("Processing case: " + arg0.getCaseId());
				return arg0;
			}
		};
	}
	
	@Bean
	ItemWriter<BCTSAlert> itemWriter() {
		return new BCTSAlertItemWriter();
	}
	
	@Bean
	Step lvCmStep1(ItemReader<BCTSAlert> itemReader, ItemProcessor<BCTSAlert, BCTSAlert> itemProcessor, ItemWriter<BCTSAlert> itemWriter){
		return steps.get("lvCmStep1").<BCTSAlert, BCTSAlert> chunk(15)
				.reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
	}
	
	@Bean
	Step pcStep1(ItemReader<BCTSAlert> pcItemReader, ItemProcessor<BCTSAlert, BCTSAlert> itemProcessor, ItemWriter<BCTSAlert> itemWriter){
		return steps.get("pcStep1").<BCTSAlert, BCTSAlert> chunk(15)
				.reader(pcItemReader).processor(itemProcessor).writer(itemWriter).build();
	}
	
	@Bean
	public Job lvCmJob(@Qualifier("lvCmStep1") Step lvCmStep1) {
		return jobBuilderFactory.get("BCTSAlertJob").start(lvCmStep1).build(); 
	}
	
	@Bean
	public Job pcJob(@Qualifier("pcStep1") Step pcStep1) {
		return jobBuilderFactory.get("BCTSAlertJob").start(pcStep1).build(); 
	}
}
