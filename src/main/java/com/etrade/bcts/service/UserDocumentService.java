package com.etrade.bcts.service;

import java.io.IOException;
import java.util.List;

import com.etrade.bcts.model.UploadFile;
import com.etrade.bcts.model.UploadFileBucket;
import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserDocument;

public interface UserDocumentService {

	UserDocument findById(int id);

	List<UserDocument> findAll();
	
	List<UserDocument> findAllByUserId(String id);
	
	List<UserDocument> findAllByPermitNo(String permitNo);
	
	void saveDocument(UserDocument document);
	
	void deleteById(int id);
	
	void uploadFile(UploadFile fileBucket, User user) throws IOException; 
}
