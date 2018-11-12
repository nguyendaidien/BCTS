package com.etrade.bcts.configuration;

import java.net.MalformedURLException;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
 
    @Value("classpath:/org/springframework/batch/core/schema-drop-oracle10g.sql")
    private Resource dropReopsitoryTables;
 
    @Value("classpath:/org/springframework/batch/core/schema-oracle10g.sql")
    private Resource dataReopsitorySchema;
    @Autowired
	HibernateConfiguration configuration;
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.sqlite.JDBC");
//        dataSource.setUrl("jdbc:sqlite:repository.sqlite");
//        return dataSource;
//    }
// 
    @Bean
    public DataSourceInitializer dataSourceInitializer()
      throws MalformedURLException {
        ResourceDatabasePopulator databasePopulator = 
          new ResourceDatabasePopulator();
 
        databasePopulator.addScript(dropReopsitoryTables);
        databasePopulator.addScript(dataReopsitorySchema);
        databasePopulator.setIgnoreFailedDrops(true);
 
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(configuration.dataSource());
        initializer.setDatabasePopulator(databasePopulator);
 
        return initializer;
    }
 
    private JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(configuration.dataSource());
        factory.setTransactionManager(getTransactionManager());
        factory.afterPropertiesSet();
        return (JobRepository) factory.getObject();
    }
 
    private PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }
 
    public JobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
}
