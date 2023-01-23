package com.wdm.entity; 
 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
 
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

 

 
 

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "product_Id")
	private long productId;

	@Column(name = "product_Name") 
	private String productName;
	
	@Column(name = "stock") 
	private String stockDetails;
	  
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private Category category;
	
	
	
	 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	
	private Set<ImageProduct> productImage;



	public long getProductId() {
		return productId;
	}



	public void setProductId(long productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getStockDetails() {
		return stockDetails;
	}



	public void setStockDetails(String stockDetails) {
		this.stockDetails = stockDetails;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Set<ImageProduct> getProductImage() {
		return productImage;
	}



	public void setProductImage(Set<ImageProduct> productImage) {
		this.productImage = productImage;
	}



	public Product(long productId, String productName, String stockDetails, Category category,
			Set<ImageProduct> productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.stockDetails = stockDetails;
		this.category = category;
		this.productImage = productImage;
	}
	 
	
	 
	
	public Product() {
		
	}
	 
	
	
}
