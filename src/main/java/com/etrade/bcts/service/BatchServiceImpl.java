/**
 * NAME		:BatchServiceImpl.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etrade.bcts.common.util.WebDataDict;
import com.etrade.bcts.common.xml.BctsXMLProcessException;
import com.etrade.bcts.common.xml.BctsXMLUtil;
import com.etrade.bcts.dao.BatchDao;
import com.etrade.bcts.model.BctsPermitType;
import com.etrade.bcts.model.BctsRoute;
import com.etrade.bcts.util.BctsConstants;
import com.etrade.bcts.util.BctsFileUtil;
import com.etrade.bcts.util.SFTPClient;
import com.etrade.bcts.util.SFTPClientException;

@Service("batchService")
@Transactional
@PropertySource("classpath:sftpconfig.properties")
public class BatchServiceImpl implements BatchService {
	private static final Logger LOG = LoggerFactory.getLogger(BatchServiceImpl.class);
	@Autowired
	private BatchDao dao;

	@Autowired
	private Environment environment;
	@Autowired
	private ApplicationContext applicationContext;
	private ArrayList<String> errorsFile = new ArrayList<String>();
	private static final String SFTPPROPFILE = "classpath:sftpconfig.properties";

	public List<BctsRoute> findActiveJobs() {
		return dao.findActiveJobs();
	}

	public List<BctsRoute> findAllJobs() {
		return dao.findAllJobs();
	}

	/**
	 * Download files from DocX for all Integrator Users and process them
	 * 
	 * @param info
	 * @return
	 * @throws IOException
	 */
	public void doReceiveDocX() throws BctsXMLProcessException, IOException {
		final Properties sftpconfigProp = new Properties();
		sftpconfigProp.load(this.applicationContext.getResource(SFTPPROPFILE).getInputStream());
		String privateKey = sftpconfigProp.getProperty(BctsConstants.KEY_SFTP_PRIVATE_KEY);
		String passPhrase = sftpconfigProp.getProperty(BctsConstants.KEY_PASS_PHRASE);
		String host = sftpconfigProp.getProperty(BctsConstants.KEY_HOST_NAME);
		String user = sftpconfigProp.getProperty(BctsConstants.KEY_USER_ID);
		/*
		 * email = docXProp.getProperty(BctsConstants.KEY_EMAIL, ""); ccEmail =
		 * docXProp.getProperty(BctsConstants.KEY_CC_EMAIL, "");
		 */
		
		if (null == privateKey || privateKey.isEmpty()) {
			throw new BctsXMLProcessException("failed - privateKey Property value is empty!");
		}
		if (null == host || host.isEmpty()) {
			throw new BctsXMLProcessException("failed - host Property value is empty!");
		}
		if (null == user || user.isEmpty()) {
			throw new BctsXMLProcessException("failed - user Property value is empty!");
		}

		LOG.debug("host: {} user: {} privateKey: {} passPhrase: {}", host, user, privateKey, passPhrase);
		String docType = BctsConstants.OUTPUTPERMIT;
		String downloadFolder = BctsConstants.DOWNLOADFOLDER;
		try {
			downloadFiles(host, user, privateKey, passPhrase, downloadFolder, docType);
		} catch (SFTPClientException e) // catch, log, and continue with other folders
		{
			LOG.error("Exception ", e);
			// emailAlert("Error downloading from " + "downloadFolder" + "docType: " +
			// "doc_type", methodName, e);
		}
	}

	/**
	 * Tests DocX Connectivity
	 * 
	 * @return
	 * @throws IOException
	 * @throws SFTPClientException
	 */

	/**
	 * Download Files from a remote folder and publish to Mapping Queue for further
	 * processing
	 * 
	 * @param host
	 * @param user
	 * @param privateKey
	 * @param remoteFolder
	 * @throws TrwDocXException
	 * @throws IOException
	 */
	private void downloadFiles(String host, String user, String privateKey, String passPhrase, String remoteFolderPath,
			String docType) throws SFTPClientException, IOException {
		final String methodName = "downloadFiles: ";
		SFTPClient client = null;
		File localFolder = null;
		List<File> downloadedFiles = null;

		// List and Download Files
		try {
			localFolder = new File(WebDataDict.PATH_DATA + "prs/input" + File.separator);

			LOG.info(methodName + "from {} to {}", remoteFolderPath, localFolder);

			client = new SFTPClient(user, host, null, privateKey, passPhrase);

			client.openSFTP();

			List<String> filePaths = client.listFolder(remoteFolderPath);

			if (filePaths != null) {
				downloadedFiles = new ArrayList<File>(filePaths.size());
				boolean downloadSuccess = false;

				for (String srcFilePath : filePaths) {
					String srcFileName = new File(srcFilePath).getName();
					if (srcFileName.endsWith("xml") || srcFileName.endsWith("XML")) {
						File dstFile = new File(localFolder.getPath(), srcFileName);
						String dstFilePath = dstFile.getPath();
						LOG.info(methodName + "from {}:{} to {}", host, srcFilePath, dstFilePath);
						downloadSuccess = client.downloadFile(srcFilePath, dstFilePath);

						if (downloadSuccess) // add successfully downloaded files to downloadedFiles
						{
							client.deleteFile(srcFilePath); // delete when confirm successful download

							if (dstFile.exists()) {
								LOG.debug(methodName + "adding {} to downloadedFiles", dstFilePath);
								downloadedFiles.add(dstFile);
							}
						}
					}

				}
			}
		} catch (SFTPClientException e) {
			throw new SFTPClientException(methodName + "from " + remoteFolderPath, e);
		} finally {
			client.closeSFTP();
			client = null;
		}

		// process downloaded files - publish to Map Queue (after closing SFTP Client
		// connection)
		if (downloadedFiles != null && !downloadedFiles.isEmpty()) {
			LOG.info(methodName + "{} files downloaded from {}:{}", downloadedFiles.size(), host, remoteFolderPath);
			for (File inputFile : downloadedFiles) {
				processFile(inputFile, docType);
			}
		}
	}

	/**
	 * Processing of files
	 * 
	 * @param inputFile
	 * @param mapId
	 */
	private boolean processFile(File inputFile, String docType) {
		LOG.info("processFile inputFile.getPath():{}", inputFile.getPath());
		boolean success = false;
		try {
			BctsPermitType bctsPermitType = BctsXMLUtil.getPermitXmlInfo(inputFile.getPath());
			System.out.println("bctsPermitType:"+bctsPermitType.toString());
			if (null != bctsPermitType) {
				// DB operation
			}
			success = true;
		} catch (BctsXMLProcessException e) {
			success = false;
			LOG.error("processFile cannot parse {}", inputFile.getPath(), e);
			// capture the error and send email
			errorsFile.add(inputFile.getName());
			errorsFile.add(", ");
		} finally {
			if (success) {
				LOG.info("processFile successful for {}", inputFile.getPath());
				File archiveFolder = new File(WebDataDict.PATH_DATA + "prs/archives/");
				File dstFile = new File(archiveFolder, inputFile.getName());
				try {
					BctsFileUtil.moveFile(inputFile, dstFile);
				} catch (IOException e) {
					LOG.error("processFile Error archiving {} to {}", inputFile.getPath(), dstFile.getPath(), e);
				}
			} else {
				LOG.info("processFile() failed for {}", inputFile.getPath());
				File errorFolder = new File(WebDataDict.PATH_DATA + "prs/error/" + docType + File.separator);
				File dstFile = new File(errorFolder, inputFile.getName());
				try {
					BctsFileUtil.moveFile(inputFile, dstFile);
				} catch (IOException e) {
					LOG.error("processFile() Error archiving {} to {}", inputFile.getPath(), dstFile.getPath(), e);
				}
			}
		}
		return success;
	}

	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
