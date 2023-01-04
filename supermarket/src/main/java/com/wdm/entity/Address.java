package com.wdm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name ="address_id")
	private long addressId;
	
	@Column(name ="NUMBER")
	private int No;
	
	@Column(name ="street")
	private String street;
	
	@Column(name ="city")
	private String city;
	
	@Column(name ="state")
	private String state;
	
	@Column(name ="country")
	private String country;

	public Address(long addressId, int no, String street, String city, String state, String country) {
		super();
		this.addressId = addressId;
		No = no;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

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

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", No=" + No + ", street=" + street + ", city=" + city + ", state="
				+ state + ", country=" + country + "]";
	}
	
}
