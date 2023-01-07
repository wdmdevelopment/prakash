package com.wdm.model;

import java.util.List;

public class RequestCart {
	
	
	private List<RequestItems> item;

	private double totalPrice;
	
	
	

	public List<RequestItems> getItem() {
		return item;
	}

	public void setItem(List<RequestItems> item) {
		this.item = item;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	

}
