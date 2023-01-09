package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Items;
 

public interface ItemsRepository extends JpaRepository<Items, Long> {
	
	@Query(value = "SELECT * FROM products WHERE price < :maxPrice", nativeQuery = true)
	public List<Items> findProductsWithMaxPrice(double maxPrice);

	


}
