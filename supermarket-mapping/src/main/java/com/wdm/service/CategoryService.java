package com.wdm.service;

import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.model.RequestCategory;

@Service
public interface CategoryService {
	
	public Category saveCategory(RequestCategory requestCategory);
	
	public void deleteCategory(long id);
	
	public Category getCategory();
	
	public Category updatecategory(RequestCategory requestCategory, long id);
	
		
}
