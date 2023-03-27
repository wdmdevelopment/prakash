package com.wdm.model;
 
import javax.validation.constraints.NotNull;

public class ResponseCart {
	
	@NotNull
	private long userId;
	
	@NotNull
	private String orderStatus;

	
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ResponseCart(@NotNull long userId, @NotNull String orderStatus) {
		super();
		this.userId = userId;
		this.orderStatus = orderStatus;
	}
	
	
	

}
