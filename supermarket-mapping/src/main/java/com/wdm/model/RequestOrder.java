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

	

	 

	 
	
	
	 
	
	
	
	
	
}
