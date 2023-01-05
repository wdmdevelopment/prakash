package com.wdm.dto;
  
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.wdm.entity.Address;
 
public class ResquestSuperMarket {

	@NotEmpty
	 private String superMarketName;
	 
	@NotBlank
	 private Address address;
	
	@NotBlank
	 private ResquestProduct product;
	 
	@NotBlank
	 private RequestWholesale wholesale;
	
	 
	 public String getSuperMarketName() {
		return superMarketName;
	}
	public void setSuperMarketName(String superMarketName) {
		this.superMarketName = superMarketName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ResquestProduct getProduct() {
		return product;
	}
	public void setProduct(ResquestProduct product) {
		this.product = product;
	}
	public RequestWholesale getWholesale() {
		return wholesale;
	}
	public void setWholesale(RequestWholesale wholesale) {
		this.wholesale = wholesale;
	}
	
	
	 
	 
	 
	 
}
