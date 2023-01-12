package com.wdm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestProduct {
	
	@JsonProperty("ProductName")
	private String ProductName;
	
	@JsonProperty("stockDetails")
	private String stockDetails;
	 
	@JsonProperty("category")
	private String category;
	
	
	

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getStockDetails() {
		return stockDetails;
	}

	public void setStockDetails(String stockDetails) {
		this.stockDetails = stockDetails;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	 
	
	
	  
	
	
	
	
	
	
	
	
	
	
	
	
}
