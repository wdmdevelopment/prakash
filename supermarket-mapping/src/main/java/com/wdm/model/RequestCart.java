package com.wdm.model;

import javax.validation.constraints.NotNull;

public class RequestCart {

	@NotNull
	private long productId;
	
	
	@NotNull
	private int quantity;
	

	 @NotNull
	 private String orderStatus;
	 
	 @NotNull
		private long userId;
	 
	 

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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	

}
