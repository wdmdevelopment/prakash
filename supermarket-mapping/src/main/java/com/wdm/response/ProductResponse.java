package com.wdm.response;

import java.util.Set;

import com.wdm.entity.ImageProduct;

public class ProductResponse {
	
	private long productId;
	
	private String ProductName;
	
	
	private long stock;
	 
	private String unit;
	
	private String categoryname;
	
	private double price;
	
	
	
	private byte[] data;
	
	private long imageId;
	
	
	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public ProductResponse(long productId, String productName, long stock, String unit, String categoryname,
			double price, byte[] data, long imageId) {
		super();
		this.productId = productId;
		ProductName = productName;
		this.stock = stock;
		this.unit = unit;
		this.categoryname = categoryname;
		this.price = price;
		this.data = data;
		this.imageId = imageId;
	}

	 



}
	  
	
	
	
	 