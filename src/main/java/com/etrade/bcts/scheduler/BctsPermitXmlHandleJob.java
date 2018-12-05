package com.etrade.bcts.scheduler;

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

import com.etrade.bcts.configuration.HibernateConfiguration;
import com.etrade.bcts.handler.InterceptingJobExecution;
import com.etrade.bcts.model.BctsJobHeader;


@Configuration
@EnableBatchProcessing
public class BctsPermitXmlHandleJob {
	static final Logger logger = LoggerFactory.getLogger(BctsPermitXmlHandleJob.class);
	@Autowired
	HibernateConfiguration configuration;
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	@Autowired
	StepBuilderFactory stepBuilderFact;
	
	@Autowired
    InterceptingJobExecution interceptingJob;
	
	
	@Bean
	ItemReader<BctsJobHeader> permitXmlItemReader() {
		return new BctsXMLReader();
	}
	
	@Bean
	ItemProcessor<BctsJobHeader, BctsJobHeader> permitXmlItemProcessor() {
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
	ItemWriter<BctsJobHeader> permitXmlItemWriter() {
		logger.info("itemWriter()################ ");
		return new BctsXMLWriter();

	}
	 
	@Bean
	Step permitXMLStep1(ItemReader<BctsJobHeader> permitXmlItemReader, ItemProcessor<BctsJobHeader, BctsJobHeader> permitXmlItemProcessor, ItemWriter<BctsJobHeader> permitXmlItemWriter){
		logger.info("stepAjay1()##################################: {}",permitXmlItemReader);
		 logger.info("stepAjay2()##################################: {}",permitXmlItemProcessor);
		 logger.info("stepAjay3()##################################: {}",permitXmlItemWriter);
		return stepBuilderFact.get("permitXMLStep1").<BctsJobHeader, BctsJobHeader> chunk(3)
				.reader(permitXmlItemReader).processor(permitXmlItemProcessor).writer(permitXmlItemWriter).build();
	}
	
	@Bean
	public Job permitXMLJob(@Qualifier("permitXMLStep1") Step step1) {
		logger.info("job()##################################: {}",step1);
		return jobBuilderFactory.get("BctsPermitXmlHandleJob").start(step1).listener(interceptingJob).build(); 
	}
}
