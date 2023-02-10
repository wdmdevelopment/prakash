package com.wdm.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Product;
import com.wdm.response.ProductResponse;

public interface ProductMappingRespository extends JpaRepository<Product, Long> {
	
	@Query(value = "select * from testproject.product  WHERE product_name LIKE %:name%", nativeQuery = true)
	public List<Product> findByfilterproduct(String name);
	
	 
	@Query(value = "SELECT * FROM product ORDER BY product_id DESC;", nativeQuery = true)
	public List<Product> findAllOrderByProductIdDesc();
	
	@Query(value = "select * from testproject.product  WHERE category_id LIKE %:category_id%", nativeQuery = true)
	 public  List<Product> findBycategoryId(long category_id);
	 
}
