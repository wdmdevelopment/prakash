package com.rentalapp.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name = "property_reservation")
public class PropertyReservation {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
		
		private LocalDate checkInDate;
		
		private LocalDate checkOutDate;
		
		
		@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE ,CascadeType.REMOVE})
		@JoinColumn(name = "userId", referencedColumnName = "id")
		private User user;
		
		private String status;
		
		private double paidAmount;
		
		private LocalDate reservedOn; 
		
		@ManyToOne
		@JoinColumn(name = "propertyId")
		@JsonIgnore
		private Property property;
		
		@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE ,CascadeType.REMOVE})
		@JoinColumn(name = "paymentId", referencedColumnName = "id")
	 	private PaymentDetail paymentDetail;
		
		
		private long numberOfPeoplesStay;
		 
		 
}
