package com.etrade.bcts.scheduler;

import org.springframework.batch.item.database.HibernateCursorItemReader;

import com.etrade.bcts.model.BCTSAlert;

public class BCTSAlertHibernateItemReader extends HibernateCursorItemReader<BCTSAlert>{
	
}
