package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wdm.entity.Supermarket;
@Repository
public interface SuperMarkerRespository extends JpaRepository<Supermarket, Long>{

}
