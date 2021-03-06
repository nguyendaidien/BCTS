package com.etrade.bcts.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.etrade.bcts.model.UploadFile;
import com.etrade.bcts.model.UploadFileBucket;



@Component
public class FileValidator implements Validator {
		
	public boolean supports(Class<?> clazz) {
		return UploadFile.class.isAssignableFrom(clazz);
	}

//	public void validate(Object obj, Errors errors) {
//		UploadFileBucket file = (UploadFileBucket) obj;
//			
//		for(MultipartFile f : file.getFiles()) {
//			if (f.getSize() == 0) {
//				errors.rejectValue("files", "missing.file");
//			}
//		}
//	}
	public void validate(Object obj, Errors errors) {
		UploadFile file = (UploadFile) obj;
			
		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file", "missing.file");
		}
	}
}

