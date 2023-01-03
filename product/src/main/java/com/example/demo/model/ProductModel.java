package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "product_Id")

	private int productId;

	@Column(name = "product_Name") 
	private String ProductName;

	
	@Column(name = "manufecturingdate")
	private Date manufecturingdate = new Date(System.currentTimeMillis());
 
	@Column(name = "description")
	private String description;
	
	@Column(name = "productType")
	private String productType;
	
	
	@ManyToOne
	 
	 @JoinColumn(name = "superId", nullable = false)
	private SuperModelEntity superModelEnitity;
	
	

	public ProductModel() {

	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public Date getManufecturingdate() {
		return manufecturingdate;
	}

	public void setManufecturingdate(Date manufecturingdate) {
		this.manufecturingdate = manufecturingdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getProductId() {
		return productId;
	} 

	public void setProductId(int productId) {
		this.productId = productId;
	}

	
	
	

	

}
