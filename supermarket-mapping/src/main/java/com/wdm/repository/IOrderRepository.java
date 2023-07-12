package com.wdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Long> {
	
	public List<Orders> findAllByUserUserIdOrderByDateTimeDesc(Long userId);

}
