package com.wdm.model;

 

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wdm.entity.Product;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestCategory {
	
	@NotNull
	private String categoryName;
	
	@NotNull
	private RequestProduct ProductName;
	
	@NotBlank
	private String stockDetails;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public RequestProduct getProductName() {
		return ProductName;
	}

	public void setProductName(RequestProduct productName) {
		ProductName = productName;
	}

	public String getStockDetails() {
		return stockDetails;
	}

	public void setStockDetails(String stockDetails) {
		this.stockDetails = stockDetails;
	}

	
 

	
	
	
	
	
	
	
	
	
	
	
}
