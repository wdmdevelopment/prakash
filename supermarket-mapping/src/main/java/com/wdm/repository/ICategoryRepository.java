package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Category;
import com.wdm.entity.Product;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
	
	
	 
	public List<Category> findAllByOrderByCategoryNameAsc();
	
	 
	public List<Category> findByCategoryNameContaining(String name);

}
