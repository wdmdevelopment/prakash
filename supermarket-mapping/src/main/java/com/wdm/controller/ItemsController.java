package com.wdm.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Cart;
import com.wdm.entity.Items;
import com.wdm.model.RequestItems;
import com.wdm.response.CartResponse;
import com.wdm.service.ItemService;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	ItemService itemservice;
	
	private static final Logger logger = LogManager.getLogger(ItemsController.class);
	
	
	@PostMapping
	public ResponseEntity<Cart> saveItem(@Valid @RequestBody RequestItems resquestItems) {
		
		//logger.info("items saved, Quantity={}, totalPrice={}", resquestItems.getQuantity());
		
		return new ResponseEntity<Cart>(itemservice.saveItems(resquestItems), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<CartResponse>> getItems(){
		
		logger.info("To get all products");
		
		List<Items> items = itemservice.getItems();
		
		List<CartResponse> reitem = new ArrayList<CartResponse>();
		
		for(Items item : items) {
			 
			CartResponse reqitem = new CartResponse();
			reqitem.setQuantity(item.getQuantity());
			//reqitem.setTotalPrice(item.getTotalPrice());
			
			 
			reqitem.setProductId(item.getProduct().getProductId());
			reitem.add(reqitem);
		}
		
		
		return new ResponseEntity<>(reitem, HttpStatus.OK);
	 
	}
	
	 
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Items> getBookingById(@PathVariable("id") long id) throws Exception {
		
		logger.info("To get item data", id);
		
		return new ResponseEntity<>(itemservice.getItemsByid(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{itemId}/{cartid}")
	
	public ResponseEntity<?> deleteItembyid(@PathVariable("itemId") long itemId, @PathVariable("cartid") long cartid) {
		
		logger.info("To get item data itemservice.deleteByid(id)");
			
		System.out.println("-----------93----------");
		itemservice.deleteByid(itemId, cartid);
		
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}
	
	
	
 
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateitems( @PathVariable("id") long id, @RequestBody RequestItems item) {

		itemservice.updatecategory(item, id);
	
		logger.info("update the item" + id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
	
	

}
