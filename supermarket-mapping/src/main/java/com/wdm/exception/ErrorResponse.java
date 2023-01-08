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
	
	private int statuserrorCode;
	private String message;
	
	 private  long timeStamp;

	public int getStatuserrorCode() {
		return statuserrorCode;
	}

	public void setStatuserrorCode(int statuserrorCode) {
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

	 
	
	

}
