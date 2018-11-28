package com.etrade.bcts.configuration;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.etrade.bcts.handler.InterceptingJobExecution;
import com.etrade.bcts.model.BctsJobHeader;
import com.etrade.bcts.scheduler.BctsXMLReader;
import com.etrade.bcts.scheduler.BctsXMLWriter;


@Configuration
@EnableBatchProcessing
public class BctsRouteSftpJob {
	static final Logger logger = LoggerFactory.getLogger(BctsRouteSftpJob.class);
	@Autowired
	HibernateConfiguration configuration;
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	@Autowired
	StepBuilderFactory stepBuilderFact;
	
	@Autowired
    InterceptingJobExecution interceptingJob;
	
	
	@Bean
	ItemReader<BctsJobHeader> itemReader() {
	return new BctsXMLReader();
	}
	
	@Bean
	ItemProcessor<BctsJobHeader, BctsJobHeader> itemProcessor() {
		logger.info("itemProcessor():Start");
		return new ItemProcessor<BctsJobHeader, BctsJobHeader>() {
			@Override
			public BctsJobHeader process(BctsJobHeader arg0) throws Exception {
				logger.info("itemProcessor() process() mapId:{}",arg0);
				//arg0.get
				
				return arg0;
			}
		};
	}
	
	@Bean
	ItemWriter<BctsJobHeader> itemWriter() {
		logger.info("itemWriter()################ ");
		return new BctsXMLWriter();

	}
	 
	@Bean
	Step step1(ItemReader<BctsJobHeader> itemReader, ItemProcessor<BctsJobHeader, BctsJobHeader> itemProcessor, ItemWriter<BctsJobHeader> itemWriter){
		logger.info("stepAjay1()##################################: {}",itemReader);
		 logger.info("stepAjay2()##################################: {}",itemProcessor);
		 logger.info("stepAjay3()##################################: {}",itemWriter);
		return stepBuilderFact.get("step1").<BctsJobHeader, BctsJobHeader> chunk(3)
				.reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
	}
	
	@Bean
	public Job job(@Qualifier("step1") Step step1) {
		logger.info("job()##################################: {}",step1);
		return jobBuilderFactory.get("BctsRouteSftpJob").start(step1).listener(interceptingJob).build(); 
	}
}
