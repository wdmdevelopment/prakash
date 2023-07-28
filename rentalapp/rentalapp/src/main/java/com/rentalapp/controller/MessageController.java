package com.rentalapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.entity.PropertyMessages;
import com.rentalapp.model.RequestGetAllMessage;
import com.rentalapp.model.RequestPropertyMessage;
import com.rentalapp.service.MessageService;

@RestController
@RequestMapping("/api/message")
@CrossOrigin("*")
public class MessageController {
	
	private static final Logger logger = LogManager.getLogger(MessageController.class);
	
		@Autowired
		MessageService messageService;
	
	
	@PostMapping
	public ResponseEntity<?> saveQuestions(@Valid @RequestBody RequestPropertyMessage message) {
		logger.info("message created successfully");
		return new ResponseEntity<PropertyMessages>(messageService.sendMessage(message),
				HttpStatus.CREATED);
	}

	 

	@GetMapping
	public ResponseEntity<List<PropertyMessages>> getAllQuestions(@Valid @RequestBody RequestGetAllMessage requestMessage) {
		logger.info("Get all Questions  successfully");
		return new ResponseEntity<List<PropertyMessages>>(messageService.getAllMessagesUser(requestMessage), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteQuestion(@PathVariable("tenantId") Long tenantId) {
		logger.info("Questions successfully");
		messageService.deleteMessageTenantUser(tenantId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
}
