package com.wdm.response;

import java.util.Set;

import com.wdm.entity.ImageProduct;

public class ProductResponse {
	
	private long productId;
	
	private String ProductName;
	
	
	private String quantity;
	 
	
	private String categoryname;
	
	private double price;
	
	
	
	private Set<ImageProduct> data;
	
	
	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	 
	  
	
	
	
	
	
	

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	
	
	
	
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductResponse(long productId, String productName, String quantity, String categoryname, double price,
			Set<ImageProduct> data) {
		super();
		this.productId = productId;
		ProductName = productName;
		this.quantity = quantity;
		this.categoryname = categoryname;
		this.price = price;
		this.data = data;
	}

	 

	 
	
	
	
	
	
	
	
	

}
