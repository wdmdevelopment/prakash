package com.wdm.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.model.RequestCategory;

@Service
public interface CategoryService {
	
	public Category saveCategory(RequestCategory requestCategory);
	
	 
	
	public Category getCategory(long id);
	
	public Category updatecategory(RequestCategory Category, long id);

	public void deleteById(long id);
 
	public List<Category> findbyOrder();
	
	public List<Category> findbyCategoryName(String name);
		
}
