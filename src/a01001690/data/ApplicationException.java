/**
 * Project: a01001690Gis
 * File: ApplicationException.java
 * Date: Feb 22, 2017
 * Time: 3:15:19 AM
 */
package a01001690.data;

/**
 * @author chrisdean A01001690
 *
 */
public class ApplicationException extends Exception {
	public ApplicationException(String m) {
		super(m);
	}

	public ApplicationException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
