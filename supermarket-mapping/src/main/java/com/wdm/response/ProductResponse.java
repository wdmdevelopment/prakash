package com.wdm.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wdm.model.RequestCategory;

public class ProductResponse {
	
	
	@NotNull
	private String ProductName;
	
	@NotBlank
	private String stockDetails;
	 
	@NotBlank
	private RequestCategory category;
	
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

	public RequestCategory getCategory() {
		return category;
	}

	public void setCategory(RequestCategory category) {
		this.category = category;
	}

	

}
