package com.wdm.exception;

public class ExceptionResponse {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionResponse(String message) {
		
		this.message = message;
	}
	
	public ExceptionResponse() {
		
	}

}
