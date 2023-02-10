package com.wdm.serviceimpl;

 
import java.util.List;
import java.util.Optional;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
 
import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.ProductCustomException;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestCategory;
import com.wdm.repository.CategoryRepository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {

	@Autowired

	CategoryRepository categoryRepo;
	
	@Autowired
	UserAccountRespository userRepo;

	public Category saveCategory(RequestCategory requestCategory) {
		try {
			UserAccount findById = userRepo.findById(requestCategory.getUserId()).orElseThrow(() -> new IdNotFoundException("userId not found"));
			
		Category save;
		  
			String getuserRoll = findById.getuserRoll();
			if(getuserRoll.equalsIgnoreCase("admin")) {
				

				Category category = new Category();

				category.setCategoryName(requestCategory.getCategoryName());

				save = categoryRepo.save(category);
				
			}
			
			else {
				throw new ProductCustomException("You are a not a admin"+getuserRoll);
			}
		 
		return save;
		}
		catch (Exception e) {
			throw new ProductCustomException("Invalid"+e.getMessage());
		}
	 		
	}
 
	
	 
	

	public Category getCategory(long id) {
		
		Category category;
		
		try {
			category = categoryRepo.findById(id).get();
		}

		catch (Exception productNotFoundException) {
			
			throw new ProductNotFoundException(productNotFoundException.getMessage());
		}

		return category;
	}
	
	 
	 
	 
	public Category updatecategory(RequestCategory category, long id) {
		
		Category category2 = categoryRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Not Found"+id));
			
		
		
		category2.setCategoryName(category.getCategoryName());
		 
		 	return categoryRepo.save(category2);
		 
		 
	}

	 
	public void deleteById(long id) {
		
		categoryRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Not Found"+id));
			
		categoryRepo.deleteById(id);
		 
			 
	}

	 
	public List<Category> findbyOrder(){
		return categoryRepo.findByOrdercategory();
	}
	 
	

}
