package com.wdm.entity; 
 
 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
 
import javax.persistence.ManyToOne;
 
import javax.persistence.OneToOne;
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
	
	@Column(name = "stocks") 
	private long stocks;
	
	@Column(name = "unit") 
	private String unit;
	
	
	@Column(name = "price") 
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private Category category;
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ProductImage", referencedColumnName = "imageId")
	private ImageProduct productImage;



	 
 

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

 



	public double getPrice() {
		return price;
	}






	public void setPrice(double price) {
		this.price = price;
	}






	public Category getCategory() {
		return category;
	}






	public void setCategory(Category category) {
		this.category = category;
	}






	public long getStocks() {
		return stocks;
	}






	public void setStocks(long stocks) {
		this.stocks = stocks;
	}






	public String getUnit() {
		return unit;
	}






	public void setUnit(String unit) {
		this.unit = unit;
	}






	public ImageProduct getProductImage() {
		return productImage;
	}






	public void setProductImage(ImageProduct productImage) {
		this.productImage = productImage;
	}






	public Product(long productId, String productName, long stocks, double price, Category category,
			ImageProduct productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.stocks = stocks;
		this.price = price;
		this.category = category;
		this.productImage = productImage;
	}






	public Product() {
		
	}
	 
	
	
}
