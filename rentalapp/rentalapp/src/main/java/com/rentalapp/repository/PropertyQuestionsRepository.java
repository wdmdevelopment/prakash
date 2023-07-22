package com.rentalapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.entity.PropertyQuestions;

public interface PropertyQuestionsRepository extends JpaRepository<PropertyQuestions, Long> {
	
	public List<PropertyQuestions> findByPropertyId(Long propertyId);

}
