package com.wdm.model;

import javax.validation.constraints.NotNull;

public class RequestSocialLogin {
	
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String userRole;
	
	@NotNull
	private String userName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	} 
	
	  
	
	
}
