package com.wdm.dto;

import java.util.List;

import com.wdm.entity.SuperMarketEntity;



public class RequestWholesale {
	
	
	private String name;
	private List<SuperMarketEntity> supermarket;
	
	
	public List<SuperMarketEntity> getSupermarket() {
		return supermarket;
	}
	public void setSupermarket(List<SuperMarketEntity> supermarket) {
		this.supermarket = supermarket;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RequestWholesale(String name, List<SuperMarketEntity> supermarket) {
		super();
		this.name = name;
		this.supermarket = supermarket;
	}
	
	
	

}
