/**
 * NAME		:AppConfig.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.etrade.bcts.controller.CaseController;


@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = "com.etrade.bcts")
public class JobsLauncherConfig extends WebMvcConfigurerAdapter{
	private static final Logger LOG = LoggerFactory.getLogger(JobsLauncherConfig.class);
	@Autowired
	private JobLauncher jobLaucher;
	
	@Autowired
	private Job lvCmJob;
	
	@Autowired
	private Job pcJob;
	
	@Autowired
	private Job permitXMLJob;
	
    @Scheduled(fixedDelay=5000)
    public void performLvCmJob() throws Exception
    {
    	LOG.info("Alert Job ----performLvCmJob");
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLaucher.run(lvCmJob, params);
    }
    
//    @Scheduled(fixedDelay=5000)
    public void performPcJob() throws Exception
    {
    	LOG.info("Alert Job ----performPcJob");
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLaucher.run(pcJob, params);
    }
    
	  @Scheduled(fixedDelay=5000)
	  public void performPermitXMLJob() throws Exception
	  {
	  		LOG.info("Alert Job ----performPermitXMLJob");
	      JobParameters params = new JobParametersBuilder()
	              .addString("JobID", String.valueOf(System.currentTimeMillis()))
	              .toJobParameters();
	      jobLaucher.run(permitXMLJob, params);
	  }
}

