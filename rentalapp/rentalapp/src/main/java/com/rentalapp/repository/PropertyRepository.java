package com.rentalapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	public List<Property> findAllByStatus(String status);
	
	public Property findByIdAndUserId(Long propertyId, Long userId);
	
	
}
