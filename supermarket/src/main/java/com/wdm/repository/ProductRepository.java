package com.wdm.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	@Query(value = "select distinct manufecturingdate from Product;", nativeQuery = true)
	public List<Date> finduniqueValue();
	
	@Query(value = "select * from product where value=\"/car/{id}\";", nativeQuery = true)
	public List<ProductEntity> getNutsType();
}
