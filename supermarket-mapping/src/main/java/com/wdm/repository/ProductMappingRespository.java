package com.wdm.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Product;

public interface ProductMappingRespository extends JpaRepository<Product, Long> {
	
	@Query(value = "select p from product p where c.product_name LIKE '%product_name%';", nativeQuery = true)
	public List<Product> findByfilterproduct(String value);

	
}
