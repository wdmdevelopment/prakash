package com.wdm.model;
 

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wdm.entity.Category;





public class RequestProduct {
	
	@NotNull
	private String ProductName;
	
	@NotBlank
	private String stockDetails;
	 
	
	private RequestCategory category;
	
	 

	public RequestCategory getCategory() {
		return category;
	}

	public void setCategory(RequestCategory category) {
		this.category = category;
	}

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

	
	
	
	
	
	
	
	
	
	
	
	
	
}
