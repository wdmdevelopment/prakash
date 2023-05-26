package com.wdm.model;

 

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
 
@NoArgsConstructor
public class RequestOrder {
	
	@NotNull
	private long cartId;
	 
  
	
	@NotNull
	private long userId;
	
	
	private long productId;
	
	private int quantity;
	
	@NotNull
	private long orderPayId;
	
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private LocalDateTime dateTime=LocalDateTime.now();
	

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	 
 

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOrderPayId() {
		return orderPayId;
	}

	public void setOrderPayId(long orderPayId) {
		this.orderPayId = orderPayId;
	}

	
	
	 

	 
	
	
	 
	
	
	
	
	
}
