package com.rentalapp.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.entity.PropertyReservation;
import com.rentalapp.model.RequestReservation;
import com.rentalapp.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin("*")
public class PropertyReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	
	private static final Logger logger = LogManager.getLogger(PropertyQuestionController.class);
	
	@PostMapping
	public ResponseEntity<PropertyReservation> saveQuestions(@Valid @RequestBody RequestReservation requestReservation) {
		logger.info("Questions successfully created");
		return new ResponseEntity<PropertyReservation>(reservationService.reserveProperty(requestReservation),
				HttpStatus.CREATED);
	}
}
