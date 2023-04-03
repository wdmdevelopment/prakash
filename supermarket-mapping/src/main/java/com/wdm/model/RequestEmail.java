package com.wdm.model;

import javax.validation.constraints.Email;

public class RequestEmail {
	
	@Email
	private String Email;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	
}
