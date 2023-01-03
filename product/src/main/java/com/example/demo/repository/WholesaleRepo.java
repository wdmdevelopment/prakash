package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.WholesaleEntity;

public interface WholesaleRepo extends JpaRepository<WholesaleEntity, Integer> {

}
