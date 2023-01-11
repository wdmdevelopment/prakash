package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	@Query(value ="select * from Category ORDER BY name ASC;" , nativeQuery = true )
	public List<Category> findByOrdercategory();

}
