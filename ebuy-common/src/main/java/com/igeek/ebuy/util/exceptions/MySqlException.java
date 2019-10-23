package com.igeek.ebuy.util.exceptions;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月30日 下午2:20:24
 */
public class MySqlException extends RuntimeException {

	/**
	 * 
	 */
	public MySqlException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MySqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MySqlException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MySqlException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MySqlException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
