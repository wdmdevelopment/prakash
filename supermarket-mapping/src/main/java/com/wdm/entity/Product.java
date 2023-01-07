package com.wdm.entity; 
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "product_Id")
	private long productId;

	@Column(name = "product_Name") 
	private String ProductName;
	
	@Column(name = "stock") 
	private String stockDetails;
	  
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonIgnoreProperties
	private Category category;
	
	
	
	
	
 	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getstock() {
		return stockDetails;
	}

	public void setstock(String stockDetails) {
		this.stockDetails = stockDetails;
	}

	 

	public Product(long productId, String productName, String stockDetails, Category category) {
		super();
		this.productId = productId;
		ProductName = productName;
		this.stockDetails = stockDetails;
		this.category = category;
		
	}
	public Product() {
		
	}
	
	
}
