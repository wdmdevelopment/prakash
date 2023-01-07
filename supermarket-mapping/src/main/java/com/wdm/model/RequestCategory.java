package com.wdm.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestCategory {
	
	@NotNull
	private String categoryName;
	
	@NotNull
	private String ProductName;
	
	@NotBlank
	private String stockDetails;

	

	public String getcategoryName() {
		return categoryName;
	}

	public void setcategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	
	
	
	
	
	
	
	
	
}
