package com.wdm.response;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUpdateProduct {
	
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
	@JsonProperty("price")
	private double price;
	
	@NotNull
	@JsonProperty("categoryId")
	private long categoryId;
	
	
	@JsonProperty("imageId")
	private long imageId;
	
	
	@JsonProperty("userId")
	private long userId;


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	 

	 

	 


	public long getStock() {
		return stock;
	}


	public void setStock(long stock) {
		this.stock = stock;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
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


	public long getImageId() {
		return imageId;
	}


	public void setImageId(long imageId) {
		this.imageId = imageId;
	}


	 
	
	
	
	
	
	
	
	
	
	
	

}
