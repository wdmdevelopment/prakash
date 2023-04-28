package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wdm.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	
	
	
	@Query(value = "select * from testproject.cart where user=:userId and order_status=:orderStatus", nativeQuery = true)
	public Cart findByOrderStatusAndUser(long userId, String orderStatus);
	
	
	@Query(value = "select * from testproject.cart where user=:userid and order_status=:status", nativeQuery = true)
	public List<Cart> findByOrderStatusUser(long userid, String status);
	
	
//	@Modifying
//	@Query("UPDATE Cart c SET c.items = c.items.stream().filter(i -> i.id != :itemId).collect(Collectors.toList())"
//			+ "	 WHERE c.id = :cartId")
//	
//	public void removeItemFromCart(@Param("cartId") Long cartId, @Param("itemId") Long itemId);
	
	
}
