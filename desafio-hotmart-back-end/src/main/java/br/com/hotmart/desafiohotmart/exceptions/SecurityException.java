package br.com.hotmart.desafiohotmart.exceptions;

/**
 * Classe responsável por conter as exceções de segurança. 
 * 
 * @author Tiago
 *
 */
public class SecurityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1133938599375420126L;

	/**
	 * Construtor default.
	 * 
	 */
	public SecurityException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SecurityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SecurityException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SecurityException(Throwable cause) {
		super(cause);
	}
	
}
