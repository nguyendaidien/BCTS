package com.etrade.bcts.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class UploadFileBucket {

	private MultipartFile[] files;
	private String permitNo;
	private String docType;
	
	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}		
}