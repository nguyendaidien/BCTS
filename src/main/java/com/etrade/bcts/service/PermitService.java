package com.etrade.bcts.service;

import java.util.List;

import com.etrade.bcts.model.Permit;

public interface PermitService {
	List<Permit> search(String term);
}
