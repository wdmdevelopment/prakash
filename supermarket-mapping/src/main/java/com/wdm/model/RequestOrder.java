package com.wdm.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

 

import lombok.NoArgsConstructor;
 
@NoArgsConstructor
public class RequestOrder {
	
	@NotEmpty
	private double totalPrice;
	
	@NotBlank
	private LocalDateTime ordertime;
	
	

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(LocalDateTime ordertime) {
		this.ordertime = ordertime;
	}



	
	
	
	
	
}
