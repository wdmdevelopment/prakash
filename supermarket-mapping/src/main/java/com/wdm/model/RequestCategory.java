package com.wdm.model;

import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestCategory {
	
	@NotNull
	private String categoryName;
	
	 

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	 

	
 

	
	
	
	
	
	
	
	
	
	
	
}
