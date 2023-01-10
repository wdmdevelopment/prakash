package com.wdm.controller;

import java.util.ArrayList;
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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	@PostMapping
	public ResponseEntity<Items> saveItem(@Valid @RequestBody RequestItems resquestProduct) {
		logger.info("items saved");
		return new ResponseEntity<>(itemservice.saveItems(resquestProduct), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<RequestItems>> getItems(){
		
		logger.info("To get all data"+itemservice.getItems());
		
		List<Items> items = itemservice.getItems();
		
		List<RequestItems> reitem = new ArrayList<>();
		
		for(Items item : items) {
			
			RequestItems reqitem = new RequestItems();
			reqitem.setQuantity(item.getQuantity());
			reqitem.setPrice(item.getPrice());
			reitem.add(reqitem);
		}
		
		
		return new ResponseEntity<>(reitem, HttpStatus.OK);
	 
	}
	
	
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Items> getBookingById(@PathVariable("id") long id) throws Exception {
		
		logger.info("To get item data"+itemservice.getItemsByid(id));
		
		return new ResponseEntity<>(itemservice.getItemsByid(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<Void> deleteItembyid(@PathVariable("id") long id) {
		
		logger.info("To get item data itemservice.deleteByid(id)");
		
		itemservice.deleteByid(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	

	
	
	
	
	
	
	@GetMapping("/maxPrice/{price}")
	public ResponseEntity<Items> maxPrice(@PathVariable("price") double price) {
		
		logger.info("To get Maximum item data"+ itemservice.getMaxPrice(price));
		
		Items theitem = (Items) itemservice.getMaxPrice(price);
		if(theitem==null) {
			logger.info("The given price is null"+ price);
			throw new IdNotFoundException("id not found");
		}
		return new ResponseEntity<Items>(theitem, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	

}
