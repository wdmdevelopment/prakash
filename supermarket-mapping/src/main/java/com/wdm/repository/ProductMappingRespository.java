package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Product;

public interface ProductMappingRespository extends JpaRepository<Product, Long> {

}
