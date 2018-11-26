package com.etrade.bcts.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
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
import com.etrade.bcts.util.TrwDate;

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
	
	public List<UserDocument> findAllByPermitNo(String permitNo) {
		return dao.findAllByPermitNo(permitNo);
	}	
	
	public void saveDocument(UserDocument document){
		dao.save(document);
	}

	public void deleteById(int id){
		dao.deleteById(id);
	}
	
//	public void uploadFile(UploadFileBucket fileBucket, User user) throws IOException{
//		MultipartFile[] files = fileBucket.getFiles();
//		logger.info("upload files:" + files.length );
//		String permitNo = fileBucket.getPermitNo();
//		for(MultipartFile file : files) {
//	        byte[] bytes = file.getBytes();
//	        logger.info("bytes:" + bytes.length );
//	        Path path = Paths.get(environment.getRequiredProperty("upload.directory") + file.getOriginalFilename());
//	        Files.write(path, bytes);
//	        saveDocument(permitNo,fileBucket.getDocType(), file, user, path.toString());
//		}
//	}	
	
	public void uploadFile(UploadFile uploadFile, User user) throws IOException{
		MultipartFile file = uploadFile.getFile();
		
		String permitNo = uploadFile.getPermitNo();
		byte[] bytes = file.getBytes();
		TrwDate date = new TrwDate();
        logger.info("bytes:" + bytes.length + "; size:" + file.getSize());

		Path directory = Paths.get(environment.getRequiredProperty("upload.directory"), 
        						user.getCompany().getUeiNo(), String.valueOf(Calendar.getInstance().get(Calendar.YEAR)),
        						date.getXMLDateString());
        
        boolean pathExist = Files.exists(directory);
        if(!pathExist) {
        	Files.createDirectories(directory);
        }
        Path path = Paths.get(directory.toString(), file.getOriginalFilename());
        Files.write (path, bytes, StandardOpenOption.CREATE);
        saveDocument(permitNo, uploadFile.getDocType(), file, user, path.toString());
	}	
	
	private void saveDocument(String permitNo, String docType, MultipartFile file, User user, String path) {
		UserDocument document = new UserDocument();		
		document.setName(file.getOriginalFilename());
		document.setUser(user);
		document.setType(docType);
		document.setUrl(path);
		document.setUser(user);
		document.setDocSize(file.getSize());
		document.setPermitNo(permitNo);
		document.setUploadedDate(new Date());
		saveDocument(document);
	}

	@Override
	public List<UserDocument> findAllByCaseId(String caseId) {
		return dao.findAllByCaseId(caseId);
	}
}
