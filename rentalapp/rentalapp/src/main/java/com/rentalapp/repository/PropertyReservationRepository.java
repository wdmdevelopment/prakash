package com.rentalapp.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.entity.PropertyReservation;

public interface PropertyReservationRepository extends JpaRepository<PropertyReservation, Long> {

	public PropertyReservation findAllByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqualAndPropertyId(
			LocalDate checkIndDate, LocalDate checkOutdDate, Long propertyId);
	
	 
}
