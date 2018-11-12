package com.etrade.bcts.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.etrade.bcts.model.*;
import com.etrade.bcts.scheduler.BCTSAlertItemWriter;


@Configuration
@EnableBatchProcessing
public class BCTSAlertJob {
	@Autowired
	HibernateConfiguration configuration;
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	@Autowired
	StepBuilderFactory steps;
	
	private static final String SQL_QUERY= "Select * from BCTS_ALERT";
	
	@Bean
	ItemReader<BCTSAlert> itemReader() {
		JdbcCursorItemReader<BCTSAlert> itemReader = new JdbcCursorItemReader<>();
		itemReader.setDataSource(configuration.dataSource());
		itemReader.setSql(SQL_QUERY);
		itemReader.setRowMapper(new BeanPropertyRowMapper<>(BCTSAlert.class));
		return itemReader;
	}
	
	@Bean
	ItemProcessor<BCTSAlert, BCTSAlert> itemProcessor() {
		return new ItemProcessor<BCTSAlert, BCTSAlert>() {
			
			@Override
			public BCTSAlert process(BCTSAlert arg0) throws Exception {
				System.out.println("Processing case: " + arg0.getCaseId());
				return arg0;
			}
		};
	}
	
	@Bean
	ItemWriter<BCTSAlert> itemWriter() {
		return new BCTSAlertItemWriter();
//		return new ItemWriter<BCTSAlert>() {
//			
//			@Override
//			public void write(List<? extends BCTSAlert> arg0) throws Exception {
//				for (BCTSAlert bctsAlert : arg0) {
//					System.out.println("Write case: " + bctsAlert.getCaseId());
//				}
//				
//			}
//		};
	}
	
	@Bean
	Step step1(ItemReader<BCTSAlert> itemReader, ItemProcessor<BCTSAlert, BCTSAlert> itemProcessor, ItemWriter<BCTSAlert> itemWriter){
		return steps.get("step1").<BCTSAlert, BCTSAlert> chunk(10)
				.reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
	}
	
	@Bean
	public Job job(@Qualifier("step1") Step step1) {
		return jobBuilderFactory.get("BCTSAlertJob").start(step1).build(); 
	}
}
