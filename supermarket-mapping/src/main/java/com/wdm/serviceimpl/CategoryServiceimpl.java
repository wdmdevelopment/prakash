package com.wdm.serviceimpl;

 
import java.util.List;
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
			
//			UserAccount findById = userRepo.findById(requestCategory.getUserId())
//					.orElseThrow(() -> new IdNotFoundException("userId not found"));

			Category save;

//			String getuserRoll = findById.getUserRole();
//			if(getuserRoll.equalsIgnoreCase("admin")) {
//				

				Category category = new Category();

				category.setCategoryName(requestCategory.getCategoryName());

				save = categoryRepo.save(category);
				
//			}
			
//			else {
//				throw new ProductCustomException("You are a not a admin"+getuserRoll);
//			}
		 
		return save;
		
		}
	
		catch (Exception e) {
			
			throw new ProductCustomException(e.getMessage());
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
		
		try {
			UserAccount findById = userRepo.findById(category.getUserId())
					.orElseThrow(() -> new IdNotFoundException("userId not found"));

				

			String getuserRoll = findById.getUserRole();
			if(getuserRoll.equalsIgnoreCase("admin")) {
				

				Category category1 = categoryRepo.
						findById(id).orElseThrow(() -> new IdNotFoundException("categoryId not found"));

				category1.setCategoryName(category.getCategoryName());

				return 	categoryRepo.save(category1);
				
			}
			
			else {
				throw new ProductCustomException("You are a not a admin"+getuserRoll);
			}
		 
			
		}
		
		catch (Exception e) {
			throw new ProductCustomException("Invalid"+e.getMessage());
		}
		 
	}

	 
	public void deleteById(long id) {
		
		categoryRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Not Found"+id));
			
		categoryRepo.deleteById(id);
		 
			 
	}

	 
	public List<Category> findbyOrder(){
		return categoryRepo.findByOrdercategory();
	}
	 
	public List<Category> findbyCategoryName(String name) {
		List<Category> category= null;
		try {
			category = categoryRepo.findByCategoryNameContaining(name);
		}
		catch (Exception e) {
			throw new ProductCustomException(e.getMessage());
		}
		
		
		return category;
	}
	

}
