/**
 * @author ajayasamanta
 * @Class: SFTPClientException.java
 */
package com.etrade.bcts.util;

import com.jcraft.jsch.JSchException;

/**
 * @author ajayasamanta
 */
public class SFTPClientException extends Exception {
	private static final long serialVersionUID = -7928538878461844826L;
	public SFTPClientException() {
		super();
	}
 
	/**
	 * @param message
	 * @param je
	 */
	public SFTPClientException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param je
	 */
	public SFTPClientException(String message, JSchException je) {
		super(message, je);
	}

	/**
	 * @param message
	 * @param e
	 */
	public SFTPClientException(String message, Exception e) {
		super(message, e);
	}
}
