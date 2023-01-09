package com.wdm.response;
 
import com.wdm.model.RequestCategory;

public class ProductResponse {
	
	
	
	private String ProductName;
	
	
	private String stockDetails;
	 
	
	private RequestCategory category;
	
	
	
	private String type;

	private String name;
	
	private String data;
	
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	

}
