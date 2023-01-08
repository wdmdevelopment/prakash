package com.wdm.exception;

public class ItemNotFoundException extends RuntimeException {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;


	public ItemNotFoundException(String string) {
		
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


}
