package com.etrade.bcts.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.etrade.bcts.converter.RoleToUserProfileConverter;
import com.etrade.bcts.dao.UserDocumentDao;
import com.etrade.bcts.model.UploadFile;
import com.etrade.bcts.model.UploadFileBucket;
import com.etrade.bcts.model.User;
import com.etrade.bcts.model.UserDocument;

@Service("userDocumentService")
@Transactional
@PropertySource(value = { "classpath:application.properties" })
public class UserDocumentServiceImpl implements UserDocumentService{

	static final Logger logger = LoggerFactory.getLogger(UserDocumentServiceImpl.class);
	
	@Autowired
    private Environment environment;
	
	@Autowired
	UserDocumentDao dao;

	public UserDocument findById(int id) {
		return dao.findById(id);
	}

	public List<UserDocument> findAll() {
		return dao.findAll();
	}

	public List<UserDocument> findAllByUserId(String userId) {
		return dao.findAllByUserId(userId);
	}
	
	public void saveDocument(UserDocument document){
		dao.save(document);
	}

	public void deleteById(int id){
		dao.deleteById(id);
	}
	
	public void uploadFile(UploadFileBucket fileBucket, User user) throws IOException{
		MultipartFile[] files = fileBucket.getFiles();
		logger.info("upload files:" + files.length );
		String permitNo = fileBucket.getPermitNo();
		for(MultipartFile file : files) {
	        byte[] bytes = file.getBytes();
	        logger.info("bytes:" + bytes.length );
	        Path path = Paths.get(environment.getRequiredProperty("upload.directory") + file.getOriginalFilename());
	        Files.write(path, bytes);
	        saveDocument(permitNo, file, user, path.toString());
		}
	}	
	
	private void saveDocument(String permitNo, MultipartFile file, User user, String path) {		
		UserDocument document = new UserDocument();		
		document.setName(file.getOriginalFilename());
		document.setUser(user);
		document.setType("1");
		document.setUrl(path);
		document.setUser(user);
		document.setDocSize("1");
		document.setPermitNo(permitNo);
		document.setUploadedDate(new Date());
		saveDocument(document);
	}
}
