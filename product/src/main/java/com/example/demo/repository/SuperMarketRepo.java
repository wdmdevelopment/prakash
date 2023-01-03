package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SuperModelEntity;

public interface SuperMarketRepo  extends JpaRepository<SuperModelEntity, Integer>{

}
