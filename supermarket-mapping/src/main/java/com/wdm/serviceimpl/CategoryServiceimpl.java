package com.wdm.serviceimpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public Category saveCategory(RequestCategory requestCategory, long userId) {

		Optional<UserAccount> findById = userRepo.findById(userId);
		Category save = null;
		if(findById.isPresent()) {
				UserAccount userAccount = findById.get();
			
			String getuserRoll = userAccount.getuserRoll();
			if(getuserRoll=="admin") {
				

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
				.anyMatch(p -> !p.getStockDetails().isBlank())).collect(Collectors.toList());
				
	}
	
	public List<Category> SortByAllCategory(){
		
		List<Category> findAll = categoryRepo.findAll();
		findAll.stream().sorted(Comparator.comparing(Category:: getCategoryName).reversed());
	
	return findAll;
	}
	
	 
	public Category updatecategory(Category category, long id) {
		try {
			return categoryRepo.save(category);
		}
		catch (Exception e) {
			throw new ProductCustomException("Insufficient"+e);
		}
	}

	 
	public long deleteById(long id) {
		try {
			 categoryRepo.deleteById(id);
			 
	}
		catch (Exception e) {
			throw new IdNotFoundException("Insufficient id"+e);
		}
		return id;
	}

	 
	
	 
	

}
