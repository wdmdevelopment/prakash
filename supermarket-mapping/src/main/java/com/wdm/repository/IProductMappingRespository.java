package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
 

import com.wdm.entity.Product;

public interface IProductMappingRespository extends JpaRepository<Product, Long> {

 
	 
	public List<Product> findAllByOrderByAddedAtDesc();
 
	public Product findByProductName(String productName);
	
	  
	public List<Product> findByproductNameContainingOrCategoryCategoryNameContaining(String name, String category);
	
	
	
	
	

}
