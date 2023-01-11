package com.wdm.model;
 

 
import java.sql.Blob;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.wdm.entity.Category;






public class RequestProduct {
	
	@NotNull
	private String ProductName;
	
	@NotBlank
	private String stockDetails;
	 
	@NotNull
	private String category;
	
	@NotNull
	private Blob productImage;

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

	public Blob getProductImage() {
		return productImage;
	}

	public void setProductImage(Blob productImage) {
		this.productImage = productImage;
	}
	
	
	  
	
	
	
	
	
	
	
	
	
	
	
	
}
