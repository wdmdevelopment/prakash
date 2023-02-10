package com.wdm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestProduct {
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("stock")
	private long stock;
	 
	 
	@JsonProperty("unit")
	private String unit;
	
	
	@JsonProperty("categoryId")
	private long categoryId;
	
	
	@JsonProperty("userId")
	private long userId;


	@JsonProperty("price")
	private double price;
	
	
	 
	
	
	
	
	
	
	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	 


	public long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	 


	public long getStock() {
		return stock;
	}


	public void setStock(long stock) {
		this.stock = stock;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	 
	 

	 

	 
	
	
	  
	
	
	
	
	
	
	
	
	
	
	
	
}
