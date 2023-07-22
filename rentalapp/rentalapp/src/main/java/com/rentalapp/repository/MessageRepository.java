package com.rentalapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalapp.entity.PropertyMessages;

@Repository
public interface MessageRepository extends JpaRepository<PropertyMessages, Long> {

		public List<PropertyMessages> findByFromIdAndToIdAndPropertyId(Long fromUserId, Long ToUserId, Long propertyId);
		
		public void deleteByFrom(Long tenantId);
}
