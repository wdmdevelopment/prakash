package com.wdm.exception;

public class IdNotFoundException extends  RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 private String errorCode;


	public IdNotFoundException(String string) {
		
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	 
}
