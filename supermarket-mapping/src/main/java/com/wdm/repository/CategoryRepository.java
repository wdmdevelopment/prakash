package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
