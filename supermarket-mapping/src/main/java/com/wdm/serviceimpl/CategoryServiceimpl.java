package com.wdm.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.exception.ProductCustomException;
import com.wdm.exception.ProductNotFoundException;
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

	public Category getCategory(long id) {
		
		Category category;
		
		try {
			category = categoryRepo.findById(id).get();
		}

		catch (Exception e) {
			throw new ProductCustomException("Product Not found" + id);
		}

		return category;
	}
	

	 
	public Category updatecategory(Category category, long id) {

		return categoryRepo.save(category);
	}

	 
	public void deleteById(long id) {
		
		categoryRepo.deleteById(id);
	}

	 
	
	 
	

}
