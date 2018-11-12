package com.etrade.bcts.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Component(value="propertiesConfig")
@PropertySource(
          value={"classpath:application.properties"},
          ignoreResourceNotFound = true)
@Scope(value=WebApplicationContext.SCOPE_APPLICATION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PropertiesConfig{
	@Autowired
	Environment environment;
	
	private Long MAX_REQUEST_SIZE = 0L;
	private Long MAX_FILE_SIZE = 0L;
	
	public Long getMAX_REQUEST_SIZE() {
		if(environment.getRequiredProperty("upload.MAX_REQUEST_SIZE") !=null) {
			MAX_REQUEST_SIZE = Long.parseLong(environment.getRequiredProperty("upload.MAX_REQUEST_SIZE"));
		}
		return MAX_REQUEST_SIZE;
	}
	public Long getMAX_FILE_SIZE() {
		if(environment.getRequiredProperty("upload.MAX_FILE_SIZE") !=null) {
			MAX_FILE_SIZE = Long.parseLong(environment.getRequiredProperty("upload.MAX_FILE_SIZE"));
		}
		return MAX_FILE_SIZE;		
	}
	
}
