package com.igeek.ebuy.util.exceptions;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月30日 下午2:21:15
 */
public class MySimpleException extends RuntimeException {

	/**
	 * 
	 */
	public MySimpleException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MySimpleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MySimpleException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MySimpleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MySimpleException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
