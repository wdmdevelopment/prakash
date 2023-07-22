package com.rentalapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "propertyquestions")
@Data
public class PropertyQuestions {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String questions;
	
	private String answer;
	
	private LocalDateTime addedAt;
	
	@ManyToOne
	@JoinColumn(name = "propertyId")
	@JsonIgnore
	private Property property;
}
