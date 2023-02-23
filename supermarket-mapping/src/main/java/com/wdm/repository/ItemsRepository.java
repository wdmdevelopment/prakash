package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Items;
 

public interface ItemsRepository extends JpaRepository<Items, Long> {
	
	  
	
	 public List<Items> findByCart_CartId(long cartId);
	 
	 

}
