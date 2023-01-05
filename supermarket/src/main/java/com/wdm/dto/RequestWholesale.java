package com.wdm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestWholesale {
	
	@NotNull
	private String name;
	
	@NotBlank
	private ResquestSuperMarket supermarket;
	
	
	public ResquestSuperMarket getSupermarket() {
		return supermarket;
	}
	public void setSupermarket(ResquestSuperMarket supermarket) {
		this.supermarket = supermarket;
	}
	public String getName() { 
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RequestWholesale(String name, ResquestSuperMarket supermarket) {
		super();
		this.name = name;
		this.supermarket = supermarket;
	}
	
	
	

}
