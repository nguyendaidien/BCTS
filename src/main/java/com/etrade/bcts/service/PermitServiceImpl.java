package com.etrade.bcts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.JobHeaderDao;
import com.etrade.bcts.dao.PermitDao;
import com.etrade.bcts.model.BctsJobHeader;
import com.etrade.bcts.model.BctsPermitType;
import com.etrade.bcts.model.Permit;

@Service("permitService")
@Transactional
public class PermitServiceImpl implements PermitService {
	static final Logger LOG = LoggerFactory.getLogger(PermitServiceImpl.class);
	@Autowired
	PermitDao dao;
	
	@Autowired
	JobHeaderDao jobHeaderDao;
	
	@Override
	public List<Permit> search(String term) {
		return dao.search(term);
	}
	
	
	@Override
	public void saveJobHeader(BctsJobHeader jobHeader) {
		LOG.info("PermitServiceImpl saveJobHeader() start");
		jobHeaderDao.saveJobHeader(jobHeader);
		LOG.info("PermitServiceImpl saveJobHeader() end");
		
	}

}
