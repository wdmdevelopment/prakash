package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.SuperMarketEntity;

public interface SuperMarketRepo extends JpaRepository<SuperMarketEntity, Long> {

}
