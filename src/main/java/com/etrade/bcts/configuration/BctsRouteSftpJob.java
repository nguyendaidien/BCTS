package com.etrade.bcts.configuration;

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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.etrade.bcts.handler.InterceptingJobExecution;
import com.etrade.bcts.model.BctsPermitType;
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
	StepBuilderFactory steps;
	
	@Autowired
    InterceptingJobExecution interceptingJob;
	
	/*private static final String SQL_QUERY= "Select * from BCTS_BATCH_ROUTE WHERE STATUS='A'";*/
	private static final String SQL_QUERY= "Select * from BCTS_BATCH_ROUTE";
	
	@Bean
	ItemReader<BctsPermitType> itemReader() {
		JdbcCursorItemReader<BctsPermitType> itemReader = new JdbcCursorItemReader<>();
		logger.info("itemReader(): {}",itemReader);
		itemReader.setDataSource(configuration.dataSource());
		itemReader.setSql(SQL_QUERY);
		itemReader.setRowMapper(new BeanPropertyRowMapper<>(BctsPermitType.class));
		return itemReader;
	}
	
	@Bean
	ItemProcessor<BctsPermitType, BctsPermitType> itemProcessor() {
		logger.info("itemProcessor():Start");
		return new ItemProcessor<BctsPermitType, BctsPermitType>() {
			@Override
			public BctsPermitType process(BctsPermitType arg0) throws Exception {
				logger.info("itemProcessor() process() mapId:{}",arg0);
				//arg0.get
				
				return arg0;
			}
		};
	}
	
	/*@Bean
	ItemWriter<BctsRoute> itemWriter() {
		logger.info("itemWriter()################ ");
		return new BatchAlertItemWriter();*/
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
	//}
	@Bean
	ItemWriter<BctsPermitType> itemWriter() {
		logger.info("itemWriter()################ ");
		BctsXMLWriter xmlWriter=new BctsXMLWriter();
		return xmlWriter;

	}
	
	/* @Bean
	    public Job job(@Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
	        return jobBuilderFactory.get("myJob").start(step1).next(step2).build();
	    }*/
	
	/* 
	 @Bean
	    protected Step step1(ItemReader<BctsRoute> reader, ItemProcessor<BctsRoute, BctsRoute> processor, ItemWriter<BctsRoute> writer) {
		 
		 logger.info("stepAjay1()##################################: {}",reader);
		 logger.info("stepAjay2()##################################: {}",processor);
		 logger.info("stepAjay3()##################################: {}",writer);
	        return steps.get("BctsRouteSftpJob")
	            .<BctsRoute, BctsRoute> chunk(10)
	            .reader(reader)
	            .processor(processor)
	            .writer(writer).listener(interceptingJob)
	            .build();
	    }*/
	 
	 /*@Bean
	    protected Step step2(Tasklet tasklet) {
		 logger.info("stepAjay1()##################################: {}",tasklet);
	        return steps.get("BctsRouteSftpJob")
	            .tasklet(tasklet)
	            .build();
	    }*/
	 
	@Bean
	Step step1(ItemReader<BctsPermitType> itemReader, ItemProcessor<BctsPermitType, BctsPermitType> itemProcessor, ItemWriter<BctsPermitType> itemWriter){
		logger.info("stepAjay1()##################################: {}",itemReader);
		 logger.info("stepAjay2()##################################: {}",itemProcessor);
		 logger.info("stepAjay3()##################################: {}",itemWriter);
		return steps.get("step1").<BctsPermitType, BctsPermitType> chunk(10)
				.reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
	}
	
	@Bean
	public Job job(@Qualifier("step1") Step step1) {
		logger.info("job()##################################: {}",step1);
		//Job job=jobBuilderFactory.get("BctsRouteSftpJob").start(step1).listener(interceptingJob).build();
		return jobBuilderFactory.get("BctsRouteSftpJob").start(step1).listener(interceptingJob).build(); 
	}
}
