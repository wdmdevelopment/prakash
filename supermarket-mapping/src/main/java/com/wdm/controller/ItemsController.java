package com.wdm.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Items;
import com.wdm.exception.IdNotFoundException;
import com.wdm.model.RequestItems;
import com.wdm.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	ItemService itemservice;
	
	private static final Logger logger = LogManager.getLogger(ItemsController.class);
	
	
	@PostMapping("/save")
	public ResponseEntity<Items> saveItem(@Valid @RequestBody RequestItems resquestProduct) {
		logger.info("Info level logger");
		return new ResponseEntity<>(itemservice.saveItems(resquestProduct), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<List<Items>> getItems(){
		 
		return new ResponseEntity<>(itemservice.getItems(), HttpStatus.OK);
	 
	}
	
	@GetMapping("/get/id")
	public ResponseEntity<Items> getBookingById(@PathVariable("id") long id) throws Exception {
		
		return new ResponseEntity<>(itemservice.getItemsByid(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/id")
	
	public void deleteItembyid(@PathVariable("id") long id) {
		
		itemservice.deleteByid(id);
	}
	
	
	

	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Items> reduceQuantity(@PathVariable("id") long id, @RequestParam int quantity) {
		
		Items theitem = itemservice.reduceQuantity(id, quantity);
		
		if(theitem==null) {
			throw new IdNotFoundException("id not found");
		}
		return new ResponseEntity<Items>(theitem, HttpStatus.OK);
	}

	@PutMapping("/addQuantity/{id}")
	public ResponseEntity<Items> addQuantity(@PathVariable("id") long id, @RequestParam int quantity) {

		Items theitem = itemservice.reduceQuantity(id, quantity);
		if(theitem==null) {
			throw new IdNotFoundException("id not found");
		}
		return new ResponseEntity<Items>(theitem, HttpStatus.OK);
	}
	
	
	
	
	
	
	@GetMapping("/maxPrice/{price}")
	public ResponseEntity<Items> maxPrice(@PathVariable("price") double price) {

		Items theitem = (Items) itemservice.getMaxPrice(price);
		if(theitem==null) {
			throw new IdNotFoundException("id not found");
		}
		return new ResponseEntity<Items>(theitem, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	

}
