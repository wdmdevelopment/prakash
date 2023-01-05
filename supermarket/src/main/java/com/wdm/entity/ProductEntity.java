package com.wdm.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 

@Entity
@Table(name = "Product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "product_Id")

	private long productId;

	@Column(name = "product_Name") 
	private String ProductName;

	
	@Column(name = "manufecturingdate")
	private Date manufecturingdate = new Date(System.currentTimeMillis());
 
	@Column(name = "description")
	private String description;
	
	@Column(name = "productType")
	private String productType;
	
	
	@ManyToOne
	 
	 @JoinColumn(name = "superId")
	
	private SuperMarketEntity superMarketEnitity;
	
	

	public SuperMarketEntity getsuperMarketEnitity() {
		return superMarketEnitity;
	}

	public void setsuperMarketEnitity(SuperMarketEntity superMarketEnitity) {
		this.superMarketEnitity = superMarketEnitity;
	}

	public ProductEntity() {

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

	public long getProductId() {
		return productId;
	} 

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public ProductEntity(String productName, Date manufecturingdate, String description,
			String productType, SuperMarketEntity superMarketEnitity) {
		super();
		
		ProductName = productName;
		this.manufecturingdate = manufecturingdate;
		this.description = description;
		this.productType = productType;
		this.superMarketEnitity = superMarketEnitity;
	}

	
	
	
	
	
	
}
