/**
 * @author ajayasamanta
 * Class: SFTPClient.java
 */
package com.etrade.bcts.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etrade.bcts.common.util.BctsDataDict;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPClient { 
	private static final Logger LOG = LoggerFactory.getLogger(SFTPClient.class);

	private String knownHostsFile = BctsDataDict.BCTS_CONFIG_PATH + "hostkey.pub"; // default to hostkey.pub if not
																					// defined
	private String user = null;
	private String host = null;
	private int port = 22; // defaults to 22 if setPort(port) is not called

	private String password = null;
	private String privateKey = null; // path to the private key file
	private String passPhrase = null; // use this only if passPhrase is needed

	private Session session = null; // IMPORTANT: Remember to closeSFTP() after use
	private ChannelSftp sftpChannel = null; // IMPORTANT: Remember to closeSFTP() after use

	/**
	 * Constructs a SFTPClient object using default KnownHosts file
	 *
	 * @param user       - login user
	 * @param host       - host ip address or hostname
	 * @param password   - if password is null, then privateKey must be provided
	 * @param privateKey - if privateKey is null, then password must be provided
	 * @throws SFTPClientException
	 */
	public SFTPClient(String user, String host, String password, String privateKey, String passPhrase)
			throws SFTPClientException {
		if (password != null && !password.isEmpty()) {
			this.password = password;
		} else {
			if (privateKey != null && !privateKey.isEmpty()) {
				this.privateKey = privateKey;
				this.passPhrase = passPhrase;
			} else {
				throw new SFTPClientException(
						"Error creating SFTPClient, either password or privateKey must be provided");
			}
		}
		this.user = user;
		this.host = host;
		createKnownHostsFile(knownHostsFile);
	}

	/**
	 * Constructs a SFTPClient object using default KnownHosts file
	 *
	 * @param user       - login user
	 * @param host       - host ip address or hostname
	 * @param port
	 * @param password   - if password is null, then privateKey must be provided
	 * @param privateKey - if privateKey is null, then password must be provided
	 * @param passPhrase
	 * @throws SFTPClientException
	 */
	public SFTPClient(String user, String host, int port, String password, String privateKey, String passPhrase)
			throws SFTPClientException {
		///////////////////////////////////////////////////////
		// Validate first
		///////////////////////////////////////////////////////
		if (password != null && !password.isEmpty()) {
			this.password = password;
		} else if (privateKey != null && !privateKey.isEmpty()) {
			this.privateKey = privateKey;
			this.passPhrase = passPhrase;
		} else {
			throw new SFTPClientException("Error creating SFTPClient, either password or privateKey must be provided");
		}
		///////////////////////////////////////////////////////
		this.user = user;
		this.host = host;
		this.port = port;

		createKnownHostsFile(knownHostsFile);
	}


	/**
	 * Closes the SFTP Channel and Session
	 */
	public void closeSFTP() {
		if (this.sftpChannel != null) {
			LOG.info(" closeSFTP() SFTP channel exiting...");
			sftpChannel.exit();
			sftpChannel = null;
		}

		if (this.session != null) {
			LOG.info(" closeSFTP() session disconnecting...");
			session.disconnect();
			session = null;
		}
		LOG.info(" closeSFTP() done...");
	}

	/**
	 * Validates input fields, then uploads the file
	 *
	 * @param srcFilePath - absolute filepath of the source file
	 * @param dstFilePath - absolute filepath of the destination file
	 * @return status of the operation "true" if successful
	 * @throws SFTPClientException - if field validation fails, source File does not
	 *                             exists, SFTP Channel not open
	 */
	public boolean uploadFile(String srcFilePath, String dstFilePath) throws SFTPClientException {
		boolean success = false;

		
		if (!(srcFilePath != null && !srcFilePath.trim().isEmpty())) {
			throw new SFTPClientException("uploadFile() failed - srcFilePath is empty!");
		}

		if (!fileExists(srcFilePath)) {
			throw new SFTPClientException("uploadFile() failed - source file does not exists: " + srcFilePath);
		}

		if (!(dstFilePath != null && !dstFilePath.trim().isEmpty())) {
			throw new SFTPClientException("uploadFile() failed - dstFilePath is empty!");
		}

		try {
			// Check if sFTP channel connected
			if (!(sftpChannel != null && sftpChannel.isConnected())){
				throw new SFTPClientException("uploadFile() failed - Please call openSFTP() first!");
			}
			LOG.info("uploadFile() Uploading file {} to {}", srcFilePath, dstFilePath);
			sftpChannel.put(srcFilePath, dstFilePath, null, ChannelSftp.OVERWRITE);
			success = true;
		} catch (SftpException e) {
			throw new SFTPClientException("uploadFile()  failed. srcFilePath: [" + srcFilePath + "] dstFilePath: " + dstFilePath, e);
		}
		return success;
	}

	/**
	 * Validates input fields, then downloads the file In actual use, do a listing
	 * first, then download 1 file at a time Followed by deleting remote file when
	 * successfully downloaded
	 *
	 * @param srcFilePath - absolute filepath of the source file
	 * @param dstFilePath - absolute filepath of the destination file
	 * @return status of the operation "true" if successful
	 * @throws SFTPClientException - if field validation fails, SFTP Channel not
	 *                             open
	 */
	public boolean downloadFile(String srcFilePath, String dstFilePath) throws SFTPClientException {
		boolean success = false;

		if (!(dstFilePath != null && !dstFilePath.trim().isEmpty())) {
			throw new SFTPClientException(" downloadFile() failed - dstFilePath is empty!");
		}

		if (!(srcFilePath != null && !srcFilePath.trim().isEmpty())) {
			throw new SFTPClientException("downloadFile() failed - srcFilePath is empty!");
		}

		FileOutputStream fos = null;
		try {
			if (!(sftpChannel != null && sftpChannel.isConnected())) {// Check if sFTP channel connected
				throw new SFTPClientException("downloadFile() failed - Please call openSFTP() first!");
			}
			LOG.info( "downloadFile() Downloading file {} to {}", srcFilePath, dstFilePath);

			fos = new FileOutputStream(dstFilePath);

			sftpChannel.get(srcFilePath, fos); // download in overwrite mode with no progress monitor
			success = true;
		} catch (SftpException | FileNotFoundException e) {
			throw new SFTPClientException("downloadFile() failed. srcFilePath: [" + srcFilePath + "] dstFilePath: " + dstFilePath, e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				LOG.error(" downloadFile() Error closing FileoutputStream",e);
			}
		}
		return success;
	}

	/**
	 * Validates input fields, then lists the remoteFolder In actual use, do a
	 * listing first, then download 1 file at a time Followed by deleting remote
	 * file when successfully downloaded
	 *
	 * @param remoteFolderPath - remote path to list
	 * @return List of Files <String> - each record contains the full filepath
	 * @throws SFTPClientException - if field validation fails, SFTP Channel not
	 *                             open
	 */
	public List<String> listFolder(String remoteFolderPath) throws SFTPClientException {
		List<String> filePaths = null;
		List<ChannelSftp.LsEntry> lsEntries = null;

		if (!(remoteFolderPath != null && !remoteFolderPath.trim().isEmpty())) {
			throw new SFTPClientException("listFolder() failed - remoteFolderPath is empty!");
		}

		try {
			if (!(sftpChannel != null && sftpChannel.isConnected())) // Check if sFTP channel connected
			{
				throw new SFTPClientException("listFolder()  failed - Please call openSFTP() first!");
			}
			LOG.info("listFolder() Listing remote folder {}", remoteFolderPath);

			lsEntries = sftpChannel.ls(remoteFolderPath);

			if (lsEntries != null && !lsEntries.isEmpty()) {
				filePaths = new ArrayList<String>(lsEntries.size());
				String fileName = null;

				for (int i = 0; i < lsEntries.size(); i++) {
					if (lsEntries.get(i) != null) {
						fileName = lsEntries.get(i).getFilename();

						if (".".equals(fileName) || "..".equals(fileName)) { // skip . and .. entries which comes with every list
							continue;
						}

						if (remoteFolderPath.endsWith("/") == false) // in case folderName does not include / delimiter
						{
							remoteFolderPath = remoteFolderPath + "/";
						}
						String remoteFilePath = remoteFolderPath + fileName;

						LOG.debug("listFolder() Adding {}", fileName);
						filePaths.add(remoteFilePath);
					}
				}
				LOG.info("listFolder() {} files found on {}", filePaths.size(), remoteFolderPath);
			} else {
				LOG.info("listFolder() No files found on {}", remoteFolderPath);
				filePaths = new ArrayList<String>(0);
			}
		} catch (SftpException e) {
			throw new SFTPClientException("listFolder() failed. remoteFolderPath: " + remoteFolderPath, e);
		}
		return filePaths;
	}

	/**
	 * Validates input fields, then deletes the file In actual use, do a listing
	 * first, then download 1 file at a time Followed by deleting remote file when
	 * successfully downloaded
	 *
	 * @param remoteFilePath - remote file to be deleted
	 * @return status of the operation "true" if successful
	 * @throws SFTPClientException - if field validation fails, SFTP Channel not
	 *                             open
	 */
	public boolean deleteFile(String remoteFilePath) throws SFTPClientException {
		boolean success = false;

		if (!(remoteFilePath != null && !remoteFilePath.trim().isEmpty())) {
			throw new SFTPClientException("deleteFile() failed - remoteFilePath is empty!");
		}

		try {
			if (!(sftpChannel != null && sftpChannel.isConnected())) // Check if sFTP channel connected
			{
				throw new SFTPClientException("deleteFile() failed - Please call openSFTP() first!");
			}
			LOG.info("Deleting remote file {}", remoteFilePath);
			sftpChannel.rm(remoteFilePath);
			success = true;
		} catch (SftpException e) {
			throw new SFTPClientException("deleteFile() failed. remoteFilePath: " + remoteFilePath, e);
		}
		return success;
	}

	/**
	 * Move file at Remote SFTP server from one folder to another folder
	 * 
	 * @param srcFilePath
	 * @param dstFilePath
	 * @return
	 * @throws SFTPClientException
	 */
	public boolean moveFile(String srcFilePath, String dstFilePath) throws SFTPClientException {
		boolean success = false;

		if (!(dstFilePath != null && !dstFilePath.trim().isEmpty())) {
			throw new SFTPClientException("moveFile() failed - dstFilePath is empty!");
		}

		if (!(srcFilePath != null && !srcFilePath.trim().isEmpty())) {
			throw new SFTPClientException("moveFile() failed - srcFilePath is empty!");
		}
		///////////////////////////////////////////////////////////////////

		try {
			if (!(sftpChannel != null && sftpChannel.isConnected())) // Check if sFTP channel connected
			{
				throw new SFTPClientException("moveFile()  failed - Please call openSFTP() first!");
			}
			LOG.info("moveFile() Moving file from {} to {}", srcFilePath, dstFilePath);

			sftpChannel.rename(srcFilePath, dstFilePath);

			success = true;
		} catch (SftpException e) {
			throw new SFTPClientException("moveFile() failed. srcFilePath: [" + srcFilePath + "] dstFilePath: " + dstFilePath, e);
		}
		return success;
	}

	/**
	 * Creates a Remote Folder
	 * 
	 * @param folderPath
	 * @return
	 * @throws SFTPClientException
	 */
	public boolean createFolder(String folderPath) throws SFTPClientException {
		boolean success = false;

		if (!(folderPath != null && !folderPath.trim().isEmpty())) {
			throw new SFTPClientException("createFolder() failed - folderPath is empty!");
		}
		///////////////////////////////////////////////////////////////////

		try {
			if (!(sftpChannel != null && sftpChannel.isConnected())) // Check if sFTP channel connected
			{
				throw new SFTPClientException("createFolder() failed - Please call openSFTP() first!");
			}
			LOG.info("createFolder() Creating {}", folderPath);

			sftpChannel.mkdir(folderPath);
			success = true;
		} catch (SftpException e) {
			throw new SFTPClientException("createFolder() failed: folderPath: [" + folderPath + "]", e);
		}
		return success;
	}

	/**
	 * Deletes a Remote Folder
	 * 
	 * @param folderPath
	 * @return
	 * @throws SFTPClientException
	 */
	public boolean removeFolder(String folderPath) throws SFTPClientException {
		boolean success = false;

		if (!(folderPath != null && !folderPath.trim().isEmpty())) {
			throw new SFTPClientException("removeFolder() failed - folderPath is empty!");
		}
		///////////////////////////////////////////////////////////////////

		try {
			if (!(sftpChannel != null && sftpChannel.isConnected())) // Check if sFTP channel connected
			{
				throw new SFTPClientException("removeFolder() failed - Please call openSFTP() first!");
			}
			LOG.info("removeFolder() Removing {}", folderPath);

			sftpChannel.rmdir(folderPath);
			success = true;
		} catch (SftpException e) {
			throw new SFTPClientException("removeFolder() failed: folderPath: [" + folderPath + "]", e);
		}
		return success;
	}

	/**
	 * Connects to an SFTP Channel, Remember to call closeSFTP() after finishing
	 *
	 * @param session
	 * @return
	 * @throws JSchException
	 */
	public void openSFTP() throws SFTPClientException {
		JSch jsch = new JSch();

		LOG.debug("openSFTP() started...");
		try {
			jsch.setKnownHosts(knownHostsFile); // for storing host key of remote hosts

			openSession(jsch);
		} catch (JSchException e) {
			LOG.error("openSFTP() JSchException at {}@{}:{}", user, host, port, e);

			if (e.getMessage() != null && e.getMessage().startsWith("UnknownHostKey")) {
				LOG.info("openSFTP() Adding missing HostKey:{} ", session.getHostKey().getKey());
				session.getHostKeyRepository().add(session.getHostKey(), null);
			} else {
				throw new SFTPClientException("openSFTP() JSchException at " + getConnectInfo(user, host, port), e);
			}
		}

		try {
			if (!session.isConnected()) // this step is needed if host key was not present
			{
				openSession(jsch);
			}
			LOG.debug("openSFTP() Opening SFTP channel...");
			sftpChannel = (ChannelSftp) session.openChannel("sftp");

			sftpChannel.setInputStream(System.in);
			sftpChannel.setOutputStream(System.out);
			sftpChannel.connect();
			LOG.info("openSFTP() SFTP channel connected...");
		} catch (JSchException e) {
			throw new SFTPClientException("openSFTP() JSchException at " + getConnectInfo(user, host, port), e);
		}
	}

	/**
	 * Opens and connects session
	 *
	 * @param jsch
	 * @throws JSchException
	 */
	private void openSession(JSch jsch) throws JSchException {
		session = jsch.getSession(user, host, port);

		if (privateKey != null && privateKey.length() > 0) {
			jsch.addIdentity(privateKey, passPhrase); // for SSH key authentication
		} else if (password != null && password.length() > 0) {
			session.setPassword(password); // For Password authentication
		}
		session.connect();
	}

	/**
	 * Check if a file exists
	 *
	 * @param filePath
	 * @return
	 */
	private boolean fileExists(String filePath) {
		boolean exists = false;

		File file = new File(filePath);

		if (file.exists()) {
			exists = true;
		}
		return exists;
	}

	/**
	 * Creates an empty knownHosts file if it doesn't exist
	 *
	 * @param hostPath
	 * @return
	 */
	private boolean createKnownHostsFile(String hostPath) {
		boolean createSuccess = false;

		try {
			File file = new File(hostPath);
			createSuccess = file.createNewFile();
		} catch (IOException e) {
			LOG.error("IOException in createKnownHostsFile: {}", hostPath, e);
		}
		return createSuccess;
	}

	/**
	 * Get connection details for error messages
	 *
	 * @param user
	 * @param host
	 * @param port
	 * @return
	 */
	private String getConnectInfo(String user, String host, int port) {
		StringBuilder errorBuff = new StringBuilder();

		errorBuff.append(user).append("@").append(host).append(":").append(port);

		return errorBuff.toString();
	}

	////////////////////////////////////////////////////////////////////////////////////
	// Getters and Setters
	////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the knownHostsFile
	 */
	public String getKnownHostsFile() {
		return knownHostsFile;
	}

	/**
	 * @param knownHostsFile
	 */
	public void setKnownHostsFile(String knownHostsFile) {
		this.knownHostsFile = knownHostsFile;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * @return the passPhrase
	 */
	public String getPassPhrase() {
		return passPhrase;
	}

	/**
	 * @param passPhrase the passPhrase to set
	 */
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}
}
