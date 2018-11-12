package com.etrade.bcts.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UploadedFiles {
	private HashMap<String, List<UploadFile>> uploadedFilesMap = new HashMap<String, List<UploadFile>>();
	private long totalFilesSize;

	
	public HashMap<String, List<UploadFile>> getUploadedFilesMap() {
		return uploadedFilesMap;
	}

	public void setUploadedFilesMap(HashMap<String, List<UploadFile>> uploadedFilesMap) {
		this.uploadedFilesMap = uploadedFilesMap;
	}

	public long getTotalFilesSize(String key) {
		totalFilesSize = 0;
		List<UploadFile> files = uploadedFilesMap.get(key);
		if(files == null) {
			return totalFilesSize;
		}
			
		for(UploadFile file : files ) {
			totalFilesSize += file.getDocSize();
		}
		return totalFilesSize;
	}

	public List<UploadFile> getFilesByPermits(String key) {
		return uploadedFilesMap.get(key);
	}
	
	public void putFiles(String key, UploadFile file){
		List<UploadFile> list = uploadedFilesMap.get(key);
		if(list == null) {
			list = new ArrayList<UploadFile>();
			list.add(file);
			uploadedFilesMap.put(key, list);
		} else {
			list.add(file);
		}		
	}
	
	public void setTotalFilesSize(long totalFilesSize) {
		this.totalFilesSize = totalFilesSize;
	}	
	
}
