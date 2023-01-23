package com.wdm.response;

import java.util.Set;

import com.wdm.entity.ImageProduct;

public class ProductResponse {
	
	private long productId;
	
	private String ProductName;
	
	
	private String stockDetails;
	 
	
	private String categoryname;
	
	
	
	
	
	private Set<ImageProduct> data;
	
	
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

	  
	
	
	
	
	
	

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	 

	public Set<ImageProduct> getData() {
		return data;
	}

	public void setData(Set<ImageProduct> data) {
		this.data = data;
	}

	public ProductResponse(long productId, String productName, String stockDetails, String categoryname,
			Set<ImageProduct> data) {
		super();
		this.productId = productId;
		ProductName = productName;
		this.stockDetails = stockDetails;
		this.categoryname = categoryname;
		this.data = data;
	}

	 
	
	
	
	
	
	
	
	

}
