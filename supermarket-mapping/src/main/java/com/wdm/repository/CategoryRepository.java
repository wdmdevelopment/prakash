package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Category;
import com.wdm.entity.Product;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	@Query(value ="SELECT * FROM catagory ORDER BY name ASC" , nativeQuery = true )
	public List<Category> findByOrdercategory();
	
	
		
	 

	
	public List<Category> findByCategoryNameContaining(String name);

}
