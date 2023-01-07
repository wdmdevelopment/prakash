package com.wdm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Items;
import com.wdm.model.RequestItems;
import com.wdm.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	ItemService itemservice;
	
	@PostMapping
	
	public ResponseEntity<Items> saveItem(@Valid @RequestBody RequestItems resquestProduct) {

		return new ResponseEntity<> (itemservice.saveItems(resquestProduct), HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
