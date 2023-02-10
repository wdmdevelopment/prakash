package com.wdm.model;

 

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
 
@NoArgsConstructor
public class RequestOrder {
	
	@NotNull
	private long cartId;
	@NotNull  
	private int quantity;

	@NotNull
	private double totalAmount;
	
	@NotNull
	private LocalDateTime Ordertime;
	
	
	@NotNull
	private long userId;
	
	

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public LocalDateTime getOrdertime() {
		return Ordertime;
	}

	public void setOrdertime(LocalDateTime ordertime) {
		Ordertime = ordertime;
	}
	
	 
	
	
	 
	
	
	
	
	
}
