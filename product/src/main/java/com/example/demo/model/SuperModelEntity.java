package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="supermarket")
public class SuperModelEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int superId;
		
	private String superMarketName;
	
	@OneToOne
	@JoinColumn(name = "address",referencedColumnName = "ADDRESS_ID")
	private Address address;
	
	
	 @OneToMany
	 private List<ProductModel> product;
	 
	
	
	
	@ManyToMany
	
	private List<WholesaleEntity> wholesale;


	public SuperModelEntity(int superId, String superMarketName, Address address, List<WholesaleEntity> wholesale) {
		super();
		this.superId = superId;
		this.superMarketName = superMarketName;
		this.address = address;
		this.wholesale = wholesale;
	}


	public int getSuperId() {
		return superId;
	}


	public void setSuperId(int superId) {
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


	public List<WholesaleEntity> getWholesale() {
		return wholesale;
	}


	public void setWholesale(List<WholesaleEntity> wholesale) {
		this.wholesale = wholesale;
	}


	@Override
	public String toString() {
		return "SuperModelEntity [superId=" + superId + ", superMarketName=" + superMarketName + ", address=" + address
				+ ", wholesale=" + wholesale + "]";
	}
	
	
}
