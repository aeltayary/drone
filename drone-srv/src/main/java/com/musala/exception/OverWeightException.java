/**
 * 
 */
package com.musala.exception;

/**
 * @author aeltayary
 *
 */
public class OverWeightException extends Exception {

	/**
	 * 
	 */
	public OverWeightException() {
	}

	/**
	 * @param message
	 */
	public OverWeightException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OverWeightException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OverWeightException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public OverWeightException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
