package com.wdm.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Product;

public interface ProductMappingRespository extends JpaRepository<Product, Long> {
	
	@Query(value = "select * from testproject.product  WHERE product_name LIKE %:name%", nativeQuery = true)
	public List<Product> findByfilterproduct(String name);
	 
	
}
