package com.etrade.bcts.dao;

import java.util.List;

import com.etrade.bcts.model.Permit;

public interface PermitDao {
	List<Permit> search(String term);
}
