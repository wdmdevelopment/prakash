package com.wdm.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestProduct {
	
	@NotNull
	@JsonProperty("productName")
	private String productName;
	
	@NotNull
	@JsonProperty("stock")
	private long stock;
	 
	 
	@NotNull
	@JsonProperty("unit")
	private String unit;
	
	@NotNull
	@JsonProperty("categoryId")
	private long categoryId;
	
	@NotNull
	@JsonProperty("userId")
	private long userId;

	@NotNull
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
