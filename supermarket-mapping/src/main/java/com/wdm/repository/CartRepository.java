package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	
	
	
	@Query(value = "select * from testproject.cart where user=:userId and order_status=:orderStatus", nativeQuery = true)
	public Cart findByOrderStatusAndUser(long userId, String orderStatus);
	
	
	@Query(value = "select * from testproject.cart where user=:userid and order_status=:status", nativeQuery = true)
	public List<Cart> findByOrderStatusUser(long userid, String status);	
}
