package com.rentalapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "search_History")
@Data

public class SearchHistory {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private Long maxNoOfPersonsAllowed;

	private LocalDateTime serachedAt;
	
	private String roomType;
	
	private String propertyName;
	
	private Integer noOfBeds;
	
	@ManyToOne
    @JoinColumn(name = "userId")
	private User user;
	
	
}
