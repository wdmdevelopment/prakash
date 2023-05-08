package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 

import com.wdm.entity.Product;

public interface ProductMappingRespository extends JpaRepository<Product, Long> {

//	@Query(value = "select * from testproject.product  WHERE product_name LIKE %:name%", nativeQuery = true)
//	public List<Product> findByProductNameOrCategoryName(String name);

//	@Query(value = "SELECT * FROM product WHERE product_name = %:name% OR category_id IN (SELECT category_id FROM catagory WHERE name = '%:categoryName%')", nativeQuery = true)
//	public List<Product> findByProductNameOrCategoryName(String name, String categoryName);
	
	
//	 @Query(value = "SELECT p FROM Product p WHERE p.productName LIKE = '%:name%' OR p.category.categoryName LIKE = '%:categoryName%'",nativeQuery = true)
//	 
//	 public List<Product> findByProductNameOrCategoryName(String name, String categoryName);
	
	
	 
//	 @Query("SELECT p FROM Product p WHERE p.product_name LIKE %:name% OR p.category_id IN (SELECT c.category_id FROM Category c WHERE c.name LIKE %:categoryName%)")
//	    List<Product> findByProductNameContainingOrCategoryNameContaining(String name, String categoryName);
	 
	 
	 

	@Query(value = "SELECT * FROM product ORDER BY added_at DESC;", nativeQuery = true)
	public List<Product> findAllOrderByAddedAtDesc();

	@Query(value = "select * from testproject.product  WHERE category_id LIKE %:category_id%", nativeQuery = true)
	public List<Product> findBycategoryId(long category_id);

	public Product findByProductName(String productName);
	
	 

	public List<Product> findByproductNameContainingOrCategory_categoryNameContaining(String name, String category);
	
	
	
	
	

}
