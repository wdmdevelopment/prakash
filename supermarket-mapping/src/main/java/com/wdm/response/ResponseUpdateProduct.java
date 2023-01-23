package com.wdm.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUpdateProduct {
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("stockDetails")
	private String stockDetails;
	 
	 
	
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


	public String getStockDetails() {
		return stockDetails;
	}


	public void setStockDetails(String stockDetails) {
		this.stockDetails = stockDetails;
	}


	public long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public long getImageId() {
		return imageId;
	}


	public void setImageId(long imageId) {
		this.imageId = imageId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
	
	

}
