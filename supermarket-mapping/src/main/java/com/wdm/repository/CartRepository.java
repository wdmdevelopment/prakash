package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
