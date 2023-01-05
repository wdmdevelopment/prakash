package com.wdm.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.NoArgsConstructor;
 
 
@NoArgsConstructor
public class ResquestProduct {
	
	@Size(min=2, max=30)
	private String ProductName;
	
	@NotEmpty
	private Date manufecturingdate;
	
	@Size(min=10)
	private String description;
	
	@NotNull
	private String productType;
	
	@NotBlank
	private ResquestSuperMarket resquestSuperMarket;

	
	

	
	
	
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

	public ResquestSuperMarket getResquestSuperMarket() {
		return resquestSuperMarket;
	}

	public void setResquestSuperMarket(ResquestSuperMarket resquestSuperMarket) {
		this.resquestSuperMarket = resquestSuperMarket;
	}

	

	
	
	
	

	
	
}
