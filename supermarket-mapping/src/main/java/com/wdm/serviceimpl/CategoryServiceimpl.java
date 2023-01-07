package com.wdm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.model.RequestCategory;
import com.wdm.repository.CategoryRepository;
import com.wdm.service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {
	
	@Autowired
	
	CategoryRepository categoryRepo;

	
	public Category saveCategory(RequestCategory requestCategory) {
		
		Category category = new Category();
		
		category.setCategoryName(requestCategory.getcategoryName());
		
		
		
		return categoryRepo.save(category);
	}

	
	public void deleteCategory(long id) {
			
		
		categoryRepo.deleteById(id);
		
	}
	
}
