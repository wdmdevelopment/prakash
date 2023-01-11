package com.wdm.serviceimpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.entity.Product;
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

	public Category saveCategory(RequestCategory requestCategory, long userId) {

		Optional<UserAccount> findById = userRepo.findById(userId);
		Category save = null;
		if(findById.isPresent()) {
				UserAccount userAccount = findById.get();
			
			String getuserRoll = userAccount.getuserRoll();
			if(getuserRoll.equalsIgnoreCase("admin")) {
				

				Category category = new Category();

				category.setCategoryName(requestCategory.getCategoryName());

				save = categoryRepo.save(category);
			}
			
			else {
				throw new ProductCustomException("Y"+getuserRoll);
			}
		}
		return save;
	 		
	}
 
	
	 
	

	public Category getCategory(long id) {
		
		Category category;
		
		try {
			category = categoryRepo.findById(id).get();
		}

		catch (Exception productNotFoundException) {
			
			throw new ProductNotFoundException(productNotFoundException);
		}

		return category;
	}
	
	public List<Category> getAllcategory() {
		return categoryRepo.findAll().stream().filter(cat -> cat.getProduct().isEmpty()).filter(cat ->cat.getProduct().stream()
				.anyMatch(p -> !p.getProductName().isBlank())).collect(Collectors.toList());
				
	}
	
	public List<Category> SortByAllCategory(){
		
		List<Category> findAll = categoryRepo.findAll();
		findAll.stream().sorted(Comparator.comparing(Category:: getCategoryName));
	
	return findAll;
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
