package com.wdm.model;
  
 
import javax.validation.constraints.NotNull;
 
public class RequestItems {
	
	
	@NotNull
	private int quantity;
	
 
	@NotNull
	private double price;
	
	@NotNull
	private String productName;
	 

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	
	
	
	


}
