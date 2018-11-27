package com.etrade.bcts.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.util.Base64;

public class BctsFileUtil
{
	static final Logger LOG = LoggerFactory.getLogger(BctsFileUtil.class);

    /**
     * Get the Parent path (without ending slash /) Checks file exists before proceeding to ensure its a real file
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String getParent(String filePath) throws IOException
    {
        String parent = "";
        File file = new File(filePath);

        if (file.exists())
        {
            parent = file.getParent();
        }
        else
        {
            throw new FileNotFoundException(filePath + " is not found");
        }
        return parent;
    }

    public static boolean exists(String filePath)
    {
        boolean exists = false;
        File file = null;
        if (filePath != null && !filePath.isEmpty())
        {
            file = new File(filePath);
            
            exists = file.exists();
        }
        else
        {
            LOG.error("exists: filePath is not valid: {}", filePath);
        }
        return exists;
    }
    
    public static void writeToFile(File strOutputFile, String content) throws IOException
    {
        writeToFile(strOutputFile.getPath(), content);
    }

    /**
     * Java NIO Channels to copy file
     * Caller need to ensure input parameters is Not Null
     * @param srcFile
     * @param dstFile
     * @return
     * @throws IOException
     */
    public static boolean copyFile(File srcFile, File dstFile) throws IOException
    {
        boolean success = false;
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        try 
        {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(dstFile);
            
            inputChannel = fis.getChannel();
            outputChannel = fos.getChannel();
            
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            
            success = true; //if no Exceptions thrown, set success to true
            
            LOG.debug("copyFile {} to {} done", srcFile.getPath(), dstFile.getPath());
        } 
        finally 
        {
            try
            {
                if (fis != null)
                {
                    fis.close();
                    fis = null;
                }
            }
            catch (IOException e)
            {
                LOG.error("copyFile > fis.close()", e);
            }
            
            try
            {
                if (fos != null)
                {
                    fos.close();
                    fos = null;
                }
            }
            catch (IOException e)
            {
                LOG.error("copyFile > fos.close()", e);
            }

            try
            {
                if (inputChannel != null)
                {
                    inputChannel.close();
                    inputChannel = null;
                }
            }
            catch (IOException e)
            {
                LOG.error("copyFile > inputChannel.close()", e);
            }

            try
            {
                if (outputChannel != null)
                {
                    outputChannel.close();
                    outputChannel = null;
                }
            }
            catch (IOException e)
            {
                LOG.error("copyFile > outputChannel.close()", e);
            }
        }
        return success;
    }
    
    public static void copyfile(String srcFilePath, File destFile) throws IOException
    {
        File srcFile = new File(srcFilePath);
        copyFile(srcFile, destFile);
    }

    public static void copyfile(String srcFilePath, String dstFilePath) throws IOException
    {
        File srcFile = new File(srcFilePath);
        File destFile = new File(dstFilePath);

        copyFile(srcFile, destFile);
    }

    public static void writeToFile(String strOutputFile, String content) throws IOException
    {
        FileWriter tempout = null;
        try
        {
            tempout = new FileWriter(strOutputFile, false);
            tempout.write(content);
        }
        finally
        {
            try
            {
                if (tempout != null)
                {
                    tempout.close();
                }
            }
            catch (IOException e)
            {
                LOG.error("IOException in writeToFile()", e);
            }
        }
    }

    public static String readFromFile(String filename) throws IOException
    {
        return (new String(getBinaryFromFile(filename)));
    }

    public static List<String> readFromFiles(List<String> filename) throws IOException
    {
        List<String> vTemp = new ArrayList<String>();
        for (int i = 0; i < filename.size(); i++)
        {
            LOG.info("Filename " + i + " : " + filename.get(i));
            vTemp.add(BctsFileUtil.readFromFile(filename.get(i)));
        }
        return vTemp;
    }

    public static byte[] getBinaryFromFile(String filename) throws IOException
    {
        FileInputStream file = null;
        try
        {
            file = new FileInputStream(filename);
            byte[] b = new byte[file.available()];
            file.read(b);
            return b;
        }
        finally
        {
            try
            {
                if (file != null)
                {
                    file.close();
                }
            }
            catch (IOException e)
            {
                LOG.error("getBinaryFromFile", e);
            }
        }
    }

