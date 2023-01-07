package com.wdm.model;

import javax.validation.constraints.NotNull;

public class RequestAddress {
	
	
	@NotNull
	private int No;
	
	@NotNull
	private String street;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private String country;

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	
	

}
