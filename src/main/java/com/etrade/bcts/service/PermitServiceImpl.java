package com.etrade.bcts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.dao.PermitDao;
import com.etrade.bcts.model.Permit;

@Service("permitService")
@Transactional
public class PermitServiceImpl implements PermitService {

	@Autowired
	PermitDao dao;
	
	@Override
	public List<Permit> search(String term) {
		return dao.search(term);
	}

}
