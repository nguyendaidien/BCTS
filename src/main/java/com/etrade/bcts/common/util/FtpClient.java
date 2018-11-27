package com.etrade.bcts.common.util;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.FilesCache;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpClient {
	private static final Logger LOG = LoggerFactory.getLogger(FtpClient.class);

	private String ftpUser;
	private String ftpPassword;
	private String ftpUri;
	private String privateKey;
	private FileObject fo;
	private DefaultFileSystemManager fsManager;
	private FileSystemOptions fsOptions;

	public FtpClient(String ftpUri, String ftpUser, String ftpPassword, String privateKey) {

		this.ftpUri = ftpUri;
		this.ftpUser = ftpUser;
		this.ftpPassword = ftpPassword;
		this.privateKey = privateKey;
	}

	public void connect() throws Exception {

		if (fsOptions != null) {
			return;
		}

		try {

			fsOptions = new FileSystemOptions();

			StaticUserAuthenticator auth = new StaticUserAuthenticator(null, ftpUser, ftpPassword);
			DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(fsOptions, auth);
			FtpFileSystemConfigBuilder.getInstance().setSoTimeout(fsOptions, 60);
			FtpFileSystemConfigBuilder.getInstance().setDataTimeout(fsOptions, 60);
			FtpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(fsOptions, false);

			if (ftpUri.startsWith("sftp")) {
				SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fsOptions, "no");
				SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(fsOptions, false);
			}

			// now we create a new filesystem manager
			fsManager = new StandardFileSystemManager();
			fsManager.init();

			// get file object representing the local file
			if (privateKey != null && !privateKey.isEmpty()) {
				File pFile = File.createTempFile("temp_", ".pem");
				FileUtils.write(pFile, privateKey);
				SftpFileSystemConfigBuilder.getInstance().setIdentities(fsOptions, new File[] { pFile });
			}
			fo = fsManager.resolveFile(ftpUri, fsOptions);

		} catch (Exception e) {

			LOG.error("connect error", e);

			/* throw new Exception(ErrorCode.FTP_OPEN_CONN_ERR_CODE, null, e); */
			throw new Exception("Connection failed" + e);
		}

	}

	public List<String> ls(String path) throws Exception {

		connect();

		FileObject fileObject = null;
		List<String> filenames = new ArrayList<>();
		FileSystemOptions opts = new FileSystemOptions();
		SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);

		try {

			fileObject = fo.getFileSystem().resolveFile(path);
			FileObject[] files = fileObject.getChildren();

			if (files != null && files.length > 0) {
				for (FileObject f : files) {
					filenames.add(f.getName().getBaseName());

				}
			}

		} catch (Exception e) {
			LOG.error("ls error", e);
			/* throw new Exception(ErrorCode.FTP_LS_ERR_CODE, null, e); */
			throw new Exception("lss error code" + e);
		}
		return filenames;
	}

	public void rm(String path) throws Exception {
		connect();
		FileObject fileObject = null;
		try {
			fileObject = fo.getFileSystem().resolveFile(path);
			fileObject.delete();
		} catch (Exception e) {
			LOG.error("rm path error", e);
			/* throw new Exception(ErrorCode.FTP_RM_ERR_CODE, null, e); */
			throw new Exception("RM error code" + e);
		}
	}

	public void rmDir(String dirPath) throws Exception {
		connect();
		FileObject fileObject = null;
		try {
			fileObject = fo.getFileSystem().resolveFile(dirPath);
			fileObject.delete();
		} catch (Exception e) {
			LOG.error("rm directory error", e);
			/* throw new Exception(ErrorCode.FTP_RM_ERR_CODE, null, e); */
		}
	}

	public InputStream get(String filePath) throws Exception {
		connect();
		FileObject fileObject = null;
		try {
			fileObject = fo.getFileSystem().resolveFile(filePath);

		} catch (Exception e) {
			LOG.error("get error", e);
			/* throw new Exception(ErrorCode.FTP_RM_ERR_CODE, null, e); */
		}
		return fileObject.getContent().getInputStream();
	}

	public void put(String targetPath, String sourcePath) throws Exception {
		connect();
		FileObject remoteFile = null;
		FileObject localFile = null;
		LOG.debug("put file, remote path: {}, local path: {}", targetPath, sourcePath);
		try {
			remoteFile = fo.getFileSystem().resolveFile(targetPath);
			localFile = VFS.getManager().resolveFile(sourcePath);
			if (localFile.getType() == FileType.FILE) {
				/*
				 * To store the file in remote destination with same filename if it is a folder
				 * or does not exist
				 */
				if (!remoteFile.exists()) {
					remoteFile.createFolder();
				}
				if (remoteFile.getType() != FileType.FILE) {
					remoteFile = remoteFile.resolveFile(sourcePath);
				}
			}
			remoteFile.copyFrom(localFile, Selectors.SELECT_ALL);
			remoteFile.close();
			localFile.close();
		} catch (Exception e) {
			LOG.error("put file error, remote path: {}, local path: {} , exception {}", targetPath, sourcePath,
					e.getMessage());
		}
	}

	public void put(String targetPath, byte[] content) throws Exception {
		connect();
		FileObject remoteFile = null;
		OutputStream os = null;
		try {
			remoteFile = fo.getFileSystem().resolveFile(targetPath);
			remoteFile.createFile();
			os = remoteFile.getContent().getOutputStream();
			os.write(content);
			os.flush();
		} catch (Exception e) {
			LOG.debug("Error occurred when writing file", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					LOG.debug("error closing file", e);
				}

				os = null;
			}
		}
	}

	public void rename(String sourcePath, String destPath) throws Exception {
		connect();

		FileObject sourceFile = null;
		FileObject destFile = null;

		try {
			sourceFile = fo.getFileSystem().resolveFile(sourcePath);
			destFile = fo.getFileSystem().resolveFile(destPath);
			sourceFile.moveTo(destFile);
		} catch (Exception e) {
			LOG.error("rename file error, source path: {}, destination path: {},exception {}", sourcePath, destPath,
					e.getMessage());
		}

	}

	public void saveExit() {
		FileSystem fs = null;
		if (fo != null) {
			fs = fo.getFileSystem();
			try {
				fo.close();
			} catch (Exception e) {
				LOG.debug("error closing VFS file object", e);
			}
		}

		if (fsManager != null) {
			FilesCache filesCache = fsManager.getFilesCache();
			if (filesCache != null && fs != null) {
				filesCache.clear(fs);
			}
			fsManager.freeUnusedResources();
			fsManager.close();
			fsManager.closeFileSystem(fs);
		}
		fo = null;
	}
}
