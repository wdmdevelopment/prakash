package com.rentalapp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "propertySlot")
public class PropertySlots {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	 
	@Column(name = "availableDates")
	private LocalDate availableDates;
	
	@ManyToOne
	@JoinColumn(name = "propertyId")
	
	private Property property;
	
	private String propertyStatus;
	 
	
	
	
	 
}
