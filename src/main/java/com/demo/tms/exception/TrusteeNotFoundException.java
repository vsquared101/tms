package com.demo.tms.exception;

public class TrusteeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrusteeNotFoundException(String exception) {
		super(exception);
	}

}
