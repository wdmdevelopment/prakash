package com.wdm.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.model.RequestCategory;

@Service
public interface CategoryService {
	
	public Category saveCategory(RequestCategory requestCategory);
	
	public void deleteCategory(long id);
	
	public Category getCategory(long id);
	
	public Category updatecategory(Category Category, long id);

	public void deleteById(long id);

	public List<Category> getAllcategory();
	
		
}
