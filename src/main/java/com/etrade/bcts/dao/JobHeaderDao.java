package com.etrade.bcts.dao;

import com.etrade.bcts.model.BctsJobHeader;

public interface JobHeaderDao {
	void saveJobHeader(BctsJobHeader jobHeader);
}
