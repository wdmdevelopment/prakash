package com.wdm.dto;

import java.util.List;

import com.wdm.entity.Address;
import com.wdm.entity.ProductEntity;
import com.wdm.entity.WholesaleEntity;




public class ResquestSuperMarket {

	
	 private String superMarketName;
	 private Address address;
	 private List<ProductEntity> product;
	 private List<WholesaleEntity> wholesale;
	
	 public ResquestSuperMarket(String superMarketName, Address address, List<ProductEntity> product,
			List<WholesaleEntity> wholesale) {
		super();
		this.superMarketName = superMarketName;
		this.address = address;
		this.product = product;
		this.wholesale = wholesale;
	}

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

	public List<ProductEntity> getProduct() {
		return product;
	}

	public void setProduct(List<ProductEntity> product) {
		this.product = product;
	}

	public List<WholesaleEntity> getWholesale() {
		return wholesale;
	}

	public void setWholesale(List<WholesaleEntity> wholesale) {
		this.wholesale = wholesale;
	}
	 
	 
	 
	 
}