    /**
     * Gets properties from file
     * @param filePath
     * @return
     * @throws IOException
     */
    public static Properties loadPropertiesFile(String filePath) throws IOException
    {
        Properties properties = new Properties();
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(filePath);
            properties.load(fis);
        }
        finally
        {
            if (fis != null)
            {
                fis.close();
                fis = null;
            }
        }
        return properties;
    }
    
    /**
     * Stores properties to file
     * @param filename
     * @return
     * @throws IOException
     */
    public static boolean storePropertiesFile(Properties prop, String filePath, String comments) throws IOException
    {
        boolean success = false;
        FileOutputStream fos = null;
        
        try
        {
            fos = new FileOutputStream(filePath);
            prop.store(fos, comments);
            success = true;
        }
        finally
        {
            if (fos != null)
            {
                fos.close();
                fos = null;
            }
        }
        return success;
    }

	public static byte[] serializeObject(Object obj) throws IOException {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			return baos.toByteArray();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				LOG.error("IOExceptin in serializeObject()", e);
			}

			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				LOG.error("IOExceptin in serializeObject()", e);
			}
		}
	}

	public static Object deserializeObject(byte[] byteobj) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(byteobj);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} finally {
			try {
				if (bais != null) {
					bais.close();
				}
			} catch (IOException e) {
				LOG.error("IOException in deserializeObject()", e);
			}

			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				LOG.error("IOException in deserializeObject()", e);
			}
		}
	}

	public static boolean createNewFolder(String dirPath) {
		File dir = new File(dirPath);
		boolean success = false;

		if (!dir.exists()) {
			success = dir.mkdirs();
		} else {
			success = true; // success is true if it already exists
		}
		return success;
	}

    /**
     * Get the FileContent as a byte[]
     */
	public static byte[] getFileBytes(String filePath) throws Exception {
		BufferedInputStream is = null;
		ByteArrayOutputStream byteOS = null;

		File inputFile = null;
		byte[] buffer = null;
		byte[] result = null;

		int bufferBytesRead = 0;

		try {
			inputFile = new File(filePath);

			if (inputFile.exists() && inputFile.length() > 0) {
				is = new BufferedInputStream(new FileInputStream(filePath));

				result = new byte[is.available()];

				byteOS = new ByteArrayOutputStream();

				if (is.available() > 0) {
					buffer = new byte[is.available()];
				} else {
					buffer = new byte[50000];
				}
				bufferBytesRead = is.read(buffer);
				while (bufferBytesRead > -1) {
					byteOS.write(buffer, 0, bufferBytesRead);
				}
				result = byteOS.toByteArray();
			}
		} finally {
			if (is != null) {
				is.close();
			}

			if (byteOS != null) {
				byteOS.close();
			}
		}
		return result;
	}
    
    /**
     * List a folder and get the list of files inside
     * @param folderPath - folder to list the files
     * @param filter - (if null, means all accepted) optional FileFilter to filter out specific file attributes
     * @return
     */
	public static List<File> listFiles(String folderPath, FileFilter filter) {
		List<File> files = null;
		File[] fileArray = null;
		File folder = new File(folderPath);

		if (folder.exists()) {
			fileArray = folder.listFiles(filter);

			if (fileArray != null && fileArray.length > 0) {
				files = new ArrayList<File>(fileArray.length);

				for (File element : fileArray) {
					files.add(element);
				}
			}
		}
		return files;
	}
    
    /**
     * Deletes a file
     */
	public static boolean deleteFile(File file) {
		boolean success = false;

		if (file != null && file.exists()) {
			success = file.delete();
		}
		return success;
	}

    /**
     * Delete Empty folders
     * @param dirPath
     */
	public static void deleteEmptyFolder(String dirPath) {
		final String methodName = "deleteEmptyFolder: ";
		File dir = new File(dirPath);

		if (dir.exists()) {
			String[] fileList = dir.list();

			if (fileList != null && fileList.length > 0) {
				LOG.warn(methodName + "{} is not empty. Skipping delete...", dirPath);
			} else {
				dir.delete();
				LOG.info(methodName + "{} deleted.", dirPath);
			}
		}
	}

    /**
     * Use NIO to move files
     * @param srcFile
     * @param dstFile
     * @return
     * @throws IOException
     */
	public static boolean moveFile(File srcFile, File dstFile) throws IOException {
		boolean success = false;

		if (srcFile != null && srcFile.exists() && dstFile != null) {
			File movedFile = moveFile(srcFile.getPath(), dstFile.getPath());

			if (movedFile != null && movedFile.exists()) {
				success = true;
			}
		}
		return success;
	}

    /**
     * Use NIO to move file
     * @param srcFilePath
     * @param dstFilePath
     * @return
     */
	public static File moveFile(String srcFilePath, String dstFilePath) {
		final String methodName = "moveFile: ";
		File dstFile = null;
		Path srcPath = FileSystems.getDefault().getPath(srcFilePath);
		Path dstPath = FileSystems.getDefault().getPath(dstFilePath);

		try {
			Path result = Files.move(srcPath, dstPath, StandardCopyOption.REPLACE_EXISTING);

			if (result != null) {
				dstFile = result.toFile();
			}
		} catch (IOException e) {
			LOG.error(methodName, e);
		}
		return dstFile;
	}
    
    /**
     * Remove File extension
     * @param fileName
     * @return
     */
	public static String removeFileExtension(String fileName) {
		String result = fileName;

		int dotIndex = fileName.lastIndexOf('.');

		if (dotIndex > 0) // to prevent exception if there is no dot
		{
			result = fileName.substring(0, dotIndex);
		}
		return result;
	}
    
    /**
     * List files in folder by last modified
     * @param folder
     * @param descendingOrder - specify if sort by descending order
     * @return
     */
	public static File[] listFilesByLastModified(File folder, boolean descendingOrder) {
		File[] files = folder.listFiles();

		final Map<File, Long> staticLastModifiedTimes = new HashMap<File, Long>();

		for (final File file : files) {
			staticLastModifiedTimes.put(file, file.lastModified());
		}

		if (descendingOrder) {
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(final File file1, final File file2) {
					return staticLastModifiedTimes.get(file2).compareTo(staticLastModifiedTimes.get(file1));
				}
			});
		} else // default as ascending
		{
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(final File file1, final File file2) {
					return staticLastModifiedTimes.get(file1).compareTo(staticLastModifiedTimes.get(file2));
				}
			});
		}
		return files;
	}

	public static void main(String[] args) {
		LOG.info("main started");

		int daysAgo = 2;
		BctsFileUtil.housekeepFiles("D:/WORKSPACE/FilterTest", daysAgo);
		try {
			File srcFile = new File("/WORKSPACE/trwMhx.log");
			File dstFile = new File("/WORKSPACE/dstFile.log");

			BctsFileUtil.moveFile(srcFile, dstFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
    /**
     * 
     * @param folderPath
     * @param daysAgo - default as 3 if not provided
     */
	public static String housekeepFiles(String folderPath, int daysAgo) {
		StringBuilder message = new StringBuilder("housekeepFiles\n");

		LOG.info("housekeepFiles > To delete files less than {} days ago...", daysAgo);

		message.append("To delete files less than " + daysAgo + " days ago\n");

		ModifiedDaysFilter filter = new ModifiedDaysFilter(daysAgo);

		List<File> files = listFiles(folderPath, filter);

		if (files != null && !files.isEmpty()) {
			message.append(files.size() + " filtered.\n");

			for (File file : files) {
				BctsDateFormatter date = new BctsDateFormatter(new Date(file.lastModified()));

				LOG.info("housekeepFiles > Deleting {} Last Modified: {}", file.getName(), date.getUIDateTimeString());

				file.delete();
			}
			message.append(files.size() + " deleted.");
		} else {
			LOG.info("Less than {} days ago (and before) no files, nothing to delete...", daysAgo);
			message.append("Less than " + daysAgo + " days ago (and before) no files, nothing to delete...");
		}
		return message.toString();
	}
    
    public static void decodeByteArrayToFile(byte[] encodedBytes, String dstFilePath)
    {
        final String methodName = "decodeByteArrayToFile: ";
        byte[] decodedBytes = Base64.decode(encodedBytes);
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(dstFilePath);
            fos.write(decodedBytes);

            LOG.info(methodName + "Completed for {}", dstFilePath);
        }
        catch (IOException e)
        {
            LOG.error(methodName + "Error with {}", dstFilePath, e);
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                    fos = null;
                }
                catch (IOException e)
                {
                    LOG.error(methodName + "fos.close: ", e);
                }
            }
        }
    }
    
    /**
     * Stream file to ServletOutputStream
     * @param res
     * @param filePath
     * @throws IOException
     */
    public static void streamFile(HttpServletResponse res, String filePath) throws IOException
    {
        if (filePath != null && !filePath.isEmpty())
        {
            ServletOutputStream sos = null;
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            
            File file = new File(filePath);

            if (file.exists() && !file.isDirectory())
            {
                try
                {
                    res.setContentType("application/octet-stream");
                    res.setHeader("Content-Disposition", "attachment; filename=" + file.getName() + ";");
                    res.setContentLength((int) file.length());
                    
                    sos = res.getOutputStream();
                    
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    bos = new BufferedOutputStream(sos);
                    
                    byte[] buff = new byte[2048];
                    
                    int byteRead;
                    while ((byteRead = bis.read(buff, 0, buff.length)) > 0)
                    {
                        bos.write(buff, 0, byteRead);
                    }
                    bos.flush();
                }
                finally
                {
                    if (fis != null)
                    {
                        fis.close();
                    }
                    if (bis != null)
                    {
                        bis.close();
                    }
                    if (bos != null)
                    {
                        bos.close();
                    }
                    if (sos != null)
                    {
                        sos.close();
                    }
                }
            }
        }
    }//streamFile
}

class ModifiedDaysFilter implements Serializable, FileFilter
{
    private int daysToFilter = 3; //default
    
    public ModifiedDaysFilter(int daysToFilter)
    {
        super();
        this.daysToFilter = daysToFilter;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 8344798341033669671L;

    @Override
    public boolean accept(File file)
    {
        boolean accept = false;
        
        if (file.isFile())
        {
            long lastModified = file.lastModified();
            
            long daysAgo = BctsDateUtil.getDaysDifference(lastModified);
            
            if (daysAgo >= daysToFilter)
            {
                accept = true;
            }
        }
        return accept;
    }        
}

