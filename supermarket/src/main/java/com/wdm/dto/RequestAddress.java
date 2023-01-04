package com.wdm.dto;



public class RequestAddress {
	
	
	private int No;
	
	
	private String street;
	
	
	private String city;
	
	
	private String state;
	
	
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

	public RequestAddress(int no, String street, String city, String state, String country) {
		super();
		No = no;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	

}
