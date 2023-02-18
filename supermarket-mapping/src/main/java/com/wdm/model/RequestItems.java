package com.wdm.model;
  
 
import javax.validation.constraints.NotNull;
 
public class RequestItems {
	
	
	@NotNull
	private int quantity;
	
	@NotNull
	private double totalprice;
	 
	
	@NotNull
	private long productId;
	
	@NotNull
	private long userId;
	
	
	 
	 

	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	 

	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public double getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}


	 

	
	
	
	


}
