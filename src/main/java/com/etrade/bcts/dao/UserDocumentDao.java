package com.etrade.bcts.dao;

import java.util.List;

import com.etrade.bcts.model.UserDocument;

public interface UserDocumentDao {

	List<UserDocument> findAll();
	
	UserDocument findById(int id);
	
	void save(UserDocument document);
	
	List<UserDocument> findAllByUserId(String userId);
	
	List<UserDocument> findAllByPermitNo(String permitNo);
	
	void deleteById(int id);
}
