package com.wdm.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
 
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ErrorResponse {
	
	private String statuserrorCode;
	private String message;
	
	 private  long timeStamp;

	 

	public String getStatuserrorCode() {
		return statuserrorCode;
	}

	public void setStatuserrorCode(String statuserrorCode) {
		this.statuserrorCode = statuserrorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ErrorResponse(String statuserrorCode, String message) {
		super();
		this.statuserrorCode = statuserrorCode;
		this.message = message;
	}

	public ErrorResponse() {
		 
	}

	 
	
	

}
