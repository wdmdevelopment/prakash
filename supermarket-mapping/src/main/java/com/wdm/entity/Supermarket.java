package com.wdm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "supermarket")
public class Supermarket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long superId;

	private String superMarketName;

	@OneToOne
	@JoinColumn(name = "address", referencedColumnName = "address_id")
	private Address address;

	
	@OneToMany(mappedBy = "supermarketmapping")

	private List<Category> category;
	
	
	

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public long getSuperId() {
		return superId;
	}

	public void setSuperId(long superId) {
		this.superId = superId;
	}

	public String getSuperMarketName() {
		return superMarketName;
	}

	public void setSuperMarketName(String superMarketName) {
		this.superMarketName = superMarketName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Supermarket(long superId, String superMarketName, Address address, List<Category> category) {
		super();
		this.superId = superId;
		this.superMarketName = superMarketName;
		this.address = address;
		this.category = category;
	}
	
	public Supermarket() {
		
	}
	
	

}
