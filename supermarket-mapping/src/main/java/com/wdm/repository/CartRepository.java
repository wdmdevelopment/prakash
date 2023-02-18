package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	
	public boolean existsByUser(long id);
	
	@Query(value = "select * from testproject.cart where user=:userId and order_status=:orderStatus", nativeQuery = true)
	public Cart findByOrderStatusAndUser(long userId, String orderStatus);
	
	
}
