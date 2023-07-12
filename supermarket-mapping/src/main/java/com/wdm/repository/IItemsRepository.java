package com.wdm.repository;

 
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
 
 

import com.wdm.entity.Items;
 

public interface IItemsRepository extends JpaRepository<Items, Long> {
	
	  
	
	 public Set<Items> findByCartCartId(Long cartId);
	 
	 
	 public Items findByProductProductIdAndCartCartId(long productId, Long cartId);
	 
	 

}
