package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {

}
