package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

	
	public List<Orders> findAllByUserUserIdOrderByDateTimeDesc(long userId);
	
	

}
