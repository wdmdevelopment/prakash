package com.wdm.dto;

import java.sql.Date;

import com.wdm.entity.SuperMarketEntity;





public class ResquestProduct {

	private String ProductName;
	
	private Date manufecturingdate;
 
	private String description;
	
	private String productType;
	
	private SuperMarketEntity superModelEnitity;

	
	
	
	
	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public Date getManufecturingdate() {
		return manufecturingdate;
	}

	public void setManufecturingdate(Date manufecturingdate) {
		this.manufecturingdate = manufecturingdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public SuperMarketEntity getSuperModelEnitity() {
		return superModelEnitity;
	}

	public void setSuperModelEnitity(SuperMarketEntity superModelEnitity) {
		this.superModelEnitity = superModelEnitity;
	}

	public ResquestProduct(String productName, Date manufecturingdate, String description, String productType,
			SuperMarketEntity superModelEnitity) {
		super();
		ProductName = productName;
		this.manufecturingdate = manufecturingdate;
		this.description = description;
		this.productType = productType;
		this.superModelEnitity = superModelEnitity;
	}
	
	

	
	
}
