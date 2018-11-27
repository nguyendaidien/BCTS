package com.etrade.bcts.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.etrade.bcts.model.BctsJobHeader;

@Repository("jobHeaderDao")
public class JobHeaderDaoImpl extends AbstractDao<Integer, BctsJobHeader> implements JobHeaderDao {
	static final Logger LOG = LoggerFactory.getLogger(JobHeaderDaoImpl.class);
	
	public void saveJobHeader(BctsJobHeader jobHeader) {
		LOG.info("JobHeaderDaoImpl: permit:{}",jobHeader.toString());
		persist(jobHeader);
	}

}
