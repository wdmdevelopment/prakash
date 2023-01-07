package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
