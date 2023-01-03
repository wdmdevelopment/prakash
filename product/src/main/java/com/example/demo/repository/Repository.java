package com.example.demo.repository;




import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ProductModel;


public interface Repository extends JpaRepository<ProductModel, Integer>{

		@Query(value = "select distinct manufecturingdate from Product;", nativeQuery = true)
		public List<Date> finduniqueValue();
		
		@Query(value = "select * from product where product_type=\"nuts\";", nativeQuery = true)
		public List<ProductModel> getNutsType();
		

}
